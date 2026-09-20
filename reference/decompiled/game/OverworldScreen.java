/*
 * Decompiled with CFR 0.152.
 */
package game;

import game.NpcEntity;
import game.BattleScreen;
import game.Player;
import game.GameStateController;
import game.TileMapRenderer;
import game.WorldManager;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Calendar;
import java.util.TimeZone;
import java.util.Vector;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

/**
 * OverworldScreen - Overworld map gameplay screen managing map rendering, player movement, and script triggers.
 */
public final class OverworldScreen
extends BaseScreen {
    private static OverworldScreen instance = null;
    private WorldManager worldManager;
    private Player player;
    private BaseScreen parentScreen;
    public ScriptSequence[] scriptSequences;
    private Vector z;
    private byte A = (byte)-1;
    public byte[][] b;
    private int B = 0;
    public int c = -1;
    private static Vector C;
    public static Vector d;
    private TileMapRenderer mapRenderer = game.TileMapRenderer.getInstance();
    public static boolean e;
    public static boolean f;
    public static boolean g;
    public static boolean h;
    public boolean i = true;
    public static boolean j;
    public boolean k = true;
    public byte l = 0;
    public static byte m;
    private short[] E;
    private byte[] F;
    private short[] G;
    private short[] H;
    private short[][] I;
    private short[][] J;
    private short[] K;
    private short[] L;
    public byte n = 0;
    public static Image o;
    private String[] M = new String[]{"ikon_1", "ikon_2", "ikon_3", "ikon_4", "ikon_5"};
    private int N;
    private int O;
    private int Z = 0;
    private int aa = 0;
    private byte[] ab;
    private String[] ac;
    private int[] ad;
    private int[] ae;
    private String[] af = null;
    private WorldEntity ag = null;
    private int ah = -1;
    private Calendar ai = null;
    private int[] aj;
    public byte p = 0;
    public static String[] q;
    public static String[] r;
    public static short[][] s;
    public static byte t;
    public static byte u;
    private static byte[][] ak;
    private static byte[][] al;

    public OverworldScreen() {
        if (this.worldManager == null) {
            this.worldManager = game.WorldManager.getInstance();
        }
        if (this.player == null) {
            this.player = game.Player.getInstance();
        }
        if (this.b == null) {
            this.b = new byte[127][];
        }
        if (s == null) {
            s = new short[200][2];
        }
        if (this.ai == null) {
            this.ai = Calendar.getInstance(TimeZone.getDefault());
        }
        try {
            int n2;
            Object object = EngineUtils.c(EngineUtils.a("/data/script/bTask.mid"));
            q = new String[((String[][])object).length];
            for (n2 = 0; n2 < ((String[][])object).length; ++n2) {
                System.arraycopy(object[n2], 0, q, n2, object[n2].length);
            }
            object = EngineUtils.c(EngineUtils.a("/data/script/mTask.mid"));
            r = new String[((String[][])object).length];
            for (n2 = 0; n2 < ((String[][])object).length; ++n2) {
                System.arraycopy(object[n2], 0, r, n2, object[n2].length);
            }
            object = EngineUtils.a("/data/script/bqTask.mid");
            ak = EngineUtils.b((InputStream)object);
            al = EngineUtils.b((InputStream)object);
            ((InputStream)object).close();
            return;
        }
        catch (IOException iOException) {
            iOException.printStackTrace();
            return;
        }
    }

    public static OverworldScreen getInstance() {
        if (instance == null) {
            instance = new OverworldScreen();
        }
        return instance;
    }

    public final void a(BaseScreen an2) {
        if (this.parentScreen != null) {
            this.parentScreen = null;
        }
        this.parentScreen = an2;
    }

    /*
     * Unable to fully structure code
     * Could not resolve type clashes
     */
    public final void b() {
        if (this.scriptSequences == null) {
            return;
        }
        ScreenView.getInstance().b();
        this.mapRenderer.d();
        var1_1 = this;
        for (var2_2 = 0; var2_2 < var1_1.a.length; ++var2_2) {
            if (var1_1.a[var2_2].a() != 0 && var1_1.a[var2_2].a() != 4) continue;
            var3_3 = var1_1.a[var2_2].d();
            var4_5 = false;
            switch (var3_3.a()) {
                case 43: {
                    if (game.WorldManager.a((int)var3_3.b()[2], (int)var3_3.b()[3]) != game.WorldManager.a(var1_1.w.f, var1_1.w.g) || var3_3.b()[4] != game.WorldManager.u || !var1_1.a((ScriptCommand)var3_3)) break;
                    game.OverworldScreen.g = true;
                    if (!game.OverworldScreen.h || var1_1.a(-1) != -1) break;
                    var6_7 = var2_2;
                    var5_6 = var1_1;
                    for (var7_12 = 0; var7_12 < var5_6.a.length; ++var7_12) {
                        if (var5_6.a[var7_12].a() == 3 || var6_7 == var7_12 || (var8_13 = var5_6.a[var7_12].d()).a() != 43 || game.WorldManager.a((int)var8_13.b()[2], (int)var8_13.b()[3]) != game.WorldManager.a(var5_6.w.f, var5_6.w.g) || var8_13.b()[4] != game.WorldManager.u || !super.a(var8_13)) continue;
                        v0 = (byte)var7_12;
                        ** GOTO lbl22
                    }
                    v0 = -1;
lbl22:
                    // 2 sources

                    if (var2_2 < v0) break;
                    var4_5 = true;
                    game.OverworldScreen.g = false;
                    var1_1.x.b((byte)0, var1_1.x.n);
                    if (var3_3.b()[1] != 1) break;
                    game.OverworldScreen.s[game.OverworldScreen.u][0] = var3_3.b()[0];
                    break;
                }
                case 44: {
                    if (game.WorldManager.a((int)var3_3.b()[2], (int)var3_3.b()[3]) != game.WorldManager.a(var1_1.w.f, var1_1.w.g) || var3_3.b()[4] != game.WorldManager.u || !var1_1.b((ScriptCommand)var3_3)) break;
                    game.OverworldScreen.g = true;
                    if (!game.OverworldScreen.h || var2_2 < var1_1.a(var2_2)) break;
                    var1_1.x.b((byte)0, var1_1.x.n);
                    game.OverworldScreen.g = false;
                    ** GOTO lbl109
                }
                case 13: {
                    if (!EngineUtils.a(var3_3.b()[0], (int)var3_3.b()[1], (int)var3_3.b()[2], (int)var3_3.b()[3], var1_1.x.i, var1_1.x.j, var1_1.x.a.k())) break;
                    var1_1.x.b((byte)0, var1_1.x.n);
                    ** GOTO lbl109
                }
                case 15: {
                    if (var1_1.b[game.WorldManager.a((int)var3_3.b()[0], (int)var3_3.b()[1])] == null || var1_1.b[game.WorldManager.a((int)var3_3.b()[0], (int)var3_3.b()[1])][var3_3.b()[2]] != 3 && var1_1.b[game.WorldManager.a((int)var3_3.b()[0], (int)var3_3.b()[1])][var3_3.b()[2]] != 4) break;
                    var4_5 = true;
                    break;
                }
                case 16: {
                    if (var3_3.b()[0] != game.WorldManager.u) break;
                    game.OverworldScreen.g = true;
                    if (!game.OverworldScreen.h) break;
                    var1_1.x.b((byte)0, var1_1.x.n);
                    game.OverworldScreen.h = false;
                    ** GOTO lbl109
                }
                case 69: {
                    if (var3_3.b()[0] != game.WorldManager.u) break;
                    ** GOTO lbl109
                }
                case 57: {
                    if (var1_1.x.p == null || ((NpcEntity)var1_1.x.p).t != 0 || ((NpcEntity)var1_1.x.p).v != 11 || ((NpcEntity)var1_1.x.p).I != var3_3.b()[3] || !game.OverworldScreen.h) break;
                    if (var1_1.w.d[var3_3.b()[0]].i == var3_3.b()[1] && var1_1.w.d[var3_3.b()[0]].j == var3_3.b()[2]) {
                        ((NpcEntity)var1_1.x.p).d((byte)0);
                    } else {
                        var4_5 = true;
                    }
                    game.OverworldScreen.h = false;
                    break;
                }
                case 59: {
                    var5_6 = new int[EngineUtils.a(var3_3.c()[0], ',').length];
                    for (var6_8 = 0; var6_8 < ((byte[])var5_6).length; ++var6_8) {
                        var5_6[var6_8] = EngineUtils.b(EngineUtils.a(var3_3.c()[0], ',')[var6_8]);
                        if (var1_1.w.d[var5_6[var6_8]].h() == 0) break;
                    }
                    if (var6_8 < ((byte[])var5_6).length) break;
                    var4_5 = true;
                    break;
                }
                case 61: {
                    var5_6 = new int[EngineUtils.a(var3_3.c()[0], ',').length];
                    for (var6_9 = 0; var6_9 < ((byte[])var5_6).length; ++var6_9) {
                        var5_6[var6_9] = EngineUtils.b(EngineUtils.a(var3_3.c()[0], ',')[var6_9]);
                        if (var1_1.w.d[var5_6[var6_9]].h() == 0) break;
                    }
                    if (var6_9 < ((byte[])var5_6).length) break;
                    var1_1.x.b((byte)0, var1_1.x.n);
                    var4_5 = true;
                    break;
                }
                case 73: {
                    var5_6 = EngineUtils.a(var3_3.c()[1], ',');
                    var6_10 = EngineUtils.a(var3_3.c()[0], ',');
                    for (var3_4 = 0; var3_4 < ((byte[])var5_6).length && var1_1.x.a(EngineUtils.d(var6_10[var3_4]), (int)EngineUtils.d((String)var5_6[var3_4])) >= 2; ++var3_4) {
                    }
                    if (var3_4 < ((byte[])var5_6).length) break;
                    var4_5 = true;
                    break;
                }
                case 75: {
                    if (var1_1.x.L.size() <= 0) break;
                    ** GOTO lbl109
                }
                case 78: {
                    var5_6 = EngineUtils.e(var3_3.c()[0]);
                    var6_11 = EngineUtils.e(var3_3.c()[1]);
                    var3_3 = EngineUtils.e(var3_3.c()[2]);
                    for (var7_12 = 0; var7_12 < ((Object)var3_3).length && var1_1.b[game.WorldManager.a((int)var5_6[var7_12], (int)var6_11[var7_12])] != null && (var1_1.b[game.WorldManager.a((int)var5_6[var7_12], (int)var6_11[var7_12])][var3_3[var7_12]] == 3 || var1_1.b[game.WorldManager.a((int)var5_6[var7_12], (int)var6_11[var7_12])][var3_3[var7_12]] == 4); ++var7_12) {
                    }
                    if (var7_12 < ((Object)var3_3).length) break;
                    var4_5 = true;
                    break;
                }
                case 79: {
                    if (var1_1.b[game.WorldManager.a((int)var3_3.b()[0], (int)var3_3.b()[1])] == null || var1_1.b[game.WorldManager.a((int)var3_3.b()[0], (int)var3_3.b()[1])][var3_3.b()[2]] != 3 || var1_1.x.k(0) || game.WorldManager.u != var3_3.b()[3]) break;
                    game.OverworldScreen.g = true;
                    if (!game.OverworldScreen.h) break;
                    game.OverworldScreen.h = false;
                    ** GOTO lbl109
                }
                case 86: {
                    if (var1_1.b[game.WorldManager.a((int)var3_3.b()[0], (int)var3_3.b()[1])] == null || var1_1.b[game.WorldManager.a((int)var3_3.b()[0], (int)var3_3.b()[1])][var3_3.b()[2]] != 3) break;
                }
lbl109:
                // 8 sources

                default: {
                    var4_5 = true;
                }
            }
            if (!var4_5) continue;
            var1_1.A = (byte)var2_2;
            var1_1.a[var2_2].b((byte)0);
            var1_1.z.addElement(var1_1.a[var2_2]);
            var1_1.a[var2_2].a((byte)1);
        }
        this.n();
    }

    public static void a(Graphics g) {
        int n2;
        if (C != null) {
            for (n2 = 0; n2 < C.size(); ++n2) {
                WorldEntity f2 = (WorldEntity)C.elementAt(n2);
                f2.b(f2.p.i, f2.p.j - 40);
                f2.a(g, MapEngine.getInstance().cameraX, MapEngine.getInstance().cameraY);
            }
        }
        if (d != null) {
            for (n2 = 0; n2 < d.size(); ++n2) {
                ((WorldEntity)d.elementAt(n2)).a(g, MapEngine.getInstance().cameraX, MapEngine.getInstance().cameraY);
            }
        }
    }

    public final void c() {
        int n2;
        if (C != null) {
            for (n2 = 0; n2 < C.size(); ++n2) {
                WorldEntity f2 = (WorldEntity)C.elementAt(n2);
                f2.a();
                if (!f2.a.e()) continue;
                f2.d();
                C.removeElementAt(n2);
                --n2;
            }
        }
        if (d != null) {
            for (n2 = 0; n2 < d.size(); ++n2) {
                ((WorldEntity)d.elementAt(n2)).a();
            }
        }
        if (this.ag != null) {
            this.ag.a();
        }
    }

    public final void b(Graphics g) {
        ScreenView.getInstance().a(g);
        Graphics graphics2 = g;
        OverworldScreen c2 = this;
        if (c2.ag != null && c2.ah == game.WorldManager.a(c2.w.f, c2.w.g)) {
            c2.ag.a(graphics2, MapEngine.getInstance().cameraX, MapEngine.getInstance().cameraY);
        }
        this.mapRenderer.a(g);
    }

    public final boolean d() {
        return true;
    }

    public final boolean a(DataInputStream dataInputStream, int n2, int n3, int n4, String[] strArray) {
        try {
            this.scriptSequences = new ScriptSequence[n4];
            this.z = new Vector();
            C = new Vector();
            int n5 = n2 << 8 | n3;
            if (this.b[game.WorldManager.l[n2] + n3] == null) {
                this.b[game.WorldManager.l[n2] + n3] = new byte[n4];
            }
            for (byte by = 0; by < n4; by = (byte)(by + 1)) {
                this.scriptSequences[by] = new ScriptSequence();
                this.scriptSequences[by].a(dataInputStream, by, n5, strArray);
                this.scriptSequences[by].a(this.b[game.WorldManager.l[n2] + n3][by]);
            }
        }
        catch (IOException iOException) {
            System.out.println(" ex = " + iOException.toString() + " event init ");
        }
        this.i();
        return false;
    }

    public final void e() {
        this.player = null;
        this.worldManager = null;
        this.b = null;
        s = null;
        this.ai = null;
        instance = null;
    }

    public final void f() {
        if (C != null) {
            C.removeAllElements();
            C = null;
        }
        if (d != null) {
            d.removeAllElements();
            d = null;
        }
        this.scriptSequences = null;
        this.E = null;
        this.F = null;
        this.G = null;
        this.H = null;
        this.I = null;
        this.J = null;
        this.K = null;
        this.L = null;
        this.ab = null;
        this.ac = null;
    }

    public final void a(byte val) {
    }

    private byte a(int n2) {
        for (int i = 0; i < this.scriptSequences.length; ++i) {
            ScriptCommand ad2;
            if (this.scriptSequences[i].a() == 3 || n2 == i || (ad2 = this.scriptSequences[i].d()).a() != 44 || game.WorldManager.a((int)ad2.b()[2], (int)ad2.b()[3]) != game.WorldManager.a(this.worldManager.f, this.worldManager.g) || ad2.b()[4] != game.WorldManager.u || !this.b(ad2)) continue;
            return (byte)i;
        }
        return -1;
    }

    private static void b(int n2) {
        for (int i = 0; i < u; ++i) {
            if (s[i][0] != n2) continue;
            game.OverworldScreen.s[i][1] = 3;
            return;
        }
    }

    private static int c(int n2) {
        for (int i = 0; i < u; ++i) {
            if (s[i][0] != n2) continue;
            return i;
        }
        return -1;
    }

    /*
     * Unable to fully structure code
     */
    private void n() {
        var1_1 = 0;
        while (var1_1 < this.z.size()) {
            var2_2 = (ScriptSequence)this.z.elementAt(var1_1);
            var3_3 = var2_2.c();
            switch (var3_3.a()) {
                case 0: {
                    break;
                }
                case 1: {
                    if (var2_2.a() != 5) {
                        ScreenView.getInstance().c(0, 9);
                        this.mapRenderer.a(var3_3.b()[1], var3_3.b()[2]);
                        this.mapRenderer.a((byte)(var3_3.b()[0] / 10 - 1), var3_3.c()[0], var3_3.b()[0] % 10);
                        this.mapRenderer.a(true);
                        var2_2.a((byte)5);
                        break;
                    }
                    if (!game.TileMapRenderer.a || !this.parentScreen.k(1)) break;
                    this.mapRenderer.b();
                    if (game.TileMapRenderer.b) break;
                    ScreenView.getInstance().a = -1;
                    this.mapRenderer.c();
                    ** GOTO lbl1044
                }
                case 2: {
                    if (var3_3.b()[0] == -1) {
                        this.player.c();
                        this.player.b((byte)0, EngineUtils.d(EngineUtils.a(var3_3.c()[1], ',')[0]));
                        break;
                    }
                    for (var4_4 = 0; var4_4 < var3_3.b()[0]; ++var4_4) {
                        this.worldManager.npcList[EngineUtils.c(EngineUtils.a(var3_3.c()[0], ',')[var4_4])].b(EngineUtils.d(EngineUtils.a(var3_3.c()[1], ',')[var4_4]));
                        if (this.worldManager.npcList[EngineUtils.c((String)EngineUtils.a((String)var3_3.c()[0], (char)',')[var4_4])].v == 1) {
                            this.worldManager.npcList[EngineUtils.c(EngineUtils.a(var3_3.c()[0], ',')[var4_4])].d((byte)0);
                        }
                        this.worldManager.npcList[EngineUtils.c(EngineUtils.a(var3_3.c()[0], ',')[var4_4])].c();
                    }
                    break;
                }
                case 3: {
                    if (var3_3.b()[0] == -1) {
                        this.player.d();
                        break;
                    }
                    for (var5_29 = 0; var5_29 < var3_3.b()[0]; ++var5_29) {
                        var4_5 = EngineUtils.c(EngineUtils.a(var3_3.c()[0], ',')[var5_29]);
                        this.worldManager.npcList[var4_5].d();
                    }
                    break;
                }
                case 4: {
                    if (var2_2.a() != 5) {
                        this.player.b((byte)0, this.player.n);
                        this.worldManager.S.a(var3_3.c()[0], var3_3.c()[1], (int)var3_3.b()[1]);
                        var2_2.a((byte)5);
                        break;
                    }
                    if (!this.worldManager.S.c(var3_3.b()[1], -1) || !this.parentScreen.k(196640)) break;
                    game.WorldManager.getInstance().e();
                    if (EngineUtils.b < EngineUtils.b()) {
                        EngineUtils.c();
                        this.worldManager.S.b(EngineUtils.b);
                        break;
                    }
                    if (game.WorldManager.u != -1 && this.worldManager.npcList[game.WorldManager.u].a.a <= 85 && this.worldManager.npcList[game.WorldManager.u].u() == 0) {
                        game.WorldManager.getInstance().a(game.WorldManager.getInstance().npcList[game.WorldManager.u].i, game.WorldManager.getInstance().npcList[game.WorldManager.u].j - 40, game.WorldManager.getInstance().npcList[game.WorldManager.u]);
                    }
                    game.OverworldScreen.g = false;
                    game.OverworldScreen.h = false;
                    this.worldManager.S.aC();
                    var2_2.a((byte)1);
                    break;
                }
                case 5: {
                    var4_6 = new WorldEntity();
                    var4_6.a(259, false);
                    var4_6.a((byte)var3_3.b()[2], (byte)-1, true);
                    if (var3_3.b()[0] == 0) {
                        var4_6.b(this.player.getPosX(), this.player.getPosY() - this.player.a.b(this.player.h(), this.player.n)[3]);
                        var4_6.a(this.player);
                    } else if (var3_3.b()[0] == 1) {
                        if (var3_3.b()[3] != 0 || var3_3.b()[4] != 0) {
                            var4_6.b(var3_3.b()[3], var3_3.b()[4]);
                        } else {
                            var4_6.b(this.worldManager.npcList[var3_3.b()[1]].l(), this.worldManager.npcList[var3_3.b()[1]].m());
                            var4_6.a(this.worldManager.npcList[var3_3.b()[1]]);
                        }
                    }
                    var4_6.c();
                    game.OverworldScreen.C.addElement(var4_6);
                    break;
                }
                case 6: {
                    this.b[game.WorldManager.l[this.worldManager.f] + this.worldManager.g][var2_2.b()] = 3;
                    game.WorldManager.getInstance().f = var3_3.b()[0];
                    game.WorldManager.getInstance().g = var3_3.b()[1];
                    this.worldManager.j = var3_3.b()[3] == 1 ? var3_3.b()[2] : -1;
                    game.GameStateController.getInstance().a((byte)22);
                    break;
                }
                case 7: {
                    if (var2_2.a() != 5) {
                        this.E = new short[var3_3.b()[0]];
                        for (var4_7 = 0; var4_7 < this.E.length; ++var4_7) {
                            this.E[var4_7] = EngineUtils.c(EngineUtils.a(var3_3.c()[0], ',')[var4_7]);
                            var5_30 = EngineUtils.d(EngineUtils.a(var3_3.c()[2], ',')[var4_7]);
                            if (this.E[var4_7] == -1) {
                                this.player.b(EngineUtils.d(EngineUtils.a(var3_3.c()[1], ',')[0]), var5_30);
                                continue;
                            }
                            this.worldManager.npcList[this.E[var4_7]].b(var5_30);
                            this.worldManager.npcList[this.E[var4_7]].d(EngineUtils.d(EngineUtils.a(var3_3.c()[1], ',')[var4_7]));
                        }
                        this.B = 0;
                        var2_2.a((byte)5);
                        break;
                    }
                    for (var4_8 = 0; var4_8 < this.E.length; ++var4_8) {
                        if (this.E[var4_8] == -1) {
                            if (!this.player.b()) continue;
                            this.player.b((byte)0, this.player.n);
                            ++this.B;
                            continue;
                        }
                        if (!this.worldManager.npcList[this.E[var4_8]].b()) continue;
                        this.worldManager.npcList[this.E[var4_8]].d((byte)0);
                        ++this.B;
                    }
                    if (this.B < this.E.length) break;
                    this.B = 0;
                    ** GOTO lbl1044
                }
                case 8: {
                    this.player.c();
                    game.WorldManager.u = (short)-1;
                    this.player.b(var3_3.b()[0], var3_3.b()[1]);
                    this.player.b.b(var3_3.b()[0], var3_3.b()[1]);
                    this.player.b((byte)0, this.player.n);
                    break;
                }
                case 9: {
                    if (var2_2.a() != 5) {
                        var4_9 = false;
                        if (var3_3.b()[0] == 12 || var3_3.b()[0] == 13) {
                            ScreenView.getInstance().c(0, var3_3.b()[0]);
                            ScreenView.getInstance().a(var3_3.b()[1], var3_3.b()[2], var3_3.b()[3], var3_3.b()[4], var3_3.b()[5]);
                        } else if (var3_3.b()[0] == 10) {
                            ScreenView.getInstance().c(0, var3_3.b()[0]);
                            ScreenView.getInstance().d(var3_3.b()[1], var3_3.b()[2]);
                        } else if (var3_3.b()[0] == 15 || var3_3.b()[0] == 14) {
                            ScreenView.getInstance().c(0, var3_3.b()[0]);
                            ScreenView.getInstance().a(this.M[var3_3.b()[1]], (int)var3_3.b()[2], (int)var3_3.b()[3], (int)var3_3.b()[4]);
                        } else if (var3_3.b()[0] == 16) {
                            if (var3_3.b()[1] == 0) {
                                var5_31 = new String[]{"star0", "star1", "star2", "star3"};
                                ScreenView.getInstance().a(16, (byte)var3_3.b()[2], (byte)7, var5_31);
                            } else if (var3_3.b()[1] == 1) {
                                var5_32 = new String[]{"fire0", "fire1", "fire2"};
                                ScreenView.getInstance().a(16, (byte)var3_3.b()[2], (byte)0, var5_32);
                            } else if (var3_3.b()[1] == 2) {
                                var5_33 = new String[]{"fire0", "fire1", "fire2"};
                                ScreenView.getInstance().a(17, (byte)var3_3.b()[2], (byte)0, var5_33);
                            } else {
                                var4_9 = true;
                                ScreenView.getInstance().a(-1, (byte)var3_3.b()[2], (byte)0, null);
                                var2_2.a((byte)1);
                            }
                        } else if (var3_3.b()[0] == 17) {
                            ScreenView.getInstance().c(var3_3.b()[1], var3_3.b()[0]);
                            ScreenView.getInstance().a((int)var3_3.b()[2], (int)var3_3.b()[3], (int)var3_3.b()[4], (int)var3_3.b()[5]);
                        } else {
                            var5_34 = var3_3.b()[1] << 24 | var3_3.b()[2] << 16 | var3_3.b()[3] << 8 | var3_3.b()[4];
                            ScreenView.getInstance().c(var5_34, var3_3.b()[0]);
                        }
                        if (var4_9) break;
                        var2_2.a((byte)5);
                        break;
                    }
                    if (ScreenView.getInstance().c && (var3_3.b()[0] == 12 || var3_3.b()[0] == 13)) {
                        var2_2.a((byte)1);
                        break;
                    }
                    if (var3_3.b()[0] != 16 && !ScreenView.getInstance().b) break;
                    ** GOTO lbl1044
                }
                case 10: {
                    if (var2_2.a() != 5) {
                        if (var3_3.b()[0] == -1) {
                            this.F = new byte[1];
                            this.player.b(EngineUtils.d(EngineUtils.a(var3_3.c()[1], ',')[0]));
                            this.player.b((byte)0, this.player.n);
                            this.player.a((byte)0, (short)EngineUtils.d(EngineUtils.a(var3_3.c()[2], ',')[0]));
                            this.F[0] = EngineUtils.d(EngineUtils.a(var3_3.c()[3], ',')[0]);
                        } else {
                            this.E = new short[var3_3.b()[0]];
                            this.F = new byte[var3_3.b()[0]];
                            for (var4_10 = 0; var4_10 < this.E.length; ++var4_10) {
                                this.E[var4_10] = EngineUtils.c(EngineUtils.a(var3_3.c()[0], ',')[var4_10]);
                                if (this.E[var4_10] != -1) {
                                    this.worldManager.npcList[this.E[var4_10]].b(EngineUtils.d(EngineUtils.a(var3_3.c()[1], ',')[var4_10]));
                                    this.worldManager.npcList[this.E[var4_10]].a((byte)0, (short)EngineUtils.d(EngineUtils.a(var3_3.c()[2], ',')[var4_10]));
                                    this.worldManager.npcList[this.E[var4_10]].d((byte)0);
                                } else {
                                    this.player.b(EngineUtils.d(EngineUtils.a(var3_3.c()[1], ',')[var4_10]));
                                    this.player.a((byte)0, (short)EngineUtils.d(EngineUtils.a(var3_3.c()[2], ',')[var4_10]));
                                    this.player.b((byte)0, this.player.n);
                                }
                                this.F[var4_10] = EngineUtils.d(EngineUtils.a(var3_3.c()[3], ',')[var4_10]);
                            }
                        }
                        this.B = 0;
                        var2_2.a((byte)5);
                        break;
                    }
                    if (var3_3.b()[0] == -1) {
                        if (this.player.h() == 0) {
                            this.player.b((byte)1, this.player.n);
                            break;
                        }
                        this.F[0] = (byte)(this.F[0] - 1);
                        if (this.F[0] > 0) break;
                        this.player.b((byte)0, this.player.n);
                        if (this.player.P[0] == 2 || this.player.P[1] == 2) {
                            this.player.a((byte)0, (short)8);
                        } else {
                            this.player.a((byte)0, (short)4);
                        }
                        var2_2.a((byte)1);
                        break;
                    }
                    for (var4_11 = 0; var4_11 < this.E.length; ++var4_11) {
                        if (this.E[var4_11] != -1 && this.worldManager.npcList[this.E[var4_11]].h() == 0 || this.E[var4_11] == -1 && this.player.h() == 0) {
                            if (this.F[var4_11] <= 0) continue;
                            if (this.E[var4_11] != -1) {
                                this.worldManager.npcList[this.E[var4_11]].d((byte)3);
                                continue;
                            }
                            this.player.b((byte)1, this.player.n);
                            continue;
                        }
                        v0 = var4_11;
                        this.F[v0] = (byte)(this.F[v0] - 1);
                        if (this.F[var4_11] > 0) continue;
                        ++this.B;
                        this.F[var4_11] = 0;
                        if (this.E[var4_11] != -1) {
                            this.worldManager.npcList[this.E[var4_11]].d((byte)0);
                            this.worldManager.npcList[this.E[var4_11]].a((byte)0, (short)4);
                            continue;
                        }
                        this.player.b((byte)0, this.player.n);
                        if (this.player.P[0] == 2 || this.player.P[1] == 2) {
                            this.player.a((byte)0, (short)8);
                            continue;
                        }
                        this.player.a((byte)0, (short)4);
                    }
                    if (this.B < this.E.length) break;
                    ** GOTO lbl1044
                }
                case 11: {
                    if (var2_2.a() != 5) {
                        var4_12 = false;
                        if (var3_3.b()[6] == 0) {
                            var4_12 = true;
                        }
                        ParticleEffect.getInstance().a(var3_3.b()[7]);
                        if (var3_3.b()[2] == 1) {
                            v1 = ParticleEffect.getInstance();
                            v2 = var3_3.b()[4];
                            v3 = var3_3.b()[5];
                            var3_3.b();
                            var3_3.b();
                            v1.a((int)v2, (int)v3, var4_12);
                        } else if (var3_3.b()[2] == 0) {
                            if (var3_3.b()[3] == -1) {
                                v4 = ParticleEffect.getInstance();
                                var3_3.b();
                                var3_3.b();
                                v4.a(this.player, var4_12);
                            } else {
                                v5 = ParticleEffect.getInstance();
                                v6 = this.worldManager.npcList[var3_3.b()[3]];
                                var3_3.b();
                                var3_3.b();
                                v5.a(v6, var4_12);
                            }
                        }
                        this.player.b((byte)0, this.player.n);
                        var2_2.a((byte)5);
                        break;
                    }
                    if (!ParticleEffect.getInstance().c()) break;
                    ** GOTO lbl1044
                }
                case 12: {
                    ++this.B;
                    if (var2_2.a() != 5) {
                        var2_2.a((byte)5);
                        break;
                    }
                    if (this.B < var3_3.b()[0]) break;
                    this.B = 0;
                    ** GOTO lbl1044
                }
                case 13: {
                    if (EngineUtils.a(var3_3.b()[0], (int)var3_3.b()[1], (int)var3_3.b()[2], (int)var3_3.b()[3], this.player.i, this.player.j, this.player.a.k())) {
                        var2_2.a((byte)1);
                        this.player.b((byte)0, this.player.n);
                        break;
                    }
                    var2_2.a((byte)6);
                    break;
                }
                case 14: {
                    var2_2.a((byte)3);
                    break;
                }
                case 16: {
                    if (var3_3.b()[0] == game.WorldManager.u) {
                        game.OverworldScreen.g = true;
                        if (!game.OverworldScreen.h) break;
                        game.OverworldScreen.h = false;
                        var2_2.a((byte)2);
                        break;
                    }
                    var2_2.a((byte)6);
                    break;
                }
                case 17: {
                    if (var2_2.a() != 5) {
                        if (var3_3.b()[0] == 0) {
                            if (this.player.a((int)var3_3.b()[1], (int)var3_3.b()[2], (byte)0)) {
                                var4_13 = GameDatabase.gameDatabase[4][var3_3.b()[1]][0];
                                this.parentScreen.S.a("Đạt được: " + BaseScreen.f(var4_13), (int)var3_3.b()[2]);
                                this.player.c(var3_3.b()[1], var3_3.b()[2], (byte)0);
                            } else {
                                this.parentScreen.S.b("Ba lô đã đủ đạo cụ này");
                            }
                        } else if (this.player.b((int)var3_3.b()[1], (int)var3_3.b()[2], (byte)0)) {
                            var4_14 = GameDatabase.gameDatabase[4][var3_3.b()[1]][0];
                            this.parentScreen.S.a("Mất: " + BaseScreen.f(var4_14), (int)var3_3.b()[2]);
                            this.player.d(var3_3.b()[1], var3_3.b()[2], (byte)0);
                        }
                        var2_2.a((byte)5);
                        break;
                    }
                    if (!this.parentScreen.S.ax()) break;
                    ** GOTO lbl1044
                }
                case 18: {
                    if (var2_2.a() != 5) {
                        if (var3_3.b()[0] == 0) {
                            if (this.player.a((int)var3_3.b()[1], (int)var3_3.b()[2], (byte)2)) {
                                var4_15 = GameDatabase.gameDatabase[3][var3_3.b()[1]][0];
                                this.parentScreen.S.a("Đạt được: " + BaseScreen.f(var4_15), (int)var3_3.b()[2]);
                                this.player.c(var3_3.b()[1], var3_3.b()[2], (byte)2);
                            } else {
                                this.parentScreen.S.b("Ba lô đã đủ đạo cụ này");
                            }
                        } else if (var3_3.b()[0] == 1) {
                            var4_16 = GameDatabase.gameDatabase[3][var3_3.b()[1]][0];
                            this.parentScreen.S.a("Mất: " + BaseScreen.f(var4_16), (int)var3_3.b()[2]);
                            this.player.d(var3_3.b()[1], var3_3.b()[2], (byte)2);
                        }
                        var2_2.a((byte)5);
                        break;
                    }
                    if (!this.parentScreen.S.ax()) break;
                    ** GOTO lbl1044
                }
                case 19: {
                    if (var2_2.a() != 5) {
                        var4_17 = GameDatabase.gameDatabase[5][var3_3.b()[0]][0];
                        this.parentScreen.S.a("Đạt được: " + BaseScreen.f(var4_17), (int)var3_3.b()[1]);
                        var5_35 = this.player.d(var3_3.b()[0], var3_3.b()[1]);
                        if (var5_35 != -1) {
                            if (var5_35 == 1) {
                                this.parentScreen.S.b("Ba lô đã đủ loại đạo cụ này");
                            } else {
                                this.player.c(var3_3.b()[0], var3_3.b()[1]);
                            }
                        } else if (var3_3.b()[0] == 0) {
                            this.player.e(var3_3.b()[0], -1);
                        } else {
                            this.player.i(var3_3.b()[0]);
                        }
                        var2_2.a((byte)5);
                        break;
                    }
                    if (!this.parentScreen.S.ax()) break;
                    ** GOTO lbl1044
                }
                case 20: {
                    if (var2_2.a() != 5) {
                        if (var3_3.b()[0] == 1) {
                            this.parentScreen.S.b("Mất: " + var3_3.c()[0]);
                            this.player.T[var3_3.b()[1]] = false;
                        } else {
                            this.parentScreen.S.b("Đạt được: " + var3_3.c()[0]);
                            this.player.T[var3_3.b()[1]] = true;
                        }
                        var2_2.a((byte)5);
                        break;
                    }
                    if (!this.parentScreen.S.ax()) break;
                    ** GOTO lbl1044
                }
                case 21: {
                    game.WorldManager.x = false;
                    game.WorldManager.y = var3_3.b()[2];
                    if (var3_3.b()[1] != 1) break;
                    game.WorldManager.z = var3_3.b()[3];
                    game.WorldManager.A = var3_3.b()[4];
                    game.WorldManager.B = var3_3.b()[5];
                    game.WorldManager.C = var3_3.b()[6];
                    break;
                }
                case 22: {
                    game.WorldManager.x = true;
                    game.WorldManager.w = (byte)var3_3.b()[1];
                    game.WorldManager.getInstance().playerSpawnX = var3_3.b()[2];
                    game.WorldManager.getInstance().playerSpawnY = var3_3.b()[3];
                    game.WorldManager.B = var3_3.b()[4];
                    game.WorldManager.C = var3_3.b()[5];
                    game.WorldManager.getInstance().j = -1;
                    break;
                }
                case 23: {
                    this.b[game.WorldManager.a((int)var3_3.b()[0], (int)var3_3.b()[1])][var3_3.b()[2]] = 3;
                    if (var3_3.b()[0] != this.worldManager.f || var3_3.b()[1] != this.worldManager.g) break;
                    this.scriptSequences[var3_3.b()[2]].a((byte)3);
                    if (this.z.size() <= 0) break;
                    this.z.removeElement(this.scriptSequences[var3_3.b()[2]]);
                    --var1_1;
                    break;
                }
                case 24: {
                    if (var2_2.a() != 5) {
                        ScreenView.getInstance().c(0, 11);
                        ScreenView.getInstance().a(var3_3.b()[0], var3_3.b()[1], var3_3.b()[2]);
                        var2_2.a((byte)5);
                        break;
                    }
                    if (!ScreenView.getInstance().b) break;
                    ** GOTO lbl1044
                }
                case 25: {
                    game.OverworldScreen.f = var3_3.b()[0] == 0;
                    break;
                }
                case 29: {
                    if (var2_2.a() != 5) {
                        var4_18 = var3_3.b()[0];
                        if (var3_3.b()[0] == -1) {
                            var4_18 = 1;
                        }
                        this.E = new short[var4_18];
                        this.K = new short[var4_18];
                        this.L = new short[var4_18];
                        this.G = new short[var4_18];
                        this.H = new short[var4_18];
                        for (var5_36 = 0; var5_36 < this.E.length; ++var5_36) {
                            this.E[var5_36] = EngineUtils.c(EngineUtils.a(var3_3.c()[0], ',')[var5_36]);
                            this.K[var5_36] = EngineUtils.c(EngineUtils.a(var3_3.c()[1], ',')[var5_36]);
                            this.L[var5_36] = EngineUtils.c(EngineUtils.a(var3_3.c()[2], ',')[var5_36]);
                            this.G[var5_36] = EngineUtils.c(EngineUtils.a(var3_3.c()[3], ',')[var5_36]);
                            this.H[var5_36] = EngineUtils.c(EngineUtils.a(var3_3.c()[4], ',')[var5_36]);
                        }
                        var2_2.a((byte)5);
                        break;
                    }
                    var4_19 = true;
                    for (var7_59 = 0; var7_59 < this.E.length; ++var7_59) {
                        if (this.G[var7_59] <= 0 && this.H[var7_59] <= 0) continue;
                        var4_19 = false;
                        v7 = var7_59;
                        this.G[v7] = (short)(this.G[v7] - 1);
                        v8 = var7_59;
                        this.H[v8] = (short)(this.H[v8] - 1);
                        if (var3_3.b()[0] == -1) {
                            var5_37 = this.player.getPosX() + this.K[var7_59];
                            var6_44 = this.player.getPosY() + this.L[var7_59];
                            this.player.b(var5_37, var6_44);
                            if (this.player.b == null) continue;
                            this.player.b.b(var5_37, var6_44);
                            continue;
                        }
                        var5_37 = this.worldManager.npcList[this.E[var7_59]].l() + this.K[var7_59];
                        var6_44 = this.worldManager.npcList[this.E[var7_59]].m() + this.L[var7_59];
                        this.worldManager.npcList[this.E[var7_59]].b(var5_37, var6_44);
                        if (this.worldManager.npcList[this.E[var7_59]].b == null) continue;
                        this.worldManager.npcList[this.E[var7_59]].b.b(var5_37, var6_44);
                    }
                    if (!var4_19) break;
                    var2_2.a((byte)1);
                    break;
                }
                case 30: {
                    if (var2_2.a() != 5) {
                        this.E = new short[var3_3.b()[0]];
                        var4_20 = EngineUtils.a(var3_3.c()[2], ',');
                        for (var5_38 = 0; var5_38 < this.E.length; ++var5_38) {
                            this.E[var5_38] = EngineUtils.c(var4_20[var5_38]);
                        }
                        var5_39 = new String[this.E.length][];
                        var6_45 = new String[this.E.length][];
                        for (var7_60 = 0; var7_60 < var6_45.length; ++var7_60) {
                            var5_39[var7_60] = EngineUtils.a(EngineUtils.a(var3_3.c()[0], '#')[var7_60], ',');
                            var6_45[var7_60] = EngineUtils.a(EngineUtils.a(var3_3.c()[1], '#')[var7_60], ',');
                        }
                        this.I = new short[this.E.length][];
                        this.J = new short[this.E.length][];
                        for (var7_60 = 0; var7_60 < this.E.length; ++var7_60) {
                            this.I[var7_60] = new short[var5_39[var7_60].length];
                            this.J[var7_60] = new short[var6_45[var7_60].length];
                            for (var8_68 = 0; var8_68 < this.I[var7_60].length; ++var8_68) {
                                this.I[var7_60][var8_68] = EngineUtils.c(var5_39[var7_60][var8_68]);
                                this.J[var7_60][var8_68] = EngineUtils.c(var6_45[var7_60][var8_68]);
                            }
                        }
                        this.B = 0;
                        var2_2.a((byte)5);
                        break;
                    }
                    for (var4_21 = 0; var4_21 < this.E.length; ++var4_21) {
                        this.worldManager.npcList[this.E[var4_21]].b(this.I[var4_21][this.B], this.J[var4_21][this.B]);
                    }
                    ++this.B;
                    if (this.B < this.I[0].length) break;
                    ** GOTO lbl1044
                }
                case 31: {
                    if (var2_2.a() != 5) {
                        if (var3_3.b()[0] == 0) {
                            if (var3_3.b()[1] == 0) {
                                this.player.s(var3_3.b()[2]);
                                this.parentScreen.S.b("Đạt được: " + var3_3.b()[2] + " kim tiền");
                            } else if (var3_3.b()[1] == 1) {
                                this.player.u(var3_3.b()[2]);
                                this.parentScreen.S.b("Đạt được: " + var3_3.b()[2] + "Huy hiệu");
                            }
                        } else if (var3_3.b()[0] == 1) {
                            if (var3_3.b()[1] == 0) {
                                this.player.s(-var3_3.b()[2]);
                                this.parentScreen.S.b("Mất: " + var3_3.b()[2] + " kim tiền");
                            } else if (var3_3.b()[1] == 1) {
                                this.player.u(-var3_3.b()[2]);
                                this.parentScreen.S.b("Mất: " + var3_3.b()[2] + " huy hiệu");
                            }
                        }
                        var2_2.a((byte)5);
                        break;
                    }
                    if (!this.worldManager.S.ax()) break;
                    ** GOTO lbl1044
                }
                case 32: {
                    this.worldManager.e();
                    game.BattleScreen.getInstance().battleType = var3_3.b()[0];
                    game.BattleScreen.getInstance().battleMode = (byte)var3_3.b()[1];
                    game.BattleScreen.getInstance().battleBgImage = Image.createImage(BaseScreen.getScreenWidth(), BaseScreen.getScreenHeight());
                    var4_22 = game.BattleScreen.getInstance().battleBgImage.getGraphics();
                    this.worldManager.stringTable.b(var4_22);
                    this.player.b((byte)0, this.player.n);
                    var2_2.a((byte)1);
                    game.GameStateController.getInstance().a((byte)12);
                    break;
                }
                case 33: {
                    break;
                }
                case 34: {
                    if (var2_2.a() != 5) {
                        var3_3.b();
                        this.N = var3_3.b()[2];
                        this.O = var3_3.b()[3];
                        this.B = var3_3.b()[4];
                        var2_2.a((byte)5);
                        break;
                    }
                    --this.B;
                    this.N -= this.O;
                    if (this.B > 0) break;
                    this.B = 0;
                    ** GOTO lbl1044
                }
                case 35: {
                    if (var2_2.a() != 5) {
                        this.Z = var3_3.b()[0];
                        this.aa = var3_3.b()[1];
                        this.ac = EngineUtils.a(var3_3.c()[0], ',');
                        this.ab = new byte[EngineUtils.a(var3_3.c()[1], ',').length];
                        var5_40 = var3_3.c()[2];
                        for (var6_46 = 0; var6_46 < this.ab.length; ++var6_46) {
                            this.ab[var6_46] = EngineUtils.d(EngineUtils.a(var3_3.c()[1], ',')[var6_46]);
                        }
                        this.parentScreen.S.a(this.aa, this.Z, this.ac, var5_40);
                        var2_2.a((byte)5);
                        break;
                    }
                    var5_41 = this.parentScreen.S.c(this.aa);
                    if (var5_41 == -1) break;
                    var2_2.b((byte)(this.ab[var5_41] - 2));
                    var2_2.a((byte)1);
                    break;
                }
                case 36: {
                    if (var2_2.a() != 5) {
                        var5_42 = this.player.getActivePetIndex();
                        if (var3_3.b()[0] == 0) {
                            if (var5_42 == 0) {
                                this.player.a(var3_3.b()[1], var3_3.b()[2], (byte)var3_3.b()[4], (byte)var3_3.b()[3], new int[]{1, var3_3.b()[5], var3_3.b()[6]});
                            } else if (var5_42 == 1) {
                                this.parentScreen.S.b("Ba lô đã đủ, đã để vào ngân hàng");
                                var6_47 = game.Pet.b(var3_3.b()[1], var3_3.b()[2], var3_3.b()[3]);
                                this.player.a(var3_3.b()[1], var3_3.b()[2], (byte)var3_3.b()[4], (byte)var3_3.b()[3], var6_47, 0, new int[]{1, var3_3.b()[5], var3_3.b()[6]});
                            } else {
                                this.parentScreen.S.b("Không có không gian, đã phóng sinh");
                            }
                        } else if (var3_3.b()[0] == 1) {
                            this.player.n(var3_3.b()[1]);
                        }
                        var2_2.a((byte)5);
                        break;
                    }
                    if (!this.parentScreen.S.ax()) break;
                    ** GOTO lbl1044
                }
                case 37: {
                    game.BattleScreen.getInstance().a(new int[][]{{var3_3.b()[0], var3_3.b()[1], var3_3.b()[2]}});
                    break;
                }
                case 38: {
                    game.OverworldScreen.g = false;
                    for (var6_48 = 0; var6_48 < EngineUtils.a(var3_3.c()[0], ',').length; ++var6_48) {
                        if (EngineUtils.d(EngineUtils.a(var3_3.c()[0], ',')[var6_48]) != game.WorldManager.u) continue;
                        game.OverworldScreen.g = true;
                        if (!game.OverworldScreen.h) break;
                        game.WorldManager.u = (short)-1;
                        var5_43 = EngineUtils.d(EngineUtils.a(var3_3.c()[1], ',')[var6_48]);
                        var2_2.b((byte)(var5_43 - 1));
                        game.WorldManager.getInstance().e();
                        game.OverworldScreen.g = false;
                        game.OverworldScreen.h = false;
                        break;
                    }
                    var2_2.a((byte)6);
                    break;
                }
                case 39: {
                    for (var6_49 = 0; var6_49 < this.player.A; ++var6_49) {
                        this.player.z[var6_49].I();
                    }
                    break;
                }
                case 40: {
                    if (var2_2.a() != 5) {
                        this.parentScreen.S.c(var3_3.c()[0]);
                        var2_2.a((byte)5);
                        break;
                    }
                    if (!this.parentScreen.S.ay()) break;
                    ** GOTO lbl1044
                }
                case 41: {
                    var2_2.b((byte)(var3_3.b()[0] - 2));
                    break;
                }
                case 42: {
                    var2_2.a((byte)4);
                    break;
                }
                case 45: {
                    if (var2_2.a() != 5) {
                        this.parentScreen.S.c(var3_3.c()[0]);
                        game.OverworldScreen.t = (byte)var3_3.b()[0];
                        var2_2.a((byte)5);
                        break;
                    }
                    if (!this.parentScreen.S.ay()) break;
                    ** GOTO lbl1044
                }
                case 46: {
                    if (var2_2.a() != 5) {
                        this.parentScreen.S.H();
                        this.parentScreen.S.a(var3_3.c()[0]);
                        var2_2.a((byte)5);
                        break;
                    }
                    if (this.parentScreen.S.f == 0) {
                        if (this.parentScreen.k(196640)) {
                            this.parentScreen.S.f = 1;
                            this.parentScreen.S.a("Đang lưu...");
                            this.parentScreen.S.J();
                            break;
                        }
                        if (!this.parentScreen.k(262144 /* MASK_SOFT_RIGHT */)) break;
                        var2_2.a((byte)1);
                        this.parentScreen.S.I();
                        this.parentScreen.S.f = 0;
                        break;
                    }
                    if (this.parentScreen.S.f == 1) {
                        this.b[game.WorldManager.a((int)this.worldManager.f, (int)this.worldManager.g)][var2_2.b()] = 3;
                        if (!((WorldManager)this.parentScreen).k()) break;
                        this.parentScreen.S.a("Lưu thành công");
                        this.parentScreen.S.f = 2;
                        break;
                    }
                    if (this.parentScreen.S.f != 2) break;
                    this.parentScreen.S.I();
                    this.parentScreen.S.f = 0;
                    ** GOTO lbl1044
                }
                case 47: {
                    if (this.l == -1) break;
                    var2_2.b((byte)(var3_3.b()[this.l] - 2));
                    break;
                }
                case 48: {
                    if (var2_2.a() != 5) {
                        this.mapRenderer.a(var3_3.b()[1], var3_3.b()[2]);
                        this.mapRenderer.a((byte)(var3_3.b()[0] / 10 - 1), var3_3.c()[0], var3_3.b()[0] % 10);
                        if (var3_3.b()[5] == 1) {
                            this.mapRenderer.a(true);
                        }
                        this.mapRenderer.b(var3_3.b()[3], var3_3.b()[4]);
                        var2_2.a((byte)5);
                        break;
                    }
                    if (!this.mapRenderer.e()) ** GOTO lbl663
                    if (!game.TileMapRenderer.a || !this.parentScreen.k(1)) break;
                    this.mapRenderer.b();
                    if (game.TileMapRenderer.b) break;
                    ScreenView.getInstance().a = -1;
                    this.mapRenderer.c();
                    ** GOTO lbl1044
lbl663:
                    // 1 sources

                    var2_2.a((byte)1);
                    break;
                }
                case 49: {
                    if (var2_2.a() != 5) {
                        this.ad = new int[2];
                        this.ae = new int[2];
                        this.af = new String[2];
                        this.ac = new String[2];
                        for (var6_50 = 0; var6_50 < 2; ++var6_50) {
                            this.ad[var6_50] = var3_3.b()[var6_50 << 1];
                            this.ae[var6_50] = var3_3.b()[(var6_50 << 1) + 1];
                            this.af[var6_50] = var3_3.c()[var6_50];
                        }
                        this.ab = new byte[EngineUtils.a(var3_3.c()[2], ',').length];
                        for (var6_50 = 0; var6_50 < this.ab.length; ++var6_50) {
                            this.ab[var6_50] = EngineUtils.d(EngineUtils.a(var3_3.c()[2], ',')[var6_50]);
                            this.ac[var6_50] = EngineUtils.a(var3_3.c()[3], ',')[var6_50];
                        }
                        this.parentScreen.S.a(this.ad, this.ae, this.af, this.ac);
                        var2_2.a((byte)5);
                        break;
                    }
                    var6_51 = this.parentScreen.S.aD();
                    if (var6_51 == -1) break;
                    if (var6_51 == 0 && var2_2.d().b()[1] == 1) {
                        game.OverworldScreen.s[game.OverworldScreen.u][1] = 1;
                        game.OverworldScreen.u = (byte)(game.OverworldScreen.u + 1);
                    }
                    var2_2.b((byte)(this.ab[var6_51] - 2));
                    var2_2.a((byte)1);
                    break;
                }
                case 50: {
                    if (var3_3.b()[0] == 0) {
                        this.player.u();
                        break;
                    }
                    this.player.t();
                    break;
                }
                case 51: {
                    this.worldManager.S.aB();
                    this.mapRenderer.a(var3_3.b()[1], var3_3.b()[2]);
                    this.mapRenderer.a((byte)(var3_3.b()[0] / 10 - 1), var3_3.c()[0], var3_3.b()[0] % 10);
                    this.mapRenderer.b(var3_3.b()[3], var3_3.b()[4]);
                    break;
                }
                case 52: {
                    this.i = var3_3.b()[0] == 0;
                    if (var3_3.b()[1] == 0) {
                        game.OverworldScreen.j = true;
                        break;
                    }
                    game.OverworldScreen.j = false;
                    break;
                }
                case 53: {
                    if (var2_2.a() != 5) {
                        if (var3_3.b()[1] == 0) {
                            this.player.a((byte)var3_3.b()[0], (byte)var3_3.b()[1], (byte)2);
                            for (var6_52 = 0; var6_52 < game.WorldManager.getInstance().npcList.length; ++var6_52) {
                                if (game.WorldManager.getInstance().npcList[var6_52].t != 0 || game.WorldManager.getInstance().npcList[var6_52].v != 1) continue;
                                game.WorldManager.getInstance().npcList[var6_52].v();
                            }
                        } else if (var3_3.b()[1] == 1) {
                            this.player.a((byte)var3_3.b()[0], (byte)var3_3.b()[1], (byte)1);
                        }
                        this.parentScreen.S.a(var3_3.b()[0]);
                        var2_2.a((byte)5);
                        break;
                    }
                    if (!this.worldManager.k(1)) break;
                    this.parentScreen.S.V();
                    ** GOTO lbl1044
                }
                case 54: {
                    var6_53 = var3_3.b()[0];
                    var7_61 = new int[var6_53][3];
                    for (var8_69 = 0; var8_69 < var6_53; ++var8_69) {
                        var7_61[var8_69][0] = EngineUtils.b(EngineUtils.a(var3_3.c()[0], ',')[var8_69]);
                        var7_61[var8_69][1] = EngineUtils.b(EngineUtils.a(var3_3.c()[1], ',')[var8_69]);
                        var7_61[var8_69][2] = EngineUtils.b(EngineUtils.a(var3_3.c()[2], ',')[var8_69]);
                    }
                    game.BattleScreen.getInstance().a(var7_61);
                    break;
                }
                case 55: {
                    if (var3_3.b()[0] == 0) {
                        if (this.ag == null) {
                            this.ag = new WorldEntity();
                            this.ag.a(340, false);
                            this.ag.c();
                            this.ah = game.WorldManager.a((int)var3_3.b()[3], (int)var3_3.b()[4]);
                        }
                        this.ag.b(var3_3.b()[1], var3_3.b()[2]);
                        break;
                    }
                    if (var3_3.b()[0] != 1 || this.ag == null) break;
                    this.ag.d();
                    this.ag = null;
                    this.ah = -1;
                    break;
                }
                case 56: {
                    var6_54 = var3_3.b()[1];
                    if (var3_3.b()[0] == 0) {
                        for (var7_62 = 0; var7_62 < var6_54; ++var7_62) {
                            var8_70 = EngineUtils.c(EngineUtils.a(var3_3.c()[0], ',')[var7_62]);
                            var4_23 = EngineUtils.d(EngineUtils.a(var3_3.c()[1], ',')[var7_62]);
                            this.worldManager.npcList[var8_70].b(var4_23);
                            if (this.worldManager.npcList[var8_70].v == 1) {
                                this.worldManager.npcList[var8_70].d((byte)0);
                            }
                            this.worldManager.npcList[var8_70].c();
                            this.worldManager.a(var8_70, 1, (byte)1, true);
                            this.worldManager.a(var8_70, 2, var4_23, true);
                            this.worldManager.npcList[var8_70].r();
                        }
                    } else {
                        if (var3_3.b()[0] != 1) break;
                        for (var7_63 = 0; var7_63 < var6_54; ++var7_63) {
                            var8_71 = EngineUtils.c(EngineUtils.a(var3_3.c()[0], ',')[var7_63]);
                            if (this.worldManager.npcList[var8_71].v == 1) {
                                this.worldManager.npcList[var8_71].d((byte)0);
                            }
                            this.worldManager.npcList[var8_71].d();
                            this.worldManager.a(var8_71, 1, (byte)0, true);
                            this.worldManager.npcList[var8_71].r();
                        }
                    }
                    break;
                }
                case 58: {
                    if (((NpcEntity)this.player.p).h() != 1 || !((NpcEntity)this.player.p).a.e()) break;
                    ((NpcEntity)this.player.p).d((byte)0);
                    this.worldManager.npcList[var3_3.b()[0]].b(var3_3.b()[1], var3_3.b()[2]);
                    if ((NpcEntity)this.worldManager.npcList[var3_3.b()[0]].p == null) break;
                    ((NpcEntity)this.worldManager.npcList[var3_3.b()[0]].p).q();
                    this.worldManager.npcList[var3_3.b()[0]].a(null);
                    break;
                }
                case 60: {
                    if (var2_2.a() != 5) {
                        this.E = new short[var3_3.b()[0]];
                        for (var6_55 = 0; var6_55 < this.E.length; ++var6_55) {
                            this.E[var6_55] = EngineUtils.c(EngineUtils.a(var3_3.c()[0], ',')[var6_55]);
                            this.worldManager.npcList[this.E[var6_55]].d(EngineUtils.d(EngineUtils.a(var3_3.c()[1], ',')[var6_55]));
                            if (this.worldManager.npcList[this.E[var6_55]].t != 0 || this.worldManager.npcList[this.E[var6_55]].v != 6 || this.worldManager.npcList[this.E[var6_55]].h() != 2) continue;
                            game.WorldManager.getInstance().stringTable.c(game.WorldManager.getInstance().npcList[this.E[var6_55]]);
                        }
                        this.B = 0;
                        var2_2.a((byte)5);
                        break;
                    }
                    for (var6_56 = 0; var6_56 < this.E.length; ++var6_56) {
                        if (!this.worldManager.npcList[this.E[var6_56]].b()) continue;
                        ++this.B;
                    }
                    if (this.B < this.E.length) break;
                    this.B = 0;
                    ** GOTO lbl1044
                }
                case 62: {
                    var6_57 = new int[EngineUtils.a(var3_3.c()[0], ',').length];
                    var8_72 = -1;
                    var2_2.a((byte)6);
                    for (var7_64 = 0; var7_64 < var6_57.length; ++var7_64) {
                        var6_57[var7_64] = EngineUtils.b(EngineUtils.a(var3_3.c()[0], ',')[var7_64]);
                        if (this.worldManager.npcList[var6_57[var7_64]].h() != 2) continue;
                        var8_72 = var6_57[var7_64];
                        break;
                    }
                    if (var8_72 < 0) break;
                    if (var8_72 == var3_3.b()[0]) ** GOTO lbl818
                    var2_2.b((byte)(var3_3.b()[2] - 2));
                    ** GOTO lbl1044
lbl818:
                    // 1 sources

                    var2_2.b((byte)(var3_3.b()[1] - 2));
                    var2_2.a((byte)1);
                    break;
                }
                case 63: {
                    if (var3_3.b()[0] == 0) {
                        this.player.h(var3_3.b()[1]);
                    } else {
                        this.player.s();
                    }
                    this.k = var3_3.b()[2] != 0;
                    break;
                }
                case 64: {
                    if (var3_3.b()[0] == 0) {
                        this.worldManager.a(var3_3.b()[1]);
                        if (var3_3.b()[2] == -1) {
                            this.worldManager.a((WorldEntity)this.player);
                            break;
                        }
                        this.worldManager.a(this.worldManager.npcList[var3_3.b()[2]]);
                        break;
                    }
                    this.worldManager.g();
                    break;
                }
                case 65: {
                    if (var2_2.a() != 5 && !game.OverworldScreen.X) {
                        this.parentScreen.a((byte)100);
                        var2_2.a((byte)5);
                        break;
                    }
                    if (game.OverworldScreen.X) {
                        var2_2.b((byte)(var3_3.b()[0] - 2));
                    } else {
                        var2_2.b((byte)(var3_3.b()[1] - 2));
                    }
                    ** GOTO lbl1044
                }
                case 66: {
                    BaseScreen.U = (byte)var3_3.b()[0];
                    BaseScreen.c(0, 3);
                    break;
                }
                case 67: {
                    game.WorldManager.v = var3_3.b()[0];
                    break;
                }
                case 70: {
                    if (var2_2.a() != 5) {
                        game.OverworldScreen.e = false;
                        this.c = var3_3.b()[0];
                        switch (var3_3.b()[0]) {
                            case 0: 
                            case 1: {
                                this.worldManager.a((byte)1);
                                break;
                            }
                            case 2: {
                                this.worldManager.a((byte)16);
                            }
                        }
                        var2_2.a((byte)5);
                        break;
                    }
                    if (!game.OverworldScreen.e) break;
                    this.c = -1;
                    ** GOTO lbl1044
                }
                case 71: {
                    if (this.player.F >= var3_3.b()[0]) {
                        var2_2.b((byte)(var3_3.b()[1] - 2));
                        break;
                    }
                    var2_2.b((byte)(var3_3.b()[2] - 2));
                    break;
                }
                case 72: {
                    var6_58 = EngineUtils.a(var3_3.c()[0], ',');
                    var7_65 = EngineUtils.a(var3_3.c()[1], ',');
                    var8_73 = new WorldEntity[var6_58.length];
                    for (var4_24 = 0; var4_24 < var6_58.length; ++var4_24) {
                        var8_73[var4_24] = new WorldEntity();
                        var8_73[var4_24].a(259, false);
                        if (EngineUtils.b(var6_58[var4_24]) == -1) {
                            var8_73[var4_24].a(EngineUtils.d(var7_65[var4_24]), (byte)-1, true);
                            var8_73[var4_24].c();
                            var8_73[var4_24].b(this.player.getPosX(), this.player.getPosY() - 40);
                            var8_73[var4_24].a(this.player);
                        } else {
                            var8_73[var4_24].a(EngineUtils.d(var7_65[var4_24]), (byte)-1, true);
                            var8_73[var4_24].c();
                            var8_73[var4_24].b(this.worldManager.npcList[EngineUtils.b(var6_58[var4_24])].l(), this.worldManager.npcList[EngineUtils.b(var6_58[var4_24])].m() - 40);
                            var8_73[var4_24].a(this.worldManager.npcList[EngineUtils.b(var6_58[var4_24])]);
                        }
                        game.OverworldScreen.C.addElement(var8_73[var4_24]);
                    }
                    break;
                }
                case 74: {
                    if (((int[])this.player.K.elementAt(0))[1] > 0) {
                        var2_2.b((byte)(var3_3.b()[0] - 2));
                        break;
                    }
                    var2_2.b((byte)(var3_3.b()[1] - 2));
                    break;
                }
                case 76: {
                    this.b[game.WorldManager.l[this.worldManager.f] + this.worldManager.g][var2_2.b()] = 3;
                    game.WorldManager.getInstance().f = var3_3.b()[0];
                    game.WorldManager.getInstance().g = var3_3.b()[1];
                    game.WorldManager.getInstance().j = -1;
                    this.worldManager.a((byte)29);
                    break;
                }
                case 77: {
                    this.b[game.WorldManager.a((int)var3_3.b()[0], (int)var3_3.b()[1])][var3_3.b()[2]] = 4;
                    if (var3_3.b()[0] != this.worldManager.f || var3_3.b()[1] != this.worldManager.g) break;
                    this.scriptSequences[var3_3.b()[2]].a((byte)4);
                    break;
                }
                case 80: {
                    if (var2_2.a() != 5) {
                        if (var3_3.b()[0] == 0) {
                            this.B = 0;
                            this.n = (byte)4;
                        } else if (var3_3.b()[0] == 1) {
                            game.GameStateController.getInstance().c = game.GameStateController.getInstance().b;
                            var7_66 = game.GameStateController.getInstance().c - game.GameStateController.getInstance().a;
                            if (EngineUtils.randomInt(var7_66)[2] <= 70L) {
                                var4_25 = this.player.getActivePetIndex();
                                if (var4_25 == 0) {
                                    this.parentScreen.S.b("Đạt được #2Lục hành điểu");
                                    this.player.a(54, 5, (byte)2, (short)-1, new int[]{1, 30, 45});
                                } else if (var4_25 == 1) {
                                    this.parentScreen.S.b("Đạt được #2Lục hành điểu#0 ba lô đã đủ, đã để vào ngân hàng");
                                    var4_25 = EngineUtils.b(GameDatabase.gameDatabase[0][54][3], (int)GameDatabase.gameDatabase[0][54][3]);
                                    this.player.a(54, 5, (byte)2, (byte)var4_25, 0, 0, new int[]{1, 30, 45});
                                } else {
                                    this.parentScreen.S.b("Không có không gian, đã phóng sinh");
                                }
                            } else if (EngineUtils.randomInt(var7_66)[2] <= 80L) {
                                this.player.s(1000);
                                this.parentScreen.S.b("Thưởng 1000 kim");
                            } else if (EngineUtils.randomInt(var7_66)[2] <= 130L) {
                                this.player.s(750);
                                this.parentScreen.S.b("Thưởng 750 kim");
                            } else if (EngineUtils.randomInt(var7_66)[2] <= 200L) {
                                this.player.s(600);
                                this.parentScreen.S.b("Thưởng 600 kim");
                            }
                            game.GameStateController.getInstance().b = 0L;
                            game.GameStateController.getInstance().a = 0L;
                        }
                        var2_2.a((byte)5);
                        break;
                    }
                    if (var3_3.b()[0] == 0) {
                        ++this.B;
                        if (this.n > 0) {
                            if (this.B / 10 == 0 || this.B % 10 != 0) break;
                            this.n = (byte)(this.n - 1);
                            break;
                        }
                        this.n = 0;
                        game.GameStateController.getInstance().b = game.GameStateController.getInstance().a = System.currentTimeMillis();
                        game.GameStateController.getInstance().c = 0L;
                    } else {
                        if (var3_3.b()[0] != 1 || !this.parentScreen.S.ax()) break;
                        if (this.p == 0) {
                            this.aj = this.j();
                        }
                        this.p = (byte)(this.p + 1);
                    }
                    ** GOTO lbl1044
                }
                case 81: {
                    if (var3_3.b()[0] == 0) {
                        if (this.player.t(var3_3.b()[1])) {
                            var2_2.b((byte)(var3_3.b()[2] - 2));
                            break;
                        }
                        var2_2.b((byte)(var3_3.b()[3] - 2));
                        break;
                    }
                    if (var3_3.b()[0] != 1) break;
                    if (this.player.v(var3_3.b()[1])) {
                        var2_2.b((byte)(var3_3.b()[2] - 2));
                        break;
                    }
                    var2_2.b((byte)(var3_3.b()[3] - 2));
                    break;
                }
                case 82: {
                    var7_67 = var3_3.b()[0];
                    var8_74 = EngineUtils.e(var3_3.c()[0]);
                    for (var4_26 = 0; var4_26 < var7_67; ++var4_26) {
                        this.worldManager.npcList[var8_74[var4_26]].r();
                        this.worldManager.npcList[var8_74[var4_26]].s();
                    }
                    break;
                }
                case 83: {
                    if (var2_2.a() != 5) {
                        this.parentScreen.a((byte)30);
                        var2_2.a((byte)5);
                        break;
                    }
                    var2_2.b((byte)(var3_3.b()[game.OverworldScreen.m] - 2));
                    ** GOTO lbl1044
                }
                case 84: {
                    if (var2_2.a() != 5) {
                        var4_27 = null;
                        if (var3_3.b()[2] == 1) {
                            var4_27 = new int[]{this.p, 5 - this.p};
                        } else if (var3_3.b()[2] == 0) {
                            var4_27 = new int[]{this.player.I, this.player.R.length - this.player.I};
                        }
                        var4_27 = game.OverworldScreen.a(var3_3.c()[1], var4_27);
                        this.worldManager.S.a(var3_3.c()[0], (String)var4_27, (int)var3_3.b()[1]);
                        var2_2.a((byte)5);
                        break;
                    }
                    if (!this.worldManager.S.c(var3_3.b()[1], -1) || !this.parentScreen.k(196640)) break;
                    game.WorldManager.getInstance().e();
                    if (EngineUtils.b < EngineUtils.b()) {
                        EngineUtils.c();
                        this.worldManager.S.b(EngineUtils.b);
                        break;
                    }
                    if (game.WorldManager.u != -1 && this.worldManager.npcList[game.WorldManager.u].a.a <= 85 && this.worldManager.npcList[game.WorldManager.u].u() == 0) {
                        game.WorldManager.getInstance().a(game.WorldManager.getInstance().npcList[game.WorldManager.u].i, game.WorldManager.getInstance().npcList[game.WorldManager.u].j - 40, game.WorldManager.getInstance().npcList[game.WorldManager.u]);
                    }
                    game.OverworldScreen.g = false;
                    game.OverworldScreen.h = false;
                    this.worldManager.S.aC();
                    var2_2.a((byte)1);
                    break;
                }
                case 85: {
                    if (this.p >= 0 && this.p < 5) {
                        var2_2.b((byte)(var3_3.b()[0] - 2));
                        break;
                    }
                    var2_2.b((byte)(var3_3.b()[1] - 2));
                    break;
                }
                case 87: {
                    if (var2_2.a() != 5) {
                        if (var3_3.b()[0] == 0) {
                            this.player.a(var3_3.b()[7], var3_3.b()[1], var3_3.b()[2], (byte)var3_3.b()[4], (byte)var3_3.b()[3], new int[]{1, var3_3.b()[5], var3_3.b()[6]});
                        } else if (var3_3.b()[0] == 1) {
                            this.player.n(var3_3.b()[1]);
                        }
                        var2_2.a((byte)5);
                        break;
                    }
                    if (!this.parentScreen.S.ax()) break;
                    ** GOTO lbl1044
                }
                case 88: {
                    if (this.player.getActivePetIndex() == 2) {
                        var2_2.b((byte)(var3_3.b()[0] - 2));
                    } else {
                        var2_2.b((byte)(var3_3.b()[1] - 2));
                    }
lbl1044:
                    // 31 sources

                    var2_2.a((byte)1);
                }
            }
            if (var2_2.a() != 5 && var2_2.a() != 6) {
                var2_2.e();
            }
            if (var2_2.a() == 3 || var2_2.a() == 4) {
                game.OverworldScreen.h = false;
                this.z.removeElement(var2_2);
                var4_28 = game.WorldManager.a(var2_2.f()[0], var2_2.f()[1]);
                if (this.b[var4_28] != null) {
                    this.b[var4_28][var2_2.b()] = var2_2.a();
                }
                if (var2_2.a() == 3 && var2_2.d().a() == 44 && var2_2.d().b()[1] == 1) {
                    game.OverworldScreen.b(var2_2.d().b()[0]);
                }
                this.i();
                continue;
            }
            ++var1_1;
        }
    }

    public final byte g() {
        return this.A;
    }

    public final boolean h() {
        if (this.scriptSequences == null) {
            return false;
        }
        for (int i = 0; i < this.z.size(); ++i) {
            ScriptSequence p2 = (ScriptSequence)this.z.elementAt(i);
            if (p2.a() == 2 || p2.a() == 6) continue;
            return true;
        }
        return false;
    }

    /*
     * Enabled aggressive block sorting
     */
    private boolean a(ScriptCommand ad2) {
        boolean bl = false;
        if (ad2.b()[7] != -1) {
            if (ad2.b()[7] == -1) return bl;
            if (this.b[game.WorldManager.a((int)ad2.b()[5], (int)ad2.b()[6])] == null) return bl;
            if (this.b[game.WorldManager.a((int)ad2.b()[5], (int)ad2.b()[6])][ad2.b()[7]] != 3) return bl;
        }
        switch (ad2.b()[8]) {
            case 0: {
                if (!this.player.T[ad2.b()[9]]) return bl;
                break;
            }
            case 1: {
                return true;
            }
            case 2: {
                int n2;
                if (this.player.O.size() + this.player.A < ad2.b()[9]) return bl;
                for (n2 = 0; n2 < this.player.A; ++n2) {
                    if (this.player.z[n2].s() != ad2.b()[10]) continue;
                    bl = true;
                    break;
                }
                if (bl) return bl;
                n2 = 0;
                while (n2 < this.player.O.size()) {
                    if (((int[])this.player.O.elementAt(n2))[1] == ad2.b()[10]) return true;
                    ++n2;
                }
                return bl;
            }
            case 3: {
                if (this.player.F < ad2.b()[9]) return bl;
                break;
            }
            case 4: {
                if (this.player.a((byte)ad2.b()[9], (int)ad2.b()[10]) != 2) return bl;
                break;
            }
            case 5: {
                if (t <= ad2.b()[9]) return bl;
                break;
            }
            case 6: {
                if (t != ad2.b()[9]) return bl;
            }
        }
        return true;
    }

    private boolean b(ScriptCommand object) {
        boolean bl = false;
        if (((ScriptCommand)object).b()[7] == -1 || ((ScriptCommand)object).b()[7] != -1 && this.b[game.WorldManager.a((int)((ScriptCommand)object).b()[5], (int)((ScriptCommand)object).b()[6])] != null && this.b[game.WorldManager.a((int)((ScriptCommand)object).b()[5], (int)((ScriptCommand)object).b()[6])][((ScriptCommand)object).b()[7]] == 3) {
            switch (((ScriptCommand)object).b()[8]) {
                case 0: {
                    if (this.player.a((byte)((ScriptCommand)object).b()[9], (int)((ScriptCommand)object).b()[10]) != 2) break;
                    bl = true;
                    break;
                }
                case 1: {
                    if (!this.player.T[((ScriptCommand)object).b()[9]]) break;
                    bl = true;
                    break;
                }
                case 2: 
                case 4: {
                    if (this.b[game.WorldManager.a((int)((ScriptCommand)object).b()[5], (int)((ScriptCommand)object).b()[6])] == null || this.b[game.WorldManager.a((int)((ScriptCommand)object).b()[5], (int)((ScriptCommand)object).b()[6])][((ScriptCommand)object).b()[7]] != 3) break;
                    bl = true;
                    break;
                }
                case 3: {
                    if (!this.player.b((int)((ScriptCommand)object).b()[9], (int)((ScriptCommand)object).b()[10], (byte)0)) break;
                    bl = true;
                    break;
                }
                case 5: {
                    if (this.player.F < ((ScriptCommand)object).b()[9]) break;
                    bl = true;
                    break;
                }
                case 6: {
                    int n2;
                    object = new byte[]{0, 1, 2, 3};
                    block8: for (n2 = 0; n2 < this.player.A; ++n2) {
                        for (int i = 0; i < ((Object)object).length; ++i) {
                            if (object[i] == -1 || object[i] != GameDatabase.spriteTable((byte)0, (short)this.player.z[n2].q(), (byte)1)) continue;
                            object[i] = -1;
                            continue block8;
                        }
                    }
                    for (n2 = 0; n2 < ((Object)object).length && object[n2] == -1; ++n2) {
                    }
                    if (n2 < ((Object)object).length) break;
                    bl = true;
                }
            }
        }
        return bl;
    }

    public final void i() {
        NpcEntity a2;
        WorldEntity f2;
        ScriptCommand ad2;
        int n2;
        if (d == null) {
            d = new Vector();
        }
        d.removeAllElements();
        Vector<String> vector = new Vector<String>();
        for (n2 = 0; n2 < al.length; ++n2) {
            if (game.WorldManager.a((int)al[n2][0], (int)al[n2][1]) != game.WorldManager.a(game.WorldManager.getInstance().f, game.WorldManager.getInstance().g) || this.scriptSequences[al[n2][2]].a() != 0 && this.scriptSequences[al[n2][2]].a() != 4 || vector.contains("" + (ad2 = this.scriptSequences[al[n2][2]].d()).b()[4])) continue;
            if (this.b(ad2)) {
                f2 = new WorldEntity();
                f2.a(259, false);
                f2.a((byte)1, (byte)-1, true);
                f2.b(this.worldManager.npcList[ad2.b()[4]].i, this.worldManager.npcList[ad2.b()[4]].j - 40);
                this.worldManager.npcList[ad2.b()[4]].t();
                a2 = this.worldManager.npcList[ad2.b()[4]];
                f2.p = a2;
                f2.c();
                d.addElement(f2);
                vector.addElement("" + ad2.b()[4]);
                continue;
            }
            int n3 = game.OverworldScreen.c(ad2.b()[0]);
            if ((ad2.b()[1] != 0 || this.b[game.WorldManager.a((int)ak[n2][0], (int)ak[n2][1])][ak[n2][2]] != 3 || this.b[game.WorldManager.a((int)al[n2][0], (int)al[n2][1])][al[n2][2]] == 3) && (ad2.b()[1] != 1 || n3 == -1 || s[n3][1] != 1)) continue;
            WorldEntity f3 = new WorldEntity();
            f3.a(259, false);
            f3.a((byte)15, (byte)-1, true);
            f3.b(this.worldManager.npcList[ad2.b()[4]].i, this.worldManager.npcList[ad2.b()[4]].j - 40);
            this.worldManager.npcList[ad2.b()[4]].t();
            a2 = this.worldManager.npcList[ad2.b()[4]];
            f3.p = a2;
            f3.c();
            d.addElement(f3);
            vector.addElement("" + ad2.b()[4]);
        }
        for (n2 = 0; n2 < ak.length; ++n2) {
            if (game.WorldManager.a((int)ak[n2][0], (int)ak[n2][1]) != game.WorldManager.a(game.WorldManager.getInstance().f, game.WorldManager.getInstance().g) || this.scriptSequences[ak[n2][2]].a() != 0 && this.scriptSequences[ak[n2][2]].a() != 4 || vector.contains("" + (ad2 = this.scriptSequences[ak[n2][2]].d()).b()[4]) || !this.a(ad2)) continue;
            f2 = new WorldEntity();
            f2.a(259, false);
            f2.a((byte)7, (byte)-1, true);
            f2.b(this.worldManager.npcList[ad2.b()[4]].i, this.worldManager.npcList[ad2.b()[4]].j - 40);
            this.worldManager.npcList[ad2.b()[4]].t();
            a2 = this.worldManager.npcList[ad2.b()[4]];
            f2.p = a2;
            f2.c();
            d.addElement(f2);
        }
    }

    public final int[] j() {
        int[] intArray = new int[4];
        int[] intArray2 = intArray;
        intArray[0] = this.ai.get(1);
        intArray2[1] = this.ai.get(2);
        intArray2[2] = this.ai.get(5);
        intArray2[3] = this.ai.get(11);
        return this.aj;
    }

    public final int[] k() {
        return this.aj;
    }

    public final void a(int[] intArray) {
        this.aj = intArray;
    }

    static {
        e = false;
        f = true;
        g = false;
        h = false;
        j = true;
        m = (byte)-1;
        s = null;
        t = 0;
        u = 0;
    }
}

