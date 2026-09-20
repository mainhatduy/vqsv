/*
 * Decompiled with CFR 0.152.
 */
/**
 * Rectangle - 2D bounding box with x, y coordinates and dimensions.
 */
public final class Rectangle {
    public int x;
    public int y;
    public int width;
    public int height;

    public Rectangle() {
        this.x = 0;
        this.y = 0;
        this.width = 0;
        this.height = 0;
    }

    public Rectangle(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }
}

