/*
 * Decompiled with CFR 0.152.
 */
/**
 * ParticleEffect - Visual particle effect manager for explosions, sparks, and status auras.
 */
public final class ParticleEffect
extends BaseEntity {
    private static ParticleEffect t;
    public byte a = 0;
    public byte b = 0;
    private int u;
    private int v;
    private int w;
    private boolean x = true;

    public static ParticleEffect getInstance() {
        if (t == null) {
            t = new ParticleEffect();
        }
        return t;
    }

    public final void a(int n2) {
        this.w = n2;
    }

    public final void b() {
        this.x = false;
    }

    public final void a(int n2, int n3, boolean flag3) {
        this.d((byte)0);
        this.x = flag3;
        if (this.x) {
            this.i = n2;
            this.j = n3;
            return;
        }
        int n4 = n3;
        n3 = n2;
        ParticleEffect ai2 = this;
        this.q = n3;
        ai2.r = n4;
    }

    public final void a(BaseEntity n2, boolean flag2) {
        this.d((byte)1);
        this.p = n2;
        this.x = flag2;
        if (this.x) {
            this.i = this.p.i;
            this.j = this.p.j;
        }
    }

    public final boolean c() {
        return this.x;
    }

    public final void d(byte val) {
        this.b = this.a;
        this.a = val;
    }

    public final void d() {
        switch (this.a) {
            case 0: {
                if (this.x || !this.a(this.w, this.q, this.r)) break;
                this.x = true;
                return;
            }
            case 1: {
                if (this.x) {
                    this.i = this.p.i;
                    this.j = this.p.j;
                    return;
                }
                if (!this.a(this.w, this.p.i, this.p.j)) break;
                this.x = true;
                return;
            }
            case 2: {
                if (this.x) {
                    return;
                }
                if (this.u > 0) {
                    --this.u;
                    return;
                }
                if (this.i == null[0] && this.j == null[1]) {
                    if (this.v < (null).length) {
                        ++this.v;
                        this.u = null[5];
                        return;
                    }
                    this.x = true;
                    return;
                }
                this.a((int)null[2], (int)null[0], (int)null[1]);
            }
        }
    }
}

