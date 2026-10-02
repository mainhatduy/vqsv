package game;

import javax.microedition.lcdui.Canvas;

/** Keeps the original game.e.a(GameMIDLet) entry point at the compatibility boundary. */
final class GameCanvasFactory {
    private GameCanvasFactory() {}

    static Canvas create(GameMIDLet midlet) {
        return e.a(midlet);
    }
}
