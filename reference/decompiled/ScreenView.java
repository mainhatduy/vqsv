/*
 * Decompiled with CFR 0.152.
 */
import game.BattleScreen;
import game.ScriptEngine;
import game.WorldManager;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

/**
 * ScreenView - Screen overlay presenter, battle animations, camera shake, and dialog UI.
 */
public final class ScreenView {
    private static ScreenView e;
    public int a = -1;
    private int f = -1;
    private int g = -1;
    private int h = -1;
    public boolean b;
    public boolean c;
    private int i;
    private int j;
    private int k = 0;
    private int l = 20;
    private int m = 5;
    private int n = -2013265920;
    private int[] o = null;
    private int[] p = null;
    private int[] q = null;
    private int r = 16;
    private int s = 4;
    private int t = 0;
    private int[][] u;
    private int[] v;
    private int w = 20;
    private int x;
    private static UIManager y;
    private static DebugLogger z;
    private static short[][] A;
    private short[][] B = new short[][]{{-20, 20, 20, -20, -15, 15, -15, 15, -5, 5, -5, 5}, {-5, 5, 5, -5}, {-5, 10, -5}};
    private int C = 0;
    private int D = 0;
    private int E = 0;
    private int F = 0;
    private int G = 0;
    private int H = 0;
    private int I;
    private int J;
    private int K;
    private int L = 0;
    private int M = 0;
    private Image N = null;
    private PixelBuffer O = null;
    private int P;
    private int Q;
    private int R = 0;
    private int S = 0;
    private int T = 0;
    private int U = 0;
    private int[] V = new int[]{0xFFFFFF, 9115396};
    private static int W;
    private static int[] X;
    private SkillEffect Y;
    private int Z = 0;
    private int aa = 0;
    public byte d = (byte)-1;
    private int ab;
    private int ac;
    private int ad;
    private int ae;
    private int af;
    private int ag;
    private static final int[][] ah;
    private byte ai = (byte)30;
    private Image[] aj;
    private int[][] ak;
    private int al = 0;
    private int am = 0;
    private byte an = 0;

    public static ScreenView getInstance() {
        if (e == null) {
            e = new ScreenView();
        }
        y = UIManager.getInstance();
        z = game.ScriptEngine.getInstance();
        return e;
    }

