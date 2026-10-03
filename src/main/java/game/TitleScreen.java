package game;

import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

public final class TitleScreen extends BaseScreen {
   private static TitleScreen a;
   private static int[] b = null;
   private static int c;
   private byte d = 0;
   private Image e;
   private Image f;
   private byte g = 10;
   private int[][] h = new int[this.g][5];
   private int[] i = new int[]{28, 3, 21, 22, 50, 5, 17, 17};
   private byte j = 30;
   private byte k = 30;
   private boolean l = false;
   private int m = 0;
   private int[] n = new int[this.g];
   private int[] o = new int[]{3958719, 3958719, 3958719, 7248110, 7248110, 9943031};

   public static TitleScreen a() {
      if (a == null) {
         a = new TitleScreen();
      }

      return a;
   }

   public final boolean d() {
      this.interactionController = ScriptEngine.a();
      this.uiManager = UIManager.getInstance();
      this.interactionController.a(this);
      c = 0;
      if (isGameStarted) {
         b = new int[]{504, 503, 505, 506, 507, 508};
      } else {
         b = new int[]{503, 505, 506, 507, 508};
      }

      if (this.e == null) {
         this.e = EngineUtils.loadImage("/data/img/", "img_833");
      }

      if (this.f == null) {
         this.f = EngineUtils.loadImage("/data/tex/", "menu");
      }

      this.c();
      this.setScreenMode((byte)0);
      return true;
   }

   private void c() {
      for (int var1 = 0; var1 < this.g; var1++) {
         this.h[var1][0] = -EngineUtils.randomInt(this.j);
         this.h[var1][1] = getScreenHeight() + EngineUtils.randomInt(this.k);
         this.h[var1][2] = EngineUtils.randomInt(2);
         this.h[var1][3] = EngineUtils.randomRange(1, 5);
         this.h[var1][4] = EngineUtils.randomRange(3, 5);
      }
   }

   private void e() {
      WorldManager.D = false;
      WorldManager.G = 0;
      OverworldScreen.f = true;
      WorldManager.x = false;
      WorldManager.a().f = 0;
      WorldManager.a().g = 0;
      if (WorldManager.a().M != null) {
         WorldManager.a().M.e();
      }

      if (WorldManager.a().c != null) {
         WorldManager.a().c.releaseParty();
      }

      if (this.interactionController != null) {
         this.interactionController.b();
      }

      Player.getInstance().y = false;
      isGameStarted = false;
      GameStateController.getInstance().setScreenMode((byte)9);
   }

   public final void b() {
      if (this.inputEnabled) {
         this.snapshotInput();
         switch (this.screenMode) {
            case 0:
               TitleScreen var1 = this;
               if (this.isKeyPressed(16400)) {
                  if (--c < 0) {
                     c = b.length - 1;
                  }
               } else if (var1.isKeyPressed(32832)) {
                  if (++c > b.length - 1) {
                     c = 0;
                  }
               } else if (var1.isKeyPressed(196640)) {
                  if (isGameStarted) {
                     switch (c) {
                        case 0:
                           TitleScreen var2 = var1;
                           if (WorldManager.a().M != null) {
                              WorldManager.a().M.e();
                           }

                           if (WorldManager.a().c != null) {
                              WorldManager.a().c.releaseParty();
                           }

                           if (var2.interactionController != null) {
                              var2.interactionController.b();
                           }

                           Player.getInstance().y = false;
                           GameStateController.getInstance().setScreenMode((byte)9);
                           GameStateController.getInstance().setScreenMode((byte)9);
                           break;
                        case 1:
                           var1.setScreenMode((byte)5);
                           break;
                        case 2:
                           var1.setScreenMode((byte)1);
                           break;
                        case 3:
                           var1.setScreenMode((byte)2);
                           break;
                        case 4:
                           var1.setScreenMode((byte)3);
                           break;
                        case 5:
                           var1.setScreenMode((byte)4);
                     }
                  } else {
                     switch (c) {
                        case 0:
                           var1.e();
                           break;
                        case 1:
                           var1.setScreenMode((byte)1);
                           break;
                        case 2:
                           var1.setScreenMode((byte)2);
                           break;
                        case 3:
                           var1.setScreenMode((byte)3);
                           break;
                        case 4:
                           var1.setScreenMode((byte)4);
                     }
                  }
               }

               var1.interactionController.f();
               var1 = this;
               if (this.l) {
                  var1.m++;
                  if (var1.m >= 100) {
                     for (int var4 = 0; var4 < var1.n.length; var4++) {
                        var1.n[var4] = 0;
                     }

                     var1.m = 0;
                     var1.c();
                     var1.l = false;
                  }
               }
               break;
            case 1:
               this.interactionController.t();
               break;
            case 2:
               this.interactionController.p();
               break;
            case 3:
               this.interactionController.r();
               break;
            case 4:
               if (this.isKeyPressed(131072)) {
                  GameStateController.getInstance().setScreenMode((byte)1);
               } else if (this.isKeyPressed(262144)) {
                  this.setScreenMode((byte)0);
               }
               break;
            case 5:
               if (this.isKeyPressed(131104)) {
                  WorldManager.a();
                  WorldManager.o();
                  this.e();
               } else if (this.isKeyPressed(262144)) {
                  this.setScreenMode((byte)0);
                  this.uiManager.closeUI("/data/ui/msgtip.ui");
               }
         }

         this.uiManager.update();
      }
   }

