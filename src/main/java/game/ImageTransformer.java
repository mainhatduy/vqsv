package game;

import javax.microedition.lcdui.Image;

public final class ImageTransformer {
   private static byte a = 1;
   private static byte b = 2;

   public static PixelBuffer a(Image var0, PixelBuffer var1) {
      int var2 = var0.getWidth();
      int var3 = var0.getHeight();
      int[] var4 = new int[var2 * var3];
      var0.getRGB(var4, 0, var2, 0, 0, var2, var3);

      for (int var5 = 0; var5 < var4.length; var5++) {
         if (var4[var5] == -1 || var4[var5] == -16777216) {
            var4[var5] = 16777215;
         }
      }

      var1.a(var4, var2, var3);
      return var1;
   }

   public static PixelBuffer a(SpriteRenderer var0, int var1, int[] var2, byte var3, PixelBuffer var4) {
      Image var5;
      (var5 = Image.createImage(var2[2], var2[3])).getGraphics().setColor(0);
      var5.getGraphics().fillRect(0, 0, var2[2], var2[3]);
      var0.drawFrame(var5.getGraphics(), var1, -var2[0], -var2[1], var3);
      var4.d = var2[0];
      var4.e = var2[1];
      return a(var5, var4);
   }

   public static PixelBuffer a(PixelBuffer var0, int var1, int var2) {
      int[] var3 = new int[var1 * var2];

      for (int var4 = 0; var4 < var2; var4++) {
         for (int var5 = 0; var5 < var1; var5++) {
            int var6 = var5 * var0.b / var1;
            int var7 = var4 * var0.c / var2;
            var3[var5 + var4 * var1] = var0.a[var6 + var7 * var0.b];
         }
      }

      var0.a(var3, var1, var2);
      var0.d = var0.d * var1 / var0.b / 10;
      var0.e = var0.e * var1 / var0.b / 10;
      return var0;
   }

   public static PixelBuffer a(PixelBuffer var0, int var1) {
      int var2 = var0.b * var1 / 10;
      int var3 = var0.c * var1 / 10;
      int[] var4 = new int[var2 * var3];

      for (int var5 = 0; var5 < var3; var5++) {
         for (int var6 = 0; var6 < var2; var6++) {
            int var7 = var6 * var0.b / var2;
            int var8 = var5 * var0.c / var3;
            var4[var6 + var5 * var2] = var0.a[var7 + var8 * var0.b];
         }
      }

      var0.a(var4, var2, var3);
      var0.d = var0.d * var1 / 10;
      var0.e = var0.e * var1 / 10;
      return var0;
   }

   public static PixelBuffer b(PixelBuffer var0, int var1, int var2) {
      for (int var7 = 0; var7 < var0.c; var7++) {
         for (int var8 = 0; var8 < var0.b; var8++) {
            int var5;
            int var6 = (var5 = var0.a[var7 * var0.b + var8]) >> 24;
            int var3 = var5 >> 16 & 0xFF;
            int var4 = var5 >> 8 & 0xFF;
            var5 &= 255;
            int var9 = var3 * var1 + var2;
            int var10 = var4 * var1 + var2;
            int var12 = var5 * var1 + var2;
            if (var9 > 255) {
               var9 = 255;
            } else if (var9 < 0) {
               var9 = 0;
            }

            if (var10 > 255) {
               var10 = 255;
            } else if (var10 < 0) {
               var10 = 0;
            }

            if (var12 > 255) {
               var12 = 255;
            } else if (var12 < 0) {
               var12 = 0;
            }

            var0.a[var7 * var0.b + var8] = var6 << 24 | var9 << 16 | var10 << 8 | var12;
         }
      }

      return var0;
   }

   public static PixelBuffer b(PixelBuffer var0, int var1) {
      if (var1 >= 0 && var1 <= 255) {
         for (int var2 = 0; var2 < var0.f; var2++) {
            if (var0.a[var2] != 16777215 && var0.a[var2] != 0) {
               if (var0.a[var2] == -16777216) {
                  var0.a[var2] = 0;
               } else {
                  var0.a[var2] = var1 << 24 | var0.a[var2] & 16777215;
               }
            }
         }

         return var0;
      } else {
         return var0;
      }
   }

   public static Image a(Image var0) {
      PixelBuffer var1 = new PixelBuffer();
      int var2 = var0.getWidth();
      int var3 = var0.getHeight();
      Image var4;
      (var4 = Image.createImage(var2, var3)).getGraphics().setColor(0);
      var4.getGraphics().fillRect(0, 0, var2, var3);
      var4.getGraphics().drawImage(var0, 0, 0, 20);
      var1.a(EngineUtils.a(var4), var2, var3);
      b(var1, 100);
      var0 = EngineUtils.a(var1.a, var1.b, var1.c);
      var1.a = null;
      return var0;
   }

   public static PixelBuffer a(PixelBuffer var0, int var1, int var2, int var3, int var4) {
      for (int var5 = 0; var5 < var0.a.length; var5++) {
         if (var0.a[var5] != 16777215) {
            if (var1 >= 0 && var1 <= 255) {
               var0.a[var5] = var1 << 24 | var2 << 16 | var3 << 8 | var4;
            } else {
               var0.a[var5] = var2 << 16 | var3 << 8 | var4;
            }
         }
      }

      return var0;
   }

   public static PixelBuffer a(PixelBuffer var0, PixelBuffer var1, byte var2) {
      int var5 = 0;
      int var6 = 0;
      if (var2 == 0) {
         var0 = b(var0, 5, 5);
      }

      for (int var7 = 0; var7 < var0.c; var7++) {
         for (int var8 = 0; var8 < var0.b; var8++) {
            int var3 = var0.a[var7 * var0.b + var8];
            int var4 = var1.a[var6 * var1.b + var5];
            if (var3 >> 24 != 0) {
               if (var2 == 0) {
                  var0.a[var7 * var0.b + var8] = var3 & var4;
               } else if (var2 == a) {
                  var0.a[var7 * var0.b + var8] = var3 | var4;
               } else if (var2 == b) {
                  var0.a[var7 * var0.b + var8] = var4;
               }
            }

            if (var5 < var1.b - 1) {
               var5++;
            } else {
               var5 = 0;
            }
         }

         if (var6 < var1.c - 1) {
            var6++;
         } else {
            var6 = 0;
         }

         var5 = 0;
      }

      return var0;
   }
}
