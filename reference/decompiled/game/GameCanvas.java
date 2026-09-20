/*
 * Decompiled with CFR 0.152.
 */
package game;

import game.GameMIDLet;
import game.GameStateController;
import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.Graphics;

/**
 * GameCanvas - Primary LCDUI Canvas, user input dispatcher, and main animation/game loop thread.
 */
public final class GameCanvas
extends Canvas
implements Runnable {
    private static GameCanvas b = null;
    private static GameMIDLet c;
    private static BaseInputHandler d;
    private GameStateController e;
    public static GameCanvas a;
    private long f = 0L;
    private long g = 0L;
    private long h = 0L;
    private int i = 0;

    public static GameCanvas create(GameMIDLet midlet) {
        if (b == null) {
            b = new GameCanvas(midlet);
        }
        return b;
    }

    public static GameCanvas getInstance() {
        return b;
    }

    private GameCanvas(GameMIDLet midlet) {
        a = this;
        this.setFullScreenMode(true);
        c = midlet;
        BaseScreen.resetFrameDelay();
        BaseScreen.setScreenSize((short)this.getWidth(), (short)this.getHeight());
        this.e = game.GameStateController.getInstance();
        this.e.c();
        midlet = this.e;
        if (d != null) {
            d.c(false);
            d = null;
        }
        if (midlet != null) {
            ((BaseInputHandler)midlet).c(true);
            d = midlet;
        }
        new Thread(this).start();
    }

    protected final void hideNotify() {
        if (!BaseScreen.T && this.e.getState() > 1) {
            this.e.g();
        }
    }

    protected final void paint(Graphics g) {
        if (this.e.getState() > 1) {
            this.e.render(g);
        }
    }

    public final void run() {
        while (this.e.getState() > 1) {
            this.f = System.currentTimeMillis();
            this.e.update();
            this.repaint();
            this.serviceRepaints();
            this.g = System.currentTimeMillis();
            this.h = this.g - this.f;
            if (this.h > (long)BaseScreen.getFrameDelay()) {
                this.h = BaseScreen.getFrameDelay();
            }
            try {
                Thread.sleep((long)BaseScreen.getFrameDelay() - this.h);
            }
            catch (InterruptedException interruptedException) {}
        }
        c.destroyApp(true);
    }

    protected final void keyPressed(int keyCode) {
        if (this.e != null) {
            this.e.onKeyPressed(keyCode);
        }
    }

    protected final void keyReleased(int keyCode) {
        if (this.e != null) {
            this.e.onKeyReleased(keyCode);
        }
    }

    protected final void pointerPressed(int x, int y) {
        this.i = 0;
        if (this.e.getState() == 13) {
            if (EngineUtils.a(x, y, 38, 225, 50)) {
                this.i = -6;
            } else if (EngineUtils.a(x, y, 150, 225, 50)) {
                this.i = -7;
            }
        } else if (EngineUtils.a(x, y, 0, 280, 40)) {
            this.i = -6;
        } else if (EngineUtils.a(x, y, 200, 280, 40)) {
            this.i = -7;
        }
        this.keyPressed(this.i);
    }

    protected final void pointerReleased(int x, int y) {
        this.keyReleased(this.i);
        if (this.e != null) {
            this.e.d(x, y);
        }
    }
}

