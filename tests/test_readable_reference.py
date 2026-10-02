"""Behavioral checks for owner/descriptor naming, inheritance and unchanged literals."""
import os
from pathlib import Path
import subprocess
import tempfile
import unittest
import zipfile

ROOT = Path(__file__).resolve().parents[1]
EMULATOR = ROOT.parent / 'emulator/freej2me_plus.jar'


class ReferenceRenameTest(unittest.TestCase):
    def test_runtime_bridge_against_original_game_classes(self):
        with tempfile.TemporaryDirectory(prefix='vqsv-runtime-test-') as temp:
            folder = Path(temp)
            work = folder / 'work'
            work.mkdir()
            (work / 'runtime').mkdir()
            original = ROOT / 'original/game.jar'
            api = os.pathsep.join(map(str, [original, EMULATOR]))
            sources = sorted((ROOT / 'src/main/java').rglob('*.java'))
            self.run_command('javac', '--release', '8', '-encoding', 'UTF-8', '-cp', api,
                             '-d', folder, *sources, ROOT / 'tests/RuntimeRefactorCheck.java')
            result = subprocess.run(['java', '-Djava.awt.headless=true', '-cp',
                                     str(folder) + os.pathsep + api, 'RuntimeRefactorCheck'],
                                    cwd=work, check=True, capture_output=True, text=True)
            self.assertIn('Runtime bridge, title/stack guards, frame delay and configuration: OK', result.stdout)

    def test_overloads_inherited_fields_callbacks_and_private_homonyms(self):
        with tempfile.TemporaryDirectory(prefix='vqsv-symbol-test-') as temp:
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
                path.write_text(source)
            self.run_command('javac', '--release', '8', '-g:none', '-d', folder,
                             *(folder / path for path in sources))
            original = folder / 'original.jar'
            with zipfile.ZipFile(original, 'w') as jar:
                for file in folder.rglob('*.class'):
                    jar.write(file, file.relative_to(folder).as_posix())
                jar.writestr('asset.txt', 'a b sample.a')
            tool_classes = folder / 'tools'
            self.run_command('javac', '--release', '8', '-cp', EMULATOR,
                             '-d', tool_classes, ROOT / 'tools/ReadableReference.java')
            mapping = folder / 'names.tsv'
            mapping.write_text('''class\tsample/a\tsample/CounterBase
class\tsample/b\tsample/CounterChild
class\tsample/i\tsample/CounterListener
field\tsample/a\ta\tI\tcount
method\tsample/i\ta\t(I)I\taddCount
method\tsample/a\ta\t(Ljava/lang/String;)I\ttextLength
method\tsample/a\tb\t(I)I\tprivateCalculation
method\tsample/a\tc\t()I\tcallPrivateCalculation
method\tsample/b\tb\t(I)I\tchildCalculation
method\tsample/b\td\t()I\tgetInheritedCount
parameters\tsample/a\ta\t(I)I\tamount
''')
            readable = folder / 'readable.jar'
            cp = os.pathsep.join([str(tool_classes), str(EMULATOR)])
            self.run_command('java', '-cp', cp, 'ReadableReference', original, mapping, readable)
            original_result = self.run_command('java', '-cp', original, 'sample.Check')
            readable_result = self.run_command('java', '-cp', readable, 'sample.Check')
            self.assertEqual(original_result, readable_result)
            effective = Path(str(readable) + '.names.tsv').read_text()
            self.assertIn('method\tsample/b\ta\t(I)I\taddCount', effective)
            with zipfile.ZipFile(readable) as jar:
                self.assertEqual(jar.read('asset.txt'), b'a b sample.a')
                self.assertIn('sample/CounterChild.class', jar.namelist())
                self.assertNotIn('sample/b.class', jar.namelist())
            # A descriptor typo must fail before creating an output JAR.
            mapping.write_text('field\tsample/a\ta\tJ\twrongType\n')
            rejected = folder / 'rejected.jar'
            result = subprocess.run(['java', '-cp', cp, 'ReadableReference', str(original),
                                     str(mapping), str(rejected)], capture_output=True, text=True)
            self.assertNotEqual(result.returncode, 0)
            self.assertIn('Missing field', result.stderr)
            self.assertFalse(rejected.exists())
            # Distinct overloads cannot silently collapse into one target signature.
            mapping.write_text('method\tsample/a\tc\t()I\tsameName\n'
                               'method\tsample/b\td\t()I\tsameName\n')
            # These are in different owners and remain valid.
            self.run_command('java', '-cp', cp, 'ReadableReference', original, mapping, rejected)
            mapping.write_text('method\tsample/a\tb\t(I)I\ta\n')
            result = subprocess.run(['java', '-cp', cp, 'ReadableReference', str(original),
                                     str(mapping), str(folder / 'duplicate.jar')], capture_output=True, text=True)
            self.assertNotEqual(result.returncode, 0)
            self.assertIn('Duplicate target member', result.stderr)

    @staticmethod
    def run_command(*args):
        result = subprocess.run(list(map(str, args)), check=True, capture_output=True, text=True)
        return result.stdout


if __name__ == '__main__':
    unittest.main()
