import java.util.*;
import java.util.zip.*;
import org.objectweb.asm.*;

/** Descriptor-level inventory used once when reconstructing editable source. */
public final class SourceInventory {
    public static void main(String[] args) throws Exception {
        try (ZipFile jar = new ZipFile(args[0])) {
            Enumeration<? extends ZipEntry> entries = jar.entries();
            while (entries.hasMoreElements()) {
                ZipEntry entry = entries.nextElement();
                if (!entry.getName().endsWith(".class")) continue;
                ClassReader reader = new ClassReader(jar.getInputStream(entry));
                final String owner = reader.getClassName();
                System.out.println("class\t" + owner + "\t" + reader.getSuperName()
                    + "\t" + String.join(",", reader.getInterfaces()));
                reader.accept(new ClassAdapter(new ClassWriter(0)) {
                    public FieldVisitor visitField(int access, String name, String desc,
                                                    String signature, Object value) {
                        System.out.println("field\t" + owner + "\t" + name + "\t" + desc + "\t" + access);
                        return null;
                    }
                    public MethodVisitor visitMethod(int access, String name, String desc,
                                                      String signature, String[] exceptions) {
                        System.out.println("method\t" + owner + "\t" + name + "\t" + desc + "\t" + access);
                        return null;
                    }
                }, ClassReader.SKIP_CODE | ClassReader.SKIP_DEBUG | ClassReader.SKIP_FRAMES);
            }
        }
    }
}
