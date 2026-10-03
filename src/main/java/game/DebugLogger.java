package game;

import java.util.Vector;

public final class DebugLogger {
   private static Vector a = new Vector();

   public static void a(Throwable var0, String var1) {
      String[] var2 = new String[]{"", ""};
      if (var1 != null) {
         var2[0] = var1;
         System.out.println(var2[0]);
      } else {
         var2[0] = "";
         System.out.println(var2[0]);
      }

      if (var0 != null) {
         var2[1] = var0.toString();
      } else {
         var2[1] = "";
      }

      a.addElement(var2);
   }
}
