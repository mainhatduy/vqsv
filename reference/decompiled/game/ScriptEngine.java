/*
 * Decompiled with CFR 0.152.
 */
package game;

import game.Pet;
import game.OverworldScreen;
import game.BattleScreen;
import game.Player;
import game.GameStateController;
import game.WorldManager;
import java.util.Vector;

/**
 * ScriptEngine - Story event interpreter, quest script execution, NPC dialogue, and shop logic.
 */
public final class ScriptEngine
implements GameStateController {
    private static ScriptEngine n;
    private BaseScreen o;
    private UIManager p = UIManager.getInstance();
    private Player q;
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
    private String[] O = new String[]{"Đạt được 2000 kim tiền", "Đạt được 5 Phong ấn cầu", "Đạt được 5 Bánh Sandwich", "Đạt được 2 Sinh mệnh thạch", "Đạt được 2 huy hiệu"};
    private short[][] P = new short[][]{{621, 622}, {623, 624}, {625, 626}, {627, 628}, {629, 630, 631, 632}};
    private short[][] Q = new short[][]{{5, 2, 112, 224, 2, 2, 5, 6, 1, 6, 0, 112, 224, 2, 0, 1, 0, 10}, {4, 0, 48, 176, 2, 2, 3, 6, 3, 6, 0, 112, 224, 2, 2, 1, 0, 10}, {3, 6, 288, 224, 3, 0, 3, 6, 3, 6, 0, 112, 224, 2, 2, 1, 0, 10}, {1, 5, 272, 128, 3, 0, 5, 6, 1, 6, 0, 112, 224, 2, 0, 1, 0, 10}, {1, 5, 272, 128, 3, 2, 0, 0, 0, 3, 6, 288, 224, 3, 0, 0, 0, 0, 4, 0, 48, 176, 2, 0, 0, 0, 0, 5, 2, 112, 224, 2, 2, 0, 0, 0}};
    private byte R;
    private byte S;
    private String[] T = new String[]{"Dẫn thưởng", "Tiến hóa", "Dị hóa", "Tài liệu", "Cách mở"};

    public static ScriptEngine getInstance() {
        if (n == null) {
            n = new ScriptEngine();
        }
        return n;
    }

    public ScriptEngine() {
        if (this.q == null) {
            this.q = game.Player.getInstance();
        }
    }

    public final void b() {
        n = null;
        this.q = null;
    }

    public final void a(BaseScreen an2) {
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
            for (int i = 1; i <= 7; ++i) {
                if (i == 2 || i == 3 || i == 4) continue;
                this.p.a.a(i).a(false);
            }
        }
    }

    public final void e() {
        if (this.u < 2 && !game.OverworldScreen.f && game.WorldManager.J && this.p.c("/data/ui/world.ui")) {
            if (UIManager.a(this.p.d("/data/ui/world.ui"), 4)) {
                ((ItemListWidget)this.p.d((String)"/data/ui/world.ui").a((int)6)).h().a = ((WorldManager)this.o).k;
                this.u = 1;
            } else if (this.u == 1 && ((ItemListWidget)this.p.d((String)"/data/ui/world.ui").a((int)1)).h().m.a().h() >= 5) {
                ((ItemListWidget)this.p.d((String)"/data/ui/world.ui").a((int)6)).h().a = "";
                this.u = (byte)2;
                game.WorldManager.J = false;
            }
        }
        this.f();
    }

    public final boolean f() {
        if (this.v < 2 && this.p.b("/data/ui/openbox.ui")) {
            if (UIManager.a(this.p.a, 3) && this.p.a.a((int)1).h().m.a().g() == 9) {
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
                if (UIManager.a(this.p.a, 4) && this.p.a.a((int)1).h().m.a().g() == 10) {
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
        ((MenuWidget)this.p.a.a((int)0)).a.a = A.length;
        ((MenuWidget)this.p.a.a((int)0)).a.a(1);
        this.aT();
    }

    private void aT() {
        this.w = ((MenuWidget)this.p.a.a((int)0)).a.e;
        this.h = ((MenuWidget)this.p.a.a((int)0)).a.f;
        for (int i = 0; i < 5; ++i) {
            this.p.a.a((int)(i + 5)).h().a = A[i + this.w];
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
            game.WorldManager.getInstance().f = B[this.h * 5];
            game.WorldManager.getInstance().g = B[this.h * 5 + 1];
            game.WorldManager.getInstance().h = B[this.h * 5 + 2];
            game.WorldManager.getInstance().i = B[this.h * 5 + 3];
            game.WorldManager.w = (byte)B[this.h * 5 + 4];
            game.WorldManager.getInstance().j = -1;
            game.GameStateController.getInstance().a((byte)9);
            return;
        }
        if (this.o.k(262144 /* MASK_SOFT_RIGHT */)) {
            this.o.a((byte)8);
            this.p.a("/data/ui/transmit.ui");
        }
    }

    public final boolean j() {
        return this.p.b("/data/ui/openbox.ui") || this.p.b("/data/ui/taskTip.ui");
    }

    public final void k() {
        String[] strArray = new String[]{"Tùy thân cửa hàng", "Sủng vật", "Lưng bao", "Đồ giám", "Nhiệm vụ", "Lưu dữ liệu"};
        this.aS();
        this.p.a("/data/ui/gamemenu.ui", 257, this);
        if (BaseScreen.X) {
            ((MenuWidget)this.p.a.a((int)0)).a.a = 6;
            this.p.a.a((int)14).h().a = BaseScreen.f(605 + this.b);
            this.p.a.a((int)15).h().a = strArray[0];
            for (int i = 0; i < 5; ++i) {
                this.p.a.a((int)(i + 5)).h().a = strArray[i + 1];
            }
        } else {
            this.p.a.a((int)14).h().a = BaseScreen.f(606 + this.b);
            ((MenuWidget)this.p.a.a((int)0)).a.a = 5;
            this.p.a.a((int)15).h().a = strArray[1];
            for (int i3 = 0; i3 < 4; ++i3) {
                this.p.a.a((int)(i3 + 5)).h().a = strArray[i3 + 2];
            }
            this.p.a.a(9).a(false);
        }
        ((MenuWidget)this.p.a.a((int)0)).a.f = this.b;
        this.p.a.a((int)18).h().a = "" + this.q.G();
        this.p.a.a((int)19).h().a = "" + this.q.E();
        this.f = 0;
    }

    public final void l() {
        this.o.l();
        if (!BaseScreen.b(this.b, 0) && !this.j() && this.o.k(4100)) {
            this.p.a.b(0);
        } else if (!BaseScreen.b(this.b, 0) && !this.j() && this.o.k(8448)) {
            this.p.a.b(1);
        } else if (!this.j() && BaseScreen.I() && this.o.k(196640)) {
            if (BaseScreen.H() && !BaseScreen.b(this.b, 0)) {
                return;
            }
            if (BaseScreen.X) {
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
        } else if (BaseScreen.J() && this.o.k(262144 /* MASK_SOFT_RIGHT */)) {
            this.p.a("/data/ui/gamemenu.ui");
            this.o.a((byte)0);
        }
        this.g();
    }

    public final void m() {
        this.aS();
        this.p.a("/data/ui/gamesystem.ui", 257, this);
        ((MenuWidget)this.p.a.a((int)0)).a.f = this.b;
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
                        ((MenuWidget)this.p.a.a((int)0)).a.f = this.c = 1;
                        this.p.a.a((int)12).h().a = "";
                        this.p.a.a((int)13).h().a = "Không";
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
                            game.GameStateController.getInstance().b = 0L;
                            game.GameStateController.getInstance().a = 0L;
                            game.Player.getInstance().y = false;
                            game.GameStateController.getInstance().a((byte)7);
                            this.p.a("/data/ui/gamesystem.ui");
                        }
                    }
                }
            }
            return;
        }
        if (this.o.k(262144 /* MASK_SOFT_RIGHT */)) {
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
            this.p.a.a((int)5).h().a = "Trợ giúp";
            this.p.a.a((int)8).h().a = "Nhấn nút 2, 4, 6, 8 để di chuyển#nNút 5: công kích, đối thoại, xác nhận#nNút 1, 3: Xem nhiệm vụ#nNút 9: lựa chọn sủng vật cưỡi#nNút 0: Xem bản đồ#nNút mềm trái: menu hệ thống#nNút mềm phải: menu trò chơi";
            for (int i = 0; i < 28; ++i) {
                this.p.a.a(i + 9).a(false);
            }
        } else if (n2 > 0) {
            this.p.a.a((int)8).h().a = "";
            for (int i3 = 0; i3 < 14; ++i3) {
                this.p.a.a(9 + (i3 << 1)).a(true);
                this.p.a.a(9 + (i3 << 1) + 1).a(true);
                if ((n2 - 1) * 14 + i3 < 26) {
                    this.p.a.a((int)(9 + (i3 << 1))).h().m = new SpriteWidget();
                    this.p.a.a((int)(9 + (i3 << 1))).h().m.a(0);
                    this.p.a.a((int)(9 + (i3 << 1))).h().m.a = (byte)2;
                    this.p.a.a((int)(9 + (i3 << 1))).h().m.a(325, false, (byte)-2);
                    this.p.a.a((int)(9 + (i3 << 1))).h().m.a((n2 - 1) * 14 + i3 + 1);
                    if ((n2 - 1) * 14 + i3 <= 10) {
                        this.p.a.a((int)(9 + (i3 << 1) + 1)).h().a = BaseScreen.f(i3 + 311);
                        continue;
                    }
                    this.p.a.a((int)(9 + (i3 << 1) + 1)).h().a = BaseScreen.f(333 + ((n2 - 1) * 14 + i3 - 11));
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
        if (this.o.k(262144 /* MASK_SOFT_RIGHT */)) {
            this.o.a((byte)0);
            this.p.a("/data/ui/help1.ui");
        }
    }

    public final void q() {
        this.p.a("/data/ui/help.ui", 257, this);
        this.p.a.a((int)5).h().a = "Quan tại";
        this.p.a.a((int)8).h().a = "Tên trò chơi: Sủng vật Vương quốc - Liệt hỏa#nViệt hóa: BIGAME";
        this.p.a.a(6).a(true);
        this.p.a.a(7).a(false);
        for (int i = 9; i < 13; ++i) {
            this.p.a.a(i).a(false);
        }
    }

    public final void r() {
        if (this.o.k(262144 /* MASK_SOFT_RIGHT */)) {
            this.o.a((byte)0);
            this.p.a("/data/ui/help.ui");
        }
    }

    public final void s() {
        this.p.a("/data/ui/help.ui", 257, this);
        this.p.a.a((int)5).h().a = "Tùy chọn";
        this.p.a.a((int)8).h().a = "";
        this.p.a.a(6).a(false);
        this.p.a.a(7).a(true);
        for (int i = 9; i < 13; ++i) {
            this.p.a.a(i).a(true);
        }
        this.aU();
    }

    private void aU() {
        for (int i = 1; i < 4; ++i) {
            this.p.a.a((int)(i + 9)).h().j = i <= game.GameStateController.getInstance().g ? -2148 : -8540732;
        }
    }

    public final void t() {
        if (this.o.k(16400)) {
            game.GameStateController.getInstance().i();
            this.aU();
            return;
        }
        if (this.o.k(32832)) {
            game.GameStateController.getInstance().h();
            this.aU();
            return;
        }
        if (this.o.k(131072 /* MASK_SOFT_LEFT */)) {
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
        if (this.o.k(262144 /* MASK_SOFT_RIGHT */)) {
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
            game.GameStateController.getInstance().i();
            this.aU();
            return;
        }
        if (this.o.k(32832)) {
            this.p.a.b(3);
            game.GameStateController.getInstance().h();
            this.aU();
            return;
        }
        if (this.o.k(131072 /* MASK_SOFT_LEFT */)) {
            this.o.a((byte)13);
            this.p.a("/data/ui/help.ui");
        }
    }

    public final void y() {
        this.p.a("/data/ui/petstate.ui", 257, this);
        this.f = 0;
        if (this.q.O.size() > 6) {
            ((MenuWidget)this.p.a.a((int)0)).a.a(1);
        } else {
            ((MenuWidget)this.p.a.a((int)0)).a.a(-1);
        }
        this.p.a.a((int)2).h().a = "Ngân hàng Sủng vật";
        this.p.a.a(75).a(false);
        this.p.a.a(76).a(false);
        this.aV();
    }

    private void aV() {
        ((MenuWidget)this.p.a.a((int)0)).a.a = this.q.O.size();
        this.w = ((MenuWidget)this.p.a.a((int)0)).a.e;
        this.h = ((MenuWidget)this.p.a.a((int)0)).a.f;
        ((MenuWidget)this.p.a.a((int)0)).a.d = this.q.O.size() >= 6 ? 6 : this.q.O.size();
        if (this.h >= this.q.O.size()) {
            ((MenuWidget)this.p.a.a((int)0)).a.f = this.h = this.q.O.size() - 1;
        }
        if (this.w > 0 && this.h - this.w < 5) {
            --this.w;
            ((MenuWidget)this.p.a.a((int)0)).a.e = this.w;
        }
        for (int i = 0; i < 6; ++i) {
            if (this.w + i < this.q.O.size()) {
                int[] intArray = (int[])this.q.O.elementAt(this.w + i);
                if (i == 0) {
                    this.p.a.a((int)(14 + i * 6)).h().a = "" + (this.w + i + 1);
                } else {
                    this.p.a.a((int)(15 + i * 6)).h().a = "" + (this.w + i + 1);
                }
                this.p.a.a((int)(16 + i * 6)).h().a = "#P" + intArray[6] * 100 / game.Pet.a(intArray[0], intArray[1], intArray[4], 1);
                this.p.a.a((int)(17 + i * 6)).h().a = "#P" + game.Pet.a((short)intArray[7], (short)intArray[1]);
                continue;
            }
            this.p.a.a((int)(16 + i * 6)).h().a = "#P0";
            this.p.a.a((int)(17 + i * 6)).h().a = "#P0";
        }
        int[] intArray = null;
        if (this.q.O.size() > 0) {
            intArray = (int[])this.q.O.elementAt(this.h);
        }
        if (intArray != null) {
            if (this.p.a.a((int)48).h().m != null) {
                this.p.a.a((int)48).h().m.d();
            } else {
                this.p.a.a((int)48).h().m = new SpriteWidget();
                this.p.a.a((int)48).h().m.a(0);
                this.p.a.a((int)48).h().m.a = (byte)3;
            }
            this.p.a.a((int)48).h().m.a(GameDatabase.spriteTable((byte)0, (short)intArray[0], (byte)17), false, (byte)-1);
            this.p.a.a((int)51).h().a = BaseScreen.f(GameDatabase.spriteTable((byte)0, (short)intArray[0], (byte)0));
            this.p.a.a((int)52).h().a = BaseScreen.f(365 + GameDatabase.spriteTable((byte)0, (short)intArray[0], (byte)1));
            if (GameDatabase.spriteTable((byte)0, (short)intArray[0], (byte)19) == -1) {
                this.p.a.a((int)62).h().a = "";
            } else if (GameDatabase.gameDatabase[0][GameDatabase.spriteTable((byte)0, (short)intArray[0], (byte)19)][2] == 1 || GameDatabase.gameDatabase[0][GameDatabase.spriteTable((byte)0, (short)intArray[0], (byte)19)][2] == 2) {
                this.p.a.a((int)62).h().a = "Có thể tiến hóa";
            } else if (GameDatabase.gameDatabase[0][GameDatabase.spriteTable((byte)0, (short)intArray[0], (byte)19)][2] == 3) {
                this.p.a.a((int)62).h().a = "Có thể dị hoá";
            }
            this.p.a.a((int)61).h().a = game.Pet.y(intArray[0]);
            if (this.p.a.a((int)59).h().m == null) {
                this.p.a.a((int)59).h().m = new SpriteWidget();
                this.p.a.a((int)59).h().m.a(0);
                this.p.a.a((int)59).h().m.a = (byte)2;
                this.p.a.a((int)59).h().m.a(258, false, (byte)-1);
            }
            if (intArray[2] != -1) {
                this.p.a.a((int)59).h().m.a(GameDatabase.gameDatabase[3][intArray[2]][1]);
                this.p.a.a((int)60).h().a = BaseScreen.f(GameDatabase.gameDatabase[3][intArray[2]][0]);
            } else {
                this.p.a.a((int)59).h().m.a(0);
                this.p.a.a((int)60).h().a = "";
            }
            this.p.a.a((int)65).h().a = "" + intArray[1];
            this.p.a.a((int)66).h().a = "" + game.Pet.a(intArray[0], intArray[1], intArray[4], 2);
            this.p.a.a((int)67).h().a = "" + game.Pet.a(intArray[0], intArray[1], intArray[4], 3);
            this.p.a.a((int)68).h().a = "" + game.Pet.a(intArray[0], intArray[1], intArray[4], 4);
            int n2 = intArray[4];
            int n3 = GameDatabase.spriteTable((byte)0, (short)intArray[0], (byte)4) - 1;
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
                this.p.a.a((int)64).h().a = "Lấy ra";
                return;
            }
            if (this.b == 2) {
                this.p.a.a((int)64).h().a = "Phóng sinh";
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
                        this.a("Ba lô Sủng vật đã đủ", "Nhấn nút 5 để tiếp tục");
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
                    int[] intArray = (int[])this.q.O.elementAt(this.h);
                    if (GameDatabase.spriteTable((byte)0, (short)intArray[0], (byte)22) == 2) {
                        this.f = 2;
                        this.E();
                        this.a("Thần thú không thể phóng sinh", "Nhấn nút 5 để tiếp tục");
                    } else {
                        this.f = 1;
                        this.p.a("/data/ui/msgconfirm.ui", 257, this);
                        this.b("Bạn muốn phóng sinh sủng vật này?", "Xác nhận");
                    }
                }
            } else if (this.o.k(786432)) {
                this.o.a((byte)16);
                this.p.a("/data/ui/petstate.ui");
            }
        } else if (this.f > 0) {
            if (this.o.k(196640) && this.b == 1 || this.o.k(131072 /* MASK_SOFT_LEFT */) && this.b == 2) {
                if (this.b == 1) {
                    this.p.a("/data/ui/msgwarm.ui");
                    this.f = 0;
                } else if (this.b == 2) {
                    if (this.f == 1) {
                        this.p.a("/data/ui/msgconfirm.ui");
                        int[] intArray = (int[])this.q.O.elementAt(this.h);
                        this.q.l(intArray[2]);
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
        this.p.a.a((int)5).h().a = "Ngân hàng Sủng vật";
        this.p.a.a((int)6).h().a = "Gởi lại";
        this.p.a.a((int)7).h().a = "Lấy ra";
        this.p.a.a((int)9).h().a = "Phóng sinh";
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
                    game.OverworldScreen.e = true;
                    this.p.a("/data/ui/shop.ui");
                    this.o.a((byte)0);
                }
            }
            return;
        }
        if (this.o.k(262144 /* MASK_SOFT_RIGHT */)) {
            game.OverworldScreen.e = true;
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
        if (!BaseScreen.b(this.b, 0) && !this.j() && this.f == 0 && this.o.k(4100) && this.aW()) {
            this.p.a.b(0);
        } else if (!BaseScreen.b(this.b, 0) && !this.j() && this.f == 0 && this.o.k(8448) && this.aW()) {
            this.p.a.b(1);
        } else if (this.aW() && !this.j() && BaseScreen.I() && this.o.k(196640)) {
            if (BaseScreen.H() && !BaseScreen.b(this.b, 0)) {
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
                        if (game.Player.getInstance().A() == -1) {
                            this.f = 6;
                            this.E();
                            this.a("Toàn bộ trạng thái đã đầy, không cần khôi phục", "Nhấn nút 5 để tiếp tục");
                            break;
                        }
                        if (!BaseScreen.X) {
                            this.f = 3;
                            for (int i = 0; i < this.q.A; ++i) {
                                this.q.z[i].I();
                            }
                            this.b("Ba lô sủng vật trạng thái toàn bộ khôi phục");
                            break;
                        }
                        ScriptEngine h2 = this;
                        h2.p.a("/data/ui/msgRecover.ui", 257, h2);
                        h2.p.a.a((int)4).h().a = "Có khôi phục trạng thái ba lô sủng vật không?";
                        h2.p.a.a((int)5).h().a = "Cần tiền tài: ";
                        h2.p.a.a((int)6).h().a = "" + game.Player.getInstance().A();
                        h2.p.a.a((int)8).h().a = "" + game.Player.getInstance().E();
                        this.p.a("/data/ui/shop.ui");
                        this.f = 1;
                        break;
                    }
                    if (this.f == 1) {
                        int n2 = game.Player.getInstance().A();
                        if (game.Player.getInstance().t(n2)) {
                            this.f = 3;
                            game.Player.getInstance().s(-n2);
                            for (n2 = 0; n2 < this.q.A; ++n2) {
                                this.q.z[n2].I();
                            }
                            this.b("Ba lô sủng vật trạng thái toàn bộ khôi phục");
                        } else {
                            this.f = 2;
                            this.E();
                            this.a("Kim tiền chưa đủ", "Nhấn nút 5 để tiếp tục");
                        }
                        this.p.a("/data/ui/msgRecover.ui");
                        break;
                    }
                    if (this.f == 2 && BaseScreen.X) {
                        this.o.a((byte)102);
                    }
                    this.f = 0;
                    this.F();
                    break;
                }
                case 3: {
                    game.OverworldScreen.e = true;
                    this.p.a("/data/ui/shop.ui");
                    this.o.a((byte)0);
                }
            }
        } else if (!this.j() && this.o.k(262144 /* MASK_SOFT_RIGHT */) && BaseScreen.J() && this.aW()) {
            if (this.f == 1) {
                this.p.a("/data/ui/shop.ui", 257, this);
                this.p.a("/data/ui/msgRecover.ui");
                this.f = 0;
                this.b = 0;
            } else if (this.f == 0) {
                game.OverworldScreen.e = true;
                this.p.a("/data/ui/shop.ui");
                this.o.a((byte)0);
            }
        }
        if (this.f == 3 && this.ax()) {
            this.f = 4;
            this.p.a("/data/ui/shop.ui", 257, this);
            this.H();
            this.a("Đang lưu...");
            this.J();
        } else if (this.f == 4 && ((WorldManager)this.o).k()) {
            this.a("Lưu thành công");
            this.f = 5;
        } else if (this.f == 5) {
            this.I();
            this.b = 0;
            this.f = 0;
        }
        this.f();
        this.g = true;
    }

    public final void a(int n2, byte val2) {
        this.p.a("/data/ui/shopbuy.ui", 257, this);
        this.b = 0;
        this.f = 0;
        ((MenuWidget)this.p.a.a((int)0)).a.a = GameDatabase.gameDatabase[n2].length;
        ((MenuWidget)this.p.a.a((int)0)).a.a(1);
        this.p.a.a((int)5).h().a = "Mua";
        if (this.o instanceof WorldManager) {
            this.p.a.a(57).a(true);
            this.p.a.a(58).a(true);
            this.p.a.a((int)57).h().a = "Mua sắm";
            this.p.a.a((int)58).h().a = "Phản hồi";
            this.p.a.a(39).a(false);
            this.p.a.a(40).a(false);
        } else if (this.o instanceof BattleScreen) {
            this.p.a.a(57).a(false);
            this.p.a.a(58).a(false);
            this.p.a.a(39).a(true);
            this.p.a.a(40).a(true);
            this.p.a.a((int)39).h().a = "Mua sắm";
            this.p.a.a((int)40).h().a = "Phản hồi";
        }
        this.b(n2, val2);
    }

    private void b(int n2, byte val2) {
        this.w = ((MenuWidget)this.p.a.a((int)0)).a.e;
        this.h = ((MenuWidget)this.p.a.a((int)0)).a.f;
        for (int i = 0; i < 5; ++i) {
            if (this.p.a.a((int)(i + 51)).h().m == null) {
                this.p.a.a((int)(i + 51)).h().m = new SpriteWidget();
                this.p.a.a((int)(i + 51)).h().m.a(0);
                this.p.a.a((int)(i + 51)).h().m.a = (byte)2;
                this.p.a.a((int)(i + 51)).h().m.a(258, false, (byte)-1);
            }
            this.p.a.a((int)(i + 51)).h().m.a(GameDatabase.gameDatabase[n2][this.w + i][1]);
            this.p.a.a((int)(14 + i * 5)).h().a = BaseScreen.f(GameDatabase.gameDatabase[n2][this.w + i][0]);
            if (this.o instanceof WorldManager) {
                if (this.j == 1 || this.j == 3) {
                    this.p.a.a((int)(15 + i * 5)).h().a = "" + GameDatabase.gameDatabase[n2][this.w + i][3];
                } else if (this.j == 2) {
                    this.p.a.a((int)(15 + i * 5)).h().a = GameDatabase.gameDatabase[n2][this.w + i][4] == 0 ? "" + GameDatabase.gameDatabase[n2][this.w + i][3] * 3 / 2 : "" + GameDatabase.gameDatabase[n2][this.w + i][3];
                }
            } else {
                this.p.a.a((int)(15 + i * 5)).h().a = val2 == 0 && n2 == 4 && this.w + i == 0 ? "" + GameDatabase.gameDatabase[n2][this.w + i][3] : "" + (GameDatabase.gameDatabase[n2][this.w + i][3] << 1);
            }
            if (GameDatabase.gameDatabase[n2][this.w + i][4] == 0) {
                this.p.a.a((int)(i + 45)).h().m.a(84);
                continue;
            }
            if (GameDatabase.gameDatabase[n2][this.w + i][4] == 1) {
                this.p.a.a((int)(i + 45)).h().m.a(83);
                continue;
            }
            if (GameDatabase.gameDatabase[n2][this.w + i][4] != 2) continue;
            this.p.a.a((int)(i + 45)).h().m.a(74);
        }
        this.p.a.a((int)56).h().a = BaseScreen.f(GameDatabase.gameDatabase[n2][this.h][2]);
        this.p.a.a((int)43).h().a = "" + this.q.G();
        this.p.a.a((int)44).h().a = "" + this.q.E();
        this.p.a.a(38).b(102 + this.h * 84 / GameDatabase.gameDatabase[n2].length, this.p.a.a());
    }

    public final void a(byte val, byte val2) {
        this.o.l();
        if (!BaseScreen.b(this.b, 0) && this.f <= 1 && this.o.k(4100) && !this.j()) {
            this.p.a.b(0);
            if (this.f == 0) {
                this.b((int)val, val2);
            }
        } else if (!BaseScreen.b(this.b, 0) && this.f <= 1 && this.o.k(8448) && !this.j()) {
            this.p.a.b(1);
            if (this.f == 0) {
                this.b((int)val, val2);
            }
        } else if (this.f == 1 && this.o.k(16400) && this.c > 0 && !this.j()) {
            --this.c;
            if (this.c <= 0) {
                this.c = 99 - this.q.a(this.h, val2);
            }
            this.a(this.c, this.c * GameDatabase.gameDatabase[val][this.h][3], (int)GameDatabase.gameDatabase[val][this.h][4], (int)val);
        } else if (this.f == 1 && this.o.k(32832) && !this.j()) {
            ++this.c;
            if (this.c > 99 - this.q.a(this.h, val2)) {
                this.c = 1;
            }
            this.a(this.c, this.c * GameDatabase.gameDatabase[val][this.h][3], (int)GameDatabase.gameDatabase[val][this.h][4], (int)val);
        } else if (BaseScreen.I() && this.o.k(196640) && !this.j()) {
            if (BaseScreen.H() && !BaseScreen.b(this.b, 0)) {
                return;
            }
            if (GameDatabase.gameDatabase[val][this.h][4] == 2) {
                if (this.f == 0) {
                    if (BaseScreen.X) {
                        if (!this.q.a(this.h, 1, (byte)0)) {
                            this.f = 3;
                            this.E();
                            this.a("Đạo cụ đã đủ", "Nhấn nút 5 để tiếp tục");
                        } else {
                            this.o.a((byte)101);
                        }
                    } else {
                        this.f = 3;
                        this.p.a("/data/ui/msgwarm.ui", 257, this);
                        this.a("Công năng còn chưa mở khải", "Nhấn nút 5 để tiếp tục");
                    }
                } else {
                    this.f = 0;
                    this.p.a("/data/ui/msgwarm.ui");
                }
            } else if (val2 == 2 && this.h < 12) {
                this.c = 1;
                if (this.q.a(this.h, this.c, val2)) {
                    if (this.f == 0) {
                        this.r = 0;
                        this.b(val, val2);
                    } else if (this.f > 0) {
                        if (BaseScreen.X) {
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
                    this.a("Đạo cụ này đã đủ", "Nhấn nút 5 để tiếp tục");
                } else {
                    this.o.m();
                    this.f = 0;
                    this.p.a("/data/ui/msgwarm.ui");
                    this.b((int)val, val2);
                }
            } else if (this.q.a(this.h, this.c, val2)) {
                if (this.f == 0) {
                    this.f = 1;
                    this.p.a("/data/ui/msgyn.ui", 257, this);
                    this.c = 1;
                    this.r = 0;
                    this.a(this.c, this.c * GameDatabase.gameDatabase[val][this.h][3], (int)GameDatabase.gameDatabase[val][this.h][4], (int)val);
                } else if (this.f == 1) {
                    this.b(val, val2);
                } else if (this.f == 2) {
                    game.WorldManager.getInstance().M.i();
                    this.f = 0;
                    this.c = 0;
                    this.p.a("/data/ui/msgwarm.ui");
                    this.b((int)val, val2);
                } else {
                    if (BaseScreen.X) {
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
                this.a("Đạo cụ này đã đủ", "Nhấn nút 5 để tiếp tục");
            } else {
                this.f = 0;
                this.p.a("/data/ui/msgwarm.ui");
            }
        } else if (this.o.k(262144 /* MASK_SOFT_RIGHT */) && !this.j() && BaseScreen.J()) {
            if (this.f == 0) {
                if (this.o instanceof WorldManager) {
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

    private void b(byte val, byte val2) {
        block29: {
            block25: {
                block26: {
                    int n2;
                    block28: {
                        block27: {
                            if (this.o instanceof WorldManager && (this.j == 1 || this.j == 3) && this.q.b(this.h, this.c * GameDatabase.gameDatabase[val][this.h][3], (int)val) || this.j == 2 && (GameDatabase.gameDatabase[val][this.h][4] == 0 && this.q.b(this.h, this.c * GameDatabase.gameDatabase[val][this.h][3] * 3 / 2, (int)val) || GameDatabase.gameDatabase[val][this.h][4] != 0 && this.q.b(this.h, this.c * GameDatabase.gameDatabase[val][this.h][3], (int)val))) {
                                if (this.r == 0) {
                                    this.q.c(this.h, this.c, val2);
                                    if (GameDatabase.gameDatabase[val][this.h][4] == 0) {
                                        if (this.j == 1 || this.j == 3) {
                                            this.q.s(-this.c * GameDatabase.gameDatabase[val][this.h][3]);
                                        } else if (this.j == 2) {
                                            this.q.s(-this.c * GameDatabase.gameDatabase[val][this.h][3] * 3 / 2);
                                        }
                                    } else if (this.j == 1 || this.j == 3) {
                                        this.q.u(-this.c * GameDatabase.gameDatabase[val][this.h][3]);
                                    } else if (this.j == 2) {
                                        this.q.u(-this.c * GameDatabase.gameDatabase[val][this.h][3]);
                                    }
                                    this.p.a("/data/ui/msgwarm.ui", 257, this);
                                    if (val == 3 && this.h == 17) {
                                        this.a("Đã thành công mua sắm #2" + BaseScreen.f(GameDatabase.gameDatabase[val][this.h][0]) + " * " + 5 * this.c, "Nhấn nút 5 để tiếp tục");
                                    } else {
                                        this.a("Đã thành công mua sắm #2" + BaseScreen.f(GameDatabase.gameDatabase[val][this.h][0]) + " * " + this.c, "Nhấn nút 5 để tiếp tục");
                                    }
                                    this.f = 2;
                                    this.c = 1;
                                } else {
                                    this.f = 0;
                                }
                                this.p.a("/data/ui/msgyn.ui");
                                return;
                            }
                            if (this.o instanceof BattleScreen && this.q.b(this.h, this.c * GameDatabase.gameDatabase[val][this.h][3] << 1, (int)val)) {
                                if (this.r == 0) {
                                    this.q.c(this.h, this.c, val2);
                                    if (GameDatabase.gameDatabase[val][this.h][4] == 0) {
                                        this.q.s(-this.c * GameDatabase.gameDatabase[val][this.h][3] << 1);
                                    } else {
                                        this.q.u(-this.c * GameDatabase.gameDatabase[val][this.h][3] << 1);
                                    }
                                    this.p.a("/data/ui/msgwarm.ui", 257, this);
                                    if (val == 3 && this.h == 17) {
                                        this.a("Đã thành công mua sắm #2" + BaseScreen.f(GameDatabase.gameDatabase[val][this.h][0]) + " * " + 5 * this.c, "Nhấn nút 5 để tiếp tục");
                                    } else {
                                        this.a("Đã thành công mua sắm #2" + BaseScreen.f(GameDatabase.gameDatabase[val][this.h][0]) + " * " + this.c, "Nhấn nút 5 để tiếp tục");
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
                            if (GameDatabase.gameDatabase[val][this.h][4] == 0) {
                                this.f = 3;
                                this.a("Kim tiền chưa đủ", "Nhấn nút 5 để tiếp tục");
                            } else {
                                this.f = 4;
                                this.a("Số lượng Huy chương chưa đủ", "Nhấn nút 5 để tiếp tục");
                            }
                            if (!(this.o instanceof WorldManager)) break block26;
                            if (this.j != 1 && this.j != 3) break block27;
                            n2 = this.c * GameDatabase.gameDatabase[val][this.h][3];
                            break block28;
                        }
                        if (this.j != 2) break block29;
                        n2 = GameDatabase.gameDatabase[val][this.h][4] == 0 ? this.c * GameDatabase.gameDatabase[val][this.h][3] * 3 / 2 : this.c * GameDatabase.gameDatabase[val][this.h][3];
                    }
                    this.b(new int[]{val, val2, this.h, GameDatabase.gameDatabase[val][this.h][4], n2, this.c});
                    break block29;
                }
                if (this.o instanceof BattleScreen) {
                    int n3 = this.c * GameDatabase.gameDatabase[val][this.h][3] << 1;
                    this.b(new int[]{val, val2, this.h, GameDatabase.gameDatabase[val][this.h][4], n3, this.c});
                }
                break block29;
            }
            this.f = 0;
        }
        this.p.a("/data/ui/msgyn.ui");
    }

    private void b(int[] intArray) {
        if (this.C == null) {
            this.C = new Vector();
        } else {
            this.C.removeAllElements();
        }
        this.C.addElement(intArray);
    }

    private void a(int n2, int n3, int n4, int n5) {
        this.p.a.a((int)9).h().a = n5 == 3 && this.h == 17 ? "" + n2 * 5 : "" + n2;
        if (this.o instanceof WorldManager) {
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

    public final void a(String text, String string2) {
        this.p.a.a((int)6).h().a = string2;
        this.p.a.a((int)7).h().a = text;
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

    public final void a(String text) {
        this.p.a.a((int)2).h().a = text;
    }

    public final void J() {
        this.p.a.a(3).a(false);
        this.p.a.a(4).a(false);
    }

    public final void K() {
        if (this.f == 0) {
            if (this.o.k(196640)) {
                this.f = 1;
                this.a("Đang lưu...");
                this.J();
                return;
            }
            if (this.o.k(262144 /* MASK_SOFT_RIGHT */)) {
                this.b = BaseScreen.X ? 5 : 4;
                this.o.a((byte)6);
                this.p.a("/data/ui/msgtip.ui");
                this.f = 0;
                return;
            }
        } else if (this.f == 1) {
            if (((WorldManager)this.o).k()) {
                this.a("Lưu thành công");
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

    private void b(String text, String string2) {
        this.p.a.a((int)2).h().a = string2;
        this.p.a.a((int)4).h().a = text;
    }

    public final void L() {
        this.p.a("/data/ui/shopbuy.ui", 257, this);
        this.b = 0;
        this.f = 0;
        this.p.a.a((int)5).h().a = "Bán ra";
        this.p.a.a((int)39).h().a = "";
        this.p.a.a((int)40).h().a = "";
        this.p.a.a((int)57).h().a = "Bán đi";
        this.p.a.a((int)58).h().a = "Phản hồi";
        this.q.x();
        this.aX();
    }

    private void aX() {
        if (this.q.S.size() > 5) {
            ((MenuWidget)this.p.a.a((int)0)).a.a(1);
        } else {
            ((MenuWidget)this.p.a.a((int)0)).a.a(0);
        }
        ((MenuWidget)this.p.a.a((int)0)).a.a = this.q.S.size();
        this.w = ((MenuWidget)this.p.a.a((int)0)).a.e;
        this.h = ((MenuWidget)this.p.a.a((int)0)).a.f;
        if (this.h >= this.q.S.size()) {
            ((MenuWidget)this.p.a.a((int)0)).a.f = this.h = this.q.S.size() - 1;
        }
        if (this.w > 0 && this.h - this.w < 4) {
            --this.w;
            ((MenuWidget)this.p.a.a((int)0)).a.e = this.w;
        }
        for (int i = 0; i < 5; ++i) {
            if (this.w + i < this.q.S.size()) {
                int n2 = ((int[])this.q.S.elementAt(this.w + i))[0];
                if (this.p.a.a((int)(i + 51)).h().m == null) {
                    this.p.a.a((int)(i + 51)).h().m = new SpriteWidget();
                    this.p.a.a((int)(i + 51)).h().m.a(0);
                    this.p.a.a((int)(i + 51)).h().m.a = (byte)2;
                    this.p.a.a((int)(i + 51)).h().m.a(258, false, (byte)-1);
                }
                this.p.a.a((int)(i + 51)).h().m.a(GameDatabase.gameDatabase[4][n2][1]);
                this.p.a.a((int)(14 + i * 5)).h().a = BaseScreen.f(GameDatabase.gameDatabase[4][n2][0]);
                this.p.a.a((int)(15 + i * 5)).h().a = "" + GameDatabase.gameDatabase[4][n2][3] / 2;
                if (GameDatabase.gameDatabase[4][n2][4] == 0) {
                    this.p.a.a((int)(i + 45)).h().m.a(84);
                    continue;
                }
                if (GameDatabase.gameDatabase[4][n2][4] == 1) {
                    this.p.a.a((int)(i + 45)).h().m.a(83);
                    continue;
                }
                if (GameDatabase.gameDatabase[4][n2][4] != 2) continue;
                this.p.a.a((int)(i + 45)).h().m.a(74);
                continue;
            }
            if (this.p.a.a((int)(i + 51)).h().m != null) {
                this.p.a.a((int)(i + 51)).h().m.d();
            }
            this.p.a.a((int)(14 + i * 5)).h().a = "";
            this.p.a.a((int)(15 + i * 5)).h().a = "";
            this.p.a.a((int)(i + 45)).h().m.a(86);
        }
        this.p.a.a((int)56).h().a = this.q.S.size() > 0 ? BaseScreen.f(GameDatabase.gameDatabase[4][((int[])this.q.S.elementAt(this.h))[0]][2]) : "";
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
            int[] intArray = (int[])this.q.S.elementAt(this.h);
            --this.c;
            if (this.c <= 0) {
                this.c = this.q.a(intArray[0], (byte)0);
            }
            this.a(this.c, this.c * GameDatabase.gameDatabase[4][intArray[0]][3] / 2, (int)GameDatabase.gameDatabase[4][intArray[0]][4], 4);
            return;
        }
        if (this.f == 1 && this.o.k(32832)) {
            int[] intArray = (int[])this.q.S.elementAt(this.h);
            ++this.c;
            if (this.c > this.q.a(intArray[0], (byte)0)) {
                this.c = 1;
            }
            this.a(this.c, this.c * GameDatabase.gameDatabase[4][intArray[0]][3] / 2, (int)GameDatabase.gameDatabase[4][intArray[0]][4], 4);
            return;
        }
        if (this.o.k(196640) && this.q.S.size() > 0) {
            int[] intArray = (int[])this.q.S.elementAt(this.h);
            if (this.f == 0) {
                this.f = 1;
                this.p.a("/data/ui/msgyn.ui", 257, this);
                this.c = 1;
                this.r = 0;
                this.a(this.c, this.c * GameDatabase.gameDatabase[4][intArray[0]][3] / 2, (int)GameDatabase.gameDatabase[4][intArray[0]][4], 4);
                return;
            }
            if (this.r != 0) {
                this.p.a("/data/ui/msgyn.ui");
                this.f = 0;
                return;
            }
            this.q.d(intArray[0], this.c, (byte)0);
            this.q.s(this.c * GameDatabase.gameDatabase[4][intArray[0]][3] / 2);
        } else {
            if (!this.o.k(262144 /* MASK_SOFT_RIGHT */)) return;
            if (this.f == 0) {
                this.o.a((byte)1);
                this.p.a("/data/ui/shopbuy.ui");
                ((MenuWidget)this.p.a.a((int)0)).a.f = this.b = 1;
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
        long l2 = game.GameStateController.getInstance().d + game.GameStateController.getInstance().e - game.GameStateController.getInstance().f;
        WorldManager k2 = this.p.a.a(31).h();
        game.WorldManager.getInstance();
        k2.a = game.WorldManager.a(l2)[1];
        ((MenuWidget)this.p.a.a((int)0)).b.f = this.c;
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
                        if (game.Player.getInstance().k(5)) {
                            this.o.a((byte)11);
                            break;
                        }
                        this.E();
                        this.a("Không đạt được sủng vật sách tranh đạo cụ", "Nhấn nút 5 để tiếp tục");
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
        } else if (this.o.k(262144 /* MASK_SOFT_RIGHT */) && this.f == 0) {
            this.b = BaseScreen.X ? 3 : 2;
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
        ((MenuWidget)this.p.a.a((int)0)).a.a(1);
        this.aZ();
        this.g = true;
    }

    private void aY() {
        ((MenuWidget)this.p.a.a((int)0)).a.e = 0;
        ((MenuWidget)this.p.a.a((int)0)).a.f = 0;
    }

    private void aZ() {
        int n2;
        ((MenuWidget)this.p.a.a((int)0)).a.a = this.q.X[this.b];
        this.w = ((MenuWidget)this.p.a.a((int)0)).a.e;
        this.h = ((MenuWidget)this.p.a.a((int)0)).a.f;
        int n3 = GameDatabase.gameDatabase[0][this.q.W[this.b] + this.h][17];
        if (this.q.a((byte)this.b, this.h + this.q.W[this.b]) > 0) {
            this.p.a.a(21).a(true);
            if (this.p.a.a((int)21).h().m != null) {
                this.p.a.a((int)21).h().m.d();
            } else {
                this.p.a.a((int)21).h().m = new SpriteWidget();
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
                this.p.a.a((int)(n2 + 44)).h().m = new SpriteWidget();
                this.p.a.a((int)(n2 + 44)).h().m.a(102);
                this.p.a.a((int)(n2 + 44)).h().m.a = (byte)2;
                this.p.a.a((int)(n2 + 44)).h().m.a(257, false, (byte)-1);
            }
            if (this.q.a((byte)this.b, n2 + this.w + this.q.W[this.b]) == 2) {
                this.p.a.a((int)(n2 + 44)).h().m.a(101);
            } else {
                this.p.a.a((int)(n2 + 44)).h().m.a(102);
            }
            this.p.a.a((int)(24 + (n2 << 2) + 3)).h().a = BaseScreen.f(GameDatabase.gameDatabase[0][this.q.W[this.b] + n2 + this.w][0]);
        }
        this.p.a.a((int)20).h().a = BaseScreen.f(365 + this.b) + n3 + "/" + this.q.X[this.b];
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
        ((MenuWidget)this.p.a.a((int)0)).b.f = this.b;
        this.c = 0;
        this.r = 0;
        this.ba();
        this.bb();
    }

    private void ba() {
        switch (this.b) {
            case 0: {
                if (game.OverworldScreen.t >= game.OverworldScreen.r.length / 2 - 1) {
                    ((MenuWidget)this.p.a.a((int)0)).a.a = game.OverworldScreen.r.length / 2;
                    ((MenuWidget)this.p.a.a((int)0)).a.f = game.OverworldScreen.r.length / 2 - 1;
                } else {
                    ((MenuWidget)this.p.a.a((int)0)).a.a = game.OverworldScreen.t + 1;
                    ((MenuWidget)this.p.a.a((int)0)).a.f = game.OverworldScreen.t;
                }
                this.p.a.a((int)36).h().a = "";
                this.h = game.OverworldScreen.t;
                this.w = game.OverworldScreen.t - 4;
                if (this.h <= 0) {
                    this.h = 0;
                }
                if (this.w <= 0) {
                    this.w = 0;
                }
                ((MenuWidget)this.p.a.a((int)0)).a.e = this.w;
                this.p.a.a((int)37).h().a = "Đầu mối chính hoàn thành độ: ";
                this.p.a.a((int)38).h().a = game.OverworldScreen.t >= game.OverworldScreen.r.length / 2 ? game.OverworldScreen.r[game.OverworldScreen.r.length - 1] : game.OverworldScreen.r[game.OverworldScreen.r.length / 2 + game.OverworldScreen.t];
                int n2 = game.OverworldScreen.t * 1000 / (game.OverworldScreen.r.length / 2);
                if (!BaseScreen.X) {
                    int n3 = n2 % 10;
                    if (n3 == 0) {
                        n3 = 1;
                    }
                    this.p.a.a((int)38).h().a = n2 / 50 + "." + n3 + "%";
                } else {
                    this.p.a.a((int)38).h().a = n2 / 10 + "." + n2 % 10 + "%";
                }
                if (game.OverworldScreen.t > 4) {
                    ((MenuWidget)this.p.a.a((int)0)).a.a(1);
                } else {
                    ((MenuWidget)this.p.a.a((int)0)).a.a(0);
                }
                this.p.a.a((int)8).h().g = 11290624;
                break;
            }
            case 1: {
                int n4;
                ((MenuWidget)this.p.a.a((int)0)).a.a = game.OverworldScreen.u;
                ((MenuWidget)this.p.a.a((int)0)).a.f = 0;
                ((MenuWidget)this.p.a.a((int)0)).a.e = 0;
                this.p.a.a((int)36).h().a = "";
                this.p.a.a((int)37).h().a = "Chi nhánh hoàn thành độ: ";
                int n5 = 0;
                for (n4 = 0; n4 < game.OverworldScreen.s.length; ++n4) {
                    if (game.OverworldScreen.s[n4][1] != 3) continue;
                    ++n5;
                }
                System.out.println(" Nhiệm vụ phụ " + game.OverworldScreen.q.length);
                n4 = n5 * 1000 / (game.OverworldScreen.q.length / 2);
                this.p.a.a((int)38).h().a = n4 / 10 + "." + n4 % 10 + "%";
                if (game.OverworldScreen.u > 5) {
                    ((MenuWidget)this.p.a.a((int)0)).a.a(1);
                } else {
                    ((MenuWidget)this.p.a.a((int)0)).a.a(0);
                }
                this.p.a.a((int)9).h().g = 11290624;
            }
        }
        this.bb();
    }

    private void bb() {
        this.w = ((MenuWidget)this.p.a.a((int)0)).a.e;
        this.h = ((MenuWidget)this.p.a.a((int)0)).a.f;
        for (int i = 0; i < 5; ++i) {
            if (this.b == 0) {
                if (game.OverworldScreen.t > 0) {
                    if (this.w + i < game.OverworldScreen.t) {
                        this.p.a.a((int)(10 + i * 5 + 2)).h().a = "" + (i + this.w + 1);
                        this.p.a.a((int)(10 + i * 5 + 3)).h().a = game.OverworldScreen.r[this.w + i];
                        this.p.a.a((int)(10 + i * 5 + 4)).h().a = "Hoàn thành";
                        continue;
                    }
                    if (this.w + i == game.OverworldScreen.t && this.w + i <= game.OverworldScreen.r.length / 2 - 1) {
                        this.p.a.a((int)(10 + i * 5 + 2)).h().a = "" + (i + this.w + 1);
                        this.p.a.a((int)(10 + i * 5 + 3)).h().a = game.OverworldScreen.r[this.w + i];
                        this.p.a.a((int)(10 + i * 5 + 4)).h().a = "";
                        continue;
                    }
                    this.p.a.a((int)(10 + i * 5 + 2)).h().a = "";
                    this.p.a.a((int)(10 + i * 5 + 3)).h().a = "";
                    this.p.a.a((int)(10 + i * 5 + 4)).h().a = "";
                    this.p.a.a((int)36).h().a = "";
                    continue;
                }
                this.p.a.a((int)12).h().a = "1";
                this.p.a.a((int)13).h().a = game.OverworldScreen.r[0];
                this.p.a.a((int)14).h().a = "";
                continue;
            }
            if (this.b != 1) continue;
            if (this.w + i < game.OverworldScreen.u) {
                this.p.a.a((int)(10 + i * 5 + 2)).h().a = "" + (i + this.w + 1);
                this.p.a.a((int)(10 + i * 5 + 3)).h().a = game.OverworldScreen.q[game.OverworldScreen.s[this.w + i][0]];
                if (game.OverworldScreen.s[this.w + i][1] == 3) {
                    this.p.a.a((int)(10 + i * 5 + 4)).h().a = "Hoàn thành";
                    continue;
                }
                this.p.a.a((int)(10 + i * 5 + 4)).h().a = "";
                continue;
            }
            this.p.a.a((int)(10 + i * 5 + 2)).h().a = "";
            this.p.a.a((int)(10 + i * 5 + 3)).h().a = "";
            this.p.a.a((int)(10 + i * 5 + 4)).h().a = "";
        }
        switch (this.b) {
            case 0: {
                this.p.a.a((int)36).h().a = game.OverworldScreen.r[game.OverworldScreen.r.length / 2 + this.h];
                break;
            }
            case 1: {
                if (game.OverworldScreen.u <= 0) break;
                this.p.a.a((int)36).h().a = game.OverworldScreen.q[game.OverworldScreen.q.length / 2 + game.OverworldScreen.s[this.h][0]];
            }
        }
        if (((MenuWidget)this.p.a.a((int)0)).a.a > 0) {
            this.p.a.a(40).b(104 + (this.h << 6) / ((MenuWidget)this.p.a.a((int)0)).a.a, this.p.a.a());
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
            this.b = BaseScreen.X ? 4 : 3;
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
        for (int i = 0; i < 8; ++i) {
            if (this.q.B[i][0] == 0) continue;
            this.p.a.a((int)(i + 25)).h().m.a(i + 46);
        }
        this.bc();
    }

    private void bc() {
        this.p.a.a((int)13).h().a = BaseScreen.f(GameDatabase.gameDatabase[2][this.b][0]);
        this.p.a.a((int)14).h().a = BaseScreen.f(GameDatabase.gameDatabase[2][this.b][2 + this.q.c((byte)this.b, (byte)1)]);
        if (this.q.c((byte)this.b, (byte)0) == 0) {
            this.p.a.a((int)16).h().a = "Chưa đạt";
            return;
        }
        this.p.a.a((int)16).h().a = "Đã đạt được";
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
            this.p.a.a((int)6).h().m = new SpriteWidget();
            this.p.a.a((int)6).h().m.a = (byte)2;
            this.p.a.a((int)6).h().m.a(-1);
            this.p.a.a((int)6).h().m.a(257, false, (byte)-1);
            this.p.a.a((int)6).h().m.a(n2 + 46);
        }
        this.p.a.a((int)7).h().a = BaseScreen.f(n2 + 187) + ":" + BaseScreen.f(n2 + 195);
        this.p.a.a((int)8).h().a = BaseScreen.f(377);
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
        Pet[] bArray = this.q.z;
        ScriptEngine h2 = this;
        h2.p.a("/data/ui/petstate.ui", 257, h2);
        h2.f(n3);
        h2.f = 0;
        if (h2.o instanceof WorldManager) {
            for (int i = 0; i < 6; ++i) {
                if (bArray[i] != null) {
                    h2.p.a.a((int)(16 + i * 6)).h().a = "#P" + bArray[i].L();
                    h2.p.a.a((int)(17 + i * 6)).h().a = "#P" + bArray[i].O();
                    continue;
                }
                h2.p.a.a((int)(16 + i * 6)).h().a = "#P0";
                h2.p.a.a((int)(17 + i * 6)).h().a = "#P0";
            }
            if (h2.o.Q == 16) {
                h2.p.a.a((int)64).h().a = "Gởi lại";
            }
            h2.p.a.a(75).a(false);
            h2.p.a.a(76).a(false);
        } else if (h2.o instanceof BattleScreen) {
            for (int i3 = 0; i3 < 6; ++i3) {
                if (i3 < ((BattleScreen)h2.o).f.length && bArray[((BattleScreen)h2.o).f[i3]] != null) {
                    h2.p.a.a((int)(16 + i3 * 6)).h().a = "#P" + bArray[((BattleScreen)h2.o).f[i3]].L();
                    h2.p.a.a((int)(17 + i3 * 6)).h().a = "#P" + bArray[((BattleScreen)h2.o).f[i3]].O();
                    continue;
                }
                h2.p.a.a((int)(16 + i3 * 6)).h().a = "#P0";
                h2.p.a.a((int)(17 + i3 * 6)).h().a = "#P0";
            }
            h2.p.a.a(63).a(false);
            h2.p.a.a(64).a(false);
            if (h2.o.Q == 4) {
                h2.p.a.a((int)75).h().a = "Sử dụng";
            } else if (h2.o.P == 5) {
                h2.p.a.a((int)75).h().a = "Xuất chiến";
            }
        }
        ((MenuWidget)h2.p.a.a((int)0)).a.a = h2.q.A;
        ((MenuWidget)h2.p.a.a((int)0)).a.d = h2.q.A;
        ((MenuWidget)h2.p.a.a((int)0)).a.f = n3;
        h2.g = true;
    }

    private void a(Pet[] bArray, int n2) {
        if (bArray[n2] != null) {
            if (this.p.a.a((int)48).h().m != null) {
                this.p.a.a((int)48).h().m.d();
            } else {
                this.p.a.a((int)48).h().m = new SpriteWidget();
                this.p.a.a((int)48).h().m.a(0);
                this.p.a.a((int)48).h().m.a = (byte)3;
            }
            this.p.a.a((int)48).h().m.a(bArray[n2].C, false, (byte)-1);
            this.p.a.a((int)51).h().a = BaseScreen.f(bArray[n2].j((byte)0));
            this.p.a.a((int)52).h().a = BaseScreen.f(365 + bArray[n2].j((byte)1));
            if (bArray[n2].j((byte)19) == -1) {
                this.p.a.a((int)62).h().a = "";
            } else if (GameDatabase.gameDatabase[0][bArray[n2].j((byte)19)][2] == 1 || GameDatabase.gameDatabase[0][bArray[n2].j((byte)19)][2] == 2) {
                this.p.a.a((int)62).h().a = "Có thể tiến hóa";
            } else if (GameDatabase.gameDatabase[0][bArray[n2].j((byte)19)][2] == 3) {
                this.p.a.a((int)62).h().a = "Có thể dị hoá";
            }
            this.p.a.a((int)61).h().a = bArray[n2].T();
            if (this.o instanceof BattleScreen) {
                this.p.a.a((int)64).h().a = "Xuất chiến";
            } else if (this.o instanceof WorldManager) {
                this.p.a.a((int)64).h().a = "Xác nhận";
            }
            if (this.p.a.a((int)59).h().m == null) {
                this.p.a.a((int)59).h().m = new SpriteWidget();
                this.p.a.a((int)59).h().m.a(0);
                this.p.a.a((int)59).h().m.a = (byte)2;
                this.p.a.a((int)59).h().m.a(258, false, (byte)-1);
            }
            if (bArray[n2].c[5] != -1) {
                this.p.a.a((int)59).h().m.a(GameDatabase.gameDatabase[3][bArray[n2].c[5]][1]);
                this.p.a.a((int)60).h().a = BaseScreen.f(GameDatabase.gameDatabase[3][bArray[n2].c[5]][0]);
            } else {
                this.p.a.a((int)59).h().m.a(0);
                this.p.a.a((int)60).h().a = "";
            }
            this.p.a.a((int)65).h().a = "" + bArray[n2].s();
            this.p.a.a((int)66).h().a = "" + bArray[n2].e((byte)2);
            this.p.a.a((int)67).h().a = "" + bArray[n2].e((byte)3);
            this.p.a.a((int)68).h().a = "" + bArray[n2].e((byte)4);
            int n3 = bArray[n2].d[0];
            int n4 = GameDatabase.spriteTable((byte)0, (short)bArray[n2].q(), (byte)4) - 1;
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
        if (this.o instanceof WorldManager) {
            this.a(this.q.z, n2);
            return;
        }
        if (this.o instanceof BattleScreen) {
            this.a(this.q.z, (int)((BattleScreen)this.o).f[n2]);
        }
    }

    public final void X() {
        block132: {
            block135: {
                block134: {
                    block133: {
                        block131: {
                            if (this.f != 0) break block131;
                            if (!BaseScreen.b(this.b, 0) && !this.j() && this.o.k(4100)) {
                                this.p.a.b(0);
                            } else if (!BaseScreen.b(this.b, 0) && !this.j() && this.o.k(8448)) {
                                this.p.a.b(1);
                            } else if (BaseScreen.I() && !this.j() && this.o.k(196640)) {
                                if (BaseScreen.H() && !BaseScreen.b(this.b, 0)) {
                                    return;
                                }
                                if (this.o instanceof BattleScreen) {
                                    int n2 = ((BattleScreen)this.o).a(this.b);
                                    if (n2 == 0) {
                                        this.f = 2;
                                        this.p.a("/data/ui/msgwarm.ui", 257, this);
                                        this.a("Sủng vật này không thể tham chiến", "Nhấn nút 5 để tiếp tục");
                                        this.p.a("/data/ui/petsetting.ui");
                                    } else if (n2 == 1) {
                                        this.f = 2;
                                        this.p.a("/data/ui/msgwarm.ui", 257, this);
                                        this.a("Sủng vật này đã đặt ở vị trí chiến đấu", "Nhấn nút 5 để tiếp tục");
                                        this.p.a("/data/ui/petsetting.ui");
                                    } else if (n2 == -1) {
                                        ((BattleScreen)this.o).a((int)((BattleScreen)this.o).g, 0);
                                        this.a = 0;
                                        this.o.a((byte)15);
                                        this.p.a("/data/ui/petsetting.ui");
                                        this.p.a("/data/ui/petstate.ui");
                                    }
                                } else if (this.o instanceof WorldManager) {
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
                                                this.a("Ba lô phải lưu ít nhất 1 sủng vật", "Nhấn nút 5 để tiếp tục");
                                            }
                                        } else {
                                            this.f = 1;
                                            this.p.a("/data/ui/msgwarm.ui", 257, this);
                                            this.a("Ngân hàng đã đầy, không thể gởi lại", "Nhấn nút 5 để tiếp tục");
                                        }
                                    } else if (this.o.Q == 6 || this.o.Q == 0) {
                                        this.c = 0;
                                        this.o.m();
                                        this.f = 1;
                                        this.p.a("/data/ui/petsetting.ui", 257, this);
                                        ((MenuWidget)this.p.a.a((int)0)).a.f = this.c;
                                        if (this.q.z[this.b].R() == 2) {
                                            this.p.a.a((int)9).h().a = "Dị hoá";
                                            ((MenuWidget)this.p.a.a((int)0)).a.a = 6;
                                            ((MenuWidget)this.p.a.a((int)0)).a.d = 6;
                                        } else if (this.q.z[this.b].R() == 1) {
                                            this.p.a.a((int)9).h().a = "Tiến hóa";
                                            ((MenuWidget)this.p.a.a((int)0)).a.a = 6;
                                            ((MenuWidget)this.p.a.a((int)0)).a.d = 6;
                                        } else {
                                            this.p.a.a((int)9).h().a = "";
                                            ((MenuWidget)this.p.a.a((int)0)).a.a = 5;
                                            ((MenuWidget)this.p.a.a((int)0)).a.d = 5;
                                        }
                                    } else if (this.o.Q == 27) {
                                        if (this.d == 1 && this.q.z[this.b].R() == 1 || this.d == 2 && this.q.z[this.b].R() == 2) {
                                            this.bg();
                                        } else {
                                            this.f = 4;
                                            this.E();
                                            if (this.d == 1) {
                                                this.a("Sủng vật này không thể tiến hóa", "Nhấn nút 5 để tiếp tục");
                                            } else if (this.d == 2) {
                                                this.a("Sủng vật này không thể dị hoá", "Nhấn nút 5 để tiếp tục");
                                            } else {
                                                this.a("Không thể vào hóa cùng dị hoá", "Nhấn nút 5 để tiếp tục");
                                            }
                                        }
                                    }
                                }
                            } else if (game.OverworldScreen.J() && !this.j() && this.o.k(262144 /* MASK_SOFT_RIGHT */)) {
                                if (this.o instanceof WorldManager) {
                                    if (this.o.Q == 16) {
                                        this.o.a((byte)16);
                                    } else if (this.o.Q == 6) {
                                        this.b = BaseScreen.X ? 1 : 0;
                                        this.o.a((byte)6);
                                    } else if (this.o.Q == 27) {
                                        this.o.a((byte)27);
                                    } else if (this.o.Q == 0) {
                                        this.o.a((byte)23);
                                    }
                                    this.p.a("/data/ui/petstate.ui");
                                } else if (this.o instanceof BattleScreen) {
                                    if (((BattleScreen)this.o).Q == 7 || ((BattleScreen)this.o).Q == 13) {
                                        return;
                                    }
                                    this.p.a("/data/ui/petstate.ui");
                                    game.BattleScreen.getInstance().k = false;
                                    this.a = 0;
                                    this.o.a((byte)20);
                                }
                            }
                            break block132;
                        }
                        if (this.f != 1) break block133;
                        if (!BaseScreen.b(this.c, 0) && !this.j() && this.o.k(4100)) {
                            this.p.a.b(0);
                        } else if (!BaseScreen.b(this.c, 0) && !this.j() && this.o.k(8448)) {
                            this.p.a.b(1);
                        } else if (BaseScreen.I() && !this.j() && this.o.k(196640)) {
                            if (BaseScreen.H() && !BaseScreen.b(this.c, 0)) {
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
                                        ScriptEngine h2 = this;
                                        this.f = 2;
                                        h2.r = 0;
                                        h2.p.a("/data/ui/choice.ui", 257, h2);
                                        h2.p.a("/data/ui/petsetting.ui");
                                        h2.p.a("/data/ui/petstate.ui");
                                        h2.p.a.a((int)8).h().a = "Đạo cụ";
                                        h2.p.a.a((int)9).h().a = "Số lượng";
                                        if (h2.o instanceof WorldManager) {
                                            h2.p.a.a(5).a(false);
                                            h2.p.a.a(6).a(false);
                                            h2.p.a.a(59).a(true);
                                            h2.p.a.a(60).a(true);
                                            h2.p.a.a((int)59).h().a = "Sử dụng";
                                        } else {
                                            h2.p.a.a(5).a(true);
                                            h2.p.a.a(6).a(true);
                                            h2.p.a.a(59).a(false);
                                            h2.p.a.a(60).a(false);
                                            h2.p.a.a((int)5).h().a = "Sử dụng";
                                        }
                                        h2.be();
                                        h2.g = true;
                                        break;
                                    }
                                    case 1: {
                                        if (!this.q.z[this.b].S()) {
                                            this.f = 2;
                                            this.p.a("/data/ui/msgwarm.ui", 257, this);
                                            this.a("Sủng vật này không thể tham chiến", "Nhấn nút 5 để tiếp tục");
                                            this.p.a("/data/ui/petsetting.ui");
                                            this.b = 0;
                                            break;
                                        }
                                        if (this.b == 0) {
                                            this.f = 2;
                                            this.b = 0;
                                            this.p.a("/data/ui/msgwarm.ui", 257, this);
                                            this.a("Sủng vật này đã xuất chiến", "Nhấn nút 5 để tiếp tục");
                                            this.p.a("/data/ui/petsetting.ui");
                                            break;
                                        }
                                        this.q.p(this.b);
                                        this.f = 0;
                                        this.b = 0;
                                        this.e(this.b);
                                        this.p.a("/data/ui/petsetting.ui");
                                        ((MenuWidget)this.p.a.a((int)0)).a.f = 0;
                                        ((MenuWidget)this.p.a.a((int)0)).a.e = 0;
                                        break;
                                    }
                                    case 2: {
                                        this.o.m();
                                        ScriptEngine h3 = this;
                                        this.f = 2;
                                        h3.r = 0;
                                        h3.p.a("/data/ui/choice.ui", 257, h3);
                                        h3.p.a("/data/ui/petsetting.ui");
                                        h3.p.a("/data/ui/petstate.ui");
                                        h3.p.a.a((int)8).h().a = "Vật phẩm trang sức";
                                        h3.p.a.a((int)9).h().a = "Trạng thái";
                                        if (h3.o instanceof WorldManager) {
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
                                        if (GameDatabase.spriteTable((byte)0, (short)this.q.z[this.b].q(), (byte)22) == 2) {
                                            this.f = 3;
                                            this.E();
                                            this.p.a("/data/ui/petsetting.ui");
                                            this.a("Thần thú không thể phóng sinh", "Nhấn nút 5 để tiếp tục");
                                            break;
                                        }
                                        this.f = 2;
                                        this.p.a("/data/ui/msgconfirm.ui", 257, this);
                                        this.p.a("/data/ui/petsetting.ui");
                                        this.b("Bạn muốn phóng sinh sủng vật này?", "Xác nhận");
                                        break;
                                    }
                                    case 4: {
                                        ScriptEngine h4 = this;
                                        this.f = 2;
                                        h4.r = 0;
                                        h4.p.a("/data/ui/skill.ui", 257, h4);
                                        h4.p.a("/data/ui/petsetting.ui");
                                        h4.p.a("/data/ui/petstate.ui");
                                        h4.p.a.a((int)12).h().a = BaseScreen.f(h4.q.z[h4.b].j((byte)0));
                                        h4.p.a.a((int)14).h().a = "" + h4.q.z[h4.b].s();
                                        if (h4.p.a.a((int)16).h().m != null) {
                                            h4.p.a.a((int)16).h().m.d();
                                        } else {
                                            h4.p.a.a((int)16).h().m = new SpriteWidget();
                                            h4.p.a.a((int)16).h().m.a(0);
                                            h4.p.a.a((int)16).h().m.a = (byte)3;
                                        }
                                        h4.p.a.a((int)16).h().m.a(h4.q.z[h4.b].C, false, (byte)-1);
                                        int n3 = h4.q.z[h4.b].E();
                                        for (int i = 0; i < n3; ++i) {
                                            h4.p.a.a((int)(i + 18)).h().a = BaseScreen.f(GameDatabase.gameDatabase[1][h4.q.z[h4.b].t(i)][1]);
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
                        } else if (game.OverworldScreen.J() && !this.j() && this.o.k(262144 /* MASK_SOFT_RIGHT */)) {
                            if (this.o.Q == 16) {
                                return;
                            }
                            this.f = 0;
                            this.p.a("/data/ui/petsetting.ui");
                        }
                        break block132;
                    }
                    if (this.f < 2) break block132;
                    if (!(this.o instanceof BattleScreen)) break block134;
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
                        ScriptEngine h5 = this;
                        if (!BaseScreen.b(h5.b, 0) && !h5.j() && h5.f == 2 && h5.o.k(4100)) {
                            h5.p.a.b(0);
                            h5.bd();
                            break;
                        }
                        if (!BaseScreen.b(h5.b, 0) && !h5.j() && h5.f == 2 && h5.o.k(8448)) {
                            h5.p.a.b(1);
                            h5.bd();
                            break;
                        }
                        if (BaseScreen.I() && !h5.j() && h5.o.k(196640) && h5.q.L.size() > 0) {
                            if (!BaseScreen.H() || BaseScreen.b(h5.b, 0)) {
                                if (h5.f == 2) {
                                    int[] intArray = (int[])h5.q.L.elementAt(h5.h);
                                    if (h5.q.z[h5.b].c[5] == intArray[0]) {
                                        h5.q.l(h5.q.z[h5.b].c[5]);
                                        h5.q.z[h5.b].c[5] = -1;
                                        h5.bd();
                                        h5.E();
                                        h5.a("Thành công dỡ xuống", "Nhấn nút 5 để tiếp tục");
                                    } else {
                                        h5.q.f(intArray[0], h5.b);
                                        h5.bd();
                                        h5.E();
                                        h5.a("Thành công mang theo", "Nhấn nút 5 để tiếp tục");
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
                        } else if (game.OverworldScreen.J() && !h5.j() && h5.f == 2 && h5.o.k(262144 /* MASK_SOFT_RIGHT */)) {
                            h5.e(h5.b);
                            h5.p.a("/data/ui/choice.ui");
                            break;
                        }
                        break block132;
                    }
                    case 0: {
                        ScriptEngine h6 = this;
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
                                    int[] intArray = (int[])h6.q.J.elementAt(h6.r);
                                    switch (intArray[0]) {
                                        case 13: 
                                        case 14: {
                                            h6.p.a("/data/ui/msgwarm.ui", 257, h6);
                                            h6.a("Đạo cụ này không thể sử dụng", "Nhấn nút 5 để tiếp tục");
                                            break block8;
                                        }
                                    }
                                    switch (h6.q.z[h6.b].x(intArray[0])) {
                                        case 0: {
                                            h6.p.a("/data/ui/msgwarm.ui", 257, h6);
                                            h6.a("Sủng vật này đã tử vong, không thể sử dụng", "Nhấn nút 5 để tiếp tục");
                                            break block8;
                                        }
                                        case 1: {
                                            h6.p.a("/data/ui/msgwarm.ui", 257, h6);
                                            h6.a("Sủng vật này không có, không thể sử dụng", "Nhấn nút 5 để tiếp tục");
                                            break block8;
                                        }
                                        case 2: {
                                            h6.p.a("/data/ui/msgwarm.ui", 257, h6);
                                            h6.a("Máu đầy, không cần sử dụng", "Nhấn nút 5 để tiếp tục");
                                            break block8;
                                        }
                                        case 3: {
                                            h6.p.a("/data/ui/msgwarm.ui", 257, h6);
                                            h6.a("Kỹ năng giá trị đã đầy, không cần sử dụng", "Nhấn nút 5 để tiếp tục");
                                            break block8;
                                        }
                                        case 4: {
                                            h6.p.a("/data/ui/msgwarm.ui", 257, h6);
                                            h6.a("Trên người đều bị lợi hiệu quả", "Nhấn nút 5 để tiếp tục");
                                            break block8;
                                        }
                                        case 5: {
                                            h6.p.a("/data/ui/msgwarm.ui", 257, h6);
                                            h6.a("Trong hưng phấn, không thể dùng", "Nhấn nút 5 để tiếp tục");
                                            break block8;
                                        }
                                        case 7: {
                                            h6.p.a("/data/ui/msgwarm.ui", 257, h6);
                                            h6.a("Máu và kỹ năng đều đã đầy, không cần sử dụng", "Nhấn nút 5 để tiếp tục");
                                            break block8;
                                        }
                                        case 8: {
                                            h6.p.a("/data/ui/msgwarm.ui", 257, h6);
                                            h6.a("Sủng vật đã chết, không thể sử dụng", "Nhấn nút 5 để tiếp tục");
                                            break block8;
                                        }
                                    }
                                    h6.q.z[h6.b].w(intArray[0]);
                                    h6.e(h6.b);
                                    h6.f = 4;
                                    h6.p.a("/data/ui/msgwarm.ui", 257, h6);
                                    h6.a("Thành công sử dụng đạo cụ", "Nhấn nút 5 để tiếp tục");
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
                        } else if (h6.f == 2 && h6.o.k(262144 /* MASK_SOFT_RIGHT */)) {
                            h6.e(h6.b);
                            h6.p.a("/data/ui/choice.ui");
                            break;
                        }
                        break block132;
                    }
                    case 4: {
                        ScriptEngine h7 = this;
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
                        if (h7.o.k(262144 /* MASK_SOFT_RIGHT */)) {
                            h7.e(h7.b);
                            h7.p.a("/data/ui/skill.ui");
                            break;
                        }
                        break block132;
                    }
                    case 3: {
                        if (this.o.k(131072 /* MASK_SOFT_LEFT */) && this.f == 2 || this.o.k(131104) && this.f == 3) {
                            if (this.f == 2) {
                                if (this.q.o(this.b)) {
                                    this.q.l(this.q.z[this.b].c[5]);
                                    this.q.z[this.b].c[5] = -1;
                                    this.q.m(this.b);
                                    if (this.b >= this.q.A) {
                                        --this.b;
                                    }
                                    ((WorldManager)this.o).M.i();
                                    this.e(this.b);
                                    this.p.a("/data/ui/msgconfirm.ui");
                                    this.f = 0;
                                    break;
                                }
                                this.f = 3;
                                this.p.a("/data/ui/msgwarm.ui", 257, this);
                                this.a("Ba lô phải lưu ít nhất 1 sủng vật", "Nhấn nút 5 để tiếp tục");
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
            ((MenuWidget)this.p.a.a((int)0)).a.a(1);
        } else {
            ((MenuWidget)this.p.a.a((int)0)).a.a(0);
        }
        ((MenuWidget)this.p.a.a((int)0)).a.a = this.q.L.size();
        this.w = ((MenuWidget)this.p.a.a((int)0)).a.e;
        this.h = ((MenuWidget)this.p.a.a((int)0)).a.f;
        if (this.h >= this.q.L.size()) {
            ((MenuWidget)this.p.a.a((int)0)).a.f = this.h = this.q.L.size() - 1;
        }
        if (this.w > 0 && this.h - this.w < 4) {
            --this.w;
            ((MenuWidget)this.p.a.a((int)0)).a.e = this.w;
        }
        if (this.q.L.size() <= 0) {
            return;
        }
        if (this.q.z[this.b].c[5] == ((int[])this.q.L.elementAt(this.h))[0]) {
            if (this.o instanceof WorldManager) {
                this.p.a.a((int)59).h().a = "Dỡ xuống";
            } else {
                this.p.a.a((int)5).h().a = "Dỡ xuống";
            }
        } else if (this.o instanceof WorldManager) {
            this.p.a.a((int)59).h().a = "Mang theo";
        } else {
            this.p.a.a((int)5).h().a = "Mang theo";
        }
        for (int i = 0; i < 5; ++i) {
            if (this.w + i < this.q.L.size()) {
                int[] intArray = (int[])this.q.L.elementAt(this.w + i);
                if (this.p.a.a((int)(i + 54)).h().m == null) {
                    this.p.a.a((int)(i + 54)).h().m = new SpriteWidget();
                    this.p.a.a((int)(i + 54)).h().m.a(0);
                    this.p.a.a((int)(i + 54)).h().m.a = (byte)2;
                    this.p.a.a((int)(i + 54)).h().m.a(258, false, (byte)-1);
                }
                this.p.a.a((int)(i + 54)).h().m.a(GameDatabase.gameDatabase[3][intArray[0]][1]);
                this.p.a.a((int)(13 + i * 5)).h().a = BaseScreen.f(GameDatabase.gameDatabase[3][intArray[0]][0]);
                if (game.Player.getInstance().z[this.b].c[5] == intArray[0]) {
                    this.p.a.a((int)(14 + i * 5)).h().a = "Đã mang theo";
                    continue;
                }
                if (intArray[1] == 1) {
                    this.p.a.a((int)(14 + i * 5)).h().a = "Bị mang theo";
                    continue;
                }
                this.p.a.a((int)(14 + i * 5)).h().a = "";
                continue;
            }
            if (this.p.a.a((int)(i + 54)).h().m != null) {
                this.p.a.a((int)(i + 54)).h().m.d();
            }
            this.p.a.a((int)(13 + i * 5)).h().a = "";
            this.p.a.a((int)(14 + i * 5)).h().a = "";
        }
        this.p.a.a((int)53).h().a = this.q.L.size() > 0 ? BaseScreen.f(GameDatabase.gameDatabase[3][((int[])this.q.L.elementAt(this.h))[0]][2]) : "";
        if (this.q.L.size() > 0) {
            this.p.a.a(51).b(98 + this.h * 62 / this.q.L.size(), this.p.a.a());
            return;
        }
        this.p.a.a(51).b(98, this.p.a.a());
    }

    private void be() {
        if (this.q.J.size() > 5) {
            ((MenuWidget)this.p.a.a((int)0)).a.a(1);
        } else {
            ((MenuWidget)this.p.a.a((int)0)).a.a(0);
        }
        ((MenuWidget)this.p.a.a((int)0)).a.a = this.q.J.size();
        this.w = ((MenuWidget)this.p.a.a((int)0)).a.e;
        this.h = ((MenuWidget)this.p.a.a((int)0)).a.f;
        if (this.h >= this.q.J.size()) {
            ((MenuWidget)this.p.a.a((int)0)).a.f = this.h = this.q.J.size() - 1;
        }
        if (this.w > 0 && this.h - this.w < 4) {
            --this.w;
            ((MenuWidget)this.p.a.a((int)0)).a.e = this.w;
        }
        for (int i = 0; i < 5; ++i) {
            if (this.w + i < this.q.J.size()) {
                int[] intArray = (int[])this.q.J.elementAt(this.w + i);
                if (this.p.a.a((int)(i + 54)).h().m == null) {
                    this.p.a.a((int)(i + 54)).h().m = new SpriteWidget();
                    this.p.a.a((int)(i + 54)).h().m.a(0);
                    this.p.a.a((int)(i + 54)).h().m.a = (byte)2;
                    this.p.a.a((int)(i + 54)).h().m.a(258, false, (byte)-1);
                }
                this.p.a.a((int)(i + 54)).h().m.a(GameDatabase.gameDatabase[4][intArray[0]][1]);
                this.p.a.a((int)(13 + i * 5)).h().a = BaseScreen.f(GameDatabase.gameDatabase[4][intArray[0]][0]);
                this.p.a.a((int)(14 + i * 5)).h().a = "" + intArray[1];
                continue;
            }
            if (this.p.a.a((int)(i + 54)).h().m != null) {
                this.p.a.a((int)(i + 54)).h().m.d();
            }
            this.p.a.a((int)(13 + i * 5)).h().a = "";
            this.p.a.a((int)(14 + i * 5)).h().a = "";
        }
        this.p.a.a((int)53).h().a = this.q.J.size() > 0 ? BaseScreen.f(GameDatabase.gameDatabase[4][((int[])this.q.J.elementAt(this.h))[0]][2]) : "";
        if (this.q.J.size() > 0) {
            this.p.a.a(51).b(98 + this.h * 72 / this.q.J.size(), this.p.a.a());
            return;
        }
        this.p.a.a(51).b(98, this.p.a.a());
    }

    private void bf() {
        if (this.q.z[this.b].t(this.r) != -1) {
            String[] strArray = new String[]{"Nhất định", "Nhất định"};
            this.p.a.a((int)9).h().a = BaseScreen.a((int)GameDatabase.gameDatabase[1][this.q.z[this.b].t(this.r)][2], strArray);
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
            this.p.a.a((int)10).h().m = new SpriteWidget();
            this.p.a.a((int)10).h().m.a(0);
            this.p.a.a((int)10).h().m.a = (byte)3;
        }
        this.p.a.a((int)10).h().m.a(this.q.z[this.b].C, false, (byte)-1);
        int n2 = GameDatabase.spriteTable((byte)0, (byte)this.q.z[this.b].q(), (byte)20) + 12;
        short s2 = GameDatabase.spriteTable((byte)0, (byte)this.q.z[this.b].q(), (byte)21);
        this.p.a.a((int)38).h().a = BaseScreen.f(GameDatabase.spriteTable((byte)0, (byte)this.q.z[this.b].q(), (byte)0));
        this.p.a.a((int)40).h().a = "" + this.q.z[this.b].s();
        this.p.a.a((int)45).h().a = BaseScreen.f(GameDatabase.spriteTable((byte)3, (short)n2, (byte)0));
        this.p.a.a((int)46).h().a = this.q.a(n2, (byte)2) + "/" + s2;
        n2 = GameDatabase.spriteTable((byte)0, (byte)this.q.z[this.b].q(), (byte)19);
        Pet b2 = new Pet();
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
        if (game.WorldManager.n != null) {
            if (!game.WorldManager.n.i()) {
                short s2 = GameDatabase.spriteTable((byte)0, (byte)this.q.z[this.b].q(), (byte)19);
                String string = BaseScreen.f(GameDatabase.spriteTable((byte)0, s2, (byte)0));
                ScriptEngine h2 = this;
                short s3 = GameDatabase.spriteTable((byte)0, (byte)h2.q.z[h2.b].q(), (byte)19);
                short s4 = GameDatabase.spriteTable((byte)0, s3, (byte)17);
                h2.p.a.a(10).a(true);
                h2.p.a.a((int)10).h().m.a(s4, false, (byte)-1);
                h2.p.a.a((int)38).h().a = BaseScreen.f(GameDatabase.spriteTable((byte)0, s3, (byte)0));
                Pet b2 = new Pet();
                short s5 = GameDatabase.spriteTable((byte)0, s3, (byte)3);
                int n2 = -1;
                if (h2.q.z[h2.b].c[0] >= s5) {
                    n2 = (byte)h2.q.z[h2.b].c[0];
                }
                b2.a(s3, h2.q.z[h2.b].s(), h2.q.z[h2.b].c[5], (byte)h2.q.z[h2.b].d[6], (short)n2, (byte)-1);
                b2.a(b2.c[1], h2.q.z[h2.b].z(), (int)h2.q.z[h2.b].E);
                b2.b(h2.q.z[h2.b].Q());
                h2.q.a((byte)h2.q.z[h2.b].j((byte)1), (int)s3, (byte)2);
                h2.q.z[h2.b].a(b2.P());
                s5 = (short)(GameDatabase.spriteTable((byte)0, (byte)h2.q.z[h2.b].q(), (byte)20) + 12);
                short s6 = GameDatabase.spriteTable((byte)0, (byte)h2.q.z[h2.b].q(), (byte)21);
                s3 = GameDatabase.spriteTable((byte)0, (byte)h2.q.z[h2.b].q(), (byte)19);
                n2 = h2.q.a((int)s5, (byte)2);
                if (s3 == -1) {
                    h2.p.a.a((int)42).h().a = "";
                    h2.p.a.a((int)45).h().a = "";
                    h2.p.a.a((int)46).h().a = "";
                } else {
                    h2.p.a.a((int)45).h().a = BaseScreen.f(GameDatabase.spriteTable((byte)3, s5, (byte)0));
                    h2.p.a.a((int)46).h().a = n2 + "/" + s6;
                }
                if (this.q.z[this.b].R() == 2) {
                    this.f = 3;
                    this.p.a("/data/ui/msgwarm.ui", 257, this);
                    this.a("Dị hoá thành #2" + string, "Nhấn nút 5 để tiếp tục");
                } else {
                    this.f = 3;
                    this.p.a("/data/ui/msgwarm.ui", 257, this);
                    this.a("Tiến hóa thành #2" + string, "Nhấn nút 5 để tiếp tục");
                }
                game.WorldManager.n = null;
            }
            return;
        }
        if (BaseScreen.I() && !this.j() && this.o.k(196640)) {
            if (this.f == 2) {
                short s7 = (short)(GameDatabase.spriteTable((byte)0, (byte)this.q.z[this.b].q(), (byte)20) + 12);
                short s8 = GameDatabase.spriteTable((byte)0, (byte)this.q.z[this.b].q(), (byte)21);
                short s9 = GameDatabase.spriteTable((byte)0, (byte)this.q.z[this.b].q(), (byte)19);
                if (s9 == -1) {
                    this.f = 3;
                    this.E();
                    this.a("Không thể lại tiến hóa hoặc dị hoá", "Nhấn nút 5 để tiếp tục");
                    return;
                }
                short s10 = GameDatabase.spriteTable((byte)0, s9, (byte)17);
                if (this.q.z[this.b].s() >= game.Pet.t[GameDatabase.spriteTable((byte)0, s9, (byte)2) - 1]) {
                    if (this.q.a((int)s7, (byte)2) >= s8) {
                        this.p.a.a(10).a(false);
                        game.WorldManager.n = new SkillEffect();
                        short[] shortArray = new short[]{0, 0, 10, 0, 0, this.q.z[this.b].C, 0, 0, s10, 0, 0};
                        game.WorldManager.n.a(shortArray);
                        game.WorldManager.n.c(true);
                        game.WorldManager.n.a();
                        this.q.d(s7, s8, (byte)2);
                        return;
                    }
                    this.f = 3;
                    this.p.a("/data/ui/msgwarm.ui", 257, this);
                    if (this.q.z[this.b].R() == 2) {
                        this.a("Tài liệu chưa đủ, không thể dị hoá", "Nhấn nút 5 để tiếp tục");
                        return;
                    }
                    this.a("Tài liệu chưa đủ, không thể tiến hóa", "Nhấn nút 5 để tiếp tục");
                    return;
                }
                this.f = 3;
                this.p.a("/data/ui/msgwarm.ui", 257, this);
                this.a("Còn chưa tới" + game.Pet.t[GameDatabase.spriteTable((byte)0, s9, (byte)2) - 1] + " cấp, không thể vào hóa", "Nhấn nút 5 để tiếp tục");
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
        } else if (this.f < 3 && this.o.k(262144 /* MASK_SOFT_RIGHT */) && !this.j() && BaseScreen.J()) {
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
        this.p.a.a((int)14).h().a = "Vật phẩm";
        this.b = 0;
    }

    private void bi() {
        ((MenuWidget)this.p.a.a((int)(8 + this.b * 39))).a.e = 0;
        ((MenuWidget)this.p.a.a((int)(8 + this.b * 39))).a.f = 0;
        this.bj();
    }

    private void bj() {
        block0 : switch (this.b) {
            case 0: {
                this.bk();
                break;
            }
            case 1: {
                ScriptEngine h2 = this;
                if (h2.q.L.size() > 5) {
                    ((MenuWidget)h2.p.a.a((int)47)).a.a(1);
                } else {
                    ((MenuWidget)h2.p.a.a((int)47)).a.a(0);
                }
                ((MenuWidget)h2.p.a.a((int)47)).a.a = h2.q.L.size();
                h2.w = ((MenuWidget)h2.p.a.a((int)47)).a.e;
                h2.h = ((MenuWidget)h2.p.a.a((int)47)).a.f;
                h2.p.a.a(7).a(false);
                for (int i = 0; i < 5; ++i) {
                    if (h2.w + i < h2.q.L.size()) {
                        int[] intArray = (int[])h2.q.L.elementAt(h2.w + i);
                        if (h2.p.a.a((int)(59 + i * 5)).h().m == null) {
                            h2.p.a.a((int)(59 + i * 5)).h().m = new SpriteWidget();
                            h2.p.a.a((int)(59 + i * 5)).h().m.a(0);
                            h2.p.a.a((int)(59 + i * 5)).h().m.a = (byte)2;
                            h2.p.a.a((int)(59 + i * 5)).h().m.a(258, false, (byte)-1);
                        }
                        if (h2.p.a.a((int)(59 + i * 5)).h().i == null) {
                            h2.p.a.a((int)(59 + i * 5)).h().i = new SpriteWidget();
                            h2.p.a.a((int)(59 + i * 5)).h().i.a(0);
                            h2.p.a.a((int)(59 + i * 5)).h().i.a = (byte)2;
                            h2.p.a.a((int)(59 + i * 5)).h().i.a(258, false, (byte)-1);
                        }
                        h2.p.a.a((int)(59 + i * 5)).h().m.a(GameDatabase.gameDatabase[3][intArray[0]][1]);
                        h2.p.a.a((int)(59 + i * 5)).h().i.a(GameDatabase.gameDatabase[3][intArray[0]][1]);
                        h2.p.a.a((int)(60 + i * 5)).h().a = BaseScreen.f(GameDatabase.gameDatabase[3][intArray[0]][0]);
                        if (intArray[1] == 1) {
                            h2.p.a.a((int)(61 + i * 5)).h().a = "Đã mang theo";
                            continue;
                        }
                        h2.p.a.a((int)(61 + i * 5)).h().a = "";
                        continue;
                    }
                    if (h2.p.a.a((int)(59 + i * 5)).h().m != null) {
                        h2.p.a.a((int)(59 + i * 5)).h().m.d();
                    }
                    h2.p.a.a((int)(60 + i * 5)).h().a = "";
                    h2.p.a.a((int)(61 + i * 5)).h().a = "";
                }
                h2.p.a.a((int)85).h().a = h2.q.L.size() > 0 ? BaseScreen.f(GameDatabase.gameDatabase[3][((int[])h2.q.L.elementAt(h2.h))[0]][2]) : "";
                if (h2.q.L.size() > 0) {
                    h2.p.a.a(84).b(127 + h2.h * 72 / h2.q.L.size(), h2.p.a.a());
                    break;
                }
                h2.p.a.a(84).b(127, h2.p.a.a());
                break;
            }
            case 2: {
                ScriptEngine h3 = this;
                if (h3.q.M.size() > 5) {
                    ((MenuWidget)h3.p.a.a((int)86)).a.a(1);
                } else {
                    ((MenuWidget)h3.p.a.a((int)86)).a.a(0);
                }
                ((MenuWidget)h3.p.a.a((int)86)).a.a = h3.q.M.size();
                h3.w = ((MenuWidget)h3.p.a.a((int)86)).a.e;
                h3.h = ((MenuWidget)h3.p.a.a((int)86)).a.f;
                h3.p.a.a(7).a(false);
                for (int i3 = 0; i3 < 5; ++i3) {
                    if (h3.w + i3 < h3.q.M.size()) {
                        int[] intArray = (int[])h3.q.M.elementAt(h3.w + i3);
                        if (h3.p.a.a((int)(98 + i3 * 5)).h().m == null) {
                            h3.p.a.a((int)(98 + i3 * 5)).h().m = new SpriteWidget();
                            h3.p.a.a((int)(98 + i3 * 5)).h().m.a(0);
                            h3.p.a.a((int)(98 + i3 * 5)).h().m.a = (byte)2;
                            h3.p.a.a((int)(98 + i3 * 5)).h().m.a(258, false, (byte)-1);
                        }
                        if (h3.p.a.a((int)(98 + i3 * 5)).h().i == null) {
                            h3.p.a.a((int)(98 + i3 * 5)).h().i = new SpriteWidget();
                            h3.p.a.a((int)(98 + i3 * 5)).h().i.a(0);
                            h3.p.a.a((int)(98 + i3 * 5)).h().i.a = (byte)2;
                            h3.p.a.a((int)(98 + i3 * 5)).h().i.a(258, false, (byte)-1);
                        }
                        h3.p.a.a((int)(98 + i3 * 5)).h().m.a(GameDatabase.gameDatabase[3][intArray[0]][1]);
                        h3.p.a.a((int)(98 + i3 * 5)).h().i.a(GameDatabase.gameDatabase[3][intArray[0]][1]);
                        h3.p.a.a((int)(99 + i3 * 5)).h().a = intArray[0] == 17 ? "Chìa khóa vàng" : BaseScreen.f(GameDatabase.gameDatabase[3][intArray[0]][0]);
                        h3.p.a.a((int)(100 + i3 * 5)).h().a = "" + intArray[1];
                        continue;
                    }
                    if (h3.p.a.a((int)(98 + i3 * 5)).h().m != null) {
                        h3.p.a.a((int)(98 + i3 * 5)).h().m.d();
                    }
                    h3.p.a.a((int)(99 + i3 * 5)).h().a = "";
                    h3.p.a.a((int)(100 + i3 * 5)).h().a = "";
                }
                h3.p.a.a((int)124).h().a = h3.q.M.size() > 0 ? BaseScreen.f(GameDatabase.gameDatabase[3][((int[])h3.q.M.elementAt(h3.h))[0]][2]) : "";
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
                int[] intArray = (int[])this.q.N.elementAt(this.h);
                this.p.a.a(164).a(false);
                this.p.a.a(165).a(false);
                switch (intArray[0]) {
                    case 0: {
                        if (this.q.k(intArray[0])) {
                            this.p.a.a(7).a(true);
                            this.p.a.a((int)7).h().a = "Ấp trứng";
                            this.p.a.a(164).a(true);
                            this.p.a.a(165).a(true);
                            if (this.q.I == 0) {
                                this.p.a.a((int)164).h().a = "#P" + game.WorldManager.q * 100 / 10;
                                this.p.a.a((int)165).h().a = game.WorldManager.q + "/10";
                                break block0;
                            }
                            this.p.a.a((int)164).h().a = "#P" + game.WorldManager.q * 100 / 30;
                            this.p.a.a((int)165).h().a = game.WorldManager.q + "/30";
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
                        this.p.a.a((int)7).h().a = "Mở ra";
                        break block0;
                    }
                    case 7: 
                    case 8: 
                    case 9: {
                        this.p.a.a((int)7).h().a = "Sử dụng";
                    }
                }
            }
        }
        this.g = true;
    }

    private void bk() {
        int n2 = this.q.K.size() + this.q.J.size();
        if (n2 > 5) {
            ((MenuWidget)this.p.a.a((int)8)).a.a(1);
        } else {
            ((MenuWidget)this.p.a.a((int)8)).a.a(0);
        }
        ((MenuWidget)this.p.a.a((int)8)).a.a = n2;
        this.w = ((MenuWidget)this.p.a.a((int)8)).a.e;
        this.h = ((MenuWidget)this.p.a.a((int)8)).a.f;
        this.p.a.a(7).a(true);
        this.p.a.a((int)7).h().a = "Sử dụng";
        for (int i = 0; i < 5; ++i) {
            if (this.w + i < n2) {
                int[] intArray = this.w + i < this.q.K.size() ? (int[])this.q.K.elementAt(this.w + i) : (int[])this.q.J.elementAt(this.w + i - this.q.K.size());
                if (this.p.a.a((int)(18 + i * 5)).h().m == null) {
                    this.p.a.a((int)(18 + i * 5)).h().m = new SpriteWidget();
                    this.p.a.a((int)(18 + i * 5)).h().m.a(0);
                    this.p.a.a((int)(18 + i * 5)).h().m.a = (byte)2;
                    this.p.a.a((int)(18 + i * 5)).h().m.a(258, false, (byte)-1);
                }
                if (this.p.a.a((int)(18 + i * 5)).h().i == null) {
                    this.p.a.a((int)(18 + i * 5)).h().i = new SpriteWidget();
                    this.p.a.a((int)(18 + i * 5)).h().i.a(0);
                    this.p.a.a((int)(18 + i * 5)).h().i.a = (byte)2;
                    this.p.a.a((int)(18 + i * 5)).h().i.a(258, false, (byte)-1);
                }
                this.p.a.a((int)(18 + i * 5)).h().m.a(GameDatabase.gameDatabase[4][intArray[0]][1]);
                this.p.a.a((int)(18 + i * 5)).h().i.a(GameDatabase.gameDatabase[4][intArray[0]][1]);
                this.p.a.a((int)(19 + i * 5)).h().a = BaseScreen.f(GameDatabase.gameDatabase[4][intArray[0]][0]);
                this.p.a.a((int)(20 + i * 5)).h().a = "" + intArray[1];
                continue;
            }
            if (this.p.a.a((int)(18 + i * 5)).h().m != null) {
                this.p.a.a((int)(18 + i * 5)).h().m.d();
            }
            this.p.a.a((int)(19 + i * 5)).h().a = "";
            this.p.a.a((int)(20 + i * 5)).h().a = "";
        }
        this.p.a.a((int)46).h().a = n2 > 0 ? (this.h < this.q.K.size() ? BaseScreen.f(GameDatabase.gameDatabase[4][((int[])this.q.K.elementAt(this.h))[0]][2]) : BaseScreen.f(GameDatabase.gameDatabase[4][((int[])this.q.J.elementAt(this.h - this.q.K.size()))[0]][2])) : "";
        if (n2 > 0) {
            this.p.a.a(43).b(127 + this.h * 72 / n2, this.p.a.a());
            return;
        }
        this.p.a.a(43).b(127, this.p.a.a());
    }

    private void bl() {
        int n2;
        if (this.q.N.size() > 5) {
            ((MenuWidget)this.p.a.a((int)125)).a.a(1);
        } else {
            ((MenuWidget)this.p.a.a((int)125)).a.a(0);
        }
        ((MenuWidget)this.p.a.a((int)125)).a.a = this.q.N.size();
        this.w = ((MenuWidget)this.p.a.a((int)125)).a.e;
        this.h = ((MenuWidget)this.p.a.a((int)125)).a.f;
        for (n2 = 0; n2 < 5; ++n2) {
            if (this.w + n2 < this.q.N.size()) {
                int[] intArray = (int[])this.q.N.elementAt(this.w + n2);
                if (this.p.a.a((int)(137 + n2 * 5)).h().m == null) {
                    this.p.a.a((int)(137 + n2 * 5)).h().m = new SpriteWidget();
                    this.p.a.a((int)(137 + n2 * 5)).h().m.a(0);
                    this.p.a.a((int)(137 + n2 * 5)).h().m.a = (byte)2;
                    this.p.a.a((int)(137 + n2 * 5)).h().m.a(258, false, (byte)-1);
                }
                if (this.p.a.a((int)(137 + n2 * 5)).h().i == null) {
                    this.p.a.a((int)(137 + n2 * 5)).h().i = new SpriteWidget();
                    this.p.a.a((int)(137 + n2 * 5)).h().i.a(0);
                    this.p.a.a((int)(137 + n2 * 5)).h().i.a = (byte)2;
                    this.p.a.a((int)(137 + n2 * 5)).h().i.a(258, false, (byte)-1);
                }
                this.p.a.a((int)(137 + n2 * 5)).h().m.a(GameDatabase.gameDatabase[5][intArray[0]][1]);
                this.p.a.a((int)(137 + n2 * 5)).h().i.a(GameDatabase.gameDatabase[5][intArray[0]][1]);
                this.p.a.a((int)(138 + n2 * 5)).h().a = BaseScreen.f(GameDatabase.gameDatabase[5][intArray[0]][0]);
                switch (intArray[0]) {
                    case 0: {
                        if (this.q.k(intArray[0])) {
                            this.p.a.a((int)163).h().a = BaseScreen.f(GameDatabase.gameDatabase[5][intArray[0]][2]);
                            if (game.WorldManager.getInstance().r()) {
                                this.p.a.a((int)(139 + n2 * 5)).h().a = "Hoàn thành";
                                break;
                            }
                            this.p.a.a((int)(139 + n2 * 5)).h().a = "1 cái";
                            break;
                        }
                        this.p.a.a((int)163).h().a = BaseScreen.f(634);
                        this.p.a.a((int)(139 + n2 * 5)).h().a = "0 cái";
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
                this.p.a.a((int)163).h().a = BaseScreen.f(GameDatabase.gameDatabase[5][n2][2]);
                this.p.a.a(7).a(true);
            }
            this.p.a.a((int)7).h().a = n2 == 0 ? (((int[])this.q.N.elementAt(this.h))[1] == 1 ? "Đóng cửa" : "Mở ra") : (n2 > 0 || n2 <= 4 ? (this.q.t == n2 - 1 ? "Triệu hồi" : "Triệu hoán") : (n2 == 10 ? "Gia tốc" : "Sử dụng"));
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
        if (this.f == 0 && this.o.k(262144 /* MASK_SOFT_RIGHT */)) {
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
        if (this.o.k(262144 /* MASK_SOFT_RIGHT */)) {
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
                    this.a("Chỉ có thể cho 50 cấp sủng vật sử dụng", "Nhấn nút 5 để tiếp tục");
                    this.f = 2;
                    return;
                }
                if (this.q.e(this.s, this.b)) {
                    this.p.a("/data/ui/msgwarm.ui", 257, this);
                    this.a("Sử dụng thành công", "Nhấn nút 5 để tiếp tục");
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
        } else if (this.o.k(262144 /* MASK_SOFT_RIGHT */) && this.f == 0) {
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
                                                    if (this.f != 0 || !this.o.k(16400) || this.j() || BaseScreen.b(this.b, 1)) break block45;
                                                    this.p.a.b(7);
                                                    this.p.a.b(2);
                                                    this.p.a.b(5);
                                                    this.bi();
                                                    this.o.m();
                                                    break block46;
                                                }
                                                if (this.f != 0 || !this.o.k(32832) || this.j() || BaseScreen.b(this.b, 1)) break block47;
                                                this.p.a.b(7);
                                                this.p.a.b(3);
                                                this.p.a.b(5);
                                                this.bi();
                                                this.o.m();
                                                break block46;
                                            }
                                            if (this.f != 0 || !this.o.k(4100) || this.j() || BaseScreen.b(this.h, 0)) break block48;
                                            this.p.a.b(0);
                                            break block46;
                                        }
                                        if (this.f != 0 || !this.o.k(8448) || this.j() || BaseScreen.b(this.h, 0)) break block49;
                                        this.p.a.b(1);
                                        break block46;
                                    }
                                    if (!this.o.k(196640) || this.j() || !BaseScreen.I()) break block50;
                                    if (this.f != 0) break block51;
                                    if (BaseScreen.H() && !BaseScreen.b(this.h, 0)) {
                                        return;
                                    }
                                    block0 : switch (this.b) {
                                        case 0: {
                                            int[] intArray;
                                            if (this.h >= this.q.K.size()) {
                                                if (this.q.J.size() <= 0) {
                                                    return;
                                                }
                                                intArray = (int[])this.q.J.elementAt(this.h - this.q.K.size());
                                            } else {
                                                intArray = (int[])this.q.K.elementAt(this.h);
                                            }
                                            switch (intArray[0]) {
                                                case 0: 
                                                case 1: 
                                                case 2: 
                                                case 3: {
                                                    if (this.f == 0) {
                                                        this.p.a("/data/ui/msgwarm.ui", 257, this);
                                                        this.a("Không thể sử dụng", "Nhấn nút 5 để tiếp tục");
                                                        this.f = 1;
                                                        break;
                                                    }
                                                    this.p.a("/data/ui/msgwarm.ui");
                                                    this.f = 0;
                                                    break;
                                                }
                                                case 14: {
                                                    if (this.f != 0) break;
                                                    if (this.q.k(0) && (this.q.I == 0 && game.WorldManager.q < 10 || this.q.I > 0 && game.WorldManager.q < 30)) {
                                                        if (!this.q.b(intArray[0], 1, (byte)0)) break;
                                                        game.WorldManager.q = this.q.I == 0 ? 10 : 30;
                                                        this.q.d(intArray[0], 1, (byte)0);
                                                        int n3 = this.q.K.size() + this.q.J.size();
                                                        if (this.h >= n3) {
                                                            ((MenuWidget)this.p.a.a((int)8)).a.f = this.h = n3 - 1;
                                                        }
                                                        if (this.w > 0 && this.h - this.w < 4) {
                                                            --this.w;
                                                            ((MenuWidget)this.p.a.a((int)8)).a.e = this.w;
                                                        }
                                                        this.bk();
                                                        this.p.a("/data/ui/msgwarm.ui", 257, this);
                                                        this.a("Thành công sử dụng, tranh thủ thời gian đi ấp trứng trứng sủng vật a!", "Nhấn nút 5 để tiếp tục");
                                                        this.f = 1;
                                                        break;
                                                    }
                                                    this.p.a("/data/ui/msgwarm.ui", 257, this);
                                                    this.a("Không có trứng có thể ấp trứng", "Nhấn nút 5 để tiếp tục");
                                                    this.f = 1;
                                                    break;
                                                }
                                                case 13: {
                                                    if (this.f != 0) break;
                                                    if (this.q.x <= 0) {
                                                        if (game.WorldManager.getInstance().f == 3 && game.WorldManager.getInstance().g == 7) {
                                                            this.E();
                                                            this.a("Nơi này không cách nào sử dụng tránh quái hoàn", "Nhấn nút 5 để tiếp tục");
                                                            this.f = 1;
                                                            break;
                                                        }
                                                        if (!this.q.b(intArray[0], 1, (byte)0)) break;
                                                        this.q.d(intArray[0], 1, (byte)0);
                                                        this.q.x = GameDatabase.gameDatabase[4][intArray[0]][6];
                                                        this.q.w = 0;
                                                        int n4 = this.q.K.size() + this.q.J.size();
                                                        if (this.h >= n4) {
                                                            ((MenuWidget)this.p.a.a((int)8)).a.f = this.h = n4 - 1;
                                                        }
                                                        if (this.w > 0 && this.h - this.w < 4) {
                                                            --this.w;
                                                            ((MenuWidget)this.p.a.a((int)8)).a.e = this.w;
                                                        }
                                                        this.bk();
                                                        this.E();
                                                        this.q.c(1);
                                                        this.a("Thành công sử dụng đạo cụ, cũng có thời gian ngắn tránh quái hiệu quả", "Nhấn nút 5 để tiếp tục");
                                                        this.f = 1;
                                                        break;
                                                    }
                                                    this.E();
                                                    this.a("Đã có được thời gian ngắn tránh quái hiệu quả", "Nhấn nút 5 để tiếp tục");
                                                    this.f = 1;
                                                    break;
                                                }
                                                default: {
                                                    this.s = intArray[0];
                                                    this.o.a((byte)17);
                                                    this.p.a("/data/ui/bag.ui");
                                                    break;
                                                }
                                            }
                                            break;
                                        }
                                        case 3: {
                                            int[] intArray = (int[])this.q.N.elementAt(this.h);
                                            switch (intArray[0]) {
                                                case 0: {
                                                    if (!this.q.k(intArray[0])) break block0;
                                                    if (game.WorldManager.getInstance().r()) {
                                                        if (this.q.y() == 2) {
                                                            this.E();
                                                            this.a("Không gian không đủ, thỉnh thanh lý không gian lại ấp trứng", "Nhấn nút 5 để tiếp tục");
                                                            this.f = 1;
                                                            break block0;
                                                        }
                                                        game.WorldManager.q = 0;
                                                        if (game.WorldManager.getInstance().M.b[game.WorldManager.a(4, 5)] != null) {
                                                            game.WorldManager.getInstance().M.b[game.WorldManager.a((int)4, (int)5)][15] = 4;
                                                            if (game.WorldManager.getInstance().f == 4 && game.WorldManager.getInstance().g == 5) {
                                                                game.WorldManager.getInstance().M.a[15].a((byte)4);
                                                            }
                                                        }
                                                        this.q.j(intArray[0]);
                                                        this.bl();
                                                        this.E();
                                                        this.a("Ấp trứng thành công", "Nhấn nút 5 để tiếp tục");
                                                        this.f = 2;
                                                        break block0;
                                                    }
                                                    this.E();
                                                    this.a("Vẫn chưa thể ấp trứng", "Nhấn nút 5 để tiếp tục");
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
                                                    this.s = intArray[0];
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
                                    this.c("Ấp trứng tìm được #2" + BaseScreen.f(GameDatabase.gameDatabase[0][58][0]) + "#0 để vào ba lô");
                                    break block44;
                                } else if (by == 1) {
                                    this.c("Ấp trứng tìm được #2" + BaseScreen.f(GameDatabase.gameDatabase[0][58][0]) + "#0 để vào ngân hàng");
                                    break block44;
                                } else {
                                    this.c("Không có không gian, đã phóng sinh");
                                }
                                break block44;
                            }
                            int n5 = EngineUtils.a(new int[]{76, 52, 28, 4, 0}, EngineUtils.randomInt(100));
                            short[] shortArray = new short[]{0, 56, 58, 95, 72};
                            byte by = this.g(shortArray[n5]);
                            for (n2 = 0; n2 < this.q.I && this.q.R[n2] != shortArray[n5]; ++n2) {
                            }
                            if (n2 >= this.q.I) {
                                this.q.a(shortArray[n5]);
                            }
                            if (by == 0) {
                                this.c("Ấp trứng tìm được #2" + BaseScreen.f(GameDatabase.gameDatabase[0][shortArray[n5]][0]) + "#0 để vào ba lô");
                            } else if (by == 1) {
                                this.c("Ấp trứng tìm được #2" + BaseScreen.f(GameDatabase.gameDatabase[0][shortArray[n5]][0]) + "#0 để vào ngân hàng");
                            } else {
                                this.c("Không có không gian, đã phóng sinh");
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
            if (this.f == 0 && this.o.k(262144 /* MASK_SOFT_RIGHT */) && !this.j() && BaseScreen.J()) {
                this.b = BaseScreen.X ? 2 : 1;
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
        if (GameDatabase.gameDatabase[0][n2][4] == 5) {
            if (GameDatabase.gameDatabase[0][n2][3] == 2) {
                n3 = 1;
                n4 = 2;
            } else if (GameDatabase.gameDatabase[0][n2][3] == 3) {
                n3 = 0;
                n4 = 3;
            }
        }
        int n5 = GameDatabase.gameDatabase[0][n2][1] * 10;
        int n6 = GameDatabase.gameDatabase[1][n5][5];
        byte by = this.q.y();
        if (n3 == -1) {
            if (by == 0) {
                this.q.a(n2, 5, (byte)2, (short)-1, new int[]{1, n5, n6});
            } else if (by == 1) {
                int n7 = EngineUtils.b(GameDatabase.gameDatabase[0][n2][3], (int)GameDatabase.gameDatabase[0][n2][3]);
                this.q.a(n2, 5, (byte)2, (byte)n7, game.Pet.b(n2, 5, n7), -1, new int[]{1, n5, n6});
            }
        } else {
            n4 = (byte)(n4 + (byte)EngineUtils.a(nArrayArray[n3], EngineUtils.randomInt(100)));
            if (by == 0) {
                this.q.a(n2, 5, (byte)2, (short)n4, new int[]{1, n5, n6});
            } else if (by == 1) {
                this.q.a(n2, 5, (byte)2, (short)n4, game.Pet.b(n2, 5, n4), -1, new int[]{1, n5, n6});
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
        for (int i = 0; i < 4; ++i) {
            if (this.p.a.a((int)(i + 4)).h().m == null) {
                this.p.a.a((int)(i + 4)).h().m = new SpriteWidget();
                this.p.a.a((int)(i + 4)).h().m.a(0);
                this.p.a.a((int)(i + 4)).h().m.a = (byte)3;
                this.p.a.a((int)(i + 4)).h().m.a(260, false, (byte)-1);
            }
            if (this.p.a.a((int)(i + 16)).h().m == null) {
                this.p.a.a((int)(i + 16)).h().m = new SpriteWidget();
                this.p.a.a((int)(i + 16)).h().m.a(131);
                this.p.a.a((int)(i + 16)).h().m.a = (byte)2;
                this.p.a.a((int)(i + 16)).h().m.a(257, false, (byte)0);
            }
            if (this.q.f(i)) {
                if (this.b == i) {
                    this.p.a.a((int)(i + 4)).h().m.a((byte)i, (byte)-1);
                    if (this.b == 0) {
                        this.p.a.a((int)(i + 8)).h().a = "Lục đi điểu";
                    } else if (this.b == 1) {
                        this.p.a.a((int)(i + 8)).h().a = "Hư không hành giả";
                    } else if (this.b == 2) {
                        this.p.a.a((int)(i + 8)).h().a = "Hải âu";
                    } else if (this.b == 3) {
                        this.p.a.a((int)(i + 8)).h().a = "Nham sơn long";
                    }
                } else {
                    this.p.a.a((int)(i + 4)).h().m.a((byte)(i + 8), (byte)-1);
                    this.p.a.a((int)(i + 8)).h().a = "";
                }
                if (!this.q.g(i)) {
                    this.p.a.a(i + 16).a(true);
                    continue;
                }
                this.p.a.a(i + 16).a(false);
                continue;
            }
            this.p.a.a(i + 16).a(false);
            this.p.a.a((int)(i + 4)).h().m.a((byte)(i + 4), (byte)-1);
            this.p.a.a((int)(i + 8)).h().a = "";
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
                    this.b("Nơi này không thể sử dụng sủng vật cưỡi");
                }
            } else {
                this.b("Chưa có sủng vật cưỡi này");
            }
        } else if (!this.j() && this.o.k(262144 /* MASK_SOFT_RIGHT */)) {
            this.p.a("/data/ui/ride.ui");
            this.o.a((byte)0);
        }
        this.f();
        this.g = true;
    }

    public final void a(Pet object, b b2) {
        this.p.a("/data/ui/battle.ui", 257, this);
        this.a = 0;
        this.e = 0;
        this.a((Pet)object, false);
        this.b(b2, false);
        object = this;
        this.p.a.a((int)59).h().a = "100%";
        ((ScriptEngine)object).p.a.a((int)58).h().a = "100%";
        ((BattleScreen)((ScriptEngine)object).o).e();
        this.p.a("/data/ui/world.ui");
    }

    public final void b(Pet b2, b b3) {
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

    public final void a(Pet b2, b b3, Pet b4, int n2, int n3) {
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

    public final void a(Pet b2, b b3, int n2, int n3) {
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

    public final boolean a(Pet b2, boolean flag2) {
        int n2;
        int n3;
        int n4 = 0;
        if (this.F == 0 && (n4 = Math.abs(b2.N() - b2.d[1]) / 11) <= 1) {
            n4 = 1;
        }
        if ((n3 = b2.N()) != (n2 = b2.d[1])) {
            ++this.G;
            if (this.G < 4) {
                if (flag2) {
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
        if (flag2) {
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
        this.p.a.a((int)12).h().a = BaseScreen.f(b2.j((byte)0));
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

    public final void a(Pet b2) {
        int n2;
        for (n2 = 0; n2 < 6; ++n2) {
            if (this.p.a.a((int)(n2 + 26)).h().m == null) {
                this.p.a.a((int)(n2 + 26)).h().m = new SpriteWidget();
                this.p.a.a((int)(n2 + 26)).h().m.a = (byte)2;
                this.p.a.a((int)(n2 + 26)).h().m.a(0);
                this.p.a.a((int)(n2 + 26)).h().m.a(325, false, (byte)0);
            }
            if (this.p.a.a((int)(n2 + 43)).h().m == null) {
                this.p.a.a((int)(n2 + 43)).h().m = new SpriteWidget();
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

    private void g(Pet b2) {
        this.p.a.a((int)11).h().a = "#P" + b2.L();
        this.p.a.a((int)38).h().a = b2.N() + "/" + b2.c[1];
        this.p.a.a((int)16).h().a = "lv" + b2.s();
    }

    public final boolean b(Pet b2, boolean flag2) {
        int n2;
        int n3;
        int n4 = 0;
        if (this.F == 0 && (n4 = Math.abs(b2.N() - b2.d[1]) / 11) <= 1) {
            n4 = 1;
        }
        if ((n3 = b2.N()) != (n2 = b2.d[1])) {
            ++this.G;
            if (this.G < 4) {
                if (flag2) {
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
        if (flag2) {
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
        this.p.a.a((int)15).h().a = BaseScreen.f(b2.j((byte)0));
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

    public final void b(Pet b2) {
        int n2;
        for (n2 = 0; n2 < 6; ++n2) {
            if (this.p.a.a((int)(n2 + 32)).h().m == null) {
                this.p.a.a((int)(n2 + 32)).h().m = new SpriteWidget();
                this.p.a.a((int)(n2 + 32)).h().m.a = (byte)2;
                this.p.a.a((int)(n2 + 32)).h().m.a(0);
                this.p.a.a((int)(n2 + 32)).h().m.a(325, false, (byte)0);
            }
            if (this.p.a.a((int)(n2 + 49)).h().m == null) {
                this.p.a.a((int)(n2 + 49)).h().m = new SpriteWidget();
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

    private void a(boolean flag) {
        this.p.a.a(20 + this.a).a(flag);
    }

    public final void ag() {
        ((MenuWidget)this.p.a.a((int)0)).b.f = this.a;
        this.a(true);
    }

    public final void c(Pet b2) {
        this.f = 0;
        this.a(b2, false);
        this.ag();
    }

    public final void d(Pet b2) {
        ((BattleScreen)this.o).l();
        if (!BaseScreen.b(this.a, 1) && this.f == 0 && !this.j() && this.o.k(16400)) {
            this.p.a.b(2);
        } else if (!BaseScreen.b(this.a, 1) && this.f == 0 && !this.j() && this.o.k(32832)) {
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
                            this.a("Trạng thái bị quấn, không thể sử dụng đạo cụ", "Nhấn nút 5 để tiếp tục");
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
                            this.a("Trạng thái bị quấn, không thể đổi sủng vật", "Nhấn nút 5 để tiếp tục");
                            this.f = 1;
                            break;
                        }
                        this.a(false);
                        ((BattleScreen)this.o).g = ((BattleScreen)this.o).e[((BattleScreen)this.o).i];
                        game.BattleScreen.getInstance().k = true;
                        this.o.a((byte)5);
                        break;
                    }
                    this.p.a("/data/ui/msgwarm.ui");
                    this.f = 0;
                    break;
                }
                case 1: {
                    if (((BattleScreen)this.o).b == 2) {
                        this.b("Trận chiến này không cho bắt sủng vật");
                        break;
                    }
                    if (this.q.y() == 2) {
                        this.b("Không gian không đủ, không cách nào bắt được");
                        break;
                    }
                    this.b = 0;
                    this.a(false);
                    ((BattleScreen)this.o).m();
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
                            this.a("Trạng thái bị quấn, không thể chạy trốn", "Nhấn nút 5 để tiếp tục");
                            this.f = 1;
                            break;
                        }
                        if (((BattleScreen)this.o).b > 0 || !game.OverworldScreen.j) {
                            this.a(false);
                            this.f = 3;
                            this.b("Trận chiến này không thể trốn chạy");
                            break;
                        }
                        boolean bl = false;
                        if (((BattleScreen)this.o).h.s() > ((BattleScreen)this.o).d[0].s()) {
                            bl = true;
                        } else if (((BattleScreen)this.o).h.s() == ((BattleScreen)this.o).d[0].s()) {
                            if (EngineUtils.randomInt(100) <= 95) {
                                bl = true;
                            }
                        } else {
                            int n2 = ((BattleScreen)this.o).d[0].s() - ((BattleScreen)this.o).h.s();
                            if ((n2 = 95 - n2 * 10) <= 15) {
                                n2 = 15;
                            }
                            if (EngineUtils.randomInt(100) < n2) {
                                bl = true;
                            }
                        }
                        if (bl) {
                            this.a(false);
                            game.GameStateController.getInstance().a((byte)10);
                            break;
                        }
                        this.f = 2;
                        this.b("Chạy trốn thất bại");
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
                ((BattleScreen)this.o).h.J = true;
                ((BattleScreen)this.o).i = (byte)(((BattleScreen)this.o).i + 1);
                this.o.a((byte)1);
            } else {
                this.a(true);
            }
            this.f = 0;
        }
    }

    public final void e(Pet b2) {
        this.p.a("/data/ui/choiceskill.ui", 257, this);
        ((MenuWidget)this.p.a.a((int)0)).a.a = b2.E();
        if (this.e >= b2.E()) {
            this.e = b2.E() - 1;
        }
        if (b2.E() > 5) {
            ((MenuWidget)this.p.a.a((int)0)).a.a(1);
        } else {
            ((MenuWidget)this.p.a.a((int)0)).a.a(-1);
        }
        this.p.a.a((int)5).h().a = "Sử dụng";
        ((MenuWidget)this.p.a.a((int)0)).a.f = this.e;
        this.h(b2);
        this.f = 0;
    }

    private void h(Pet b2) {
        this.w = ((MenuWidget)this.p.a.a((int)0)).a.e;
        this.h = ((MenuWidget)this.p.a.a((int)0)).a.f;
        int n2 = b2.E();
        for (int i = 0; i < 5; ++i) {
            if (i >= n2) {
                this.p.a.a((int)(13 + i * 5)).h().a = "";
                this.p.a.a((int)(14 + i * 5)).h().a = "";
                continue;
            }
            this.p.a.a((int)(13 + i * 5)).h().a = BaseScreen.f(GameDatabase.gameDatabase[1][b2.t(this.w + i)][1]);
            this.p.a.a((int)(14 + i * 5)).h().a = b2.y[this.w + i] + "/" + GameDatabase.gameDatabase[1][b2.t(this.w + i)][5];
        }
        this.h(b2.z[this.e]);
        this.p.a.a(51).b(98 + this.h * 72 / n2, this.p.a.a());
    }

    private void h(int n2) {
        this.p.a.a((int)53).h().a = BaseScreen.f(GameDatabase.gameDatabase[1][n2][2]);
    }

    public final void f(Pet b2) {
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
                        ((BattleScreen)this.o).b(b2.z[this.e]);
                        int n2 = ((BattleScreen)this.o).a;
                        ((BattleScreen)this.o).getClass();
                        if (n2 == 0) {
                            ((BattleScreen)this.o).i();
                        } else {
                            this.o.a((byte)6);
                        }
                    } else {
                        this.f = 1;
                        this.p.a("/data/ui/msgwarm.ui", 257, this);
                        this.a("Kỹ năng giá trị chưa đủ", "Nhấn nút 5 để tiếp tục");
                    }
                } else {
                    this.f = 0;
                    this.p.a("/data/ui/msgwarm.ui");
                    if (b2.p(2) && b2.r() == 0) {
                        boolean bl = false;
                        for (int i = 0; i < b2.y.length; ++i) {
                            if (b2.y[i] == 0) continue;
                            bl = true;
                        }
                        if (!bl) {
                            this.p.a("/data/ui/choiceskill.ui");
                            this.c("Không có kỹ năng giá trị, không cách nào chiến đấu");
                            ((BattleScreen)this.o).h();
                        }
                    }
                }
            } else if (this.o.k(262144 /* MASK_SOFT_RIGHT */) && this.f == 0) {
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
        this.p.a.a((int)9).h().a = "Tỉ lệ bắt";
        this.p.a.a((int)5).h().a = "Sử dụng";
        ((MenuWidget)this.p.a.a((int)0)).a.f = this.b;
        ((MenuWidget)this.p.a.a((int)0)).a.a(0);
        ((MenuWidget)this.p.a.a((int)0)).a.a = this.q.K.size();
        for (int i = 0; i < this.q.K.size(); ++i) {
            int[] intArray = (int[])this.q.K.elementAt(i);
            if (this.p.a.a((int)(i + 54)).h().m == null) {
                this.p.a.a((int)(i + 54)).h().m = new SpriteWidget();
                this.p.a.a((int)(i + 54)).h().m.a(0);
                this.p.a.a((int)(i + 54)).h().m.a = (byte)2;
                this.p.a.a((int)(i + 54)).h().m.a(258, false, (byte)-1);
            }
            this.p.a.a((int)(i + 54)).h().m.a(GameDatabase.gameDatabase[4][intArray[0]][1]);
            this.p.a.a((int)(13 + i * 5)).h().a = BaseScreen.f(GameDatabase.gameDatabase[4][intArray[0]][0]);
            this.p.a.a((int)(14 + i * 5)).h().a = ((BattleScreen)this.o).b(intArray[0]) + "%";
        }
        this.p.a.a(59).a(false);
        this.p.a.a(60).a(false);
        this.bn();
    }

    private void bn() {
        int[] intArray = (int[])this.q.K.elementAt(this.b);
        this.p.a.a((int)53).h().a = "Số lượng: " + intArray[1] + " cái ";
    }

    public final void ai() {
        this.o.l();
        if (!BaseScreen.b(this.b, 0) && this.f == 0 && this.o.k(4100) && !this.j()) {
            this.p.a.b(0);
            this.bn();
        } else if (!BaseScreen.b(this.b, 0) && this.f == 0 && this.o.k(8448) && !this.j()) {
            this.p.a.b(1);
            this.bn();
        } else if (this.o.k(196640) && !this.j() && BaseScreen.I()) {
            if (BaseScreen.H() && !BaseScreen.b(this.b, 0)) {
                return;
            }
            if (this.f == 0) {
                int[] intArray = (int[])this.q.K.elementAt(this.b);
                if (!this.q.b(intArray[0], 1, (byte)0)) {
                    this.p.a("/data/ui/msgwarm.ui", 257, this);
                    this.a("Số lượng Pokemon ball không đủ", "Nhấn nút 5 để tiếp tục");
                    this.f = 1;
                } else {
                    this.f = 0;
                    game.BattleScreen.l = (byte)intArray[0];
                    this.o.m();
                    this.q.d(intArray[0], 1, (byte)0);
                    this.o.a((byte)17);
                    this.p.a("/data/ui/choice.ui");
                }
            } else if (this.f == 1) {
                if (BaseScreen.X && ((int[])this.q.K.elementAt(this.b))[0] == 0) {
                    this.p.a("/data/ui/choice.ui");
                    this.o.a((byte)101);
                }
                this.f = 0;
                this.p.a("/data/ui/msgwarm.ui");
            }
        } else if (game.OverworldScreen.J() && this.f == 0 && this.o.k(262144 /* MASK_SOFT_RIGHT */) && !this.j()) {
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
        this.p.a.a((int)8).h().a = "Đạo cụ";
        this.p.a.a((int)9).h().a = "Số lượng";
        this.p.a.a((int)5).h().a = "Sử dụng";
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
                switch (GameDatabase.gameDatabase[4][this.s][5]) {
                    case 7: 
                    case 8: 
                    case 9: 
                    case 10: {
                        this.p.a("/data/ui/msgwarm.ui", 257, this);
                        this.a("Trong chiến đấu không thể sử dụng", "Nhấn nút 5 để tiếp tục");
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
        } else if (this.f == 0 && this.o.k(262144 /* MASK_SOFT_RIGHT */)) {
            this.p.a("/data/ui/choice.ui");
            this.o.a((byte)20);
        }
    }

    private void bo() {
        if (this.f == 0) {
            this.f = 1;
            int n2 = this.o instanceof WorldManager ? this.q.z[this.c].x(this.s) : this.q.z[((BattleScreen)this.o).f[this.c]].x(this.s);
            switch (n2) {
                case 0: {
                    this.p.a("/data/ui/msgwarm.ui", 257, this);
                    this.a("Sủng vật này đã tử vong, không thể sử dụng", "Nhấn nút 5 để tiếp tục");
                    return;
                }
                case 1: {
                    this.p.a("/data/ui/msgwarm.ui", 257, this);
                    this.a("Sủng vật này không có, không thể sử dụng", "Nhấn nút 5 để tiếp tục");
                    return;
                }
                case 2: {
                    this.p.a("/data/ui/msgwarm.ui", 257, this);
                    this.a("Máu đầy, không cần sử dụng", "Nhấn nút 5 để tiếp tục");
                    return;
                }
                case 3: {
                    this.p.a("/data/ui/msgwarm.ui", 257, this);
                    this.a("Kỹ năng giá trị đã đầy, không cần sử dụng", "Nhấn nút 5 để tiếp tục");
                    return;
                }
                case 4: {
                    this.p.a("/data/ui/msgwarm.ui", 257, this);
                    this.a("Trên người đều bị lợi hiệu quả", "Nhấn nút 5 để tiếp tục");
                    return;
                }
                case 5: {
                    this.p.a("/data/ui/msgwarm.ui", 257, this);
                    this.a("Trong hưng phấn, không thể dùng", "Nhấn nút 5 để tiếp tục");
                    return;
                }
                case 7: {
                    this.p.a("/data/ui/msgwarm.ui", 257, this);
                    this.a("Máu và kỹ năng đều đã đầy, không cần sử dụng", "Nhấn nút 5 để tiếp tục");
                    return;
                }
                case 8: {
                    this.p.a("/data/ui/msgwarm.ui", 257, this);
                    this.a("Sủng vật đã chết, không thể sử dụng", "Nhấn nút 5 để tiếp tục");
                    return;
                }
            }
            if (this.q.b(this.s, 1, (byte)0)) {
                if (this.o instanceof WorldManager) {
                    this.q.z[this.c].w(this.s);
                } else {
                    ((BattleScreen)this.o).h.J = true;
                    this.q.z[((BattleScreen)this.o).f[this.c]].w(this.s);
                }
                this.e(this.c);
                this.f = 1;
                this.l = true;
                this.p.a("/data/ui/msgwarm.ui", 257, this);
                this.a("Thành công sử dụng đạo cụ", "Nhấn nút 5 để tiếp tục");
                return;
            }
            this.f = 2;
            this.E();
            this.a("Đã không có đạo này cụ, thỉnh mua sắm", "Nhấn nút 5 để tiếp tục");
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
            if (this.o instanceof WorldManager) {
                this.o.a((byte)8);
                return;
            }
            if (game.BattleScreen.getInstance().h.equals(((BattleScreen)this.o).c(this.c))) {
                this.g(((BattleScreen)this.o).d(this.c));
            }
            if (((BattleScreen)this.o).h.J) {
                ((BattleScreen)this.o).i = (byte)(((BattleScreen)this.o).i + 1);
                ((BattleScreen)this.o).a((byte)1);
                return;
            }
            ((BattleScreen)this.o).a((byte)4);
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
        if (this.f == 0 && this.o.k(262144 /* MASK_SOFT_RIGHT */)) {
            if (this.l) {
                if (game.BattleScreen.getInstance().h.equals(((BattleScreen)this.o).c(this.c))) {
                    this.g(((BattleScreen)this.o).d(this.c));
                }
                if (((BattleScreen)this.o).h.J) {
                    ((BattleScreen)this.o).i = (byte)(((BattleScreen)this.o).i + 1);
                    ((BattleScreen)this.o).a((byte)1);
                } else {
                    ((BattleScreen)this.o).a((byte)4);
                }
                this.p.a("/data/ui/petstate.ui");
                return;
            }
            ((BattleScreen)this.o).a((byte)4);
            this.p.a("/data/ui/petstate.ui");
        }
    }

    public final void a(int n2, int n3) {
        Pet b2;
        if (this.i >= game.BattleScreen.j.size()) {
            this.i = 0;
            game.GameStateController.getInstance().a((byte)10);
            return;
        }
        block0: while (true) {
            b2 = (Pet)game.BattleScreen.j.elementAt(this.i);
            while (this.i < game.BattleScreen.j.size() && b2.t()) {
                ++this.i;
                if (this.i >= game.BattleScreen.j.size()) continue;
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
                Pet b2;
                block18: {
                    if (this.i >= game.BattleScreen.j.size()) {
                        this.i = 0;
                        game.GameStateController.getInstance().a((byte)10);
                        return;
                    }
                    if (this.x <= 0) {
                        this.J += 8;
                    }
                    b2 = (Pet)game.BattleScreen.j.elementAt(this.i);
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
                            ((BattleScreen)this.o).a((byte)22);
                            return;
                        }
                        if (n3 >= n2) {
                            this.p.a.a((int)40).h().a = n2 + "/" + b2.u();
                            this.p.a.a((int)9).h().a = "#P" + b2.v(n2);
                            b2.j(n3);
                            ++this.i;
                            while (this.i < game.BattleScreen.j.size() && ((Pet)game.BattleScreen.j.elementAt(this.i)).t()) {
                                ++this.i;
                            }
                            if (this.i >= game.BattleScreen.j.size()) {
                                this.i = 0;
                                game.GameStateController.getInstance().a((byte)10);
                            } else {
                                ((Pet)game.BattleScreen.j.elementAt(this.i)).b(this.H, this.I);
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
                    Pet b3 = b2;
                    ScriptEngine h2 = this;
                    this.p.a.a((int)12).h().a = BaseScreen.f(b3.j((byte)0));
                    h2.p.a.a((int)13).h().a = "lv" + b3.s();
                    h2.p.a.a((int)17).h().m.a(94 + b3.j((byte)1));
                    if (n3 < n4) break block18;
                    b2.j(0);
                    ((BattleScreen)this.o).a((byte)22);
                    break block19;
                }
                if (n3 < n2) break block20;
                ++this.x;
                b2.j(n3);
                if (this.x >= 10) {
                    ++this.i;
                    while (this.i < game.BattleScreen.j.size() && ((Pet)game.BattleScreen.j.elementAt(this.i)).t()) {
                        ++this.i;
                    }
                    if (this.i >= game.BattleScreen.j.size()) {
                        this.i = 0;
                        game.GameStateController.getInstance().a((byte)10);
                    } else {
                        ((Pet)game.BattleScreen.j.elementAt(this.i)).b(this.H, this.I);
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
        Pet b2 = (Pet)game.BattleScreen.j.elementAt(this.i);
        String[] strArray = new String[4];
        for (n2 = 0; n2 < 4; ++n2) {
            by = (byte)(n2 + 1);
            strArray[n2] = "" + b2.c[by];
        }
        b2.v();
        this.g(b2);
        this.p.a("/data/ui/levelUp.ui", 257, this);
        for (n2 = 0; n2 < 4; ++n2) {
            this.p.a.a((int)(n2 + 19)).h().a = strArray[n2];
        }
        if (b2.E() < 5 && b2.E() < b2.s() / 10 + 1) {
            this.y = b2.F();
            this.p.a.a((int)51).h().a = "Có thể học tập kỹ năng mới";
        } else {
            this.p.a.a((int)51).h().a = "";
        }
        this.p.a.a((int)38).h().a = BaseScreen.f(GameDatabase.gameDatabase[0][b2.q()][0]);
        this.p.a.a((int)40).h().a = "" + b2.s();
        if (this.p.a.a((int)10).h().m == null) {
            this.p.a.a((int)10).h().m = new SpriteWidget();
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
                ((BattleScreen)this.o).a((byte)23);
            } else if (this.i + 1 >= game.BattleScreen.j.size()) {
                if (((Pet)game.BattleScreen.j.elementAt(this.i)).z() > 0) {
                    this.o.a((byte)8);
                } else {
                    this.i = 0;
                    game.GameStateController.getInstance().a((byte)10);
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
            if (this.i + 1 >= game.BattleScreen.j.size()) {
                if (((Pet)game.BattleScreen.j.elementAt(this.i)).z() > 0) {
                    this.o.a((byte)8);
                } else {
                    this.i = 0;
                    game.GameStateController.getInstance().a((byte)10);
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
        ((MenuWidget)this.p.a.a((int)0)).a.a = this.y.length;
        if (this.y.length > 5) {
            ((MenuWidget)this.p.a.a((int)0)).a.a(1);
        } else {
            ((MenuWidget)this.p.a.a((int)0)).a.a(-1);
        }
        if (this.p.a.a((int)5).h().m == null) {
            this.p.a.a((int)5).h().m = new SpriteWidget();
            this.p.a.a((int)5).h().m.a = (byte)3;
            this.p.a.a((int)5).h().m.a(0);
            this.p.a.a((int)5).h().m.a(257, false, (byte)-1);
        }
        this.p.a.a((int)5).h().m.a((byte)11, (byte)-1);
        this.p.a.a(6).a(false);
        this.bp();
        if (!game.WorldManager.D) {
            this.b("Có thể nhấn #1nút mềm trái#0 để học tập kỹ năng");
            game.WorldManager.D = true;
        }
    }

    private void bp() {
        this.w = ((MenuWidget)this.p.a.a((int)0)).a.e;
        this.h = ((MenuWidget)this.p.a.a((int)0)).a.f;
        for (int i = 0; i < 5; ++i) {
            if (i >= this.y.length) {
                this.p.a.a((int)(13 + i * 5)).h().a = "";
                this.p.a.a((int)(14 + i * 5)).h().a = "";
                continue;
            }
            this.p.a.a((int)(13 + i * 5)).h().a = BaseScreen.f(GameDatabase.gameDatabase[1][this.y[this.w + i]][1]);
            this.p.a.a((int)(14 + i * 5)).h().a = "" + GameDatabase.gameDatabase[1][this.y[this.w + i]][5];
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
        } else if (!this.j() && this.f == 0 && (this.o.k(131072 /* MASK_SOFT_LEFT */) || this.o.Q()) || this.f == 1 && this.o.k(196640)) {
            if (this.f == 0) {
                this.f = 1;
                this.p.a("/data/ui/msgwarm.ui", 257, this);
                this.a("Học tập" + BaseScreen.f(GameDatabase.gameDatabase[1][this.y[this.b]][1]), "Nhấn nút 5 để tiếp tục");
            } else if (this.f == 1) {
                Pet b2 = (Pet)game.BattleScreen.j.elementAt(this.i);
                b2.g((byte)this.y[this.h]);
                this.y = null;
                if (this.i + 1 >= game.BattleScreen.j.size() && b2.z() <= 0) {
                    this.i = 0;
                    game.GameStateController.getInstance().a((byte)10);
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
        this.b("Ba lô sủng vật đều thăng 5 cấp");
    }

    /*
     * Enabled aggressive block sorting
     */
    public final void as() {
        block14: {
            ScriptEngine h2;
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
                                                if (game.WorldManager.E.size() <= 0) {
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
                                    h2.a("Đang lưu...");
                                    h2.J();
                                    break block19;
                                }
                                if (h2.f != 6) break block20;
                                game.WorldManager.G = (byte)2;
                                game.WorldManager.h();
                                if (((WorldManager)h2.o).j()) {
                                    h2.a("Lưu thành công");
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
                if ((h2.j() || h2.f != 3 || !h2.o.k(131072 /* MASK_SOFT_LEFT */) && !h2.o.Q()) && (h2.f != 4 || !h2.o.k(196640))) break block14;
                if (h2.f == 3) {
                    h2.f = 4;
                    h2.p.a("/data/ui/msgwarm.ui", 257, h2);
                    h2.a("Học tập" + BaseScreen.f(GameDatabase.gameDatabase[1][h2.y[h2.h]][1]), "Nhấn nút 5 để tiếp tục");
                    break block14;
                } else if (h2.f == 4) {
                    ((Pet)game.WorldManager.E.elementAt(h2.i)).g((byte)h2.y[h2.h]);
                    h2.y = null;
                    ++h2.i;
                    if (h2.i >= game.WorldManager.E.size()) {
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
                ScriptEngine h3 = h2;
                h3.p.a("/data/ui/choiceskill.ui", 257, h3);
                h3.p.a("/data/ui/levelUp.ui");
                h3.b = 0;
                h3.f = 3;
                ((MenuWidget)h3.p.a.a((int)0)).a.a = h3.y.length;
                if (h3.y.length > 5) {
                    ((MenuWidget)h3.p.a.a((int)0)).a.a(1);
                } else {
                    ((MenuWidget)h3.p.a.a((int)0)).a.a(-1);
                }
                if (h3.p.a.a((int)5).h().m == null) {
                    h3.p.a.a((int)5).h().m = new SpriteWidget();
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
        Pet b2 = (Pet)game.WorldManager.E.elementAt(this.i);
        this.p.a("/data/ui/levelUp.ui", 257, this);
        for (n2 = 0; n2 < 4; ++n2) {
            this.p.a.a((int)(n2 + 19)).h().a = "" + b2.i((int)((byte)(n2 + 1 - 1)));
        }
        if (b2.E() < 5 && b2.E() < b2.s() / 10 + 1) {
            this.y = b2.F();
            this.p.a.a((int)51).h().a = "Nhấn nút 5 học tập kỹ năng mới";
        } else {
            this.p.a.a((int)51).h().a = "";
        }
        this.p.a.a((int)38).h().a = BaseScreen.f(GameDatabase.gameDatabase[0][b2.q()][0]);
        this.p.a.a((int)40).h().a = "" + b2.s();
        if (this.p.a.a((int)10).h().m == null) {
            this.p.a.a((int)10).h().m = new SpriteWidget();
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
            this.p.a.a((int)1).h().m = new SpriteWidget();
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
            this.p.a.a((int)n2).h().m = new SpriteWidget();
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
                        this.p.a.a((int)n2).h().m = new SpriteWidget();
                        this.p.a.a((int)n2).h().m.a = (byte)2;
                        this.p.a.a((int)n2).h().m.a(0);
                    }
                    if (n2 % 2 == 1) {
                        this.p.a.a((int)n2).h().m.a(0, false, (byte)-1);
                    } else if (game.WorldManager.u == -1) {
                        if (game.WorldManager.v == -1) {
                            this.p.a.a((int)n2).h().m.a(game.WorldManager.getInstance().d[8].a.a, false, (byte)-1);
                        } else {
                            this.p.a.a((int)n2).h().m.a(game.WorldManager.getInstance().d[game.WorldManager.v].a.a, false, (byte)-1);
                        }
                    } else {
                        this.p.a.a((int)n2).h().m.a(game.WorldManager.getInstance().d[game.WorldManager.u].a.a, false, (byte)-1);
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
                        this.p.a.a((int)(n2 + 32)).h().m = new SpriteWidget();
                        this.p.a.a((int)(n2 + 32)).h().m.a = (byte)2;
                        this.p.a.a((int)(n2 + 32)).h().m.a(0);
                    }
                    if (n2 % 2 == 1) {
                        this.p.a.a((int)(n2 + 32)).h().m.a(0, false, (byte)-1);
                    } else if (game.WorldManager.u == -1) {
                        if (game.WorldManager.v == -1) {
                            this.p.a.a((int)(n2 + 32)).h().m.a(game.WorldManager.getInstance().d[8].a.a, false, (byte)-1);
                        } else {
                            this.p.a.a((int)(n2 + 32)).h().m.a(game.WorldManager.getInstance().d[game.WorldManager.v].a.a, false, (byte)-1);
                        }
                    } else {
                        this.p.a.a((int)(n2 + 32)).h().m.a(game.WorldManager.getInstance().d[game.WorldManager.u].a.a, false, (byte)-1);
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
                        this.p.a.a((int)(n2 + 2)).h().m = new SpriteWidget();
                        this.p.a.a((int)(n2 + 2)).h().m.a = (byte)2;
                        this.p.a.a((int)(n2 + 2)).h().m.a(0);
                    }
                    if (n2 % 2 == 1) {
                        this.p.a.a((int)(n2 + 2)).h().m.a(0, false, (byte)-1);
                    } else if (game.WorldManager.u == -1) {
                        if (game.WorldManager.v == -1) {
                            this.p.a.a((int)(n2 + 2)).h().m.a(game.WorldManager.getInstance().d[8].a.a, false, (byte)-1);
                        } else {
                            this.p.a.a((int)(n2 + 2)).h().m.a(game.WorldManager.getInstance().d[game.WorldManager.v].a.a, false, (byte)-1);
                        }
                    } else {
                        this.p.a.a((int)(n2 + 2)).h().m.a(game.WorldManager.getInstance().d[game.WorldManager.u].a.a, false, (byte)-1);
                    }
                    this.p.a.a((int)(n2 + 2)).h().m.a(1);
                }
                this.p.a.a((int)1).h().m.a(n3);
                this.L = game.BattleScreen.getInstance().j();
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

    private void e(String text) {
        this.t = text;
        if (this.p.a.a((int)1).h().m == null) {
            this.p.a.a((int)1).h().m = new SpriteWidget();
            this.p.a.a((int)1).h().m.a(0);
            this.p.a.a((int)1).h().m.a = (byte)3;
            this.p.a.a((int)1).h().m.a(257, false, (byte)-2);
        }
        this.p.a.a((int)1).h().m.a((byte)9, (byte)-2);
        this.v = 0;
    }

    public final void au() {
        this.p.a("/data/ui/openbox.ui", 257, this);
        this.e("Không có cái chìa khóa, có thể đến tài liệu cửa hàng mua sắm");
    }

    public final void av() {
        this.p.a("/data/ui/openbox.ui", 257, this);
        this.e("Đạo cụ đã đủ");
    }

    public final void a(String text, int n2) {
        this.p.a("/data/ui/openbox.ui", 257, this);
        this.e(text + " x " + n2);
    }

    public final void b(String text) {
        this.p.a("/data/ui/openbox.ui", 257, this);
        this.e(text);
    }

    public final void aw() {
        if (this.p.b("/data/ui/openbox.ui")) {
            this.p.a("/data/ui/openbox.ui");
        }
    }

    public final boolean ax() {
        return !this.p.b("/data/ui/openbox.ui");
    }

    public final void c(String text) {
        this.p.a("/data/ui/taskTip.ui", 257, this);
        String string = text;
        text = this;
        this.t = string;
        if (((ScriptEngine)text).p.a.a((int)1).h().m == null) {
            ((ScriptEngine)text).p.a.a((int)1).h().m = new SpriteWidget();
            ((ScriptEngine)text).p.a.a((int)1).h().m.a(0);
            ((ScriptEngine)text).p.a.a((int)1).h().m.a = (byte)3;
            ((ScriptEngine)text).p.a.a((int)1).h().m.a(257, false, (byte)-2);
        }
        ((ScriptEngine)text).p.a.a((int)1).h().m.a((byte)10, (byte)-2);
        ((ScriptEngine)text).v = 0;
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
                object = "Tùy thời mua sắm các loại đạo cụ, già trẻ không gạt.";
                break;
            }
            case 1: {
                object = new int[]{2, 1, 2};
                object = BaseScreen.f(602) + BaseScreen.a(604, (int[])object);
                break;
            }
            case 2: {
                object = new int[]{2, 1, 2};
                object = BaseScreen.f(603) + BaseScreen.a(604, (int[])object);
                break;
            }
            case 3: {
                object = new int[]{2, 1, 2};
                object = BaseScreen.f(601) + BaseScreen.a(604, (int[])object);
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
                if (this.o.k(131072 /* MASK_SOFT_LEFT */)) {
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
                        if (this.f == 0 && this.o.k(131072 /* MASK_SOFT_LEFT */) || this.f == 1 && this.o.k(65568)) {
                            if (this.f == 0) {
                                this.bt();
                                if (this.o.L() == 3) {
                                    int n2;
                                    if (game.WorldManager.H != null) {
                                        game.WorldManager.H.removeAllElements();
                                    }
                                    for (n2 = 0; n2 < game.Player.getInstance().A && game.Player.getInstance().z[n2].s() >= 50; ++n2) {
                                    }
                                    if (n2 >= game.Player.getInstance().A) {
                                        this.f = 1;
                                        this.p.a("/data/ui/msgwarm.ui", 257, this);
                                        this.a("Trong ba lô sủng vật đều đã max level", "Nhấn nút 5 để tiếp tục");
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
                        if (this.o.k(131072 /* MASK_SOFT_LEFT */)) {
                            this.o.h(1);
                            return;
                        }
                        if (!this.o.k(262144 /* MASK_SOFT_RIGHT */)) break block0;
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
            this.a("Đang lưu...");
            this.J();
        } else if (this.f == 1) {
            if (this.o.L() == 3) {
                if (game.WorldManager.getInstance().k()) {
                    this.a("Lưu thành công");
                    this.f = 2;
                }
            } else if (game.WorldManager.getInstance().n()) {
                this.a("Lưu thành công");
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

    public final void a(String text, String string2, int n2) {
        this.p.a("/data/ui/dialog.ui", 257, this);
        EngineUtils.a(string2, BaseScreen.getFontHeight(), this.p.a.a(14).d(), BaseScreen.getSmallFont(), this.o.R.b);
        EngineUtils.c(this.p.a.a(14).e());
        this.p.a.a((int)14).h().a = EngineUtils.d(1);
        game.WorldManager.t = (byte)n2;
        game.WorldManager.s = (byte)-1;
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
                this.p.a.a((int)12).h().a = text;
                return;
            }
            case 1: {
                this.p.a.a(12).a(false);
                this.p.a.a((int)13).h().a = text;
            }
        }
    }

    public final void b(int n2) {
        this.p.a.a((int)14).h().a = EngineUtils.d(n2);
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

    public final void a(int n2, int n3, String[] strArray, String text) {
        this.b = 0;
        this.p.a(this.N[n2], 257, this);
        ((MenuWidget)this.p.a.a((int)0)).a.a = n3;
        switch (n2) {
            case 0: {
                for (n2 = 0; n2 < strArray.length; ++n2) {
                    this.p.a.a((int)(n2 + 12)).h().a = strArray[n2];
                }
                return;
            }
            case 1: {
                this.p.a.a((int)5).h().a = text;
                for (n2 = 0; n2 < strArray.length; ++n2) {
                    this.p.a.a((int)(9 + (n2 << 2))).h().a = strArray[n2];
                }
                return;
            }
            case 2: {
                this.p.a.a(10).a(false);
                this.p.a.a((int)8).h().a = "Trò chơi";
                this.p.a.a((int)9).h().a = "Xác nhận";
                for (n2 = 0; n2 < strArray.length; ++n2) {
                    this.p.a.a((int)(n2 + 5)).h().a = strArray[n2];
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
            ScriptEngine h2 = this;
            h2.p.a(h2.N[n3]);
            return this.b;
        }
        return -1;
    }

    public final void a(int[] intArray, int[] nArray2, String[] strArray, String[] stringArray2) {
        int n2;
        this.b = 0;
        this.p.a("/data/ui/taskOption.ui", 257, this);
        for (n2 = 0; n2 < stringArray2.length; ++n2) {
            this.p.a.a((int)(n2 + 17)).h().a = stringArray2[n2];
        }
        block10: for (n2 = 0; n2 < intArray.length; ++n2) {
            if (this.p.a.a((int)((n2 << 1) + 13)).h().m == null) {
                this.p.a.a((int)((n2 << 1) + 13)).h().m = new SpriteWidget();
                this.p.a.a((int)((n2 << 1) + 13)).h().m.a = (byte)2;
                if (intArray[n2] < 3 || intArray[n2] >= 5) {
                    this.p.a.a((int)((n2 << 1) + 13)).h().m.a(0);
                    this.p.a.a((int)((n2 << 1) + 13)).h().m.a(258, false, (byte)0);
                } else {
                    this.p.a.a((int)((n2 << 1) + 13)).h().m.a(-1);
                    this.p.a.a((int)((n2 << 1) + 13)).h().m.a(257, false, (byte)0);
                }
            }
            switch (intArray[n2]) {
                case 0: {
                    this.p.a.a((int)((n2 << 1) + 13)).h().m.a(GameDatabase.gameDatabase[4][intArray2[n2]][1]);
                    this.p.a.a((int)((n2 << 1) + 14)).h().a = strArray[n2];
                    continue block10;
                }
                case 1: {
                    this.p.a.a((int)((n2 << 1) + 13)).h().m.a(GameDatabase.gameDatabase[3][intArray2[n2]][1]);
                    this.p.a.a((int)((n2 << 1) + 14)).h().a = strArray[n2];
                    continue block10;
                }
                case 2: {
                    this.p.a.a((int)((n2 << 1) + 13)).h().m.a(GameDatabase.gameDatabase[5][intArray2[n2]][1]);
                    this.p.a.a((int)((n2 << 1) + 14)).h().a = strArray[n2];
                    continue block10;
                }
                case 3: {
                    this.p.a.a((int)((n2 << 1) + 13)).h().m.a(84);
                    this.p.a.a((int)((n2 << 1) + 14)).h().a = strArray[n2];
                    continue block10;
                }
                case 4: {
                    this.p.a.a((int)((n2 << 1) + 13)).h().m.a(83);
                    this.p.a.a((int)((n2 << 1) + 14)).h().a = strArray[n2];
                    continue block10;
                }
                case 5: {
                    continue block10;
                }
                case 6: {
                    this.p.a.a((int)21).h().a = "#2" + BaseScreen.f(GameDatabase.spriteTable((byte)0, (short)intArray2[n2], (byte)0)) + " #0" + strArray[n2];
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
            if (this.o.k(262144 /* MASK_SOFT_RIGHT */)) {
                this.p.a("/data/ui/taskOption.ui");
                return 1;
            }
        }
        return -1;
    }

    public final void aE() {
        this.bw();
        this.c("Có dùng 10000 kim tiền để khôi phục trạng thái của tất cả sủng vật trong ba lô không?", "Tại chỗ sống lại");
    }

    public final void aF() {
        if (this.o.k(196640)) {
            if (this.f == 0) {
                if (this.q.t(10000)) {
                    this.q.s(-10000);
                    for (int i = 0; i < this.q.A; ++i) {
                        this.q.z[i].I();
                        this.q.z[i].u(this.q.z[i].d[1]);
                    }
                    game.BattleScreen.getInstance().c();
                    this.o.a((byte)0);
                    this.bx();
                    return;
                }
                this.E();
                this.a("Kim tiền chưa đủ", "Nhấn nút 5 để tiếp tục");
                this.f = 1;
                return;
            }
            for (int i3 = 0; i3 < this.q.A; ++i3) {
                this.q.z[i3].l(1);
                this.q.z[i3].u(1);
                this.q.z[i3].c();
            }
            if (BaseScreen.X) {
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
        if (game.WorldManager.getInstance().f == 9 && game.WorldManager.getInstance().g != 0) {
            n2 = (byte)game.WorldManager.getInstance().g;
        }
        if (n2 == -1) {
            game.WorldManager.getInstance();
            if (game.WorldManager.i()) {
                game.WorldManager.getInstance().f();
                this.q.y = false;
                game.GameStateController.getInstance().a((byte)9);
                return;
            }
            game.GameStateController.getInstance().a((byte)7);
            return;
        }
        if (game.WorldManager.getInstance().g == 0) {
            short[] shortArray = new short[]{15, 194, 433, 16, 142, 357, 17, 97, 268, 18, 183, 224};
            for (int i = 0; i < game.WorldManager.getInstance().d.length; ++i) {
                for (int i3 = 0; i3 < shortArray.length / 3; ++i3) {
                    if (game.WorldManager.getInstance().d[i].I != shortArray[i3 * 3]) continue;
                    game.WorldManager.getInstance().d[i].b(shortArray[i3 * 3 + 1], shortArray[i3 * 3 + 2]);
                }
            }
        }
        game.WorldManager.u = (short)-1;
        for (int i4 = 0; i4 < this.q.A; ++i4) {
            this.q.z[i4].l(1);
            this.q.z[i4].u(1);
            this.q.z[i4].c();
        }
        game.WorldManager.getInstance().f = this.m[n2 << 2];
        game.WorldManager.getInstance().g = this.m[(n2 << 2) + 1];
        game.Player.getInstance().b(this.m[(n2 << 2) + 2], this.m[(n2 << 2) + 3]);
        game.Player.getInstance().b.b(this.m[(n2 << 2) + 2], this.m[(n2 << 2) + 3]);
        game.Player.getInstance().n = (byte)2;
        game.GameStateController.getInstance().a((byte)10);
    }

    public final void aG() {
        this.o.c((byte)0);
        this.o.d((byte)0);
        this.bw();
        Object object = new int[]{4, 1, 4};
        object = BaseScreen.f(599) + BaseScreen.a(604, object);
        this.c((String)object, "Kích hoạt");
    }

    public final void aH() {
        this.o.c((byte)1);
        this.o.d((byte)0);
        this.bw();
        Object object = new int[]{2, 1, 2};
        object = BaseScreen.f(600) + BaseScreen.a(604, object);
        this.c((String)object, "Mua sắm tất trúng cầu");
    }

    public final void aI() {
        this.f = 0;
        this.o.c((byte)4);
        this.o.d((byte)0);
        this.bw();
        Object object = new int[]{2, 1, 2};
        object = BaseScreen.f(603) + BaseScreen.a(604, object);
        this.c((String)object, "Mua sắm huy hiệu");
    }

    public final void aJ() {
        this.f = 0;
        this.o.c((byte)2);
        this.o.d((byte)0);
        this.bw();
        Object object = new int[]{2, 1, 2};
        object = BaseScreen.f(601) + BaseScreen.a(604, object);
        this.c((String)object, "Mua sắm kim tiền");
    }

    private void bw() {
        this.p.a("/data/ui/smsInfo.ui", 257, this);
        if (this.o instanceof WorldManager) {
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
        this.p.a.a((int)10).h().a = "Xác nhận";
        this.p.a.a((int)11).h().a = "Phản hồi";
    }

    private void c(String text, String string2) {
        this.p.a.a((int)8).h().a = text;
        this.p.a.a((int)5).h().a = string2;
    }

    private void bx() {
        this.p.a("/data/ui/smsInfo.ui");
    }

    public final void aK() {
        if (!this.p.c("/data/ui/smsTip.ui")) {
            this.p.a("/data/ui/smsTip.ui", 257, this);
        }
        for (int i = 0; i < 3; ++i) {
            this.p.a.a(i + 6).a(false);
        }
        this.g = true;
    }

    public final void d(String text) {
        this.g = true;
        this.p.a.a((int)5).h().a = text;
    }

    public final void aL() {
        this.p.a("/data/ui/smsTip.ui");
    }

    public final void aM() {
        switch (this.o.N()) {
            case 0: {
                if (this.o.k(16400) || this.o.k(32832)) break;
                if (this.o.k(131072 /* MASK_SOFT_LEFT */)) {
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
                if (this.o.k(131072 /* MASK_SOFT_LEFT */)) {
                    this.o.h(1);
                    return;
                }
                if (!this.o.k(262144 /* MASK_SOFT_RIGHT */)) break;
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

    public final void a(byte val, int n2, int n3) {
        this.c = 0;
        this.R = val;
        this.S = (byte)n2;
        switch (n2) {
            case 0: {
                this.p.a("/data/ui/wharf1.ui", 257, this);
                this.p.a.a((int)8).h().a = BaseScreen.f(n3);
                for (n2 = 0; n2 < this.P[val].length; ++n2) {
                    this.p.a.a((int)(n2 + 5)).h().a = BaseScreen.f(this.P[val][n2]);
                }
                break;
            }
            case 1: {
                this.p.a("/data/ui/wharf2.ui", 257, this);
                this.p.a.a((int)10).h().a = BaseScreen.f(n3);
                for (n2 = 0; n2 < this.P[val].length; ++n2) {
                    this.p.a.a((int)(n2 + 5)).h().a = BaseScreen.f(this.P[val][n2]);
                }
                break;
            }
        }
        this.p.a.a((int)(5 + this.P[val].length)).h().a = "Không ra hàng";
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
                if (game.WorldManager.u != -1 && game.WorldManager.getInstance().d[game.WorldManager.u].u() == 0) {
                    game.WorldManager.getInstance().a(game.WorldManager.getInstance().d[game.WorldManager.u].i, game.WorldManager.getInstance().d[game.WorldManager.u].j - 40, game.WorldManager.getInstance().d[game.WorldManager.u]);
                }
            } else if (game.WorldManager.getInstance().M.b[game.WorldManager.a((int)this.Q[this.R][this.c * 9 + 6], (int)this.Q[this.R][this.c * 9 + 7])] != null && game.WorldManager.getInstance().M.b[game.WorldManager.a((int)this.Q[this.R][this.c * 9 + 6], (int)this.Q[this.R][this.c * 9 + 7])][this.Q[this.R][this.c * 9 + 8]] == 3) {
                game.WorldManager.getInstance().f = this.Q[this.R][this.c * 9];
                game.WorldManager.getInstance().g = this.Q[this.R][this.c * 9 + 1];
                game.WorldManager.getInstance().h = this.Q[this.R][this.c * 9 + 2];
                game.WorldManager.getInstance().i = this.Q[this.R][this.c * 9 + 3];
                game.WorldManager.w = (byte)this.Q[this.R][this.c * 9 + 4];
                game.WorldManager.getInstance().j = -1;
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
                this.b("Đường thủy chưa mở");
            }
        } else if (this.o.k(262144 /* MASK_SOFT_RIGHT */) && !this.j()) {
            if (game.WorldManager.u != -1 && game.WorldManager.getInstance().d[game.WorldManager.u].u() == 0) {
                game.WorldManager.getInstance().a(game.WorldManager.getInstance().d[game.WorldManager.u].i, game.WorldManager.getInstance().d[game.WorldManager.u].j - 40, game.WorldManager.getInstance().d[game.WorldManager.u]);
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
        ((MenuWidget)this.p.a.a((int)0)).a.a = 1;
        ((MenuWidget)this.p.a.a((int)0)).a.a(0);
        this.p.a.a(41).a(false);
        this.p.a.a(43).a(false);
        this.p.a.a((int)5).h().a = "Mua";
        this.p.a.a(57).a(true);
        this.p.a.a(58).a(true);
        this.p.a.a((int)57).h().a = "Mua sắm";
        this.p.a.a((int)58).h().a = "Phản hồi";
        this.p.a.a(39).a(false);
        this.p.a.a(40).a(false);
        ScriptEngine h2 = this;
        h2.w = ((MenuWidget)h2.p.a.a((int)0)).a.e;
        h2.h = ((MenuWidget)h2.p.a.a((int)0)).a.f;
        if (h2.p.a.a((int)51).h().m == null) {
            h2.p.a.a((int)51).h().m = new SpriteWidget();
            h2.p.a.a((int)51).h().m.a(0);
            h2.p.a.a((int)51).h().m.a = (byte)2;
            h2.p.a.a((int)51).h().m.a(258, false, (byte)-1);
        }
        h2.p.a.a((int)51).h().m.a(GameDatabase.gameDatabase[5][0][1]);
        h2.p.a.a((int)14).h().a = BaseScreen.f(GameDatabase.gameDatabase[5][0][0]);
        h2.p.a.a((int)15).h().a = "5000";
        h2.p.a.a((int)45).h().m.a(84);
        h2.p.a.a((int)56).h().a = "Ấp trứng ra sủng vật";
        h2.p.a.a((int)44).h().a = "" + h2.q.E();
        h2.p.a.a(38).b(102 + h2.h * 84 / GameDatabase.gameDatabase[5].length, h2.p.a.a());
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
                        this.a("Đã có trứng sủng vật, không cần mua sắm", "Nhấn nút 5 để tiếp tục");
                        this.f = 2;
                    } else {
                        this.q.e(0, -1);
                        this.E();
                        this.a("Đã thành công mua sắm #2 trứng sủng vật", "Nhấn nút 5 để tiếp tục");
                        this.f = 2;
                    }
                } else {
                    this.E();
                    this.a("Kim tiền chưa đủ", "Nhấn nút 5 để tiếp tục");
                    this.f = 1;
                }
            } else if (this.f > 0) {
                this.F();
                if (this.f == 1) {
                    this.o.a((byte)102);
                } else if (this.f == 2) {
                    game.OverworldScreen.m = 0;
                    this.by();
                    this.o.a((byte)0);
                }
            }
        } else if (this.o.k(262144 /* MASK_SOFT_RIGHT */) && this.f == 0) {
            game.OverworldScreen.m = 1;
            this.by();
            this.o.a((byte)0);
        }
        return -1;
    }

    public final void aQ() {
        this.p.a("/data/ui/wharf2.ui", 257, this);
        ((MenuWidget)this.p.a.a((int)0)).a.f = this.d;
        this.f = 0;
        this.p.a.a((int)10).h().a = "Tiện lợi điếm";
        this.p.a.a((int)12).h().a = "Tiến vào";
        for (int i = 0; i < this.T.length; ++i) {
            this.p.a.a((int)(i + 5)).h().a = this.T[i];
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
                        game.WorldManager.getInstance();
                        if (game.WorldManager.K) {
                            this.p.a("/data/ui/wharf2.ui");
                            this.c = 0;
                            this.o.a((byte)7);
                            return;
                        }
                        this.E();
                        this.f = 1;
                        this.a("Công năng theo đạo học sau mở ra", "Nhấn nút 5 để tiếp tục");
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
        if (this.f == 0 && this.o.k(262144 /* MASK_SOFT_RIGHT */) && !this.j()) {
            this.p.a("/data/ui/wharf2.ui");
            this.o.a((byte)0);
        }
    }

    public final void a(int[] intArray) {
        this.z = intArray;
        if (this.o instanceof WorldManager) {
            switch (((WorldManager)this.o).P) {
                case 0: {
                    return;
                }
                case 1: {
                    int[] intArray2 = intArray;
                    Object var2_22 = null;
                    this.b = intArray2[0];
                    return;
                }
                case 2: 
                case 26: 
                case 32: {
                    this.d(intArray);
                    return;
                }
                case 3: {
                    int[] nArray3 = intArray;
                    ScriptEngine h2 = this;
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
                    int[] nArray4 = intArray;
                    ScriptEngine h3 = this;
                    this.b = nArray4[1];
                    h3.bm();
                    return;
                }
                case 6: {
                    int[] nArray5 = intArray;
                    ScriptEngine h4 = this;
                    this.b = nArray5[0];
                    if (BaseScreen.X) {
                        h4.p.a.a((int)14).h().a = BaseScreen.f(605 + h4.b);
                        return;
                    }
                    h4.p.a.a((int)14).h().a = BaseScreen.f(606 + h4.b);
                    return;
                }
                case 7: {
                    this.c(intArray);
                    return;
                }
                case 8: {
                    int[] nArray6 = intArray;
                    ScriptEngine h5 = this;
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
                    int[] nArray7 = intArray;
                    Object var2_27 = null;
                    this.c = nArray7[1];
                    return;
                }
                case 10: {
                    int[] nArray8 = intArray;
                    ScriptEngine h6 = this;
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
                    int[] nArray9 = intArray;
                    ScriptEngine h7 = this;
                    this.c = nArray9[0];
                    h7.b = nArray9[1];
                    return;
                }
                case 12: {
                    int[] nArray10 = intArray;
                    Object var2_30 = null;
                    this.b = nArray10[1];
                    return;
                }
                case 13: {
                    if (this.f == 0) {
                        this.b = intArray[0];
                        return;
                    }
                    this.c = intArray[0];
                    return;
                }
                case 14: {
                    int[] nArray11 = intArray;
                    Object var2_31 = null;
                    this.c = nArray11[0];
                    return;
                }
                case 15: {
                    int[] nArray12 = intArray;
                    Object var2_32 = null;
                    this.c = nArray12[0];
                    return;
                }
                case 16: {
                    int[] nArray13 = intArray;
                    Object var2_33 = null;
                    this.b = nArray13[0];
                    return;
                }
                case 17: 
                case 18: 
                case 19: {
                    int[] nArray14 = intArray;
                    Object var2_34 = null;
                    this.c = nArray14[0];
                    return;
                }
                case 20: {
                    this.c = intArray[1];
                    return;
                }
                case 24: {
                    int[] nArray15 = intArray;
                    Object var2_35 = null;
                    this.c = nArray15[0];
                    return;
                }
                case 28: {
                    int[] nArray16 = intArray;
                    Object var2_36 = null;
                    this.c = nArray16[0];
                    return;
                }
                case 27: {
                    this.d = intArray[0];
                }
            }
            return;
        }
        if (this.o instanceof BattleScreen) {
            switch (((BattleScreen)this.o).P) {
                case 2: {
                    return;
                }
                case 3: {
                    int[] nArray17 = intArray;
                    Object var2_37 = null;
                    this.e = nArray17[0];
                    return;
                }
                case 4: {
                    int[] nArray18 = intArray;
                    ScriptEngine h8 = this;
                    this.b = nArray18[0];
                    h8.be();
                    return;
                }
                case 5: {
                    this.c(intArray);
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
                    this.d(intArray);
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
                    int[] nArray19 = intArray;
                    ScriptEngine h9 = this;
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
                    int[] nArray20 = intArray;
                    ScriptEngine h10 = this;
                    this.a = nArray20[1];
                    h10.a(true);
                    return;
                }
                case 21: {
                    int[] nArray21 = intArray;
                    Object var2_41 = null;
                    this.b = nArray21[0];
                }
                case 23: {
                    int[] nArray21 = intArray;
                    this.b = intArray[0];
                }
            }
        }
    }

    private void c(int[] intArray) {
        if (this.f == 0) {
            this.b = intArray[0];
            this.f(this.b);
            return;
        }
        if (this.f == 1) {
            this.c = intArray[0];
            return;
        }
        if (this.f == 2) {
            this.r = intArray[0];
            switch (this.c) {
                case 0: {
                    this.be();
                }
            }
        }
    }

    private void d(int[] intArray) {
        if (this.f == 0) {
            this.b = intArray[0];
            return;
        }
        this.r = intArray[0];
    }

    static {
        A = new String[]{"Thủy Kimura", "Bích Thủy thành", "Nguyên Mộc Thành", "Niêm Thổ Thành", "Hắc Thạch thành", "Thiên không", "Xa cổ"};
        B = new short[]{1, 0, 196, 208, 0, 2, 1, 196, 208, 0, 3, 3, 196, 208, 0, 4, 5, 320, 352, 0, 5, 3, 320, 196, 0, 7, 2, 288, 112, 0, 8, 0, 160, 144, 0};
    }
}

