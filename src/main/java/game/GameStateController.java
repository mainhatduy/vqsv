package game;

import java.io.InputStream;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;
import javax.microedition.media.Manager;
import javax.microedition.media.MediaException;
import javax.microedition.media.control.VolumeControl;

public final class GameStateController extends BaseScreen {
   private static GameStateController instance = null;
   private byte state;
   private byte previousState;
   private int k;
   private Image l;
   private BaseScreen activeScreen;
   private static int n = 0;
   private static int o = 10;
   private String[] p = new String[]{
      "Hỏa hệ khắc mộc hệ",
      "Mộc hệ khắc thổ hệ",
      "Thổ hệ khắc thủy hệ",
      "Thủy hệ khắc hỏa hệ",
      "Quỷ hệ khắc phong hệ",
      "Phong hệ khắc điện hệ",
      "Điện hệ khắc quỷ hệ"
   };
   private String[] q = new String[]{"Thuyền càng đi càng xa", "Bơi... bơi...", "Đích đến ngày càng gần", "Thuyền nhỏ đang chạy"};
   public long a = 0L;
   public long b = 0L;
   public long c = 0L;
   public long d = 0L;
   public long e = 0L;
   public long f = 0L;
   private boolean r = false;
   private String s = "";
   public byte g = 0;
   private javax.microedition.media.Player musicPlayer;
   private VolumeControl volumeControl;
   private static String v = null;

   public static GameStateController getInstance() {
      if (instance == null) {
         instance = new GameStateController();
         v = "0";
      }

      return instance;
   }

   public final void setInputEnabled(boolean var1) {
      if (var1) {
         this.c();
      } else {
         this.j();
      }
   }

   public final void c() {
      this.f = System.currentTimeMillis();
      this.setScreenMode((byte)3);
      super.setInputEnabled(true);
   }

   private void j() {
      this.setScreenMode((byte)1);
      super.setInputEnabled(false);
   }

   public final void setScreenMode(byte var1) {
      if (var1 < 24) {
         this.previousState = this.state;
         switch (this.state) {
            case 2:
            case 4:
            case 5:
            case 6:
            case 8:
            case 10:
            case 11:
            case 13:
            case 14:
            case 15:
            case 16:
            case 17:
            case 18:
            case 19:
            case 20:
            case 21:
            default:
               break;
            case 3:
               ScreenView.a().a = -1;
               break;
            case 7:
            case 9:
            case 12:
            case 22:
            case 23:
               n = 0;
         }

         this.state = (byte)var1;
         switch (var1) {
            case 2:
               if (WorldManager.a().c != null) {
                  WorldManager.a().c.J();
               }
               break;
            case 3:
               ScreenView.a().c(0, 19);
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 10:
            case 13:
            case 14:
            case 16:
            case 17:
            case 18:
            case 19:
            case 20:
            case 21:
            default:
               break;
            case 9:
               resumeGame();
               n = 0;
               this.s = this.p[EngineUtils.randomInt(this.p.length)];
               break;
            case 11:
               WorldManager.a().interactionController.g = true;
               break;
            case 12:
            case 22:
               resumeGame();
               n = 0;
            case 15:
               break;
            case 23:
               resumeGame();
               n = 0;
               this.s = this.q[EngineUtils.randomInt(this.q.length)];
         }

         this.k = 0;
      }
   }

   public final byte getState() {
      return this.state;
   }

   public final void g() {
      if (this.state != 9 && this.state != 22 && this.state != 23) {
         if (this.state != 2) {
            this.n();
            this.r = true;
            this.setScreenMode((byte)2);
            this.resetInputState();
         }
      } else {
         this.n();
         this.r = true;
         this.resetInputState();
      }
   }

   private void k() {
      this.a("0");
      this.setScreenMode(this.previousState);
      this.r = false;
      this.resetInputState();
   }

   public final boolean d() {
      this.s();
      this.interactionController = ScriptEngine.a();
      this.uiManager = UIManager.getInstance();
      this.interactionController.a(this);
      ImageCache.initialize();
      AnimationCache.initialize();
      GameDatabase.initialize();
      setKeyDelay(0);
      getSmallFont();
      WorldManager.a();
      WorldManager.i();
      t();
      return true;
   }

   public final void f() {
      if (this.activeScreen != null) {
         this.activeScreen.f();
         this.activeScreen = null;
      }
   }