    /*
     * Unable to fully structure code
     */
    public final void a(Graphics var1_1) {
        if (this.a == -1 && this.g == -1 && this.h == -1) {
            return;
        }
        switch (this.h) {
            case 18: {
                ScreenView.y.a(var1_1);
                if (this.i < (null).length) break;
                this.i = 0;
                this.h = -1;
                this.b = true;
                ScreenView.y.a("/data/ui/menu1.ui");
                return;
            }
        }
        switch (this.a) {
            case 0: 
            case 1: 
            case 2: {
                var3_2 = var1_1;
                var2_8 = this;
                var4_13 = var2_8.n & 0xFFFFFF;
                var6_21 = false;
                var5_24 = 0;
                if (var2_8.a == 0) {
                    var5_24 = var2_8.n;
                } else if (var2_8.a == 1) {
                    if (255 - var2_8.k * var2_8.m < 0) {
                        --var2_8.k;
                        var2_8.a = -1;
                        var6_21 = true;
                    }
                    var5_24 = 255 - var2_8.k * var2_8.m << 24;
                    var5_24 = var4_13 | var5_24;
                } else if (var2_8.a == 2) {
                    if (var2_8.k * var2_8.m > 255) {
                        --var2_8.k;
                        var6_21 = true;
                    }
                    var5_24 = var2_8.k * var2_8.m << 24;
                    var5_24 = var4_13 | var5_24;
                }
                var7_31 = var2_8.w;
                var8_33 = BaseScreen.getScreenHeight() / var7_31 + 1;
                var4_13 = BaseScreen.getScreenWidth() * var8_33;
                if (var2_8.o == null || var2_8.o.length != var4_13) {
                    var2_8.o = new int[var4_13];
                }
                if (var2_8.o[0] != var5_24) {
                    for (var9_35 = 0; var9_35 < var2_8.o.length; ++var9_35) {
                        var2_8.o[var9_35] = var5_24;
                    }
                }
                for (var9_35 = 0; var9_35 < var7_31; ++var9_35) {
                    var3_2.drawRGB(var2_8.o, 0, BaseScreen.getScreenWidth(), 0, var9_35 * var8_33, BaseScreen.getScreenWidth(), var8_33, true);
                }
                if (var6_21) {
                    var2_8.d();
                    v0 = true;
                } else {
                    ++var2_8.k;
                    v0 = false;
                }
                this.b = v0;
                break;
            }
            case 3: {
                var3_3 = var1_1;
                var2_9 = this;
                var4_14 = BaseScreen.getScreenWidth();
                var5_25 = BaseScreen.getScreenHeight();
                var6_22 = var2_9.n & 0xFFFFFF;
                var7_32 = var2_9.l;
                var8_34 = 255 / var7_32 / 2;
                var9_36 = var5_25 / 2;
                var10_37 = (var4_14 /= 2) * 200 / 120;
                var11_38 = var10_37 * var10_37;
                var12_39 = false;
                if (var2_9.q == null) {
                    var2_9.q = new int[var10_37];
                }
                var13_40 = var10_37 + var7_32 - (var2_9.k << 1);
                if (var2_9.k <= 0) {
                    var12_39 = true;
                    var13_40 = var10_37 + var7_32 - (--var2_9.k << 1);
                }
                if (var2_9.o == null || var2_9.o.length != var4_14 * var9_36) {
                    var2_9.o = new int[var4_14 * var9_36];
                }
                if (var2_9.p == null || var2_9.p.length != var4_14 * var9_36) {
                    var2_9.p = new int[var4_14 * var9_36];
                }
                for (var14_41 = 0; var14_41 < var2_9.q.length; ++var14_41) {
                    var5_25 = var14_41 - var13_40;
                    var5_25 = var5_25 > var7_32 ? 255 : (var5_25 < -var7_32 ? 0 : 127 + var5_25 * var8_34);
                    var2_9.q[var14_41] = var6_22 | var5_25 << 24;
                }
                var14_41 = var10_37 - 1;
                var5_25 = var13_40 + var7_32;
                var6_22 = var13_40 - var7_32;
                if (var6_22 < 0) {
                    var6_22 = 0;
                }
                var5_25 = var5_25 * var11_38 / var14_41;
                var6_22 = var6_22 * var11_38 / var14_41;
                for (var7_32 = 0; var7_32 < var9_36; ++var7_32) {
                    var10_37 = var7_32 * var7_32;
                    var13_40 = var7_32 * var4_14;
                    for (var15_42 = 0; var15_42 < var4_14; ++var15_42) {
                        var8_34 = var10_37 + var15_42 * var15_42;
                        var2_9.o[var13_40 + var15_42] = var8_34 > var5_25 ? -16777216 : (var8_34 < var6_22 ? 0 : var2_9.q[var14_41 * var8_34 / var11_38]);
                    }
                }
                if (var2_9.o != null) {
                    var3_3.drawRGB(var2_9.o, 0, var4_14, var4_14, var9_36, var4_14, var9_36, true);
                    var3_3.drawRGB(ScreenView.a(var2_9.o, var2_9.p, var4_14, var9_36, (byte)2), 0, var4_14, 0, var9_36, var4_14, var9_36, true);
                    var3_3.drawRGB(ScreenView.a(var2_9.o, var2_9.p, var4_14, var9_36, (byte)3), 0, var4_14, 0, 0, var4_14, var9_36, true);
                    var3_3.drawRGB(ScreenView.a(var2_9.o, var2_9.p, var4_14, var9_36, (byte)1), 0, var4_14, var4_14, 0, var4_14, var9_36, true);
                }
                if (var12_39) {
                    var2_9.d();
                    v1 = true;
                } else {
                    var2_9.k -= 10;
                    v1 = false;
                }
                this.b = v1;
                break;
            }
            case 4: {
                if (this.b) {
                    this.a = -1;
                    var1_1.fillRect(0, 0, BaseScreen.getScreenWidth(), BaseScreen.getScreenHeight());
                    break;
                }
                v2 = this;
                v3 = this.d(var1_1);
                ** GOTO lbl302
            }
            case 5: {
                if (this.b) break;
                v2 = this;
                v3 = this.d(var1_1);
                ** GOTO lbl302
            }
            case 6: {
                if (this.b) {
                    this.a = -1;
                    var1_1.fillRect(0, 0, BaseScreen.getScreenWidth(), BaseScreen.getScreenHeight());
                    break;
                }
                var3_4 = var1_1;
                var2_10 = this;
                if (var2_10.i >= 10) ** GOTO lbl142
                if (var2_10.i % 3 == 1) {
                    var3_4.setColor(0xFFFFFF);
                    var3_4.fillRect(0, 0, BaseScreen.getScreenWidth(), BaseScreen.getScreenHeight());
                } else {
                    game.WorldManager.getInstance();
                    game.WorldManager.a(var3_4, 0, 0, BaseScreen.getScreenWidth(), BaseScreen.getScreenHeight());
                    game.WorldManager.getInstance().b.a(var3_4);
                }
                ++var2_10.i;
                ** GOTO lbl185
lbl142:
                // 1 sources

                if (var2_10.i >= BaseScreen.getScreenWidth()) {
                    var3_4.setColor(0);
                    var3_4.fillRect(0, 0, BaseScreen.getScreenWidth(), BaseScreen.getScreenHeight());
                    v4 = true;
                } else {
                    switch (var2_10.x) {
                        case 0: {
                            var3_4.setColor(0);
                            var3_4.fillRect(0, 0, var2_10.i, BaseScreen.getHalfHeight());
                            for (var4_15 = 1; var4_15 < 6; ++var4_15) {
                                var3_4.fillRect(var2_10.i + var4_15 * 15, 0, 15 - var4_15 * 3, BaseScreen.getHalfHeight());
                            }
                            var3_4.fillRect(BaseScreen.getScreenWidth() - var2_10.i, BaseScreen.getHalfHeight(), var2_10.i, BaseScreen.getHalfHeight());
                            for (var4_15 = 1; var4_15 < 6; ++var4_15) {
                                var3_4.fillRect(BaseScreen.getScreenWidth() - var2_10.i - var4_15 * 15, BaseScreen.getHalfHeight(), 15 - var4_15 * 3, BaseScreen.getHalfHeight());
                            }
                            var2_10.i += 15;
                            break;
                        }
                        case 1: {
                            var3_4.setColor(0);
                            var4_16 = false;
                            for (var5_26 = 0; var5_26 < BaseScreen.getScreenHeight(); var5_26 += 10) {
                                if (var4_16) {
                                    var3_4.fillRect(0, var5_26, var2_10.i, 10);
                                    var4_16 = false;
                                    continue;
                                }
                                var3_4.fillRect(BaseScreen.getScreenWidth() - var2_10.i, var5_26, var2_10.i, 10);
                                var4_16 = true;
                            }
                            var2_10.i += 15;
                            break;
                        }
                        case 2: {
                            var3_4.setColor(0);
                            var4_17 = false;
                            for (var5_27 = 0; var5_27 < BaseScreen.getScreenWidth(); var5_27 += 10) {
                                if (var4_17) {
                                    var3_4.fillRect(var5_27, 0, 10, var2_10.i);
                                    var4_17 = false;
                                    continue;
                                }
                                var3_4.fillRect(var5_27, BaseScreen.getScreenHeight() - var2_10.i, 10, var2_10.i);
                                var4_17 = true;
                            }
                            var2_10.i += 15;
                        }
                    }
lbl185:
                    // 5 sources

                    v4 = false;
                }
                this.b = v4;
                break;
            }
            case 7: {
                if (this.Y == null) break;
                this.Y.a(var1_1);
                break;
            }
            case 8: {
                if (this.i >= 5) {
                    var1_1.drawImage(game.BattleScreen.getInstance().c, 0, 0, 20);
                }
                ScreenView.y.a(var1_1);
                if (this.i < ScreenView.A.length) break;
                this.i = 0;
                this.a = -1;
                this.b = true;
                ScreenView.y.a("/data/ui/npcEnemy.ui");
                return;
            }
            case 9: {
                var1_1.setColor(this.n);
                var1_1.fillRect(0, 0, BaseScreen.getScreenWidth(), BaseScreen.getScreenHeight());
                break;
            }
            case 10: {
                if (this.i > this.L) break;
                if (this.i % 3 / (this.M + 1) == 0) {
                    var1_1.setColor(0xFFFFFF);
                    var1_1.fillRect(0, 0, BaseScreen.getScreenWidth(), BaseScreen.getScreenHeight());
                } else if (this.i % 3 / (this.M + 1) == 1) {
                    var1_1.setColor(0);
                    var1_1.fillRect(0, 0, BaseScreen.getScreenWidth(), BaseScreen.getScreenHeight());
                }
                ++this.i;
                break;
            }
            case 14: 
            case 15: {
                var3_5 = var1_1;
                var2_11 = this;
                var4_18 = false;
                var5_28 = 0;
                if (var2_11.a == 15) {
                    var5_28 = var2_11.k;
                    if (var5_28 >= 255) {
                        var5_28 = 255;
                        var2_11.k = 255;
                        var4_18 = true;
                    }
                } else if (var2_11.a == 14 && (var5_28 = 255 - var2_11.k) <= 0) {
                    var5_28 = 0;
                    var2_11.a = -1;
                    var4_18 = true;
                }
                if (var2_11.O != null) {
                    var2_11.O = ImageTransformer.b(var2_11.O, var5_28);
                    var3_5.drawRGB(var2_11.O.a, 0, var2_11.O.b, var2_11.P - var2_11.O.b / 2, var2_11.Q - var2_11.O.c / 2, var2_11.O.b, var2_11.O.c, true);
                }
                if (var4_18) {
                    var2_11.a = -1;
                    var2_11.d();
                    v5 = true;
                } else {
                    var2_11.k += var2_11.m;
                    v5 = false;
                }
                this.b = v5;
                break;
            }
            case 17: {
                var1_1.setColor(this.V[this.n]);
                var5_29 = this.R;
                var4_19 = this.U;
                var3_6 = this.T;
                var1_1.fillArc(var3_6 - var5_29, var4_19 - var5_29, var5_29 << 1, var5_29 << 1, 0, 360);
                break;
            }
            case 19: 
            case 20: {
                v2 = this;
                var3_7 = var1_1;
                var2_12 = this;
                var3_7.setColor(var2_12.n);
                var4_20 = 0;
                for (var5_30 = 0; var5_30 < ScreenView.W; ++var5_30) {
                    for (var6_23 = 0; var6_23 < ScreenView.X[1]; ++var6_23) {
                        switch (ScreenView.X[2]) {
                            case 1: {
                                var4_20 = ScreenView.X[0];
                                break;
                            }
                            case -1: /* KEY_UP */ {
                                var4_20 = 0;
                            }
                        }
                        switch (ScreenView.X[3]) {
                            case 0: {
                                var4_20 = (var4_20 += (var6_23 - ScreenView.X[4]) * ScreenView.X[2]) < 0 ? 0 : (var4_20 > ScreenView.X[0] ? ScreenView.X[0] : var4_20);
                                break;
                            }
                            case 1: {
                                var4_20 = (var4_20 += (ScreenView.X[1] - var6_23 - ScreenView.X[4]) * ScreenView.X[2]) < 0 ? 0 : (var4_20 > ScreenView.X[0] ? ScreenView.X[0] : var4_20);
                                break;
                            }
                            case 2: {
                                var4_20 = (var4_20 += (var5_30 - ScreenView.X[4]) * ScreenView.X[2]) < 0 ? 0 : (var4_20 > ScreenView.X[0] ? ScreenView.X[0] : var4_20);
                                break;
                            }
                            case 3: {
                                var4_20 = (var4_20 += (8 - var5_30 - ScreenView.X[4]) * ScreenView.X[2]) < 0 ? 0 : (var4_20 > ScreenView.X[0] ? ScreenView.X[0] : var4_20);
                                break;
                            }
                            case 4: {
                                var4_20 = (var4_20 += ((var5_30 + var6_23 >> 1) - ScreenView.X[4]) * ScreenView.X[2]) < 0 ? 0 : (var4_20 > ScreenView.X[0] ? ScreenView.X[0] : var4_20);
                                break;
                            }
                            case 5: {
                                var4_20 = (var4_20 += ((8 - var5_30 + var6_23 >> 1) - ScreenView.X[4]) * ScreenView.X[2]) < 0 ? 0 : (var4_20 > ScreenView.X[0] ? ScreenView.X[0] : var4_20);
                                break;
                            }
                            case 6: {
                                var4_20 = (var4_20 += ((var5_30 + ScreenView.X[1] - var6_23 >> 1) - ScreenView.X[4]) * ScreenView.X[2]) < 0 ? 0 : (var4_20 > ScreenView.X[0] ? ScreenView.X[0] : var4_20);
                                break;
                            }
                            case 7: {
                                var4_20 = (var4_20 += ((8 - var5_30 + ScreenView.X[1] - var6_23 >> 1) - ScreenView.X[4]) * ScreenView.X[2]) < 0 ? 0 : (var4_20 > ScreenView.X[0] ? ScreenView.X[0] : var4_20);
                            }
                        }
                        var3_7.fillRect(ScreenView.X[0] * var5_30 + (ScreenView.X[0] - var4_20 >> 1), ScreenView.X[0] * var6_23 + (ScreenView.X[0] - var4_20 >> 1), var4_20, var4_20);
                    }
                }
                ScreenView.X[4] = ScreenView.X[4] + 2;
                if (ScreenView.X[4] > 40) {
                    if (var2_12.a == 20) {
                        var2_12.a = -1;
                    }
                    if (var2_12.a == 19) {
                        var3_7.fillRect(0, 0, BaseScreen.getScreenWidth(), BaseScreen.getScreenHeight());
                    }
                    v3 = true;
                } else {
                    v3 = false;
                }
lbl302:
                // 4 sources

                v2.b = v3;
            }
        }
        switch (this.g) {
            case 12: {
                var1_1.setColor(0);
                var1_1.fillRect(0, 0, this.I, this.J - this.j * this.J / this.G);
                var1_1.fillRect(0, BaseScreen.getScreenHeight() - this.K + this.j * this.K / this.G, this.I, this.K - this.j * this.K / this.G);
                return;
            }
            case 13: {
                var1_1.setColor(0);
                var1_1.fillRect(0, 0, this.I, this.j * this.J / this.G);
                var1_1.fillRect(0, BaseScreen.getScreenHeight() - this.j * this.K / this.G, this.I, this.j * this.K / this.G);
            }
        }
    }

