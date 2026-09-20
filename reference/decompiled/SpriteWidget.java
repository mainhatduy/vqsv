/*
 * Decompiled with CFR 0.152.
 */
import javax.microedition.lcdui.Graphics;

/**
 * SpriteWidget - Sprite frame presenter and animated icon wrapper for UI layouts.
 */
public final class SpriteWidget {
    private SpriteRenderer b = null;
    private byte c = 0;
    private short d = (short)-1;
    public byte a = (byte)4;

    public final void a(int n2, boolean flag2, byte val3) {
        if (n2 != -1) {
            this.b = new SpriteRenderer();
            this.b.a(n2, flag2);
            switch (this.a) {
                case 3: {
                    this.b.a((byte)this.d, val3, true);
                }
            }
        }
    }

    public final void a(byte val, byte val2) {
        this.d = val;
        this.b.a(val, val2, true);
    }

    public final SpriteRenderer a() {
        return this.b;
    }

    public final void a(int n2) {
        this.d = (short)n2;
    }

    public final void b() {
        this.c = 1;
    }

    public SpriteWidget() {
        this.d = (short)-1;
        this.a = (byte)4;
    }

    public final void a(Graphics g, Rectangle ak2, int n2) {
        if (this.b != null) {
            Object object;
            boolean bl = false;
            SpriteWidget m2 = this;
            if (m2.d == -1) {
                object = null;
            } else {
                int[] intArray = new int[4];
                switch (m2.a) {
                    case 3: {
                        intArray = m2.b.a((int)m2.d, (byte)0);
                        break;
                    }
                    case 2: {
                        intArray = m2.b.b(m2.d, (byte)0);
                    }
                }
                object = m2 = new Rectangle(intArray[0], intArray[1], intArray[2], intArray[3]);
            }
            if (object == null) {
                return;
            }
            int n3 = ak2.a;
            int n4 = ak2.b;
            switch (n2) {
                case 4: {
                    n3 = ak2.a + (ak2.c - ((Rectangle)((Object)m2)).c) / 2 - ((Rectangle)((Object)m2)).a;
                    n4 = ak2.b + (ak2.d - ((Rectangle)((Object)m2)).d) / 2 - ((Rectangle)((Object)m2)).b;
                    break;
                }
                case 3: {
                    n3 = ak2.a - ((Rectangle)((Object)m2)).a;
                    n4 = ak2.b + (ak2.d - ((Rectangle)((Object)m2)).d) / 2 - ((Rectangle)((Object)m2)).b;
                    break;
                }
                case 5: {
                    n3 = ak2.a + (ak2.c - ((Rectangle)((Object)m2)).c) - ((Rectangle)((Object)m2)).a;
                    n4 = ak2.b + (ak2.d - ((Rectangle)((Object)m2)).d) / 2 - ((Rectangle)((Object)m2)).b;
                    break;
                }
                case 6: {
                    n3 = ak2.a - ((Rectangle)((Object)m2)).a;
                    n4 = ak2.b + (ak2.d - ((Rectangle)((Object)m2)).d) - ((Rectangle)((Object)m2)).b;
                    break;
                }
                case 8: {
                    n3 = ak2.a + (ak2.c - ((Rectangle)((Object)m2)).c) - ((Rectangle)((Object)m2)).a;
                    n4 = ak2.b + (ak2.d - ((Rectangle)((Object)m2)).d) - ((Rectangle)((Object)m2)).b;
                    break;
                }
                case 7: {
                    n3 = ak2.a + (ak2.c - ((Rectangle)((Object)m2)).c) / 2 - ((Rectangle)((Object)m2)).a;
                    n4 = ak2.b + (ak2.d - ((Rectangle)((Object)m2)).d) - ((Rectangle)((Object)m2)).b;
                    break;
                }
                case 0: {
                    n3 = ak2.a - ((Rectangle)((Object)m2)).a;
                    n4 = ak2.b - ((Rectangle)((Object)m2)).b;
                    break;
                }
                case 2: {
                    n3 = ak2.a + (ak2.c - ((Rectangle)((Object)m2)).c) - ((Rectangle)((Object)m2)).a;
                    n4 = ak2.b - ((Rectangle)((Object)m2)).b;
                    break;
                }
                case 1: {
                    n3 = ak2.a + (ak2.c - ((Rectangle)((Object)m2)).c) / 2 - ((Rectangle)((Object)m2)).a;
                    n4 = ak2.b - ((Rectangle)((Object)m2)).b;
                }
            }
            if (this.a == 3) {
                this.b.a(g, n3, n4, this.c);
                return;
            }
            if (this.a == 2) {
                this.b.a(g, this.d, n3, n4, (byte)0);
            }
        }
    }

    public final void c() {
        if (this.a == 3) {
            this.b.d();
        }
    }

    public final void d() {
        if (this.b != null) {
            this.b.getInstance();
            this.b = null;
        }
    }
}

