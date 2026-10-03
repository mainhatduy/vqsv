package game;

import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

public final class SpriteRenderer {
   private static int[] c = new int[]{0, 5, 3, 6, 2, 4, 1, 7};
   private static int[] d = new int[]{2, 4, 1, 7, 0, 5, 3, 6};
   private static int[] e = new int[]{3, 6, 0, 5, 1, 7, 2, 4};
   private static int[] f = new int[]{1, 7, 2, 4, 3, 6, 0, 5};
   private static int[] g = new int[]{0, 270, 180, 90, 8192, 8462, 8372, 8282};
   private static int[] h = new int[]{8192, 8462, 8372, 8282, 0, 270, 180, 90};
   private static int[] i = new int[]{180, 90, 0, 270, 8372, 8282, 8192, 8462};
   private static int[] j = new int[]{8372, 8282, 8192, 8462, 180, 90, 0, 270};
   private Image[] imageSheets;
   private SpriteData spriteData;
   private int[] imageIds;
   public int spriteId;
   private int remainingFrameTicks;
   private int remainingExtraTicks;
   private byte nextAnimationId;
   private int animationStepIndex;
   private int animationStepCount;
   protected byte animationId;
   private boolean s = true;

   public final boolean loadSprite(int var1, boolean var2) {
      this.imageIds = new int[GameDatabase.spriteTable[var1].length - 1];
      this.imageSheets = new Image[GameDatabase.spriteTable[var1].length - 1];

      for (int var3 = 0; var3 < this.imageSheets.length; var3++) {
         this.imageIds[var3] = GameDatabase.spriteTable[var1][var3 + 1];
         this.imageSheets[var3] = ImageCache.getImage(this.imageIds[var3]);
      }

      this.spriteId = var1;
      this.spriteData = AnimationCache.getAnimationData(GameDatabase.spriteTable[var1][0]);
      this.spriteData.hasExtendedAnimationSteps = var2;
      this.selectAnimationStep(0);
      return true;
   }

   public final void releaseSprite() {
      if (this.imageIds != null) {
         for (int var1 = 0; var1 < this.imageIds.length; var1++) {
            if (this.imageSheets != null) {
               this.imageSheets[var1] = null;
            }

            ImageCache.releaseImage(this.imageIds[var1]);
         }
      }

      this.imageIds = null;
      if (this.spriteId != 257) {
         this.spriteData = null;
         AnimationCache.releaseAnimationData(this.spriteId);
      }
   }

   public final void evictSprite() {
      if (this.imageIds != null) {
         for (int var1 = 0; var1 < this.imageIds.length; var1++) {
            if (this.imageSheets != null) {
               this.imageSheets[var1] = null;
            }

            ImageCache.evictImage(this.imageIds[var1]);
         }

         this.imageIds = null;
         this.spriteData = null;
         AnimationCache.evictAnimationData(this.spriteId);
      }
   }

   public final void replaceImage(int var1, int var2, boolean var3) {
      this.imageSheets[var1] = null;
      ImageCache.evictImage(this.imageIds[var1]);
      this.imageIds[var1] = var2;
      this.imageSheets[var1] = ImageCache.getImage(this.imageIds[var1]);
   }

   public static void c() {
   }

   public final void a(int var1) {
      for (int var2 = 0; var2 < this.imageSheets.length; var2++) {
         if (var1 == 1) {
            this.imageSheets[var2] = ImageTransformer.a(ImageCache.getImage(this.imageIds[var2]));
         } else {
            this.evictSprite();
         }
      }

      if (var1 == 0) {
         this.loadSprite(this.spriteId, false);
      }
   }

   public final boolean setAnimation(byte var1, byte var2, boolean var3) {
      if (this.animationId == var1 && !var3) {
         this.animationId = var1;
      } else {
         this.animationId = var1;
         this.selectAnimationStep(0);
      }

      this.nextAnimationId = var2;
      return true;
   }

   public final void setAnimationWithoutReset(byte var1, byte var2) {
      this.animationId = var1;
      this.nextAnimationId = -1;
   }