   public final void b() {
      if (this.inputEnabled) {
         this.snapshotInput();
         switch (this.state) {
            case 2:
               if (this.isKeyPressed(262144)) {
                  this.k();
               }
               break;
            case 3:
               if (!isPaused()) {
                  this.d();
               }

               if (ScreenView.a().b && isPaused()) {
                  this.setScreenMode((byte)6);
               }
               break;
            case 4:
               if (this.isKeyPressed(131072)) {
                  this.j();
               } else if (this.isKeyPressed(262144)) {
                  this.k();
               }
            case 5:
            case 14:
            case 17:
            case 18:
            case 19:
            case 21:
            default:
               break;
            case 6:
               if (this.isKeyPressed(131072)) {
                  this.g = 2;
                  this.a(v);
                  this.setScreenMode((byte)7);
               } else if (this.isKeyPressed(262144)) {
                  this.g = 0;
                  this.setScreenMode((byte)7);
               }
               break;
            case 7:
               this.f();
               this.activeScreen = TitleScreen.a();
               this.activeScreen.d();
               this.setInputDelegate(this.activeScreen);
               this.setScreenMode((byte)8);
               break;
            case 8:
            case 11:
            case 13:
            case 20:
               if (this.activeScreen != null) {
                  this.activeScreen.b();
               }
               break;
            case 9:
            case 22:
            case 23:
               this.f();
               this.activeScreen = WorldManager.a();
               this.activeScreen.d();
               Player.U = false;
               this.setScreenMode((byte)11);
               if (this.r) {
                  GameStateController var1 = this;
                  if (this.state != 2) {
                     var1.setScreenMode((byte)2);
                  }
               }

               this.setInputDelegate(this.activeScreen);
               break;
            case 10:
               this.f();
               this.activeScreen = WorldManager.a();
               ((WorldManager)this.activeScreen).p();
               this.setInputDelegate(this.activeScreen);
               this.setScreenMode((byte)11);
               break;
            case 12:
               if (!isPaused()) {
                  this.activeScreen = null;
                  this.activeScreen = BattleScreen.a();
                  this.activeScreen.d();
                  this.setInputDelegate(this.activeScreen);
                  if (((BattleScreen)this.activeScreen).b == 0) {
                     ScreenView.a().c(-2013265920, 6);
                  } else if (((BattleScreen)this.activeScreen).b == 2) {
                     ScreenView.a().c(-2013265920, 8);
                  } else if (((BattleScreen)this.activeScreen).b == 1) {
                     ScreenView.a().c(-2013265920, 7);
                  }
               }

               if (isPaused()) {
                  ScreenView.a().b();
                  Player.U = false;
               }

               if (ScreenView.a().b) {
                  ((BattleScreen)this.activeScreen).g();
                  this.setScreenMode((byte)13);
               }
               break;
            case 15:
               this.k++;
               if (this.k >= 10) {
                  this.k = 0;
                  this.l = null;
                  this.setScreenMode((byte)6);
               }
               break;
            case 16:
               this.k++;
               if (this.k >= 10) {
                  this.l = null;
                  this.setScreenMode((byte)6);
               }
         }

         if (WorldManager.a().f == 3 && WorldManager.a().g == 7 && this.c == 0L && this.a != 0L) {
            this.b = System.currentTimeMillis();
         }

         this.e = System.currentTimeMillis();
      }
   }

