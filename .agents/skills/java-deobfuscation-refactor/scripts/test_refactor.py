#!/usr/bin/env python3
"""Run isolated behavioral tests for the skill's actual CLI; never start an application."""
import argparse
import hashlib
import json
import os
from pathlib import Path
import subprocess
import sys
import tempfile
import zipfile

HERE = Path(__file__).resolve().parent


def run(*args, check=True):
    return subprocess.run(list(map(str, args)), check=check, capture_output=True, text=True)


def test(args):
    asm = os.pathsep.join(str(Path(entry).resolve()) for entry in args.asm_classpath.split(os.pathsep))
    cfr = args.cfr.resolve()
    with tempfile.TemporaryDirectory(prefix='java-refactor-fixture-') as temp:
        folder = Path(temp)
        sources = {
            'sample/a.java': '''package sample;
public class a implements i {
 public int a = 7;
 public int a(int x) { return a + x; }
 public int a(String x) { return x.length(); }
 private int b(int x) { return x + 100; }
 public int c() { return b(1); }
}''',
            'sample/b.java': '''package sample;
public class b extends a {
 public int a(int x) { return super.a(x) * 2; }
 public int b(int x) { return x + 200; }
 public int d() { return this.a; }
}''',
            'sample/i.java': 'package sample; public interface i { int a(int x); }',
            'sample/Check.java': '''package sample;
public class Check {
 public static void main(String[] args) {
  b child = new b(); i listener = child;
  if (listener.a(3) != 20 || child.a("abc") != 3 || child.c() != 101
      || child.b(1) != 201 || child.d() != 7) throw new AssertionError();
  System.out.println("sample.a; asset a b unchanged");
 }
}''',
        }
        for relative, source in sources.items():
            path = folder / relative
            path.parent.mkdir(parents=True, exist_ok=True)
            path.write_text(source, encoding='utf-8')
        classes = folder / 'classes'
        run('javac', '--release', '8', '-g:none', '-d', classes,
            *(folder / path for path in sources))
        jar_path = folder / 'original.jar'
        with zipfile.ZipFile(jar_path, 'w') as jar:
            for file in classes.rglob('*.class'):
                jar.write(file, file.relative_to(classes).as_posix())
            jar.writestr('asset.txt', b'a b sample.a')
        original_hash = hashlib.sha256(jar_path.read_bytes()).hexdigest()
        mapping = {
            'classes': {'sample/a': 'sample/CounterBase', 'sample/b': 'sample/CounterChild',
                        'sample/i': 'sample/CounterListener'},
            'fields': [['sample/a', 'a', 'I', 'count']],
            'methods': [['sample/i', 'a', '(I)I', 'addCount'],
                        ['sample/a', 'a', '(Ljava/lang/String;)I', 'textLength'],
                        ['sample/a', 'b', '(I)I', 'privateCalculation'],
                        ['sample/a', 'c', '()I', 'callPrivateCalculation'],
                        ['sample/b', 'b', '(I)I', 'childCalculation'],
                        ['sample/b', 'd', '()I', 'getInheritedCount']],
            'parameters': [['sample/a', 'a', '(I)I', ['amount']]],
        }
        names = folder / 'mapping.json'
        names.write_text(json.dumps(mapping), encoding='utf-8')
        command = [sys.executable, HERE / 'refactor_reference.py', '--input', jar_path,
                   '--mapping', names, '--asm-classpath', asm, '--cfr', cfr,
                   '--expected-sha256', original_hash]
        output = folder / 'reference'
        completed = run(*command, '--output', output)
        if 'Round-trip verified:' not in completed.stdout:
            raise AssertionError('Round-trip did not run')
        before = run('java', '-cp', jar_path, 'sample.Check').stdout
        after = run('java', '-cp', output / 'readable-reference.jar', 'sample.Check').stdout
        if before != after or before.strip() != 'sample.a; asset a b unchanged':
            raise AssertionError('Dispatch, inheritance, private homonym or literal behavior changed')
        effective = (output / 'names.tsv').read_text(encoding='utf-8')
        if 'method\tsample/b\ta\t(I)I\taddCount' not in effective:
            raise AssertionError('Interface/override name was not propagated')
        with zipfile.ZipFile(output / 'readable-reference.jar') as jar:
            if jar.read('asset.txt') != b'a b sample.a':
                raise AssertionError('Asset changed')
        before_outputs = {p.relative_to(output).as_posix(): p.read_bytes()
                          for p in output.rglob('*') if p.is_file()}
        refused = run(*command, '--output', output, check=False)
        if refused.returncode == 0 or 'Output already exists' not in refused.stderr:
            raise AssertionError('Existing output was not protected')
        after_outputs = {p.relative_to(output).as_posix(): p.read_bytes()
                         for p in output.rglob('*') if p.is_file()}
        if before_outputs != after_outputs:
            raise AssertionError('Refused operation changed existing output')
        # A descriptor typo must fail with no published output.
        mapping['fields'][0][2] = 'J'
        names.write_text(json.dumps(mapping), encoding='utf-8')
        rejected = folder / 'invalid-descriptor'
        refused = run(*command, '--output', rejected, check=False)
        if refused.returncode == 0 or rejected.exists() or 'Missing field' not in refused.stderr:
            raise AssertionError('Invalid member descriptor was not rejected')
        # Naming an absent argument-bearing method must not silently do nothing.
        mapping['fields'][0][2] = 'I'
        mapping['parameters'] = [['sample/a', 'missing', '(I)I', ['amount']]]
        names.write_text(json.dumps(mapping), encoding='utf-8')
        rejected = folder / 'invalid-parameter-owner'
        refused = run(*command, '--output', rejected, check=False)
        if refused.returncode == 0 or rejected.exists() or 'Missing parameter method' not in refused.stderr:
            raise AssertionError('Missing parameter method was not rejected')
        # Metadata needing a richer remapper must fail before publication.
        generic = folder / 'generic' / 'sample' / 'Generic.java'
        generic.parent.mkdir(parents=True)
        generic.write_text('package sample; public class Generic<T> { public T value; }', encoding='utf-8')
        generic_classes = folder / 'generic-classes'
        run('javac', '--release', '8', '-g:none', '-d', generic_classes, generic)
        generic_jar = folder / 'generic.jar'
        with zipfile.ZipFile(generic_jar, 'w') as jar:
            jar.write(generic_classes / 'sample/Generic.class', 'sample/Generic.class')
        names.write_text('{}', encoding='utf-8')
        rejected = folder / 'unsupported-metadata'
        refused = run(sys.executable, HERE / 'refactor_reference.py', '--input', generic_jar,
                      '--mapping', names, '--asm-classpath', asm, '--cfr', cfr,
                      '--output', rejected, check=False)
        if refused.returncode == 0 or rejected.exists() or 'Signature' not in refused.stderr:
            raise AssertionError('Unsupported metadata was not rejected')
        if hashlib.sha256(jar_path.read_bytes()).hexdigest() != original_hash:
            raise AssertionError('Original JAR changed')
        print('PASS: overloads, interface/override dispatch, inherited fields, private homonyms,')
        print('      literal/assets, inverse verification, output protection and failure checks.')


if __name__ == '__main__':
    parser = argparse.ArgumentParser(description=__doc__)
    parser.add_argument('--asm-classpath', required=True)
    parser.add_argument('--cfr', required=True, type=Path)
    test(parser.parse_args())
