package game;

import javax.microedition.lcdui.Image;

public final class ImageCache {
   private static Image[] images;
   private static byte[] referenceCounts;
   private static int capacity;

   public static void initialize() {
      capacity = 50000;
      images = new Image[50000];
      referenceCounts = new byte[capacity];
   }

   public static Image getImage(int var0) {
      if (images[var0] == null) {
         images[var0] = EngineUtils.loadImage("/data/img/", "img_" + var0);
      }

      referenceCounts[var0]++;
      return images[var0];
   }

   public static void releaseImage(int var0) {
      referenceCounts[var0]--;
      if (referenceCounts[var0] <= 0) {
         referenceCounts[var0] = 0;
      }
   }

   public static boolean evictImage(int var0) {
      if (var0 == -1) {
         return true;
      } else {
         referenceCounts[var0]--;
         if (referenceCounts[var0] <= 0) {
            referenceCounts[var0] = 0;
            images[var0] = null;
            return true;
         } else {
            return false;
         }
      }
   }
}