    private void a(int n2) {
        this.aa = n2;
        switch (this.a) {
            case 7: {
                n2 = GameDatabase.gameDatabase[0][game.BattleScreen.getInstance().k()][17];
                switch (this.aa) {
                    case 0: {
                        short[] shortArray = new short[]{8, 118, 160, n2, 0, 1, 0, 4, 0, 2, 1, 8, 0, -16, 10, 0, 0};
                        this.Y = new SkillEffect();
                        this.Y.a(shortArray);
                        this.Y.c(true);
                        this.Y.a();
                        return;
                    }
                    case 1: {
                        short[] shortArray = new short[]{17, 118, 160, n2, 0, 1, 100, 255, 255, 255, 12, 0, 1, 1, 9};
                        this.Y.a(shortArray);
                        this.Y.a();
                        return;
                    }
                    case 2: {
                        short[] shortArray = new short[]{17, 118, 160, n2, 0, 1, 255, 255, 255, 255, 15, 0, 1, 1, 13};
                        this.Y.a(shortArray);
                        this.Y.a();
                        return;
                    }
                    case 3: {
                        short[] shortArray = new short[]{9, 118, 160, n2, 0, 1, 160, 255, 255, 255, 0, 4, 1};
                        this.Y.a(shortArray);
                        this.Y.a();
                    }
                }
                return;
            }
            case 8: {
                this.Z = A[this.i].length;
            }
        }
    }

