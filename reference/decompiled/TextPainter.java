/*
 * Decompiled with CFR 0.152.
 */
import javax.microedition.lcdui.Graphics;

/**
 * TextPainter - Text drawing helper with color formatting and clipping delegation.
 */
public final class TextPainter {
    public static void a(String text, int n2, int n3, int n4, int n5, Graphics g) {
        g.setColor(n5);
        TextLayoutHelper.a(g, text, n2, n3, n4);
    }

    public static int a(String text, int n2, int n3) {
        return TextLayoutHelper.a(text, 0, n3 + 0);
    }
}

