#!/usr/bin/env python3
"""Local edit/build/run workflow. Requires Python 3 and the installed JDK."""
import argparse
import hashlib
import os
from pathlib import Path
import shutil
import subprocess
import tempfile
import zipfile

ROOT = Path(__file__).resolve().parent
ORIGINAL = ROOT / 'original/game.jar'
EMULATOR = ROOT.parent / 'emulator/freej2me_plus.jar'
OUTPUT = ROOT / 'build/vuong-quoc-sung-vat-dev.jar'


def build():
    expected = (ROOT / 'original/SHA256.txt').read_text().split()[0]
    if hashlib.sha256(ORIGINAL.read_bytes()).hexdigest() != expected:
        raise SystemExit('original/game.jar has changed; restore the original before building.')
    if not EMULATOR.is_file():
        raise SystemExit(f'Missing J2ME API/emulator: {EMULATOR}')
    OUTPUT.parent.mkdir(exist_ok=True)
    sources = sorted((ROOT / 'src/main/java').rglob('*.java'))
    with tempfile.TemporaryDirectory(prefix='vqsv-build-') as temp:
        classes = Path(temp) / 'classes'
        classes.mkdir()
        if sources:
            subprocess.run([
                'javac', '--release', '8', '-encoding', 'UTF-8', '-g',
                '-classpath', os.pathsep.join([str(ORIGINAL), str(EMULATOR)]),
                '-d', str(classes), *map(str, sources),
            ], check=True)
        replacements = {}
        for folder in [ROOT / 'src/main/resources', classes]:
            for file in sorted(folder.rglob('*')):
                if file.is_file() and not file.name.startswith('.'):
                    replacements[file.relative_to(folder).as_posix()] = file.read_bytes()
        staged = Path(temp) / 'game.jar'
        with zipfile.ZipFile(ORIGINAL) as original, zipfile.ZipFile(staged, 'w', zipfile.ZIP_DEFLATED) as output:
            for info in original.infolist():
                name = info.filename
                if name in replacements or info.is_dir():
                    continue
                if name.upper().startswith('META-INF/') and name.upper().endswith(('.SF', '.RSA', '.DSA', '.EC')):
                    continue
                output.writestr(name, original.read(info))
            for name, content in replacements.items():
                output.writestr(name, content)
        with zipfile.ZipFile(staged) as result:
            bad = result.testzip()
            if bad:
                raise SystemExit(f'Invalid output entry: {bad}')
        shutil.copyfile(staged, OUTPUT)
    print(f'Built: {OUTPUT}', flush=True)
    print(f'Compiled {len(sources)} source file(s); remaining classes come from original/game.jar.', flush=True)


def run():
    build()
    runtime = ROOT / 'runtime'
    settings = runtime / 'freej2me_system'
    settings.mkdir(parents=True, exist_ok=True)
    config = settings / 'freej2me.conf'
    if not config.exists():
        shared = ROOT.parent / 'freej2me_system/freej2me.conf'
        if shared.exists():
            shutil.copyfile(shared, config)
        else:
            config.write_text(''.join(f'input_Num{n}:{48+n}\n' for n in range(10)))
    root_speed = ROOT / 'speed.conf'
    runtime_speed = runtime / 'speed.conf'
    if root_speed.exists():
        if not runtime_speed.exists() or root_speed.stat().st_mtime > runtime_speed.stat().st_mtime:
            shutil.copyfile(root_speed, runtime_speed)
    print('Development saves are kept separately in runtime/.', flush=True)
    os.chdir(runtime)
    os.execvp('java', ['java', '-Dfile.encoding=ISO_8859_1', '-jar', str(EMULATOR), OUTPUT.as_uri(), '0', '240', '320', '2'])


def decompile():
    destination = ROOT / 'build/decompiled'
    if destination.exists():
        raise SystemExit('build/decompiled already exists. Rename it before generating a fresh copy.')
    subprocess.run(['java', '-jar', str(ROOT / 'tools/cfr-0.152.jar'), str(ORIGINAL),
                    '--outputdir', str(destination), '--extraclasspath', str(EMULATOR), '--silent', 'true'], check=True)
    print(f'Decompiled reference: {destination}')


if __name__ == '__main__':
    parser = argparse.ArgumentParser(description=__doc__)
    parser.add_argument('command', choices=['build', 'run', 'decompile'])
    args = parser.parse_args()
    try:
        {'build': build, 'run': run, 'decompile': decompile}[args.command]()
    except subprocess.CalledProcessError as error:
        raise SystemExit(error.returncode)
