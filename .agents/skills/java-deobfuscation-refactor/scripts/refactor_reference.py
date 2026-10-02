#!/usr/bin/env python3
"""Create a verified readable reference from a legacy Java JAR.

Requires Python 3, JDK 9+ (javac --release 8), CFR, and an ASM 3.x-compatible
classpath exposing ClassAdapter/MethodAdapter. Output must be a new directory.
No dependencies are downloaded; no application or existing source is modified.
"""
import argparse
import hashlib
import json
import os
from pathlib import Path
import re
import shutil
import struct
import subprocess
import tempfile
import zipfile

HERE = Path(__file__).resolve().parent
# The legacy visitor remaps these attributes; anything else needs a richer backend.
SUPPORTED_ATTRIBUTES = {
    'Code', 'ConstantValue', 'Exceptions', 'SourceFile', 'SourceDebugExtension',
    'LineNumberTable', 'LocalVariableTable', 'StackMap', 'StackMapTable',
    'Synthetic', 'Deprecated',
}
JAVA_KEYWORDS = set(('abstract assert boolean break byte case catch char class const continue default '
                     'do double else enum extends final finally float for goto if implements import '
                     'instanceof int interface long native new package private protected public return '
                     'short static strictfp super switch synchronized this throw throws transient try '
                     'void volatile while true false null _').split())


class ClassBytes:
    def __init__(self, content):
        self.content = content
        self.position = 0

    def take(self, count):
        end = self.position + count
        if end > len(self.content):
            raise ValueError('Truncated class file')
        value = self.content[self.position:end]
        self.position = end
        return value

    def u2(self):
        return struct.unpack('>H', self.take(2))[0]

    def u4(self):
        return struct.unpack('>I', self.take(4))[0]


def check_class(content):
    """Reject unsupported metadata before the old ASM reader can silently retain it."""
    reader = ClassBytes(content)
    if reader.u4() != 0xCAFEBABE:
        raise ValueError('Invalid class magic')
    minor, major = reader.u2(), reader.u2()
    if not 45 <= major <= 52 or minor == 65535:
        raise ValueError(f'Unsupported class version {major}.{minor}; use modern ASM')
    pool_size = reader.u2()
    strings = {}
    index = 1
    while index < pool_size:
        tag = reader.take(1)[0]
        if tag == 1:
            # Attribute names are ASCII. Other UTF8 entries may use JVM modified UTF8.
            strings[index] = reader.take(reader.u2())
        elif tag in (3, 4):
            reader.take(4)
        elif tag in (5, 6):
            reader.take(8)
            index += 1
        elif tag in (7, 8):
            reader.take(2)
        elif tag in (9, 10, 11, 12):
            reader.take(4)
        else:
            raise ValueError(f'Unsupported constant-pool tag {tag}; use modern ASM')
        index += 1

    def attributes(cursor):
        for _ in range(cursor.u2()):
            name = strings.get(cursor.u2(), b'').decode('ascii')
            body = cursor.take(cursor.u4())
            if name not in SUPPORTED_ATTRIBUTES:
                raise ValueError(f'Unsupported attribute {name!r}; use a metadata-aware backend')
            if name == 'Code':
                code = ClassBytes(body)
                code.take(4)  # max stack, max locals
                code.take(code.u4())
                code.take(code.u2() * 8)  # exception table
                attributes(code)
                if code.position != len(body):
                    raise ValueError('Malformed Code attribute')

    access = reader.u2()
    reader.take(4)  # this class, superclass
    reader.take(reader.u2() * 2)  # interfaces
    for kind in range(2):  # fields, methods
        for _ in range(reader.u2()):
            flags, name_index, _ = reader.u2(), reader.u2(), reader.u2()
            if kind == 1 and access & 0x0200 and not flags & 0x0400:
                if strings.get(name_index) != b'<clinit>':
                    raise ValueError('Static/default interface methods require modern ASM')
            attributes(reader)
    attributes(reader)
    if reader.position != len(content):
        raise ValueError('Trailing class bytes')


def internal_name(name):
    if not isinstance(name, str) or not name or name.startswith('/') or '\\' in name:
        raise ValueError(f'Invalid internal class name: {name!r}')
    if any(not re.fullmatch(r'[A-Za-z_$][A-Za-z0-9_$]*', part) for part in name.split('/')):
        raise ValueError(f'Use ASCII JVM internal names, e.g. package/ClassName: {name!r}')
    return name