    public final void b() {
        if (this.a == -1 && this.g == -1 && this.h == -1) {
            return;
        }
        switch (this.a) {
            case 8: {
                if (this.aa < this.Z) {
                    z.b(this.i, A[this.i][this.aa]);
                    ++this.aa;
                } else {
                    ++this.i;
                    if (this.i < A.length) {
                        this.a(0);
                    }
                }
                y.c();
                break;
            }
            case 7: {
                if (this.Y == null || this.Y.e()) break;
                ++this.aa;
                if (this.aa >= 4) {
                    this.b = true;
                    this.a = -1;
                    this.i = 0;
                    this.Y = null;
                    return;
                }
                this.a(this.aa);
                break;
            }
            case 11: {
                if (this.C == 0) {
                    if (this.F >= this.B[this.D].length * this.E) {
                        this.a = -1;
                        this.b = true;
                        this.F = 0;
                        ParticleEffect.getInstance().a = ParticleEffect.getInstance().b;
                        return;
                    }
                    ParticleEffect.getInstance().d(this.B[this.D][this.F % this.B[this.D].length]);
                    ++this.F;
                    break;
                }
                if (this.F >= this.B[this.D].length * this.E) {
                    this.a = -1;
                    this.b = true;
                    this.F = 0;
                    ParticleEffect.getInstance().a = ParticleEffect.getInstance().b;
                    return;
                }
                ParticleEffect.getInstance().e(this.B[this.D][this.F % this.B[this.D].length]);
                ++this.F;
                break;
            }
            case 10: {
                if (this.i <= this.L) break;
                this.b = true;
                this.a = -1;
                this.i = 0;
                break;
            }
            case 17: {
                ++this.i;
                if (this.S == 0) {
                    if ((BaseScreen.getScreenWidth() - this.T) * (BaseScreen.getScreenWidth() - this.T) + (BaseScreen.getScreenHeight() - this.U) * (BaseScreen.getScreenHeight() - this.U) < this.R * this.R) {
                        this.i = 0;
                        this.b = true;
                    }
                    this.R += 10;
                    break;
                }
                if (this.S == 1) {
                    this.R -= 10;
                    if (this.R > 0) break;
                    this.i = 0;
                    this.a = -1;
                    this.b = true;
                    break;
                }
                if (this.i <= 10) {
                    this.R += 10;
                    break;
                }
                if (this.i > 10 && this.i <= 20) {
                    this.R -= 10;
                    break;
                }
                this.i = 0;
                this.b = true;
                this.a = -1;
            }
        }
        switch (this.g) {
            case 13: {
                this.j += this.H;
                if (this.j <= this.G) break;
                this.j = this.G;
                this.c = true;
                return;
            }
            case 12: {
                this.j += this.H;
                if (this.j <= this.G) break;
                this.j = 0;
                this.c = true;
                this.g = -1;
            }
        }
    }