   private void selectAnimationStep(int var1) {
      this.animationStepIndex = var1;
      if (this.spriteData.animations != null) {
         if (this.spriteData.hasExtendedAnimationSteps) {
            this.remainingFrameTicks = this.spriteData.animations[this.animationId][this.animationStepIndex << 2];
            this.animationStepCount = this.spriteData.animations[this.animationId].length / 4;
         } else {
            this.remainingFrameTicks = this.spriteData.animations[this.animationId][this.animationStepIndex << 1];
            this.animationStepCount = this.spriteData.animations[this.animationId].length / 2;
         }

         this.remainingExtraTicks = 0;
         if (this.remainingFrameTicks > 0) {
            this.remainingFrameTicks--;
         } else {
            if (this.remainingExtraTicks > 0) {
               this.remainingExtraTicks--;
            }
         }
      }
   }

   public final boolean advanceAnimation() {
      if (this.remainingFrameTicks > 0) {
         this.remainingFrameTicks--;
      } else if (this.remainingExtraTicks > 0) {
         this.remainingExtraTicks--;
      } else {
         this.animationStepIndex++;
         if (this.animationStepIndex >= this.animationStepCount) {
            if (this.nextAnimationId >= 0) {
               this.setAnimation(this.nextAnimationId, (byte)-1, true);
            } else if (this.nextAnimationId == -2) {
               this.animationStepIndex--;
               this.selectAnimationStep(this.animationStepIndex);
            } else if (this.nextAnimationId == -1) {
               this.selectAnimationStep(0);
            }

            return true;
         }

         this.selectAnimationStep(this.animationStepIndex);
      }

      return false;
   }

   public final boolean isLastAnimationStep() {
      return this.animationStepIndex >= this.animationStepCount - 1;
   }

   public final boolean isFrameTimerExpired() {
      return this.remainingFrameTicks == 0;
   }

   public final boolean isAnimationStep(int var1) {
      return this.animationStepIndex == var1;
   }

   public final byte getAnimationId() {
      return this.animationId;
   }

   public final int getAnimationStepIndex() {
      return this.animationStepIndex;
   }

   public final int[] getAnimationBounds(int var1, byte var2) {
      if (var1 >= 0 && var1 < this.spriteData.animations.length) {
         int[] var8;
         int var3 = (var8 = this.getFrameBounds(this.spriteData.animations[var1][1], (byte)0))[0];
         int var4 = var8[0] + var8[2];
         int var5 = var8[1];
         int var6 = var8[1] + var8[3];
         if (this.spriteData.hasExtendedAnimationSteps) {
            for (int var7 = 1; var7 != this.spriteData.animations[var1].length / 4; var7++) {
               int[] var9;
               if ((var9 = this.getFrameBounds(this.spriteData.animations[var1][(var7 << 2) + 1], (byte)0)) != null) {
                  if (var3 > var9[0]) {
                     var3 = var9[0];
                  }

                  if (var4 < var9[0] + var9[2]) {
                     var4 = var9[0] + var9[2];
                  }

                  if (var5 > var9[1]) {
                     var5 = var9[1];
                  }

                  if (var6 < var9[1] + var9[3]) {
                     var6 = var9[1] + var9[3];
                  }
               }
            }
         } else {
            for (int var11 = 1; var11 != this.spriteData.animations[var1].length / 2; var11++) {
               int[] var10;
               if ((var10 = this.getFrameBounds(this.spriteData.animations[var1][(var11 << 1) + 1], (byte)0)) != null) {
                  if (var3 > var10[0]) {
                     var3 = var10[0];
                  }

                  if (var4 < var10[0] + var10[2]) {
                     var4 = var10[0] + var10[2];
                  }

                  if (var5 > var10[1]) {
                     var5 = var10[1];
                  }

                  if (var6 < var10[1] + var10[3]) {
                     var6 = var10[1] + var10[3];
                  }
               }
            }
         }

         return new int[]{var3, var5, var4 - var3, var6 - var5};
      } else {
         return null;
      }
   }

