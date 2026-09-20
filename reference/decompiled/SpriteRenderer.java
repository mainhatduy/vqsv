/*
 * Decompiled with CFR 0.152.
 */
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

/**
 * SpriteRenderer - Animated sprite renderer; draws frame clips and animation sequences.
 */
public final class SpriteRenderer {
    private static int[] c = new int[]{0, 5, 3, 6, 2, 4, 1, 7};
    private static int[] d = new int[]{2, 4, 1, 7, 0, 5, 3, 6};
    private static int[] e = new int[]{3, 6, 0, 5, 1, 7, 2, 4};
    private static int[] f = new int[]{1, 7, 2, 4, 3, 6, 0, 5};
    private static int[] g = new int[]{0, 270, 180, 90, 8192 /* MASK_DOWN */, 8462, 8372, 8282};
    private static int[] h = new int[]{8192 /* MASK_DOWN */, 8462, 8372, 8282, 0, 270, 180, 90};
    private static int[] i = new int[]{180, 90, 0, 270, 8372, 8282, 8192 /* MASK_DOWN */, 8462};
    private static int[] j = new int[]{8372, 8282, 8192 /* MASK_DOWN */, 8462, 180, 90, 0, 270};
    private Image[] frameImages;
    private SpriteData animationData;
    private int[] m;
    public int spriteId;
    private int n;
    private int o;
    private byte p;
    private int q;
    private int r;
    protected byte b;
    private boolean s = true;

    public final boolean loadSprite(int spriteId, boolean loop) {
        this.m = new int[GameDatabase.spriteTable[spriteId].length - 1];
        this.frameImages = new Image[GameDatabase.spriteTable[spriteId].length - 1];
        for (int i = 0; i < this.frameImages.length; ++i) {
            this.m[i] = GameDatabase.spriteTable[spriteId][i + 1];
            this.frameImages[i] = ImageCache.getImage(this.m[i]);
        }
        this.spriteId = spriteId;
        this.animationData = AnimationCache.getAnimationData(GameDatabase.spriteTable[spriteId][0]);
        this.animationData.g = loop;
        this.c(0);
        return true;
    }

    public final void releaseSprite() {
        if (this.m != null) {
            for (int i = 0; i < this.m.length; ++i) {
                if (this.frameImages != null) {
                    this.frameImages[i] = null;
                }
                ImageCache.releaseImage(this.m[i]);
            }
        }
        this.m = null;
        if (this.spriteId != 257) {
            this.animationData = null;
            AnimationCache.releaseAnimationData(this.spriteId);
        }
    }

    public final void updateAnimation() {
        if (this.m == null) {
            return;
        }
        for (int i = 0; i < this.m.length; ++i) {
            if (this.frameImages != null) {
                this.frameImages[i] = null;
            }
            ImageCache.c(this.m[i]);
        }
        this.m = null;
        this.animationData = null;
        AnimationCache.c(this.spriteId);
    }

    public final void loadSprite(int spriteId, boolean loop) {
        this.frameImages[spriteId] = null;
        ImageCache.c(this.m[spriteId]);
        this.m[spriteId] = n3;
        this.frameImages[spriteId] = ImageCache.getImage(this.m[spriteId]);
    }

    public static void c() {
    }

    public final void a(int n2) {
        for (int i = 0; i < this.frameImages.length; ++i) {
            if (n2 == 1) {
                this.frameImages[i] = ImageTransformer.a(ImageCache.getImage(this.m[i]));
                continue;
            }
            this.b();
        }
        if (n2 == 0) {
            this.spriteId(this.spriteId, false);
        }
    }

    public final boolean a(byte val, byte val2, boolean flag3) {
        if (this.b != val || flag3) {
            this.b = val;
            this.c(0);
        } else {
            this.b = val;
        }
        this.p = val2;
        return true;
    }

    public final void a(byte val, byte val2) {
        this.b = val;
        this.p = (byte)-1;
    }

    private void c(int n2) {
        this.q = n2;
        if (this.animationData.f == null) {
            return;
        }
        if (this.animationData.g) {
            this.n = this.animationData.f[this.b][this.q << 2];
            this.r = this.animationData.f[this.b].length / 4;
        } else {
            this.n = this.animationData.f[this.b][this.q << 1];
            this.r = this.animationData.f[this.b].length / 2;
        }
        this.o = 0;
        if (this.n > 0) {
            --this.n;
            return;
        }
        if (this.o > 0) {
            --this.o;
        }
    }