    private boolean d(Graphics g) {
        int n2;
        int n3;
        int n4;
        int n5;
        int n6;
        int n7 = this.n & 0xFFFFFF;
        int n8 = 0;
        int n9 = this.s;
        int n10 = 255 / ((n9 << 1) + 1);
        int n11 = this.r / 2;
        int n12 = n11 * 200 / 120;
        int n13 = n12 * n12;
        int n14 = this.t / this.r + 1;
        int n15 = BaseScreen.getScreenWidth() / this.r;
        int n16 = BaseScreen.getScreenHeight() / this.r;
        int n17 = n15 / 2;
        int n18 = n16 / 2;
        int[] intArray = new int[n12];
        if (this.u == null) {
            this.u = new int[EngineUtils.a(n17 * n17 + n18 * n18, 0)][];
        }
        if (this.v == null) {
            this.v = new int[this.u.length];
        }
        if (n14 > this.u.length) {
            n14 = this.u.length;
        }
        for (n6 = 0; n6 < n14; ++n6) {
            if (this.u[n6] == null) {
                this.u[n6] = new int[this.r * this.r];
            }
            if (this.v[n6] >= n12 + n9) continue;
            n5 = -n9 + this.v[n6];
            for (n4 = 0; n4 < intArray.length; ++n4) {
                n3 = n4 - n5;
                if (n3 > n9) {
                    n8 = this.a == 4 ? 0 : 255;
                } else if (n3 < -n9) {
                    n8 = this.a == 4 ? 255 : 0;
                } else if (this.a == 4) {
                    n8 = 127 - n3 * n10;
                } else if (this.a == 5) {
                    n8 = 127 + n3 * n10;
                }
                intArray[n4] = n7 | n8 << 24;
            }
            n4 = n12 - 1;
            n3 = n5 + n9;
            if ((n5 -= n9) < 0) {
                n5 = 0;
            }
            int n19 = n3 * n13 / n4;
            n2 = n5 * n13 / n4;
            for (n5 = 0; n5 < this.r; ++n5) {
                n3 = (n5 - n11) * (n5 - n11);
                int n20 = n5 * this.r;
                for (int i = 0; i < this.r; ++i) {
                    n4 = n3 + (i - n11) * (i - n11);
                    int n21 = EngineUtils.a(n4, 1);
                    this.u[n6][n20 + i] = n4 > n19 ? (this.a == 4 ? 0 : -16777216) : (n4 < n2 ? (this.a == 4 ? -16777216 : 0) : intArray[n21]);
                }
            }
            int n22 = n6;
            this.v[n22] = this.v[n22] + 1;
        }
        n6 = 1;
        g.setColor(n7);
        for (n4 = 0; n4 < n16; ++n4) {
            n3 = (n4 - n18) * (n4 - n18);
            for (n2 = 0; n2 < n15; ++n2) {
                n5 = EngineUtils.a(n3 + (n2 - n17) * (n2 - n17), 1);
                if (this.u[n5] == null) {
                    if (this.a != 5) continue;
                    g.fillRect(n2 * this.r, n4 * this.r, this.r, this.r);
                    continue;
                }
                if (this.v[n5] >= n12 + n9) {
                    if (this.a != 4) continue;
                    g.fillRect(n2 * this.r, n4 * this.r, this.r, this.r);
                    continue;
                }
                n6 = 0;
                g.drawRGB(this.u[n5], 0, this.r, n2 * this.r, n4 * this.r, this.r, this.r, true);
            }
        }
        if (n6 != 0) {
            this.d();
            return true;
        }
        this.t += 15;
        return false;
    }

