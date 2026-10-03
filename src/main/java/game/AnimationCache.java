package game;

public final class AnimationCache {
   private static SpriteData[] animationDataById;
   private static short[] petFrameXOffsets = new short[]{0, 10, 3, 7, -10};
   private static short[][] petAnimations = new short[][]{{2, 0}, {1, 0, 1, 1, 1, 2, 1, 3, 1, 2}, {5, 0, 5, 4}};

   public static void initialize() {
      animationDataById = new SpriteData[1000];
   }

   public static SpriteData getAnimationData(int var0) {
      if (animationDataById[var0] == null) {
         animationDataById[var0] = new SpriteData();
         byte[] var1 = new byte[20000];
         int[] var2 = new int[]{0};
         short[][] var3 = new short[5][4];
         EngineUtils.a(var1, "/data/spr/spr_" + var0 + "_all(r)");
         animationDataById[var0].modules = EngineUtils.readFlatShorts(var1, var2);
         if (var0 >= 86 && var0 <= 185) {
            animationDataById[var0].frames = EngineUtils.readShortMatrix(var1, var2);

            for (int var4 = 0; var4 < var3.length; var4++) {
               for (int var5 = 0; var5 < 4; var5++) {
                  if (var5 == 1) {
                     var3[var4][var5] = (short)(animationDataById[var0].frames[0][var5] + petFrameXOffsets[var4]);
                  } else {
                     var3[var4][var5] = animationDataById[var0].frames[0][var5];
                  }
               }
            }

            animationDataById[var0].frames = var3;
            animationDataById[var0].animations = EngineUtils.readShortMatrix(var1, var2);
            animationDataById[var0].animations = petAnimations;
         } else {
            animationDataById[var0].frames = EngineUtils.readShortMatrix(var1, var2);
            animationDataById[var0].animations = EngineUtils.readShortMatrix(var1, var2);
         }

         animationDataById[var0].collisionRegions = groupRegionsByFrame(EngineUtils.readFlatShorts(var1, var2), animationDataById[var0].frames.length);
         animationDataById[var0].attackRegions = groupRegionsByFrame(EngineUtils.readFlatShorts(var1, var2), animationDataById[var0].frames.length);
      }

      animationDataById[var0].referenceCount++;
      return animationDataById[var0];
   }

   private static short[][] groupRegionsByFrame(short[] var0, int var1) {
      if (var0 == null) {
         return null;
      }

      short[][] var3 = new short[var1][];

      for (int var2 = 0; var2 < var0.length / 5; var2++) {
         var3[var0[var2 * 5]] = EngineUtils.a(var3[var0[var2 * 5]], new short[]{var0[var2 * 5 + 1], var0[var2 * 5 + 2], var0[var2 * 5 + 3], var0[var2 * 5 + 4]});
      }

      return var3;
   }

   public static void releaseAnimationData(int var0) {
      animationDataById[var0].referenceCount--;
      if (animationDataById[var0].referenceCount <= 0) {
         animationDataById[var0].referenceCount = 0;
         evictAnimationData(var0);
      }
   }

   public static boolean evictAnimationData(int var0) {
      if (animationDataById[var0] != null) {
         animationDataById[var0].referenceCount = 0;
         animationDataById[var0] = null;
         return true;
      } else {
         return false;
      }
   }
}
