/*
 * Decompiled with CFR 0.152.
 */
/**
 * BaseEntity - Abstract movable entity with position coordinates, bounding box, and velocity.
 */
public class BaseEntity {
    public short[] baseStats;
    public short[] currentStats;
    protected boolean e;
    protected boolean f;
    protected boolean g;
    protected byte h;
    public int posX;
    public int posY;
    public int k;
    public int l;
    public byte m;
    public byte facingDirection;
    public byte o;
    public BaseEntity ownerEntity;
    public int q;
    public int r;
    public int s;
    private short followDistance = (short)10;
    private int[][] followHistory;
    private boolean isFollowing = false;

    public final void a(byte val, short sVal2) {
        this.currentStats[val] = sVal2;
    }

    public final short a(byte val) {
        return this.currentStats[1];
    }

    public void g() {
        for (int n2 = 0; n2 < this.baseStats.length; n2 = (int)((byte)(n2 + 1))) {
            int n3 = n2;
            short s2 = this.baseStats[n3];
            n3 = n2;
            this.currentStats[n3] = s2;
        }
    }

    public final byte h() {
        return this.h;
    }

    public final void a(boolean flag) {
        this.e = flag;
    }

    public final boolean i() {
        return this.e;
    }

    public final void b(boolean flag) {
        this.f = flag;
    }

    public final boolean j() {
        return this.f;
    }

    public final void c(boolean flag) {
        this.g = flag;
    }

    public final boolean k() {
        return this.g;
    }

    public void setPosition(int x, int y) {
        this.posX = x;
        this.posY = y;
    }

    public final void setFacingDirection(byte dir) {
        this.facingDirection = dir;
    }

    public final int getPosX() {
        return this.posX;
    }

    public final int getPosY() {
        return this.posY;
    }

    public final void movePosX(int dx) {
        this.posX += dx;
    }

    public final void movePosY(int dy) {
        this.posY += dy;
    }

    public void b(int n2) {
        this.posX += n2;
        this.posY += 4;
    }

    public final boolean a(int n2, int n3, int n4) {
        if (this.posX == n3 && this.posY == n4) {
            return true;
        }
        int n5 = EngineUtils.a(this.posX, this.posY, n3, n4);
        if (n5 < n2) {
            this.posX = n3;
            this.posY = n4;
        } else {
            this.movePosX((n3 - this.posX) * n2 / n5);
            this.movePosY((n4 - this.posY) * n2 / n5);
        }
        return false;
    }

    public final void a(SpriteRenderer d2, d d3) {
        if (!this.isFollowing) {
            return;
        }
        if (this.ownerEntity.h == 0) {
            return;
        }
        this.followHistory[0][0] = this.ownerEntity.i;
        this.followHistory[0][1] = this.ownerEntity.j;
        this.followHistory[0][2] = d2.b;
        this.followHistory[0][3] = this.ownerEntity.n;
        for (int i2 = this.followDistance; i2 > 0; --i2) {
            byte by;
            this.followHistory[i2][0] = this.followHistory[i2 - 1][0];
            this.followHistory[i2][1] = this.followHistory[i2 - 1][1];
            this.followHistory[i2][2] = this.followHistory[i2 - 1][2];
            this.followHistory[i2][3] = this.followHistory[i2 - 1][3];
            if (i2 % this.followDistance != 0) continue;
            this.setPosition(this.followHistory[i2][0], this.followHistory[i2][1]);
            if (this.followHistory[i2][3] == 3) {
                d3.a((byte)this.followHistory[i2][2], (byte)1, false);
            } else {
                d3.a((byte)this.followHistory[i2][2], (byte)this.followHistory[i2][3], false);
            }
            this.facingDirection = by = (byte)this.followHistory[i2][3];
        }
    }

    public final void c(byte val) {
        int n2;
        this.isFollowing = true;
        this.followHistory = new int[this.followDistance + 1][4];
        for (n2 = 0; n2 < this.followDistance + 1; ++n2) {
            this.followHistory[n2][0] = this.ownerEntity.i;
            this.followHistory[n2][1] = this.ownerEntity.j;
            this.followHistory[n2][3] = this.ownerEntity.n;
        }
        if (val >= 0) {
            for (n2 = 0; n2 < this.followDistance + 1; ++n2) {
                this.followHistory[n2][2] = val;
            }
        }
        switch (this.ownerEntity.n) {
            case 1: {
                int[] intArray = this.followHistory[10];
                intArray[0] = intArray[0] - this.followDistance;
                break;
            }
            case 3: {
                int[] intArray = this.followHistory[10];
                intArray[0] = intArray[0] + this.followDistance;
                break;
            }
            case 2: {
                int[] intArray = this.followHistory[10];
                intArray[1] = intArray[1] + this.followDistance;
                break;
            }
            case 0: {
                int[] intArray = this.followHistory[10];
                intArray[1] = intArray[1] - this.followDistance;
            }
        }
        this.setPosition(this.followHistory[10][0], this.followHistory[10][1]);
    }

    public final boolean isFollowing() {
        return this.isFollowing;
    }

    public final void setOwnerEntity(BaseEntity owner) {
        this.ownerEntity = owner;
    }
}

