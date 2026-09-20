/*
 * Decompiled with CFR 0.152.
 */
import javax.microedition.lcdui.Image;

/**
 * ImageCache - Resource manager for loaded image tilesets, icons, and background images.
 */
public final class ImageCache {
    private static Image[] a;
    private static byte[] b;
    private static int c;

    public static void a() {
        c = 50000;
        a = new Image[50000];
        b = new byte[c];
    }

    public static Image getImage(int n2) {
        if (a[n2] == null) {
            ImageCache.a[n2] = ae.loadImage("/data/img/", "img_" + n2);
        }
        int n3 = n2;
        b[n3] = (byte)(b[n3] + 1);
        return a[n2];
    }

    public static void releaseImage(int n2) {
        int n3 = n2;
        b[n3] = (byte)(b[n3] - 1);
        if (b[n2] <= 0) {
            ImageCache.b[n2] = 0;
        }
    }

    public static boolean c(int n2) {
        if (n2 == -1) {
            return true;
        }
        int n3 = n2;
        b[n3] = (byte)(b[n3] - 1);
        if (b[n2] <= 0) {
            ImageCache.b[n2] = 0;
            ImageCache.a[n2] = null;
            return true;
        }
        return false;
    }
}

