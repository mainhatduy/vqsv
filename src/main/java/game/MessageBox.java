package game;

import javax.microedition.lcdui.Graphics;

public final class MessageBox implements UIComponent {
   private int f;
   private int g;
   private int h;
   private int i;
   private int j;
   private int k;
   private int l;
   private int m;
   private int n;
   private int o;
   private int p;
   private int q;
   private int r;
   private int s;
   private int t;
   private int u;
   private int v;
   private int w;
   private int x;
   public int a;
   public SpriteWidget b;
   public SpriteWidget c;
   public boolean d;
   private boolean y = true;
   private int z;
   private int A;
   private int B;
   UIHotspot[] e;

   public final void a(int var1) {
      this.h = var1;
   }

   public final void b(int var1) {
      this.i = var1;
   }

   public final void c(int var1) {
      this.j = var1;
   }

   public final void d(int var1) {
      this.k = var1;
   }

   public final int l() {
      return this.l;
   }

   public final void e(int var1) {
      this.l = var1;
   }

   public final int m() {
      return this.m;
   }

   public final void f(int var1) {
      this.m = var1;
   }

   public final void g(int var1) {
      if (var1 > 0 && var1 <= this.l) {
         this.p = var1;
      } else {
         this.p = this.l;
      }
   }

   public final void h(int var1) {
      if (var1 > 0 && var1 <= this.m) {
         this.r = var1;
      } else {
         this.r = this.m;
      }
   }

   public final void i(int var1) {
      this.q = var1;
   }

   public final void j(int var1) {
      this.s = var1;
   }

   private int o() {
      return this.s + this.r - 1;
   }

   private int p() {
      return this.q + this.p - 1;
   }

   public final void k(int var1) {
      this.n = var1;
   }

   public final void l(int var1) {
      this.o = var1;
   }

   public final void m(int var1) {
      this.t = var1;
   }

   public final void n(int var1) {
      this.v = var1;
   }

   public final void o(int var1) {
      this.u = var1;
   }

   public final void p(int var1) {
      this.w = var1;
   }

