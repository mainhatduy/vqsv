/*
 * Decompiled with CFR 0.152.
 */
import java.io.DataInputStream;
import java.io.InputStream;
import java.util.Vector;

/**
 * ResourceStream - Custom DataInputStream wrapper with endian conversions and memory pooling.
 */
public class ResourceStream
extends DataInputStream {
    private String a;
    private static Vector b = new Vector();
    private static Class c;

    private ResourceStream(InputStream inputStream, String text) {
        super(inputStream);
        this.a = text;
    }

    public static InputStream a(String text) {
        InputStream inputStream;
        Class<?> clazz = c;
        if (clazz == null) {
            try {
                clazz = c = Class.forName("aj");
            }
            catch (ClassNotFoundException classNotFoundException) {
                throw new NoClassDefFoundError(classNotFoundException.getMessage());
            }
        }
        if ((inputStream = clazz.getResourceAsStream(text)) != null) {
            inputStream = new ResourceStream(inputStream, text);
            b.addElement(inputStream);
            if (b.size() > 10) {
                System.out.println("current size: " + b.size());
                int n2 = 0;
                while (n2 < b.size()) {
                    ResourceStream aj2 = (ResourceStream)b.elementAt(n2);
                    try {
                        if (aj2.available() == 0) {
                            System.out.println("auto close1: " + aj2.a);
                            aj2.close();
                            --n2;
                        }
                    }
                    catch (Exception exception) {}
                    ++n2;
                }
                System.out.println("new size: " + b.size());
                if (b.size() > 10) {
                    ResourceStream aj3 = (ResourceStream)b.elementAt(0);
                    try {
                        System.out.println("auto close2: " + aj3.a);
                        aj3.close();
                    }
                    catch (Exception exception) {}
                }
            }
        }
        return inputStream;
    }

    public void close() {
        b.removeElement(this);
        try {
            super.close();
            return;
        }
        catch (Exception exception) {
            System.out.println("close error");
            return;
        }
    }
}

