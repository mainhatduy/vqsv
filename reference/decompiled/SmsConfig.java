/*
 * Decompiled with CFR 0.152.
 */
import game.GameMIDLet;
import java.io.DataInputStream;
import java.io.FilterInputStream;
import javax.microedition.midlet.MIDlet;

/**
 * SmsConfig - Billing configuration parser, SMS shortcode table, and carrier fee mapping.
 */
public final class SmsConfig {
    public static String a;
    public static String b;
    private static String[] d;
    private static String[] e;
    public static String[] c;
    private static int[] f;
    private static boolean g;

    static {
        g = false;
    }

    public static final void a(int n2) {
        if (d == null || e == null) {
            return;
        }
        if (n2 >= e.length) {
            return;
        }
        String string = GameMIDLet.a.getAppProperty("uid");
        if (string == null) {
            string = "0";
        }
        string = SmsConfig.a(e[n2], "%1", string);
        String string2 = GameMIDLet.a.getAppProperty("Term");
        if (string2 == null) {
            string2 = "";
        }
        string = SmsConfig.a(string, "%2", string2);
        string2 = GameMIDLet.a.getAppProperty("RefCode");
        if (string2 == null) {
            string2 = "";
        }
        string = SmsConfig.a(string, "%cp", string2);
        string = string.trim();
        string = SmsConfig.a(string, "  ", " ");
        a = d[n2];
        b = string;
    }

    private static byte a(byte val) {
        return (byte)(val <= 57 ? val - 48 : val + 10 - 97);
    }

    private static byte[] a(byte[] byteArray) {
        int n2 = byteArray.length;
        int n3 = n2 / 2;
        byte[] byteArray2 = new byte[n3];
        int n4 = 0;
        int n5 = 0;
        while (n4 < n2) {
            int n6 = n5;
            byteArray2[n6] = (byte)(byteArray2[n6] | SmsConfig.a(byteArray[n4]));
            int n7 = n5++;
            byteArray2[n7] = (byte)(byteArray2[n7] | SmsConfig.a(byteArray[n4 + 1]) << 4);
            n4 += 2;
        }
        return byteArray2;
    }

    private static String a(String text, String string2, String string3) {
        StringBuffer stringBuffer = new StringBuffer();
        int n2 = text.indexOf(string2);
        int n3 = 0;
        int n4 = string2.length();
        while (n2 != -1) {
            stringBuffer.append(text.substring(n3, n2)).append(string3);
            n3 = n2 + n4;
            n2 = text.indexOf(string2, n3);
        }
        stringBuffer.append(text.substring(n3, text.length()));
        return stringBuffer.toString();
    }

    private static void a(byte[] byteArray, byte[] byArray2) {
        int n2 = byteArray.length;
        int n3 = byteArray2.length;
        int n4 = 0;
        while (n4 < n2) {
            int n5 = n3 - n4 % 3;
            int n6 = 0;
            while (n6 < n5) {
                byteArray[n4] = (byte)(byteArray2[n6] ^ byteArray[n4]);
                ++n6;
            }
            ++n4;
        }
    }

    private static String b(byte[] byteArray) {
        String string;
        try {
            string = new String(byteArray, "utf-8");
        }
        catch (Exception exception) {
            try {
                string = new String(byteArray, "UTF-8");
            }
            catch (Exception exception2) {
                string = new String(byteArray);
            }
        }
        return string;
    }