   public final UIHotspot[] n() {
      int var3 = this.m;
      int var2 = this.l;
      UIHotspot[] var5 = new UIHotspot[this.l * var3];

      for (int var6 = 0; var6 != var2 * var3; var6++) {
         UIHotspot var7 = new UIHotspot(var6 - var2, var6 + var2, var6 - 1, var6 + 1);
         var5[var6] = var7;
      }

      UIHotspot[] var1 = var5;
      int var23 = this.m;
      int var4 = this.l;
      UIHotspot[] var20 = var1;
      MessageBox var18 = this;

      for (int var25 = 0; var25 != var23; var25++) {
         UIHotspot var27 = new UIHotspot((byte)0);
         UIHotspot var8 = new UIHotspot((byte)0);
         int var9 = -1;
         int var10 = -1;

         for (int var11 = 0; var11 != var4; var11++) {
            if (var27.a() == 0 && var20[var25 * var4 + var11].a() == 1) {
               var27 = var20[var25 * var4 + var11];
               var9 = var25 * var4 + var11;
            }

            if (var8.a() == 0 && var20[(var25 + 1) * var4 - var11 - 1].a() == 1) {
               var8 = var20[(var25 + 1) * var4 - var11 - 1];
               var10 = (var25 + 1) * var4 - var11 - 1;
            }
         }

         if (var27.a() == 1) {
            if (var18.t == 0) {
               var27.c(var9);
               var8.d(var10);
            } else {
               int var12 = var9;
               int var40 = var23;
               var9 = var4;
               UIHotspot[] var28 = var20;
               int var14 = -1;

               for (int var15 = 0; var15 != var28.length; var15++) {
                  int var16;
                  if ((var16 = var12 - var15 - 1) < 0) {
                     var16 += var9 * var40;
                  }

                  if (var14 == -1 && var28[var16].a() == 1) {
                     var14 = var16;
                  }
               }

               var27.c(var14);
               var12 = var10;
               var40 = var23;
               var9 = var4;
               UIHotspot[] var29 = var20;
               int var49 = -1;

               for (int var52 = 0; var52 != var29.length; var52++) {
                  int var55;
                  if ((var55 = var12 + var52 + 1) >= var9 * var40) {
                     var55 -= var9 * var40;
                  }

                  if (var49 == -1 && var29[var55].a() == 1) {
                     var49 = var55;
                  }
               }

               var8.d(var49);
            }
         }
      }

      int var24 = this.m;
      var4 = this.l;
      UIHotspot[] var21 = var1;
      MessageBox var19 = this;

      for (int var26 = 0; var26 != var4; var26++) {
         UIHotspot var30 = new UIHotspot((byte)0);
         UIHotspot var33 = new UIHotspot((byte)0);
         int var36 = -1;
         int var39 = -1;

         for (int var42 = 0; var42 != var24; var42++) {
            if (var30.a() == 0 && var21[var26 + var4 * var42].a() == 1) {
               var30 = var21[var26 + var4 * var42];
               var36 = var26 + var4 * var42;
            }

            if (var33.a() == 0 && var21[var26 + var4 * (var24 - var42 - 1)].a() == 1) {
               var33 = var21[var26 + var4 * (var24 - var42 - 1)];
               var39 = var26 + var4 * (var24 - var42 - 1);
            }
         }

         if (var30.a() == 1) {
            if (var19.v == 0) {
               var30.a(var36);
               var33.b(var39);
            } else {
               int var13 = var36;
               int var46 = var24;
               int var43 = var4;
               UIHotspot[] var37 = var21;
               MessageBox var31 = var19;
               int var50 = -1;
               int var53 = var13 % var43;

               for (int var56 = 0; var56 != var46; var56++) {
                  int var17;
                  if ((var17 = var13 / var43 - 1 - var56) < 0) {
                     var17 += var46;
                  }

                  if (var50 == -1 && var37[var17 * var31.l + var53].a() == 1) {
                     var50 = var17 * var31.l + var53;
                  }
               }

               var30.a(var50);
               var13 = var39;
               var46 = var24;
               var43 = var4;
               UIHotspot[] var38 = var21;
               MessageBox var32 = var19;
               int var51 = -1;
               var53 = var13 % var43;

               for (int var57 = 0; var57 != var46; var57++) {
                  int var58;
                  if ((var58 = var13 / var43 + 1 + var57) >= var46) {
                     var58 -= var46;
                  }

                  if (var51 == -1 && var38[var58 * var32.l + var53].a() == 1) {
                     var51 = var58 * var32.l + var53;
                  }
               }

               var33.b(var51);
            }
         }
      }

      return var1;
   }

   public final boolean a(byte var1) {
      boolean var2 = false;
      int var3 = this.x;
      if (var1 == 0) {
         if (this.e[this.x].a() == 1 && var3 != this.e[this.x].b()) {
            var3 = this.e[this.x].b();
            var2 = true;
         }
      } else if (var1 == 1) {
         if (this.e[this.x].a() == 1 && var3 != this.e[this.x].c()) {
            var3 = this.e[this.x].c();
            var2 = true;
         }
      } else if (var1 == 2) {
         if (this.e[this.x].a() == 1 && var3 != this.e[this.x].d()) {
            var3 = this.e[this.x].d();
            var2 = true;
         }
      } else if (var1 == 3 && this.e[this.x].a() == 1 && var3 != this.e[this.x].e()) {
         var3 = this.e[this.x].e();
         var2 = true;
      }

      this.r(var3);
      return var2;
   }

   public MessageBox() {
      this.f = this.g = 0;
      this.h = this.i = this.j = this.k = this.l = this.m = 0;
      this.n = this.o = 0;
      this.p = this.q = 0;
      this.r = this.s = 0;
      this.A = 2;
      this.z = this.B = -1;
      this.x = 0;
      this.e = new UIHotspot[0];
      this.c = null;
      this.d = false;
      this.y = true;
   }

