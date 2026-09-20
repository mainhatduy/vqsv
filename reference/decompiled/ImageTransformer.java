/*
 * Decompiled with CFR 0.152.
 */
import javax.microedition.lcdui.Image;

/**
 * ImageTransformer - Pixel-level image scaling, color transformations, and sprite rasterizer.
 */
public final class ImageTransformer {
    private static byte a = 1;
    private static byte b = (byte)2;

    public static PixelBuffer a(Image img, PixelBuffer e2) {
        int n2 = img.getWidth();
        int n3 = img.getHeight();
        int[] intArray = new int[n2 * n3];
        img.getRGB(intArray, 0, n2, 0, 0, n2, n3);
        for (int i = 0; i < intArray.length; ++i) {
            if (intArray[i] != -1 && intArray[i] != -16777216) continue;
            intArray[i] = 0xFFFFFF;
        }
        e2.a(intArray, n2, n3);
        return e2;
    }

    public static PixelBuffer a(SpriteRenderer d2, int n2, int[] intArray, byte val4, PixelBuffer e2) {
        Image image = Image.createImage(intArray[2], intArray[3]);
        image.getGraphics().setColor(0);
        image.getGraphics().fillRect(0, 0, intArray[2], intArray[3]);
        d2.a(image.getGraphics(), n2, -intArray[0], -intArray[1], val4);
        e2.d = intArray[0];
        e2.e = intArray[1];
        return ImageTransformer.a(image, e2);
    }

    public static PixelBuffer a(e e2, int n2, int n3) {
        int[] intArray = new int[n2 * n3];
        for (int i = 0; i < n3; ++i) {
            for (int i3 = 0; i3 < n2; ++i3) {
                int n4 = i3 * e2.b / n2;
                int n5 = i * e2.c / n3;
                intArray[i3 + i * n2] = e2.a[n4 + n5 * e2.b];
            }
        }
        e2.a(intArray, n2, n3);
        e2.d = e2.d * n2 / e2.b / 10;
        e2.e = e2.e * n2 / e2.b / 10;
        return e2;
    }

    public static PixelBuffer a(e e2, int n2) {
        int n3 = e2.b * n2 / 10;
        int n4 = e2.c * n2 / 10;
        int[] intArray = new int[n3 * n4];
        for (int i = 0; i < n4; ++i) {
            for (int i3 = 0; i3 < n3; ++i3) {
                int n5 = i3 * e2.b / n3;
                int n6 = i * e2.c / n4;
                intArray[i3 + i * n3] = e2.a[n5 + n6 * e2.b];
            }
        }
        e2.a(intArray, n3, n4);
        e2.d = e2.d * n2 / 10;
        e2.e = e2.e * n2 / 10;
        return e2;
    }

    public static PixelBuffer b(e e2, int n2, int n3) {
        for (int i = 0; i < e2.c; ++i) {
            for (int i3 = 0; i3 < e2.b; ++i3) {
                int n4 = e2.a[i * e2.b + i3];
                int n5 = n4 >> 24;
                int n6 = n4 >> 16 & 0xFF;
                int n7 = n4 >> 8 & 0xFF;
                n4 &= 0xFF;
                n6 = n6 * n2 + n3;
                n7 = n7 * n2 + n3;
                n4 = n4 * n2 + n3;
                if (n6 > 255) {
                    n6 = 255;
                } else if (n6 < 0) {
                    n6 = 0;
                }
                if (n7 > 255) {
                    n7 = 255;
                } else if (n7 < 0) {
                    n7 = 0;
                }
                if (n4 > 255) {
                    n4 = 255;
                } else if (n4 < 0) {
                    n4 = 0;
                }
                e2.a[i * e2.b + i3] = n5 << 24 | n6 << 16 | n7 << 8 | n4;
            }
        }
        return e2;
    }

    public static PixelBuffer b(e e2, int n2) {
        if (n2 < 0 || n2 > 255) {
            return e2;
        }
        for (int i = 0; i < e2.f; ++i) {
            if (e2.a[i] == 0xFFFFFF || e2.a[i] == 0) continue;
            e2.a[i] = e2.a[i] == -16777216 ? 0 : n2 << 24 | e2.a[i] & 0xFFFFFF;
        }
        return e2;
    }

    public static Image a(Image img) {
        PixelBuffer e2 = new PixelBuffer();
        int n2 = img.getWidth();
        int n3 = img.getHeight();
        Image image2 = Image.createImage(n2, n3);
        image2.getGraphics().setColor(0);
        image2.getGraphics().fillRect(0, 0, n2, n3);
        image2.getGraphics().drawImage(img, 0, 0, 20);
        e2.a(EngineUtils.randomInt(image2), n2, n3);
        ImageTransformer.b(e2, 100);
        img = EngineUtils.a(e2.a, e2.b, e2.c);
        e2.a = null;
        return img;
    }

    public static PixelBuffer a(e e2, int n2, int n3, int n4, int n5) {
        for (int i = 0; i < e2.a.length; ++i) {
            if (e2.a[i] == 0xFFFFFF) continue;
            e2.a[i] = n2 < 0 || n2 > 255 ? n3 << 16 | n4 << 8 | n5 : n2 << 24 | n3 << 16 | n4 << 8 | n5;
        }
        return e2;
    }

    public static PixelBuffer a(e e2, PixelBuffer e3, byte val3) {
        int n2 = 0;
        int n3 = 0;
        if (val3 == 0) {
            e2 = ImageTransformer.b(e2, 5, 5);
        }
        for (int i = 0; i < e2.c; ++i) {
            for (int i3 = 0; i3 < e2.b; ++i3) {
                int n4 = e2.a[i * e2.b + i3];
                int n5 = e3.a[n3 * e3.b + n2];
                if (n4 >> 24 != 0) {
                    if (val3 == 0) {
                        e2.a[i * e2.b + i3] = n4 & n5;
                    } else if (val3 == a) {
                        e2.a[i * e2.b + i3] = n4 | n5;
                    } else if (val3 == b) {
                        e2.a[i * e2.b + i3] = n5;
                    }
                }
                if (n2 < e3.b - 1) {
                    ++n2;
                    continue;
                }
                n2 = 0;
            }
            n3 = n3 < e3.c - 1 ? ++n3 : 0;
            n2 = 0;
        }
        return e2;
    }
}

