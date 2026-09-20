/*
 * Decompiled with CFR 0.152.
 */
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.FilterInputStream;
import java.io.InputStream;
import java.util.Random;
import java.util.Vector;
import javax.microedition.lcdui.Font;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

/**
 * EngineUtils - Core utility functions for RNG, resource streams, trigonometric tables, and array math.
 */
public final class EngineUtils {
    private static int c = 5;
    private static int d = 300;
    private static int[] e = null;
    private static Random f;
    private static int[][] g;
    private static int[] h;
    private static int[] i;
    private static FormattedTextBuffer j;
    private static char[] k;
    private static int l;
    public static String[] a;
    public static int b;
    private static int m;

    public static short[] a(short[] shortArray, short[] sArray2) {
        int n2 = 0;
        if (shortArray != null) {
            n2 = shortArray.length;
        }
        short[] sArray3 = new short[n2 + shortArray2.length];
        if (shortArray != null) {
            System.arraycopy(shortArray, 0, sArray3, 0, shortArray.length);
        }
        System.arraycopy(shortArray2, 0, sArray3, n2, shortArray2.length);
        return sArray3;
    }

    public static void a(byte[] byteArray, String text) {
        try {
            if (((String)text).substring(0, 1).endsWith("/")) {
                new Object().getClass();
                text = ResourceStream.a((String)text);
            } else {
                new Object().getClass();
                text = ResourceStream.a("/" + (String)text);
            }
            text = new DataInputStream((InputStream)text);
            ((FilterInputStream)text).skip(0L);
            ((DataInputStream)text).read(byteArray, 0, byteArray.length);
            ((FilterInputStream)text).close();
            return;
        }
        catch (Exception exception) {
            System.out.println("GameInf err at getStream()  :  " + exception);
            return;
        }
    }

    public static short[] a(byte[] byteArray, int[] intArray) {
        short s2 = EngineUtils.f(byteArray, intArray);
        int n2 = EngineUtils.f(byteArray, intArray);
        if (s2 == 0) {
            return null;
        }
        short[] shortArray = new short[s2 * n2];
        for (n2 = 0; n2 < shortArray.length; ++n2) {
            byte by = byteArray[intArray[0]];
            intArray[0] = intArray[0] + 1;
            byte by2 = byteArray[intArray[0]];
            intArray[0] = intArray[0] + 1;
            shortArray[n2] = (short)(by << 8 | by2 & 0xFF);
        }
        return shortArray;
    }

    public static short[][] b(byte[] byteArray, int[] intArray) {
        int n2 = EngineUtils.f(byteArray, intArray);
        short s2 = EngineUtils.f(byteArray, intArray);
        if (n2 == 0) {
            return null;
        }
        short[][] sArrayArray = new short[n2][];
        for (int i = 0; i < n2; ++i) {
            int n3 = EngineUtils.f(byteArray, intArray);
            sArrayArray[i] = new short[n3 * s2];
            for (n3 = 0; n3 < sArrayArray[i].length; ++n3) {
                byte by = byteArray[intArray[0]];
                intArray[0] = intArray[0] + 1;
                byte by2 = byteArray[intArray[0]];
                intArray[0] = intArray[0] + 1;
                sArrayArray[i][n3] = (short)(by << 8 | by2 & 0xFF);
            }
        }
        return sArrayArray;
    }

    private static short f(byte[] byteArray, int[] intArray) {
        int n2 = intArray[0];
        intArray[0] = n2 + 1;
        int n3 = intArray[0];
        intArray[0] = n3 + 1;
        return (short)((byteArray[n2] & 0xFF) << 8 | byteArray[n3] & 0xFF);
    }

    public static InputStream a(String text) {
        try {
            "".getClass();
            return ResourceStream.a(text);
        }
        catch (Exception exception) {
            return null;
        }
    }

    public static short[][] a(InputStream inputStream) {
        short[][] sArrayArray = null;
        if (inputStream == null) {
            return null;
        }
        try {
            DataInputStream dataInputStream = new DataInputStream(inputStream);
            int n2 = dataInputStream.readShort();
            if (n2 >= 0) {
                sArrayArray = new short[n2][];
            }
            for (int i = 0; i < sArrayArray.length; ++i) {
                n2 = dataInputStream.readShort();
                sArrayArray[i] = new short[n2];
                for (n2 = 0; n2 < sArrayArray[i].length; ++n2) {
                    sArrayArray[i][n2] = dataInputStream.readShort();
                }
            }
        }
        catch (Exception exception) {}
        return sArrayArray;
    }