   public final int[] getFrameBounds(int var1, byte var2) {
      if (this.spriteData.frames[var1].length <= 0) {
         return null;
      }

      short var3 = this.spriteData.frames[var1][0];
      int var4 = this.spriteData.frames[var1][1];
      int var5 = this.spriteData.frames[var1][2];

      for (int var6 = 0; var6 < this.spriteData.frames[var1].length; var6 += 4) {
         var3 = this.spriteData.frames[var1][var6];
         if (this.spriteData.frames[var1][var6 + 1] < var4) {
            var4 = this.spriteData.frames[var1][var6 + 1];
         }

         if (this.spriteData.frames[var1][var6 + 2] < var5) {
            var5 = this.spriteData.frames[var1][var6 + 2];
         }
      }

      int[] var17 = new int[2];
      int[] var18;
      int var7 = (var18 = this.a(0, var3, var1, var4, var5, var17))[0];
      int var8 = var18[1];

      for (int var9 = 0; var9 < this.spriteData.frames[var1].length; var9 += 4) {
         var3 = this.spriteData.frames[var1][var9];
         if ((var18 = this.a(var9, var3, var1, var4, var5, var18))[0] > var7) {
            var7 = var18[0];
         }

         if (var18[1] > var8) {
            var8 = var18[1];
         }
      }

      switch (var2) {
         case 1:
            var3 = this.spriteData.frames[var1][0];
            if (this.spriteData.frames[var1][3] % 2 == 1) {
               var4 = -this.spriteData.frames[var1][1] - this.spriteData.modules[var3 * 5 + 4];
            } else {
               var4 = -this.spriteData.frames[var1][1] - this.spriteData.modules[var3 * 5 + 3];
            }

            for (int var21 = 0; var21 < this.spriteData.frames[var1].length; var21 += 4) {
               var3 = this.spriteData.frames[var1][var21];
               if (this.spriteData.frames[var1][var21 + 3] % 2 == 1) {
                  if (-this.spriteData.frames[var1][var21 + 1] - this.spriteData.modules[var3 * 5 + 4] < var4) {
                     var4 = -this.spriteData.frames[var1][var21 + 1] - this.spriteData.modules[var3 * 5 + 4];
                  }
               } else if (-this.spriteData.frames[var1][var21 + 1] - this.spriteData.modules[var3 * 5 + 3] < var4) {
                  var4 = -this.spriteData.frames[var1][var21 + 1] - this.spriteData.modules[var3 * 5 + 3];
               }
            }
         case 2:
         default:
            break;
         case 3:
            var3 = this.spriteData.frames[var1][0];
            if (this.spriteData.frames[var1][3] % 2 == 1) {
               var4 = -this.spriteData.frames[var1][1] - this.spriteData.modules[var3 * 5 + 4];
               var5 = -this.spriteData.frames[var1][2] - this.spriteData.modules[var3 * 5 + 3];
            } else {
               var4 = -this.spriteData.frames[var1][1] - this.spriteData.modules[var3 * 5 + 3];
               var5 = -this.spriteData.frames[var1][2] - this.spriteData.modules[var3 * 5 + 4];
            }

            for (int var20 = 0; var20 < this.spriteData.frames[var1].length; var20 += 4) {
               var3 = this.spriteData.frames[var1][var20];
               if (this.spriteData.frames[var1][var20 + 3] % 2 == 1) {
                  if (-this.spriteData.frames[var1][var20 + 1] - this.spriteData.modules[var3 * 5 + 4] < var4) {
                     var4 = -this.spriteData.frames[var1][var20 + 1] - this.spriteData.modules[var3 * 5 + 4];
                  }

                  if (-this.spriteData.frames[var1][var20 + 2] - this.spriteData.modules[var3 * 5 + 3] < var5) {
                     var5 = -this.spriteData.frames[var1][var20 + 2] - this.spriteData.modules[var3 * 5 + 3];
                  }
               } else {
                  if (-this.spriteData.frames[var1][var20 + 1] - this.spriteData.modules[var3 * 5 + 3] < var4) {
                     var4 = -this.spriteData.frames[var1][var20 + 1] - this.spriteData.modules[var3 * 5 + 3];
                  }

                  if (-this.spriteData.frames[var1][var20 + 2] - this.spriteData.modules[var3 * 5 + 4] < var5) {
                     var5 = -this.spriteData.frames[var1][var20 + 2] - this.spriteData.modules[var3 * 5 + 4];
                  }
               }
            }
            break;
         case 4:
            var3 = this.spriteData.frames[var1][0];
            if (this.spriteData.frames[var1][3] % 2 == 1) {
               var5 = -this.spriteData.frames[var1][2] - this.spriteData.modules[var3 * 5 + 3];
            } else {
               var5 = -this.spriteData.frames[var1][2] - this.spriteData.modules[var3 * 5 + 4];
            }

            for (int var19 = 0; var19 < this.spriteData.frames[var1].length; var19 += 4) {
               var3 = this.spriteData.frames[var1][var19];
               if (this.spriteData.frames[var1][var19 + 3] % 2 == 1) {
                  if (-this.spriteData.frames[var1][var19 + 2] - this.spriteData.modules[var3 * 5 + 3] < var5) {
                     var5 = -this.spriteData.frames[var1][var19 + 2] - this.spriteData.modules[var3 * 5 + 3];
                  }
               } else if (-this.spriteData.frames[var1][var19 + 2] - this.spriteData.modules[var3 * 5 + 4] < var5) {
                  var5 = -this.spriteData.frames[var1][var19 + 2] - this.spriteData.modules[var3 * 5 + 4];
               }
            }
      }

      return new int[]{var4, var5, var7, var8};
   }