def identifier(name, readable=False):
    if not isinstance(name, str) or not re.fullmatch(r'[A-Za-z_$][A-Za-z0-9_$]*', name):
        raise ValueError(f'Invalid readable identifier: {name!r}')
    if readable and name in JAVA_KEYWORDS:
        raise ValueError(f'Readable name is a Java keyword: {name}')
    return name


def descriptor(value, classes):
    return re.sub(r'L([^;]+);', lambda match: 'L' + classes.get(match[1], match[1]) + ';', value)


def load_mapping(path):
    mapping = json.loads(path.read_text(encoding='utf-8'))
    classes = mapping.get('classes', {})
    if not isinstance(classes, dict):
        raise ValueError('classes must be an object')
    for old, new in classes.items():
        internal_name(old)
        internal_name(new)
        for part in new.split('/'):
            identifier(part, readable=True)
    if len(set(classes.values())) != len(classes):
        raise ValueError('Duplicate target class')
    rows = [['class', old, new] for old, new in classes.items()]
    keys = set()
    for kind in ('field', 'method', 'parameters'):
        entries = mapping.get(kind if kind == 'parameters' else kind + 's', [])
        for entry in entries:
            if not isinstance(entry, list) or len(entry) != 4:
                raise ValueError(f'{kind}: expected [owner, originalName, descriptor, readableName/names]')
            owner, name, desc, target = entry
            internal_name(owner)
            if name not in ('<init>', '<clinit>'):
                identifier(name)
            if not isinstance(desc, str) or not desc or any(char in desc for char in '\t\r\n'):
                raise ValueError(f'Invalid descriptor: {desc!r}')
            if kind != 'parameters' and name.startswith('<'):
                raise ValueError('Constructors/static initializers cannot be renamed')
            key = kind, owner, name, desc
            if key in keys:
                raise ValueError(f'Duplicate symbol: {key}')
            keys.add(key)
            if kind == 'parameters':
                if not isinstance(target, list) or not target:
                    raise ValueError('parameters must contain a nonempty list of argument names')
                for argument in target:
                    identifier(argument, readable=True)
                if len(set(target)) != len(target):
                    raise ValueError('Duplicate parameter names')
                rows.append([kind, owner, name, desc, *target])
            else:
                identifier(target, readable=True)
                rows.append([kind, owner, name, desc, target])
    return classes, rows


def run(*args):
    subprocess.run(list(map(str, args)), check=True)


