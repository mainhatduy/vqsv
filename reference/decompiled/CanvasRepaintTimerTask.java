/*
 * Decompiled with CFR 0.152.
 */
import game.GameCanvas;
import java.util.TimerTask;

/**
 * CanvasRepaintTimerTask - Periodic TimerTask scheduling canvas repaints.
 */
public final class CanvasRepaintTimerTask
extends TimerTask {
    public final void run() {
        e.getInstance().repaint();
    }
}

