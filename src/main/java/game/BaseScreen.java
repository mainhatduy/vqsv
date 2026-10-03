package game;

import java.util.Timer;
import java.util.TimerTask;
import java.util.Vector;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Font;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

public abstract class BaseScreen extends BaseInputHandler implements BillingResultListener, SmsResultListener {
   private static short screenWidth;
   private static short screenHeight;
   public static int frameDelayMs;
   private static Font smallFont;
   private static Font mediumFont;
   private static int keyDelay;
   public byte screenMode;
   public byte previousScreenMode;
   public UIManager uiManager;
   public ScriptEngine interactionController;
   private static boolean g = false;
   public static boolean T = false;
   public static byte U = -1;
   public static byte V = 0;
   private static byte[][] h = new byte[7][3];
   private static Timer i;
   private static TimerTask j;
   private static boolean k = false;
   public static boolean isGameStarted;
   public static boolean isVipUnlocked = true;
   private byte l;
   private static byte[] m = new byte[]{0, 0, 0, 0, 0};
   private SmsSender n = null;
   private byte o;
   private byte p;
   private byte q;
   private String[] r = new String[]{"01", "02", "03", "04", "05"};
   private byte[][] s = new byte[][]{{4, 1, 0}, {2, 1, 1}, {2, 1, 2}, {2, 1, 3}, {2, 1, 4}};
   private String[][] t = new String[][]{
      {
            "Kích hoạt",
            "Bạn muốn khám phá bí mật của vương quốc sủng vật, dẫn dắt thú yêu chiến đấu, tiến hóa, ấp trứng? Chỉ cần 1 tin nhắn 15000đ để kích hoạt trò chơi, chỉ nhắn tin 1 lần cho tất cả các lượt chơi. Bạn có muốn nhắn tin không?"
      },
      {"Tất trúng cầu", "Chỉ cần nhắn 1 tin nhắn 10000đ, bạn sẽ sở hữu 1 tất trúng cầu, tỷ lệ 100% bắt được sủng vật? Bạn có muốn nhắn tin không?"},
      {"Mua sắm kim tiền", "Kiếm tiền vất vả, vật phẩm đắt đỏ? Chỉ cần nhắn 1 tin nhắn 10000đ bạn sẽ đạt được 10000 kim tiền. Bạn có muốn nhắn tin không?"},
      {
            "Mua đẳng cấp",
            "Thăng cấp chậm chạp, kẻ địch lại quá mạnh? Chỉ cần 1 tin nhắn 10000đ, tất cả sủng vật trong ba lô của bạn đều được thăng lên 5 cấp. Bạn có muốn nhắn tin không?"
      },
      {"Mua sắm huy hiệu", "Kiếm huy hiệu khó khăn? Chỉ cần 1 tin nhắn 10000đ, bạn sẽ đạt được 10 huy hiệu. Bạn có muốn nhắn tin không?"}
   };
   private BillingCanvas u;

   public abstract void b();

   public abstract void b(Graphics var1);

   public abstract boolean d();

   public abstract void f();

   public abstract void setScreenMode(byte var1);

   public final void s() {
      if (!g) {
         if (i == null || j == null) {
            i = new Timer();
            j = new CanvasRepaintTimerTask();
         }

         i.schedule(j, 10L, 200L);
         g = true;
      }
   }

   protected static void t() {
      if (j != null) {
         j.cancel();
         j = null;
      }

      if (i != null) {
         i.cancel();
         i = null;
         System.gc();
      }

      g = false;
      k = true;
   }

   public static void resumeGame() {
      k = false;
   }

   public static boolean isPaused() {
      return k;
   }

   public static void setScreenSize(short var0, short var1) {
      screenWidth = var0;
      screenHeight = var1;
   }

   public static short getScreenWidth() {
      return screenWidth;
   }

   public static short getScreenHeight() {
      return screenHeight;
   }

   public static short getHalfWidth() {
      return (short)(screenWidth / 2);
   }

   public static short getHalfHeight() {
      return (short)(screenHeight / 2);
   }

   public static void resetFrameDelay() {
      frameDelayMs = 33;
   }

   public static int getFrameDelay() {
      return frameDelayMs;
   }

   public static void setKeyDelay(int var0) {
      keyDelay = var0;
   }

   public static int getKeyDelay() {
      return keyDelay;
   }

   public static Font getSmallFont() {
      if (smallFont == null) {
         smallFont = Font.getFont(0, 0, 8);
      }

      return smallFont;
   }