    private static int[] a(int[] intArray, int[] nArray2, int n2, int n3, byte val5) {
        block11: {
            block14: {
                block13: {
                    block12: {
                        block10: {
                            if (val5 != 5) break block10;
                            for (val5 = 0; val5 < n3; val5 = (byte)(val5 + 1)) {
                                int n4 = val5 * n2;
                                int n5 = -1 - val5;
                                int n6 = 0;
                                int n7 = 1;
                                while (n6 < n2) {
                                    intArray2[n7 * n3 + n5] = intArray[n4 + n6];
                                    ++n6;
                                    ++n7;
                                }
                            }
                            break block11;
                        }
                        if (val5 != 3) break block12;
                        val5 = (byte)(n2 * n3 - 1);
                        for (int i = 0; i < n3; ++i) {
                            int n8 = i * n2;
                            int n9 = val5 - i * n2;
                            for (int i3 = 0; i3 < n2; ++i3) {
                                intArray2[n9 - i3] = intArray[n8 + i3];
                            }
                        }
                        break block11;
                    }
                    if (val5 != 6) break block13;
                    val5 = (byte)(n2 - 1);
                    for (int i4 = 0; i4 < n3; ++i4) {
                        int n10 = val5 * n3 + i4;
                        int n11 = i4 * n2;
                        for (int i5 = 0; i5 < n2; ++i5) {
                            intArray2[n10 - i5 * n3] = intArray[n11 + i5];
                        }
                    }
                    break block11;
                }
                if (val5 == 0 || val5 == 7) break block11;
                if (val5 != 1) break block14;
                val5 = (byte)(n3 - 1);
                for (int i6 = 0; i6 < n3; ++i6) {
                    int n12 = i6 * n2;
                    int n13 = (val5 - i6) * n2;
                    for (int i7 = 0; i7 < n2; ++i7) {
                        intArray2[n13 + i7] = intArray[n12 + i7];
                    }
                }
                break block11;
            }
            if (val5 == 4 || val5 != 2) break block11;
            val5 = (byte)(n2 - 1);
            for (int i8 = 0; i8 < n3; ++i8) {
                int n14 = i8 * n2;
                int n15 = n14 + val5;
                for (int i9 = 0; i9 < n2; ++i9) {
                    intArray2[n15 - i9] = intArray[n14 + i9];
                }
            }
        }
        return intArray2;
    }

