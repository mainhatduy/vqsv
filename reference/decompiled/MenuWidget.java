/*
 * Decompiled with CFR 0.152.
 */
import javax.microedition.lcdui.Graphics;

/**
 * MenuWidget - Context menu and option selection widget with cursor navigation.
 */
public final class MenuWidget
implements UIComponent {
    private int c = -1;
    private int d = 0;
    private int e = 0;
    private int f = 0;
    private int g = 0;
    private int h = 0;
    private byte[][] i = null;
    private UIButton j = new UIButton();
    private int k = -1;
    private byte l;
    private NumericInputWidget m = null;
    private UIComponent[] n = new UIComponent[60];
    public NumericInputWidget a = null;
    public NumericInputWidget b = null;
    private boolean o = true;

    public MenuWidget() {
        this.l = (byte)9;
    }

    public final void a(boolean flag) {
        this.o = flag;
    }

    public final void a(byte[][] byArray) {
        this.i = byteArray;
    }

    public final byte[][] l() {
        return this.i;
    }

    public final void a(Graphics g, boolean flag2, boolean flag3, UIComponent w2, int[] intArray) {
        if (!this.o) {
            return;
        }
        for (int i = 0; i < this.n.length && this.n[i2] != null; ++i) {
            if (this.n[i].f() != null) {
                boolean bl3 = false;
                int n2 = EngineUtils.randomInt(intArray);
                if (n2 > 0 && intArray[n2 - 1] == this.c) {
                    bl3 = true;
                }
                this.n[i].f().a(g, this.n[i].a(), bl3, intArray, flag3, w2);
                continue;
            }
            this.n[i].a(g, flag2, flag3, w2, intArray);
        }
    }

    public final void a(boolean flag, boolean flag2, UIComponent w2, int[] intArray) {
        for (int i = 0; i < this.n.length && this.n[i2] != null; ++i) {
            if (this.n[i].f() != null) {
                this.n[i].f().a(this.n[i].a(), intArray, flag2, w2);
                continue;
            }
            this.n[i].a(flag, flag2, w2, intArray);
        }
    }

    public final int a() {
        return this.c;
    }

    public final void a(int n2) {
        this.c = n2;
    }

    public final int b() {
        return this.d;
    }

    public final void a(int n2, UIComponent w2) {
        this.d = n2;
        this.a(w2);
    }

    public final int c() {
        return this.e;
    }

    public final void b(int n2, UIComponent w2) {
        this.e = n2;
        this.a(w2);
    }

    public final int d() {
        return this.f;
    }

    public final void c(int n2, UIComponent w2) {
        this.f = n2;
        this.a(w2);
    }

    public final int e() {
        return this.g;
    }

    public final void d(int n2, UIComponent w2) {
        this.g = n2;
        this.a(w2);
    }

    public final NumericInputWidget f() {
        return this.m;
    }

    public final void a(NumericInputWidget z2) {
        this.m = z2;
    }

    public final UIComponent[] g() {
        return this.n;
    }

    public final UIButton h() {
        return this.j;
    }

    public final void a(UIButton k2) {
        this.j = k2;
    }

    public final int i() {
        return this.h;
    }

    public final void b(int n2) {
        this.h = n2;
    }

    public final int j() {
        return this.k;
    }

    public final void c(int n2) {
        this.k = n2;
    }

    public final void a(UIComponent w2) {
        if (w2 != null) {
            if (this.k > 0 && this.l != 9) {
                UIComponent w3 = EngineUtils.a(w2, this.k);
                switch (this.l) {
                    case 4: {
                        this.d = w3.b();
                        this.e = w3.c();
                        this.f = w3.d();
                        this.g = w3.e();
                        break;
                    }
                    case 3: {
                        this.d = w3.b();
                        this.e = w3.c() + (w3.e() - this.g) / 2;
                        this.g = w3.e();
                        break;
                    }
                    case 5: {
                        this.d = w3.b() + (w3.d() - this.f);
                        this.e = w3.c() + (w3.e() - this.g) / 2;
                        this.g = w3.e();
                        break;
                    }
                    case 6: {
                        this.d = w3.b();
                        this.e = w3.c() + (w3.e() - this.g);
                        break;
                    }
                    case 8: {
                        this.d = w3.b() + (w3.d() - this.f);
                        this.e = w3.c() + (w3.e() - this.g);
                        break;
                    }
                    case 7: {
                        this.d = w3.b() + (w3.d() - this.f) / 2;
                        this.e = w3.c() + (w3.e() - this.g);
                        this.f = w3.d();
                        break;
                    }
                    case 0: {
                        this.d = w3.b();
                        this.e = w3.c();
                        break;
                    }
                    case 2: {
                        this.d = w3.b() + (w3.d() - this.f);
                        this.e = w3.c();
                        break;
                    }
                    case 1: {
                        this.d = w3.b() + (w3.d() - this.f) / 2;
                        this.e = w3.c();
                        this.f = w3.d();
                    }
                }
            }
            if (this.n != null) {
                for (int i = 0; i < this.n.length && this.n[i2] != null; ++i) {
                    this.n[i].a(w2);
                }
            }
        }
    }

    public final void k() {
        if (this.b != null) {
            this.b.getInstance();
            this.b = null;
        }
        if (this.a != null) {
            this.a.a();
            this.a = null;
        }
        if (this.n != null) {
            for (int i = 0; i < this.n.length; ++i) {
                if (this.n[i] != null) {
                    this.n[i].k();
                }
                this.n[i] = null;
            }
            this.n = null;
        }
        if (this.m != null) {
            this.m = null;
        }
        if (this.i != null) {
            this.i = null;
        }
        if (this.j != null) {
            this.j.c();
            this.j = null;
        }
    }
}

