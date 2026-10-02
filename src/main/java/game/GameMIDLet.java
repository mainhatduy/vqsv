/*
 * Recovered entry point. The public field a is part of the original binary ABI.
 * Edit this file to experiment; the build replaces only compiled classes.
 */
package game;

import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.Display;
import javax.microedition.midlet.MIDlet;

public class GameMIDLet
extends MIDlet {
    private Display display;
    private Canvas canvas;
    /** Original classes still reference this binary name. Use getInstance() in new code. */
    public static GameMIDLet a;

    public GameMIDLet() {
        a = this;
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

