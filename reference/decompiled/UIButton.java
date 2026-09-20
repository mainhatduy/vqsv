/*
 * Decompiled with CFR 0.152.
 */
import javax.microedition.lcdui.Font;
import javax.microedition.lcdui.Graphics;

/**
 * UIButton - Interactive UI button widget with border, fill color, text label, and icon.
 */
public final class UIButton {
    public String a = "";
    private int[] n;
    private int o;
    public int b;
    public int c;
    public boolean d;
    public int e;
    public int f;
    public int g;
    private boolean p = false;
    private int q = 0;
    public byte h = (byte)-1;
    public SpriteWidget i;
    public int j;
    public int k;
    public int l;
    public SpriteWidget m;
    private Rectangle r = null;
    private String s = "";
    private Font t = Font.getFont(0, 0, 8);
    private boolean[] u = new boolean[]{false, false};

    public UIButton() {
        this.n = new int[2];
        this.n[0] = 0;
        this.n[1] = 0;
        this.o = 2;
        this.b = 4;
        this.c = 4;
        this.d = false;
        this.e = 0xFFFFFF;
        this.f = 0xFFFFFF;
        this.g = 0xFFFFFF;
        this.i = null;
        this.j = 0xFFFFFF;
        this.k = 0xFFFFFF;
        this.l = 0xFFFFFF;
        this.m = null;
        this.p = false;
        this.h = (byte)-1;
        this.q = 0;
    }

    private static void a(Graphics g, Rectangle ak2, int n2) {
        if (g == null || n2 >> 24 == 0) {
            return;
        }
        g.setColor(n2);
        g.fillRect(ak2.a, ak2.b, ak2.c, ak2.d);
    }

    public final void a(Rectangle ak2) {
        this.r = ak2;
    }

    public final void a() {
        this.n[1] = -this.r.d;
        this.n[0] = -this.r.c / 2;
        this.u[1] = false;
        this.u[0] = false;
    }

    public final boolean b() {
        return this.u[0] && this.u[1];
    }

    private void a(Graphics g, Rectangle ak2, String text, int n2, int n3, boolean flag6, byte val7, TextPainter y2, byte val9) {
        if (g == null || n2 >> 24 == 0) {
            return;
        }
        if (text.startsWith("#P") && text.length() > 2) {
            int n4 = Integer.parseInt(text.substring(2).trim()) * ak2.c / 100;
            g.setColor(n2);
            g.fillRect(ak2.a + 1, ak2.b + 1, n4 - 1, ak2.d - 1);
            return;
        }
        if (!this.s.equals(text)) {
            this.n[1] = -ak2.d;
            this.n[0] = -ak2.c / 2;
        }
        this.s = text;
        EngineUtils.a(g, text, n2, ak2.a, ak2.b, this.t.getHeight(), ak2.c, ak2.d, this.t, flag6, n3, this.n, this.o, val7, y2, this.u);
    }

    private static void b(Graphics g, Rectangle ak2, int n2) {
        if (g == null || n2 >> 24 == 0) {
            return;
        }
        g.setColor(n2);
        g.drawRect(ak2.a, ak2.b, ak2.c, ak2.d);
    }

    public final void a(Graphics g, int n2, int n3, int n4, int n5, boolean flag6, byte val7, byte val8, TextPainter y2) {
        Rectangle ak2;
        if (this.p != flag6 || this.q == 0) {
            this.q = -1;
            this.p = flag6;
        }
        this.r = ak2 = new Rectangle(n2, n3, n4, n5);
        g.setClip(0, 0, BaseScreen.getScreenWidth(), BaseScreen.getScreenHeight());
        if (flag6) {
            UIButton.a(g, this.r, this.e);
            UIButton.b(g, this.r, this.f);
            if (this.i != null) {
                this.i.a(g, this.r, this.c);
            }
            this.a(g, this.r, this.a, this.g, this.b, this.d, val7, y2, val8);
            return;
        }
        UIButton.a(g, this.r, this.j);
        UIButton.b(g, this.r, this.k);
        if (this.m != null) {
            this.m.a(g, this.r, this.c);
        }
        this.a(g, this.r, this.a, this.l, this.b, this.d, val7, y2, val8);
    }

    public final void c() {
        if (this.i != null) {
            this.i.d();
            this.i = null;
        }
        if (this.m != null) {
            this.m.d();
            this.m = null;
        }
    }
}