   private int[] a(int var1, int var2, int var3, int var4, int var5, int[] var6) {
      if (this.spriteData.frames[var3][var1 + 3] % 2 == 1) {
         var6[1] = this.spriteData.frames[var3][var1 + 2] - var5 + this.spriteData.modules[var2 * 5 + 3];
         var6[0] = this.spriteData.frames[var3][var1 + 1] - var4 + this.spriteData.modules[var2 * 5 + 4];
      } else {
         var6[0] = this.spriteData.frames[var3][var1 + 1] - var4 + this.spriteData.modules[var2 * 5 + 3];
         var6[1] = this.spriteData.frames[var3][var1 + 2] - var5 + this.spriteData.modules[var2 * 5 + 4];
      }

      return var6;
   }

   public final void drawCurrentFrame(Graphics var1, int var2, int var3, byte var4) {
      if (this.spriteData.hasExtendedAnimationSteps) {
         this.drawFrame(var1, this.spriteData.animations[this.animationId][(this.animationStepIndex << 2) + 1], var2, var3, var4);
      } else {
         this.drawFrame(var1, this.spriteData.animations[this.animationId][(this.animationStepIndex << 1) + 1], var2, var3, var4);
      }
   }

   public final void drawFrame(Graphics var1, int var2, int var3, int var4, byte var5) {
      if (this.spriteData.frames[var2].length > 0) {
         switch (var5) {
            case 0:
               for (int var9 = 0; var9 < this.spriteData.frames[var2].length; var9 += 4) {
                  this.a(
                     var1,
                     this.spriteData.frames[var2][var9],
                     var3 + this.spriteData.frames[var2][var9 + 1],
                     var4 + this.spriteData.frames[var2][var9 + 2],
                     this.s ? c[this.spriteData.frames[var2][var9 + 3]] : g[this.spriteData.frames[var2][var9 + 3]],
                     20
                  );
               }

               return;
            case 1:
               for (int var8 = 0; var8 < this.spriteData.frames[var2].length; var8 += 4) {
                  if (this.spriteData.frames[var2][var8 + 3] % 2 == 1) {
                     this.a(
                        var1,
                        this.spriteData.frames[var2][var8],
                        var3 - this.spriteData.frames[var2][var8 + 1] - this.spriteData.modules[this.spriteData.frames[var2][var8] * 5 + 4],
                        var4 + this.spriteData.frames[var2][var8 + 2],
                        this.s ? d[this.spriteData.frames[var2][var8 + 3]] : h[this.spriteData.frames[var2][var8 + 3]],
                        20
                     );
                  } else {
                     this.a(
                        var1,
                        this.spriteData.frames[var2][var8],
                        var3 - this.spriteData.frames[var2][var8 + 1] - this.spriteData.modules[this.spriteData.frames[var2][var8] * 5 + 3],
                        var4 + this.spriteData.frames[var2][var8 + 2],
                        this.s ? d[this.spriteData.frames[var2][var8 + 3]] : h[this.spriteData.frames[var2][var8 + 3]],
                        20
                     );
                  }
               }

               return;
            case 3:
               for (int var7 = 0; var7 < this.spriteData.frames[var2].length; var7 += 4) {
                  if (this.spriteData.frames[var2][var7 + 3] % 2 == 1) {
                     this.a(
                        var1,
                        this.spriteData.frames[var2][var7],
                        var3 - this.spriteData.frames[var2][var7 + 1] - this.spriteData.modules[this.spriteData.frames[var2][var7] * 5 + 4],
                        var4 - this.spriteData.frames[var2][var7 + 2] - this.spriteData.modules[this.spriteData.frames[var2][var7] * 5 + 3],
                        this.s ? e[this.spriteData.frames[var2][var7 + 3]] : i[this.spriteData.frames[var2][var7 + 3]],
                        20
                     );
                  } else {
                     this.a(
                        var1,
                        this.spriteData.frames[var2][var7],
                        var3 - this.spriteData.frames[var2][var7 + 1] - this.spriteData.modules[this.spriteData.frames[var2][var7] * 5 + 3],
                        var4 - this.spriteData.frames[var2][var7 + 2] - this.spriteData.modules[this.spriteData.frames[var2][var7] * 5 + 4],
                        this.s ? e[this.spriteData.frames[var2][var7 + 3]] : i[this.spriteData.frames[var2][var7 + 3]],
                        20
                     );
                  }
               }

               return;
            case 4:
               for (int var6 = 0; var6 < this.spriteData.frames[var2].length; var6 += 4) {
                  if (this.spriteData.frames[var2][var6 + 3] % 2 == 1) {
                     this.a(
                        var1,
                        this.spriteData.frames[var2][var6],
                        var3 + this.spriteData.frames[var2][var6 + 1],
                        var4 - this.spriteData.frames[var2][var6 + 2] - this.spriteData.modules[this.spriteData.frames[var2][var6] * 5 + 3],
                        this.s ? f[this.spriteData.frames[var2][var6 + 3]] : j[this.spriteData.frames[var2][var6 + 3]],
                        20
                     );
                  } else {
                     this.a(
                        var1,
                        this.spriteData.frames[var2][var6],
                        var3 + this.spriteData.frames[var2][var6 + 1],
                        var4 - this.spriteData.frames[var2][var6 + 2] - this.spriteData.modules[this.spriteData.frames[var2][var6] * 5 + 4],
                        this.s ? f[this.spriteData.frames[var2][var6 + 3]] : j[this.spriteData.frames[var2][var6 + 3]],
                        20
                     );
                  }
               }
            case 2:
         }
      }
   }

