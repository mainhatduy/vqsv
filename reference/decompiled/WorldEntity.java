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
    public SpriteRenderer spriteRenderer = new SpriteRenderer();
    public WorldEntity targetEntity = null;

    public boolean a(int n2, boolean flag2) {
        return this.spriteRenderer.a(n2, flag2);
    }

    public final void a(int n2, int n3) {
        this.spriteRenderer.a(n2, n3, true);
    }

    public final boolean a(byte val, byte val2, boolean flag3) {
        return this.spriteRenderer.a(val, val2, flag3);
    }

    public final void a(byte val, byte val2) {
        this.spriteRenderer.a(val, (byte)-1);
        this.facingDirection = val = val2;
    }

    public final boolean a() {
        if (!this.f) {
            return false;
        }
        return this.spriteRenderer.onPointerEvent();
    }

    public final boolean b() {
        return this.spriteRenderer.e();
    }

    public final void a(Graphics g, int n2, int n3) {
        if (!this.f) {
            return;
        }
        if (this.facingDirection == 3) {
            this.spriteRenderer.a(g, this.posX - n2, this.posY - n3, (byte)1);
            return;
        }
        this.spriteRenderer.a(g, this.posX - n2, this.posY - n3, (byte)0);
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
        if (this.targetEntity != null) {
            this.targetEntity.d();
        }
    }

    public void a(int n2) {
        switch (this.facingDirection) {
            case 3: {
                this.movePosX(-n2);
                break;
            }
            case 1: {
                this.movePosX(n2);
                break;
            }
            case 2: {
                this.movePosY(-n2);
                break;
            }
            case 0: {
                this.movePosY(n2);
            }
        }
        if (this.targetEntity != null) {
            this.targetEntity.setPosition(this.posX, this.posY);
        }
    }

    public final void b(int n2) {
        switch (n2) {
            case 3: {
                this.movePosX(-4);
                break;
            }
            case 1: {
                this.movePosX(4);
                break;
            }
            case 2: {
                this.movePosY(-4);
                break;
            }
            case 0: {
                this.movePosY(4);
            }
        }
        if (this.targetEntity != null) {
            this.targetEntity.setPosition(this.posX, this.posY);
        }
    }

    public final void f() {
        if (!this.f) {
            return;
        }
        if (this.spriteRenderer.onKeyPressed()) {
            this.c(true);
            return;
        }
        if (EngineUtils.a(MapEngine.getInstance().cameraX, MapEngine.getInstance().cameraY, (int)BaseScreen.getScreenWidth(), (int)BaseScreen.getScreenHeight(), this.posX, this.posY, this.spriteRenderer.onKeyReleased())) {
            this.c(true);
            return;
        }
        this.c(false);
    }

    public final void c(int n2) {
        this.spriteRenderer.a(n2);
    }
}

