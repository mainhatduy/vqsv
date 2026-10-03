/*
 * Entry point for the source-built game and desktop development controls.
 */
package game;

import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.Display;
import javax.microedition.midlet.MIDlet;

public class GameMIDLet
extends MIDlet {
    private Display display;
    private Canvas canvas;
    public static GameMIDLet instance;
    /** Legacy alias retained for existing development integrations. */
    public static GameMIDLet a;

    public GameMIDLet() {
        a = this;
        instance = this;
        this.display = Display.getDisplay(this);
        this.canvas = GameCanvasFactory.create(this);
        this.display.setCurrent(this.canvas);
        try {
            Class.forName("GameSpeedConfig").getMethod("init").invoke(null);
        } catch (Throwable initializationError) {
            initializationError.printStackTrace();
        }
    }

    public static GameMIDLet getInstance() {
        return a;
    }

    public void startApp() {
        System.out.println("[VQSV Dev] Running the editable GameMIDLet.");
    }

    public void pauseApp() {
    }

    public void destroyApp(boolean notifyOnDestroy) {
        this.canvas = null;
        System.gc();
        if (notifyOnDestroy) {
            this.notifyDestroyed();
        }
    }
}