    public final boolean d() {
        if (this.n > 0) {
            --this.n;
        } else if (this.o > 0) {
            --this.o;
        } else {
            ++this.q;
            if (this.q >= this.r) {
                if (this.p >= 0) {
                    this.spriteId(this.p, (byte)-1, true);
                } else if (this.p == -2) {
                    --this.q;
                    this.c(this.q);
                } else if (this.p == -1) {
                    this.c(0);
                }
                return true;
            }
            this.c(this.q);
        }
        return false;
    }

    public final boolean e() {
        return this.q >= this.r - 1;
    }

    public final boolean f() {
        return this.n == 0;
    }

    public final boolean b(int n2) {
        return this.q == n2;
    }

    public final byte g() {
        return this.b;
    }

    public final int h() {
        return this.q;
    }

    public final int[] a(int n2, byte val2) {
        if (n2 >= 0 && n2 < this.animationData.f.length) {
            int[] intArray = this.b(this.animationData.f[n2][1], (byte)0);
            int n3 = intArray[0];
            int n4 = intArray[0] + intArray[2];
            int n5 = intArray[1];
            int n6 = intArray[1] + intArray[3];
            if (this.animationData.g) {
                for (int i = 1; i != this.animationData.f[n2].length / 4; ++i) {
                    intArray = this.b(this.animationData.f[n2][(i << 2) + 1], (byte)0);
                    if (intArray == null) continue;
                    if (n3 > intArray[0]) {
                        n3 = intArray[0];
                    }
                    if (n4 < intArray[0] + intArray[2]) {
                        n4 = intArray[0] + intArray[2];
                    }
                    if (n5 > intArray[1]) {
                        n5 = intArray[1];
                    }
                    if (n6 >= intArray[1] + intArray[3]) continue;
                    n6 = intArray[1] + intArray[3];
                }
            } else {
                for (int i3 = 1; i3 != this.animationData.f[n2].length / 2; ++i3) {
                    intArray = this.b(this.animationData.f[n2][(i3 << 1) + 1], (byte)0);
                    if (intArray == null) continue;
                    if (n3 > intArray[0]) {
                        n3 = intArray[0];
                    }
                    if (n4 < intArray[0] + intArray[2]) {
                        n4 = intArray[0] + intArray[2];
                    }
                    if (n5 > intArray[1]) {
                        n5 = intArray[1];
                    }
                    if (n6 >= intArray[1] + intArray[3]) continue;
                    n6 = intArray[1] + intArray[3];
                }
            }
            return new int[]{n3, n5, n4 - n3, n6 - n5};
        }
        return null;
    }

