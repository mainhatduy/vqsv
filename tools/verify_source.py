#!/usr/bin/env python3
"""Compare source runtime with the preserved patched game, using isolated emulator saves.

This optional verification uses original bytecode as an oracle. project.py build
does not depend on the oracle, reference patches, mapping, or any decompiler.
"""
from collections import Counter
import hashlib
import json
import os
from pathlib import Path
import shutil
import subprocess
import sys
import tempfile
import zipfile

ROOT = Path(__file__).resolve().parents[1]
sys.path.insert(0, str(ROOT))
import project


def compare(baseline, source):
    original = baseline.read_text().splitlines()
    actual = source.read_text().splitlines()
    if len(original) != len(actual):
        raise AssertionError(f'Case counts differ: {len(original)} vs {len(actual)}')
    differences = [(a, b) for a, b in zip(original, actual) if a != b]
    if differences:
        raise AssertionError(f'{len(differences)} differences; first: {differences[0]}')
    return {
        'cases': len(original),
        'matched_successes': sum('\tERROR:' not in line for line in original),
        'matched_baseline_exceptions': sum('\tERROR:' in line for line in original),
        'groups': dict(Counter(line.split('/')[0].split('\t')[0] for line in original)),
    }


def verify():
    project.ensure_jdk_environment()
    project.build()
    destination = ROOT / 'build/source-verification'
    with tempfile.TemporaryDirectory(prefix='vqsv-source-check-', dir=ROOT / 'build') as temporary:
        stage = Path(temporary)
        oracle = stage / 'baseline.jar'
        patches = {p.relative_to(ROOT / 'reference/runtime-patches').as_posix(): p.read_bytes()
                   for p in (ROOT / 'reference/runtime-patches').rglob('*.class')}
        with zipfile.ZipFile(project.ORIGINAL) as original, zipfile.ZipFile(oracle, 'w') as output:
            for item in original.infolist():
                if not item.is_dir():
                    output.writestr(item.filename, patches.get(item.filename, original.read(item)))
        classes = stage / 'tools'
        subprocess.run(['javac', '--release', '8', '-cp', str(project.EMULATOR), '-d', str(classes),
                        str(ROOT / 'tests/SourceParityProbe.java')], check=True)
        cp = os.pathsep.join(map(str, [classes, project.EMULATOR]))
        tasks = []
        for mode in ('cases', 'flow', 'startup'):
            for variant, jar, mapping in [('baseline', oracle, '-'),
                                         ('source', project.OUTPUT, str(ROOT / 'tools/source-names.tsv'))]:
                if mode == 'startup' and variant == 'baseline':
                    continue
                work = stage / mode / variant
                work.mkdir(parents=True)
                log = (work / 'runtime.log').open('w')
                command = ['java', '-Dfile.encoding=ISO_8859_1', '-Djava.awt.headless=true',
                           '-cp', cp, 'SourceParityProbe', str(jar), mapping, str(work / 'cases.tsv')]
                if mode != 'cases':
                    command.append(mode)
                process = subprocess.Popen(command, cwd=work, stdout=log, stderr=subprocess.STDOUT)
                tasks.append((process, log, work))
        try:
            for process, log, work in tasks:
                result = process.wait(timeout=180)
                log.close()
                if result:
                    failed = ROOT / 'build/source-verification-failed'
                    if failed.exists():
                        shutil.rmtree(failed)
                    shutil.copytree(stage, failed)
                    detail = (work / 'runtime.log').read_text(errors='replace')[-3500:]
                    raise AssertionError(f'Probe exited {result}: {failed}\n{detail}')
                if not (work / 'cases.tsv').is_file():
                    raise AssertionError(f'Probe exited without results: {work}')
        finally:
            for process, log, work in tasks:
                if process.poll() is None:
                    process.kill()
                    process.wait()
                if not log.closed:
                    log.close()
        # Keep all evidence before comparison, including a failing assertion.
        if destination.exists():
            shutil.rmtree(destination)
        shutil.copytree(stage, destination)
        report = {}
        for mode in ('cases', 'flow'):
            report[mode] = compare(stage / mode / 'baseline/cases.tsv', stage / mode / 'source/cases.tsv')
        states = {line.split('\t')[1].split('/')[0]
                  for line in (stage / 'flow/source/cases.tsv').read_text().splitlines() if line.startswith('flow/')}
        if not {'8', '11', '12', '13', '10'} <= states:
            raise AssertionError(f'Flow did not cover title, overworld, battle and return: {states}')
        report['flow']['states'] = sorted(map(int, states))
        report['startup'] = (stage / 'startup/source/cases.tsv').read_text().splitlines()
        report['source_jar_sha256'] = hashlib.sha256(project.OUTPUT.read_bytes()).hexdigest()
        (stage / 'report.json').write_text(json.dumps(report, indent=2) + '\n')
        if destination.exists():
            shutil.rmtree(destination)
        shutil.copytree(stage, destination)
        print(json.dumps(report, indent=2))
        print(f'Logs, results and rendered samples: {destination}')


if __name__ == '__main__':
    verify()
