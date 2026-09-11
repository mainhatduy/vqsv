/*
 * Decompiled with CFR 0.152.
 */
package game;

import game.b;
import game.c;
import game.d;
import game.g;
import game.i;
import game.k;
import java.util.Vector;

public final class h
implements i {
    private static h n;
    private an o;
    private ab p = ab.a();
    private g q;
    protected int a;
    protected int b;
    protected int c;
    private int r;
    protected int d;
    protected int e;
    private int s;
    protected int f;
    protected boolean g;
    private String t = "";
    private byte u;
    private byte v;
    private int w;
    protected int h;
    private int x;
    protected int i;
    private int[] y;
    private int[] z;
    public byte j = (byte)-1;
    private static String[] A;
    private static short[] B;
    private Vector C = new Vector();
    private int D = 0;
    private int E = 0;
    private int F = 0;
    private int G = 0;
    public int k = 0;
    boolean l = false;
    private int H;
    private int I;
    private int J = 0;
    private int K = 0;
    private int L = 0;
    private int M = 0;
    private String[] N = new String[]{"/data/ui/option.ui", "/data/ui/answer.ui", "/data/ui/wharf1.ui"};
    public short[] m = new short[]{9, 0, 120, 448, 9, 1, 136, 272, 9, 2, 208, 256, 9, 3, 80, 264, 9, 4, 112, 288, 9, 5, 40, 280, 9, 6, 136, 328, 9, 7, 104, 328};
    private String[] O = new String[]{"\u0110\u1ea1t \u0111\u01b0\u1ee3c 2000 kim ti\u1ec1n", "\u0110\u1ea1t \u0111\u01b0\u1ee3c 5 Phong \u1ea5n c\u1ea7u", "\u0110\u1ea1t \u0111\u01b0\u1ee3c 5 B\u00e1nh Sandwich", "\u0110\u1ea1t \u0111\u01b0\u1ee3c 2 Sinh m\u1ec7nh th\u1ea1ch", "\u0110\u1ea1t \u0111\u01b0\u1ee3c 2 huy hi\u1ec7u"};
    private short[][] P = new short[][]{{621, 622}, {623, 624}, {625, 626}, {627, 628}, {629, 630, 631, 632}};
    private short[][] Q = new short[][]{{5, 2, 112, 224, 2, 2, 5, 6, 1, 6, 0, 112, 224, 2, 0, 1, 0, 10}, {4, 0, 48, 176, 2, 2, 3, 6, 3, 6, 0, 112, 224, 2, 2, 1, 0, 10}, {3, 6, 288, 224, 3, 0, 3, 6, 3, 6, 0, 112, 224, 2, 2, 1, 0, 10}, {1, 5, 272, 128, 3, 0, 5, 6, 1, 6, 0, 112, 224, 2, 0, 1, 0, 10}, {1, 5, 272, 128, 3, 2, 0, 0, 0, 3, 6, 288, 224, 3, 0, 0, 0, 0, 4, 0, 48, 176, 2, 0, 0, 0, 0, 5, 2, 112, 224, 2, 2, 0, 0, 0}};
    private byte R;
    private byte S;
    private String[] T = new String[]{"D\u1eabn th\u01b0\u1edfng", "Ti\u1ebfn h\u00f3a", "D\u1ecb h\u00f3a", "T\u00e0i li\u1ec7u", "C\u00e1ch m\u1edf"};

    public static h a() {
        if (n == null) {
            n = new h();
        }
        return n;
    }

    public h() {
        if (this.q == null) {
            this.q = game.g.o();
        }
    }

    public final void b() {
        n = null;
        this.q = null;
    }

    public final void a(an an2) {
        if (this.o != null) {
            this.o = null;
        }
        this.o = an2;
        this.g = true;
    }

    public final void c() {
        this.p.a("/data/ui/world.ui", 257, this);
        this.u = 0;
    }

    public final void d() {
        this.p.a.a(5).a(true);
        this.p.a.a(7).a(true);
    }

    private void aS() {
        if (this.p.b("/data/ui/world.ui")) {
            for (int i2 = 1; i2 <= 7; ++i2) {
                if (i2 == 2 || i2 == 3 || i2 == 4) continue;
                this.p.a.a(i2).a(false);
            }
        }
    }

    public final void e() {
        if (this.u < 2 && !game.c.f && game.k.J && this.p.c("/data/ui/world.ui")) {
            if (ab.a(this.p.d("/data/ui/world.ui"), 4)) {
                ((af)this.p.d((String)"/data/ui/world.ui").a((int)6)).h().a = ((k)this.o).k;
                this.u = 1;
            } else if (this.u == 1 && ((af)this.p.d((String)"/data/ui/world.ui").a((int)1)).h().m.a().h() >= 5) {
                ((af)this.p.d((String)"/data/ui/world.ui").a((int)6)).h().a = "";
                this.u = (byte)2;
                game.k.J = false;
            }
        }
        this.f();
    }

    public final boolean f() {
        if (this.v < 2 && this.p.b("/data/ui/openbox.ui")) {
            if (ab.a(this.p.a, 3) && this.p.a.a((int)1).h().m.a().g() == 9) {
                this.p.a.a((int)2).h().a = this.t;
                if (this.p.a.a(2).h().b()) {
                    if (this.p.d()) {
                        this.p.a.a((int)1).h().m.a((byte)12, (byte)-1);
                        this.v = 1;
                    }
                    if (this.o.k(196640)) {
                        this.p.a.a((int)2).h().a = "";
                        this.v = (byte)2;
                        this.g = true;
                        this.aw();
                        return true;
                    }
                }
            } else if (this.v == 1) {
                this.p.a.a((int)2).h().a = "";
                if (this.p.d()) {
                    this.v = (byte)2;
                    this.g = true;
                    this.aw();
                    return true;
                }
            }
        }
        return this.g();
    }

    public final boolean g() {
        if (this.v < 2) {
            if (this.p.b("/data/ui/taskTip.ui")) {
                if (ab.a(this.p.a, 4) && this.p.a.a((int)1).h().m.a().g() == 10) {
                    this.p.a.a((int)2).h().a = this.t;
                    if (this.p.a.a(2).h().b()) {
                        if (this.p.d()) {
                            this.p.a.a((int)1).h().m.a((byte)13, (byte)-1);
                            this.v = 1;
                        }
                        if (this.o.k(196640)) {
                            this.v = (byte)2;
                            this.g = true;
                            this.br();
                            return true;
                        }
                    }
                } else if (this.v == 1) {
                    this.p.a.a((int)2).h().a = "";
                    if (this.p.d()) {
                        this.g = true;
                        this.v = (byte)2;
                        this.br();
                        return true;
                    }
                }
            }
            this.g = true;
        }
        return false;
    }

    public final void h() {
        this.p.a("/data/ui/transmit.ui", 257, this);
        ((al)this.p.a.a((int)0)).a.a = A.length;
        ((al)this.p.a.a((int)0)).a.a(1);
        this.aT();
    }

    private void aT() {
        this.w = ((al)this.p.a.a((int)0)).a.e;
        this.h = ((al)this.p.a.a((int)0)).a.f;
        for (int i2 = 0; i2 < 5; ++i2) {
            this.p.a.a((int)(i2 + 5)).h().a = A[i2 + this.w];
        }
        this.p.a.a(13).b(109 + this.h * 88 / A.length, this.p.a.a());
    }

    public final void i() {
        if (this.o.k(4100)) {
            this.p.a.b(0);
            this.aT();
            return;
        }
        if (this.o.k(8448)) {
            this.p.a.b(1);
            this.aT();
            return;
        }
        if (this.o.k(196640)) {
            game.k.a().f = B[this.h * 5];
            game.k.a().g = B[this.h * 5 + 1];
            game.k.a().h = B[this.h * 5 + 2];
            game.k.a().i = B[this.h * 5 + 3];
            game.k.w = (byte)B[this.h * 5 + 4];
            game.k.a().j = -1;
            game.i.a().a((byte)9);
            return;
        }
        if (this.o.k(262144)) {
            this.o.a((byte)8);
            this.p.a("/data/ui/transmit.ui");
        }
    }

    public final boolean j() {
        return this.p.b("/data/ui/openbox.ui") || this.p.b("/data/ui/taskTip.ui");
    }

    public final void k() {
        String[] stringArray = new String[]{"T\u00f9y th\u00e2n c\u1eeda h\u00e0ng", "S\u1ee7ng v\u1eadt", "L\u01b0ng bao", "\u0110\u1ed3 gi\u00e1m", "Nhi\u1ec7m v\u1ee5", "L\u01b0u d\u1eef li\u1ec7u"};
        this.aS();
        this.p.a("/data/ui/gamemenu.ui", 257, this);
        if (an.X) {
            ((al)this.p.a.a((int)0)).a.a = 6;
            this.p.a.a((int)14).h().a = an.f(605 + this.b);
            this.p.a.a((int)15).h().a = stringArray[0];
            for (int i2 = 0; i2 < 5; ++i2) {
                this.p.a.a((int)(i2 + 5)).h().a = stringArray[i2 + 1];
            }
        } else {
            this.p.a.a((int)14).h().a = an.f(606 + this.b);
            ((al)this.p.a.a((int)0)).a.a = 5;
            this.p.a.a((int)15).h().a = stringArray[1];
            for (int i3 = 0; i3 < 4; ++i3) {
                this.p.a.a((int)(i3 + 5)).h().a = stringArray[i3 + 2];
            }
            this.p.a.a(9).a(false);
        }
        ((al)this.p.a.a((int)0)).a.f = this.b;
        this.p.a.a((int)18).h().a = "" + this.q.G();
        this.p.a.a((int)19).h().a = "" + this.q.E();
        this.f = 0;
    }

    public final void l() {
        this.o.l();
        if (!an.b(this.b, 0) && !this.j() && this.o.k(4100)) {
            this.p.a.b(0);
        } else if (!an.b(this.b, 0) && !this.j() && this.o.k(8448)) {
            this.p.a.b(1);
        } else if (!this.j() && an.I() && this.o.k(196640)) {
            if (an.H() && !an.b(this.b, 0)) {
                return;
            }
            if (an.X) {
                switch (this.b) {
                    case 0: {
                        this.o.a((byte)14);
                        this.p.a("/data/ui/gamemenu.ui");
                        break;
                    }
                    case 1: {
                        this.c = 0;
                        this.o.m();
                        this.o.a((byte)7);
                        this.p.a("/data/ui/gamemenu.ui");
                        break;
                    }
                    case 2: {
                        this.o.m();
                        this.o.a((byte)8);
                        this.p.a("/data/ui/gamemenu.ui");
                        break;
                    }
                    case 3: {
                        this.c = 0;
                        this.o.a((byte)9);
                        this.p.a("/data/ui/gamemenu.ui");
                        break;
                    }
                    case 4: {
                        this.b = 0;
                        this.o.a((byte)10);
                        this.p.a("/data/ui/gamemenu.ui");
                        break;
                    }
                    case 5: {
                        this.p.a.a(11).a(false);
                        this.p.a.a(12).a(false);
                        this.o.a((byte)22);
                    }
                }
            } else {
                switch (this.b) {
                    case 0: {
                        this.c = 0;
                        this.o.m();
                        this.o.a((byte)7);
                        this.p.a("/data/ui/gamemenu.ui");
                        break;
                    }
                    case 1: {
                        this.o.m();
                        this.o.a((byte)8);
                        this.p.a("/data/ui/gamemenu.ui");
                        break;
                    }
                    case 2: {
                        this.c = 0;
                        this.o.a((byte)9);
                        this.p.a("/data/ui/gamemenu.ui");
                        break;
                    }
                    case 3: {
                        this.b = 0;
                        this.o.a((byte)10);
                        this.p.a("/data/ui/gamemenu.ui");
                        break;
                    }
                    case 4: {
                        this.p.a.a(11).a(false);
                        this.p.a.a(12).a(false);
                        this.o.a((byte)22);
                    }
                }
            }
        } else if (an.J() && this.o.k(262144)) {
            this.p.a("/data/ui/gamemenu.ui");
            this.o.a((byte)0);
        }
        this.g();
    }

    public final void m() {
        this.aS();
        this.p.a("/data/ui/gamesystem.ui", 257, this);
        ((al)this.p.a.a((int)0)).a.f = this.b;
        this.f = 0;
    }

    public final void n() {
        if (this.o.k(4100)) {
            this.p.a.b(0);
            return;
        }
        if (this.o.k(8448)) {
            this.p.a.b(1);
            return;
        }
        if (this.o.k(196640)) {
            switch (this.b) {
                case 0: {
                    this.p.a("/data/ui/gamesystem.ui");
                    this.o.a((byte)0);
                    return;
                }
                case 1: {
                    this.o.a((byte)20);
                    this.p.a("/data/ui/gamesystem.ui");
                    return;
                }
                case 2: {
                    this.o.a((byte)21);
                    this.p.a("/data/ui/gamesystem.ui");
                    return;
                }
                case 3: {
                    if (this.f == 0) {
                        this.p.a("/data/ui/option.ui", 257, this);
                        ((al)this.p.a.a((int)0)).a.f = this.c = 1;
                        this.p.a.a((int)12).h().a = "";
                        this.p.a.a((int)13).h().a = "Kh\u00f4ng";
                        this.f = 1;
                        return;
                    }
                    switch (this.c) {
                        case 1: {
                            this.p.a("/data/ui/option.ui");
                            this.f = 0;
                            this.g = true;
                            return;
                        }
                        case 0: {
                            game.i.a().b = 0L;
                            game.i.a().a = 0L;
                            game.g.o().y = false;
                            game.i.a().a((byte)7);
                            this.p.a("/data/ui/gamesystem.ui");
                        }
                    }
                }
            }
            return;
        }
        if (this.o.k(262144)) {
            if (this.f == 0) {
                this.p.a("/data/ui/gamesystem.ui");
                this.o.a((byte)0);
                return;
            }
            if (this.f == 1) {
                this.g = true;
                this.p.a("/data/ui/option.ui");
                this.f = 0;
            }
        }
    }

    public final void o() {
        this.p.a("/data/ui/help1.ui", 257, this);
        this.b = 0;
        this.p.a.a(6).a(true);
        this.p.a.a(7).a(false);
        this.d(this.b);
    }

    private void d(int n2) {
        if (n2 == 0) {
            this.p.a.a((int)5).h().a = "Tr\u1ee3 gi\u00fap";
            this.p.a.a((int)8).h().a = "Nh\u1ea5n n\u00fat 2, 4, 6, 8 \u0111\u1ec3 di chuy\u1ec3n#nN\u00fat 5: c\u00f4ng k\u00edch, \u0111\u1ed1i tho\u1ea1i, x\u00e1c nh\u1eadn#nN\u00fat 1, 3: Xem nhi\u1ec7m v\u1ee5#nN\u00fat 9: l\u1ef1a ch\u1ecdn s\u1ee7ng v\u1eadt c\u01b0\u1ee1i#nN\u00fat 0: Xem b\u1ea3n \u0111\u1ed3#nN\u00fat m\u1ec1m tr\u00e1i: menu h\u1ec7 th\u1ed1ng#nN\u00fat m\u1ec1m ph\u1ea3i: menu tr\u00f2 ch\u01a1i";
            for (int i2 = 0; i2 < 28; ++i2) {
                this.p.a.a(i2 + 9).a(false);
            }
        } else if (n2 > 0) {
            this.p.a.a((int)8).h().a = "";
            for (int i3 = 0; i3 < 14; ++i3) {
                this.p.a.a(9 + (i3 << 1)).a(true);
                this.p.a.a(9 + (i3 << 1) + 1).a(true);
                if ((n2 - 1) * 14 + i3 < 26) {
                    this.p.a.a((int)(9 + (i3 << 1))).h().m = new m();
                    this.p.a.a((int)(9 + (i3 << 1))).h().m.a(0);
                    this.p.a.a((int)(9 + (i3 << 1))).h().m.a = (byte)2;
                    this.p.a.a((int)(9 + (i3 << 1))).h().m.a(325, false, (byte)-2);
                    this.p.a.a((int)(9 + (i3 << 1))).h().m.a((n2 - 1) * 14 + i3 + 1);
                    if ((n2 - 1) * 14 + i3 <= 10) {
                        this.p.a.a((int)(9 + (i3 << 1) + 1)).h().a = an.f(i3 + 311);
                        continue;
                    }
                    this.p.a.a((int)(9 + (i3 << 1) + 1)).h().a = an.f(333 + ((n2 - 1) * 14 + i3 - 11));
                    continue;
                }
                this.p.a.a(9 + (i3 << 1)).a(false);
                this.p.a.a(9 + (i3 << 1) + 1).a(false);
            }
        }
        this.p.a.a((int)39).h().a = n2 + 1 + "/3";
    }

    public final void p() {
        if (this.o.k(16400)) {
            --this.b;
            if (this.b <= 0) {
                this.b = 0;
            }
            this.d(this.b);
            return;
        }
        if (this.o.k(32832)) {
            ++this.b;
            if (this.b >= 2) {
                this.b = 2;
            }
            this.d(this.b);
            return;
        }
        if (this.o.k(262144)) {
            this.o.a((byte)0);
            this.p.a("/data/ui/help1.ui");
        }
    }

    public final void q() {
        this.p.a("/data/ui/help.ui", 257, this);
        this.p.a.a((int)5).h().a = "Quan t\u1ea1i";
        this.p.a.a((int)8).h().a = "T\u00ean tr\u00f2 ch\u01a1i: S\u1ee7ng v\u1eadt V\u01b0\u01a1ng qu\u1ed1c - Li\u1ec7t h\u1ecfa#nVi\u1ec7t h\u00f3a: BIGAME";
        this.p.a.a(6).a(true);
        this.p.a.a(7).a(false);
        for (int i2 = 9; i2 < 13; ++i2) {
            this.p.a.a(i2).a(false);
        }
    }

    public final void r() {
        if (this.o.k(262144)) {
            this.o.a((byte)0);
            this.p.a("/data/ui/help.ui");
        }
    }

    public final void s() {
        this.p.a("/data/ui/help.ui", 257, this);
        this.p.a.a((int)5).h().a = "T\u00f9y ch\u1ecdn";
        this.p.a.a((int)8).h().a = "";
        this.p.a.a(6).a(false);
        this.p.a.a(7).a(true);
        for (int i2 = 9; i2 < 13; ++i2) {
            this.p.a.a(i2).a(true);
        }
        this.aU();
    }

    private void aU() {
        for (int i2 = 1; i2 < 4; ++i2) {
            this.p.a.a((int)(i2 + 9)).h().j = i2 <= game.i.a().g ? -2148 : -8540732;
        }
    }

    public final void t() {
        if (this.o.k(16400)) {
            game.i.a().i();
            this.aU();
            return;
        }
        if (this.o.k(32832)) {
            game.i.a().h();
            this.aU();
            return;
        }
        if (this.o.k(131072)) {
            this.o.a((byte)0);
            this.p.a("/data/ui/help.ui");
        }
    }

    public final void u() {
        this.p.a("/data/ui/help1.ui", 257, this);
        this.p.a("/data/ui/gamesystem.ui");
        this.r = 0;
        this.p.a.a(6).a(true);
        this.p.a.a(7).a(false);
        this.d(this.r);
    }

    public final void v() {
        if (this.o.k(16400)) {
            --this.r;
            if (this.r <= 0) {
                this.r = 0;
            }
            this.d(this.r);
            return;
        }
        if (this.o.k(32832)) {
            ++this.r;
            if (this.r >= 2) {
                this.r = 2;
            }
            this.d(this.r);
            return;
        }
        if (this.o.k(262144)) {
            this.o.a((byte)13);
            this.p.a("/data/ui/help1.ui");
        }
    }

    public final void w() {
        this.s();
        this.p.a("/data/ui/gamesystem.ui");
    }

    public final void x() {
        if (this.o.k(16400)) {
            this.p.a.b(2);
            game.i.a().i();
            this.aU();
            return;
        }
        if (this.o.k(32832)) {
            this.p.a.b(3);
            game.i.a().h();
            this.aU();
            return;
        }
        if (this.o.k(131072)) {
            this.o.a((byte)13);
            this.p.a("/data/ui/help.ui");
        }
    }

    public final void y() {
        this.p.a("/data/ui/petstate.ui", 257, this);
        this.f = 0;
        if (this.q.O.size() > 6) {
            ((al)this.p.a.a((int)0)).a.a(1);
        } else {
            ((al)this.p.a.a((int)0)).a.a(-1);
        }
        this.p.a.a((int)2).h().a = "Ng\u00e2n h\u00e0ng S\u1ee7ng v\u1eadt";
        this.p.a.a(75).a(false);
        this.p.a.a(76).a(false);
        this.aV();
    }

    private void aV() {
        ((al)this.p.a.a((int)0)).a.a = this.q.O.size();
        this.w = ((al)this.p.a.a((int)0)).a.e;
        this.h = ((al)this.p.a.a((int)0)).a.f;
        ((al)this.p.a.a((int)0)).a.d = this.q.O.size() >= 6 ? 6 : this.q.O.size();
        if (this.h >= this.q.O.size()) {
            ((al)this.p.a.a((int)0)).a.f = this.h = this.q.O.size() - 1;
        }
        if (this.w > 0 && this.h - this.w < 5) {
            --this.w;
            ((al)this.p.a.a((int)0)).a.e = this.w;
        }
        for (int i2 = 0; i2 < 6; ++i2) {
            if (this.w + i2 < this.q.O.size()) {
                int[] nArray = (int[])this.q.O.elementAt(this.w + i2);
                if (i2 == 0) {
                    this.p.a.a((int)(14 + i2 * 6)).h().a = "" + (this.w + i2 + 1);
                } else {
                    this.p.a.a((int)(15 + i2 * 6)).h().a = "" + (this.w + i2 + 1);
                }
                this.p.a.a((int)(16 + i2 * 6)).h().a = "#P" + nArray[6] * 100 / game.b.a(nArray[0], nArray[1], nArray[4], 1);
                this.p.a.a((int)(17 + i2 * 6)).h().a = "#P" + game.b.a((short)nArray[7], (short)nArray[1]);
                continue;
            }
            this.p.a.a((int)(16 + i2 * 6)).h().a = "#P0";
            this.p.a.a((int)(17 + i2 * 6)).h().a = "#P0";
        }
        int[] nArray = null;
        if (this.q.O.size() > 0) {
            nArray = (int[])this.q.O.elementAt(this.h);
        }
        if (nArray != null) {
            if (this.p.a.a((int)48).h().m != null) {
                this.p.a.a((int)48).h().m.d();
            } else {
                this.p.a.a((int)48).h().m = new m();
                this.p.a.a((int)48).h().m.a(0);
                this.p.a.a((int)48).h().m.a = (byte)3;
            }
            this.p.a.a((int)48).h().m.a(aq.a((byte)0, (short)nArray[0], (byte)17), false, (byte)-1);
            this.p.a.a((int)51).h().a = an.f(aq.a((byte)0, (short)nArray[0], (byte)0));
            this.p.a.a((int)52).h().a = an.f(365 + aq.a((byte)0, (short)nArray[0], (byte)1));
            if (aq.a((byte)0, (short)nArray[0], (byte)19) == -1) {
                this.p.a.a((int)62).h().a = "";
            } else if (aq.c[0][aq.a((byte)0, (short)nArray[0], (byte)19)][2] == 1 || aq.c[0][aq.a((byte)0, (short)nArray[0], (byte)19)][2] == 2) {
                this.p.a.a((int)62).h().a = "C\u00f3 th\u1ec3 ti\u1ebfn h\u00f3a";
            } else if (aq.c[0][aq.a((byte)0, (short)nArray[0], (byte)19)][2] == 3) {
                this.p.a.a((int)62).h().a = "C\u00f3 th\u1ec3 d\u1ecb ho\u00e1";
            }
            this.p.a.a((int)61).h().a = game.b.y(nArray[0]);
            if (this.p.a.a((int)59).h().m == null) {
                this.p.a.a((int)59).h().m = new m();
                this.p.a.a((int)59).h().m.a(0);
                this.p.a.a((int)59).h().m.a = (byte)2;
                this.p.a.a((int)59).h().m.a(258, false, (byte)-1);
            }
            if (nArray[2] != -1) {
                this.p.a.a((int)59).h().m.a(aq.c[3][nArray[2]][1]);
                this.p.a.a((int)60).h().a = an.f(aq.c[3][nArray[2]][0]);
            } else {
                this.p.a.a((int)59).h().m.a(0);
                this.p.a.a((int)60).h().a = "";
            }
            this.p.a.a((int)65).h().a = "" + nArray[1];
            this.p.a.a((int)66).h().a = "" + game.b.a(nArray[0], nArray[1], nArray[4], 2);
            this.p.a.a((int)67).h().a = "" + game.b.a(nArray[0], nArray[1], nArray[4], 3);
            this.p.a.a((int)68).h().a = "" + game.b.a(nArray[0], nArray[1], nArray[4], 4);
            int n2 = nArray[4];
            int n3 = aq.a((byte)0, (short)nArray[0], (byte)4) - 1;
            for (int i3 = 0; i3 < 5; ++i3) {
                this.p.a.a(74 - i3).a(true);
                this.p.a.a((int)(74 - i3)).h().m.a = (byte)3;
                if (i3 > n3) {
                    this.p.a.a(74 - i3).a(false);
                    continue;
                }
                if (n2 > 0) {
                    this.p.a.a((int)(74 - i3)).h().m.a((byte)14, (byte)-1);
                    --n2;
                    continue;
                }
                this.p.a.a((int)(74 - i3)).h().m.a((byte)16, (byte)-1);
            }
            if (this.b == 1) {
                this.p.a.a((int)64).h().a = "L\u1ea5y ra";
                return;
            }
            if (this.b == 2) {
                this.p.a.a((int)64).h().a = "Ph\u00f3ng sinh";
            }
        }
    }

    public final void z() {
        if (this.f == 0 && this.o.k(4100)) {
            this.p.a.b(0);
            this.aV();
        } else if (this.f == 0 && this.o.k(8448)) {
            this.p.a.b(1);
            this.aV();
        }
        if (this.f == 0) {
            if (this.o.k(196640)) {
                if (this.b == 1) {
                    if (this.q.A >= 6) {
                        this.p.a("/data/ui/msgwarm.ui", 257, this);
                        this.a("Ba l\u00f4 S\u1ee7ng v\u1eadt \u0111\u00e3 \u0111\u1ee7", "Nh\u1ea5n n\u00fat 5 \u0111\u1ec3 ti\u1ebfp t\u1ee5c");
                        this.f = 1;
                    } else {
                        if (this.q.O.size() <= 0) {
                            return;
                        }
                        this.q.r(this.h);
                        if (this.q.O.size() <= 0) {
                            this.o.a((byte)16);
                            this.p.a("/data/ui/petstate.ui");
                        } else {
                            this.aV();
                        }
                    }
                } else if (this.b == 2) {
                    if (this.q.O.size() <= 0) {
                        return;
                    }
                    int[] nArray = (int[])this.q.O.elementAt(this.h);
                    if (aq.a((byte)0, (short)nArray[0], (byte)22) == 2) {
                        this.f = 2;
                        this.E();
                        this.a("Th\u1ea7n th\u00fa kh\u00f4ng th\u1ec3 ph\u00f3ng sinh", "Nh\u1ea5n n\u00fat 5 \u0111\u1ec3 ti\u1ebfp t\u1ee5c");
                    } else {
                        this.f = 1;
                        this.p.a("/data/ui/msgconfirm.ui", 257, this);
                        this.b("B\u1ea1n mu\u1ed1n ph\u00f3ng sinh s\u1ee7ng v\u1eadt n\u00e0y?", "X\u00e1c nh\u1eadn");
                    }
                }
            } else if (this.o.k(786432)) {
                this.o.a((byte)16);
                this.p.a("/data/ui/petstate.ui");
            }
        } else if (this.f > 0) {
            if (this.o.k(196640) && this.b == 1 || this.o.k(131072) && this.b == 2) {
                if (this.b == 1) {
                    this.p.a("/data/ui/msgwarm.ui");
                    this.f = 0;
                } else if (this.b == 2) {
                    if (this.f == 1) {
                        this.p.a("/data/ui/msgconfirm.ui");
                        int[] nArray = (int[])this.q.O.elementAt(this.h);
                        this.q.l(nArray[2]);
                        this.q.q(this.h);
                        this.aV();
                    } else if (this.f == 2) {
                        this.F();
                    }
                    this.f = 0;
                }
            } else if (this.o.k(786432)) {
                if (this.b == 1) {
                    return;
                }
                this.p.a("/data/ui/msgconfirm.ui");
                this.f = 0;
            }
        }
        this.g = true;
    }

    public final void A() {
        this.aS();
        this.p.a("/data/ui/shop.ui", 257, this);
        this.b = 0;
        this.p.a.a((int)5).h().a = "Ng\u00e2n h\u00e0ng S\u1ee7ng v\u1eadt";
        this.p.a.a((int)6).h().a = "G\u1edfi l\u1ea1i";
        this.p.a.a((int)7).h().a = "L\u1ea5y ra";
        this.p.a.a((int)9).h().a = "Ph\u00f3ng sinh";
    }

    public final void B() {
        if (this.o.k(4100)) {
            this.p.a.b(0);
            return;
        }
        if (this.o.k(8448)) {
            this.p.a.b(1);
            return;
        }
        if (this.o.k(196640)) {
            switch (this.b) {
                case 0: {
                    this.c = 0;
                    this.o.a((byte)7);
                    this.p.a("/data/ui/shop.ui");
                    return;
                }
                case 1: {
                    this.o.a((byte)15);
                    this.p.a("/data/ui/shop.ui");
                    return;
                }
                case 2: {
                    this.o.a((byte)15);
                    this.p.a("/data/ui/shop.ui");
                    return;
                }
                case 3: {
                    game.c.e = true;
                    this.p.a("/data/ui/shop.ui");
                    this.o.a((byte)0);
                }
            }
            return;
        }
        if (this.o.k(262144)) {
            game.c.e = true;
            this.p.a("/data/ui/shop.ui");
            this.o.a((byte)0);
        }
    }

    public final void C() {
        this.aS();
        this.p.a("/data/ui/shop.ui", 257, this);
        this.b = 0;
    }

    public final void D() {
        this.o.l();
        if (!an.b(this.b, 0) && !this.j() && this.f == 0 && this.o.k(4100) && this.aW()) {
            this.p.a.b(0);
        } else if (!an.b(this.b, 0) && !this.j() && this.f == 0 && this.o.k(8448) && this.aW()) {
            this.p.a.b(1);
        } else if (this.aW() && !this.j() && an.I() && this.o.k(196640)) {
            if (an.H() && !an.b(this.b, 0)) {
                return;
            }
            switch (this.b) {
                case 0: {
                    this.o.m();
                    this.o.a((byte)2);
                    this.p.a("/data/ui/shop.ui");
                    break;
                }
                case 1: {
                    this.o.a((byte)3);
                    this.p.a("/data/ui/shop.ui");
                    break;
                }
                case 2: {
                    if (this.f == 0) {
                        if (game.g.o().A() == -1) {
                            this.f = 6;
                            this.E();
                            this.a("To\u00e0n b\u1ed9 tr\u1ea1ng th\u00e1i \u0111\u00e3 \u0111\u1ea7y, kh\u00f4ng c\u1ea7n kh\u00f4i ph\u1ee5c", "Nh\u1ea5n n\u00fat 5 \u0111\u1ec3 ti\u1ebfp t\u1ee5c");
                            break;
                        }
                        if (!an.X) {
                            this.f = 3;
                            for (int i2 = 0; i2 < this.q.A; ++i2) {
                                this.q.z[i2].I();
                            }
                            this.b("Ba l\u00f4 s\u1ee7ng v\u1eadt tr\u1ea1ng th\u00e1i to\u00e0n b\u1ed9 kh\u00f4i ph\u1ee5c");
                            break;
                        }
                        h h2 = this;
                        h2.p.a("/data/ui/msgRecover.ui", 257, h2);
                        h2.p.a.a((int)4).h().a = "C\u00f3 kh\u00f4i ph\u1ee5c tr\u1ea1ng th\u00e1i ba l\u00f4 s\u1ee7ng v\u1eadt kh\u00f4ng?";
                        h2.p.a.a((int)5).h().a = "C\u1ea7n ti\u1ec1n t\u00e0i: ";
                        h2.p.a.a((int)6).h().a = "" + game.g.o().A();
                        h2.p.a.a((int)8).h().a = "" + game.g.o().E();
                        this.p.a("/data/ui/shop.ui");
                        this.f = 1;
                        break;
                    }
                    if (this.f == 1) {
                        int n2 = game.g.o().A();
                        if (game.g.o().t(n2)) {
                            this.f = 3;
                            game.g.o().s(-n2);
                            for (n2 = 0; n2 < this.q.A; ++n2) {
                                this.q.z[n2].I();
                            }
                            this.b("Ba l\u00f4 s\u1ee7ng v\u1eadt tr\u1ea1ng th\u00e1i to\u00e0n b\u1ed9 kh\u00f4i ph\u1ee5c");
                        } else {
                            this.f = 2;
                            this.E();
                            this.a("Kim ti\u1ec1n ch\u01b0a \u0111\u1ee7", "Nh\u1ea5n n\u00fat 5 \u0111\u1ec3 ti\u1ebfp t\u1ee5c");
                        }
                        this.p.a("/data/ui/msgRecover.ui");
                        break;
                    }
                    if (this.f == 2 && an.X) {
                        this.o.a((byte)102);
                    }
                    this.f = 0;
                    this.F();
                    break;
                }
                case 3: {
                    game.c.e = true;
                    this.p.a("/data/ui/shop.ui");
                    this.o.a((byte)0);
                }
            }
        } else if (!this.j() && this.o.k(262144) && an.J() && this.aW()) {
            if (this.f == 1) {
                this.p.a("/data/ui/shop.ui", 257, this);
                this.p.a("/data/ui/msgRecover.ui");
                this.f = 0;
                this.b = 0;
            } else if (this.f == 0) {
                game.c.e = true;
                this.p.a("/data/ui/shop.ui");
                this.o.a((byte)0);
            }
        }
        if (this.f == 3 && this.ax()) {
            this.f = 4;
            this.p.a("/data/ui/shop.ui", 257, this);
            this.H();
            this.a("\u0110ang l\u01b0u...");
            this.J();
        } else if (this.f == 4 && ((k)this.o).k()) {
            this.a("L\u01b0u th\u00e0nh c\u00f4ng");
            this.f = 5;
        } else if (this.f == 5) {
            this.I();
            this.b = 0;
            this.f = 0;
        }
        this.f();
        this.g = true;
    }

    public final void a(int n2, byte by) {
        this.p.a("/data/ui/shopbuy.ui", 257, this);
        this.b = 0;
        this.f = 0;
        ((al)this.p.a.a((int)0)).a.a = aq.c[n2].length;
        ((al)this.p.a.a((int)0)).a.a(1);
        this.p.a.a((int)5).h().a = "Mua";
        if (this.o instanceof k) {
            this.p.a.a(57).a(true);
            this.p.a.a(58).a(true);
            this.p.a.a((int)57).h().a = "Mua s\u1eafm";
            this.p.a.a((int)58).h().a = "Ph\u1ea3n h\u1ed3i";
            this.p.a.a(39).a(false);
            this.p.a.a(40).a(false);
        } else if (this.o instanceof d) {
            this.p.a.a(57).a(false);
            this.p.a.a(58).a(false);
            this.p.a.a(39).a(true);
            this.p.a.a(40).a(true);
            this.p.a.a((int)39).h().a = "Mua s\u1eafm";
            this.p.a.a((int)40).h().a = "Ph\u1ea3n h\u1ed3i";
        }
        this.b(n2, by);
    }

    private void b(int n2, byte by) {
        this.w = ((al)this.p.a.a((int)0)).a.e;
        this.h = ((al)this.p.a.a((int)0)).a.f;
        for (int i2 = 0; i2 < 5; ++i2) {
            if (this.p.a.a((int)(i2 + 51)).h().m == null) {
                this.p.a.a((int)(i2 + 51)).h().m = new m();
                this.p.a.a((int)(i2 + 51)).h().m.a(0);
                this.p.a.a((int)(i2 + 51)).h().m.a = (byte)2;
                this.p.a.a((int)(i2 + 51)).h().m.a(258, false, (byte)-1);
            }
            this.p.a.a((int)(i2 + 51)).h().m.a(aq.c[n2][this.w + i2][1]);
            this.p.a.a((int)(14 + i2 * 5)).h().a = an.f(aq.c[n2][this.w + i2][0]);
            if (this.o instanceof k) {
                if (this.j == 1 || this.j == 3) {
                    this.p.a.a((int)(15 + i2 * 5)).h().a = "" + aq.c[n2][this.w + i2][3];
                } else if (this.j == 2) {
                    this.p.a.a((int)(15 + i2 * 5)).h().a = aq.c[n2][this.w + i2][4] == 0 ? "" + aq.c[n2][this.w + i2][3] * 3 / 2 : "" + aq.c[n2][this.w + i2][3];
                }
            } else {
                this.p.a.a((int)(15 + i2 * 5)).h().a = by == 0 && n2 == 4 && this.w + i2 == 0 ? "" + aq.c[n2][this.w + i2][3] : "" + (aq.c[n2][this.w + i2][3] << 1);
            }
            if (aq.c[n2][this.w + i2][4] == 0) {
                this.p.a.a((int)(i2 + 45)).h().m.a(84);
                continue;
            }
            if (aq.c[n2][this.w + i2][4] == 1) {
                this.p.a.a((int)(i2 + 45)).h().m.a(83);
                continue;
            }
            if (aq.c[n2][this.w + i2][4] != 2) continue;
            this.p.a.a((int)(i2 + 45)).h().m.a(74);
        }
        this.p.a.a((int)56).h().a = an.f(aq.c[n2][this.h][2]);
        this.p.a.a((int)43).h().a = "" + this.q.G();
        this.p.a.a((int)44).h().a = "" + this.q.E();
        this.p.a.a(38).b(102 + this.h * 84 / aq.c[n2].length, this.p.a.a());
    }

    public final void a(byte by, byte by2) {
        this.o.l();
        if (!an.b(this.b, 0) && this.f <= 1 && this.o.k(4100) && !this.j()) {
            this.p.a.b(0);
            if (this.f == 0) {
                this.b((int)by, by2);
            }
        } else if (!an.b(this.b, 0) && this.f <= 1 && this.o.k(8448) && !this.j()) {
            this.p.a.b(1);
            if (this.f == 0) {
                this.b((int)by, by2);
            }
        } else if (this.f == 1 && this.o.k(16400) && this.c > 0 && !this.j()) {
            --this.c;
            if (this.c <= 0) {
                this.c = 99 - this.q.a(this.h, by2);
            }
            this.a(this.c, this.c * aq.c[by][this.h][3], (int)aq.c[by][this.h][4], (int)by);
        } else if (this.f == 1 && this.o.k(32832) && !this.j()) {
            ++this.c;
            if (this.c > 99 - this.q.a(this.h, by2)) {
                this.c = 1;
            }
            this.a(this.c, this.c * aq.c[by][this.h][3], (int)aq.c[by][this.h][4], (int)by);
        } else if (an.I() && this.o.k(196640) && !this.j()) {
            if (an.H() && !an.b(this.b, 0)) {
                return;
            }
            if (aq.c[by][this.h][4] == 2) {
                if (this.f == 0) {
                    if (an.X) {
                        if (!this.q.a(this.h, 1, (byte)0)) {
                            this.f = 3;
                            this.E();
                            this.a("\u0110\u1ea1o c\u1ee5 \u0111\u00e3 \u0111\u1ee7", "Nh\u1ea5n n\u00fat 5 \u0111\u1ec3 ti\u1ebfp t\u1ee5c");
                        } else {
                            this.o.a((byte)101);
                        }
                    } else {
                        this.f = 3;
                        this.p.a("/data/ui/msgwarm.ui", 257, this);
                        this.a("C\u00f4ng n\u0103ng c\u00f2n ch\u01b0a m\u1edf kh\u1ea3i", "Nh\u1ea5n n\u00fat 5 \u0111\u1ec3 ti\u1ebfp t\u1ee5c");
                    }
                } else {
                    this.f = 0;
                    this.p.a("/data/ui/msgwarm.ui");
                }
            } else if (by2 == 2 && this.h < 12) {
                this.c = 1;
                if (this.q.a(this.h, this.c, by2)) {
                    if (this.f == 0) {
                        this.r = 0;
                        this.b(by, by2);
                    } else if (this.f > 0) {
                        if (an.X) {
                            if (this.f == 4) {
                                this.o.a((byte)104);
                            } else if (this.f == 3) {
                                this.o.a((byte)102);
                            }
                        }
                        this.f = 0;
                        this.p.a("/data/ui/msgwarm.ui");
                    }
                } else if (this.f == 0) {
                    this.f = 2;
                    this.p.a("/data/ui/msgwarm.ui", 257, this);
                    this.a("\u0110\u1ea1o c\u1ee5 n\u00e0y \u0111\u00e3 \u0111\u1ee7", "Nh\u1ea5n n\u00fat 5 \u0111\u1ec3 ti\u1ebfp t\u1ee5c");
                } else {
                    this.o.m();
                    this.f = 0;
                    this.p.a("/data/ui/msgwarm.ui");
                    this.b((int)by, by2);
                }
            } else if (this.q.a(this.h, this.c, by2)) {
                if (this.f == 0) {
                    this.f = 1;
                    this.p.a("/data/ui/msgyn.ui", 257, this);
                    this.c = 1;
                    this.r = 0;
                    this.a(this.c, this.c * aq.c[by][this.h][3], (int)aq.c[by][this.h][4], (int)by);
                } else if (this.f == 1) {
                    this.b(by, by2);
                } else if (this.f == 2) {
                    game.k.a().M.i();
                    this.f = 0;
                    this.c = 0;
                    this.p.a("/data/ui/msgwarm.ui");
                    this.b((int)by, by2);
                } else {
                    if (an.X) {
                        if (this.f == 4) {
                            this.p.a("/data/ui/msgyn.ui");
                            this.o.a((byte)104);
                        } else if (this.f == 3) {
                            this.p.a("/data/ui/msgyn.ui");
                            this.o.a((byte)102);
                        }
                    }
                    this.f = 0;
                    this.c = 0;
                    this.p.a("/data/ui/msgwarm.ui");
                }
            } else if (this.f == 0) {
                this.f = 2;
                this.p.a("/data/ui/msgwarm.ui", 257, this);
                this.a("\u0110\u1ea1o c\u1ee5 n\u00e0y \u0111\u00e3 \u0111\u1ee7", "Nh\u1ea5n n\u00fat 5 \u0111\u1ec3 ti\u1ebfp t\u1ee5c");
            } else {
                this.f = 0;
                this.p.a("/data/ui/msgwarm.ui");
            }
        } else if (this.o.k(262144) && !this.j() && an.J()) {
            if (this.f == 0) {
                if (this.o instanceof k) {
                    if (this.j == 1) {
                        this.o.a((byte)1);
                    } else if (this.j == 2) {
                        this.o.a((byte)14);
                    } else if (this.j == 3) {
                        this.o.a((byte)27);
                    }
                    this.p.a("/data/ui/shopbuy.ui");
                } else {
                    this.p.a("/data/ui/shopbuy.ui");
                    this.o.a((byte)20);
                }
            } else if (this.f == 1) {
                this.f = 0;
                this.c = 0;
                this.p.a("/data/ui/msgyn.ui");
            }
        }
        this.f();
    }

    private void b(byte by, byte by2) {
        block29: {
            block25: {
                block26: {
                    int n2;
                    block28: {
                        block27: {
                            if (this.o instanceof k && (this.j == 1 || this.j == 3) && this.q.b(this.h, this.c * aq.c[by][this.h][3], (int)by) || this.j == 2 && (aq.c[by][this.h][4] == 0 && this.q.b(this.h, this.c * aq.c[by][this.h][3] * 3 / 2, (int)by) || aq.c[by][this.h][4] != 0 && this.q.b(this.h, this.c * aq.c[by][this.h][3], (int)by))) {
                                if (this.r == 0) {
                                    this.q.c(this.h, this.c, by2);
                                    if (aq.c[by][this.h][4] == 0) {
                                        if (this.j == 1 || this.j == 3) {
                                            this.q.s(-this.c * aq.c[by][this.h][3]);
                                        } else if (this.j == 2) {
                                            this.q.s(-this.c * aq.c[by][this.h][3] * 3 / 2);
                                        }
                                    } else if (this.j == 1 || this.j == 3) {
                                        this.q.u(-this.c * aq.c[by][this.h][3]);
                                    } else if (this.j == 2) {
                                        this.q.u(-this.c * aq.c[by][this.h][3]);
                                    }
                                    this.p.a("/data/ui/msgwarm.ui", 257, this);
                                    if (by == 3 && this.h == 17) {
                                        this.a("\u0110\u00e3 th\u00e0nh c\u00f4ng mua s\u1eafm #2" + an.f(aq.c[by][this.h][0]) + " * " + 5 * this.c, "Nh\u1ea5n n\u00fat 5 \u0111\u1ec3 ti\u1ebfp t\u1ee5c");
                                    } else {
                                        this.a("\u0110\u00e3 th\u00e0nh c\u00f4ng mua s\u1eafm #2" + an.f(aq.c[by][this.h][0]) + " * " + this.c, "Nh\u1ea5n n\u00fat 5 \u0111\u1ec3 ti\u1ebfp t\u1ee5c");
                                    }
                                    this.f = 2;
                                    this.c = 1;
                                } else {
                                    this.f = 0;
                                }
                                this.p.a("/data/ui/msgyn.ui");
                                return;
                            }
                            if (this.o instanceof d && this.q.b(this.h, this.c * aq.c[by][this.h][3] << 1, (int)by)) {
                                if (this.r == 0) {
                                    this.q.c(this.h, this.c, by2);
                                    if (aq.c[by][this.h][4] == 0) {
                                        this.q.s(-this.c * aq.c[by][this.h][3] << 1);
                                    } else {
                                        this.q.u(-this.c * aq.c[by][this.h][3] << 1);
                                    }
                                    this.p.a("/data/ui/msgwarm.ui", 257, this);
                                    if (by == 3 && this.h == 17) {
                                        this.a("\u0110\u00e3 th\u00e0nh c\u00f4ng mua s\u1eafm #2" + an.f(aq.c[by][this.h][0]) + " * " + 5 * this.c, "Nh\u1ea5n n\u00fat 5 \u0111\u1ec3 ti\u1ebfp t\u1ee5c");
                                    } else {
                                        this.a("\u0110\u00e3 th\u00e0nh c\u00f4ng mua s\u1eafm #2" + an.f(aq.c[by][this.h][0]) + " * " + this.c, "Nh\u1ea5n n\u00fat 5 \u0111\u1ec3 ti\u1ebfp t\u1ee5c");
                                    }
                                    this.f = 2;
                                    this.c = 1;
                                } else {
                                    this.f = 0;
                                }
                                this.p.a("/data/ui/msgyn.ui");
                                return;
                            }
                            if (this.r != 0) break block25;
                            this.p.a("/data/ui/msgwarm.ui", 257, this);
                            if (aq.c[by][this.h][4] == 0) {
                                this.f = 3;
                                this.a("Kim ti\u1ec1n ch\u01b0a \u0111\u1ee7", "Nh\u1ea5n n\u00fat 5 \u0111\u1ec3 ti\u1ebfp t\u1ee5c");
                            } else {
                                this.f = 4;
                                this.a("S\u1ed1 l\u01b0\u1ee3ng Huy ch\u01b0\u01a1ng ch\u01b0a \u0111\u1ee7", "Nh\u1ea5n n\u00fat 5 \u0111\u1ec3 ti\u1ebfp t\u1ee5c");
                            }
                            if (!(this.o instanceof k)) break block26;
                            if (this.j != 1 && this.j != 3) break block27;
                            n2 = this.c * aq.c[by][this.h][3];
                            break block28;
                        }
                        if (this.j != 2) break block29;
                        n2 = aq.c[by][this.h][4] == 0 ? this.c * aq.c[by][this.h][3] * 3 / 2 : this.c * aq.c[by][this.h][3];
                    }
                    this.b(new int[]{by, by2, this.h, aq.c[by][this.h][4], n2, this.c});
                    break block29;
                }
                if (this.o instanceof d) {
                    int n3 = this.c * aq.c[by][this.h][3] << 1;
                    this.b(new int[]{by, by2, this.h, aq.c[by][this.h][4], n3, this.c});
                }
                break block29;
            }
            this.f = 0;
        }
        this.p.a("/data/ui/msgyn.ui");
    }

    private void b(int[] nArray) {
        if (this.C == null) {
            this.C = new Vector();
        } else {
            this.C.removeAllElements();
        }
        this.C.addElement(nArray);
    }

    private void a(int n2, int n3, int n4, int n5) {
        this.p.a.a((int)9).h().a = n5 == 3 && this.h == 17 ? "" + n2 * 5 : "" + n2;
        if (this.o instanceof k) {
            if (this.j == 1 || this.j == 3) {
                this.p.a.a((int)11).h().a = "" + n3;
            } else if (this.j == 2) {
                this.p.a.a((int)11).h().a = n4 == 0 ? "" + n3 * 3 / 2 : "" + n3;
            }
        } else {
            this.p.a.a((int)11).h().a = "" + (n3 << 1);
        }
        if (n4 == 0) {
            this.p.a.a((int)12).h().m.a(84);
            return;
        }
        if (n4 == 1) {
            this.p.a.a((int)12).h().m.a(83);
        }
    }

    public final void E() {
        this.p.a("/data/ui/msgwarm.ui", 257, this);
    }

    public final void F() {
        this.p.a("/data/ui/msgwarm.ui");
    }

    public final boolean G() {
        return !this.p.b("/data/ui/msgwarm.ui");
    }

    public final void a(String string, String string2) {
        this.p.a.a((int)6).h().a = string2;
        this.p.a.a((int)7).h().a = string;
    }

    public final void H() {
        this.p.a("/data/ui/msgtip.ui", 257, this);
    }

    public final void I() {
        this.p.a("/data/ui/msgtip.ui");
    }

    private boolean aW() {
        return !this.p.b("/data/ui/msgtip.ui");
    }

    public final void a(String string) {
        this.p.a.a((int)2).h().a = string;
    }

    public final void J() {
        this.p.a.a(3).a(false);
        this.p.a.a(4).a(false);
    }

    public final void K() {
        if (this.f == 0) {
            if (this.o.k(196640)) {
                this.f = 1;
                this.a("\u0110ang l\u01b0u...");
                this.J();
                return;
            }
            if (this.o.k(262144)) {
                this.b = an.X ? 5 : 4;
                this.o.a((byte)6);
                this.p.a("/data/ui/msgtip.ui");
                this.f = 0;
                return;
            }
        } else if (this.f == 1) {
            if (((k)this.o).k()) {
                this.a("L\u01b0u th\u00e0nh c\u00f4ng");
                this.f = 2;
                return;
            }
        } else if (this.f == 2) {
            this.p.a("/data/ui/msgtip.ui");
            this.p.a("/data/ui/gamemenu.ui");
            this.o.a((byte)0);
            this.f = 0;
        }
    }

    private void b(String string, String string2) {
        this.p.a.a((int)2).h().a = string2;
        this.p.a.a((int)4).h().a = string;
    }

    public final void L() {
        this.p.a("/data/ui/shopbuy.ui", 257, this);
        this.b = 0;
        this.f = 0;
        this.p.a.a((int)5).h().a = "B\u00e1n ra";
        this.p.a.a((int)39).h().a = "";
        this.p.a.a((int)40).h().a = "";
        this.p.a.a((int)57).h().a = "B\u00e1n \u0111i";
        this.p.a.a((int)58).h().a = "Ph\u1ea3n h\u1ed3i";
        this.q.x();
        this.aX();
    }

    private void aX() {
        if (this.q.S.size() > 5) {
            ((al)this.p.a.a((int)0)).a.a(1);
        } else {
            ((al)this.p.a.a((int)0)).a.a(0);
        }
        ((al)this.p.a.a((int)0)).a.a = this.q.S.size();
        this.w = ((al)this.p.a.a((int)0)).a.e;
        this.h = ((al)this.p.a.a((int)0)).a.f;
        if (this.h >= this.q.S.size()) {
            ((al)this.p.a.a((int)0)).a.f = this.h = this.q.S.size() - 1;
        }
        if (this.w > 0 && this.h - this.w < 4) {
            --this.w;
            ((al)this.p.a.a((int)0)).a.e = this.w;
        }
        for (int i2 = 0; i2 < 5; ++i2) {
            if (this.w + i2 < this.q.S.size()) {
                int n2 = ((int[])this.q.S.elementAt(this.w + i2))[0];
                if (this.p.a.a((int)(i2 + 51)).h().m == null) {
                    this.p.a.a((int)(i2 + 51)).h().m = new m();
                    this.p.a.a((int)(i2 + 51)).h().m.a(0);
                    this.p.a.a((int)(i2 + 51)).h().m.a = (byte)2;
                    this.p.a.a((int)(i2 + 51)).h().m.a(258, false, (byte)-1);
                }
                this.p.a.a((int)(i2 + 51)).h().m.a(aq.c[4][n2][1]);
                this.p.a.a((int)(14 + i2 * 5)).h().a = an.f(aq.c[4][n2][0]);
                this.p.a.a((int)(15 + i2 * 5)).h().a = "" + aq.c[4][n2][3] / 2;
                if (aq.c[4][n2][4] == 0) {
                    this.p.a.a((int)(i2 + 45)).h().m.a(84);
                    continue;
                }
                if (aq.c[4][n2][4] == 1) {
                    this.p.a.a((int)(i2 + 45)).h().m.a(83);
                    continue;
                }
                if (aq.c[4][n2][4] != 2) continue;
                this.p.a.a((int)(i2 + 45)).h().m.a(74);
                continue;
            }
            if (this.p.a.a((int)(i2 + 51)).h().m != null) {
                this.p.a.a((int)(i2 + 51)).h().m.d();
            }
            this.p.a.a((int)(14 + i2 * 5)).h().a = "";
            this.p.a.a((int)(15 + i2 * 5)).h().a = "";
            this.p.a.a((int)(i2 + 45)).h().m.a(86);
        }
        this.p.a.a((int)56).h().a = this.q.S.size() > 0 ? an.f(aq.c[4][((int[])this.q.S.elementAt(this.h))[0]][2]) : "";
        if (this.q.S.size() <= 0) {
            return;
        }
        this.p.a.a((int)43).h().a = "" + this.q.G();
        this.p.a.a((int)44).h().a = "" + this.q.E();
        this.p.a.a(38).b(102 + this.h * 84 / this.q.S.size(), this.p.a.a());
    }

    /*
     * Enabled aggressive block sorting
     */
    public final void M() {
        if (this.o.k(4100)) {
            this.p.a.b(0);
            return;
        }
        if (this.o.k(8448)) {
            this.p.a.b(1);
            return;
        }
        if (this.f == 1 && this.o.k(16400) && this.c > 0) {
            int[] nArray = (int[])this.q.S.elementAt(this.h);
            --this.c;
            if (this.c <= 0) {
                this.c = this.q.a(nArray[0], (byte)0);
            }
            this.a(this.c, this.c * aq.c[4][nArray[0]][3] / 2, (int)aq.c[4][nArray[0]][4], 4);
            return;
        }
        if (this.f == 1 && this.o.k(32832)) {
            int[] nArray = (int[])this.q.S.elementAt(this.h);
            ++this.c;
            if (this.c > this.q.a(nArray[0], (byte)0)) {
                this.c = 1;
            }
            this.a(this.c, this.c * aq.c[4][nArray[0]][3] / 2, (int)aq.c[4][nArray[0]][4], 4);
            return;
        }
        if (this.o.k(196640) && this.q.S.size() > 0) {
            int[] nArray = (int[])this.q.S.elementAt(this.h);
            if (this.f == 0) {
                this.f = 1;
                this.p.a("/data/ui/msgyn.ui", 257, this);
                this.c = 1;
                this.r = 0;
                this.a(this.c, this.c * aq.c[4][nArray[0]][3] / 2, (int)aq.c[4][nArray[0]][4], 4);
                return;
            }
            if (this.r != 0) {
                this.p.a("/data/ui/msgyn.ui");
                this.f = 0;
                return;
            }
            this.q.d(nArray[0], this.c, (byte)0);
            this.q.s(this.c * aq.c[4][nArray[0]][3] / 2);
        } else {
            if (!this.o.k(262144)) return;
            if (this.f == 0) {
                this.o.a((byte)1);
                this.p.a("/data/ui/shopbuy.ui");
                ((al)this.p.a.a((int)0)).a.f = this.b = 1;
                return;
            }
        }
        this.f = 0;
        this.p.a("/data/ui/msgyn.ui");
        this.q.x();
        this.aX();
    }

    public final void N() {
        this.p.a("/data/ui/record.ui", 257, this);
        this.p.a("/data/ui/gamemenu.ui");
        this.p.a.a((int)14).h().a = "" + (this.q.A + this.q.O.size());
        this.p.a.a((int)17).h().a = "" + this.q.F;
        this.p.a.a((int)20).h().a = "" + this.q.H;
        this.p.a.a((int)26).h().a = "" + this.q.G;
        int n2 = 0;
        for (byte by = 0; by < this.q.B.length; by = (byte)(by + 1)) {
            if (this.q.c(by, (byte)0) != 2) continue;
            ++n2;
        }
        this.p.a.a((int)29).h().a = "" + n2;
        long l2 = game.i.a().d + game.i.a().e - game.i.a().f;
        k k2 = this.p.a.a(31).h();
        game.k.a();
        k2.a = game.k.a(l2)[1];
        ((al)this.p.a.a((int)0)).b.f = this.c;
        this.b = 0;
        this.f = 0;
        this.g = true;
    }

    public final void O() {
        if (this.o.k(16400)) {
            this.p.a.b(2);
            this.g = true;
        } else if (this.o.k(32832)) {
            this.p.a.b(3);
            this.g = true;
        } else if (this.o.k(196640)) {
            if (this.f == 0) {
                switch (this.c) {
                    case 0: {
                        if (game.g.o().k(5)) {
                            this.o.a((byte)11);
                            break;
                        }
                        this.E();
                        this.a("Kh\u00f4ng \u0111\u1ea1t \u0111\u01b0\u1ee3c s\u1ee7ng v\u1eadt s\u00e1ch tranh \u0111\u1ea1o c\u1ee5", "Nh\u1ea5n n\u00fat 5 \u0111\u1ec3 ti\u1ebfp t\u1ee5c");
                        this.f = 1;
                        break;
                    }
                    case 1: {
                        this.o.a((byte)12);
                    }
                }
            } else {
                this.f = 0;
                this.F();
            }
        } else if (this.o.k(262144) && this.f == 0) {
            this.b = an.X ? 3 : 2;
            this.o.a((byte)6);
            this.p.a("/data/ui/record.ui");
        }
        this.g = true;
    }

    public final void P() {
        this.p.a("/data/ui/petmap.ui", 257, this);
        this.p.a("/data/ui/record.ui");
        this.b = 0;
        this.c = 0;
        this.f = 0;
        ((al)this.p.a.a((int)0)).a.a(1);
        this.aZ();
        this.g = true;
    }

    private void aY() {
        ((al)this.p.a.a((int)0)).a.e = 0;
        ((al)this.p.a.a((int)0)).a.f = 0;
    }

    private void aZ() {
        int n2;
        ((al)this.p.a.a((int)0)).a.a = this.q.X[this.b];
        this.w = ((al)this.p.a.a((int)0)).a.e;
        this.h = ((al)this.p.a.a((int)0)).a.f;
        int n3 = aq.c[0][this.q.W[this.b] + this.h][17];
        if (this.q.a((byte)this.b, this.h + this.q.W[this.b]) > 0) {
            this.p.a.a(21).a(true);
            if (this.p.a.a((int)21).h().m != null) {
                this.p.a.a((int)21).h().m.d();
            } else {
                this.p.a.a((int)21).h().m = new m();
                this.p.a.a((int)21).h().m.a(0);
                this.p.a.a((int)21).h().m.a = (byte)3;
            }
            this.p.a.a((int)21).h().m.a(n3, false, (byte)-1);
            this.p.a.a((int)21).h().m.b();
        } else {
            this.p.a.a(21).a(false);
        }
        n3 = 0;
        for (n2 = 0; n2 < this.q.X[this.b]; ++n2) {
            if (this.q.a((byte)this.b, this.q.W[this.b] + n2) != 2) continue;
            ++n3;
        }
        for (n2 = 0; n2 < 5; ++n2) {
            if (this.p.a.a((int)(n2 + 44)).h().m == null) {
                this.p.a.a((int)(n2 + 44)).h().m = new m();
                this.p.a.a((int)(n2 + 44)).h().m.a(102);
                this.p.a.a((int)(n2 + 44)).h().m.a = (byte)2;
                this.p.a.a((int)(n2 + 44)).h().m.a(257, false, (byte)-1);
            }
            if (this.q.a((byte)this.b, n2 + this.w + this.q.W[this.b]) == 2) {
                this.p.a.a((int)(n2 + 44)).h().m.a(101);
            } else {
                this.p.a.a((int)(n2 + 44)).h().m.a(102);
            }
            this.p.a.a((int)(24 + (n2 << 2) + 3)).h().a = an.f(aq.c[0][this.q.W[this.b] + n2 + this.w][0]);
        }
        this.p.a.a((int)20).h().a = an.f(365 + this.b) + n3 + "/" + this.q.X[this.b];
        this.p.a.a(23).b(99 + (this.h << 6) / this.q.X[this.b], this.p.a.a());
    }

    public final void Q() {
        if (this.o.k(4100)) {
            this.p.a.b(0);
            this.aZ();
        } else if (this.o.k(8448)) {
            this.p.a.b(1);
            this.aZ();
        } else if (this.o.k(16400)) {
            this.p.a.b(2);
            this.aY();
            this.aZ();
        } else if (this.o.k(32832)) {
            this.p.a.b(3);
            this.aY();
            this.aZ();
        } else if (!this.o.k(196640) && this.o.k(786432)) {
            if (this.o.Q == 8) {
                this.o.a((byte)8);
            } else {
                this.c = 0;
                this.o.a((byte)9);
            }
            this.p.a("/data/ui/petmap.ui");
        }
        this.g = true;
    }

    public final void R() {
        this.p.a("/data/ui/task.ui", 257, this);
        this.p.a("/data/ui/gamemenu.ui");
        ((al)this.p.a.a((int)0)).b.f = this.b;
        this.c = 0;
        this.r = 0;
        this.ba();
        this.bb();
    }

    private void ba() {
        switch (this.b) {
            case 0: {
                if (game.c.t >= game.c.r.length / 2 - 1) {
                    ((al)this.p.a.a((int)0)).a.a = game.c.r.length / 2;
                    ((al)this.p.a.a((int)0)).a.f = game.c.r.length / 2 - 1;
                } else {
                    ((al)this.p.a.a((int)0)).a.a = game.c.t + 1;
                    ((al)this.p.a.a((int)0)).a.f = game.c.t;
                }
                this.p.a.a((int)36).h().a = "";
                this.h = game.c.t;
                this.w = game.c.t - 4;
                if (this.h <= 0) {
                    this.h = 0;
                }
                if (this.w <= 0) {
                    this.w = 0;
                }
                ((al)this.p.a.a((int)0)).a.e = this.w;
                this.p.a.a((int)37).h().a = "\u0110\u1ea7u m\u1ed1i ch\u00ednh ho\u00e0n th\u00e0nh \u0111\u1ed9: ";
                this.p.a.a((int)38).h().a = game.c.t >= game.c.r.length / 2 ? game.c.r[game.c.r.length - 1] : game.c.r[game.c.r.length / 2 + game.c.t];
                int n2 = game.c.t * 1000 / (game.c.r.length / 2);
                if (!an.X) {
                    int n3 = n2 % 10;
                    if (n3 == 0) {
                        n3 = 1;
                    }
                    this.p.a.a((int)38).h().a = n2 / 50 + "." + n3 + "%";
                } else {
                    this.p.a.a((int)38).h().a = n2 / 10 + "." + n2 % 10 + "%";
                }
                if (game.c.t > 4) {
                    ((al)this.p.a.a((int)0)).a.a(1);
                } else {
                    ((al)this.p.a.a((int)0)).a.a(0);
                }
                this.p.a.a((int)8).h().g = 11290624;
                break;
            }
            case 1: {
                int n4;
                ((al)this.p.a.a((int)0)).a.a = game.c.u;
                ((al)this.p.a.a((int)0)).a.f = 0;
                ((al)this.p.a.a((int)0)).a.e = 0;
                this.p.a.a((int)36).h().a = "";
                this.p.a.a((int)37).h().a = "Chi nh\u00e1nh ho\u00e0n th\u00e0nh \u0111\u1ed9: ";
                int n5 = 0;
                for (n4 = 0; n4 < game.c.s.length; ++n4) {
                    if (game.c.s[n4][1] != 3) continue;
                    ++n5;
                }
                System.out.println(" Nhi\u1ec7m v\u1ee5 ph\u1ee5 " + game.c.q.length);
                n4 = n5 * 1000 / (game.c.q.length / 2);
                this.p.a.a((int)38).h().a = n4 / 10 + "." + n4 % 10 + "%";
                if (game.c.u > 5) {
                    ((al)this.p.a.a((int)0)).a.a(1);
                } else {
                    ((al)this.p.a.a((int)0)).a.a(0);
                }
                this.p.a.a((int)9).h().g = 11290624;
            }
        }
        this.bb();
    }

    private void bb() {
        this.w = ((al)this.p.a.a((int)0)).a.e;
        this.h = ((al)this.p.a.a((int)0)).a.f;
        for (int i2 = 0; i2 < 5; ++i2) {
            if (this.b == 0) {
                if (game.c.t > 0) {
                    if (this.w + i2 < game.c.t) {
                        this.p.a.a((int)(10 + i2 * 5 + 2)).h().a = "" + (i2 + this.w + 1);
                        this.p.a.a((int)(10 + i2 * 5 + 3)).h().a = game.c.r[this.w + i2];
                        this.p.a.a((int)(10 + i2 * 5 + 4)).h().a = "Ho\u00e0n th\u00e0nh";
                        continue;
                    }
                    if (this.w + i2 == game.c.t && this.w + i2 <= game.c.r.length / 2 - 1) {
                        this.p.a.a((int)(10 + i2 * 5 + 2)).h().a = "" + (i2 + this.w + 1);
                        this.p.a.a((int)(10 + i2 * 5 + 3)).h().a = game.c.r[this.w + i2];
                        this.p.a.a((int)(10 + i2 * 5 + 4)).h().a = "";
                        continue;
                    }
                    this.p.a.a((int)(10 + i2 * 5 + 2)).h().a = "";
                    this.p.a.a((int)(10 + i2 * 5 + 3)).h().a = "";
                    this.p.a.a((int)(10 + i2 * 5 + 4)).h().a = "";
                    this.p.a.a((int)36).h().a = "";
                    continue;
                }
                this.p.a.a((int)12).h().a = "1";
                this.p.a.a((int)13).h().a = game.c.r[0];
                this.p.a.a((int)14).h().a = "";
                continue;
            }
            if (this.b != 1) continue;
            if (this.w + i2 < game.c.u) {
                this.p.a.a((int)(10 + i2 * 5 + 2)).h().a = "" + (i2 + this.w + 1);
                this.p.a.a((int)(10 + i2 * 5 + 3)).h().a = game.c.q[game.c.s[this.w + i2][0]];
                if (game.c.s[this.w + i2][1] == 3) {
                    this.p.a.a((int)(10 + i2 * 5 + 4)).h().a = "Ho\u00e0n th\u00e0nh";
                    continue;
                }
                this.p.a.a((int)(10 + i2 * 5 + 4)).h().a = "";
                continue;
            }
            this.p.a.a((int)(10 + i2 * 5 + 2)).h().a = "";
            this.p.a.a((int)(10 + i2 * 5 + 3)).h().a = "";
            this.p.a.a((int)(10 + i2 * 5 + 4)).h().a = "";
        }
        switch (this.b) {
            case 0: {
                this.p.a.a((int)36).h().a = game.c.r[game.c.r.length / 2 + this.h];
                break;
            }
            case 1: {
                if (game.c.u <= 0) break;
                this.p.a.a((int)36).h().a = game.c.q[game.c.q.length / 2 + game.c.s[this.h][0]];
            }
        }
        if (((al)this.p.a.a((int)0)).a.a > 0) {
            this.p.a.a(40).b(104 + (this.h << 6) / ((al)this.p.a.a((int)0)).a.a, this.p.a.a());
        }
    }

    public final void S() {
        if (this.o.k(4100)) {
            this.p.a.b(0);
            this.bb();
            return;
        }
        if (this.o.k(8448)) {
            this.p.a.b(1);
            this.bb();
            return;
        }
        if (this.o.k(16400)) {
            this.p.a.b(2);
            this.ba();
            return;
        }
        if (this.o.k(32832)) {
            this.p.a.b(3);
            this.ba();
            return;
        }
        if (this.o.k(983072)) {
            this.p.a("/data/ui/task.ui");
            this.b = an.X ? 4 : 3;
            if (this.o.Q == 0) {
                this.b = 0;
                this.o.a((byte)0);
                return;
            }
            this.o.a((byte)6);
            return;
        }
        if (this.o.k(10)) {
            this.p.a("/data/ui/task.ui");
            this.o.a((byte)0);
        }
    }

    public final void T() {
        this.p.a("/data/ui/badge.ui", 257, this);
        this.p.a("/data/ui/record.ui");
        this.b = 0;
        this.f = 0;
        for (int i2 = 0; i2 < 8; ++i2) {
            if (this.q.B[i2][0] == 0) continue;
            this.p.a.a((int)(i2 + 25)).h().m.a(i2 + 46);
        }
        this.bc();
    }

    private void bc() {
        this.p.a.a((int)13).h().a = an.f(aq.c[2][this.b][0]);
        this.p.a.a((int)14).h().a = an.f(aq.c[2][this.b][2 + this.q.c((byte)this.b, (byte)1)]);
        if (this.q.c((byte)this.b, (byte)0) == 0) {
            this.p.a.a((int)16).h().a = "Ch\u01b0a \u0111\u1ea1t";
            return;
        }
        this.p.a.a((int)16).h().a = "\u0110\u00e3 \u0111\u1ea1t \u0111\u01b0\u1ee3c";
        this.q.c((byte)this.b, (byte)1);
        this.p.a.a((int)33).h().a = "";
    }

    public final void U() {
        if (this.o.k(4100)) {
            this.p.a.b(0);
            this.bc();
            return;
        }
        if (this.o.k(8448)) {
            this.p.a.b(1);
            this.bc();
            return;
        }
        if (this.o.k(16400)) {
            this.p.a.b(2);
            this.bc();
            return;
        }
        if (this.o.k(32832)) {
            this.p.a.b(3);
            this.bc();
            return;
        }
        if (this.o.k(786432)) {
            if (this.o.Q == 8) {
                this.o.a((byte)8);
            } else {
                this.c = 1;
                this.o.a((byte)9);
            }
            this.p.a("/data/ui/badge.ui");
        }
    }

    public final void a(int n2) {
        this.p.a("/data/ui/smsTip.ui", 257, this);
        if (this.p.a.a((int)6).h().m == null) {
            this.p.a.a((int)6).h().m = new m();
            this.p.a.a((int)6).h().m.a = (byte)2;
            this.p.a.a((int)6).h().m.a(-1);
            this.p.a.a((int)6).h().m.a(257, false, (byte)-1);
            this.p.a.a((int)6).h().m.a(n2 + 46);
        }
        this.p.a.a((int)7).h().a = an.f(n2 + 187) + ":" + an.f(n2 + 195);
        this.p.a.a((int)8).h().a = an.f(377);
    }

    public final void V() {
        this.p.a("/data/ui/smsTip.ui");
    }

    public final void W() {
        this.b = 0;
        this.e(this.c);
    }

    private void e(int n2) {
        int n3 = n2;
        b[] bArray = this.q.z;
        h h2 = this;
        h2.p.a("/data/ui/petstate.ui", 257, h2);
        h2.f(n3);
        h2.f = 0;
        if (h2.o instanceof k) {
            for (int i2 = 0; i2 < 6; ++i2) {
                if (bArray[i2] != null) {
                    h2.p.a.a((int)(16 + i2 * 6)).h().a = "#P" + bArray[i2].L();
                    h2.p.a.a((int)(17 + i2 * 6)).h().a = "#P" + bArray[i2].O();
                    continue;
                }
                h2.p.a.a((int)(16 + i2 * 6)).h().a = "#P0";
                h2.p.a.a((int)(17 + i2 * 6)).h().a = "#P0";
            }
            if (h2.o.Q == 16) {
                h2.p.a.a((int)64).h().a = "G\u1edfi l\u1ea1i";
            }
            h2.p.a.a(75).a(false);
            h2.p.a.a(76).a(false);
        } else if (h2.o instanceof d) {
            for (int i3 = 0; i3 < 6; ++i3) {
                if (i3 < ((d)h2.o).f.length && bArray[((d)h2.o).f[i3]] != null) {
                    h2.p.a.a((int)(16 + i3 * 6)).h().a = "#P" + bArray[((d)h2.o).f[i3]].L();
                    h2.p.a.a((int)(17 + i3 * 6)).h().a = "#P" + bArray[((d)h2.o).f[i3]].O();
                    continue;
                }
                h2.p.a.a((int)(16 + i3 * 6)).h().a = "#P0";
                h2.p.a.a((int)(17 + i3 * 6)).h().a = "#P0";
            }
            h2.p.a.a(63).a(false);
            h2.p.a.a(64).a(false);
            if (h2.o.Q == 4) {
                h2.p.a.a((int)75).h().a = "S\u1eed d\u1ee5ng";
            } else if (h2.o.P == 5) {
                h2.p.a.a((int)75).h().a = "Xu\u1ea5t chi\u1ebfn";
            }
        }
        ((al)h2.p.a.a((int)0)).a.a = h2.q.A;
        ((al)h2.p.a.a((int)0)).a.d = h2.q.A;
        ((al)h2.p.a.a((int)0)).a.f = n3;
        h2.g = true;
    }

    private void a(b[] bArray, int n2) {
        if (bArray[n2] != null) {
            if (this.p.a.a((int)48).h().m != null) {
                this.p.a.a((int)48).h().m.d();
            } else {
                this.p.a.a((int)48).h().m = new m();
                this.p.a.a((int)48).h().m.a(0);
                this.p.a.a((int)48).h().m.a = (byte)3;
            }
            this.p.a.a((int)48).h().m.a(bArray[n2].C, false, (byte)-1);
            this.p.a.a((int)51).h().a = an.f(bArray[n2].j((byte)0));
            this.p.a.a((int)52).h().a = an.f(365 + bArray[n2].j((byte)1));
            if (bArray[n2].j((byte)19) == -1) {
                this.p.a.a((int)62).h().a = "";
            } else if (aq.c[0][bArray[n2].j((byte)19)][2] == 1 || aq.c[0][bArray[n2].j((byte)19)][2] == 2) {
                this.p.a.a((int)62).h().a = "C\u00f3 th\u1ec3 ti\u1ebfn h\u00f3a";
            } else if (aq.c[0][bArray[n2].j((byte)19)][2] == 3) {
                this.p.a.a((int)62).h().a = "C\u00f3 th\u1ec3 d\u1ecb ho\u00e1";
            }
            this.p.a.a((int)61).h().a = bArray[n2].T();
            if (this.o instanceof d) {
                this.p.a.a((int)64).h().a = "Xu\u1ea5t chi\u1ebfn";
            } else if (this.o instanceof k) {
                this.p.a.a((int)64).h().a = "X\u00e1c nh\u1eadn";
            }
            if (this.p.a.a((int)59).h().m == null) {
                this.p.a.a((int)59).h().m = new m();
                this.p.a.a((int)59).h().m.a(0);
                this.p.a.a((int)59).h().m.a = (byte)2;
                this.p.a.a((int)59).h().m.a(258, false, (byte)-1);
            }
            if (bArray[n2].c[5] != -1) {
                this.p.a.a((int)59).h().m.a(aq.c[3][bArray[n2].c[5]][1]);
                this.p.a.a((int)60).h().a = an.f(aq.c[3][bArray[n2].c[5]][0]);
            } else {
                this.p.a.a((int)59).h().m.a(0);
                this.p.a.a((int)60).h().a = "";
            }
            this.p.a.a((int)65).h().a = "" + bArray[n2].s();
            this.p.a.a((int)66).h().a = "" + bArray[n2].e((byte)2);
            this.p.a.a((int)67).h().a = "" + bArray[n2].e((byte)3);
            this.p.a.a((int)68).h().a = "" + bArray[n2].e((byte)4);
            int n3 = bArray[n2].d[0];
            int n4 = aq.a((byte)0, (short)bArray[n2].q(), (byte)4) - 1;
            for (n2 = 0; n2 < 5; ++n2) {
                this.p.a.a(74 - n2).a(true);
                this.p.a.a((int)(74 - n2)).h().m.a(257, false, (byte)-1);
                this.p.a.a((int)(74 - n2)).h().m.a = (byte)3;
                if (n2 > n4) {
                    this.p.a.a(74 - n2).a(false);
                    continue;
                }
                if (n3 > 0) {
                    this.p.a.a((int)(74 - n2)).h().m.a((byte)14, (byte)-1);
                    --n3;
                    continue;
                }
                this.p.a.a((int)(74 - n2)).h().m.a((byte)16, (byte)-1);
            }
        }
    }

    private void f(int n2) {
        if (this.o instanceof k) {
            this.a(this.q.z, n2);
            return;
        }
        if (this.o instanceof d) {
            this.a(this.q.z, (int)((d)this.o).f[n2]);
        }
    }

    public final void X() {
        block132: {
            block135: {
                block134: {
                    block133: {
                        block131: {
                            if (this.f != 0) break block131;
                            if (!an.b(this.b, 0) && !this.j() && this.o.k(4100)) {
                                this.p.a.b(0);
                            } else if (!an.b(this.b, 0) && !this.j() && this.o.k(8448)) {
                                this.p.a.b(1);
                            } else if (an.I() && !this.j() && this.o.k(196640)) {
                                if (an.H() && !an.b(this.b, 0)) {
                                    return;
                                }
                                if (this.o instanceof d) {
                                    int n2 = ((d)this.o).a(this.b);
                                    if (n2 == 0) {
                                        this.f = 2;
                                        this.p.a("/data/ui/msgwarm.ui", 257, this);
                                        this.a("S\u1ee7ng v\u1eadt n\u00e0y kh\u00f4ng th\u1ec3 tham chi\u1ebfn", "Nh\u1ea5n n\u00fat 5 \u0111\u1ec3 ti\u1ebfp t\u1ee5c");
                                        this.p.a("/data/ui/petsetting.ui");
                                    } else if (n2 == 1) {
                                        this.f = 2;
                                        this.p.a("/data/ui/msgwarm.ui", 257, this);
                                        this.a("S\u1ee7ng v\u1eadt n\u00e0y \u0111\u00e3 \u0111\u1eb7t \u1edf v\u1ecb tr\u00ed chi\u1ebfn \u0111\u1ea5u", "Nh\u1ea5n n\u00fat 5 \u0111\u1ec3 ti\u1ebfp t\u1ee5c");
                                        this.p.a("/data/ui/petsetting.ui");
                                    } else if (n2 == -1) {
                                        ((d)this.o).a((int)((d)this.o).g, 0);
                                        this.a = 0;
                                        this.o.a((byte)15);
                                        this.p.a("/data/ui/petsetting.ui");
                                        this.p.a("/data/ui/petstate.ui");
                                    }
                                } else if (this.o instanceof k) {
                                    if (this.o.Q == 16) {
                                        if (this.q.z()) {
                                            if (this.q.o(this.b)) {
                                                this.q.l(this.q.z[this.b].c[5]);
                                                this.q.z[this.b].c[5] = -1;
                                                this.q.b(this.q.z[this.b].P());
                                                this.q.m(this.b);
                                                if (this.b >= this.q.A) {
                                                    --this.b;
                                                }
                                                this.e(this.b);
                                            } else {
                                                this.f = 1;
                                                this.p.a("/data/ui/msgwarm.ui", 257, this);
                                                this.a("Ba l\u00f4 ph\u1ea3i l\u01b0u \u00edt nh\u1ea5t 1 s\u1ee7ng v\u1eadt", "Nh\u1ea5n n\u00fat 5 \u0111\u1ec3 ti\u1ebfp t\u1ee5c");
                                            }
                                        } else {
                                            this.f = 1;
                                            this.p.a("/data/ui/msgwarm.ui", 257, this);
                                            this.a("Ng\u00e2n h\u00e0ng \u0111\u00e3 \u0111\u1ea7y, kh\u00f4ng th\u1ec3 g\u1edfi l\u1ea1i", "Nh\u1ea5n n\u00fat 5 \u0111\u1ec3 ti\u1ebfp t\u1ee5c");
                                        }
                                    } else if (this.o.Q == 6 || this.o.Q == 0) {
                                        this.c = 0;
                                        this.o.m();
                                        this.f = 1;
                                        this.p.a("/data/ui/petsetting.ui", 257, this);
                                        ((al)this.p.a.a((int)0)).a.f = this.c;
                                        if (this.q.z[this.b].R() == 2) {
                                            this.p.a.a((int)9).h().a = "D\u1ecb ho\u00e1";
                                            ((al)this.p.a.a((int)0)).a.a = 6;
                                            ((al)this.p.a.a((int)0)).a.d = 6;
                                        } else if (this.q.z[this.b].R() == 1) {
                                            this.p.a.a((int)9).h().a = "Ti\u1ebfn h\u00f3a";
                                            ((al)this.p.a.a((int)0)).a.a = 6;
                                            ((al)this.p.a.a((int)0)).a.d = 6;
                                        } else {
                                            this.p.a.a((int)9).h().a = "";
                                            ((al)this.p.a.a((int)0)).a.a = 5;
                                            ((al)this.p.a.a((int)0)).a.d = 5;
                                        }
                                    } else if (this.o.Q == 27) {
                                        if (this.d == 1 && this.q.z[this.b].R() == 1 || this.d == 2 && this.q.z[this.b].R() == 2) {
                                            this.bg();
                                        } else {
                                            this.f = 4;
                                            this.E();
                                            if (this.d == 1) {
                                                this.a("S\u1ee7ng v\u1eadt n\u00e0y kh\u00f4ng th\u1ec3 ti\u1ebfn h\u00f3a", "Nh\u1ea5n n\u00fat 5 \u0111\u1ec3 ti\u1ebfp t\u1ee5c");
                                            } else if (this.d == 2) {
                                                this.a("S\u1ee7ng v\u1eadt n\u00e0y kh\u00f4ng th\u1ec3 d\u1ecb ho\u00e1", "Nh\u1ea5n n\u00fat 5 \u0111\u1ec3 ti\u1ebfp t\u1ee5c");
                                            } else {
                                                this.a("Kh\u00f4ng th\u1ec3 v\u00e0o h\u00f3a c\u00f9ng d\u1ecb ho\u00e1", "Nh\u1ea5n n\u00fat 5 \u0111\u1ec3 ti\u1ebfp t\u1ee5c");
                                            }
                                        }
                                    }
                                }
                            } else if (game.c.J() && !this.j() && this.o.k(262144)) {
                                if (this.o instanceof k) {
                                    if (this.o.Q == 16) {
                                        this.o.a((byte)16);
                                    } else if (this.o.Q == 6) {
                                        this.b = an.X ? 1 : 0;
                                        this.o.a((byte)6);
                                    } else if (this.o.Q == 27) {
                                        this.o.a((byte)27);
                                    } else if (this.o.Q == 0) {
                                        this.o.a((byte)23);
                                    }
                                    this.p.a("/data/ui/petstate.ui");
                                } else if (this.o instanceof d) {
                                    if (((d)this.o).Q == 7 || ((d)this.o).Q == 13) {
                                        return;
                                    }
                                    this.p.a("/data/ui/petstate.ui");
                                    game.d.a().k = false;
                                    this.a = 0;
                                    this.o.a((byte)20);
                                }
                            }
                            break block132;
                        }
                        if (this.f != 1) break block133;
                        if (!an.b(this.c, 0) && !this.j() && this.o.k(4100)) {
                            this.p.a.b(0);
                        } else if (!an.b(this.c, 0) && !this.j() && this.o.k(8448)) {
                            this.p.a.b(1);
                        } else if (an.I() && !this.j() && this.o.k(196640)) {
                            if (an.H() && !an.b(this.c, 0)) {
                                return;
                            }
                            if (this.o.Q == 16) {
                                this.o.a((byte)16);
                                this.p.a("/data/ui/msgwarm.ui");
                                this.p.a("/data/ui/petstate.ui");
                                this.f = 0;
                            } else if (this.o.Q == 6 || this.o.Q == 0) {
                                switch (this.c) {
                                    case 0: {
                                        h h2 = this;
                                        this.f = 2;
                                        h2.r = 0;
                                        h2.p.a("/data/ui/choice.ui", 257, h2);
                                        h2.p.a("/data/ui/petsetting.ui");
                                        h2.p.a("/data/ui/petstate.ui");
                                        h2.p.a.a((int)8).h().a = "\u0110\u1ea1o c\u1ee5";
                                        h2.p.a.a((int)9).h().a = "S\u1ed1 l\u01b0\u1ee3ng";
                                        if (h2.o instanceof k) {
                                            h2.p.a.a(5).a(false);
                                            h2.p.a.a(6).a(false);
                                            h2.p.a.a(59).a(true);
                                            h2.p.a.a(60).a(true);
                                            h2.p.a.a((int)59).h().a = "S\u1eed d\u1ee5ng";
                                        } else {
                                            h2.p.a.a(5).a(true);
                                            h2.p.a.a(6).a(true);
                                            h2.p.a.a(59).a(false);
                                            h2.p.a.a(60).a(false);
                                            h2.p.a.a((int)5).h().a = "S\u1eed d\u1ee5ng";
                                        }
                                        h2.be();
                                        h2.g = true;
                                        break;
                                    }
                                    case 1: {
                                        if (!this.q.z[this.b].S()) {
                                            this.f = 2;
                                            this.p.a("/data/ui/msgwarm.ui", 257, this);
                                            this.a("S\u1ee7ng v\u1eadt n\u00e0y kh\u00f4ng th\u1ec3 tham chi\u1ebfn", "Nh\u1ea5n n\u00fat 5 \u0111\u1ec3 ti\u1ebfp t\u1ee5c");
                                            this.p.a("/data/ui/petsetting.ui");
                                            this.b = 0;
                                            break;
                                        }
                                        if (this.b == 0) {
                                            this.f = 2;
                                            this.b = 0;
                                            this.p.a("/data/ui/msgwarm.ui", 257, this);
                                            this.a("S\u1ee7ng v\u1eadt n\u00e0y \u0111\u00e3 xu\u1ea5t chi\u1ebfn", "Nh\u1ea5n n\u00fat 5 \u0111\u1ec3 ti\u1ebfp t\u1ee5c");
                                            this.p.a("/data/ui/petsetting.ui");
                                            break;
                                        }
                                        this.q.p(this.b);
                                        this.f = 0;
                                        this.b = 0;
                                        this.e(this.b);
                                        this.p.a("/data/ui/petsetting.ui");
                                        ((al)this.p.a.a((int)0)).a.f = 0;
                                        ((al)this.p.a.a((int)0)).a.e = 0;
                                        break;
                                    }
                                    case 2: {
                                        this.o.m();
                                        h h3 = this;
                                        this.f = 2;
                                        h3.r = 0;
                                        h3.p.a("/data/ui/choice.ui", 257, h3);
                                        h3.p.a("/data/ui/petsetting.ui");
                                        h3.p.a("/data/ui/petstate.ui");
                                        h3.p.a.a((int)8).h().a = "V\u1eadt ph\u1ea9m trang s\u1ee9c";
                                        h3.p.a.a((int)9).h().a = "Tr\u1ea1ng th\u00e1i";
                                        if (h3.o instanceof k) {
                                            h3.p.a.a(5).a(false);
                                            h3.p.a.a(6).a(false);
                                            h3.p.a.a(59).a(true);
                                            h3.p.a.a(60).a(true);
                                            h3.p.a.a((int)59).h().a = "Mang theo";
                                        } else {
                                            h3.p.a.a(5).a(true);
                                            h3.p.a.a(6).a(true);
                                            h3.p.a.a(59).a(false);
                                            h3.p.a.a(60).a(false);
                                            h3.p.a.a((int)5).h().a = "Mang theo";
                                        }
                                        h3.bd();
                                        h3.g = true;
                                        break;
                                    }
                                    case 3: {
                                        if (aq.a((byte)0, (short)this.q.z[this.b].q(), (byte)22) == 2) {
                                            this.f = 3;
                                            this.E();
                                            this.p.a("/data/ui/petsetting.ui");
                                            this.a("Th\u1ea7n th\u00fa kh\u00f4ng th\u1ec3 ph\u00f3ng sinh", "Nh\u1ea5n n\u00fat 5 \u0111\u1ec3 ti\u1ebfp t\u1ee5c");
                                            break;
                                        }
                                        this.f = 2;
                                        this.p.a("/data/ui/msgconfirm.ui", 257, this);
                                        this.p.a("/data/ui/petsetting.ui");
                                        this.b("B\u1ea1n mu\u1ed1n ph\u00f3ng sinh s\u1ee7ng v\u1eadt n\u00e0y?", "X\u00e1c nh\u1eadn");
                                        break;
                                    }
                                    case 4: {
                                        h h4 = this;
                                        this.f = 2;
                                        h4.r = 0;
                                        h4.p.a("/data/ui/skill.ui", 257, h4);
                                        h4.p.a("/data/ui/petsetting.ui");
                                        h4.p.a("/data/ui/petstate.ui");
                                        h4.p.a.a((int)12).h().a = an.f(h4.q.z[h4.b].j((byte)0));
                                        h4.p.a.a((int)14).h().a = "" + h4.q.z[h4.b].s();
                                        if (h4.p.a.a((int)16).h().m != null) {
                                            h4.p.a.a((int)16).h().m.d();
                                        } else {
                                            h4.p.a.a((int)16).h().m = new m();
                                            h4.p.a.a((int)16).h().m.a(0);
                                            h4.p.a.a((int)16).h().m.a = (byte)3;
                                        }
                                        h4.p.a.a((int)16).h().m.a(h4.q.z[h4.b].C, false, (byte)-1);
                                        int n3 = h4.q.z[h4.b].E();
                                        for (int i2 = 0; i2 < n3; ++i2) {
                                            h4.p.a.a((int)(i2 + 18)).h().a = an.f(aq.c[1][h4.q.z[h4.b].t(i2)][1]);
                                        }
                                        h4.bf();
                                        h4.g = true;
                                        break;
                                    }
                                    case 5: {
                                        this.o.m();
                                        this.bg();
                                    }
                                    default: {
                                        break;
                                    }
                                }
                            }
                        } else if (game.c.J() && !this.j() && this.o.k(262144)) {
                            if (this.o.Q == 16) {
                                return;
                            }
                            this.f = 0;
                            this.p.a("/data/ui/petsetting.ui");
                        }
                        break block132;
                    }
                    if (this.f < 2) break block132;
                    if (!(this.o instanceof d)) break block134;
                    if (this.o.k(196640)) {
                        this.f = 0;
                        this.p.a("/data/ui/msgwarm.ui");
                    }
                    break block132;
                }
                if (this.o.Q != 6 && this.o.Q != 0) break block135;
                block8 : switch (this.c) {
                    case 1: {
                        if (this.o.k(196640)) {
                            this.f = 0;
                            this.p.a("/data/ui/msgwarm.ui");
                            break;
                        }
                        break block132;
                    }
                    case 2: {
                        h h5 = this;
                        if (!an.b(h5.b, 0) && !h5.j() && h5.f == 2 && h5.o.k(4100)) {
                            h5.p.a.b(0);
                            h5.bd();
                            break;
                        }
                        if (!an.b(h5.b, 0) && !h5.j() && h5.f == 2 && h5.o.k(8448)) {
                            h5.p.a.b(1);
                            h5.bd();
                            break;
                        }
                        if (an.I() && !h5.j() && h5.o.k(196640) && h5.q.L.size() > 0) {
                            if (!an.H() || an.b(h5.b, 0)) {
                                if (h5.f == 2) {
                                    int[] nArray = (int[])h5.q.L.elementAt(h5.h);
                                    if (h5.q.z[h5.b].c[5] == nArray[0]) {
                                        h5.q.l(h5.q.z[h5.b].c[5]);
                                        h5.q.z[h5.b].c[5] = -1;
                                        h5.bd();
                                        h5.E();
                                        h5.a("Th\u00e0nh c\u00f4ng d\u1ee1 xu\u1ed1ng", "Nh\u1ea5n n\u00fat 5 \u0111\u1ec3 ti\u1ebfp t\u1ee5c");
                                    } else {
                                        h5.q.f(nArray[0], h5.b);
                                        h5.bd();
                                        h5.E();
                                        h5.a("Th\u00e0nh c\u00f4ng mang theo", "Nh\u1ea5n n\u00fat 5 \u0111\u1ec3 ti\u1ebfp t\u1ee5c");
                                    }
                                    h5.f = 3;
                                    break;
                                }
                                h5.f = 2;
                                h5.o.m();
                                h5.e(h5.b);
                                h5.F();
                                h5.p.a("/data/ui/choice.ui");
                                break;
                            }
                        } else if (game.c.J() && !h5.j() && h5.f == 2 && h5.o.k(262144)) {
                            h5.e(h5.b);
                            h5.p.a("/data/ui/choice.ui");
                            break;
                        }
                        break block132;
                    }
                    case 0: {
                        h h6 = this;
                        if (h6.f == 2 && h6.o.k(4100)) {
                            h6.p.a.b(0);
                            break;
                        }
                        if (h6.f == 2 && h6.o.k(8448)) {
                            h6.p.a.b(1);
                            break;
                        }
                        if (h6.o.k(196640)) {
                            if (h6.q.J.size() > 0) {
                                if (h6.f == 2) {
                                    h6.f = 3;
                                    int[] nArray = (int[])h6.q.J.elementAt(h6.r);
                                    switch (nArray[0]) {
                                        case 13: 
                                        case 14: {
                                            h6.p.a("/data/ui/msgwarm.ui", 257, h6);
                                            h6.a("\u0110\u1ea1o c\u1ee5 n\u00e0y kh\u00f4ng th\u1ec3 s\u1eed d\u1ee5ng", "Nh\u1ea5n n\u00fat 5 \u0111\u1ec3 ti\u1ebfp t\u1ee5c");
                                            break block8;
                                        }
                                    }
                                    switch (h6.q.z[h6.b].x(nArray[0])) {
                                        case 0: {
                                            h6.p.a("/data/ui/msgwarm.ui", 257, h6);
                                            h6.a("S\u1ee7ng v\u1eadt n\u00e0y \u0111\u00e3 t\u1eed vong, kh\u00f4ng th\u1ec3 s\u1eed d\u1ee5ng", "Nh\u1ea5n n\u00fat 5 \u0111\u1ec3 ti\u1ebfp t\u1ee5c");
                                            break block8;
                                        }
                                        case 1: {
                                            h6.p.a("/data/ui/msgwarm.ui", 257, h6);
                                            h6.a("S\u1ee7ng v\u1eadt n\u00e0y kh\u00f4ng c\u00f3, kh\u00f4ng th\u1ec3 s\u1eed d\u1ee5ng", "Nh\u1ea5n n\u00fat 5 \u0111\u1ec3 ti\u1ebfp t\u1ee5c");
                                            break block8;
                                        }
                                        case 2: {
                                            h6.p.a("/data/ui/msgwarm.ui", 257, h6);
                                            h6.a("M\u00e1u \u0111\u1ea7y, kh\u00f4ng c\u1ea7n s\u1eed d\u1ee5ng", "Nh\u1ea5n n\u00fat 5 \u0111\u1ec3 ti\u1ebfp t\u1ee5c");
                                            break block8;
                                        }
                                        case 3: {
                                            h6.p.a("/data/ui/msgwarm.ui", 257, h6);
                                            h6.a("K\u1ef9 n\u0103ng gi\u00e1 tr\u1ecb \u0111\u00e3 \u0111\u1ea7y, kh\u00f4ng c\u1ea7n s\u1eed d\u1ee5ng", "Nh\u1ea5n n\u00fat 5 \u0111\u1ec3 ti\u1ebfp t\u1ee5c");
                                            break block8;
                                        }
                                        case 4: {
                                            h6.p.a("/data/ui/msgwarm.ui", 257, h6);
                                            h6.a("Tr\u00ean ng\u01b0\u1eddi \u0111\u1ec1u b\u1ecb l\u1ee3i hi\u1ec7u qu\u1ea3", "Nh\u1ea5n n\u00fat 5 \u0111\u1ec3 ti\u1ebfp t\u1ee5c");
                                            break block8;
                                        }
                                        case 5: {
                                            h6.p.a("/data/ui/msgwarm.ui", 257, h6);
                                            h6.a("Trong h\u01b0ng ph\u1ea5n, kh\u00f4ng th\u1ec3 d\u00f9ng", "Nh\u1ea5n n\u00fat 5 \u0111\u1ec3 ti\u1ebfp t\u1ee5c");
                                            break block8;
                                        }
                                        case 7: {
                                            h6.p.a("/data/ui/msgwarm.ui", 257, h6);
                                            h6.a("M\u00e1u v\u00e0 k\u1ef9 n\u0103ng \u0111\u1ec1u \u0111\u00e3 \u0111\u1ea7y, kh\u00f4ng c\u1ea7n s\u1eed d\u1ee5ng", "Nh\u1ea5n n\u00fat 5 \u0111\u1ec3 ti\u1ebfp t\u1ee5c");
                                            break block8;
                                        }
                                        case 8: {
                                            h6.p.a("/data/ui/msgwarm.ui", 257, h6);
                                            h6.a("S\u1ee7ng v\u1eadt \u0111\u00e3 ch\u1ebft, kh\u00f4ng th\u1ec3 s\u1eed d\u1ee5ng", "Nh\u1ea5n n\u00fat 5 \u0111\u1ec3 ti\u1ebfp t\u1ee5c");
                                            break block8;
                                        }
                                    }
                                    h6.q.z[h6.b].w(nArray[0]);
                                    h6.e(h6.b);
                                    h6.f = 4;
                                    h6.p.a("/data/ui/msgwarm.ui", 257, h6);
                                    h6.a("Th\u00e0nh c\u00f4ng s\u1eed d\u1ee5ng \u0111\u1ea1o c\u1ee5", "Nh\u1ea5n n\u00fat 5 \u0111\u1ec3 ti\u1ebfp t\u1ee5c");
                                    h6.p.a("/data/ui/choice.ui");
                                    break;
                                }
                                if (h6.f == 3) {
                                    h6.f = 2;
                                    h6.p.a("/data/ui/msgwarm.ui");
                                    break;
                                }
                                if (h6.f == 4) {
                                    h6.f = 0;
                                    h6.p.a("/data/ui/msgwarm.ui");
                                    break;
                                }
                            }
                        } else if (h6.f == 2 && h6.o.k(262144)) {
                            h6.e(h6.b);
                            h6.p.a("/data/ui/choice.ui");
                            break;
                        }
                        break block132;
                    }
                    case 4: {
                        h h7 = this;
                        if (h7.o.k(4100)) {
                            h7.p.a.b(0);
                            h7.bf();
                            break;
                        }
                        if (h7.o.k(8448)) {
                            h7.p.a.b(1);
                            h7.bf();
                            break;
                        }
                        if (h7.o.k(16400)) {
                            h7.p.a.b(2);
                            h7.bf();
                            break;
                        }
                        if (h7.o.k(32832)) {
                            h7.p.a.b(3);
                            h7.bf();
                            break;
                        }
                        if (h7.o.k(262144)) {
                            h7.e(h7.b);
                            h7.p.a("/data/ui/skill.ui");
                            break;
                        }
                        break block132;
                    }
                    case 3: {
                        if (this.o.k(131072) && this.f == 2 || this.o.k(131104) && this.f == 3) {
                            if (this.f == 2) {
                                if (this.q.o(this.b)) {
                                    this.q.l(this.q.z[this.b].c[5]);
                                    this.q.z[this.b].c[5] = -1;
                                    this.q.m(this.b);
                                    if (this.b >= this.q.A) {
                                        --this.b;
                                    }
                                    ((k)this.o).M.i();
                                    this.e(this.b);
                                    this.p.a("/data/ui/msgconfirm.ui");
                                    this.f = 0;
                                    break;
                                }
                                this.f = 3;
                                this.p.a("/data/ui/msgwarm.ui", 257, this);
                                this.a("Ba l\u00f4 ph\u1ea3i l\u01b0u \u00edt nh\u1ea5t 1 s\u1ee7ng v\u1eadt", "Nh\u1ea5n n\u00fat 5 \u0111\u1ec3 ti\u1ebfp t\u1ee5c");
                                this.p.a("/data/ui/msgconfirm.ui");
                                break;
                            }
                            this.f = 0;
                            this.p.a("/data/ui/msgwarm.ui");
                            break;
                        }
                        if (this.o.k(786432) && this.f <= 2) {
                            this.f = 0;
                            this.p.a("/data/ui/msgconfirm.ui");
                            break;
                        }
                        break block132;
                    }
                    case 5: {
                        this.bh();
                    }
                }
                break block132;
            }
            if (this.f <= 3) {
                this.bh();
            } else if (this.f == 4 && this.o.k(196640)) {
                this.f = 0;
                this.p.a("/data/ui/msgwarm.ui");
            }
        }
        this.g = true;
        this.g();
    }

    private void bd() {
        if (this.q.L.size() > 5) {
            ((al)this.p.a.a((int)0)).a.a(1);
        } else {
            ((al)this.p.a.a((int)0)).a.a(0);
        }
        ((al)this.p.a.a((int)0)).a.a = this.q.L.size();
        this.w = ((al)this.p.a.a((int)0)).a.e;
        this.h = ((al)this.p.a.a((int)0)).a.f;
        if (this.h >= this.q.L.size()) {
            ((al)this.p.a.a((int)0)).a.f = this.h = this.q.L.size() - 1;
        }
        if (this.w > 0 && this.h - this.w < 4) {
            --this.w;
            ((al)this.p.a.a((int)0)).a.e = this.w;
        }
        if (this.q.L.size() <= 0) {
            return;
        }
        if (this.q.z[this.b].c[5] == ((int[])this.q.L.elementAt(this.h))[0]) {
            if (this.o instanceof k) {
                this.p.a.a((int)59).h().a = "D\u1ee1 xu\u1ed1ng";
            } else {
                this.p.a.a((int)5).h().a = "D\u1ee1 xu\u1ed1ng";
            }
        } else if (this.o instanceof k) {
            this.p.a.a((int)59).h().a = "Mang theo";
        } else {
            this.p.a.a((int)5).h().a = "Mang theo";
        }
        for (int i2 = 0; i2 < 5; ++i2) {
            if (this.w + i2 < this.q.L.size()) {
                int[] nArray = (int[])this.q.L.elementAt(this.w + i2);
                if (this.p.a.a((int)(i2 + 54)).h().m == null) {
                    this.p.a.a((int)(i2 + 54)).h().m = new m();
                    this.p.a.a((int)(i2 + 54)).h().m.a(0);
                    this.p.a.a((int)(i2 + 54)).h().m.a = (byte)2;
                    this.p.a.a((int)(i2 + 54)).h().m.a(258, false, (byte)-1);
                }
                this.p.a.a((int)(i2 + 54)).h().m.a(aq.c[3][nArray[0]][1]);
                this.p.a.a((int)(13 + i2 * 5)).h().a = an.f(aq.c[3][nArray[0]][0]);
                if (game.g.o().z[this.b].c[5] == nArray[0]) {
                    this.p.a.a((int)(14 + i2 * 5)).h().a = "\u0110\u00e3 mang theo";
                    continue;
                }
                if (nArray[1] == 1) {
                    this.p.a.a((int)(14 + i2 * 5)).h().a = "B\u1ecb mang theo";
                    continue;
                }
                this.p.a.a((int)(14 + i2 * 5)).h().a = "";
                continue;
            }
            if (this.p.a.a((int)(i2 + 54)).h().m != null) {
                this.p.a.a((int)(i2 + 54)).h().m.d();
            }
            this.p.a.a((int)(13 + i2 * 5)).h().a = "";
            this.p.a.a((int)(14 + i2 * 5)).h().a = "";
        }
        this.p.a.a((int)53).h().a = this.q.L.size() > 0 ? an.f(aq.c[3][((int[])this.q.L.elementAt(this.h))[0]][2]) : "";
        if (this.q.L.size() > 0) {
            this.p.a.a(51).b(98 + this.h * 62 / this.q.L.size(), this.p.a.a());
            return;
        }
        this.p.a.a(51).b(98, this.p.a.a());
    }

    private void be() {
        if (this.q.J.size() > 5) {
            ((al)this.p.a.a((int)0)).a.a(1);
        } else {
            ((al)this.p.a.a((int)0)).a.a(0);
        }
        ((al)this.p.a.a((int)0)).a.a = this.q.J.size();
        this.w = ((al)this.p.a.a((int)0)).a.e;
        this.h = ((al)this.p.a.a((int)0)).a.f;
        if (this.h >= this.q.J.size()) {
            ((al)this.p.a.a((int)0)).a.f = this.h = this.q.J.size() - 1;
        }
        if (this.w > 0 && this.h - this.w < 4) {
            --this.w;
            ((al)this.p.a.a((int)0)).a.e = this.w;
        }
        for (int i2 = 0; i2 < 5; ++i2) {
            if (this.w + i2 < this.q.J.size()) {
                int[] nArray = (int[])this.q.J.elementAt(this.w + i2);
                if (this.p.a.a((int)(i2 + 54)).h().m == null) {
                    this.p.a.a((int)(i2 + 54)).h().m = new m();
                    this.p.a.a((int)(i2 + 54)).h().m.a(0);
                    this.p.a.a((int)(i2 + 54)).h().m.a = (byte)2;
                    this.p.a.a((int)(i2 + 54)).h().m.a(258, false, (byte)-1);
                }
                this.p.a.a((int)(i2 + 54)).h().m.a(aq.c[4][nArray[0]][1]);
                this.p.a.a((int)(13 + i2 * 5)).h().a = an.f(aq.c[4][nArray[0]][0]);
                this.p.a.a((int)(14 + i2 * 5)).h().a = "" + nArray[1];
                continue;
            }
            if (this.p.a.a((int)(i2 + 54)).h().m != null) {
                this.p.a.a((int)(i2 + 54)).h().m.d();
            }
            this.p.a.a((int)(13 + i2 * 5)).h().a = "";
            this.p.a.a((int)(14 + i2 * 5)).h().a = "";
        }
        this.p.a.a((int)53).h().a = this.q.J.size() > 0 ? an.f(aq.c[4][((int[])this.q.J.elementAt(this.h))[0]][2]) : "";
        if (this.q.J.size() > 0) {
            this.p.a.a(51).b(98 + this.h * 72 / this.q.J.size(), this.p.a.a());
            return;
        }
        this.p.a.a(51).b(98, this.p.a.a());
    }

    private void bf() {
        if (this.q.z[this.b].t(this.r) != -1) {
            String[] stringArray = new String[]{"Nh\u1ea5t \u0111\u1ecbnh", "Nh\u1ea5t \u0111\u1ecbnh"};
            this.p.a.a((int)9).h().a = an.a((int)aq.c[1][this.q.z[this.b].t(this.r)][2], stringArray);
            return;
        }
        this.p.a.a((int)9).h().a = "";
    }

    private void bg() {
        this.f = 2;
        this.r = 0;
        this.p.a("/data/ui/evolve.ui", 257, this);
        this.p.a("/data/ui/petsetting.ui");
        this.p.a("/data/ui/petstate.ui");
        if (this.p.a.a((int)10).h().m == null) {
            this.p.a.a((int)10).h().m = new m();
            this.p.a.a((int)10).h().m.a(0);
            this.p.a.a((int)10).h().m.a = (byte)3;
        }
        this.p.a.a((int)10).h().m.a(this.q.z[this.b].C, false, (byte)-1);
        int n2 = aq.a((byte)0, (byte)this.q.z[this.b].q(), (byte)20) + 12;
        short s2 = aq.a((byte)0, (byte)this.q.z[this.b].q(), (byte)21);
        this.p.a.a((int)38).h().a = an.f(aq.a((byte)0, (byte)this.q.z[this.b].q(), (byte)0));
        this.p.a.a((int)40).h().a = "" + this.q.z[this.b].s();
        this.p.a.a((int)45).h().a = an.f(aq.a((byte)3, (short)n2, (byte)0));
        this.p.a.a((int)46).h().a = this.q.a(n2, (byte)2) + "/" + s2;
        n2 = aq.a((byte)0, (byte)this.q.z[this.b].q(), (byte)19);
        b b2 = new b();
        b2.a(n2, (byte)this.q.z[this.b].s(), (short)-1, (byte)-1, (short)-1, (byte)-1);
        for (n2 = 0; n2 < 4; ++n2) {
            byte by = (byte)(n2 + 1);
            this.p.a.a((int)(n2 + 19)).h().a = "" + this.q.z[this.b].c[by];
            by = (byte)(n2 + 1);
            this.p.a.a((int)(n2 + 31)).h().a = "" + b2.c[by];
        }
        this.g = true;
    }

    private void bh() {
        if (game.k.n != null) {
            if (!game.k.n.i()) {
                short s2 = aq.a((byte)0, (byte)this.q.z[this.b].q(), (byte)19);
                String string = an.f(aq.a((byte)0, s2, (byte)0));
                h h2 = this;
                short s3 = aq.a((byte)0, (byte)h2.q.z[h2.b].q(), (byte)19);
                short s4 = aq.a((byte)0, s3, (byte)17);
                h2.p.a.a(10).a(true);
                h2.p.a.a((int)10).h().m.a(s4, false, (byte)-1);
                h2.p.a.a((int)38).h().a = an.f(aq.a((byte)0, s3, (byte)0));
                b b2 = new b();
                short s5 = aq.a((byte)0, s3, (byte)3);
                int n2 = -1;
                if (h2.q.z[h2.b].c[0] >= s5) {
                    n2 = (byte)h2.q.z[h2.b].c[0];
                }
                b2.a(s3, h2.q.z[h2.b].s(), h2.q.z[h2.b].c[5], (byte)h2.q.z[h2.b].d[6], (short)n2, (byte)-1);
                b2.a(b2.c[1], h2.q.z[h2.b].z(), (int)h2.q.z[h2.b].E);
                b2.b(h2.q.z[h2.b].Q());
                h2.q.a((byte)h2.q.z[h2.b].j((byte)1), (int)s3, (byte)2);
                h2.q.z[h2.b].a(b2.P());
                s5 = (short)(aq.a((byte)0, (byte)h2.q.z[h2.b].q(), (byte)20) + 12);
                short s6 = aq.a((byte)0, (byte)h2.q.z[h2.b].q(), (byte)21);
                s3 = aq.a((byte)0, (byte)h2.q.z[h2.b].q(), (byte)19);
                n2 = h2.q.a((int)s5, (byte)2);
                if (s3 == -1) {
                    h2.p.a.a((int)42).h().a = "";
                    h2.p.a.a((int)45).h().a = "";
                    h2.p.a.a((int)46).h().a = "";
                } else {
                    h2.p.a.a((int)45).h().a = an.f(aq.a((byte)3, s5, (byte)0));
                    h2.p.a.a((int)46).h().a = n2 + "/" + s6;
                }
                if (this.q.z[this.b].R() == 2) {
                    this.f = 3;
                    this.p.a("/data/ui/msgwarm.ui", 257, this);
                    this.a("D\u1ecb ho\u00e1 th\u00e0nh #2" + string, "Nh\u1ea5n n\u00fat 5 \u0111\u1ec3 ti\u1ebfp t\u1ee5c");
                } else {
                    this.f = 3;
                    this.p.a("/data/ui/msgwarm.ui", 257, this);
                    this.a("Ti\u1ebfn h\u00f3a th\u00e0nh #2" + string, "Nh\u1ea5n n\u00fat 5 \u0111\u1ec3 ti\u1ebfp t\u1ee5c");
                }
                game.k.n = null;
            }
            return;
        }
        if (an.I() && !this.j() && this.o.k(196640)) {
            if (this.f == 2) {
                short s7 = (short)(aq.a((byte)0, (byte)this.q.z[this.b].q(), (byte)20) + 12);
                short s8 = aq.a((byte)0, (byte)this.q.z[this.b].q(), (byte)21);
                short s9 = aq.a((byte)0, (byte)this.q.z[this.b].q(), (byte)19);
                if (s9 == -1) {
                    this.f = 3;
                    this.E();
                    this.a("Kh\u00f4ng th\u1ec3 l\u1ea1i ti\u1ebfn h\u00f3a ho\u1eb7c d\u1ecb ho\u00e1", "Nh\u1ea5n n\u00fat 5 \u0111\u1ec3 ti\u1ebfp t\u1ee5c");
                    return;
                }
                short s10 = aq.a((byte)0, s9, (byte)17);
                if (this.q.z[this.b].s() >= game.b.t[aq.a((byte)0, s9, (byte)2) - 1]) {
                    if (this.q.a((int)s7, (byte)2) >= s8) {
                        this.p.a.a(10).a(false);
                        game.k.n = new ah();
                        short[] sArray = new short[]{0, 0, 10, 0, 0, this.q.z[this.b].C, 0, 0, s10, 0, 0};
                        game.k.n.a(sArray);
                        game.k.n.c(true);
                        game.k.n.a();
                        this.q.d(s7, s8, (byte)2);
                        return;
                    }
                    this.f = 3;
                    this.p.a("/data/ui/msgwarm.ui", 257, this);
                    if (this.q.z[this.b].R() == 2) {
                        this.a("T\u00e0i li\u1ec7u ch\u01b0a \u0111\u1ee7, kh\u00f4ng th\u1ec3 d\u1ecb ho\u00e1", "Nh\u1ea5n n\u00fat 5 \u0111\u1ec3 ti\u1ebfp t\u1ee5c");
                        return;
                    }
                    this.a("T\u00e0i li\u1ec7u ch\u01b0a \u0111\u1ee7, kh\u00f4ng th\u1ec3 ti\u1ebfn h\u00f3a", "Nh\u1ea5n n\u00fat 5 \u0111\u1ec3 ti\u1ebfp t\u1ee5c");
                    return;
                }
                this.f = 3;
                this.p.a("/data/ui/msgwarm.ui", 257, this);
                this.a("C\u00f2n ch\u01b0a t\u1edbi" + game.b.t[aq.a((byte)0, s9, (byte)2) - 1] + " c\u1ea5p, kh\u00f4ng th\u1ec3 v\u00e0o h\u00f3a", "Nh\u1ea5n n\u00fat 5 \u0111\u1ec3 ti\u1ebfp t\u1ee5c");
                return;
            }
            if (this.f == 3) {
                if (this.o.Q == 6 || this.o.Q == 0) {
                    this.f = 2;
                    this.p.a("/data/ui/msgwarm.ui");
                    this.o.m();
                    return;
                }
                if (this.o.Q == 27) {
                    this.e(this.b);
                    this.f = 0;
                    this.c = 0;
                    this.p.a("/data/ui/msgwarm.ui");
                    this.p.a("/data/ui/evolve.ui");
                    return;
                }
            }
        } else if (this.f < 3 && this.o.k(262144) && !this.j() && an.J()) {
            this.f = 0;
            this.e(this.b);
            this.p.a("/data/ui/evolve.ui");
        }
    }

    public final void Y() {
        this.p.a("/data/ui/bag.ui", 257, this);
        this.b = 0;
        this.bi();
        this.p.a.b(5);
        this.p.a.a((int)14).h().a = "V\u1eadt ph\u1ea9m";
        this.b = 0;
    }

    private void bi() {
        ((al)this.p.a.a((int)(8 + this.b * 39))).a.e = 0;
        ((al)this.p.a.a((int)(8 + this.b * 39))).a.f = 0;
        this.bj();
    }

    private void bj() {
        block0 : switch (this.b) {
            case 0: {
                this.bk();
                break;
            }
            case 1: {
                h h2 = this;
                if (h2.q.L.size() > 5) {
                    ((al)h2.p.a.a((int)47)).a.a(1);
                } else {
                    ((al)h2.p.a.a((int)47)).a.a(0);
                }
                ((al)h2.p.a.a((int)47)).a.a = h2.q.L.size();
                h2.w = ((al)h2.p.a.a((int)47)).a.e;
                h2.h = ((al)h2.p.a.a((int)47)).a.f;
                h2.p.a.a(7).a(false);
                for (int i2 = 0; i2 < 5; ++i2) {
                    if (h2.w + i2 < h2.q.L.size()) {
                        int[] nArray = (int[])h2.q.L.elementAt(h2.w + i2);
                        if (h2.p.a.a((int)(59 + i2 * 5)).h().m == null) {
                            h2.p.a.a((int)(59 + i2 * 5)).h().m = new m();
                            h2.p.a.a((int)(59 + i2 * 5)).h().m.a(0);
                            h2.p.a.a((int)(59 + i2 * 5)).h().m.a = (byte)2;
                            h2.p.a.a((int)(59 + i2 * 5)).h().m.a(258, false, (byte)-1);
                        }
                        if (h2.p.a.a((int)(59 + i2 * 5)).h().i == null) {
                            h2.p.a.a((int)(59 + i2 * 5)).h().i = new m();
                            h2.p.a.a((int)(59 + i2 * 5)).h().i.a(0);
                            h2.p.a.a((int)(59 + i2 * 5)).h().i.a = (byte)2;
                            h2.p.a.a((int)(59 + i2 * 5)).h().i.a(258, false, (byte)-1);
                        }
                        h2.p.a.a((int)(59 + i2 * 5)).h().m.a(aq.c[3][nArray[0]][1]);
                        h2.p.a.a((int)(59 + i2 * 5)).h().i.a(aq.c[3][nArray[0]][1]);
                        h2.p.a.a((int)(60 + i2 * 5)).h().a = an.f(aq.c[3][nArray[0]][0]);
                        if (nArray[1] == 1) {
                            h2.p.a.a((int)(61 + i2 * 5)).h().a = "\u0110\u00e3 mang theo";
                            continue;
                        }
                        h2.p.a.a((int)(61 + i2 * 5)).h().a = "";
                        continue;
                    }
                    if (h2.p.a.a((int)(59 + i2 * 5)).h().m != null) {
                        h2.p.a.a((int)(59 + i2 * 5)).h().m.d();
                    }
                    h2.p.a.a((int)(60 + i2 * 5)).h().a = "";
                    h2.p.a.a((int)(61 + i2 * 5)).h().a = "";
                }
                h2.p.a.a((int)85).h().a = h2.q.L.size() > 0 ? an.f(aq.c[3][((int[])h2.q.L.elementAt(h2.h))[0]][2]) : "";
                if (h2.q.L.size() > 0) {
                    h2.p.a.a(84).b(127 + h2.h * 72 / h2.q.L.size(), h2.p.a.a());
                    break;
                }
                h2.p.a.a(84).b(127, h2.p.a.a());
                break;
            }
            case 2: {
                h h3 = this;
                if (h3.q.M.size() > 5) {
                    ((al)h3.p.a.a((int)86)).a.a(1);
                } else {
                    ((al)h3.p.a.a((int)86)).a.a(0);
                }
                ((al)h3.p.a.a((int)86)).a.a = h3.q.M.size();
                h3.w = ((al)h3.p.a.a((int)86)).a.e;
                h3.h = ((al)h3.p.a.a((int)86)).a.f;
                h3.p.a.a(7).a(false);
                for (int i3 = 0; i3 < 5; ++i3) {
                    if (h3.w + i3 < h3.q.M.size()) {
                        int[] nArray = (int[])h3.q.M.elementAt(h3.w + i3);
                        if (h3.p.a.a((int)(98 + i3 * 5)).h().m == null) {
                            h3.p.a.a((int)(98 + i3 * 5)).h().m = new m();
                            h3.p.a.a((int)(98 + i3 * 5)).h().m.a(0);
                            h3.p.a.a((int)(98 + i3 * 5)).h().m.a = (byte)2;
                            h3.p.a.a((int)(98 + i3 * 5)).h().m.a(258, false, (byte)-1);
                        }
                        if (h3.p.a.a((int)(98 + i3 * 5)).h().i == null) {
                            h3.p.a.a((int)(98 + i3 * 5)).h().i = new m();
                            h3.p.a.a((int)(98 + i3 * 5)).h().i.a(0);
                            h3.p.a.a((int)(98 + i3 * 5)).h().i.a = (byte)2;
                            h3.p.a.a((int)(98 + i3 * 5)).h().i.a(258, false, (byte)-1);
                        }
                        h3.p.a.a((int)(98 + i3 * 5)).h().m.a(aq.c[3][nArray[0]][1]);
                        h3.p.a.a((int)(98 + i3 * 5)).h().i.a(aq.c[3][nArray[0]][1]);
                        h3.p.a.a((int)(99 + i3 * 5)).h().a = nArray[0] == 17 ? "Ch\u00eca kh\u00f3a v\u00e0ng" : an.f(aq.c[3][nArray[0]][0]);
                        h3.p.a.a((int)(100 + i3 * 5)).h().a = "" + nArray[1];
                        continue;
                    }
                    if (h3.p.a.a((int)(98 + i3 * 5)).h().m != null) {
                        h3.p.a.a((int)(98 + i3 * 5)).h().m.d();
                    }
                    h3.p.a.a((int)(99 + i3 * 5)).h().a = "";
                    h3.p.a.a((int)(100 + i3 * 5)).h().a = "";
                }
                h3.p.a.a((int)124).h().a = h3.q.M.size() > 0 ? an.f(aq.c[3][((int[])h3.q.M.elementAt(h3.h))[0]][2]) : "";
                if (h3.q.M.size() > 0) {
                    h3.p.a.a(123).b(127 + h3.h * 72 / h3.q.M.size(), h3.p.a.a());
                    break;
                }
                h3.p.a.a(123).b(127, h3.p.a.a());
                break;
            }
            case 3: {
                this.bl();
                if (this.h < 0 || this.q.N.size() <= 0) {
                    return;
                }
                int[] nArray = (int[])this.q.N.elementAt(this.h);
                this.p.a.a(164).a(false);
                this.p.a.a(165).a(false);
                switch (nArray[0]) {
                    case 0: {
                        if (this.q.k(nArray[0])) {
                            this.p.a.a(7).a(true);
                            this.p.a.a((int)7).h().a = "\u1ea4p tr\u1ee9ng";
                            this.p.a.a(164).a(true);
                            this.p.a.a(165).a(true);
                            if (this.q.I == 0) {
                                this.p.a.a((int)164).h().a = "#P" + game.k.q * 100 / 10;
                                this.p.a.a((int)165).h().a = game.k.q + "/10";
                                break block0;
                            }
                            this.p.a.a((int)164).h().a = "#P" + game.k.q * 100 / 30;
                            this.p.a.a((int)165).h().a = game.k.q + "/30";
                            break block0;
                        }
                        this.p.a.a(7).a(false);
                        break block0;
                    }
                    case 1: 
                    case 2: 
                    case 3: 
                    case 4: {
                        this.p.a.a(7).a(false);
                        break block0;
                    }
                    case 5: 
                    case 6: 
                    case 10: {
                        this.p.a.a((int)7).h().a = "M\u1edf ra";
                        break block0;
                    }
                    case 7: 
                    case 8: 
                    case 9: {
                        this.p.a.a((int)7).h().a = "S\u1eed d\u1ee5ng";
                    }
                }
            }
        }
        this.g = true;
    }

    private void bk() {
        int n2 = this.q.K.size() + this.q.J.size();
        if (n2 > 5) {
            ((al)this.p.a.a((int)8)).a.a(1);
        } else {
            ((al)this.p.a.a((int)8)).a.a(0);
        }
        ((al)this.p.a.a((int)8)).a.a = n2;
        this.w = ((al)this.p.a.a((int)8)).a.e;
        this.h = ((al)this.p.a.a((int)8)).a.f;
        this.p.a.a(7).a(true);
        this.p.a.a((int)7).h().a = "S\u1eed d\u1ee5ng";
        for (int i2 = 0; i2 < 5; ++i2) {
            if (this.w + i2 < n2) {
                int[] nArray = this.w + i2 < this.q.K.size() ? (int[])this.q.K.elementAt(this.w + i2) : (int[])this.q.J.elementAt(this.w + i2 - this.q.K.size());
                if (this.p.a.a((int)(18 + i2 * 5)).h().m == null) {
                    this.p.a.a((int)(18 + i2 * 5)).h().m = new m();
                    this.p.a.a((int)(18 + i2 * 5)).h().m.a(0);
                    this.p.a.a((int)(18 + i2 * 5)).h().m.a = (byte)2;
                    this.p.a.a((int)(18 + i2 * 5)).h().m.a(258, false, (byte)-1);
                }
                if (this.p.a.a((int)(18 + i2 * 5)).h().i == null) {
                    this.p.a.a((int)(18 + i2 * 5)).h().i = new m();
                    this.p.a.a((int)(18 + i2 * 5)).h().i.a(0);
                    this.p.a.a((int)(18 + i2 * 5)).h().i.a = (byte)2;
                    this.p.a.a((int)(18 + i2 * 5)).h().i.a(258, false, (byte)-1);
                }
                this.p.a.a((int)(18 + i2 * 5)).h().m.a(aq.c[4][nArray[0]][1]);
                this.p.a.a((int)(18 + i2 * 5)).h().i.a(aq.c[4][nArray[0]][1]);
                this.p.a.a((int)(19 + i2 * 5)).h().a = an.f(aq.c[4][nArray[0]][0]);
                this.p.a.a((int)(20 + i2 * 5)).h().a = "" + nArray[1];
                continue;
            }
            if (this.p.a.a((int)(18 + i2 * 5)).h().m != null) {
                this.p.a.a((int)(18 + i2 * 5)).h().m.d();
            }
            this.p.a.a((int)(19 + i2 * 5)).h().a = "";
            this.p.a.a((int)(20 + i2 * 5)).h().a = "";
        }
        this.p.a.a((int)46).h().a = n2 > 0 ? (this.h < this.q.K.size() ? an.f(aq.c[4][((int[])this.q.K.elementAt(this.h))[0]][2]) : an.f(aq.c[4][((int[])this.q.J.elementAt(this.h - this.q.K.size()))[0]][2])) : "";
        if (n2 > 0) {
            this.p.a.a(43).b(127 + this.h * 72 / n2, this.p.a.a());
            return;
        }
        this.p.a.a(43).b(127, this.p.a.a());
    }

    private void bl() {
        int n2;
        if (this.q.N.size() > 5) {
            ((al)this.p.a.a((int)125)).a.a(1);
        } else {
            ((al)this.p.a.a((int)125)).a.a(0);
        }
        ((al)this.p.a.a((int)125)).a.a = this.q.N.size();
        this.w = ((al)this.p.a.a((int)125)).a.e;
        this.h = ((al)this.p.a.a((int)125)).a.f;
        for (n2 = 0; n2 < 5; ++n2) {
            if (this.w + n2 < this.q.N.size()) {
                int[] nArray = (int[])this.q.N.elementAt(this.w + n2);
                if (this.p.a.a((int)(137 + n2 * 5)).h().m == null) {
                    this.p.a.a((int)(137 + n2 * 5)).h().m = new m();
                    this.p.a.a((int)(137 + n2 * 5)).h().m.a(0);
                    this.p.a.a((int)(137 + n2 * 5)).h().m.a = (byte)2;
                    this.p.a.a((int)(137 + n2 * 5)).h().m.a(258, false, (byte)-1);
                }
                if (this.p.a.a((int)(137 + n2 * 5)).h().i == null) {
                    this.p.a.a((int)(137 + n2 * 5)).h().i = new m();
                    this.p.a.a((int)(137 + n2 * 5)).h().i.a(0);
                    this.p.a.a((int)(137 + n2 * 5)).h().i.a = (byte)2;
                    this.p.a.a((int)(137 + n2 * 5)).h().i.a(258, false, (byte)-1);
                }
                this.p.a.a((int)(137 + n2 * 5)).h().m.a(aq.c[5][nArray[0]][1]);
                this.p.a.a((int)(137 + n2 * 5)).h().i.a(aq.c[5][nArray[0]][1]);
                this.p.a.a((int)(138 + n2 * 5)).h().a = an.f(aq.c[5][nArray[0]][0]);
                switch (nArray[0]) {
                    case 0: {
                        if (this.q.k(nArray[0])) {
                            this.p.a.a((int)163).h().a = an.f(aq.c[5][nArray[0]][2]);
                            if (game.k.a().r()) {
                                this.p.a.a((int)(139 + n2 * 5)).h().a = "Ho\u00e0n th\u00e0nh";
                                break;
                            }
                            this.p.a.a((int)(139 + n2 * 5)).h().a = "1 c\u00e1i";
                            break;
                        }
                        this.p.a.a((int)163).h().a = an.f(634);
                        this.p.a.a((int)(139 + n2 * 5)).h().a = "0 c\u00e1i";
                        break;
                    }
                    default: {
                        this.p.a.a((int)(139 + n2 * 5)).h().a = "";
                        break;
                    }
                }
                continue;
            }
            if (this.p.a.a((int)(137 + n2 * 5)).h().m != null) {
                this.p.a.a((int)(137 + n2 * 5)).h().m.d();
            }
            this.p.a.a((int)(138 + n2 * 5)).h().a = "";
            this.p.a.a((int)(139 + n2 * 5)).h().a = "";
        }
        if (this.q.N.size() > 0) {
            n2 = ((int[])this.q.N.elementAt(this.h))[0];
            if (n2 != 0) {
                this.p.a.a((int)163).h().a = an.f(aq.c[5][n2][2]);
                this.p.a.a(7).a(true);
            }
            this.p.a.a((int)7).h().a = n2 == 0 ? (((int[])this.q.N.elementAt(this.h))[1] == 1 ? "\u0110\u00f3ng c\u1eeda" : "M\u1edf ra") : (n2 > 0 || n2 <= 4 ? (this.q.t == n2 - 1 ? "Tri\u1ec7u h\u1ed3i" : "Tri\u1ec7u ho\u00e1n") : (n2 == 10 ? "Gia t\u1ed1c" : "S\u1eed d\u1ee5ng"));
        } else {
            this.p.a.a((int)163).h().a = "";
            this.p.a.a(7).a(false);
        }
        if (this.q.N.size() > 0) {
            this.p.a.a(162).b(127 + this.h * 72 / this.q.N.size(), this.p.a.a());
            return;
        }
        this.p.a.a(162).b(127, this.p.a.a());
    }

    public final void Z() {
        if (this.f == 0 && this.o.k(4100)) {
            this.p.a.b(0);
            this.f(this.c);
            return;
        }
        if (this.f == 0 && this.o.k(8448)) {
            this.p.a.b(1);
            this.f(this.c);
            return;
        }
        if (this.o.k(196640)) {
            this.bo();
            return;
        }
        if (this.f == 0 && this.o.k(262144)) {
            this.o.a((byte)8);
            this.p.a("/data/ui/petstate.ui");
        }
    }

    public final void aa() {
        if (this.o.k(4100)) {
            this.p.a.b(0);
            this.f(this.b);
            return;
        }
        if (this.o.k(8448)) {
            this.p.a.b(1);
            this.f(this.b);
            return;
        }
        if (this.o.k(196640)) {
            this.q.f(this.s, this.b);
            this.o.a((byte)8);
            return;
        }
        if (this.o.k(262144)) {
            this.o.a((byte)8);
            this.p.a("/data/ui/petstate.ui");
        }
    }

    public final void ab() {
        if (this.o.k(4100)) {
            this.p.a.b(0);
            this.f(this.b);
            return;
        }
        if (this.o.k(8448)) {
            this.p.a.b(1);
            this.f(this.b);
            return;
        }
        if (this.o.k(196640)) {
            if (this.f == 0) {
                if (this.q.z[this.b].s() < 50) {
                    this.p.a("/data/ui/msgwarm.ui", 257, this);
                    this.a("Ch\u1ec9 c\u00f3 th\u1ec3 cho 50 c\u1ea5p s\u1ee7ng v\u1eadt s\u1eed d\u1ee5ng", "Nh\u1ea5n n\u00fat 5 \u0111\u1ec3 ti\u1ebfp t\u1ee5c");
                    this.f = 2;
                    return;
                }
                if (this.q.e(this.s, this.b)) {
                    this.p.a("/data/ui/msgwarm.ui", 257, this);
                    this.a("S\u1eed d\u1ee5ng th\u00e0nh c\u00f4ng", "Nh\u1ea5n n\u00fat 5 \u0111\u1ec3 ti\u1ebfp t\u1ee5c");
                    this.f = 1;
                    return;
                }
            } else {
                if (this.f == 1) {
                    this.f = 0;
                    this.o.a((byte)8);
                    this.p.a("/data/ui/msgwarm.ui");
                    this.p.a("/data/ui/petstate.ui");
                    return;
                }
                if (this.f == 2) {
                    this.f = 0;
                    this.p.a("/data/ui/msgwarm.ui");
                    return;
                }
            }
        } else if (this.o.k(262144) && this.f == 0) {
            this.o.a((byte)8);
            this.p.a("/data/ui/petstate.ui");
        }
    }

    /*
     * Enabled aggressive block sorting
     */
    public final void ac() {
        block46: {
            block50: {
                block54: {
                    block52: {
                        block44: {
                            int n2;
                            block53: {
                                block51: {
                                    block49: {
                                        block48: {
                                            block47: {
                                                block45: {
                                                    this.o.l();
                                                    if (this.f != 0 || !this.o.k(16400) || this.j() || an.b(this.b, 1)) break block45;
                                                    this.p.a.b(7);
                                                    this.p.a.b(2);
                                                    this.p.a.b(5);
                                                    this.bi();
                                                    this.o.m();
                                                    break block46;
                                                }
                                                if (this.f != 0 || !this.o.k(32832) || this.j() || an.b(this.b, 1)) break block47;
                                                this.p.a.b(7);
                                                this.p.a.b(3);
                                                this.p.a.b(5);
                                                this.bi();
                                                this.o.m();
                                                break block46;
                                            }
                                            if (this.f != 0 || !this.o.k(4100) || this.j() || an.b(this.h, 0)) break block48;
                                            this.p.a.b(0);
                                            break block46;
                                        }
                                        if (this.f != 0 || !this.o.k(8448) || this.j() || an.b(this.h, 0)) break block49;
                                        this.p.a.b(1);
                                        break block46;
                                    }
                                    if (!this.o.k(196640) || this.j() || !an.I()) break block50;
                                    if (this.f != 0) break block51;
                                    if (an.H() && !an.b(this.h, 0)) {
                                        return;
                                    }
                                    block0 : switch (this.b) {
                                        case 0: {
                                            int[] nArray;
                                            if (this.h >= this.q.K.size()) {
                                                if (this.q.J.size() <= 0) {
                                                    return;
                                                }
                                                nArray = (int[])this.q.J.elementAt(this.h - this.q.K.size());
                                            } else {
                                                nArray = (int[])this.q.K.elementAt(this.h);
                                            }
                                            switch (nArray[0]) {
                                                case 0: 
                                                case 1: 
                                                case 2: 
                                                case 3: {
                                                    if (this.f == 0) {
                                                        this.p.a("/data/ui/msgwarm.ui", 257, this);
                                                        this.a("Kh\u00f4ng th\u1ec3 s\u1eed d\u1ee5ng", "Nh\u1ea5n n\u00fat 5 \u0111\u1ec3 ti\u1ebfp t\u1ee5c");
                                                        this.f = 1;
                                                        break;
                                                    }
                                                    this.p.a("/data/ui/msgwarm.ui");
                                                    this.f = 0;
                                                    break;
                                                }
                                                case 14: {
                                                    if (this.f != 0) break;
                                                    if (this.q.k(0) && (this.q.I == 0 && game.k.q < 10 || this.q.I > 0 && game.k.q < 30)) {
                                                        if (!this.q.b(nArray[0], 1, (byte)0)) break;
                                                        game.k.q = this.q.I == 0 ? 10 : 30;
                                                        this.q.d(nArray[0], 1, (byte)0);
                                                        int n3 = this.q.K.size() + this.q.J.size();
                                                        if (this.h >= n3) {
                                                            ((al)this.p.a.a((int)8)).a.f = this.h = n3 - 1;
                                                        }
                                                        if (this.w > 0 && this.h - this.w < 4) {
                                                            --this.w;
                                                            ((al)this.p.a.a((int)8)).a.e = this.w;
                                                        }
                                                        this.bk();
                                                        this.p.a("/data/ui/msgwarm.ui", 257, this);
                                                        this.a("Th\u00e0nh c\u00f4ng s\u1eed d\u1ee5ng, tranh th\u1ee7 th\u1eddi gian \u0111i \u1ea5p tr\u1ee9ng tr\u01b0\u0301ng su\u0309ng v\u00e2\u0323t a!", "Nh\u1ea5n n\u00fat 5 \u0111\u1ec3 ti\u1ebfp t\u1ee5c");
                                                        this.f = 1;
                                                        break;
                                                    }
                                                    this.p.a("/data/ui/msgwarm.ui", 257, this);
                                                    this.a("Kh\u00f4ng c\u00f3 tr\u1ee9ng c\u00f3 th\u1ec3 \u1ea5p tr\u1ee9ng", "Nh\u1ea5n n\u00fat 5 \u0111\u1ec3 ti\u1ebfp t\u1ee5c");
                                                    this.f = 1;
                                                    break;
                                                }
                                                case 13: {
                                                    if (this.f != 0) break;
                                                    if (this.q.x <= 0) {
                                                        if (game.k.a().f == 3 && game.k.a().g == 7) {
                                                            this.E();
                                                            this.a("N\u01a1i n\u00e0y kh\u00f4ng c\u00e1ch n\u00e0o s\u1eed d\u1ee5ng tr\u00e1nh qu\u00e1i ho\u00e0n", "Nh\u1ea5n n\u00fat 5 \u0111\u1ec3 ti\u1ebfp t\u1ee5c");
                                                            this.f = 1;
                                                            break;
                                                        }
                                                        if (!this.q.b(nArray[0], 1, (byte)0)) break;
                                                        this.q.d(nArray[0], 1, (byte)0);
                                                        this.q.x = aq.c[4][nArray[0]][6];
                                                        this.q.w = 0;
                                                        int n4 = this.q.K.size() + this.q.J.size();
                                                        if (this.h >= n4) {
                                                            ((al)this.p.a.a((int)8)).a.f = this.h = n4 - 1;
                                                        }
                                                        if (this.w > 0 && this.h - this.w < 4) {
                                                            --this.w;
                                                            ((al)this.p.a.a((int)8)).a.e = this.w;
                                                        }
                                                        this.bk();
                                                        this.E();
                                                        this.q.c(1);
                                                        this.a("Th\u00e0nh c\u00f4ng s\u1eed d\u1ee5ng \u0111\u1ea1o c\u1ee5, c\u0169ng c\u00f3 th\u1eddi gian ng\u1eafn tr\u00e1nh qu\u00e1i hi\u1ec7u qu\u1ea3", "Nh\u1ea5n n\u00fat 5 \u0111\u1ec3 ti\u1ebfp t\u1ee5c");
                                                        this.f = 1;
                                                        break;
                                                    }
                                                    this.E();
                                                    this.a("\u0110\u00e3 c\u00f3 \u0111\u01b0\u1ee3c th\u1eddi gian ng\u1eafn tr\u00e1nh qu\u00e1i hi\u1ec7u qu\u1ea3", "Nh\u1ea5n n\u00fat 5 \u0111\u1ec3 ti\u1ebfp t\u1ee5c");
                                                    this.f = 1;
                                                    break;
                                                }
                                                default: {
                                                    this.s = nArray[0];
                                                    this.o.a((byte)17);
                                                    this.p.a("/data/ui/bag.ui");
                                                    break;
                                                }
                                            }
                                            break;
                                        }
                                        case 3: {
                                            int[] nArray = (int[])this.q.N.elementAt(this.h);
                                            switch (nArray[0]) {
                                                case 0: {
                                                    if (!this.q.k(nArray[0])) break block0;
                                                    if (game.k.a().r()) {
                                                        if (this.q.y() == 2) {
                                                            this.E();
                                                            this.a("Kh\u00f4ng gian kh\u00f4ng \u0111\u1ee7, th\u1ec9nh thanh l\u00fd kh\u00f4ng gian l\u1ea1i \u1ea5p tr\u1ee9ng", "Nh\u1ea5n n\u00fat 5 \u0111\u1ec3 ti\u1ebfp t\u1ee5c");
                                                            this.f = 1;
                                                            break block0;
                                                        }
                                                        game.k.q = 0;
                                                        if (game.k.a().M.b[game.k.a(4, 5)] != null) {
                                                            game.k.a().M.b[game.k.a((int)4, (int)5)][15] = 4;
                                                            if (game.k.a().f == 4 && game.k.a().g == 5) {
                                                                game.k.a().M.a[15].a((byte)4);
                                                            }
                                                        }
                                                        this.q.j(nArray[0]);
                                                        this.bl();
                                                        this.E();
                                                        this.a("\u1ea4p tr\u1ee9ng th\u00e0nh c\u00f4ng", "Nh\u1ea5n n\u00fat 5 \u0111\u1ec3 ti\u1ebfp t\u1ee5c");
                                                        this.f = 2;
                                                        break block0;
                                                    }
                                                    this.E();
                                                    this.a("V\u1eabn ch\u01b0a th\u1ec3 \u1ea5p tr\u1ee9ng", "Nh\u1ea5n n\u00fat 5 \u0111\u1ec3 ti\u1ebfp t\u1ee5c");
                                                    this.f = 1;
                                                    break block0;
                                                }
                                                case 5: {
                                                    this.o.a((byte)11);
                                                    this.p.a("/data/ui/bag.ui");
                                                    break block0;
                                                }
                                                case 10: {
                                                    this.o.a((byte)24);
                                                    this.p.a("/data/ui/bag.ui");
                                                    break block0;
                                                }
                                                case 6: {
                                                    this.o.a((byte)12);
                                                    this.p.a("/data/ui/bag.ui");
                                                    break block0;
                                                }
                                                case 7: 
                                                case 8: 
                                                case 9: {
                                                    this.s = nArray[0];
                                                    this.o.a((byte)19);
                                                    this.p.a("/data/ui/bag.ui");
                                                    break block0;
                                                }
                                            }
                                        }
                                    }
                                    break block46;
                                }
                                if (this.f != 1 && this.f != 2) break block46;
                                if (this.f != 2) break block52;
                                if (this.q.I != 0) break block53;
                                byte by = this.g(58);
                                this.q.a((short)58);
                                if (by == 0) {
                                    this.c("\u1ea4p tr\u1ee9ng t\u00ecm \u0111\u01b0\u1ee3c #2" + an.f(aq.c[0][58][0]) + "#0 \u0111\u1ec3 v\u00e0o ba l\u00f4");
                                    break block44;
                                } else if (by == 1) {
                                    this.c("\u1ea4p tr\u1ee9ng t\u00ecm \u0111\u01b0\u1ee3c #2" + an.f(aq.c[0][58][0]) + "#0 \u0111\u1ec3 v\u00e0o ng\u00e2n h\u00e0ng");
                                    break block44;
                                } else {
                                    this.c("Kh\u00f4ng c\u00f3 kh\u00f4ng gian, \u0111\u00e3 ph\u00f3ng sinh");
                                }
                                break block44;
                            }
                            int n5 = ae.a(new int[]{76, 52, 28, 4, 0}, ae.a(100));
                            short[] sArray = new short[]{0, 56, 58, 95, 72};
                            byte by = this.g(sArray[n5]);
                            for (n2 = 0; n2 < this.q.I && this.q.R[n2] != sArray[n5]; ++n2) {
                            }
                            if (n2 >= this.q.I) {
                                this.q.a(sArray[n5]);
                            }
                            if (by == 0) {
                                this.c("\u1ea4p tr\u1ee9ng t\u00ecm \u0111\u01b0\u1ee3c #2" + an.f(aq.c[0][sArray[n5]][0]) + "#0 \u0111\u1ec3 v\u00e0o ba l\u00f4");
                            } else if (by == 1) {
                                this.c("\u1ea4p tr\u1ee9ng t\u00ecm \u0111\u01b0\u1ee3c #2" + an.f(aq.c[0][sArray[n5]][0]) + "#0 \u0111\u1ec3 v\u00e0o ng\u00e2n h\u00e0ng");
                            } else {
                                this.c("Kh\u00f4ng c\u00f3 kh\u00f4ng gian, \u0111\u00e3 ph\u00f3ng sinh");
                            }
                        }
                        this.f = 3;
                        break block54;
                    }
                    this.o.m();
                    this.f = 0;
                }
                this.F();
                break block46;
            }
            if (this.f == 0 && this.o.k(262144) && !this.j() && an.J()) {
                this.b = an.X ? 2 : 1;
                this.o.a((byte)6);
                this.p.a("/data/ui/bag.ui");
            }
        }
        if (this.f == 3 && !this.j()) {
            this.o.m();
            this.bj();
            this.f = 0;
        }
        this.f();
        this.g = true;
    }

    private byte g(int n2) {
        int[][] nArrayArray = new int[][]{{60, 20, 0}, {75, 50, 20, 0}};
        int n3 = -1;
        int n4 = 0;
        if (aq.c[0][n2][4] == 5) {
            if (aq.c[0][n2][3] == 2) {
                n3 = 1;
                n4 = 2;
            } else if (aq.c[0][n2][3] == 3) {
                n3 = 0;
                n4 = 3;
            }
        }
        int n5 = aq.c[0][n2][1] * 10;
        int n6 = aq.c[1][n5][5];
        byte by = this.q.y();
        if (n3 == -1) {
            if (by == 0) {
                this.q.a(n2, 5, (byte)2, (short)-1, new int[]{1, n5, n6});
            } else if (by == 1) {
                int n7 = ae.b(aq.c[0][n2][3], (int)aq.c[0][n2][3]);
                this.q.a(n2, 5, (byte)2, (byte)n7, game.b.b(n2, 5, n7), -1, new int[]{1, n5, n6});
            }
        } else {
            n4 = (byte)(n4 + (byte)ae.a(nArrayArray[n3], ae.a(100)));
            if (by == 0) {
                this.q.a(n2, 5, (byte)2, (short)n4, new int[]{1, n5, n6});
            } else if (by == 1) {
                this.q.a(n2, 5, (byte)2, (short)n4, game.b.b(n2, 5, n4), -1, new int[]{1, n5, n6});
            }
        }
        return by;
    }

    public final void ad() {
        this.aS();
        this.p.a("/data/ui/ride.ui", 257, this);
        this.b = 0;
        this.bm();
    }

    private void bm() {
        for (int i2 = 0; i2 < 4; ++i2) {
            if (this.p.a.a((int)(i2 + 4)).h().m == null) {
                this.p.a.a((int)(i2 + 4)).h().m = new m();
                this.p.a.a((int)(i2 + 4)).h().m.a(0);
                this.p.a.a((int)(i2 + 4)).h().m.a = (byte)3;
                this.p.a.a((int)(i2 + 4)).h().m.a(260, false, (byte)-1);
            }
            if (this.p.a.a((int)(i2 + 16)).h().m == null) {
                this.p.a.a((int)(i2 + 16)).h().m = new m();
                this.p.a.a((int)(i2 + 16)).h().m.a(131);
                this.p.a.a((int)(i2 + 16)).h().m.a = (byte)2;
                this.p.a.a((int)(i2 + 16)).h().m.a(257, false, (byte)0);
            }
            if (this.q.f(i2)) {
                if (this.b == i2) {
                    this.p.a.a((int)(i2 + 4)).h().m.a((byte)i2, (byte)-1);
                    if (this.b == 0) {
                        this.p.a.a((int)(i2 + 8)).h().a = "L\u1ee5c \u0111i \u0111i\u1ec3u";
                    } else if (this.b == 1) {
                        this.p.a.a((int)(i2 + 8)).h().a = "H\u01b0 kh\u00f4ng h\u00e0nh gi\u1ea3";
                    } else if (this.b == 2) {
                        this.p.a.a((int)(i2 + 8)).h().a = "H\u1ea3i \u00e2u";
                    } else if (this.b == 3) {
                        this.p.a.a((int)(i2 + 8)).h().a = "Nham s\u01a1n long";
                    }
                } else {
                    this.p.a.a((int)(i2 + 4)).h().m.a((byte)(i2 + 8), (byte)-1);
                    this.p.a.a((int)(i2 + 8)).h().a = "";
                }
                if (!this.q.g(i2)) {
                    this.p.a.a(i2 + 16).a(true);
                    continue;
                }
                this.p.a.a(i2 + 16).a(false);
                continue;
            }
            this.p.a.a(i2 + 16).a(false);
            this.p.a.a((int)(i2 + 4)).h().m.a((byte)(i2 + 4), (byte)-1);
            this.p.a.a((int)(i2 + 8)).h().a = "";
        }
    }

    public final void ae() {
        if (!this.j() && this.o.k(16400)) {
            this.p.a.b(2);
        } else if (!this.j() && this.o.k(32832)) {
            this.p.a.b(3);
        } else if (!this.j() && this.o.k(512)) {
            this.p.a("/data/ui/ride.ui");
            this.o.a((byte)0);
        } else if (!this.j() && this.o.k(196640)) {
            if (this.q.f(this.b)) {
                if (this.q.g(this.b)) {
                    this.q.h(this.b);
                    this.p.a("/data/ui/ride.ui");
                    this.o.a((byte)0);
                } else {
                    this.b("N\u01a1i n\u00e0y kh\u00f4ng th\u1ec3 s\u1eed d\u1ee5ng s\u1ee7ng v\u1eadt c\u01b0\u1ee1i");
                }
            } else {
                this.b("Ch\u01b0a c\u00f3 s\u1ee7ng v\u1eadt c\u01b0\u1ee1i n\u00e0y");
            }
        } else if (!this.j() && this.o.k(262144)) {
            this.p.a("/data/ui/ride.ui");
            this.o.a((byte)0);
        }
        this.f();
        this.g = true;
    }

    public final void a(b object, b b2) {
        this.p.a("/data/ui/battle.ui", 257, this);
        this.a = 0;
        this.e = 0;
        this.a((b)object, false);
        this.b(b2, false);
        object = this;
        this.p.a.a((int)59).h().a = "100%";
        ((h)object).p.a.a((int)58).h().a = "100%";
        ((d)((h)object).o).e();
        this.p.a("/data/ui/world.ui");
    }

    public final void b(b b2, b b3) {
        if (b2.a(b3) == 0) {
            if (b2.r() == 0) {
                this.p.a.a((int)59).h().a = "300%";
                this.p.a.a((int)58).h().a = "60%";
                return;
            }
            this.p.a.a((int)59).h().a = "60%";
            this.p.a.a((int)58).h().a = "300%";
            return;
        }
        if (b2.a(b3) == 1) {
            if (b2.r() == 0) {
                this.p.a.a((int)59).h().a = "60%";
                this.p.a.a((int)58).h().a = "300%";
                return;
            }
            this.p.a.a((int)59).h().a = "300%";
            this.p.a.a((int)58).h().a = "60%";
            return;
        }
        this.p.a.a((int)59).h().a = "100%";
        this.p.a.a((int)58).h().a = "100%";
    }

    public final void a(b b2, b b3, b b4, int n2, int n3) {
        if (b2.a(b3) == 0) {
            if (b4.r() == 0) {
                if ((n2 *= 200 / n3) == n3 && n2 != 200) {
                    n2 = 200;
                }
                this.p.a.a((int)59).h().a = n2 + 100 + "%";
                return;
            }
            if (b4.r() == 1) {
                if ((n2 *= 40 / n3) == n3 && n2 != 40) {
                    n2 = 40;
                }
                this.p.a.a((int)58).h().a = 100 - n2 + "%";
                return;
            }
        } else if (b2.a(b3) == 1) {
            if (b4.r() == 0) {
                if ((n2 *= 40 / n3) == n3 && n2 != 40) {
                    n2 = 40;
                }
                this.p.a.a((int)59).h().a = 100 - n2 + "%";
                return;
            }
            if (b4.r() == 1) {
                if ((n2 *= 200 / n3) == n3 && n2 != 200) {
                    n2 = 200;
                }
                this.p.a.a((int)58).h().a = n2 + 100 + "%";
                return;
            }
        } else {
            this.p.a.a((int)59).h().a = "100%";
            this.p.a.a((int)58).h().a = "100%";
        }
    }

    public final void a(b b2, b b3, int n2, int n3) {
        this.E = 0;
        this.D = 0;
        if (b2.a(b3) == 0) {
            this.D += n2 * (200 / n3);
            if (this.D == n3 && this.D != 200) {
                this.D = 200;
            }
            this.p.a.a((int)59).h().a = 100 + this.D + "%";
            this.E += n2 * (40 / n3);
            if (this.E == n3 && this.E != 40) {
                this.E = 40;
            }
            this.p.a.a((int)58).h().a = 100 - this.E + "%";
            return;
        }
        if (b2.a(b3) == 1) {
            this.D += n2 * (40 / n3);
            if (this.D == n3 && this.D != 40) {
                this.D = 40;
            }
            this.p.a.a((int)59).h().a = 100 - this.D + "%";
            this.E += n2 * (200 / n3);
            if (this.E == n3 && this.E != 200) {
                this.E = 200;
            }
            this.p.a.a((int)58).h().a = 100 + this.E + "%";
            return;
        }
        this.p.a.a((int)59).h().a = "100%";
        this.p.a.a((int)58).h().a = "100%";
    }

    public final boolean a(b b2, boolean bl) {
        int n2;
        int n3;
        int n4 = 0;
        if (this.F == 0 && (n4 = Math.abs(b2.N() - b2.d[1]) / 11) <= 1) {
            n4 = 1;
        }
        if ((n3 = b2.N()) != (n2 = b2.d[1])) {
            ++this.G;
            if (this.G < 4) {
                if (bl) {
                    this.p.a.a((int)55).h().a = "#P" + b2.L();
                    this.p.a.a((int)11).h().a = "#P" + b2.M();
                } else {
                    this.p.a.a((int)55).h().a = "#P" + b2.M();
                    this.p.a.a((int)11).h().a = "#P" + b2.L();
                }
                return false;
            }
        }
        this.F += n4;
        if (bl) {
            if ((n3 += this.F) >= n2) {
                n3 = n2;
            }
            b2.u(n3);
            this.p.a.a((int)41).h().a = "#P" + b2.L();
            this.p.a.a((int)11).h().a = "#P" + b2.M();
            this.p.a.a((int)55).h().a = "#P" + b2.M();
        } else {
            if ((n3 -= this.F) <= n2) {
                n3 = n2;
            }
            b2.u(n3);
            this.p.a.a((int)41).h().a = "#P" + b2.M();
            this.p.a.a((int)55).h().a = "#P" + b2.L();
            this.p.a.a((int)11).h().a = "#P" + b2.L();
        }
        this.p.a.a((int)38).h().a = b2.N() + "/" + b2.c[1];
        this.p.a.a((int)9).h().a = "#P" + b2.O();
        this.p.a.a((int)40).h().a = b2.z() + "/" + b2.u();
        this.p.a.a((int)12).h().a = an.f(b2.j((byte)0));
        this.p.a.a((int)13).h().a = "lv" + b2.s();
        this.p.a.a((int)17).h().m.a(94 + b2.j((byte)1));
        if (n3 == n2) {
            this.F = 0;
            this.G = 0;
            this.k = 0;
            return true;
        }
        return false;
    }

    public final void a(b b2) {
        int n2;
        for (n2 = 0; n2 < 6; ++n2) {
            if (this.p.a.a((int)(n2 + 26)).h().m == null) {
                this.p.a.a((int)(n2 + 26)).h().m = new m();
                this.p.a.a((int)(n2 + 26)).h().m.a = (byte)2;
                this.p.a.a((int)(n2 + 26)).h().m.a(0);
                this.p.a.a((int)(n2 + 26)).h().m.a(325, false, (byte)0);
            }
            if (this.p.a.a((int)(n2 + 43)).h().m == null) {
                this.p.a.a((int)(n2 + 43)).h().m = new m();
                this.p.a.a((int)(n2 + 43)).h().m.a = (byte)2;
                this.p.a.a((int)(n2 + 43)).h().m.a(145);
                this.p.a.a((int)(n2 + 43)).h().m.a(257, false, (byte)0);
            }
            this.p.a.a((int)(n2 + 43)).h().m.a(145);
            this.p.a.a((int)(n2 + 26)).h().m.a(0);
        }
        for (n2 = 0; n2 < 3; ++n2) {
            if (b2.x[0][n2] != -1 && b2.v[b2.x[0][n2]][0] > 0) {
                this.p.a.a((int)(43 + this.k)).h().m.a(134 + b2.v[b2.x[0][n2]][0]);
                this.p.a.a((int)(26 + this.k)).h().m.a(b2.x[0][n2] + 12);
                ++this.k;
            }
            if (b2.x[1][n2] == -1 || b2.w[b2.x[1][n2]][0] <= 0) continue;
            this.p.a.a((int)(43 + this.k)).h().m.a(134 + b2.w[b2.x[1][n2]][0]);
            this.p.a.a((int)(26 + this.k)).h().m.a(b2.x[1][n2] + 1);
            ++this.k;
        }
    }

    private void g(b b2) {
        this.p.a.a((int)11).h().a = "#P" + b2.L();
        this.p.a.a((int)38).h().a = b2.N() + "/" + b2.c[1];
        this.p.a.a((int)16).h().a = "lv" + b2.s();
    }

    public final boolean b(b b2, boolean bl) {
        int n2;
        int n3;
        int n4 = 0;
        if (this.F == 0 && (n4 = Math.abs(b2.N() - b2.d[1]) / 11) <= 1) {
            n4 = 1;
        }
        if ((n3 = b2.N()) != (n2 = b2.d[1])) {
            ++this.G;
            if (this.G < 4) {
                if (bl) {
                    this.p.a.a((int)56).h().a = "#P" + b2.L();
                    this.p.a.a((int)14).h().a = "#P" + b2.M();
                } else {
                    this.p.a.a((int)56).h().a = "#P" + b2.M();
                    this.p.a.a((int)14).h().a = "#P" + b2.L();
                }
                return false;
            }
        }
        this.F += n4;
        if (bl) {
            if ((n3 += this.F) >= n2) {
                n3 = n2;
            }
            b2.u(n3);
            this.p.a.a((int)42).h().a = "#P" + b2.L();
            this.p.a.a((int)14).h().a = "#P" + b2.M();
            this.p.a.a((int)56).h().a = "#P" + b2.M();
        } else {
            if ((n3 -= this.F) <= n2) {
                n3 = n2;
            }
            b2.u(n3);
            this.p.a.a((int)42).h().a = "#P" + b2.M();
            this.p.a.a((int)14).h().a = "#P" + b2.L();
            this.p.a.a((int)56).h().a = "#P" + b2.L();
        }
        this.p.a.a((int)39).h().a = b2.N() + "/" + b2.c[1];
        if (this.q.a((byte)b2.j((byte)1), b2.q()) == 2) {
            this.p.a.a((int)19).h().m.a(101);
        } else {
            this.p.a.a((int)19).h().m.a(102);
        }
        this.p.a.a((int)15).h().a = an.f(b2.j((byte)0));
        this.p.a.a((int)16).h().a = "lv" + b2.s();
        this.p.a.a((int)18).h().m.a(94 + b2.j((byte)1));
        if (n3 == n2) {
            this.F = 0;
            this.G = 0;
            this.k = 0;
            return true;
        }
        return false;
    }

    public final void b(b b2) {
        int n2;
        for (n2 = 0; n2 < 6; ++n2) {
            if (this.p.a.a((int)(n2 + 32)).h().m == null) {
                this.p.a.a((int)(n2 + 32)).h().m = new m();
                this.p.a.a((int)(n2 + 32)).h().m.a = (byte)2;
                this.p.a.a((int)(n2 + 32)).h().m.a(0);
                this.p.a.a((int)(n2 + 32)).h().m.a(325, false, (byte)0);
            }
            if (this.p.a.a((int)(n2 + 49)).h().m == null) {
                this.p.a.a((int)(n2 + 49)).h().m = new m();
                this.p.a.a((int)(n2 + 49)).h().m.a = (byte)2;
                this.p.a.a((int)(n2 + 49)).h().m.a(145);
                this.p.a.a((int)(n2 + 49)).h().m.a(257, false, (byte)0);
            }
            this.p.a.a((int)(n2 + 49)).h().m.a(145);
            this.p.a.a((int)(n2 + 32)).h().m.a(0);
        }
        for (n2 = 0; n2 < 3; ++n2) {
            if (b2.x[0][n2] != -1 && b2.v[b2.x[0][n2]][0] > 0) {
                this.p.a.a((int)(49 + this.k)).h().m.a(134 + b2.v[b2.x[0][n2]][0]);
                this.p.a.a((int)(32 + this.k)).h().m.a(b2.x[0][n2] + 12);
                ++this.k;
            }
            if (b2.x[1][n2] == -1 || b2.w[b2.x[1][n2]][0] <= 0) continue;
            this.p.a.a((int)(49 + this.k)).h().m.a(134 + b2.w[b2.x[1][n2]][0]);
            this.p.a.a((int)(32 + this.k)).h().m.a(b2.x[1][n2] + 1);
            ++this.k;
        }
    }

    public final void af() {
        this.a = 0;
        this.y = null;
        this.p.a("/data/ui/battle.ui");
    }

    private void a(boolean bl) {
        this.p.a.a(20 + this.a).a(bl);
    }

    public final void ag() {
        ((al)this.p.a.a((int)0)).b.f = this.a;
        this.a(true);
    }

    public final void c(b b2) {
        this.f = 0;
        this.a(b2, false);
        this.ag();
    }

    public final void d(b b2) {
        ((d)this.o).l();
        if (!an.b(this.a, 1) && this.f == 0 && !this.j() && this.o.k(16400)) {
            this.p.a.b(2);
        } else if (!an.b(this.a, 1) && this.f == 0 && !this.j() && this.o.k(32832)) {
            this.p.a.b(3);
        } else if (!this.j() && this.o.k(196640)) {
            switch (this.a) {
                case 0: {
                    this.a(false);
                    this.o.a((byte)3);
                    break;
                }
                case 2: {
                    if (this.f == 0) {
                        if (b2.p(2)) {
                            this.p.a("/data/ui/msgwarm.ui", 257, this);
                            this.a("Tr\u1ea1ng th\u00e1i b\u1ecb qu\u1ea5n, kh\u00f4ng th\u1ec3 s\u1eed d\u1ee5ng \u0111\u1ea1o c\u1ee5", "Nh\u1ea5n n\u00fat 5 \u0111\u1ec3 ti\u1ebfp t\u1ee5c");
                            this.f = 1;
                            break;
                        }
                        this.a(false);
                        this.o.a((byte)4);
                        break;
                    }
                    this.p.a("/data/ui/msgwarm.ui");
                    this.f = 0;
                    break;
                }
                case 3: {
                    if (this.f == 0) {
                        if (b2.p(2)) {
                            this.p.a("/data/ui/msgwarm.ui", 257, this);
                            this.a("Tr\u1ea1ng th\u00e1i b\u1ecb qu\u1ea5n, kh\u00f4ng th\u1ec3 \u0111\u1ed5i s\u1ee7ng v\u1eadt", "Nh\u1ea5n n\u00fat 5 \u0111\u1ec3 ti\u1ebfp t\u1ee5c");
                            this.f = 1;
                            break;
                        }
                        this.a(false);
                        ((d)this.o).g = ((d)this.o).e[((d)this.o).i];
                        game.d.a().k = true;
                        this.o.a((byte)5);
                        break;
                    }
                    this.p.a("/data/ui/msgwarm.ui");
                    this.f = 0;
                    break;
                }
                case 1: {
                    if (((d)this.o).b == 2) {
                        this.b("Tr\u1eadn chi\u1ebfn n\u00e0y kh\u00f4ng cho b\u1eaft s\u1ee7ng v\u1eadt");
                        break;
                    }
                    if (this.q.y() == 2) {
                        this.b("Kh\u00f4ng gian kh\u00f4ng \u0111\u1ee7, kh\u00f4ng c\u00e1ch n\u00e0o b\u1eaft \u0111\u01b0\u1ee3c");
                        break;
                    }
                    this.b = 0;
                    this.a(false);
                    ((d)this.o).m();
                    this.o.a((byte)21);
                    break;
                }
                case 4: {
                    this.a(false);
                    this.o.a((byte)11);
                    break;
                }
                case 5: {
                    if (this.f == 0) {
                        if (b2.p(2)) {
                            this.p.a("/data/ui/msgwarm.ui", 257, this);
                            this.a("Tr\u1ea1ng th\u00e1i b\u1ecb qu\u1ea5n, kh\u00f4ng th\u1ec3 ch\u1ea1y tr\u1ed1n", "Nh\u1ea5n n\u00fat 5 \u0111\u1ec3 ti\u1ebfp t\u1ee5c");
                            this.f = 1;
                            break;
                        }
                        if (((d)this.o).b > 0 || !game.c.j) {
                            this.a(false);
                            this.f = 3;
                            this.b("Tr\u1eadn chi\u1ebfn n\u00e0y kh\u00f4ng th\u1ec3 tr\u1ed1n ch\u1ea1y");
                            break;
                        }
                        boolean bl = false;
                        if (((d)this.o).h.s() > ((d)this.o).d[0].s()) {
                            bl = true;
                        } else if (((d)this.o).h.s() == ((d)this.o).d[0].s()) {
                            if (ae.a(100) <= 95) {
                                bl = true;
                            }
                        } else {
                            int n2 = ((d)this.o).d[0].s() - ((d)this.o).h.s();
                            if ((n2 = 95 - n2 * 10) <= 15) {
                                n2 = 15;
                            }
                            if (ae.a(100) < n2) {
                                bl = true;
                            }
                        }
                        if (bl) {
                            this.a(false);
                            game.i.a().a((byte)10);
                            break;
                        }
                        this.f = 2;
                        this.b("Ch\u1ea1y tr\u1ed1n th\u1ea5t b\u1ea1i");
                        break;
                    }
                    this.p.a("/data/ui/msgwarm.ui");
                    this.f = 0;
                }
            }
        }
        this.f();
        if (this.f >= 2 && this.ax()) {
            if (this.f == 2) {
                ((d)this.o).h.J = true;
                ((d)this.o).i = (byte)(((d)this.o).i + 1);
                this.o.a((byte)1);
            } else {
                this.a(true);
            }
            this.f = 0;
        }
    }

    public final void e(b b2) {
        this.p.a("/data/ui/choiceskill.ui", 257, this);
        ((al)this.p.a.a((int)0)).a.a = b2.E();
        if (this.e >= b2.E()) {
            this.e = b2.E() - 1;
        }
        if (b2.E() > 5) {
            ((al)this.p.a.a((int)0)).a.a(1);
        } else {
            ((al)this.p.a.a((int)0)).a.a(-1);
        }
        this.p.a.a((int)5).h().a = "S\u1eed d\u1ee5ng";
        ((al)this.p.a.a((int)0)).a.f = this.e;
        this.h(b2);
        this.f = 0;
    }

    private void h(b b2) {
        this.w = ((al)this.p.a.a((int)0)).a.e;
        this.h = ((al)this.p.a.a((int)0)).a.f;
        int n2 = b2.E();
        for (int i2 = 0; i2 < 5; ++i2) {
            if (i2 >= n2) {
                this.p.a.a((int)(13 + i2 * 5)).h().a = "";
                this.p.a.a((int)(14 + i2 * 5)).h().a = "";
                continue;
            }
            this.p.a.a((int)(13 + i2 * 5)).h().a = an.f(aq.c[1][b2.t(this.w + i2)][1]);
            this.p.a.a((int)(14 + i2 * 5)).h().a = b2.y[this.w + i2] + "/" + aq.c[1][b2.t(this.w + i2)][5];
        }
        this.h(b2.z[this.e]);
        this.p.a.a(51).b(98 + this.h * 72 / n2, this.p.a.a());
    }

    private void h(int n2) {
        this.p.a.a((int)53).h().a = an.f(aq.c[1][n2][2]);
    }

    public final void f(b b2) {
        if (this.ay()) {
            if (this.f == 0 && this.o.k(4100)) {
                this.p.a.b(0);
                this.h(b2);
            } else if (this.f == 0 && this.o.k(8448)) {
                this.p.a.b(1);
                this.h(b2);
            } else if (this.o.k(196640)) {
                if (this.f == 0) {
                    if (b2.s(this.e)) {
                        this.p.a("/data/ui/choiceskill.ui");
                        ((d)this.o).b(b2.z[this.e]);
                        int n2 = ((d)this.o).a;
                        ((d)this.o).getClass();
                        if (n2 == 0) {
                            ((d)this.o).i();
                        } else {
                            this.o.a((byte)6);
                        }
                    } else {
                        this.f = 1;
                        this.p.a("/data/ui/msgwarm.ui", 257, this);
                        this.a("K\u1ef9 n\u0103ng gi\u00e1 tr\u1ecb ch\u01b0a \u0111\u1ee7", "Nh\u1ea5n n\u00fat 5 \u0111\u1ec3 ti\u1ebfp t\u1ee5c");
                    }
                } else {
                    this.f = 0;
                    this.p.a("/data/ui/msgwarm.ui");
                    if (b2.p(2) && b2.r() == 0) {
                        boolean bl = false;
                        for (int i2 = 0; i2 < b2.y.length; ++i2) {
                            if (b2.y[i2] == 0) continue;
                            bl = true;
                        }
                        if (!bl) {
                            this.p.a("/data/ui/choiceskill.ui");
                            this.c("Kh\u00f4ng c\u00f3 k\u1ef9 n\u0103ng gi\u00e1 tr\u1ecb, kh\u00f4ng c\u00e1ch n\u00e0o chi\u1ebfn \u0111\u1ea5u");
                            ((d)this.o).h();
                        }
                    }
                }
            } else if (this.o.k(262144) && this.f == 0) {
                this.p.a("/data/ui/choiceskill.ui");
                this.o.a((byte)20);
            }
        }
        this.g();
    }

    public final void ah() {
        this.f = 0;
        this.p.a("/data/ui/choice.ui", 257, this);
        this.p.a.a((int)8).h().a = "Pokemon ball";
        this.p.a.a((int)9).h().a = "T\u1ec9 l\u1ec7 b\u1eaft";
        this.p.a.a((int)5).h().a = "S\u1eed d\u1ee5ng";
        ((al)this.p.a.a((int)0)).a.f = this.b;
        ((al)this.p.a.a((int)0)).a.a(0);
        ((al)this.p.a.a((int)0)).a.a = this.q.K.size();
        for (int i2 = 0; i2 < this.q.K.size(); ++i2) {
            int[] nArray = (int[])this.q.K.elementAt(i2);
            if (this.p.a.a((int)(i2 + 54)).h().m == null) {
                this.p.a.a((int)(i2 + 54)).h().m = new m();
                this.p.a.a((int)(i2 + 54)).h().m.a(0);
                this.p.a.a((int)(i2 + 54)).h().m.a = (byte)2;
                this.p.a.a((int)(i2 + 54)).h().m.a(258, false, (byte)-1);
            }
            this.p.a.a((int)(i2 + 54)).h().m.a(aq.c[4][nArray[0]][1]);
            this.p.a.a((int)(13 + i2 * 5)).h().a = an.f(aq.c[4][nArray[0]][0]);
            this.p.a.a((int)(14 + i2 * 5)).h().a = ((d)this.o).b(nArray[0]) + "%";
        }
        this.p.a.a(59).a(false);
        this.p.a.a(60).a(false);
        this.bn();
    }

    private void bn() {
        int[] nArray = (int[])this.q.K.elementAt(this.b);
        this.p.a.a((int)53).h().a = "S\u1ed1 l\u01b0\u1ee3ng: " + nArray[1] + " c\u00e1i ";
    }

    public final void ai() {
        this.o.l();
        if (!an.b(this.b, 0) && this.f == 0 && this.o.k(4100) && !this.j()) {
            this.p.a.b(0);
            this.bn();
        } else if (!an.b(this.b, 0) && this.f == 0 && this.o.k(8448) && !this.j()) {
            this.p.a.b(1);
            this.bn();
        } else if (this.o.k(196640) && !this.j() && an.I()) {
            if (an.H() && !an.b(this.b, 0)) {
                return;
            }
            if (this.f == 0) {
                int[] nArray = (int[])this.q.K.elementAt(this.b);
                if (!this.q.b(nArray[0], 1, (byte)0)) {
                    this.p.a("/data/ui/msgwarm.ui", 257, this);
                    this.a("S\u1ed1 l\u01b0\u1ee3ng Pokemon ball kh\u00f4ng \u0111\u1ee7", "Nh\u1ea5n n\u00fat 5 \u0111\u1ec3 ti\u1ebfp t\u1ee5c");
                    this.f = 1;
                } else {
                    this.f = 0;
                    game.d.l = (byte)nArray[0];
                    this.o.m();
                    this.q.d(nArray[0], 1, (byte)0);
                    this.o.a((byte)17);
                    this.p.a("/data/ui/choice.ui");
                }
            } else if (this.f == 1) {
                if (an.X && ((int[])this.q.K.elementAt(this.b))[0] == 0) {
                    this.p.a("/data/ui/choice.ui");
                    this.o.a((byte)101);
                }
                this.f = 0;
                this.p.a("/data/ui/msgwarm.ui");
            }
        } else if (game.c.J() && this.f == 0 && this.o.k(262144) && !this.j()) {
            this.p.a("/data/ui/choice.ui");
            this.o.a((byte)20);
        }
        this.g();
    }

    public final void aj() {
        this.s = 0;
        this.f = 0;
        this.b = 0;
        this.p.a("/data/ui/choice.ui", 257, this);
        this.p.a.a((int)8).h().a = "\u0110\u1ea1o c\u1ee5";
        this.p.a.a((int)9).h().a = "S\u1ed1 l\u01b0\u1ee3ng";
        this.p.a.a((int)5).h().a = "S\u1eed d\u1ee5ng";
        this.p.a.a(59).a(false);
        this.p.a.a(60).a(false);
        this.be();
    }

    public final void ak() {
        if (this.f == 0 && this.o.k(4100)) {
            this.p.a.b(0);
            return;
        }
        if (this.f == 0 && this.o.k(8448)) {
            this.p.a.b(1);
            return;
        }
        if (this.o.k(196640)) {
            if (this.q.J.size() <= 0) {
                return;
            }
            this.s = ((int[])this.q.J.elementAt(this.h))[0];
            if (this.f == 0) {
                switch (aq.c[4][this.s][5]) {
                    case 7: 
                    case 8: 
                    case 9: 
                    case 10: {
                        this.p.a("/data/ui/msgwarm.ui", 257, this);
                        this.a("Trong chi\u1ebfn \u0111\u1ea5u kh\u00f4ng th\u1ec3 s\u1eed d\u1ee5ng", "Nh\u1ea5n n\u00fat 5 \u0111\u1ec3 ti\u1ebfp t\u1ee5c");
                        this.f = 1;
                        return;
                    }
                }
                this.o.a((byte)16);
                this.p.a("/data/ui/choice.ui");
                return;
            }
            if (this.f == 1) {
                this.p.a("/data/ui/msgwarm.ui");
                this.f = 0;
                return;
            }
        } else if (this.f == 0 && this.o.k(262144)) {
            this.p.a("/data/ui/choice.ui");
            this.o.a((byte)20);
        }
    }

    private void bo() {
        if (this.f == 0) {
            this.f = 1;
            int n2 = this.o instanceof k ? this.q.z[this.c].x(this.s) : this.q.z[((d)this.o).f[this.c]].x(this.s);
            switch (n2) {
                case 0: {
                    this.p.a("/data/ui/msgwarm.ui", 257, this);
                    this.a("S\u1ee7ng v\u1eadt n\u00e0y \u0111\u00e3 t\u1eed vong, kh\u00f4ng th\u1ec3 s\u1eed d\u1ee5ng", "Nh\u1ea5n n\u00fat 5 \u0111\u1ec3 ti\u1ebfp t\u1ee5c");
                    return;
                }
                case 1: {
                    this.p.a("/data/ui/msgwarm.ui", 257, this);
                    this.a("S\u1ee7ng v\u1eadt n\u00e0y kh\u00f4ng c\u00f3, kh\u00f4ng th\u1ec3 s\u1eed d\u1ee5ng", "Nh\u1ea5n n\u00fat 5 \u0111\u1ec3 ti\u1ebfp t\u1ee5c");
                    return;
                }
                case 2: {
                    this.p.a("/data/ui/msgwarm.ui", 257, this);
                    this.a("M\u00e1u \u0111\u1ea7y, kh\u00f4ng c\u1ea7n s\u1eed d\u1ee5ng", "Nh\u1ea5n n\u00fat 5 \u0111\u1ec3 ti\u1ebfp t\u1ee5c");
                    return;
                }
                case 3: {
                    this.p.a("/data/ui/msgwarm.ui", 257, this);
                    this.a("K\u1ef9 n\u0103ng gi\u00e1 tr\u1ecb \u0111\u00e3 \u0111\u1ea7y, kh\u00f4ng c\u1ea7n s\u1eed d\u1ee5ng", "Nh\u1ea5n n\u00fat 5 \u0111\u1ec3 ti\u1ebfp t\u1ee5c");
                    return;
                }
                case 4: {
                    this.p.a("/data/ui/msgwarm.ui", 257, this);
                    this.a("Tr\u00ean ng\u01b0\u1eddi \u0111\u1ec1u b\u1ecb l\u1ee3i hi\u1ec7u qu\u1ea3", "Nh\u1ea5n n\u00fat 5 \u0111\u1ec3 ti\u1ebfp t\u1ee5c");
                    return;
                }
                case 5: {
                    this.p.a("/data/ui/msgwarm.ui", 257, this);
                    this.a("Trong h\u01b0ng ph\u1ea5n, kh\u00f4ng th\u1ec3 d\u00f9ng", "Nh\u1ea5n n\u00fat 5 \u0111\u1ec3 ti\u1ebfp t\u1ee5c");
                    return;
                }
                case 7: {
                    this.p.a("/data/ui/msgwarm.ui", 257, this);
                    this.a("M\u00e1u v\u00e0 k\u1ef9 n\u0103ng \u0111\u1ec1u \u0111\u00e3 \u0111\u1ea7y, kh\u00f4ng c\u1ea7n s\u1eed d\u1ee5ng", "Nh\u1ea5n n\u00fat 5 \u0111\u1ec3 ti\u1ebfp t\u1ee5c");
                    return;
                }
                case 8: {
                    this.p.a("/data/ui/msgwarm.ui", 257, this);
                    this.a("S\u1ee7ng v\u1eadt \u0111\u00e3 ch\u1ebft, kh\u00f4ng th\u1ec3 s\u1eed d\u1ee5ng", "Nh\u1ea5n n\u00fat 5 \u0111\u1ec3 ti\u1ebfp t\u1ee5c");
                    return;
                }
            }
            if (this.q.b(this.s, 1, (byte)0)) {
                if (this.o instanceof k) {
                    this.q.z[this.c].w(this.s);
                } else {
                    ((d)this.o).h.J = true;
                    this.q.z[((d)this.o).f[this.c]].w(this.s);
                }
                this.e(this.c);
                this.f = 1;
                this.l = true;
                this.p.a("/data/ui/msgwarm.ui", 257, this);
                this.a("Th\u00e0nh c\u00f4ng s\u1eed d\u1ee5ng \u0111\u1ea1o c\u1ee5", "Nh\u1ea5n n\u00fat 5 \u0111\u1ec3 ti\u1ebfp t\u1ee5c");
                return;
            }
            this.f = 2;
            this.E();
            this.a("\u0110\u00e3 kh\u00f4ng c\u00f3 \u0111\u1ea1o n\u00e0y c\u1ee5, th\u1ec9nh mua s\u1eafm", "Nh\u1ea5n n\u00fat 5 \u0111\u1ec3 ti\u1ebfp t\u1ee5c");
            return;
        }
        if (this.f == 1) {
            this.f = 0;
            this.p.a("/data/ui/msgwarm.ui");
            return;
        }
        if (this.f == 2) {
            this.f = 0;
            this.p.a("/data/ui/msgwarm.ui");
            this.p.a("/data/ui/petstate.ui");
            if (this.o instanceof k) {
                this.o.a((byte)8);
                return;
            }
            if (game.d.a().h.equals(((d)this.o).c(this.c))) {
                this.g(((d)this.o).d(this.c));
            }
            if (((d)this.o).h.J) {
                ((d)this.o).i = (byte)(((d)this.o).i + 1);
                ((d)this.o).a((byte)1);
                return;
            }
            ((d)this.o).a((byte)4);
        }
    }

    public final void al() {
        if (this.f == 0 && this.o.k(4100)) {
            this.p.a.b(0);
            return;
        }
        if (this.f == 0 && this.o.k(8448)) {
            this.p.a.b(1);
            return;
        }
        if (this.o.k(196640)) {
            this.bo();
            return;
        }
        if (this.f == 0 && this.o.k(262144)) {
            if (this.l) {
                if (game.d.a().h.equals(((d)this.o).c(this.c))) {
                    this.g(((d)this.o).d(this.c));
                }
                if (((d)this.o).h.J) {
                    ((d)this.o).i = (byte)(((d)this.o).i + 1);
                    ((d)this.o).a((byte)1);
                } else {
                    ((d)this.o).a((byte)4);
                }
                this.p.a("/data/ui/petstate.ui");
                return;
            }
            ((d)this.o).a((byte)4);
            this.p.a("/data/ui/petstate.ui");
        }
    }

    public final void a(int n2, int n3) {
        b b2;
        if (this.i >= game.d.j.size()) {
            this.i = 0;
            game.i.a().a((byte)10);
            return;
        }
        block0: while (true) {
            b2 = (b)game.d.j.elementAt(this.i);
            while (this.i < game.d.j.size() && b2.t()) {
                ++this.i;
                if (this.i >= game.d.j.size()) continue;
                continue block0;
            }
            break;
        }
        this.H = n2;
        this.I = n3;
        b2.c();
        b2.b(n2, n3);
        this.x = 0;
    }

    public final void am() {
        block20: {
            block19: {
                int n2;
                int n3;
                b b2;
                block18: {
                    if (this.i >= game.d.j.size()) {
                        this.i = 0;
                        game.i.a().a((byte)10);
                        return;
                    }
                    if (this.x <= 0) {
                        this.J += 8;
                    }
                    b2 = (b)game.d.j.elementAt(this.i);
                    n3 = b2.A() + this.J;
                    int n4 = b2.u();
                    n2 = b2.z();
                    if (n3 >= n4) {
                        n3 = n4;
                    } else if (n3 >= n2) {
                        n3 = n2;
                    }
                    if (this.o.k(196640)) {
                        if (n2 >= n4) {
                            n3 = n4;
                            this.p.a.a((int)40).h().a = n3 + "/" + n3;
                            this.p.a.a((int)9).h().a = "#P" + b2.v(n3);
                            b2.j(0);
                            this.x = 0;
                            ((d)this.o).a((byte)22);
                            return;
                        }
                        if (n3 >= n2) {
                            this.p.a.a((int)40).h().a = n2 + "/" + b2.u();
                            this.p.a.a((int)9).h().a = "#P" + b2.v(n2);
                            b2.j(n3);
                            ++this.i;
                            while (this.i < game.d.j.size() && ((b)game.d.j.elementAt(this.i)).t()) {
                                ++this.i;
                            }
                            if (this.i >= game.d.j.size()) {
                                this.i = 0;
                                game.i.a().a((byte)10);
                            } else {
                                ((b)game.d.j.elementAt(this.i)).b(this.H, this.I);
                            }
                            this.x = 0;
                            this.J = 0;
                            return;
                        }
                        this.J = 0;
                        n3 = n2;
                        b2.j(n3);
                        this.p.a.a((int)40).h().a = n3 + "/" + b2.u();
                        this.p.a.a((int)9).h().a = "#P" + b2.v(n3);
                        return;
                    }
                    this.p.a.a((int)40).h().a = n3 + "/" + b2.u();
                    this.p.a.a((int)9).h().a = "#P" + b2.v(n3);
                    b b3 = b2;
                    h h2 = this;
                    this.p.a.a((int)12).h().a = an.f(b3.j((byte)0));
                    h2.p.a.a((int)13).h().a = "lv" + b3.s();
                    h2.p.a.a((int)17).h().m.a(94 + b3.j((byte)1));
                    if (n3 < n4) break block18;
                    b2.j(0);
                    ((d)this.o).a((byte)22);
                    break block19;
                }
                if (n3 < n2) break block20;
                ++this.x;
                b2.j(n3);
                if (this.x >= 10) {
                    ++this.i;
                    while (this.i < game.d.j.size() && ((b)game.d.j.elementAt(this.i)).t()) {
                        ++this.i;
                    }
                    if (this.i >= game.d.j.size()) {
                        this.i = 0;
                        game.i.a().a((byte)10);
                    } else {
                        ((b)game.d.j.elementAt(this.i)).b(this.H, this.I);
                    }
                    this.x = 0;
                }
            }
            this.J = 0;
        }
    }

    public final void an() {
        byte by;
        int n2;
        b b2 = (b)game.d.j.elementAt(this.i);
        String[] stringArray = new String[4];
        for (n2 = 0; n2 < 4; ++n2) {
            by = (byte)(n2 + 1);
            stringArray[n2] = "" + b2.c[by];
        }
        b2.v();
        this.g(b2);
        this.p.a("/data/ui/levelUp.ui", 257, this);
        for (n2 = 0; n2 < 4; ++n2) {
            this.p.a.a((int)(n2 + 19)).h().a = stringArray[n2];
        }
        if (b2.E() < 5 && b2.E() < b2.s() / 10 + 1) {
            this.y = b2.F();
            this.p.a.a((int)51).h().a = "C\u00f3 th\u1ec3 h\u1ecdc t\u1eadp k\u1ef9 n\u0103ng m\u1edbi";
        } else {
            this.p.a.a((int)51).h().a = "";
        }
        this.p.a.a((int)38).h().a = an.f(aq.c[0][b2.q()][0]);
        this.p.a.a((int)40).h().a = "" + b2.s();
        if (this.p.a.a((int)10).h().m == null) {
            this.p.a.a((int)10).h().m = new m();
            this.p.a.a((int)10).h().m.a = (byte)3;
            this.p.a.a((int)10).h().m.a(0);
            this.p.a.a((int)10).h().m.a(b2.C, false, (byte)-1);
        }
        for (n2 = 0; n2 < 4; ++n2) {
            by = (byte)(n2 + 1);
            this.p.a.a((int)(n2 + 31)).h().a = "" + b2.c[by];
        }
    }

    public final void ao() {
        ++this.K;
        if (this.K > 40) {
            this.K = 0;
            if (this.y != null) {
                ((d)this.o).a((byte)23);
            } else if (this.i + 1 >= game.d.j.size()) {
                if (((b)game.d.j.elementAt(this.i)).z() > 0) {
                    this.o.a((byte)8);
                } else {
                    this.i = 0;
                    game.i.a().a((byte)10);
                }
                this.p.a("/data/ui/levelUp.ui");
            } else {
                this.o.a((byte)8);
                this.p.a("/data/ui/levelUp.ui");
            }
        }
        if (this.o.k(196640)) {
            this.K = 0;
            if (this.y != null) {
                this.o.a((byte)23);
                return;
            }
            if (this.i + 1 >= game.d.j.size()) {
                if (((b)game.d.j.elementAt(this.i)).z() > 0) {
                    this.o.a((byte)8);
                } else {
                    this.i = 0;
                    game.i.a().a((byte)10);
                }
                this.p.a("/data/ui/levelUp.ui");
                return;
            }
            this.o.a((byte)8);
            this.p.a("/data/ui/levelUp.ui");
        }
    }

    public final void ap() {
        this.p.a("/data/ui/choiceskill.ui", 257, this);
        this.p.a("/data/ui/levelUp.ui");
        this.b = 0;
        this.f = 0;
        ((al)this.p.a.a((int)0)).a.a = this.y.length;
        if (this.y.length > 5) {
            ((al)this.p.a.a((int)0)).a.a(1);
        } else {
            ((al)this.p.a.a((int)0)).a.a(-1);
        }
        if (this.p.a.a((int)5).h().m == null) {
            this.p.a.a((int)5).h().m = new m();
            this.p.a.a((int)5).h().m.a = (byte)3;
            this.p.a.a((int)5).h().m.a(0);
            this.p.a.a((int)5).h().m.a(257, false, (byte)-1);
        }
        this.p.a.a((int)5).h().m.a((byte)11, (byte)-1);
        this.p.a.a(6).a(false);
        this.bp();
        if (!game.k.D) {
            this.b("C\u00f3 th\u1ec3 nh\u1ea5n #1n\u00fat m\u1ec1m tr\u00e1i#0 \u0111\u1ec3 h\u1ecdc t\u1eadp k\u1ef9 n\u0103ng");
            game.k.D = true;
        }
    }

    private void bp() {
        this.w = ((al)this.p.a.a((int)0)).a.e;
        this.h = ((al)this.p.a.a((int)0)).a.f;
        for (int i2 = 0; i2 < 5; ++i2) {
            if (i2 >= this.y.length) {
                this.p.a.a((int)(13 + i2 * 5)).h().a = "";
                this.p.a.a((int)(14 + i2 * 5)).h().a = "";
                continue;
            }
            this.p.a.a((int)(13 + i2 * 5)).h().a = an.f(aq.c[1][this.y[this.w + i2]][1]);
            this.p.a.a((int)(14 + i2 * 5)).h().a = "" + aq.c[1][this.y[this.w + i2]][5];
        }
        this.h(this.y[this.h]);
        this.p.a.a(51).b(98 + this.h * 62 / this.y.length, this.p.a.a());
    }

    public final void aq() {
        if (!this.j() && this.o.k(4100) && this.f == 0) {
            this.p.a.b(0);
            this.bp();
        } else if (!this.j() && this.o.k(8448) && this.f == 0) {
            this.p.a.b(1);
            this.bp();
        } else if (!this.j() && this.f == 0 && (this.o.k(131072) || this.o.Q()) || this.f == 1 && this.o.k(196640)) {
            if (this.f == 0) {
                this.f = 1;
                this.p.a("/data/ui/msgwarm.ui", 257, this);
                this.a("H\u1ecdc t\u1eadp" + an.f(aq.c[1][this.y[this.b]][1]), "Nh\u1ea5n n\u00fat 5 \u0111\u1ec3 ti\u1ebfp t\u1ee5c");
            } else if (this.f == 1) {
                b b2 = (b)game.d.j.elementAt(this.i);
                b2.g((byte)this.y[this.h]);
                this.y = null;
                if (this.i + 1 >= game.d.j.size() && b2.z() <= 0) {
                    this.i = 0;
                    game.i.a().a((byte)10);
                } else {
                    this.o.a((byte)8);
                }
                this.p.a("/data/ui/msgwarm.ui");
                this.p.a("/data/ui/choiceskill.ui");
            }
        }
        this.f();
    }

    public final void ar() {
        this.f = 0;
        this.b("Ba l\u00f4 s\u1ee7ng v\u1eadt \u0111\u1ec1u th\u0103ng 5 c\u1ea5p");
    }

    /*
     * Enabled aggressive block sorting
     */
    public final void as() {
        block14: {
            h h2;
            block17: {
                block22: {
                    block21: {
                        block19: {
                            block20: {
                                block18: {
                                    block16: {
                                        block15: {
                                            if (this.f != 0) break block15;
                                            if (this.ax()) {
                                                this.f = 1;
                                                if (game.k.E.size() <= 0) {
                                                    this.o.a((byte)14);
                                                }
                                            }
                                            break block14;
                                        }
                                        if (this.f != 1) break block16;
                                        this.p.a("/data/ui/bodyShop.ui");
                                        this.bq();
                                        this.g = true;
                                        break block14;
                                    }
                                    h2 = this;
                                    if (h2.f < 3) break block17;
                                    if (h2.f != 5) break block18;
                                    h2.f = 6;
                                    h2.H();
                                    h2.a("\u0110ang l\u01b0u...");
                                    h2.J();
                                    break block19;
                                }
                                if (h2.f != 6) break block20;
                                game.k.G = (byte)2;
                                game.k.h();
                                if (((k)h2.o).j()) {
                                    h2.a("L\u01b0u th\u00e0nh c\u00f4ng");
                                    h2.f = 7;
                                }
                                break block19;
                            }
                            if (h2.f != 7) break block19;
                            h2.p.a("/data/ui/msgtip.ui");
                            h2.f = 0;
                            if (h2.o.Q == 14) {
                                h2.o.a((byte)14);
                                break block14;
                            } else {
                                h2.o.a((byte)0);
                            }
                            break block14;
                        }
                        if (h2.j() || !h2.o.k(4100) || h2.f != 3) break block21;
                        h2.p.a.b(0);
                        h2.bp();
                        break block14;
                    }
                    if (h2.j() || !h2.o.k(8448) || h2.f != 3) break block22;
                    h2.p.a.b(1);
                    h2.bp();
                    break block14;
                }
                if ((h2.j() || h2.f != 3 || !h2.o.k(131072) && !h2.o.Q()) && (h2.f != 4 || !h2.o.k(196640))) break block14;
                if (h2.f == 3) {
                    h2.f = 4;
                    h2.p.a("/data/ui/msgwarm.ui", 257, h2);
                    h2.a("H\u1ecdc t\u1eadp" + an.f(aq.c[1][h2.y[h2.h]][1]), "Nh\u1ea5n n\u00fat 5 \u0111\u1ec3 ti\u1ebfp t\u1ee5c");
                    break block14;
                } else if (h2.f == 4) {
                    ((b)game.k.E.elementAt(h2.i)).g((byte)h2.y[h2.h]);
                    h2.y = null;
                    ++h2.i;
                    if (h2.i >= game.k.E.size()) {
                        h2.i = 0;
                        h2.f = 5;
                    } else {
                        h2.bq();
                    }
                    h2.p.a("/data/ui/msgwarm.ui");
                    h2.p.a("/data/ui/choiceskill.ui");
                }
                break block14;
            }
            if (h2.o.k(196640)) {
                h h3 = h2;
                h3.p.a("/data/ui/choiceskill.ui", 257, h3);
                h3.p.a("/data/ui/levelUp.ui");
                h3.b = 0;
                h3.f = 3;
                ((al)h3.p.a.a((int)0)).a.a = h3.y.length;
                if (h3.y.length > 5) {
                    ((al)h3.p.a.a((int)0)).a.a(1);
                } else {
                    ((al)h3.p.a.a((int)0)).a.a(-1);
                }
                if (h3.p.a.a((int)5).h().m == null) {
                    h3.p.a.a((int)5).h().m = new m();
                    h3.p.a.a((int)5).h().m.a = (byte)3;
                    h3.p.a.a((int)5).h().m.a(0);
                    h3.p.a.a((int)5).h().m.a(257, false, (byte)-1);
                }
                h3.p.a.a((int)5).h().m.a((byte)11, (byte)-1);
                h3.p.a.a(6).a(false);
                h3.bp();
                h2.g = true;
            }
        }
        this.f();
    }

    private void bq() {
        int n2;
        this.f = 2;
        b b2 = (b)game.k.E.elementAt(this.i);
        this.p.a("/data/ui/levelUp.ui", 257, this);
        for (n2 = 0; n2 < 4; ++n2) {
            this.p.a.a((int)(n2 + 19)).h().a = "" + b2.i((int)((byte)(n2 + 1 - 1)));
        }
        if (b2.E() < 5 && b2.E() < b2.s() / 10 + 1) {
            this.y = b2.F();
            this.p.a.a((int)51).h().a = "Nh\u1ea5n n\u00fat 5 h\u1ecdc t\u1eadp k\u1ef9 n\u0103ng m\u1edbi";
        } else {
            this.p.a.a((int)51).h().a = "";
        }
        this.p.a.a((int)38).h().a = an.f(aq.c[0][b2.q()][0]);
        this.p.a.a((int)40).h().a = "" + b2.s();
        if (this.p.a.a((int)10).h().m == null) {
            this.p.a.a((int)10).h().m = new m();
            this.p.a.a((int)10).h().m.a = (byte)3;
            this.p.a.a((int)10).h().m.a(0);
            this.p.a.a((int)10).h().m.a(b2.C, false, (byte)-1);
        }
        for (n2 = 0; n2 < 4; ++n2) {
            byte by = (byte)(n2 + 1);
            this.p.a.a((int)(n2 + 31)).h().a = "" + b2.c[by];
        }
    }

    public final void at() {
        this.p.a("/data/ui/npcEnemy.ui", 296, this);
        if (this.p.a.a((int)1).h().m == null) {
            this.p.a.a((int)1).h().m = new m();
            this.p.a.a((int)1).h().m.a = (byte)2;
            this.p.a.a((int)1).h().m.a(296, false, (byte)0);
            this.p.a.a((int)1).h().m.a(0);
        }
        this.p.a.a(36).a(false);
    }

    private void a(int n2, int n3, int n4) {
        if (n4 != -1 && this.p.a.a((int)n4).h().m != null) {
            this.p.a.a(n4).a(false);
        }
        if (this.p.a.a((int)n2).h().m == null) {
            this.p.a.a((int)n2).h().m = new m();
            this.p.a.a((int)n2).h().m.a = (byte)2;
            this.p.a.a((int)n2).h().m.a(296, false, (byte)0);
            this.p.a.a((int)n2).h().m.a(0);
        }
        this.p.a.a((int)n2).h().m.a(n3);
    }

    public final void b(int n2, int n3) {
        switch (n2) {
            case 0: {
                this.p.a.a((int)1).h().m.a(n3);
                return;
            }
            case 1: {
                for (n2 = 2; n2 < 4; ++n2) {
                    if (this.p.a.a((int)n2).h().m == null) {
                        this.p.a.a((int)n2).h().m = new m();
                        this.p.a.a((int)n2).h().m.a = (byte)2;
                        this.p.a.a((int)n2).h().m.a(0);
                    }
                    if (n2 % 2 == 1) {
                        this.p.a.a((int)n2).h().m.a(0, false, (byte)-1);
                    } else if (game.k.u == -1) {
                        if (game.k.v == -1) {
                            this.p.a.a((int)n2).h().m.a(game.k.a().d[8].a.a, false, (byte)-1);
                        } else {
                            this.p.a.a((int)n2).h().m.a(game.k.a().d[game.k.v].a.a, false, (byte)-1);
                        }
                    } else {
                        this.p.a.a((int)n2).h().m.a(game.k.a().d[game.k.u].a.a, false, (byte)-1);
                    }
                    this.p.a.a((int)n2).h().m.a(1);
                }
                this.p.a.a((int)1).h().m.a(n3);
                return;
            }
            case 2: {
                for (n2 = 2; n2 < 4; ++n2) {
                    if (this.p.a.a((int)n2).h().m != null) {
                        this.p.a.a(n2).a(false);
                    }
                    if (this.p.a.a((int)(n2 + 32)).h().m == null) {
                        this.p.a.a((int)(n2 + 32)).h().m = new m();
                        this.p.a.a((int)(n2 + 32)).h().m.a = (byte)2;
                        this.p.a.a((int)(n2 + 32)).h().m.a(0);
                    }
                    if (n2 % 2 == 1) {
                        this.p.a.a((int)(n2 + 32)).h().m.a(0, false, (byte)-1);
                    } else if (game.k.u == -1) {
                        if (game.k.v == -1) {
                            this.p.a.a((int)(n2 + 32)).h().m.a(game.k.a().d[8].a.a, false, (byte)-1);
                        } else {
                            this.p.a.a((int)(n2 + 32)).h().m.a(game.k.a().d[game.k.v].a.a, false, (byte)-1);
                        }
                    } else {
                        this.p.a.a((int)(n2 + 32)).h().m.a(game.k.a().d[game.k.u].a.a, false, (byte)-1);
                    }
                    this.p.a.a((int)(n2 + 32)).h().m.a(1);
                }
                this.p.a.a((int)1).h().m.a(n3);
                return;
            }
            case 3: {
                for (n2 = 2; n2 < 4; ++n2) {
                    if (this.p.a.a((int)(n2 + 32)).h().m != null) {
                        this.p.a.a(n2 + 32).a(false);
                    }
                    if (this.p.a.a((int)(n2 + 2)).h().m == null) {
                        this.p.a.a((int)(n2 + 2)).h().m = new m();
                        this.p.a.a((int)(n2 + 2)).h().m.a = (byte)2;
                        this.p.a.a((int)(n2 + 2)).h().m.a(0);
                    }
                    if (n2 % 2 == 1) {
                        this.p.a.a((int)(n2 + 2)).h().m.a(0, false, (byte)-1);
                    } else if (game.k.u == -1) {
                        if (game.k.v == -1) {
                            this.p.a.a((int)(n2 + 2)).h().m.a(game.k.a().d[8].a.a, false, (byte)-1);
                        } else {
                            this.p.a.a((int)(n2 + 2)).h().m.a(game.k.a().d[game.k.v].a.a, false, (byte)-1);
                        }
                    } else {
                        this.p.a.a((int)(n2 + 2)).h().m.a(game.k.a().d[game.k.u].a.a, false, (byte)-1);
                    }
                    this.p.a.a((int)(n2 + 2)).h().m.a(1);
                }
                this.p.a.a((int)1).h().m.a(n3);
                this.L = game.d.a().j();
                this.M = this.q.A;
                if (n3 - 3 < this.L) {
                    this.a(6, 6, -1);
                }
                if (n3 - 3 >= this.M) break;
                this.a(18, 6, -1);
                return;
            }
            case 4: {
                if (n3 - 3 < this.L) {
                    this.a(6 + (n3 - 3 << 1), 6, 6 + (n3 - 4 << 1));
                } else {
                    this.a(6 + (n3 - 3 << 1), 5, 6 + (n3 - 4 << 1));
                }
                if (n3 - 4 < this.L) {
                    this.a(7 + (n3 - 4 << 1), 6, 6 + (n3 - 4 << 1));
                } else {
                    this.a(7 + (n3 - 4 << 1), 5, 6 + (n3 - 4 << 1));
                }
                if (n3 - 4 < this.M) {
                    this.a(19 + (n3 - 4 << 1), 6, 18 + (n3 - 4 << 1));
                } else {
                    this.a(19 + (n3 - 4 << 1), 5, 18 + (n3 - 4 << 1));
                }
                if (n3 - 3 < this.M) {
                    this.a(18 + (n3 - 3 << 1), 6, 18 + (n3 - 4 << 1));
                    return;
                }
                this.a(18 + (n3 - 3 << 1), 5, 18 + (n3 - 4 << 1));
                return;
            }
            case 5: {
                if (n3 - 4 < this.L) {
                    this.a(7 + (n3 - 4 << 1), 6, 6 + (n3 - 4 << 1));
                } else {
                    this.a(7 + (n3 - 4 << 1), 5, 6 + (n3 - 4 << 1));
                }
                if (n3 - 4 < this.M) {
                    this.a(19 + (n3 - 4 << 1), 6, 18 + (n3 - 4 << 1));
                    return;
                }
                this.a(19 + (n3 - 4 << 1), 5, 18 + (n3 - 4 << 1));
                return;
            }
            case 6: {
                this.a(30, 8, -1);
                this.a(31, 7, -1);
                return;
            }
            case 7: {
                this.a(32, 8, 30);
                this.a(33, 7, 31);
                return;
            }
            case 8: {
                this.p.a.a(36).a(true);
                return;
            }
            case 9: {
                this.p.a.a(36).a(false);
                return;
            }
            case 10: {
                this.a(1, 4, 32);
                this.a(1, 4, 33);
                for (n2 = 4; n2 < 6; ++n2) {
                    this.p.a.a(n2).a(false);
                }
                for (n2 = 7; n2 < 19; n2 += 2) {
                    this.p.a.a(n2).a(172 + 17 * (n2 - 7) / 2, this.p.a.a());
                    this.p.a.a(n2 + 12).a(-30 + 17 * (n2 - 7) / 2, this.p.a.a());
                }
                return;
            }
            case 11: {
                for (n2 = 4; n2 < 6; ++n2) {
                    this.p.a.a(n2).a(false);
                }
                for (n2 = 7; n2 < 19; n2 += 2) {
                    this.p.a.a(n2).a(false);
                    this.p.a.a(n2 + 12).a(false);
                }
                this.a(1, 0, -1);
            }
        }
    }

    private void e(String string) {
        this.t = string;
        if (this.p.a.a((int)1).h().m == null) {
            this.p.a.a((int)1).h().m = new m();
            this.p.a.a((int)1).h().m.a(0);
            this.p.a.a((int)1).h().m.a = (byte)3;
            this.p.a.a((int)1).h().m.a(257, false, (byte)-2);
        }
        this.p.a.a((int)1).h().m.a((byte)9, (byte)-2);
        this.v = 0;
    }

    public final void au() {
        this.p.a("/data/ui/openbox.ui", 257, this);
        this.e("Kh\u00f4ng c\u00f3 c\u00e1i ch\u00eca kh\u00f3a, c\u00f3 th\u1ec3 \u0111\u1ebfn t\u00e0i li\u1ec7u c\u1eeda h\u00e0ng mua s\u1eafm");
    }

    public final void av() {
        this.p.a("/data/ui/openbox.ui", 257, this);
        this.e("\u0110\u1ea1o c\u1ee5 \u0111\u00e3 \u0111\u1ee7");
    }

    public final void a(String string, int n2) {
        this.p.a("/data/ui/openbox.ui", 257, this);
        this.e(string + " x " + n2);
    }

    public final void b(String string) {
        this.p.a("/data/ui/openbox.ui", 257, this);
        this.e(string);
    }

    public final void aw() {
        if (this.p.b("/data/ui/openbox.ui")) {
            this.p.a("/data/ui/openbox.ui");
        }
    }

    public final boolean ax() {
        return !this.p.b("/data/ui/openbox.ui");
    }

    public final void c(String object) {
        this.p.a("/data/ui/taskTip.ui", 257, this);
        String string = object;
        object = this;
        this.t = string;
        if (((h)object).p.a.a((int)1).h().m == null) {
            ((h)object).p.a.a((int)1).h().m = new m();
            ((h)object).p.a.a((int)1).h().m.a(0);
            ((h)object).p.a.a((int)1).h().m.a = (byte)3;
            ((h)object).p.a.a((int)1).h().m.a(257, false, (byte)-2);
        }
        ((h)object).p.a.a((int)1).h().m.a((byte)10, (byte)-2);
        ((h)object).v = 0;
    }

    private void br() {
        if (this.p.b("/data/ui/taskTip.ui")) {
            this.p.a("/data/ui/taskTip.ui");
        }
    }

    public final boolean ay() {
        return !this.p.b("/data/ui/taskTip.ui");
    }

    public final void az() {
        this.c = 0;
        this.f = 0;
        this.p.a("/data/ui/bodyShop.ui", 257, this);
        this.bs();
    }

    private void bs() {
        Object object = "";
        switch (this.c) {
            case 0: {
                object = "T\u00f9y th\u1eddi mua s\u1eafm c\u00e1c lo\u1ea1i \u0111\u1ea1o c\u1ee5, gi\u00e0 tr\u1ebb kh\u00f4ng g\u1ea1t.";
                break;
            }
            case 1: {
                object = new int[]{2, 1, 2};
                object = an.f(602) + an.a(604, (int[])object);
                break;
            }
            case 2: {
                object = new int[]{2, 1, 2};
                object = an.f(603) + an.a(604, (int[])object);
                break;
            }
            case 3: {
                object = new int[]{2, 1, 2};
                object = an.f(601) + an.a(604, (int[])object);
            }
        }
        this.p.a.a((int)11).h().a = (String)object;
        if (this.c > 0) {
            this.o.d((byte)0);
            this.bt();
        }
    }

    private void bt() {
        switch (this.c) {
            case 1: {
                this.o.c((byte)3);
                return;
            }
            case 2: {
                this.o.c((byte)4);
                return;
            }
            case 3: {
                this.o.c((byte)2);
            }
        }
    }

    public final void aA() {
        block0 : switch (this.c) {
            case 0: {
                if (this.o.k(4100) && this.f == 0) {
                    this.p.a.b(0);
                    this.bs();
                    return;
                }
                if (this.o.k(8448) && this.f == 0) {
                    this.p.a.b(1);
                    this.bs();
                    return;
                }
                if (this.o.k(131072)) {
                    this.o.a((byte)26);
                    this.p.a("/data/ui/bodyShop.ui");
                    return;
                }
                if (!this.o.k(786432)) break;
                this.b = 0;
                this.o.a((byte)6);
                this.p.a("/data/ui/bodyShop.ui");
                return;
            }
            default: {
                switch (this.o.N()) {
                    case 0: {
                        if (this.o.k(4100) && this.f == 0) {
                            this.p.a.b(0);
                            this.bs();
                            return;
                        }
                        if (this.o.k(8448) && this.f == 0) {
                            this.p.a.b(1);
                            this.bs();
                            return;
                        }
                        if (this.f == 0 && this.o.k(131072) || this.f == 1 && this.o.k(65568)) {
                            if (this.f == 0) {
                                this.bt();
                                if (this.o.L() == 3) {
                                    int n2;
                                    if (game.k.H != null) {
                                        game.k.H.removeAllElements();
                                    }
                                    for (n2 = 0; n2 < game.g.o().A && game.g.o().z[n2].s() >= 50; ++n2) {
                                    }
                                    if (n2 >= game.g.o().A) {
                                        this.f = 1;
                                        this.p.a("/data/ui/msgwarm.ui", 257, this);
                                        this.a("Trong ba l\u00f4 s\u1ee7ng v\u1eadt \u0111\u1ec1u \u0111\u00e3 max level", "Nh\u1ea5n n\u00fat 5 \u0111\u1ec3 ti\u1ebfp t\u1ee5c");
                                        return;
                                    }
                                }
                                if (this.o.O() > 1) {
                                    this.o.d((byte)1);
                                    return;
                                }
                                this.o.g(1);
                                return;
                            }
                            this.f = 0;
                            this.p.a("/data/ui/msgwarm.ui");
                            return;
                        }
                        if (!this.o.k(786432) || this.f != 0) break block0;
                        this.b = 0;
                        this.o.a((byte)6);
                        this.p.a("/data/ui/bodyShop.ui");
                        return;
                    }
                    case 1: {
                        if (this.o.k(131072)) {
                            this.o.h(1);
                            return;
                        }
                        if (!this.o.k(262144)) break block0;
                        this.o.h(2);
                        return;
                    }
                    case 2: {
                        if (!this.bu() || !this.o.k(917504)) break block0;
                        if (this.o.M()) {
                            if (this.o.L() == 3) {
                                this.o.a((byte)25);
                            }
                            this.o.d((byte)5);
                        } else {
                            this.o.d((byte)1);
                        }
                        this.f = 0;
                        return;
                    }
                    case 3: {
                        if (!this.o.k(393216)) break block0;
                        this.o.h(1);
                    }
                }
            }
        }
    }

    private boolean bu() {
        if (this.f == 0) {
            this.f = 1;
            this.H();
            this.a("\u0110ang l\u01b0u...");
            this.J();
        } else if (this.f == 1) {
            if (this.o.L() == 3) {
                if (game.k.a().k()) {
                    this.a("L\u01b0u th\u00e0nh c\u00f4ng");
                    this.f = 2;
                }
            } else if (game.k.a().n()) {
                this.a("L\u01b0u th\u00e0nh c\u00f4ng");
                this.f = 2;
            }
        } else if (this.f == 2) {
            this.p.a("/data/ui/msgtip.ui");
            this.f = 3;
        } else if (this.f == 3) {
            return true;
        }
        return false;
    }

    public final void aB() {
        this.p.a("/data/ui/dialog.ui", 257, this);
        this.p.a.a(12).a(false);
        this.p.a.a(13).a(false);
    }

    public final void a(String string, String string2, int n2) {
        this.p.a("/data/ui/dialog.ui", 257, this);
        ae.a(string2, an.G(), this.p.a.a(14).d(), an.D(), this.o.R.b);
        ae.c(this.p.a.a(14).e());
        this.p.a.a((int)14).h().a = ae.d(1);
        game.k.t = (byte)n2;
        game.k.s = (byte)-1;
        this.p.a.a(8).a(false);
        this.p.a.a(11).a(false);
        this.p.a.a(12).a(true);
        this.p.a.a(13).a(true);
        if (n2 == -1) {
            this.p.a.a(12).a(false);
            this.p.a.a(13).a(false);
        }
        switch (n2) {
            case 0: {
                this.p.a.a(13).a(false);
                this.p.a.a((int)12).h().a = string;
                return;
            }
            case 1: {
                this.p.a.a(12).a(false);
                this.p.a.a((int)13).h().a = string;
            }
        }
    }

    public final void b(int n2) {
        this.p.a.a((int)14).h().a = ae.d(n2);
    }

    public final void aC() {
        this.p.a("/data/ui/dialog.ui");
    }

    public final boolean c(int n2, int n3) {
        if (n3 == -1) {
            return true;
        }
        switch (n2) {
            case 0: {
                if (!this.p.a.a((int)11).h().m.a().e()) break;
                return true;
            }
            case 1: {
                if (!this.p.a.a((int)8).h().m.a().e()) break;
                return true;
            }
        }
        this.g = true;
        return false;
    }

    public final void a(int n2, int n3, String[] stringArray, String string) {
        this.b = 0;
        this.p.a(this.N[n2], 257, this);
        ((al)this.p.a.a((int)0)).a.a = n3;
        switch (n2) {
            case 0: {
                for (n2 = 0; n2 < stringArray.length; ++n2) {
                    this.p.a.a((int)(n2 + 12)).h().a = stringArray[n2];
                }
                return;
            }
            case 1: {
                this.p.a.a((int)5).h().a = string;
                for (n2 = 0; n2 < stringArray.length; ++n2) {
                    this.p.a.a((int)(9 + (n2 << 2))).h().a = stringArray[n2];
                }
                return;
            }
            case 2: {
                this.p.a.a(10).a(false);
                this.p.a.a((int)8).h().a = "Tr\u00f2 ch\u01a1i";
                this.p.a.a((int)9).h().a = "X\u00e1c nh\u1eadn";
                for (n2 = 0; n2 < stringArray.length; ++n2) {
                    this.p.a.a((int)(n2 + 5)).h().a = stringArray[n2];
                }
                break;
            }
        }
    }

    public final int c(int n2) {
        if (this.o.k(4100)) {
            this.p.a.b(0);
            this.b = this.z[0];
        } else if (this.o.k(8448)) {
            this.p.a.b(1);
            this.b = this.z[0];
        } else if (this.o.k(196640)) {
            int n3 = n2;
            h h2 = this;
            h2.p.a(h2.N[n3]);
            return this.b;
        }
        return -1;
    }

    public final void a(int[] nArray, int[] nArray2, String[] stringArray, String[] stringArray2) {
        int n2;
        this.b = 0;
        this.p.a("/data/ui/taskOption.ui", 257, this);
        for (n2 = 0; n2 < stringArray2.length; ++n2) {
            this.p.a.a((int)(n2 + 17)).h().a = stringArray2[n2];
        }
        block10: for (n2 = 0; n2 < nArray.length; ++n2) {
            if (this.p.a.a((int)((n2 << 1) + 13)).h().m == null) {
                this.p.a.a((int)((n2 << 1) + 13)).h().m = new m();
                this.p.a.a((int)((n2 << 1) + 13)).h().m.a = (byte)2;
                if (nArray[n2] < 3 || nArray[n2] >= 5) {
                    this.p.a.a((int)((n2 << 1) + 13)).h().m.a(0);
                    this.p.a.a((int)((n2 << 1) + 13)).h().m.a(258, false, (byte)0);
                } else {
                    this.p.a.a((int)((n2 << 1) + 13)).h().m.a(-1);
                    this.p.a.a((int)((n2 << 1) + 13)).h().m.a(257, false, (byte)0);
                }
            }
            switch (nArray[n2]) {
                case 0: {
                    this.p.a.a((int)((n2 << 1) + 13)).h().m.a(aq.c[4][nArray2[n2]][1]);
                    this.p.a.a((int)((n2 << 1) + 14)).h().a = stringArray[n2];
                    continue block10;
                }
                case 1: {
                    this.p.a.a((int)((n2 << 1) + 13)).h().m.a(aq.c[3][nArray2[n2]][1]);
                    this.p.a.a((int)((n2 << 1) + 14)).h().a = stringArray[n2];
                    continue block10;
                }
                case 2: {
                    this.p.a.a((int)((n2 << 1) + 13)).h().m.a(aq.c[5][nArray2[n2]][1]);
                    this.p.a.a((int)((n2 << 1) + 14)).h().a = stringArray[n2];
                    continue block10;
                }
                case 3: {
                    this.p.a.a((int)((n2 << 1) + 13)).h().m.a(84);
                    this.p.a.a((int)((n2 << 1) + 14)).h().a = stringArray[n2];
                    continue block10;
                }
                case 4: {
                    this.p.a.a((int)((n2 << 1) + 13)).h().m.a(83);
                    this.p.a.a((int)((n2 << 1) + 14)).h().a = stringArray[n2];
                    continue block10;
                }
                case 5: {
                    continue block10;
                }
                case 6: {
                    this.p.a.a((int)21).h().a = "#2" + an.f(aq.a((byte)0, (short)nArray2[n2], (byte)0)) + " #0" + stringArray[n2];
                }
            }
        }
    }

    public final int aD() {
        if (this.o.k(4100)) {
            this.p.a.b(0);
            this.b = this.z[0];
        } else if (this.o.k(8448)) {
            this.p.a.b(1);
            this.b = this.z[0];
        } else {
            if (this.o.k(196640)) {
                this.p.a("/data/ui/taskOption.ui");
                return this.b;
            }
            if (this.o.k(262144)) {
                this.p.a("/data/ui/taskOption.ui");
                return 1;
            }
        }
        return -1;
    }

    public final void aE() {
        this.bw();
        this.c("C\u00f3 d\u00f9ng 10000 kim ti\u1ec1n \u0111\u1ec3 kh\u00f4i ph\u1ee5c tr\u1ea1ng th\u00e1i c\u1ee7a t\u1ea5t c\u1ea3 s\u1ee7ng v\u1eadt trong ba l\u00f4 kh\u00f4ng?", "T\u1ea1i ch\u1ed7 s\u1ed1ng l\u1ea1i");
    }

    public final void aF() {
        if (this.o.k(196640)) {
            if (this.f == 0) {
                if (this.q.t(10000)) {
                    this.q.s(-10000);
                    for (int i2 = 0; i2 < this.q.A; ++i2) {
                        this.q.z[i2].I();
                        this.q.z[i2].u(this.q.z[i2].d[1]);
                    }
                    game.d.a().c();
                    this.o.a((byte)0);
                    this.bx();
                    return;
                }
                this.E();
                this.a("Kim ti\u1ec1n ch\u01b0a \u0111\u1ee7", "Nh\u1ea5n n\u00fat 5 \u0111\u1ec3 ti\u1ebfp t\u1ee5c");
                this.f = 1;
                return;
            }
            for (int i3 = 0; i3 < this.q.A; ++i3) {
                this.q.z[i3].l(1);
                this.q.z[i3].u(1);
                this.q.z[i3].c();
            }
            if (an.X) {
                this.o.a((byte)102);
            } else {
                this.bv();
            }
            this.F();
            return;
        }
        if (this.f == 0 && this.o.k(786432)) {
            this.bv();
            this.bx();
        }
    }

    private void bv() {
        int n2 = -1;
        if (game.k.a().f == 9 && game.k.a().g != 0) {
            n2 = (byte)game.k.a().g;
        }
        if (n2 == -1) {
            game.k.a();
            if (game.k.i()) {
                game.k.a().f();
                this.q.y = false;
                game.i.a().a((byte)9);
                return;
            }
            game.i.a().a((byte)7);
            return;
        }
        if (game.k.a().g == 0) {
            short[] sArray = new short[]{15, 194, 433, 16, 142, 357, 17, 97, 268, 18, 183, 224};
            for (int i2 = 0; i2 < game.k.a().d.length; ++i2) {
                for (int i3 = 0; i3 < sArray.length / 3; ++i3) {
                    if (game.k.a().d[i2].I != sArray[i3 * 3]) continue;
                    game.k.a().d[i2].b(sArray[i3 * 3 + 1], sArray[i3 * 3 + 2]);
                }
            }
        }
        game.k.u = (short)-1;
        for (int i4 = 0; i4 < this.q.A; ++i4) {
            this.q.z[i4].l(1);
            this.q.z[i4].u(1);
            this.q.z[i4].c();
        }
        game.k.a().f = this.m[n2 << 2];
        game.k.a().g = this.m[(n2 << 2) + 1];
        game.g.o().b(this.m[(n2 << 2) + 2], this.m[(n2 << 2) + 3]);
        game.g.o().b.b(this.m[(n2 << 2) + 2], this.m[(n2 << 2) + 3]);
        game.g.o().n = (byte)2;
        game.i.a().a((byte)10);
    }

    public final void aG() {
        this.o.c((byte)0);
        this.o.d((byte)0);
        this.bw();
        Object object = new int[]{4, 1, 4};
        object = an.f(599) + an.a(604, object);
        this.c((String)object, "K\u00edch ho\u1ea1t");
    }

    public final void aH() {
        this.o.c((byte)1);
        this.o.d((byte)0);
        this.bw();
        Object object = new int[]{2, 1, 2};
        object = an.f(600) + an.a(604, object);
        this.c((String)object, "Mua s\u1eafm t\u1ea5t tr\u00fang c\u1ea7u");
    }

    public final void aI() {
        this.f = 0;
        this.o.c((byte)4);
        this.o.d((byte)0);
        this.bw();
        Object object = new int[]{2, 1, 2};
        object = an.f(603) + an.a(604, object);
        this.c((String)object, "Mua s\u1eafm huy hi\u1ec7u");
    }

    public final void aJ() {
        this.f = 0;
        this.o.c((byte)2);
        this.o.d((byte)0);
        this.bw();
        Object object = new int[]{2, 1, 2};
        object = an.f(601) + an.a(604, object);
        this.c((String)object, "Mua s\u1eafm kim ti\u1ec1n");
    }

    private void bw() {
        this.p.a("/data/ui/smsInfo.ui", 257, this);
        if (this.o instanceof k) {
            this.p.a.a(6).a(true);
            this.p.a.a(7).a(true);
            this.p.a.a(10).a(false);
            this.p.a.a(11).a(false);
            return;
        }
        this.p.a.a(6).a(false);
        this.p.a.a(7).a(false);
        this.p.a.a(10).a(true);
        this.p.a.a(11).a(true);
        this.p.a.a((int)10).h().a = "X\u00e1c nh\u1eadn";
        this.p.a.a((int)11).h().a = "Ph\u1ea3n h\u1ed3i";
    }

    private void c(String string, String string2) {
        this.p.a.a((int)8).h().a = string;
        this.p.a.a((int)5).h().a = string2;
    }

    private void bx() {
        this.p.a("/data/ui/smsInfo.ui");
    }

    public final void aK() {
        if (!this.p.c("/data/ui/smsTip.ui")) {
            this.p.a("/data/ui/smsTip.ui", 257, this);
        }
        for (int i2 = 0; i2 < 3; ++i2) {
            this.p.a.a(i2 + 6).a(false);
        }
        this.g = true;
    }

    public final void d(String string) {
        this.g = true;
        this.p.a.a((int)5).h().a = string;
    }

    public final void aL() {
        this.p.a("/data/ui/smsTip.ui");
    }

    public final void aM() {
        switch (this.o.N()) {
            case 0: {
                if (this.o.k(16400) || this.o.k(32832)) break;
                if (this.o.k(131072)) {
                    if (this.o.O() > 1) {
                        this.o.d((byte)1);
                        return;
                    }
                    this.o.g(1);
                    return;
                }
                if (!this.o.k(786432)) break;
                this.bx();
                this.o.d((byte)5);
                this.o.a(this.o.Q);
                return;
            }
            case 1: {
                if (this.o.k(131072)) {
                    this.o.h(1);
                    return;
                }
                if (!this.o.k(262144)) break;
                this.o.h(2);
                return;
            }
            case 2: {
                boolean bl = false;
                if (this.o.P == 100) {
                    if (this.x >= this.O.length && this.ax()) {
                        bl = true;
                    } else if (this.ax()) {
                        this.b(this.O[this.x]);
                        ++this.x;
                    }
                    this.f();
                } else {
                    bl = true;
                }
                if (!bl || !this.bu() || !this.o.k(917504)) break;
                this.x = 0;
                if (this.o.M()) {
                    this.bx();
                    this.aL();
                    this.o.a(this.o.Q);
                } else {
                    this.o.d((byte)5);
                }
                this.f = 0;
                return;
            }
            case 3: {
                if (!this.o.k(393216)) break;
                this.o.h(1);
            }
        }
    }

    public final void a(byte by, int n2, int n3) {
        this.c = 0;
        this.R = by;
        this.S = (byte)n2;
        switch (n2) {
            case 0: {
                this.p.a("/data/ui/wharf1.ui", 257, this);
                this.p.a.a((int)8).h().a = an.f(n3);
                for (n2 = 0; n2 < this.P[by].length; ++n2) {
                    this.p.a.a((int)(n2 + 5)).h().a = an.f(this.P[by][n2]);
                }
                break;
            }
            case 1: {
                this.p.a("/data/ui/wharf2.ui", 257, this);
                this.p.a.a((int)10).h().a = an.f(n3);
                for (n2 = 0; n2 < this.P[by].length; ++n2) {
                    this.p.a.a((int)(n2 + 5)).h().a = an.f(this.P[by][n2]);
                }
                break;
            }
        }
        this.p.a.a((int)(5 + this.P[by].length)).h().a = "Kh\u00f4ng ra h\u00e0ng";
    }

    public final void aN() {
        if (this.o.k(4100) && !this.j()) {
            this.p.a.b(0);
        } else if (this.o.k(8448) && !this.j()) {
            this.p.a.b(1);
        } else if (this.o.k(196640) && !this.j()) {
            if (this.c == this.Q[this.R].length / 9) {
                switch (this.S) {
                    case 0: {
                        this.p.a("/data/ui/wharf1.ui");
                        break;
                    }
                    case 1: {
                        this.p.a("/data/ui/wharf2.ui");
                    }
                }
                this.o.a((byte)0);
                if (game.k.u != -1 && game.k.a().d[game.k.u].u() == 0) {
                    game.k.a().a(game.k.a().d[game.k.u].i, game.k.a().d[game.k.u].j - 40, game.k.a().d[game.k.u]);
                }
            } else if (game.k.a().M.b[game.k.a((int)this.Q[this.R][this.c * 9 + 6], (int)this.Q[this.R][this.c * 9 + 7])] != null && game.k.a().M.b[game.k.a((int)this.Q[this.R][this.c * 9 + 6], (int)this.Q[this.R][this.c * 9 + 7])][this.Q[this.R][this.c * 9 + 8]] == 3) {
                game.k.a().f = this.Q[this.R][this.c * 9];
                game.k.a().g = this.Q[this.R][this.c * 9 + 1];
                game.k.a().h = this.Q[this.R][this.c * 9 + 2];
                game.k.a().i = this.Q[this.R][this.c * 9 + 3];
                game.k.w = (byte)this.Q[this.R][this.c * 9 + 4];
                game.k.a().j = -1;
                this.o.a((byte)29);
                switch (this.S) {
                    case 0: {
                        this.p.a("/data/ui/wharf1.ui");
                        break;
                    }
                    case 1: {
                        this.p.a("/data/ui/wharf2.ui");
                    }
                }
            } else {
                this.b("\u0110\u01b0\u1eddng th\u1ee7y ch\u01b0a m\u1edf");
            }
        } else if (this.o.k(262144) && !this.j()) {
            if (game.k.u != -1 && game.k.a().d[game.k.u].u() == 0) {
                game.k.a().a(game.k.a().d[game.k.u].i, game.k.a().d[game.k.u].j - 40, game.k.a().d[game.k.u]);
            }
            switch (this.S) {
                case 0: {
                    this.p.a("/data/ui/wharf1.ui");
                    break;
                }
                case 1: {
                    this.p.a("/data/ui/wharf2.ui");
                }
            }
            this.o.a((byte)0);
        }
        this.f();
    }

    public final void aO() {
        this.b = 0;
        this.p.a("/data/ui/shopbuy.ui", 257, this);
        this.b = 0;
        this.f = 0;
        ((al)this.p.a.a((int)0)).a.a = 1;
        ((al)this.p.a.a((int)0)).a.a(0);
        this.p.a.a(41).a(false);
        this.p.a.a(43).a(false);
        this.p.a.a((int)5).h().a = "Mua";
        this.p.a.a(57).a(true);
        this.p.a.a(58).a(true);
        this.p.a.a((int)57).h().a = "Mua s\u1eafm";
        this.p.a.a((int)58).h().a = "Ph\u1ea3n h\u1ed3i";
        this.p.a.a(39).a(false);
        this.p.a.a(40).a(false);
        h h2 = this;
        h2.w = ((al)h2.p.a.a((int)0)).a.e;
        h2.h = ((al)h2.p.a.a((int)0)).a.f;
        if (h2.p.a.a((int)51).h().m == null) {
            h2.p.a.a((int)51).h().m = new m();
            h2.p.a.a((int)51).h().m.a(0);
            h2.p.a.a((int)51).h().m.a = (byte)2;
            h2.p.a.a((int)51).h().m.a(258, false, (byte)-1);
        }
        h2.p.a.a((int)51).h().m.a(aq.c[5][0][1]);
        h2.p.a.a((int)14).h().a = an.f(aq.c[5][0][0]);
        h2.p.a.a((int)15).h().a = "5000";
        h2.p.a.a((int)45).h().m.a(84);
        h2.p.a.a((int)56).h().a = "\u1ea4p tr\u1ee9ng ra s\u1ee7ng v\u1eadt";
        h2.p.a.a((int)44).h().a = "" + h2.q.E();
        h2.p.a.a(38).b(102 + h2.h * 84 / aq.c[5].length, h2.p.a.a());
    }

    private void by() {
        this.p.a("/data/ui/shopbuy.ui");
    }

    public final int aP() {
        if (this.o.k(196640)) {
            if (this.f == 0) {
                if (this.q.t(5000)) {
                    if (this.q.k(0)) {
                        this.E();
                        this.a("\u0110\u00e3 c\u00f3 tr\u01b0\u0301ng su\u0309ng v\u00e2\u0323t, kh\u00f4ng c\u1ea7n mua s\u1eafm", "Nh\u1ea5n n\u00fat 5 \u0111\u1ec3 ti\u1ebfp t\u1ee5c");
                        this.f = 2;
                    } else {
                        this.q.e(0, -1);
                        this.E();
                        this.a("\u0110\u00e3 th\u00e0nh c\u00f4ng mua s\u1eafm #2 tr\u01b0\u0301ng su\u0309ng v\u00e2\u0323t", "Nh\u1ea5n n\u00fat 5 \u0111\u1ec3 ti\u1ebfp t\u1ee5c");
                        this.f = 2;
                    }
                } else {
                    this.E();
                    this.a("Kim ti\u1ec1n ch\u01b0a \u0111\u1ee7", "Nh\u1ea5n n\u00fat 5 \u0111\u1ec3 ti\u1ebfp t\u1ee5c");
                    this.f = 1;
                }
            } else if (this.f > 0) {
                this.F();
                if (this.f == 1) {
                    this.o.a((byte)102);
                } else if (this.f == 2) {
                    game.c.m = 0;
                    this.by();
                    this.o.a((byte)0);
                }
            }
        } else if (this.o.k(262144) && this.f == 0) {
            game.c.m = 1;
            this.by();
            this.o.a((byte)0);
        }
        return -1;
    }

    public final void aQ() {
        this.p.a("/data/ui/wharf2.ui", 257, this);
        ((al)this.p.a.a((int)0)).a.f = this.d;
        this.f = 0;
        this.p.a.a((int)10).h().a = "Ti\u1ec7n l\u1ee3i \u0111i\u1ebfm";
        this.p.a.a((int)12).h().a = "Ti\u1ebfn v\u00e0o";
        for (int i2 = 0; i2 < this.T.length; ++i2) {
            this.p.a.a((int)(i2 + 5)).h().a = this.T[i2];
        }
    }

    public final void aR() {
        if (this.o.k(4100) && !this.j()) {
            this.p.a.b(0);
            return;
        }
        if (this.o.k(8448) && !this.j()) {
            this.p.a.b(1);
            return;
        }
        if (this.o.k(196640) && !this.j()) {
            if (this.f == 0) {
                switch (this.d) {
                    case 0: {
                        this.p.a("/data/ui/wharf2.ui");
                        this.o.a((byte)31);
                        return;
                    }
                    case 1: 
                    case 2: {
                        game.k.a();
                        if (game.k.K) {
                            this.p.a("/data/ui/wharf2.ui");
                            this.c = 0;
                            this.o.a((byte)7);
                            return;
                        }
                        this.E();
                        this.f = 1;
                        this.a("C\u00f4ng n\u0103ng theo \u0111\u1ea1o h\u1ecdc sau m\u1edf ra", "Nh\u1ea5n n\u00fat 5 \u0111\u1ec3 ti\u1ebfp t\u1ee5c");
                        return;
                    }
                    case 3: {
                        this.p.a("/data/ui/wharf2.ui");
                        this.o.a((byte)32);
                        return;
                    }
                    case 4: {
                        this.p.a("/data/ui/wharf2.ui");
                        this.o.a((byte)0);
                    }
                }
                return;
            }
            this.f = 0;
            this.F();
            this.g = true;
            return;
        }
        if (this.f == 0 && this.o.k(262144) && !this.j()) {
            this.p.a("/data/ui/wharf2.ui");
            this.o.a((byte)0);
        }
    }

    public final void a(int[] nArray) {
        this.z = nArray;
        if (this.o instanceof k) {
            switch (((k)this.o).P) {
                case 0: {
                    return;
                }
                case 1: {
                    int[] nArray2 = nArray;
                    Object var2_22 = null;
                    this.b = nArray2[0];
                    return;
                }
                case 2: 
                case 26: 
                case 32: {
                    this.d(nArray);
                    return;
                }
                case 3: {
                    int[] nArray3 = nArray;
                    h h2 = this;
                    if (h2.f == 0) {
                        h2.b = nArray3[0];
                        h2.aX();
                        return;
                    }
                    h2.r = nArray3[0];
                    return;
                }
                case 4: {
                    return;
                }
                case 5: {
                    int[] nArray4 = nArray;
                    h h3 = this;
                    this.b = nArray4[1];
                    h3.bm();
                    return;
                }
                case 6: {
                    int[] nArray5 = nArray;
                    h h4 = this;
                    this.b = nArray5[0];
                    if (an.X) {
                        h4.p.a.a((int)14).h().a = an.f(605 + h4.b);
                        return;
                    }
                    h4.p.a.a((int)14).h().a = an.f(606 + h4.b);
                    return;
                }
                case 7: {
                    this.c(nArray);
                    return;
                }
                case 8: {
                    int[] nArray6 = nArray;
                    h h5 = this;
                    if (nArray6[0] >= 0) {
                        h5.c = nArray6[0];
                    }
                    if (nArray6[1] >= 0) {
                        h5.b = nArray6[1];
                    }
                    h5.bj();
                    return;
                }
                case 9: {
                    int[] nArray7 = nArray;
                    Object var2_27 = null;
                    this.c = nArray7[1];
                    return;
                }
                case 10: {
                    int[] nArray8 = nArray;
                    h h6 = this;
                    this.b = nArray8[1];
                    switch (h6.b) {
                        case 0: {
                            h6.c = nArray8[0];
                            return;
                        }
                        case 1: {
                            h6.r = nArray8[0];
                        }
                    }
                    return;
                }
                case 11: {
                    int[] nArray9 = nArray;
                    h h7 = this;
                    this.c = nArray9[0];
                    h7.b = nArray9[1];
                    return;
                }
                case 12: {
                    int[] nArray10 = nArray;
                    Object var2_30 = null;
                    this.b = nArray10[1];
                    return;
                }
                case 13: {
                    if (this.f == 0) {
                        this.b = nArray[0];
                        return;
                    }
                    this.c = nArray[0];
                    return;
                }
                case 14: {
                    int[] nArray11 = nArray;
                    Object var2_31 = null;
                    this.c = nArray11[0];
                    return;
                }
                case 15: {
                    int[] nArray12 = nArray;
                    Object var2_32 = null;
                    this.c = nArray12[0];
                    return;
                }
                case 16: {
                    int[] nArray13 = nArray;
                    Object var2_33 = null;
                    this.b = nArray13[0];
                    return;
                }
                case 17: 
                case 18: 
                case 19: {
                    int[] nArray14 = nArray;
                    Object var2_34 = null;
                    this.c = nArray14[0];
                    return;
                }
                case 20: {
                    this.c = nArray[1];
                    return;
                }
                case 24: {
                    int[] nArray15 = nArray;
                    Object var2_35 = null;
                    this.c = nArray15[0];
                    return;
                }
                case 28: {
                    int[] nArray16 = nArray;
                    Object var2_36 = null;
                    this.c = nArray16[0];
                    return;
                }
                case 27: {
                    this.d = nArray[0];
                }
            }
            return;
        }
        if (this.o instanceof d) {
            switch (((d)this.o).P) {
                case 2: {
                    return;
                }
                case 3: {
                    int[] nArray17 = nArray;
                    Object var2_37 = null;
                    this.e = nArray17[0];
                    return;
                }
                case 4: {
                    int[] nArray18 = nArray;
                    h h8 = this;
                    this.b = nArray18[0];
                    h8.be();
                    return;
                }
                case 5: {
                    this.c(nArray);
                    return;
                }
                case 6: {
                    return;
                }
                case 7: {
                    return;
                }
                case 8: {
                    return;
                }
                case 9: {
                    return;
                }
                case 10: {
                    return;
                }
                case 11: {
                    this.d(nArray);
                    return;
                }
                case 12: {
                    return;
                }
                case 13: {
                    return;
                }
                case 14: {
                    return;
                }
                case 15: {
                    return;
                }
                case 16: {
                    int[] nArray19 = nArray;
                    h h9 = this;
                    this.c = nArray19[0];
                    h9.f(h9.c);
                    return;
                }
                case 17: {
                    return;
                }
                case 18: {
                    return;
                }
                case 19: {
                    return;
                }
                case 20: {
                    int[] nArray20 = nArray;
                    h h10 = this;
                    this.a = nArray20[1];
                    h10.a(true);
                    return;
                }
                case 21: {
                    int[] nArray21 = nArray;
                    Object var2_41 = null;
                    this.b = nArray21[0];
                }
                case 23: {
                    int[] nArray21 = nArray;
                    this.b = nArray[0];
                }
            }
        }
    }

    private void c(int[] nArray) {
        if (this.f == 0) {
            this.b = nArray[0];
            this.f(this.b);
            return;
        }
        if (this.f == 1) {
            this.c = nArray[0];
            return;
        }
        if (this.f == 2) {
            this.r = nArray[0];
            switch (this.c) {
                case 0: {
                    this.be();
                }
            }
        }
    }

    private void d(int[] nArray) {
        if (this.f == 0) {
            this.b = nArray[0];
            return;
        }
        this.r = nArray[0];
    }

    static {
        A = new String[]{"Th\u1ee7y Kimura", "B\u00edch Th\u1ee7y th\u00e0nh", "Nguy\u00ean M\u1ed9c Th\u00e0nh", "Ni\u00eam Th\u1ed5 Th\u00e0nh", "H\u1eafc Th\u1ea1ch th\u00e0nh", "Thi\u00ean kh\u00f4ng", "Xa c\u1ed5"};
        B = new short[]{1, 0, 196, 208, 0, 2, 1, 196, 208, 0, 3, 3, 196, 208, 0, 4, 5, 320, 352, 0, 5, 3, 320, 196, 0, 7, 2, 288, 112, 0, 8, 0, 160, 144, 0};
    }
}

