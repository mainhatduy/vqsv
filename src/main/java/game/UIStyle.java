package game;

import javax.microedition.lcdui.Font;
import javax.microedition.lcdui.Graphics;

public final class UIStyle {
   public String text;
   private int[] n;
   private int o;
   public int b;
   public int c;
   public boolean d;
   public int e;
   public int f;
   public int g;
   private boolean p = false;
   private int q = 0;
   public byte h = -1;
   public SpriteWidget i;
   public int j;
   public int k;
   public int l;
   public SpriteWidget m;
   private Rectangle r = null;
   private String s = "";
   private Font t = Font.getFont(0, 0, 8);
   private boolean[] u = new boolean[]{false, false};

   public UIStyle() {
      this.text = "";
      this.n = new int[2];
      this.n[1] = this.n[0] = 0;
      this.o = 2;
      this.b = 4;
      this.c = 4;
      this.d = false;
      this.e = 16777215;
      this.f = 16777215;
      this.g = 16777215;
      this.i = null;
      this.j = 16777215;
      this.k = 16777215;
      this.l = 16777215;
      this.m = null;
      this.p = false;
      this.h = -1;
      this.q = 0;
   }

   private static void a(Graphics var0, Rectangle var1, int var2) {
      if (var0 != null && var2 >> 24 != 0) {
         var0.setColor(var2);
         var0.fillRect(var1.x, var1.y, var1.width, var1.height);
      }
   }

   public final void a(Rectangle var1) {
      this.r = var1;
   }

   public final void a() {
      this.n[1] = -this.r.height;
      this.n[0] = -this.r.width / 2;
      this.u[0] = this.u[1] = false;
   }

   public final boolean b() {
      return this.u[0] && this.u[1];
   }

   private void a(Graphics var1, Rectangle var2, String var3, int var4, int var5, boolean var6, byte var7, TextPainter var8, byte var9) {
      if (var1 != null && var4 >> 24 != 0) {
         if (var3.startsWith("#P") && var3.length() > 2) {
            int var10 = Integer.parseInt(var3.substring(2).trim()) * var2.width / 100;
            var1.setColor(var4);
            var1.fillRect(var2.x + 1, var2.y + 1, var10 - 1, var2.height - 1);
         } else {
            if (!this.s.equals(var3)) {
               this.n[1] = -var2.height;
               this.n[0] = -var2.width / 2;
            }

            this.s = var3;
            EngineUtils.a(var1, var3, var4, var2.x, var2.y, this.t.getHeight(), var2.width, var2.height, this.t, var6, var5, this.n, this.o, var7, var8, this.u);
         }
      }
   }

   private static void b(Graphics var0, Rectangle var1, int var2) {
      if (var0 != null && var2 >> 24 != 0) {
         var0.setColor(var2);
         var0.drawRect(var1.x, var1.y, var1.width, var1.height);
      }
   }

   public final void a(Graphics var1, int var2, int var3, int var4, int var5, boolean var6, byte var7, byte var8, TextPainter var9) {
      if (this.p != var6 || this.q == 0) {
         this.q = -1;
         this.p = var6;
      }

      this.r = new Rectangle(var2, var3, var4, var5);
      var1.setClip(0, 0, BaseScreen.getScreenWidth(), BaseScreen.getScreenHeight());
      if (var6) {
         a(var1, this.r, this.e);
         b(var1, this.r, this.f);
         if (this.i != null) {
            this.i.a(var1, this.r, this.c);
         }

         this.a(var1, this.r, this.text, this.g, this.b, this.d, var7, var9, var8);
      } else {
         a(var1, this.r, this.j);
         b(var1, this.r, this.k);
         if (this.m != null) {
            this.m.a(var1, this.r, this.c);
         }

         this.a(var1, this.r, this.text, this.l, this.b, this.d, var7, var9, var8);
      }
   }

   public final void c() {
      if (this.i != null) {
         this.i.d();
         this.i = null;
      }

      if (this.m != null) {
         this.m.d();
         this.m = null;
      }
   }
}
