package game;

import javax.microedition.lcdui.Graphics;

public final class TextPainter {
   public static void a(String var0, int var1, int var2, int var3, int var4, Graphics var5) {
      var5.setColor(var4);
      TextLayoutHelper.a(var5, var0, var1, var2, var3);
   }

   public static int a(String var0, int var1, int var2) {
      return TextLayoutHelper.a(var0, 0, var2 + 0);
   }
}
