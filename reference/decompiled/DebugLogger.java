/*
 * Decompiled with CFR 0.152.
 */
import java.util.Vector;

/**
 * DebugLogger - Static exception and diagnostic message logger.
 */
public final class DebugLogger {
    private static Vector a = new Vector();

    public static void a(Throwable throwable, String text) {
        String[] strArray = new String[]{"", ""};
        if (text != null) {
            strArray[0] = text;
            System.out.println(strArray[0]);
        } else {
            strArray[0] = "";
            System.out.println(strArray[0]);
        }
        strArray[1] = throwable != null ? throwable.toString() : "";
        a.addElement(strArray);
    }
}

