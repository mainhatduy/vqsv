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
    private static ScriptEngine instance;
    private BaseScreen parentScreen;
    private UIManager uiManager = UIManager.getInstance();
    private Player player;
    protected int a;
    protected int selectedSubMenuIndex;
    protected int selectedShopIndex;
    private int r;
    protected int d;
    protected int e;
    private int s;
    protected int dialogSubState;
    protected boolean isDialogActive;
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
        if (instance == null) {
            instance = new ScriptEngine();
        }
        return instance;
    }

    public ScriptEngine() {
        if (this.player == null) {
            this.player = game.Player.getInstance();
        }
    }

    public final void b() {
        instance = null;
        this.player = null;
    }

    public final void a(BaseScreen an2) {
        if (this.parentScreen != null) {
            this.parentScreen = null;
        }
        this.parentScreen = an2;
        this.isDialogActive = true;
    }

    public final void c() {
        this.uiManager.openUI("/data/ui/world.ui", 257, this);
        this.u = 0;
    }

    public final void d() {
        this.uiManager.activeView.a(5).a(true);
        this.uiManager.activeView.a(7).a(true);
    }

    private void aS() {
        if (this.uiManager.isUILoaded("/data/ui/world.ui")) {
            for (int i = 1; i <= 7; ++i) {
                if (i == 2 || i == 3 || i == 4) continue;
                this.uiManager.activeView.a(i).a(false);
            }
        }
    }

    public final void e() {
        if (this.u < 2 && !game.OverworldScreen.f && game.WorldManager.J && this.uiManager.isUIOpen("/data/ui/world.ui")) {
            if (UIManager.a(this.uiManager.d("/data/ui/world.ui"), 4)) {
                ((ItemListWidget)this.uiManager.d((String)"/data/ui/world.ui").a((int)6)).h().a = ((WorldManager)this.parentScreen).k;
                this.u = 1;
            } else if (this.u == 1 && ((ItemListWidget)this.uiManager.d((String)"/data/ui/world.ui").a((int)1)).h().m.a().h() >= 5) {
                ((ItemListWidget)this.uiManager.d((String)"/data/ui/world.ui").a((int)6)).h().a = "";
                this.u = (byte)2;
                game.WorldManager.J = false;
            }
        }
        this.f();
    }

    public final boolean f() {
        if (this.v < 2 && this.uiManager.isUILoaded("/data/ui/openbox.ui")) {
            if (UIManager.a(this.uiManager.activeView, 3) && this.uiManager.activeView.a((int)1).h().m.a().g() == 9) {
                this.uiManager.activeView.a((int)2).h().a = this.t;
                if (this.uiManager.activeView.a(2).h().b()) {
                    if (this.uiManager.d()) {
                        this.uiManager.activeView.a((int)1).h().m.a((byte)12, (byte)-1);
                        this.v = 1;
                    }
                    if (this.parentScreen.isKeyPressed(196640)) {
                        this.uiManager.activeView.a((int)2).h().a = "";
                        this.v = (byte)2;
                        this.isDialogActive = true;
                        this.aw();
                        return true;
                    }
                }
            } else if (this.v == 1) {
                this.uiManager.activeView.a((int)2).h().a = "";
                if (this.uiManager.d()) {
                    this.v = (byte)2;
                    this.isDialogActive = true;
                    this.aw();
                    return true;
                }
            }
        }
        return this.g();
    }

    public final boolean g() {
        if (this.v < 2) {
            if (this.uiManager.isUILoaded("/data/ui/taskTip.ui")) {
                if (UIManager.a(this.uiManager.activeView, 4) && this.uiManager.activeView.a((int)1).h().m.a().g() == 10) {
                    this.uiManager.activeView.a((int)2).h().a = this.t;
                    if (this.uiManager.activeView.a(2).h().b()) {
                        if (this.uiManager.d()) {
                            this.uiManager.activeView.a((int)1).h().m.a((byte)13, (byte)-1);
                            this.v = 1;
                        }
                        if (this.parentScreen.isKeyPressed(196640)) {
                            this.v = (byte)2;
                            this.isDialogActive = true;
                            this.br();
                            return true;
                        }
                    }
                } else if (this.v == 1) {
                    this.uiManager.activeView.a((int)2).h().a = "";
                    if (this.uiManager.d()) {
                        this.isDialogActive = true;
                        this.v = (byte)2;
                        this.br();
                        return true;
                    }
                }
            }
            this.isDialogActive = true;
        }
        return false;
    }

    public final void h() {
        this.uiManager.openUI("/data/ui/transmit.ui", 257, this);
        ((MenuWidget)this.uiManager.activeView.a((int)0)).a.a = A.length;
        ((MenuWidget)this.uiManager.activeView.a((int)0)).a.a(1);
        this.aT();
    }

    private void aT() {
        this.w = ((MenuWidget)this.uiManager.activeView.a((int)0)).a.e;
        this.h = ((MenuWidget)this.uiManager.activeView.a((int)0)).a.f;
        for (int i = 0; i < 5; ++i) {
            this.uiManager.activeView.a((int)(i + 5)).h().a = A[i + this.w];
        }
        this.uiManager.activeView.a(13).b(109 + this.h * 88 / A.length, this.uiManager.activeView.a());
    }

    public final void i() {
        if (this.parentScreen.isKeyPressed(4100)) {
            this.uiManager.activeView.navigateSelection(0);
            this.aT();
            return;
        }
        if (this.parentScreen.isKeyPressed(8448)) {
            this.uiManager.activeView.navigateSelection(1);
            this.aT();
            return;
        }
        if (this.parentScreen.isKeyPressed(196640)) {
            game.WorldManager.getInstance().f = B[this.h * 5];
            game.WorldManager.getInstance().g = B[this.h * 5 + 1];
            game.WorldManager.getInstance().playerSpawnX = B[this.h * 5 + 2];
            game.WorldManager.getInstance().playerSpawnY = B[this.h * 5 + 3];
            game.WorldManager.w = (byte)B[this.h * 5 + 4];
            game.WorldManager.getInstance().j = -1;
            game.GameStateController.getInstance().a((byte)9);
            return;
        }
        if (this.parentScreen.isKeyPressed(262144 /* MASK_SOFT_RIGHT */)) {
            this.parentScreen.setScreenMode((byte)8);
            this.uiManager.closeUI("/data/ui/transmit.ui");
        }
    }

    public final boolean j() {
        return this.uiManager.isUILoaded("/data/ui/openbox.ui") || this.uiManager.isUILoaded("/data/ui/taskTip.ui");
    }

    public final void k() {
        String[] strArray = new String[]{"Tùy thân cửa hàng", "Sủng vật", "Lưng bao", "Đồ giám", "Nhiệm vụ", "Lưu dữ liệu"};
        this.aS();
        this.uiManager.openUI("/data/ui/gamemenu.ui", 257, this);
        if (BaseScreen.isVipUnlocked) {
            ((MenuWidget)this.uiManager.activeView.a((int)0)).a.a = 6;
            this.uiManager.activeView.a((int)14).h().a = BaseScreen.f(605 + this.selectedSubMenuIndex);
            this.uiManager.activeView.a((int)15).h().a = strArray[0];
            for (int i = 0; i < 5; ++i) {
                this.uiManager.activeView.a((int)(i + 5)).h().a = strArray[i + 1];
            }
        } else {
            this.uiManager.activeView.a((int)14).h().a = BaseScreen.f(606 + this.selectedSubMenuIndex);
            ((MenuWidget)this.uiManager.activeView.a((int)0)).a.a = 5;
            this.uiManager.activeView.a((int)15).h().a = strArray[1];
            for (int i3 = 0; i3 < 4; ++i3) {
                this.uiManager.activeView.a((int)(i3 + 5)).h().a = strArray[i3 + 2];
            }
            this.uiManager.activeView.a(9).a(false);
        }
        ((MenuWidget)this.uiManager.activeView.a((int)0)).a.f = this.selectedSubMenuIndex;
        this.uiManager.activeView.a((int)18).h().a = "" + this.player.G();
        this.uiManager.activeView.a((int)19).h().a = "" + this.player.E();
        this.dialogSubState = 0;
    }

    public final void l() {
        this.parentScreen.isKeyHeld();
        if (!BaseScreen.b(this.selectedSubMenuIndex, 0) && !this.j() && this.parentScreen.isKeyPressed(4100)) {
            this.uiManager.activeView.navigateSelection(0);
        } else if (!BaseScreen.b(this.selectedSubMenuIndex, 0) && !this.j() && this.parentScreen.isKeyPressed(8448)) {
            this.uiManager.activeView.navigateSelection(1);
        } else if (!this.j() && BaseScreen.I() && this.parentScreen.isKeyPressed(196640)) {
            if (BaseScreen.H() && !BaseScreen.b(this.selectedSubMenuIndex, 0)) {
                return;
            }
            if (BaseScreen.isVipUnlocked) {
                switch (this.selectedSubMenuIndex) {
                    case 0: {
                        this.parentScreen.setScreenMode((byte)14);
                        this.uiManager.closeUI("/data/ui/gamemenu.ui");
                        break;
                    }
                    case 1: {
                        this.selectedShopIndex = 0;
                        this.parentScreen.m();
                        this.parentScreen.setScreenMode((byte)7);
                        this.uiManager.closeUI("/data/ui/gamemenu.ui");
                        break;
                    }
                    case 2: {
                        this.parentScreen.m();
                        this.parentScreen.setScreenMode((byte)8);
                        this.uiManager.closeUI("/data/ui/gamemenu.ui");
                        break;
                    }
                    case 3: {
                        this.selectedShopIndex = 0;
                        this.parentScreen.setScreenMode((byte)9);
                        this.uiManager.closeUI("/data/ui/gamemenu.ui");
                        break;
                    }
                    case 4: {
                        this.selectedSubMenuIndex = 0;
                        this.parentScreen.setScreenMode((byte)10);
                        this.uiManager.closeUI("/data/ui/gamemenu.ui");
                        break;
                    }
                    case 5: {
                        this.uiManager.activeView.a(11).a(false);
                        this.uiManager.activeView.a(12).a(false);
                        this.parentScreen.setScreenMode((byte)22);
                    }
                }
            } else {
                switch (this.selectedSubMenuIndex) {
                    case 0: {
                        this.selectedShopIndex = 0;
                        this.parentScreen.m();
                        this.parentScreen.setScreenMode((byte)7);
                        this.uiManager.closeUI("/data/ui/gamemenu.ui");
                        break;
                    }
                    case 1: {
                        this.parentScreen.m();
                        this.parentScreen.setScreenMode((byte)8);
                        this.uiManager.closeUI("/data/ui/gamemenu.ui");
                        break;
                    }
                    case 2: {
                        this.selectedShopIndex = 0;
                        this.parentScreen.setScreenMode((byte)9);
                        this.uiManager.closeUI("/data/ui/gamemenu.ui");
                        break;
                    }
                    case 3: {
                        this.selectedSubMenuIndex = 0;
                        this.parentScreen.setScreenMode((byte)10);
                        this.uiManager.closeUI("/data/ui/gamemenu.ui");
                        break;
                    }
                    case 4: {
                        this.uiManager.activeView.a(11).a(false);
                        this.uiManager.activeView.a(12).a(false);
                        this.parentScreen.setScreenMode((byte)22);
                    }
                }
            }
        } else if (BaseScreen.J() && this.parentScreen.isKeyPressed(262144 /* MASK_SOFT_RIGHT */)) {
            this.uiManager.closeUI("/data/ui/gamemenu.ui");
            this.parentScreen.setScreenMode((byte)0);
        }
        this.g();
    }

    public final void m() {
        this.aS();
        this.uiManager.openUI("/data/ui/gamesystem.ui", 257, this);
        ((MenuWidget)this.uiManager.activeView.a((int)0)).a.f = this.selectedSubMenuIndex;
        this.dialogSubState = 0;
    }

    public final void n() {
        if (this.parentScreen.isKeyPressed(4100)) {
            this.uiManager.activeView.navigateSelection(0);
            return;
        }
        if (this.parentScreen.isKeyPressed(8448)) {
            this.uiManager.activeView.navigateSelection(1);
            return;
        }
        if (this.parentScreen.isKeyPressed(196640)) {
            switch (this.selectedSubMenuIndex) {
                case 0: {
                    this.uiManager.closeUI("/data/ui/gamesystem.ui");
                    this.parentScreen.setScreenMode((byte)0);
                    return;
                }
                case 1: {
                    this.parentScreen.setScreenMode((byte)20);
                    this.uiManager.closeUI("/data/ui/gamesystem.ui");
                    return;
                }
                case 2: {
                    this.parentScreen.setScreenMode((byte)21);
                    this.uiManager.closeUI("/data/ui/gamesystem.ui");
                    return;
                }
                case 3: {
                    if (this.dialogSubState == 0) {
                        this.uiManager.openUI("/data/ui/option.ui", 257, this);
                        ((MenuWidget)this.uiManager.activeView.a((int)0)).a.f = this.selectedShopIndex = 1;
                        this.uiManager.activeView.a((int)12).h().a = "";
                        this.uiManager.activeView.a((int)13).h().a = "Không";
                        this.dialogSubState = 1;
                        return;
                    }
                    switch (this.selectedShopIndex) {
                        case 1: {
                            this.uiManager.closeUI("/data/ui/option.ui");
                            this.dialogSubState = 0;
                            this.isDialogActive = true;
                            return;
                        }
                        case 0: {
                            game.GameStateController.getInstance().b = 0L;
                            game.GameStateController.getInstance().a = 0L;
                            game.Player.getInstance().y = false;
                            game.GameStateController.getInstance().a((byte)7);
                            this.uiManager.closeUI("/data/ui/gamesystem.ui");
                        }
                    }
                }
            }
            return;
        }
        if (this.parentScreen.isKeyPressed(262144 /* MASK_SOFT_RIGHT */)) {
            if (this.dialogSubState == 0) {
                this.uiManager.closeUI("/data/ui/gamesystem.ui");
                this.parentScreen.setScreenMode((byte)0);
                return;
            }
            if (this.dialogSubState == 1) {
                this.isDialogActive = true;
                this.uiManager.closeUI("/data/ui/option.ui");
                this.dialogSubState = 0;
            }
        }
    }

    public final void o() {
        this.uiManager.openUI("/data/ui/help1.ui", 257, this);
        this.selectedSubMenuIndex = 0;
        this.uiManager.activeView.a(6).a(true);
        this.uiManager.activeView.a(7).a(false);
        this.d(this.selectedSubMenuIndex);
    }

    private void d(int n2) {
        if (n2 == 0) {
            this.uiManager.activeView.a((int)5).h().a = "Trợ giúp";
            this.uiManager.activeView.a((int)8).h().a = "Nhấn nút 2, 4, 6, 8 để di chuyển#nNút 5: công kích, đối thoại, xác nhận#nNút 1, 3: Xem nhiệm vụ#nNút 9: lựa chọn sủng vật cưỡi#nNút 0: Xem bản đồ#nNút mềm trái: menu hệ thống#nNút mềm phải: menu trò chơi";
            for (int i = 0; i < 28; ++i) {
                this.uiManager.activeView.a(i + 9).a(false);
            }
        } else if (n2 > 0) {
            this.uiManager.activeView.a((int)8).h().a = "";
            for (int i3 = 0; i3 < 14; ++i3) {
                this.uiManager.activeView.a(9 + (i3 << 1)).a(true);
                this.uiManager.activeView.a(9 + (i3 << 1) + 1).a(true);
                if ((n2 - 1) * 14 + i3 < 26) {
                    this.uiManager.activeView.a((int)(9 + (i3 << 1))).h().m = new SpriteWidget();
                    this.uiManager.activeView.a((int)(9 + (i3 << 1))).h().m.a(0);
                    this.uiManager.activeView.a((int)(9 + (i3 << 1))).h().m.a = (byte)2;
                    this.uiManager.activeView.a((int)(9 + (i3 << 1))).h().m.a(325, false, (byte)-2);
                    this.uiManager.activeView.a((int)(9 + (i3 << 1))).h().m.a((n2 - 1) * 14 + i3 + 1);
                    if ((n2 - 1) * 14 + i3 <= 10) {
                        this.uiManager.activeView.a((int)(9 + (i3 << 1) + 1)).h().a = BaseScreen.f(i3 + 311);
                        continue;
                    }
                    this.uiManager.activeView.a((int)(9 + (i3 << 1) + 1)).h().a = BaseScreen.f(333 + ((n2 - 1) * 14 + i3 - 11));
                    continue;
                }
                this.uiManager.activeView.a(9 + (i3 << 1)).a(false);
                this.uiManager.activeView.a(9 + (i3 << 1) + 1).a(false);
            }
        }
        this.uiManager.activeView.a((int)39).h().a = n2 + 1 + "/3";
    }

    public final void p() {
        if (this.parentScreen.isKeyPressed(16400)) {
            --this.selectedSubMenuIndex;
            if (this.selectedSubMenuIndex <= 0) {
                this.selectedSubMenuIndex = 0;
            }
            this.d(this.selectedSubMenuIndex);
            return;
        }
        if (this.parentScreen.isKeyPressed(32832)) {
            ++this.selectedSubMenuIndex;
            if (this.selectedSubMenuIndex >= 2) {
                this.selectedSubMenuIndex = 2;
            }
            this.d(this.selectedSubMenuIndex);
            return;
        }
        if (this.parentScreen.isKeyPressed(262144 /* MASK_SOFT_RIGHT */)) {
            this.parentScreen.setScreenMode((byte)0);
            this.uiManager.closeUI("/data/ui/help1.ui");
        }
    }

    public final void q() {
        this.uiManager.openUI("/data/ui/help.ui", 257, this);
        this.uiManager.activeView.a((int)5).h().a = "Quan tại";
        this.uiManager.activeView.a((int)8).h().a = "Tên trò chơi: Sủng vật Vương quốc - Liệt hỏa#nViệt hóa: BIGAME";
        this.uiManager.activeView.a(6).a(true);
        this.uiManager.activeView.a(7).a(false);
        for (int i = 9; i < 13; ++i) {
            this.uiManager.activeView.a(i).a(false);
        }
    }

    public final void r() {
        if (this.parentScreen.isKeyPressed(262144 /* MASK_SOFT_RIGHT */)) {
            this.parentScreen.setScreenMode((byte)0);
            this.uiManager.closeUI("/data/ui/help.ui");
        }
    }

    public final void s() {
        this.uiManager.openUI("/data/ui/help.ui", 257, this);
        this.uiManager.activeView.a((int)5).h().a = "Tùy chọn";
        this.uiManager.activeView.a((int)8).h().a = "";
        this.uiManager.activeView.a(6).a(false);
        this.uiManager.activeView.a(7).a(true);
        for (int i = 9; i < 13; ++i) {
            this.uiManager.activeView.a(i).a(true);
        }
        this.aU();
    }

    private void aU() {
        for (int i = 1; i < 4; ++i) {
            this.uiManager.activeView.a((int)(i + 9)).h().j = i <= game.GameStateController.getInstance().g ? -2148 : -8540732;
        }
    }

    public final void t() {
        if (this.parentScreen.isKeyPressed(16400)) {
            game.GameStateController.getInstance().i();
            this.aU();
            return;
        }
        if (this.parentScreen.isKeyPressed(32832)) {
            game.GameStateController.getInstance().h();
            this.aU();
            return;
        }
        if (this.parentScreen.isKeyPressed(131072 /* MASK_SOFT_LEFT */)) {
            this.parentScreen.setScreenMode((byte)0);
            this.uiManager.closeUI("/data/ui/help.ui");
        }
    }

    public final void u() {
        this.uiManager.openUI("/data/ui/help1.ui", 257, this);
        this.uiManager.closeUI("/data/ui/gamesystem.ui");
        this.r = 0;
        this.uiManager.activeView.a(6).a(true);
        this.uiManager.activeView.a(7).a(false);
        this.d(this.r);
    }

    public final void v() {
        if (this.parentScreen.isKeyPressed(16400)) {
            --this.r;
            if (this.r <= 0) {
                this.r = 0;
            }
            this.d(this.r);
            return;
        }
        if (this.parentScreen.isKeyPressed(32832)) {
            ++this.r;
            if (this.r >= 2) {
                this.r = 2;
            }
            this.d(this.r);
            return;
        }
        if (this.parentScreen.isKeyPressed(262144 /* MASK_SOFT_RIGHT */)) {
            this.parentScreen.setScreenMode((byte)13);
            this.uiManager.closeUI("/data/ui/help1.ui");
        }
    }

    public final void w() {
        this.s();
        this.uiManager.closeUI("/data/ui/gamesystem.ui");
    }

    public final void x() {
        if (this.parentScreen.isKeyPressed(16400)) {
            this.uiManager.activeView.navigateSelection(2);
            game.GameStateController.getInstance().i();
            this.aU();
            return;
        }
        if (this.parentScreen.isKeyPressed(32832)) {
            this.uiManager.activeView.navigateSelection(3);
            game.GameStateController.getInstance().h();
            this.aU();
            return;
        }
        if (this.parentScreen.isKeyPressed(131072 /* MASK_SOFT_LEFT */)) {
            this.parentScreen.setScreenMode((byte)13);
            this.uiManager.closeUI("/data/ui/help.ui");
        }
    }

    public final void y() {
        this.uiManager.openUI("/data/ui/petstate.ui", 257, this);
        this.dialogSubState = 0;
        if (this.player.O.size() > 6) {
            ((MenuWidget)this.uiManager.activeView.a((int)0)).a.a(1);
        } else {
            ((MenuWidget)this.uiManager.activeView.a((int)0)).a.a(-1);
        }
        this.uiManager.activeView.a((int)2).h().a = "Ngân hàng Sủng vật";
        this.uiManager.activeView.a(75).a(false);
        this.uiManager.activeView.a(76).a(false);
        this.aV();
    }

    private void aV() {
        ((MenuWidget)this.uiManager.activeView.a((int)0)).a.a = this.player.O.size();
        this.w = ((MenuWidget)this.uiManager.activeView.a((int)0)).a.e;
        this.h = ((MenuWidget)this.uiManager.activeView.a((int)0)).a.f;
        ((MenuWidget)this.uiManager.activeView.a((int)0)).a.d = this.player.O.size() >= 6 ? 6 : this.player.O.size();
        if (this.h >= this.player.O.size()) {
            ((MenuWidget)this.uiManager.activeView.a((int)0)).a.f = this.h = this.player.O.size() - 1;
        }
        if (this.w > 0 && this.h - this.w < 5) {
            --this.w;
            ((MenuWidget)this.uiManager.activeView.a((int)0)).a.e = this.w;
        }
        for (int i = 0; i < 6; ++i) {
            if (this.w + i < this.player.O.size()) {
                int[] intArray = (int[])this.player.O.elementAt(this.w + i);
                if (i == 0) {
                    this.uiManager.activeView.a((int)(14 + i * 6)).h().a = "" + (this.w + i + 1);
                } else {
                    this.uiManager.activeView.a((int)(15 + i * 6)).h().a = "" + (this.w + i + 1);
                }
                this.uiManager.activeView.a((int)(16 + i * 6)).h().a = "#P" + intArray[6] * 100 / game.Pet.a(intArray[0], intArray[1], intArray[4], 1);
                this.uiManager.activeView.a((int)(17 + i * 6)).h().a = "#P" + game.Pet.a((short)intArray[7], (short)intArray[1]);
                continue;
            }
            this.uiManager.activeView.a((int)(16 + i * 6)).h().a = "#P0";
            this.uiManager.activeView.a((int)(17 + i * 6)).h().a = "#P0";
        }
        int[] intArray = null;
        if (this.player.O.size() > 0) {
            intArray = (int[])this.player.O.elementAt(this.h);
        }
        if (intArray != null) {
            if (this.uiManager.activeView.a((int)48).h().m != null) {
                this.uiManager.activeView.a((int)48).h().m.d();
            } else {
                this.uiManager.activeView.a((int)48).h().m = new SpriteWidget();
                this.uiManager.activeView.a((int)48).h().m.a(0);
                this.uiManager.activeView.a((int)48).h().m.a = (byte)3;
            }
            this.uiManager.activeView.a((int)48).h().m.a(GameDatabase.spriteTable((byte)0, (short)intArray[0], (byte)17), false, (byte)-1);
            this.uiManager.activeView.a((int)51).h().a = BaseScreen.f(GameDatabase.spriteTable((byte)0, (short)intArray[0], (byte)0));
            this.uiManager.activeView.a((int)52).h().a = BaseScreen.f(365 + GameDatabase.spriteTable((byte)0, (short)intArray[0], (byte)1));
            if (GameDatabase.spriteTable((byte)0, (short)intArray[0], (byte)19) == -1) {
                this.uiManager.activeView.a((int)62).h().a = "";
            } else if (GameDatabase.gameDatabase[0][GameDatabase.spriteTable((byte)0, (short)intArray[0], (byte)19)][2] == 1 || GameDatabase.gameDatabase[0][GameDatabase.spriteTable((byte)0, (short)intArray[0], (byte)19)][2] == 2) {
                this.uiManager.activeView.a((int)62).h().a = "Có thể tiến hóa";
            } else if (GameDatabase.gameDatabase[0][GameDatabase.spriteTable((byte)0, (short)intArray[0], (byte)19)][2] == 3) {
                this.uiManager.activeView.a((int)62).h().a = "Có thể dị hoá";
            }
            this.uiManager.activeView.a((int)61).h().a = game.Pet.y(intArray[0]);
            if (this.uiManager.activeView.a((int)59).h().m == null) {
                this.uiManager.activeView.a((int)59).h().m = new SpriteWidget();
                this.uiManager.activeView.a((int)59).h().m.a(0);
                this.uiManager.activeView.a((int)59).h().m.a = (byte)2;
                this.uiManager.activeView.a((int)59).h().m.a(258, false, (byte)-1);
            }
            if (intArray[2] != -1) {
                this.uiManager.activeView.a((int)59).h().m.a(GameDatabase.gameDatabase[3][intArray[2]][1]);
                this.uiManager.activeView.a((int)60).h().a = BaseScreen.f(GameDatabase.gameDatabase[3][intArray[2]][0]);
            } else {
                this.uiManager.activeView.a((int)59).h().m.a(0);
                this.uiManager.activeView.a((int)60).h().a = "";
            }
            this.uiManager.activeView.a((int)65).h().a = "" + intArray[1];
            this.uiManager.activeView.a((int)66).h().a = "" + game.Pet.a(intArray[0], intArray[1], intArray[4], 2);
            this.uiManager.activeView.a((int)67).h().a = "" + game.Pet.a(intArray[0], intArray[1], intArray[4], 3);
            this.uiManager.activeView.a((int)68).h().a = "" + game.Pet.a(intArray[0], intArray[1], intArray[4], 4);
            int n2 = intArray[4];
            int n3 = GameDatabase.spriteTable((byte)0, (short)intArray[0], (byte)4) - 1;
            for (int i3 = 0; i3 < 5; ++i3) {
                this.uiManager.activeView.a(74 - i3).a(true);
                this.uiManager.activeView.a((int)(74 - i3)).h().m.a = (byte)3;
                if (i3 > n3) {
                    this.uiManager.activeView.a(74 - i3).a(false);
                    continue;
                }
                if (n2 > 0) {
                    this.uiManager.activeView.a((int)(74 - i3)).h().m.a((byte)14, (byte)-1);
                    --n2;
                    continue;
                }
                this.uiManager.activeView.a((int)(74 - i3)).h().m.a((byte)16, (byte)-1);
            }
            if (this.selectedSubMenuIndex == 1) {
                this.uiManager.activeView.a((int)64).h().a = "Lấy ra";
                return;
            }
            if (this.selectedSubMenuIndex == 2) {
                this.uiManager.activeView.a((int)64).h().a = "Phóng sinh";
            }
        }
    }

    public final void z() {
        if (this.dialogSubState == 0 && this.parentScreen.isKeyPressed(4100)) {
            this.uiManager.activeView.navigateSelection(0);
            this.aV();
        } else if (this.dialogSubState == 0 && this.parentScreen.isKeyPressed(8448)) {
            this.uiManager.activeView.navigateSelection(1);
            this.aV();
        }
        if (this.dialogSubState == 0) {
            if (this.parentScreen.isKeyPressed(196640)) {
                if (this.selectedSubMenuIndex == 1) {
                    if (this.player.partyPetCount >= 6) {
                        this.uiManager.openUI("/data/ui/msgwarm.ui", 257, this);
                        this.a("Ba lô Sủng vật đã đủ", "Nhấn nút 5 để tiếp tục");
                        this.dialogSubState = 1;
                    } else {
                        if (this.player.O.size() <= 0) {
                            return;
                        }
                        this.player.r(this.h);
                        if (this.player.O.size() <= 0) {
                            this.parentScreen.setScreenMode((byte)16);
                            this.uiManager.closeUI("/data/ui/petstate.ui");
                        } else {
                            this.aV();
                        }
                    }
                } else if (this.selectedSubMenuIndex == 2) {
                    if (this.player.O.size() <= 0) {
                        return;
                    }
                    int[] intArray = (int[])this.player.O.elementAt(this.h);
                    if (GameDatabase.spriteTable((byte)0, (short)intArray[0], (byte)22) == 2) {
                        this.dialogSubState = 2;
                        this.E();
                        this.a("Thần thú không thể phóng sinh", "Nhấn nút 5 để tiếp tục");
                    } else {
                        this.dialogSubState = 1;
                        this.uiManager.openUI("/data/ui/msgconfirm.ui", 257, this);
                        this.b("Bạn muốn phóng sinh sủng vật này?", "Xác nhận");
                    }
                }
            } else if (this.parentScreen.isKeyPressed(786432)) {
                this.parentScreen.setScreenMode((byte)16);
                this.uiManager.closeUI("/data/ui/petstate.ui");
            }
        } else if (this.dialogSubState > 0) {
            if (this.parentScreen.isKeyPressed(196640) && this.selectedSubMenuIndex == 1 || this.parentScreen.isKeyPressed(131072 /* MASK_SOFT_LEFT */) && this.selectedSubMenuIndex == 2) {
                if (this.selectedSubMenuIndex == 1) {
                    this.uiManager.closeUI("/data/ui/msgwarm.ui");
                    this.dialogSubState = 0;
                } else if (this.selectedSubMenuIndex == 2) {
                    if (this.dialogSubState == 1) {
                        this.uiManager.closeUI("/data/ui/msgconfirm.ui");
                        int[] intArray = (int[])this.player.O.elementAt(this.h);
                        this.player.consumeBall(intArray[2]);
                        this.player.q(this.h);
                        this.aV();
                    } else if (this.dialogSubState == 2) {
                        this.F();
                    }
                    this.dialogSubState = 0;
                }
            } else if (this.parentScreen.isKeyPressed(786432)) {
                if (this.selectedSubMenuIndex == 1) {
                    return;
                }
                this.uiManager.closeUI("/data/ui/msgconfirm.ui");
                this.dialogSubState = 0;
            }
        }
        this.isDialogActive = true;
    }

    public final void A() {
        this.aS();
        this.uiManager.openUI("/data/ui/shop.ui", 257, this);
        this.selectedSubMenuIndex = 0;
        this.uiManager.activeView.a((int)5).h().a = "Ngân hàng Sủng vật";
        this.uiManager.activeView.a((int)6).h().a = "Gởi lại";
        this.uiManager.activeView.a((int)7).h().a = "Lấy ra";
        this.uiManager.activeView.a((int)9).h().a = "Phóng sinh";
    }

    public final void B() {
        if (this.parentScreen.isKeyPressed(4100)) {
            this.uiManager.activeView.navigateSelection(0);
            return;
        }
        if (this.parentScreen.isKeyPressed(8448)) {
            this.uiManager.activeView.navigateSelection(1);
            return;
        }
        if (this.parentScreen.isKeyPressed(196640)) {
            switch (this.selectedSubMenuIndex) {
                case 0: {
                    this.selectedShopIndex = 0;
                    this.parentScreen.setScreenMode((byte)7);
                    this.uiManager.closeUI("/data/ui/shop.ui");
                    return;
                }
                case 1: {
                    this.parentScreen.setScreenMode((byte)15);
                    this.uiManager.closeUI("/data/ui/shop.ui");
                    return;
                }
                case 2: {
                    this.parentScreen.setScreenMode((byte)15);
                    this.uiManager.closeUI("/data/ui/shop.ui");
                    return;
                }
                case 3: {
                    game.OverworldScreen.e = true;
                    this.uiManager.closeUI("/data/ui/shop.ui");
                    this.parentScreen.setScreenMode((byte)0);
                }
            }
            return;
        }
        if (this.parentScreen.isKeyPressed(262144 /* MASK_SOFT_RIGHT */)) {
            game.OverworldScreen.e = true;
            this.uiManager.closeUI("/data/ui/shop.ui");
            this.parentScreen.setScreenMode((byte)0);
        }
    }

    public final void C() {
        this.aS();
        this.uiManager.openUI("/data/ui/shop.ui", 257, this);
        this.selectedSubMenuIndex = 0;
    }

    public final void D() {
        this.parentScreen.isKeyHeld();
        if (!BaseScreen.b(this.selectedSubMenuIndex, 0) && !this.j() && this.dialogSubState == 0 && this.parentScreen.isKeyPressed(4100) && this.aW()) {
            this.uiManager.activeView.navigateSelection(0);
        } else if (!BaseScreen.b(this.selectedSubMenuIndex, 0) && !this.j() && this.dialogSubState == 0 && this.parentScreen.isKeyPressed(8448) && this.aW()) {
            this.uiManager.activeView.navigateSelection(1);
        } else if (this.aW() && !this.j() && BaseScreen.I() && this.parentScreen.isKeyPressed(196640)) {
            if (BaseScreen.H() && !BaseScreen.b(this.selectedSubMenuIndex, 0)) {
                return;
            }
            switch (this.selectedSubMenuIndex) {
                case 0: {
                    this.parentScreen.m();
                    this.parentScreen.setScreenMode((byte)2);
                    this.uiManager.closeUI("/data/ui/shop.ui");
                    break;
                }
                case 1: {
                    this.parentScreen.setScreenMode((byte)3);
                    this.uiManager.closeUI("/data/ui/shop.ui");
                    break;
                }
                case 2: {
                    if (this.dialogSubState == 0) {
                        if (game.Player.getInstance().A() == -1) {
                            this.dialogSubState = 6;
                            this.E();
                            this.a("Toàn bộ trạng thái đã đầy, không cần khôi phục", "Nhấn nút 5 để tiếp tục");
                            break;
                        }
                        if (!BaseScreen.isVipUnlocked) {
                            this.dialogSubState = 3;
                            for (int i = 0; i < this.player.partyPetCount; ++i) {
                                this.player.petParty[i].I();
                            }
                            this.b("Ba lô sủng vật trạng thái toàn bộ khôi phục");
                            break;
                        }
                        ScriptEngine h2 = this;
                        h2.p.a("/data/ui/msgRecover.ui", 257, h2);
                        h2.p.activeView.a((int)4).h().a = "Có khôi phục trạng thái ba lô sủng vật không?";
                        h2.p.activeView.a((int)5).h().a = "Cần tiền tài: ";
                        h2.p.activeView.a((int)6).h().a = "" + game.Player.getInstance().A();
                        h2.p.activeView.a((int)8).h().a = "" + game.Player.getInstance().E();
                        this.uiManager.closeUI("/data/ui/shop.ui");
                        this.dialogSubState = 1;
                        break;
                    }
                    if (this.dialogSubState == 1) {
                        int n2 = game.Player.getInstance().A();
                        if (game.Player.getInstance().hasGold(n2)) {
                            this.dialogSubState = 3;
                            game.Player.getInstance().addGold(-n2);
                            for (n2 = 0; n2 < this.player.partyPetCount; ++n2) {
                                this.player.petParty[n2].I();
                            }
                            this.b("Ba lô sủng vật trạng thái toàn bộ khôi phục");
                        } else {
                            this.dialogSubState = 2;
                            this.E();
                            this.setWarningDialogMessage("Kim tiền chưa đủ", "Nhấn nút 5 để tiếp tục");
                        }
                        this.uiManager.closeUI("/data/ui/msgRecover.ui");
                        break;
                    }
                    if (this.dialogSubState == 2 && BaseScreen.isVipUnlocked) {
                        this.parentScreen.setScreenMode((byte)102);
                    }
                    this.dialogSubState = 0;
                    this.F();
                    break;
                }
                case 3: {
                    game.OverworldScreen.e = true;
                    this.uiManager.closeUI("/data/ui/shop.ui");
                    this.parentScreen.setScreenMode((byte)0);
                }
            }
        } else if (!this.j() && this.parentScreen.isKeyPressed(262144 /* MASK_SOFT_RIGHT */) && BaseScreen.J() && this.aW()) {
            if (this.dialogSubState == 1) {
                this.uiManager.openUI("/data/ui/shop.ui", 257, this);
                this.uiManager.closeUI("/data/ui/msgRecover.ui");
                this.dialogSubState = 0;
                this.selectedSubMenuIndex = 0;
            } else if (this.dialogSubState == 0) {
                game.OverworldScreen.e = true;
                this.uiManager.closeUI("/data/ui/shop.ui");
                this.parentScreen.setScreenMode((byte)0);
            }
        }
        if (this.dialogSubState == 3 && this.ax()) {
            this.dialogSubState = 4;
            this.uiManager.openUI("/data/ui/shop.ui", 257, this);
            this.openMsgTipUi();
            this.setMsgTipText("Đang lưu...");
            this.hideMsgTipButtons();
        } else if (this.dialogSubState == 4 && ((WorldManager)this.parentScreen).k()) {
            this.setMsgTipText("Lưu thành công");
            this.dialogSubState = 5;
        } else if (this.dialogSubState == 5) {
            this.closeMsgTipUi();
            this.selectedSubMenuIndex = 0;
            this.dialogSubState = 0;
        }
        this.f();
        this.isDialogActive = true;
    }

    public final void a(int n2, byte val2) {
        this.uiManager.openUI("/data/ui/shopbuy.ui", 257, this);
        this.selectedSubMenuIndex = 0;
        this.dialogSubState = 0;
        ((MenuWidget)this.uiManager.activeView.a((int)0)).a.a = GameDatabase.gameDatabase[n2].length;
        ((MenuWidget)this.uiManager.activeView.a((int)0)).a.a(1);
        this.uiManager.activeView.a((int)5).h().a = "Mua";
        if (this.parentScreen instanceof WorldManager) {
            this.uiManager.activeView.a(57).a(true);
            this.uiManager.activeView.a(58).a(true);
            this.uiManager.activeView.a((int)57).h().a = "Mua sắm";
            this.uiManager.activeView.a((int)58).h().a = "Phản hồi";
            this.uiManager.activeView.a(39).a(false);
            this.uiManager.activeView.a(40).a(false);
        } else if (this.parentScreen instanceof BattleScreen) {
            this.uiManager.activeView.a(57).a(false);
            this.uiManager.activeView.a(58).a(false);
            this.uiManager.activeView.a(39).a(true);
            this.uiManager.activeView.a(40).a(true);
            this.uiManager.activeView.a((int)39).h().a = "Mua sắm";
            this.uiManager.activeView.a((int)40).h().a = "Phản hồi";
        }
        this.b(n2, val2);
    }

    private void b(int n2, byte val2) {
        this.w = ((MenuWidget)this.uiManager.activeView.a((int)0)).a.e;
        this.h = ((MenuWidget)this.uiManager.activeView.a((int)0)).a.f;
        for (int i = 0; i < 5; ++i) {
            if (this.uiManager.activeView.a((int)(i + 51)).h().m == null) {
                this.uiManager.activeView.a((int)(i + 51)).h().m = new SpriteWidget();
                this.uiManager.activeView.a((int)(i + 51)).h().m.a(0);
                this.uiManager.activeView.a((int)(i + 51)).h().m.a = (byte)2;
                this.uiManager.activeView.a((int)(i + 51)).h().m.a(258, false, (byte)-1);
            }
            this.uiManager.activeView.a((int)(i + 51)).h().m.a(GameDatabase.gameDatabase[n2][this.w + i][1]);
            this.uiManager.activeView.a((int)(14 + i * 5)).h().a = BaseScreen.f(GameDatabase.gameDatabase[n2][this.w + i][0]);
            if (this.parentScreen instanceof WorldManager) {
                if (this.j == 1 || this.j == 3) {
                    this.uiManager.activeView.a((int)(15 + i * 5)).h().a = "" + GameDatabase.gameDatabase[n2][this.w + i][3];
                } else if (this.j == 2) {
                    this.uiManager.activeView.a((int)(15 + i * 5)).h().a = GameDatabase.gameDatabase[n2][this.w + i][4] == 0 ? "" + GameDatabase.gameDatabase[n2][this.w + i][3] * 3 / 2 : "" + GameDatabase.gameDatabase[n2][this.w + i][3];
                }
            } else {
                this.uiManager.activeView.a((int)(15 + i * 5)).h().a = val2 == 0 && n2 == 4 && this.w + i == 0 ? "" + GameDatabase.gameDatabase[n2][this.w + i][3] : "" + (GameDatabase.gameDatabase[n2][this.w + i][3] << 1);
            }
            if (GameDatabase.gameDatabase[n2][this.w + i][4] == 0) {
                this.uiManager.activeView.a((int)(i + 45)).h().m.a(84);
                continue;
            }
            if (GameDatabase.gameDatabase[n2][this.w + i][4] == 1) {
                this.uiManager.activeView.a((int)(i + 45)).h().m.a(83);
                continue;
            }
            if (GameDatabase.gameDatabase[n2][this.w + i][4] != 2) continue;
            this.uiManager.activeView.a((int)(i + 45)).h().m.a(74);
        }
        this.uiManager.activeView.a((int)56).h().a = BaseScreen.f(GameDatabase.gameDatabase[n2][this.h][2]);
        this.uiManager.activeView.a((int)43).h().a = "" + this.player.G();
        this.uiManager.activeView.a((int)44).h().a = "" + this.player.E();
        this.uiManager.activeView.a(38).b(102 + this.h * 84 / GameDatabase.gameDatabase[n2].length, this.uiManager.activeView.a());
    }

    public final void a(byte val, byte val2) {
        this.parentScreen.isKeyHeld();
        if (!BaseScreen.b(this.selectedSubMenuIndex, 0) && this.dialogSubState <= 1 && this.parentScreen.isKeyPressed(4100) && !this.j()) {
            this.uiManager.activeView.navigateSelection(0);
            if (this.dialogSubState == 0) {
                this.b((int)val, val2);
            }
        } else if (!BaseScreen.b(this.selectedSubMenuIndex, 0) && this.dialogSubState <= 1 && this.parentScreen.isKeyPressed(8448) && !this.j()) {
            this.uiManager.activeView.navigateSelection(1);
            if (this.dialogSubState == 0) {
                this.b((int)val, val2);
            }
        } else if (this.dialogSubState == 1 && this.parentScreen.isKeyPressed(16400) && this.selectedShopIndex > 0 && !this.j()) {
            --this.selectedShopIndex;
            if (this.selectedShopIndex <= 0) {
                this.selectedShopIndex = 99 - this.player.a(this.h, val2);
            }
            this.a(this.selectedShopIndex, this.selectedShopIndex * GameDatabase.gameDatabase[val][this.h][3], (int)GameDatabase.gameDatabase[val][this.h][4], (int)val);
        } else if (this.dialogSubState == 1 && this.parentScreen.isKeyPressed(32832) && !this.j()) {
            ++this.selectedShopIndex;
            if (this.selectedShopIndex > 99 - this.player.a(this.h, val2)) {
                this.selectedShopIndex = 1;
            }
            this.a(this.selectedShopIndex, this.selectedShopIndex * GameDatabase.gameDatabase[val][this.h][3], (int)GameDatabase.gameDatabase[val][this.h][4], (int)val);
        } else if (BaseScreen.I() && this.parentScreen.isKeyPressed(196640) && !this.j()) {
            if (BaseScreen.H() && !BaseScreen.b(this.selectedSubMenuIndex, 0)) {
                return;
            }
            if (GameDatabase.gameDatabase[val][this.h][4] == 2) {
                if (this.dialogSubState == 0) {
                    if (BaseScreen.isVipUnlocked) {
                        if (!this.player.a(this.h, 1, (byte)0)) {
                            this.dialogSubState = 3;
                            this.E();
                            this.a("Đạo cụ đã đủ", "Nhấn nút 5 để tiếp tục");
                        } else {
                            this.parentScreen.setScreenMode((byte)101);
                        }
                    } else {
                        this.dialogSubState = 3;
                        this.uiManager.openUI("/data/ui/msgwarm.ui", 257, this);
                        this.a("Công năng còn chưa mở khải", "Nhấn nút 5 để tiếp tục");
                    }
                } else {
                    this.dialogSubState = 0;
                    this.uiManager.closeUI("/data/ui/msgwarm.ui");
                }
            } else if (val2 == 2 && this.h < 12) {
                this.selectedShopIndex = 1;
                if (this.player.a(this.h, this.selectedShopIndex, val2)) {
                    if (this.dialogSubState == 0) {
                        this.r = 0;
                        this.b(val, val2);
                    } else if (this.dialogSubState > 0) {
                        if (BaseScreen.isVipUnlocked) {
                            if (this.dialogSubState == 4) {
                                this.parentScreen.setScreenMode((byte)104);
                            } else if (this.dialogSubState == 3) {
                                this.parentScreen.setScreenMode((byte)102);
                            }
                        }
                        this.dialogSubState = 0;
                        this.uiManager.closeUI("/data/ui/msgwarm.ui");
                    }
                } else if (this.dialogSubState == 0) {
                    this.dialogSubState = 2;
                    this.uiManager.openUI("/data/ui/msgwarm.ui", 257, this);
                    this.a("Đạo cụ này đã đủ", "Nhấn nút 5 để tiếp tục");
                } else {
                    this.parentScreen.m();
                    this.dialogSubState = 0;
                    this.uiManager.closeUI("/data/ui/msgwarm.ui");
                    this.b((int)val, val2);
                }
            } else if (this.player.a(this.h, this.selectedShopIndex, val2)) {
                if (this.dialogSubState == 0) {
                    this.dialogSubState = 1;
                    this.uiManager.openUI("/data/ui/msgyn.ui", 257, this);
                    this.selectedShopIndex = 1;
                    this.r = 0;
                    this.a(this.selectedShopIndex, this.selectedShopIndex * GameDatabase.gameDatabase[val][this.h][3], (int)GameDatabase.gameDatabase[val][this.h][4], (int)val);
                } else if (this.dialogSubState == 1) {
                    this.b(val, val2);
                } else if (this.dialogSubState == 2) {
                    game.WorldManager.getInstance().M.i();
                    this.dialogSubState = 0;
                    this.selectedShopIndex = 0;
                    this.uiManager.closeUI("/data/ui/msgwarm.ui");
                    this.b((int)val, val2);
                } else {
                    if (BaseScreen.isVipUnlocked) {
                        if (this.dialogSubState == 4) {
                            this.uiManager.closeUI("/data/ui/msgyn.ui");
                            this.parentScreen.setScreenMode((byte)104);
                        } else if (this.dialogSubState == 3) {
                            this.uiManager.closeUI("/data/ui/msgyn.ui");
                            this.parentScreen.setScreenMode((byte)102);
                        }
                    }
                    this.dialogSubState = 0;
                    this.selectedShopIndex = 0;
                    this.uiManager.closeUI("/data/ui/msgwarm.ui");
                }
            } else if (this.dialogSubState == 0) {
                this.dialogSubState = 2;
                this.uiManager.openUI("/data/ui/msgwarm.ui", 257, this);
                this.a("Đạo cụ này đã đủ", "Nhấn nút 5 để tiếp tục");
            } else {
                this.dialogSubState = 0;
                this.uiManager.closeUI("/data/ui/msgwarm.ui");
            }
        } else if (this.parentScreen.isKeyPressed(262144 /* MASK_SOFT_RIGHT */) && !this.j() && BaseScreen.J()) {
            if (this.dialogSubState == 0) {
                if (this.parentScreen instanceof WorldManager) {
                    if (this.j == 1) {
                        this.parentScreen.setScreenMode((byte)1);
                    } else if (this.j == 2) {
                        this.parentScreen.setScreenMode((byte)14);
                    } else if (this.j == 3) {
                        this.parentScreen.setScreenMode((byte)27);
                    }
                    this.uiManager.closeUI("/data/ui/shopbuy.ui");
                } else {
                    this.uiManager.closeUI("/data/ui/shopbuy.ui");
                    this.parentScreen.setScreenMode((byte)20);
                }
            } else if (this.dialogSubState == 1) {
                this.dialogSubState = 0;
                this.selectedShopIndex = 0;
                this.uiManager.closeUI("/data/ui/msgyn.ui");
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
                            if (this.parentScreen instanceof WorldManager && (this.j == 1 || this.j == 3) && this.player.b(this.h, this.selectedShopIndex * GameDatabase.gameDatabase[val][this.h][3], (int)val) || this.j == 2 && (GameDatabase.gameDatabase[val][this.h][4] == 0 && this.player.b(this.h, this.selectedShopIndex * GameDatabase.gameDatabase[val][this.h][3] * 3 / 2, (int)val) || GameDatabase.gameDatabase[val][this.h][4] != 0 && this.player.b(this.h, this.selectedShopIndex * GameDatabase.gameDatabase[val][this.h][3], (int)val))) {
                                if (this.r == 0) {
                                    this.player.c(this.h, this.selectedShopIndex, val2);
                                    if (GameDatabase.gameDatabase[val][this.h][4] == 0) {
                                        if (this.j == 1 || this.j == 3) {
                                            this.player.addGold(-this.selectedShopIndex * GameDatabase.gameDatabase[val][this.h][3]);
                                        } else if (this.j == 2) {
                                            this.player.addGold(-this.selectedShopIndex * GameDatabase.gameDatabase[val][this.h][3] * 3 / 2);
                                        }
                                    } else if (this.j == 1 || this.j == 3) {
                                        this.player.addArenaPoints(-this.selectedShopIndex * GameDatabase.gameDatabase[val][this.h][3]);
                                    } else if (this.j == 2) {
                                        this.player.addArenaPoints(-this.selectedShopIndex * GameDatabase.gameDatabase[val][this.h][3]);
                                    }
                                    this.uiManager.openUI("/data/ui/msgwarm.ui", 257, this);
                                    if (val == 3 && this.h == 17) {
                                        this.a("Đã thành công mua sắm #2" + BaseScreen.f(GameDatabase.gameDatabase[val][this.h][0]) + " * " + 5 * this.selectedShopIndex, "Nhấn nút 5 để tiếp tục");
                                    } else {
                                        this.a("Đã thành công mua sắm #2" + BaseScreen.f(GameDatabase.gameDatabase[val][this.h][0]) + " * " + this.selectedShopIndex, "Nhấn nút 5 để tiếp tục");
                                    }
                                    this.dialogSubState = 2;
                                    this.selectedShopIndex = 1;
                                } else {
                                    this.dialogSubState = 0;
                                }
                                this.uiManager.closeUI("/data/ui/msgyn.ui");
                                return;
                            }
                            if (this.parentScreen instanceof BattleScreen && this.player.b(this.h, this.selectedShopIndex * GameDatabase.gameDatabase[val][this.h][3] << 1, (int)val)) {
                                if (this.r == 0) {
                                    this.player.c(this.h, this.selectedShopIndex, val2);
                                    if (GameDatabase.gameDatabase[val][this.h][4] == 0) {
                                        this.player.addGold(-this.selectedShopIndex * GameDatabase.gameDatabase[val][this.h][3] << 1);
                                    } else {
                                        this.player.u(-this.selectedShopIndex * GameDatabase.gameDatabase[val][this.h][3] << 1);
                                    }
                                    this.uiManager.openUI("/data/ui/msgwarm.ui", 257, this);
                                    if (val == 3 && this.h == 17) {
                                        this.a("Đã thành công mua sắm #2" + BaseScreen.f(GameDatabase.gameDatabase[val][this.h][0]) + " * " + 5 * this.selectedShopIndex, "Nhấn nút 5 để tiếp tục");
                                    } else {
                                        this.a("Đã thành công mua sắm #2" + BaseScreen.f(GameDatabase.gameDatabase[val][this.h][0]) + " * " + this.selectedShopIndex, "Nhấn nút 5 để tiếp tục");
                                    }
                                    this.dialogSubState = 2;
                                    this.selectedShopIndex = 1;
                                } else {
                                    this.dialogSubState = 0;
                                }
                                this.uiManager.closeUI("/data/ui/msgyn.ui");
                                return;
                            }
                            if (this.r != 0) break block25;
                            this.uiManager.openUI("/data/ui/msgwarm.ui", 257, this);
                            if (GameDatabase.gameDatabase[val][this.h][4] == 0) {
                                this.dialogSubState = 3;
                                this.setWarningDialogMessage("Kim tiền chưa đủ", "Nhấn nút 5 để tiếp tục");
                            } else {
                                this.dialogSubState = 4;
                                this.a("Số lượng Huy chương chưa đủ", "Nhấn nút 5 để tiếp tục");
                            }
                            if (!(this.parentScreen instanceof WorldManager)) break block26;
                            if (this.j != 1 && this.j != 3) break block27;
                            n2 = this.selectedShopIndex * GameDatabase.gameDatabase[val][this.h][3];
                            break block28;
                        }
                        if (this.j != 2) break block29;
                        n2 = GameDatabase.gameDatabase[val][this.h][4] == 0 ? this.selectedShopIndex * GameDatabase.gameDatabase[val][this.h][3] * 3 / 2 : this.selectedShopIndex * GameDatabase.gameDatabase[val][this.h][3];
                    }
                    this.b(new int[]{val, val2, this.h, GameDatabase.gameDatabase[val][this.h][4], n2, this.selectedShopIndex});
                    break block29;
                }
                if (this.parentScreen instanceof BattleScreen) {
                    int n3 = this.selectedShopIndex * GameDatabase.gameDatabase[val][this.h][3] << 1;
                    this.b(new int[]{val, val2, this.h, GameDatabase.gameDatabase[val][this.h][4], n3, this.selectedShopIndex});
                }
                break block29;
            }
            this.dialogSubState = 0;
        }
        this.uiManager.closeUI("/data/ui/msgyn.ui");
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
        this.uiManager.activeView.a((int)9).h().a = n5 == 3 && this.h == 17 ? "" + n2 * 5 : "" + n2;
        if (this.parentScreen instanceof WorldManager) {
            if (this.j == 1 || this.j == 3) {
                this.uiManager.activeView.a((int)11).h().a = "" + n3;
            } else if (this.j == 2) {
                this.uiManager.activeView.a((int)11).h().a = n4 == 0 ? "" + n3 * 3 / 2 : "" + n3;
            }
        } else {
            this.uiManager.activeView.a((int)11).h().a = "" + (n3 << 1);
        }
        if (n4 == 0) {
            this.uiManager.activeView.a((int)12).h().m.a(84);
            return;
        }
        if (n4 == 1) {
            this.uiManager.activeView.a((int)12).h().m.a(83);
        }
    }

    public final void E() {
        this.uiManager.openUI("/data/ui/msgwarm.ui", 257, this);
    }

    public final void F() {
        this.uiManager.closeUI("/data/ui/msgwarm.ui");
    }

    public final boolean G() {
        return !this.uiManager.isUILoaded("/data/ui/msgwarm.ui");
    }

    public final void setWarningDialogMessage(String text, String buttonHint) {
        this.uiManager.activeView.a((int)6).h().a = string2;
        this.uiManager.activeView.a((int)7).h().a = text;
    }

    public final void openMsgTipUi() {
        this.uiManager.openUI("/data/ui/msgtip.ui", 257, this);
    }

    public final void closeMsgTipUi() {
        this.uiManager.closeUI("/data/ui/msgtip.ui");
    }

    private boolean aW() {
        return !this.uiManager.isUILoaded("/data/ui/msgtip.ui");
    }

    public final void setMsgTipText(String text) {
        this.uiManager.activeView.a((int)2).h().a = text;
    }

    public final void hideMsgTipButtons() {
        this.uiManager.activeView.a(3).a(false);
        this.uiManager.activeView.a(4).a(false);
    }

    public final void K() {
        if (this.dialogSubState == 0) {
            if (this.parentScreen.isKeyPressed(196640)) {
                this.dialogSubState = 1;
                this.setMsgTipText("Đang lưu...");
                this.hideMsgTipButtons();
                return;
            }
            if (this.parentScreen.isKeyPressed(262144 /* MASK_SOFT_RIGHT */)) {
                this.selectedSubMenuIndex = BaseScreen.isVipUnlocked ? 5 : 4;
                this.parentScreen.setScreenMode((byte)6);
                this.uiManager.closeUI("/data/ui/msgtip.ui");
                this.dialogSubState = 0;
                return;
            }
        } else if (this.dialogSubState == 1) {
            if (((WorldManager)this.parentScreen).k()) {
                this.setMsgTipText("Lưu thành công");
                this.dialogSubState = 2;
                return;
            }
        } else if (this.dialogSubState == 2) {
            this.uiManager.closeUI("/data/ui/msgtip.ui");
            this.uiManager.closeUI("/data/ui/gamemenu.ui");
            this.parentScreen.setScreenMode((byte)0);
            this.dialogSubState = 0;
        }
    }

    private void b(String text, String string2) {
        this.uiManager.activeView.a((int)2).h().a = string2;
        this.uiManager.activeView.a((int)4).h().a = text;
    }

    public final void L() {
        this.uiManager.openUI("/data/ui/shopbuy.ui", 257, this);
        this.selectedSubMenuIndex = 0;
        this.dialogSubState = 0;
        this.uiManager.activeView.a((int)5).h().a = "Bán ra";
        this.uiManager.activeView.a((int)39).h().a = "";
        this.uiManager.activeView.a((int)40).h().a = "";
        this.uiManager.activeView.a((int)57).h().a = "Bán đi";
        this.uiManager.activeView.a((int)58).h().a = "Phản hồi";
        this.player.x();
        this.aX();
    }

    private void aX() {
        if (this.player.S.size() > 5) {
            ((MenuWidget)this.uiManager.activeView.a((int)0)).a.a(1);
        } else {
            ((MenuWidget)this.uiManager.activeView.a((int)0)).a.a(0);
        }
        ((MenuWidget)this.uiManager.activeView.a((int)0)).a.a = this.player.S.size();
        this.w = ((MenuWidget)this.uiManager.activeView.a((int)0)).a.e;
        this.h = ((MenuWidget)this.uiManager.activeView.a((int)0)).a.f;
        if (this.h >= this.player.S.size()) {
            ((MenuWidget)this.uiManager.activeView.a((int)0)).a.f = this.h = this.player.S.size() - 1;
        }
        if (this.w > 0 && this.h - this.w < 4) {
            --this.w;
            ((MenuWidget)this.uiManager.activeView.a((int)0)).a.e = this.w;
        }
        for (int i = 0; i < 5; ++i) {
            if (this.w + i < this.player.S.size()) {
                int n2 = ((int[])this.player.S.elementAt(this.w + i))[0];
                if (this.uiManager.activeView.a((int)(i + 51)).h().m == null) {
                    this.uiManager.activeView.a((int)(i + 51)).h().m = new SpriteWidget();
                    this.uiManager.activeView.a((int)(i + 51)).h().m.a(0);
                    this.uiManager.activeView.a((int)(i + 51)).h().m.a = (byte)2;
                    this.uiManager.activeView.a((int)(i + 51)).h().m.a(258, false, (byte)-1);
                }
                this.uiManager.activeView.a((int)(i + 51)).h().m.a(GameDatabase.gameDatabase[4][n2][1]);
                this.uiManager.activeView.a((int)(14 + i * 5)).h().a = BaseScreen.f(GameDatabase.gameDatabase[4][n2][0]);
                this.uiManager.activeView.a((int)(15 + i * 5)).h().a = "" + GameDatabase.gameDatabase[4][n2][3] / 2;
                if (GameDatabase.gameDatabase[4][n2][4] == 0) {
                    this.uiManager.activeView.a((int)(i + 45)).h().m.a(84);
                    continue;
                }
                if (GameDatabase.gameDatabase[4][n2][4] == 1) {
                    this.uiManager.activeView.a((int)(i + 45)).h().m.a(83);
                    continue;
                }
                if (GameDatabase.gameDatabase[4][n2][4] != 2) continue;
                this.uiManager.activeView.a((int)(i + 45)).h().m.a(74);
                continue;
            }
            if (this.uiManager.activeView.a((int)(i + 51)).h().m != null) {
                this.uiManager.activeView.a((int)(i + 51)).h().m.d();
            }
            this.uiManager.activeView.a((int)(14 + i * 5)).h().a = "";
            this.uiManager.activeView.a((int)(15 + i * 5)).h().a = "";
            this.uiManager.activeView.a((int)(i + 45)).h().m.a(86);
        }
        this.uiManager.activeView.a((int)56).h().a = this.player.S.size() > 0 ? BaseScreen.f(GameDatabase.gameDatabase[4][((int[])this.player.S.elementAt(this.h))[0]][2]) : "";
        if (this.player.S.size() <= 0) {
            return;
        }
        this.uiManager.activeView.a((int)43).h().a = "" + this.player.G();
        this.uiManager.activeView.a((int)44).h().a = "" + this.player.E();
        this.uiManager.activeView.a(38).b(102 + this.h * 84 / this.player.S.size(), this.uiManager.activeView.a());
    }

    /*
     * Enabled aggressive block sorting
     */
    public final void M() {
        if (this.parentScreen.isKeyPressed(4100)) {
            this.uiManager.activeView.navigateSelection(0);
            return;
        }
        if (this.parentScreen.isKeyPressed(8448)) {
            this.uiManager.activeView.navigateSelection(1);
            return;
        }
        if (this.dialogSubState == 1 && this.parentScreen.isKeyPressed(16400) && this.selectedShopIndex > 0) {
            int[] intArray = (int[])this.player.S.elementAt(this.h);
            --this.selectedShopIndex;
            if (this.selectedShopIndex <= 0) {
                this.selectedShopIndex = this.player.a(intArray[0], (byte)0);
            }
            this.a(this.selectedShopIndex, this.selectedShopIndex * GameDatabase.gameDatabase[4][intArray[0]][3] / 2, (int)GameDatabase.gameDatabase[4][intArray[0]][4], 4);
            return;
        }
        if (this.dialogSubState == 1 && this.parentScreen.isKeyPressed(32832)) {
            int[] intArray = (int[])this.player.S.elementAt(this.h);
            ++this.selectedShopIndex;
            if (this.selectedShopIndex > this.player.a(intArray[0], (byte)0)) {
                this.selectedShopIndex = 1;
            }
            this.a(this.selectedShopIndex, this.selectedShopIndex * GameDatabase.gameDatabase[4][intArray[0]][3] / 2, (int)GameDatabase.gameDatabase[4][intArray[0]][4], 4);
            return;
        }
        if (this.parentScreen.isKeyPressed(196640) && this.player.S.size() > 0) {
            int[] intArray = (int[])this.player.S.elementAt(this.h);
            if (this.dialogSubState == 0) {
                this.dialogSubState = 1;
                this.uiManager.openUI("/data/ui/msgyn.ui", 257, this);
                this.selectedShopIndex = 1;
                this.r = 0;
                this.a(this.selectedShopIndex, this.selectedShopIndex * GameDatabase.gameDatabase[4][intArray[0]][3] / 2, (int)GameDatabase.gameDatabase[4][intArray[0]][4], 4);
                return;
            }
            if (this.r != 0) {
                this.uiManager.closeUI("/data/ui/msgyn.ui");
                this.dialogSubState = 0;
                return;
            }
            this.player.d(intArray[0], this.selectedShopIndex, (byte)0);
            this.player.addGold(this.selectedShopIndex * GameDatabase.gameDatabase[4][intArray[0]][3] / 2);
        } else {
            if (!this.parentScreen.isKeyPressed(262144 /* MASK_SOFT_RIGHT */)) return;
            if (this.dialogSubState == 0) {
                this.parentScreen.setScreenMode((byte)1);
                this.uiManager.closeUI("/data/ui/shopbuy.ui");
                ((MenuWidget)this.uiManager.activeView.a((int)0)).a.f = this.selectedSubMenuIndex = 1;
                return;
            }
        }
        this.dialogSubState = 0;
        this.uiManager.closeUI("/data/ui/msgyn.ui");
        this.player.x();
        this.aX();
    }

    public final void N() {
        this.uiManager.openUI("/data/ui/record.ui", 257, this);
        this.uiManager.closeUI("/data/ui/gamemenu.ui");
        this.uiManager.activeView.a((int)14).h().a = "" + (this.player.partyPetCount + this.player.O.size());
        this.uiManager.activeView.a((int)17).h().a = "" + this.player.F;
        this.uiManager.activeView.a((int)20).h().a = "" + this.player.H;
        this.uiManager.activeView.a((int)26).h().a = "" + this.player.G;
        int n2 = 0;
        for (byte by = 0; by < this.player.B.length; by = (byte)(by + 1)) {
            if (this.player.c(by, (byte)0) != 2) continue;
            ++n2;
        }
        this.uiManager.activeView.a((int)29).h().a = "" + n2;
        long l2 = game.GameStateController.getInstance().d + game.GameStateController.getInstance().e - game.GameStateController.getInstance().f;
        WorldManager k2 = this.uiManager.activeView.a(31).h();
        game.WorldManager.getInstance();
        k2.a = game.WorldManager.a(l2)[1];
        ((MenuWidget)this.uiManager.activeView.a((int)0)).b.f = this.selectedShopIndex;
        this.selectedSubMenuIndex = 0;
        this.dialogSubState = 0;
        this.isDialogActive = true;
    }

    public final void O() {
        if (this.parentScreen.isKeyPressed(16400)) {
            this.uiManager.activeView.navigateSelection(2);
            this.isDialogActive = true;
        } else if (this.parentScreen.isKeyPressed(32832)) {
            this.uiManager.activeView.navigateSelection(3);
            this.isDialogActive = true;
        } else if (this.parentScreen.isKeyPressed(196640)) {
            if (this.dialogSubState == 0) {
                switch (this.selectedShopIndex) {
                    case 0: {
                        if (game.Player.getInstance().hasBadge(5)) {
                            this.parentScreen.setScreenMode((byte)11);
                            break;
                        }
                        this.E();
                        this.a("Không đạt được sủng vật sách tranh đạo cụ", "Nhấn nút 5 để tiếp tục");
                        this.dialogSubState = 1;
                        break;
                    }
                    case 1: {
                        this.parentScreen.setScreenMode((byte)12);
                    }
                }
            } else {
                this.dialogSubState = 0;
                this.F();
            }
        } else if (this.parentScreen.isKeyPressed(262144 /* MASK_SOFT_RIGHT */) && this.dialogSubState == 0) {
            this.selectedSubMenuIndex = BaseScreen.isVipUnlocked ? 3 : 2;
            this.parentScreen.setScreenMode((byte)6);
            this.uiManager.closeUI("/data/ui/record.ui");
        }
        this.isDialogActive = true;
    }

    public final void P() {
        this.uiManager.openUI("/data/ui/petmap.ui", 257, this);
        this.uiManager.closeUI("/data/ui/record.ui");
        this.selectedSubMenuIndex = 0;
        this.selectedShopIndex = 0;
        this.dialogSubState = 0;
        ((MenuWidget)this.uiManager.activeView.a((int)0)).a.a(1);
        this.aZ();
        this.isDialogActive = true;
    }

    private void aY() {
        ((MenuWidget)this.uiManager.activeView.a((int)0)).a.e = 0;
        ((MenuWidget)this.uiManager.activeView.a((int)0)).a.f = 0;
    }

    private void aZ() {
        int n2;
        ((MenuWidget)this.uiManager.activeView.a((int)0)).a.a = this.player.X[this.selectedSubMenuIndex];
        this.w = ((MenuWidget)this.uiManager.activeView.a((int)0)).a.e;
        this.h = ((MenuWidget)this.uiManager.activeView.a((int)0)).a.f;
        int n3 = GameDatabase.gameDatabase[0][this.player.W[this.selectedSubMenuIndex] + this.h][17];
        if (this.player.a((byte)this.selectedSubMenuIndex, this.h + this.player.W[this.selectedSubMenuIndex]) > 0) {
            this.uiManager.activeView.a(21).a(true);
            if (this.uiManager.activeView.a((int)21).h().m != null) {
                this.uiManager.activeView.a((int)21).h().m.d();
            } else {
                this.uiManager.activeView.a((int)21).h().m = new SpriteWidget();
                this.uiManager.activeView.a((int)21).h().m.a(0);
                this.uiManager.activeView.a((int)21).h().m.a = (byte)3;
            }
            this.uiManager.activeView.a((int)21).h().m.a(n3, false, (byte)-1);
            this.uiManager.activeView.a((int)21).h().m.b();
        } else {
            this.uiManager.activeView.a(21).a(false);
        }
        n3 = 0;
        for (n2 = 0; n2 < this.player.X[this.selectedSubMenuIndex]; ++n2) {
            if (this.player.a((byte)this.selectedSubMenuIndex, this.player.W[this.selectedSubMenuIndex] + n2) != 2) continue;
            ++n3;
        }
        for (n2 = 0; n2 < 5; ++n2) {
            if (this.uiManager.activeView.a((int)(n2 + 44)).h().m == null) {
                this.uiManager.activeView.a((int)(n2 + 44)).h().m = new SpriteWidget();
                this.uiManager.activeView.a((int)(n2 + 44)).h().m.a(102);
                this.uiManager.activeView.a((int)(n2 + 44)).h().m.a = (byte)2;
                this.uiManager.activeView.a((int)(n2 + 44)).h().m.a(257, false, (byte)-1);
            }
            if (this.player.a((byte)this.selectedSubMenuIndex, n2 + this.w + this.player.W[this.selectedSubMenuIndex]) == 2) {
                this.uiManager.activeView.a((int)(n2 + 44)).h().m.a(101);
            } else {
                this.uiManager.activeView.a((int)(n2 + 44)).h().m.a(102);
            }
            this.uiManager.activeView.a((int)(24 + (n2 << 2) + 3)).h().a = BaseScreen.f(GameDatabase.gameDatabase[0][this.player.W[this.selectedSubMenuIndex] + n2 + this.w][0]);
        }
        this.uiManager.activeView.a((int)20).h().a = BaseScreen.f(365 + this.selectedSubMenuIndex) + n3 + "/" + this.player.X[this.selectedSubMenuIndex];
        this.uiManager.activeView.a(23).b(99 + (this.h << 6) / this.player.X[this.selectedSubMenuIndex], this.uiManager.activeView.a());
    }

    public final void Q() {
        if (this.parentScreen.isKeyPressed(4100)) {
            this.uiManager.activeView.navigateSelection(0);
            this.aZ();
        } else if (this.parentScreen.isKeyPressed(8448)) {
            this.uiManager.activeView.navigateSelection(1);
            this.aZ();
        } else if (this.parentScreen.isKeyPressed(16400)) {
            this.uiManager.activeView.navigateSelection(2);
            this.aY();
            this.aZ();
        } else if (this.parentScreen.isKeyPressed(32832)) {
            this.uiManager.activeView.navigateSelection(3);
            this.aY();
            this.aZ();
        } else if (!this.parentScreen.isKeyPressed(196640) && this.parentScreen.isKeyPressed(786432)) {
            if (this.parentScreen.Q == 8) {
                this.parentScreen.setScreenMode((byte)8);
            } else {
                this.selectedShopIndex = 0;
                this.parentScreen.setScreenMode((byte)9);
            }
            this.uiManager.closeUI("/data/ui/petmap.ui");
        }
        this.isDialogActive = true;
    }

    public final void R() {
        this.uiManager.openUI("/data/ui/task.ui", 257, this);
        this.uiManager.closeUI("/data/ui/gamemenu.ui");
        ((MenuWidget)this.uiManager.activeView.a((int)0)).b.f = this.selectedSubMenuIndex;
        this.selectedShopIndex = 0;
        this.r = 0;
        this.ba();
        this.bb();
    }

    private void ba() {
        switch (this.selectedSubMenuIndex) {
            case 0: {
                if (game.OverworldScreen.t >= game.OverworldScreen.r.length / 2 - 1) {
                    ((MenuWidget)this.uiManager.activeView.a((int)0)).a.a = game.OverworldScreen.r.length / 2;
                    ((MenuWidget)this.uiManager.activeView.a((int)0)).a.f = game.OverworldScreen.r.length / 2 - 1;
                } else {
                    ((MenuWidget)this.uiManager.activeView.a((int)0)).a.a = game.OverworldScreen.t + 1;
                    ((MenuWidget)this.uiManager.activeView.a((int)0)).a.f = game.OverworldScreen.t;
                }
                this.uiManager.activeView.a((int)36).h().a = "";
                this.h = game.OverworldScreen.t;
                this.w = game.OverworldScreen.t - 4;
                if (this.h <= 0) {
                    this.h = 0;
                }
                if (this.w <= 0) {
                    this.w = 0;
                }
                ((MenuWidget)this.uiManager.activeView.a((int)0)).a.e = this.w;
                this.uiManager.activeView.a((int)37).h().a = "Đầu mối chính hoàn thành độ: ";
                this.uiManager.activeView.a((int)38).h().a = game.OverworldScreen.t >= game.OverworldScreen.r.length / 2 ? game.OverworldScreen.r[game.OverworldScreen.r.length - 1] : game.OverworldScreen.r[game.OverworldScreen.r.length / 2 + game.OverworldScreen.t];
                int n2 = game.OverworldScreen.t * 1000 / (game.OverworldScreen.r.length / 2);
                if (!BaseScreen.isVipUnlocked) {
                    int n3 = n2 % 10;
                    if (n3 == 0) {
                        n3 = 1;
                    }
                    this.uiManager.activeView.a((int)38).h().a = n2 / 50 + "." + n3 + "%";
                } else {
                    this.uiManager.activeView.a((int)38).h().a = n2 / 10 + "." + n2 % 10 + "%";
                }
                if (game.OverworldScreen.t > 4) {
                    ((MenuWidget)this.uiManager.activeView.a((int)0)).a.a(1);
                } else {
                    ((MenuWidget)this.uiManager.activeView.a((int)0)).a.a(0);
                }
                this.uiManager.activeView.a((int)8).h().g = 11290624;
                break;
            }
            case 1: {
                int n4;
                ((MenuWidget)this.uiManager.activeView.a((int)0)).a.a = game.OverworldScreen.u;
                ((MenuWidget)this.uiManager.activeView.a((int)0)).a.f = 0;
                ((MenuWidget)this.uiManager.activeView.a((int)0)).a.e = 0;
                this.uiManager.activeView.a((int)36).h().a = "";
                this.uiManager.activeView.a((int)37).h().a = "Chi nhánh hoàn thành độ: ";
                int n5 = 0;
                for (n4 = 0; n4 < game.OverworldScreen.s.length; ++n4) {
                    if (game.OverworldScreen.s[n4][1] != 3) continue;
                    ++n5;
                }
                System.out.println(" Nhiệm vụ phụ " + game.OverworldScreen.q.length);
                n4 = n5 * 1000 / (game.OverworldScreen.q.length / 2);
                this.uiManager.activeView.a((int)38).h().a = n4 / 10 + "." + n4 % 10 + "%";
                if (game.OverworldScreen.u > 5) {
                    ((MenuWidget)this.uiManager.activeView.a((int)0)).a.a(1);
                } else {
                    ((MenuWidget)this.uiManager.activeView.a((int)0)).a.a(0);
                }
                this.uiManager.activeView.a((int)9).h().g = 11290624;
            }
        }
        this.bb();
    }

    private void bb() {
        this.w = ((MenuWidget)this.uiManager.activeView.a((int)0)).a.e;
        this.h = ((MenuWidget)this.uiManager.activeView.a((int)0)).a.f;
        for (int i = 0; i < 5; ++i) {
            if (this.selectedSubMenuIndex == 0) {
                if (game.OverworldScreen.t > 0) {
                    if (this.w + i < game.OverworldScreen.t) {
                        this.uiManager.activeView.a((int)(10 + i * 5 + 2)).h().a = "" + (i + this.w + 1);
                        this.uiManager.activeView.a((int)(10 + i * 5 + 3)).h().a = game.OverworldScreen.r[this.w + i];
                        this.uiManager.activeView.a((int)(10 + i * 5 + 4)).h().a = "Hoàn thành";
                        continue;
                    }
                    if (this.w + i == game.OverworldScreen.t && this.w + i <= game.OverworldScreen.r.length / 2 - 1) {
                        this.uiManager.activeView.a((int)(10 + i * 5 + 2)).h().a = "" + (i + this.w + 1);
                        this.uiManager.activeView.a((int)(10 + i * 5 + 3)).h().a = game.OverworldScreen.r[this.w + i];
                        this.uiManager.activeView.a((int)(10 + i * 5 + 4)).h().a = "";
                        continue;
                    }
                    this.uiManager.activeView.a((int)(10 + i * 5 + 2)).h().a = "";
                    this.uiManager.activeView.a((int)(10 + i * 5 + 3)).h().a = "";
                    this.uiManager.activeView.a((int)(10 + i * 5 + 4)).h().a = "";
                    this.uiManager.activeView.a((int)36).h().a = "";
                    continue;
                }
                this.uiManager.activeView.a((int)12).h().a = "1";
                this.uiManager.activeView.a((int)13).h().a = game.OverworldScreen.r[0];
                this.uiManager.activeView.a((int)14).h().a = "";
                continue;
            }
            if (this.selectedSubMenuIndex != 1) continue;
            if (this.w + i < game.OverworldScreen.u) {
                this.uiManager.activeView.a((int)(10 + i * 5 + 2)).h().a = "" + (i + this.w + 1);
                this.uiManager.activeView.a((int)(10 + i * 5 + 3)).h().a = game.OverworldScreen.q[game.OverworldScreen.s[this.w + i][0]];
                if (game.OverworldScreen.s[this.w + i][1] == 3) {
                    this.uiManager.activeView.a((int)(10 + i * 5 + 4)).h().a = "Hoàn thành";
                    continue;
                }
                this.uiManager.activeView.a((int)(10 + i * 5 + 4)).h().a = "";
                continue;
            }
            this.uiManager.activeView.a((int)(10 + i * 5 + 2)).h().a = "";
            this.uiManager.activeView.a((int)(10 + i * 5 + 3)).h().a = "";
            this.uiManager.activeView.a((int)(10 + i * 5 + 4)).h().a = "";
        }
        switch (this.selectedSubMenuIndex) {
            case 0: {
                this.uiManager.activeView.a((int)36).h().a = game.OverworldScreen.r[game.OverworldScreen.r.length / 2 + this.h];
                break;
            }
            case 1: {
                if (game.OverworldScreen.u <= 0) break;
                this.uiManager.activeView.a((int)36).h().a = game.OverworldScreen.q[game.OverworldScreen.q.length / 2 + game.OverworldScreen.s[this.h][0]];
            }
        }
        if (((MenuWidget)this.uiManager.activeView.a((int)0)).a.a > 0) {
            this.uiManager.activeView.a(40).b(104 + (this.h << 6) / ((MenuWidget)this.uiManager.activeView.a((int)0)).a.a, this.uiManager.activeView.a());
        }
    }

    public final void S() {
        if (this.parentScreen.isKeyPressed(4100)) {
            this.uiManager.activeView.navigateSelection(0);
            this.bb();
            return;
        }
        if (this.parentScreen.isKeyPressed(8448)) {
            this.uiManager.activeView.navigateSelection(1);
            this.bb();
            return;
        }
        if (this.parentScreen.isKeyPressed(16400)) {
            this.uiManager.activeView.navigateSelection(2);
            this.ba();
            return;
        }
        if (this.parentScreen.isKeyPressed(32832)) {
            this.uiManager.activeView.navigateSelection(3);
            this.ba();
            return;
        }
        if (this.parentScreen.isKeyPressed(983072)) {
            this.uiManager.closeUI("/data/ui/task.ui");
            this.selectedSubMenuIndex = BaseScreen.isVipUnlocked ? 4 : 3;
            if (this.parentScreen.Q == 0) {
                this.selectedSubMenuIndex = 0;
                this.parentScreen.setScreenMode((byte)0);
                return;
            }
            this.parentScreen.setScreenMode((byte)6);
            return;
        }
        if (this.parentScreen.isKeyPressed(10)) {
            this.uiManager.closeUI("/data/ui/task.ui");
            this.parentScreen.setScreenMode((byte)0);
        }
    }

    public final void T() {
        this.uiManager.openUI("/data/ui/badge.ui", 257, this);
        this.uiManager.closeUI("/data/ui/record.ui");
        this.selectedSubMenuIndex = 0;
        this.dialogSubState = 0;
        for (int i = 0; i < 8; ++i) {
            if (this.player.B[i][0] == 0) continue;
            this.uiManager.activeView.a((int)(i + 25)).h().m.a(i + 46);
        }
        this.bc();
    }

    private void bc() {
        this.uiManager.activeView.a((int)13).h().a = BaseScreen.f(GameDatabase.gameDatabase[2][this.selectedSubMenuIndex][0]);
        this.uiManager.activeView.a((int)14).h().a = BaseScreen.f(GameDatabase.gameDatabase[2][this.selectedSubMenuIndex][2 + this.player.c((byte)this.selectedSubMenuIndex, (byte)1)]);
        if (this.player.c((byte)this.selectedSubMenuIndex, (byte)0) == 0) {
            this.uiManager.activeView.a((int)16).h().a = "Chưa đạt";
            return;
        }
        this.uiManager.activeView.a((int)16).h().a = "Đã đạt được";
        this.player.c((byte)this.selectedSubMenuIndex, (byte)1);
        this.uiManager.activeView.a((int)33).h().a = "";
    }

    public final void U() {
        if (this.parentScreen.isKeyPressed(4100)) {
            this.uiManager.activeView.navigateSelection(0);
            this.bc();
            return;
        }
        if (this.parentScreen.isKeyPressed(8448)) {
            this.uiManager.activeView.navigateSelection(1);
            this.bc();
            return;
        }
        if (this.parentScreen.isKeyPressed(16400)) {
            this.uiManager.activeView.navigateSelection(2);
            this.bc();
            return;
        }
        if (this.parentScreen.isKeyPressed(32832)) {
            this.uiManager.activeView.navigateSelection(3);
            this.bc();
            return;
        }
        if (this.parentScreen.isKeyPressed(786432)) {
            if (this.parentScreen.Q == 8) {
                this.parentScreen.setScreenMode((byte)8);
            } else {
                this.selectedShopIndex = 1;
                this.parentScreen.setScreenMode((byte)9);
            }
            this.uiManager.closeUI("/data/ui/badge.ui");
        }
    }

    public final void a(int n2) {
        this.uiManager.openUI("/data/ui/smsTip.ui", 257, this);
        if (this.uiManager.activeView.a((int)6).h().m == null) {
            this.uiManager.activeView.a((int)6).h().m = new SpriteWidget();
            this.uiManager.activeView.a((int)6).h().m.a = (byte)2;
            this.uiManager.activeView.a((int)6).h().m.a(-1);
            this.uiManager.activeView.a((int)6).h().m.a(257, false, (byte)-1);
            this.uiManager.activeView.a((int)6).h().m.a(n2 + 46);
        }
        this.uiManager.activeView.a((int)7).h().a = BaseScreen.f(n2 + 187) + ":" + BaseScreen.f(n2 + 195);
        this.uiManager.activeView.a((int)8).h().a = BaseScreen.f(377);
    }

    public final void V() {
        this.uiManager.closeUI("/data/ui/smsTip.ui");
    }

    public final void W() {
        this.selectedSubMenuIndex = 0;
        this.e(this.selectedShopIndex);
    }

    private void e(int n2) {
        int n3 = n2;
        Pet[] bArray = this.player.petParty;
        ScriptEngine h2 = this;
        h2.p.a("/data/ui/petstate.ui", 257, h2);
        h2.f(n3);
        h2.f = 0;
        if (h2.o instanceof WorldManager) {
            for (int i = 0; i < 6; ++i) {
                if (bArray[i] != null) {
                    h2.p.activeView.a((int)(16 + i * 6)).h().a = "#P" + bArray[i].L();
                    h2.p.activeView.a((int)(17 + i * 6)).h().a = "#P" + bArray[i].O();
                    continue;
                }
                h2.p.activeView.a((int)(16 + i * 6)).h().a = "#P0";
                h2.p.activeView.a((int)(17 + i * 6)).h().a = "#P0";
            }
            if (h2.o.Q == 16) {
                h2.p.activeView.a((int)64).h().a = "Gởi lại";
            }
            h2.p.activeView.a(75).a(false);
            h2.p.activeView.a(76).a(false);
        } else if (h2.o instanceof BattleScreen) {
            for (int i3 = 0; i3 < 6; ++i3) {
                if (i3 < ((BattleScreen)h2.o).f.length && bArray[((BattleScreen)h2.o).f[i3]] != null) {
                    h2.p.activeView.a((int)(16 + i3 * 6)).h().a = "#P" + bArray[((BattleScreen)h2.o).f[i3]].L();
                    h2.p.activeView.a((int)(17 + i3 * 6)).h().a = "#P" + bArray[((BattleScreen)h2.o).f[i3]].O();
                    continue;
                }
                h2.p.activeView.a((int)(16 + i3 * 6)).h().a = "#P0";
                h2.p.activeView.a((int)(17 + i3 * 6)).h().a = "#P0";
            }
            h2.p.activeView.a(63).a(false);
            h2.p.activeView.a(64).a(false);
            if (h2.o.Q == 4) {
                h2.p.activeView.a((int)75).h().a = "Sử dụng";
            } else if (h2.o.P == 5) {
                h2.p.activeView.a((int)75).h().a = "Xuất chiến";
            }
        }
        ((MenuWidget)h2.p.activeView.a((int)0)).a.a = h2.q.A;
        ((MenuWidget)h2.p.activeView.a((int)0)).a.d = h2.q.A;
        ((MenuWidget)h2.p.activeView.a((int)0)).a.f = n3;
        h2.g = true;
    }

    private void a(Pet[] bArray, int n2) {
        if (bArray[n2] != null) {
            if (this.uiManager.activeView.a((int)48).h().m != null) {
                this.uiManager.activeView.a((int)48).h().m.d();
            } else {
                this.uiManager.activeView.a((int)48).h().m = new SpriteWidget();
                this.uiManager.activeView.a((int)48).h().m.a(0);
                this.uiManager.activeView.a((int)48).h().m.a = (byte)3;
            }
            this.uiManager.activeView.a((int)48).h().m.a(bArray[n2].C, false, (byte)-1);
            this.uiManager.activeView.a((int)51).h().a = BaseScreen.f(bArray[n2].j((byte)0));
            this.uiManager.activeView.a((int)52).h().a = BaseScreen.f(365 + bArray[n2].j((byte)1));
            if (bArray[n2].j((byte)19) == -1) {
                this.uiManager.activeView.a((int)62).h().a = "";
            } else if (GameDatabase.gameDatabase[0][bArray[n2].j((byte)19)][2] == 1 || GameDatabase.gameDatabase[0][bArray[n2].j((byte)19)][2] == 2) {
                this.uiManager.activeView.a((int)62).h().a = "Có thể tiến hóa";
            } else if (GameDatabase.gameDatabase[0][bArray[n2].j((byte)19)][2] == 3) {
                this.uiManager.activeView.a((int)62).h().a = "Có thể dị hoá";
            }
            this.uiManager.activeView.a((int)61).h().a = bArray[n2].T();
            if (this.parentScreen instanceof BattleScreen) {
                this.uiManager.activeView.a((int)64).h().a = "Xuất chiến";
            } else if (this.parentScreen instanceof WorldManager) {
                this.uiManager.activeView.a((int)64).h().a = "Xác nhận";
            }
            if (this.uiManager.activeView.a((int)59).h().m == null) {
                this.uiManager.activeView.a((int)59).h().m = new SpriteWidget();
                this.uiManager.activeView.a((int)59).h().m.a(0);
                this.uiManager.activeView.a((int)59).h().m.a = (byte)2;
                this.uiManager.activeView.a((int)59).h().m.a(258, false, (byte)-1);
            }
            if (bArray[n2].c[5] != -1) {
                this.uiManager.activeView.a((int)59).h().m.a(GameDatabase.gameDatabase[3][bArray[n2].c[5]][1]);
                this.uiManager.activeView.a((int)60).h().a = BaseScreen.f(GameDatabase.gameDatabase[3][bArray[n2].c[5]][0]);
            } else {
                this.uiManager.activeView.a((int)59).h().m.a(0);
                this.uiManager.activeView.a((int)60).h().a = "";
            }
            this.uiManager.activeView.a((int)65).h().a = "" + bArray[n2].s();
            this.uiManager.activeView.a((int)66).h().a = "" + bArray[n2].e((byte)2);
            this.uiManager.activeView.a((int)67).h().a = "" + bArray[n2].e((byte)3);
            this.uiManager.activeView.a((int)68).h().a = "" + bArray[n2].e((byte)4);
            int n3 = bArray[n2].d[0];
            int n4 = GameDatabase.spriteTable((byte)0, (short)bArray[n2].q(), (byte)4) - 1;
            for (n2 = 0; n2 < 5; ++n2) {
                this.uiManager.activeView.a(74 - n2).a(true);
                this.uiManager.activeView.a((int)(74 - n2)).h().m.a(257, false, (byte)-1);
                this.uiManager.activeView.a((int)(74 - n2)).h().m.a = (byte)3;
                if (n2 > n4) {
                    this.uiManager.activeView.a(74 - n2).a(false);
                    continue;
                }
                if (n3 > 0) {
                    this.uiManager.activeView.a((int)(74 - n2)).h().m.a((byte)14, (byte)-1);
                    --n3;
                    continue;
                }
                this.uiManager.activeView.a((int)(74 - n2)).h().m.a((byte)16, (byte)-1);
            }
        }
    }

    private void f(int n2) {
        if (this.parentScreen instanceof WorldManager) {
            this.a(this.player.petParty, n2);
            return;
        }
        if (this.parentScreen instanceof BattleScreen) {
            this.a(this.player.petParty, (int)((BattleScreen)this.parentScreen).f[n2]);
        }
    }

    public final void X() {
        block132: {
            block135: {
                block134: {
                    block133: {
                        block131: {
                            if (this.dialogSubState != 0) break block131;
                            if (!BaseScreen.b(this.selectedSubMenuIndex, 0) && !this.j() && this.parentScreen.isKeyPressed(4100)) {
                                this.uiManager.activeView.navigateSelection(0);
                            } else if (!BaseScreen.b(this.selectedSubMenuIndex, 0) && !this.j() && this.parentScreen.isKeyPressed(8448)) {
                                this.uiManager.activeView.navigateSelection(1);
                            } else if (BaseScreen.I() && !this.j() && this.parentScreen.isKeyPressed(196640)) {
                                if (BaseScreen.H() && !BaseScreen.b(this.selectedSubMenuIndex, 0)) {
                                    return;
                                }
                                if (this.parentScreen instanceof BattleScreen) {
                                    int n2 = ((BattleScreen)this.parentScreen).a(this.selectedSubMenuIndex);
                                    if (n2 == 0) {
                                        this.dialogSubState = 2;
                                        this.uiManager.openUI("/data/ui/msgwarm.ui", 257, this);
                                        this.a("Sủng vật này không thể tham chiến", "Nhấn nút 5 để tiếp tục");
                                        this.uiManager.closeUI("/data/ui/petsetting.ui");
                                    } else if (n2 == 1) {
                                        this.dialogSubState = 2;
                                        this.uiManager.openUI("/data/ui/msgwarm.ui", 257, this);
                                        this.a("Sủng vật này đã đặt ở vị trí chiến đấu", "Nhấn nút 5 để tiếp tục");
                                        this.uiManager.closeUI("/data/ui/petsetting.ui");
                                    } else if (n2 == -1) {
                                        ((BattleScreen)this.parentScreen).a((int)((BattleScreen)this.parentScreen).g, 0);
                                        this.a = 0;
                                        this.parentScreen.setScreenMode((byte)15);
                                        this.uiManager.closeUI("/data/ui/petsetting.ui");
                                        this.uiManager.closeUI("/data/ui/petstate.ui");
                                    }
                                } else if (this.parentScreen instanceof WorldManager) {
                                    if (this.parentScreen.Q == 16) {
                                        if (this.player.z()) {
                                            if (this.player.o(this.selectedSubMenuIndex)) {
                                                this.player.consumeBall(this.player.petParty[this.selectedSubMenuIndex].c[5]);
                                                this.player.petParty[this.selectedSubMenuIndex].c[5] = -1;
                                                this.player.b(this.player.petParty[this.selectedSubMenuIndex].P());
                                                this.player.m(this.selectedSubMenuIndex);
                                                if (this.selectedSubMenuIndex >= this.player.partyPetCount) {
                                                    --this.selectedSubMenuIndex;
                                                }
                                                this.e(this.selectedSubMenuIndex);
                                            } else {
                                                this.dialogSubState = 1;
                                                this.uiManager.openUI("/data/ui/msgwarm.ui", 257, this);
                                                this.a("Ba lô phải lưu ít nhất 1 sủng vật", "Nhấn nút 5 để tiếp tục");
                                            }
                                        } else {
                                            this.dialogSubState = 1;
                                            this.uiManager.openUI("/data/ui/msgwarm.ui", 257, this);
                                            this.a("Ngân hàng đã đầy, không thể gởi lại", "Nhấn nút 5 để tiếp tục");
                                        }
                                    } else if (this.parentScreen.Q == 6 || this.parentScreen.Q == 0) {
                                        this.selectedShopIndex = 0;
                                        this.parentScreen.m();
                                        this.dialogSubState = 1;
                                        this.uiManager.openUI("/data/ui/petsetting.ui", 257, this);
                                        ((MenuWidget)this.uiManager.activeView.a((int)0)).a.f = this.selectedShopIndex;
                                        if (this.player.petParty[this.selectedSubMenuIndex].R() == 2) {
                                            this.uiManager.activeView.a((int)9).h().a = "Dị hoá";
                                            ((MenuWidget)this.uiManager.activeView.a((int)0)).a.a = 6;
                                            ((MenuWidget)this.uiManager.activeView.a((int)0)).a.d = 6;
                                        } else if (this.player.petParty[this.selectedSubMenuIndex].R() == 1) {
                                            this.uiManager.activeView.a((int)9).h().a = "Tiến hóa";
                                            ((MenuWidget)this.uiManager.activeView.a((int)0)).a.a = 6;
                                            ((MenuWidget)this.uiManager.activeView.a((int)0)).a.d = 6;
                                        } else {
                                            this.uiManager.activeView.a((int)9).h().a = "";
                                            ((MenuWidget)this.uiManager.activeView.a((int)0)).a.a = 5;
                                            ((MenuWidget)this.uiManager.activeView.a((int)0)).a.d = 5;
                                        }
                                    } else if (this.parentScreen.Q == 27) {
                                        if (this.d == 1 && this.player.petParty[this.selectedSubMenuIndex].R() == 1 || this.d == 2 && this.player.petParty[this.selectedSubMenuIndex].R() == 2) {
                                            this.bg();
                                        } else {
                                            this.dialogSubState = 4;
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
                            } else if (game.OverworldScreen.J() && !this.j() && this.parentScreen.isKeyPressed(262144 /* MASK_SOFT_RIGHT */)) {
                                if (this.parentScreen instanceof WorldManager) {
                                    if (this.parentScreen.Q == 16) {
                                        this.parentScreen.setScreenMode((byte)16);
                                    } else if (this.parentScreen.Q == 6) {
                                        this.selectedSubMenuIndex = BaseScreen.isVipUnlocked ? 1 : 0;
                                        this.parentScreen.setScreenMode((byte)6);
                                    } else if (this.parentScreen.Q == 27) {
                                        this.parentScreen.setScreenMode((byte)27);
                                    } else if (this.parentScreen.Q == 0) {
                                        this.parentScreen.setScreenMode((byte)23);
                                    }
                                    this.uiManager.closeUI("/data/ui/petstate.ui");
                                } else if (this.parentScreen instanceof BattleScreen) {
                                    if (((BattleScreen)this.parentScreen).Q == 7 || ((BattleScreen)this.parentScreen).Q == 13) {
                                        return;
                                    }
                                    this.uiManager.closeUI("/data/ui/petstate.ui");
                                    game.BattleScreen.getInstance().k = false;
                                    this.a = 0;
                                    this.parentScreen.setScreenMode((byte)20);
                                }
                            }
                            break block132;
                        }
                        if (this.dialogSubState != 1) break block133;
                        if (!BaseScreen.b(this.selectedShopIndex, 0) && !this.j() && this.parentScreen.isKeyPressed(4100)) {
                            this.uiManager.activeView.navigateSelection(0);
                        } else if (!BaseScreen.b(this.selectedShopIndex, 0) && !this.j() && this.parentScreen.isKeyPressed(8448)) {
                            this.uiManager.activeView.navigateSelection(1);
                        } else if (BaseScreen.I() && !this.j() && this.parentScreen.isKeyPressed(196640)) {
                            if (BaseScreen.H() && !BaseScreen.b(this.selectedShopIndex, 0)) {
                                return;
                            }
                            if (this.parentScreen.Q == 16) {
                                this.parentScreen.setScreenMode((byte)16);
                                this.uiManager.closeUI("/data/ui/msgwarm.ui");
                                this.uiManager.closeUI("/data/ui/petstate.ui");
                                this.dialogSubState = 0;
                            } else if (this.parentScreen.Q == 6 || this.parentScreen.Q == 0) {
                                switch (this.selectedShopIndex) {
                                    case 0: {
                                        ScriptEngine h2 = this;
                                        this.dialogSubState = 2;
                                        h2.r = 0;
                                        h2.p.a("/data/ui/choice.ui", 257, h2);
                                        h2.p.closeUI("/data/ui/petsetting.ui");
                                        h2.p.closeUI("/data/ui/petstate.ui");
                                        h2.p.activeView.a((int)8).h().a = "Đạo cụ";
                                        h2.p.activeView.a((int)9).h().a = "Số lượng";
                                        if (h2.o instanceof WorldManager) {
                                            h2.p.activeView.a(5).a(false);
                                            h2.p.activeView.a(6).a(false);
                                            h2.p.activeView.a(59).a(true);
                                            h2.p.activeView.a(60).a(true);
                                            h2.p.activeView.a((int)59).h().a = "Sử dụng";
                                        } else {
                                            h2.p.activeView.a(5).a(true);
                                            h2.p.activeView.a(6).a(true);
                                            h2.p.activeView.a(59).a(false);
                                            h2.p.activeView.a(60).a(false);
                                            h2.p.activeView.a((int)5).h().a = "Sử dụng";
                                        }
                                        h2.be();
                                        h2.g = true;
                                        break;
                                    }
                                    case 1: {
                                        if (!this.player.petParty[this.selectedSubMenuIndex].S()) {
                                            this.dialogSubState = 2;
                                            this.uiManager.openUI("/data/ui/msgwarm.ui", 257, this);
                                            this.a("Sủng vật này không thể tham chiến", "Nhấn nút 5 để tiếp tục");
                                            this.uiManager.closeUI("/data/ui/petsetting.ui");
                                            this.selectedSubMenuIndex = 0;
                                            break;
                                        }
                                        if (this.selectedSubMenuIndex == 0) {
                                            this.dialogSubState = 2;
                                            this.selectedSubMenuIndex = 0;
                                            this.uiManager.openUI("/data/ui/msgwarm.ui", 257, this);
                                            this.a("Sủng vật này đã xuất chiến", "Nhấn nút 5 để tiếp tục");
                                            this.uiManager.closeUI("/data/ui/petsetting.ui");
                                            break;
                                        }
                                        this.player.p(this.selectedSubMenuIndex);
                                        this.dialogSubState = 0;
                                        this.selectedSubMenuIndex = 0;
                                        this.e(this.selectedSubMenuIndex);
                                        this.uiManager.closeUI("/data/ui/petsetting.ui");
                                        ((MenuWidget)this.uiManager.activeView.a((int)0)).a.f = 0;
                                        ((MenuWidget)this.uiManager.activeView.a((int)0)).a.e = 0;
                                        break;
                                    }
                                    case 2: {
                                        this.parentScreen.m();
                                        ScriptEngine h3 = this;
                                        this.dialogSubState = 2;
                                        h3.r = 0;
                                        h3.p.a("/data/ui/choice.ui", 257, h3);
                                        h3.p.closeUI("/data/ui/petsetting.ui");
                                        h3.p.closeUI("/data/ui/petstate.ui");
                                        h3.p.activeView.a((int)8).h().a = "Vật phẩm trang sức";
                                        h3.p.activeView.a((int)9).h().a = "Trạng thái";
                                        if (h3.o instanceof WorldManager) {
                                            h3.p.activeView.a(5).a(false);
                                            h3.p.activeView.a(6).a(false);
                                            h3.p.activeView.a(59).a(true);
                                            h3.p.activeView.a(60).a(true);
                                            h3.p.activeView.a((int)59).h().a = "Mang theo";
                                        } else {
                                            h3.p.activeView.a(5).a(true);
                                            h3.p.activeView.a(6).a(true);
                                            h3.p.activeView.a(59).a(false);
                                            h3.p.activeView.a(60).a(false);
                                            h3.p.activeView.a((int)5).h().a = "Mang theo";
                                        }
                                        h3.bd();
                                        h3.g = true;
                                        break;
                                    }
                                    case 3: {
                                        if (GameDatabase.spriteTable((byte)0, (short)this.player.petParty[this.selectedSubMenuIndex].q(), (byte)22) == 2) {
                                            this.dialogSubState = 3;
                                            this.E();
                                            this.uiManager.closeUI("/data/ui/petsetting.ui");
                                            this.a("Thần thú không thể phóng sinh", "Nhấn nút 5 để tiếp tục");
                                            break;
                                        }
                                        this.dialogSubState = 2;
                                        this.uiManager.openUI("/data/ui/msgconfirm.ui", 257, this);
                                        this.uiManager.closeUI("/data/ui/petsetting.ui");
                                        this.b("Bạn muốn phóng sinh sủng vật này?", "Xác nhận");
                                        break;
                                    }
                                    case 4: {
                                        ScriptEngine h4 = this;
                                        this.dialogSubState = 2;
                                        h4.r = 0;
                                        h4.p.a("/data/ui/skill.ui", 257, h4);
                                        h4.p.closeUI("/data/ui/petsetting.ui");
                                        h4.p.closeUI("/data/ui/petstate.ui");
                                        h4.p.activeView.a((int)12).h().a = BaseScreen.f(h4.q.z[h4.b].j((byte)0));
                                        h4.p.activeView.a((int)14).h().a = "" + h4.q.z[h4.b].getLevel();
                                        if (h4.p.activeView.a((int)16).h().m != null) {
                                            h4.p.activeView.a((int)16).h().m.d();
                                        } else {
                                            h4.p.activeView.a((int)16).h().m = new SpriteWidget();
                                            h4.p.activeView.a((int)16).h().m.a(0);
                                            h4.p.activeView.a((int)16).h().m.a = (byte)3;
                                        }
                                        h4.p.activeView.a((int)16).h().m.a(h4.q.z[h4.b].C, false, (byte)-1);
                                        int n3 = h4.q.z[h4.b].E();
                                        for (int i = 0; i < n3; ++i) {
                                            h4.p.activeView.a((int)(i + 18)).h().a = BaseScreen.f(GameDatabase.gameDatabase[1][h4.q.z[h4.b].t(i)][1]);
                                        }
                                        h4.bf();
                                        h4.g = true;
                                        break;
                                    }
                                    case 5: {
                                        this.parentScreen.m();
                                        this.bg();
                                    }
                                    default: {
                                        break;
                                    }
                                }
                            }
                        } else if (game.OverworldScreen.J() && !this.j() && this.parentScreen.isKeyPressed(262144 /* MASK_SOFT_RIGHT */)) {
                            if (this.parentScreen.Q == 16) {
                                return;
                            }
                            this.dialogSubState = 0;
                            this.uiManager.closeUI("/data/ui/petsetting.ui");
                        }
                        break block132;
                    }
                    if (this.dialogSubState < 2) break block132;
                    if (!(this.parentScreen instanceof BattleScreen)) break block134;
                    if (this.parentScreen.isKeyPressed(196640)) {
                        this.dialogSubState = 0;
                        this.uiManager.closeUI("/data/ui/msgwarm.ui");
                    }
                    break block132;
                }
                if (this.parentScreen.Q != 6 && this.parentScreen.Q != 0) break block135;
                block8 : switch (this.selectedShopIndex) {
                    case 1: {
                        if (this.parentScreen.isKeyPressed(196640)) {
                            this.dialogSubState = 0;
                            this.uiManager.closeUI("/data/ui/msgwarm.ui");
                            break;
                        }
                        break block132;
                    }
                    case 2: {
                        ScriptEngine h5 = this;
                        if (!BaseScreen.b(h5.b, 0) && !h5.j() && h5.f == 2 && h5.o.k(4100)) {
                            h5.p.activeView.navigateSelection(0);
                            h5.bd();
                            break;
                        }
                        if (!BaseScreen.b(h5.b, 0) && !h5.j() && h5.f == 2 && h5.o.k(8448)) {
                            h5.p.activeView.navigateSelection(1);
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
                                h5.p.closeUI("/data/ui/choice.ui");
                                break;
                            }
                        } else if (game.OverworldScreen.J() && !h5.j() && h5.f == 2 && h5.o.k(262144 /* MASK_SOFT_RIGHT */)) {
                            h5.e(h5.b);
                            h5.p.closeUI("/data/ui/choice.ui");
                            break;
                        }
                        break block132;
                    }
                    case 0: {
                        ScriptEngine h6 = this;
                        if (h6.f == 2 && h6.o.k(4100)) {
                            h6.p.activeView.navigateSelection(0);
                            break;
                        }
                        if (h6.f == 2 && h6.o.k(8448)) {
                            h6.p.activeView.navigateSelection(1);
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
                                    h6.p.closeUI("/data/ui/choice.ui");
                                    break;
                                }
                                if (h6.f == 3) {
                                    h6.f = 2;
                                    h6.p.closeUI("/data/ui/msgwarm.ui");
                                    break;
                                }
                                if (h6.f == 4) {
                                    h6.f = 0;
                                    h6.p.closeUI("/data/ui/msgwarm.ui");
                                    break;
                                }
                            }
                        } else if (h6.f == 2 && h6.o.k(262144 /* MASK_SOFT_RIGHT */)) {
                            h6.e(h6.b);
                            h6.p.closeUI("/data/ui/choice.ui");
                            break;
                        }
                        break block132;
                    }
                    case 4: {
                        ScriptEngine h7 = this;
                        if (h7.o.k(4100)) {
                            h7.p.activeView.navigateSelection(0);
                            h7.bf();
                            break;
                        }
                        if (h7.o.k(8448)) {
                            h7.p.activeView.navigateSelection(1);
                            h7.bf();
                            break;
                        }
                        if (h7.o.k(16400)) {
                            h7.p.activeView.navigateSelection(2);
                            h7.bf();
                            break;
                        }
                        if (h7.o.k(32832)) {
                            h7.p.activeView.navigateSelection(3);
                            h7.bf();
                            break;
                        }
                        if (h7.o.k(262144 /* MASK_SOFT_RIGHT */)) {
                            h7.e(h7.b);
                            h7.p.closeUI("/data/ui/skill.ui");
                            break;
                        }
                        break block132;
                    }
                    case 3: {
                        if (this.parentScreen.isKeyPressed(131072 /* MASK_SOFT_LEFT */) && this.dialogSubState == 2 || this.parentScreen.isKeyPressed(131104) && this.dialogSubState == 3) {
                            if (this.dialogSubState == 2) {
                                if (this.player.o(this.selectedSubMenuIndex)) {
                                    this.player.consumeBall(this.player.petParty[this.selectedSubMenuIndex].c[5]);
                                    this.player.petParty[this.selectedSubMenuIndex].c[5] = -1;
                                    this.player.m(this.selectedSubMenuIndex);
                                    if (this.selectedSubMenuIndex >= this.player.partyPetCount) {
                                        --this.selectedSubMenuIndex;
                                    }
                                    ((WorldManager)this.parentScreen).M.i();
                                    this.e(this.selectedSubMenuIndex);
                                    this.uiManager.closeUI("/data/ui/msgconfirm.ui");
                                    this.dialogSubState = 0;
                                    break;
                                }
                                this.dialogSubState = 3;
                                this.uiManager.openUI("/data/ui/msgwarm.ui", 257, this);
                                this.a("Ba lô phải lưu ít nhất 1 sủng vật", "Nhấn nút 5 để tiếp tục");
                                this.uiManager.closeUI("/data/ui/msgconfirm.ui");
                                break;
                            }
                            this.dialogSubState = 0;
                            this.uiManager.closeUI("/data/ui/msgwarm.ui");
                            break;
                        }
                        if (this.parentScreen.isKeyPressed(786432) && this.dialogSubState <= 2) {
                            this.dialogSubState = 0;
                            this.uiManager.closeUI("/data/ui/msgconfirm.ui");
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
            if (this.dialogSubState <= 3) {
                this.bh();
            } else if (this.dialogSubState == 4 && this.parentScreen.isKeyPressed(196640)) {
                this.dialogSubState = 0;
                this.uiManager.closeUI("/data/ui/msgwarm.ui");
            }
        }
        this.isDialogActive = true;
        this.g();
    }

    private void bd() {
        if (this.player.L.size() > 5) {
            ((MenuWidget)this.uiManager.activeView.a((int)0)).a.a(1);
        } else {
            ((MenuWidget)this.uiManager.activeView.a((int)0)).a.a(0);
        }
        ((MenuWidget)this.uiManager.activeView.a((int)0)).a.a = this.player.L.size();
        this.w = ((MenuWidget)this.uiManager.activeView.a((int)0)).a.e;
        this.h = ((MenuWidget)this.uiManager.activeView.a((int)0)).a.f;
        if (this.h >= this.player.L.size()) {
            ((MenuWidget)this.uiManager.activeView.a((int)0)).a.f = this.h = this.player.L.size() - 1;
        }
        if (this.w > 0 && this.h - this.w < 4) {
            --this.w;
            ((MenuWidget)this.uiManager.activeView.a((int)0)).a.e = this.w;
        }
        if (this.player.L.size() <= 0) {
            return;
        }
        if (this.player.petParty[this.selectedSubMenuIndex].c[5] == ((int[])this.player.L.elementAt(this.h))[0]) {
            if (this.parentScreen instanceof WorldManager) {
                this.uiManager.activeView.a((int)59).h().a = "Dỡ xuống";
            } else {
                this.uiManager.activeView.a((int)5).h().a = "Dỡ xuống";
            }
        } else if (this.parentScreen instanceof WorldManager) {
            this.uiManager.activeView.a((int)59).h().a = "Mang theo";
        } else {
            this.uiManager.activeView.a((int)5).h().a = "Mang theo";
        }
        for (int i = 0; i < 5; ++i) {
            if (this.w + i < this.player.L.size()) {
                int[] intArray = (int[])this.player.L.elementAt(this.w + i);
                if (this.uiManager.activeView.a((int)(i + 54)).h().m == null) {
                    this.uiManager.activeView.a((int)(i + 54)).h().m = new SpriteWidget();
                    this.uiManager.activeView.a((int)(i + 54)).h().m.a(0);
                    this.uiManager.activeView.a((int)(i + 54)).h().m.a = (byte)2;
                    this.uiManager.activeView.a((int)(i + 54)).h().m.a(258, false, (byte)-1);
                }
                this.uiManager.activeView.a((int)(i + 54)).h().m.a(GameDatabase.gameDatabase[3][intArray[0]][1]);
                this.uiManager.activeView.a((int)(13 + i * 5)).h().a = BaseScreen.f(GameDatabase.gameDatabase[3][intArray[0]][0]);
                if (game.Player.getInstance().petParty[this.selectedSubMenuIndex].c[5] == intArray[0]) {
                    this.uiManager.activeView.a((int)(14 + i * 5)).h().a = "Đã mang theo";
                    continue;
                }
                if (intArray[1] == 1) {
                    this.uiManager.activeView.a((int)(14 + i * 5)).h().a = "Bị mang theo";
                    continue;
                }
                this.uiManager.activeView.a((int)(14 + i * 5)).h().a = "";
                continue;
            }
            if (this.uiManager.activeView.a((int)(i + 54)).h().m != null) {
                this.uiManager.activeView.a((int)(i + 54)).h().m.d();
            }
            this.uiManager.activeView.a((int)(13 + i * 5)).h().a = "";
            this.uiManager.activeView.a((int)(14 + i * 5)).h().a = "";
        }
        this.uiManager.activeView.a((int)53).h().a = this.player.L.size() > 0 ? BaseScreen.f(GameDatabase.gameDatabase[3][((int[])this.player.L.elementAt(this.h))[0]][2]) : "";
        if (this.player.L.size() > 0) {
            this.uiManager.activeView.a(51).b(98 + this.h * 62 / this.player.L.size(), this.uiManager.activeView.a());
            return;
        }
        this.uiManager.activeView.a(51).b(98, this.uiManager.activeView.a());
    }

    private void be() {
        if (this.player.J.size() > 5) {
            ((MenuWidget)this.uiManager.activeView.a((int)0)).a.a(1);
        } else {
            ((MenuWidget)this.uiManager.activeView.a((int)0)).a.a(0);
        }
        ((MenuWidget)this.uiManager.activeView.a((int)0)).a.a = this.player.J.size();
        this.w = ((MenuWidget)this.uiManager.activeView.a((int)0)).a.e;
        this.h = ((MenuWidget)this.uiManager.activeView.a((int)0)).a.f;
        if (this.h >= this.player.J.size()) {
            ((MenuWidget)this.uiManager.activeView.a((int)0)).a.f = this.h = this.player.J.size() - 1;
        }
        if (this.w > 0 && this.h - this.w < 4) {
            --this.w;
            ((MenuWidget)this.uiManager.activeView.a((int)0)).a.e = this.w;
        }
        for (int i = 0; i < 5; ++i) {
            if (this.w + i < this.player.J.size()) {
                int[] intArray = (int[])this.player.J.elementAt(this.w + i);
                if (this.uiManager.activeView.a((int)(i + 54)).h().m == null) {
                    this.uiManager.activeView.a((int)(i + 54)).h().m = new SpriteWidget();
                    this.uiManager.activeView.a((int)(i + 54)).h().m.a(0);
                    this.uiManager.activeView.a((int)(i + 54)).h().m.a = (byte)2;
                    this.uiManager.activeView.a((int)(i + 54)).h().m.a(258, false, (byte)-1);
                }
                this.uiManager.activeView.a((int)(i + 54)).h().m.a(GameDatabase.gameDatabase[4][intArray[0]][1]);
                this.uiManager.activeView.a((int)(13 + i * 5)).h().a = BaseScreen.f(GameDatabase.gameDatabase[4][intArray[0]][0]);
                this.uiManager.activeView.a((int)(14 + i * 5)).h().a = "" + intArray[1];
                continue;
            }
            if (this.uiManager.activeView.a((int)(i + 54)).h().m != null) {
                this.uiManager.activeView.a((int)(i + 54)).h().m.d();
            }
            this.uiManager.activeView.a((int)(13 + i * 5)).h().a = "";
            this.uiManager.activeView.a((int)(14 + i * 5)).h().a = "";
        }
        this.uiManager.activeView.a((int)53).h().a = this.player.J.size() > 0 ? BaseScreen.f(GameDatabase.gameDatabase[4][((int[])this.player.J.elementAt(this.h))[0]][2]) : "";
        if (this.player.J.size() > 0) {
            this.uiManager.activeView.a(51).b(98 + this.h * 72 / this.player.J.size(), this.uiManager.activeView.a());
            return;
        }
        this.uiManager.activeView.a(51).b(98, this.uiManager.activeView.a());
    }

    private void bf() {
        if (this.player.petParty[this.selectedSubMenuIndex].t(this.r) != -1) {
            String[] strArray = new String[]{"Nhất định", "Nhất định"};
            this.uiManager.activeView.a((int)9).h().a = BaseScreen.a((int)GameDatabase.gameDatabase[1][this.player.petParty[this.selectedSubMenuIndex].t(this.r)][2], strArray);
            return;
        }
        this.uiManager.activeView.a((int)9).h().a = "";
    }

    private void bg() {
        this.dialogSubState = 2;
        this.r = 0;
        this.uiManager.openUI("/data/ui/evolve.ui", 257, this);
        this.uiManager.closeUI("/data/ui/petsetting.ui");
        this.uiManager.closeUI("/data/ui/petstate.ui");
        if (this.uiManager.activeView.a((int)10).h().m == null) {
            this.uiManager.activeView.a((int)10).h().m = new SpriteWidget();
            this.uiManager.activeView.a((int)10).h().m.a(0);
            this.uiManager.activeView.a((int)10).h().m.a = (byte)3;
        }
        this.uiManager.activeView.a((int)10).h().m.a(this.player.petParty[this.selectedSubMenuIndex].C, false, (byte)-1);
        int n2 = GameDatabase.spriteTable((byte)0, (byte)this.player.petParty[this.selectedSubMenuIndex].q(), (byte)20) + 12;
        short s2 = GameDatabase.spriteTable((byte)0, (byte)this.player.petParty[this.selectedSubMenuIndex].q(), (byte)21);
        this.uiManager.activeView.a((int)38).h().a = BaseScreen.f(GameDatabase.spriteTable((byte)0, (byte)this.player.petParty[this.selectedSubMenuIndex].q(), (byte)0));
        this.uiManager.activeView.a((int)40).h().a = "" + this.player.petParty[this.selectedSubMenuIndex].getLevel();
        this.uiManager.activeView.a((int)45).h().a = BaseScreen.f(GameDatabase.spriteTable((byte)3, (short)n2, (byte)0));
        this.uiManager.activeView.a((int)46).h().a = this.player.a(n2, (byte)2) + "/" + s2;
        n2 = GameDatabase.spriteTable((byte)0, (byte)this.player.petParty[this.selectedSubMenuIndex].q(), (byte)19);
        Pet b2 = new Pet();
        b2.a(n2, (byte)this.player.petParty[this.selectedSubMenuIndex].getLevel(), (short)-1, (byte)-1, (short)-1, (byte)-1);
        for (n2 = 0; n2 < 4; ++n2) {
            byte by = (byte)(n2 + 1);
            this.uiManager.activeView.a((int)(n2 + 19)).h().a = "" + this.player.petParty[this.selectedSubMenuIndex].c[by];
            by = (byte)(n2 + 1);
            this.uiManager.activeView.a((int)(n2 + 31)).h().a = "" + b2.c[by];
        }
        this.isDialogActive = true;
    }

    private void bh() {
        if (game.WorldManager.n != null) {
            if (!game.WorldManager.n.i()) {
                short s2 = GameDatabase.spriteTable((byte)0, (byte)this.player.petParty[this.selectedSubMenuIndex].q(), (byte)19);
                String string = BaseScreen.f(GameDatabase.spriteTable((byte)0, s2, (byte)0));
                ScriptEngine h2 = this;
                short s3 = GameDatabase.spriteTable((byte)0, (byte)h2.q.z[h2.b].q(), (byte)19);
                short s4 = GameDatabase.spriteTable((byte)0, s3, (byte)17);
                h2.p.activeView.a(10).a(true);
                h2.p.activeView.a((int)10).h().m.a(s4, false, (byte)-1);
                h2.p.activeView.a((int)38).h().a = BaseScreen.f(GameDatabase.spriteTable((byte)0, s3, (byte)0));
                Pet b2 = new Pet();
                short s5 = GameDatabase.spriteTable((byte)0, s3, (byte)3);
                int n2 = -1;
                if (h2.q.z[h2.b].c[0] >= s5) {
                    n2 = (byte)h2.q.z[h2.b].c[0];
                }
                b2.a(s3, h2.q.z[h2.b].getLevel(), h2.q.z[h2.b].c[5], (byte)h2.q.z[h2.b].d[6], (short)n2, (byte)-1);
                b2.a(b2.c[1], h2.q.z[h2.b].z(), (int)h2.q.z[h2.b].E);
                b2.b(h2.q.z[h2.b].Q());
                h2.q.a((byte)h2.q.z[h2.b].j((byte)1), (int)s3, (byte)2);
                h2.q.z[h2.b].a(b2.P());
                s5 = (short)(GameDatabase.spriteTable((byte)0, (byte)h2.q.z[h2.b].q(), (byte)20) + 12);
                short s6 = GameDatabase.spriteTable((byte)0, (byte)h2.q.z[h2.b].q(), (byte)21);
                s3 = GameDatabase.spriteTable((byte)0, (byte)h2.q.z[h2.b].q(), (byte)19);
                n2 = h2.q.a((int)s5, (byte)2);
                if (s3 == -1) {
                    h2.p.activeView.a((int)42).h().a = "";
                    h2.p.activeView.a((int)45).h().a = "";
                    h2.p.activeView.a((int)46).h().a = "";
                } else {
                    h2.p.activeView.a((int)45).h().a = BaseScreen.f(GameDatabase.spriteTable((byte)3, s5, (byte)0));
                    h2.p.activeView.a((int)46).h().a = n2 + "/" + s6;
                }
                if (this.player.petParty[this.selectedSubMenuIndex].R() == 2) {
                    this.dialogSubState = 3;
                    this.uiManager.openUI("/data/ui/msgwarm.ui", 257, this);
                    this.a("Dị hoá thành #2" + string, "Nhấn nút 5 để tiếp tục");
                } else {
                    this.dialogSubState = 3;
                    this.uiManager.openUI("/data/ui/msgwarm.ui", 257, this);
                    this.a("Tiến hóa thành #2" + string, "Nhấn nút 5 để tiếp tục");
                }
                game.WorldManager.instance = null;
            }
            return;
        }
        if (BaseScreen.I() && !this.j() && this.parentScreen.isKeyPressed(196640)) {
            if (this.dialogSubState == 2) {
                short s7 = (short)(GameDatabase.spriteTable((byte)0, (byte)this.player.petParty[this.selectedSubMenuIndex].q(), (byte)20) + 12);
                short s8 = GameDatabase.spriteTable((byte)0, (byte)this.player.petParty[this.selectedSubMenuIndex].q(), (byte)21);
                short s9 = GameDatabase.spriteTable((byte)0, (byte)this.player.petParty[this.selectedSubMenuIndex].q(), (byte)19);
                if (s9 == -1) {
                    this.dialogSubState = 3;
                    this.E();
                    this.a("Không thể lại tiến hóa hoặc dị hoá", "Nhấn nút 5 để tiếp tục");
                    return;
                }
                short s10 = GameDatabase.spriteTable((byte)0, s9, (byte)17);
                if (this.player.petParty[this.selectedSubMenuIndex].getLevel() >= game.Pet.t[GameDatabase.spriteTable((byte)0, s9, (byte)2) - 1]) {
                    if (this.player.a((int)s7, (byte)2) >= s8) {
                        this.uiManager.activeView.a(10).a(false);
                        game.WorldManager.n = new SkillEffect();
                        short[] shortArray = new short[]{0, 0, 10, 0, 0, this.player.petParty[this.selectedSubMenuIndex].C, 0, 0, s10, 0, 0};
                        game.WorldManager.n.a(shortArray);
                        game.WorldManager.n.c(true);
                        game.WorldManager.n.a();
                        this.player.d(s7, s8, (byte)2);
                        return;
                    }
                    this.dialogSubState = 3;
                    this.uiManager.openUI("/data/ui/msgwarm.ui", 257, this);
                    if (this.player.petParty[this.selectedSubMenuIndex].R() == 2) {
                        this.a("Tài liệu chưa đủ, không thể dị hoá", "Nhấn nút 5 để tiếp tục");
                        return;
                    }
                    this.a("Tài liệu chưa đủ, không thể tiến hóa", "Nhấn nút 5 để tiếp tục");
                    return;
                }
                this.dialogSubState = 3;
                this.uiManager.openUI("/data/ui/msgwarm.ui", 257, this);
                this.a("Còn chưa tới" + game.Pet.t[GameDatabase.spriteTable((byte)0, s9, (byte)2) - 1] + " cấp, không thể vào hóa", "Nhấn nút 5 để tiếp tục");
                return;
            }
            if (this.dialogSubState == 3) {
                if (this.parentScreen.Q == 6 || this.parentScreen.Q == 0) {
                    this.dialogSubState = 2;
                    this.uiManager.closeUI("/data/ui/msgwarm.ui");
                    this.parentScreen.m();
                    return;
                }
                if (this.parentScreen.Q == 27) {
                    this.e(this.selectedSubMenuIndex);
                    this.dialogSubState = 0;
                    this.selectedShopIndex = 0;
                    this.uiManager.closeUI("/data/ui/msgwarm.ui");
                    this.uiManager.closeUI("/data/ui/evolve.ui");
                    return;
                }
            }
        } else if (this.dialogSubState < 3 && this.parentScreen.isKeyPressed(262144 /* MASK_SOFT_RIGHT */) && !this.j() && BaseScreen.J()) {
            this.dialogSubState = 0;
            this.e(this.selectedSubMenuIndex);
            this.uiManager.closeUI("/data/ui/evolve.ui");
        }
    }

    public final void Y() {
        this.uiManager.openUI("/data/ui/bag.ui", 257, this);
        this.selectedSubMenuIndex = 0;
        this.bi();
        this.uiManager.activeView.navigateSelection(5);
        this.uiManager.activeView.a((int)14).h().a = "Vật phẩm";
        this.selectedSubMenuIndex = 0;
    }

    private void bi() {
        ((MenuWidget)this.uiManager.activeView.a((int)(8 + this.selectedSubMenuIndex * 39))).a.e = 0;
        ((MenuWidget)this.uiManager.activeView.a((int)(8 + this.selectedSubMenuIndex * 39))).a.f = 0;
        this.bj();
    }

    private void bj() {
        block0 : switch (this.selectedSubMenuIndex) {
            case 0: {
                this.bk();
                break;
            }
            case 1: {
                ScriptEngine h2 = this;
                if (h2.q.L.size() > 5) {
                    ((MenuWidget)h2.p.activeView.a((int)47)).a.a(1);
                } else {
                    ((MenuWidget)h2.p.activeView.a((int)47)).a.a(0);
                }
                ((MenuWidget)h2.p.activeView.a((int)47)).a.a = h2.q.L.size();
                h2.w = ((MenuWidget)h2.p.activeView.a((int)47)).a.e;
                h2.h = ((MenuWidget)h2.p.activeView.a((int)47)).a.f;
                h2.p.activeView.a(7).a(false);
                for (int i = 0; i < 5; ++i) {
                    if (h2.w + i < h2.q.L.size()) {
                        int[] intArray = (int[])h2.q.L.elementAt(h2.w + i);
                        if (h2.p.activeView.a((int)(59 + i * 5)).h().m == null) {
                            h2.p.activeView.a((int)(59 + i * 5)).h().m = new SpriteWidget();
                            h2.p.activeView.a((int)(59 + i * 5)).h().m.a(0);
                            h2.p.activeView.a((int)(59 + i * 5)).h().m.a = (byte)2;
                            h2.p.activeView.a((int)(59 + i * 5)).h().m.a(258, false, (byte)-1);
                        }
                        if (h2.p.activeView.a((int)(59 + i * 5)).h().i == null) {
                            h2.p.activeView.a((int)(59 + i * 5)).h().i = new SpriteWidget();
                            h2.p.activeView.a((int)(59 + i * 5)).h().i.a(0);
                            h2.p.activeView.a((int)(59 + i * 5)).h().i.a = (byte)2;
                            h2.p.activeView.a((int)(59 + i * 5)).h().i.a(258, false, (byte)-1);
                        }
                        h2.p.activeView.a((int)(59 + i * 5)).h().m.a(GameDatabase.gameDatabase[3][intArray[0]][1]);
                        h2.p.activeView.a((int)(59 + i * 5)).h().i.a(GameDatabase.gameDatabase[3][intArray[0]][1]);
                        h2.p.activeView.a((int)(60 + i * 5)).h().a = BaseScreen.f(GameDatabase.gameDatabase[3][intArray[0]][0]);
                        if (intArray[1] == 1) {
                            h2.p.activeView.a((int)(61 + i * 5)).h().a = "Đã mang theo";
                            continue;
                        }
                        h2.p.activeView.a((int)(61 + i * 5)).h().a = "";
                        continue;
                    }
                    if (h2.p.activeView.a((int)(59 + i * 5)).h().m != null) {
                        h2.p.activeView.a((int)(59 + i * 5)).h().m.d();
                    }
                    h2.p.activeView.a((int)(60 + i * 5)).h().a = "";
                    h2.p.activeView.a((int)(61 + i * 5)).h().a = "";
                }
                h2.p.activeView.a((int)85).h().a = h2.q.L.size() > 0 ? BaseScreen.f(GameDatabase.gameDatabase[3][((int[])h2.q.L.elementAt(h2.h))[0]][2]) : "";
                if (h2.q.L.size() > 0) {
                    h2.p.activeView.a(84).b(127 + h2.h * 72 / h2.q.L.size(), h2.p.activeView.a());
                    break;
                }
                h2.p.activeView.a(84).b(127, h2.p.activeView.a());
                break;
            }
            case 2: {
                ScriptEngine h3 = this;
                if (h3.q.M.size() > 5) {
                    ((MenuWidget)h3.p.activeView.a((int)86)).a.a(1);
                } else {
                    ((MenuWidget)h3.p.activeView.a((int)86)).a.a(0);
                }
                ((MenuWidget)h3.p.activeView.a((int)86)).a.a = h3.q.M.size();
                h3.w = ((MenuWidget)h3.p.activeView.a((int)86)).a.e;
                h3.h = ((MenuWidget)h3.p.activeView.a((int)86)).a.f;
                h3.p.activeView.a(7).a(false);
                for (int i3 = 0; i3 < 5; ++i3) {
                    if (h3.w + i3 < h3.q.M.size()) {
                        int[] intArray = (int[])h3.q.M.elementAt(h3.w + i3);
                        if (h3.p.activeView.a((int)(98 + i3 * 5)).h().m == null) {
                            h3.p.activeView.a((int)(98 + i3 * 5)).h().m = new SpriteWidget();
                            h3.p.activeView.a((int)(98 + i3 * 5)).h().m.a(0);
                            h3.p.activeView.a((int)(98 + i3 * 5)).h().m.a = (byte)2;
                            h3.p.activeView.a((int)(98 + i3 * 5)).h().m.a(258, false, (byte)-1);
                        }
                        if (h3.p.activeView.a((int)(98 + i3 * 5)).h().i == null) {
                            h3.p.activeView.a((int)(98 + i3 * 5)).h().i = new SpriteWidget();
                            h3.p.activeView.a((int)(98 + i3 * 5)).h().i.a(0);
                            h3.p.activeView.a((int)(98 + i3 * 5)).h().i.a = (byte)2;
                            h3.p.activeView.a((int)(98 + i3 * 5)).h().i.a(258, false, (byte)-1);
                        }
                        h3.p.activeView.a((int)(98 + i3 * 5)).h().m.a(GameDatabase.gameDatabase[3][intArray[0]][1]);
                        h3.p.activeView.a((int)(98 + i3 * 5)).h().i.a(GameDatabase.gameDatabase[3][intArray[0]][1]);
                        h3.p.activeView.a((int)(99 + i3 * 5)).h().a = intArray[0] == 17 ? "Chìa khóa vàng" : BaseScreen.f(GameDatabase.gameDatabase[3][intArray[0]][0]);
                        h3.p.activeView.a((int)(100 + i3 * 5)).h().a = "" + intArray[1];
                        continue;
                    }
                    if (h3.p.activeView.a((int)(98 + i3 * 5)).h().m != null) {
                        h3.p.activeView.a((int)(98 + i3 * 5)).h().m.d();
                    }
                    h3.p.activeView.a((int)(99 + i3 * 5)).h().a = "";
                    h3.p.activeView.a((int)(100 + i3 * 5)).h().a = "";
                }
                h3.p.activeView.a((int)124).h().a = h3.q.M.size() > 0 ? BaseScreen.f(GameDatabase.gameDatabase[3][((int[])h3.q.M.elementAt(h3.h))[0]][2]) : "";
                if (h3.q.M.size() > 0) {
                    h3.p.activeView.a(123).b(127 + h3.h * 72 / h3.q.M.size(), h3.p.activeView.a());
                    break;
                }
                h3.p.activeView.a(123).b(127, h3.p.activeView.a());
                break;
            }
            case 3: {
                this.bl();
                if (this.h < 0 || this.player.N.size() <= 0) {
                    return;
                }
                int[] intArray = (int[])this.player.N.elementAt(this.h);
                this.uiManager.activeView.a(164).a(false);
                this.uiManager.activeView.a(165).a(false);
                switch (intArray[0]) {
                    case 0: {
                        if (this.player.hasBadge(intArray[0])) {
                            this.uiManager.activeView.a(7).a(true);
                            this.uiManager.activeView.a((int)7).h().a = "Ấp trứng";
                            this.uiManager.activeView.a(164).a(true);
                            this.uiManager.activeView.a(165).a(true);
                            if (this.player.I == 0) {
                                this.uiManager.activeView.a((int)164).h().a = "#P" + game.WorldManager.q * 100 / 10;
                                this.uiManager.activeView.a((int)165).h().a = game.WorldManager.q + "/10";
                                break block0;
                            }
                            this.uiManager.activeView.a((int)164).h().a = "#P" + game.WorldManager.q * 100 / 30;
                            this.uiManager.activeView.a((int)165).h().a = game.WorldManager.q + "/30";
                            break block0;
                        }
                        this.uiManager.activeView.a(7).a(false);
                        break block0;
                    }
                    case 1: 
                    case 2: 
                    case 3: 
                    case 4: {
                        this.uiManager.activeView.a(7).a(false);
                        break block0;
                    }
                    case 5: 
                    case 6: 
                    case 10: {
                        this.uiManager.activeView.a((int)7).h().a = "Mở ra";
                        break block0;
                    }
                    case 7: 
                    case 8: 
                    case 9: {
                        this.uiManager.activeView.a((int)7).h().a = "Sử dụng";
                    }
                }
            }
        }
        this.isDialogActive = true;
    }

    private void bk() {
        int n2 = this.player.K.size() + this.player.J.size();
        if (n2 > 5) {
            ((MenuWidget)this.uiManager.activeView.a((int)8)).a.a(1);
        } else {
            ((MenuWidget)this.uiManager.activeView.a((int)8)).a.a(0);
        }
        ((MenuWidget)this.uiManager.activeView.a((int)8)).a.a = n2;
        this.w = ((MenuWidget)this.uiManager.activeView.a((int)8)).a.e;
        this.h = ((MenuWidget)this.uiManager.activeView.a((int)8)).a.f;
        this.uiManager.activeView.a(7).a(true);
        this.uiManager.activeView.a((int)7).h().a = "Sử dụng";
        for (int i = 0; i < 5; ++i) {
            if (this.w + i < n2) {
                int[] intArray = this.w + i < this.player.K.size() ? (int[])this.player.K.elementAt(this.w + i) : (int[])this.player.J.elementAt(this.w + i - this.player.K.size());
                if (this.uiManager.activeView.a((int)(18 + i * 5)).h().m == null) {
                    this.uiManager.activeView.a((int)(18 + i * 5)).h().m = new SpriteWidget();
                    this.uiManager.activeView.a((int)(18 + i * 5)).h().m.a(0);
                    this.uiManager.activeView.a((int)(18 + i * 5)).h().m.a = (byte)2;
                    this.uiManager.activeView.a((int)(18 + i * 5)).h().m.a(258, false, (byte)-1);
                }
                if (this.uiManager.activeView.a((int)(18 + i * 5)).h().i == null) {
                    this.uiManager.activeView.a((int)(18 + i * 5)).h().i = new SpriteWidget();
                    this.uiManager.activeView.a((int)(18 + i * 5)).h().i.a(0);
                    this.uiManager.activeView.a((int)(18 + i * 5)).h().i.a = (byte)2;
                    this.uiManager.activeView.a((int)(18 + i * 5)).h().i.a(258, false, (byte)-1);
                }
                this.uiManager.activeView.a((int)(18 + i * 5)).h().m.a(GameDatabase.gameDatabase[4][intArray[0]][1]);
                this.uiManager.activeView.a((int)(18 + i * 5)).h().i.a(GameDatabase.gameDatabase[4][intArray[0]][1]);
                this.uiManager.activeView.a((int)(19 + i * 5)).h().a = BaseScreen.f(GameDatabase.gameDatabase[4][intArray[0]][0]);
                this.uiManager.activeView.a((int)(20 + i * 5)).h().a = "" + intArray[1];
                continue;
            }
            if (this.uiManager.activeView.a((int)(18 + i * 5)).h().m != null) {
                this.uiManager.activeView.a((int)(18 + i * 5)).h().m.d();
            }
            this.uiManager.activeView.a((int)(19 + i * 5)).h().a = "";
            this.uiManager.activeView.a((int)(20 + i * 5)).h().a = "";
        }
        this.uiManager.activeView.a((int)46).h().a = n2 > 0 ? (this.h < this.player.K.size() ? BaseScreen.f(GameDatabase.gameDatabase[4][((int[])this.player.K.elementAt(this.h))[0]][2]) : BaseScreen.f(GameDatabase.gameDatabase[4][((int[])this.player.J.elementAt(this.h - this.player.K.size()))[0]][2])) : "";
        if (n2 > 0) {
            this.uiManager.activeView.a(43).b(127 + this.h * 72 / n2, this.uiManager.activeView.a());
            return;
        }
        this.uiManager.activeView.a(43).b(127, this.uiManager.activeView.a());
    }

    private void bl() {
        int n2;
        if (this.player.N.size() > 5) {
            ((MenuWidget)this.uiManager.activeView.a((int)125)).a.a(1);
        } else {
            ((MenuWidget)this.uiManager.activeView.a((int)125)).a.a(0);
        }
        ((MenuWidget)this.uiManager.activeView.a((int)125)).a.a = this.player.N.size();
        this.w = ((MenuWidget)this.uiManager.activeView.a((int)125)).a.e;
        this.h = ((MenuWidget)this.uiManager.activeView.a((int)125)).a.f;
        for (n2 = 0; n2 < 5; ++n2) {
            if (this.w + n2 < this.player.N.size()) {
                int[] intArray = (int[])this.player.N.elementAt(this.w + n2);
                if (this.uiManager.activeView.a((int)(137 + n2 * 5)).h().m == null) {
                    this.uiManager.activeView.a((int)(137 + n2 * 5)).h().m = new SpriteWidget();
                    this.uiManager.activeView.a((int)(137 + n2 * 5)).h().m.a(0);
                    this.uiManager.activeView.a((int)(137 + n2 * 5)).h().m.a = (byte)2;
                    this.uiManager.activeView.a((int)(137 + n2 * 5)).h().m.a(258, false, (byte)-1);
                }
                if (this.uiManager.activeView.a((int)(137 + n2 * 5)).h().i == null) {
                    this.uiManager.activeView.a((int)(137 + n2 * 5)).h().i = new SpriteWidget();
                    this.uiManager.activeView.a((int)(137 + n2 * 5)).h().i.a(0);
                    this.uiManager.activeView.a((int)(137 + n2 * 5)).h().i.a = (byte)2;
                    this.uiManager.activeView.a((int)(137 + n2 * 5)).h().i.a(258, false, (byte)-1);
                }
                this.uiManager.activeView.a((int)(137 + n2 * 5)).h().m.a(GameDatabase.gameDatabase[5][intArray[0]][1]);
                this.uiManager.activeView.a((int)(137 + n2 * 5)).h().i.a(GameDatabase.gameDatabase[5][intArray[0]][1]);
                this.uiManager.activeView.a((int)(138 + n2 * 5)).h().a = BaseScreen.f(GameDatabase.gameDatabase[5][intArray[0]][0]);
                switch (intArray[0]) {
                    case 0: {
                        if (this.player.hasBadge(intArray[0])) {
                            this.uiManager.activeView.a((int)163).h().a = BaseScreen.f(GameDatabase.gameDatabase[5][intArray[0]][2]);
                            if (game.WorldManager.getInstance().r()) {
                                this.uiManager.activeView.a((int)(139 + n2 * 5)).h().a = "Hoàn thành";
                                break;
                            }
                            this.uiManager.activeView.a((int)(139 + n2 * 5)).h().a = "1 cái";
                            break;
                        }
                        this.uiManager.activeView.a((int)163).h().a = BaseScreen.f(634);
                        this.uiManager.activeView.a((int)(139 + n2 * 5)).h().a = "0 cái";
                        break;
                    }
                    default: {
                        this.uiManager.activeView.a((int)(139 + n2 * 5)).h().a = "";
                        break;
                    }
                }
                continue;
            }
            if (this.uiManager.activeView.a((int)(137 + n2 * 5)).h().m != null) {
                this.uiManager.activeView.a((int)(137 + n2 * 5)).h().m.d();
            }
            this.uiManager.activeView.a((int)(138 + n2 * 5)).h().a = "";
            this.uiManager.activeView.a((int)(139 + n2 * 5)).h().a = "";
        }
        if (this.player.N.size() > 0) {
            n2 = ((int[])this.player.N.elementAt(this.h))[0];
            if (n2 != 0) {
                this.uiManager.activeView.a((int)163).h().a = BaseScreen.f(GameDatabase.gameDatabase[5][n2][2]);
                this.uiManager.activeView.a(7).a(true);
            }
            this.uiManager.activeView.a((int)7).h().a = n2 == 0 ? (((int[])this.player.N.elementAt(this.h))[1] == 1 ? "Đóng cửa" : "Mở ra") : (n2 > 0 || n2 <= 4 ? (this.player.t == n2 - 1 ? "Triệu hồi" : "Triệu hoán") : (n2 == 10 ? "Gia tốc" : "Sử dụng"));
        } else {
            this.uiManager.activeView.a((int)163).h().a = "";
            this.uiManager.activeView.a(7).a(false);
        }
        if (this.player.N.size() > 0) {
            this.uiManager.activeView.a(162).b(127 + this.h * 72 / this.player.N.size(), this.uiManager.activeView.a());
            return;
        }
        this.uiManager.activeView.a(162).b(127, this.uiManager.activeView.a());
    }

    public final void Z() {
        if (this.dialogSubState == 0 && this.parentScreen.isKeyPressed(4100)) {
            this.uiManager.activeView.navigateSelection(0);
            this.f(this.selectedShopIndex);
            return;
        }
        if (this.dialogSubState == 0 && this.parentScreen.isKeyPressed(8448)) {
            this.uiManager.activeView.navigateSelection(1);
            this.f(this.selectedShopIndex);
            return;
        }
        if (this.parentScreen.isKeyPressed(196640)) {
            this.bo();
            return;
        }
        if (this.dialogSubState == 0 && this.parentScreen.isKeyPressed(262144 /* MASK_SOFT_RIGHT */)) {
            this.parentScreen.setScreenMode((byte)8);
            this.uiManager.closeUI("/data/ui/petstate.ui");
        }
    }

    public final void aa() {
        if (this.parentScreen.isKeyPressed(4100)) {
            this.uiManager.activeView.navigateSelection(0);
            this.f(this.selectedSubMenuIndex);
            return;
        }
        if (this.parentScreen.isKeyPressed(8448)) {
            this.uiManager.activeView.navigateSelection(1);
            this.f(this.selectedSubMenuIndex);
            return;
        }
        if (this.parentScreen.isKeyPressed(196640)) {
            this.player.f(this.s, this.selectedSubMenuIndex);
            this.parentScreen.setScreenMode((byte)8);
            return;
        }
        if (this.parentScreen.isKeyPressed(262144 /* MASK_SOFT_RIGHT */)) {
            this.parentScreen.setScreenMode((byte)8);
            this.uiManager.closeUI("/data/ui/petstate.ui");
        }
    }

    public final void ab() {
        if (this.parentScreen.isKeyPressed(4100)) {
            this.uiManager.activeView.navigateSelection(0);
            this.f(this.selectedSubMenuIndex);
            return;
        }
        if (this.parentScreen.isKeyPressed(8448)) {
            this.uiManager.activeView.navigateSelection(1);
            this.f(this.selectedSubMenuIndex);
            return;
        }
        if (this.parentScreen.isKeyPressed(196640)) {
            if (this.dialogSubState == 0) {
                if (this.player.petParty[this.selectedSubMenuIndex].getLevel() < 50) {
                    this.uiManager.openUI("/data/ui/msgwarm.ui", 257, this);
                    this.a("Chỉ có thể cho 50 cấp sủng vật sử dụng", "Nhấn nút 5 để tiếp tục");
                    this.dialogSubState = 2;
                    return;
                }
                if (this.player.e(this.s, this.selectedSubMenuIndex)) {
                    this.uiManager.openUI("/data/ui/msgwarm.ui", 257, this);
                    this.a("Sử dụng thành công", "Nhấn nút 5 để tiếp tục");
                    this.dialogSubState = 1;
                    return;
                }
            } else {
                if (this.dialogSubState == 1) {
                    this.dialogSubState = 0;
                    this.parentScreen.setScreenMode((byte)8);
                    this.uiManager.closeUI("/data/ui/msgwarm.ui");
                    this.uiManager.closeUI("/data/ui/petstate.ui");
                    return;
                }
                if (this.dialogSubState == 2) {
                    this.dialogSubState = 0;
                    this.uiManager.closeUI("/data/ui/msgwarm.ui");
                    return;
                }
            }
        } else if (this.parentScreen.isKeyPressed(262144 /* MASK_SOFT_RIGHT */) && this.dialogSubState == 0) {
            this.parentScreen.setScreenMode((byte)8);
            this.uiManager.closeUI("/data/ui/petstate.ui");
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
                                                    this.parentScreen.isKeyHeld();
                                                    if (this.dialogSubState != 0 || !this.parentScreen.isKeyPressed(16400) || this.j() || BaseScreen.b(this.selectedSubMenuIndex, 1)) break block45;
                                                    this.uiManager.activeView.navigateSelection(7);
                                                    this.uiManager.activeView.navigateSelection(2);
                                                    this.uiManager.activeView.navigateSelection(5);
                                                    this.bi();
                                                    this.parentScreen.m();
                                                    break block46;
                                                }
                                                if (this.dialogSubState != 0 || !this.parentScreen.isKeyPressed(32832) || this.j() || BaseScreen.b(this.selectedSubMenuIndex, 1)) break block47;
                                                this.uiManager.activeView.navigateSelection(7);
                                                this.uiManager.activeView.navigateSelection(3);
                                                this.uiManager.activeView.navigateSelection(5);
                                                this.bi();
                                                this.parentScreen.m();
                                                break block46;
                                            }
                                            if (this.dialogSubState != 0 || !this.parentScreen.isKeyPressed(4100) || this.j() || BaseScreen.b(this.h, 0)) break block48;
                                            this.uiManager.activeView.navigateSelection(0);
                                            break block46;
                                        }
                                        if (this.dialogSubState != 0 || !this.parentScreen.isKeyPressed(8448) || this.j() || BaseScreen.b(this.h, 0)) break block49;
                                        this.uiManager.activeView.navigateSelection(1);
                                        break block46;
                                    }
                                    if (!this.parentScreen.isKeyPressed(196640) || this.j() || !BaseScreen.I()) break block50;
                                    if (this.dialogSubState != 0) break block51;
                                    if (BaseScreen.H() && !BaseScreen.b(this.h, 0)) {
                                        return;
                                    }
                                    block0 : switch (this.selectedSubMenuIndex) {
                                        case 0: {
                                            int[] intArray;
                                            if (this.h >= this.player.K.size()) {
                                                if (this.player.J.size() <= 0) {
                                                    return;
                                                }
                                                intArray = (int[])this.player.J.elementAt(this.h - this.player.K.size());
                                            } else {
                                                intArray = (int[])this.player.K.elementAt(this.h);
                                            }
                                            switch (intArray[0]) {
                                                case 0: 
                                                case 1: 
                                                case 2: 
                                                case 3: {
                                                    if (this.dialogSubState == 0) {
                                                        this.uiManager.openUI("/data/ui/msgwarm.ui", 257, this);
                                                        this.a("Không thể sử dụng", "Nhấn nút 5 để tiếp tục");
                                                        this.dialogSubState = 1;
                                                        break;
                                                    }
                                                    this.uiManager.closeUI("/data/ui/msgwarm.ui");
                                                    this.dialogSubState = 0;
                                                    break;
                                                }
                                                case 14: {
                                                    if (this.dialogSubState != 0) break;
                                                    if (this.player.hasBadge(0) && (this.player.I == 0 && game.WorldManager.q < 10 || this.player.I > 0 && game.WorldManager.q < 30)) {
                                                        if (!this.player.b(intArray[0], 1, (byte)0)) break;
                                                        game.WorldManager.q = this.player.I == 0 ? 10 : 30;
                                                        this.player.d(intArray[0], 1, (byte)0);
                                                        int n3 = this.player.K.size() + this.player.J.size();
                                                        if (this.h >= n3) {
                                                            ((MenuWidget)this.uiManager.activeView.a((int)8)).a.f = this.h = n3 - 1;
                                                        }
                                                        if (this.w > 0 && this.h - this.w < 4) {
                                                            --this.w;
                                                            ((MenuWidget)this.uiManager.activeView.a((int)8)).a.e = this.w;
                                                        }
                                                        this.bk();
                                                        this.uiManager.openUI("/data/ui/msgwarm.ui", 257, this);
                                                        this.a("Thành công sử dụng, tranh thủ thời gian đi ấp trứng trứng sủng vật a!", "Nhấn nút 5 để tiếp tục");
                                                        this.dialogSubState = 1;
                                                        break;
                                                    }
                                                    this.uiManager.openUI("/data/ui/msgwarm.ui", 257, this);
                                                    this.a("Không có trứng có thể ấp trứng", "Nhấn nút 5 để tiếp tục");
                                                    this.dialogSubState = 1;
                                                    break;
                                                }
                                                case 13: {
                                                    if (this.dialogSubState != 0) break;
                                                    if (this.player.x <= 0) {
                                                        if (game.WorldManager.getInstance().f == 3 && game.WorldManager.getInstance().g == 7) {
                                                            this.E();
                                                            this.a("Nơi này không cách nào sử dụng tránh quái hoàn", "Nhấn nút 5 để tiếp tục");
                                                            this.dialogSubState = 1;
                                                            break;
                                                        }
                                                        if (!this.player.b(intArray[0], 1, (byte)0)) break;
                                                        this.player.d(intArray[0], 1, (byte)0);
                                                        this.player.x = GameDatabase.gameDatabase[4][intArray[0]][6];
                                                        this.player.w = 0;
                                                        int n4 = this.player.K.size() + this.player.J.size();
                                                        if (this.h >= n4) {
                                                            ((MenuWidget)this.uiManager.activeView.a((int)8)).a.f = this.h = n4 - 1;
                                                        }
                                                        if (this.w > 0 && this.h - this.w < 4) {
                                                            --this.w;
                                                            ((MenuWidget)this.uiManager.activeView.a((int)8)).a.e = this.w;
                                                        }
                                                        this.bk();
                                                        this.E();
                                                        this.player.c(1);
                                                        this.a("Thành công sử dụng đạo cụ, cũng có thời gian ngắn tránh quái hiệu quả", "Nhấn nút 5 để tiếp tục");
                                                        this.dialogSubState = 1;
                                                        break;
                                                    }
                                                    this.E();
                                                    this.a("Đã có được thời gian ngắn tránh quái hiệu quả", "Nhấn nút 5 để tiếp tục");
                                                    this.dialogSubState = 1;
                                                    break;
                                                }
                                                default: {
                                                    this.s = intArray[0];
                                                    this.parentScreen.setScreenMode((byte)17);
                                                    this.uiManager.closeUI("/data/ui/bag.ui");
                                                    break;
                                                }
                                            }
                                            break;
                                        }
                                        case 3: {
                                            int[] intArray = (int[])this.player.N.elementAt(this.h);
                                            switch (intArray[0]) {
                                                case 0: {
                                                    if (!this.player.hasBadge(intArray[0])) break block0;
                                                    if (game.WorldManager.getInstance().r()) {
                                                        if (this.player.y() == 2) {
                                                            this.E();
                                                            this.a("Không gian không đủ, thỉnh thanh lý không gian lại ấp trứng", "Nhấn nút 5 để tiếp tục");
                                                            this.dialogSubState = 1;
                                                            break block0;
                                                        }
                                                        game.WorldManager.q = 0;
                                                        if (game.WorldManager.getInstance().M.b[game.WorldManager.a(4, 5)] != null) {
                                                            game.WorldManager.getInstance().M.b[game.WorldManager.a((int)4, (int)5)][15] = 4;
                                                            if (game.WorldManager.getInstance().f == 4 && game.WorldManager.getInstance().g == 5) {
                                                                game.WorldManager.getInstance().M.a[15].a((byte)4);
                                                            }
                                                        }
                                                        this.player.j(intArray[0]);
                                                        this.bl();
                                                        this.E();
                                                        this.a("Ấp trứng thành công", "Nhấn nút 5 để tiếp tục");
                                                        this.dialogSubState = 2;
                                                        break block0;
                                                    }
                                                    this.E();
                                                    this.a("Vẫn chưa thể ấp trứng", "Nhấn nút 5 để tiếp tục");
                                                    this.dialogSubState = 1;
                                                    break block0;
                                                }
                                                case 5: {
                                                    this.parentScreen.setScreenMode((byte)11);
                                                    this.uiManager.closeUI("/data/ui/bag.ui");
                                                    break block0;
                                                }
                                                case 10: {
                                                    this.parentScreen.setScreenMode((byte)24);
                                                    this.uiManager.closeUI("/data/ui/bag.ui");
                                                    break block0;
                                                }
                                                case 6: {
                                                    this.parentScreen.setScreenMode((byte)12);
                                                    this.uiManager.closeUI("/data/ui/bag.ui");
                                                    break block0;
                                                }
                                                case 7: 
                                                case 8: 
                                                case 9: {
                                                    this.s = intArray[0];
                                                    this.parentScreen.setScreenMode((byte)19);
                                                    this.uiManager.closeUI("/data/ui/bag.ui");
                                                    break block0;
                                                }
                                            }
                                        }
                                    }
                                    break block46;
                                }
                                if (this.dialogSubState != 1 && this.dialogSubState != 2) break block46;
                                if (this.dialogSubState != 2) break block52;
                                if (this.player.I != 0) break block53;
                                byte by = this.g(58);
                                this.player.a((short)58);
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
                            for (n2 = 0; n2 < this.player.I && this.player.R[n2] != shortArray[n5]; ++n2) {
                            }
                            if (n2 >= this.player.I) {
                                this.player.a(shortArray[n5]);
                            }
                            if (by == 0) {
                                this.c("Ấp trứng tìm được #2" + BaseScreen.f(GameDatabase.gameDatabase[0][shortArray[n5]][0]) + "#0 để vào ba lô");
                            } else if (by == 1) {
                                this.c("Ấp trứng tìm được #2" + BaseScreen.f(GameDatabase.gameDatabase[0][shortArray[n5]][0]) + "#0 để vào ngân hàng");
                            } else {
                                this.c("Không có không gian, đã phóng sinh");
                            }
                        }
                        this.dialogSubState = 3;
                        break block54;
                    }
                    this.parentScreen.m();
                    this.dialogSubState = 0;
                }
                this.F();
                break block46;
            }
            if (this.dialogSubState == 0 && this.parentScreen.isKeyPressed(262144 /* MASK_SOFT_RIGHT */) && !this.j() && BaseScreen.J()) {
                this.selectedSubMenuIndex = BaseScreen.isVipUnlocked ? 2 : 1;
                this.parentScreen.setScreenMode((byte)6);
                this.uiManager.closeUI("/data/ui/bag.ui");
            }
        }
        if (this.dialogSubState == 3 && !this.j()) {
            this.parentScreen.m();
            this.bj();
            this.dialogSubState = 0;
        }
        this.f();
        this.isDialogActive = true;
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
        byte by = this.player.y();
        if (n3 == -1) {
            if (by == 0) {
                this.player.a(n2, 5, (byte)2, (short)-1, new int[]{1, n5, n6});
            } else if (by == 1) {
                int n7 = EngineUtils.b(GameDatabase.gameDatabase[0][n2][3], (int)GameDatabase.gameDatabase[0][n2][3]);
                this.player.a(n2, 5, (byte)2, (byte)n7, game.Pet.b(n2, 5, n7), -1, new int[]{1, n5, n6});
            }
        } else {
            n4 = (byte)(n4 + (byte)EngineUtils.a(nArrayArray[n3], EngineUtils.randomInt(100)));
            if (by == 0) {
                this.player.a(n2, 5, (byte)2, (short)n4, new int[]{1, n5, n6});
            } else if (by == 1) {
                this.player.a(n2, 5, (byte)2, (short)n4, game.Pet.b(n2, 5, n4), -1, new int[]{1, n5, n6});
            }
        }
        return by;
    }

    public final void ad() {
        this.aS();
        this.uiManager.openUI("/data/ui/ride.ui", 257, this);
        this.selectedSubMenuIndex = 0;
        this.bm();
    }

    private void bm() {
        for (int i = 0; i < 4; ++i) {
            if (this.uiManager.activeView.a((int)(i + 4)).h().m == null) {
                this.uiManager.activeView.a((int)(i + 4)).h().m = new SpriteWidget();
                this.uiManager.activeView.a((int)(i + 4)).h().m.a(0);
                this.uiManager.activeView.a((int)(i + 4)).h().m.a = (byte)3;
                this.uiManager.activeView.a((int)(i + 4)).h().m.a(260, false, (byte)-1);
            }
            if (this.uiManager.activeView.a((int)(i + 16)).h().m == null) {
                this.uiManager.activeView.a((int)(i + 16)).h().m = new SpriteWidget();
                this.uiManager.activeView.a((int)(i + 16)).h().m.a(131);
                this.uiManager.activeView.a((int)(i + 16)).h().m.a = (byte)2;
                this.uiManager.activeView.a((int)(i + 16)).h().m.a(257, false, (byte)0);
            }
            if (this.player.f(i)) {
                if (this.selectedSubMenuIndex == i) {
                    this.uiManager.activeView.a((int)(i + 4)).h().m.a((byte)i, (byte)-1);
                    if (this.selectedSubMenuIndex == 0) {
                        this.uiManager.activeView.a((int)(i + 8)).h().a = "Lục đi điểu";
                    } else if (this.selectedSubMenuIndex == 1) {
                        this.uiManager.activeView.a((int)(i + 8)).h().a = "Hư không hành giả";
                    } else if (this.selectedSubMenuIndex == 2) {
                        this.uiManager.activeView.a((int)(i + 8)).h().a = "Hải âu";
                    } else if (this.selectedSubMenuIndex == 3) {
                        this.uiManager.activeView.a((int)(i + 8)).h().a = "Nham sơn long";
                    }
                } else {
                    this.uiManager.activeView.a((int)(i + 4)).h().m.a((byte)(i + 8), (byte)-1);
                    this.uiManager.activeView.a((int)(i + 8)).h().a = "";
                }
                if (!this.player.g(i)) {
                    this.uiManager.activeView.a(i + 16).a(true);
                    continue;
                }
                this.uiManager.activeView.a(i + 16).a(false);
                continue;
            }
            this.uiManager.activeView.a(i + 16).a(false);
            this.uiManager.activeView.a((int)(i + 4)).h().m.a((byte)(i + 4), (byte)-1);
            this.uiManager.activeView.a((int)(i + 8)).h().a = "";
        }
    }

    public final void ae() {
        if (!this.j() && this.parentScreen.isKeyPressed(16400)) {
            this.uiManager.activeView.navigateSelection(2);
        } else if (!this.j() && this.parentScreen.isKeyPressed(32832)) {
            this.uiManager.activeView.navigateSelection(3);
        } else if (!this.j() && this.parentScreen.isKeyPressed(512)) {
            this.uiManager.closeUI("/data/ui/ride.ui");
            this.parentScreen.setScreenMode((byte)0);
        } else if (!this.j() && this.parentScreen.isKeyPressed(196640)) {
            if (this.player.f(this.selectedSubMenuIndex)) {
                if (this.player.g(this.selectedSubMenuIndex)) {
                    this.player.h(this.selectedSubMenuIndex);
                    this.uiManager.closeUI("/data/ui/ride.ui");
                    this.parentScreen.setScreenMode((byte)0);
                } else {
                    this.b("Nơi này không thể sử dụng sủng vật cưỡi");
                }
            } else {
                this.b("Chưa có sủng vật cưỡi này");
            }
        } else if (!this.j() && this.parentScreen.isKeyPressed(262144 /* MASK_SOFT_RIGHT */)) {
            this.uiManager.closeUI("/data/ui/ride.ui");
            this.parentScreen.setScreenMode((byte)0);
        }
        this.f();
        this.isDialogActive = true;
    }

    public final void a(Pet object, b b2) {
        this.uiManager.openUI("/data/ui/battle.ui", 257, this);
        this.a = 0;
        this.e = 0;
        this.a((Pet)object, false);
        this.b(b2, false);
        object = this;
        this.uiManager.activeView.a((int)59).h().a = "100%";
        ((ScriptEngine)object).p.activeView.a((int)58).h().a = "100%";
        ((BattleScreen)((ScriptEngine)object).o).e();
        this.uiManager.closeUI("/data/ui/world.ui");
    }

    public final void b(Pet b2, b b3) {
        if (b2.a(b3) == 0) {
            if (b2.r() == 0) {
                this.uiManager.activeView.a((int)59).h().a = "300%";
                this.uiManager.activeView.a((int)58).h().a = "60%";
                return;
            }
            this.uiManager.activeView.a((int)59).h().a = "60%";
            this.uiManager.activeView.a((int)58).h().a = "300%";
            return;
        }
        if (b2.a(b3) == 1) {
            if (b2.r() == 0) {
                this.uiManager.activeView.a((int)59).h().a = "60%";
                this.uiManager.activeView.a((int)58).h().a = "300%";
                return;
            }
            this.uiManager.activeView.a((int)59).h().a = "300%";
            this.uiManager.activeView.a((int)58).h().a = "60%";
            return;
        }
        this.uiManager.activeView.a((int)59).h().a = "100%";
        this.uiManager.activeView.a((int)58).h().a = "100%";
    }

    public final void a(Pet b2, b b3, Pet b4, int n2, int n3) {
        if (b2.a(b3) == 0) {
            if (b4.r() == 0) {
                if ((n2 *= 200 / n3) == n3 && n2 != 200) {
                    n2 = 200;
                }
                this.uiManager.activeView.a((int)59).h().a = n2 + 100 + "%";
                return;
            }
            if (b4.r() == 1) {
                if ((n2 *= 40 / n3) == n3 && n2 != 40) {
                    n2 = 40;
                }
                this.uiManager.activeView.a((int)58).h().a = 100 - n2 + "%";
                return;
            }
        } else if (b2.a(b3) == 1) {
            if (b4.r() == 0) {
                if ((n2 *= 40 / n3) == n3 && n2 != 40) {
                    n2 = 40;
                }
                this.uiManager.activeView.a((int)59).h().a = 100 - n2 + "%";
                return;
            }
            if (b4.r() == 1) {
                if ((n2 *= 200 / n3) == n3 && n2 != 200) {
                    n2 = 200;
                }
                this.uiManager.activeView.a((int)58).h().a = n2 + 100 + "%";
                return;
            }
        } else {
            this.uiManager.activeView.a((int)59).h().a = "100%";
            this.uiManager.activeView.a((int)58).h().a = "100%";
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
            this.uiManager.activeView.a((int)59).h().a = 100 + this.D + "%";
            this.E += n2 * (40 / n3);
            if (this.E == n3 && this.E != 40) {
                this.E = 40;
            }
            this.uiManager.activeView.a((int)58).h().a = 100 - this.E + "%";
            return;
        }
        if (b2.a(b3) == 1) {
            this.D += n2 * (40 / n3);
            if (this.D == n3 && this.D != 40) {
                this.D = 40;
            }
            this.uiManager.activeView.a((int)59).h().a = 100 - this.D + "%";
            this.E += n2 * (200 / n3);
            if (this.E == n3 && this.E != 200) {
                this.E = 200;
            }
            this.uiManager.activeView.a((int)58).h().a = 100 + this.E + "%";
            return;
        }
        this.uiManager.activeView.a((int)59).h().a = "100%";
        this.uiManager.activeView.a((int)58).h().a = "100%";
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
                    this.uiManager.activeView.a((int)55).h().a = "#P" + b2.L();
                    this.uiManager.activeView.a((int)11).h().a = "#P" + b2.M();
                } else {
                    this.uiManager.activeView.a((int)55).h().a = "#P" + b2.M();
                    this.uiManager.activeView.a((int)11).h().a = "#P" + b2.L();
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
            this.uiManager.activeView.a((int)41).h().a = "#P" + b2.L();
            this.uiManager.activeView.a((int)11).h().a = "#P" + b2.M();
            this.uiManager.activeView.a((int)55).h().a = "#P" + b2.M();
        } else {
            if ((n3 -= this.F) <= n2) {
                n3 = n2;
            }
            b2.u(n3);
            this.uiManager.activeView.a((int)41).h().a = "#P" + b2.M();
            this.uiManager.activeView.a((int)55).h().a = "#P" + b2.L();
            this.uiManager.activeView.a((int)11).h().a = "#P" + b2.L();
        }
        this.uiManager.activeView.a((int)38).h().a = b2.N() + "/" + b2.c[1];
        this.uiManager.activeView.a((int)9).h().a = "#P" + b2.O();
        this.uiManager.activeView.a((int)40).h().a = b2.z() + "/" + b2.u();
        this.uiManager.activeView.a((int)12).h().a = BaseScreen.f(b2.j((byte)0));
        this.uiManager.activeView.a((int)13).h().a = "lv" + b2.s();
        this.uiManager.activeView.a((int)17).h().m.a(94 + b2.j((byte)1));
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
            if (this.uiManager.activeView.a((int)(n2 + 26)).h().m == null) {
                this.uiManager.activeView.a((int)(n2 + 26)).h().m = new SpriteWidget();
                this.uiManager.activeView.a((int)(n2 + 26)).h().m.a = (byte)2;
                this.uiManager.activeView.a((int)(n2 + 26)).h().m.a(0);
                this.uiManager.activeView.a((int)(n2 + 26)).h().m.a(325, false, (byte)0);
            }
            if (this.uiManager.activeView.a((int)(n2 + 43)).h().m == null) {
                this.uiManager.activeView.a((int)(n2 + 43)).h().m = new SpriteWidget();
                this.uiManager.activeView.a((int)(n2 + 43)).h().m.a = (byte)2;
                this.uiManager.activeView.a((int)(n2 + 43)).h().m.a(145);
                this.uiManager.activeView.a((int)(n2 + 43)).h().m.a(257, false, (byte)0);
            }
            this.uiManager.activeView.a((int)(n2 + 43)).h().m.a(145);
            this.uiManager.activeView.a((int)(n2 + 26)).h().m.a(0);
        }
        for (n2 = 0; n2 < 3; ++n2) {
            if (b2.x[0][n2] != -1 && b2.v[b2.x[0][n2]][0] > 0) {
                this.uiManager.activeView.a((int)(43 + this.k)).h().m.a(134 + b2.v[b2.x[0][n2]][0]);
                this.uiManager.activeView.a((int)(26 + this.k)).h().m.a(b2.x[0][n2] + 12);
                ++this.k;
            }
            if (b2.x[1][n2] == -1 || b2.w[b2.x[1][n2]][0] <= 0) continue;
            this.uiManager.activeView.a((int)(43 + this.k)).h().m.a(134 + b2.w[b2.x[1][n2]][0]);
            this.uiManager.activeView.a((int)(26 + this.k)).h().m.a(b2.x[1][n2] + 1);
            ++this.k;
        }
    }

    private void g(Pet b2) {
        this.uiManager.activeView.a((int)11).h().a = "#P" + b2.L();
        this.uiManager.activeView.a((int)38).h().a = b2.N() + "/" + b2.c[1];
        this.uiManager.activeView.a((int)16).h().a = "lv" + b2.s();
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
                    this.uiManager.activeView.a((int)56).h().a = "#P" + b2.L();
                    this.uiManager.activeView.a((int)14).h().a = "#P" + b2.M();
                } else {
                    this.uiManager.activeView.a((int)56).h().a = "#P" + b2.M();
                    this.uiManager.activeView.a((int)14).h().a = "#P" + b2.L();
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
            this.uiManager.activeView.a((int)42).h().a = "#P" + b2.L();
            this.uiManager.activeView.a((int)14).h().a = "#P" + b2.M();
            this.uiManager.activeView.a((int)56).h().a = "#P" + b2.M();
        } else {
            if ((n3 -= this.F) <= n2) {
                n3 = n2;
            }
            b2.u(n3);
            this.uiManager.activeView.a((int)42).h().a = "#P" + b2.M();
            this.uiManager.activeView.a((int)14).h().a = "#P" + b2.L();
            this.uiManager.activeView.a((int)56).h().a = "#P" + b2.L();
        }
        this.uiManager.activeView.a((int)39).h().a = b2.N() + "/" + b2.c[1];
        if (this.player.a((byte)b2.j((byte)1), b2.q()) == 2) {
            this.uiManager.activeView.a((int)19).h().m.a(101);
        } else {
            this.uiManager.activeView.a((int)19).h().m.a(102);
        }
        this.uiManager.activeView.a((int)15).h().a = BaseScreen.f(b2.j((byte)0));
        this.uiManager.activeView.a((int)16).h().a = "lv" + b2.s();
        this.uiManager.activeView.a((int)18).h().m.a(94 + b2.j((byte)1));
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
            if (this.uiManager.activeView.a((int)(n2 + 32)).h().m == null) {
                this.uiManager.activeView.a((int)(n2 + 32)).h().m = new SpriteWidget();
                this.uiManager.activeView.a((int)(n2 + 32)).h().m.a = (byte)2;
                this.uiManager.activeView.a((int)(n2 + 32)).h().m.a(0);
                this.uiManager.activeView.a((int)(n2 + 32)).h().m.a(325, false, (byte)0);
            }
            if (this.uiManager.activeView.a((int)(n2 + 49)).h().m == null) {
                this.uiManager.activeView.a((int)(n2 + 49)).h().m = new SpriteWidget();
                this.uiManager.activeView.a((int)(n2 + 49)).h().m.a = (byte)2;
                this.uiManager.activeView.a((int)(n2 + 49)).h().m.a(145);
                this.uiManager.activeView.a((int)(n2 + 49)).h().m.a(257, false, (byte)0);
            }
            this.uiManager.activeView.a((int)(n2 + 49)).h().m.a(145);
            this.uiManager.activeView.a((int)(n2 + 32)).h().m.a(0);
        }
        for (n2 = 0; n2 < 3; ++n2) {
            if (b2.x[0][n2] != -1 && b2.v[b2.x[0][n2]][0] > 0) {
                this.uiManager.activeView.a((int)(49 + this.k)).h().m.a(134 + b2.v[b2.x[0][n2]][0]);
                this.uiManager.activeView.a((int)(32 + this.k)).h().m.a(b2.x[0][n2] + 12);
                ++this.k;
            }
            if (b2.x[1][n2] == -1 || b2.w[b2.x[1][n2]][0] <= 0) continue;
            this.uiManager.activeView.a((int)(49 + this.k)).h().m.a(134 + b2.w[b2.x[1][n2]][0]);
            this.uiManager.activeView.a((int)(32 + this.k)).h().m.a(b2.x[1][n2] + 1);
            ++this.k;
        }
    }

    public final void af() {
        this.a = 0;
        this.y = null;
        this.uiManager.closeUI("/data/ui/battle.ui");
    }

    private void a(boolean flag) {
        this.uiManager.activeView.a(20 + this.a).a(flag);
    }

    public final void ag() {
        ((MenuWidget)this.uiManager.activeView.a((int)0)).b.f = this.a;
        this.a(true);
    }

    public final void c(Pet b2) {
        this.dialogSubState = 0;
        this.a(b2, false);
        this.ag();
    }

    public final void d(Pet b2) {
        ((BattleScreen)this.parentScreen).l();
        if (!BaseScreen.b(this.a, 1) && this.dialogSubState == 0 && !this.j() && this.parentScreen.isKeyPressed(16400)) {
            this.uiManager.activeView.navigateSelection(2);
        } else if (!BaseScreen.b(this.a, 1) && this.dialogSubState == 0 && !this.j() && this.parentScreen.isKeyPressed(32832)) {
            this.uiManager.activeView.navigateSelection(3);
        } else if (!this.j() && this.parentScreen.isKeyPressed(196640)) {
            switch (this.a) {
                case 0: {
                    this.a(false);
                    this.parentScreen.setScreenMode((byte)3);
                    break;
                }
                case 2: {
                    if (this.dialogSubState == 0) {
                        if (b2.p(2)) {
                            this.uiManager.openUI("/data/ui/msgwarm.ui", 257, this);
                            this.a("Trạng thái bị quấn, không thể sử dụng đạo cụ", "Nhấn nút 5 để tiếp tục");
                            this.dialogSubState = 1;
                            break;
                        }
                        this.a(false);
                        this.parentScreen.setScreenMode((byte)4);
                        break;
                    }
                    this.uiManager.closeUI("/data/ui/msgwarm.ui");
                    this.dialogSubState = 0;
                    break;
                }
                case 3: {
                    if (this.dialogSubState == 0) {
                        if (b2.p(2)) {
                            this.uiManager.openUI("/data/ui/msgwarm.ui", 257, this);
                            this.a("Trạng thái bị quấn, không thể đổi sủng vật", "Nhấn nút 5 để tiếp tục");
                            this.dialogSubState = 1;
                            break;
                        }
                        this.a(false);
                        ((BattleScreen)this.parentScreen).g = ((BattleScreen)this.parentScreen).e[((BattleScreen)this.parentScreen).i];
                        game.BattleScreen.getInstance().k = true;
                        this.parentScreen.setScreenMode((byte)5);
                        break;
                    }
                    this.uiManager.closeUI("/data/ui/msgwarm.ui");
                    this.dialogSubState = 0;
                    break;
                }
                case 1: {
                    if (((BattleScreen)this.parentScreen).b == 2) {
                        this.b("Trận chiến này không cho bắt sủng vật");
                        break;
                    }
                    if (this.player.y() == 2) {
                        this.b("Không gian không đủ, không cách nào bắt được");
                        break;
                    }
                    this.selectedSubMenuIndex = 0;
                    this.a(false);
                    ((BattleScreen)this.parentScreen).m();
                    this.parentScreen.setScreenMode((byte)21);
                    break;
                }
                case 4: {
                    this.a(false);
                    this.parentScreen.setScreenMode((byte)11);
                    break;
                }
                case 5: {
                    if (this.dialogSubState == 0) {
                        if (b2.p(2)) {
                            this.uiManager.openUI("/data/ui/msgwarm.ui", 257, this);
                            this.a("Trạng thái bị quấn, không thể chạy trốn", "Nhấn nút 5 để tiếp tục");
                            this.dialogSubState = 1;
                            break;
                        }
                        if (((BattleScreen)this.parentScreen).b > 0 || !game.OverworldScreen.j) {
                            this.a(false);
                            this.dialogSubState = 3;
                            this.b("Trận chiến này không thể trốn chạy");
                            break;
                        }
                        boolean bl = false;
                        if (((BattleScreen)this.parentScreen).h.s() > ((BattleScreen)this.parentScreen).d[0].s()) {
                            bl = true;
                        } else if (((BattleScreen)this.parentScreen).h.s() == ((BattleScreen)this.parentScreen).d[0].s()) {
                            if (EngineUtils.randomInt(100) <= 95) {
                                bl = true;
                            }
                        } else {
                            int n2 = ((BattleScreen)this.parentScreen).d[0].s() - ((BattleScreen)this.parentScreen).h.s();
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
                        this.dialogSubState = 2;
                        this.b("Chạy trốn thất bại");
                        break;
                    }
                    this.uiManager.closeUI("/data/ui/msgwarm.ui");
                    this.dialogSubState = 0;
                }
            }
        }
        this.f();
        if (this.dialogSubState >= 2 && this.ax()) {
            if (this.dialogSubState == 2) {
                ((BattleScreen)this.parentScreen).h.J = true;
                ((BattleScreen)this.parentScreen).i = (byte)(((BattleScreen)this.parentScreen).i + 1);
                this.parentScreen.setScreenMode((byte)1);
            } else {
                this.a(true);
            }
            this.dialogSubState = 0;
        }
    }

    public final void e(Pet b2) {
        this.uiManager.openUI("/data/ui/choiceskill.ui", 257, this);
        ((MenuWidget)this.uiManager.activeView.a((int)0)).a.a = b2.E();
        if (this.e >= b2.E()) {
            this.e = b2.E() - 1;
        }
        if (b2.E() > 5) {
            ((MenuWidget)this.uiManager.activeView.a((int)0)).a.a(1);
        } else {
            ((MenuWidget)this.uiManager.activeView.a((int)0)).a.a(-1);
        }
        this.uiManager.activeView.a((int)5).h().a = "Sử dụng";
        ((MenuWidget)this.uiManager.activeView.a((int)0)).a.f = this.e;
        this.h(b2);
        this.dialogSubState = 0;
    }

    private void h(Pet b2) {
        this.w = ((MenuWidget)this.uiManager.activeView.a((int)0)).a.e;
        this.h = ((MenuWidget)this.uiManager.activeView.a((int)0)).a.f;
        int n2 = b2.E();
        for (int i = 0; i < 5; ++i) {
            if (i >= n2) {
                this.uiManager.activeView.a((int)(13 + i * 5)).h().a = "";
                this.uiManager.activeView.a((int)(14 + i * 5)).h().a = "";
                continue;
            }
            this.uiManager.activeView.a((int)(13 + i * 5)).h().a = BaseScreen.f(GameDatabase.gameDatabase[1][b2.t(this.w + i)][1]);
            this.uiManager.activeView.a((int)(14 + i * 5)).h().a = b2.y[this.w + i] + "/" + GameDatabase.gameDatabase[1][b2.t(this.w + i)][5];
        }
        this.h(b2.z[this.e]);
        this.uiManager.activeView.a(51).b(98 + this.h * 72 / n2, this.uiManager.activeView.a());
    }

    private void h(int n2) {
        this.uiManager.activeView.a((int)53).h().a = BaseScreen.f(GameDatabase.gameDatabase[1][n2][2]);
    }

    public final void f(Pet b2) {
        if (this.ay()) {
            if (this.dialogSubState == 0 && this.parentScreen.isKeyPressed(4100)) {
                this.uiManager.activeView.navigateSelection(0);
                this.h(b2);
            } else if (this.dialogSubState == 0 && this.parentScreen.isKeyPressed(8448)) {
                this.uiManager.activeView.navigateSelection(1);
                this.h(b2);
            } else if (this.parentScreen.isKeyPressed(196640)) {
                if (this.dialogSubState == 0) {
                    if (b2.s(this.e)) {
                        this.uiManager.closeUI("/data/ui/choiceskill.ui");
                        ((BattleScreen)this.parentScreen).b(b2.z[this.e]);
                        int n2 = ((BattleScreen)this.parentScreen).a;
                        ((BattleScreen)this.parentScreen).getClass();
                        if (n2 == 0) {
                            ((BattleScreen)this.parentScreen).i();
                        } else {
                            this.parentScreen.setScreenMode((byte)6);
                        }
                    } else {
                        this.dialogSubState = 1;
                        this.uiManager.openUI("/data/ui/msgwarm.ui", 257, this);
                        this.a("Kỹ năng giá trị chưa đủ", "Nhấn nút 5 để tiếp tục");
                    }
                } else {
                    this.dialogSubState = 0;
                    this.uiManager.closeUI("/data/ui/msgwarm.ui");
                    if (b2.p(2) && b2.r() == 0) {
                        boolean bl = false;
                        for (int i = 0; i < b2.y.length; ++i) {
                            if (b2.y[i] == 0) continue;
                            bl = true;
                        }
                        if (!bl) {
                            this.uiManager.closeUI("/data/ui/choiceskill.ui");
                            this.c("Không có kỹ năng giá trị, không cách nào chiến đấu");
                            ((BattleScreen)this.parentScreen).h();
                        }
                    }
                }
            } else if (this.parentScreen.isKeyPressed(262144 /* MASK_SOFT_RIGHT */) && this.dialogSubState == 0) {
                this.uiManager.closeUI("/data/ui/choiceskill.ui");
                this.parentScreen.setScreenMode((byte)20);
            }
        }
        this.g();
    }

    public final void ah() {
        this.dialogSubState = 0;
        this.uiManager.openUI("/data/ui/choice.ui", 257, this);
        this.uiManager.activeView.a((int)8).h().a = "Pokemon ball";
        this.uiManager.activeView.a((int)9).h().a = "Tỉ lệ bắt";
        this.uiManager.activeView.a((int)5).h().a = "Sử dụng";
        ((MenuWidget)this.uiManager.activeView.a((int)0)).a.f = this.selectedSubMenuIndex;
        ((MenuWidget)this.uiManager.activeView.a((int)0)).a.a(0);
        ((MenuWidget)this.uiManager.activeView.a((int)0)).a.a = this.player.K.size();
        for (int i = 0; i < this.player.K.size(); ++i) {
            int[] intArray = (int[])this.player.K.elementAt(i);
            if (this.uiManager.activeView.a((int)(i + 54)).h().m == null) {
                this.uiManager.activeView.a((int)(i + 54)).h().m = new SpriteWidget();
                this.uiManager.activeView.a((int)(i + 54)).h().m.a(0);
                this.uiManager.activeView.a((int)(i + 54)).h().m.a = (byte)2;
                this.uiManager.activeView.a((int)(i + 54)).h().m.a(258, false, (byte)-1);
            }
            this.uiManager.activeView.a((int)(i + 54)).h().m.a(GameDatabase.gameDatabase[4][intArray[0]][1]);
            this.uiManager.activeView.a((int)(13 + i * 5)).h().a = BaseScreen.f(GameDatabase.gameDatabase[4][intArray[0]][0]);
            this.uiManager.activeView.a((int)(14 + i * 5)).h().a = ((BattleScreen)this.parentScreen).b(intArray[0]) + "%";
        }
        this.uiManager.activeView.a(59).a(false);
        this.uiManager.activeView.a(60).a(false);
        this.bn();
    }

    private void bn() {
        int[] intArray = (int[])this.player.K.elementAt(this.selectedSubMenuIndex);
        this.uiManager.activeView.a((int)53).h().a = "Số lượng: " + intArray[1] + " cái ";
    }

    public final void ai() {
        this.parentScreen.isKeyHeld();
        if (!BaseScreen.b(this.selectedSubMenuIndex, 0) && this.dialogSubState == 0 && this.parentScreen.isKeyPressed(4100) && !this.j()) {
            this.uiManager.activeView.navigateSelection(0);
            this.bn();
        } else if (!BaseScreen.b(this.selectedSubMenuIndex, 0) && this.dialogSubState == 0 && this.parentScreen.isKeyPressed(8448) && !this.j()) {
            this.uiManager.activeView.navigateSelection(1);
            this.bn();
        } else if (this.parentScreen.isKeyPressed(196640) && !this.j() && BaseScreen.I()) {
            if (BaseScreen.H() && !BaseScreen.b(this.selectedSubMenuIndex, 0)) {
                return;
            }
            if (this.dialogSubState == 0) {
                int[] intArray = (int[])this.player.K.elementAt(this.selectedSubMenuIndex);
                if (!this.player.b(intArray[0], 1, (byte)0)) {
                    this.uiManager.openUI("/data/ui/msgwarm.ui", 257, this);
                    this.a("Số lượng Pokemon ball không đủ", "Nhấn nút 5 để tiếp tục");
                    this.dialogSubState = 1;
                } else {
                    this.dialogSubState = 0;
                    game.BattleScreen.l = (byte)intArray[0];
                    this.parentScreen.m();
                    this.player.d(intArray[0], 1, (byte)0);
                    this.parentScreen.setScreenMode((byte)17);
                    this.uiManager.closeUI("/data/ui/choice.ui");
                }
            } else if (this.dialogSubState == 1) {
                if (BaseScreen.isVipUnlocked && ((int[])this.player.K.elementAt(this.selectedSubMenuIndex))[0] == 0) {
                    this.uiManager.closeUI("/data/ui/choice.ui");
                    this.parentScreen.setScreenMode((byte)101);
                }
                this.dialogSubState = 0;
                this.uiManager.closeUI("/data/ui/msgwarm.ui");
            }
        } else if (game.OverworldScreen.J() && this.dialogSubState == 0 && this.parentScreen.isKeyPressed(262144 /* MASK_SOFT_RIGHT */) && !this.j()) {
            this.uiManager.closeUI("/data/ui/choice.ui");
            this.parentScreen.setScreenMode((byte)20);
        }
        this.g();
    }

    public final void aj() {
        this.s = 0;
        this.dialogSubState = 0;
        this.selectedSubMenuIndex = 0;
        this.uiManager.openUI("/data/ui/choice.ui", 257, this);
        this.uiManager.activeView.a((int)8).h().a = "Đạo cụ";
        this.uiManager.activeView.a((int)9).h().a = "Số lượng";
        this.uiManager.activeView.a((int)5).h().a = "Sử dụng";
        this.uiManager.activeView.a(59).a(false);
        this.uiManager.activeView.a(60).a(false);
        this.be();
    }

    public final void ak() {
        if (this.dialogSubState == 0 && this.parentScreen.isKeyPressed(4100)) {
            this.uiManager.activeView.navigateSelection(0);
            return;
        }
        if (this.dialogSubState == 0 && this.parentScreen.isKeyPressed(8448)) {
            this.uiManager.activeView.navigateSelection(1);
            return;
        }
        if (this.parentScreen.isKeyPressed(196640)) {
            if (this.player.J.size() <= 0) {
                return;
            }
            this.s = ((int[])this.player.J.elementAt(this.h))[0];
            if (this.dialogSubState == 0) {
                switch (GameDatabase.gameDatabase[4][this.s][5]) {
                    case 7: 
                    case 8: 
                    case 9: 
                    case 10: {
                        this.uiManager.openUI("/data/ui/msgwarm.ui", 257, this);
                        this.a("Trong chiến đấu không thể sử dụng", "Nhấn nút 5 để tiếp tục");
                        this.dialogSubState = 1;
                        return;
                    }
                }
                this.parentScreen.setScreenMode((byte)16);
                this.uiManager.closeUI("/data/ui/choice.ui");
                return;
            }
            if (this.dialogSubState == 1) {
                this.uiManager.closeUI("/data/ui/msgwarm.ui");
                this.dialogSubState = 0;
                return;
            }
        } else if (this.dialogSubState == 0 && this.parentScreen.isKeyPressed(262144 /* MASK_SOFT_RIGHT */)) {
            this.uiManager.closeUI("/data/ui/choice.ui");
            this.parentScreen.setScreenMode((byte)20);
        }
    }

    private void bo() {
        if (this.dialogSubState == 0) {
            this.dialogSubState = 1;
            int n2 = this.parentScreen instanceof WorldManager ? this.player.petParty[this.selectedShopIndex].x(this.s) : this.player.petParty[((BattleScreen)this.parentScreen).f[this.selectedShopIndex]].x(this.s);
            switch (n2) {
                case 0: {
                    this.uiManager.openUI("/data/ui/msgwarm.ui", 257, this);
                    this.a("Sủng vật này đã tử vong, không thể sử dụng", "Nhấn nút 5 để tiếp tục");
                    return;
                }
                case 1: {
                    this.uiManager.openUI("/data/ui/msgwarm.ui", 257, this);
                    this.a("Sủng vật này không có, không thể sử dụng", "Nhấn nút 5 để tiếp tục");
                    return;
                }
                case 2: {
                    this.uiManager.openUI("/data/ui/msgwarm.ui", 257, this);
                    this.a("Máu đầy, không cần sử dụng", "Nhấn nút 5 để tiếp tục");
                    return;
                }
                case 3: {
                    this.uiManager.openUI("/data/ui/msgwarm.ui", 257, this);
                    this.a("Kỹ năng giá trị đã đầy, không cần sử dụng", "Nhấn nút 5 để tiếp tục");
                    return;
                }
                case 4: {
                    this.uiManager.openUI("/data/ui/msgwarm.ui", 257, this);
                    this.a("Trên người đều bị lợi hiệu quả", "Nhấn nút 5 để tiếp tục");
                    return;
                }
                case 5: {
                    this.uiManager.openUI("/data/ui/msgwarm.ui", 257, this);
                    this.a("Trong hưng phấn, không thể dùng", "Nhấn nút 5 để tiếp tục");
                    return;
                }
                case 7: {
                    this.uiManager.openUI("/data/ui/msgwarm.ui", 257, this);
                    this.a("Máu và kỹ năng đều đã đầy, không cần sử dụng", "Nhấn nút 5 để tiếp tục");
                    return;
                }
                case 8: {
                    this.uiManager.openUI("/data/ui/msgwarm.ui", 257, this);
                    this.a("Sủng vật đã chết, không thể sử dụng", "Nhấn nút 5 để tiếp tục");
                    return;
                }
            }
            if (this.player.b(this.s, 1, (byte)0)) {
                if (this.parentScreen instanceof WorldManager) {
                    this.player.petParty[this.selectedShopIndex].w(this.s);
                } else {
                    ((BattleScreen)this.parentScreen).h.J = true;
                    this.player.petParty[((BattleScreen)this.parentScreen).f[this.selectedShopIndex]].w(this.s);
                }
                this.e(this.selectedShopIndex);
                this.dialogSubState = 1;
                this.l = true;
                this.uiManager.openUI("/data/ui/msgwarm.ui", 257, this);
                this.a("Thành công sử dụng đạo cụ", "Nhấn nút 5 để tiếp tục");
                return;
            }
            this.dialogSubState = 2;
            this.E();
            this.a("Đã không có đạo này cụ, thỉnh mua sắm", "Nhấn nút 5 để tiếp tục");
            return;
        }
        if (this.dialogSubState == 1) {
            this.dialogSubState = 0;
            this.uiManager.closeUI("/data/ui/msgwarm.ui");
            return;
        }
        if (this.dialogSubState == 2) {
            this.dialogSubState = 0;
            this.uiManager.closeUI("/data/ui/msgwarm.ui");
            this.uiManager.closeUI("/data/ui/petstate.ui");
            if (this.parentScreen instanceof WorldManager) {
                this.parentScreen.setScreenMode((byte)8);
                return;
            }
            if (game.BattleScreen.getInstance().activePlayerPet.equals(((BattleScreen)this.parentScreen).c(this.selectedShopIndex))) {
                this.g(((BattleScreen)this.parentScreen).d(this.selectedShopIndex));
            }
            if (((BattleScreen)this.parentScreen).h.J) {
                ((BattleScreen)this.parentScreen).i = (byte)(((BattleScreen)this.parentScreen).i + 1);
                ((BattleScreen)this.parentScreen).a((byte)1);
                return;
            }
            ((BattleScreen)this.parentScreen).a((byte)4);
        }
    }

    public final void al() {
        if (this.dialogSubState == 0 && this.parentScreen.isKeyPressed(4100)) {
            this.uiManager.activeView.navigateSelection(0);
            return;
        }
        if (this.dialogSubState == 0 && this.parentScreen.isKeyPressed(8448)) {
            this.uiManager.activeView.navigateSelection(1);
            return;
        }
        if (this.parentScreen.isKeyPressed(196640)) {
            this.bo();
            return;
        }
        if (this.dialogSubState == 0 && this.parentScreen.isKeyPressed(262144 /* MASK_SOFT_RIGHT */)) {
            if (this.l) {
                if (game.BattleScreen.getInstance().activePlayerPet.equals(((BattleScreen)this.parentScreen).c(this.selectedShopIndex))) {
                    this.g(((BattleScreen)this.parentScreen).d(this.selectedShopIndex));
                }
                if (((BattleScreen)this.parentScreen).h.J) {
                    ((BattleScreen)this.parentScreen).i = (byte)(((BattleScreen)this.parentScreen).i + 1);
                    ((BattleScreen)this.parentScreen).a((byte)1);
                } else {
                    ((BattleScreen)this.parentScreen).a((byte)4);
                }
                this.uiManager.closeUI("/data/ui/petstate.ui");
                return;
            }
            ((BattleScreen)this.parentScreen).a((byte)4);
            this.uiManager.closeUI("/data/ui/petstate.ui");
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
                    if (this.parentScreen.isKeyPressed(196640)) {
                        if (n2 >= n4) {
                            n3 = n4;
                            this.uiManager.activeView.a((int)40).h().a = n3 + "/" + n3;
                            this.uiManager.activeView.a((int)9).h().a = "#P" + b2.v(n3);
                            b2.j(0);
                            this.x = 0;
                            ((BattleScreen)this.parentScreen).a((byte)22);
                            return;
                        }
                        if (n3 >= n2) {
                            this.uiManager.activeView.a((int)40).h().a = n2 + "/" + b2.u();
                            this.uiManager.activeView.a((int)9).h().a = "#P" + b2.v(n2);
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
                        this.uiManager.activeView.a((int)40).h().a = n3 + "/" + b2.u();
                        this.uiManager.activeView.a((int)9).h().a = "#P" + b2.v(n3);
                        return;
                    }
                    this.uiManager.activeView.a((int)40).h().a = n3 + "/" + b2.u();
                    this.uiManager.activeView.a((int)9).h().a = "#P" + b2.v(n3);
                    Pet b3 = b2;
                    ScriptEngine h2 = this;
                    this.uiManager.activeView.a((int)12).h().a = BaseScreen.f(b3.j((byte)0));
                    h2.p.activeView.a((int)13).h().a = "lv" + b3.s();
                    h2.p.activeView.a((int)17).h().m.a(94 + b3.j((byte)1));
                    if (n3 < n4) break block18;
                    b2.j(0);
                    ((BattleScreen)this.parentScreen).a((byte)22);
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
        this.uiManager.openUI("/data/ui/levelUp.ui", 257, this);
        for (n2 = 0; n2 < 4; ++n2) {
            this.uiManager.activeView.a((int)(n2 + 19)).h().a = strArray[n2];
        }
        if (b2.E() < 5 && b2.E() < b2.s() / 10 + 1) {
            this.y = b2.F();
            this.uiManager.activeView.a((int)51).h().a = "Có thể học tập kỹ năng mới";
        } else {
            this.uiManager.activeView.a((int)51).h().a = "";
        }
        this.uiManager.activeView.a((int)38).h().a = BaseScreen.f(GameDatabase.gameDatabase[0][b2.q()][0]);
        this.uiManager.activeView.a((int)40).h().a = "" + b2.s();
        if (this.uiManager.activeView.a((int)10).h().m == null) {
            this.uiManager.activeView.a((int)10).h().m = new SpriteWidget();
            this.uiManager.activeView.a((int)10).h().m.a = (byte)3;
            this.uiManager.activeView.a((int)10).h().m.a(0);
            this.uiManager.activeView.a((int)10).h().m.a(b2.C, false, (byte)-1);
        }
        for (n2 = 0; n2 < 4; ++n2) {
            by = (byte)(n2 + 1);
            this.uiManager.activeView.a((int)(n2 + 31)).h().a = "" + b2.c[by];
        }
    }

    public final void ao() {
        ++this.K;
        if (this.K > 40) {
            this.K = 0;
            if (this.y != null) {
                ((BattleScreen)this.parentScreen).a((byte)23);
            } else if (this.i + 1 >= game.BattleScreen.j.size()) {
                if (((Pet)game.BattleScreen.j.elementAt(this.i)).z() > 0) {
                    this.parentScreen.setScreenMode((byte)8);
                } else {
                    this.i = 0;
                    game.GameStateController.getInstance().a((byte)10);
                }
                this.uiManager.closeUI("/data/ui/levelUp.ui");
            } else {
                this.parentScreen.setScreenMode((byte)8);
                this.uiManager.closeUI("/data/ui/levelUp.ui");
            }
        }
        if (this.parentScreen.isKeyPressed(196640)) {
            this.K = 0;
            if (this.y != null) {
                this.parentScreen.setScreenMode((byte)23);
                return;
            }
            if (this.i + 1 >= game.BattleScreen.j.size()) {
                if (((Pet)game.BattleScreen.j.elementAt(this.i)).z() > 0) {
                    this.parentScreen.setScreenMode((byte)8);
                } else {
                    this.i = 0;
                    game.GameStateController.getInstance().a((byte)10);
                }
                this.uiManager.closeUI("/data/ui/levelUp.ui");
                return;
            }
            this.parentScreen.setScreenMode((byte)8);
            this.uiManager.closeUI("/data/ui/levelUp.ui");
        }
    }

    public final void ap() {
        this.uiManager.openUI("/data/ui/choiceskill.ui", 257, this);
        this.uiManager.closeUI("/data/ui/levelUp.ui");
        this.selectedSubMenuIndex = 0;
        this.dialogSubState = 0;
        ((MenuWidget)this.uiManager.activeView.a((int)0)).a.a = this.y.length;
        if (this.y.length > 5) {
            ((MenuWidget)this.uiManager.activeView.a((int)0)).a.a(1);
        } else {
            ((MenuWidget)this.uiManager.activeView.a((int)0)).a.a(-1);
        }
        if (this.uiManager.activeView.a((int)5).h().m == null) {
            this.uiManager.activeView.a((int)5).h().m = new SpriteWidget();
            this.uiManager.activeView.a((int)5).h().m.a = (byte)3;
            this.uiManager.activeView.a((int)5).h().m.a(0);
            this.uiManager.activeView.a((int)5).h().m.a(257, false, (byte)-1);
        }
        this.uiManager.activeView.a((int)5).h().m.a((byte)11, (byte)-1);
        this.uiManager.activeView.a(6).a(false);
        this.bp();
        if (!game.WorldManager.D) {
            this.b("Có thể nhấn #1nút mềm trái#0 để học tập kỹ năng");
            game.WorldManager.D = true;
        }
    }

    private void bp() {
        this.w = ((MenuWidget)this.uiManager.activeView.a((int)0)).a.e;
        this.h = ((MenuWidget)this.uiManager.activeView.a((int)0)).a.f;
        for (int i = 0; i < 5; ++i) {
            if (i >= this.y.length) {
                this.uiManager.activeView.a((int)(13 + i * 5)).h().a = "";
                this.uiManager.activeView.a((int)(14 + i * 5)).h().a = "";
                continue;
            }
            this.uiManager.activeView.a((int)(13 + i * 5)).h().a = BaseScreen.f(GameDatabase.gameDatabase[1][this.y[this.w + i]][1]);
            this.uiManager.activeView.a((int)(14 + i * 5)).h().a = "" + GameDatabase.gameDatabase[1][this.y[this.w + i]][5];
        }
        this.h(this.y[this.h]);
        this.uiManager.activeView.a(51).b(98 + this.h * 62 / this.y.length, this.uiManager.activeView.a());
    }

    public final void aq() {
        if (!this.j() && this.parentScreen.isKeyPressed(4100) && this.dialogSubState == 0) {
            this.uiManager.activeView.navigateSelection(0);
            this.bp();
        } else if (!this.j() && this.parentScreen.isKeyPressed(8448) && this.dialogSubState == 0) {
            this.uiManager.activeView.navigateSelection(1);
            this.bp();
        } else if (!this.j() && this.dialogSubState == 0 && (this.parentScreen.isKeyPressed(131072 /* MASK_SOFT_LEFT */) || this.parentScreen.isLeftSoftAreaTouched()) || this.dialogSubState == 1 && this.parentScreen.isKeyPressed(196640)) {
            if (this.dialogSubState == 0) {
                this.dialogSubState = 1;
                this.uiManager.openUI("/data/ui/msgwarm.ui", 257, this);
                this.a("Học tập" + BaseScreen.f(GameDatabase.gameDatabase[1][this.y[this.selectedSubMenuIndex]][1]), "Nhấn nút 5 để tiếp tục");
            } else if (this.dialogSubState == 1) {
                Pet b2 = (Pet)game.BattleScreen.j.elementAt(this.i);
                b2.g((byte)this.y[this.h]);
                this.y = null;
                if (this.i + 1 >= game.BattleScreen.j.size() && b2.z() <= 0) {
                    this.i = 0;
                    game.GameStateController.getInstance().a((byte)10);
                } else {
                    this.parentScreen.setScreenMode((byte)8);
                }
                this.uiManager.closeUI("/data/ui/msgwarm.ui");
                this.uiManager.closeUI("/data/ui/choiceskill.ui");
            }
        }
        this.f();
    }

    public final void ar() {
        this.dialogSubState = 0;
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
                                            if (this.dialogSubState != 0) break block15;
                                            if (this.ax()) {
                                                this.dialogSubState = 1;
                                                if (game.WorldManager.eligibleLevelUpPets.size() <= 0) {
                                                    this.parentScreen.setScreenMode((byte)14);
                                                }
                                            }
                                            break block14;
                                        }
                                        if (this.dialogSubState != 1) break block16;
                                        this.uiManager.closeUI("/data/ui/bodyShop.ui");
                                        this.bq();
                                        this.isDialogActive = true;
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
                                game.WorldManager.levelUpStatus = (byte)2;
                                game.WorldManager.h();
                                if (((WorldManager)h2.o).j()) {
                                    h2.a("Lưu thành công");
                                    h2.f = 7;
                                }
                                break block19;
                            }
                            if (h2.f != 7) break block19;
                            h2.p.closeUI("/data/ui/msgtip.ui");
                            h2.f = 0;
                            if (h2.o.Q == 14) {
                                h2.o.setScreenMode((byte)14);
                                break block14;
                            } else {
                                h2.o.setScreenMode((byte)0);
                            }
                            break block14;
                        }
                        if (h2.j() || !h2.o.isKeyPressed(4100) || h2.f != 3) break block21;
                        h2.p.activeView.navigateSelection(0);
                        h2.bp();
                        break block14;
                    }
                    if (h2.j() || !h2.o.isKeyPressed(8448) || h2.f != 3) break block22;
                    h2.p.activeView.navigateSelection(1);
                    h2.bp();
                    break block14;
                }
                if ((h2.j() || h2.f != 3 || !h2.o.isKeyPressed(131072 /* MASK_SOFT_LEFT */) && !h2.o.isLeftSoftAreaTouched()) && (h2.f != 4 || !h2.o.isKeyPressed(196640))) break block14;
                if (h2.f == 3) {
                    h2.f = 4;
                    h2.p.a("/data/ui/msgwarm.ui", 257, h2);
                    h2.a("Học tập" + BaseScreen.f(GameDatabase.gameDatabase[1][h2.y[h2.h]][1]), "Nhấn nút 5 để tiếp tục");
                    break block14;
                } else if (h2.f == 4) {
                    ((Pet)game.WorldManager.eligibleLevelUpPets.elementAt(h2.i)).g((byte)h2.y[h2.h]);
                    h2.y = null;
                    ++h2.i;
                    if (h2.i >= game.WorldManager.eligibleLevelUpPets.size()) {
                        h2.i = 0;
                        h2.f = 5;
                    } else {
                        h2.bq();
                    }
                    h2.p.closeUI("/data/ui/msgwarm.ui");
                    h2.p.closeUI("/data/ui/choiceskill.ui");
                }
                break block14;
            }
            if (h2.o.isKeyPressed(196640)) {
                ScriptEngine h3 = h2;
                h3.p.a("/data/ui/choiceskill.ui", 257, h3);
                h3.p.closeUI("/data/ui/levelUp.ui");
                h3.b = 0;
                h3.f = 3;
                ((MenuWidget)h3.p.activeView.a((int)0)).a.a = h3.y.length;
                if (h3.y.length > 5) {
                    ((MenuWidget)h3.p.activeView.a((int)0)).a.a(1);
                } else {
                    ((MenuWidget)h3.p.activeView.a((int)0)).a.a(-1);
                }
                if (h3.p.activeView.a((int)5).h().m == null) {
                    h3.p.activeView.a((int)5).h().m = new SpriteWidget();
                    h3.p.activeView.a((int)5).h().m.a = (byte)3;
                    h3.p.activeView.a((int)5).h().m.a(0);
                    h3.p.activeView.a((int)5).h().m.a(257, false, (byte)-1);
                }
                h3.p.activeView.a((int)5).h().m.a((byte)11, (byte)-1);
                h3.p.activeView.a(6).a(false);
                h3.bp();
                h2.g = true;
            }
        }
        this.f();
    }

    private void bq() {
        int n2;
        this.dialogSubState = 2;
        Pet b2 = (Pet)game.WorldManager.eligibleLevelUpPets.elementAt(this.i);
        this.uiManager.openUI("/data/ui/levelUp.ui", 257, this);
        for (n2 = 0; n2 < 4; ++n2) {
            this.uiManager.activeView.a((int)(n2 + 19)).h().a = "" + b2.i((int)((byte)(n2 + 1 - 1)));
        }
        if (b2.E() < 5 && b2.E() < b2.s() / 10 + 1) {
            this.y = b2.F();
            this.uiManager.activeView.a((int)51).h().a = "Nhấn nút 5 học tập kỹ năng mới";
        } else {
            this.uiManager.activeView.a((int)51).h().a = "";
        }
        this.uiManager.activeView.a((int)38).h().a = BaseScreen.f(GameDatabase.gameDatabase[0][b2.q()][0]);
        this.uiManager.activeView.a((int)40).h().a = "" + b2.s();
        if (this.uiManager.activeView.a((int)10).h().m == null) {
            this.uiManager.activeView.a((int)10).h().m = new SpriteWidget();
            this.uiManager.activeView.a((int)10).h().m.a = (byte)3;
            this.uiManager.activeView.a((int)10).h().m.a(0);
            this.uiManager.activeView.a((int)10).h().m.a(b2.C, false, (byte)-1);
        }
        for (n2 = 0; n2 < 4; ++n2) {
            byte by = (byte)(n2 + 1);
            this.uiManager.activeView.a((int)(n2 + 31)).h().a = "" + b2.c[by];
        }
    }

    public final void at() {
        this.uiManager.openUI("/data/ui/npcEnemy.ui", 296, this);
        if (this.uiManager.activeView.a((int)1).h().m == null) {
            this.uiManager.activeView.a((int)1).h().m = new SpriteWidget();
            this.uiManager.activeView.a((int)1).h().m.a = (byte)2;
            this.uiManager.activeView.a((int)1).h().m.a(296, false, (byte)0);
            this.uiManager.activeView.a((int)1).h().m.a(0);
        }
        this.uiManager.activeView.a(36).a(false);
    }

    private void a(int n2, int n3, int n4) {
        if (n4 != -1 && this.uiManager.activeView.a((int)n4).h().m != null) {
            this.uiManager.activeView.a(n4).a(false);
        }
        if (this.uiManager.activeView.a((int)n2).h().m == null) {
            this.uiManager.activeView.a((int)n2).h().m = new SpriteWidget();
            this.uiManager.activeView.a((int)n2).h().m.a = (byte)2;
            this.uiManager.activeView.a((int)n2).h().m.a(296, false, (byte)0);
            this.uiManager.activeView.a((int)n2).h().m.a(0);
        }
        this.uiManager.activeView.a((int)n2).h().m.a(n3);
    }

    public final void b(int n2, int n3) {
        switch (n2) {
            case 0: {
                this.uiManager.activeView.a((int)1).h().m.a(n3);
                return;
            }
            case 1: {
                for (n2 = 2; n2 < 4; ++n2) {
                    if (this.uiManager.activeView.a((int)n2).h().m == null) {
                        this.uiManager.activeView.a((int)n2).h().m = new SpriteWidget();
                        this.uiManager.activeView.a((int)n2).h().m.a = (byte)2;
                        this.uiManager.activeView.a((int)n2).h().m.a(0);
                    }
                    if (n2 % 2 == 1) {
                        this.uiManager.activeView.a((int)n2).h().m.a(0, false, (byte)-1);
                    } else if (game.WorldManager.u == -1) {
                        if (game.WorldManager.v == -1) {
                            this.uiManager.activeView.a((int)n2).h().m.a(game.WorldManager.getInstance().npcList[8].a.a, false, (byte)-1);
                        } else {
                            this.uiManager.activeView.a((int)n2).h().m.a(game.WorldManager.getInstance().npcList[game.WorldManager.v].a.a, false, (byte)-1);
                        }
                    } else {
                        this.uiManager.activeView.a((int)n2).h().m.a(game.WorldManager.getInstance().npcList[game.WorldManager.u].a.a, false, (byte)-1);
                    }
                    this.uiManager.activeView.a((int)n2).h().m.a(1);
                }
                this.uiManager.activeView.a((int)1).h().m.a(n3);
                return;
            }
            case 2: {
                for (n2 = 2; n2 < 4; ++n2) {
                    if (this.uiManager.activeView.a((int)n2).h().m != null) {
                        this.uiManager.activeView.a(n2).a(false);
                    }
                    if (this.uiManager.activeView.a((int)(n2 + 32)).h().m == null) {
                        this.uiManager.activeView.a((int)(n2 + 32)).h().m = new SpriteWidget();
                        this.uiManager.activeView.a((int)(n2 + 32)).h().m.a = (byte)2;
                        this.uiManager.activeView.a((int)(n2 + 32)).h().m.a(0);
                    }
                    if (n2 % 2 == 1) {
                        this.uiManager.activeView.a((int)(n2 + 32)).h().m.a(0, false, (byte)-1);
                    } else if (game.WorldManager.u == -1) {
                        if (game.WorldManager.v == -1) {
                            this.uiManager.activeView.a((int)(n2 + 32)).h().m.a(game.WorldManager.getInstance().npcList[8].a.a, false, (byte)-1);
                        } else {
                            this.uiManager.activeView.a((int)(n2 + 32)).h().m.a(game.WorldManager.getInstance().npcList[game.WorldManager.v].a.a, false, (byte)-1);
                        }
                    } else {
                        this.uiManager.activeView.a((int)(n2 + 32)).h().m.a(game.WorldManager.getInstance().npcList[game.WorldManager.u].a.a, false, (byte)-1);
                    }
                    this.uiManager.activeView.a((int)(n2 + 32)).h().m.a(1);
                }
                this.uiManager.activeView.a((int)1).h().m.a(n3);
                return;
            }
            case 3: {
                for (n2 = 2; n2 < 4; ++n2) {
                    if (this.uiManager.activeView.a((int)(n2 + 32)).h().m != null) {
                        this.uiManager.activeView.a(n2 + 32).a(false);
                    }
                    if (this.uiManager.activeView.a((int)(n2 + 2)).h().m == null) {
                        this.uiManager.activeView.a((int)(n2 + 2)).h().m = new SpriteWidget();
                        this.uiManager.activeView.a((int)(n2 + 2)).h().m.a = (byte)2;
                        this.uiManager.activeView.a((int)(n2 + 2)).h().m.a(0);
                    }
                    if (n2 % 2 == 1) {
                        this.uiManager.activeView.a((int)(n2 + 2)).h().m.a(0, false, (byte)-1);
                    } else if (game.WorldManager.u == -1) {
                        if (game.WorldManager.v == -1) {
                            this.uiManager.activeView.a((int)(n2 + 2)).h().m.a(game.WorldManager.getInstance().npcList[8].a.a, false, (byte)-1);
                        } else {
                            this.uiManager.activeView.a((int)(n2 + 2)).h().m.a(game.WorldManager.getInstance().npcList[game.WorldManager.v].a.a, false, (byte)-1);
                        }
                    } else {
                        this.uiManager.activeView.a((int)(n2 + 2)).h().m.a(game.WorldManager.getInstance().npcList[game.WorldManager.u].a.a, false, (byte)-1);
                    }
                    this.uiManager.activeView.a((int)(n2 + 2)).h().m.a(1);
                }
                this.uiManager.activeView.a((int)1).h().m.a(n3);
                this.L = game.BattleScreen.getInstance().j();
                this.M = this.player.partyPetCount;
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
                this.uiManager.activeView.a(36).a(true);
                return;
            }
            case 9: {
                this.uiManager.activeView.a(36).a(false);
                return;
            }
            case 10: {
                this.a(1, 4, 32);
                this.a(1, 4, 33);
                for (n2 = 4; n2 < 6; ++n2) {
                    this.uiManager.activeView.a(n2).a(false);
                }
                for (n2 = 7; n2 < 19; n2 += 2) {
                    this.uiManager.activeView.a(n2).a(172 + 17 * (n2 - 7) / 2, this.uiManager.activeView.a());
                    this.uiManager.activeView.a(n2 + 12).a(-30 + 17 * (n2 - 7) / 2, this.uiManager.activeView.a());
                }
                return;
            }
            case 11: {
                for (n2 = 4; n2 < 6; ++n2) {
                    this.uiManager.activeView.a(n2).a(false);
                }
                for (n2 = 7; n2 < 19; n2 += 2) {
                    this.uiManager.activeView.a(n2).a(false);
                    this.uiManager.activeView.a(n2 + 12).a(false);
                }
                this.a(1, 0, -1);
            }
        }
    }

    private void e(String text) {
        this.t = text;
        if (this.uiManager.activeView.a((int)1).h().m == null) {
            this.uiManager.activeView.a((int)1).h().m = new SpriteWidget();
            this.uiManager.activeView.a((int)1).h().m.a(0);
            this.uiManager.activeView.a((int)1).h().m.a = (byte)3;
            this.uiManager.activeView.a((int)1).h().m.a(257, false, (byte)-2);
        }
        this.uiManager.activeView.a((int)1).h().m.a((byte)9, (byte)-2);
        this.v = 0;
    }

    public final void au() {
        this.uiManager.openUI("/data/ui/openbox.ui", 257, this);
        this.e("Không có cái chìa khóa, có thể đến tài liệu cửa hàng mua sắm");
    }

    public final void av() {
        this.uiManager.openUI("/data/ui/openbox.ui", 257, this);
        this.e("Đạo cụ đã đủ");
    }

    public final void a(String text, int n2) {
        this.uiManager.openUI("/data/ui/openbox.ui", 257, this);
        this.e(text + " x " + n2);
    }

    public final void b(String text) {
        this.uiManager.openUI("/data/ui/openbox.ui", 257, this);
        this.e(text);
    }

    public final void aw() {
        if (this.uiManager.isUILoaded("/data/ui/openbox.ui")) {
            this.uiManager.closeUI("/data/ui/openbox.ui");
        }
    }

    public final boolean ax() {
        return !this.uiManager.isUILoaded("/data/ui/openbox.ui");
    }

    public final void c(String text) {
        this.uiManager.openUI("/data/ui/taskTip.ui", 257, this);
        String string = text;
        text = this;
        this.t = string;
        if (((ScriptEngine)text).p.activeView.a((int)1).h().m == null) {
            ((ScriptEngine)text).p.activeView.a((int)1).h().m = new SpriteWidget();
            ((ScriptEngine)text).p.activeView.a((int)1).h().m.a(0);
            ((ScriptEngine)text).p.activeView.a((int)1).h().m.a = (byte)3;
            ((ScriptEngine)text).p.activeView.a((int)1).h().m.a(257, false, (byte)-2);
        }
        ((ScriptEngine)text).p.activeView.a((int)1).h().m.a((byte)10, (byte)-2);
        ((ScriptEngine)text).v = 0;
    }

    private void br() {
        if (this.uiManager.isUILoaded("/data/ui/taskTip.ui")) {
            this.uiManager.closeUI("/data/ui/taskTip.ui");
        }
    }

    public final boolean ay() {
        return !this.uiManager.isUILoaded("/data/ui/taskTip.ui");
    }

    public final void openBodyShop() {
        this.selectedShopIndex = 0;
        this.dialogSubState = 0;
        this.uiManager.openUI("/data/ui/bodyShop.ui", 257, this);
        this.updateBodyShopDescription();
    }

    private void updateBodyShopDescription() {
        Object object = "";
        switch (this.selectedShopIndex) {
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
        this.uiManager.activeView.a((int)11).h().a = (String)object;
        if (this.selectedShopIndex > 0) {
            this.parentScreen.setBillingDialogState((byte)0);
            this.applyBodyShopPackage();
        }
    }

    private void applyBodyShopPackage() {
        switch (this.selectedShopIndex) {
            case 1: {
                this.parentScreen.setBillingPackage((byte)3);
                return;
            }
            case 2: {
                this.parentScreen.setBillingPackage((byte)4);
                return;
            }
            case 3: {
                this.parentScreen.setBillingPackage((byte)2);
            }
        }
    }

    public final void handleBodyShopInput() {
        block0 : switch (this.selectedShopIndex) {
            case 0: {
                if (this.parentScreen.isKeyPressed(4100) && this.dialogSubState == 0) {
                    this.uiManager.activeView.navigateSelection(0);
                    this.updateBodyShopDescription();
                    return;
                }
                if (this.parentScreen.isKeyPressed(8448) && this.dialogSubState == 0) {
                    this.uiManager.activeView.navigateSelection(1);
                    this.updateBodyShopDescription();
                    return;
                }
                if (this.parentScreen.isKeyPressed(196640)) {
                    this.parentScreen.setScreenMode((byte)26);
                    this.uiManager.closeUI("/data/ui/bodyShop.ui");
                    return;
                }
                if (!this.parentScreen.isKeyPressed(786432)) break;
                this.selectedSubMenuIndex = 0;
                this.parentScreen.setScreenMode((byte)6);
                this.uiManager.closeUI("/data/ui/bodyShop.ui");
                return;
            }
            default: {
                switch (this.parentScreen.getBillingDialogState()) {
                    case 0: {
                        if (this.parentScreen.isKeyPressed(4100) && this.dialogSubState == 0) {
                            this.uiManager.activeView.navigateSelection(0);
                            this.updateBodyShopDescription();
                            return;
                        }
                        if (this.parentScreen.isKeyPressed(8448) && this.dialogSubState == 0) {
                            this.uiManager.activeView.navigateSelection(1);
                            this.updateBodyShopDescription();
                            return;
                        }
                        if (this.dialogSubState == 0 && this.parentScreen.isKeyPressed(196640) || this.dialogSubState == 1 && this.parentScreen.isKeyPressed(65568)) {
                            if (this.dialogSubState == 0) {
                                this.applyBodyShopPackage();
                                if (this.parentScreen.getBillingPackageId() == 3) {
                                    int n2;
                                    if (game.WorldManager.levelUpPets != null) {
                                        game.WorldManager.levelUpPets.removeAllElements();
                                    }
                                    for (n2 = 0; n2 < game.Player.getInstance().partyPetCount && game.Player.getInstance().petParty[n2].getLevel() >= 50; ++n2) {
                                    }
                                    if (n2 >= game.Player.getInstance().partyPetCount) {
                                        this.dialogSubState = 1;
                                        this.uiManager.openUI("/data/ui/msgwarm.ui", 257, this);
                                        this.setWarningDialogMessage("Trong ba lô sủng vật đều đã max level", "Nhấn nút 5 để tiếp tục");
                                        return;
                                    }
                                }
                                if (this.parentScreen.getRequiredSmsCount() > 1) {
                                    this.parentScreen.setBillingDialogState((byte)1);
                                    return;
                                }
                                this.parentScreen.confirmBillingPurchase(1);
                                return;
                            }
                            this.dialogSubState = 0;
                            this.uiManager.closeUI("/data/ui/msgwarm.ui");
                            return;
                        }
                        if (!this.parentScreen.isKeyPressed(786432) || this.dialogSubState != 0) break block0;
                        this.selectedSubMenuIndex = 0;
                        this.parentScreen.setScreenMode((byte)6);
                        this.uiManager.closeUI("/data/ui/bodyShop.ui");
                        return;
                    }
                    case 1: {
                        if (this.parentScreen.isKeyPressed(196640)) {
                            this.parentScreen.handleBillingDialogAction(1);
                            return;
                        }
                        if (!this.parentScreen.isKeyPressed(262144 /* MASK_SOFT_RIGHT */)) break block0;
                        this.parentScreen.handleBillingDialogAction(2);
                        return;
                    }
                    case 2: {
                        if (this.parentScreen.isPaymentComplete()) {
                            if (this.parentScreen.getBillingPackageId() == 3) {
                                this.parentScreen.setScreenMode((byte)25);
                            }
                            this.parentScreen.setBillingDialogState((byte)5);
                        } else {
                            this.parentScreen.setBillingDialogState((byte)1);
                        }
                        this.dialogSubState = 0;
                        return;
                    }
                    case 3: {
                        if (!this.parentScreen.isKeyPressed(393216)) break block0;
                        this.parentScreen.handleBillingDialogAction(1);
                    }
                }
            }
        }
    }

    private boolean tickSaveDialog() {
        if (this.dialogSubState == 0) {
            this.dialogSubState = 1;
            this.openMsgTipUi();
            this.setMsgTipText("Đang lưu...");
            this.hideMsgTipButtons();
        } else if (this.dialogSubState == 1) {
            if (this.parentScreen.getBillingPackageId() == 3) {
                if (game.WorldManager.getInstance().k()) {
                    this.setMsgTipText("Lưu thành công");
                    this.dialogSubState = 2;
                }
            } else if (game.WorldManager.getInstance().n()) {
                this.setMsgTipText("Lưu thành công");
                this.dialogSubState = 2;
            }
        } else if (this.dialogSubState == 2) {
            this.uiManager.closeUI("/data/ui/msgtip.ui");
            this.dialogSubState = 3;
        } else if (this.dialogSubState == 3) {
            return true;
        }
        return false;
    }

    public final void openStandardDialog() {
        this.uiManager.openUI("/data/ui/dialog.ui", 257, this);
        this.uiManager.activeView.a(12).a(false);
        this.uiManager.activeView.a(13).a(false);
    }

    public final void a(String text, String string2, int n2) {
        this.uiManager.openUI("/data/ui/dialog.ui", 257, this);
        EngineUtils.a(string2, BaseScreen.getFontHeight(), this.uiManager.activeView.a(14).d(), BaseScreen.getSmallFont(), this.parentScreen.R.b);
        EngineUtils.c(this.uiManager.activeView.a(14).e());
        this.uiManager.activeView.a((int)14).h().a = EngineUtils.d(1);
        game.WorldManager.t = (byte)n2;
        game.WorldManager.s = (byte)-1;
        this.uiManager.activeView.a(8).a(false);
        this.uiManager.activeView.a(11).a(false);
        this.uiManager.activeView.a(12).a(true);
        this.uiManager.activeView.a(13).a(true);
        if (n2 == -1) {
            this.uiManager.activeView.a(12).a(false);
            this.uiManager.activeView.a(13).a(false);
        }
        switch (n2) {
            case 0: {
                this.uiManager.activeView.a(13).a(false);
                this.uiManager.activeView.a((int)12).h().a = text;
                return;
            }
            case 1: {
                this.uiManager.activeView.a(12).a(false);
                this.uiManager.activeView.a((int)13).h().a = text;
            }
        }
    }

    public final void b(int n2) {
        this.uiManager.activeView.a((int)14).h().a = EngineUtils.d(n2);
    }

    public final void aC() {
        this.uiManager.closeUI("/data/ui/dialog.ui");
    }

    public final boolean c(int n2, int n3) {
        if (n3 == -1) {
            return true;
        }
        switch (n2) {
            case 0: {
                if (!this.uiManager.activeView.a((int)11).h().m.a().e()) break;
                return true;
            }
            case 1: {
                if (!this.uiManager.activeView.a((int)8).h().m.a().e()) break;
                return true;
            }
        }
        this.isDialogActive = true;
        return false;
    }

    public final void a(int n2, int n3, String[] strArray, String text) {
        this.selectedSubMenuIndex = 0;
        this.uiManager.a(this.N[n2], 257, this);
        ((MenuWidget)this.uiManager.activeView.a((int)0)).a.a = n3;
        switch (n2) {
            case 0: {
                for (n2 = 0; n2 < strArray.length; ++n2) {
                    this.uiManager.activeView.a((int)(n2 + 12)).h().a = strArray[n2];
                }
                return;
            }
            case 1: {
                this.uiManager.activeView.a((int)5).h().a = text;
                for (n2 = 0; n2 < strArray.length; ++n2) {
                    this.uiManager.activeView.a((int)(9 + (n2 << 2))).h().a = strArray[n2];
                }
                return;
            }
            case 2: {
                this.uiManager.activeView.a(10).a(false);
                this.uiManager.activeView.a((int)8).h().a = "Trò chơi";
                this.uiManager.activeView.a((int)9).h().a = "Xác nhận";
                for (n2 = 0; n2 < strArray.length; ++n2) {
                    this.uiManager.activeView.a((int)(n2 + 5)).h().a = strArray[n2];
                }
                break;
            }
        }
    }

    public final int c(int n2) {
        if (this.parentScreen.isKeyPressed(4100)) {
            this.uiManager.activeView.navigateSelection(0);
            this.selectedSubMenuIndex = this.z[0];
        } else if (this.parentScreen.isKeyPressed(8448)) {
            this.uiManager.activeView.navigateSelection(1);
            this.selectedSubMenuIndex = this.z[0];
        } else if (this.parentScreen.isKeyPressed(196640)) {
            int n3 = n2;
            ScriptEngine h2 = this;
            h2.p.a(h2.N[n3]);
            return this.selectedSubMenuIndex;
        }
        return -1;
    }

    public final void a(int[] intArray, int[] nArray2, String[] strArray, String[] stringArray2) {
        int n2;
        this.selectedSubMenuIndex = 0;
        this.uiManager.openUI("/data/ui/taskOption.ui", 257, this);
        for (n2 = 0; n2 < stringArray2.length; ++n2) {
            this.uiManager.activeView.a((int)(n2 + 17)).h().a = stringArray2[n2];
        }
        block10: for (n2 = 0; n2 < intArray.length; ++n2) {
            if (this.uiManager.activeView.a((int)((n2 << 1) + 13)).h().m == null) {
                this.uiManager.activeView.a((int)((n2 << 1) + 13)).h().m = new SpriteWidget();
                this.uiManager.activeView.a((int)((n2 << 1) + 13)).h().m.a = (byte)2;
                if (intArray[n2] < 3 || intArray[n2] >= 5) {
                    this.uiManager.activeView.a((int)((n2 << 1) + 13)).h().m.a(0);
                    this.uiManager.activeView.a((int)((n2 << 1) + 13)).h().m.a(258, false, (byte)0);
                } else {
                    this.uiManager.activeView.a((int)((n2 << 1) + 13)).h().m.a(-1);
                    this.uiManager.activeView.a((int)((n2 << 1) + 13)).h().m.a(257, false, (byte)0);
                }
            }
            switch (intArray[n2]) {
                case 0: {
                    this.uiManager.activeView.a((int)((n2 << 1) + 13)).h().m.a(GameDatabase.gameDatabase[4][intArray2[n2]][1]);
                    this.uiManager.activeView.a((int)((n2 << 1) + 14)).h().a = strArray[n2];
                    continue block10;
                }
                case 1: {
                    this.uiManager.activeView.a((int)((n2 << 1) + 13)).h().m.a(GameDatabase.gameDatabase[3][intArray2[n2]][1]);
                    this.uiManager.activeView.a((int)((n2 << 1) + 14)).h().a = strArray[n2];
                    continue block10;
                }
                case 2: {
                    this.uiManager.activeView.a((int)((n2 << 1) + 13)).h().m.a(GameDatabase.gameDatabase[5][intArray2[n2]][1]);
                    this.uiManager.activeView.a((int)((n2 << 1) + 14)).h().a = strArray[n2];
                    continue block10;
                }
                case 3: {
                    this.uiManager.activeView.a((int)((n2 << 1) + 13)).h().m.a(84);
                    this.uiManager.activeView.a((int)((n2 << 1) + 14)).h().a = strArray[n2];
                    continue block10;
                }
                case 4: {
                    this.uiManager.activeView.a((int)((n2 << 1) + 13)).h().m.a(83);
                    this.uiManager.activeView.a((int)((n2 << 1) + 14)).h().a = strArray[n2];
                    continue block10;
                }
                case 5: {
                    continue block10;
                }
                case 6: {
                    this.uiManager.activeView.a((int)21).h().a = "#2" + BaseScreen.f(GameDatabase.spriteTable((byte)0, (short)intArray2[n2], (byte)0)) + " #0" + strArray[n2];
                }
            }
        }
    }

    public final int aD() {
        if (this.parentScreen.isKeyPressed(4100)) {
            this.uiManager.activeView.navigateSelection(0);
            this.selectedSubMenuIndex = this.z[0];
        } else if (this.parentScreen.isKeyPressed(8448)) {
            this.uiManager.activeView.navigateSelection(1);
            this.selectedSubMenuIndex = this.z[0];
        } else {
            if (this.parentScreen.isKeyPressed(196640)) {
                this.uiManager.closeUI("/data/ui/taskOption.ui");
                return this.selectedSubMenuIndex;
            }
            if (this.parentScreen.isKeyPressed(262144 /* MASK_SOFT_RIGHT */)) {
                this.uiManager.closeUI("/data/ui/taskOption.ui");
                return 1;
            }
        }
        return -1;
    }

    public final void aE() {
        this.openSmsInfoUi();
        this.c("Có dùng 10000 kim tiền để khôi phục trạng thái của tất cả sủng vật trong ba lô không?", "Tại chỗ sống lại");
    }

    public final void aF() {
        if (this.parentScreen.isKeyPressed(196640)) {
            if (this.dialogSubState == 0) {
                if (this.player.hasGold(10000)) {
                    this.player.addGold(-10000);
                    for (int i = 0; i < this.player.partyPetCount; ++i) {
                        this.player.petParty[i].I();
                        this.player.petParty[i].u(this.player.petParty[i].d[1]);
                    }
                    game.BattleScreen.getInstance().c();
                    this.parentScreen.setScreenMode((byte)0);
                    this.closeSmsInfoUi();
                    return;
                }
                this.E();
                this.setWarningDialogMessage("Kim tiền chưa đủ", "Nhấn nút 5 để tiếp tục");
                this.dialogSubState = 1;
                return;
            }
            for (int i3 = 0; i3 < this.player.partyPetCount; ++i3) {
                this.player.petParty[i3].l(1);
                this.player.petParty[i3].u(1);
                this.player.petParty[i3].c();
            }
            if (BaseScreen.isVipUnlocked) {
                this.parentScreen.setScreenMode((byte)102);
            } else {
                this.respawnPlayer();
            }
            this.F();
            return;
        }
        if (this.dialogSubState == 0 && this.parentScreen.isKeyPressed(786432)) {
            this.respawnPlayer();
            this.closeSmsInfoUi();
        }
    }

    private void respawnPlayer() {
        int n2 = -1;
        if (game.WorldManager.getInstance().f == 9 && game.WorldManager.getInstance().g != 0) {
            n2 = (byte)game.WorldManager.getInstance().g;
        }
        if (n2 == -1) {
            game.WorldManager.getInstance();
            if (game.WorldManager.i()) {
                game.WorldManager.getInstance().f();
                this.player.y = false;
                game.GameStateController.getInstance().a((byte)9);
                return;
            }
            game.GameStateController.getInstance().a((byte)7);
            return;
        }
        if (game.WorldManager.getInstance().g == 0) {
            short[] shortArray = new short[]{15, 194, 433, 16, 142, 357, 17, 97, 268, 18, 183, 224};
            for (int i = 0; i < game.WorldManager.getInstance().npcList.length; ++i) {
                for (int i3 = 0; i3 < shortArray.length / 3; ++i3) {
                    if (game.WorldManager.getInstance().npcList[i].I != shortArray[i3 * 3]) continue;
                    game.WorldManager.getInstance().npcList[i].b(shortArray[i3 * 3 + 1], shortArray[i3 * 3 + 2]);
                }
            }
        }
        game.WorldManager.u = (short)-1;
        for (int i4 = 0; i4 < this.player.partyPetCount; ++i4) {
            this.player.petParty[i4].l(1);
            this.player.petParty[i4].u(1);
            this.player.petParty[i4].c();
        }
        game.WorldManager.getInstance().f = this.m[n2 << 2];
        game.WorldManager.getInstance().g = this.m[(n2 << 2) + 1];
        game.Player.getInstance().b(this.m[(n2 << 2) + 2], this.m[(n2 << 2) + 3]);
        game.Player.getInstance().b.b(this.m[(n2 << 2) + 2], this.m[(n2 << 2) + 3]);
        game.Player.getInstance().facingDirection = (byte)2;
        game.GameStateController.getInstance().a((byte)10);
    }

    public final void aG() {
        this.parentScreen.setBillingPackage((byte)0);
        this.parentScreen.setBillingDialogState((byte)0);
        this.openSmsInfoUi();
        Object object = new int[]{4, 1, 4};
        object = BaseScreen.f(599) + BaseScreen.a(604, object);
        this.c((String)object, "Kích hoạt");
    }

    public final void aH() {
        this.parentScreen.setBillingPackage((byte)1);
        this.parentScreen.setBillingDialogState((byte)0);
        this.openSmsInfoUi();
        Object object = new int[]{2, 1, 2};
        object = BaseScreen.f(600) + BaseScreen.a(604, object);
        this.c((String)object, "Mua sắm tất trúng cầu");
    }

    public final void aI() {
        this.dialogSubState = 0;
        this.parentScreen.setBillingPackage((byte)4);
        this.parentScreen.setBillingDialogState((byte)0);
        this.openSmsInfoUi();
        Object object = new int[]{2, 1, 2};
        object = BaseScreen.f(603) + BaseScreen.a(604, object);
        this.c((String)object, "Mua sắm huy hiệu");
    }

    public final void aJ() {
        this.dialogSubState = 0;
        this.parentScreen.setBillingPackage((byte)2);
        this.parentScreen.setBillingDialogState((byte)0);
        this.openSmsInfoUi();
        Object object = new int[]{2, 1, 2};
        object = BaseScreen.f(601) + BaseScreen.a(604, object);
        this.c((String)object, "Mua sắm kim tiền");
    }

    private void openSmsInfoUi() {
        this.uiManager.openUI("/data/ui/smsInfo.ui", 257, this);
        if (this.parentScreen instanceof WorldManager) {
            this.uiManager.activeView.a(6).a(true);
            this.uiManager.activeView.a(7).a(true);
            this.uiManager.activeView.a(10).a(false);
            this.uiManager.activeView.a(11).a(false);
            return;
        }
        this.uiManager.activeView.a(6).a(false);
        this.uiManager.activeView.a(7).a(false);
        this.uiManager.activeView.a(10).a(true);
        this.uiManager.activeView.a(11).a(true);
        this.uiManager.activeView.a((int)10).h().a = "Xác nhận";
        this.uiManager.activeView.a((int)11).h().a = "Phản hồi";
    }

    private void c(String text, String string2) {
        this.uiManager.activeView.a((int)8).h().a = text;
        this.uiManager.activeView.a((int)5).h().a = string2;
    }

    private void closeSmsInfoUi() {
        this.uiManager.closeUI("/data/ui/smsInfo.ui");
    }

    public final void showSmsTipDialog() {
        if (!this.uiManager.isUIOpen("/data/ui/smsTip.ui")) {
            this.uiManager.openUI("/data/ui/smsTip.ui", 257, this);
        }
        for (int i = 0; i < 3; ++i) {
            this.uiManager.activeView.a(i + 6).a(false);
        }
        this.isDialogActive = true;
    }

    public final void d(String text) {
        this.isDialogActive = true;
        this.uiManager.activeView.a((int)5).h().a = text;
    }

    public final void closeSmsTipDialog() {
        this.uiManager.closeUI("/data/ui/smsTip.ui");
    }

    public final void handleSmsTipInput() {
        switch (this.parentScreen.getBillingDialogState()) {
            case 0: {
                if (this.parentScreen.isKeyPressed(16400) || this.parentScreen.isKeyPressed(32832)) break;
                if (this.parentScreen.isKeyPressed(196640)) {
                    if (this.parentScreen.getRequiredSmsCount() > 1) {
                        this.parentScreen.setBillingDialogState((byte)1);
                        return;
                    }
                    this.parentScreen.confirmBillingPurchase(1);
                    return;
                }
                if (!this.parentScreen.isKeyPressed(786432)) break;
                this.closeSmsInfoUi();
                this.parentScreen.setBillingDialogState((byte)5);
                this.parentScreen.a(this.parentScreen.Q);
                return;
            }
            case 1: {
                if (this.parentScreen.isKeyPressed(196640)) {
                    this.parentScreen.handleBillingDialogAction(1);
                    return;
                }
                if (!this.parentScreen.isKeyPressed(262144 /* MASK_SOFT_RIGHT */)) break;
                this.parentScreen.handleBillingDialogAction(2);
                return;
            }
            case 2: {
                boolean bl = false;
                if (this.parentScreen.P == 100) {
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
                if (!bl) break;
                this.x = 0;
                if (this.parentScreen.isPaymentComplete()) {
                    this.closeSmsInfoUi();
                    this.closeSmsTipDialog();
                    this.parentScreen.a(this.parentScreen.Q);
                } else {
                    this.parentScreen.setBillingDialogState((byte)5);
                }
                this.dialogSubState = 0;
                return;
            }
            case 3: {
                if (!this.parentScreen.isKeyPressed(393216)) break;
                this.parentScreen.handleBillingDialogAction(1);
            }
        }
    }

    public final void a(byte val, int n2, int n3) {
        this.selectedShopIndex = 0;
        this.R = val;
        this.S = (byte)n2;
        switch (n2) {
            case 0: {
                this.uiManager.openUI("/data/ui/wharf1.ui", 257, this);
                this.uiManager.activeView.a((int)8).h().a = BaseScreen.f(n3);
                for (n2 = 0; n2 < this.P[val].length; ++n2) {
                    this.uiManager.activeView.a((int)(n2 + 5)).h().a = BaseScreen.f(this.P[val][n2]);
                }
                break;
            }
            case 1: {
                this.uiManager.openUI("/data/ui/wharf2.ui", 257, this);
                this.uiManager.activeView.a((int)10).h().a = BaseScreen.f(n3);
                for (n2 = 0; n2 < this.P[val].length; ++n2) {
                    this.uiManager.activeView.a((int)(n2 + 5)).h().a = BaseScreen.f(this.P[val][n2]);
                }
                break;
            }
        }
        this.uiManager.activeView.a((int)(5 + this.P[val].length)).h().a = "Không ra hàng";
    }

    public final void aN() {
        if (this.parentScreen.isKeyPressed(4100) && !this.j()) {
            this.uiManager.activeView.navigateSelection(0);
        } else if (this.parentScreen.isKeyPressed(8448) && !this.j()) {
            this.uiManager.activeView.navigateSelection(1);
        } else if (this.parentScreen.isKeyPressed(196640) && !this.j()) {
            if (this.selectedShopIndex == this.Q[this.R].length / 9) {
                switch (this.S) {
                    case 0: {
                        this.uiManager.closeUI("/data/ui/wharf1.ui");
                        break;
                    }
                    case 1: {
                        this.uiManager.closeUI("/data/ui/wharf2.ui");
                    }
                }
                this.parentScreen.setScreenMode((byte)0);
                if (game.WorldManager.u != -1 && game.WorldManager.getInstance().npcList[game.WorldManager.u].u() == 0) {
                    game.WorldManager.getInstance().a(game.WorldManager.getInstance().npcList[game.WorldManager.u].i, game.WorldManager.getInstance().npcList[game.WorldManager.u].j - 40, game.WorldManager.getInstance().npcList[game.WorldManager.u]);
                }
            } else if (game.WorldManager.getInstance().M.b[game.WorldManager.a((int)this.Q[this.R][this.selectedShopIndex * 9 + 6], (int)this.Q[this.R][this.selectedShopIndex * 9 + 7])] != null && game.WorldManager.getInstance().M.b[game.WorldManager.a((int)this.Q[this.R][this.selectedShopIndex * 9 + 6], (int)this.Q[this.R][this.selectedShopIndex * 9 + 7])][this.Q[this.R][this.selectedShopIndex * 9 + 8]] == 3) {
                game.WorldManager.getInstance().f = this.Q[this.R][this.selectedShopIndex * 9];
                game.WorldManager.getInstance().g = this.Q[this.R][this.selectedShopIndex * 9 + 1];
                game.WorldManager.getInstance().playerSpawnX = this.Q[this.R][this.selectedShopIndex * 9 + 2];
                game.WorldManager.getInstance().playerSpawnY = this.Q[this.R][this.selectedShopIndex * 9 + 3];
                game.WorldManager.w = (byte)this.Q[this.R][this.selectedShopIndex * 9 + 4];
                game.WorldManager.getInstance().j = -1;
                this.parentScreen.setScreenMode((byte)29);
                switch (this.S) {
                    case 0: {
                        this.uiManager.closeUI("/data/ui/wharf1.ui");
                        break;
                    }
                    case 1: {
                        this.uiManager.closeUI("/data/ui/wharf2.ui");
                    }
                }
            } else {
                this.b("Đường thủy chưa mở");
            }
        } else if (this.parentScreen.isKeyPressed(262144 /* MASK_SOFT_RIGHT */) && !this.j()) {
            if (game.WorldManager.u != -1 && game.WorldManager.getInstance().npcList[game.WorldManager.u].u() == 0) {
                game.WorldManager.getInstance().a(game.WorldManager.getInstance().npcList[game.WorldManager.u].i, game.WorldManager.getInstance().npcList[game.WorldManager.u].j - 40, game.WorldManager.getInstance().npcList[game.WorldManager.u]);
            }
            switch (this.S) {
                case 0: {
                    this.uiManager.closeUI("/data/ui/wharf1.ui");
                    break;
                }
                case 1: {
                    this.uiManager.closeUI("/data/ui/wharf2.ui");
                }
            }
            this.parentScreen.setScreenMode((byte)0);
        }
        this.f();
    }

    public final void aO() {
        this.selectedSubMenuIndex = 0;
        this.uiManager.openUI("/data/ui/shopbuy.ui", 257, this);
        this.selectedSubMenuIndex = 0;
        this.dialogSubState = 0;
        ((MenuWidget)this.uiManager.activeView.a((int)0)).a.a = 1;
        ((MenuWidget)this.uiManager.activeView.a((int)0)).a.a(0);
        this.uiManager.activeView.a(41).a(false);
        this.uiManager.activeView.a(43).a(false);
        this.uiManager.activeView.a((int)5).h().a = "Mua";
        this.uiManager.activeView.a(57).a(true);
        this.uiManager.activeView.a(58).a(true);
        this.uiManager.activeView.a((int)57).h().a = "Mua sắm";
        this.uiManager.activeView.a((int)58).h().a = "Phản hồi";
        this.uiManager.activeView.a(39).a(false);
        this.uiManager.activeView.a(40).a(false);
        ScriptEngine h2 = this;
        h2.w = ((MenuWidget)h2.p.activeView.a((int)0)).a.e;
        h2.h = ((MenuWidget)h2.p.activeView.a((int)0)).a.f;
        if (h2.p.activeView.a((int)51).h().m == null) {
            h2.p.activeView.a((int)51).h().m = new SpriteWidget();
            h2.p.activeView.a((int)51).h().m.a(0);
            h2.p.activeView.a((int)51).h().m.a = (byte)2;
            h2.p.activeView.a((int)51).h().m.a(258, false, (byte)-1);
        }
        h2.p.activeView.a((int)51).h().m.a(GameDatabase.gameDatabase[5][0][1]);
        h2.p.activeView.a((int)14).h().a = BaseScreen.f(GameDatabase.gameDatabase[5][0][0]);
        h2.p.activeView.a((int)15).h().a = "5000";
        h2.p.activeView.a((int)45).h().m.a(84);
        h2.p.activeView.a((int)56).h().a = "Ấp trứng ra sủng vật";
        h2.p.activeView.a((int)44).h().a = "" + h2.q.E();
        h2.p.activeView.a(38).b(102 + h2.h * 84 / GameDatabase.gameDatabase[5].length, h2.p.activeView.a());
    }

    private void by() {
        this.uiManager.closeUI("/data/ui/shopbuy.ui");
    }

    public final int aP() {
        if (this.parentScreen.isKeyPressed(196640)) {
            if (this.dialogSubState == 0) {
                if (this.player.hasGold(5000)) {
                    if (this.player.hasBadge(0)) {
                        this.E();
                        this.a("Đã có trứng sủng vật, không cần mua sắm", "Nhấn nút 5 để tiếp tục");
                        this.dialogSubState = 2;
                    } else {
                        this.player.e(0, -1);
                        this.E();
                        this.a("Đã thành công mua sắm #2 trứng sủng vật", "Nhấn nút 5 để tiếp tục");
                        this.dialogSubState = 2;
                    }
                } else {
                    this.E();
                    this.setWarningDialogMessage("Kim tiền chưa đủ", "Nhấn nút 5 để tiếp tục");
                    this.dialogSubState = 1;
                }
            } else if (this.dialogSubState > 0) {
                this.F();
                if (this.dialogSubState == 1) {
                    this.parentScreen.setScreenMode((byte)102);
                } else if (this.dialogSubState == 2) {
                    game.OverworldScreen.m = 0;
                    this.by();
                    this.parentScreen.setScreenMode((byte)0);
                }
            }
        } else if (this.parentScreen.isKeyPressed(262144 /* MASK_SOFT_RIGHT */) && this.dialogSubState == 0) {
            game.OverworldScreen.m = 1;
            this.by();
            this.parentScreen.setScreenMode((byte)0);
        }
        return -1;
    }

    public final void aQ() {
        this.uiManager.openUI("/data/ui/wharf2.ui", 257, this);
        ((MenuWidget)this.uiManager.activeView.a((int)0)).a.f = this.d;
        this.dialogSubState = 0;
        this.uiManager.activeView.a((int)10).h().a = "Tiện lợi điếm";
        this.uiManager.activeView.a((int)12).h().a = "Tiến vào";
        for (int i = 0; i < this.T.length; ++i) {
            this.uiManager.activeView.a((int)(i + 5)).h().a = this.T[i];
        }
    }

    public final void aR() {
        if (this.parentScreen.isKeyPressed(4100) && !this.j()) {
            this.uiManager.activeView.navigateSelection(0);
            return;
        }
        if (this.parentScreen.isKeyPressed(8448) && !this.j()) {
            this.uiManager.activeView.navigateSelection(1);
            return;
        }
        if (this.parentScreen.isKeyPressed(196640) && !this.j()) {
            if (this.dialogSubState == 0) {
                switch (this.d) {
                    case 0: {
                        this.uiManager.closeUI("/data/ui/wharf2.ui");
                        this.parentScreen.setScreenMode((byte)31);
                        return;
                    }
                    case 1: 
                    case 2: {
                        game.WorldManager.getInstance();
                        if (game.WorldManager.K) {
                            this.uiManager.closeUI("/data/ui/wharf2.ui");
                            this.selectedShopIndex = 0;
                            this.parentScreen.setScreenMode((byte)7);
                            return;
                        }
                        this.E();
                        this.dialogSubState = 1;
                        this.a("Công năng theo đạo học sau mở ra", "Nhấn nút 5 để tiếp tục");
                        return;
                    }
                    case 3: {
                        this.uiManager.closeUI("/data/ui/wharf2.ui");
                        this.parentScreen.setScreenMode((byte)32);
                        return;
                    }
                    case 4: {
                        this.uiManager.closeUI("/data/ui/wharf2.ui");
                        this.parentScreen.setScreenMode((byte)0);
                    }
                }
                return;
            }
            this.dialogSubState = 0;
            this.F();
            this.isDialogActive = true;
            return;
        }
        if (this.dialogSubState == 0 && this.parentScreen.isKeyPressed(262144 /* MASK_SOFT_RIGHT */) && !this.j()) {
            this.uiManager.closeUI("/data/ui/wharf2.ui");
            this.parentScreen.setScreenMode((byte)0);
        }
    }

    public final void a(int[] intArray) {
        this.z = intArray;
        if (this.parentScreen instanceof WorldManager) {
            switch (((WorldManager)this.parentScreen).P) {
                case 0: {
                    return;
                }
                case 1: {
                    int[] intArray2 = intArray;
                    Object var2_22 = null;
                    this.selectedSubMenuIndex = intArray2[0];
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
                    this.selectedSubMenuIndex = nArray4[1];
                    h3.bm();
                    return;
                }
                case 6: {
                    int[] nArray5 = intArray;
                    ScriptEngine h4 = this;
                    this.selectedSubMenuIndex = nArray5[0];
                    if (BaseScreen.isVipUnlocked) {
                        h4.p.activeView.a((int)14).h().a = BaseScreen.f(605 + h4.b);
                        return;
                    }
                    h4.p.activeView.a((int)14).h().a = BaseScreen.f(606 + h4.b);
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
                    this.selectedShopIndex = nArray7[1];
                    return;
                }
                case 10: {
                    int[] nArray8 = intArray;
                    ScriptEngine h6 = this;
                    this.selectedSubMenuIndex = nArray8[1];
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
                    this.selectedShopIndex = nArray9[0];
                    h7.b = nArray9[1];
                    return;
                }
                case 12: {
                    int[] nArray10 = intArray;
                    Object var2_30 = null;
                    this.selectedSubMenuIndex = nArray10[1];
                    return;
                }
                case 13: {
                    if (this.dialogSubState == 0) {
                        this.selectedSubMenuIndex = intArray[0];
                        return;
                    }
                    this.selectedShopIndex = intArray[0];
                    return;
                }
                case 14: {
                    int[] nArray11 = intArray;
                    Object var2_31 = null;
                    this.selectedShopIndex = nArray11[0];
                    return;
                }
                case 15: {
                    int[] nArray12 = intArray;
                    Object var2_32 = null;
                    this.selectedShopIndex = nArray12[0];
                    return;
                }
                case 16: {
                    int[] nArray13 = intArray;
                    Object var2_33 = null;
                    this.selectedSubMenuIndex = nArray13[0];
                    return;
                }
                case 17: 
                case 18: 
                case 19: {
                    int[] nArray14 = intArray;
                    Object var2_34 = null;
                    this.selectedShopIndex = nArray14[0];
                    return;
                }
                case 20: {
                    this.selectedShopIndex = intArray[1];
                    return;
                }
                case 24: {
                    int[] nArray15 = intArray;
                    Object var2_35 = null;
                    this.selectedShopIndex = nArray15[0];
                    return;
                }
                case 28: {
                    int[] nArray16 = intArray;
                    Object var2_36 = null;
                    this.selectedShopIndex = nArray16[0];
                    return;
                }
                case 27: {
                    this.d = intArray[0];
                }
            }
            return;
        }
        if (this.parentScreen instanceof BattleScreen) {
            switch (((BattleScreen)this.parentScreen).P) {
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
                    this.selectedSubMenuIndex = nArray18[0];
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
                    this.selectedShopIndex = nArray19[0];
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
                    this.selectedSubMenuIndex = nArray21[0];
                }
                case 23: {
                    int[] nArray21 = intArray;
                    this.selectedSubMenuIndex = intArray[0];
                }
            }
        }
    }

    private void c(int[] intArray) {
        if (this.dialogSubState == 0) {
            this.selectedSubMenuIndex = intArray[0];
            this.f(this.selectedSubMenuIndex);
            return;
        }
        if (this.dialogSubState == 1) {
            this.selectedShopIndex = intArray[0];
            return;
        }
        if (this.dialogSubState == 2) {
            this.r = intArray[0];
            switch (this.selectedShopIndex) {
                case 0: {
                    this.be();
                }
            }
        }
    }

    private void d(int[] intArray) {
        if (this.dialogSubState == 0) {
            this.selectedSubMenuIndex = intArray[0];
            return;
        }
        this.r = intArray[0];
    }

    static {
        A = new String[]{"Thủy Kimura", "Bích Thủy thành", "Nguyên Mộc Thành", "Niêm Thổ Thành", "Hắc Thạch thành", "Thiên không", "Xa cổ"};
        B = new short[]{1, 0, 196, 208, 0, 2, 1, 196, 208, 0, 3, 3, 196, 208, 0, 4, 5, 320, 352, 0, 5, 3, 320, 196, 0, 7, 2, 288, 112, 0, 8, 0, 160, 144, 0};
    }
}