    public static byte[][] b(InputStream inputStream) {
        byte[][] byArrayArray = null;
        if (inputStream == null) {
            return null;
        }
        try {
            DataInputStream dataInputStream = new DataInputStream(inputStream);
            int n2 = dataInputStream.readShort();
            if (n2 >= 0) {
                byArrayArray = new byte[n2][];
            }
            for (int i = 0; i < byArrayArray.length; ++i) {
                n2 = dataInputStream.readShort();
                byArrayArray[i] = new byte[n2];
                for (n2 = 0; n2 < byArrayArray[i].length; ++n2) {
                    byArrayArray[i][n2] = dataInputStream.readByte();
                }
            }
        }
        catch (Exception exception) {}
        return byArrayArray;
    }

    public static String[][] c(InputStream object) {
        String[][] stringArrayArray = null;
        StringBuffer stringBuffer = new StringBuffer();
        try {
            object = new DataInputStream((InputStream)object);
            int n2 = ((DataInputStream)object).readShort();
            if (n2 == 0) {
                return null;
            }
            if (n2 > 0) {
                stringArrayArray = new String[n2][];
            }
            int n3 = 0;
            while (n3 < stringArrayArray.length) {
                n2 = ((DataInputStream)object).readShort();
                stringArrayArray[n3] = new String[n2];
                n2 = 0;
                while (n2 < stringArrayArray[n3].length) {
                    int n4 = ((DataInputStream)object).readUnsignedByte();
                    if (n4 == 255) {
                        n4 = ((DataInputStream)object).readShort();
                    }
                    int n5 = 0;
                    while (n5 < n4) {
                        stringBuffer.append((char)((DataInputStream)object).readShort());
                        ++n5;
                    }
                    stringArrayArray[n3][n2] = stringBuffer.toString();
                    stringBuffer = new StringBuffer();
                    ++n2;
                }
                ++n3;
            }
        }
        catch (Exception exception) {
            object = exception;
            exception.printStackTrace();
        }
        return stringArrayArray;
    }

    public static int a(int n2, int n3, int n4, int n5) {
        n2 = (n2 - n4) * (n2 - n4) + (n3 - n5) * (n3 - n5);
        n3 = 0;
        for (n4 = 0x40000000; n4 > 0; n4 >>= 2) {
            if (n2 >= n3 + n4) {
                n2 -= n3 + n4;
                n3 = (n3 >> 1) + n4;
                continue;
            }
            n3 >>= 1;
        }
        return n3;
    }

    public static int a(int n2, int n3) {
        int n4;
        int n5;
        if (e == null) {
            e = new int[d / c + 1];
            n5 = c * c;
            for (n4 = e.length - 1; n4 >= 0; --n4) {
                EngineUtils.e[n4] = n4 * n4 * n5;
            }
        }
        if (n2 < 0) {
            return -1;
        }
        if (n2 <= e[30]) {
            if (n2 <= e[15]) {
                if (n2 <= e[5]) {
                    for (n5 = 1; n5 <= 5 && n2 > e[n5]; ++n5) {
                    }
                } else if (n2 <= e[10]) {
                    for (n5 = 6; n5 <= 10 && n2 > e[n5]; ++n5) {
                    }
                } else {
                    for (n5 = 11; n5 <= 15 && n2 > e[n5]; ++n5) {
                    }
                }
            } else if (n2 <= e[20]) {
                for (n5 = 16; n5 <= 20 && n2 > e[n5]; ++n5) {
                }
            } else if (n2 <= e[25]) {
                for (n5 = 21; n5 <= 25 && n2 > e[n5]; ++n5) {
                }
            } else {
                for (n5 = 26; n5 <= 30 && n2 > e[n5]; ++n5) {
                }
            }
        } else if (n2 <= e[45]) {
            if (n2 <= e[35]) {
                for (n5 = 31; n5 <= 35 && n2 > e[n5]; ++n5) {
                }
            } else if (n2 <= e[40]) {
                for (n5 = 36; n5 <= 40 && n2 > e[n5]; ++n5) {
                }
            } else {
                for (n5 = 41; n5 <= 45 && n2 > e[n5]; ++n5) {
                }
            }
        } else if (n2 <= e[50]) {
            for (n5 = 46; n5 <= 50 && n2 > e[n5]; ++n5) {
            }
        } else if (n2 <= e[55]) {
            for (n5 = 51; n5 <= 55 && n2 > e[n5]; ++n5) {
            }
        } else {
            for (n5 = 56; n5 <= 60 && n2 > e[n5]; ++n5) {
            }
        }
        for (n4 = (n5 *= c) - c + 1; n4 <= n5; ++n4) {
            if (n4 * n4 < n2) continue;
            if (n3 == 0) {
                return n4;
            }
            if (n3 != 1) continue;
            return n4 - 1;
        }
        return -1;
    }

