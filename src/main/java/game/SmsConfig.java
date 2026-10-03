package game;

import java.io.DataInputStream;
import javax.microedition.midlet.MIDlet;

public final class SmsConfig {
   public static String a;
   public static String b;
   private static String[] d;
   private static String[] e;
   public static String[] c;
   private static int[] f;
   private static boolean g = false;

   public static final void a(int var0) {
      if (d != null && e != null) {
         if (var0 < e.length) {
            String var1;
            if ((var1 = GameMIDLet.instance.getAppProperty("uid")) == null) {
               var1 = "0";
            }

            String var3 = a(e[var0], "%1", var1);
            String var2;
            if ((var2 = GameMIDLet.instance.getAppProperty("Term")) == null) {
               var2 = "";
            }

            var1 = a(var3, "%2", var2);
            if ((var2 = GameMIDLet.instance.getAppProperty("RefCode")) == null) {
               var2 = "";
            }

            var1 = a(a(var1, "%cp", var2).trim(), "  ", " ");
            a = d[var0];
            b = var1;
         }
      }
   }

   private static byte a(byte var0) {
      return (byte)(var0 <= 57 ? var0 - 48 : var0 + 10 - 97);
   }

   private static byte[] a(byte[] var0) {
      int var1;
      byte[] var2 = new byte[(var1 = var0.length) / 2];
      byte var3 = 0;

      for (int var4 = 0; var3 < var1; var4++) {
         var2[var4] |= a(var0[var3]);
         var2[var4] = (byte)(var2[var4] | a(var0[var3 + 1]) << 4);
         var3 += 2;
      }

      return var2;
   }

   private static String a(String var0, String var1, String var2) {
      StringBuffer var3 = new StringBuffer();
      int var4 = var0.indexOf(var1);
      int var5 = 0;
      int var6 = var1.length();

      while (var4 != -1) {
         var3.append(var0.substring(var5, var4)).append(var2);
         var5 = var4 + var6;
         var4 = var0.indexOf(var1, var5);
      }

      var3.append(var0.substring(var5, var0.length()));
      return var3.toString();
   }

   private static void a(byte[] var0, byte[] var1) {
      int var2 = var0.length;
      int var3 = var1.length;

      for (int var4 = 0; var4 < var2; var4++) {
         int var5 = var3 - var4 % 3;

         for (int var6 = 0; var6 < var5; var6++) {
            var0[var4] ^= var1[var6];
         }
      }
   }

   private static String b(byte[] var0) {
      String var1;
      try {
         var1 = new String(var0, "utf-8");
      } catch (Exception var3) {
         try {
            var1 = new String(var0, "UTF-8");
         } catch (Exception var2) {
            var1 = new String(var0);
         }
      }

      return var1;
   }

   private static String[] a(String var0, String var1) {
      if (var0 == null) {
         System.out.println(" split. aStr == null");
         return null;
      }

      if (var0.length() > 0 && var1 != null) {
         if (var1.length() <= 0) {
            return new String[]{var0};
         }

         String[] var2 = null;
         String var3 = var0;
         var1 = var1;
         int[] var4 = new int[var3.length()];
         int[] var5 = new int[var3.length()];
         int var6 = 0;
         boolean var7 = false;
         int var8 = 0;
         int var9 = 0;

         do {
            boolean var10 = false;
            if ((var9 = var8 + var1.length()) <= var3.length() && var3.substring(var8, var9).equals(var1)) {
               var10 = true;
            }

            if (var10) {
               if (var7) {
                  var7 = false;
                  var5[var6++] = var8;
               }

               var8 = var9;
            } else {
               if (!var7) {
                  var7 = true;
                  var4[var6] = var8;
               }

               var8++;
            }
         } while (var8 < var3.length());

         if (var7) {
            var5[var6++] = var3.length();
         }

         if (var6 > 0) {
            var2 = new String[var6];

            for (int var13 = 0; var13 < var6; var13++) {
               var2[var13] = var0.substring(var4[var13], var5[var13]);
            }
         }

         return var2;
      } else {
         return new String[]{var0};
      }
   }

   public static void a(MIDlet var0) {
      if (!g) {
         g = true;
         String var1 = var0.getAppProperty("sr");
         boolean var2 = true;
         String[] var3 = null;
         byte[] var9;
         if (var1 == null) {
            try {
               DataInputStream var4;
               byte[] var8 = new byte[(var4 = new DataInputStream("".getClass().getResourceAsStream("/l2.bin"))).available()];
               var4.read(var8);
               var4.close();
               var9 = (var3 = a(b(var8), String.valueOf('\n')))[0].trim().getBytes();
            } catch (Exception var7) {
               var7.printStackTrace();
               return;
            }

            var2 = false;
         } else {
            var9 = var1.getBytes();
         }

         byte[] var15 = new byte[6];
         int var5 = var9.length;
         System.arraycopy(var9, 0, var15, 0, 3);
         System.arraycopy(var9, var5 - 3, var15, 3, 3);
         byte[] var6 = new byte[var5 - 6];
         System.arraycopy(var9, 3, var6, 0, var5 - 6);
         byte[] var21;
         a(var21 = a(var6), var15);
         byte[] var10 = new byte[]{1, 2, 5, 7, 4};
         var5 = var21.length - 5;

         for (int var16 = 0; var16 < var5; var16++) {
            var10[var16 % 5] = (byte)(var10[var16 % 5] ^ var21[var16]);
         }

         for (int var17 = 0; var17 < 5; var17++) {
            if (var10[var17] != var21[var5 + var17]) {
               return;
            }
         }

         byte[] var11 = var21;
         var6 = new byte[var5];
         System.arraycopy(var11, 0, var6, 0, var5);
         String[] var18;
         d = new String[var5 = Integer.parseInt((var18 = a(b(var6), "|"))[0])];
         e = new String[var5];
         f = new int[var5];

         for (int var12 = 0; var12 < var5; var12++) {
            d[var12] = var18[var12 + 1];
            f[var12] = Integer.parseInt(var18[var12 + var5 + 1]);
            e[var12] = var18[var12 + var5 + var5 + 1];
         }

         c = new String[var5];
         if (var2) {
            for (int var14 = 0; var14 < var5; var14++) {
               c[var14] = var0.getAppProperty("sr" + (var14 + 1));
            }
         } else {
            for (int var13 = 0; var13 < var5; var13++) {
               c[var13] = var3[var13 + 1].trim();
            }
         }
      }
   }
}
