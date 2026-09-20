/*
 * Decompiled with CFR 0.152.
 */
import game.WorldManager;
import javax.microedition.lcdui.Graphics;

/**
 * WorldEntity - World map actor entity extending BaseEntity; handles facing direction, walking speed, and pathing.
 */
public class WorldEntity
extends BaseEntity {
    public SpriteRenderer a = new SpriteRenderer();
    public WorldEntity b = null;

    public boolean a(int n2, boolean flag2) {
        return this.a.a(n2, flag2);
    }

    public final void a(int n2, int n3) {
        this.a.a(n2, n3, true);
    }

    public final boolean a(byte val, byte val2, boolean flag3) {
        return this.a.a(val, val2, flag3);
    }

    public final void a(byte val, byte val2) {
        this.a.a(val, (byte)-1);
        this.n = val = val2;
    }

    public final boolean a() {
        if (!this.f) {
            return false;
        }
        return this.a.onPointerEvent();
    }

    public final boolean b() {
        return this.a.e();
    }

    public final void a(Graphics g, int n2, int n3) {
        if (!this.f) {
            return;
        }
        if (this.n == 3) {
            this.a.a(g, this.i - n2, this.j - n3, (byte)1);
            return;
        }
        this.a.a(g, this.i - n2, this.j - n3, (byte)0);
    }

    public void c() {
        this.a(true);
        this.b(true);
        this.c(true);
    }

    public void d() {
        this.a(false);
        this.b(false);
        this.c(false);
    }

    public final void e() {
        if (this.b != null) {
            this.b.d();
        }
    }

    public void a(int n2) {
        switch (this.n) {
            case 3: {
                this.d(-n2);
                break;
            }
            case 1: {
                this.d(n2);
                break;
            }
            case 2: {
                this.e(-n2);
                break;
            }
            case 0: {
                this.e(n2);
            }
        }
        if (this.b != null) {
            this.b.b(this.i, this.j);
        }
    }

    public final void b(int n2) {
        switch (n2) {
            case 3: {
                this.d(-4);
                break;
            }
            case 1: {
                this.d(4);
                break;
            }
            case 2: {
                this.e(-4);
                break;
            }
            case 0: {
                this.e(4);
            }
        }
        if (this.b != null) {
            this.b.b(this.i, this.j);
        }
    }

    public final void f() {
        if (!this.f) {
            return;
        }
        if (this.a.onKeyPressed()) {
            this.c(true);
            return;
        }
        if (EngineUtils.a(game.WorldManager.getInstance().a.a, game.WorldManager.getInstance().a.b, (int)BaseScreen.getScreenWidth(), (int)BaseScreen.getScreenHeight(), this.i, this.j, this.a.onKeyReleased())) {
            this.c(true);
            return;
        }
        this.c(false);
    }

    public final void c(int n2) {
        this.a.a(n2);
    }
}