   public final void render(Graphics var1, boolean var2, boolean var3, UIComponent var4, int[] var5) {
      if (this.y) {
         if (var1 != null) {
            var1.setColor(this.a);
            var1.fillRect(this.f, this.g, this.getWidth(), this.getHeight());
            Rectangle var8 = new Rectangle(this.f, this.g, this.getWidth(), this.getHeight());
            if (this.b != null && var1 != null) {
               this.b.a(var1, var8, 0);
            }

            int endRow = this.o();
            int var12 = this.p();

            for (int var13 = this.s; var13 <= endRow; var13++) {
               for (int var6 = this.q; var6 <= var12; var6++) {
                  Rectangle var9 = new Rectangle(
                     this.f + this.j + (var6 - this.q) * (this.j + this.h), this.g + this.k + (var13 - this.s) * (this.k + this.i), this.h, this.i
                  );
                  SpriteWidget var7;
                  if ((var7 = this.e[var13 * this.l + var6].a) != null && var1 != null) {
                     var7.a(var1, var9, 0);
                  }
               }
            }

            Rectangle var10 = new Rectangle(
               this.f + this.j + (this.x % this.l - this.q) * (this.j + this.h) + this.n,
               this.g + this.k + (this.x / this.l - this.s) * (this.k + this.i) + this.o,
               this.getWidth(),
               this.getHeight()
            );
            if (this.d && this.c != null && var1 != null) {
               this.c.a(var1, var10, 0);
            }
         }
      }
   }

   public final void a(boolean var1, boolean var2, UIComponent var3, int[] var4) {
      if (this.b != null) {
         this.b.c();
      }

      for (int var5 = 0; var5 < this.e.length; var5++) {
         if (this.e[var5].a != null) {
            this.e[var5].a.c();
         }
      }

      if (this.d && this.c != null) {
         this.c.c();
      }
   }

   public final int getId() {
      return this.z;
   }

   public final void q(int var1) {
      this.z = var1;
   }

   public final int getX() {
      return this.f;
   }

   public final void setX(int var1, UIComponent var2) {
      this.f = var1;
   }

   public final int getY() {
      return this.g;
   }

   public final void setY(int var1, UIComponent var2) {
      this.g = var1;
   }

   public final int getWidth() {
      return this.p > 0 && this.p <= this.l ? (this.h + this.j) * this.p + this.j : (this.h + this.j) * this.l + this.j;
   }

   public final void setWidth(int var1, UIComponent var2) {
   }

   public final int getHeight() {
      return this.r > 0 && this.r <= this.m ? (this.i + this.k) * this.r + this.k : (this.i + this.k) * this.m + this.k;
   }

   public final void setHeight(int var1, UIComponent var2) {
   }

   public final void r(int var1) {
      if (this.e != null && var1 > -2 && var1 < this.e.length) {
         this.x = var1;
         MessageBox var4 = this;
         MessageBox var2 = this;
         int var3 = this.x / var2.l;
         if (var2.w == 1) {
            var2.s = var3 / var2.r * var2.r;
         } else if (var3 < var2.s) {
            var2.s = var3;
         } else if (var3 > var2.o()) {
            MessageBox var5;
            (var5 = var2).s = var3 - var5.r + 1;
         }

         var2 = var4;
         var3 = var4.x % var2.l;
         if (var2.u == 1) {
            var2.q = var3 / var2.p * var2.p;
            return;
         }

         if (var3 < var2.q) {
            var2.q = var3;
            return;
         }

         if (var3 > var2.p()) {
            MessageBox var7;
            (var7 = var2).q = var3 - var7.p + 1;
         }
      }
   }

   public final UISelectionConfig getSelection() {
      return null;
   }

   public final UIComponent[] getChildren() {
      return null;
   }

   public final UIStyle getStyle() {
      return null;
   }

   public final void setStyle(UIStyle var1) {
   }

   public final int getType() {
      return this.A;
   }

   public final int getParentId() {
      return this.B;
   }

   public final void s(int var1) {
      this.B = var1;
   }

   public final void updateLayout(UIComponent var1) {
   }

   public final void release() {
      if (this.c != null) {
         this.c.d();
         this.c = null;
      }

      if (this.b != null) {
         this.b.d();
         this.b = null;
      }

      if (this.e != null) {
         for (int var1 = 0; var1 < this.e.length; var1++) {
            if (this.e[var1] != null) {
               this.e[var1].a.d();
               this.e[var1] = null;
            }
         }

         this.e = null;
      }
   }

   public final void setVisible(boolean var1) {
      this.y = var1;
   }
}
