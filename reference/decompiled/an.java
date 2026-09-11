/*
 * Decompiled with CFR 0.152.
 */
import game.GameMIDLet;
import game.e;
import game.g;
import game.h;
import game.k;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Vector;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Font;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

public abstract class an
extends ap
implements c,
x {
    private static short a;
    private static short b;
    private static int c;
    private static Font d;
    private static Font e;
    private static int f;
    public byte P;
    public byte Q;
    public ab R;
    public h S;
    private static boolean g;
    public static boolean T;
    public static byte U;
    public static byte V;
    private static byte[][] h;
    private static Timer i;
    private static TimerTask j;
    private static boolean k;
    public static boolean W;
    public static boolean X;
    private byte l;
    private static byte[] m;
    private ag n = null;
    private byte o;
    private byte p;
    private byte q;
    private String[] r = new String[]{"01", "02", "03", "04", "05"};
    private byte[][] s = new byte[][]{{4, 1, 0}, {2, 1, 1}, {2, 1, 2}, {2, 1, 3}, {2, 1, 4}};
    private String[][] t = new String[][]{{"K\u00edch ho\u1ea1t", "B\u1ea1n mu\u1ed1n kh\u00e1m ph\u00e1 b\u00ed m\u1eadt c\u1ee7a v\u01b0\u01a1ng qu\u1ed1c s\u1ee7ng v\u1eadt, d\u1eabn d\u1eaft th\u00fa y\u00eau chi\u1ebfn \u0111\u1ea5u, ti\u1ebfn h\u00f3a, \u1ea5p tr\u1ee9ng? Ch\u1ec9 c\u1ea7n 1 tin nh\u1eafn 15000\u0111 \u0111\u1ec3 k\u00edch ho\u1ea1t tr\u00f2 ch\u01a1i, ch\u1ec9 nh\u1eafn tin 1 l\u1ea7n cho t\u1ea5t c\u1ea3 c\u00e1c l\u01b0\u1ee3t ch\u01a1i. B\u1ea1n c\u00f3 mu\u1ed1n nh\u1eafn tin kh\u00f4ng?"}, {"T\u1ea5t tr\u00fang c\u1ea7u", "Ch\u1ec9 c\u1ea7n nh\u1eafn 1 tin nh\u1eafn 10000\u0111, b\u1ea1n s\u1ebd s\u1edf h\u1eefu 1 t\u1ea5t tr\u00fang c\u1ea7u, t\u1ef7 l\u1ec7 100% b\u1eaft \u0111\u01b0\u1ee3c s\u1ee7ng v\u1eadt? B\u1ea1n c\u00f3 mu\u1ed1n nh\u1eafn tin kh\u00f4ng?"}, {"Mua s\u1eafm kim ti\u1ec1n", "Ki\u1ebfm ti\u1ec1n v\u1ea5t v\u1ea3, v\u1eadt ph\u1ea9m \u0111\u1eaft \u0111\u1ecf? Ch\u1ec9 c\u1ea7n nh\u1eafn 1 tin nh\u1eafn 10000\u0111 b\u1ea1n s\u1ebd \u0111\u1ea1t \u0111\u01b0\u1ee3c 10000 kim ti\u1ec1n. B\u1ea1n c\u00f3 mu\u1ed1n nh\u1eafn tin kh\u00f4ng?"}, {"Mua \u0111\u1eb3ng c\u1ea5p", "Th\u0103ng c\u1ea5p ch\u1eadm ch\u1ea1p, k\u1ebb \u0111\u1ecbch l\u1ea1i qu\u00e1 m\u1ea1nh? Ch\u1ec9 c\u1ea7n 1 tin nh\u1eafn 10000\u0111, t\u1ea5t c\u1ea3 s\u1ee7ng v\u1eadt trong ba l\u00f4 c\u1ee7a b\u1ea1n \u0111\u1ec1u \u0111\u01b0\u1ee3c th\u0103ng l\u00ean 5 c\u1ea5p. B\u1ea1n c\u00f3 mu\u1ed1n nh\u1eafn tin kh\u00f4ng?"}, {"Mua s\u1eafm huy hi\u1ec7u", "Ki\u1ebfm huy hi\u1ec7u kh\u00f3 kh\u0103n? Ch\u1ec9 c\u1ea7n 1 tin nh\u1eafn 10000\u0111, b\u1ea1n s\u1ebd \u0111\u1ea1t \u0111\u01b0\u1ee3c 10 huy hi\u1ec7u. B\u1ea1n c\u00f3 mu\u1ed1n nh\u1eafn tin kh\u00f4ng?"}};
    private q u;

    public abstract void b();

    public abstract void b(Graphics var1);

    public abstract boolean d();

    public abstract void f();

    public abstract void a(byte var1);

    public final void s() {
        if (g) {
            return;
        }
        if (i == null || j == null) {
            i = new Timer();
            j = new as();
        }
        i.schedule(j, 10L, 200L);
        g = true;
    }

    protected static void t() {
        if (j != null) {
            j.cancel();
            j = null;
        }
        if (i != null) {
            i.cancel();
            i = null;
            System.gc();
        }
        g = false;
        k = true;
    }

    public static void u() {
        k = false;
    }

    public static boolean v() {
        return k;
    }

    public static void a(short s2, short s3) {
        a = s2;
        b = s3;
    }

    public static short w() {
        return a;
    }

    public static short x() {
        return b;
    }

    public static short y() {
        return (short)(a / 2);
    }

    public static short z() {
        return (short)(b / 2);
    }

    public static void A() {
        c = 66;
    }

    public static int B() {
        return c;
    }

    public static void e(int n2) {
        f = n2;
    }

    public static int C() {
        return f;
    }

    public static Font D() {
        if (d == null) {
            d = Font.getFont(0, 0, 8);
        }
        return d;
    }

    public static Font E() {
        if (e == null) {
            e = Font.getFont(0, 0, 16);
        }
        return e;
    }

    public static int F() {
        if (d == null) {
            return 18;
        }
        return d.stringWidth("S\u1ee7ng");
    }

    public static int G() {
        return d.getHeight();
    }

    public static String f(int n2) {
        if (n2 == 0) {
            return "";
        }
        return aq.d[n2];
    }

    public static String a(int n2, int[] nArray) {
        if (n2 == 0) {
            return "";
        }
        int n3 = 0;
        String string = "";
        int n4 = an.f(n2).indexOf("%s", 0);
        if (n4 == -1) {
            return an.f(n2);
        }
        int n5 = 0;
        while (n4 != -1) {
            string = string + an.f(n2).substring(n5, n4) + nArray[n3];
            ++n3;
            n5 = n4 + 2;
            n4 = an.f(n2).indexOf("%s", n5);
        }
        return string + an.f(n2).substring(n5);
    }

    public static String a(String string, int[] nArray) {
        if (string.equals("")) {
            return "";
        }
        int n2 = 0;
        String string2 = "";
        int n3 = string.indexOf("%s", 0);
        if (n3 == -1) {
            return string;
        }
        int n4 = 0;
        while (n3 != -1) {
            string2 = string2 + string.substring(n4, n3) + nArray[n2];
            ++n2;
            n4 = n3 + 2;
            n3 = string.indexOf("%s", n4);
        }
        return string2 + string.substring(n4);
    }

    public static String a(int n2, String[] stringArray) {
        if (n2 == 0) {
            return "";
        }
        int n3 = 0;
        String string = "";
        int n4 = an.f(n2).indexOf("%s", 0);
        if (n4 == -1) {
            return an.f(n2);
        }
        int n5 = 0;
        while (n4 != -1) {
            string = string + an.f(n2).substring(n5, n4) + stringArray[n3];
            ++n3;
            n5 = n4 + 2;
            n4 = an.f(n2).indexOf("%s", n5);
        }
        return string + an.f(n2).substring(n5);
    }

    public static void a(Graphics graphics, Image image, String string, int n2, int n3, int n4, int n5) {
        for (int i2 = 0; i2 < string.length(); ++i2) {
            int n6 = string.charAt(i2);
            if (Character.isDigit((char)n6)) {
                n6 = (char)(n6 - 48);
            } else {
                switch (n6) {
                    case 45: {
                        n6 = 10;
                        break;
                    }
                    case 43: {
                        n6 = 10;
                    }
                }
            }
            graphics.drawRegion(image, n6 * n4, 0, n4, n5, 0, n2 - ((string.length() - 1 - (i2 << 1)) * n4 >> 1), n3, 20);
        }
    }

    public static boolean H() {
        return U != -1;
    }

    public void l() {
    }

    public void m() {
    }

    public static boolean I() {
        if (U == -1) {
            return true;
        }
        return h[U][0] == 1;
    }

    public static boolean J() {
        if (U == -1) {
            return true;
        }
        return h[U][0] == 2;
    }

    public static boolean b(int n2, int n3) {
        if (U == -1) {
            return false;
        }
        if (n3 != h[U][2]) {
            return true;
        }
        if (h[U][1] == -1) {
            return true;
        }
        return h[U][1] == n2;
    }

    public static void c(int n2, int n3) {
        if (U == -1) {
            return;
        }
        if (n3 == -1) {
            an.h[an.U][2] = 0;
        }
        an.h[an.U][n2] = (byte)n3;
    }

    public static byte K() {
        if (U == -1) {
            return -1;
        }
        return h[U][1];
    }

    public final void b(boolean bl) {
        if (this.l == 4) {
            if (bl) {
                an an2 = this;
                an2.p = (byte)(an2.p + 1);
                byte by = an2.o;
                m[by] = (byte)(m[by] + 1);
                System.out.println(" curNum = " + an2.p + " tolNum = " + an2.q);
                if (an2.p >= an2.q) {
                    switch (an2.o) {
                        case 0: {
                            X = true;
                            game.g.o().s(2000);
                            game.g.o().c(1, 5, (byte)0);
                            game.g.o().c(4, 5, (byte)0);
                            game.g.o().c(11, 2, (byte)0);
                            game.g.o().u(5);
                            game.c.a().b[game.k.a((int)9, (int)0)][5] = 3;
                            game.c.a().a[5].a((byte)3);
                            break;
                        }
                        case 1: {
                            game.g.o().c(0, 1, (byte)0);
                            break;
                        }
                        case 2: {
                            game.g.o().s(10000);
                            break;
                        }
                        case 3: {
                            game.k.G = 0;
                            if (game.k.F == null) {
                                game.k.F = new Vector();
                            }
                            if (game.k.E == null) {
                                game.k.E = new Vector();
                            }
                            game.k.F.removeAllElements();
                            game.k.E.removeAllElements();
                            for (int i2 = 0; i2 < game.g.o().A; ++i2) {
                                if (game.g.o().z[i2].s() == 50) {
                                    game.g.o().z[i2].J();
                                    continue;
                                }
                                game.g.o().z[i2].x();
                                if (game.g.o().z[i2].s() + 5 >= 50) {
                                    game.g.o().z[i2].h(50 - game.g.o().z[i2].s());
                                } else {
                                    game.g.o().z[i2].h(5);
                                }
                                game.g.o().z[i2].I();
                                if (game.g.o().z[i2].E() >= 5 || game.g.o().z[i2].E() >= game.g.o().z[i2].s() / 10 + 1) continue;
                                game.k.E.addElement(game.g.o().z[i2]);
                                game.k.F.addElement("" + i2);
                            }
                            if (game.k.E.size() <= 0) {
                                game.k.G = (byte)2;
                                break;
                            }
                            game.k.G = 1;
                            break;
                        }
                        case 4: {
                            game.g.o().u(10);
                        }
                    }
                }
                an2.d((byte)2);
                return;
            }
            this.d((byte)3);
        }
    }

    private boolean a() {
        if (this.n == null) {
            try {
                this.n = new ag(this);
                this.n.a("sms://");
            }
            catch (ClassNotFoundException classNotFoundException) {
                return false;
            }
        }
        switch (this.o) {
            case 0: {
                this.a((int)this.o);
                break;
            }
            case 1: {
                this.a((int)this.o);
                break;
            }
            case 2: {
                this.a((int)this.o);
                break;
            }
            case 3: {
                this.a((int)this.o);
                break;
            }
            case 4: {
                this.a((int)this.o);
            }
        }
        return true;
    }

    public final boolean c(byte by) {
        this.o = by;
        switch (by) {
            case 0: {
                this.q = 1;
                break;
            }
            case 1: {
                this.q = 1;
                break;
            }
            case 2: {
                this.q = 1;
                break;
            }
            case 3: {
                this.q = 1;
                break;
            }
            case 4: {
                this.q = 1;
            }
        }
        this.p = 0;
        return true;
    }

    public final void d(byte by) {
        while (true) {
            if (by != 5 && by != 0) {
                this.S.aK();
            }
            switch (by) {
                case 1: {
                    System.out.println(" " + an.a(513, new int[]{this.q, this.p}));
                    this.S.d(an.a(513, new int[]{this.q, this.p}));
                    break;
                }
                case 4: {
                    System.out.println(" " + an.f(514));
                    this.S.d(an.f(514));
                    break;
                }
                case 2: {
                    if (this.M()) {
                        if (this.o == 0) {
                            this.S.d(an.f(515) + an.f(633));
                            break;
                        }
                        this.S.d(an.f(515));
                        break;
                    }
                    this.S.d(an.f(516));
                    System.out.println(" " + an.f(516));
                    break;
                }
                case 3: {
                    System.out.println(" " + an.f(516));
                    this.S.d(an.f(516));
                    break;
                }
                case 5: {
                    T = false;
                    this.S.aL();
                }
            }
            this.l = by;
            if (by != 5) break;
            by = 0;
        }
    }

    public final int L() {
        return this.o;
    }

    public final boolean M() {
        return this.p >= this.q;
    }

    public final byte N() {
        return this.l;
    }

    public final byte O() {
        return this.q;
    }

    public final void g(int n2) {
        T = true;
        if (n2 == 1) {
            this.d((byte)4);
            if (!this.a()) {
                this.d((byte)3);
                return;
            }
        } else if (n2 == 2) {
            this.d((byte)5);
        }
    }

    public final void h(int n2) {
        switch (this.l) {
            case 1: {
                this.g(n2);
                return;
            }
            case 3: {
                if (n2 != 1 && n2 != 2) break;
                this.d((byte)5);
            }
        }
    }

    private void a(int n2) {
        u.a(GameMIDLet.a);
        u.a(n2);
        this.u = new q(GameMIDLet.a, game.e.a, "", "", this.r[n2], this.s[n2][0], this.t[n2][0], u.c[n2], "");
        Display.getDisplay(GameMIDLet.a).setCurrent(this.u);
        this.u.a(this);
    }

    public final void a(boolean bl) {
        this.b(bl);
    }

    static {
        g = false;
        T = false;
        U = (byte)-1;
        V = 0;
        h = new byte[7][3];
        k = false;
        X = true;
        m = new byte[]{0, 0, 0, 0, 0};
    }
}

