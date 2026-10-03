package game;

import javax.microedition.lcdui.Graphics;

public final class SkillEffect extends BaseEntity {
   private PixelBuffer[] b;
   private short[] t;
   private byte u;
   private int v = 0;
   private int w = 0;
   private int[] x = new int[]{262, 263, 264, 265, 266, 267, 268, 299, 300, 301, 304, 306, 307, 308, 309};
   public SpriteRenderer a = new SpriteRenderer();

   public final void a(short[] var1) {
      this.u = (byte)var1[0];
      switch (this.u) {
         case 0:
            this.t = new short[3];
            System.arraycopy(var1, 0, this.t, 0, this.t.length);
            this.setPosition(var1[3], var1[4]);
            this.b = new PixelBuffer[3];
            SpriteRenderer var16 = new SpriteRenderer();

            for (int var27 = 0; var27 < 2; var27++) {
               var16.loadSprite(var1[5 + var27 * 3], false);
               int[] var35 = var16.getFrameBounds(var1[6 + var27 * 3], (byte)var1[7 + var27 * 3]);
               this.b[var27] = new PixelBuffer();
               this.b[var27] = ImageTransformer.a(var16, var1[6 + var27 * 3], var35, (byte)var1[7 + var27 * 3], this.b[var27]);
               var16.releaseSprite();
            }

            this.b[2] = this.b[0].a();
            return;
         case 1:
            this.t = new short[var1.length - 6];
            this.facingDirection = (byte)var1[5];
            System.arraycopy(var1, 6, this.t, 0, this.t.length);
            this.setPosition(var1[1], var1[2]);
            this.b = new PixelBuffer[3];
            SpriteRenderer var15;
            (var15 = new SpriteRenderer()).loadSprite(var1[3], false);
            int[] var26 = var15.getFrameBounds(var1[4], (byte)var1[5]);
            this.b[0] = new PixelBuffer();
            this.b[0] = ImageTransformer.a(var15, var1[4], var26, (byte)var1[5], this.b[0]);
            var15.releaseSprite();
            this.b[1] = new PixelBuffer();
            this.b[1].a(GameDatabase.texturePixels[this.t[2]], 16, 16);
            this.b[2] = this.b[0].a();
            return;
         case 2:
            return;
         case 3:
            return;
         case 4:
            return;
         case 5:
            return;
         case 6:
            return;
         case 7:
            this.t = new short[var1.length - 6];
            this.facingDirection = (byte)var1[5];
            System.arraycopy(var1, 6, this.t, 0, this.t.length);
            this.setPosition(var1[1], var1[2]);
            this.b = new PixelBuffer[2];
            SpriteRenderer var14;
            (var14 = new SpriteRenderer()).loadSprite(var1[3], false);
            int[] var25 = var14.getFrameBounds(var1[4], (byte)var1[5]);
            this.b[0] = new PixelBuffer();
            this.b[0] = ImageTransformer.a(var14, var1[4], var25, (byte)var1[5], this.b[0]);
            this.b[1] = this.b[0].a();
            int var34 = var25[2] * var1[9] / var1[10];
            int var5 = var25[3] * var1[11] / var1[12];
            this.v = (var25[2] - var34) / 2;
            this.w = var25[3] - var5;
            this.b[1] = ImageTransformer.a(this.b[1], var34, var5);
            var14.releaseSprite();
            return;
         case 8:
            this.t = new short[var1.length - 6];
            this.facingDirection = (byte)var1[5];
            System.arraycopy(var1, 6, this.t, 0, this.t.length);
            this.setPosition(var1[1], var1[2]);
            this.b = new PixelBuffer[2];
            SpriteRenderer var13;
            (var13 = new SpriteRenderer()).loadSprite(var1[3], false);
            int[] var24 = var13.getFrameBounds(var1[4], (byte)var1[5]);
            this.b[0] = new PixelBuffer();
            this.b[0] = ImageTransformer.a(var13, var1[4], var24, (byte)var1[5], this.b[0]);
            var13.releaseSprite();
            this.b[1] = this.b[0].a();
            if (this.t[4] == 1) {
               this.b[1] = ImageTransformer.b(ImageTransformer.a(this.b[1], this.t[2]), 1, 50);
               this.b[1].d = this.b[1].d + this.t[3];
               this.b[1].e = this.b[1].e + this.t[4];
            }

            return;
         case 9:
            this.t = new short[var1.length - 10];
            this.facingDirection = (byte)var1[5];
            System.arraycopy(var1, 10, this.t, 0, this.t.length);
            this.setPosition(var1[1], var1[2]);
            this.b = new PixelBuffer[2];
            SpriteRenderer var12;
            (var12 = new SpriteRenderer()).loadSprite(var1[3], false);
            int[] var23 = var12.getFrameBounds(var1[4], (byte)var1[5]);
            this.b[0] = new PixelBuffer();
            this.b[0] = ImageTransformer.a(var12, var1[4], var23, (byte)var1[5], this.b[0]);
            this.b[1] = this.b[0].a();
            this.b[1] = ImageTransformer.a(this.b[1], var1[6], var1[7], var1[8], var1[9]);
            this.b[1] = ImageTransformer.b(this.b[1], 1, 50);
            var12.releaseSprite();
            return;
         case 10:
            this.t = new short[var1.length - 7];
            this.facingDirection = (byte)var1[5];
            System.arraycopy(var1, 7, this.t, 0, this.t.length);
            this.setPosition(var1[1], var1[2]);
            this.b = new PixelBuffer[2];
            SpriteRenderer var11;
            (var11 = new SpriteRenderer()).loadSprite(var1[3], false);
            int[] var22 = var11.getFrameBounds(var1[4], (byte)var1[5]);
            this.b[0] = new PixelBuffer();
            this.b[0] = ImageTransformer.a(var11, var1[4], var22, (byte)var1[5], this.b[0]);
            this.b[1] = this.b[0].a();
            this.b[1] = ImageTransformer.b(this.b[1], var1[6]);
            var11.releaseSprite();
            return;
         case 11:
         case 14:
            this.t = new short[var1.length - 7 - (var1[6] - 1 << 2)];
            this.facingDirection = (byte)var1[5];
            System.arraycopy(var1, 7 + (var1[6] - 1 << 2), this.t, 0, this.t.length);
            this.setPosition(var1[1], var1[2]);
            this.b = new PixelBuffer[var1[6]];
            SpriteRenderer var10;
            (var10 = new SpriteRenderer()).loadSprite(var1[3], false);
            int[] var21 = var10.getFrameBounds(var1[4], (byte)var1[5]);
            this.b[0] = new PixelBuffer();
            this.b[0] = ImageTransformer.a(var10, var1[4], var21, (byte)var1[5], this.b[0]);
            if (var1[0] == 11) {
               for (int var32 = 1; var32 < this.b.length; var32++) {
                  this.b[var32] = this.b[0].a();
                  this.b[var32] = ImageTransformer.a(
                     this.b[var32], var1[7 + (var32 - 1 << 2)], var1[8 + (var32 - 1 << 2)], var1[9 + (var32 - 1 << 2)], var1[10 + (var32 - 1 << 2)]
                  );
               }
            } else {
               for (int var33 = 1; var33 < this.b.length; var33++) {
                  this.b[var33] = this.b[0].a();
                  this.b[var33] = ImageTransformer.b(this.b[var33], var1[7 + (var33 - 1 << 2)], var1[8 + (var33 - 1 << 2)]);
               }
            }

            var10.releaseSprite();
            return;
         case 12:
            this.facingDirection = (byte)var1[5];
            this.t = new short[var1.length - 9];
            System.arraycopy(var1, 9, this.t, 0, this.t.length);
            this.setPosition(var1[1], var1[2]);
            this.b = new PixelBuffer[var1[6]];
            SpriteRenderer var9;
            (var9 = new SpriteRenderer()).loadSprite(var1[3], false);
            int[] var20 = var9.getFrameBounds(var1[4], (byte)var1[5]);
            this.b[0] = new PixelBuffer();
            this.b[0] = ImageTransformer.a(var9, var1[4], var20, (byte)var1[5], this.b[0]);

            for (int var30 = 1; var30 < this.b.length; var30++) {
               this.b[var30] = this.b[0].a();
            }

            for (int var31 = 0; var31 < this.b.length; var31++) {
               this.b[var31] = ImageTransformer.b(this.b[var31], var1[var31 + 7]);
            }

            var9.releaseSprite();
            return;
         case 13:
            this.facingDirection = (byte)var1[5];
            this.t = new short[var1.length - 7 - var1[6]];
            System.arraycopy(var1, 7 + var1[6], this.t, 0, this.t.length);
            this.setPosition(var1[1], var1[2]);
            this.b = new PixelBuffer[var1[6]];
            SpriteRenderer var8;
            (var8 = new SpriteRenderer()).loadSprite(var1[3], false);
            int[] var19 = var8.getFrameBounds(var1[4], (byte)var1[5]);
            this.b[0] = new PixelBuffer();
            this.b[0] = ImageTransformer.a(var8, var1[4], var19, (byte)var1[5], this.b[0]);

            for (int var28 = 1; var28 < this.b.length; var28++) {
               this.b[var28] = this.b[0].a();
            }

            for (int var29 = 0; var29 < this.b.length; var29++) {
               this.b[var29] = ImageTransformer.b(this.b[var29], var1[var29 + 7]);
            }

            return;
         case 15:
            this.facingDirection = (byte)var1[5];
            this.t = new short[var1.length - 7 - (var1[6] - 1 << 2)];
            System.arraycopy(var1, 7 + (var1[6] - 1 << 2), this.t, 0, this.t.length);
            this.setPosition(var1[1], var1[2]);
            this.b = new PixelBuffer[var1[6]];
            SpriteRenderer var7;
            (var7 = new SpriteRenderer()).loadSprite(var1[3], false);
            int[] var18 = var7.getFrameBounds(var1[4], (byte)var1[5]);
            this.b[0] = new PixelBuffer();
            this.b[0] = ImageTransformer.a(var7, var1[4], var18, (byte)var1[5], this.b[0]);

            for (int var4 = 1; var4 < this.b.length; var4++) {
               this.b[var4] = this.b[0].a();
               this.b[var4] = ImageTransformer.a(
                  this.b[var4], var1[7 + (var4 - 1 << 2)], var1[8 + (var4 - 1 << 2)], var1[9 + (var4 - 1 << 2)], var1[10 + (var4 - 1 << 2)]
               );
            }

            var7.releaseSprite();
            return;
         case 16:
            this.t = new short[var1.length - 6];
            this.facingDirection = (byte)var1[5];
            System.arraycopy(var1, 6, this.t, 0, this.t.length);
            this.setPosition(var1[1], var1[2]);
            this.b = new PixelBuffer[1];
            SpriteRenderer var6;
            (var6 = new SpriteRenderer()).loadSprite(var1[3], false);
            int[] var17 = var6.getFrameBounds(var1[4], (byte)var1[5]);
            this.b[0] = new PixelBuffer();
            this.b[0] = ImageTransformer.a(var6, var1[4], var17, (byte)var1[5], this.b[0]);
            this.t[1] = (short)(this.b[0].c / this.t[2]);
            var6.releaseSprite();
            return;
         case 17:
            this.t = new short[var1.length - 11];
            this.facingDirection = (byte)var1[5];
            System.arraycopy(var1, 11, this.t, 0, this.t.length);
            this.setPosition(var1[1], var1[2]);
            this.b = new PixelBuffer[2];
            SpriteRenderer var2;
            (var2 = new SpriteRenderer()).loadSprite(var1[3], false);
            int[] var3 = var2.getFrameBounds(var1[4], (byte)var1[5]);
            this.b[0] = new PixelBuffer();
            this.b[0] = ImageTransformer.a(var2, var1[4], var3, (byte)var1[5], this.b[0]);
            this.b[0] = ImageTransformer.a(this.b[0], var1[10]);
            this.b[1] = this.b[0].a();
            this.b[1] = ImageTransformer.a(this.b[1], var1[6], var1[7], var1[8], var1[9]);
            var2.releaseSprite();
            return;
         default:
            this.facingDirection = (byte)var1[2];
            this.a.loadSprite(this.x[this.u - 20], false);
            this.a.setAnimation((byte)var1[1], (byte)0, true);
      }
   }