   public final void b(Graphics var1) {
      if (this.inputEnabled) {
         var1.setFont(getSmallFont());
         switch (this.state) {
            case 2:
               var1.setColor(0);
               var1.fillRect(0, 0, getScreenWidth(), getScreenHeight());
               var1.setColor(16777215);
               var1.drawString("Trò chơi tạm dừng", getScreenWidth() >> 1, getHalfHeight(), 33);
               var1.drawString("Phản hồi", getScreenWidth() - 2, getScreenHeight() - 2, 40);
               return;
            case 3:
               var1.setColor(16777215);
               var1.fillRect(0, 0, getScreenWidth(), getScreenHeight());
               ScreenView.a().a(var1);
               return;
            case 4:
               return;
            case 5:
            case 14:
            case 17:
            case 18:
            case 19:
            case 21:
            case 22:
            default:
               break;
            case 6:
               var1.setColor(0);
               var1.fillRect(0, 0, getScreenWidth(), getScreenHeight());
               var1.setFont(getSmallFont());
               var1.setColor(16777215);
               var1.drawString(getString(8), getScreenWidth() >> 1, getHalfHeight() - 12, 17);
               var1.drawString(getString(4), 2, getScreenHeight() - 2, 36);
               var1.drawString(getString(5), getScreenWidth() - 2, getScreenHeight() - 2, 40);
               var1.setColor(16739328);
               var1.drawString(getString(9), getScreenWidth() >> 1, getHalfHeight() + 12, 17);
               return;
            case 7:
               return;
            case 8:
            case 11:
            case 13:
            case 20:
               if (this.activeScreen != null) {
                  this.activeScreen.b(var1);
               }
               break;
            case 9:
               Graphics var6 = var1;
               GameStateController var5 = this;
               if (Player.U) {
                  var6.setColor(0);
                  var6.fillRect(0, 0, getScreenWidth(), getScreenHeight());
                  if (n % 4 == 3) {
                     Player.getInstance().setAnimation((byte)1, (byte)-1, false);
                  } else {
                     Player.getInstance().setAnimation((byte)(n % 4), (byte)-1, false);
                  }

                  byte var3;
                  Player.getInstance().facingDirection = var3 = (byte)(n % 4);
                  Player.getInstance().renderInWorld(var6, MapEngine.getInstance().cameraX, MapEngine.getInstance().cameraY - n);
               } else {
                  var6.setColor(0);
                  var6.fillRect(0, 0, getScreenWidth(), getScreenHeight());
               }

               if (n < 148) {
                  n = n + o;
               }

               if (n > 148) {
                  n = 148;
               }

               if (!Player.U) {
                  var6.setColor(0);
                  var6.fillRect(45, getScreenHeight() - 48, 150, 5);
                  var6.setColor(7877410);
                  var6.fillRect(46, getScreenHeight() - 47, 148, 3);
                  var6.setColor(16707204);
                  var6.fillRect(46, getScreenHeight() - 47, n, 3);
                  var6.setColor(16777215);
                  var6.drawString(var5.s, getScreenWidth() >> 1, getScreenHeight() - 70, 17);
               }

               return;
            case 10:
               return;
            case 12:
               WorldManager.a().b.a(var1);
               if (isPaused()) {
                  ScreenView.a().a(var1);
                  Player.U = false;
                  return;
               }
               break;
            case 15:
               return;
            case 16:
               return;
            case 23:
               Graphics var2 = var1;
               GameStateController var4 = this;
               var2.setColor(0);
               var2.fillRect(0, 0, getScreenWidth(), getScreenHeight());
               var2.setColor(16777215);
               var2.drawString(var4.s, getScreenWidth() >> 1, getScreenHeight() >> 1, 17);
               return;
         }
      }
   }

   private void a(String var1) {
      v = var1;
      if (this.g != 0) {
         try {
            if (this.musicPlayer != null) {
               if (this.o()) {
                  this.musicPlayer.start();
                  return;
               }
            } else {
               getInstance().getClass();
               InputStream var2 = ResourceStream.openResource("/data/sound/" + var1 + ".mid");
               this.musicPlayer = Manager.createPlayer(var2, "audio/midi");
               this.musicPlayer.realize();
               this.volumeControl = (VolumeControl)this.musicPlayer.getControl("VolumeControl");
               this.volumeControl.setLevel(this.g * 30);
               this.musicPlayer.prefetch();
               this.musicPlayer.setLoopCount(-1);
               this.musicPlayer.start();
               var2.close();
            }
         } catch (MediaException var3) {
            DebugLogger.a(var3, "startMusic");
         } catch (Exception var4) {
            DebugLogger.a(var4, "/data/sound" + var1 + ".mid");
         }
      }
   }

   private void n() {
      if (this.musicPlayer != null) {
         this.musicPlayer.deallocate();
         this.musicPlayer.close();
         this.musicPlayer = null;
      }
   }

   private boolean o() {
      try {
         this.musicPlayer.prefetch();
         if (this.musicPlayer.getState() == 300) {
            return true;
         }
      } catch (Exception var1) {
      }

      return false;
   }

   private void p() {
      if (this.volumeControl != null) {
         this.volumeControl.setLevel(this.g * 30);
      }
   }

   public final void h() {
      this.g++;
      if (this.g > 3) {
         this.g = 3;
      }

      if (this.g > 0) {
         this.p();
         this.a(v);
      }
   }

   public final void i() {
      this.g--;
      if (this.g < 0) {
         this.g = 0;
      }

      if (this.g == 0) {
         this.n();
      } else {
         this.p();
      }
   }
}
