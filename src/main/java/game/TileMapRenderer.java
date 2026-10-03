package game;

import javax.microedition.lcdui.Graphics;

public final class TileMapRenderer {
   private static int c = 0;
   private char[] d;
   private int e;
   private int f;
   private int g;
   private int h;
   private int i;
   private int[] j;
   private int k;
   private int l;
   private int m;
   private int n;
   private int o;
   private int p;
   private int q;
   private int r;
   public static boolean a = false;
   private int s;
   public static boolean b = false;
   private byte t;
   private static TileMapRenderer u;
   private boolean v;
   private int w;
   private int[][] x;
   private int y;
   private char[] z;
   private char[] A;
   private String[] B;
   private int C;
   private long D;
   private int E;

   public TileMapRenderer() {
      BaseScreen.getFontHeight();
      this.g = 0;
      this.h = 0;
      this.i = 0;
      this.j = new int[10];
      this.k = 0;
      this.l = 0;
      this.m = 2;
      this.n = 0;
      this.q = 0;
      this.r = 2;
      this.s = 0;
      this.t = 0;
      this.w = 0;
      this.x = null;
      this.y = 0;
      this.z = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
      this.A = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
      this.B = new String[]{"0000", "0001", "0010", "0011", "0100", "0101", "0110", "0111", "1000", "1001", "1010", "1011", "1100", "1101", "1110", "1111"};
   }

   public static TileMapRenderer a() {
      if (u == null) {
         u = new TileMapRenderer();
      }

      return u;
   }

   public final void b() {
      char[] var1 = this.d;
      switch (c) {
         case 0:
            int var2 = var1.length;
            int var3;
            int var4 = (var3 = this.E - 10) - TextLayoutHelper.b;
            int var5 = 1;
            int var6 = 0;

            for (; var6 < 2; var6++) {
               if (this.h < var2) {
                  this.D = 0L;
                  if (this.h == 0) {
                     this.C = 0;
                  }

                  char var7;
                  if ((var7 = var1[this.h]) == '#') {
                     this.i += 7;
                     this.h += 7;
                  } else {
                     int var8 = TextLayoutHelper.a(var7);
                     int var9;
                     if ((var9 = this.C + var8) > var3 || var7 == ' ' && var9 > var4) {
                        var9 = 0;
                        this.C = 0;
                        if (var7 != ' ') {
                           var9 = var8 + 0;
                        }

                        if (++var5 > this.q) {
                           var5 = 0;
                           this.g = this.h;
                        }
                     }

                     this.C = var9;
                     this.g++;
                  }
               } else if (this.D == 0L) {
                  this.D = System.currentTimeMillis() + 2500L;
               } else if (System.currentTimeMillis() > this.D) {
                  if (!this.v) {
                     this.c();
                     b = false;
                     if (WorldManager.a().uiManager.isTopUI("/data/ui/dialog.ui")) {
                        WorldManager.a().interactionController.aC();
                     }
                  }

                  a = true;
                  this.r++;
               }
            }

            OverworldScreen.o = null;
            this.j[0] = this.j[this.l];
            b = false;
            this.g = 0;
            this.h = 0;
            this.i = 0;
            a = false;
            return;
         case 1:
            b = false;
            this.g = 0;
            this.h = 0;
            this.i = 0;
            return;
         case 2:
            return;
         case 3:
            if (this.s < this.r * (this.y + 1)) {
               if (this.r * (this.y + 1) > ((int[])null).length) {
                  this.s = ((int[])null).length;
                  return;
               } else {
                  this.s = this.r * (this.y + 1);
               }
            }
      }
   }