   public final void b(Graphics var1) {
      switch (this.screenMode) {
         case 0:
            if (this.f != null) {
               var1.drawImage(this.f, 0, 0, 20);
            }

            String var10002 = getString(b[c]);
            int var10003 = (getScreenWidth() - getSmallFont().stringWidth(getString(b[c]))) / 2;
            int var6 = getScreenHeight() - 20;
            int var5 = var10003;
            String var10 = var10002;
            Graphics var3 = var1;
            TitleScreen var7 = this;
            var3.setColor(var7.o[var7.d]);
            var3.drawString(var10, var5, var6 - 1, 36);
            var3.drawString(var10, var5, var6 + 1, 36);
            var3.drawString(var10, var5 - 1, var6, 36);
            var3.drawString(var10, var5 + 1, var6, 36);
            var3.setColor(16777215);
            var3.drawString(var10, var5, var6, 36);
            var7.d++;
            if (var7.d >= 6) {
               var7.d = 0;
            }

            var3 = var1;
            var7 = this;
            if (!this.l) {
               for (int var11 = 0; var11 < var7.g; var11++) {
                  var3.drawRegion(
                     var7.e,
                     var7.i[var7.h[var11][2] << 2],
                     var7.i[(var7.h[var11][2] << 2) + 1],
                     var7.i[(var7.h[var11][2] << 2) + 2],
                     var7.i[(var7.h[var11][2] << 2) + 3],
                     0,
                     var7.h[var11][0],
                     var7.h[var11][1],
                     20
                  );
                  var7.h[var11][0] = var7.h[var11][0] + var7.h[var11][3];
                  var7.h[var11][1] = var7.h[var11][1] - var7.h[var11][4];
                  if (var7.h[var11][0] > getScreenWidth() || var7.h[var11][1] < 0) {
                     var7.n[var11]++;
                  }
               }

               int var12 = 0;

               while (var12 < var7.n.length && var7.n[var12] > 0) {
                  var12++;
               }

               if (var12 >= var7.n.length) {
                  var7.l = true;
               }
            }
            break;
         case 1:
         case 2:
         case 3:
            Graphics var2 = var1;

            for (int var4 = 0; var4 < getScreenHeight() / 20; var4++) {
               if (var4 % 2 == 0) {
                  var2.setColor(10440998);
               } else {
                  var2.setColor(12082732);
               }

               var2.fillRect(0, var4 * 20, getScreenWidth(), 20);
            }
            break;
         case 4:
            var1.setColor(0);
            var1.fillRect(0, 0, getScreenWidth(), getScreenHeight());
            var1.setColor(16777215);
            var1.drawString("Bạn có muốn thoát không?", getHalfWidth(), getHalfHeight() - 10, 17);
            var1.drawString("", 2, getScreenHeight(), 36);
            var1.drawString("Không", getScreenWidth() - 2, getScreenHeight(), 40);
      }

      this.uiManager.render(var1);
   }

   public final void f() {
      this.e = null;
      this.f = null;
      this.uiManager.releaseAll();
   }

   public final void setScreenMode(byte var1) {
      this.screenMode = var1;
      switch (var1) {
         case 0:
            return;
         case 1:
            this.interactionController.s();
            return;
         case 2:
            this.interactionController.o();
            return;
         case 3:
            this.interactionController.q();
            return;
         case 5:
            this.interactionController.H();
            this.interactionController.a("Có chắc chắn xóa dữ liệu cũ để chơi mới không?");
         case 4:
      }
   }
}
