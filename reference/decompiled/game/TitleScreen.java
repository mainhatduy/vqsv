/*
 * Decompiled with CFR 0.152.
 */
package game;

import game.OverworldScreen;
import game.Player;
import game.ScriptEngine;
import game.GameStateController;
import game.WorldManager;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

/**
 * TitleScreen - Start menu, save/load slot management, options, and new game initialization.
 */
public final class TitleScreen
extends BaseScreen {
    private static TitleScreen a;
    private static int[] b;
    private static int c;
    private byte d = 0;
    private Image e;
    private Image f;
    private byte g = (byte)10;
    private int[][] h = new int[this.g][5];
    private int[] i = new int[]{28, 3, 21, 22, 50, 5, 17, 17};
    private byte j = (byte)30;
    private byte k = (byte)30;
    private boolean l = false;
    private int m = 0;
    private int[] n = new int[this.g];
    private int[] o = new int[]{3958719, 3958719, 3958719, 7248110, 7248110, 9943031};

    public static TitleScreen getInstance() {
        if (a == null) {
            a = new TitleScreen();
        }
        return a;
    }

    public final boolean d() {
        this.S = game.ScriptEngine.getInstance();
        this.R = UIManager.getInstance();
        this.S.a(this);
        c = 0;
        b = W ? new int[]{504, 503, 505, 506, 507, 508} : new int[]{503, 505, 506, 507, 508};
        if (this.e == null) {
            this.e = ae.loadImage("/data/img/", "img_833");
        }
        if (this.f == null) {
            this.f = ae.loadImage("/data/tex/", "menu");
        }
        this.c();
        this.a((byte)0);
        return true;
    }

    private void c() {
        for (int i = 0; i < this.g; ++i) {
            this.h[i][0] = -EngineUtils.a(this.j);
            this.h[i][1] = game.TitleScreen.x() + EngineUtils.a(this.k);
            this.h[i][2] = EngineUtils.randomInt(2);
            this.h[i][3] = EngineUtils.randomRange(1, 5);
            this.h[i][4] = EngineUtils.randomRange(3, 5);
        }
    }

    private void e() {
        game.WorldManager.D = false;
        game.WorldManager.G = 0;
        game.OverworldScreen.f = true;
        game.WorldManager.x = false;
        game.WorldManager.getInstance().f = 0;
        game.WorldManager.getInstance().g = 0;
        if (game.WorldManager.getInstance().M != null) {
            game.WorldManager.getInstance().M.e();
        }
        if (game.WorldManager.getInstance().c != null) {
            game.WorldManager.getInstance().c.p();
        }
        if (this.S != null) {
            this.S.b();
        }
        game.Player.getInstance().y = false;
        W = false;
        game.GameStateController.getInstance().a((byte)9);
    }

    public final void b() {
        if (!this.Y) {
            return;
        }
        this.S();
        switch (this.P) {
            case 0: {
                TitleScreen f2 = this;
                if (f2.k(16400)) {
                    if (--c < 0) {
                        c = b.length - 1;
                    }
                } else if (f2.k(32832)) {
                    if (++c > b.length - 1) {
                        c = 0;
                    }
                } else if (f2.k(196640)) {
                    if (W) {
                        switch (c) {
                            case 0: {
                                TitleScreen f3 = f2;
                                if (game.WorldManager.getInstance().M != null) {
                                    game.WorldManager.getInstance().M.e();
                                }
                                if (game.WorldManager.getInstance().c != null) {
                                    game.WorldManager.getInstance().c.p();
                                }
                                if (f3.S != null) {
                                    f3.S.b();
                                }
                                game.Player.getInstance().y = false;
                                game.GameStateController.getInstance().a((byte)9);
                                game.GameStateController.getInstance().a((byte)9);
                                break;
                            }
                            case 1: {
                                f2.a((byte)5);
                                break;
                            }
                            case 2: {
                                f2.a((byte)1);
                                break;
                            }
                            case 3: {
                                f2.a((byte)2);
                                break;
                            }
                            case 4: {
                                f2.a((byte)3);
                                break;
                            }
                            case 5: {
                                f2.a((byte)4);
                            }
                        }
                    } else {
                        switch (c) {
                            case 0: {
                                f2.e();
                                break;
                            }
                            case 1: {
                                f2.a((byte)1);
                                break;
                            }
                            case 2: {
                                f2.a((byte)2);
                                break;
                            }
                            case 3: {
                                f2.a((byte)3);
                                break;
                            }
                            case 4: {
                                f2.a((byte)4);
                            }
                        }
                    }
                }
                f2.S.f();
                f2 = this;
                if (!f2.l) break;
                ++f2.m;
                if (f2.m < 100) break;
                for (int i = 0; i < f2.n.length; ++i) {
                    f2.n[i] = 0;
                }
                f2.m = 0;
                f2.c();
                f2.l = false;
                break;
            }
            case 1: {
                this.S.t();
                break;
            }
            case 2: {
                this.S.p();
                break;
            }
            case 3: {
                this.S.r();
                break;
            }
            case 4: {
                if (this.k(131072 /* MASK_SOFT_LEFT */)) {
                    game.GameStateController.getInstance().a((byte)1);
                    break;
                }
                if (!this.k(262144 /* MASK_SOFT_RIGHT */)) break;
                this.a((byte)0);
                break;
            }
            case 5: {
                if (this.k(131104)) {
                    game.WorldManager.getInstance();
                    game.WorldManager.o();
                    this.e();
                    break;
                }
                if (!this.k(262144 /* MASK_SOFT_RIGHT */)) break;
                this.a((byte)0);
                this.R.a("/data/ui/msgtip.ui");
            }
        }
        this.R.c();
    }

    public final void b(Graphics g) {
        switch (this.P) {
            case 0: {
                int n2;
                if (this.f != null) {
                    g.drawImage(this.f, 0, 0, 20);
                }
                int n3 = game.TitleScreen.x() - 20;
                int n4 = (game.TitleScreen.w() - game.TitleScreen.D().stringWidth(game.TitleScreen.f(b[c]))) / 2;
                String string = game.TitleScreen.f(b[c]);
                Graphics graphics2 = g;
                TitleScreen f2 = this;
                graphics2.setColor(f2.o[f2.d]);
                graphics2.drawString(string, n4, n3 - 1, 36);
                graphics2.drawString(string, n4, n3 + 1, 36);
                graphics2.drawString(string, n4 - 1, n3, 36);
                graphics2.drawString(string, n4 + 1, n3, 36);
                graphics2.setColor(0xFFFFFF);
                graphics2.drawString(string, n4, n3, 36);
                f2.d = (byte)(f2.d + 1);
                if (f2.d >= 6) {
                    f2.d = 0;
                }
                graphics2 = g;
                f2 = this;
                if (f2.l) break;
                for (n2 = 0; n2 < f2.g; ++n2) {
                    graphics2.drawRegion(f2.e, f2.i[f2.h[n2][2] << 2], f2.i[(f2.h[n2][2] << 2) + 1], f2.i[(f2.h[n2][2] << 2) + 2], f2.i[(f2.h[n2][2] << 2) + 3], 0, f2.h[n2][0], f2.h[n2][1], 20);
                    int[] intArray = f2.h[n2];
                    intArray[0] = intArray[0] + f2.h[n2][3];
                    int[] intArray2 = f2.h[n2];
                    intArray2[1] = intArray2[1] - f2.h[n2][4];
                    if (f2.h[n2][0] <= game.TitleScreen.w() && f2.h[n2][1] >= 0) continue;
                    int n5 = n2;
                    f2.n[n5] = f2.n[n5] + 1;
                }
                for (n2 = 0; n2 < f2.n.length && f2.n[n2] > 0; ++n2) {
                }
                if (n2 < f2.n.length) break;
                f2.l = true;
                break;
            }
            case 1: 
            case 2: 
            case 3: {
                Graphics graphics3 = g;
                for (int i = 0; i < game.TitleScreen.x() / 20; ++i) {
                    if (i % 2 == 0) {
                        graphics3.setColor(10440998);
                    } else {
                        graphics3.setColor(12082732);
                    }
                    graphics3.fillRect(0, i * 20, game.TitleScreen.w(), 20);
                }
                break;
            }
            case 4: {
                g.setColor(0);
                g.fillRect(0, 0, game.TitleScreen.w(), game.TitleScreen.x());
                g.setColor(0xFFFFFF);
                g.drawString("Bạn có muốn thoát không?", game.TitleScreen.y(), game.TitleScreen.z() - 10, 17);
                g.drawString("", 2, game.TitleScreen.x(), 36);
                g.drawString("Không", game.TitleScreen.w() - 2, game.TitleScreen.x(), 40);
            }
        }
        this.R.a(g);
    }

    public final void f() {
        this.e = null;
        this.f = null;
        this.R.b();
    }

    public final void a(byte val) {
        this.P = val;
        switch (val) {
            case 2: {
                this.S.o();
                return;
            }
            case 3: {
                this.S.q();
                return;
            }
            case 1: {
                this.S.s();
                return;
            }
            case 0: {
                return;
            }
            case 5: {
                this.S.H();
                this.S.a("Có chắc chắn xóa dữ liệu cũ để chơi mới không?");
            }
        }
    }

    static {
        b = null;
    }
}

