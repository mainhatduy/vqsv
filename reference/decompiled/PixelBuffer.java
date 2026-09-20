/*
 * Decompiled with CFR 0.152.
 */
/**
 * PixelBuffer - Low-level ARGB pixel array buffer with width, height, and cloning support.
 */
public final class PixelBuffer {
    public int[] a;
    public int b;
    public int c;
    public int d;
    public int e;
    public int f;

    public final void a(int[] intArray, int n2, int n3) {
        this.a = intArray;
        this.b = n2;
        this.c = n3;
        this.f = intArray.length;
    }

    public final PixelBuffer a() {
        PixelBuffer e2 = new PixelBuffer();
        new PixelBuffer().a = new int[this.a.length];
        System.arraycopy(this.a, 0, e2.a, 0, e2.a.length);
        e2.b = this.b;
        e2.c = this.c;
        e2.f = this.a.length;
        e2.d = this.d;
        e2.e = this.e;
        return e2;
    }
}