   public final void a(Graphics var1) {
      if (b) {
         label83:
         switch (c) {
            case 0:
               switch (this.t) {
                  case 0:
                     var1.setColor(16777215);
                     this.a(var1, this.o, this.p);
                     break label83;
                  case 1:
                     this.a(var1, this.o, this.p - (BaseScreen.getFontHeight() >> 1));
                  default:
                     break label83;
               }
            case 1:
               switch (this.t) {
                  case 0:
                     int var14 = 0;

                     while (true) {
                        if (var14 >= EngineUtils.a.length) {
                           break label83;
                        }

                        var1.setColor(16777215);
                        var1.drawString(EngineUtils.a[var14], this.o, this.p + var14 * (BaseScreen.getFontHeight() + BaseScreen.getFontHeight() / 2), 20);
                        var14++;
                     }
                  case 1:
                     for (int var13 = 0; var13 < EngineUtils.a.length; var13++) {
                        var1.setColor(16777215);
                        var1.drawString(
                           EngineUtils.a[var13],
                           this.o,
                           (this.p - (BaseScreen.getFontHeight() + BaseScreen.getFontHeight() / 2) * EngineUtils.a.length >> 1)
                              + var13 * (BaseScreen.getFontHeight() + BaseScreen.getFontHeight() / 2),
                           17
                        );
                     }
                  default:
                     break label83;
               }
            case 2:
               switch (this.t) {
                  case 0:
                     int var12 = 0;

                     while (true) {
                        if (var12 >= EngineUtils.a.length) {
                           break label83;
                        }

                        var1.setColor(16777215);
                        var1.drawString(
                           EngineUtils.a[var12], this.o, this.p + var12 * (BaseScreen.getFontHeight() + BaseScreen.getFontHeight() / 2) + this.n, 20
                        );
                        var12++;
                     }
                  case 1:
                     for (int var11 = 0; var11 < EngineUtils.a.length; var11++) {
                        var1.setColor(16777215);
                        var1.drawString(
                           EngineUtils.a[var11],
                           this.o,
                           (this.p - (BaseScreen.getFontHeight() + BaseScreen.getFontHeight() / 2) * EngineUtils.a.length >> 1)
                              + var11 * (BaseScreen.getFontHeight() + BaseScreen.getFontHeight() / 2)
                              + this.n,
                           17
                        );
                     }
                  default:
                     break label83;
               }
            case 3:
               int var2 = this.p;
               int var3 = this.o;
               int var4 = this.d.length;

               for (int var5 = this.y * this.r; var5 < this.s; var5++) {
                  int var6 = TextLayoutHelper.a * (20 - this.y * this.r) + var2;
                  int var7 = this.f + var3;

                  for (int var8 = ((int[])null)[0]; var8 < ((int[])null)[1]; var8++) {
                     while (var8 < var4 && this.d[var8] == '#') {
                        String var9 = "";

                        for (int var10 = 0; var10 < 7; var10++) {
                           var9 = var9 + this.d[var8 + var10];
                        }

                        this.k = ++this.k >= this.j.length ? 0 : this.k;
                        this.j[this.k] = this.a(var9);
                        var1.setColor(this.j[this.k]);
                        var8 += 7;
                     }

                     var1.setColor(this.j[this.k]);
                     if (var8 < var4) {
                        char var15 = this.d[var8];
                        TextLayoutHelper.a(var1, var15, var7, var6);
                        var7 += TextLayoutHelper.a(var15);
                     }
                  }
               }

               this.l = this.k;
               this.k = 0;
         }

         if (this.v && a && this.s % 10 < 5) {
            var1.setColor(16777215);
            var1.drawString("Nhấn nút 0 để tiếp tục", BaseScreen.getScreenWidth() >> 1, BaseScreen.getScreenHeight() - 8, 33);
         }
      }
   }

   public final void c() {
      this.d = null;
      EngineUtils.a = null;
      this.s = 0;
   }

   public final void a(byte var1, String var2, int var3) {
      this.d = var2.toCharArray();
      c = var1;
      switch (var1) {
         case 0:
            TileMapRenderer var15 = this;
            char[] var17 = this.d;
            int var18 = this.d.length;
            int var19 = 0;
            int var20 = 0;
            int var21;
            int var22 = (var21 = var15.E - 10) - TextLayoutHelper.b;
            StringBuffer var23 = new StringBuffer();

            while (var15.h < var18) {
               char var25;
               if ((var25 = var17[var15.g]) == '#') {
                  var15.i += 7;
                  var15.h += 7;
               } else {
                  var15.h++;
                  int var26 = TextLayoutHelper.a(var25);
                  int var27 = var20 + var26;
                  var23.append(var25);
                  if (var20 > var21 || var25 == ' ' && var20 > var22) {
                     var27 = 0;
                     if (var25 != ' ') {
                        var27 = var26 + 0;
                     }

                     var19++;
                     var23 = new StringBuffer();
                  }

                  var20 = var27;
               }
            }

            if (var20 > 0) {
               var19++;
            }

            this.r = var19;
         case 1:
         case 2:
         default:
            break;
         case 3:
            this.y = 0;
            c = 3;
            this.g = 0;
            this.h = 0;
            this.i = 0;
            char[] var14 = this.d;
            int var16 = this.d.length;
            int[][] var4 = new int[50][2];
            int var5 = 0;
            int var6;
            int var7 = (var6 = this.E - 10) - TextLayoutHelper.b;
            int var8 = 0;
            int var9 = 0;

            for (int var24 = 0; this.h < var16; var24++) {
               char var11;
               if ((var11 = var14[var24]) == '#') {
                  var24 += 7;
               } else {
                  int var12 = TextLayoutHelper.a(var11);
                  int var13 = var5 + var12;
                  if (var5 > var6 || var11 == ' ' && var5 > var7) {
                     var13 = 0;
                     if (var11 != ' ') {
                        var13 = var12 + 0;
                     }

                     var4[var9][0] = var8;
                     var4[var9][1] = var24;
                     var8 = var24;
                     var9++;
                  }

                  var5 = var13;
                  var24++;
               }
            }

            this.a(0, 80);
      }

      this.g = 0;
      this.h = 0;
      this.i = 0;
      b = true;
      a = false;
      this.v = false;
      this.t = (byte)var3;
   }