    private static String[] a(String text, String string2) {
        int n2;
        if (text == null) {
            System.out.println(" split. aStr == null");
            return null;
        }
        if (text.length() <= 0 || string2 == null) {
            return new String[]{text};
        }
        if (string2.length() <= 0) {
            return new String[]{text};
        }
        String[] strArray = null;
        String string3 = text;
        int[] intArray = new int[string3.length()];
        int[] intArray2 = new int[string3.length()];
        int n3 = 0;
        boolean bl = false;
        int n4 = 0;
        int n5 = 0;
        do {
            n2 = 0;
            n5 = n4 + string2.length();
            if (n5 <= string3.length() && string3.substring(n4, n5).equals(string2)) {
                n2 = 1;
            }
            if (n2 != 0) {
                if (bl) {
                    bl = false;
                    intArray2[n3++] = n4;
                }
                n4 = n5;
                continue;
            }
            if (!bl) {
                bl = true;
                intArray[n3] = n4;
            }
            ++n4;
        } while (n4 < string3.length());
        if (bl) {
            intArray2[n3++] = string3.length();
        }
        if (n3 > 0) {
            strArray = new String[n3];
            n2 = 0;
            while (n2 < n3) {
                strArray[n2] = text.substring(intArray[n2], intArray2[n2]);
                ++n2;
            }
        }
        return strArray;
    }

    public static void a(MIDlet mIDlet) {
        Object object;
        int n2;
        Object object2;
        if (g) {
            return;
        }
        g = true;
        Object object3 = mIDlet.getAppProperty("sr");
        boolean bl = true;
        String[] strArray = null;
        if (object3 == null) {
            try {
                DataInputStream dataInputStream = new DataInputStream("".getClass().getResourceAsStream("/l2.bin"));
                object2 = dataInputStream;
                n2 = dataInputStream.available();
                object3 = new byte[n2];
                ((DataInputStream)object2).read((byte[])object3);
                ((FilterInputStream)object2).close();
                object = SmsConfig.b((byte[])object3);
                strArray = SmsConfig.a((String)object, String.valueOf('\n'));
                object3 = strArray[0].trim().getBytes();
            }
            catch (Exception exception) {
                Exception exception2 = exception;
                exception.printStackTrace();
                return;
            }
            bl = false;
        } else {
            object3 = ((String)object3).getBytes();
        }
        object2 = new byte[6];
        n2 = ((Object)object3).length;
        System.arraycopy(object3, 0, object2, 0, 3);
        System.arraycopy(object3, n2 - 3, object2, 3, 3);
        object = new byte[n2 - 6];
        System.arraycopy(object3, 3, object, 0, n2 - 6);
        byte[] byteArray = SmsConfig.a((byte[])object);
        object = byteArray;
        SmsConfig.a(byteArray, (byte[])object2);
        object3 = new byte[]{1, 2, 5, 7, 4};
        n2 = ((Object)object).length - 5;
        int n3 = 0;
        while (n3 < n2) {
            Object object4 = object3;
            int n4 = n3 % 5;
            object4[n4] = (byte)(object4[n4] ^ object[n3]);
            ++n3;
        }
        n3 = 0;
        while (n3 < 5) {
            if (object3[n3] != object[n2 + n3]) {
                return;
            }
            ++n3;
        }
        object3 = object;
        object = new byte[n2];
        System.arraycopy(object3, 0, object, 0, n2);
        object3 = SmsConfig.b((byte[])object);
        String[] stringArray2 = SmsConfig.a((String)object3, "|");
        n2 = Integer.parseInt(stringArray2[0]);
        d = new String[n2];
        e = new String[n2];
        f = new int[n2];
        int n5 = 0;
        while (n5 < n2) {
            SmsConfig.d[n5] = stringArray2[n5 + 1];
            SmsConfig.f[n5] = Integer.parseInt(stringArray2[n5 + n2 + 1]);
            SmsConfig.e[n5] = stringArray2[n5 + n2 + n2 + 1];
            ++n5;
        }
        c = new String[n2];
        if (bl) {
            n5 = 0;
            while (n5 < n2) {
                SmsConfig.c[n5] = mIDlet.getAppProperty("sr" + (n5 + 1));
                ++n5;
            }
            return;
        }
        n5 = 0;
        while (n5 < n2) {
            SmsConfig.c[n5] = strArray[n5 + 1].trim();
            ++n5;
        }
    }
}