    public static int randomInt(int max) {
        if (f == null) {
            f = new Random(System.currentTimeMillis());
        }
        return (f.nextInt() >>> 1) % n2;
    }

    public static int a() {
        if (f == null) {
            f = new Random(System.currentTimeMillis());
        }
        return -2 + (f.nextInt() >>> 1) % 4;
    }

    public static int randomRange(int min, int max) {
        if (f == null) {
            f = new Random(System.currentTimeMillis());
        }
        return (f.nextInt() >>> 1) % (n3 - n2 + 1) + n2;
    }

    public static int a(int[] intArray, int n2) {
        int n3;
        for (n3 = 0; n3 < intArray.length && n2 < intArray[n3]; ++n3) {
        }
        return n3;
    }

    public static boolean a(int n2, int n3, int n4, int n5, int n6, int n7, int n8, int n9, int n10) {
        if (n10 % 2 != 0) {
            n10 = n4;
            n4 = n5;
            n5 = n10;
        }
        return n2 + n4 >= n6 && n6 + n8 >= n2 && n3 + n5 > n7 && n7 + n9 > n3;
    }

    public static boolean a(int n2, int n3, int n4, int n5, int n6) {
        return n2 >= n4 && n2 <= n4 + n6 && n3 >= n5 && n3 <= n5 + 40;
    }

    public static boolean a(int n2, int n3, int n4, int n5, short[] shortArray, short[] sArray2) {
        return n2 + shortArray[0] + shortArray[2] >= n4 + shortArray2[0] && n2 + shortArray[0] <= n4 + shortArray2[0] + shortArray2[2] && n3 + shortArray[1] <= n5 + shortArray2[1] + shortArray2[3] && n3 + shortArray[1] + shortArray[3] >= n5 + shortArray2[1];
    }

    public static boolean a(int n2, int n3, int n4, int n5, int n6, int n7, short[] shortArray) {
        return n2 + n4 >= n6 + shortArray[0] && n2 <= n6 + shortArray[0] + shortArray[2] && n3 <= n7 + shortArray[1] + shortArray[3] && n3 + n5 >= n7 + shortArray[1];
    }

    public static boolean a(int n2, int n3, int n4, int n5, short[] shortArray) {
        return n2 >= n4 + shortArray[0] && n2 <= n4 + shortArray[0] + shortArray[2] && n3 <= n5 + shortArray[1] + shortArray[3] && n3 >= n5 + shortArray[1];
    }

    public static Image a(String text, String string2) {
        Image image;
        try {
            image = Image.createImage(text + string2 + ".png");
        }
        catch (Exception exception) {
            DebugLogger.a(exception, text + string2 + ".png error!!");
            return null;
        }
        return image;
    }

    public static Image b(String text, String text) {
        byte[] byteArray = EngineUtils.g((String)text + text + ".mid");
        text = byteArray;
        return Image.createImage(byteArray, 0, ((Object)text).length);
    }

