package game;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import javax.microedition.lcdui.Image;

public final class GameDatabase {
   public static short[][] spriteTable;
   public static short[][] tilesetImageTable;
   public static short[][][] gameDatabase;
   public static String[] strings;
   public static int[][] texturePixels;
   public static Image backgroundImage;

   public static void initialize() {
      String var0 = "/data/script/sprite.mid";

      try {
         InputStream var9;
         spriteTable = EngineUtils.readShortRows(var9 = EngineUtils.openResource(var0));
         var9.close();
      } catch (Exception var7) {
      }

      var0 = "/data/mod/modInfo.mid";

      try {
         "".getClass();
         InputStream var11 = ResourceStream.openResource(var0);
         DataInputStream var1;
         byte var2;
         tilesetImageTable = new short[var2 = (var1 = new DataInputStream(var11)).readByte()][];

         for (int var3 = 0; var3 < var2; var3++) {
            byte var4 = var1.readByte();
            tilesetImageTable[var3] = new short[var4];

            for (int var5 = 0; var5 < var4; var5++) {
               short var6 = var1.readShort();
               tilesetImageTable[var3][var5] = var6;
            }
         }

         var1.close();
         var11.close();
      } catch (IOException var8) {
         var8.printStackTrace();
      }

      loadStrings("/data/script/chs.mid");
      loadNpcDialog("/data/script/npcDialog.mid");
      loadTexturePixels();
      loadDatabase("/data/script/db.mid");
      backgroundImage = EngineUtils.loadImage("/data/tex/", "bk");
   }

   private static void loadNpcDialog(String var0) {
      try {
         String[][] var1;
         InputStream var4;
         WorldManager.N = new String[(var1 = EngineUtils.readStringRows(var4 = EngineUtils.openResource(var0))).length];

         for (int var2 = 0; var2 < var1.length; var2++) {
            System.arraycopy(var1[var2], 0, WorldManager.N, var2, var1[var2].length);
         }

         var4.close();
      } catch (IOException var3) {
         var3.printStackTrace();
      }
   }

   public static short getValue(byte var0, short var1, byte var2) {
      return gameDatabase[var0][var1][var2];
   }

   private static void loadDatabase(String var0) {
      try {
         InputStream var3 = EngineUtils.openResource(var0);
         gameDatabase = new short[9][][];

         for (int var1 = 0; var1 < 9; var1++) {
            gameDatabase[var1] = EngineUtils.readShortRows(var3);
         }

         var3.close();
      } catch (IOException var2) {
      }
   }

   private static void loadStrings(String var0) {
      try {
         String[][] var1;
         InputStream var6;
         strings = new String[(var1 = EngineUtils.readStringRows(var6 = EngineUtils.openResource(var0))).length];
         StringBuffer var2 = new StringBuffer();

         for (int var3 = 0; var3 < var1.length; var3++) {
            var2.delete(0, var2.length());

            for (int var4 = 0; var4 < var1[var3].length; var4++) {
               var2.append(var1[var3][var4]);
            }

            strings[var3] = var2.toString();
         }

         var6.close();
      } catch (IOException var5) {
      }
   }

   private static void loadTexturePixels() {
      texturePixels = new int[4][];

      for (int var0 = 0; var0 < 4; var0++) {
         texturePixels[var0] = EngineUtils.a(EngineUtils.loadImage("/data/tex/", "tex_" + var0));
      }
   }
}
