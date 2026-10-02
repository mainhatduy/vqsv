import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.*;
import java.util.zip.*;
import org.objectweb.asm.*;

/** Rename reference bytecode by owner + descriptor, never by source text. */
public final class ReadableReference {
    private final Map<String, String> classes = new TreeMap<>();
    private final Map<String, String> fields = new TreeMap<>();
    private final Map<String, String> methods = new TreeMap<>();
    private final Map<String, String[]> parameters = new TreeMap<>();
    private final Map<String, ClassInfo> hierarchy = new TreeMap<>();

    private static final class ClassInfo {
        String parent;
        String[] interfaces;
        final Map<String, Integer> fields = new HashMap<>();
        final Map<String, Integer> methods = new HashMap<>();
    }

    private static String member(String name, String descriptor) {
        return name + "\t" + descriptor;
    }

    private static String key(String owner, String name, String descriptor) {
        return owner + "\t" + member(name, descriptor);
    }

    private String type(String name) {
        if (name == null) return null;
        return name.startsWith("[") ? descriptor(name) : classes.getOrDefault(name, name);
    }

    private String descriptor(String value) {
        if (value == null) return null;
        StringBuilder result = new StringBuilder();
        for (int index = 0; index < value.length(); index++) {
            char character = value.charAt(index);
            result.append(character);
            if (character == 'L') {
                int end = value.indexOf(';', index);
                if (end < 0) throw new IllegalArgumentException(value);
                String name = value.substring(index + 1, end);
                result.append(classes.getOrDefault(name, name)).append(';');
                index = end;
            }
        }
        return result.toString();
    }

    private String[] types(String[] values) {
        if (values == null) return null;
        String[] result = values.clone();
        for (int index = 0; index < result.length; index++) result[index] = type(result[index]);
        return result;
    }

    private void readHierarchy(ZipFile jar) throws IOException {
        Enumeration<? extends ZipEntry> entries = jar.entries();
        while (entries.hasMoreElements()) {
            ZipEntry entry = entries.nextElement();
            if (!entry.getName().endsWith(".class")) continue;
            ClassReader reader = new ClassReader(jar.getInputStream(entry));
            final ClassInfo info = new ClassInfo();
            info.parent = reader.getSuperName();
            info.interfaces = reader.getInterfaces();
            hierarchy.put(reader.getClassName(), info);
            reader.accept(new ClassAdapter(new ClassWriter(0)) {
                public FieldVisitor visitField(int access, String name, String desc,
                                               String signature, Object value) {
                    info.fields.put(member(name, desc), access);
                    return null;
                }
                public MethodVisitor visitMethod(int access, String name, String desc,
                                                 String signature, String[] exceptions) {
                    info.methods.put(member(name, desc), access);
                    return null;
                }
            }, ClassReader.SKIP_CODE | ClassReader.SKIP_DEBUG | ClassReader.SKIP_FRAMES);
        }
    }

    private String declaringClass(String owner, String name, String desc, boolean method) {
        if (owner == null) return null;
        ClassInfo info = hierarchy.get(owner);
        if (info == null) return null;
        if ((method ? info.methods : info.fields).containsKey(member(name, desc))) return owner;
        // Field lookup searches interfaces before the superclass (JVMS 5.4.3.2).
        if (!method) {
            for (String parent : info.interfaces) {
                String found = declaringClass(parent, name, desc, false);
                if (found != null) return found;
            }
        }
        String found = declaringClass(info.parent, name, desc, method);
        if (found != null) return found;
        if (method) {
            for (String parent : info.interfaces) {
                found = declaringClass(parent, name, desc, true);
                if (found != null) return found;
            }
        }
        return null;
    }

    private String memberName(String owner, String name, String desc, boolean method) {
        String declaring = declaringClass(owner, name, desc, method);
        if (declaring == null) return name;
        return (method ? methods : fields).getOrDefault(key(declaring, name, desc), name);
    }