    public final int[] b(int n2, byte val2) {
        int n3;
        if (this.animationData.e[n2].length <= 0) {
            return null;
        }
        short s2 = this.animationData.e[n2][0];
        int n4 = this.animationData.e[n2][1];
        int n5 = this.animationData.e[n2][2];
        for (int i2 = 0; i2 < this.animationData.e[n2].length; i2 += 4) {
            s2 = this.animationData.e[n2][i2];
            if (this.animationData.e[n2][i2 + 1] < n4) {
                n4 = this.animationData.e[n2][i2 + 1];
            }
            if (this.animationData.e[n2][i2 + 2] >= n5) continue;
            n5 = this.animationData.e[n2][i2 + 2];
        }
        int[] intArray = new int[2];
        intArray = this.spriteId(0, (int)s2, n2, n4, n5, intArray);
        int n6 = intArray[0];
        int n7 = intArray[1];
        for (n3 = 0; n3 < this.animationData.e[n2].length; n3 += 4) {
            s2 = this.animationData.e[n2][n3];
            if ((intArray = this.spriteId(n3, (int)s2, n2, n4, n5, intArray))[0] > n6) {
                n6 = intArray[0];
            }
            if (intArray[1] <= n7) continue;
            n7 = intArray[1];
        }
        switch (val2) {
            case 4: {
                s2 = this.animationData.e[n2][0];
                n5 = this.animationData.e[n2][3] % 2 == 1 ? -this.animationData.e[n2][2] - this.animationData.b[s2 * 5 + 3] : -this.animationData.e[n2][2] - this.animationData.b[s2 * 5 + 4];
                for (n3 = 0; n3 < this.animationData.e[n2].length; n3 += 4) {
                    s2 = this.animationData.e[n2][n3];
                    if (this.animationData.e[n2][n3 + 3] % 2 == 1) {
                        if (-this.animationData.e[n2][n3 + 2] - this.animationData.b[s2 * 5 + 3] >= n5) continue;
                        n5 = -this.animationData.e[n2][n3 + 2] - this.animationData.b[s2 * 5 + 3];
                        continue;
                    }
                    if (-this.animationData.e[n2][n3 + 2] - this.animationData.b[s2 * 5 + 4] >= n5) continue;
                    n5 = -this.animationData.e[n2][n3 + 2] - this.animationData.b[s2 * 5 + 4];
                }
                break;
            }
            case 1: {
                s2 = this.animationData.e[n2][0];
                n4 = this.animationData.e[n2][3] % 2 == 1 ? -this.animationData.e[n2][1] - this.animationData.b[s2 * 5 + 4] : -this.animationData.e[n2][1] - this.animationData.b[s2 * 5 + 3];
                for (n3 = 0; n3 < this.animationData.e[n2].length; n3 += 4) {
                    s2 = this.animationData.e[n2][n3];
                    if (this.animationData.e[n2][n3 + 3] % 2 == 1) {
                        if (-this.animationData.e[n2][n3 + 1] - this.animationData.b[s2 * 5 + 4] >= n4) continue;
                        n4 = -this.animationData.e[n2][n3 + 1] - this.animationData.b[s2 * 5 + 4];
                        continue;
                    }
                    if (-this.animationData.e[n2][n3 + 1] - this.animationData.b[s2 * 5 + 3] >= n4) continue;
                    n4 = -this.animationData.e[n2][n3 + 1] - this.animationData.b[s2 * 5 + 3];
                }
                break;
            }
            case 3: {
                s2 = this.animationData.e[n2][0];
                if (this.animationData.e[n2][3] % 2 == 1) {
                    n4 = -this.animationData.e[n2][1] - this.animationData.b[s2 * 5 + 4];
                    n5 = -this.animationData.e[n2][2] - this.animationData.b[s2 * 5 + 3];
                } else {
                    n4 = -this.animationData.e[n2][1] - this.animationData.b[s2 * 5 + 3];
                    n5 = -this.animationData.e[n2][2] - this.animationData.b[s2 * 5 + 4];
                }
                for (n3 = 0; n3 < this.animationData.e[n2].length; n3 += 4) {
                    s2 = this.animationData.e[n2][n3];
                    if (this.animationData.e[n2][n3 + 3] % 2 == 1) {
                        if (-this.animationData.e[n2][n3 + 1] - this.animationData.b[s2 * 5 + 4] < n4) {
                            n4 = -this.animationData.e[n2][n3 + 1] - this.animationData.b[s2 * 5 + 4];
                        }
                        if (-this.animationData.e[n2][n3 + 2] - this.animationData.b[s2 * 5 + 3] >= n5) continue;
                        n5 = -this.animationData.e[n2][n3 + 2] - this.animationData.b[s2 * 5 + 3];
                        continue;
                    }
                    if (-this.animationData.e[n2][n3 + 1] - this.animationData.b[s2 * 5 + 3] < n4) {
                        n4 = -this.animationData.e[n2][n3 + 1] - this.animationData.b[s2 * 5 + 3];
                    }
                    if (-this.animationData.e[n2][n3 + 2] - this.animationData.b[s2 * 5 + 4] >= n5) continue;
                    n5 = -this.animationData.e[n2][n3 + 2] - this.animationData.b[s2 * 5 + 4];
                }
                break;
            }
        }
        return new int[]{n4, n5, n6, n7};
    }

    private int[] a(int n2, int n3, int n4, int n5, int n6, int[] intArray) {
        if (this.animationData.e[n4][n2 + 3] % 2 == 1) {
            intArray[1] = this.animationData.e[n4][n2 + 2] - n6 + this.animationData.b[n3 * 5 + 3];
            intArray[0] = this.animationData.e[n4][n2 + 1] - n5 + this.animationData.b[n3 * 5 + 4];
        } else {
            intArray[0] = this.animationData.e[n4][n2 + 1] - n5 + this.animationData.b[n3 * 5 + 3];
            intArray[1] = this.animationData.e[n4][n2 + 2] - n6 + this.animationData.b[n3 * 5 + 4];
        }
        return intArray;
    }

    public final void a(Graphics g, int n2, int n3, byte val4) {
        if (this.animationData.g) {
            this.spriteId(g, this.animationData.f[this.b][(this.q << 2) + 1], n2, n3, val4);
            return;
        }
        this.spriteId(g, this.animationData.f[this.b][(this.q << 1) + 1], n2, n3, val4);
    }

