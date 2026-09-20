/*
 * Decompiled with CFR 0.152.
 */
import javax.microedition.lcdui.Graphics;

/**
 * ItemListWidget - Scrollable item list selector widget for inventory and shop menus.
 */
public final class ItemListWidget
implements UIComponent {
    private int d = -1;
    private int e = 0;
    private int f = 0;
    private int g = 0;
    private int h = 0;
    private int i = 1;
    private UIButton j;
    private int k = -1;
    private byte l;
    private NumericInputWidget m = null;
    public byte a = (byte)-1;
    public byte b = (byte)-1;
    TextPainter c;
    private boolean n = true;

    public ItemListWidget() {
        this.j = new UIButton();
        this.l = (byte)9;
    }

    public final void l() {
        Rectangle ak2 = new Rectangle(this.e, this.f, this.g, this.h);
        this.j.a(ak2);
        this.j.getInstance();
    }

    public final void a(Graphics g, boolean flag2, boolean flag3, UIComponent w2, int[] intArray) {
        if (!this.n) {
            return;
        }
        if (this.j != null) {
            this.j.a(g, this.e, this.f, this.g, this.h, flag2, this.a, this.b, this.c);
        }
    }

    public final void a(boolean flag, boolean flag2, UIComponent w2, int[] intArray) {
        if (this.j != null) {
            flag2 = flag;
            UIButton k2 = this.j;
            if (flag2) {
                if (k2.i != null) {
                    k2.i.c();
                    return;
                }
            } else if (k2.m != null) {
                k2.m.c();
            }
        }
    }

    public final void a(boolean flag) {
        this.n = flag;
    }

    public final int a() {
        return this.d;
    }

    public final void a(int n2) {
        this.d = n2;
    }

    public final int b() {
        return this.e;
    }

    public final void a(int n2, UIComponent w2) {
        this.e = n2;
        this.a(w2);
    }

    public final int c() {
        return this.f;
    }

    public final void b(int n2, UIComponent w2) {
        this.f = n2;
        this.a(w2);
    }

    public final int d() {
        return this.g;
    }

    public final void c(int n2, UIComponent w2) {
        this.g = n2;
        this.a(w2);
    }

    public final int e() {
        return this.h;
    }

    public final void d(int n2, UIComponent w2) {
        this.h = n2;
        this.a(w2);
    }

    public final NumericInputWidget f() {
        return this.m;
    }

    public final void a(NumericInputWidget z2) {
        this.m = z2;
    }

    public final UIComponent[] g() {
        return null;
    }

    public final UIButton h() {
        return this.j;
    }

    public final void a(UIButton k2) {
        this.j = k2;
    }

    public final int i() {
        return this.i;
    }

    public final void b(int n2) {
        this.i = n2;
    }

    public final int j() {
        return this.k;
    }

    public final void c(int n2) {
        this.k = n2;
    }

    public final void a(UIComponent w2) {
        if (w2 != null && this.k > 0 && this.l != 9) {
            w2 = EngineUtils.a(w2, this.k);
            switch (this.l) {
                case 4: {
                    this.e = w2.b();
                    this.f = w2.c();
                    this.g = w2.d();
                    this.h = w2.e();
                    return;
                }
                case 3: {
                    this.e = w2.b();
                    this.f = w2.c() + (w2.e() - this.h) / 2;
                    this.h = w2.e();
                    return;
                }
                case 5: {
                    this.e = w2.b() + (w2.d() - this.g);
                    this.f = w2.c() + (w2.e() - this.h) / 2;
                    this.h = w2.e();
                    return;
                }
                case 6: {
                    this.e = w2.b();
                    this.f = w2.c() + (w2.e() - this.h);
                    return;
                }
                case 8: {
                    this.e = w2.b() + (w2.d() - this.g);
                    this.f = w2.c() + (w2.e() - this.h);
                    return;
                }
                case 7: {
                    this.e = w2.b() + (w2.d() - this.g) / 2;
                    this.f = w2.c() + (w2.e() - this.h);
                    this.g = w2.d();
                    return;
                }
                case 0: {
                    this.e = w2.b();
                    this.f = w2.c();
                    return;
                }
                case 2: {
                    this.e = w2.b() + (w2.d() - this.g);
                    this.f = w2.c();
                    return;
                }
                case 1: {
                    this.e = w2.b() + (w2.d() - this.g) / 2;
                    this.f = w2.c();
                    this.g = w2.d();
                }
            }
        }
    }

    public final void k() {
        if (this.m != null) {
            this.m = null;
        }
        if (this.j != null) {
            this.j.c();
            this.j = null;
        }
        if (this.c != null) {
            this.c = null;
        }
    }
}