   public final void a(int var1, int var2) {
      this.o = var1;
      this.p = var2;
      this.E = BaseScreen.getScreenWidth() - 2 * this.o;
      this.f = 0;
   }

   public final void b(int var1, int var2) {
      this.e = var1 / BaseScreen.getFontCharWidth();
      this.f = var1 - this.e * BaseScreen.getFontCharWidth() >> 1;
      this.r = var2 / BaseScreen.getFontHeight();
   }

   private void a(Graphics var1, int var2, int var3) {
      this.q = this.g;
      char[] var4 = this.d;
      int var5 = this.d.length;
      int var6 = var2;
      int var13 = var3;
      int var7;
      int var8 = (var7 = this.E + var2 - 10) - TextLayoutHelper.b;

      for (int var9 = this.g; var9 < this.h; var9++) {
         while (var9 < var5 && var4[var9] == '#') {
            String var10 = "";

            for (int var11 = 0; var11 < 7; var11++) {
               var10 = var10 + var4[var9 + var11];
            }

            this.k = ++this.k >= this.j.length ? 0 : this.k;
            this.j[this.k] = this.a(var10);
            var1.setColor(this.j[this.k]);
            var9 += 7;
         }

         var1.setColor(this.j[this.k]);
         if (var9 < var5) {
            char var14;
            int var15 = TextLayoutHelper.a(var14 = var4[var9]);
            int var12;
            if ((var12 = var6 + var15) > var7 || var14 == ' ' && var12 > var8) {
               var12 = var2;
               var6 = var2;
               if (var14 != ' ') {
                  var12 = var6 + var15;
               }

               var13 += TextLayoutHelper.a + 1;
            }

            TextLayoutHelper.a(var1, var14, var6, var13);
            var6 = var12;
         }

         this.q++;
      }

      this.l = this.k;
      this.k = 0;
   }

   private int a(String var1) {
      char[] var4 = var1.toCharArray();
      StringBuffer var2 = new StringBuffer();

      for (int var3 = 0; var3 < var4.length; var3++) {
         var2.append(this.a(var4[var3]));
      }

      char[] var5 = var2.toString().toCharArray();
      int var7 = 0;

      for (int var6 = 0; var6 < var5.length; var6++) {
         if (var5[var6] == '1') {
            var7 += 1 << var5.length - var6 - 1;
         }
      }

      return var7;
   }

   private String a(char var1) {
      for (int var2 = 0; var2 < this.z.length; var2++) {
         if (var1 == this.z[var2] || var1 == this.A[var2]) {
            return this.B[var2];
         }
      }

      return "0000";
   }

   public final void d() {
      char[] var1 = this.d;
      if (b) {
         switch (c) {
            case 0:
               int var2 = var1.length;
               int var3;
               int var4 = (var3 = this.E - 10) - TextLayoutHelper.b;
               int var5 = 1;

               for (int var6 = 0; var6 < 2; var6++) {
                  if (this.h < var2) {
                     this.D = 0L;
                     if (this.w == 0) {
                        this.C = 0;
                     }

                     char var7;
                     if ((var7 = var1[this.w]) == '#') {
                        this.i += 7;
                        this.h += 7;
                     } else {
                        int var8 = TextLayoutHelper.a(var7);
                        int var9;
                        if ((var9 = this.C + var8) > var3 || var7 == ' ' && var9 > var4) {
                           var9 = 0;
                           this.C = 0;
                           if (var7 != ' ') {
                              var9 = var8 + 0;
                           }

                           if (++var5 > this.r) {
                              var5 = 0;
                              this.g = this.h;
                           }
                        }

                        this.C = var9;
                        this.w++;
                     }
                  } else if (this.D == 0L) {
                     this.D = System.currentTimeMillis() + 2500L;
                  } else if (System.currentTimeMillis() > this.D) {
                     if (!this.v) {
                        this.c();
                        b = false;
                        if (WorldManager.a().uiManager.isTopUI("/data/ui/dialog.ui")) {
                           WorldManager.a().interactionController.aC();
                        }
                     }

                     a = true;
                     this.s++;
                  }
               }

               return;
            case 1:
               a = true;
               this.s++;
               return;
            case 2:
               this.n = this.n + this.m;
               if (this.n > EngineUtils.a.length * (BaseScreen.getScreenHeight() + BaseScreen.getScreenHeight() / 2)) {
                  this.n = 0;
                  a = true;
                  b = false;
                  this.s++;
                  return;
               }
               break;
            case 3:
               if (this.s < ((int[])null).length) {
                  this.w++;
                  if (this.w >= 20) {
                     this.s++;
                     if (this.s > this.r * (this.y + 1)) {
                        this.y++;
                        this.j[0] = this.j[this.l];
                     }

                     this.w = 0;
                  }
               }
         }
      }
   }

   public final void a(boolean var1) {
      this.v = true;
   }

   public final boolean e() {
      return this.v;
   }
}
