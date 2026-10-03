package game;

import javax.microedition.lcdui.Canvas;

/** Creates the canvas compiled from the recovered engine source. */
final class GameCanvasFactory {
    private GameCanvasFactory() {}

    static Canvas create(GameMIDLet midlet) {
        return GameCanvas.getInstance(midlet);
    }
}
