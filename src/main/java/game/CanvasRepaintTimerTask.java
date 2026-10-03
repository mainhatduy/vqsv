package game;

import java.util.TimerTask;

public final class CanvasRepaintTimerTask extends TimerTask {
   public final void run() {
      GameCanvas.getInstance().repaint();
   }
}