   private void a(Graphics var1, int var2, int var3, int var4, int var5, int var6) {
      var1.drawRegion(
         this.imageSheets[this.spriteData.modules[var2 * 5]],
         this.spriteData.modules[var2 * 5 + 1],
         this.spriteData.modules[var2 * 5 + 2],
         this.spriteData.modules[var2 * 5 + 3],
         this.spriteData.modules[var2 * 5 + 4],
         var5,
         var3,
         var4,
         20
      );
   }

   public final boolean i() {
      return this.spriteData.attackRegions == null;
   }

   public final short[] j() {
      if (this.spriteData.attackRegions == null) {
         return null;
      } else {
         return this.spriteData.hasExtendedAnimationSteps
            ? this.spriteData.attackRegions[this.spriteData.animations[this.animationId][(this.animationStepIndex << 2) + 1]]
            : this.spriteData.attackRegions[this.spriteData.animations[this.animationId][(this.animationStepIndex << 1) + 1]];
      }
   }

   public final short[] k() {
      if (this.spriteData.collisionRegions == null) {
         return null;
      } else {
         return this.spriteData.hasExtendedAnimationSteps
            ? this.spriteData.collisionRegions[this.spriteData.animations[this.animationId][(this.animationStepIndex << 2) + 1]]
            : this.spriteData.collisionRegions[this.spriteData.animations[this.animationId][(this.animationStepIndex << 1) + 1]];
      }
   }
}
