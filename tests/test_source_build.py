"""Check that ordinary compilation and packaging cannot fall back to binary game classes."""
from pathlib import Path
import os
import subprocess
import sys
import tempfile
import unittest
from unittest.mock import patch
import zipfile

ROOT = Path(__file__).resolve().parents[1]
sys.path.insert(0, str(ROOT))
import project


class SourceBuildTest(unittest.TestCase):
    def test_build_without_original_jar_or_binary_patches(self):
        with tempfile.TemporaryDirectory(prefix='vqsv-source-build-') as temporary:
            output = Path(temporary) / 'game.jar'
            with patch.object(project, 'ORIGINAL', Path(temporary) / 'missing-original.jar'), \
                 patch.object(project, 'OUTPUT', output):
                project.build()
            self.assertFalse(list((ROOT / 'src/main/resources').rglob('*.class')))
            expected_classes = set()
            for line in (ROOT / 'tools/source-names.tsv').read_text().splitlines():
                row = line.split('\t')
                if row[0] == 'class':
                    expected_classes.add(row[2] + '.class')
            self.assertEqual(68, len(expected_classes))
            with zipfile.ZipFile(output) as jar:
                packaged = set(jar.namelist())
                self.assertTrue(expected_classes <= packaged)
                self.assertNotIn('game/b.class', packaged)
                self.assertNotIn('an.class', packaged)
                for path in (ROOT / 'src/main/resources').rglob('*'):
                    if path.is_file() and not path.name.startswith('.'):
                        self.assertEqual(path.read_bytes(), jar.read(path.relative_to(ROOT / 'src/main/resources').as_posix()))
                self.assertIn(b'game.GameMIDLet', jar.read('META-INF/MANIFEST.MF'))

    def test_waiting_dialogue_recovers_missing_pages(self):
        with tempfile.TemporaryDirectory(prefix='vqsv-dialogue-recovery-') as temporary:
            work = Path(temporary)
            jar = work / 'game.jar'
            with patch.object(project, 'OUTPUT', jar):
                project.build()
            classes = work / 'checks'
            subprocess.run(['javac', '--release', '8', '-encoding', 'UTF-8',
                            '-cp', str(project.EMULATOR), '-d', str(classes),
                            str(ROOT / 'tests/SourceParityProbe.java'),
                            str(ROOT / 'tests/DialogueRecoveryCheck.java')], check=True,
                           capture_output=True, text=True)
            result = subprocess.run(['java', '-Djava.awt.headless=true',
                                     '-Dfile.encoding=ISO_8859_1', '-cp',
                                     os.pathsep.join(map(str, [classes, project.EMULATOR])),
                                     'DialogueRecoveryCheck', str(jar),
                                     str(ROOT / 'tools/source-names.tsv')], cwd=work,
                                    capture_output=True, text=True, timeout=30)
            self.assertEqual(0, result.returncode, result.stdout + result.stderr)
            self.assertIn('Waiting dialogue recovery, all pages and script completion: OK',
                          result.stdout)


if __name__ == '__main__':
    unittest.main()