   private void f() {
      if (this.b != null) {
         for (int var1 = 0; var1 < this.b.length; var1++) {
            this.b[var1].a = null;
            this.b[var1] = null;
         }

         this.b = null;
      }

      if (this.t != null) {
         this.t = null;
      }
   }

   public final void a() {
      this.a(true);
      this.setVisible(true);
   }

   public final void b() {
      this.a(false);
      this.setVisible(false);
   }

   public final boolean c() {
      return this.u == 8 && this.e;
   }

   public final boolean d() {
      return this.a.isLastAnimationStep();
   }

   public final boolean a(int var1) {
      return this.a.isAnimationStep(var1);
   }

   public final boolean e() {
      if (!this.e) {
         return false;
      }

      switch (this.u) {
         case 0:
            if (this.t[1] < this.t[2] / 5) {
               this.b[2] = this.b[0].a();
               if (this.t[1] % 2 == 1) {
                  this.b[2] = ImageTransformer.b(ImageTransformer.a(this.b[2], 6), 5, 1);
               } else {
                  this.b[2] = ImageTransformer.b(this.b[2], 2, 1);
               }
            } else if (this.t[1] >= (this.t[2] << 2) / 5) {
               this.b[2] = this.b[1].a();
               if (this.t[1] % 2 == 1) {
                  this.b[2] = ImageTransformer.b(ImageTransformer.a(this.b[2], 6), 5, 1);
               } else {
                  this.b[2] = ImageTransformer.b(this.b[2], 2, 1);
               }
            } else {
               if (this.t[1] % 4 != 1 && this.t[1] % 4 != 2) {
                  this.b[2] = this.b[1].a();
               } else {
                  this.b[2] = this.b[0].a();
               }

               if (this.t[1] % 2 == 1) {
                  this.b[2] = ImageTransformer.b(ImageTransformer.a(this.b[2], 8), 8, 1);
               } else {
                  this.b[2] = ImageTransformer.b(ImageTransformer.a(this.b[2], 4), 4, 1);
               }
            }

            if (this.t[1] >= this.t[2]) {
               this.b();
               this.f();
               return false;
            }

            this.t[1]++;
            break;
         case 1:
            switch (this.t[4]) {
               case 0:
                  int[] var6 = new int[4];

                  for (int var9 = 0; var9 < this.b[1].b; var9++) {
                     for (int var18 = 0; var18 < 4; var18++) {
                        var6[var18] = this.b[1].a[var9 + var18 * this.b[1].b];
                     }

                     for (int var19 = 0; var19 < this.b[1].c - 4; var19++) {
                        this.b[1].a[var9 + var19 * this.b[1].b] = this.b[1].a[var9 + (var19 + 4) * this.b[1].b];
                     }

                     for (int var20 = 0; var20 < 4; var20++) {
                        this.b[1].a[var9 + (var20 + this.b[1].c - 4) * this.b[1].b] = var6[var20];
                     }
                  }
                  break;
               case 1:
                  int[] var5 = new int[4];

                  for (int var8 = 0; var8 < this.b[1].b; var8++) {
                     for (int var15 = 0; var15 < 4; var15++) {
                        var5[var15] = this.b[1].a[var8 + (this.b[1].c - 4 + var15) * this.b[1].b];
                     }

                     for (int var16 = this.b[1].c - 1; var16 > 3; var16--) {
                        this.b[1].a[var8 + var16 * this.b[1].b] = this.b[1].a[var8 + (var16 - 4) * this.b[1].b];
                     }

                     for (int var17 = 0; var17 < 4; var17++) {
                        this.b[1].a[var8 + var17 * this.b[1].b] = var5[var17];
                     }
                  }
                  break;
               case 2:
                  int[] var4 = new int[4];

                  for (int var7 = 0; var7 < this.b[1].c; var7++) {
                     for (int var12 = 0; var12 < 4; var12++) {
                        var4[var12] = this.b[1].a[var7 * this.b[1].c + var12];
                     }

                     for (int var13 = 0; var13 < this.b[1].b - 4; var13++) {
                        this.b[1].a[var7 * this.b[1].c + var13] = this.b[1].a[var7 * this.b[1].c + var13 + 4];
                     }

                     for (int var14 = 0; var14 < 4; var14++) {
                        this.b[1].a[var7 * this.b[1].c + var14 + this.b[1].b - 4] = var4[var14];
                     }
                  }
                  break;
               case 3:
                  int[] var1 = new int[4];

                  for (int var2 = 0; var2 < this.b[1].c; var2++) {
                     for (int var3 = 0; var3 < 4; var3++) {
                        var1[var3] = this.b[1].a[var2 * this.b[1].c + this.b[1].b - 4 + var3];
                     }

                     for (int var10 = this.b[1].b - 1; var10 > 3; var10--) {
                        this.b[1].a[var2 * this.b[1].c + var10] = this.b[1].a[var2 * this.b[1].c + var10 - 4];
                     }

                     for (int var11 = 0; var11 < 4; var11++) {
                        this.b[1].a[var2 * this.b[1].c + var11] = var1[var11];
                     }
                  }
            }

            this.b[2] = this.b[0].a();
            this.b[2] = ImageTransformer.a(this.b[2], this.b[1], (byte)this.t[3]);
            if (this.t[0] >= this.t[1]) {
               this.b();
               this.f();
               return false;
            }

            this.t[0]++;
         case 2:
         case 3:
         case 4:
         case 5:
         case 6:
            break;
         case 7:
         case 9:
         case 10:
         case 16:
         case 17:
            if (this.t[0] >= this.t[1]) {
               this.b();
               this.f();
               return false;
            }

            this.t[0]++;
            break;
         case 8:
            if (this.t[0] < this.t[1] / this.t[3] * this.t[2]) {
               if (this.t[4] == 1) {
                  this.b[1] = this.b[0].a();
               }

               this.b[1] = ImageTransformer.b(ImageTransformer.a(this.b[1], this.t[5 + (this.t[2] - 1) * 3]), 1, 50);
               this.b[1].d = this.b[1].d + this.t[6 + (this.t[2] - 1) * 3];
               this.b[1].e = this.b[1].e + this.t[7 + (this.t[2] - 1) * 3];
            } else {
               this.t[2]++;
            }

            if (this.t[0] >= this.t[1]) {
               this.b();
               this.f();
               return false;
            }

            this.t[0]++;
            break;
         case 11:
         case 12:
         case 13:
         case 14:
         case 15:
            if (this.t[2] < this.t[3]) {
               this.t[2]++;
            } else {
               this.t[2] = 0;
               this.t[0]++;
               if (this.t[0] >= this.t[1]) {
                  this.t[0] = 0;
                  this.b();
                  this.f();
                  return false;
               }
            }
            break;
         default:
            this.a.advanceAnimation();
      }

      return true;
   }