    public final void a(Graphics g, int n2, int n3, int n4, byte val5) {
        if (this.animationData.e[n2].length <= 0) {
            return;
        }
        switch (val5) {
            case 0: {
                for (val5 = 0; val5 < this.animationData.e[n2].length; val5 = (byte)(val5 + 4)) {
                    this.spriteId(g, (int)this.animationData.e[n2][val5], n3 + this.animationData.e[n2][val5 + 1], n4 + this.animationData.e[n2][val5 + 2], this.s ? c[this.animationData.e[n2][val5 + 3]] : g[this.animationData.e[n2][val5 + 3]], 20);
                }
                return;
            }
            case 1: {
                for (val5 = 0; val5 < this.animationData.e[n2].length; val5 = (byte)(val5 + 4)) {
                    if (this.animationData.e[n2][val5 + 3] % 2 == 1) {
                        this.spriteId(g, (int)this.animationData.e[n2][val5], n3 - this.animationData.e[n2][val5 + 1] - this.animationData.b[this.animationData.e[n2][val5] * 5 + 4], n4 + this.animationData.e[n2][val5 + 2], this.s ? d[this.animationData.e[n2][val5 + 3]] : h[this.animationData.e[n2][val5 + 3]], 20);
                        continue;
                    }
                    this.spriteId(g, (int)this.animationData.e[n2][val5], n3 - this.animationData.e[n2][val5 + 1] - this.animationData.b[this.animationData.e[n2][val5] * 5 + 3], n4 + this.animationData.e[n2][val5 + 2], this.s ? d[this.animationData.e[n2][val5 + 3]] : h[this.animationData.e[n2][val5 + 3]], 20);
                }
                return;
            }
            case 3: {
                for (val5 = 0; val5 < this.animationData.e[n2].length; val5 = (byte)(val5 + 4)) {
                    if (this.animationData.e[n2][val5 + 3] % 2 == 1) {
                        this.spriteId(g, (int)this.animationData.e[n2][val5], n3 - this.animationData.e[n2][val5 + 1] - this.animationData.b[this.animationData.e[n2][val5] * 5 + 4], n4 - this.animationData.e[n2][val5 + 2] - this.animationData.b[this.animationData.e[n2][val5] * 5 + 3], this.s ? e[this.animationData.e[n2][val5 + 3]] : i[this.animationData.e[n2][val5 + 3]], 20);
                        continue;
                    }
                    this.spriteId(g, (int)this.animationData.e[n2][val5], n3 - this.animationData.e[n2][val5 + 1] - this.animationData.b[this.animationData.e[n2][val5] * 5 + 3], n4 - this.animationData.e[n2][val5 + 2] - this.animationData.b[this.animationData.e[n2][val5] * 5 + 4], this.s ? e[this.animationData.e[n2][val5 + 3]] : i[this.animationData.e[n2][val5 + 3]], 20);
                }
                return;
            }
            case 4: {
                for (val5 = 0; val5 < this.animationData.e[n2].length; val5 = (byte)(val5 + 4)) {
                    if (this.animationData.e[n2][val5 + 3] % 2 == 1) {
                        this.spriteId(g, (int)this.animationData.e[n2][val5], n3 + this.animationData.e[n2][val5 + 1], n4 - this.animationData.e[n2][val5 + 2] - this.animationData.b[this.animationData.e[n2][val5] * 5 + 3], this.s ? f[this.animationData.e[n2][val5 + 3]] : j[this.animationData.e[n2][val5 + 3]], 20);
                        continue;
                    }
                    this.spriteId(g, (int)this.animationData.e[n2][val5], n3 + this.animationData.e[n2][val5 + 1], n4 - this.animationData.e[n2][val5 + 2] - this.animationData.b[this.animationData.e[n2][val5] * 5 + 4], this.s ? f[this.animationData.e[n2][val5 + 3]] : j[this.animationData.e[n2][val5 + 3]], 20);
                }
                break;
            }
        }
    }

    private void a(Graphics g, int n2, int n3, int n4, int n5, int n6) {
        g.drawRegion(this.frameImages[this.animationData.b[n2 * 5]], this.animationData.b[n2 * 5 + 1], this.animationData.b[n2 * 5 + 2], this.animationData.b[n2 * 5 + 3], this.animationData.b[n2 * 5 + 4], n5, n3, n4, 20);
    }

    public final boolean i() {
        return this.animationData.c == null;
    }

    public final short[] j() {
        if (this.animationData.c == null) {
            return null;
        }
        if (this.animationData.g) {
            return this.animationData.c[this.animationData.f[this.b][(this.q << 2) + 1]];
        }
        return this.animationData.c[this.animationData.f[this.b][(this.q << 1) + 1]];
    }

    public final short[] k() {
        if (this.animationData.d == null) {
            return null;
        }
        if (this.animationData.g) {
            return this.animationData.d[this.animationData.f[this.b][(this.q << 2) + 1]];
        }
        return this.animationData.d[this.animationData.f[this.b][(this.q << 1) + 1]];
    }
}

