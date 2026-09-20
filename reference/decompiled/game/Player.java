/*
 * Decompiled with CFR 0.152.
 */
package game;

import game.NpcEntity;
import game.OverworldScreen;
import game.GameStateController;
import game.WorldManager;
import java.util.Vector;

/**
 * Player - Player entity state, bag inventory, pet party, gold/currency, badges, and world coordinates.
 */
public final class Player
extends WorldEntity {
    private static Player instance;
    public int mountState;
    private int Z;
    private int aa;
    public byte u;
    private int ab;
    private boolean ac;
    public int badges;
    private int ad;
    private int ae;
    public int repelSteps;
    public int encounterSteps;
    public boolean y;
    public game.Pet[] petParty;
    public int partyPetCount;
    public byte[][] B;
    public byte[][] C;
    public byte[][] D;
    public byte[] E;
    public byte F;
    public byte G;
    public byte H;
    public byte I;
    public Vector bagItems;
    public Vector bagPotions;
    public Vector bagBalls;
    public Vector bagScrolls;
    public Vector bagBadges;
    public Vector bagQuestItems;
    public byte[] P;
    public byte[] Q;
    public short[] quickItemSlots;
    public Vector S;
    public boolean[] T;
    public static boolean U;
    public Vector V;
    public int gold;
    public int arenaPoints;
    public int[] W = new int[]{0, 16, 32, 48, 64, 76, 88};
    public int[] X = new int[]{16, 16, 16, 16, 12, 12, 12};
    private static byte[][] ah;
    private static short[][] ai;
    private Vector aj = null;

    public static Player getInstance() {
        if (instance == null) {
            instance = new Player();
        }
        return instance;
    }

    public final void p() {
        this.partyPetCount = 0;
        instance = null;
    }

    public Player() {
        int n2;
        this.c = new short[3];
        this.d = new short[3];
        this.petParty = new game.Pet[6];
        this.B = new byte[8][2];
        this.T = new boolean[21];
        this.bagItems = new Vector();
        this.bagPotions = new Vector();
        int[] intArray = new int[]{0, 0, 1};
        this.bagPotions.addElement(intArray);
        this.bagBalls = new Vector();
        this.bagScrolls = new Vector();
        this.bagBadges = new Vector();
        this.bagQuestItems = new Vector();
        this.V = new Vector();
        this.C = new byte[7][];
        this.E = new byte[7];
        this.D = new byte[7][];
        this.quickItemSlots = new short[]{-1, -1, -1, -1, -1};
        for (n2 = 0; n2 < this.D.length; ++n2) {
            this.D[n2] = new byte[this.X[n2]];
            for (int i = 0; i < this.D[n2].length; ++i) {
                this.D[n2][i] = -1;
            }
        }
        this.C[0] = new byte[16];
        this.C[1] = new byte[16];
        this.C[2] = new byte[16];
        this.C[3] = new byte[16];
        this.C[4] = new byte[12];
        this.C[5] = new byte[12];
        this.C[6] = new byte[12];
        this.P = new byte[4];
        this.Q = new byte[4];
        this.u = 0;
        for (n2 = 0; n2 < 8; ++n2) {
            this.B[n2][0] = 0;
            this.B[n2][1] = 0;
        }
        for (n2 = 0; n2 < 4; ++n2) {
            this.P[n2] = 0;
        }
        this.gold = 1000;
        this.arenaPoints = 0;
        this.mountState = -1;
        this.y = false;
    }

    public final void a(short[] shortArray) {
        short s2;
        if (this.mountState == -1) {
            this.a(0, false);
        }
        this.h(this.mountState);
        this.posX = shortArray[0];
        this.posY = shortArray[1];
        this.b((byte)0, (byte)shortArray[2]);
        this.c[0] = s2 = shortArray[3];
        this.c[1] = s2 = shortArray[4];
        this.c[2] = s2 = shortArray[5];
        if (this.mountState == -1) {
            this.g();
        }
        this.s = 1;
        this.ad = shortArray[6];
        this.ae = shortArray[7];
        this.badges = this.C();
        if (this.u == 1) {
            this.a(0, 107);
        }
        if (this.targetEntity == null) {
            this.targetEntity = new WorldEntity();
            this.targetEntity.a(337, false);
        }
        this.targetEntity.b(this.posX, this.posY);
        if (this.a.a == 4) {
            this.targetEntity.a((byte)0, (byte)0, false);
        } else {
            this.targetEntity.a((byte)1, (byte)0, false);
        }
        this.targetEntity.c();
        this.ownerEntity = null;
        this.y = true;
    }

    public final void q() {
        if (this.n() && this.ownerEntity.h() != 0) {
            this.a(((WorldEntity)this.ownerEntity).a, this.a);
            return;
        }
        switch (this.h) {
            case 0: {
                if (this.P[2] == 2 || !this.N()) break;
                this.b((byte)3, this.facingDirection);
                return;
            }
            case 1: {
                if (this.P[2] == 2) {
                    boolean bl;
                    int n2 = this.posY;
                    int n3 = this.posX;
                    Player g2 = this;
                    switch (g2.n) {
                        case 2: {
                            bl = MapEngine.getInstance().c(n3, n2 - 25 - g2.Z);
                            break;
                        }
                        case 0: {
                            bl = MapEngine.getInstance().c(n3, n2 - 25 + g2.Z);
                            break;
                        }
                        case 3: {
                            bl = MapEngine.getInstance().c(n3 - g2.Z, n2 - 25);
                            break;
                        }
                        case 1: {
                            bl = MapEngine.getInstance().c(n3 + g2.Z, n2 - 25);
                            break;
                        }
                        default: {
                            bl = false;
                        }
                    }
                    if (bl) break;
                    game.WorldManager.u = this.K();
                    this.ownerEntity = null;
                    for (int i = 0; i < game.WorldManager.getInstance().npcList.length; ++i) {
                        this.w(i);
                    }
                    this.a((int)this.d[0]);
                    this.aa = this.aa < 8 ? (this.aa += this.d[0]) : 4;
                    this.O();
                    return;
                }
                game.WorldManager.u = this.K();
                if (this.L() && this.M()) {
                    this.a(this.Z);
                    this.aa = this.aa < 8 ? (this.aa += this.Z) : 4;
                    this.O();
                    return;
                }
                this.aa = 0;
                return;
            }
            case 2: {
                if (this.L() && this.M()) {
                    this.a(this.Z);
                    this.aa = this.aa < 8 ? (this.aa += this.Z) : 4;
                    this.O();
                    return;
                }
                this.aa = 0;
                return;
            }
            case 4: {
                return;
            }
            case 5: {
                int n4;
                if (this.ab < 16) {
                    int n5;
                    switch (this.m) {
                        case 1: 
                        case 3: {
                            if (this.m == 3) {
                                if (this.posX > this.ownerEntity.i) {
                                    this.b((int)this.m);
                                }
                            } else if (this.posX < this.ownerEntity.i) {
                                this.b((int)this.m);
                            }
                            if (this.l > this.ownerEntity.j - 16) {
                                if (this.posY <= this.ownerEntity.j - 16) {
                                    this.posY = n5 = this.ownerEntity.j - 16;
                                    break;
                                }
                                this.b(2);
                                break;
                            }
                            if (this.l >= this.ownerEntity.j - 16) break;
                            if (this.posY >= this.ownerEntity.j - 16) {
                                this.posY = n5 = this.ownerEntity.j - 16;
                                break;
                            }
                            this.b(0);
                            break;
                        }
                        case 0: 
                        case 2: {
                            if (this.m == 2) {
                                if (this.posY > this.ownerEntity.j - 16) {
                                    this.b((int)this.m);
                                }
                            } else if (this.posY < this.ownerEntity.j - 16) {
                                this.b((int)this.m);
                            }
                            if (this.k > this.ownerEntity.i) {
                                if (this.posX <= this.ownerEntity.i) {
                                    this.posX = n5 = this.ownerEntity.i;
                                    break;
                                }
                                this.b(3);
                                break;
                            }
                            if (this.k >= this.ownerEntity.i) break;
                            if (this.posX >= this.ownerEntity.i) {
                                this.posX = n5 = this.ownerEntity.i;
                                break;
                            }
                            this.b(1);
                        }
                    }
                    if (this.ab % 4 == 3) {
                        this.a((byte)1, (byte)-1, false);
                    } else {
                        this.a((byte)(this.ab % 4), (byte)-1, false);
                    }
                    this.facingDirection = n5 = (int)(this.ab % 4);
                    ++this.ab;
                    return;
                }
                int n6 = 0;
                for (n4 = 0; n4 < ah.length; ++n4) {
                    if (game.WorldManager.getInstance().f != ah[n4][0] || game.WorldManager.getInstance().g != ah[n4][1]) continue;
                    game.WorldManager.getInstance().f = ah[n4][2];
                    game.WorldManager.getInstance().g = ah[n4][3];
                    n6 = ah[n4][4];
                    break;
                }
                for (n4 = 0; n4 < ai[n6].length / 4; ++n4) {
                    if (((NpcEntity)this.ownerEntity).I < ai[n6][n4 << 2] || ((NpcEntity)this.ownerEntity).I > ai[n6][(n4 << 2) + 1]) continue;
                    game.WorldManager.getInstance().playerSpawnX = ai[n6][(n4 << 2) + 2];
                    game.WorldManager.getInstance().playerSpawnY = ai[n6][(n4 << 2) + 3];
                    break;
                }
                U = true;
                game.WorldManager.getInstance().j = -1;
                game.GameStateController.getInstance().a((byte)9);
                return;
            }
            case 6: {
                Player g3 = this;
                boolean bl = true;
                switch (g3.n) {
                    case 3: {
                        bl = g3.d(MapEngine.getInstance().b(g3.i - 16, g3.j));
                        break;
                    }
                    case 1: {
                        bl = g3.d(MapEngine.getInstance().b(g3.i + 16, g3.j));
                        break;
                    }
                    case 2: {
                        bl = g3.d(MapEngine.getInstance().b(g3.i, g3.j - 16));
                        break;
                    }
                    case 0: {
                        bl = g3.d(MapEngine.getInstance().b(g3.i, g3.j + 16));
                    }
                }
                if (bl && MapEngine.getInstance().b(g3.i, g3.j) != 3 ? false : bl) {
                    this.a((int)this.c[2]);
                    return;
                }
                this.a((int)this.d[1]);
                this.b((byte)0, this.facingDirection);
                return;
            }
            case 7: {
                if (((NpcEntity)this.ownerEntity).z == 0) {
                    byte by;
                    if (this.ab < 7) {
                        this.a(4);
                        ++this.ab;
                        return;
                    }
                    if (this.ab != 7) break;
                    if (this.facingDirection == 3) {
                        this.a((byte)1, (byte)-1, false);
                    } else {
                        this.a(this.facingDirection, (byte)-1, false);
                    }
                    this.facingDirection = by = this.facingDirection;
                    ((NpcEntity)this.ownerEntity).p();
                    ++this.ab;
                    return;
                }
                if (((NpcEntity)this.ownerEntity).z != 2) break;
                if (this.ab < 8 && this.ab > 0) {
                    this.a(4);
                    --this.ab;
                    return;
                }
                if (this.ab == 8) {
                    this.b((byte)7, this.facingDirection);
                    --this.ab;
                    return;
                }
                ((NpcEntity)this.ownerEntity).z = 0;
                ((NpcEntity)this.ownerEntity).p = null;
                this.ownerEntity = null;
                this.b((byte)0, this.facingDirection);
                return;
            }
            case 8: {
                if (this.ab < 16) {
                    byte by;
                    if (this.ab % 4 == 3) {
                        this.a((byte)1, (byte)-1, false);
                    } else {
                        this.a((byte)(this.ab % 4), (byte)-1, false);
                    }
                    this.facingDirection = by = (byte)(this.ab % 4);
                    ++this.ab;
                    return;
                }
                int n7 = game.WorldManager.getInstance().npcList[game.WorldManager.getInstance().j].i - game.WorldManager.getInstance().npcList[game.WorldManager.getInstance().j].i % this.c[2];
                int n8 = game.WorldManager.getInstance().npcList[game.WorldManager.getInstance().j].j - game.WorldManager.getInstance().npcList[game.WorldManager.getInstance().j].j % this.c[2];
                this.b(n7, n8);
                this.targetEntity.b(n7, n8);
                this.b((byte)0, game.WorldManager.getInstance().npcList[game.WorldManager.getInstance().j].C);
                this.a(32);
                ParticleEffect.getInstance().a(8);
                ParticleEffect.getInstance().b();
                return;
            }
            case 9: {
                if (this.ab < 16) {
                    byte by;
                    if (this.ab % 4 == 3) {
                        this.a((byte)1, (byte)-1, false);
                    } else {
                        this.a((byte)(this.ab % 4), (byte)-1, false);
                    }
                    this.facingDirection = by = (byte)(this.ab % 4);
                    ++this.ab;
                    return;
                }
                int n9 = game.WorldManager.getInstance().npcList[game.WorldManager.getInstance().j].i - game.WorldManager.getInstance().npcList[game.WorldManager.getInstance().j].i % this.c[2];
                int n10 = game.WorldManager.getInstance().npcList[game.WorldManager.getInstance().j].j - game.WorldManager.getInstance().npcList[game.WorldManager.getInstance().j].j % this.c[2];
                this.b(n9, n10);
                this.targetEntity.b(n9, n10);
                this.b((byte)10, this.facingDirection);
                ParticleEffect.getInstance().a(8);
                ParticleEffect.getInstance().b();
                return;
            }
            case 10: {
                if (this.ab > 0) {
                    byte by;
                    if (this.ab % 4 == 3) {
                        this.a((byte)1, (byte)-1, false);
                    } else {
                        this.a((byte)(this.ab % 4), (byte)-1, false);
                    }
                    this.facingDirection = by = (byte)(this.ab % 4);
                    --this.ab;
                    return;
                }
                this.b((byte)0, game.WorldManager.getInstance().npcList[game.WorldManager.getInstance().j].C);
                this.a(32);
            }
        }
    }

    public final void b(byte val, byte val2) {
        block11: while (true) {
            switch (val) {
                case 0: {
                    if (this.P[2] != 2 && this.N()) {
                        val = (byte)3;
                        continue block11;
                    }
                    if (val2 == 3) {
                        this.a((byte)1, (byte)-1, false);
                    } else {
                        this.a(val2, (byte)-1, false);
                    }
                    this.facingDirection = val2;
                    break block11;
                }
                case 1: {
                    if (this.P[2] != 2 && this.N()) {
                        val = (byte)2;
                        continue block11;
                    }
                    if (this.a.g() < 6) {
                        if (val2 == 3) {
                            this.a((byte)4, val2);
                            break block11;
                        }
                        this.a((byte)(val2 + 3), val2);
                        break block11;
                    }
                    if (val2 == 3) {
                        this.a((byte)(val * 3 + 1), (byte)-1, false);
                    } else {
                        this.a((byte)(val * 3 + val2), (byte)-1, false);
                    }
                    this.facingDirection = val2;
                    break block11;
                }
                case 2: {
                    if (this.N()) {
                        if (this.a.g() < 9) {
                            if (val2 == 3) {
                                this.a((byte)7, val2);
                            } else {
                                this.a((byte)(val2 + 6), val2);
                            }
                        } else if (val2 == 3) {
                            this.a((byte)(val * 3 + 1), (byte)-1, false);
                        } else {
                            this.a((byte)(val * 3 + val2), (byte)-1, false);
                        }
                    } else if (val2 == 3) {
                        this.a((byte)(this.h * 3 + 1), (byte)-1, false);
                    } else {
                        this.a((byte)(this.h * 3 + val2), (byte)-1, false);
                    }
                    this.facingDirection = val2;
                    break block11;
                }
                case 3: {
                    if (val2 == 3) {
                        this.a((byte)(val * 3 + 1), (byte)-1, false);
                    } else {
                        this.a((byte)(val * 3 + val2), (byte)-1, false);
                    }
                    this.facingDirection = val2;
                    break block11;
                }
                case 4: {
                    this.a((byte)(val * 3), (byte)-2, false);
                    this.facingDirection = val2;
                    this.aa = 0;
                    break block11;
                }
                case 5: {
                    this.ab = 0;
                    this.m = val2;
                    int n2 = this.posY;
                    int n3 = this.posX;
                    Player g2 = this;
                    this.k = n3;
                    g2.l = n2;
                    if (val2 == 3) {
                        this.a((byte)1, (byte)-1, false);
                    } else {
                        this.a(val2, (byte)-1, false);
                    }
                    this.facingDirection = val2;
                    break block11;
                }
                case 6: {
                    if (val2 == 3) {
                        this.a((byte)1, (byte)-1, false);
                    } else {
                        this.a(val2, (byte)-1, false);
                    }
                    this.facingDirection = val2;
                    break block11;
                }
                case 7: {
                    if (val2 == 3) {
                        this.a((byte)4, (byte)-1, false);
                    } else {
                        this.a((byte)(val2 + 3), (byte)-1, false);
                    }
                    this.facingDirection = val2;
                    break block11;
                }
                case 8: 
                case 9: {
                    this.ab = 0;
                    this.facingDirection = val2;
                }
            }
            break;
        }
        this.h = val;
        if (this.h == 0 || this.h == 1) {
            if (game.WorldManager.getInstance().o != null) {
                game.WorldManager.getInstance().o.a(true);
                return;
            }
        } else if (game.WorldManager.getInstance().o != null) {
            game.WorldManager.getInstance().o.a(false);
        }
    }

    public final boolean f(int n2) {
        return this.P[n2] != 0;
    }

    public final boolean g(int n2) {
        return this.Q[n2] != 1;
    }

    public final boolean r() {
        return this.mountState != 2 || MapEngine.getInstance().b(this.posX + 7, this.posY + 7) == 0 && MapEngine.getInstance().b(this.posX - 8, this.posY - 8) == 0;
    }

    public final void h(int n2) {
        if (n2 == -1) {
            return;
        }
        this.P[n2] = 2;
        this.spriteRenderer.b();
        this.a(n2 + 1, false);
        if (this.u == 1) {
            this.a(1, 107);
        }
        this.b((byte)0, this.facingDirection);
        this.d[0] = this.P[n2] == 2 && n2 == 0 || this.P[n2] == 2 && n2 == 1 ? 8 : 4;
        if (this.P[2] == 2 && game.WorldManager.getInstance().o != null) {
            game.WorldManager.getInstance().o.d();
        }
        this.Z = this.d[0];
        this.mountState = n2;
    }

    public final void dismount() {
        int n2;
        this.spriteRenderer.b();
        this.a(0, false);
        for (n2 = 0; n2 < 4; ++n2) {
            if (this.P[n2] != 2) continue;
            this.P[n2] = 1;
        }
        if (this.u == 1) {
            this.a(0, 107);
        }
        if (game.WorldManager.getInstance().o != null) {
            game.WorldManager.getInstance().o.c();
        }
        this.d[0] = n2 = this.c[0];
        this.mountState = -1;
    }

    public final void t() {
        this.u = 1;
        boolean bl = false;
        for (int i = 0; i < 4; ++i) {
            if (this.P[i] != 2) continue;
            bl = true;
            break;
        }
        if (bl) {
            this.a(1, 107);
            return;
        }
        this.a(0, 107);
    }

    public final void u() {
        this.u = 0;
        boolean bl = false;
        for (int i = 0; i < 4; ++i) {
            if (this.P[i] != 2) continue;
            bl = true;
            break;
        }
        if (bl) {
            this.a(1, 100);
            return;
        }
        this.a(0, 100);
    }

    private short K() {
        for (short s2 = 0; s2 < game.WorldManager.getInstance().npcList.length; s2 = (short)((short)(s2 + 1))) {
            if (!game.WorldManager.getInstance().npcList[s2].j() || game.WorldManager.getInstance().npcList[s2].a.a > 85 && game.WorldManager.getInstance().npcList[s2].a.a != 226 && game.WorldManager.getInstance().npcList[s2].a.a != 92 && game.WorldManager.getInstance().npcList[s2].a.a != 102 && game.WorldManager.getInstance().npcList[s2].a.a != 137 || game.WorldManager.getInstance().npcList[s2].t != 0 || game.WorldManager.getInstance().npcList[s2].v != 1 && game.WorldManager.getInstance().npcList[s2].v != 18 || !this.a(game.WorldManager.getInstance().npcList[s2], this.a.k(), game.WorldManager.getInstance().npcList[s2].a.k())) continue;
            if (game.WorldManager.getInstance().npcList[s2].u() == 0) {
                game.WorldManager.getInstance().a(game.WorldManager.getInstance().npcList[s2].i, game.WorldManager.getInstance().npcList[s2].j - 40, game.WorldManager.getInstance().npcList[s2]);
                if (game.WorldManager.getInstance().npcList[s2].G != null) {
                    game.WorldManager.getInstance().npcList[s2].G.d();
                }
            } else if (game.WorldManager.getInstance().npcList[s2].u() == 1) {
                game.WorldManager.getInstance().a(game.WorldManager.getInstance().npcList[s2].i, game.WorldManager.getInstance().npcList[s2].j - 40, game.WorldManager.getInstance().npcList[s2]);
                if (game.OverworldScreen.d != null && game.OverworldScreen.d.size() > 0) {
                    for (int i = 0; i < game.OverworldScreen.d.size(); ++i) {
                        if (!((WorldEntity)game.OverworldScreen.d.elementAt((int)i)).p.equals(game.WorldManager.getInstance().npcList[s2])) continue;
                        ((WorldEntity)game.OverworldScreen.d.elementAt(i)).d();
                        break;
                    }
                }
            } else {
                game.WorldManager.getInstance().a(game.WorldManager.getInstance().npcList[s2].i, game.WorldManager.getInstance().npcList[s2].j - 40, game.WorldManager.getInstance().npcList[s2]);
                if (game.WorldManager.getInstance().npcList[s2].w != 0) {
                    game.WorldManager.getInstance().npcList[s2].w();
                }
            }
            return s2;
        }
        game.WorldManager.getInstance().e();
        game.OverworldScreen.g = false;
        return -1;
    }

    private boolean w(int n2) {
        switch (game.WorldManager.getInstance().npcList[n2].t) {
            case 3: {
                short[] shortArray = game.WorldManager.getInstance().npcList[n2].a.k();
                short s2 = shortArray[0];
                short s3 = shortArray[1];
                short s4 = (short)(shortArray[2] + 16);
                short s5 = (short)(shortArray[3] + 16);
                if (!game.WorldManager.getInstance().npcList[n2].u || !this.a(game.WorldManager.getInstance().npcList[n2], this.a.k(), new short[]{s2, s3, s4, s5})) break;
                NpcEntity a2 = game.WorldManager.getInstance().npcList[n2];
                this.ownerEntity = a2;
            }
        }
        return true;
    }

    private boolean L() {
        WorldEntity f2 = null;
        this.ownerEntity = null;
        int n2 = 1;
        if (this.aj != null) {
            this.aj.removeAllElements();
        }
        block24: for (int i = 0; i < game.WorldManager.getInstance().npcList.length; ++i) {
            this.w(i);
            if (!game.WorldManager.getInstance().npcList[i].u || !this.a(game.WorldManager.getInstance().npcList[i], this.a.k(), game.WorldManager.getInstance().npcList[i].a.k())) continue;
            switch (game.WorldManager.getInstance().npcList[i].t) {
                case 0: {
                    switch (game.WorldManager.getInstance().npcList[i].v) {
                        case 0: {
                            return false;
                        }
                        case 4: 
                        case 11: {
                            if (game.WorldManager.getInstance().npcList[i].h() == 2 || !game.WorldManager.getInstance().npcList[i].j()) break;
                            f2 = game.WorldManager.getInstance().npcList[i];
                            this.ownerEntity = f2;
                            return false;
                        }
                        case 5: {
                            if (game.WorldManager.getInstance().npcList[i].h() != 2) {
                                if (this.B[5][0] == 2) {
                                    f2 = game.WorldManager.getInstance().npcList[i];
                                    this.ownerEntity = f2;
                                }
                                return false;
                            }
                        }
                        case 6: {
                            if (game.WorldManager.getInstance().npcList[i].h() == 2) break;
                            if (this.P[3] != 2) {
                                if (this.B[2][0] == 2) {
                                    f2 = game.WorldManager.getInstance().npcList[i];
                                    this.ownerEntity = f2;
                                    if (this.aj == null) {
                                        this.aj = new Vector();
                                    }
                                    this.aj.addElement(game.WorldManager.getInstance().npcList[i]);
                                    game.WorldManager.getInstance().npcList[i].f(20);
                                }
                                n2 = 0;
                                break;
                            }
                            game.WorldManager.getInstance().npcList[i].d((byte)1);
                            game.WorldManager.getInstance().stringTable.c(game.WorldManager.getInstance().npcList[i]);
                            break;
                        }
                        case 15: {
                            if (game.WorldManager.getInstance().npcList[i].h() == 2) break;
                            if (this.T[6]) {
                                f2 = game.WorldManager.getInstance().npcList[i];
                                this.ownerEntity = f2;
                                return false;
                            }
                            game.WorldManager.getInstance().npcList[i].d((byte)1);
                            game.WorldManager.getInstance().stringTable.c(game.WorldManager.getInstance().npcList[i]);
                            break;
                        }
                        case 7: {
                            if (game.WorldManager.getInstance().npcList[i].h() == 2) break;
                            if (this.P[3] != 2) {
                                if (this.B[1][0] == 2) {
                                    f2 = game.WorldManager.getInstance().npcList[i];
                                    this.ownerEntity = f2;
                                    if (this.aj == null) {
                                        this.aj = new Vector();
                                    }
                                    this.aj.addElement(game.WorldManager.getInstance().npcList[i]);
                                    game.WorldManager.getInstance().npcList[i].f(30);
                                }
                                n2 = 0;
                                break;
                            }
                            game.WorldManager.getInstance().npcList[i].d((byte)1);
                            game.WorldManager.getInstance().stringTable.c(game.WorldManager.getInstance().npcList[i]);
                            break;
                        }
                        case 8: {
                            byte by;
                            if (!game.WorldManager.getInstance().npcList[i].j()) break;
                            if ((NpcEntity)game.WorldManager.getInstance().npcList[i].p != null && ((NpcEntity)game.WorldManager.getInstance().npcList[i].p).B > ((NpcEntity)game.WorldManager.getInstance().npcList[i].p).A) {
                                return false;
                            }
                            block25: for (n2 = 0; n2 < game.WorldManager.getInstance().npcList.length; ++n2) {
                                if (!game.WorldManager.getInstance().npcList[n2].u || game.WorldManager.getInstance().npcList[n2].equals(game.WorldManager.getInstance().npcList[i]) || game.WorldManager.getInstance().npcList[n2].t != 0 || game.WorldManager.getInstance().npcList[n2].v != 8 && game.WorldManager.getInstance().npcList[n2].v != 11) continue;
                                switch (this.facingDirection) {
                                    case 2: {
                                        if (!EngineUtils.a(game.WorldManager.getInstance().npcList[i].i, game.WorldManager.getInstance().npcList[i].j - 8, game.WorldManager.getInstance().npcList[n2].i, game.WorldManager.getInstance().npcList[n2].j, game.WorldManager.getInstance().npcList[i].a.k(), game.WorldManager.getInstance().npcList[n2].a.k())) continue block25;
                                        return false;
                                    }
                                    case 0: {
                                        if (!EngineUtils.a(game.WorldManager.getInstance().npcList[i].i, game.WorldManager.getInstance().npcList[i].j + 8, game.WorldManager.getInstance().npcList[n2].i, game.WorldManager.getInstance().npcList[n2].j, game.WorldManager.getInstance().npcList[i].a.k(), game.WorldManager.getInstance().npcList[n2].a.k())) continue block25;
                                        return false;
                                    }
                                    case 3: {
                                        if (!EngineUtils.a(game.WorldManager.getInstance().npcList[i].i - 8, game.WorldManager.getInstance().npcList[i].j, game.WorldManager.getInstance().npcList[n2].i, game.WorldManager.getInstance().npcList[n2].j, game.WorldManager.getInstance().npcList[i].a.k(), game.WorldManager.getInstance().npcList[n2].a.k())) continue block25;
                                        return false;
                                    }
                                    case 1: {
                                        if (!EngineUtils.a(game.WorldManager.getInstance().npcList[i].i + 8, game.WorldManager.getInstance().npcList[i].j, game.WorldManager.getInstance().npcList[n2].i, game.WorldManager.getInstance().npcList[n2].j, game.WorldManager.getInstance().npcList[i].a.k(), game.WorldManager.getInstance().npcList[n2].a.k())) continue block25;
                                        return false;
                                    }
                                }
                            }
                            game.WorldManager.getInstance().npcList[i].d((byte)1);
                            game.WorldManager.getInstance().npcList[i].n = by = this.facingDirection;
                            return false;
                        }
                        case 9: {
                            if (MapEngine.getInstance().b(this.posX, this.posY) == 2 || MapEngine.getInstance().b(this.posX, this.posY) == 1) {
                                return false;
                            }
                            if (this.facingDirection != 3 && this.facingDirection != 1) break;
                            this.ab = 0;
                            this.b((byte)7, this.facingDirection);
                            f2 = this;
                            game.WorldManager.getInstance().npcList[i].p = f2;
                            f2 = game.WorldManager.getInstance().npcList[i];
                            this.ownerEntity = f2;
                            return false;
                        }
                        case 10: {
                            if (MapEngine.getInstance().b(this.posX, this.posY) == 2 || MapEngine.getInstance().b(this.posX, this.posY) == 1) {
                                return false;
                            }
                            if (this.facingDirection != 0 && this.facingDirection != 2) break;
                            this.ab = 0;
                            this.b((byte)7, this.facingDirection);
                            f2 = this;
                            game.WorldManager.getInstance().npcList[i].p = f2;
                            f2 = game.WorldManager.getInstance().npcList[i];
                            this.ownerEntity = f2;
                            return false;
                        }
                        case 14: {
                            return false;
                        }
                        case 16: {
                            f2 = game.WorldManager.getInstance().npcList[i];
                            this.ownerEntity = f2;
                        }
                    }
                    continue block24;
                }
                case 3: {
                    return false;
                }
                case 1: {
                    if (game.WorldManager.getInstance().npcList[i].v != 3) continue block24;
                    return false;
                }
            }
        }
        return n2 != 0;
    }

    private boolean M() {
        this.Z = this.d[0];
        int n2 = this.posX - 8;
        int n3 = this.posY - 8;
        int n4 = this.posX + 7;
        int n5 = this.posY + 7;
        byte[] byteArray = new byte[]{-1, -1, -1, -1, -1};
        switch (this.facingDirection) {
            case 2: {
                if (MapEngine.getInstance().c(this.posX, n3 - this.Z)) {
                    return false;
                }
                byteArray[0] = MapEngine.getInstance().b(n2, n3 - this.Z);
                byteArray[1] = MapEngine.getInstance().b(n4, n3 - this.Z);
                byteArray[2] = MapEngine.getInstance().b(this.posX, n3 - this.Z);
                if (!this.d(byteArray[0]) && !this.d(byteArray[1])) {
                    if (MapEngine.getInstance().b(this.posX, this.posY) == 3) {
                        return false;
                    }
                    byteArray[0] = MapEngine.getInstance().b(this.posX - 16, n3 - this.Z);
                    byteArray[1] = MapEngine.getInstance().b(this.posX + 16, n3 - this.Z);
                    byteArray[3] = MapEngine.getInstance().b(this.posX - 16, this.posY);
                    byteArray[4] = MapEngine.getInstance().b(this.posX + 16, this.posY);
                    this.Z = this.d[1];
                    if (!this.d(byteArray[0])) {
                        return this.d(byteArray[1], (byte)1);
                    }
                    if (!this.d(byteArray[1])) {
                        return this.d(byteArray[0], (byte)3);
                    }
                    if (this.d(byteArray[4])) {
                        return this.d(byteArray[1], (byte)1);
                    }
                    if (this.d(byteArray[3])) {
                        return this.d(byteArray[0], (byte)3);
                    }
                } else {
                    if (!this.d(byteArray[0])) {
                        this.Z = this.d[1];
                        return this.d(byteArray[1], (byte)1);
                    }
                    if (!this.d(byteArray[1])) {
                        this.Z = this.d[1];
                        return this.d(byteArray[0], (byte)3);
                    }
                    return this.d(byteArray[2], (byte)2);
                }
            }
            case 0: {
                if (MapEngine.getInstance().c(this.posX, n5 + this.Z)) {
                    return false;
                }
                byteArray[0] = MapEngine.getInstance().b(n2, n5 + this.Z);
                byteArray[1] = MapEngine.getInstance().b(n4, n5 + this.Z);
                byteArray[2] = MapEngine.getInstance().b(this.posX, n5 + this.Z);
                if (!this.d(byteArray[0]) && !this.d(byteArray[1])) {
                    if (MapEngine.getInstance().b(this.posX, this.posY) == 3) {
                        return false;
                    }
                    byteArray[0] = MapEngine.getInstance().b(n2 - 16, n5 + this.Z);
                    byteArray[1] = MapEngine.getInstance().b(n4 + 16, n5 + this.Z);
                    byteArray[3] = MapEngine.getInstance().b(this.posX - 16, this.posY);
                    byteArray[4] = MapEngine.getInstance().b(this.posX + 16, this.posY);
                    this.Z = this.d[1];
                    if (!this.d(byteArray[0])) {
                        return this.d(byteArray[1], (byte)1);
                    }
                    if (!this.d(byteArray[1])) {
                        return this.d(byteArray[0], (byte)3);
                    }
                    if (this.d(byteArray[4])) {
                        return this.d(byteArray[1], (byte)1);
                    }
                    if (this.d(byteArray[3])) {
                        return this.d(byteArray[0], (byte)3);
                    }
                } else {
                    if (!this.d(byteArray[0])) {
                        this.Z = this.d[1];
                        return this.d(byteArray[1], (byte)1);
                    }
                    if (!this.d(byteArray[1])) {
                        this.Z = this.d[1];
                        return this.d(byteArray[0], (byte)3);
                    }
                    return this.d(byteArray[2], (byte)0);
                }
            }
            case 3: {
                if (MapEngine.getInstance().c(n2 - this.Z, this.posY)) {
                    return false;
                }
                byteArray[0] = MapEngine.getInstance().b(n2 - this.Z, n3);
                byteArray[1] = MapEngine.getInstance().b(n2 - this.Z, n5);
                byteArray[2] = MapEngine.getInstance().b(n2 - this.Z, this.posY);
                if (!this.d(byteArray[0]) && !this.d(byteArray[1])) {
                    if (MapEngine.getInstance().b(this.posX, this.posY) == 3) {
                        return false;
                    }
                    byteArray[0] = MapEngine.getInstance().b(n2 - this.Z, n3 - 16);
                    byteArray[1] = MapEngine.getInstance().b(n2 - this.Z, n5 + 16);
                    byteArray[3] = MapEngine.getInstance().b(this.posX, this.posY - 16);
                    byteArray[4] = MapEngine.getInstance().b(this.posX, this.posY + 16);
                    this.Z = this.d[1];
                    if (!this.d(byteArray[0])) {
                        return this.d(byteArray[1], (byte)0);
                    }
                    if (!this.d(byteArray[1])) {
                        return this.d(byteArray[0], (byte)2);
                    }
                    if (this.d(byteArray[4])) {
                        return this.d(byteArray[1], (byte)0);
                    }
                    if (this.d(byteArray[3])) {
                        return this.d(byteArray[0], (byte)2);
                    }
                } else {
                    if (!this.d(byteArray[0])) {
                        this.Z = this.d[1];
                        return this.d(byteArray[1], (byte)0);
                    }
                    if (!this.d(byteArray[1])) {
                        this.Z = this.d[1];
                        return this.d(byteArray[0], (byte)2);
                    }
                    return this.d(byteArray[2], (byte)3);
                }
            }
            case 1: {
                if (MapEngine.getInstance().c(n4 + this.Z, this.posY)) {
                    return false;
                }
                byteArray[0] = MapEngine.getInstance().b(n4 + this.Z, n3);
                byteArray[1] = MapEngine.getInstance().b(n4 + this.Z, n5);
                byteArray[2] = MapEngine.getInstance().b(n4 + this.Z, this.posY);
                if (!this.d(byteArray[0]) && !this.d(byteArray[1])) {
                    if (MapEngine.getInstance().b(this.posX, this.posY) == 3) {
                        return false;
                    }
                    byteArray[0] = MapEngine.getInstance().b(n4 + this.Z, n3 - 16);
                    byteArray[1] = MapEngine.getInstance().b(n4 + this.Z, n5 + 16);
                    byteArray[3] = MapEngine.getInstance().b(this.posX, this.posY - 16);
                    byteArray[4] = MapEngine.getInstance().b(this.posX, this.posY + 16);
                    this.Z = this.d[1];
                    if (!this.d(byteArray[0])) {
                        return this.d(byteArray[1], (byte)0);
                    }
                    if (!this.d(byteArray[1])) {
                        return this.d(byteArray[0], (byte)2);
                    }
                    if (this.d(byteArray[4])) {
                        return this.d(byteArray[1], (byte)0);
                    }
                    if (!this.d(byteArray[3])) break;
                    return this.d(byteArray[0], (byte)2);
                }
                if (!this.d(byteArray[0])) {
                    this.Z = this.d[1];
                    return this.d(byteArray[1], (byte)0);
                }
                if (!this.d(byteArray[1])) {
                    this.Z = this.d[1];
                    return this.d(byteArray[0], (byte)2);
                }
                return this.d(byteArray[2], (byte)1);
            }
        }
        return true;
    }

    private boolean d(byte val) {
        switch (val) {
            case 1: {
                return false;
            }
            case 2: {
                return this.B[3][0] == 2;
            }
        }
        return true;
    }

    public final boolean a(NpcEntity a2, short[] shortArray, short[] sArray2) {
        if (shortArray2 == null) {
            return false;
        }
        switch (this.facingDirection) {
            case 3: {
                if (a2.v == 14) {
                    return game.Player.a(a2, shortArray2, shortArray, this.posX - this.d[0], this.posY);
                }
                if (!EngineUtils.a(this.posX - this.d[0], this.posY, a2.i, a2.j, shortArray, shortArray2)) break;
                return true;
            }
            case 1: {
                if (a2.v == 14) {
                    return game.Player.a(a2, shortArray2, shortArray, this.posX + this.d[0], this.posY);
                }
                if (!EngineUtils.a(this.posX + this.d[0], this.posY, a2.i, a2.j, shortArray, shortArray2)) break;
                return true;
            }
            case 2: {
                if (a2.v == 14) {
                    return game.Player.a(a2, shortArray2, shortArray, this.posX, this.posY - this.d[0]);
                }
                if (!EngineUtils.a(this.posX, this.posY - this.d[0], a2.i, a2.j, shortArray, shortArray2)) break;
                return true;
            }
            case 0: {
                if (a2.v == 14) {
                    return game.Player.a(a2, shortArray2, shortArray, this.posX, this.posY + this.d[0]);
                }
                if (!EngineUtils.a(this.posX, this.posY + this.d[0], a2.i, a2.j, shortArray, shortArray2)) break;
                return true;
            }
        }
        return false;
    }

    private static boolean a(NpcEntity a2, short[] shortArray, short[] sArray2, int n2, int n3) {
        switch (a2.a.g()) {
            case 1: {
                if (!EngineUtils.a(a2.i + shortArray[0], a2.j + shortArray[1], shortArray[2] + (a2.A << 4), (int)shortArray[3], n2, n3, shortArray2)) break;
                return true;
            }
            case 3: {
                if (!EngineUtils.a(a2.i + shortArray[0] - (a2.A << 4), a2.j + shortArray[1], shortArray[2] + (a2.A << 4), (int)shortArray[3], n2, n3, shortArray2)) break;
                return true;
            }
            case 2: {
                if (!EngineUtils.a(a2.i + shortArray[0], a2.j + shortArray[1] - (a2.A << 4), (int)shortArray[2], shortArray[3] + (a2.A << 4), n2, n3, shortArray2)) break;
                return true;
            }
            case 0: {
                if (!EngineUtils.a(a2.i + shortArray[0], a2.j + shortArray[1], (int)shortArray[2], shortArray[3] + (a2.A << 4), n2, n3, shortArray2)) break;
                return true;
            }
        }
        return false;
    }

    private boolean N() {
        boolean bl = true;
        switch (this.facingDirection) {
            case 3: {
                bl = this.d(MapEngine.getInstance().b(this.posX - this.d[0], this.posY));
                break;
            }
            case 1: {
                bl = this.d(MapEngine.getInstance().b(this.posX + this.d[0], this.posY));
                break;
            }
            case 2: {
                bl = this.d(MapEngine.getInstance().b(this.posX, this.posY - this.d[0]));
                break;
            }
            case 0: {
                bl = this.d(MapEngine.getInstance().b(this.posX, this.posY + this.d[0]));
            }
        }
        if (bl && MapEngine.getInstance().b(this.posX, this.posY) != 2) {
            return false;
        }
        return bl;
    }

    private boolean d(byte val, byte val2) {
        switch (val) {
            case -1: /* KEY_UP */ 
            case 0: {
                this.b((byte)1, val2);
                break;
            }
            case 1: {
                if (MapEngine.getInstance().b(this.posX, this.posY) == 2) {
                    this.b((byte)2, val2);
                }
                return false;
            }
            case 2: {
                if (this.d((byte)2)) {
                    this.b((byte)2, val2);
                    break;
                }
                return false;
            }
            case 3: {
                this.b((byte)6, val2);
            }
        }
        return true;
    }

    public final void v() {
        if (((NpcEntity)this.ownerEntity).h() == 1) {
            game.WorldManager.getInstance().S.aw();
            game.WorldManager.getInstance().S.b("Bảo rương này đã trống");
            return;
        }
        if (((NpcEntity)this.ownerEntity).v == 0) {
            ((NpcEntity)this.ownerEntity).d((byte)1);
            if (this.a((int)((NpcEntity)this.ownerEntity).F, (int)((NpcEntity)this.ownerEntity).D, (byte)((NpcEntity)this.ownerEntity).E)) {
                this.c(((NpcEntity)this.ownerEntity).F, ((NpcEntity)this.ownerEntity).D, (byte)((NpcEntity)this.ownerEntity).E);
                String string = null;
                if (((NpcEntity)this.ownerEntity).E == 0) {
                    string = BaseScreen.f(GameDatabase.gameDatabase[4][((NpcEntity)this.ownerEntity).F][0]);
                } else if (((NpcEntity)this.ownerEntity).E == 2) {
                    string = BaseScreen.f(GameDatabase.gameDatabase[3][((NpcEntity)this.ownerEntity).F][0]);
                }
                game.WorldManager.getInstance().S.a("Đạt được: " + string, (int)((NpcEntity)this.ownerEntity).D);
            } else {
                game.WorldManager.getInstance().S.av();
            }
            this.b((byte)0, this.facingDirection);
            return;
        }
        if (((NpcEntity)this.ownerEntity).v == 1) {
            if (this.b(17, 1, (byte)2)) {
                ((NpcEntity)this.ownerEntity).d((byte)1);
                this.d(17, 1, (byte)2);
                if (this.a((int)((NpcEntity)this.ownerEntity).F, (int)((NpcEntity)this.ownerEntity).D, (byte)((NpcEntity)this.ownerEntity).E)) {
                    this.c(((NpcEntity)this.ownerEntity).F, ((NpcEntity)this.ownerEntity).D, (byte)((NpcEntity)this.ownerEntity).E);
                    String string = null;
                    if (((NpcEntity)this.ownerEntity).E == 0) {
                        string = BaseScreen.f(GameDatabase.gameDatabase[4][((NpcEntity)this.ownerEntity).F][0]);
                    } else if (((NpcEntity)this.ownerEntity).E == 2) {
                        string = BaseScreen.f(GameDatabase.gameDatabase[3][((NpcEntity)this.ownerEntity).F][0]);
                    }
                    game.WorldManager.getInstance().S.a("Đạt được: " + string, (int)((NpcEntity)this.ownerEntity).D);
                } else {
                    game.WorldManager.getInstance().S.av();
                }
            } else {
                game.WorldManager.getInstance().S.au();
            }
            this.b((byte)0, this.facingDirection);
        }
    }

    public final boolean hasActivePet() {
        if (this.ownerEntity == null) {
            return false;
        }
        if (((NpcEntity)this.ownerEntity).h() == 0) {
            game.OverworldScreen.h = true;
            if (((NpcEntity)this.ownerEntity).v == 7 || ((NpcEntity)this.ownerEntity).v == 6) {
                for (int i = 0; i < this.aj.size(); ++i) {
                    NpcEntity a2 = (NpcEntity)this.aj.elementAt(i);
                    a2.d((byte)1);
                    a2.x();
                }
                this.aj.removeAllElements();
            } else if (((NpcEntity)this.ownerEntity).v != 16) {
                ((NpcEntity)this.ownerEntity).d((byte)1);
            }
            return true;
        }
        return false;
    }

    private static boolean a(int n2, int n3, Vector list) {
        for (int i = 0; i < list.size(); ++i) {
            int[] intArray = (int[])list.elementAt(i);
            if (intArray[0] != n2) continue;
            return intArray[1] < 99;
        }
        return n3 <= 99;
    }

    private static boolean b(int n2, int n3, Vector list) {
        for (int i = 0; i < list.size(); ++i) {
            int[] intArray = (int[])list.elementAt(i);
            if (intArray[0] != n2) continue;
            return intArray[1] - n3 >= 0;
        }
        return false;
    }

    private static boolean c(int n2, int n3, Vector list) {
        int[] intArray;
        for (int i = 0; i < list.size(); ++i) {
            intArray = (int[])list.elementAt(i);
            if (intArray[0] != n2) continue;
            intArray[1] = intArray[1] + n3;
            if (intArray[1] >= 99) {
                intArray[1] = 99;
            }
            return true;
        }
        intArray = new int[]{n2, n3, 0};
        list.addElement(intArray);
        return false;
    }

    private static boolean d(int n2, int n3, Vector list) {
        for (int i = 0; i < list.size(); ++i) {
            int[] intArray = (int[])list.elementAt(i);
            if (intArray[0] != n2) continue;
            intArray[1] = intArray[1] - n3;
            if (intArray[1] <= 0 && intArray[2] == 0) {
                list.removeElementAt(i);
            }
            return true;
        }
        return false;
    }

    public final boolean a(int n2, int n3, byte val3) {
        switch (val3) {
            case 0: {
                if (GameDatabase.gameDatabase[4][n2][5] == 0) {
                    return game.Player.a(n2, n3, this.bagPotions);
                }
                return game.Player.a(n2, n3, this.bagItems);
            }
            case 2: {
                if (n2 >= 12) {
                    return game.Player.a(n2, n3, this.bagScrolls);
                }
                n3 = n2;
                Player g2 = this;
                for (val3 = 0; val3 < g2.L.size(); val3 = (byte)(val3 + 1)) {
                    if (((int[])g2.L.elementAt(val3))[0] != n3) continue;
                    return false;
                }
                return true;
            }
        }
        return false;
    }

    public final boolean b(int n2, int n3, byte val3) {
        switch (val3) {
            case 0: {
                if (GameDatabase.gameDatabase[4][n2][5] == 0) {
                    return game.Player.b(n2, n3, this.bagPotions);
                }
                return game.Player.b(n2, n3, this.bagItems);
            }
            case 2: {
                return game.Player.b(n2, n3, this.bagScrolls);
            }
        }
        return false;
    }

    public final boolean c(int n2, int n3, byte val3) {
        switch (val3) {
            case 0: {
                if (GameDatabase.gameDatabase[4][n2][5] == 0) {
                    return game.Player.c(n2, n3, this.bagPotions);
                }
                return game.Player.c(n2, n3, this.bagItems);
            }
            case 2: {
                if (n2 >= 12) {
                    if (n2 == 17) {
                        return game.Player.c(n2, n3 * 5, this.bagScrolls);
                    }
                    return game.Player.c(n2, n3, this.bagScrolls);
                }
                n3 = n2;
                Player g2 = this;
                int[] intArray = new int[]{n3, 0, 0};
                g2.L.addElement(intArray);
                return true;
            }
        }
        return false;
    }

    public final boolean d(int n2, int n3, byte val3) {
        switch (val3) {
            case 0: {
                if (GameDatabase.gameDatabase[4][n2][5] == 0) {
                    return game.Player.d(n2, n3, this.bagPotions);
                }
                return game.Player.d(n2, n3, this.bagItems);
            }
            case 2: {
                return game.Player.d(n2, n3, this.bagScrolls);
            }
        }
        return false;
    }

    public final int a(int n2, byte val2) {
        switch (val2) {
            case 0: {
                if (GameDatabase.gameDatabase[4][n2][5] == 0) {
                    for (int i = 0; i < this.bagPotions.size(); ++i) {
                        int[] intArray = (int[])this.bagPotions.elementAt(i);
                        if (intArray[0] != n2) continue;
                        return intArray[1];
                    }
                } else {
                    for (int i3 = 0; i3 < this.bagItems.size(); ++i3) {
                        int[] intArray = (int[])this.bagItems.elementAt(i3);
                        if (intArray[0] != n2) continue;
                        return intArray[1];
                    }
                }
                break;
            }
            case 2: {
                for (int i4 = 0; i4 < this.bagScrolls.size(); ++i4) {
                    int[] intArray = (int[])this.bagScrolls.elementAt(i4);
                    if (intArray[0] != n2) continue;
                    return intArray[1];
                }
                break;
            }
        }
        return 0;
    }

    public final void x() {
        int[] intArray;
        int n2;
        if (this.S == null) {
            this.S = new Vector();
        } else {
            this.S.removeAllElements();
        }
        for (n2 = 0; n2 < this.bagPotions.size(); ++n2) {
            intArray = (int[])this.bagPotions.elementAt(n2);
            if (GameDatabase.gameDatabase[4][intArray[0]][4] != 0) continue;
            this.S.addElement(intArray);
        }
        for (n2 = 0; n2 < this.bagItems.size(); ++n2) {
            intArray = (int[])this.bagItems.elementAt(n2);
            if (GameDatabase.gameDatabase[4][intArray[0]][4] != 0) continue;
            this.S.addElement(intArray);
        }
    }

    public final boolean i(int n2) {
        int[] intArray;
        if (n2 == 0) {
            intArray = new int[]{n2, 0, 0};
        } else {
            intArray = new int[]{n2, 1, 0};
            if (n2 == 1 || n2 == 2 || n2 == 3 || n2 == 4) {
                this.P[n2 - 1] = 1;
            }
        }
        this.bagBadges.addElement(intArray);
        return true;
    }

    public final void c(int n2, int n3) {
        int[] intArray;
        for (int i = 0; i < this.bagBadges.size(); ++i) {
            intArray = (int[])this.bagBadges.elementAt(i);
            if (intArray[0] != n2) continue;
            intArray[2] = intArray[2] + n3;
            if (intArray[2] >= 99) {
                intArray[2] = 99;
            }
            return;
        }
        intArray = new int[]{n2, 0, n3};
        this.bagBadges.addElement(intArray);
    }

    public final int d(int n2, int n3) {
        if (0 < this.bagBadges.size()) {
            int[] intArray = (int[])this.bagBadges.elementAt(0);
            if (intArray[0] == n2 && (n2 == 7 || n2 == 9 || n2 == 8)) {
                if (intArray[1] <= 99) {
                    return 0;
                }
                return 1;
            }
            return -1;
        }
        if (n3 > 99) {
            return 1;
        }
        return -1;
    }

    public final boolean e(int n2, int n3) {
        for (int i = 0; i < this.bagBadges.size(); ++i) {
            int[] intArray = (int[])this.bagBadges.elementAt(i);
            if (intArray[0] == n2 && (n2 == 7 || n2 == 9 || n2 == 8)) {
                this.petParty[n3].i((byte)n2);
                if (intArray[2] > 0) {
                    intArray[2] = intArray[2] - 1;
                    this.bagBadges.setElementAt(new int[]{intArray[0], 0, intArray[2]}, i);
                } else if (intArray[2] <= 0) {
                    this.bagBadges.removeElementAt(i);
                }
                return true;
            }
            if (intArray[0] != n2 || intArray[1] != 0) continue;
            this.bagBadges.setElementAt(new int[]{intArray[0], 1, intArray[2]}, i);
            break;
        }
        return false;
    }

    public final void j(int n2) {
        for (int i = 0; i < this.bagBadges.size(); ++i) {
            int[] intArray = (int[])this.bagBadges.elementAt(i);
            if (intArray[0] != n2 || intArray[1] != 1) continue;
            this.bagBadges.setElementAt(new int[]{intArray[0], 0, intArray[2]}, i);
            return;
        }
    }

    public final boolean hasBadge(int badgeId) {
        for (int i = 0; i < this.bagBadges.size(); ++i) {
            int[] intArray = (int[])this.bagBadges.elementAt(i);
            if (intArray[0] != n2 || intArray[1] != 1) continue;
            return true;
        }
        return false;
    }

    public final boolean consumeBall(int ballId) {
        for (int i = 0; i < this.bagBalls.size(); ++i) {
            int[] intArray = (int[])this.bagBalls.elementAt(i);
            if (intArray[0] != n2) continue;
            intArray[1] = 0;
            return true;
        }
        return false;
    }

    public final void f(int n2, int n3) {
        short s2;
        boolean bl;
        int[] intArray;
        Player g2;
        int n4;
        block7: {
            if (this.petParty[n3].c[5] >= 0) {
                this.l(this.petParty[n3].c[5]);
                this.petParty[n3].c[5] = -1;
            }
            n4 = 0;
            g2 = this;
            for (n4 = 0; n4 < g2.L.size(); ++n4) {
                intArray = (int[])g2.L.elementAt(n4);
                if (intArray[0] != n2 || intArray[1] != 1) continue;
                bl = true;
                break block7;
            }
            bl = false;
        }
        if (bl) {
            this.l(n2);
            boolean bl2 = false;
            for (n4 = 0; n4 < this.partyPetCount; ++n4) {
                if (this.petParty[n4].c[5] != n2) continue;
                this.petParty[n4].c[5] = -1;
                bl2 = true;
                break;
            }
            if (!bl2) {
                for (n4 = 0; n4 < this.bagQuestItems.size(); ++n4) {
                    int[] intArray2 = (int[])this.bagQuestItems.elementAt(n3);
                    if (intArray2[2] != n2) continue;
                    intArray2[2] = -1;
                    break;
                }
            }
        }
        g2 = this;
        for (n4 = 0; n4 < g2.L.size(); ++n4) {
            intArray = (int[])g2.L.elementAt(n4);
            if (intArray[0] != n2) continue;
            intArray[1] = 1;
            break;
        }
        this.petParty[n3].c[5] = s2 = (short)n2;
    }

    public final byte getActivePetIndex() {
        if (this.partyPetCount < 6) {
            return 0;
        }
        if (this.bagQuestItems.size() < 100) {
            return 1;
        }
        return 2;
    }

    public final boolean isPartyAlive() {
        return this.bagQuestItems.size() < 100;
    }

    public final void a(int n2, int n3, byte val3, short sVal4, int[] intArray) {
        this.petParty[this.partyPetCount] = new game.Pet();
        this.petParty[this.partyPetCount].a(n2, n3, (short)-1, val3, sVal4, (byte)-1);
        this.petParty[this.partyPetCount].b(intArray);
        this.a((byte)this.petParty[this.partyPetCount].j((byte)1), n2, (byte)2);
        ++this.partyPetCount;
    }

    public final void a(int n2, int n3, int n4, byte val4, short sVal5, int[] intArray) {
        this.petParty[this.partyPetCount] = new game.Pet();
        System.arraycopy(this.petParty, n2, this.petParty, n2 + 1, this.partyPetCount - n2);
        this.petParty[n2] = null;
        this.petParty[n2] = new game.Pet();
        this.petParty[n2].a(n3, n4, (short)-1, val4, sVal5, (byte)-1);
        this.petParty[n2].b(intArray);
        ++this.partyPetCount;
    }

    public final void a(int[] intArray) {
        this.petParty[this.partyPetCount] = new game.Pet();
        this.petParty[this.partyPetCount].a(intArray[0], intArray[1], (short)intArray[2], (byte)intArray[3], (short)intArray[4], (byte)intArray[5]);
        this.petParty[this.partyPetCount].a((short)intArray[6], intArray[7], intArray[8]);
        int[] intArray2 = new int[intArray.length - 9];
        for (int i = 0; i < intArray2.length; ++i) {
            intArray2[i] = intArray[i + 9];
        }
        this.petParty[this.partyPetCount].b(intArray2);
        this.a((byte)this.petParty[this.partyPetCount].j((byte)1), intArray[0], (byte)2);
        ++this.partyPetCount;
    }

    public final void m(int n2) {
        this.petParty[n2] = null;
        while (n2 < this.partyPetCount - 1) {
            this.petParty[n2] = this.petParty[n2 + 1];
            this.petParty[n2 + 1] = null;
            ++n2;
        }
        --this.partyPetCount;
    }

    public final void n(int n2) {
        for (int i = 0; i < this.partyPetCount; ++i) {
            if (this.petParty[i].getPetId() != n2) continue;
            this.m(i);
            return;
        }
    }

    public final boolean o(int n2) {
        int n3 = 0;
        for (int i = 0; i < this.partyPetCount; ++i) {
            if (i == n2 || !this.petParty[i].S()) continue;
            ++n3;
        }
        return n3 > 0;
    }

    public final int A() {
        int n2;
        int[] intArray = new int[this.partyPetCount];
        for (n2 = 0; n2 < this.partyPetCount; ++n2) {
            intArray[n2] = this.petParty[n2].c[1] - this.petParty[n2].d[1];
        }
        n2 = intArray[0];
        for (int i = 1; i < intArray.length; ++i) {
            if (n2 >= intArray[i]) continue;
            n2 = intArray[i];
        }
        if (n2 == 0) {
            return -1;
        }
        return n2;
    }

    public final void p(int n2) {
        game.Pet b2 = this.petParty[n2];
        while (n2 > 0) {
            this.petParty[n2] = this.petParty[n2 - 1];
            --n2;
        }
        this.petParty[0] = b2;
    }

    public final void a(int n2, int n3, byte val3, short sVal4, int n4, int n5, int[] intArray) {
        int[] intArray2 = new int[9 + intArray.length];
        int[] nArray3 = intArray2;
        intArray2[0] = n2;
        nArray3[1] = n3;
        nArray3[2] = -1;
        nArray3[3] = val3;
        nArray3[4] = sVal4;
        nArray3[5] = -1;
        nArray3[6] = n4;
        nArray3[7] = 0;
        nArray3[8] = n5;
        System.arraycopy(intArray, 0, nArray3, 9, intArray.length);
        this.bagQuestItems.addElement(nArray3);
        this.a((byte)GameDatabase.gameDatabase[0][n2][1], n2, (byte)2);
    }

    public final void b(int[] intArray) {
        this.bagQuestItems.addElement(intArray);
        this.a((byte)GameDatabase.gameDatabase[0][intArray[0]][1], intArray[0], (byte)2);
    }

    public final void q(int n2) {
        this.bagQuestItems.removeElementAt(n2);
    }

    public final void r(int n2) {
        Object object = this;
        game.Pet b2 = new game.Pet();
        object = (int[])((Player)object).O.elementAt(n2);
        b2.a((int)object[0], (int)object[1], (short)object[2], (byte)object[3], (short)object[4], (byte)object[5]);
        b2.a((short)object[6], (int)object[7], (int)object[8]);
        int[] intArray = new int[((Object)object).length - 9];
        for (int i = 0; i < intArray.length; ++i) {
            intArray[i] = (int)object[i + 9];
        }
        b2.b(intArray);
        this.petParty[this.partyPetCount] = b2;
        ++this.partyPetCount;
        this.q(n2);
    }

    public final void a(short sVal) {
        this.quickItemSlots[this.I] = sVal;
        this.I = (byte)(this.I + 1);
    }

    public final void a(byte val, byte val2, byte val3) {
        this.B[val][val2] = val3;
        if (this.B[0][0] == 2) {
            ScreenView.getInstance().a(GameDatabase.spriteTable((byte)2, (short)0, (byte)5) / 2, GameDatabase.spriteTable((byte)2, (short)0, (byte)5) / 2);
        }
    }

    public final byte c(byte val, byte val2) {
        return this.B[val][val2];
    }

    public final void a(byte val, int n2, byte val3) {
        boolean bl;
        block11: {
            Player g2 = this;
            for (int i = 0; i < g2.E[val]; ++i) {
                if (g2.D[val][i] != n2) continue;
                bl = false;
                break block11;
            }
            bl = true;
        }
        if (bl) {
            this.D[val][this.E[val]] = (byte)n2;
            byte by3 = val;
            this.E[by3] = (byte)(this.E[by3] + 1);
            if (val3 == 2) {
                this.F = (byte)(this.F + 1);
                if (GameDatabase.gameDatabase[0][n2][22] == 2) {
                    this.G = (byte)(this.G + 1);
                } else if (GameDatabase.gameDatabase[0][n2][22] == 1) {
                    this.H = (byte)(this.H + 1);
                }
            }
            this.C[val][n2 - this.W[val]] = val3;
            return;
        }
        if (this.a(val, n2) <= 1) {
            if (val3 == 2) {
                this.F = (byte)(this.F + 1);
                if (GameDatabase.gameDatabase[0][n2][22] == 2) {
                    this.G = (byte)(this.G + 1);
                } else if (GameDatabase.gameDatabase[0][n2][22] == 1) {
                    this.H = (byte)(this.H + 1);
                }
            }
            this.C[val][n2 - this.W[val]] = val3;
        }
    }

    public final byte a(byte val, int n2) {
        return this.C[val][n2 - this.W[val]];
    }

    public final void B() {
        this.ac = true;
    }

    public final int C() {
        if (game.WorldManager.getInstance().f == 4 && game.WorldManager.getInstance().g == 1) {
            return EngineUtils.randomRange(4, 8);
        }
        return EngineUtils.b(this.ad, this.ae);
    }

    private void O() {
        if (game.WorldManager.getInstance().M.h() || game.OverworldScreen.t == 0) {
            return;
        }
        --this.repelSteps;
        if (this.repelSteps <= 0) {
            game.WorldManager.getInstance().q();
            this.repelSteps = 0;
        }
        --this.encounterSteps;
        if (this.encounterSteps == 0) {
            this.spriteRenderer.a(0);
            this.encounterSteps = -1;
        }
        if (!game.WorldManager.getInstance().c() || this.encounterSteps > 0) {
            return;
        }
        if (this.ac && this.badges > 0 && this.P[1] != 2 && this.P[3] != 2) {
            --this.badges;
        }
    }

    public final boolean D() {
        return this.badges <= 0;
    }

    public final int getGold() {
        return this.gold;
    }

    public final void addGold(int delta) {
        this.gold += delta;
    }

    public final void resetGold() {
        this.gold = 0;
    }

    public final boolean hasGold(int threshold) {
        return this.gold >= threshold;
    }

    public final int getArenaPoints() {
        return this.arenaPoints;
    }

    public final void addArenaPoints(int delta) {
        this.arenaPoints += delta;
    }

    public final void resetArenaPoints() {
        this.arenaPoints = 0;
    }

    public final boolean hasArenaPoints(int threshold) {
        return this.arenaPoints >= threshold;
    }

    public final boolean b(int n2, int n3, int n4) {
        if (GameDatabase.gameDatabase[n4][n2][4] == 0) {
            return this.hasExceededPrimarySteps(n3);
        }
        return this.hasExceededSecondarySteps(n3);
    }

    public final void I() {
        this.a(68, 7, (byte)2, (short)2, new int[]{1, 40, 45});
        this.i(0);
    }

    public final boolean a(int n2, boolean flag2) {
        super.a(n2, flag2);
        if (this.encounterSteps > 0) {
            this.spriteRenderer.a(1);
        }
        return true;
    }

    public final void J() {
        this.b((byte)0, this.facingDirection);
    }

    static {
        ah = new byte[][]{{9, 2, 9, 3, 0}};
        ai = new short[][]{{0, 3, 112, 256}};
    }
}

