package game;

import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.Graphics;

public final class GameCanvas extends Canvas implements Runnable {
   private static GameCanvas instance = null;
   private static GameMIDLet midlet;
   private static BaseInputHandler activeInputHandler;
   private GameStateController stateController;
   public static GameCanvas activeCanvas;
   private long frameStartTimeMs = 0L;
   private long frameEndTimeMs = 0L;
   private long frameWorkTimeMs = 0L;
   private int i = 0;

   public static GameCanvas getInstance(GameMIDLet var0) {
      if (instance == null) {
         instance = new GameCanvas(var0);
      }

      return instance;
   }

   public static GameCanvas getInstance() {
      return instance;
   }

   private GameCanvas(GameMIDLet var1) {
      activeCanvas = this;
      this.setFullScreenMode(true);
      midlet = var1;
      BaseScreen.resetFrameDelay();
      BaseScreen.setScreenSize((short)this.getWidth(), (short)this.getHeight());
      this.stateController = GameStateController.getInstance();
      this.stateController.c();
      GameStateController var2 = this.stateController;
      if (activeInputHandler != null) {
         activeInputHandler.setInputEnabled(false);
         activeInputHandler = null;
      }

      if (var2 != null) {
         var2.setInputEnabled(true);
         activeInputHandler = var2;
      }

      new Thread(this).start();
   }

   public final void hideNotify() {
      if (!BaseScreen.T && this.stateController.getState() > 1) {
         this.stateController.g();
      }
   }

   protected final void paint(Graphics var1) {
      if (this.stateController.getState() > 1) {
         this.stateController.b(var1);
      }
   }

   public final void run() {
      while (this.stateController.getState() > 1) {
         this.frameStartTimeMs = System.currentTimeMillis();
         this.stateController.b();
         this.repaint();
         this.serviceRepaints();
         this.frameEndTimeMs = System.currentTimeMillis();
         this.frameWorkTimeMs = this.frameEndTimeMs - this.frameStartTimeMs;
         if (this.frameWorkTimeMs > BaseScreen.getFrameDelay()) {
            this.frameWorkTimeMs = BaseScreen.getFrameDelay();
         }

         try {
            Thread.sleep(BaseScreen.getFrameDelay() - this.frameWorkTimeMs);
         } catch (InterruptedException var1) {
         }
      }

      midlet.destroyApp(true);
   }

   public final void keyPressed(int var1) {
      if (this.stateController != null) {
         this.stateController.onKeyPressed(var1);
      }
   }

   public final void keyReleased(int var1) {
      if (this.stateController != null) {
         this.stateController.onKeyReleased(var1);
      }
   }

   public final void pointerPressed(int var1, int var2) {
      this.i = 0;
      if (this.stateController.getState() == 13) {
         if (EngineUtils.a(var1, var2, 38, 225, 50)) {
            this.i = -6;
         } else if (EngineUtils.a(var1, var2, 150, 225, 50)) {
            this.i = -7;
         }
      } else if (EngineUtils.a(var1, var2, 0, 280, 40)) {
         this.i = -6;
      } else if (EngineUtils.a(var1, var2, 200, 280, 40)) {
         this.i = -7;
      }

      this.keyPressed(this.i);
   }

   public final void pointerReleased(int var1, int var2) {
      this.keyReleased(this.i);
      if (this.stateController != null) {
         this.stateController.onPointerEvent(var1, var2);
      }
   }
}
