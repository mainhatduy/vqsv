/*
 * Decompiled with CFR 0.152.
 */
package game;

import game.BattleScreen;
import game.TitleScreen;
import game.Player;
import game.ScriptEngine;
import game.WorldManager;
import java.io.InputStream;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;
import javax.microedition.media.Manager;
import javax.microedition.media.MediaException;
import javax.microedition.media.Player;
import javax.microedition.media.control.VolumeControl;
import org.recompile.mobile.PlatformGraphics;

/**
 * GameStateController - Central state machine coordinating transitions between gameplay, battle, title, and menus.
 */
public final class GameStateController
extends BaseScreen {
    private static GameStateController h = null;
    private byte currentState;
    private byte previousState;
    private int k;
    private Image l;
    private BaseScreen m;
    private static int n = 0;
    private static int o = 10;
    private String[] p = new String[]{"Hỏa hệ khắc mộc hệ", "Mộc hệ khắc thổ hệ", "Thổ hệ khắc thủy hệ", "Thủy hệ khắc hỏa hệ", "Quỷ hệ khắc phong hệ", "Phong hệ khắc điện hệ", "Điện hệ khắc quỷ hệ"};
    private String[] q = new String[]{"Thuyền càng đi càng xa", "Bơi... bơi...", "Đích đến ngày càng gần", "Thuyền nhỏ đang chạy"};
    public long a = 0L;
    public long b = 0L;
    public long c = 0L;
    public long d = 0L;
    public long e = 0L;
    public long f = 0L;
    private boolean r = false;
    private String s = "";
    public byte g = 0;
    private Player t;
    private VolumeControl u;
    private static String v = null;

    public static GameStateController getInstance() {
        if (h == null) {
            h = new GameStateController();
            v = "0";
        }
        return h;
    }

    public final void c(boolean flag) {
        if (flag) {
            this.c();
            return;
        }
        this.previousState();
    }

    public final void c() {
        this.f = System.currentTimeMillis();
        this.a((byte)3);
        super.c(true);
    }

    private void j() {
        this.a((byte)1);
        super.c(false);
    }

    public final void setState(byte val) {
        if (val >= 24) {
            return;
        }
        this.previousState = this.currentState;
        switch (this.currentState) {
            case 2: {
                break;
            }
            case 4: {
                break;
            }
            case 16: {
                break;
            }
            case 15: {
                break;
            }
            case 6: {
                break;
            }
            case 7: 
            case 9: 
            case 12: 
            case 22: 
            case 23: {
                n = 0;
                break;
            }
            case 10: {
                break;
            }
            case 8: 
            case 11: 
            case 13: {
                break;
            }
            case 3: {
                ScreenView.getInstance().a = -1;
            }
        }
        this.currentState = val;
        switch (val) {
            case 3: {
                ScreenView.getInstance().c(0, 19);
                break;
            }
            case 15: {
                this.l = EngineUtils.a("/data/logo/", "0");
                break;
            }
            case 12: 
            case 22: {
                game.GameStateController.u();
                n = 0;
                break;
            }
            case 23: {
                game.GameStateController.u();
                n = 0;
                val = (byte)EngineUtils.a(this.q.length);
                this.s = this.q[val];
                break;
            }
            case 9: {
                game.GameStateController.u();
                n = 0;
                val = (byte)EngineUtils.a(this.p.length);
                this.s = this.p[val];
                break;
            }
            case 11: {
                game.WorldManager.getInstance().S.g = true;
                break;
            }
            case 2: {
                if (game.WorldManager.getInstance().c == null) break;
                game.WorldManager.getInstance().c.J();
            }
        }
        this.k = 0;
    }

    public final byte getState() {
        return this.currentState;
    }

    public final void g() {
        if (this.currentState == 9 || this.currentState == 22 || this.currentState == 23) {
            this.n();
            this.r = true;
            this.resetInputState();
            return;
        }
        if (this.currentState != 2) {
            this.n();
            this.r = true;
            this.a((byte)2);
            this.resetInputState();
        }
    }

    private void k() {
        this.a("0");
        this.a(this.previousState);
        this.r = false;
        this.resetInputState();
    }

    public final boolean d() {
        this.s();
        this.S = game.ScriptEngine.getInstance();
        this.R = UIManager.getInstance();
        this.S.a(this);
        ImageCache.getImage();
        AnimationCache.getAnimationData();
        GameDatabase.spriteTable();
        game.GameStateController.e(0);
        game.GameStateController.D();
        game.WorldManager.getInstance();
        game.WorldManager.i();
        game.GameStateController.t();
        return true;
    }

    public final void f() {
        if (this.m != null) {
            this.m.f();
            this.m = null;
        }
    }

    public final void b() {
        if (!this.Y) {
            return;
        }
        this.S();
        switch (this.currentState) {
            case 3: {
                if (!game.GameStateController.v()) {
                    this.d();
                }
                if (!ScreenView.getInstance().b || !game.GameStateController.v()) break;
                this.a((byte)15);
                break;
            }
            case 2: {
                if (!this.k(262144 /* MASK_SOFT_RIGHT */)) break;
                this.k();
                break;
            }
            case 4: {
                if (this.k(131072 /* MASK_SOFT_LEFT */)) {
                    this.previousState();
                    break;
                }
                if (!this.k(262144 /* MASK_SOFT_RIGHT */)) break;
                this.k();
                break;
            }
            case 16: {
                ++this.k;
                if (this.k < 10) break;
                this.l = null;
                this.a((byte)6);
                break;
            }
            case 15: {
                ++this.k;
                if (this.k < 10) break;
                this.k = 0;
                this.l = null;
                this.l = EngineUtils.a("/data/logo/", "cwalogo");
                this.a((byte)16);
                break;
            }
            case 6: {
                if (this.k(131072 /* MASK_SOFT_LEFT */)) {
                    this.g = (byte)2;
                    this.a(v);
                    this.a((byte)7);
                    break;
                }
                if (!this.k(262144 /* MASK_SOFT_RIGHT */)) break;
                this.g = 0;
                this.a((byte)7);
                break;
            }
            case 9: 
            case 22: 
            case 23: {
                this.f();
                this.m = game.WorldManager.getInstance();
                this.m.d();
                game.Player.U = false;
                this.a((byte)11);
                if (this.r) {
                    GameStateController i2 = this;
                    if (i2.i != 2) {
                        i2.a((byte)2);
                    }
                }
                this.a(this.m);
                break;
            }
            case 10: {
                this.f();
                this.m = game.WorldManager.getInstance();
                ((WorldManager)this.m).p();
                this.a(this.m);
                this.a((byte)11);
                break;
            }
            case 7: {
                this.f();
                this.m = game.TitleScreen.getInstance();
                this.m.d();
                this.a(this.m);
                this.a((byte)8);
                break;
            }
            case 12: {
                if (!game.GameStateController.v()) {
                    this.m = null;
                    this.m = game.BattleScreen.getInstance();
                    this.m.d();
                    this.a(this.m);
                    if (((BattleScreen)this.m).b == 0) {
                        ScreenView.getInstance().c(-2013265920, 6);
                    } else if (((BattleScreen)this.m).b == 2) {
                        ScreenView.getInstance().c(-2013265920, 8);
                    } else if (((BattleScreen)this.m).b == 1) {
                        ScreenView.getInstance().c(-2013265920, 7);
                    }
                }
                if (game.GameStateController.v()) {
                    ScreenView.getInstance().b();
                    game.Player.U = false;
                }
                if (!ScreenView.getInstance().b) break;
                ((BattleScreen)this.m).g();
                this.a((byte)13);
                break;
            }
            case 8: 
            case 11: 
            case 13: 
            case 20: {
                if (this.m == null) break;
                this.m.b();
            }
        }
        if (game.WorldManager.getInstance().f == 3 && game.WorldManager.getInstance().g == 7 && this.c == 0L && this.a != 0L) {
            this.b = System.currentTimeMillis();
        }
        this.e = System.currentTimeMillis();
    }

    public final void b(Graphics g) {
        if (!this.Y) {
            return;
        }
        ((PlatformGraphics)g).setFont(game.GameStateController.D());
        switch (this.currentState) {
            case 3: {
                ((PlatformGraphics)g).setColor(0xFFFFFF);
                ((PlatformGraphics)g).fillRect(0, 0, game.GameStateController.w(), game.GameStateController.x());
                ScreenView.getInstance().a((Graphics)g);
                return;
            }
            case 2: {
                ((PlatformGraphics)g).setColor(0);
                ((PlatformGraphics)g).fillRect(0, 0, game.GameStateController.w(), game.GameStateController.x());
                ((PlatformGraphics)g).setColor(0xFFFFFF);
                ((PlatformGraphics)g).drawString("Trò chơi tạm dừng", game.GameStateController.w() >> 1, game.GameStateController.z(), 33);
                ((PlatformGraphics)g).drawString("Phản hồi", game.GameStateController.w() - 2, game.GameStateController.x() - 2, 40);
                return;
            }
            case 4: {
                return;
            }
            case 15: {
                ((PlatformGraphics)g).setColor(0xFFFFFF);
                ((PlatformGraphics)g).fillRect(0, 0, game.GameStateController.w(), game.GameStateController.x());
                ((PlatformGraphics)g).setColor(0xFFFFFF);
                ((PlatformGraphics)g).setFont(game.GameStateController.D());
                if (this.l == null) break;
                ((PlatformGraphics)g).drawImage(this.l, game.GameStateController.y(), game.GameStateController.z(), 3);
                return;
            }
            case 16: {
                ((PlatformGraphics)g).setColor(game.GameStateController.C());
                ((PlatformGraphics)g).fillRect(0, 0, game.GameStateController.w(), game.GameStateController.x());
                if (this.l == null) break;
                ((PlatformGraphics)g).drawImage(this.l, (game.GameStateController.w() - this.l.getWidth()) / 2, (game.GameStateController.x() - this.l.getHeight()) / 2, 20);
                return;
            }
            case 6: {
                ((PlatformGraphics)g).setColor(0);
                ((PlatformGraphics)g).fillRect(0, 0, game.GameStateController.w(), game.GameStateController.x());
                ((PlatformGraphics)g).setFont(game.GameStateController.D());
                ((PlatformGraphics)g).setColor(0xFFFFFF);
                ((PlatformGraphics)g).drawString(game.GameStateController.f(8), game.GameStateController.w() >> 1, game.GameStateController.z() - 12, 17);
                ((PlatformGraphics)g).drawString(game.GameStateController.f(4), 2, game.GameStateController.x() - 2, 36);
                ((PlatformGraphics)g).drawString(game.GameStateController.f(5), game.GameStateController.w() - 2, game.GameStateController.x() - 2, 40);
                ((PlatformGraphics)g).setColor(16739328);
                ((PlatformGraphics)g).drawString(game.GameStateController.f(9), game.GameStateController.w() >> 1, game.GameStateController.z() + 12, 17);
                return;
            }
            case 7: {
                return;
            }
            case 12: {
                game.WorldManager.getInstance().b.a((Graphics)g);
                if (!game.GameStateController.v()) break;
                ScreenView.getInstance().a((Graphics)g);
                game.Player.U = false;
                return;
            }
            case 9: {
                Graphics graphics = g;
                g = this;
                if (game.Player.U) {
                    byte by;
                    graphics.setColor(0);
                    graphics.fillRect(0, 0, game.GameStateController.w(), game.GameStateController.x());
                    if (n % 4 == 3) {
                        game.Player.getInstance().a((byte)1, (byte)-1, false);
                    } else {
                        game.Player.getInstance().a((byte)(n % 4), (byte)-1, false);
                    }
                    game.Player.getInstance().n = by = (byte)(n % 4);
                    game.Player.getInstance().a(graphics, MapEngine.getInstance().a, MapEngine.getInstance().b - n);
                } else {
                    graphics.setColor(0);
                    graphics.fillRect(0, 0, game.GameStateController.w(), game.GameStateController.x());
                }
                if (n < 148) {
                    n += o;
                }
                if (n > 148) {
                    n = 148;
                }
                if (!game.Player.U) {
                    graphics.setColor(0);
                    graphics.fillRect(45, game.GameStateController.x() - 48, 150, 5);
                    graphics.setColor(7877410);
                    graphics.fillRect(46, game.GameStateController.x() - 47, 148, 3);
                    graphics.setColor(16707204);
                    graphics.fillRect(46, game.GameStateController.x() - 47, n, 3);
                    graphics.setColor(0xFFFFFF);
                    graphics.drawString(((GameStateController)g).s, game.GameStateController.w() >> 1, game.GameStateController.x() - 70, 17);
                }
                return;
            }
            case 23: {
                Graphics graphics = g;
                g = this;
                graphics.setColor(0);
                graphics.fillRect(0, 0, game.GameStateController.w(), game.GameStateController.x());
                graphics.setColor(0xFFFFFF);
                graphics.drawString(((GameStateController)g).s, game.GameStateController.w() >> 1, game.GameStateController.x() >> 1, 17);
                return;
            }
            case 10: {
                return;
            }
            case 8: 
            case 11: 
            case 13: 
            case 20: {
                if (this.m == null) break;
                this.m.b((Graphics)g);
            }
        }
    }

    private void a(String text) {
        v = text;
        if (this.g == 0) {
            return;
        }
        try {
            if (this.t != null) {
                if (this.o()) {
                    this.t.start();
                    return;
                }
            } else {
                game.GameStateController.getInstance().getClass();
                InputStream inputStream = ResourceStream.a("/data/sound/" + text + ".mid");
                this.t = Manager.createPlayer(inputStream, "audio/midi");
                this.t.realize();
                this.u = (VolumeControl)this.t.getControl("VolumeControl");
                this.u.setLevel(this.g * 30);
                this.t.prefetch();
                this.t.setLoopCount(-1);
                this.t.start();
                inputStream.close();
            }
            return;
        }
        catch (MediaException mediaException) {
            h.a(mediaException, "startMusic");
            return;
        }
        catch (Exception exception) {
            h.a(exception, "/data/sound" + text + ".mid");
            return;
        }
    }

    private void n() {
        if (this.t == null) {
            return;
        }
        this.t.deallocate();
        this.t.close();
        this.t = null;
    }

    private boolean o() {
        try {
            this.t.prefetch();
            if (this.t.getState() == 300) {
                return true;
            }
        }
        catch (Exception exception) {}
        return false;
    }

    private void p() {
        if (this.u != null) {
            this.u.setLevel(this.g * 30);
        }
    }

    public final void h() {
        this.g = (byte)(this.g + 1);
        if (this.g > 3) {
            this.g = (byte)3;
        }
        if (this.g > 0) {
            this.p();
            this.a(v);
        }
    }

    public final void i() {
        this.g = (byte)(this.g - 1);
        if (this.g < 0) {
            this.g = 0;
        }
        if (this.g == 0) {
            this.n();
            return;
        }
        this.p();
    }
}