    private void synchronizeOverrides() {
        boolean changed;
        do {
            changed = false;
            for (Map.Entry<String, ClassInfo> entry : hierarchy.entrySet()) {
                String owner = entry.getKey();
                ClassInfo info = entry.getValue();
                List<String> parents = new ArrayList<>(Arrays.asList(info.interfaces));
                if (info.parent != null) parents.add(info.parent);
                for (Map.Entry<String, Integer> method : info.methods.entrySet()) {
                    if ((method.getValue() & (Opcodes.ACC_PRIVATE | Opcodes.ACC_STATIC)) != 0) continue;
                    String[] parts = method.getKey().split("\t");
                    if (parts[0].startsWith("<")) continue;
                    for (String parent : parents) {
                        String ancestor = declaringClass(parent, parts[0], parts[1], true);
                        if (ancestor == null) continue;
                        int access = hierarchy.get(ancestor).methods.get(method.getKey());
                        if ((access & (Opcodes.ACC_PRIVATE | Opcodes.ACC_STATIC)) != 0) continue;
                        if ((access & (Opcodes.ACC_PUBLIC | Opcodes.ACC_PROTECTED)) == 0
                            && !packageName(owner).equals(packageName(ancestor))) continue;
                        String ownKey = key(owner, parts[0], parts[1]);
                        String parentKey = key(ancestor, parts[0], parts[1]);
                        String own = methods.get(ownKey), inherited = methods.get(parentKey);
                        if (own != null && inherited != null && !own.equals(inherited)) {
                            throw new IllegalArgumentException("Conflicting override names: " + ownKey
                                + " -> " + own + "; " + parentKey + " -> " + inherited);
                        }
                        if (own == null && inherited != null) {
                            methods.put(ownKey, inherited); changed = true;
                        } else if (inherited == null && own != null) {
                            methods.put(parentKey, own); changed = true;
                        }
                    }
                }
            }
        } while (changed);
    }

    private static String packageName(String name) {
        int end = name.lastIndexOf('/');
        return end < 0 ? "" : name.substring(0, end);
    }

    private byte[] rename(byte[] input) {
        ClassReader reader = new ClassReader(input);
        ClassWriter writer = new ClassWriter(0);
        final String owner = reader.getClassName();
        reader.accept(new ClassAdapter(writer) {
            public void visit(int version, int access, String name, String signature,
                              String parent, String[] interfaces) {
                if (signature != null) throw new IllegalArgumentException("Unexpected generic signature");
                super.visit(version, access, type(name), null, type(parent), types(interfaces));
            }
            public FieldVisitor visitField(int access, String name, String desc,
                                           String signature, Object value) {
                if (signature != null) throw new IllegalArgumentException("Unexpected field signature");
                return super.visitField(access, memberName(owner, name, desc, false), descriptor(desc), null, value);
            }
            public MethodVisitor visitMethod(int access, String name, String desc,
                                             String signature, String[] exceptions) {
                if (signature != null) throw new IllegalArgumentException("Unexpected method signature");
                MethodVisitor target = super.visitMethod(access, memberName(owner, name, desc, true),
                    descriptor(desc), null, types(exceptions));
                final String[] argumentNames = parameters.get(key(owner, name, desc));
                final Type[] argumentTypes = Type.getArgumentTypes(desc);
                final int firstSlot = (access & Opcodes.ACC_STATIC) == 0 ? 1 : 0;
                return new MethodAdapter(target) {
                    private final Label argumentStart = new Label();
                    private final Label[] argumentEnds = new Label[argumentTypes.length];
                    public void visitVarInsn(int opcode, int slot) {
                        // Object arguments are often reused as unrelated resource streams;
                        // primitive arguments also get assigned to themselves by the obfuscator.
                        // Keep compatible primitive scopes, end object/type-changing scopes.
                        if (argumentNames != null && opcode >= Opcodes.ISTORE && opcode <= Opcodes.ASTORE) {
                            int argumentSlot = firstSlot;
                            for (int index = 0; index < argumentTypes.length; index++) {
                                int expected = argumentTypes[index].getOpcode(Opcodes.ISTORE);
                                if (slot == argumentSlot && argumentEnds[index] == null
                                    && (opcode == Opcodes.ASTORE || opcode != expected)) {
                                    argumentEnds[index] = new Label();
                                    super.visitLabel(argumentEnds[index]);
                                }
                                argumentSlot += argumentTypes[index].getSize();
                            }
                        }
                        super.visitVarInsn(opcode, slot);
                    }
                    public void visitCode() {
                        super.visitCode();
                        if (argumentNames != null) super.visitLabel(argumentStart);
                    }
                    public void visitMaxs(int maxStack, int maxLocals) {
                        if (argumentNames != null) {
                            Label end = new Label();
                            super.visitLabel(end);
                            int slot = firstSlot;
                            for (int index = 0; index < argumentTypes.length; index++) {
                                super.visitLocalVariable(argumentNames[index],
                                    descriptor(argumentTypes[index].getDescriptor()), null, argumentStart,
                                    argumentEnds[index] == null ? end : argumentEnds[index], slot);
                                slot += argumentTypes[index].getSize();
                            }
                        }
                        super.visitMaxs(maxStack, maxLocals);
                    }
                    public void visitTypeInsn(int opcode, String name) {
                        super.visitTypeInsn(opcode, type(name));
                    }
                    public void visitFieldInsn(int opcode, String owner, String name, String desc) {
                        super.visitFieldInsn(opcode, type(owner), memberName(owner, name, desc, false), descriptor(desc));
                    }
                    public void visitMethodInsn(int opcode, String owner, String name, String desc) {
                        super.visitMethodInsn(opcode, type(owner), memberName(owner, name, desc, true), descriptor(desc));
                    }
                    public void visitLdcInsn(Object value) {
                        // Ordinary strings (resources, RMS names, messages) stay byte-for-byte intact.
                        if (value instanceof Type) value = Type.getType(descriptor(((Type)value).getDescriptor()));
                        super.visitLdcInsn(value);
                    }
                    public void visitMultiANewArrayInsn(String desc, int dimensions) {
                        super.visitMultiANewArrayInsn(descriptor(desc), dimensions);
                    }
                    public void visitTryCatchBlock(Label start, Label end, Label handler, String name) {
                        super.visitTryCatchBlock(start, end, handler, type(name));
                    }
                    public void visitLocalVariable(String name, String desc, String signature,
                                                   Label start, Label end, int index) {
                        // Parameter names come from the descriptor-specific registry.
                        int slot = firstSlot;
                        if (argumentNames != null) for (Type argument : argumentTypes) {
                            if (index == slot) return;
                            slot += argument.getSize();
                        }
                        super.visitLocalVariable(name, descriptor(desc), signature, start, end, index);
                    }
                    public void visitFrame(int kind, int localCount, Object[] locals,
                                           int stackCount, Object[] stack) {
                        super.visitFrame(kind, localCount, frame(locals), stackCount, frame(stack));
                    }
                };
            }
        }, 0);
        return writer.toByteArray();
    }