   public static Font getMediumFont() {
      if (mediumFont == null) {
         mediumFont = Font.getFont(0, 0, 16);
      }

      return mediumFont;
   }

   public static int getFontCharWidth() {
      return smallFont == null ? 18 : smallFont.stringWidth("Sủng");
   }

   public static int getFontHeight() {
      return smallFont.getHeight();
   }

   public static String getString(int var0) {
      return var0 == 0 ? "" : GameDatabase.strings[var0];
   }

   public static String a(int var0, int[] var1) {
      if (var0 == 0) {
         return "";
      }

      int var2 = 0;
      String var3 = "";
      int var4;
      if ((var4 = getString(var0).indexOf("%s", 0)) == -1) {
         return getString(var0);
      }

      int var5;
      for (var5 = 0; var4 != -1; var4 = getString(var0).indexOf("%s", var5)) {
         var3 = var3 + getString(var0).substring(var5, var4) + var1[var2];
         var2++;
         var5 = var4 + 2;
      }

      return var3 + getString(var0).substring(var5);
   }

   public static String a(String var0, int[] var1) {
      if (var0.equals("")) {
         return "";
      }

      int var2 = 0;
      String var3 = "";
      int var4;
      if ((var4 = var0.indexOf("%s", 0)) == -1) {
         return var0;
      }

      int var5;
      for (var5 = 0; var4 != -1; var4 = var0.indexOf("%s", var5)) {
         var3 = var3 + var0.substring(var5, var4) + var1[var2];
         var2++;
         var5 = var4 + 2;
      }

      return var3 + var0.substring(var5);
   }

   public static String a(int var0, String[] var1) {
      if (var0 == 0) {
         return "";
      }

      int var2 = 0;
      String var3 = "";
      int var4;
      if ((var4 = getString(var0).indexOf("%s", 0)) == -1) {
         return getString(var0);
      }

      int var5;
      for (var5 = 0; var4 != -1; var4 = getString(var0).indexOf("%s", var5)) {
         var3 = var3 + getString(var0).substring(var5, var4) + var1[var2];
         var2++;
         var5 = var4 + 2;
      }

      return var3 + getString(var0).substring(var5);
   }

   public static void a(Graphics var0, Image var1, String var2, int var3, int var4, int var5, int var6) {
      for (int var7 = 0; var7 < var2.length(); var7++) {
         char var8;
         if (Character.isDigit(var8 = var2.charAt(var7))) {
            var8 = (char)(var8 - '0');
         } else {
            switch (var8) {
               case '+':
                  var8 = '\n';
                  break;
               case '-':
                  var8 = '\n';
            }
         }

         var0.drawRegion(var1, var8 * var5, 0, var5, var6, 0, var3 - ((var2.length() - 1 - (var7 << 1)) * var5 >> 1), var4, 20);
      }
   }

   public static boolean H() {
      return U != -1;
   }

   public void l() {
   }

   public void m() {
   }

   public static boolean I() {
      return U == -1 ? true : h[U][0] == 1;
   }

   public static boolean J() {
      return U == -1 ? true : h[U][0] == 2;
   }

   public static boolean b(int var0, int var1) {
      if (U == -1) {
         return false;
      } else if (var1 != h[U][2]) {
         return true;
      } else {
         return h[U][1] == -1 ? true : h[U][1] == var0;
      }
   }

   public static void c(int var0, int var1) {
      if (U != -1) {
         if (var1 == -1) {
            h[U][2] = 0;
         }

         h[U][var0] = (byte)var1;
      }
   }

   public static byte K() {
      return U == -1 ? -1 : h[U][1];
   }