    private static byte[] f(String text) {
        byte[] byteArray = null;
        try {
            int n2;
            int n3 = 0;
            int n4 = 0;
            InputStream inputStream = "".getClass().getResourceAsStream(text);
            DataInputStream dataInputStream = new DataInputStream(inputStream);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            byteArrayOutputStream.write(EngineUtils.a(-1991225785L, 4));
            byteArrayOutputStream.write(EngineUtils.a(218765834L, 4));
            byteArrayOutputStream.write(EngineUtils.a(13L, 4));
            byteArrayOutputStream.write(EngineUtils.a(1229472850L, 4));
            byteArrayOutputStream.write(EngineUtils.a((long)dataInputStream.readInt(), 4));
            byteArrayOutputStream.write(EngineUtils.a((long)dataInputStream.readInt(), 4));
            byteArrayOutputStream.write(dataInputStream.readByte());
            byte by = dataInputStream.readByte();
            g = new int[20][2];
            byteArrayOutputStream.write(by);
            byteArrayOutputStream.write(EngineUtils.a(0L, 3));
            byteArrayOutputStream.write(EngineUtils.a(0L, 4));
            EngineUtils.g[0][0] = 8;
            EngineUtils.g[0][1] = 13;
            int n5 = 8 + (g[0][1] + 12);
            ++n4;
            if (by == 3) {
                n3 = dataInputStream.readInt();
                byteArrayOutputStream.write(EngineUtils.a((long)n3, 4));
                byteArrayOutputStream.write(EngineUtils.a(1347179589L, 4));
                byte[] byteArray2 = new byte[n3];
                for (n2 = 0; n2 < byteArray2.length; ++n2) {
                    byteArray2[n2] = (byte)dataInputStream.read();
                }
                byteArrayOutputStream.write(byteArray2);
                byteArrayOutputStream.write(EngineUtils.a(0L, 4));
                EngineUtils.g[1][0] = n5;
                EngineUtils.g[1][1] = n3;
                n5 += g[1][1] + 12;
                ++n4;
                n3 = dataInputStream.readInt();
                if (n3 == 1951551059) {
                    int n6 = dataInputStream.read();
                    if (n6 == 0) {
                        n6 = 256;
                    }
                    byteArrayOutputStream.write(EngineUtils.a((long)n6, 4));
                    byteArrayOutputStream.write(EngineUtils.a(1951551059L, 4));
                    n2 = dataInputStream.read();
                    for (n3 = 0; n3 < n6; ++n3) {
                        if (n3 == n2) {
                            byteArrayOutputStream.write(0);
                            continue;
                        }
                        byteArrayOutputStream.write(255);
                    }
                    byteArrayOutputStream.write(EngineUtils.a(0L, 4));
                    EngineUtils.g[2][0] = n5;
                    EngineUtils.g[2][1] = n6;
                    n5 += g[2][1] + 12;
                    ++n4;
                    n3 = dataInputStream.readInt();
                } else {
                    EngineUtils.g[2][0] = n5;
                    EngineUtils.g[2][1] = n3;
                    ++n4;
                }
            } else if (by == 6) {
                n3 = dataInputStream.readInt();
            }
            byteArrayOutputStream.write(EngineUtils.a((long)n3, 4));
            byteArrayOutputStream.write(EngineUtils.a(1229209940L, 4));
            byte[] byArray3 = new byte[n3];
            for (n2 = 0; n2 < byArray3.length; ++n2) {
                byArray3[n2] = (byte)dataInputStream.read();
            }
            byteArrayOutputStream.write(byArray3);
            byteArrayOutputStream.write(EngineUtils.a(0L, 4));
            EngineUtils.g[n4][0] = n5;
            EngineUtils.g[n4][1] = n3;
            ++n4;
            byteArrayOutputStream.write(EngineUtils.a(0L, 4));
            byteArrayOutputStream.write(EngineUtils.a(1229278788L, 4));
            byteArrayOutputStream.write(EngineUtils.a(-1371381630L, 4));
            byteArray = byteArrayOutputStream.toByteArray();
            inputStream.close();
            byteArrayOutputStream.close();
            for (n2 = 0; n2 < n4; ++n2) {
                n5 = g[n2][1];
                n3 = g[n2][0];
                byArray3 = byteArray;
                int n7 = n5 + 4;
                int n8 = n3 + 4;
                int n9 = ~EngineUtils.a(byteArray, n8, n7);
                byArray3[n3 + 8 + n5] = n9 >> 24;
                byArray3[n3 + 8 + n5 + 1] = (byte)(n9 >> 16);
                byArray3[n3 + 8 + n5 + 2] = (byte)(n9 >> 8);
                byArray3[n3 + 8 + n5 + 3] = (byte)n9;
            }
        }
        catch (Exception exception) {}
        return byteArray;
    }

    private static byte[] a(long l2, int n2) {
        byte[] byteArray = new byte[n2];
        --n2;
        int n3 = 0;
        while (n2 >= 0) {
            byteArray[n3] = (byte)(l2 >> (n2 << 3) & 0xFFL);
            --n2;
            ++n3;
        }
        return byteArray;
    }