    private Object[] frame(Object[] values) {
        if (values == null) return null;
        Object[] result = values.clone();
        for (int index = 0; index < result.length; index++)
            if (result[index] instanceof String) result[index] = type((String)result[index]);
        return result;
    }

    public static void main(String[] args) throws Exception {
        if (args.length == 3 && args[0].equals("--compare")) {
            compareJars(args[1], args[2]);
            return;
        }
        if (args.length != 3) throw new IllegalArgumentException("Usage: input.jar mapping.tsv output.jar");
        ReadableReference remapper = new ReadableReference();
        for (String line : Files.readAllLines(Paths.get(args[1]), StandardCharsets.UTF_8)) {
            String[] values = line.split("\t");
            if (values[0].equals("class")) remapper.classes.put(values[1], values[2]);
            else if (values[0].equals("field")) remapper.fields.put(key(values[1], values[2], values[3]), values[4]);
            else if (values[0].equals("method")) remapper.methods.put(key(values[1], values[2], values[3]), values[4]);
            else if (values[0].equals("parameters")) {
                String[] names = Arrays.copyOfRange(values, 4, values.length);
                if (names.length != Type.getArgumentTypes(values[3]).length)
                    throw new IllegalArgumentException("Wrong parameter count: " + line);
                remapper.parameters.put(key(values[1], values[2], values[3]), names);
            }
            else throw new IllegalArgumentException(line);
        }
        try (ZipFile input = new ZipFile(args[0])) {
            remapper.readHierarchy(input);
            for (String owner : remapper.classes.keySet())
                if (!remapper.hierarchy.containsKey(owner))
                    throw new IllegalArgumentException("Missing class: " + owner);
            for (Map.Entry<String, String> entry : remapper.fields.entrySet()) {
                String[] parts = entry.getKey().split("\t");
                if (!remapper.hierarchy.containsKey(parts[0])
                    || !remapper.hierarchy.get(parts[0]).fields.containsKey(member(parts[1], parts[2])))
                    throw new IllegalArgumentException("Missing field: " + entry.getKey());
            }
            for (Map.Entry<String, String> entry : remapper.methods.entrySet()) {
                String[] parts = entry.getKey().split("\t");
                if (!remapper.hierarchy.containsKey(parts[0])
                    || !remapper.hierarchy.get(parts[0]).methods.containsKey(member(parts[1], parts[2])))
                    throw new IllegalArgumentException("Missing method: " + entry.getKey());
            }
            remapper.synchronizeOverrides();
            Set<String> classNames = new HashSet<>();
            for (String owner : remapper.hierarchy.keySet()) {
                if (!classNames.add(remapper.type(owner)))
                    throw new IllegalArgumentException("Duplicate target class: " + owner);
                ClassInfo info = remapper.hierarchy.get(owner);
                for (boolean isMethod : new boolean[]{false, true}) {
                    Set<String> names = new HashSet<>();
                    for (String signature : (isMethod ? info.methods : info.fields).keySet()) {
                        String[] parts = signature.split("\t");
                        String renamed = member(remapper.memberName(owner, parts[0], parts[1], isMethod),
                            remapper.descriptor(parts[1]));
                        if (!names.add(renamed))
                            throw new IllegalArgumentException("Duplicate target member: " + owner + ": " + renamed);
                    }
                }
            }
            // Include propagated overrides so the effective mapping is reviewable.
            List<String> effective = new ArrayList<>();
            for (Map.Entry<String, String> entry : remapper.classes.entrySet())
                effective.add("class\t" + entry.getKey() + "\t" + entry.getValue());
            for (Map.Entry<String, String> entry : remapper.fields.entrySet())
                effective.add("field\t" + entry.getKey() + "\t" + entry.getValue());
            for (Map.Entry<String, String> entry : remapper.methods.entrySet())
                effective.add("method\t" + entry.getKey() + "\t" + entry.getValue());
            Files.write(Paths.get(args[2] + ".names.tsv"), effective, StandardCharsets.UTF_8);
            try (ZipOutputStream output = new ZipOutputStream(new FileOutputStream(args[2]))) {
                Enumeration<? extends ZipEntry> entries = input.entries();
                while (entries.hasMoreElements()) {
                    ZipEntry entry = entries.nextElement();
                    if (entry.isDirectory()) continue;
                    byte[] bytes;
                    try (InputStream stream = input.getInputStream(entry)) {
                        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
                        byte[] block = new byte[8192]; int length;
                        while ((length = stream.read(block)) != -1) buffer.write(block, 0, length);
                        bytes = buffer.toByteArray();
                    }
                    String name = entry.getName();
                    if (name.endsWith(".class")) {
                        name = remapper.type(name.substring(0, name.length() - 6)) + ".class";
                        bytes = remapper.rename(bytes);
                    }
                    ZipEntry renamed = new ZipEntry(name);
                    renamed.setTime(0);
                    output.putNextEntry(renamed); output.write(bytes); output.closeEntry();
                }
            }
        }
        System.out.println("Readable reference: " + remapper.classes.size() + " classes, "
            + remapper.fields.size() + " fields, " + remapper.methods.size() + " methods (including overrides).");
    }