   public final void onSmsResult(boolean var1) {
      if (this.l == 4) {
         if (var1) {
            BaseScreen var3 = this;
            this.p++;
            m[var3.o]++;
            System.out.println(" curNum = " + var3.p + " tolNum = " + var3.q);
            if (var3.p >= var3.q) {
               switch (var3.o) {
                  case 0:
                     isVipUnlocked = true;
                     Player.getInstance().addGold(2000);
                     Player.getInstance().c(1, 5, (byte)0);
                     Player.getInstance().c(4, 5, (byte)0);
                     Player.getInstance().c(11, 2, (byte)0);
                     Player.getInstance().addArenaPoints(5);
                     OverworldScreen.a().b[WorldManager.a(9, 0)][5] = 3;
                     OverworldScreen.a().a[5].setState((byte)3);
                     break;
                  case 1:
                     Player.getInstance().c(0, 1, (byte)0);
                     break;
                  case 2:
                     Player.getInstance().addGold(10000);
                     break;
                  case 3:
                     WorldManager.G = 0;
                     if (WorldManager.F == null) {
                        WorldManager.F = new Vector();
                     }

                     if (WorldManager.E == null) {
                        WorldManager.E = new Vector();
                     }

                     WorldManager.F.removeAllElements();
                     WorldManager.E.removeAllElements();

                     for (int var2 = 0; var2 < Player.getInstance().partyPetCount; var2++) {
                        if (Player.getInstance().petParty[var2].getLevel() == 50) {
                           Player.getInstance().petParty[var2].checkEvolution();
                        } else {
                           Player.getInstance().petParty[var2].x();
                           if (Player.getInstance().petParty[var2].getLevel() + 5 >= 50) {
                              Player.getInstance().petParty[var2].h(50 - Player.getInstance().petParty[var2].getLevel());
                           } else {
                              Player.getInstance().petParty[var2].h(5);
                           }

                           Player.getInstance().petParty[var2].I();
                           if (Player.getInstance().petParty[var2].E() < 5
                              && Player.getInstance().petParty[var2].E() < Player.getInstance().petParty[var2].getLevel() / 10 + 1) {
                              WorldManager.E.addElement(Player.getInstance().petParty[var2]);
                              WorldManager.F.addElement("" + var2);
                           }
                        }
                     }

                     if (WorldManager.E.size() <= 0) {
                        WorldManager.G = 2;
                     } else {
                        WorldManager.G = 1;
                     }
                     break;
                  case 4:
                     Player.getInstance().addArenaPoints(10);
               }
            }

            var3.d((byte)2);
            return;
         }

         this.d((byte)3);
      }
   }

   private boolean a() {
      if (this.n == null) {
         try {
            this.n = new SmsSender(this);
            this.n.a("sms://");
         } catch (ClassNotFoundException var1) {
            return false;
         }
      }

      switch (this.o) {
         case 0:
            this.a(this.o);
            break;
         case 1:
            this.a(this.o);
            break;
         case 2:
            this.a(this.o);
            break;
         case 3:
            this.a(this.o);
            break;
         case 4:
            this.a(this.o);
      }

      return true;
   }

   public final boolean c(byte var1) {
      this.o = var1;
      switch (var1) {
         case 0:
            this.q = 1;
            break;
         case 1:
            this.q = 1;
            break;
         case 2:
            this.q = 1;
            break;
         case 3:
            this.q = 1;
            break;
         case 4:
            this.q = 1;
      }

      this.p = 0;
      return true;
   }

   public final void d(byte var1) {
      while (true) {
         if (var1 != 5 && var1 != 0) {
            this.interactionController.aK();
         }

         switch (var1) {
            case 1:
               System.out.println(" " + a(513, new int[]{this.q, this.p}));
               this.interactionController.d(a(513, new int[]{this.q, this.p}));
            case 2:
            default:
               break;
            case 3:
               System.out.println(" " + getString(516));
               this.interactionController.d(getString(516));
               break;
            case 4:
               System.out.println(" " + getString(514));
               this.interactionController.d(getString(514));
               break;
            case 5:
               T = false;
               this.interactionController.aL();
         }

         this.l = var1;
         if (var1 != 5) {
            return;
         }

         var1 = 0;
      }
   }

   public final int L() {
      return this.o;
   }

   public final boolean M() {
      return this.p >= this.q;
   }

   public final byte N() {
      return this.l;
   }

   public final byte O() {
      return this.q;
   }

   public final void g(int var1) {
      T = true;
      if (var1 == 1) {
         this.d((byte)4);
         this.onSmsResult(true);
      } else {
         if (var1 == 2) {
            this.d((byte)5);
         }
      }
   }

   public final void h(int var1) {
      switch (this.l) {
         case 1:
            this.g(var1);
            return;
         case 3:
            if (var1 == 1 || var1 == 2) {
               this.d((byte)5);
            }
         case 2:
         case 4:
      }
   }

   private void a(int var1) {
      SmsConfig.a(GameMIDLet.instance);
      SmsConfig.a(var1);
      this.u = new BillingCanvas(GameMIDLet.instance, GameCanvas.activeCanvas, "", "", this.r[var1], this.s[var1][0], this.t[var1][0], SmsConfig.c[var1], "");
      Display.getDisplay(GameMIDLet.instance).setCurrent(this.u);
      this.u.a(this);
   }

   public final void onBillingResult(boolean var1) {
      this.onSmsResult(var1);
   }
}