    private static int a(byte[] byteArray, int n2, int n3) {
        int n4;
        int n5 = -1;
        if (h == null) {
            h = new int[256];
            for (int i = 0; i < 256; ++i) {
                n4 = i;
                for (int i3 = 0; i3 < 8; ++i3) {
                    if ((n4 & 1) == 1) {
                        n4 = 0xEDB88320 ^ n4 >>> 1;
                        continue;
                    }
                    n4 >>>= 1;
                }
                EngineUtils.h[i] = n4;
            }
        }
        for (n4 = n2; n4 < n3 + n2; ++n4) {
            n5 = h[(n5 ^ byteArray[n4]) & 0xFF] ^ n5 >>> 8;
        }
        return n5;
    }

    public static int[] a(Image img) {
        int n2 = img.getWidth();
        int n3 = img.getHeight();
        int[] intArray = new int[n2 * n3];
        img.getRGB(intArray, 0, n2, 0, 0, n2, n3);
        return intArray;
    }

    public static Image a(int[] intArray, int n2, int n3) {
        return Image.createRGBImage(intArray, n2, n3, true);
    }

    public static int a(int[] intArray) {
        int n2 = 0;
        for (int i = 0; i < intArray.length; ++i) {
            if (intArray[i] != -1) continue;
            n2 = i;
            break;
        }
        return n2;
    }

    public static UIComponent a(w w2, int n2) {
        if (w2.a() == n2) {
            return w2;
        }
        for (int i = 0; i < w2.g().length && w2.g()[i2] != null; ++i) {
            if (w2.g()[i].i() == 0) {
                UIComponent w3 = EngineUtils.a((MenuWidget)w2.g()[i], n2);
                if (w3 == null) continue;
                return w3;
            }
            if (w2.g()[i].a() != n2) continue;
            return w2.g()[i];
        }
        return null;
    }

    public static int[] b(int n2) {
        int[] intArray = new int[n2];
        for (int i = 0; i < n2; ++i) {
            intArray[i] = -1;
        }
        return intArray;
    }

    public static void b(int[] intArray, int n2) {
        int n3;
        if (0 >= intArray.length) {
            return;
        }
        int n4 = 0;
        for (n3 = 0; n3 < intArray.length; ++n3) {
            if (intArray[n3] != -1) continue;
            n4 = n3;
            break;
        }
        for (n3 = n4 - 1; n3 >= 0; --n3) {
            if (n3 + 1 >= intArray.length) continue;
            intArray[n3 + 1] = intArray[n3];
        }
        intArray[0] = n2;
    }

    public static void b(byte[] byteArray, String text) {
        try {
            int n2 = 0;
            for (int i = 0; i < ((String)text).length(); ++i) {
                if (((String)text).charAt(i) == '/') continue;
                n2 = i;
                break;
            }
            String string = "/" + ((String)text).substring(n2);
            new Object().getClass();
            text = ResourceStream.a(string);
            text = new DataInputStream((InputStream)text);
            ((FilterInputStream)text).skip(0L);
            ((DataInputStream)text).read(byteArray, 0, byteArray.length);
            ((FilterInputStream)text).close();
            return;
        }
        catch (Exception exception) {
            System.out.println("GameInf err at getStream()  :  " + exception);
            return;
        }
    }

    public static byte c(byte[] byteArray, int[] intArray) {
        int n2 = intArray[0];
        intArray[0] = n2 + 1;
        return byteArray[n2];
    }

    public static short d(byte[] byteArray, int[] intArray) {
        int n2 = intArray[0];
        intArray[0] = n2 + 1;
        int n3 = intArray[0];
        intArray[0] = n3 + 1;
        return (short)((byteArray[n2] & 0xFF) << 8 | byteArray[n3] & 0xFF);
    }

    public static int e(byte[] byteArray, int[] intArray) {
        int n2 = intArray[0];
        intArray[0] = n2 + 1;
        int n3 = intArray[0];
        intArray[0] = n3 + 1;
        int n4 = intArray[0];
        intArray[0] = n4 + 1;
        int n5 = intArray[0];
        intArray[0] = n5 + 1;
        return (byteArray[n2] & 0xFF) << 24 | (byteArray[n3] & 0xFF) << 16 | (byteArray[n4] & 0xFF) << 8 | byteArray[n5] & 0xFF;
    }

    public static String a(byte[] byteArray) {
        char[] cArray = new char[byteArray.length >> 1];
        for (int i2 = 0; i2 < byteArray.length; i2 += 2) {
            cArray[i2 >> 1] = (char)(byteArray[i2] << 8 | byteArray[i2 + 1] & 0xFF);
        }
        return new String(cArray);
    }