    /** Round-trip proof: ignore debug names, compare canonical class bytes and exact assets. */
    private static void compareJars(String originalPath, String restoredPath) throws IOException {
        try (ZipFile original = new ZipFile(originalPath); ZipFile restored = new ZipFile(restoredPath)) {
            int count = 0;
            Enumeration<? extends ZipEntry> entries = original.entries();
            while (entries.hasMoreElements()) {
                ZipEntry entry = entries.nextElement();
                if (entry.isDirectory()) continue;
                ZipEntry other = restored.getEntry(entry.getName());
                if (other == null) throw new IllegalArgumentException("Missing entry: " + entry.getName());
                byte[] left = readBytes(original.getInputStream(entry));
                byte[] right = readBytes(restored.getInputStream(other));
                if (entry.getName().endsWith(".class")) {
                    left = canonicalClass(left); right = canonicalClass(right);
                }
                if (!Arrays.equals(left, right))
                    throw new IllegalArgumentException("Round-trip differs: " + entry.getName());
                count++;
            }
            int otherCount = 0;
            Enumeration<? extends ZipEntry> others = restored.entries();
            while (others.hasMoreElements()) if (!others.nextElement().isDirectory()) otherCount++;
            if (count != otherCount) throw new IllegalArgumentException("Unexpected restored entries");
            System.out.println("Round-trip verified: " + count + " entries (code, signatures, constants, assets).");
        }
    }

    private static byte[] canonicalClass(byte[] bytes) {
        ClassWriter writer = new ClassWriter(0);
        new ClassReader(bytes).accept(writer, ClassReader.SKIP_DEBUG);
        return writer.toByteArray();
    }

    private static byte[] readBytes(InputStream input) throws IOException {
        try (InputStream stream = input) {
            ByteArrayOutputStream result = new ByteArrayOutputStream();
            byte[] block = new byte[8192]; int count;
            while ((count = stream.read(block)) != -1) result.write(block, 0, count);
            return result.toByteArray();
        }
    }
}
