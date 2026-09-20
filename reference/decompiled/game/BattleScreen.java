/*
 * Decompiled with CFR 0.152.
 */
package game;

import game.Pet;
import game.Player;
import game.ScriptEngine;
import game.GameStateController;
import game.WorldManager;
import java.io.IOException;
import java.io.InputStream;
import java.util.Vector;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

/**
 * BattleScreen - Turn-based combat engine handling turns, attack animations, damage resolution, and pet capture.
 */
public final class BattleScreen
extends BaseScreen {
    private final byte[] n = new byte[]{2, 4};
    private static BattleScreen o;
    private static Player p;
    private byte q = 0;
    public int a;
    public byte b;
    private byte r;
    public Image c;
    private int[][] s;
    public Pet[] d;
    private byte[] t;
    public byte[] e;
    public byte[] f;
    private byte[] u;
    public byte g = 0;
    public Pet h;
    private Vector v;
    public byte i;
    private boolean w;
    private static Vector x;
    public static Vector j;
    private boolean y;
    public boolean k;
    private boolean z;
    private boolean A;
    private boolean B;
    private byte C;
    private byte[] D;
    private byte[] E;
    private byte F;
    private int G;
    private SkillEffect H;
    private byte I = 0;
    private byte J = 0;
    private byte K = 0;
    private byte L = 0;
    private boolean M;
    private boolean N;
    private byte[] O;
    private int[] Z;
    private boolean aa;
    private byte ab;
    private byte ac;
    private byte ad;
    private byte[] ae;
    private byte[][] af;
    private byte[] ag;
    private byte[] ah;
    private byte[][] ai = new byte[][]{{3, 5, 13}, {0, 1, 2, 3, 8, 9, 10}};
    private WorldEntity aj;
    private boolean ak;
    public static byte l;
    private WorldEntity[] al;
    private static short[][] am;
    private static short[][][] an;
    private static byte[][] ao;
    public static short[][] m;
    private static byte[][] ap;
    private static byte[][] aq;
    private static byte[][] ar;
    private int as;
    private int at;
    private int au;
    private int av;
    private int aw;
    private int ax;
    private String ay = null;
    private static Image[] az;
    private static short[][] aA;
    private Vector aB = new Vector();
    private Vector aC = new Vector();
    private int aD;
    private boolean aE;
    private int aF;
    private byte[] aG;
    private byte[] aH;
    private byte[] aI;

    public static BattleScreen getInstance() {
        if (o == null) {
            o = new BattleScreen();
        }
        return o;
    }

    public BattleScreen() {
        new Vector();
        new Vector();
        this.aD = 0;
        this.aE = false;
        this.aF = 0;
        this.aG = new byte[]{10, 11, 12, 13, 15};
        this.aH = new byte[]{10, 12, 13, 14, 15, 16};
        this.aI = new byte[]{105, 100, 80, 60, 40, 20, 5};
        if (this.v == null) {
            this.v = new Vector();
        }
        if (x == null) {
            x = new Vector();
        }
        if (j == null) {
            j = new Vector();
        }
    }

    public final void f() {
        int n2;
        this.v.removeAllElements();
        for (n2 = 0; n2 < this.f.length; ++n2) {
            this.c(n2).C();
            this.c(n2).D();
            this.c((int)n2).F = 0;
            this.c(n2).d(false);
            this.c((int)n2).G.removeAllElements();
            this.c((int)n2).H.removeAllElements();
            this.c(n2).u(this.c((int)n2).d[1]);
        }
        x.removeAllElements();
        j.removeAllElements();
        for (n2 = 0; n2 < this.d.length; ++n2) {
            if (this.d[n2] == null) continue;
            this.d[n2].d();
            this.d[n2] = null;
        }
        for (n2 = 0; n2 < az.length; ++n2) {
            if (az[n2] == null) continue;
            game.BattleScreen.az[n2] = null;
        }
        az = null;
        if (this.aj != null) {
            this.aj.a.b();
            this.aj = null;
        }
        if (this.al != null) {
            for (n2 = 0; n2 < this.al.length; ++n2) {
                this.al[n2].a.b();
                this.al[n2] = null;
            }
            this.al = null;
        }
        this.u = null;
        this.i = 0;
        this.G = 0;
        this.h = null;
        this.w = false;
        this.y = false;
        this.H = null;
        this.d = null;
        this.c = null;
        this.t = null;
        this.e = null;
        this.D = null;
        this.E = null;
        this.ae = null;
        this.O = null;
        ap = null;
        aq = null;
        game.WorldManager.r = 0;
        am = null;
        an = null;
        ao = null;
        m = null;
        aA = null;
        ap = null;
        aq = null;
        ar = null;
        this.S.af();
    }

    public final boolean d() {
        int n2;
        InputStream inputStream;
        int n3;
        this.s();
        this.u = new byte[2];
        p = game.Player.getInstance();
        this.f = new byte[game.BattleScreen.p.A];
        int n4 = 0;
        for (n3 = 0; n3 < game.BattleScreen.p.A; ++n3) {
            this.f[n3] = (byte)n3;
            if (this.c(n3) != null && this.c(n3).S()) {
                ++n4;
            }
            this.c(n3).j(this.c(n3).z());
        }
        this.d = n4 == 1 && this.a == 1 ? new Pet[3] : new Pet[this.n[this.a]];
        try {
            inputStream = null;
            am = EngineUtils.a(EngineUtils.a("/data/script/pos.mid"));
            inputStream = EngineUtils.a("/data/script/cpos.mid");
            an = new short[3][][];
            for (n3 = 0; n3 < 3; ++n3) {
                game.BattleScreen.an[n3] = EngineUtils.randomInt(inputStream);
            }
            ao = EngineUtils.b(EngineUtils.a("/data/script/effect.mid"));
            m = EngineUtils.a(EngineUtils.a("/data/script/speffect.mid"));
            aA = EngineUtils.a(EngineUtils.a("/data/script/blood.mid"));
            inputStream = EngineUtils.a("/data/script/bufDebuf.mid");
            ap = EngineUtils.b(inputStream);
            aq = EngineUtils.b(inputStream);
            ar = EngineUtils.b(inputStream);
            inputStream.close();
        }
        catch (IOException iOException) {
            inputStream = null;
            iOException.printStackTrace();
        }
        this.r = this.a == 0 ? (this.b == 1 ? (byte)2 : (byte)0) : (byte)1;
        this.al = new WorldEntity[this.d.length + 2];
        for (n3 = 0; n3 < this.al.length; ++n3) {
            this.al[n3] = new WorldEntity();
            this.al[n3].a(294, false);
            if (n3 == this.d.length + 1) {
                this.al[n3].a((byte)2, (byte)-1, false);
                if (this.a != 0) continue;
                this.al[n3].c();
                continue;
            }
            if (n3 == this.d.length) {
                this.al[n3].a((byte)1, (byte)-1, false);
                this.al[n3].c();
                this.al[n3].b(false);
                continue;
            }
            this.al[n3].a((byte)0, (byte)-1, false);
            this.al[n3].b(an[this.r][n3][2], an[this.r][n3][3]);
            this.al[n3].c();
        }
        this.D = new byte[this.d.length];
        this.E = new byte[this.d.length];
        this.n(this.d.length);
        n3 = 0;
        block13: for (n2 = 0; n2 < this.d.length; ++n2) {
            if (this.a == 0) {
                if (n2 > 0) {
                    while (!this.c((int)this.f[n3]).S()) {
                        ++n3;
                    }
                    this.a(n2, n3);
                    x.addElement(this.c((int)this.f[n3]));
                    this.e(0, n3);
                    continue;
                }
                switch (this.b) {
                    case 0: 
                    case 1: {
                        this.m(n2);
                        break;
                    }
                    case 2: {
                        this.m(n2);
                    }
                }
                continue;
            }
            if (n2 > 1) {
                while (!this.c((int)this.f[n3]).S()) {
                    ++n3;
                }
                this.a(n2, n3);
                x.addElement(this.c((int)this.f[n3]));
                this.e(n2 - 2, n3);
                ++n3;
                continue;
            }
            switch (this.b) {
                case 0: 
                case 1: {
                    this.m(n2);
                    continue block13;
                }
                case 2: {
                    this.m(n2);
                }
            }
        }
        this.T();
        az = new Image[3];
        for (n2 = 0; n2 < az.length; ++n2) {
            game.BattleScreen.az[n2] = ae.loadImage("/data/tex/", "blood_" + n2);
        }
        for (n2 = 0; n2 < game.BattleScreen.p.A; ++n2) {
            game.BattleScreen.p.z[n2].A = game.BattleScreen.p.z[n2].d[1];
        }
        this.a((byte)0);
        game.BattleScreen.t();
        return true;
    }

    public final void c() {
        int n2;
        int n3 = 0;
        for (int i = 0; i < game.BattleScreen.p.A; ++i) {
            if (this.c(i) != null && this.c(i).S()) {
                ++n3;
            }
            this.c(i).j(this.c(i).z());
        }
        Pet[] bArray = this.a == 0 ? new Pet[1] : new Pet[2];
        for (n2 = 0; n2 < this.d.length; n2 = (int)((byte)(n2 + 1))) {
            if (this.d[n2].r() != 1) continue;
            bArray[n2] = this.d[n2];
        }
        this.d = n3 == 1 && this.a == 1 ? new Pet[3] : new Pet[this.n[this.a]];
        this.al = new WorldEntity[this.d.length + 2];
        for (n2 = 0; n2 < this.al.length; ++n2) {
            this.al[n2] = new WorldEntity();
            this.al[n2].a(294, false);
            if (n2 == this.d.length + 1) {
                this.al[n2].a((byte)2, (byte)-1, false);
                if (this.a != 0) continue;
                this.al[n2].c();
                continue;
            }
            if (n2 == this.d.length) {
                this.al[n2].a((byte)1, (byte)-1, false);
                this.al[n2].c();
                this.al[n2].b(false);
                continue;
            }
            this.al[n2].a((byte)0, (byte)-1, false);
            this.al[n2].b(an[this.r][n2][2], an[this.r][n2][3]);
            this.al[n2].c();
        }
        this.D = new byte[this.d.length];
        this.E = new byte[this.d.length];
        this.G = 0;
        this.n(this.d.length);
        n2 = 0;
        for (n3 = 0; n3 < this.d.length; ++n3) {
            if (this.a == 0) {
                if (n3 > 0) {
                    while (!this.c((int)this.f[n2]).S()) {
                        ++n2;
                    }
                    this.a(n3, n2);
                    x.addElement(this.c((int)this.f[n2]));
                    this.e(0, n2);
                    continue;
                }
                this.d[n3] = bArray[n3];
                continue;
            }
            if (n3 > 1) {
                while (!this.c((int)this.f[n2]).S()) {
                    ++n2;
                }
                this.a(n3, n2);
                x.addElement(this.c((int)this.f[n2]));
                this.e(n3 - 2, n2);
                ++n2;
                continue;
            }
            this.d[n3] = bArray[n3];
        }
        this.T();
    }

    public final void e() {
        if (this.a == 1) {
            int n2 = 0;
            while (((Pet)this.v.elementAt(n2)).r() != 0 || ((Pet)this.v.elementAt(n2)).r() == 0 && !((Pet)this.v.elementAt(n2)).S()) {
                ++n2;
            }
            if (this.d[0].S()) {
                this.S.b(this.d[this.e[n2]], this.d[0]);
                return;
            }
            this.S.b(this.d[this.e[n2]], this.d[1]);
        }
    }

    public final void g() {
        this.S = game.ScriptEngine.getInstance();
        this.S.a(this);
        this.R = UIManager.getInstance();
        if (this.a == 0) {
            this.S.a(this.d[1], this.d[0]);
            return;
        }
        this.S.a(this.d[2], this.d[0]);
    }

    public final void a(int n2, int n3) {
        this.d[n2] = this.c((int)this.f[n3]);
        this.d[n2].d(true);
        this.d[n2].f(0);
        this.d[n2].n = 0;
        this.d[n2].b(an[this.r][n2][0], an[this.r][n2][1]);
        this.d[n2].c();
    }

    private void m(int n2) {
        this.d[n2] = new Pet();
        this.d[n2].a(this.s[this.u[0]][0], this.s[this.u[0]][1], (short)-1, (byte)2, (short)this.s[this.u[0]][2], (byte)-1);
        this.d[n2].f(1);
        this.d[n2].n = 1;
        this.d[n2].b(an[this.r][n2][0], an[this.r][n2][1]);
        short s2 = GameDatabase.gameDatabase[0][this.s[this.u[0]][0]][1];
        this.d[n2].g((byte)(s2 * 10));
        this.d[n2].G();
        this.d[n2].c();
        p.a((byte)this.d[n2].j((byte)1), this.d[n2].q(), (byte)1);
        this.u[0] = (byte)(this.u[0] + 1);
    }

    private void n() {
        this.J = this.I;
        this.O = ao[this.h.D];
        if (this.O[this.J * 7 + 1] == 1) {
            short s2;
            short s3;
            short s4;
            short s5;
            short s6;
            this.H = new SkillEffect();
            if (this.O[this.J * 7] == 0) {
                s6 = (short)((Pet)this.h.p).i;
                s5 = (short)((Pet)this.h.p).j;
                s4 = (short)((Pet)this.h.p).q();
                s4 = GameDatabase.gameDatabase[0][s4][17];
                s3 = ((Pet)this.h.p).p();
                s2 = ((Pet)this.h.p).n;
            } else {
                s6 = (short)this.h.i;
                s5 = (short)this.h.j;
                s4 = (short)this.h.q();
                s4 = GameDatabase.gameDatabase[0][s4][17];
                s3 = this.h.p();
                s2 = this.h.n;
            }
            short[] shortArray = m[this.O[this.J * 7 + 2]];
            short[] shortArray2 = new short[shortArray.length + 5];
            System.arraycopy(shortArray, 1, shortArray2, 6, shortArray.length - 1);
            short[] sArray3 = new short[]{shortArray[0], s6, s5, s4, s3, s2};
            System.arraycopy(sArray3, 0, shortArray2, 0, sArray3.length);
            this.H.a(shortArray2);
            this.H.c(true);
        } else if (this.O[this.J * 7] == 0) {
            ((Pet)this.h.p).a((short)this.O[this.J * 7 + 2], this.O[this.J * 7 + 3]);
        } else {
            this.h.a((short)this.O[this.J * 7 + 2], this.O[this.J * 7 + 3]);
        }
        this.I = (byte)(this.I + 1);
    }

    private boolean a(Pet b2) {
        while (!this.b(b2)) {
            for (int i = 0; i < this.ai[this.ag[this.ad << 1]].length; ++i) {
                if (this.ai[this.ag[this.ad << 1]][i] != this.ag[(this.ad << 1) + 1]) continue;
                return false;
            }
            if (this.ag[this.ad << 1] == 0) {
                b2.o(this.ag[(this.ad << 1) + 1]);
                b2.d(this.ag[(this.ad << 1) + 1], this.ah[this.ad]);
            } else if (this.ag[this.ad << 1] == 1) {
                b2.q(this.ag[(this.ad << 1) + 1]);
                b2.c(this.ag[(this.ad << 1) + 1], this.ah[this.ad]);
            }
            if (b2.r() == 0) {
                this.S.a(b2, false);
                this.S.a(b2);
            } else {
                this.S.b(b2, this.aE);
                this.S.b(b2);
            }
            this.ad = (byte)(this.ad + 1);
        }
        return true;
    }

    private void o() {
        Pet b2 = (Pet)this.v.elementAt(this.i);
        this.ac = this.ab;
        this.ae = this.af[this.ad];
        if (this.ae[this.ac << 2] == 1) {
            this.H = new SkillEffect();
            short s2 = (short)b2.i;
            short s3 = (short)b2.j;
            short s4 = (short)b2.q();
            s4 = GameDatabase.gameDatabase[0][s4][17];
            short s5 = b2.p();
            short s6 = b2.n;
            short[] shortArray = m[this.ae[(this.ac << 2) + 1]];
            short[] shortArray2 = new short[shortArray.length + 5];
            System.arraycopy(shortArray, 1, shortArray2, 6, shortArray.length - 1);
            short[] sArray3 = new short[]{shortArray[0], s2, s3, s4, s5, s6};
            System.arraycopy(sArray3, 0, shortArray2, 0, sArray3.length);
            this.H.a(shortArray2);
            this.H.c(true);
        } else {
            b2.a((short)this.ae[(this.ac << 2) + 1], this.ae[(this.ac << 2) + 2]);
        }
        this.ab = (byte)(this.ab + 1);
    }

    private void a(Pet b2, boolean n2) {
        if (!b2.S()) {
            b2.C();
            b2.D();
            this.S.b(b2);
            this.h(b2);
            this.u[1] = (byte)(this.u[1] + 1);
        }
        if (this.u[1] >= this.s.length) {
            game.BattleScreen.X();
            this.a((byte)8);
            return;
        }
        if (!b2.S()) {
            for (n2 = 0; n2 < this.d.length; ++n2) {
                if (!this.d[n2].m(11) || !this.d[this.d[n2].v[11][1]].equals(b2)) continue;
                this.d[n2].n(11);
            }
            if (this.u[0] < this.s.length) {
                this.m(this.e[this.i]);
                this.g = this.e[this.i];
                this.a((byte)15);
                return;
            }
            this.i = (byte)(this.i + 1);
            this.p();
            return;
        }
        if (n2 != 0) {
            this.a((byte)2);
        }
    }

    private void b(Pet b2, boolean n2) {
        int n3;
        for (n3 = 0; n3 < this.f.length && !this.c((int)this.f[n3]).S(); ++n3) {
        }
        if (n3 >= this.f.length) {
            this.a((byte)9);
            return;
        }
        if (!b2.S()) {
            for (n2 = 0; n2 < this.d.length; ++n2) {
                if (!this.d[n2].m(11) || !this.d[this.d[n2].v[11][1]].equals(b2)) continue;
                this.d[n2].n(11);
            }
            b2.C();
            b2.D();
            this.S.a(b2);
            x.removeElement(b2);
            j.removeElement(b2);
            b2.B = 0;
            b2.d(false);
            b2.F = 0;
            if (this.r()) {
                this.g = this.e[this.i];
                this.a((byte)5);
                return;
            }
            this.i = (byte)(this.i + 1);
            this.p();
            return;
        }
        if (n2 != 0) {
            if (b2.p(9)) {
                this.a((byte)2);
                return;
            }
            this.a((byte)20);
        }
    }

    private boolean b(Pet b2) {
        if (this.ad >= this.af.length) {
            this.ad = 0;
            this.ab = 0;
            this.ac = 0;
            this.af = null;
            this.ag = null;
            this.ah = null;
            this.H = null;
            if (this.battlePhase == 12) {
                this.a(b2, true);
            } else if (this.battlePhase == 13) {
                this.b(b2, true);
            }
            return true;
        }
        return false;
    }

    private void c(Pet b2) {
        this.ad = (byte)(this.ad + 1);
        if (!this.b(b2)) {
            if (this.a(b2)) {
                return;
            }
            this.ab = 0;
            this.ac = 0;
            this.o();
            if (this.battlePhase == 12) {
                if (this.g(b2) == 2) {
                    this.ad = 0;
                    this.af = null;
                    this.ag = null;
                    this.ah = null;
                }
                this.a(b2, false);
                return;
            }
            if (this.battlePhase == 13) {
                if (this.g(b2) == 1) {
                    this.ad = 0;
                    this.af = null;
                    this.ag = null;
                    this.ah = null;
                }
                this.b(b2, false);
            }
        }
    }

    private void n(int n2) {
        this.t = new byte[n2];
        this.e = new byte[n2];
        for (n2 = 0; n2 < this.t.length; ++n2) {
            this.t[n2] = (byte)n2;
        }
    }

    private void e(byte val) {
        switch (val) {
            case 0: {
                this.aj.a(val, (byte)0, true);
                break;
            }
            case 1: {
                this.d[0].b(false);
                short s2 = GameDatabase.gameDatabase[0][((Pet)this.h.p).q()][17];
                short[] shortArray = new short[]{8, (short)((Pet)this.h.p).i, (short)((Pet)this.h.p).j, s2, 0, ((Pet)this.h.p).n, 0, 9, 1, 3, 0, 10, 0, 0, 7, 0, -10, 4, 0, -20};
                this.H = new SkillEffect();
                this.H.a(shortArray);
                this.H.c(true);
                this.H.a();
                this.aj.a(val, (byte)-2, true);
                break;
            }
            case 2: {
                this.aj.a(val, (byte)0, true);
                break;
            }
            case 3: {
                this.aj.a(val, (byte)-2, true);
                break;
            }
            case 4: {
                short s3 = GameDatabase.gameDatabase[0][((Pet)this.h.p).q()][17];
                short[] shortArray = new short[]{8, (short)((Pet)this.h.p).i, (short)((Pet)this.h.p).j, s3, 0, ((Pet)this.h.p).n, 0, 8, 1, 4, 1, 4, 0, -20, 6, 0, -12, 8, 0, -4, 10, 0, 0};
                this.H = new SkillEffect();
                this.H.a(shortArray);
                this.H.c(true);
                this.H.a();
                this.aj.a((byte)1, (byte)-2, true);
            }
        }
        this.q = val;
    }

    private void a(int n2, boolean flag2) {
        this.al[this.d.length + 1].b(flag2);
        this.al[this.d.length + 1].b(am[this.a][(n2 << 2) + 2], am[this.a][(n2 << 2) + 3]);
    }

    private void b(int n2, boolean flag2) {
        this.al[this.d.length].b(flag2);
        this.al[this.d.length].b(am[this.a][(n2 << 2) + 2], am[this.a][(n2 << 2) + 3]);
    }

    public final void a(byte val) {
        this.Q = this.battlePhase;
        this.battlePhase = val;
        switch (val) {
            case 0: {
                this.i = 0;
                while (((Pet)this.v.elementAt(this.i)).r() != 0) {
                    this.i = (byte)(this.i + 1);
                }
                break;
            }
            case 15: {
                this.S.a = 0;
                this.y = true;
                this.G = this.g;
                this.E[this.G] = 0;
                this.v.setElementAt(this.d[this.g], this.t[this.g]);
                this.d[this.g].J = true;
                this.b(this.g, false);
                this.i = (byte)(this.i + 1);
                return;
            }
            case 20: {
                this.h = (Pet)this.v.elementAt(this.i);
                for (val = 0; val < this.d.length; val = (byte)(val + 1)) {
                    if (this.d[val].r() != 1 || !this.d[val].S()) continue;
                    this.S.b(this.d[val], false);
                    this.S.b(this.d[val]);
                }
                this.b(this.e[this.i], true);
                this.S.c(this.h);
                if (this.d[0].S()) {
                    this.S.b(this.h, this.d[0]);
                    return;
                }
                this.S.b(this.h, this.d[1]);
                return;
            }
            case 1: {
                if (this.i >= this.v.size()) {
                    this.i = 0;
                }
                this.h = (Pet)this.v.elementAt(this.i);
                while (this.h.J || !this.h.S()) {
                    this.i = (byte)(this.i + 1);
                    if (this.h.J) {
                        this.h.J = false;
                    }
                    if (this.i >= this.v.size()) {
                        this.w = true;
                        this.i = 0;
                        break;
                    }
                    this.h = (Pet)this.v.elementAt(this.i);
                }
                if (!this.h.p(2) || this.h.r() != 0) break;
                val = 0;
                for (int i = 0; i < this.h.y.length; ++i) {
                    if (this.h.y[i] == 0) continue;
                    val = 1;
                }
                if (val == 0) {
                    this.S.c("Không còn tinh lực, không cách nào chiến đấu");
                    this.i = (byte)(this.i + 1);
                    if (this.i >= this.v.size()) {
                        this.w = true;
                        this.i = 0;
                        return;
                    }
                }
                return;
            }
            case 12: 
            case 13: {
                int n2;
                if (this.h.r() == 0) {
                    this.S.a(this.h, false);
                    this.S.a(this.h);
                } else {
                    this.S.b(this.h, false);
                    this.S.b(this.h);
                }
                Pet b2 = (Pet)this.v.elementAt(this.i);
                if (b2.m(13) || b2.m(14)) {
                    b2.C();
                }
                this.af = new byte[b2.r(0) + b2.r(1)][];
                this.ag = new byte[this.af.length << 1];
                this.ah = new byte[this.af.length];
                int n3 = 0;
                for (n2 = 0; n2 < 3; ++n2) {
                    if (b2.x[0][n2] == -1) continue;
                    this.ah[n3] = (byte)n2;
                    this.af[n3] = ap[ar[0][b2.x[0][n2]]];
                    this.ag[n3 << 1] = 0;
                    this.ag[(n3 << 1) + 1] = b2.x[0][n2];
                    ++n3;
                }
                for (n2 = 0; n2 < 3; ++n2) {
                    if (b2.x[1][n2] == -1) continue;
                    this.ah[n3] = (byte)n2;
                    this.af[n3] = aq[ar[1][b2.x[1][n2]]];
                    this.ag[n3 << 1] = 1;
                    this.ag[(n3 << 1) + 1] = b2.x[1][n2];
                    ++n3;
                }
                this.ab = 0;
                this.ac = 0;
                if (this.a(b2)) break;
                this.o();
                return;
            }
            case 2: {
                return;
            }
            case 7: {
                if (this.h.r() == 0) {
                    this.S.a(this.h, false);
                    this.S.a(this.h);
                } else {
                    this.S.b(this.h, false);
                    this.S.b(this.h);
                }
                if (((Pet)this.h.p).r() == 1) {
                    this.S.b((Pet)this.h.p, false);
                    this.S.b((Pet)this.h.p);
                } else {
                    this.S.a((Pet)this.h.p, false);
                    this.S.a((Pet)this.h.p);
                }
                this.z = false;
                this.A = false;
                this.n();
                BattleScreen d2 = this;
                if (d2.h.r() != ((Pet)d2.h.p).r() || d2.h.p(8)) {
                    this.Z = this.h.b((Pet)this.h.p);
                }
                switch (this.h.D) {
                    case 52: 
                    case 58: {
                        if (EngineUtils.randomInt(100) > 30) {
                            this.aa = false;
                            break;
                        }
                    }
                    default: {
                        this.aa = true;
                    }
                }
                if (this.O[this.J * 7] == 0) {
                    this.h.d((byte)1);
                    return;
                }
                this.h.d((byte)0);
                return;
            }
            case 3: {
                this.S.e((Pet)this.v.elementAt(this.i));
                return;
            }
            case 4: {
                this.S.aj();
                return;
            }
            case 6: {
                this.C = 0;
                this.S.b((Pet)this.h.G.elementAt(this.C), false);
                this.S.b(this.h, (Pet)this.h.G.elementAt(this.C));
                this.S.b((Pet)this.h.G.elementAt(this.C));
                this.a(Integer.parseInt((String)this.h.H.elementAt(this.C)), true);
                return;
            }
            case 16: {
                this.S.c = 0;
                this.S.l = false;
                this.S.W();
                return;
            }
            case 5: {
                this.S.c = 0;
                this.S.W();
                return;
            }
            case 17: {
                Pet b3 = this.d[0];
                this.h.p = b3;
                if (this.aj == null) {
                    this.aj = new WorldEntity();
                    this.aj.a(269, false);
                }
                this.aj.b(this.h.i, this.h.j);
                this.aj.c();
                this.e((byte)0);
                this.ak = false;
                int n4 = this.b((int)l);
                this.ak = EngineUtils.randomInt(100) < n4;
                if (U == 0 && V == 5) {
                    this.ak = false;
                }
                this.S.f = 0;
                return;
            }
            case 18: {
                return;
            }
            case 21: {
                Pet b4 = this.d[0];
                this.h.p = b4;
                this.S.ah();
                return;
            }
            case 10: {
                return;
            }
            case 8: {
                game.WorldManager.getInstance().M.l = 0;
                for (int i3 = 0; i3 < x.size(); ++i3) {
                    val = (byte)(((Pet)game.BattleScreen.x.elementAt((int)i3)).A - ((Pet)game.BattleScreen.x.elementAt((int)i3)).d[1]);
                    val = X ? (byte)(val % 20 / 100) : (byte)(val % 50 / 100);
                    if (val <= 0) continue;
                    ((Pet)x.elementAt(i3)).l(val);
                    ((Pet)x.elementAt(i3)).u(((Pet)game.BattleScreen.x.elementAt((int)i3)).d[1]);
                }
                this.al[0].b(am[0][6], am[0][7]);
                this.S.a((int)am[0][4], (int)am[0][5]);
                return;
            }
            case 22: {
                this.S.an();
                return;
            }
            case 9: {
                BattleScreen d3 = this;
                if (game.WorldManager.getInstance().M.i) {
                    d3.a((byte)24);
                } else {
                    for (int i4 = 0; i4 < game.BattleScreen.p.A; ++i4) {
                        game.BattleScreen.p.z[i4].l(1);
                        game.BattleScreen.p.z[i4].u(1);
                        game.BattleScreen.p.z[i4].c();
                    }
                    game.GameStateController.getInstance().a((byte)10);
                }
                game.WorldManager.getInstance().M.l = 1;
                game.WorldManager.getInstance().M.i = true;
                return;
            }
            case 11: {
                this.S.a(4, (byte)0);
                return;
            }
            case 14: {
                return;
            }
            case 23: {
                this.S.ap();
                return;
            }
            case 24: {
                this.S.aE();
                return;
            }
            case 102: {
                this.S.aJ();
                return;
            }
            case 104: {
                this.S.aI();
                return;
            }
            case 101: {
                this.S.aH();
            }
        }
    }

    /*
     * Unable to fully structure code
     */
    public final void b() {
        if (!this.Y) {
            return;
        }
        this.S();
        switch (this.battlePhase) {
            case 0: {
                this.F = (byte)(this.F + 1);
                this.D[this.G] = this.E[this.G];
                this.d[this.G].b(game.BattleScreen.an[this.r][this.G][this.E[this.G] << 2], game.BattleScreen.an[this.r][this.G][(this.E[this.G] << 2) + 1]);
                this.al[this.G].b(game.BattleScreen.an[this.r][this.G][(this.E[this.G] << 2) + 2], game.BattleScreen.an[this.r][this.G][(this.E[this.G] << 2) + 3]);
                if (this.a == 1 && this.E[this.G] > game.BattleScreen.an[this.r][this.G].length / 4 - 3 && this.G % 2 == 0 && this.E.length > this.G + 1) {
                    v0 = this.G + 1;
                    this.E[v0] = (byte)(this.E[v0] + 1);
                    this.D[this.G + 1] = this.E[this.G + 1];
                    this.d[this.G + 1].b(game.BattleScreen.an[this.r][this.G + 1][this.E[this.G + 1] << 2], game.BattleScreen.an[this.r][this.G + 1][(this.E[this.G + 1] << 2) + 1]);
                    this.al[this.G + 1].b(game.BattleScreen.an[this.r][this.G + 1][(this.E[this.G + 1] << 2) + 2], game.BattleScreen.an[this.r][this.G + 1][(this.E[this.G + 1] << 2) + 3]);
                }
                if (this.a == 0) {
                    this.S.a(this.d[1], this.d[0], this.d[this.G], this.E[this.G] + 1, game.BattleScreen.an[this.r][this.G].length / 4);
                }
                if (this.F > 1) {
                    v1 = this.G;
                    this.E[v1] = (byte)(this.E[v1] + 1);
                    this.F = 0;
                }
                if (this.E[this.G] <= game.BattleScreen.an[this.r][this.G].length / 4 - 1) break;
                this.E[this.G] = (byte)(game.BattleScreen.an[this.r][this.G].length / 4 - 1);
                this.D[this.G] = this.E[this.G];
                ++this.G;
                if (this.G <= this.d.length - 1) break;
                this.G = this.d.length - 1;
                this.a((byte)20);
                break;
            }
            case 15: {
                if (this.a == 0) {
                    this.S.a(this.d[1], this.d[0], this.E[this.G] + 1, game.BattleScreen.an[this.r][this.G].length / 4);
                }
                if (this.F > 0) {
                    v2 = this.G;
                    this.E[v2] = (byte)(this.E[v2] + 1);
                    this.F = 0;
                } else {
                    this.F = (byte)(this.F + 1);
                    if (this.E[this.G] > game.BattleScreen.an[this.r][this.G].length / 4 - 3) {
                        this.D[this.G] = this.E[this.G];
                    }
                    this.d[this.G].b(game.BattleScreen.an[this.r][this.G][this.E[this.G] << 2], game.BattleScreen.an[this.r][this.G][(this.E[this.G] << 2) + 1]);
                }
                if (this.E[this.G] <= game.BattleScreen.an[this.r][this.G].length / 4 - 1) break;
                this.D[this.G] = this.E[this.G] = (byte)(game.BattleScreen.an[this.r][this.G].length / 4 - 1);
                var1_1 = true;
                if (!this.k) ** GOTO lbl60
                while (!(this.i >= this.v.size() || ((Pet)this.v.elementAt(this.i)).S() && ((Pet)this.v.elementAt(this.i)).r() == 0)) {
                    this.i = (byte)(this.i + 1);
                }
                if (this.i >= this.v.size()) {
                    this.i = 0;
                    this.a((byte)1);
                } else if (game.BattleScreen.d((Pet)this.v.elementAt(this.i))) {
                    this.a((byte)13);
                } else {
                    var1_1 = false;
                    this.a((byte)20);
                }
                this.k = false;
                ** GOTO lbl127
lbl60:
                // 1 sources

                if (this.a == 0) {
                    this.i = (byte)this.v.size();
                }
                if (this.i < this.v.size()) ** GOTO lbl107
                for (var2_9 = 0; var2_9 < this.d.length; ++var2_9) {
                    this.d[var2_9].J = false;
                }
                if (this.y) {
                    this.T();
                    this.y = false;
                }
                if (this.Q == 12 || this.Q == 13) {
                    this.i = 0;
                    while (((Pet)this.v.elementAt(this.i)).r() != 0 || ((Pet)this.v.elementAt(this.i)).r() == 0 && !((Pet)this.v.elementAt(this.i)).S()) {
                        this.i = (byte)(this.i + 1);
                    }
                    if (game.BattleScreen.d((Pet)this.v.elementAt(this.i))) {
                        this.a((byte)13);
                    } else {
                        var1_1 = false;
                        this.a((byte)20);
                    }
                } else {
                    var2_9 = 0;
                    if (this.h.m(12) && this.h.K[12] == 2) {
                        this.h.K[12] = (short)(this.h.K[12] - 1);
                        if (!((Pet)this.h.p).S()) {
                            var2_9 = 1;
                            this.h.K[12] = (short)(this.h.K[12] - 1);
                        } else {
                            this.i = (byte)(this.i - 1);
                            this.a((byte)2);
                        }
                    } else {
                        var3_15 = EngineUtils.randomInt(100);
                        if ((this.h.D == 63 || this.h.D == 69) && var3_15 <= GameDatabase.gameDatabase[1][this.h.D][8] && ((Pet)this.h.p).S()) {
                            this.i = (byte)(this.i - 1);
                            this.a((byte)2);
                        } else {
                            var2_9 = 1;
                        }
                    }
                    if (var2_9 != 0) {
                        this.i = 0;
                        while (((Pet)this.v.elementAt(this.i)).r() != 0 || ((Pet)this.v.elementAt(this.i)).r() == 0 && !((Pet)this.v.elementAt(this.i)).S()) {
                            this.i = (byte)(this.i + 1);
                        }
                        if (game.BattleScreen.d((Pet)this.v.elementAt(this.i))) {
                            this.a((byte)13);
                        } else {
                            var1_1 = false;
                            this.a((byte)20);
                        }
                    }
                }
                ** GOTO lbl127
lbl107:
                // 1 sources

                if (this.Q != 13 && this.Q != 12) ** GOTO lbl110
                this.a((byte)1);
                ** GOTO lbl127
lbl110:
                // 1 sources

                var2_9 = 0;
                if (!this.h.m(12) || this.h.K[12] != 2) ** GOTO lbl119
                this.h.K[12] = (short)(this.h.K[12] - 1);
                if (((Pet)this.h.p).S()) ** GOTO lbl116
                this.h.K[12] = (short)(this.h.K[12] - 1);
                ** GOTO lbl-1000
lbl116:
                // 1 sources

                this.i = (byte)(this.i - 1);
                this.a((byte)2);
                ** GOTO lbl125
lbl119:
                // 1 sources

                var3_15 = EngineUtils.randomInt(100);
                if ((this.h.D == 63 || this.h.D == 69) && var3_15 <= GameDatabase.gameDatabase[1][this.h.D][8] && ((Pet)this.h.p).S()) {
                    this.i = (byte)(this.i - 1);
                    this.a((byte)2);
                } else lbl-1000:
                // 2 sources

                {
                    var2_9 = 1;
                }
lbl125:
                // 3 sources

                if (var2_9 != 0) {
                    this.a((byte)1);
                }
lbl127:
                // 9 sources

                if (!var1_1) break;
                this.e();
                if (this.d[this.g].r() == 0) {
                    this.S.a(this.d[this.g], false);
                    this.S.a(this.d[this.g]);
                    break;
                }
                this.S.b(this.d[this.g], false);
                this.S.b(this.d[this.g]);
                break;
            }
            case 1: {
                if (this.S.ay()) {
                    if (this.h.r() == 1 && this.b == 0 && (this.h.q() == 33 || this.h.q() == 59) && this.h.d[1] < this.h.c[1]) {
                        this.a((byte)10);
                        return;
                    }
                    if (this.w) {
                        if (this.y) {
                            this.T();
                            this.i = 0;
                            this.y = false;
                        }
                        this.i = 0;
                        while (((Pet)this.v.elementAt(this.i)).r() != 0 || ((Pet)this.v.elementAt(this.i)).r() == 0 && !((Pet)this.v.elementAt(this.i)).S()) {
                            this.i = (byte)(this.i + 1);
                        }
                        if (game.BattleScreen.d((Pet)this.v.elementAt(this.i))) {
                            this.a((byte)13);
                        } else {
                            this.a((byte)20);
                        }
                        this.w = false;
                    } else if (this.h.r() == 1) {
                        if (game.BattleScreen.d(this.h)) {
                            this.a((byte)12);
                        } else {
                            this.a((byte)2);
                        }
                    } else {
                        this.a((byte)2);
                    }
                }
                this.S.g();
                this.al[this.d.length].a();
                break;
            }
            case 20: {
                this.al[this.d.length].a();
                this.S.d(this.h);
                break;
            }
            case 12: 
            case 13: {
                var1_2 = this;
                var2_10 = (Pet)var1_2.v.elementAt(var1_2.i);
                if (var2_10.u == null) ** GOTO lbl194
                if (!var2_10.u.i()) ** GOTO lbl193
                if (!var2_10.u.a.e()) ** GOTO lbl190
                var2_10.u.b();
                var2_10.u = null;
                if (var1_2.ab <= var1_2.ae.length / 4 - 1) ** GOTO lbl187
                if (var1_2.H == null) {
                    var1_2.A = true;
                }
                ** GOTO lbl194
lbl187:
                // 1 sources

                var1_2.o();
                if (var2_10.u == null) ** GOTO lbl194
                ** GOTO lbl193
lbl190:
                // 1 sources

                if (var1_2.ae[(var1_2.ac << 2) + 3] != -1 && var2_10.u.a((int)var1_2.ae[(var1_2.ac << 2) + 3]) && var1_2.ab < var1_2.ae.length / 4) {
                    var1_2.o();
                }
                ** GOTO lbl194
lbl193:
                // 2 sources

                var2_10.u.a();
lbl194:
                // 5 sources

                if (var1_2.H != null && !var1_2.H.i()) {
                    var1_2.H.a();
                    var2_10.b(false);
                }
                if (var1_2.H != null && var1_2.H.i() && !var1_2.H.e()) {
                    var1_2.H = null;
                    var2_10.b(true);
                    if (var1_2.ab > var1_2.ae.length / 4 - 1) {
                        var1_2.A = true;
                    } else {
                        var1_2.o();
                    }
                }
                if (!var1_2.A) break;
                var3_16 = var2_10;
                var2_10 = var1_2;
                var4_19 = false;
                var5_20 = var3_16.N();
                var6_21 = 0;
                if (!var2_10.aE) {
                    if (var2_10.ag[var2_10.ad << 1] == 0) {
                        var6_21 = var3_16.o(var2_10.ag[(var2_10.ad << 1) + 1]);
                        var3_16.d(var2_10.ag[(var2_10.ad << 1) + 1], var2_10.ah[var2_10.ad]);
                    } else if (var2_10.ag[var2_10.ad << 1] == 1) {
                        var3_16.q(var2_10.ag[(var2_10.ad << 1) + 1]);
                        var3_16.c(var2_10.ag[(var2_10.ad << 1) + 1], var2_10.ah[var2_10.ad]);
                    }
                    if (var3_16.d[1] < var5_20) {
                        super.a("" + (var3_16.d[1] - var5_20), (byte)0, 0, var3_16.r(), var3_16.i, var3_16.j, 9, 12);
                    }
                    if (var6_21 > 0) {
                        super.a("+" + var6_21, (byte)0, 2, var3_16.r(), var3_16.i, var3_16.j, 9, 12);
                    }
                    var2_10.aE = true;
                    var2_10.S.k = 0;
                    if (var3_16.r() == 0) {
                        var2_10.S.a(var3_16);
                    } else {
                        var2_10.S.b(var3_16);
                    }
                }
                var6_21 = super.V();
                if (var3_16.r() == 0) {
                    if (var5_20 < var3_16.d[1]) {
                        if (var2_10.S.a(var3_16, true) && var6_21 != 0) {
                            var2_10.aE = false;
                            var4_19 = true;
                            super.c(var3_16);
                        }
                    } else if (var2_10.S.a(var3_16, false) && var6_21 != 0) {
                        var2_10.aE = false;
                        var4_19 = true;
                        super.c(var3_16);
                    }
                } else if (var5_20 < var3_16.d[1]) {
                    if (var2_10.S.b(var3_16, true) && var6_21 != 0) {
                        var2_10.aE = false;
                        var4_19 = true;
                        super.c(var3_16);
                    }
                } else if (var2_10.S.b(var3_16, false) && var6_21 != 0) {
                    var2_10.aE = false;
                    var4_19 = true;
                    super.c(var3_16);
                }
                if (!var4_19) break;
                var1_2.A = false;
                break;
            }
            case 2: {
                if (this.h.r() == 1) {
                    var1_3 = (byte)game.BattleScreen.e(this.h);
                    if (this.h.p(9)) {
                        this.f(this.h);
                    } else {
                        var2_11 = 1;
                        if (game.Pet.b(var1_3, (byte)9) == 0 && this.h.p(8) && EngineUtils.randomInt(100) > GameDatabase.spriteTable((byte)1, var1_3, (byte)8)) {
                            this.f(this.h);
                            var2_11 = 0;
                        }
                        if (var2_11 != 0) {
                            this.b(var1_3);
                        }
                    }
                    var2_11 = EngineUtils.a(this.h.G.size());
                    var3_17 = (Pet)this.h.G.elementAt(var2_11);
                    this.h.I = Byte.parseByte((String)this.h.H.elementAt(var2_11));
                    this.S.b(this.h, var3_17);
                    this.h.a(var1_3, var3_17);
                    this.a((byte)7);
                    break;
                }
                if (this.h.p(9)) {
                    this.f(this.h);
                    var1_4 = EngineUtils.a(this.h.G.size());
                    var2_12 = (Pet)this.h.G.elementAt(var1_4);
                    this.h.I = Byte.parseByte((String)this.h.H.elementAt(var1_4));
                    var3_18 = (byte)game.BattleScreen.e(this.h);
                    this.h.a(var3_18, var2_12);
                    this.S.b(this.h, var2_12);
                    this.a((byte)7);
                    break;
                }
                var1_5 = true;
                if (game.Pet.b(this.h.H(), (byte)9) == 0 && this.h.p(8) && EngineUtils.randomInt(100) > GameDatabase.spriteTable((byte)1, this.h.H(), (byte)8)) {
                    this.f(this.h);
                    var1_5 = false;
                }
                if (var1_5) {
                    if ((Pet)this.h.p != null && !((Pet)this.h.p).S()) {
                        for (var2_13 = 0; var2_13 < this.h.G.size(); ++var2_13) {
                            if (!((Pet)this.h.G.elementAt(var2_13)).S()) continue;
                            this.h.I = Byte.parseByte((String)this.h.H.elementAt(var2_13));
                            this.h.a(this.h.H(), (Pet)this.h.G.elementAt(var2_13));
                        }
                    } else {
                        this.h.a(this.h.H(), (Pet)this.h.p);
                    }
                }
                this.S.b(this.h, (Pet)this.h.p);
                this.a((byte)7);
                break;
            }
            case 7: {
                if (this.h.u != null) {
                    if (this.h.p() == 0) {
                        if (this.h.u.i()) {
                            if (this.h.u.a.e()) {
                                this.h.u.b();
                                this.h.u = null;
                                if (this.I > this.O.length / 7 - 1 || this.W()) {
                                    this.A = true;
                                    this.B = true;
                                } else {
                                    this.n();
                                    if (this.h.u != null) {
                                        this.h.u.a();
                                    }
                                    if (this.H != null) {
                                        this.M = true;
                                    }
                                }
                            } else if (this.O[this.J * 7 + 4] != -1 && this.h.u.a((int)this.O[this.J * 7 + 4])) {
                                this.h.u.b();
                                if (this.I < this.O.length / 7 - 1 || this.W()) {
                                    this.n();
                                    if (this.H != null) {
                                        this.M = true;
                                    }
                                }
                            } else if (this.O[this.J * 7 + 5] != -1 && this.h.u.a((int)this.O[this.J * 7 + 5])) {
                                this.h.d(this.O[this.J * 7 + 6]);
                            }
                        } else {
                            this.N = false;
                            this.h.u.a();
                        }
                    } else if (this.h.p() == 1 && this.h.a.e()) {
                        this.h.d((byte)0);
                    }
                } else if (((Pet)this.h.p).u != null) {
                    if (((Pet)this.h.p).u.i()) {
                        if (((Pet)this.h.p).u.d()) {
                            ((Pet)this.h.p).u.b();
                            if (this.I > this.O.length / 7 - 1 || this.W()) {
                                ((Pet)this.h.p).d((byte)2);
                            } else if (this.O[this.I * 7] == 1) {
                                ((Pet)this.h.p).d((byte)2);
                            } else {
                                this.K = 0;
                                ((Pet)this.h.p).u = null;
                                this.n();
                                if (((Pet)this.h.p).u != null) {
                                    ((Pet)this.h.p).u.a();
                                }
                                if (this.H != null) {
                                    this.M = true;
                                }
                            }
                        } else {
                            if (this.O[this.J * 7 + 5] != -1) {
                                this.K = this.J;
                            }
                            if (this.O[this.K * 7 + 5] != -1 && ((Pet)this.h.p).u.a((int)this.O[this.K * 7 + 5])) {
                                ((Pet)this.h.p).d(this.O[this.K * 7 + 6]);
                                this.K = 0;
                            }
                            if (this.O[this.J * 7 + 4] != -1 && ((Pet)this.h.p).u.a((int)this.O[this.J * 7 + 4])) {
                                this.n();
                                if (this.H != null) {
                                    this.M = true;
                                }
                            }
                        }
                    } else if (this.h.p() == 1 && this.h.b() || this.N) {
                        this.h.d((byte)0);
                        ((Pet)this.h.p).u.a();
                        this.N = false;
                    } else if (((Pet)this.h.p).p() == 2 && ((Pet)this.h.p).b()) {
                        this.z = true;
                        ((Pet)this.h.p).u = null;
                        if (this.I > this.O.length / 7 - 1 || this.W()) {
                            this.A = true;
                        } else {
                            this.n();
                            if (this.H != null) {
                                this.M = true;
                            }
                        }
                    }
                }
                if (this.H != null && !this.H.i() && (this.h.p() == 1 && this.h.b() || this.M || this.h.p() == 0)) {
                    if (this.J == 0) {
                        this.N = true;
                    }
                    this.h.d((byte)0);
                    this.H.a();
                    this.L = this.J;
                    if (this.O[this.J * 7] == 0) {
                        ((Pet)this.h.p).b(false);
                    } else {
                        this.h.b(false);
                    }
                }
                if (this.H != null && this.H.i() && !this.H.e()) {
                    this.H = null;
                    this.M = false;
                    if (this.O[this.L * 7] == 0) {
                        ((Pet)this.h.p).b(true);
                    } else {
                        this.h.b(true);
                    }
                    if (((Pet)this.h.p).u == null && this.h.u == null) {
                        if (this.I > this.O.length / 7 - 1 || this.W()) {
                            if (this.O[this.J * 7] == 0) {
                                this.z = true;
                            }
                            this.B = true;
                            this.A = true;
                        } else {
                            if (this.O[this.I * 7] == 1) {
                                this.z = true;
                            }
                            this.n();
                            if (this.H != null) {
                                this.M = true;
                            }
                        }
                    }
                    this.L = 0;
                }
                if (!this.z) ** GOTO lbl465
                this.B = false;
                var1_6 = this;
                if (GameDatabase.gameDatabase[1][var1_6.h.D][3] != 0) ** GOTO lbl416
                var1_6.S.k = 0;
                if (((Pet)var1_6.h.p).r() == 0) {
                    var1_6.S.a((Pet)var1_6.h.p);
                } else {
                    var1_6.S.b((Pet)var1_6.h.p);
                }
                ((Pet)var1_6.h.p).d((byte)0);
                v3 = true;
                ** GOTO lbl462
lbl416:
                // 1 sources

                if (((Pet)var1_6.h.p).p() != 3) ** GOTO lbl419
                v3 = true;
                ** GOTO lbl462
lbl419:
                // 1 sources

                if (!var1_6.aE) {
                    var2_14 = var1_6.h.s();
                    if (var1_6.h.r() == 0 && game.BattleScreen.p.c((byte)4, (byte)0) == 2 && game.BattleScreen.p.c((byte)4, (byte)1) == 1) {
                        var2_14 += GameDatabase.gameDatabase[2][4][6];
                    }
                    var2_14 = var1_6.h.m(4) != false ? ((Pet)var1_6.h.p).s() - (var2_14 - var1_6.h.w[4][1]) << 1 : ((Pet)var1_6.h.p).s() - var2_14 << 1;
                    if (var1_6.h.f((byte)9)) {
                        var2_14 = 0;
                    }
                    if (var2_14 <= 0) {
                        var2_14 = 0;
                    } else if (var2_14 >= 20) {
                        var2_14 = 20;
                    }
                    if (EngineUtils.randomInt(100) >= var2_14) {
                        ((Pet)var1_6.h.p).k(var1_6.Z[0]);
                        if (var1_6.h.f((byte)10) && ((Pet)var1_6.h.p).a((byte)1) <= GameDatabase.gameDatabase[3][10][5]) {
                            ((Pet)var1_6.h.p).a((byte)1, GameDatabase.gameDatabase[3][10][5]);
                        }
                        if (var1_6.Z[1] == 1) {
                            var1_6.a("-" + var1_6.Z[0], (byte)0, 1, ((Pet)var1_6.h.p).r(), ((Pet)var1_6.h.p).l(), ((Pet)var1_6.h.p).m(), 15, 19);
                        } else {
                            var1_6.a("-" + var1_6.Z[0], (byte)0, 0, ((Pet)var1_6.h.p).r(), ((Pet)var1_6.h.p).l(), ((Pet)var1_6.h.p).m(), 9, 12);
                        }
                        if (var1_6.Z[2] != -1) {
                            var1_6.a(BaseScreen.f(GameDatabase.gameDatabase[7][var1_6.Z[2]][0]), (byte)1, 0, ((Pet)var1_6.h.p).r(), ((Pet)var1_6.h.p).l(), ((Pet)var1_6.h.p).m(), 9, 12);
                        }
                    } else {
                        var1_6.a("Né tránh", (byte)1, 0, ((Pet)var1_6.h.p).r(), ((Pet)var1_6.h.p).l(), ((Pet)var1_6.h.p).m(), 9, 12);
                    }
                    var1_6.aE = true;
                    var1_6.S.k = 0;
                    if (((Pet)var1_6.h.p).r() == 0) {
                        var1_6.S.a((Pet)var1_6.h.p);
                    } else {
                        var1_6.S.b((Pet)var1_6.h.p);
                    }
                }
                var2_14 = var1_6.V();
                if (((Pet)var1_6.h.p).r() != 0) ** GOTO lbl456
                if (!var1_6.S.a((Pet)var1_6.h.p, false) || var2_14 == 0) ** GOTO lbl-1000
                var1_6.U();
                var1_6.aE = false;
                v3 = true;
                ** GOTO lbl462
lbl456:
                // 1 sources

                if (var1_6.S.b((Pet)var1_6.h.p, false) && var2_14 != 0) {
                    var1_6.U();
                    var1_6.aE = false;
                    v3 = true;
                } else lbl-1000:
                // 2 sources

                {
                    v3 = false;
                }
lbl462:
                // 5 sources

                if (v3 && (((Pet)this.h.p).S() || this.c((Pet)this.h.p, true))) {
                    this.B = true;
                    this.z = false;
                }
lbl465:
                // 4 sources

                if (!this.B || !this.A || !this.q()) break;
                this.B = false;
                this.A = false;
                break;
            }
            case 3: {
                this.S.f((Pet)this.v.elementAt(this.i));
                break;
            }
            case 4: {
                this.S.ak();
                break;
            }
            case 17: {
                if (this.S.f == 0) {
                    if (this.q == 0 && this.aj.b()) {
                        this.e((byte)1);
                    } else if (this.q == 1 && this.aj.b()) {
                        if (!this.H.e()) {
                            this.e((byte)2);
                        }
                    } else if (this.q == 2 && this.aj.b()) {
                        if (this.ak) {
                            this.e((byte)3);
                        } else {
                            this.e((byte)4);
                        }
                    } else if (this.q == 3 && this.aj.b()) {
                        var1_7 = game.BattleScreen.p.y();
                        if (var1_7 == 0) {
                            this.S.f = 1;
                            this.S.b("Bắt thành công #2" + BaseScreen.f(GameDatabase.gameDatabase[0][((Pet)this.h.p).q()][0]));
                            game.BattleScreen.p.a(((Pet)this.h.p).P());
                        } else if (var1_7 == 1) {
                            this.S.f = 2;
                            this.S.b("Bắt thành công #2" + BaseScreen.f(GameDatabase.gameDatabase[0][((Pet)this.h.p).q()][0]));
                            game.BattleScreen.p.b(((Pet)this.h.p).P());
                        } else {
                            this.S.f = 1;
                            this.S.b("Không còn không gian, sủng vật này đã phóng sinh");
                        }
                    } else if (this.q == 4 && this.aj.b() && !this.H.e()) {
                        this.H = null;
                        this.d[0].b(true);
                        this.aj.d();
                        this.h.J = true;
                        if (this.ak) {
                            this.S.b("Ngân hàng và Ba lô đều đã đầy");
                            this.S.f = 3;
                        } else {
                            this.i = (byte)(this.i + 1);
                            this.a((byte)1);
                        }
                    }
                    this.aj.a();
                    break;
                }
                if (this.S.ax()) {
                    if (this.S.f == 3) {
                        this.S.f = 0;
                        this.h.J = true;
                        this.i = (byte)(this.i + 1);
                        this.a((byte)1);
                    } else if (this.S.f == 2) {
                        this.S.b("Sủng vật ba lô đã đủ, đã để vào ngân hàng");
                        this.S.f = 4;
                    } else if (this.S.f == 4 || this.S.f == 1) {
                        this.S.f = 0;
                        game.WorldManager.getInstance().M.l = (byte)-1;
                        this.l();
                        game.GameStateController.getInstance().a((byte)10);
                    }
                }
                this.S.f();
                break;
            }
            case 16: {
                this.S.al();
                break;
            }
            case 5: {
                this.S.X();
                break;
            }
            case 18: {
                break;
            }
            case 19: {
                if (!this.k(786432)) break;
                this.a((byte)18);
                break;
            }
            case 6: {
                if (this.k(4100)) {
                    if (this.a == 1) {
                        this.C = (byte)(this.C - 1);
                        if (this.C <= 0) {
                            this.C = 0;
                        }
                        this.a(Integer.parseInt((String)this.h.H.elementAt(this.C)), true);
                        this.S.b((Pet)this.h.G.elementAt(this.C), false);
                        this.S.b(this.h, (Pet)this.h.G.elementAt(this.C));
                        this.S.b((Pet)this.h.G.elementAt(this.C));
                    }
                } else if (this.k(8448)) {
                    if (this.a == 1) {
                        this.C = (byte)(this.C + 1);
                        if (this.C >= this.h.G.size() - 1) {
                            this.C = (byte)(this.h.G.size() - 1);
                        }
                        this.a(Integer.parseInt((String)this.h.H.elementAt(this.C)), true);
                        this.S.b((Pet)this.h.G.elementAt(this.C), false);
                        this.S.b(this.h, (Pet)this.h.G.elementAt(this.C));
                        this.S.b((Pet)this.h.G.elementAt(this.C));
                    }
                } else if (this.k(16400)) {
                    if (this.a == 1) {
                        this.C = (byte)(this.C - 1);
                        if (this.C <= 0) {
                            this.C = 0;
                        }
                        this.a(Integer.parseInt((String)this.h.H.elementAt(this.C)), true);
                        this.S.b((Pet)this.h.G.elementAt(this.C), false);
                        this.S.b(this.h, (Pet)this.h.G.elementAt(this.C));
                        this.S.b((Pet)this.h.G.elementAt(this.C));
                    }
                } else if (this.k(32832)) {
                    if (this.a == 1) {
                        this.C = (byte)(this.C + 1);
                        if (this.C >= this.h.G.size() - 1) {
                            this.C = (byte)(this.h.G.size() - 1);
                        }
                        this.a(Integer.parseInt((String)this.h.H.elementAt(this.C)), true);
                        this.S.b((Pet)this.h.G.elementAt(this.C), false);
                        this.S.b(this.h, (Pet)this.h.G.elementAt(this.C));
                        this.S.b((Pet)this.h.G.elementAt(this.C));
                    }
                } else if (this.k(196640)) {
                    this.i();
                } else if (this.k(786432)) {
                    this.a(Integer.parseInt((String)this.h.H.elementAt(this.C)), false);
                    this.a((byte)3);
                }
                this.al[this.d.length].a();
                this.al[this.d.length + 1].a();
                break;
            }
            case 21: {
                this.S.ai();
                break;
            }
            case 10: {
                if (!this.S.j() && this.c(this.h, false)) {
                    this.S.c(game.BattleScreen.f(GameDatabase.gameDatabase[0][this.h.q()][0]) + "Chạy trốn");
                }
                if (!this.S.g()) break;
                game.GameStateController.getInstance().a((byte)10);
                break;
            }
            case 8: {
                this.S.am();
                break;
            }
            case 22: {
                this.S.ao();
                break;
            }
            case 9: {
                break;
            }
            case 11: {
                this.S.a((byte)4, (byte)0);
                break;
            }
            case 14: {
                break;
            }
            case 23: {
                this.S.aq();
                break;
            }
            case 24: {
                this.S.aF();
                break;
            }
            case 101: 
            case 102: 
            case 104: {
                this.S.aM();
            }
        }
        for (var1_8 = 0; var1_8 < this.d.length; ++var1_8) {
            this.d[var1_8].o();
        }
        this.R.c();
    }

    private void a(Graphics g) {
        g.setColor(0xFFFFFF);
        for (int i = 0; i < this.d.length; ++i) {
            this.d[i].a(g);
        }
    }

    private void a(Graphics g, boolean flag2) {
        for (int i = 0; i < this.d.length; ++i) {
            this.al[i].a(g, 0, 0);
        }
        if (flag2) {
            this.al[this.d.length].a(g, 0, 0);
            if (this.a == 1) {
                this.al[this.d.length + 1].a(g, 0, 0);
            }
        }
    }

    private void a(String text, byte val2, int n2, int n3, int n4, int n5, int n6, int n7) {
        this.ay = text;
        this.as = n4;
        this.at = n5;
        this.aw = n2;
        this.ax = n3;
        this.au = n6;
        this.av = n7;
        this.aC.addElement(this.ay);
        this.aB.addElement(new int[]{val2, this.aw, this.ax, -1, this.au, this.av});
    }

    private void c(Graphics g) {
        block4: for (int i = 0; i < this.aB.size(); ++i) {
            int[] intArray = (int[])this.aB.elementAt(i);
            String string = (String)this.aC.elementAt(i);
            switch (intArray[0]) {
                case 0: {
                    if (intArray[2] == 0) {
                        game.BattleScreen.a(g, az[intArray[1]], string, this.as + aA[intArray[0]][intArray[3] << 1] + 30, this.at + aA[intArray[0]][(intArray[3] << 1) + 1] - 30, intArray[4], intArray[5]);
                        continue block4;
                    }
                    game.BattleScreen.a(g, az[intArray[1]], string, this.as - aA[intArray[0]][intArray[3] << 1] - 30, this.at + aA[intArray[0]][(intArray[3] << 1) + 1] - 30, intArray[4], intArray[5]);
                    continue block4;
                }
                case 1: {
                    if (intArray[2] == 0) {
                        EngineUtils.a(g, string, 16704699, this.as - 10, this.at + aA[intArray[0]][(intArray[3] << 1) + 1] - 30, this.R.b, 2);
                        continue block4;
                    }
                    EngineUtils.a(g, string, 16704699, this.as + 10, this.at + aA[intArray[0]][(intArray[3] << 1) + 1] - 30, this.R.b, 2);
                }
            }
        }
    }

    public final void b(Graphics g) {
        if (!this.Y) {
            return;
        }
        if (this.c != null) {
            g.drawImage(this.c, 0, 0, 20);
        } else {
            g.setColor(0);
            g.fillRect(0, 0, BaseScreen.getScreenWidth(), BaseScreen.getScreenHeight());
        }
        switch (this.battlePhase) {
            case 0: {
                this.a(g, false);
                this.a(g);
                break;
            }
            case 12: 
            case 13: {
                this.a(g, false);
                if (this.H != null) {
                    this.H.a(g);
                }
                this.a(g);
                this.c(g);
                break;
            }
            case 1: 
            case 10: {
                this.a(g, false);
                this.a(g);
                break;
            }
            case 20: {
                this.a(g, true);
                this.a(g);
                break;
            }
            case 2: {
                this.a(g, false);
                this.a(g);
                break;
            }
            case 7: {
                this.a(g, false);
                if (this.H != null) {
                    this.H.a(g);
                }
                this.a(g);
                this.c(g);
                break;
            }
            case 15: {
                this.a(g, false);
                this.a(g);
                break;
            }
            case 3: {
                g.setColor(0xFFFFFF);
                break;
            }
            case 4: {
                g.setColor(0xFFFFFF);
                break;
            }
            case 5: {
                g.setColor(0xFFFFFF);
                g.drawString(game.BattleScreen.f(GameDatabase.gameDatabase[0][this.c((int)this.f[0]).q()][0]), game.BattleScreen.w() >> 1, 200, 17);
                break;
            }
            case 17: {
                this.a(g, false);
                this.a(g);
                if (this.H != null && this.H.c()) {
                    this.H.a(g);
                    this.aj.a(g, 0, 0);
                    break;
                }
                this.aj.a(g, 0, 0);
                break;
            }
            case 18: {
                break;
            }
            case 21: {
                break;
            }
            case 6: {
                if (this.a == 1) {
                    this.a(g, true);
                } else {
                    this.a(g, false);
                }
                this.a(g);
                break;
            }
            case 8: {
                this.al[0].a(g, 0, 0);
                if (this.S.i >= j.size()) break;
                ((Pet)j.elementAt(this.S.i)).a(g);
            }
        }
        this.R.a(g);
    }

    public final void h() {
        this.i = (byte)(this.i + 1);
        if (this.i < this.v.size()) {
            while (((Pet)this.v.elementAt(this.i)).r() != 0 || ((Pet)this.v.elementAt(this.i)).r() == 0 && !((Pet)this.v.elementAt(this.i)).S()) {
                this.i = (byte)(this.i + 1);
                if (this.i < this.v.size()) continue;
            }
        }
        if (this.i >= this.v.size()) {
            this.a((byte)1);
            return;
        }
        if (game.BattleScreen.d((Pet)this.v.elementAt(this.i))) {
            this.a((byte)13);
            return;
        }
        this.a((byte)20);
    }

    private void p() {
        if (this.i >= this.v.size()) {
            if (this.y) {
                this.T();
                this.y = false;
            }
            for (int i = 0; i < this.v.size(); ++i) {
                ((Pet)this.v.elementAt((int)i)).J = false;
            }
            this.i = 0;
            while (((Pet)this.v.elementAt(this.i)).r() != 0 || ((Pet)this.v.elementAt(this.i)).r() == 0 && !((Pet)this.v.elementAt(this.i)).S()) {
                this.i = (byte)(this.i + 1);
            }
            if (game.BattleScreen.d((Pet)this.v.elementAt(this.i))) {
                this.a((byte)13);
                return;
            }
            this.a((byte)20);
            return;
        }
        this.a((byte)1);
    }

    private boolean q() {
        int n2;
        int n3;
        BattleScreen d2 = this;
        if (!d2.aE) {
            int n4;
            n3 = 0;
            d2.aF = GameDatabase.gameDatabase[1][d2.h.D][9] == 0 ? d2.h.N() : ((Pet)d2.h.p).N();
            n2 = d2.h.D;
            switch (n2) {
                case 11: 
                case 17: {
                    n3 = (short)(d2.h.B() * GameDatabase.gameDatabase[1][n2][8] / 100);
                    d2.h.u(d2.h.d[1]);
                    if (n3 <= 0) {
                        n3 = 1;
                    }
                    d2.h.l(n3);
                    break;
                }
                case 21: 
                case 27: 
                case 42: 
                case 48: 
                case 62: 
                case 68: {
                    d2.h.a((byte)GameDatabase.gameDatabase[1][n2][7], -1, n2);
                    break;
                }
                case 52: 
                case 58: {
                    if (d2.aa) {
                        n3 = d2.Z[0] * GameDatabase.gameDatabase[1][n2][8] / 100;
                        d2.h.u(d2.h.d[1]);
                        d2.h.l(d2.Z[0] * GameDatabase.gameDatabase[1][n2][8] / 100);
                    }
                }
                case 64: {
                    d2.h.a((byte)GameDatabase.gameDatabase[1][n2][7], (int)d2.h.I, n2);
                    break;
                }
                default: {
                    if (GameDatabase.gameDatabase[1][n2][6] != 1) break;
                    n3 = ((Pet)d2.h.p).a((byte)GameDatabase.gameDatabase[1][n2][7], -1, n2);
                }
            }
            byte by = (byte)GameDatabase.gameDatabase[1][n2][6];
            if (GameDatabase.gameDatabase[1][d2.h.D][9] == 0) {
                if (d2.h.f((byte)8) && EngineUtils.randomInt(100) <= GameDatabase.gameDatabase[3][8][5]) {
                    d2.h.u(d2.h.d[1]);
                    d2.h.l((short)(d2.Z[0] * GameDatabase.gameDatabase[3][8][6] / 100));
                }
                if (((Pet)d2.h.p).m(2)) {
                    n4 = d2.Z[0] * ((Pet)d2.h.p).v[2][2] / 100;
                    d2.h.k(n4);
                }
                if (((Pet)d2.h.p).m(5) && (n4 = d2.h.K[5]) > 0) {
                    d2.h.k(n4);
                    d2.h.K[5] = 0;
                }
                if (d2.h.d[1] < d2.aF) {
                    d2.a("" + (d2.h.d[1] - d2.aF), (byte)0, 0, d2.h.r(), d2.h.i, d2.h.j, 9, 12);
                } else if (n3 > 0) {
                    d2.a("+" + n3, (byte)0, 2, d2.h.r(), d2.h.i, d2.h.j, 9, 12);
                }
            } else if (n3 > 0) {
                d2.a("+" + n3, (byte)0, 2, ((Pet)d2.h.p).r(), ((Pet)d2.h.p).i, ((Pet)d2.h.p).j, 9, 12);
            }
            if (by == 1) {
                n4 = GameDatabase.gameDatabase[1][n2][7];
                switch (n2) {
                    case 21: 
                    case 27: 
                    case 42: 
                    case 48: 
                    case 62: 
                    case 64: 
                    case 68: {
                        d2.a(BaseScreen.f(GameDatabase.gameDatabase[6][n4][0]), (byte)1, 2, d2.h.r(), d2.h.i, d2.h.j, 9, 12);
                        break;
                    }
                    default: {
                        d2.a(BaseScreen.f(GameDatabase.gameDatabase[6][n4][0]), (byte)1, 2, ((Pet)d2.h.p).r(), ((Pet)d2.h.p).i, ((Pet)d2.h.p).j, 9, 12);
                    }
                }
            }
            if (GameDatabase.gameDatabase[1][d2.h.D][9] == 0) {
                d2.S.k = 0;
                if (d2.h.r() == 0) {
                    d2.S.a(d2.h);
                } else {
                    d2.S.b(d2.h);
                }
            } else {
                d2.S.k = 0;
                if (((Pet)d2.h.p).r() == 0) {
                    d2.S.a((Pet)d2.h.p);
                } else {
                    d2.S.b((Pet)d2.h.p);
                }
            }
            d2.aE = true;
        }
        n3 = d2.V();
        if (((Pet)d2.h.p).r() != d2.h.r() || d2.h.p(9) ? d2.a(d2.h, n3 != 0, d2.aF) : d2.a((Pet)d2.h.p, n3 != 0, d2.aF)) {
            this.I = 0;
            this.J = 0;
            for (n3 = 0; n3 < this.f.length && !this.c((int)this.f[n3]).S(); ++n3) {
            }
            if (((Pet)this.h.p).r() == 1 && !((Pet)this.h.p).S() || this.h.r() == 1 && !this.h.S()) {
                ((Pet)this.h.p).C();
                ((Pet)this.h.p).D();
                this.S.b((Pet)this.h.p);
                this.h((Pet)this.h.p);
                this.u[1] = (byte)(this.u[1] + 1);
            } else if (((Pet)this.h.p).r() == 0 && !((Pet)this.h.p).S() || this.h.r() == 0 && !this.h.S()) {
                ((Pet)this.h.p).C();
                ((Pet)this.h.p).D();
                this.S.a((Pet)this.h.p);
                x.removeElement((Pet)this.h.p);
                j.removeElement((Pet)this.h.p);
                ((Pet)this.h.p).B = 0;
                ((Pet)this.h.p).d(false);
                ((Pet)this.h.p).F = 0;
            }
            int n5 = n3 >= this.f.length ? 2 : (this.u[1] >= this.s.length ? 1 : 0);
            switch (n5) {
                case 0: {
                    n5 = 0;
                    if (!((Pet)this.h.p).S() || !this.h.S()) {
                        for (n2 = 0; n2 < this.d.length; ++n2) {
                            if (!this.d[n2].m(11) || !this.d[this.d[n2].v[11][1]].equals((Pet)this.h.p)) continue;
                            this.d[n2].n(11);
                        }
                        if (((Pet)this.h.p).r() == 1 && !((Pet)this.h.p).S() || this.h.r() == 1 && !this.h.S()) {
                            if (this.u[0] < this.s.length) {
                                this.g = this.h.r() == 1 && !this.h.S() ? this.e[this.i] : this.h.I;
                                this.m(this.g);
                                this.a((byte)15);
                            } else {
                                n5 = 1;
                            }
                        } else if (this.r()) {
                            this.g = this.h.r() == 0 && !this.h.S() ? this.e[this.i] : this.h.I;
                            this.a((byte)5);
                        } else {
                            n5 = 1;
                        }
                    } else {
                        n5 = 1;
                    }
                    if (n5 == 0) break;
                    if (this.h.m(12) && this.h.K[12] == 2) {
                        this.h.K[12] = (short)(this.h.K[12] - 1);
                        if (!((Pet)this.h.p).S()) {
                            this.h.K[12] = (short)(this.h.K[12] - 1);
                            this.i = (byte)(this.i + 1);
                            this.p();
                            break;
                        }
                        this.a((byte)2);
                        break;
                    }
                    n2 = EngineUtils.randomInt(100);
                    if ((this.h.D == 63 || this.h.D == 69) && n2 <= GameDatabase.gameDatabase[1][this.h.D][8]) {
                        if (!((Pet)this.h.p).S()) {
                            this.h.K[12] = (short)(this.h.K[12] - 1);
                        } else {
                            this.a((byte)2);
                            break;
                        }
                    }
                    this.i = (byte)(this.i + 1);
                    this.p();
                    break;
                }
                case 1: {
                    game.BattleScreen.X();
                    this.a((byte)8);
                    break;
                }
                case 2: {
                    this.a((byte)9);
                }
            }
            if (p.c((byte)5, (byte)0) == 2 && p.c((byte)5, (byte)1) == 1) {
                for (n5 = 0; n5 < this.d.length; ++n5) {
                    if (this.d[n3].r() != 0 || !this.d[n3].S()) continue;
                    this.d[n3].y();
                }
            }
            return true;
        }
        return false;
    }

    private static boolean d(Pet b2) {
        return b2.r(0) > 0 || b2.r(1) > 0;
    }

    private static int e(Pet b2) {
        byte by = b2.z[0];
        int[] intArray = new int[]{50, 20, 15, 10, 5, 5, 5, 5, 5, 5};
        int n2 = EngineUtils.randomInt(100);
        for (int i = 0; i < b2.z.length; ++i) {
            if (b2.z[i] == -1 || b2.y[i] <= 0 || n2 >= intArray[i]) continue;
            by = b2.z[i];
        }
        return by;
    }

    public final void b(byte val) {
        this.h.G.removeAllElements();
        this.h.H.removeAllElements();
        switch (game.Pet.b(val, (byte)9)) {
            case 1: {
                for (val = 0; val < this.d.length; val = (byte)(val + 1)) {
                    if (this.d[val].r() != this.h.r() || !this.d[val].S()) continue;
                    this.h.G.addElement(this.d[val]);
                    this.h.H.addElement("" + val);
                }
                return;
            }
            case 0: {
                for (val = 0; val < this.d.length; val = (byte)(val + 1)) {
                    if (this.d[val].r() == this.h.r() || !this.d[val].S()) continue;
                    this.h.G.addElement(this.d[val]);
                    this.h.H.addElement("" + val);
                }
                break;
            }
        }
    }

    private void f(Pet b2) {
        b2.G.removeAllElements();
        b2.H.removeAllElements();
        for (int i = 0; i < this.d.length; ++i) {
            if (!this.d[i].S() || this.d[i].equals(b2)) continue;
            b2.G.addElement(this.d[i]);
            b2.H.addElement("" + i);
        }
    }

    public final void i() {
        Pet b2 = (Pet)this.h.G.elementAt(this.C);
        this.h.p = b2;
        this.h.I = Byte.parseByte((String)this.h.H.elementAt(this.C));
        this.h.h(((Pet)this.v.elementAt((int)this.i)).z[this.S.e]);
        this.a(Integer.parseInt((String)this.h.H.elementAt(this.C)), false);
        this.h();
    }

    private int g(Pet b2) {
        if (!b2.S()) {
            if (b2.r() == 0) {
                if (this.r()) {
                    return 1;
                }
            } else if (this.u[0] < this.s.length) {
                return 2;
            }
        }
        return 0;
    }

    private boolean r() {
        int n2 = 0;
        for (int i = 0; i < game.BattleScreen.p.A; ++i) {
            if (!this.c(i).S() || this.c(i).K()) continue;
            ++n2;
        }
        return n2 > 0;
    }

    private void e(int n2, int n3) {
        byte by = this.f[n3];
        this.f[n3] = this.f[n2];
        this.f[n2] = by;
    }

    public final int a(int n2) {
        if (!this.c((int)this.f[n2]).S()) {
            return 0;
        }
        if (this.c((int)this.f[n2]).K()) {
            return 1;
        }
        byte by = this.f[n2];
        --n2;
        while (n2 >= 0) {
            this.f[n2 + 1] = this.f[n2];
            --n2;
        }
        this.f[0] = by;
        for (n2 = 0; n2 < x.size() && !x.elementAt(n2).equals(this.c((int)this.f[0])); ++n2) {
        }
        if (n2 >= x.size()) {
            x.addElement(this.c((int)this.f[0]));
        }
        this.c((int)this.f[0]).J = true;
        this.c((int)this.f[0]).d(true);
        this.h.d(false);
        this.h.F = 0;
        for (n2 = 0; n2 < this.d.length; ++n2) {
            if (!this.d[n2].m(11) || !this.d[this.d[n2].v[11][1]].equals(this.h)) continue;
            this.d[n2].n(11);
        }
        return -1;
    }

    private void T() {
        byte by;
        int n2;
        this.v.removeAllElements();
        int n3 = -1;
        for (n2 = 0; n2 < this.t.length - 1; ++n2) {
            for (int i = n2 + 1; i < this.t.length; ++i) {
                if (this.d[n2].c[4] >= this.d[i].c[4]) continue;
                by = this.t[n2];
                this.t[n2] = this.t[i];
                this.t[i] = by;
            }
        }
        for (n2 = 0; n2 < this.t.length; ++n2) {
            if (!this.d[n2].f((byte)7)) continue;
            n3 = n2;
            this.t[n2] = 0;
            break;
        }
        if (n3 != -1) {
            int n4;
            n2 = 1;
            int[] intArray = new int[this.t.length - 1];
            for (n4 = 0; n4 < this.t.length; ++n4) {
                if (n4 == n3) continue;
                intArray[n2 - 1] = n4;
                this.t[n4] = (byte)n2;
                ++n2;
            }
            for (n4 = 0; n4 < intArray.length - 1; ++n4) {
                for (n3 = n4 + 1; n3 < intArray.length; ++n3) {
                    if (this.d[intArray[n4]].c[4] >= this.d[intArray[n3]].c[4]) continue;
                    by = this.t[intArray[n4]];
                    this.t[intArray[n4]] = this.t[intArray[n3]];
                    this.t[intArray[n3]] = by;
                }
            }
        }
        for (n2 = 0; n2 < this.t.length; ++n2) {
            this.e[this.t[n2]] = (byte)n2;
        }
        for (n2 = 0; n2 < this.e.length; ++n2) {
            this.v.addElement(this.d[this.e[n2]]);
        }
    }

    private void U() {
        if (this.h.f((byte)10) && ((Pet)this.h.p).d[1] <= GameDatabase.gameDatabase[3][10][5]) {
            short s2;
            ((Pet)this.h.p).d[1] = s2 = GameDatabase.gameDatabase[3][10][5];
        }
        if (((Pet)this.h.p).d[1] <= 0) {
            ((Pet)this.h.p).d((byte)3);
            return;
        }
        ((Pet)this.h.p).d((byte)0);
    }

    private boolean c(Pet b2, boolean flag2) {
        if (this.b == 0 && flag2) {
            if (b2.L != null && !b2.L.i()) {
                return true;
            }
        } else {
            int n2 = b2.i;
            this.aD = b2.r() == 0 ? (this.aD -= 10) : (this.aD += 10);
            int n3 = n2 + this.aD;
            if (Math.abs(this.aD) >= 100) {
                b2.d();
                this.aD = 0;
                return true;
            }
            b2.b(n3, b2.j);
        }
        return false;
    }

    private boolean V() {
        int n2;
        for (n2 = 0; n2 < this.aB.size(); ++n2) {
            int[] intArray = (int[])this.aB.elementAt(n2);
            int[] intArray2 = intArray;
            intArray[3] = intArray[3] + 1;
            if (intArray2[3] < aA[intArray2[0]].length / 2) continue;
            this.aB.removeElementAt(n2);
            this.aC.removeElementAt(n2);
            --n2;
        }
        return n2 <= 0;
    }

    private boolean W() {
        if (this.Z != null) {
            if (this.Z[2] != -1) {
                return false;
            }
            return this.O[this.O.length - 1] == this.I;
        }
        return !this.aa;
    }

    private boolean a(Pet b2, boolean flag2, int n2) {
        if (b2.r() == 0) {
            if (n2 < b2.d[1]) {
                if (this.S.a(b2, true) && flag2) {
                    this.aE = false;
                    return true;
                }
            } else if (this.S.a(b2, false) && flag2) {
                this.aE = false;
                return true;
            }
        } else if (n2 < b2.d[1]) {
            if (this.S.b(b2, true) && flag2) {
                this.aE = false;
                return true;
            }
        } else if (this.S.b(b2, false) && flag2) {
            this.aE = false;
            return true;
        }
        return false;
    }

    private static void X() {
        int n2;
        for (n2 = 0; n2 < j.size(); ++n2) {
            if (((Pet)j.elementAt(n2)).S()) {
                ((Pet)j.elementAt(n2)).g(((Pet)game.BattleScreen.j.elementAt((int)n2)).B);
                ((Pet)game.BattleScreen.j.elementAt((int)n2)).B = 0;
                ((Pet)j.elementAt(n2)).d(false);
                continue;
            }
            j.removeElementAt(n2);
            --n2;
        }
        if (p.c((byte)0, (byte)0) == 2 && p.c((byte)0, (byte)1) == 1) {
            for (n2 = 0; n2 < game.BattleScreen.p.A; ++n2) {
                if (!game.BattleScreen.p.z[n2].S()) continue;
                game.BattleScreen.p.z[n2].u(game.BattleScreen.p.z[n2].d[1] + GameDatabase.gameDatabase[0][game.BattleScreen.p.z[n2].q()][5] * GameDatabase.gameDatabase[2][0][6] / 100);
                game.BattleScreen.p.z[n2].l(GameDatabase.gameDatabase[0][game.BattleScreen.p.z[n2].q()][5] * GameDatabase.gameDatabase[2][0][6] / 100);
            }
        }
    }

    private void h(Pet b2) {
        int n2;
        int n3;
        int n4 = b2.s();
        int n5 = ((n4 << 1) * n4 + 50) * this.aG[b2.c[0] - 1] / 10 + 400;
        int n6 = x.size();
        int[] intArray = new int[n6];
        Pet b3 = null;
        byte by = 0;
        for (n3 = 0; n3 < n6; ++n3) {
            b3 = (Pet)x.elementAt(n3);
            if (b3.s() - n4 >= 6) {
                by = this.aI[6];
            } else if (b3.s() - n4 > 0) {
                by = this.aI[b3.s() - n4];
            } else if (b3.s() == n4) {
                by = this.aI[1];
            } else if (b3.s() < n4) {
                by = this.aI[0];
            }
            n2 = n5 / n6 * this.aH[n6 - 1] * by / 1000;
            if (b3.f((byte)5)) {
                n2 = n2 * (GameDatabase.gameDatabase[3][5][5] + 100) / 100;
            }
            b3.B += n2;
            intArray[n3] = n2;
            if (j.contains(b3)) continue;
            j.addElement(b3);
        }
        for (n3 = 0; n3 < game.BattleScreen.p.A; ++n3) {
            if (!this.c(n3).S() || x.contains(this.c(n3))) continue;
            if (p.c((byte)7, (byte)0) == 2) {
                if (b3.s() - n4 >= 6) {
                    by = this.aI[6];
                } else if (b3.s() - n4 > 0) {
                    by = this.aI[b3.s() - n4];
                } else if (b3.s() == n4) {
                    by = this.aI[1];
                } else if (b3.s() < n4) {
                    by = this.aI[0];
                }
                n2 = n5 / n6 * this.aH[n6 - 1] * by / 3000;
                this.c((int)n3).B += n2;
                this.c(n3).c();
                if (j.contains(this.c(n3))) continue;
                j.addElement(this.c(n3));
                continue;
            }
            if (!this.c(n3).f((byte)6)) continue;
            if (b3.s() - n4 >= 6) {
                by = this.aI[6];
            } else if (b3.s() - n4 > 0) {
                by = this.aI[b3.s() - n4];
            } else if (b3.s() == n4) {
                by = this.aI[1];
            } else if (b3.s() < n4) {
                by = this.aI[0];
            }
            n2 = n5 / n6 * this.aH[n6 - 1] * by / 1000;
            this.c((int)n3).B += n2;
            this.c(n3).c();
            if (j.contains(this.c(n3))) continue;
            j.addElement(this.c(n3));
        }
        for (n5 = 0; n5 < x.size(); ++n5) {
            Pet b4 = (Pet)x.elementAt(n5);
            if (b4.K()) continue;
            x.removeElement(b4);
        }
        if (p.k(0)) {
            if (game.BattleScreen.p.I == 0) {
                if (b3.s() >= 30 && ++game.WorldManager.q >= 10) {
                    game.WorldManager.q = 10;
                    return;
                }
            } else if (b3.s() >= 40 && ++game.WorldManager.q >= 30) {
                game.WorldManager.q = 30;
            }
        }
    }

    public final int b(int n2) {
        if (n2 == 0) {
            return 100;
        }
        int n3 = 0;
        if (((Pet)this.h.p).m(1)) {
            n3 = 1;
        }
        if (((Pet)this.h.p).m(2)) {
            n3 = 2;
        }
        if (((Pet)this.h.p).m(10)) {
            n3 = 3;
        }
        if (this.h.f((byte)11)) {
            n3 = 4;
        }
        int n4 = 1;
        short s2 = ((Pet)this.h.p).d[1];
        short s3 = ((Pet)this.h.p).c[1];
        if (s2 <= s3 * 15 / 100) {
            n4 = 85;
        } else if (s2 <= s3 * 50 / 100) {
            n4 = 45;
        } else if (s2 <= s3) {
            n4 = 20;
        }
        n4 = n4 * GameDatabase.gameDatabase[4][n2][6] / 100;
        int[] intArray = new int[]{110, 100, 95, 80, 70};
        n4 = n4 * intArray[((Pet)this.h.p).c[0] - 1] / 100;
        intArray = new int[]{10, 11, 12, 12, 12};
        n4 = n4 * intArray[n3] / 10;
        if (this.h.f((byte)11)) {
            n4 = n4 * (100 + GameDatabase.gameDatabase[3][11][5]) / 100;
        }
        Object[] objectArray = new int[]{1000, 500, 1, 1000};
        n4 = n4 * objectArray[GameDatabase.gameDatabase[0][((Pet)this.h.p).q()][22]] / 1000;
        if (((Pet)this.h.p).s() >= 20 && n4 >= (objectArray = (Object[])new byte[]{0, 15, 35, 65})[n2]) {
            n4 = objectArray[n2];
        }
        if (n4 >= 100) {
            n4 = 100;
        } else if (n4 <= 0) {
            n4 = 1;
        }
        return n4;
    }

    public final Pet c(int n2) {
        if (n2 > this.f.length - 1) {
            return null;
        }
        return game.BattleScreen.p.z[n2];
    }

    public final Pet d(int n2) {
        if (n2 > this.f.length - 1) {
            return null;
        }
        return game.BattleScreen.p.z[this.f[n2]];
    }

    public final void a(int[][] nArray) {
        this.s = intArray;
    }

    public final int j() {
        return this.s.length;
    }

    public final int k() {
        return this.s[0][0];
    }

    public final void l() {
        switch (U) {
            case 0: {
                if (V == 0) {
                    if (this.h == null) break;
                    int n2 = this.d[0].c[1] * 50 / 100;
                    if (this.d[0].d[1] <= n2) {
                        game.BattleScreen.c(0, 1);
                        V = (byte)(V + 1);
                        this.S.c("Di Lặc thỏ thỏ đã bị thương, nhanh sử dụng #2 phong ấn cầu #1 tiến hành bắt được a");
                    }
                    return;
                }
                if (V == 1) {
                    if (!this.S.ay()) break;
                    V = (byte)(V + 1);
                    this.S.a = 1;
                    game.BattleScreen.c(2, 1);
                    game.BattleScreen.c(1, 1);
                    this.S.ag();
                    this.S.c("Hãy nhấn #2nút 5");
                    return;
                }
                if (V == 3) {
                    V = (byte)(V + 1);
                    game.BattleScreen.c(2, 0);
                    game.BattleScreen.c(1, 1);
                    this.S.c("Hãy lựa chọn phong ấn cầu");
                    return;
                }
                if (V == 5) {
                    V = (byte)(V + 1);
                    this.S.c("Đáng tiếc đã bắt trượt, thử dùng loại xịn #2Tất trúng cầu#1 xem sao!");
                    return;
                }
                if (V == 6) {
                    if (!this.S.ay()) break;
                    game.BattleScreen.c(1, 0);
                    this.S.a = 1;
                    this.S.ag();
                    this.S.b = 0;
                    V = (byte)(V + 1);
                    this.a((byte)21);
                    return;
                }
                if (V != 8) break;
                game.BattleScreen.c(1, -1);
                game.BattleScreen.c(0, 0);
                U = (byte)-1;
                V = 0;
                return;
            }
            case 2: {
                int n3;
                if (V == 0) {
                    game.BattleScreen.c(0, 0);
                    V = (byte)(V + 1);
                    this.S.c("Tranh thủ thời gian lựa chọn #2Tất trúng cầu#1 để bắt sủng vật");
                    return;
                }
                if (V != 2) break;
                if (this.h != null && this.d[0].d[1] <= (n3 = this.d[0].c[1] * 50 / 100 + 2)) {
                    V = (byte)(V - 1);
                    this.S.c("Lựa chọn #2Tất trúng cầu#1 để bắt sủng vật");
                }
                if (p.a((byte)1, 29) != 2) break;
                U = (byte)-1;
                V = 0;
                return;
            }
            case 5: {
                if (V != 0) break;
                this.S.a = 1;
                V = (byte)(V + 1);
                this.S.ag();
                this.S.c("Tranh thủ thời gian lựa chọn #2Tất trúng cầu#1 để bắt sủng vật");
            }
        }
    }

    public final void m() {
        switch (U) {
            case 0: {
                if (V != 2 && V != 4 && V != 7) break;
                V = (byte)(V + 1);
                return;
            }
            case 5: {
                if (V == 1) {
                    V = (byte)(V + 1);
                    game.BattleScreen.c(1, 0);
                    game.BattleScreen.c(0, 1);
                    return;
                }
                if (V != 2) break;
                game.BattleScreen.c(0, 0);
                U = (byte)-1;
                V = 0;
            }
        }
    }

    static {
        l = 0;
    }
}