    public static void a(UIComponent w2, int n2, int n3, UIComponent w3) {
        w2.a(w2.b() + n2, w3);
        w2.b(w2.c() + n3, w3);
        if (w2.i() != 1 && w2.i() == 0) {
            for (int i = 0; i < w2.g().length && w2.g()[i2] != null; ++i) {
                EngineUtils.a(w2.g()[i], n2, n3, w3);
            }
        }
    }

    public static void a(Graphics g, String text, int n2, int n3, int n4, int n5, int n6, int n7, Font font, boolean flag10, int n8, int[] intArray, int n9, byte val14, TextPainter y2, boolean[] blArray) {
        if (val14 != -1 && y2 != null) {
            n5 = TextLayoutHelper.a;
        }
        int[] intArray2 = new int[1 + i.length];
        int[] nArray3 = intArray2;
        intArray2[0] = n2;
        for (n2 = 1; n2 < nArray3.length; ++n2) {
            nArray3[n2] = i[n2 - 1];
        }
        j = EngineUtils.a(text, n5, n6, flag10, font, val14, y2);
        n2 = 0;
        if (EngineUtils.j.d > 0) {
            if (flag10) {
                if (EngineUtils.j.a[EngineUtils.j.d - 1][3] + n5 > n7) {
                    blArray[0] = true;
                    if (EngineUtils.j.a[EngineUtils.j.d - 1][3] + n5 > intArray[1]) {
                        intArray[1] = intArray[1] + n9;
                    } else {
                        intArray[1] = -n7;
                        blArray[1] = true;
                    }
                } else {
                    blArray[1] = true;
                    blArray[0] = true;
                    intArray[1] = 0;
                }
            } else {
                n2 = font.stringWidth(text);
                if (val14 != -1 && y2 != null) {
                    String string2 = text;
                    n2 = TextLayoutHelper.a(string2);
                }
                if (n2 > n6) {
                    blArray[1] = true;
                    if (n2 > intArray[0]) {
                        intArray[0] = intArray[0] + n9;
                        if (intArray[0] >= n2 - n6) {
                            blArray[0] = true;
                        }
                    } else {
                        intArray[0] = -n6;
                    }
                } else {
                    blArray[0] = true;
                    blArray[1] = true;
                    intArray[0] = 0;
                }
            }
        }
        for (int i = 0; i < EngineUtils.j.d; ++i) {
            if (flag10) {
                g.clipRect(n3, n4, n6, n7);
                EngineUtils.a(g, text.substring(EngineUtils.j.a[i][0], EngineUtils.j.a[i][1]), nArray3[EngineUtils.j.a[i][4]], n3 + EngineUtils.j.a[i][2], n4 + EngineUtils.j.a[i][3] - intArray[1], y2);
                g.clipRect(0, 0, BaseScreen.getScreenWidth(), BaseScreen.getScreenHeight());
                continue;
            }
            n9 = 0;
            int n10 = 0;
            switch (n8) {
                case 4: {
                    n9 = n2 > n6 ? n3 : n3 + (n6 - (EngineUtils.j.c + EngineUtils.j.b)) / 2;
                    n10 = n4 + (n7 - n5) / 2;
                    break;
                }
                case 3: {
                    n9 = n3;
                    n10 = n4 + (n7 - n5) / 2;
                    break;
                }
                case 5: {
                    n9 = n3 + (n6 - (EngineUtils.j.c + EngineUtils.j.b));
                    n10 = n4 + (n7 - n5) / 2;
                    break;
                }
                case 6: {
                    n9 = n3;
                    n10 = n4 + (n7 - n5);
                    break;
                }
                case 8: {
                    n9 = n3 + (n6 - (EngineUtils.j.c + EngineUtils.j.b));
                    n10 = n4 + (n7 - n5);
                    break;
                }
                case 7: {
                    n9 = n2 > n6 ? n3 : n3 + (n6 - (EngineUtils.j.c + EngineUtils.j.b)) / 2;
                    n10 = n4 + (n7 - n5);
                    break;
                }
                case 0: {
                    n9 = n3;
                    n10 = n4;
                    break;
                }
                case 2: {
                    n9 = n3 + (n6 - (EngineUtils.j.c + EngineUtils.j.b));
                    n10 = n4;
                    break;
                }
                case 1: {
                    n9 = n3 + (n6 - (EngineUtils.j.c + EngineUtils.j.b)) / 2;
                    n10 = n4;
                }
            }
            g.clipRect(n3, n4, n6, n7);
            EngineUtils.a(g, text.substring(EngineUtils.j.a[i][0], EngineUtils.j.a[i][1]), nArray3[EngineUtils.j.a[i][4]], n9 + EngineUtils.j.a[i][2] - intArray[0], n10 + EngineUtils.j.a[i][3], y2);
            g.clipRect(0, 0, BaseScreen.getScreenWidth(), BaseScreen.getScreenHeight());
        }
    }