    public final void c() {
        this.d = (byte)-1;
        this.O = null;
    }

    public final void a(int n2, int n3, int n4, int n5, int n6, int n7) {
        this.d = 0;
        this.b(n2, n3);
        this.ad = n4;
        this.ae = n5;
        this.af = n6;
        this.ag = n7;
    }

    public final void a(int n2, int n3) {
        this.af = n2;
        this.ag = n3;
    }

    public final void b(int n2, int n3) {
        this.ab = n2;
        this.ac = n3;
    }

    public final void b(Graphics g) {
        g.setColor(0);
        g.fillRect(0, 0, this.ad, this.ac - this.ag);
        g.fillRect(0, this.ac - this.ag, this.ab - this.af, this.ag << 1);
        g.fillRect(0, this.ac + this.ag, this.ad, this.ae - (this.ac + this.ag));
        g.fillRect(this.ab + this.af, this.ac - this.ag, this.ad - (this.ab + this.af), this.ag << 1);
    }

    public final void c(int n2, int n3) {
        this.k = 0;
        this.n = n2;
        if (n3 == 12 || n3 == 13) {
            this.g = n3;
            this.c = false;
        } else if (n3 == 18) {
            this.h = n3;
        } else {
            this.a = n3;
        }
        this.b = false;
        switch (this.a) {
            case 1: 
            case 2: {
                this.m = 17;
                return;
            }
            case 3: {
                this.k = BaseScreen.getHalfWidth();
                this.l = 20;
                return;
            }
            case 4: 
            case 5: {
                this.t = 0;
                return;
            }
            case 6: {
                this.i = 0;
                this.x = EngineUtils.randomInt(2);
                return;
            }
            case 7: {
                this.a(0);
                return;
            }
            case 8: {
                this.i = 0;
                z.at();
                this.a(0);
                return;
            }
            case 10: 
            case 17: {
                this.i = 0;
                return;
            }
            case 19: {
                this.c(-1);
                return;
            }
            case 20: {
                this.c(1);
            }
        }
    }

    public final void a(int n2, int n3, int n4) {
        ParticleEffect.getInstance().d((byte)3);
        this.C = n2;
        this.D = n3;
        this.E = n4;
    }

    public final void a(int n2, int n3, int n4, int n5, int n6) {
        this.j = 0;
        this.G = n2;
        this.H = n3;
        this.I = n4;
        this.J = n5;
        this.K = n6;
    }

    public final void d(int n2, int n3) {
        this.L = n2;
        this.M = n3;
    }

    public final void a(int n2, int n3, int n4, int n5) {
        this.S = n2;
        this.T = n3;
        this.U = n4;
        this.R = n5;
    }

    public final void a(String text, int n2, int n3, int n4) {
        this.N = ae.loadImage("/data/tex/", text);
        this.O = new PixelBuffer();
        this.O = ImageTransformer.a(this.N, this.O);
        this.P = n2;
        this.Q = n3;
        this.m = n4;
    }

    private void d() {
        this.o = null;
        this.p = null;
        this.q = null;
        this.u = null;
        this.v = null;
        this.N = null;
    }

