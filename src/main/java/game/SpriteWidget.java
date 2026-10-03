package game;

import javax.microedition.lcdui.Graphics;

public final class SpriteWidget {
   private SpriteRenderer b = null;
   private byte c = 0;
   private short d = -1;
   public byte a = 4;

   public final void a(int var1, boolean var2, byte var3) {
      if (var1 != -1) {
         this.b = new SpriteRenderer();
         this.b.loadSprite(var1, var2);
         switch (this.a) {
            case 3:
               this.b.setAnimation((byte)this.d, var3, true);
         }
      }
   }

   public final void a(byte var1, byte var2) {
      this.d = var1;
      this.b.setAnimation(var1, var2, true);
   }

   public final SpriteRenderer a() {
      return this.b;
   }

   public final void a(int var1) {
      this.d = (short)var1;
   }

   public final void b() {
      this.c = 1;
   }

   public SpriteWidget() {
      this.b = null;
      this.d = -1;
      this.a = 4;
   }

   public final void a(Graphics var1, Rectangle var2, int var3) {
      if (this.b != null) {
         boolean var4 = false;
         SpriteWidget var7 = this;
         Rectangle var10000;
         if (this.d == -1) {
            var10000 = null;
         } else {
            int[] var5 = new int[4];
            switch (var7.a) {
               case 2:
                  var5 = var7.b.getFrameBounds(var7.d, (byte)0);
                  break;
               case 3:
                  var5 = var7.b.getAnimationBounds(var7.d, (byte)0);
            }

            var10000 = new Rectangle(var5[0], var5[1], var5[2], var5[3]);
         }

         Rectangle var8 = var10000;
         if (var10000 == null) {
            return;
         }

         int var9 = var2.x;
         int var6 = var2.y;
         switch (var3) {
            case 0:
               var9 = var2.x - var8.x;
               var6 = var2.y - var8.y;
               break;
            case 1:
               var9 = var2.x + (var2.width - var8.width) / 2 - var8.x;
               var6 = var2.y - var8.y;
               break;
            case 2:
               var9 = var2.x + (var2.width - var8.width) - var8.x;
               var6 = var2.y - var8.y;
               break;
            case 3:
               var9 = var2.x - var8.x;
               var6 = var2.y + (var2.height - var8.height) / 2 - var8.y;
               break;
            case 4:
               var9 = var2.x + (var2.width - var8.width) / 2 - var8.x;
               var6 = var2.y + (var2.height - var8.height) / 2 - var8.y;
               break;
            case 5:
               var9 = var2.x + (var2.width - var8.width) - var8.x;
               var6 = var2.y + (var2.height - var8.height) / 2 - var8.y;
               break;
            case 6:
               var9 = var2.x - var8.x;
               var6 = var2.y + (var2.height - var8.height) - var8.y;
               break;
            case 7:
               var9 = var2.x + (var2.width - var8.width) / 2 - var8.x;
               var6 = var2.y + (var2.height - var8.height) - var8.y;
               break;
            case 8:
               var9 = var2.x + (var2.width - var8.width) - var8.x;
               var6 = var2.y + (var2.height - var8.height) - var8.y;
         }

         if (this.a == 3) {
            this.b.drawCurrentFrame(var1, var9, var6, this.c);
            return;
         }

         if (this.a == 2) {
            this.b.drawFrame(var1, this.d, var9, var6, (byte)0);
         }
      }
   }

   public final void c() {
      if (this.a == 3) {
         this.b.advanceAnimation();
      }
   }

   public final void d() {
      if (this.b != null) {
         this.b.releaseSprite();
         this.b = null;
      }
   }
}