    private static FormattedTextBuffer a(String text, int n2, int n3, boolean flag4, Font font, byte val6, TextPainter y2) {
        FormattedTextBuffer g2 = new FormattedTextBuffer();
        int n4 = 0;
        int n5 = 0;
        int n6 = 0;
        int n7 = 0;
        int n8 = text.length() - 1;
        int n9 = n3 - TextLayoutHelper.b;
        int n10 = 0;
        while (n10 <= n8) {
            block15: {
                block17: {
                    block16: {
                        int n11;
                        char c2 = text.charAt(n10);
                        if (c2 == '#' && n10 != n8) {
                            if (text.charAt(n10 + 1) == 'n') {
                                g2.a[g2.d++] = new int[]{n4, n10, n5, n6, n7};
                                n5 = 0;
                                n6 += n2 + 1;
                                n4 = n10 + 2;
                            } else if (text.charAt(n10 + 1) >= '0' && text.charAt(n10 + 1) <= '7') {
                                g2.c += g2.b;
                                g2.a[g2.d++] = new int[]{n4, n10, n5, n6, n7};
                                n11 = font.charsWidth(text.substring(n4, n10).toCharArray(), 0, n10 - n4);
                                if (val6 != -1 && y2 != null) {
                                    n11 = TextPainter.a(text.substring(n4, n10), 0, n10 - n4);
                                }
                                n5 += n11;
                                n7 = text.charAt(n10 + 1) - 48;
                                n4 = n10 + 2;
                            }
                        } else if (n10 - n4 >= 0) {
                            g2.b = n11 = TextPainter.a(text.substring(n4, n10 + 1), 0, n10 - n4 + 1);
                            if (flag4 && (n5 + n11 >= n3 || c2 == ' ' && n5 + n11 >= n9)) {
                                g2.a[g2.d++] = new int[]{n4, n10, n5, n6, n7};
                                n5 = 0;
                                n6 += n2 + 1;
                                n4 = n10;
                            }
                        }
                        if (n10 != n8) break block15;
                        if (n10 != n4) break block16;
                        n11 = 0;
                        while (n11 < k.length) {
                            if (text.charAt(n10) == k[n11]) {
                                g2.a[g2.d - 1][1] = n10 + 1;
                                break;
                            }
                            ++n11;
                        }
                        if (n11 < k.length) break block15;
                        break block17;
                    }
                    if (n10 + 1 - n4 <= 0) break block15;
                }
                g2.a[g2.d++] = new int[]{n4, n10 + 1, n5, n6, n7};
            }
            ++n10;
        }
        return g2;
    }

    private static void a(Graphics g, String text, int n2, int n3, int n4, TextPainter y2) {
        TextPainter.a(text, n3, n4, 20, n2, g);
    }

    public static void a(Graphics g, String text, int n2, int n3, int n4, TextPainter y2, int n5) {
        switch (n5) {
            case 0: {
                if (y2 != null) {
                    TextPainter.a(text, n3 - 1, n4, 17, 8607289, g);
                    TextPainter.a(text, n3 + 1, n4, 17, 8607289, g);
                    break;
                }
                g.setColor(8607289);
                g.drawString(text, n3 - 1, n4, 17);
                g.drawString(text, n3 + 1, n4, 17);
                break;
            }
            case 1: {
                if (y2 != null) {
                    TextPainter.a(text, n3, n4 - 1, 17, 8607289, g);
                    TextPainter.a(text, n3, n4 + 1, 17, 8607289, g);
                    break;
                }
                g.setColor(8607289);
                g.drawString(text, n3, n4 - 1, 17);
                g.drawString(text, n3, n4 + 1, 17);
                break;
            }
            case 2: {
                if (y2 != null) {
                    TextPainter.a(text, n3, n4 - 1, 17, 8607289, g);
                    TextPainter.a(text, n3, n4 + 1, 17, 8607289, g);
                    TextPainter.a(text, n3 - 1, n4, 17, 8607289, g);
                    TextPainter.a(text, n3 + 1, n4, 17, 8607289, g);
                    break;
                }
                g.setColor(8607289);
                g.drawString(text, n3, n4 - 1, 17);
                g.drawString(text, n3, n4 + 1, 17);
                g.drawString(text, n3 - 1, n4, 17);
                g.drawString(text, n3 + 1, n4, 17);
            }
        }
        if (y2 != null) {
            TextPainter.a(text, n3, n4, 17, n2, g);
            return;
        }
        g.setColor(n2);
        g.drawString(text, n3, n4, 17);
    }