    public final void a(int n2, byte val2, byte val3, String[] strArray) {
        this.f = n2;
        if (this.a == 17 && this.S == 0) {
            return;
        }
        this.a = -1;
        if (this.f < 16) {
            return;
        }
        this.ai = val2;
        this.al = n2;
        this.am = 0;
        this.an = val3;
        this.aj = null;
        this.aj = new Image[strArray.length];
        for (n2 = 0; n2 < strArray.length; ++n2) {
            this.aj[n2] = ae.loadImage("/data/tex/", strArray[n2]);
        }
        this.ak = new int[val2][5];
        for (n2 = 0; n2 < val2; ++n2) {
            this.b(n2);
        }
    }

    private void b(int n2) {
        int n3 = EngineUtils.randomInt(100);
        this.ak[n2][0] = n3 < 3 ? this.aj.length - 1 : (n3 < 15 ? this.aj.length - 2 : (n3 < 50 ? this.aj.length - 3 : 0));
        this.ak[n2][1] = EngineUtils.a(BaseScreen.getScreenWidth());
        this.ak[n2][2] = EngineUtils.a(BaseScreen.getScreenHeight());
        this.ak[n2][3] = EngineUtils.a(ah[this.ak[n2][0]][1] - ah[this.ak[n2][0]][0]) + ah[this.ak[n2][0]][0];
        this.ak[n2][4] = EngineUtils.a();
    }

    public final void c(Graphics g) {
        if (this.f < 16) {
            return;
        }
        for (int i = 0; i < this.ai; ++i) {
            if (this.ak[i][1] < BaseScreen.getScreenWidth() && this.ak[i][2] < BaseScreen.getScreenHeight()) {
                g.drawImage(this.aj[this.ak[i][0]], this.ak[i][1], this.ak[i][2], 20);
            }
            switch (this.an) {
                case 0: {
                    int[] intArray = this.ak[i];
                    intArray[2] = intArray[2] - this.ak[i][3];
                    break;
                }
                case 1: {
                    int[] intArray = this.ak[i];
                    intArray[1] = intArray[1] + this.ak[i][3];
                    int[] intArray2 = this.ak[i];
                    intArray2[2] = intArray2[2] - this.ak[i][3];
                    break;
                }
                case 2: {
                    int[] intArray = this.ak[i];
                    intArray[1] = intArray[1] + this.ak[i][3];
                    break;
                }
                case 3: {
                    int[] intArray = this.ak[i];
                    intArray[1] = intArray[1] + this.ak[i][3];
                    int[] nArray3 = this.ak[i];
                    nArray3[2] = nArray3[2] + this.ak[i][3];
                    break;
                }
                case 4: {
                    int[] intArray = this.ak[i];
                    intArray[2] = intArray[2] + this.ak[i][3];
                    break;
                }
                case 5: {
                    int[] intArray = this.ak[i];
                    intArray[1] = intArray[1] - this.ak[i][3];
                    int[] nArray4 = this.ak[i];
                    nArray4[2] = nArray4[2] + this.ak[i][3];
                    break;
                }
                case 6: {
                    int[] intArray = this.ak[i];
                    intArray[1] = intArray[1] - this.ak[i][3];
                    break;
                }
                case 7: {
                    int[] intArray = this.ak[i];
                    intArray[1] = intArray[1] - this.ak[i][3];
                    int[] nArray5 = this.ak[i];
                    nArray5[2] = nArray5[2] - this.ak[i][3];
                }
            }
            if (this.ak[i][1] >= this.al - this.aj[this.ak[i][0]].getWidth() && this.ak[i][2] >= 0 - this.aj[this.ak[i][0]].getHeight()) continue;
            this.b(i);
        }
    }

    private void c(int n2) {
        ScreenView.X[0] = 20;
        W = BaseScreen.getScreenWidth() / X[0];
        ScreenView.X[1] = (BaseScreen.getScreenHeight() - 1) / X[0] + 1;
        ScreenView.X[2] = n2;
        ScreenView.X[3] = EngineUtils.randomRange(0, 7);
        ScreenView.X[4] = 0;
        this.b = false;
    }

    static {
        A = new short[][]{{0}, {1}, {2}, {3}, {4, 5, 6, 7, 8}, {9}, {10}, {11, 12}, {13}, {14, 15, 16, 17, 18, 19, 20}, {21}, {22}};
        W = 0;
        X = new int[5];
        byte[] byArray = new byte[]{0, 5, 3, 6, 2, 7, 1, 4};
        ah = new int[][]{{1, 3}, {1, 4}, {2, 5}, {2, 6}};
    }
}

