package game;

import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

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
   private static ScriptEngine z;
   private static short[][] A = new short[][]{{0}, {1}, {2}, {3}, {4, 5, 6, 7, 8}, {9}, {10}, {11, 12}, {13}, {14, 15, 16, 17, 18, 19, 20}, {21}, {22}};
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
   private int[] V = new int[]{16777215, 9115396};
   private static int W = 0;
   private static int[] X = new int[5];
   private SkillEffect Y;
   private int Z = 0;
   private int aa = 0;
   public byte d = -1;
   private int ab;
   private int ac;
   private int ad;
   private int ae;
   private int af;
   private int ag;
   private static final int[][] ah = new int[][]{{1, 3}, {1, 4}, {2, 5}, {2, 6}};
   private byte ai = 30;
   private Image[] aj;
   private int[][] ak;
   private int al = 0;
   private int am = 0;
   private byte an = 0;

   public static ScreenView a() {
      if (e == null) {
         e = new ScreenView();
      }

      y = UIManager.getInstance();
      z = ScriptEngine.a();
      return e;
   }

   public final void a(Graphics var1) {
      if (this.a != -1 || this.g != -1 || this.h != -1) {
         switch (this.h) {
            case 18:
               y.render(var1);
               if (this.i >= ((int[])null).length) {
                  this.i = 0;
                  this.h = -1;
                  this.b = true;
                  y.closeUI("/data/ui/menu1.ui");
                  return;
               }
         }

         label396: {
            ScreenView var10000;
            boolean var10001;
            switch (this.a) {
               case 0:
               case 1:
               case 2:
                  Graphics var24 = var1;
                  ScreenView var19 = this;
                  int var41 = this.n & 16777215;
                  boolean var58 = false;
                  int var52 = 0;
                  if (var19.a == 0) {
                     var52 = var19.n;
                  } else if (var19.a == 1) {
                     if (255 - var19.k * var19.m < 0) {
                        var19.k--;
                        var19.a = -1;
                        var58 = true;
                     }

                     var52 = 255 - var19.k * var19.m << 24;
                     var52 = var41 | var52;
                  } else if (var19.a == 2) {
                     if (var19.k * var19.m > 255) {
                        var19.k--;
                        var58 = true;
                     }

                     var52 = var19.k * var19.m << 24;
                     var52 = var41 | var52;
                  }

                  int var60 = var19.w;
                  int var62 = BaseScreen.getScreenHeight() / var60 + 1;
                  var41 = BaseScreen.getScreenWidth() * var62;
                  if (var19.o == null || var19.o.length != var41) {
                     var19.o = new int[var41];
                  }

                  if (var19.o[0] != var52) {
                     for (int var63 = 0; var63 < var19.o.length; var63++) {
                        var19.o[var63] = var52;
                     }
                  }

                  for (int var64 = 0; var64 < var60; var64++) {
                     var24.drawRGB(var19.o, 0, BaseScreen.getScreenWidth(), 0, var64 * var62, BaseScreen.getScreenWidth(), var62, true);
                  }

                  if (var58) {
                     var19.d();
                     var10001 = true;
                  } else {
                     var19.k++;
                     var10001 = false;
                  }

                  this.b = var10001;
                  break label396;
               case 3:
                  Graphics var23 = var1;
                  ScreenView var18 = this;
                  short var39 = BaseScreen.getScreenWidth();
                  int var47 = BaseScreen.getScreenHeight();
                  int var55 = var18.n & 16777215;
                  int var7 = var18.l;
                  int var8 = 255 / var7 / 2;
                  int var40 = var39 / 2;
                  int var9 = var47 / 2;
                  int var10;
                  var10 = var40 * 200 / 120;
                  int var11 = var10 * var10;
                  boolean var12 = false;
                  if (var18.q == null) {
                     var18.q = new int[var10];
                  }

                  int var13 = var10 + var7 - (var18.k << 1);
                  if (var18.k <= 0) {
                     var12 = true;
                     var13 = var10 + var7 - (--var18.k << 1);
                  }

                  if (var18.o == null || var18.o.length != var40 * var9) {
                     var18.o = new int[var40 * var9];
                  }

                  if (var18.p == null || var18.p.length != var40 * var9) {
                     var18.p = new int[var40 * var9];
                  }

                  for (int var14 = 0; var14 < var18.q.length; var14++) {
                     if ((var47 = var14 - var13) > var7) {
                        var47 = 255;
                     } else if (var47 < -var7) {
                        var47 = 0;
                     } else {
                        var47 = 127 + var47 * var8;
                     }

                     var18.q[var14] = var55 | var47 << 24;
                  }

                  int var67 = var10 - 1;
                  var47 = var13 + var7;
                  if ((var55 = var13 - var7) < 0) {
                     var55 = 0;
                  }

                  var47 = var47 * var11 / var67;
                  var55 = var55 * var11 / var67;

                  for (int var59 = 0; var59 < var9; var59++) {
                     var10 = var59 * var59;
                     var13 = var59 * var40;

                     for (int var15 = 0; var15 < var40; var15++) {
                        if ((var8 = var10 + var15 * var15) > var47) {
                           var18.o[var13 + var15] = -16777216;
                        } else if (var8 < var55) {
                           var18.o[var13 + var15] = 0;
                        } else {
                           var18.o[var13 + var15] = var18.q[var67 * var8 / var11];
                        }
                     }
                  }

                  if (var18.o != null) {
                     var23.drawRGB(var18.o, 0, var40, var40, var9, var40, var9, true);
                     var23.drawRGB(a(var18.o, var18.p, var40, var9, (byte)2), 0, var40, 0, var9, var40, var9, true);
                     var23.drawRGB(a(var18.o, var18.p, var40, var9, (byte)3), 0, var40, 0, 0, var40, var9, true);
                     var23.drawRGB(a(var18.o, var18.p, var40, var9, (byte)1), 0, var40, var40, 0, var40, var9, true);
                  }

                  boolean var71;
                  if (var12) {
                     var18.d();
                     var71 = true;
                  } else {
                     var18.k -= 10;
                     var71 = false;
                  }

                  this.b = var71;
                  break label396;
               case 4:
                  if (this.b) {
                     this.a = -1;
                     var1.fillRect(0, 0, BaseScreen.getScreenWidth(), BaseScreen.getScreenHeight());
                     break label396;
                  }

                  var10000 = this;
                  var10001 = this.d(var1);
                  break;
               case 5:
                  if (this.b) {
                     break label396;
                  }

                  var10000 = this;
                  var10001 = this.d(var1);
                  break;
               case 6:
                  if (this.b) {
                     this.a = -1;
                     var1.fillRect(0, 0, BaseScreen.getScreenWidth(), BaseScreen.getScreenHeight());
                  } else {
                     boolean var69;
                     label374: {
                        Graphics var22 = var1;
                        ScreenView var17 = this;
                        if (this.i < 10) {
                           if (var17.i % 3 == 1) {
                              var22.setColor(16777215);
                              var22.fillRect(0, 0, BaseScreen.getScreenWidth(), BaseScreen.getScreenHeight());
                           } else {
                              WorldManager.a();
                              WorldManager.a(var22, 0, 0, BaseScreen.getScreenWidth(), BaseScreen.getScreenHeight());
                              WorldManager.a().b.a(var22);
                           }

                           var17.i++;
                        } else {
                           if (var17.i >= BaseScreen.getScreenWidth()) {
                              var22.setColor(0);
                              var22.fillRect(0, 0, BaseScreen.getScreenWidth(), BaseScreen.getScreenHeight());
                              var69 = true;
                              break label374;
                           }

                           switch (var17.x) {
                              case 0:
                                 var22.setColor(0);
                                 var22.fillRect(0, 0, var17.i, BaseScreen.getHalfHeight());

                                 for (int var37 = 1; var37 < 6; var37++) {
                                    var22.fillRect(var17.i + var37 * 15, 0, 15 - var37 * 3, BaseScreen.getHalfHeight());
                                 }

                                 var22.fillRect(BaseScreen.getScreenWidth() - var17.i, BaseScreen.getHalfHeight(), var17.i, BaseScreen.getHalfHeight());

                                 for (int var38 = 1; var38 < 6; var38++) {
                                    var22.fillRect(
                                       BaseScreen.getScreenWidth() - var17.i - var38 * 15,
                                       BaseScreen.getHalfHeight(),
                                       15 - var38 * 3,
                                       BaseScreen.getHalfHeight()
                                    );
                                 }

                                 var17.i += 15;
                                 break;
                              case 1:
                                 var22.setColor(0);
                                 boolean var36 = false;

                                 for (int var46 = 0; var46 < BaseScreen.getScreenHeight(); var46 += 10) {
                                    if (var36) {
                                       var22.fillRect(0, var46, var17.i, 10);
                                       var36 = false;
                                    } else {
                                       var22.fillRect(BaseScreen.getScreenWidth() - var17.i, var46, var17.i, 10);
                                       var36 = true;
                                    }
                                 }

                                 var17.i += 15;
                                 break;
                              case 2:
                                 var22.setColor(0);
                                 boolean var35 = false;

                                 for (int var45 = 0; var45 < BaseScreen.getScreenWidth(); var45 += 10) {
                                    if (var35) {
                                       var22.fillRect(var45, 0, 10, var17.i);
                                       var35 = false;
                                    } else {
                                       var22.fillRect(var45, BaseScreen.getScreenHeight() - var17.i, 10, var17.i);
                                       var35 = true;
                                    }
                                 }

                                 var17.i += 15;
                           }
                        }

                        var69 = false;
                     }

                     this.b = var69;
                  }
                  break label396;
               case 7:
                  if (this.Y != null) {
                     this.Y.a(var1);
                  }
                  break label396;
               case 8:
                  if (this.i >= 5) {
                     var1.drawImage(BattleScreen.a().c, 0, 0, 20);
                  }

                  y.render(var1);
                  if (this.i >= A.length) {
                     this.i = 0;
                     this.a = -1;
                     this.b = true;
                     y.closeUI("/data/ui/npcEnemy.ui");
                     return;
                  }
                  break label396;
               case 9:
                  var1.setColor(this.n);
                  var1.fillRect(0, 0, BaseScreen.getScreenWidth(), BaseScreen.getScreenHeight());
                  break label396;
               case 10:
                  if (this.i <= this.L) {
                     if (this.i % 3 / (this.M + 1) == 0) {
                        var1.setColor(16777215);
                        var1.fillRect(0, 0, BaseScreen.getScreenWidth(), BaseScreen.getScreenHeight());
                     } else if (this.i % 3 / (this.M + 1) == 1) {
                        var1.setColor(0);
                        var1.fillRect(0, 0, BaseScreen.getScreenWidth(), BaseScreen.getScreenHeight());
                     }

                     this.i++;
                  }
               case 11:
               case 12:
               case 13:
               case 16:
               case 18:
               default:
                  break label396;
               case 14:
               case 15:
                  Graphics var21 = var1;
                  ScreenView var16 = this;
                  boolean var34 = false;
                  int var44 = 0;
                  if (var16.a == 15) {
                     var44 = var16.k;
                     if (var16.k >= 255) {
                        var44 = 255;
                        var16.k = 255;
                        var34 = true;
                     }
                  } else if (var16.a == 14 && (var44 = 255 - var16.k) <= 0) {
                     var44 = 0;
                     var16.a = -1;
                     var34 = true;
                  }

                  if (var16.O != null) {
                     var16.O = ImageTransformer.b(var16.O, var44);
                     var21.drawRGB(var16.O.a, 0, var16.O.b, var16.P - var16.O.b / 2, var16.Q - var16.O.c / 2, var16.O.b, var16.O.c, true);
                  }

                  if (var34) {
                     var16.a = -1;
                     var16.d();
                     var10001 = true;
                  } else {
                     var16.k = var16.k + var16.m;
                     var10001 = false;
                  }

                  this.b = var10001;
                  break label396;
               case 17:
                  var1.setColor(this.V[this.n]);
                  int var43 = this.R;
                  int var33 = this.U;
                  int var20 = this.T;
                  var1.fillArc(this.T - var43, var33 - var43, var43 << 1, var43 << 1, 0, 360);
                  break label396;
               case 19:
               case 20:
                  var10000 = this;
                  Graphics var3 = var1;
                  ScreenView var2 = this;
                  var3.setColor(var2.n);
                  int var4 = 0;

                  for (int var5 = 0; var5 < W; var5++) {
                     for (int var6 = 0; var6 < X[1]; var6++) {
                        switch (X[2]) {
                           case -1:
                              var4 = 0;
                              break;
                           case 1:
                              var4 = X[0];
                        }

                        switch (X[3]) {
                           case 0:
                              int var32;
                              var4 = (var32 = var4 + (var6 - X[4]) * X[2]) < 0 ? 0 : (var32 > X[0] ? X[0] : var32);
                              break;
                           case 1:
                              int var31;
                              var4 = (var31 = var4 + (X[1] - var6 - X[4]) * X[2]) < 0 ? 0 : (var31 > X[0] ? X[0] : var31);
                              break;
                           case 2:
                              int var30;
                              var4 = (var30 = var4 + (var5 - X[4]) * X[2]) < 0 ? 0 : (var30 > X[0] ? X[0] : var30);
                              break;
                           case 3:
                              int var29;
                              var4 = (var29 = var4 + (8 - var5 - X[4]) * X[2]) < 0 ? 0 : (var29 > X[0] ? X[0] : var29);
                              break;
                           case 4:
                              int var28;
                              var4 = (var28 = var4 + ((var5 + var6 >> 1) - X[4]) * X[2]) < 0 ? 0 : (var28 > X[0] ? X[0] : var28);
                              break;
                           case 5:
                              int var27;
                              var4 = (var27 = var4 + ((8 - var5 + var6 >> 1) - X[4]) * X[2]) < 0 ? 0 : (var27 > X[0] ? X[0] : var27);
                              break;
                           case 6:
                              int var26;
                              var4 = (var26 = var4 + ((var5 + X[1] - var6 >> 1) - X[4]) * X[2]) < 0 ? 0 : (var26 > X[0] ? X[0] : var26);
                              break;
                           case 7:
                              int var25;
                              var4 = (var25 = var4 + ((8 - var5 + X[1] - var6 >> 1) - X[4]) * X[2]) < 0 ? 0 : (var25 > X[0] ? X[0] : var25);
                        }

                        var3.fillRect(X[0] * var5 + (X[0] - var4 >> 1), X[0] * var6 + (X[0] - var4 >> 1), var4, var4);
                     }
                  }

                  X[4] = X[4] + 2;
                  if (X[4] > 40) {
                     if (var2.a == 20) {
                        var2.a = -1;
                     }

                     if (var2.a == 19) {
                        var3.fillRect(0, 0, BaseScreen.getScreenWidth(), BaseScreen.getScreenHeight());
                     }

                     var10001 = true;
                  } else {
                     var10001 = false;
                  }
            }

            var10000.b = (boolean)var10001;
         }

         switch (this.g) {
            case 12:
               var1.setColor(0);
               var1.fillRect(0, 0, this.I, this.J - this.j * this.J / this.G);
               var1.fillRect(0, BaseScreen.getScreenHeight() - this.K + this.j * this.K / this.G, this.I, this.K - this.j * this.K / this.G);
               return;
            case 13:
               var1.setColor(0);
               var1.fillRect(0, 0, this.I, this.j * this.J / this.G);
               var1.fillRect(0, BaseScreen.getScreenHeight() - this.j * this.K / this.G, this.I, this.j * this.K / this.G);
         }
      }
   }

   private void a(int var1) {
      this.aa = var1;
      switch (this.a) {
         case 7:
            short var2 = GameDatabase.gameDatabase[0][BattleScreen.a().k()][17];
            switch (this.aa) {
               case 0:
                  short[] var6 = new short[]{8, 118, 160, var2, 0, 1, 0, 4, 0, 2, 1, 8, 0, -16, 10, 0, 0};
                  this.Y = new SkillEffect();
                  this.Y.a(var6);
                  this.Y.c(true);
                  this.Y.a();
                  return;
               case 1:
                  short[] var5 = new short[]{17, 118, 160, var2, 0, 1, 100, 255, 255, 255, 12, 0, 1, 1, 9};
                  this.Y.a(var5);
                  this.Y.a();
                  return;
               case 2:
                  short[] var4 = new short[]{17, 118, 160, var2, 0, 1, 255, 255, 255, 255, 15, 0, 1, 1, 13};
                  this.Y.a(var4);
                  this.Y.a();
                  return;
               case 3:
                  short[] var3 = new short[]{9, 118, 160, var2, 0, 1, 160, 255, 255, 255, 0, 4, 1};
                  this.Y.a(var3);
                  this.Y.a();
               default:
                  return;
            }
         case 8:
            this.Z = A[this.i].length;
      }
   }

   public final void b() {
      if (this.a != -1 || this.g != -1 || this.h != -1) {
         switch (this.a) {
            case 7:
               if (this.Y != null && !this.Y.e()) {
                  this.aa++;
                  if (this.aa >= 4) {
                     this.b = true;
                     this.a = -1;
                     this.i = 0;
                     this.Y = null;
                     return;
                  }

                  this.a(this.aa);
               }
               break;
            case 8:
               if (this.aa < this.Z) {
                  z.b(this.i, A[this.i][this.aa]);
                  this.aa++;
               } else {
                  this.i++;
                  if (this.i < A.length) {
                     this.a(0);
                  }
               }

               y.update();
            case 9:
            case 12:
            case 13:
            case 14:
            case 15:
            case 16:
            default:
               break;
            case 10:
               if (this.i > this.L) {
                  this.b = true;
                  this.a = -1;
                  this.i = 0;
               }
               break;
            case 11:
               if (this.C == 0) {
                  if (this.F >= this.B[this.D].length * this.E) {
                     this.a = -1;
                     this.b = true;
                     this.F = 0;
                     ParticleEffect.a().a = ParticleEffect.a().b;
                     return;
                  }

                  ParticleEffect.a().movePosX(this.B[this.D][this.F % this.B[this.D].length]);
                  this.F++;
               } else {
                  if (this.F >= this.B[this.D].length * this.E) {
                     this.a = -1;
                     this.b = true;
                     this.F = 0;
                     ParticleEffect.a().a = ParticleEffect.a().b;
                     return;
                  }

                  ParticleEffect.a().movePosY(this.B[this.D][this.F % this.B[this.D].length]);
                  this.F++;
               }
               break;
            case 17:
               this.i++;
               if (this.S == 0) {
                  if ((BaseScreen.getScreenWidth() - this.T) * (BaseScreen.getScreenWidth() - this.T)
                        + (BaseScreen.getScreenHeight() - this.U) * (BaseScreen.getScreenHeight() - this.U)
                     < this.R * this.R) {
                     this.i = 0;
                     this.b = true;
                  }

                  this.R += 10;
               } else if (this.S == 1) {
                  this.R -= 10;
                  if (this.R <= 0) {
                     this.i = 0;
                     this.a = -1;
                     this.b = true;
                  }
               } else if (this.i <= 10) {
                  this.R += 10;
               } else if (this.i > 10 && this.i <= 20) {
                  this.R -= 10;
               } else {
                  this.i = 0;
                  this.b = true;
                  this.a = -1;
               }
         }

         switch (this.g) {
            case 12:
               this.j = this.j + this.H;
               if (this.j > this.G) {
                  this.j = 0;
                  this.c = true;
                  this.g = -1;
               }
               break;
            case 13:
               this.j = this.j + this.H;
               if (this.j > this.G) {
                  this.j = this.G;
                  this.c = true;
                  return;
               }
         }
      }
   }

   private boolean d(Graphics var1) {
      int var2 = this.n & 16777215;
      int var3 = 0;
      int var4 = this.s;
      int var5 = 255 / ((var4 << 1) + 1);
      int var6;
      int var7;
      int var10000 = var7 = (var6 = this.r / 2) * 200 / 120;
      int var8 = var10000 * var10000;
      int var10 = this.t / this.r + 1;
      int var11 = BaseScreen.getScreenWidth() / this.r;
      int var12 = BaseScreen.getScreenHeight() / this.r;
      int var13 = var11 / 2;
      int var14 = var12 / 2;
      int[] var15 = new int[var7];
      if (this.u == null) {
         this.u = new int[EngineUtils.a(var13 * var13 + var14 * var14, 0)][];
      }

      if (this.v == null) {
         this.v = new int[this.u.length];
      }

      if (var10 > this.u.length) {
         var10 = this.u.length;
      }

      for (int var16 = 0; var16 < var10; var16++) {
         if (this.u[var16] == null) {
            this.u[var16] = new int[this.r * this.r];
         }

         if (this.v[var16] < var7 + var4) {
            int var9 = -var4 + this.v[var16];

            for (int var17 = 0; var17 < var15.length; var17++) {
               int var18;
               if ((var18 = var17 - var9) > var4) {
                  var3 = this.a == 4 ? 0 : 255;
               } else if (var18 < -var4) {
                  var3 = this.a == 4 ? 255 : 0;
               } else if (this.a == 4) {
                  var3 = 127 - var18 * var5;
               } else if (this.a == 5) {
                  var3 = 127 + var18 * var5;
               }

               var15[var17] = var2 | var3 << 24;
            }

            int var28 = var7 - 1;
            int var31 = var9 + var4;
            if ((var9 = var9 - var4) < 0) {
               var9 = 0;
            }

            int var19 = var31 * var8 / var28;
            int var20 = var9 * var8 / var28;

            for (int var25 = 0; var25 < this.r; var25++) {
               var31 = (var25 - var6) * (var25 - var6);
               int var21 = var25 * this.r;

               for (int var23 = 0; var23 < this.r; var23++) {
                  int var22 = EngineUtils.a(var28 = var31 + (var23 - var6) * (var23 - var6), 1);
                  if (var28 > var19) {
                     this.u[var16][var21 + var23] = this.a == 4 ? 0 : -16777216;
                  } else if (var28 < var20) {
                     this.u[var16][var21 + var23] = this.a == 4 ? -16777216 : 0;
                  } else {
                     this.u[var16][var21 + var23] = var15[var22];
                  }
               }
            }

            this.v[var16]++;
         }
      }

      boolean var27 = true;
      var1.setColor(var2);

      for (int var30 = 0; var30 < var12; var30++) {
         int var33 = (var30 - var14) * (var30 - var14);

         for (int var34 = 0; var34 < var11; var34++) {
            int var26 = EngineUtils.a(var33 + (var34 - var13) * (var34 - var13), 1);
            if (this.u[var26] == null) {
               if (this.a == 5) {
                  var1.fillRect(var34 * this.r, var30 * this.r, this.r, this.r);
               }
            } else if (this.v[var26] >= var7 + var4) {
               if (this.a == 4) {
                  var1.fillRect(var34 * this.r, var30 * this.r, this.r, this.r);
               }
            } else {
               var27 = false;
               var1.drawRGB(this.u[var26], 0, this.r, var34 * this.r, var30 * this.r, this.r, this.r, true);
            }
         }
      }

      if (var27) {
         this.d();
         return true;
      } else {
         this.t += 15;
         return false;
      }
   }

   private static int[] a(int[] var0, int[] var1, int var2, int var3, byte var4) {
      if (var4 == 5) {
         for (int var9 = 0; var9 < var3; var9++) {
            int var5 = var9 * var2;
            int var6 = -1 - var9;
            int var7 = 0;

            for (int var8 = 1; var7 < var2; var8++) {
               var1[var8 * var3 + var6] = var0[var5 + var7];
               var7++;
            }
         }
      } else if (var4 == 3) {
         int limit = var2 * var3 - 1;

         for (int var14 = 0; var14 < var3; var14++) {
            int var18 = var14 * var2;
            int var22 = limit - var14 * var2;

            for (int var26 = 0; var26 < var2; var26++) {
               var1[var22 - var26] = var0[var18 + var26];
            }
         }
      } else if (var4 == 6) {
         int limit = var2 - 1;

         for (int var15 = 0; var15 < var3; var15++) {
            int var19 = limit * var3 + var15;
            int var23 = var15 * var2;

            for (int var27 = 0; var27 < var2; var27++) {
               var1[var19 - var27 * var3] = var0[var23 + var27];
            }
         }
      } else if (var4 != 0 && var4 != 7) {
         if (var4 == 1) {
            int limit = var3 - 1;

            for (int var16 = 0; var16 < var3; var16++) {
               int var20 = var16 * var2;
               int var24 = (limit - var16) * var2;

               for (int var28 = 0; var28 < var2; var28++) {
                  var1[var24 + var28] = var0[var20 + var28];
               }
            }
         } else if (var4 != 4 && var4 == 2) {
            int limit = var2 - 1;

            for (int var17 = 0; var17 < var3; var17++) {
               int var21;
               int var25 = (var21 = var17 * var2) + limit;

               for (int var29 = 0; var29 < var2; var29++) {
                  var1[var25 - var29] = var0[var21 + var29];
               }
            }
         }
      }

      return var1;
   }

   public final void c() {
      this.d = -1;
      this.O = null;
   }

   public final void a(int var1, int var2, int var3, int var4, int var5, int var6) {
      this.d = 0;
      this.b(var1, var2);
      this.ad = var3;
      this.ae = var4;
      this.af = var5;
      this.ag = var6;
   }

   public final void a(int var1, int var2) {
      this.af = var1;
      this.ag = var2;
   }

   public final void b(int var1, int var2) {
      this.ab = var1;
      this.ac = var2;
   }

   public final void b(Graphics var1) {
      var1.setColor(0);
      var1.fillRect(0, 0, this.ad, this.ac - this.ag);
      var1.fillRect(0, this.ac - this.ag, this.ab - this.af, this.ag << 1);
      var1.fillRect(0, this.ac + this.ag, this.ad, this.ae - (this.ac + this.ag));
      var1.fillRect(this.ab + this.af, this.ac - this.ag, this.ad - (this.ab + this.af), this.ag << 1);
   }

   public final void c(int var1, int var2) {
      this.k = 0;
      this.n = var1;
      if (var2 == 12 || var2 == 13) {
         this.g = var2;
         this.c = false;
      } else if (var2 == 18) {
         this.h = var2;
      } else {
         this.a = var2;
      }

      this.b = false;
      switch (this.a) {
         case 1:
         case 2:
            this.m = 17;
            return;
         case 3:
            this.k = BaseScreen.getHalfWidth();
            this.l = 20;
            return;
         case 4:
         case 5:
            this.t = 0;
            return;
         case 6:
            this.i = 0;
            this.x = EngineUtils.randomInt(2);
            return;
         case 7:
            this.a(0);
            return;
         case 8:
            this.i = 0;
            z.at();
            this.a(0);
            return;
         case 10:
         case 17:
            this.i = 0;
            return;
         case 19:
            this.c(-1);
            return;
         case 20:
            this.c(1);
         case 9:
         case 11:
         case 12:
         case 13:
         case 14:
         case 15:
         case 16:
         case 18:
      }
   }

   public final void a(int var1, int var2, int var3) {
      ParticleEffect.a().d((byte)3);
      this.C = var1;
      this.D = var2;
      this.E = var3;
   }

   public final void a(int var1, int var2, int var3, int var4, int var5) {
      this.j = 0;
      this.G = var1;
      this.H = var2;
      this.I = var3;
      this.J = var4;
      this.K = var5;
   }

   public final void d(int var1, int var2) {
      this.L = var1;
      this.M = var2;
   }

   public final void a(int var1, int var2, int var3, int var4) {
      this.S = var1;
      this.T = var2;
      this.U = var3;
      this.R = var4;
   }

   public final void a(String var1, int var2, int var3, int var4) {
      this.N = EngineUtils.loadImage("/data/tex/", var1);
      this.O = new PixelBuffer();
      this.O = ImageTransformer.a(this.N, this.O);
      this.P = var2;
      this.Q = var3;
      this.m = var4;
   }

   private void d() {
      this.o = null;
      this.p = null;
      this.q = null;
      this.u = null;
      this.v = null;
      this.N = null;
   }

   public final void a(int var1, byte var2, byte var3, String[] var4) {
      this.f = var1;
      if (this.a != 17 || this.S != 0) {
         this.a = -1;
         if (this.f >= 16) {
            this.ai = var2;
            this.al = var1;
            this.am = 0;
            this.an = var3;
            this.aj = null;
            this.aj = new Image[var4.length];

            for (int var5 = 0; var5 < var4.length; var5++) {
               this.aj[var5] = EngineUtils.loadImage("/data/tex/", var4[var5]);
            }

            this.ak = new int[var2][5];

            for (int var6 = 0; var6 < var2; var6++) {
               this.b(var6);
            }
         }
      }
   }

   private void b(int var1) {
      int var2;
      if ((var2 = EngineUtils.randomInt(100)) < 3) {
         this.ak[var1][0] = this.aj.length - 1;
      } else if (var2 < 15) {
         this.ak[var1][0] = this.aj.length - 2;
      } else if (var2 < 50) {
         this.ak[var1][0] = this.aj.length - 3;
      } else {
         this.ak[var1][0] = 0;
      }

      this.ak[var1][1] = EngineUtils.randomInt(BaseScreen.getScreenWidth());
      this.ak[var1][2] = EngineUtils.randomInt(BaseScreen.getScreenHeight());
      this.ak[var1][3] = EngineUtils.randomInt(ah[this.ak[var1][0]][1] - ah[this.ak[var1][0]][0]) + ah[this.ak[var1][0]][0];
      this.ak[var1][4] = EngineUtils.a();
   }

   public final void c(Graphics var1) {
      if (this.f >= 16) {
         for (int var2 = 0; var2 < this.ai; var2++) {
            if (this.ak[var2][1] < BaseScreen.getScreenWidth() && this.ak[var2][2] < BaseScreen.getScreenHeight()) {
               var1.drawImage(this.aj[this.ak[var2][0]], this.ak[var2][1], this.ak[var2][2], 20);
            }

            switch (this.an) {
               case 0:
                  this.ak[var2][2] = this.ak[var2][2] - this.ak[var2][3];
                  break;
               case 1:
                  this.ak[var2][1] = this.ak[var2][1] + this.ak[var2][3];
                  this.ak[var2][2] = this.ak[var2][2] - this.ak[var2][3];
                  break;
               case 2:
                  this.ak[var2][1] = this.ak[var2][1] + this.ak[var2][3];
                  break;
               case 3:
                  this.ak[var2][1] = this.ak[var2][1] + this.ak[var2][3];
                  this.ak[var2][2] = this.ak[var2][2] + this.ak[var2][3];
                  break;
               case 4:
                  this.ak[var2][2] = this.ak[var2][2] + this.ak[var2][3];
                  break;
               case 5:
                  this.ak[var2][1] = this.ak[var2][1] - this.ak[var2][3];
                  this.ak[var2][2] = this.ak[var2][2] + this.ak[var2][3];
                  break;
               case 6:
                  this.ak[var2][1] = this.ak[var2][1] - this.ak[var2][3];
                  break;
               case 7:
                  this.ak[var2][1] = this.ak[var2][1] - this.ak[var2][3];
                  this.ak[var2][2] = this.ak[var2][2] - this.ak[var2][3];
            }

            if (this.ak[var2][1] < this.al - this.aj[this.ak[var2][0]].getWidth() || this.ak[var2][2] < 0 - this.aj[this.ak[var2][0]].getHeight()) {
               this.b(var2);
            }
         }
      }
   }

   private void c(int var1) {
      X[0] = 20;
      W = BaseScreen.getScreenWidth() / X[0];
      X[1] = (BaseScreen.getScreenHeight() - 1) / X[0] + 1;
      X[2] = var1;
      X[3] = EngineUtils.randomRange(0, 7);
      X[4] = 0;
      this.b = false;
   }

   static {
      byte[] var10000 = new byte[]{0, 5, 3, 6, 2, 7, 1, 4};
   }
}