    public static void a(String text, int n2, int n3, Font stringArray, TextPainter y2) {
        Object object = EngineUtils.a(text, n2, n3, true, (Font)strArray, (byte)-1, y2);
        n3 = ((FormattedTextBuffer)object).d;
        object = ((FormattedTextBuffer)object).a;
        b = 1;
        m = 0;
        strArray = new String[50];
        int n4 = 0;
        Object object2 = object[0][3];
        Object object3 = 0;
        for (int i = 0; i < n3; ++i) {
            if (object2 != object[i][3]) {
                object2 = object[i][3];
                strArray[n4++] = text.substring((int)object3, (int)object[i][0]);
                object3 = object[i][0];
            }
            if (i != n3 - 1) continue;
            strArray[n4++] = text.substring((int)object3, (int)object[i][1]);
            break;
        }
        String[] stringArray2 = new String[n4];
        System.arraycopy(strArray, 0, stringArray2, 0, stringArray2.length);
        a = stringArray2;
    }

    public static void c(int n2) {
        l = n2 / BaseScreen.getFontHeight();
    }

    public static String d(int n2) {
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = l * m; i < (n2 * l > a.length ? a.length : n2 * l); ++i) {
            stringBuffer.append(a[i]);
        }
        m = n2;
        return stringBuffer.toString();
    }

    public static int b() {
        if (a.length % l == 0) {
            return a.length / l;
        }
        return a.length / l + 1;
    }

    public static void c() {
        ++b;
    }

    public static String[] a(String objectArray, char c2) {
        Vector<String> vector = new Vector<String>();
        int n2 = 0;
        int n3 = 0;
        while (n2 != -1 && (n2 = objectArray.trim().indexOf(c2, n3)) != -1) {
            String string = objectArray.trim().substring(n3, n2);
            n3 = n2 + 1;
            vector.addElement(string);
        }
        vector.addElement(objectArray.substring(n3));
        objectArray = new String[vector.size()];
        vector.copyInto(objectArray);
        return objectArray;
    }

    public static int b(String text) {
        return Integer.parseInt(text);
    }

    public static short c(String text) {
        return Short.parseShort(text);
    }

    public static byte d(String text) {
        return Byte.parseByte(text);
    }

    public static byte[] e(String stringArray) {
        strArray = EngineUtils.a((String)strArray, ',');
        byte[] byteArray = new byte[strArray.length];
        for (int i = 0; i < byteArray.length; ++i) {
            byteArray[i] = Byte.parseByte(strArray[i]);
        }
        return byteArray;
    }

    public static long[] a(long l2) {
        long[] lArray = new long[3];
        long[] lArray2 = lArray;
        lArray[0] = l2 / 3600000L;
        lArray2[1] = l2 / 60000L;
        lArray2[2] = l2 / 1000L;
        return lArray2;
    }

    static {
        g = new int[20][2];
        i = new int[]{1862801, 15673612, 12067264, 11689977, 16359727, 0xFF0000, 255};
        j = null;
        k = new char[]{'，', '。', '？', '！', '：', '；', '’', '”', '、'};
        l = 0;
        b = 1;
        m = 0;
    }

    private static byte[] g(String text) {
        try {
            int n2;
            if (((String)text).indexOf("img_") < 0 && !((String)text).endsWith("menu.mid")) {
                return EngineUtils.f((String)text);
            }
            byte[] byteArray = new byte[1024];
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            "".getClass();
            text = ResourceStream.a((String)text);
            while ((n2 = ((InputStream)text).read(byteArray)) > 0) {
                byteArrayOutputStream.write(byteArray, 0, n2);
            }
            return byteArrayOutputStream.toByteArray();
        }
        catch (Exception exception) {
            Exception exception2 = exception;
            exception.printStackTrace();
            return null;
        }
    }
}