   public final void a(Graphics var1) {
      if (this.visible && this.g) {
         switch (this.u) {
            case 0:
               var1.drawRGB(this.b[2].a, 0, this.b[2].b, this.posX + this.b[2].d, this.posY + this.b[2].e, this.b[2].b, this.b[2].c, true);
               return;
            case 1:
               var1.drawRGB(this.b[2].a, 0, this.b[2].b, this.posX + this.b[2].d, this.posY + this.b[2].e, this.b[2].b, this.b[2].c, true);
               return;
            case 2:
               return;
            case 3:
               return;
            case 4:
               return;
            case 5:
               return;
            case 6:
               return;
            case 7:
               if (this.t[0] / this.t[2] % 2 == 0) {
                  var1.drawRGB(this.b[1].a, 0, this.b[1].b, this.posX + this.b[0].d + this.v, this.posY + this.b[0].e + this.w, this.b[1].b, this.b[1].c, true);
                  return;
               }

               var1.drawRGB(this.b[0].a, 0, this.b[0].b, this.posX + this.b[0].d, this.posY + this.b[0].e, this.b[0].b, this.b[0].c, true);
               return;
            case 8:
               var1.drawRGB(this.b[1].a, 0, this.b[1].b, this.posX + this.b[1].d, this.posY + this.b[1].e, this.b[1].b, this.b[1].c, true);
               return;
            case 9:
            case 10:
               var1.drawRGB(this.b[0].a, 0, this.b[0].b, this.posX + this.b[0].d, this.posY + this.b[0].e, this.b[0].b, this.b[0].c, true);
               if (this.t[0] / this.t[2] % 2 == 0) {
                  var1.drawRGB(this.b[1].a, 0, this.b[1].b, this.posX + this.b[1].d, this.posY + this.b[1].e, this.b[1].b, this.b[1].c, true);
                  return;
               }
               break;
            case 11:
            case 14:
               var1.setColor(16711935);

               for (int var6 = 1; var6 < this.b.length; var6++) {
                  if (this.facingDirection == 1) {
                     var1.drawRGB(
                        this.b[var6].a,
                        0,
                        this.b[var6].b,
                        this.posX + this.b[var6].d - this.t[4 + (this.t[0] * (this.b.length - 1) << 1) + (var6 - 1 << 1)],
                        this.posY + this.b[var6].e + this.t[4 + (this.t[0] * (this.b.length - 1) << 1) + (var6 - 1 << 1) + 1],
                        this.b[var6].b,
                        this.b[var6].c,
                        true
                     );
                  } else {
                     var1.drawRGB(
                        this.b[var6].a,
                        0,
                        this.b[var6].b,
                        this.posX + this.b[var6].d + this.t[4 + (this.t[0] * (this.b.length - 1) << 1) + (var6 - 1 << 1)],
                        this.posY + this.b[var6].e + this.t[4 + (this.t[0] * (this.b.length - 1) << 1) + (var6 - 1 << 1) + 1],
                        this.b[var6].b,
                        this.b[var6].c,
                        true
                     );
                  }
               }

               return;
            case 12:
               if (this.facingDirection == 1) {
                  var1.drawRGB(
                     this.b[1].a,
                     0,
                     this.b[1].b,
                     this.posX + this.b[1].d - (this.t[4 + (this.t[1] << 1) + (this.t[0] << 1)] + this.t[4 + (this.t[0] << 1)]),
                     this.posY + this.b[1].e - this.t[4 + (this.t[1] << 1) + (this.t[0] << 1) + 1] + this.t[4 + (this.t[0] << 1) + 1],
                     this.b[1].b,
                     this.b[1].c,
                     true
                  );
                  var1.drawRGB(
                     this.b[0].a,
                     0,
                     this.b[0].b,
                     this.posX + this.b[0].d - this.t[4 + (this.t[0] << 1)],
                     this.posY + this.b[0].e + this.t[4 + (this.t[0] << 1) + 1],
                     this.b[0].b,
                     this.b[0].c,
                     true
                  );
                  return;
               }

               var1.drawRGB(
                  this.b[1].a,
                  0,
                  this.b[1].b,
                  this.posX + this.b[1].d + this.t[4 + (this.t[1] << 1) + (this.t[0] << 1)] + this.t[4 + (this.t[0] << 1)],
                  this.posY + this.b[1].e - this.t[4 + (this.t[1] << 1) + (this.t[0] << 1) + 1] + this.t[4 + (this.t[0] << 1) + 1],
                  this.b[1].b,
                  this.b[1].c,
                  true
               );
               var1.drawRGB(
                  this.b[0].a,
                  0,
                  this.b[0].b,
                  this.posX + this.b[0].d + this.t[4 + (this.t[0] << 1)],
                  this.posY + this.b[0].e + this.t[4 + (this.t[0] << 1) + 1],
                  this.b[0].b,
                  this.b[0].c,
                  true
               );
               return;
            case 13:
               for (int var5 = 0; var5 < this.b.length; var5++) {
                  if (this.facingDirection == 1) {
                     var1.drawRGB(
                        this.b[var5].a,
                        0,
                        this.b[var5].b,
                        this.posX + this.b[var5].d - this.t[4 + (this.t[0] * this.b.length << 1) + (var5 << 1)],
                        this.posY + this.b[var5].e + this.t[4 + (this.t[0] * this.b.length << 1) + (var5 << 1) + 1],
                        this.b[var5].b,
                        this.b[var5].c,
                        true
                     );
                  } else {
                     var1.drawRGB(
                        this.b[var5].a,
                        0,
                        this.b[var5].b,
                        this.posX + this.b[var5].d + this.t[4 + (this.t[0] * this.b.length << 1) + (var5 << 1)],
                        this.posY + this.b[var5].e + this.t[4 + (this.t[0] * this.b.length << 1) + (var5 << 1) + 1],
                        this.b[var5].b,
                        this.b[var5].c,
                        true
                     );
                  }
               }

               return;
            case 15:
               int var4 = 4 + this.t[0] * 3;
               if (this.facingDirection == 1) {
                  var1.drawRGB(
                     this.b[this.t[var4]].a,
                     0,
                     this.b[this.t[var4]].b,
                     this.posX + this.b[this.t[var4]].d - this.t[var4 + 1],
                     this.posY + this.b[this.t[var4]].e + this.t[var4 + 2],
                     this.b[this.t[var4]].b,
                     this.b[this.t[var4]].c,
                     true
                  );
                  return;
               }

               var1.drawRGB(
                  this.b[this.t[var4]].a,
                  0,
                  this.b[this.t[var4]].b,
                  this.posX + this.b[this.t[var4]].d + this.t[var4 + 1],
                  this.posY + this.b[this.t[var4]].e + this.t[var4 + 2],
                  this.b[this.t[var4]].b,
                  this.b[this.t[var4]].c,
                  true
               );
               return;
            case 16:
               for (int var2 = 0; var2 < this.t[2]; var2++) {
                  for (int var3 = 0; var3 < this.b[0].b * this.t[0]; var3++) {
                     if (this.b[0].a[var2 * this.t[1] * this.b[0].b + var3] != 16777215 && this.b[0].a[var2 * this.t[1] * this.b[0].b + var3] != 0) {
                        this.b[0].a[var2 * this.t[1] * this.b[0].b + var3] = this.b[0].a[var2 * this.t[1] * this.b[0].b + var3] & 16777215;
                     }
                  }
               }

               var1.drawRGB(this.b[0].a, 0, this.b[0].b, this.posX + this.b[0].d, this.posY + this.b[0].e, this.b[0].b, this.b[0].c, true);
               return;
            case 17:
               var1.drawRGB(this.b[0].a, 0, this.b[0].b, this.posX + this.b[0].d, this.posY + this.b[0].e + this.t[3], this.b[0].b, this.b[0].c, true);
               if (this.t[0] / this.t[2] % 2 == 0) {
                  var1.drawRGB(this.b[1].a, 0, this.b[1].b, this.posX + this.b[1].d, this.posY + this.b[1].e + this.t[3], this.b[1].b, this.b[1].c, true);
                  return;
               }
               break;
            default:
               this.a.drawCurrentFrame(var1, this.posX, this.posY, this.facingDirection);
         }
      }
   }
}
