package game;

import java.io.DataInputStream;
import java.util.Hashtable;
import javax.microedition.lcdui.Graphics;

public final class TextLayoutHelper {
   public static byte a;
   private static byte[][] c;
   private static int[] d;
   private static int[] e;
   private static Hashtable f;
   public static int b;
   private static String g;
   private static int h;
   private static char i;
   private static int j;

   static {
      if (c == null) {
         f = new Hashtable();
         DataInputStream var0 = new DataInputStream("".getClass().getResourceAsStream("/font.bin"));

         try {
            String var1 = var0.readUTF();
            byte var2 = a = var0.readByte();
            int var3;
            d = new int[var3 = var1.length()];
            e = new int[var3];
            int var4 = 0;

            for (int var5 = 0; var5 < var3; var5++) {
               d[var5] = var0.readByte();
               e[var5] = var4;
               var4 += d[var5];
               f.put(new Integer(var1.charAt(var5)), new Integer(var5));
            }

            c = new byte[var2][var4];
            int var10 = 7;
            byte var8 = 0;

            for (int var9 = 0; var9 < var2; var9++) {
               for (int var6 = 0; var6 < var4; var6++) {
                  if (++var10 >= 8) {
                     var10 = 0;
                     var8 = var0.readByte();
                  }

                  if ((var8 & 1) != 0) {
                     c[var9][var6] = 1;
                  }

                  var8 = (byte)(var8 >> 1);
               }
            }

            var0.close();
         } catch (Exception var7) {
            var7.printStackTrace();
         }
      }

      b = a("nhung1");
   }

   public static int a(String var0) {
      if (var0 == g) {
         return h;
      }

      g = var0;
      return h = a(var0, 0, var0.length() - 1);
   }

   public static int a(String var0, int var1, int var2) {
      int var3 = 0;

      for (int var6 = var1; var6 < var2; var6++) {
         Integer var4 = new Integer(var0.charAt(var6));

         try {
            var3 += d[((Integer)f.get(var4)).intValue()];
         } catch (Exception var5) {
         }
      }

      return var3;
   }

   public static int a(char var0) {
      if (var0 == i) {
         return j;
      }

      try {
         i = var0;
         return j = d[((Integer)f.get(new Integer(var0))).intValue()];
      } catch (Exception var1) {
         j = 0;
         return 0;
      }
   }

   public static final void a(Graphics var0, String var1, int var2, int var3) {
      int var4 = var1.length();

      for (int var5 = 0; var5 < var4; var5++) {
         var2 += a(var0, var1.charAt(var5), var2, var3);
      }
   }

   public static final void a(Graphics var0, String var1, int var2, int var3, int var4) {
      int var5 = var2;
      int var6 = var3;
      if ((var4 & 1) != 0) {
         var5 = var2 - a(var1) / 2;
      } else if ((var4 & 8) != 0) {
         var5 = var2 - a(var1);
      }

      if ((var4 & 2) != 0) {
         var6 = var3 - a / 2;
      } else if ((var4 & 32) != 0) {
         var6 = var3 - a;
      }

      var2 = var1.length();

      for (int var8 = 0; var8 < var2; var8++) {
         var5 += a(var0, var1.charAt(var8), var5, var6);
      }
   }

   public static final int a(Graphics var0, char var1, int var2, int var3) {
      Integer var4 = new Integer(var1);

      try {
         int var11 = (Integer)f.get(var4);
         int var5 = d[var11];
         var11 = e[var11];
         byte var6 = a;

         for (int var7 = 0; var7 < var6; var7++) {
            byte[] var8 = c[var7];

            for (int var9 = 0; var9 < var5; var9++) {
               if (var8[var9 + var11] != 0) {
                  var0.drawLine(var2 + var9, var3 + var7, var2 + var9, var3 + var7);
               }
            }
         }

         return var5;
      } catch (Exception var10) {
         System.out.println("c1=" + String.valueOf(var1));
         return 0;
      }
   }

   public static String[] a(String var0, int var1) {
      String[] var2 = new String[50];
      int var3 = var0.length();
      int var4 = var1 - b;
      int var5 = 0;
      int var6 = 0;
      StringBuffer var7 = new StringBuffer();

      for (int var8 = 0; var8 < var3; var8++) {
         char var9;
         int var10 = a(var9 = var0.charAt(var8));
         if ((var5 = var5 + var10) > var1 || var9 == ' ' && var5 > var4) {
            var2[var6++] = var7.toString();
            var7 = new StringBuffer();
            if (var9 != ' ') {
               var5 = var10;
               var7.append(var9);
            } else {
               var5 = 0;
            }
         } else {
            var7.append(var9);
         }

         var5 = var5;
      }

      if (var7.length() > 0) {
         var2[var6++] = var7.toString();
      }

      String[] var12 = new String[var6];
      System.arraycopy(var2, 0, var12, 0, var6);
      return var12;
   }
}
