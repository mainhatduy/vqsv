/*
 * Decompiled with CFR 0.152.
 */
package game;

import game.GameCanvas;
import javax.microedition.lcdui.Display;
import javax.microedition.midlet.MIDlet;

/**
 * Main J2ME MIDlet entry point; initializes Display and manages application lifecycle.
 */
public class GameMIDLet
extends MIDlet {
    private Display b;
    private GameCanvas c;
    public static GameMIDLet a;

    public GameMIDLet() {
        a = this;
        this.b = Display.getDisplay(this);
        this.c = GameCanvas.a(this);
        this.b.setCurrent(this.c);
    }

    public void startApp() {
    }

    public void pauseApp() {
    }

    public void destroyApp(boolean flag) {
        this.c = null;
        System.gc();
        if (flag) {
            this.notifyDestroyed();
        }
    }
}

