#!/usr/bin/env python3
"""Stage editable source from the patched baseline; never overwrite maintained Java.

One-time recovery tool, not part of the source build. Requires Vineflower 1.12.0
(Apache-2.0) supplied locally via --vineflower. Intermediate JARs are test oracles,
not runtime dependencies. Symbol remapping is checked by an inverse round-trip.
"""
import argparse
import json
import os
from pathlib import Path
import re
import subprocess
import zipfile

from rebuild_reference import ROOT, ORIGINAL, EMULATOR, descriptor, ensure_jdk_environment


def run(*args, capture=False):
    result = subprocess.run(list(map(str, args)), check=True, text=True,
                            stdout=subprocess.PIPE if capture else None)
    return result.stdout


def recover(destination, vineflower):
    ensure_jdk_environment()
    if destination.exists():
        raise SystemExit(f'Refusing to overwrite {destination}')
    destination.mkdir(parents=True)
    baseline = destination / 'patched-baseline.jar'
    patches = {p.relative_to(ROOT / 'reference/runtime-patches').as_posix(): p.read_bytes()
               for p in (ROOT / 'reference/runtime-patches').rglob('*.class')}
    with zipfile.ZipFile(ORIGINAL) as source, zipfile.ZipFile(baseline, 'w') as out:
        for item in source.infolist():
            if not item.is_dir():
                out.writestr(item.filename, patches.get(item.filename, source.read(item)))
    stage = destination / 'tools'
    stage.mkdir()
    run('javac', '--release', '8', '-cp', EMULATOR, '-d', stage,
        ROOT / 'tools/ReadableReference.java', ROOT / 'tools/SourceInventory.java')
    cp = os.pathsep.join(map(str, [stage, EMULATOR]))
    inventory = run('java', '-cp', cp, 'SourceInventory', baseline, capture=True)
    (destination / 'inventory.tsv').write_text(inventory)
    registry = json.loads((ROOT / 'tools/reference-names.json').read_text())
    classes = {old: (new if '/' in new else 'game/' + new)
               for old, new in registry['classes'].items()}
    classes.update({old: new.replace('a/', 'game/billing/', 1)
                    for old, new in classes.items() if new.startswith('a/')})
    fields = {tuple(row[:3]): row[3] for row in registry['fields']}
    methods = {tuple(row[:3]): row[3] for row in registry['methods']}
    methods.update({
        ('game/d', 'c', '(I)Lgame/b;'): 'getBattlePet',
        ('game/g', 'a', '(IB)I'): 'countItem',
        ('game/g', 'a', '(BI)B'): 'getCollectionFlag',
        ('a/h', 'a', '(Ljava/lang/Object;Ljava/lang/Object;)V'): 'put',
        ('a/h', 'a', '(ILjava/lang/Object;)V'): 'setKeyAt',
    })
    # JVM allows return-type-only overloads and repeated field names. Java does
    # not. Use descriptor-specific neutral names only for those source clashes.
    declarations = [line.split('\t') for line in inventory.splitlines()]
    groups = {}
    for kind, owner, name, desc, *rest in declarations:
        if kind == 'class' or name.startswith('<'):
            continue
        renamed = (fields if kind == 'field' else methods).get((owner, name, desc), name)
        signature = desc.split(')')[0] if kind == 'method' else ''
        groups.setdefault((kind, owner, renamed, signature), []).append((owner, name, desc))
    for (kind, owner, name, signature), members in groups.items():
        if len(members) > 1:
            target = fields if kind == 'field' else methods
            for member in members:
                suffix = re.sub(r'[^A-Za-z0-9]', '_', member[2])
                target[member] = name + '_' + suffix
    rows = [['class', old, new] for old, new in classes.items()]
    rows += [['field', *key, value] for key, value in fields.items()]
    rows += [['method', *key, value] for key, value in methods.items()]
    # No synthetic parameter metadata: legacy bytecode reuses argument slots.
    mapping = destination / 'source-names.tsv'
    mapping.write_text(''.join('\t'.join(row) + '\n' for row in rows))
    oracle = destination / 'source-oracle.jar'
    run('java', '-cp', cp, 'ReadableReference', baseline, mapping, oracle)
    effective = Path(str(oracle) + '.names.tsv').read_text()
    mapping.write_text(effective)
    inverse = []
    for line in effective.splitlines():
        values = line.split('\t')
        if values[0] == 'class':
            inverse.append(['class', values[2], values[1]])
        else:
            kind, owner, name, desc, new = values
            inverse.append([kind, classes.get(owner, owner), new, descriptor(desc, classes), name])
    reverse = destination / 'inverse.tsv'
    reverse.write_text(''.join('\t'.join(row) + '\n' for row in inverse))
    restored = destination / 'restored.jar'
    run('java', '-cp', cp, 'ReadableReference', oracle, reverse, restored)
    run('java', '-cp', cp, 'ReadableReference', '--compare', baseline, restored)
    run('java', '-jar', vineflower, '--folder', '--verify-merges=true',
        '--verify-pre-post-merges=true', '--simplify-stack=false', '--boolean-as-int=false',
        '--bytecode-source-mapping=true', '-e=' + str(EMULATOR), oracle, destination / 'java')
    print(f'Recovered source staged at {destination}; review before copying into src/main/java.')


if __name__ == '__main__':
    parser = argparse.ArgumentParser(description=__doc__)
    parser.add_argument('--output', type=Path, default=ROOT / 'build/source-recovery')
    parser.add_argument('--vineflower', type=Path, required=True)
    args = parser.parse_args()
    recover(args.output.resolve(), args.vineflower.resolve())