def regenerate(args):
    source = args.input.resolve()
    destination = args.output.resolve()
    if destination.exists():
        raise ValueError(f'Output already exists: {destination}; choose a new directory')
    for file in (source, args.mapping, args.cfr):
        if not file.is_file():
            raise ValueError(f'Missing file: {file}')
    digest = hashlib.sha256(source.read_bytes()).hexdigest()
    if args.expected_sha256 and args.expected_sha256.lower() != digest:
        raise ValueError('Input SHA-256 differs from the expected value')
    mapping_digest = hashlib.sha256(args.mapping.read_bytes()).hexdigest()
    classes, rows = load_mapping(args.mapping)
    with zipfile.ZipFile(source) as jar:
        entries = jar.namelist()
        if len(entries) != len(set(entries)):
            raise ValueError('Duplicate ZIP entry names')
        for entry in entries:
            if entry.endswith('.class'):
                try:
                    check_class(jar.read(entry))
                except ValueError as error:
                    raise ValueError(f'{entry}: {error}') from error
        expected_classes = {classes.get(entry[:-6], entry[:-6]) + '.java'
                            for entry in entries if entry.endswith('.class')}
    with tempfile.TemporaryDirectory(prefix='java-symbol-refactor-') as temp:
        stage = Path(temp)
        tsv = stage / 'input-names.tsv'
        tsv.write_text(''.join('\t'.join(row) + '\n' for row in rows), encoding='utf-8')
        tool_classes = stage / 'tool-classes'
        run('javac', '--release', '8', '-encoding', 'UTF-8', '-cp', args.asm_classpath,
            '-d', tool_classes, HERE / 'ReadableReference.java')
        cp = os.pathsep.join([str(tool_classes), args.asm_classpath])
        result = stage / 'result'
        result.mkdir()
        readable = result / 'readable-reference.jar'
        run('java', '-cp', cp, 'ReadableReference', source, tsv, readable)
        effective_path = Path(str(readable) + '.names.tsv')
        effective = effective_path.read_text(encoding='utf-8')
        inverse = []
        for line in effective.splitlines():
            values = line.split('\t')
            if values[0] == 'class':
                inverse.append(['class', values[2], values[1]])
            else:
                kind, owner, name, desc, target = values
                inverse.append([kind, classes.get(owner, owner), target,
                                descriptor(desc, classes), name])
        reverse = stage / 'inverse.tsv'
        reverse.write_text(''.join('\t'.join(row) + '\n' for row in inverse), encoding='utf-8')
        restored = stage / 'restored.jar'
        run('java', '-cp', cp, 'ReadableReference', readable, reverse, restored)
        run('java', '-cp', cp, 'ReadableReference', '--compare', source, restored)
        decompiled = result / 'decompiled'
        command = ['java', '-jar', args.cfr, readable, '--outputdir', decompiled, '--silent', 'true']
        if args.source_classpath:
            command.extend(['--extraclasspath', args.source_classpath])
        run(*command)
        actual = {p.relative_to(decompiled).as_posix() for p in decompiled.rglob('*.java')}
        if actual != expected_classes:
            raise ValueError(f'Decompiled class set differs: {actual ^ expected_classes}')
        for file in decompiled.rglob('*.java'):
            body = '\n'.join(line.rstrip() for line in file.read_text().splitlines()) + '\n'
            file.write_text('/* Generated readable reference; not a runtime replacement.\n'
                            ' * Unmapped symbols and decompiler errors require further inspection.\n'
                            ' */\n' + body, encoding='utf-8')
        summary = decompiled / 'summary.txt'
        if summary.exists():
            lines = summary.read_text(encoding='utf-8').splitlines()
            if lines:
                lines[0] = 'Summary for readable-reference.jar'
                summary.write_text('\n'.join(lines) + '\n', encoding='utf-8')
        effective_path.rename(result / 'names.tsv')
        if hashlib.sha256(args.mapping.read_bytes()).hexdigest() != mapping_digest:
            raise ValueError('Mapping changed during processing')
        (result / 'mapping.json').write_text(args.mapping.read_text(encoding='utf-8'), encoding='utf-8')
        report = {'input_sha256': digest, 'mapping_sha256': mapping_digest, 'input_name': source.name,
                  'class_count': len(actual), 'mapped_class_count': len(classes),
                  'mapped_field_count': sum(line.startswith('field\t') for line in effective.splitlines()),
                  'mapped_method_count': sum(line.startswith('method\t') for line in effective.splitlines()),
                  'round_trip': 'passed; canonical class bytes excluding debug, exact resource bytes',
                  'runtime_verified': False, 'purpose': 'reference-only'}
        (result / 'verification.json').write_text(json.dumps(report, indent=2) + '\n', encoding='utf-8')
        # Detect inputs edited concurrently before publishing staged results.
        if hashlib.sha256(source.read_bytes()).hexdigest() != digest:
            raise ValueError('Input changed during processing')
        destination.parent.mkdir(parents=True, exist_ok=True)
        shutil.copytree(result, destination)
        print(f'Verified reference: {destination}')


def main():
    parser = argparse.ArgumentParser(description=__doc__)
    parser.add_argument('--input', required=True, type=Path, help='Original JAR; never modified')
    parser.add_argument('--mapping', required=True, type=Path, help='Owner/name/descriptor JSON mapping')
    parser.add_argument('--output', required=True, type=Path, help='New directory for verified artifacts')
    parser.add_argument('--asm-classpath', required=True, help='ASM 3.x-compatible JAR(s), joined with OS path separator')
    parser.add_argument('--cfr', required=True, type=Path, help='CFR executable JAR')
    parser.add_argument('--source-classpath', help='Libraries for decompiler type resolution')
    parser.add_argument('--expected-sha256', help='Optional required hash of original input')
    args = parser.parse_args()
    try:
        regenerate(args)
    except (ValueError, OSError, subprocess.CalledProcessError, zipfile.BadZipFile) as error:
        parser.exit(1, f'Reference refactor failed: {error}\n')


if __name__ == '__main__':
    main()
