/*
 * Decompiled with CFR 0.152.
 */
/**
 * UIHotspot - Interactive rectangular region and button hotspot with optional sprite icon.
 */
public final class UIHotspot {
    public SpriteWidget a;
    private int b;
    private int c;
    private int d;
    private int e;
    private int f;

    public final int a() {
        return this.f;
    }

    public final int b() {
        return this.b;
    }

    public final void a(int n2) {
        this.b = n2;
    }

    public final int c() {
        return this.c;
    }

    public final void b(int n2) {
        this.c = n2;
    }

    public final int d() {
        return this.d;
    }

    public final void c(int n2) {
        this.d = n2;
    }

    public final int e() {
        return this.e;
    }

    public final void d(int n2) {
        this.e = n2;
    }

    public UIHotspot() {
        this.f = 0;
        this.a = null;
    }

    public UIHotspot(int n2, int n3, int n4, int n5) {
        this.a = null;
        this.b = n2;
        this.c = n3;
        this.d = n4;
        this.e = n5;
        this.f = 1;
    }

    public UIHotspot(int n2, byte val2, int n3, int n4, int n5, int n6) {
        if (n2 != -1) {
            this.a = new SpriteWidget();
            this.a.a(n2);
            this.a.a = val2;
        } else {
            this.a = null;
        }
        this.b = n3;
        this.c = n4;
        this.d = n5;
        this.e = n6;
        this.f = 1;
    }

    public UIHotspot(byte val) {
        this.a = null;
        this.b = 0;
        this.c = 0;
        this.d = 0;
        this.e = 0;
        this.f = 0;
    }
}

