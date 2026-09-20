/*
 * Decompiled with CFR 0.152.
 */
/**
 * AnimationCache - Cache and resource loader for sprite animations and sound data.
 */
public final class AnimationCache {
    private static SpriteData[] a;
    private static short[] b;
    private static short[][] c;

    public static void a() {
        a = new SpriteData[1000];
    }

    public static SpriteData getAnimationData(int n2) {
        if (a[n2] == null) {
            AnimationCache.a[n2] = new SpriteData();
            byte[] byteArray = new byte[20000];
            int[] intArray = new int[]{0};
            short[][] shortArray = new short[5][4];
            EngineUtils.a(byteArray, "/data/spr/spr_" + n2 + "_all(r)");
            AnimationCache.a[n2].b = EngineUtils.a(byteArray, intArray);
            if (n2 >= 86 && n2 <= 185) {
                AnimationCache.a[n2].e = EngineUtils.randomRange(byteArray, intArray);
                for (int i = 0; i < shortArray.length; ++i) {
                    for (int i3 = 0; i3 < 4; ++i3) {
                        shortArray[i][i3] = i3 == 1 ? (short)(AnimationCache.a[n2].e[0][i3] + b[i]) : AnimationCache.a[n2].e[0][i3];
                    }
                }
                AnimationCache.a[n2].e = shortArray;
                AnimationCache.a[n2].f = EngineUtils.randomRange(byteArray, intArray);
                AnimationCache.a[n2].f = c;
            } else {
                AnimationCache.a[n2].e = EngineUtils.randomRange(byteArray, intArray);
                AnimationCache.a[n2].f = EngineUtils.randomRange(byteArray, intArray);
            }
            AnimationCache.a[n2].d = AnimationCache.getAnimationData(EngineUtils.a(byteArray, intArray), AnimationCache.a[n2].e.length);
            AnimationCache.a[n2].c = AnimationCache.getAnimationData(EngineUtils.a(byteArray, intArray), AnimationCache.a[n2].e.length);
        }
        ++AnimationCache.a[n2].a;
        return a[n2];
    }

    private static short[][] a(short[] shortArray, int n2) {
        if (shortArray == null) {
            return null;
        }
        short[][] sArrayArray = new short[n2][];
        for (int i = 0; i < shortArray.length / 5; ++i) {
            sArrayArray[shortArray[i * 5]] = EngineUtils.a(sArrayArray[shortArray[i * 5]], new short[]{shortArray[i * 5 + 1], shortArray[i * 5 + 2], shortArray[i * 5 + 3], shortArray[i * 5 + 4]});
        }
        return sArrayArray;
    }

    public static void releaseAnimationData(int n2) {
        --AnimationCache.a[n2].a;
        if (AnimationCache.a[n2].a <= 0) {
            AnimationCache.a[n2].a = 0;
            AnimationCache.c(n2);
        }
    }

    public static boolean c(int n2) {
        if (a[n2] != null) {
            AnimationCache.a[n2].a = 0;
            AnimationCache.a[n2] = null;
            return true;
        }
        return false;
    }

    static {
        b = new short[]{0, 10, 3, 7, -10};
        c = new short[][]{{2, 0}, {1, 0, 1, 1, 1, 2, 1, 3, 1, 2}, {5, 0, 5, 4}};
    }
}

