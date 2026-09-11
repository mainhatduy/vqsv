/*
 * Decompiled with CFR 0.152.
 */
package game;

import game.e;
import javax.microedition.lcdui.Display;
import javax.microedition.midlet.MIDlet;

public class GameMIDLet
extends MIDlet {
    private Display b;
    private e c;
    public static GameMIDLet a;

    public GameMIDLet() {
        a = this;
        this.b = Display.getDisplay(this);
        this.c = e.a(this);
        this.b.setCurrent(this.c);
    }

    public void startApp() {
    }

    public void pauseApp() {
    }

    public void destroyApp(boolean bl) {
        this.c = null;
        System.gc();
        if (bl) {
            this.notifyDestroyed();
        }
    }
}

