/*
 * Decompiled with CFR 0.152.
 */
package game;

import game.OverworldScreen;
import game.Player;
import game.GameStateController;
import game.WorldManager;
import javax.microedition.lcdui.Graphics;

/**
 * NpcEntity - Non-player character and interactive map entity extending WorldEntity.
 */
public final class NpcEntity
extends WorldEntity {
    public byte t;
    public boolean u;
    public byte v;
    public byte w;
    public short x;
    public short y;
    private int J;
    private int K;
    private int L;
    private int M;
    public byte z;
    public int A;
    public int B;
    public byte C;
    private short N;
    private short O;
    private short P;
    public short D;
    public short E;
    public short F;
    private boolean Q;
    private byte[] R = new byte[]{2, 3, 0, 1};
    private byte S = 0;
    public WorldEntity G;
    public WorldEntity H;
    public short I = (short)-1;
    private short[] T = new short[]{8, 9, 2, 96, 320, 0};

    public final void a(short[] shortArray, int n2) {
        this.I = (short)n2;
        this.t = (byte)shortArray[0];
        this.spriteRenderer.a((int)shortArray[1], false);
        NpcEntity a2 = this;
        a2.a.c();
        this.v = (byte)shortArray[6];
        if (this.t == 0 && (this.v == 1 || this.v == 18)) {
            byte by;
            this.facingDirection = by = (byte)(shortArray[2] % 3);
        }
        this.d((byte)shortArray[2]);
        this.posX = shortArray[3];
        this.posY = shortArray[4];
        if (shortArray[5] == 1) {
            this.b(true);
        } else {
            this.b(false);
        }
        switch (this.t) {
            case 0: {
                this.s = (byte)shortArray[7];
                this.w = (byte)shortArray[8];
                NpcEntity a3 = this;
                if (a3.w != 0 && a3.G == null && a3.j()) {
                    a3.G = new WorldEntity();
                    a3.G.a(259, false);
                    a3.G.a(a3.w, (byte)-1, true);
                    a3.G.b(a3.i, a3.j - 40);
                    NpcEntity a4 = a3;
                    a3.G.p = a4;
                }
                a3.z();
                this.v();
                this.u = shortArray[9] != 0;
                this.x = shortArray[11];
                this.y = shortArray[12];
                this.J = 0;
                this.L = EngineUtils.randomRange(20, 40);
                this.z = 0;
                if (this.v == 12) {
                    this.facingDirection = 0;
                } else if (this.v == 13) {
                    this.facingDirection = 1;
                }
                if (this.v == 3) {
                    if (this.h == 4) {
                        this.facingDirection = 1;
                    }
                } else if (this.v == 2) {
                    if (this.h == 5) {
                        this.facingDirection = (byte)2;
                    } else if (this.h == 3) {
                        this.facingDirection = 0;
                    }
                }
                if ((this.v != 1 || this.a.a == 226) && this.v != 2 && this.v != 3 && this.v != 17) break;
                if (this.targetEntity == null) {
                    this.targetEntity = new WorldEntity();
                    this.targetEntity.a(337, false);
                }
                this.targetEntity.b(this.posX, this.posY);
                if (this.a.a == 4) {
                    this.targetEntity.a((byte)0, (byte)0, this.Q);
                } else {
                    this.targetEntity.a((byte)1, (byte)0, this.Q);
                }
                this.targetEntity.c();
                break;
            }
            case 1: {
                this.s = shortArray[1] == 320 ? 2 : 1;
                if (this.j() && shortArray[0] > 0 && shortArray[0] <= 3) {
                    game.WorldManager.getInstance().e.addElement(this);
                }
                if (this.v == 3) {
                    this.u = true;
                }
                this.C = (byte)shortArray[7];
                this.N = shortArray[8];
                this.O = shortArray[9];
                this.P = shortArray[10];
                break;
            }
            case 2: {
                if (shortArray[7] == 0) {
                    this.Q = false;
                    break;
                }
                this.Q = true;
                break;
            }
            case 3: {
                this.s = 1;
                this.D = shortArray[7];
                this.E = shortArray[8];
                switch (this.E) {
                    case 9: {
                        this.E = 1;
                        break;
                    }
                    case 10: {
                        this.E = 0;
                        break;
                    }
                    case 11: {
                        this.E = (short)2;
                        break;
                    }
                    case 12: {
                        this.E = (short)3;
                    }
                }
                this.F = shortArray[9];
                this.u = true;
            }
        }
        this.c = new short[3];
        this.d = new short[3];
    }

    public final void d(byte val) {
        switch (this.t) {
            case 0: {
                if (this.v == 8) {
                    this.a((byte)0, (byte)-1, false);
                    this.J = 0;
                    this.h = val;
                    return;
                }
                if (this.v == 1 || this.v == 18) {
                    this.h = (byte)(val / 3);
                    if (this.h == 0) {
                        if (this.facingDirection == 3) {
                            this.a((byte)1, (byte)-1, false);
                            return;
                        }
                        this.a(this.facingDirection, (byte)-1, false);
                        return;
                    }
                    if (this.h != 1) break;
                    if (this.facingDirection == 3) {
                        this.a((byte)(this.h * 3 + 1), (byte)-1, false);
                        return;
                    }
                    this.a((byte)(this.h * 3 + this.facingDirection), (byte)-1, false);
                    return;
                }
                this.a(val, (byte)-1, false);
                this.h = val;
                game.WorldManager.getInstance().a(this.I, 0, this.h, true);
                return;
            }
            case 1: {
                if (this.v == 0) {
                    switch (val) {
                        case 0: {
                            this.a(val, (byte)-1, false);
                            break;
                        }
                        case 1: {
                            this.a(val, (byte)-2, false);
                            break;
                        }
                        case 2: {
                            this.a(val, (byte)-1, false);
                            break;
                        }
                        case 3: {
                            this.a(val, (byte)-2, false);
                        }
                    }
                } else {
                    this.a(val, (byte)-1, false);
                    if (this.v == 3) {
                        game.WorldManager.getInstance().a(this.I, 0, val, true);
                    }
                }
                this.h = val;
                return;
            }
            case 2: {
                this.h = val;
                return;
            }
            case 3: {
                this.a(val, (byte)-2, false);
                this.h = val;
                game.WorldManager.getInstance().a(this.I, 0, this.h, true);
            }
        }
    }

    public final void o() {
        switch (this.t) {
            case 0: {
                Object object = this;
                block4 : switch (((NpcEntity)object).v) {
                    case 2: {
                        if (((NpcEntity)object).y()) {
                            byte[] byteArray = new byte[]{0, 1, 2, 3, 5};
                            ((NpcEntity)object).d(byteArray[EngineUtils.randomInt(5)]);
                            ((BaseEntity)object).n = ((NpcEntity)object).h == 3 || ((NpcEntity)object).h == 0 ? (byte)0 : (((NpcEntity)object).h == 5 || ((NpcEntity)object).h == 2 ? (byte)2 : (EngineUtils.randomInt(2) == 0 ? (byte)3 : (byte)1));
                        }
                        if (((NpcEntity)object).h == 3) {
                            if (((NpcEntity)object).J >= 64) {
                                ((NpcEntity)object).d((byte)0);
                                break;
                            }
                            super.a(4);
                            ((NpcEntity)object).J += 4;
                            break;
                        }
                        if (((NpcEntity)object).h != 5) break;
                        if (((NpcEntity)object).J <= 0) {
                            ((NpcEntity)object).d((byte)2);
                            break;
                        }
                        super.a(4);
                        ((NpcEntity)object).J -= 4;
                        break;
                    }
                    case 3: {
                        if (((NpcEntity)object).y()) {
                            byte[] byteArray = new byte[]{0, 1, 2, 4};
                            ((NpcEntity)object).d(byteArray[EngineUtils.randomInt(4)]);
                            ((BaseEntity)object).n = ((NpcEntity)object).h == 0 ? (byte)0 : (((NpcEntity)object).h == 2 ? (byte)2 : (EngineUtils.randomInt(2) == 0 ? (byte)3 : (byte)1));
                        }
                        if (((NpcEntity)object).h != 4) break;
                        if (((NpcEntity)object).n == 1) {
                            if (((NpcEntity)object).J >= 64) {
                                ((NpcEntity)object).d((byte)0);
                                break;
                            }
                            super.a(4);
                            ((NpcEntity)object).J += 4;
                            break;
                        }
                        if (((NpcEntity)object).n != 3) break;
                        if (((NpcEntity)object).J <= 0) {
                            ((NpcEntity)object).d((byte)2);
                            break;
                        }
                        super.a(4);
                        ((NpcEntity)object).J -= 4;
                        break;
                    }
                    case 11: {
                        break;
                    }
                    case 4: 
                    case 5: 
                    case 6: 
                    case 7: 
                    case 15: {
                        int n2;
                        if (((NpcEntity)object).H != null && ((BaseEntity)object).j() && ((NpcEntity)object).H.p.equals(object) && (((NpcEntity)object).h != 0 || !game.Player.getInstance().a((NpcEntity)object, game.Player.getInstance().a.k(), ((NpcEntity)object).a.k()))) {
                            ((NpcEntity)object).x();
                        }
                        if (((NpcEntity)object).h != 1 || !((NpcEntity)object).a.e()) break;
                        ((NpcEntity)object).d((byte)2);
                        if (((NpcEntity)object).v == 6 && ((NpcEntity)object).v == 7) {
                            game.WorldManager.getInstance().a(((NpcEntity)object).I, 0, ((NpcEntity)object).h, false);
                        } else {
                            game.WorldManager.getInstance().a(((NpcEntity)object).I, 0, ((NpcEntity)object).h, true);
                        }
                        if ((((NpcEntity)object).v == 7 || ((NpcEntity)object).v == 6) && (n2 = EngineUtils.randomInt(2)) > 0) {
                            game.Player.getInstance().s(n2);
                            object = new int[]{n2, ((BaseEntity)object).i, ((BaseEntity)object).j - 20, 0};
                            game.Player.getInstance().V.addElement(object);
                        }
                        game.OverworldScreen.h = false;
                        break;
                    }
                    case 8: {
                        if (((NpcEntity)object).h != 1) break;
                        if (((NpcEntity)object).J < 2 && ((NpcEntity)object).a(((NpcEntity)object).n, 8, (byte)0)) {
                            ++((NpcEntity)object).J;
                            super.a(8);
                            ((NpcEntity)object).r();
                            break;
                        }
                        ((NpcEntity)object).d((byte)0);
                        break;
                    }
                    case 9: 
                    case 10: {
                        if (((NpcEntity)object).z != 1) break;
                        if ((((NpcEntity)object).a.a == 302 || ((NpcEntity)object).a.a == 298) && ((NpcEntity)object).a(((NpcEntity)object).n, 4, (byte)1)) {
                            super.a(4);
                            ((WorldEntity)((NpcEntity)object).p).a(4);
                            break;
                        }
                        if (((NpcEntity)object).a(((NpcEntity)object).n, 4, (byte)2)) {
                            super.a(4);
                            ((WorldEntity)((NpcEntity)object).p).a(4);
                            break;
                        }
                        ((NpcEntity)object).r();
                        ((NpcEntity)object).z = (byte)2;
                        ((NpcEntity)object).d((byte)0);
                        break;
                    }
                    case 12: {
                        if (!((BaseEntity)object).j()) break;
                        if (EngineUtils.a(game.Player.getInstance().posX, game.Player.getInstance().posY, ((NpcEntity)object).i, ((NpcEntity)object).j, ((NpcEntity)object).a.k())) {
                            if (game.Player.getInstance().h() == 8) break;
                            game.Player.getInstance().b((byte)8, ((NpcEntity)object).n);
                            break;
                        }
                        if (((NpcEntity)object).a(((NpcEntity)object).n, 4, (byte)0)) {
                            super.a(4);
                            break;
                        }
                        if (((NpcEntity)object).n == 2) {
                            ((NpcEntity)object).n = 0;
                            break;
                        }
                        ((NpcEntity)object).n = (byte)2;
                        break;
                    }
                    case 13: {
                        if (!((BaseEntity)object).j()) break;
                        if (EngineUtils.a(game.Player.getInstance().posX, game.Player.getInstance().posY, ((NpcEntity)object).i, ((NpcEntity)object).j, ((NpcEntity)object).a.k())) {
                            if (game.Player.getInstance().h() == 8) break;
                            game.Player.getInstance().b((byte)8, ((NpcEntity)object).n);
                            break;
                        }
                        if (((NpcEntity)object).a(((NpcEntity)object).n, 4, (byte)0)) {
                            super.a(4);
                            break;
                        }
                        if (((NpcEntity)object).n == 3) {
                            ((NpcEntity)object).n = 1;
                            break;
                        }
                        ((NpcEntity)object).n = (byte)3;
                        break;
                    }
                    case 16: {
                        if (!EngineUtils.a(game.Player.getInstance().posX, game.Player.getInstance().posY, ((NpcEntity)object).i, ((NpcEntity)object).j, ((NpcEntity)object).a.k()) || game.Player.getInstance().h() == 5) break;
                        game.Player.getInstance().b((byte)5, game.Player.getInstance().facingDirection);
                        break;
                    }
                    case 14: {
                        ((NpcEntity)object).q();
                        if (((NpcEntity)object).M < 4) {
                            ++((NpcEntity)object).M;
                            break;
                        }
                        ((NpcEntity)object).M = 0;
                        break;
                    }
                    case 1: {
                        int n3;
                        if (((NpcEntity)object).h == 1) {
                            n3 = ((BaseEntity)object).d[0];
                            super.a(n3);
                            if (game.WorldManager.getInstance().p != null && game.WorldManager.getInstance().p.p != null && game.WorldManager.getInstance().p.p.equals(object) && game.Player.getInstance().a != null && !game.Player.getInstance().a((NpcEntity)object, game.Player.getInstance().a.k(), ((NpcEntity)object).a.k())) {
                                game.WorldManager.getInstance().e();
                            }
                        }
                        if (!(game.WorldManager.getInstance().p == null || game.WorldManager.getInstance().p.p == null || !game.WorldManager.getInstance().p.p.equals(object) || game.Player.getInstance().a == null || ((BaseEntity)object).j() && game.Player.getInstance().a((NpcEntity)object, game.Player.getInstance().a.k(), ((NpcEntity)object).a.k()))) {
                            game.WorldManager.u = (short)-1;
                            game.WorldManager.getInstance().e();
                        }
                        if (((NpcEntity)object).G != null && ((BaseEntity)object).j() && ((NpcEntity)object).G.p.equals(object) && game.Player.getInstance().a != null && !game.Player.getInstance().a((NpcEntity)object, game.Player.getInstance().a.k(), ((NpcEntity)object).a.k())) {
                            ((NpcEntity)object).z();
                        }
                        if (game.OverworldScreen.d == null || game.OverworldScreen.d.size() <= 0 || ((NpcEntity)object).S != 1 || game.Player.getInstance().a == null || game.Player.getInstance().a((NpcEntity)object, game.Player.getInstance().a.k(), ((NpcEntity)object).a.k())) break;
                        for (n3 = 0; n3 < game.OverworldScreen.d.size(); ++n3) {
                            if (!((WorldEntity)game.OverworldScreen.d.elementAt((int)n3)).p.equals(object)) continue;
                            ((WorldEntity)game.OverworldScreen.d.elementAt(n3)).c();
                            break block4;
                        }
                        break;
                    }
                }
                break;
            }
            case 1: {
                if (this.v == 0) {
                    if (this.h == 0 && EngineUtils.a(game.Player.getInstance().posX, game.Player.getInstance().posY, this.posX, this.posY, game.Player.getInstance().a.k(), this.a.k())) {
                        this.d((byte)1);
                    } else if (this.h == 2 && !EngineUtils.a(game.Player.getInstance().posX, game.Player.getInstance().posY, this.posX, this.posY, game.Player.getInstance().a.k(), this.a.k())) {
                        this.d((byte)3);
                    } else if (this.h == 1 && this.spriteRenderer.e()) {
                        this.d((byte)2);
                    } else if (this.h == 3 && this.spriteRenderer.e()) {
                        this.d((byte)0);
                    }
                }
                if (this.v == 0 && this.spriteRenderer.e() || this.v == 1 || this.v == 3 && this.h() == 2) {
                    if (this.a.a == 320 && !this.j()) {
                        return;
                    }
                    if ((game.Player.getInstance().o != this.R[this.C] || this.a.a == 320 || this.a.a == 310) && this.a.a != 320 && this.a.a != 310 || !EngineUtils.a(game.Player.getInstance().posX, game.Player.getInstance().posY, this.posX, this.posY, game.Player.getInstance().a.k(), this.spriteRenderer.onKeyReleased())) break;
                    game.WorldManager.getInstance().f = this.N;
                    game.WorldManager.getInstance().g = this.O;
                    game.WorldManager.getInstance().j = this.P;
                    game.GameStateController.getInstance().a((byte)9);
                    break;
                }
                if (this.v == 2) {
                    if ((game.Player.getInstance().o != this.R[this.C] || this.a.a == 320) && this.a.a != 320 || !EngineUtils.a(game.Player.getInstance().posX, game.Player.getInstance().posY, this.posX, this.posY, game.Player.getInstance().a.k(), this.spriteRenderer.onKeyReleased())) break;
                    for (int i = 0; i < this.T.length / 6; ++i) {
                        if (this.T[i * 6] != this.I || this.T[i * 6 + 1] != game.WorldManager.getInstance().f || this.T[i * 6 + 2] != game.WorldManager.getInstance().g) continue;
                        game.WorldManager.getInstance().playerSpawnX = this.T[i * 6 + 3];
                        game.WorldManager.getInstance().playerSpawnY = this.T[i * 6 + 4];
                        game.WorldManager.w = (byte)this.T[i * 6 + 5];
                        break;
                    }
                    game.WorldManager.getInstance().f = this.N;
                    game.WorldManager.getInstance().g = this.O;
                    game.WorldManager.getInstance().j = -1;
                    game.GameStateController.getInstance().a((byte)9);
                    break;
                }
                if (this.v != 4 || game.Player.getInstance().h() == 9 || game.Player.getInstance().h() == 10 || !EngineUtils.a(game.Player.getInstance().posX, game.Player.getInstance().posY, this.posX, this.posY, game.Player.getInstance().a.k(), this.spriteRenderer.onKeyReleased())) break;
                game.Player.getInstance().b(this.posX, this.posY);
                game.Player.getInstance().b.b(this.posX, this.posY);
                game.Player.getInstance().b((byte)9, this.facingDirection);
                game.WorldManager.getInstance().j = this.P;
            }
        }
        this.f();
    }

    public final void p() {
        if (this.v == 9) {
            this.d((byte)1);
            this.facingDirection = (this.a.a == 302 || this.a.a == 298) && this.a((byte)1, 4, (byte)1) ? (byte)1 : (this.a((byte)1, 4, (byte)2) ? (byte)1 : (byte)3);
        } else if ((this.a.a == 302 || this.a.a == 298) && this.a((byte)2, 4, (byte)1)) {
            this.d((byte)2);
            this.facingDirection = (byte)2;
        } else if (this.a((byte)2, 4, (byte)2)) {
            this.d((byte)2);
            this.facingDirection = (byte)2;
        } else {
            this.d((byte)1);
            this.facingDirection = 0;
        }
        this.z = 1;
    }

    /*
     * Enabled aggressive block sorting
     */
    public final void q() {
        this.A = 0;
        while (true) {
            NpcEntity a2 = null;
            boolean bl = false;
            int n2 = 16 * (this.A + 1);
            int n3 = this.a.g();
            a2 = this;
            byte by = 0;
            switch (n3) {
                case 2: {
                    by = MapEngine.getInstance().b(a2.i, a2.j - n2);
                    for (n3 = 0; n3 < game.WorldManager.getInstance().npcList.length; ++n3) {
                        if (game.WorldManager.getInstance().npcList[n3].v == a2.v || game.WorldManager.getInstance().npcList[n3].a.k() == null || !EngineUtils.a(a2.i, a2.j - n2, game.WorldManager.getInstance().npcList[n3].i, game.WorldManager.getInstance().npcList[n3].j, game.WorldManager.getInstance().npcList[n3].a.k())) continue;
                        NpcEntity a3 = a2;
                        game.WorldManager.getInstance().npcList[n3].p = a3;
                        return;
                    }
                    break;
                }
                case 0: {
                    by = MapEngine.getInstance().b(a2.i, a2.j + n2);
                    for (n3 = 0; n3 < game.WorldManager.getInstance().npcList.length; ++n3) {
                        if (game.WorldManager.getInstance().npcList[n3].v == a2.v || game.WorldManager.getInstance().npcList[n3].a.k() == null || !EngineUtils.a(a2.i, a2.j + n2, game.WorldManager.getInstance().npcList[n3].i, game.WorldManager.getInstance().npcList[n3].j, game.WorldManager.getInstance().npcList[n3].a.k())) continue;
                        NpcEntity a4 = a2;
                        game.WorldManager.getInstance().npcList[n3].p = a4;
                        return;
                    }
                    break;
                }
                case 3: {
                    by = MapEngine.getInstance().b(a2.i - n2, a2.j);
                    for (n3 = 0; n3 < game.WorldManager.getInstance().npcList.length; ++n3) {
                        if (game.WorldManager.getInstance().npcList[n3].v == a2.v || game.WorldManager.getInstance().npcList[n3].a.k() == null || !EngineUtils.a(a2.i - n2, a2.j, game.WorldManager.getInstance().npcList[n3].i, game.WorldManager.getInstance().npcList[n3].j, game.WorldManager.getInstance().npcList[n3].a.k())) continue;
                        NpcEntity a5 = a2;
                        game.WorldManager.getInstance().npcList[n3].p = a5;
                        return;
                    }
                    break;
                }
                case 1: {
                    by = MapEngine.getInstance().b(a2.i + n2, a2.j);
                    for (n3 = 0; n3 < game.WorldManager.getInstance().npcList.length; ++n3) {
                        if (game.WorldManager.getInstance().npcList[n3].v == a2.v || game.WorldManager.getInstance().npcList[n3].a.k() == null || !EngineUtils.a(a2.i + n2, a2.j, game.WorldManager.getInstance().npcList[n3].i, game.WorldManager.getInstance().npcList[n3].j, game.WorldManager.getInstance().npcList[n3].a.k())) continue;
                        NpcEntity a6 = a2;
                        game.WorldManager.getInstance().npcList[n3].p = a6;
                        return;
                    }
                }
            }
            if (by != 0) {
                return;
            }
            boolean bl2 = true;
            if (!bl2) {
                return;
            }
            ++this.A;
        }
    }

    private boolean y() {
        ++this.K;
        if (this.K >= this.L) {
            this.K = 0;
            this.L = EngineUtils.randomRange(20, 40);
            return true;
        }
        return false;
    }

    private boolean a(byte val, int n2, byte val3) {
        byte by3 = 0;
        switch (val) {
            case 2: {
                by3 = MapEngine.getInstance().b(this.posX, this.posY - this.a.k()[3] - n2);
                break;
            }
            case 0: {
                by3 = MapEngine.getInstance().b(this.posX, this.posY + n2);
                break;
            }
            case 3: {
                by3 = MapEngine.getInstance().b(this.posX - n2 - this.a.k()[2] / 2, this.posY);
                break;
            }
            case 1: {
                by3 = MapEngine.getInstance().b(this.posX + n2 + this.a.k()[2] / 2, this.posY);
            }
        }
        return by3 == val3;
    }

    public final void b(Graphics g, int n2, int n3) {
        switch (this.a.g()) {
            case 2: {
                g.setColor(65280);
                g.fillRect(this.posX - n2 - (this.M + 5) / 2, this.posY - this.spriteRenderer.onKeyReleased()[3] - n3 - (this.A << 4) + 8, this.M + 5, this.A << 4);
                g.setColor(0xFFFFFF);
                g.fillRect(this.posX - n2 - (this.M + 3) / 2, this.posY - this.spriteRenderer.onKeyReleased()[3] - n3 - (this.A << 4) + 8, this.M + 3, this.A << 4);
                return;
            }
            case 0: {
                g.setColor(65280);
                g.fillRect(this.posX - n2 - (this.M + 5) / 2, this.posY - this.spriteRenderer.onKeyReleased()[3] - n3 + 20, this.M + 5, this.A + 1 << 4);
                g.setColor(0xFFFFFF);
                g.fillRect(this.posX - n2 - (this.M + 3) / 2, this.posY - this.spriteRenderer.onKeyReleased()[3] - n3 + 20, this.M + 3, this.A + 1 << 4);
                return;
            }
            case 3: {
                g.setColor(65280);
                g.fillRect(this.posX - n2 - 8 - (this.A << 4), this.posY - this.spriteRenderer.onKeyReleased()[3] - n3 - (this.M + 5) / 2 + 13, this.A << 4, this.M + 5);
                g.setColor(0xFFFFFF);
                g.fillRect(this.posX - n2 - 8 - (this.A << 4), this.posY - this.spriteRenderer.onKeyReleased()[3] - n3 - (this.M + 3) / 2 + 13, this.A << 4, this.M + 3);
                return;
            }
            case 1: {
                g.setColor(65280);
                g.fillRect(this.posX - n2 + 7, this.posY - this.spriteRenderer.onKeyReleased()[3] - n3 - (this.M + 5) / 2 + 13, this.A << 4, this.M + 5);
                g.setColor(0xFFFFFF);
                g.fillRect(this.posX - n2 + 7, this.posY - this.spriteRenderer.onKeyReleased()[3] - n3 - (this.M + 3) / 2 + 13, this.A << 4, this.M + 3);
            }
        }
    }

    public final void r() {
        game.WorldManager.getInstance().a((int)this.I, 0, this.posX);
        game.WorldManager.getInstance().a((int)this.I, 1, this.posY);
    }

    public final void s() {
        byte by = 0;
        if (this.j()) {
            by = 1;
        }
        game.WorldManager.getInstance().a(this.I, 1, by, true);
        game.WorldManager.getInstance().a(this.I, 0, this.h, true);
        game.WorldManager.getInstance().a(this.I, 2, this.facingDirection, true);
    }

    public final void a(int n2) {
        super.a(n2);
    }

    public final void t() {
        this.S = 1;
    }

    public final byte u() {
        return this.S;
    }

    public final void f(int n2) {
        if (this.H == null && this.j()) {
            this.H = new WorldEntity();
            this.H.a(259, false);
            this.H.a((byte)7, (byte)-1, true);
            this.H.b(this.posX, this.posY - n2);
            NpcEntity a2 = this;
            this.H.p = a2;
        }
        NpcEntity a3 = this;
        if (a3.H != null) {
            a3.H.c();
        }
    }

    public final void v() {
        if (game.WorldManager.getInstance().b(this.I)) {
            this.w();
        }
    }

    private void z() {
        if (this.G != null) {
            this.G.c();
        }
    }

    public final void d() {
        super.d();
        this.w();
        this.e();
        this.x();
    }

    public final void w() {
        if (this.G != null) {
            this.G.d();
        }
    }

    public final void x() {
        if (this.H != null) {
            this.H.d();
        }
    }

    public final void b(int n2, int n3) {
        super.b(n2, n3);
        if (this.targetEntity != null && this.targetEntity.j()) {
            this.targetEntity.b(n2, n3);
        }
    }
}

