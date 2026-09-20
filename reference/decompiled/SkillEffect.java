/*
 * Decompiled with CFR 0.152.
 */
import javax.microedition.lcdui.Graphics;

/**
 * SkillEffect - Battle skill projectile, visual effect particle, and animated spell renderer.
 */
public final class SkillEffect
extends BaseEntity {
    private PixelBuffer[] b;
    private short[] t;
    private byte u;
    private int v = 0;
    private int w = 0;
    private int[] x = new int[]{262, 263, 264, 265, 266, 267, 268, 299, 300, 301, 304, 306, 307, 308, 309};
    public SpriteRenderer a = new SpriteRenderer();

    public final void a(short[] shortArray) {
        this.u = (byte)shortArray[0];
        switch (this.u) {
            case 0: {
                this.t = new short[3];
                System.arraycopy(shortArray, 0, this.t, 0, this.t.length);
                this.b(shortArray[3], shortArray[4]);
                this.b = new PixelBuffer[3];
                SpriteRenderer d2 = new SpriteRenderer();
                for (int i = 0; i < 2; ++i) {
                    d2.a((int)shortArray[5 + i * 3], false);
                    int[] intArray = d2.b(shortArray[6 + i * 3], (byte)shortArray[7 + i * 3]);
                    this.b[i] = new PixelBuffer();
                    this.b[i] = ImageTransformer.a(d2, (int)shortArray[6 + i * 3], intArray, (byte)shortArray[7 + i * 3], this.b[i]);
                    d2.a();
                }
                this.b[2] = this.b[0].a();
                return;
            }
            case 1: {
                this.t = new short[shortArray.length - 6];
                this.n = (byte)shortArray[5];
                System.arraycopy(shortArray, 6, this.t, 0, this.t.length);
                this.b(shortArray[1], shortArray[2]);
                this.b = new PixelBuffer[3];
                SpriteRenderer d3 = new SpriteRenderer();
                d3.a((int)shortArray[3], false);
                int[] intArray = d3.b(shortArray[4], (byte)shortArray[5]);
                this.b[0] = new PixelBuffer();
                this.b[0] = ImageTransformer.a(d3, (int)shortArray[4], intArray, (byte)shortArray[5], this.b[0]);
                d3.a();
                this.b[1] = new PixelBuffer();
                this.b[1].a(GameDatabase.e[this.t[2]], 16, 16);
                this.b[2] = this.b[0].a();
                return;
            }
            case 2: {
                return;
            }
            case 3: {
                return;
            }
            case 4: {
                return;
            }
            case 5: {
                return;
            }
            case 6: {
                return;
            }
            case 7: {
                this.t = new short[shortArray.length - 6];
                this.n = (byte)shortArray[5];
                System.arraycopy(shortArray, 6, this.t, 0, this.t.length);
                this.b(shortArray[1], shortArray[2]);
                this.b = new PixelBuffer[2];
                SpriteRenderer d4 = new SpriteRenderer();
                d4.a((int)shortArray[3], false);
                int[] intArray = d4.b(shortArray[4], (byte)shortArray[5]);
                this.b[0] = new PixelBuffer();
                this.b[0] = ImageTransformer.a(d4, (int)shortArray[4], intArray, (byte)shortArray[5], this.b[0]);
                this.b[1] = this.b[0].a();
                int n2 = intArray[2] * shortArray[9] / shortArray[10];
                int n3 = intArray[3] * shortArray[11] / shortArray[12];
                this.v = (intArray[2] - n2) / 2;
                this.w = intArray[3] - n3;
                this.b[1] = ImageTransformer.a(this.b[1], n2, n3);
                d4.a();
                return;
            }
            case 9: {
                this.t = new short[shortArray.length - 10];
                this.n = (byte)shortArray[5];
                System.arraycopy(shortArray, 10, this.t, 0, this.t.length);
                this.b(shortArray[1], shortArray[2]);
                this.b = new PixelBuffer[2];
                SpriteRenderer d5 = new SpriteRenderer();
                d5.a((int)shortArray[3], false);
                int[] intArray = d5.b(shortArray[4], (byte)shortArray[5]);
                this.b[0] = new PixelBuffer();
                this.b[0] = ImageTransformer.a(d5, (int)shortArray[4], intArray, (byte)shortArray[5], this.b[0]);
                this.b[1] = this.b[0].a();
                this.b[1] = ImageTransformer.a(this.b[1], (int)shortArray[6], shortArray[7], shortArray[8], shortArray[9]);
                this.b[1] = ImageTransformer.b(this.b[1], 1, 50);
                d5.a();
                return;
            }
            case 17: {
                this.t = new short[shortArray.length - 11];
                this.n = (byte)shortArray[5];
                System.arraycopy(shortArray, 11, this.t, 0, this.t.length);
                this.b(shortArray[1], shortArray[2]);
                this.b = new PixelBuffer[2];
                SpriteRenderer d6 = new SpriteRenderer();
                d6.a((int)shortArray[3], false);
                int[] intArray = d6.b(shortArray[4], (byte)shortArray[5]);
                this.b[0] = new PixelBuffer();
                this.b[0] = ImageTransformer.a(d6, (int)shortArray[4], intArray, (byte)shortArray[5], this.b[0]);
                this.b[0] = ImageTransformer.a(this.b[0], shortArray[10]);
                this.b[1] = this.b[0].a();
                this.b[1] = ImageTransformer.a(this.b[1], (int)shortArray[6], shortArray[7], shortArray[8], shortArray[9]);
                d6.a();
                return;
            }
            case 10: {
                this.t = new short[shortArray.length - 7];
                this.n = (byte)shortArray[5];
                System.arraycopy(shortArray, 7, this.t, 0, this.t.length);
                this.b(shortArray[1], shortArray[2]);
                this.b = new PixelBuffer[2];
                SpriteRenderer d7 = new SpriteRenderer();
                d7.a((int)shortArray[3], false);
                int[] intArray = d7.b(shortArray[4], (byte)shortArray[5]);
                this.b[0] = new PixelBuffer();
                this.b[0] = ImageTransformer.a(d7, (int)shortArray[4], intArray, (byte)shortArray[5], this.b[0]);
                this.b[1] = this.b[0].a();
                this.b[1] = ImageTransformer.b(this.b[1], shortArray[6]);
                d7.a();
                return;
            }
            case 16: {
                this.t = new short[shortArray.length - 6];
                this.n = (byte)shortArray[5];
                System.arraycopy(shortArray, 6, this.t, 0, this.t.length);
                this.b(shortArray[1], shortArray[2]);
                this.b = new PixelBuffer[1];
                SpriteRenderer d8 = new SpriteRenderer();
                d8.a((int)shortArray[3], false);
                int[] intArray = d8.b(shortArray[4], (byte)shortArray[5]);
                this.b[0] = new PixelBuffer();
                this.b[0] = ImageTransformer.a(d8, (int)shortArray[4], intArray, (byte)shortArray[5], this.b[0]);
                this.t[1] = (short)(this.b[0].c / this.t[2]);
                d8.a();
                return;
            }
            case 8: {
                this.t = new short[shortArray.length - 6];
                this.n = (byte)shortArray[5];
                System.arraycopy(shortArray, 6, this.t, 0, this.t.length);
                this.b(shortArray[1], shortArray[2]);
                this.b = new PixelBuffer[2];
                SpriteRenderer d9 = new SpriteRenderer();
                d9.a((int)shortArray[3], false);
                int[] intArray = d9.b(shortArray[4], (byte)shortArray[5]);
                this.b[0] = new PixelBuffer();
                this.b[0] = ImageTransformer.a(d9, (int)shortArray[4], intArray, (byte)shortArray[5], this.b[0]);
                d9.a();
                this.b[1] = this.b[0].a();
                if (this.t[4] == 1) {
                    this.b[1] = ImageTransformer.b(ImageTransformer.a(this.b[1], this.t[2]), 1, 50);
                    this.b[1].d += this.t[3];
                    this.b[1].e += this.t[4];
                }
                return;
            }
            case 11: 
            case 14: {
                this.t = new short[shortArray.length - 7 - (shortArray[6] - 1 << 2)];
                this.n = (byte)shortArray[5];
                System.arraycopy(shortArray, 7 + (shortArray[6] - 1 << 2), this.t, 0, this.t.length);
                this.b(shortArray[1], shortArray[2]);
                this.b = new PixelBuffer[shortArray[6]];
                SpriteRenderer d10 = new SpriteRenderer();
                d10.a((int)shortArray[3], false);
                int[] intArray = d10.b(shortArray[4], (byte)shortArray[5]);
                this.b[0] = new PixelBuffer();
                this.b[0] = ImageTransformer.a(d10, (int)shortArray[4], intArray, (byte)shortArray[5], this.b[0]);
                if (shortArray[0] == 11) {
                    for (int i3 = 1; i3 < this.b.length; ++i3) {
                        this.b[i3] = this.b[0].a();
                        this.b[i3] = ImageTransformer.a(this.b[i3], (int)shortArray[7 + (i3 - 1 << 2)], shortArray[8 + (i3 - 1 << 2)], shortArray[9 + (i3 - 1 << 2)], shortArray[10 + (i3 - 1 << 2)]);
                    }
                } else {
                    for (int i4 = 1; i4 < this.b.length; ++i4) {
                        this.b[i4] = this.b[0].a();
                        this.b[i4] = ImageTransformer.b(this.b[i4], shortArray[7 + (i4 - 1 << 2)], shortArray[8 + (i4 - 1 << 2)]);
                    }
                }
                d10.a();
                return;
            }
            case 12: {
                int n4;
                this.n = (byte)shortArray[5];
                this.t = new short[shortArray.length - 9];
                System.arraycopy(shortArray, 9, this.t, 0, this.t.length);
                this.b(shortArray[1], shortArray[2]);
                this.b = new PixelBuffer[shortArray[6]];
                SpriteRenderer d11 = new SpriteRenderer();
                d11.a((int)shortArray[3], false);
                int[] intArray = d11.b(shortArray[4], (byte)shortArray[5]);
                this.b[0] = new PixelBuffer();
                this.b[0] = ImageTransformer.a(d11, (int)shortArray[4], intArray, (byte)shortArray[5], this.b[0]);
                for (n4 = 1; n4 < this.b.length; ++n4) {
                    this.b[n4] = this.b[0].a();
                }
                for (n4 = 0; n4 < this.b.length; ++n4) {
                    this.b[n4] = ImageTransformer.b(this.b[n4], shortArray[n4 + 7]);
                }
                d11.a();
                return;
            }
            case 13: {
                int n5;
                this.n = (byte)shortArray[5];
                this.t = new short[shortArray.length - 7 - shortArray[6]];
                System.arraycopy(shortArray, 7 + shortArray[6], this.t, 0, this.t.length);
                this.b(shortArray[1], shortArray[2]);
                this.b = new PixelBuffer[shortArray[6]];
                SpriteRenderer d12 = new SpriteRenderer();
                d12.a((int)shortArray[3], false);
                int[] intArray = d12.b(shortArray[4], (byte)shortArray[5]);
                this.b[0] = new PixelBuffer();
                this.b[0] = ImageTransformer.a(d12, (int)shortArray[4], intArray, (byte)shortArray[5], this.b[0]);
                for (n5 = 1; n5 < this.b.length; ++n5) {
                    this.b[n5] = this.b[0].a();
                }
                for (n5 = 0; n5 < this.b.length; ++n5) {
                    this.b[n5] = ImageTransformer.b(this.b[n5], shortArray[n5 + 7]);
                }
                return;
            }
            case 15: {
                this.n = (byte)shortArray[5];
                this.t = new short[shortArray.length - 7 - (shortArray[6] - 1 << 2)];
                System.arraycopy(shortArray, 7 + (shortArray[6] - 1 << 2), this.t, 0, this.t.length);
                this.b(shortArray[1], shortArray[2]);
                this.b = new PixelBuffer[shortArray[6]];
                SpriteRenderer d13 = new SpriteRenderer();
                d13.a((int)shortArray[3], false);
                int[] intArray = d13.b(shortArray[4], (byte)shortArray[5]);
                this.b[0] = new PixelBuffer();
                this.b[0] = ImageTransformer.a(d13, (int)shortArray[4], intArray, (byte)shortArray[5], this.b[0]);
                for (int i5 = 1; i5 < this.b.length; ++i5) {
                    this.b[i5] = this.b[0].a();
                    this.b[i5] = ImageTransformer.a(this.b[i5], (int)shortArray[7 + (i5 - 1 << 2)], shortArray[8 + (i5 - 1 << 2)], shortArray[9 + (i5 - 1 << 2)], shortArray[10 + (i5 - 1 << 2)]);
                }
                d13.a();
                return;
            }
        }
        this.n = (byte)shortArray[2];
        this.a.a(this.x[this.u - 20], false);
        this.a.a((byte)shortArray[1], (byte)0, true);
    }

    private void f() {
        if (this.b != null) {
            for (int i = 0; i < this.b.length; ++i) {
                this.b[i].a = null;
                this.b[i] = null;
            }
            this.b = null;
        }
        if (this.t != null) {
            this.t = null;
        }
    }

    public final void a() {
        this.a(true);
        this.b(true);
    }

    public final void b() {
        this.a(false);
        this.b(false);
    }

    public final boolean c() {
        return this.u == 8 && this.e;
    }

    public final boolean d() {
        return this.a.e();
    }

    public final boolean a(int n2) {
        return this.a.b(n2);
    }

    public final boolean e() {
        if (!this.e) {
            return false;
        }
        switch (this.u) {
            case 0: {
                if (this.t[1] < this.t[2] / 5) {
                    this.b[2] = this.b[0].a();
                    this.b[2] = this.t[1] % 2 == 1 ? ImageTransformer.b(ImageTransformer.a(this.b[2], 6), 5, 1) : ImageTransformer.b(this.b[2], 2, 1);
                } else if (this.t[1] < (this.t[2] << 2) / 5) {
                    this.b[2] = this.t[1] % 4 == 1 || this.t[1] % 4 == 2 ? this.b[0].a() : this.b[1].a();
                    this.b[2] = this.t[1] % 2 == 1 ? ImageTransformer.b(ImageTransformer.a(this.b[2], 8), 8, 1) : ImageTransformer.b(ImageTransformer.a(this.b[2], 4), 4, 1);
                } else {
                    this.b[2] = this.b[1].a();
                    this.b[2] = this.t[1] % 2 == 1 ? ImageTransformer.b(ImageTransformer.a(this.b[2], 6), 5, 1) : ImageTransformer.b(this.b[2], 2, 1);
                }
                if (this.t[1] < this.t[2]) {
                    this.t[1] = (short)(this.t[1] + 1);
                    break;
                }
                this.b();
                this.f();
                return false;
            }
            case 1: {
                switch (this.t[4]) {
                    case 0: {
                        int[] intArray = new int[4];
                        for (int i = 0; i < this.b[1].b; ++i) {
                            int n2;
                            for (n2 = 0; n2 < 4; ++n2) {
                                intArray[n2] = this.b[1].a[i + n2 * this.b[1].b];
                            }
                            for (n2 = 0; n2 < this.b[1].c - 4; ++n2) {
                                this.b[1].a[i + n2 * this.b[1].b] = this.b[1].a[i + (n2 + 4) * this.b[1].b];
                            }
                            for (n2 = 0; n2 < 4; ++n2) {
                                this.b[1].a[i + (n2 + this.b[1].c - 4) * this.b[1].b] = intArray[n2];
                            }
                        }
                        break;
                    }
                    case 1: {
                        int[] intArray = new int[4];
                        for (int i3 = 0; i3 < this.b[1].b; ++i3) {
                            int n3;
                            for (n3 = 0; n3 < 4; ++n3) {
                                intArray[n3] = this.b[1].a[i3 + (this.b[1].c - 4 + n3) * this.b[1].b];
                            }
                            for (n3 = this.b[1].c - 1; n3 > 3; --n3) {
                                this.b[1].a[i3 + n3 * this.b[1].b] = this.b[1].a[i3 + (n3 - 4) * this.b[1].b];
                            }
                            for (n3 = 0; n3 < 4; ++n3) {
                                this.b[1].a[i3 + n3 * this.b[1].b] = intArray[n3];
                            }
                        }
                        break;
                    }
                    case 2: {
                        int[] intArray = new int[4];
                        for (int i4 = 0; i4 < this.b[1].c; ++i4) {
                            int n4;
                            for (n4 = 0; n4 < 4; ++n4) {
                                intArray[n4] = this.b[1].a[i4 * this.b[1].c + n4];
                            }
                            for (n4 = 0; n4 < this.b[1].b - 4; ++n4) {
                                this.b[1].a[i4 * this.b[1].c + n4] = this.b[1].a[i4 * this.b[1].c + n4 + 4];
                            }
                            for (n4 = 0; n4 < 4; ++n4) {
                                this.b[1].a[i4 * this.b[1].c + n4 + this.b[1].b - 4] = intArray[n4];
                            }
                        }
                        break;
                    }
                    case 3: {
                        int[] intArray = new int[4];
                        for (int i5 = 0; i5 < this.b[1].c; ++i5) {
                            int n5;
                            for (n5 = 0; n5 < 4; ++n5) {
                                intArray[n5] = this.b[1].a[i5 * this.b[1].c + this.b[1].b - 4 + n5];
                            }
                            for (n5 = this.b[1].b - 1; n5 > 3; --n5) {
                                this.b[1].a[i5 * this.b[1].c + n5] = this.b[1].a[i5 * this.b[1].c + n5 - 4];
                            }
                            for (n5 = 0; n5 < 4; ++n5) {
                                this.b[1].a[i5 * this.b[1].c + n5] = intArray[n5];
                            }
                        }
                        break;
                    }
                }
                this.b[2] = this.b[0].a();
                this.b[2] = ImageTransformer.a(this.b[2], this.b[1], (byte)this.t[3]);
                if (this.t[0] < this.t[1]) {
                    this.t[0] = (short)(this.t[0] + 1);
                    break;
                }
                this.b();
                this.f();
                return false;
            }
            case 2: {
                break;
            }
            case 3: {
                break;
            }
            case 4: {
                break;
            }
            case 5: {
                break;
            }
            case 6: {
                break;
            }
            case 7: 
            case 9: 
            case 10: 
            case 16: 
            case 17: {
                if (this.t[0] < this.t[1]) {
                    this.t[0] = (short)(this.t[0] + 1);
                    break;
                }
                this.b();
                this.f();
                return false;
            }
            case 8: {
                if (this.t[0] < this.t[1] / this.t[3] * this.t[2]) {
                    if (this.t[4] == 1) {
                        this.b[1] = this.b[0].a();
                    }
                    this.b[1] = ImageTransformer.b(ImageTransformer.a(this.b[1], this.t[5 + (this.t[2] - 1) * 3]), 1, 50);
                    this.b[1].d += this.t[6 + (this.t[2] - 1) * 3];
                    this.b[1].e += this.t[7 + (this.t[2] - 1) * 3];
                } else {
                    this.t[2] = (short)(this.t[2] + 1);
                }
                if (this.t[0] < this.t[1]) {
                    this.t[0] = (short)(this.t[0] + 1);
                    break;
                }
                this.b();
                this.f();
                return false;
            }
            case 11: 
            case 12: 
            case 13: 
            case 14: 
            case 15: {
                if (this.t[2] < this.t[3]) {
                    this.t[2] = (short)(this.t[2] + 1);
                    break;
                }
                this.t[2] = 0;
                this.t[0] = (short)(this.t[0] + 1);
                if (this.t[0] < this.t[1]) break;
                this.t[0] = 0;
                this.b();
                this.f();
                return false;
            }
            default: {
                this.a.onPointerEvent();
            }
        }
        return true;
    }

    public final void a(Graphics g) {
        if (!this.f || !this.g) {
            return;
        }
        switch (this.u) {
            case 0: {
                g.drawRGB(this.b[2].a, 0, this.b[2].b, this.i + this.b[2].d, this.j + this.b[2].e, this.b[2].b, this.b[2].c, true);
                return;
            }
            case 1: {
                g.drawRGB(this.b[2].a, 0, this.b[2].b, this.i + this.b[2].d, this.j + this.b[2].e, this.b[2].b, this.b[2].c, true);
                return;
            }
            case 2: {
                return;
            }
            case 3: {
                return;
            }
            case 4: {
                return;
            }
            case 5: {
                return;
            }
            case 6: {
                return;
            }
            case 7: {
                if (this.t[0] / this.t[2] % 2 == 0) {
                    g.drawRGB(this.b[1].a, 0, this.b[1].b, this.i + this.b[0].d + this.v, this.j + this.b[0].e + this.w, this.b[1].b, this.b[1].c, true);
                    return;
                }
                g.drawRGB(this.b[0].a, 0, this.b[0].b, this.i + this.b[0].d, this.j + this.b[0].e, this.b[0].b, this.b[0].c, true);
                return;
            }
            case 9: 
            case 10: {
                g.drawRGB(this.b[0].a, 0, this.b[0].b, this.i + this.b[0].d, this.j + this.b[0].e, this.b[0].b, this.b[0].c, true);
                if (this.t[0] / this.t[2] % 2 != 0) break;
                g.drawRGB(this.b[1].a, 0, this.b[1].b, this.i + this.b[1].d, this.j + this.b[1].e, this.b[1].b, this.b[1].c, true);
                return;
            }
            case 17: {
                g.drawRGB(this.b[0].a, 0, this.b[0].b, this.i + this.b[0].d, this.j + this.b[0].e + this.t[3], this.b[0].b, this.b[0].c, true);
                if (this.t[0] / this.t[2] % 2 != 0) break;
                g.drawRGB(this.b[1].a, 0, this.b[1].b, this.i + this.b[1].d, this.j + this.b[1].e + this.t[3], this.b[1].b, this.b[1].c, true);
                return;
            }
            case 16: {
                for (int i = 0; i < this.t[2]; ++i) {
                    for (int i3 = 0; i3 < this.b[0].b * this.t[0]; ++i3) {
                        if (this.b[0].a[i * this.t[1] * this.b[0].b + i3] == 0xFFFFFF || this.b[0].a[i * this.t[1] * this.b[0].b + i3] == 0) continue;
                        this.b[0].a[i * this.t[1] * this.b[0].b + i3] = this.b[0].a[i * this.t[1] * this.b[0].b + i3] & 0xFFFFFF;
                    }
                }
                g.drawRGB(this.b[0].a, 0, this.b[0].b, this.i + this.b[0].d, this.j + this.b[0].e, this.b[0].b, this.b[0].c, true);
                return;
            }
            case 8: {
                g.drawRGB(this.b[1].a, 0, this.b[1].b, this.i + this.b[1].d, this.j + this.b[1].e, this.b[1].b, this.b[1].c, true);
                return;
            }
            case 11: 
            case 14: {
                g.setColor(0xFF00FF);
                for (int i4 = 1; i4 < this.b.length; ++i4) {
                    if (this.n == 1) {
                        g.drawRGB(this.b[i4].a, 0, this.b[i4].b, this.i + this.b[i4].d - this.t[4 + (this.t[0] * (this.b.length - 1) << 1) + (i4 - 1 << 1)], this.j + this.b[i4].e + this.t[4 + (this.t[0] * (this.b.length - 1) << 1) + (i4 - 1 << 1) + 1], this.b[i4].b, this.b[i4].c, true);
                        continue;
                    }
                    g.drawRGB(this.b[i4].a, 0, this.b[i4].b, this.i + this.b[i4].d + this.t[4 + (this.t[0] * (this.b.length - 1) << 1) + (i4 - 1 << 1)], this.j + this.b[i4].e + this.t[4 + (this.t[0] * (this.b.length - 1) << 1) + (i4 - 1 << 1) + 1], this.b[i4].b, this.b[i4].c, true);
                }
                return;
            }
            case 12: {
                if (this.n == 1) {
                    g.drawRGB(this.b[1].a, 0, this.b[1].b, this.i + this.b[1].d - (this.t[4 + (this.t[1] << 1) + (this.t[0] << 1)] + this.t[4 + (this.t[0] << 1)]), this.j + this.b[1].e - this.t[4 + (this.t[1] << 1) + (this.t[0] << 1) + 1] + this.t[4 + (this.t[0] << 1) + 1], this.b[1].b, this.b[1].c, true);
                    g.drawRGB(this.b[0].a, 0, this.b[0].b, this.i + this.b[0].d - this.t[4 + (this.t[0] << 1)], this.j + this.b[0].e + this.t[4 + (this.t[0] << 1) + 1], this.b[0].b, this.b[0].c, true);
                    return;
                }
                g.drawRGB(this.b[1].a, 0, this.b[1].b, this.i + this.b[1].d + this.t[4 + (this.t[1] << 1) + (this.t[0] << 1)] + this.t[4 + (this.t[0] << 1)], this.j + this.b[1].e - this.t[4 + (this.t[1] << 1) + (this.t[0] << 1) + 1] + this.t[4 + (this.t[0] << 1) + 1], this.b[1].b, this.b[1].c, true);
                g.drawRGB(this.b[0].a, 0, this.b[0].b, this.i + this.b[0].d + this.t[4 + (this.t[0] << 1)], this.j + this.b[0].e + this.t[4 + (this.t[0] << 1) + 1], this.b[0].b, this.b[0].c, true);
                return;
            }
            case 13: {
                for (int i5 = 0; i5 < this.b.length; ++i5) {
                    if (this.n == 1) {
                        g.drawRGB(this.b[i5].a, 0, this.b[i5].b, this.i + this.b[i5].d - this.t[4 + (this.t[0] * this.b.length << 1) + (i5 << 1)], this.j + this.b[i5].e + this.t[4 + (this.t[0] * this.b.length << 1) + (i5 << 1) + 1], this.b[i5].b, this.b[i5].c, true);
                        continue;
                    }
                    g.drawRGB(this.b[i5].a, 0, this.b[i5].b, this.i + this.b[i5].d + this.t[4 + (this.t[0] * this.b.length << 1) + (i5 << 1)], this.j + this.b[i5].e + this.t[4 + (this.t[0] * this.b.length << 1) + (i5 << 1) + 1], this.b[i5].b, this.b[i5].c, true);
                }
                return;
            }
            case 15: {
                int n2 = 4 + this.t[0] * 3;
                if (this.n == 1) {
                    g.drawRGB(this.b[this.t[n2]].a, 0, this.b[this.t[n2]].b, this.i + this.b[this.t[n2]].d - this.t[n2 + 1], this.j + this.b[this.t[n2]].e + this.t[n2 + 2], this.b[this.t[n2]].b, this.b[this.t[n2]].c, true);
                    return;
                }
                g.drawRGB(this.b[this.t[n2]].a, 0, this.b[this.t[n2]].b, this.i + this.b[this.t[n2]].d + this.t[n2 + 1], this.j + this.b[this.t[n2]].e + this.t[n2 + 2], this.b[this.t[n2]].b, this.b[this.t[n2]].c, true);
                return;
            }
            default: {
                this.a.a(g, this.i, this.j, this.n);
            }
        }
    }
}

