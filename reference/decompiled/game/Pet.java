/*
 * Decompiled with CFR 0.152.
 */
package game;

import game.Player;
import game.WorldManager;
import java.util.Vector;
import javax.microedition.lcdui.Graphics;

/**
 * Pet - Monster entity managing base/current stats, leveling, evolution, exp, skills, and battle calculations.
 */
public final class Pet
extends WorldEntity {
    private static short[] M = new short[]{90, 95, 100, 110, 125};
    public static final byte[] t = new byte[]{12, 30, 5};
    SkillEffect currentSpellEffect;
    short[][] v;
    short[][] w;
    byte[][] x;
    private byte[] N;
    short[] y;
    byte[] z;
    private byte O;
    private short[] P;
    protected int A = 0;
    private int Q;
    private int R = 0;
    private int currentExp = 0;
    protected int B = 0;
    private int level = 0;
    protected short growthRate;
    private byte U = 0;
    private int petId = 0;
    private byte W;
    protected byte D;
    private int X = 0;
    private boolean Y;
    protected short E;
    protected byte F;
    protected Vector G = new Vector();
    protected Vector H = new Vector();
    public byte I = 0;
    protected boolean J;
    protected short[] K;
    protected SkillEffect passiveAuraEffect = null;
    private byte Z = 0;

    public Pet() {
        this.c = new short[23];
        this.d = new short[23];
        this.y = new short[5];
        this.z = new byte[5];
        this.P = new short[4];
        for (int i = 0; i < this.z.length; ++i) {
            this.z[i] = -1;
        }
        this.K = new short[16];
        this.v = new short[16][5];
        this.w = new short[11][5];
        this.x = new byte[][]{{-1, -1, -1}, {-1, -1, -1}};
        this.N = new byte[2];
    }

    public final void initPet(int petId, int level, short skillId, byte rarity, short quality, byte nature) {
        this.petId = petId;
        this.level = level;
        this.c[0] = quality == -1 ? (petId = (int)((short)EngineUtils.b(GameDatabase.gameDatabase[0][this.petId][3], (int)GameDatabase.gameDatabase[0][this.petId][3]))) : (petId = (int)quality);
        this.c[1] = petId = (int)((short)((GameDatabase.gameDatabase[0][this.petId][5] + GameDatabase.gameDatabase[0][this.petId][6] * level + GameDatabase.gameDatabase[0][this.petId][7]) * M[this.c[0] - 1] / 100));
        this.c[2] = petId = (int)((short)((GameDatabase.gameDatabase[0][this.petId][8] + GameDatabase.gameDatabase[0][this.petId][9] * level + GameDatabase.gameDatabase[0][this.petId][10]) * M[this.c[0] - 1] / 100));
        this.c[3] = petId = (int)((short)((GameDatabase.gameDatabase[0][this.petId][11] + GameDatabase.gameDatabase[0][this.petId][12] * level / 10 + GameDatabase.gameDatabase[0][this.petId][13]) * M[this.c[0] - 1] / 100));
        this.c[4] = petId = (int)((short)((GameDatabase.gameDatabase[0][this.petId][14] + GameDatabase.gameDatabase[0][this.petId][15] * level / 10 + GameDatabase.gameDatabase[0][this.petId][16]) * M[this.c[0] - 1] / 100));
        this.c[5] = skillId;
        this.c[6] = petId = (int)rarity;
        this.i(nature);
        this.g();
        this.growthRate = GameDatabase.gameDatabase[0][this.petId][17];
        Pet b2 = this;
        b2.u(b2.d[1]);
    }

    public final void a(int[] intArray) {
        this.a(intArray[0], intArray[1], (short)intArray[2], (byte)intArray[3], (short)intArray[4], (byte)intArray[5]);
        this.U();
        this.a((short)intArray[6], intArray[7], intArray[8]);
        int[] intArray2 = new int[intArray.length - 9];
        for (int i = 0; i < intArray2.length; ++i) {
            intArray2[i] = intArray[i + 9];
        }
        this.b(intArray2);
    }

    public final void a(short sVal, int n2, int n3) {
        this.U();
        this.d[1] = sVal;
        this.u(this.d[1]);
        this.currentExp = n2;
        this.E = (byte)n3;
    }

    public final void c() {
        super.c();
        if (this.a == null) {
            this.a = new SpriteRenderer();
        }
        this.spriteRenderer.a((int)this.growthRate, false);
        this.d((byte)0);
    }

    public final void d() {
        super.d();
        if (this.a != null) {
            this.spriteRenderer.a();
            this.a = null;
        }
    }

    public final void a(short sVal, byte val2) {
        byte by2 = this.facingDirection;
        this.currentSpellEffect = null;
        this.currentSpellEffect = new SkillEffect();
        this.currentSpellEffect.a(new short[]{sVal, val2, by2});
        this.currentSpellEffect.b(this.posX, this.posY);
        if (sVal == 20 && val2 == 3 || sVal == 22 && val2 == 4) {
            int[] intArray = this.spriteRenderer.b(0, by2);
            this.currentSpellEffect.b(this.posX, this.posY - intArray[3]);
        }
        this.currentSpellEffect.c(true);
    }

    private void z(int n2) {
        this.passiveAuraEffect = new SkillEffect();
        short[] shortArray = game.BattleScreen.m[n2];
        short[] shortArray2 = new short[shortArray.length + 5];
        System.arraycopy(shortArray, 1, shortArray2, 6, shortArray.length - 1);
        shortArray = new short[]{shortArray[0], (short)this.posX, (short)this.posY, GameDatabase.gameDatabase[0][this.petId][17], 0, this.facingDirection};
        System.arraycopy(shortArray, 0, shortArray2, 0, shortArray.length);
        this.passiveAuraEffect.a(shortArray2);
        this.passiveAuraEffect.c(true);
    }

    public final void d(byte val) {
        switch (val) {
            case 0: {
                this.spriteRenderer.a(val, (byte)-1, true);
                break;
            }
            case 1: {
                this.spriteRenderer.a(val, (byte)0, true);
                switch (this.petId) {
                    case 0: {
                        this.z(27);
                        break;
                    }
                    case 10: {
                        this.Z = 1;
                        this.z(28);
                        break;
                    }
                    case 91: {
                        this.z(26);
                        break;
                    }
                    case 92: {
                        this.z(25);
                        break;
                    }
                    case 97: 
                    case 98: {
                        this.z(23);
                        break;
                    }
                    case 62: {
                        this.z(24);
                        break;
                    }
                    case 75: {
                        this.z(20);
                        break;
                    }
                    case 87: {
                        this.z(21);
                    }
                }
                break;
            }
            case 2: {
                this.spriteRenderer.a(val, (byte)0, true);
                break;
            }
            case 3: {
                if (game.BattleScreen.getInstance().battleMode != 0) break;
                this.d();
                Pet b2 = this;
                short[] shortArray = new short[]{16, 0, 0, 4};
                b2.L = new SkillEffect();
                short[] shortArray2 = new short[shortArray.length + 5];
                System.arraycopy(shortArray, 1, shortArray2, 6, shortArray.length - 1);
                shortArray = new short[]{shortArray[0], (short)b2.i, (short)b2.j, GameDatabase.gameDatabase[0][b2.V][17], 0, b2.n};
                System.arraycopy(shortArray, 0, shortArray2, 0, shortArray.length);
                b2.L.a(shortArray2);
                b2.L.c(true);
                b2.L.a();
                break;
            }
            case 4: {
                this.spriteRenderer.a(val, (byte)-1, true);
            }
        }
        this.U = val;
    }

    public final void o() {
        this.a();
        if (this.currentSpellEffect != null) {
            this.currentSpellEffect.e();
        }
        if (this.passiveAuraEffect != null) {
            this.passiveAuraEffect.e();
        }
    }

    public final void a(Graphics g) {
        if (this.passiveAuraEffect != null && this.U == 1) {
            switch (this.petId) {
                case 0: {
                    if (!this.spriteRenderer.b(1)) break;
                    this.passiveAuraEffect.a();
                    break;
                }
                case 10: {
                    if (!this.spriteRenderer.b(1)) break;
                    this.passiveAuraEffect.a();
                    break;
                }
                case 91: {
                    if (!this.spriteRenderer.b(1)) break;
                    this.passiveAuraEffect.a();
                    break;
                }
                case 92: {
                    if (!this.spriteRenderer.b(1)) break;
                    this.passiveAuraEffect.a();
                    break;
                }
                case 97: 
                case 98: {
                    if (!this.spriteRenderer.b(1)) break;
                    this.passiveAuraEffect.a();
                    break;
                }
                case 62: {
                    if (!this.spriteRenderer.b(1)) break;
                    this.passiveAuraEffect.a();
                    break;
                }
                case 75: {
                    if (!this.spriteRenderer.b(1)) break;
                    this.passiveAuraEffect.a();
                    break;
                }
                case 87: {
                    if (!this.spriteRenderer.b(1)) break;
                    this.passiveAuraEffect.a();
                }
            }
        }
        if (this.passiveAuraEffect != null && this.Z == 0) {
            this.passiveAuraEffect.a(g);
        }
        Graphics graphics2 = g;
        Pet b2 = this;
        if (b2.f) {
            b2.a.a(graphics2, b2.i, b2.j, b2.n);
        }
        if (this.passiveAuraEffect != null && this.Z == 1) {
            this.passiveAuraEffect.a(g);
        }
        if (this.currentSpellEffect != null) {
            this.currentSpellEffect.a(g);
        }
    }

    public final byte p() {
        return this.U;
    }

    public final int getPetId() {
        return this.petId;
    }

    public final void f(int n2) {
        this.X = n2;
    }

    public final int r() {
        return this.X;
    }

    public final int getLevel() {
        return this.level;
    }

    public final boolean isMaxLevel() {
        return this.level == 50;
    }

    protected final void g(int n2) {
        if (this.level >= 50) {
            return;
        }
        this.currentExp += n2;
        if (this.currentExp < 0) {
            this.currentExp = 0;
        }
    }

    public final int getExpToNextLevel() {
        if (this.level >= 50) {
            return game.Pet.A(50);
        }
        return game.Pet.A(this.level + 1);
    }

    public final void levelUp() {
        ++this.level;
        this.g(-game.Pet.A(this.level));
        this.J();
        for (int i = 0; i < this.z.length; ++i) {
            if (this.z[i] == -1) continue;
            this.y[i] = GameDatabase.gameDatabase[1][this.z[i]][5];
        }
        this.petId();
    }

    public final void h(int n2) {
        this.level += n2;
        this.J();
        this.petId();
    }

    private static int A(int n2) {
        return n2 * 15 * n2 - 200;
    }

    private void U() {
        short s2;
        if (game.Player.getInstance().c((byte)1, (byte)0) == 2 && game.Player.getInstance().c((byte)1, (byte)1) == 1) {
            s2 = (short)(this.c[1] * GameDatabase.gameDatabase[2][1][6] / 100);
            this.c[1] = s2 = (short)(this.c[1] + s2);
        }
        if (game.Player.getInstance().c((byte)2, (byte)0) == 2 && game.Player.getInstance().c((byte)2, (byte)1) == 1) {
            s2 = (short)(this.c[3] * GameDatabase.gameDatabase[2][2][6] / 100);
            this.c[3] = s2 = (short)(this.c[3] + s2);
        }
    }

    private void V() {
        short s2;
        this.c[1] = s2 = (short)((GameDatabase.gameDatabase[0][this.petId][5] + GameDatabase.gameDatabase[0][this.petId][6] * this.level + GameDatabase.gameDatabase[0][this.petId][7]) * M[this.c[0] - 1] / 100);
        this.c[2] = s2 = (short)((GameDatabase.gameDatabase[0][this.petId][8] + GameDatabase.gameDatabase[0][this.petId][9] * this.level + GameDatabase.gameDatabase[0][this.petId][10]) * M[this.c[0] - 1] / 100);
        this.c[3] = s2 = (short)((GameDatabase.gameDatabase[0][this.petId][11] + GameDatabase.gameDatabase[0][this.petId][12] * this.level / 10 + GameDatabase.gameDatabase[0][this.petId][13]) * M[this.c[0] - 1] / 100);
        this.c[4] = s2 = (short)((GameDatabase.gameDatabase[0][this.petId][14] + GameDatabase.gameDatabase[0][this.petId][15] * this.level / 10 + GameDatabase.gameDatabase[0][this.petId][16]) * M[this.c[0] - 1] / 100);
        this.g();
        this.u(this.d[1]);
    }

    public static short b(int n2, int n3, int n4) {
        return (short)((GameDatabase.gameDatabase[0][n2][5] + GameDatabase.gameDatabase[0][n2][6] * n3 + GameDatabase.gameDatabase[0][n2][7]) * M[n4 - 1] / 100);
    }

    public final void w() {
        for (int i = 0; i < this.P.length; ++i) {
            this.P[i] = game.Pet.a(this.petId, this.level - 5, this.c[0], i + 1);
        }
    }

    public final void x() {
        for (int i = 0; i < this.P.length; ++i) {
            byte by = (byte)(i + 1);
            this.P[i] = this.c[by];
        }
    }

    public final short i(int n2) {
        return this.P[n2];
    }

    public final void y() {
        if (this.F < 20) {
            short s2;
            this.F = (byte)(this.F + 1);
            this.d[2] = s2 = (short)(this.c[2] + this.c[2] * this.F / 100);
            this.d[3] = s2 = (short)(this.c[3] + this.c[3] * this.F / 100);
            this.d[4] = s2 = (short)(this.c[4] + this.c[4] * this.F / 100);
        }
    }

    public final int getCurrentExp() {
        return this.currentExp;
    }

    public final void j(int n2) {
        this.R = n2;
    }

    public final int A() {
        return this.R;
    }

    public final int B() {
        int n2;
        if (((Pet)this.ownerEntity).X == 0 && game.Player.getInstance().c((byte)4, (byte)0) == 2) {
            this.ownerEntity.d[3] = n2 = (int)((short)(this.ownerEntity.c[3] * (100 + GameDatabase.gameDatabase[2][4][5]) / 100));
        }
        n2 = ((Pet)this.ownerEntity).f((byte)2) ? this.d[2] - this.ownerEntity.d[3] * (100 + GameDatabase.gameDatabase[3][2][5]) / 100 : this.d[2] - this.ownerEntity.d[3];
        if (this.f((byte)0)) {
            if (this.d[1] <= GameDatabase.gameDatabase[3][0][5] * this.c[1] / 100) {
                n2 = this.d[2] * (100 + GameDatabase.gameDatabase[3][0][6]) / 100 - this.ownerEntity.d[3];
            }
        } else if (this.f((byte)1)) {
            n2 = this.d[2] * (100 + GameDatabase.gameDatabase[3][1][5]) / 100 - this.ownerEntity.d[3];
        }
        return n2;
    }

    public final int e(byte val) {
        int n2 = this.c[val];
        switch (val) {
            case 2: {
                if (this.f((byte)0)) {
                    if (this.d[1] > GameDatabase.gameDatabase[3][0][5] * this.c[1] / 100) break;
                    n2 = this.d[2] * (100 + GameDatabase.gameDatabase[3][0][6]) / 100;
                    break;
                }
                if (!this.f((byte)1)) break;
                n2 = this.c[2] * (100 + GameDatabase.gameDatabase[3][1][5]) / 100;
                break;
            }
            case 3: {
                if (!this.f((byte)2)) break;
                n2 = this.d[3] * (100 + GameDatabase.gameDatabase[3][2][5]) / 100;
            }
        }
        return n2;
    }

    public final void k(int n2) {
        int n3 = n2;
        if (n2 <= 0) {
            n3 = 1;
        }
        this.u(this.d[1]);
        this.d[1] = n3 = (int)((short)(this.d[1] - n3));
        if (this.d[1] <= 0) {
            this.d[1] = 0;
        }
    }

    public final void l(int n2) {
        this.d[1] = n2 = (int)((short)(this.d[1] + n2));
        if (this.d[1] >= this.c[1]) {
            this.d[1] = n2 = this.c[1];
        }
    }

    private void B(int n2) {
        for (int i = 0; i < this.z.length; ++i) {
            if (this.z[i] == -1) continue;
            int n3 = i;
            this.y[n3] = (short)(this.y[n3] + n2);
            if (this.y[i] < GameDatabase.gameDatabase[1][this.z[i]][5]) continue;
            this.y[i] = GameDatabase.gameDatabase[1][this.z[i]][5];
        }
    }

    public final boolean m(int n2) {
        return this.v[n2][4] == 1;
    }

    public final int a(byte val, int n2, int n3) {
        int n4 = 0;
        if (val == -1) {
            return 0;
        }
        switch (val) {
            case 0: {
                this.v[val][1] = (short)(this.c[3] * GameDatabase.gameDatabase[6][val][3] / 100);
                this.v[val][2] = (short)(GameDatabase.gameDatabase[6][val][4] * this.B() / 100);
                this.d[3] = n2 = (int)((short)(this.c[3] + this.v[val][1]));
                break;
            }
            case 1: {
                this.v[val][1] = (short)(this.c[3] * GameDatabase.gameDatabase[6][val][3] / 100);
                this.v[val][2] = GameDatabase.gameDatabase[6][val][4];
                this.d[3] = n2 = (int)((short)(this.c[3] - this.v[val][1]));
                break;
            }
            case 2: {
                this.v[val][1] = (short)(this.c[3] * GameDatabase.gameDatabase[6][val][3] / 100);
                this.v[val][2] = GameDatabase.gameDatabase[6][val][4];
                this.d[3] = n2 = (int)((short)(this.c[3] + this.v[val][1]));
                break;
            }
            case 3: {
                this.v[val][1] = (short)(this.c[1] * GameDatabase.gameDatabase[6][val][3] / 100);
                n4 = this.v[val][1];
                this.u(this.d[1]);
                this.l(this.v[val][1]);
                break;
            }
            case 4: {
                this.K[4] = (short)n3;
                this.v[val][1] = (short)(this.c[3] * GameDatabase.gameDatabase[1][n3][8] / 100);
                this.d[3] = n2 = (int)((short)(this.c[3] + this.v[val][1]));
                break;
            }
            case 5: {
                this.v[val][1] = GameDatabase.gameDatabase[6][val][3];
                break;
            }
            case 6: {
                this.v[val][1] = GameDatabase.gameDatabase[6][val][3];
                this.v[val][2] = GameDatabase.gameDatabase[6][val][4];
                break;
            }
            case 7: {
                this.K[7] = (short)n3;
                this.v[val][1] = (short)(this.c[4] * GameDatabase.gameDatabase[1][n3][8] / 100);
                this.d[4] = n2 = (int)((short)(this.c[4] + this.v[val][1]));
                break;
            }
            case 8: {
                this.v[val][1] = GameDatabase.gameDatabase[6][val][3];
                break;
            }
            case 9: {
                this.v[val][1] = (short)(this.c[4] * GameDatabase.gameDatabase[6][val][3] / 100);
                this.v[val][2] = (short)(this.c[3] * GameDatabase.gameDatabase[6][val][4] / 100);
                this.d[4] = n2 = (int)((short)(this.c[4] + this.v[val][1]));
                this.d[3] = n2 = (int)((short)(this.c[3] - this.v[val][2]));
                break;
            }
            case 10: {
                this.v[val][1] = (short)(this.c[2] * GameDatabase.gameDatabase[6][val][3] / 100);
                this.d[2] = n2 = (int)((short)(this.c[2] + this.v[val][1]));
                break;
            }
            case 11: {
                this.v[val][1] = (short)n2;
                Pet b2 = game.BattleScreen.getInstance().enemyPets[n2];
                for (int i = 0; i < b2.N[0]; ++i) {
                    this.a(b2.x[0][i], (int)b2.v[b2.x[0][i]][1], (int)game.BattleScreen.getInstance().enemyPets[n2].K[i]);
                }
                b2.D();
                break;
            }
            case 12: {
                this.K[12] = 1;
                break;
            }
            case 13: {
                this.v[val][1] = (short)(this.c[1] * GameDatabase.gameDatabase[6][val][3] / 100);
                n4 = this.v[val][1];
                this.u(this.d[1]);
                this.l(this.v[val][1]);
                this.growthRate();
                break;
            }
            case 14: {
                this.growthRate();
                break;
            }
            case 15: {
                this.v[val][1] = (short)(n2 * GameDatabase.gameDatabase[6][val][3]);
            }
        }
        this.a(0, val);
        this.v[val][0] = GameDatabase.gameDatabase[6][val][2];
        this.v[val][4] = 1;
        return n4;
    }

    public final void n(int n2) {
        this.v[n2][4] = 0;
        for (n2 = 2; n2 <= 4; n2 = (int)((byte)(n2 + 1))) {
            int n3 = n2;
            short s2 = this.c[n3];
            n3 = n2;
            this.d[n3] = s2;
        }
    }

    public final int o(int n2) {
        int n3 = 0;
        switch (n2) {
            case 0: {
                break;
            }
            case 1: {
                short s2;
                this.d[3] = s2 = (short)(this.c[3] - this.v[n2][1]);
                break;
            }
            case 2: {
                short s3;
                this.d[3] = s3 = (short)(this.c[3] + this.v[n2][1]);
                break;
            }
            case 3: {
                n3 = this.v[n2][1];
                this.u(this.d[1]);
                this.l(this.v[n2][1]);
                break;
            }
            case 4: {
                short s4;
                this.d[3] = s4 = (short)(this.d[3] + this.v[n2][1]);
                break;
            }
            case 5: {
                break;
            }
            case 6: {
                break;
            }
            case 7: {
                short s5;
                this.d[4] = s5 = (short)(this.c[4] + this.v[n2][1]);
                break;
            }
            case 8: {
                break;
            }
            case 9: {
                short s6;
                this.d[4] = s6 = (short)(this.c[4] + this.v[n2][1]);
                this.d[3] = s6 = (short)(this.c[3] - this.v[n2][2]);
                break;
            }
            case 10: {
                short s7;
                this.d[2] = s7 = (short)(this.c[2] + this.v[n2][1]);
                break;
            }
            case 11: {
                Pet b2 = game.BattleScreen.getInstance().enemyPets[this.v[11][1]];
                for (int i = 0; i < b2.N[0]; ++i) {
                    this.a(b2.x[0][i], (int)b2.v[b2.x[0][i]][1], (int)game.BattleScreen.getInstance().enemyPets[this.v[11][1]].K[i]);
                }
                b2.D();
                break;
            }
            case 12: {
                this.K[12] = 2;
                break;
            }
            case 13: {
                n3 = this.v[n2][1];
                this.u(this.d[1]);
                this.l(this.v[n2][1]);
            }
        }
        return n3;
    }

    public final boolean p(int n2) {
        return this.w[n2][4] == 1;
    }

    public final void C() {
        int n2;
        for (n2 = 0; n2 < 11; ++n2) {
            if (this.w[n2][4] != 1) continue;
            this.growthRate(n2);
        }
        for (n2 = 0; n2 < 3; ++n2) {
            this.e(1, n2);
        }
    }

    public final void D() {
        int n2;
        for (n2 = 0; n2 < 16; ++n2) {
            if (this.v[n2][4] != 1) continue;
            this.n(n2);
        }
        for (n2 = 0; n2 < 3; ++n2) {
            this.e(0, n2);
        }
    }

    private void C(int n2) {
        this.w[n2][4] = 0;
        for (n2 = 2; n2 <= 4; n2 = (int)((byte)(n2 + 1))) {
            int n3 = n2;
            short s2 = this.c[n3];
            n3 = n2;
            this.d[n3] = s2;
        }
    }

    private void e(int n2, int n3) {
        this.x[n2][n3] = -1;
        if (this.N[n2] > 0) {
            int n4 = n2;
            this.N[n4] = (byte)(this.N[n4] - 1);
        }
    }

    public final void q(int n2) {
        switch (n2) {
            case 0: {
                short s2 = this.w[0][1];
                n2 = GameDatabase.gameDatabase[1][this.w[0][3]][8];
                this.k(s2 / n2);
                if (!this.currentExp()) {
                    this.d((byte)3);
                }
                return;
            }
            case 1: {
                return;
            }
            case 2: {
                return;
            }
            case 3: {
                if (this.w[n2][0] > 1) break;
                short s3 = this.w[n2][1];
                n2 = GameDatabase.gameDatabase[1][this.w[n2][3]][8];
                this.k(s3 * n2 / 100);
                if (!this.currentExp()) {
                    this.d((byte)3);
                }
                return;
            }
            case 4: {
                return;
            }
            case 5: {
                this.d[4] = n2 = (int)((short)(this.c[4] - this.w[n2][1]));
                return;
            }
            case 6: {
                return;
            }
            case 7: {
                this.d[3] = n2 = (int)((short)(this.c[3] - this.w[n2][1]));
            }
        }
    }

    public final void c(int n2, int n3) {
        if (this.p(n2)) {
            if (this.w[n2][0] > 0) {
                short[] shortArray = this.w[n2];
                shortArray[0] = (short)(shortArray[0] - 1);
            }
            if (this.w[n2][0] <= 0) {
                this.growthRate(n2);
                this.e(1, n3);
            }
        }
    }

    public final void d(int n2, int n3) {
        if (this.m(n2)) {
            if (this.v[n2][0] > 0) {
                short[] shortArray = this.v[n2];
                shortArray[0] = (short)(shortArray[0] - 1);
            }
            if (this.v[n2][0] <= 0) {
                this.n(n2);
                this.e(0, n3);
            }
        }
    }

    private void a(int n2, byte val2) {
        int n3;
        for (n3 = 0; n3 < 3; ++n3) {
            int n4;
            if (this.x[n2][n3] != -1) continue;
            for (n4 = 0; n4 < 3; ++n4) {
                if (this.x[n2][n4] != val2) continue;
                return;
            }
            if (n4 < 3) continue;
            this.x[n2][n3] = val2;
            if (this.N[n2] >= 3) break;
            int n5 = n2;
            this.N[n5] = (byte)(this.N[n5] + 1);
            break;
        }
        if (n3 >= 3) {
            this.x[n2][0] = val2;
        }
    }

    public final byte r(int n2) {
        return this.N[n2];
    }

    public final boolean f(byte val) {
        return this.c[5] == val;
    }

    public final int E() {
        return this.O;
    }

    public final int[] F() {
        int n2;
        int[] intArray = null;
        Vector<String> vector = new Vector<String>();
        short s2 = GameDatabase.gameDatabase[0][this.petId][18];
        short s3 = GameDatabase.gameDatabase[0][this.petId][1];
        int n3 = this.W();
        for (n2 = s3 * 10; n2 < s3 * 10 + 10; ++n2) {
            int n4 = 0;
            if (GameDatabase.gameDatabase[1][n2][4] > GameDatabase.gameDatabase[8][s2][n3]) continue;
            for (n4 = 0; n4 < this.z.length && n2 != this.z[n4]; ++n4) {
            }
            if (n4 < this.z.length) continue;
            vector.addElement(String.valueOf(n2));
        }
        if (vector.size() > 0) {
            intArray = new int[vector.size()];
            for (n2 = 0; n2 < intArray.length; ++n2) {
                intArray[n2] = Integer.parseInt((String)vector.elementAt(n2));
            }
        }
        return intArray;
    }

    public final void g(byte val) {
        for (int i = 0; i < this.z.length; ++i) {
            if (this.z[i] != -1) continue;
            this.z[i] = val;
            this.O = (byte)(this.O + 1);
            this.y[i] = GameDatabase.gameDatabase[1][val][5];
            return;
        }
    }

    public final void G() {
        int n2 = GameDatabase.gameDatabase[0][this.petId][1];
        if (this.level <= 5) {
            n2 *= 10;
            boolean bl = true;
            for (int i = 0; i < this.z.length; ++i) {
                if (n2 != this.z[i]) continue;
                bl = false;
                break;
            }
            if (bl) {
                this.g((byte)n2);
            }
            return;
        }
        if (this.O >= this.W() + 1) {
            return;
        }
        int[] intArray = this.F();
        int n3 = intArray.length;
        for (int i3 = 0; i3 < intArray.length; ++i3) {
            this.g((byte)intArray[n2]);
            if (this.O >= this.level / 10 + 1) break;
            for (n2 = EngineUtils.randomInt(n3); n2 < n3 - 1; ++n2) {
                intArray[n2] = intArray[n2 + 1];
            }
            --n3;
        }
    }

    public final boolean s(int n2) {
        if (n2 == -1) {
            return false;
        }
        return this.y[n2] > 0;
    }

    public final void a(byte val, Pet b2) {
        this.ownerEntity = b2;
        this.D = val;
        for (int i = 0; i < this.z.length; ++i) {
            if (this.z[i] != val) continue;
            int n2 = i;
            this.y[n2] = (short)(this.y[n2] - 1);
            if (this.m(12) && this.K[12] == 1) {
                int n3 = i;
                this.y[n3] = (short)(this.y[n3] + 1);
            }
            if (!this.m(8)) continue;
            int n4 = i;
            this.y[n4] = (short)(this.y[n4] - 1);
        }
    }

    public final void b(int[] intArray) {
        this.O = (byte)intArray[0];
        for (int i = 0; i < intArray[0]; ++i) {
            this.z[i] = (byte)intArray[i + 1];
            this.y[i] = (short)intArray[intArray[0] + 1 + i];
        }
    }

    public final byte t(int n2) {
        if (n2 > this.z.length - 1 || n2 < 0) {
            return -1;
        }
        return this.z[n2];
    }

    public static short b(byte val, byte val2) {
        return GameDatabase.gameDatabase[1][val][val2];
    }

    private int W() {
        int[] intArray = new int[]{5, 10, 20, 30, 40};
        int n2 = 0;
        for (int i = 0; i < intArray.length; ++i) {
            if (this.level < intArray[i]) continue;
            n2 = i;
        }
        return n2;
    }

    public final void h(byte val) {
        this.D = val;
    }

    public final byte H() {
        return this.D;
    }

    public final void g() {
        this.U();
        super.g();
        this.u(this.c[1]);
    }

    public final void I() {
        for (int i = 0; i < this.z.length; ++i) {
            if (this.z[i] == -1) continue;
            this.y[i] = GameDatabase.gameDatabase[1][this.z[i]][5];
        }
        this.g();
        this.c();
    }

    public final void J() {
        short s2;
        if (game.WorldManager.levelUpPets == null) {
            game.WorldManager.levelUpPets = new Vector();
        }
        if ((s2 = GameDatabase.spriteTable((byte)0, (short)this.petId, (byte)19)) == -1) {
            return;
        }
        short s3 = GameDatabase.spriteTable((byte)0, (short)this.petId, (byte)21);
        int n2 = GameDatabase.spriteTable((byte)0, (short)this.petId, (byte)20) + 12;
        boolean bl = false;
        if (!game.WorldManager.K && this.R() > 0 && this.level >= t[GameDatabase.spriteTable((byte)0, s2, (byte)2) - 1] && game.Player.getInstance().a(n2, (byte)2) >= s3) {
            bl = true;
        } else if (this.R() > 0 && this.level >= t[GameDatabase.spriteTable((byte)0, s2, (byte)2) - 1]) {
            bl = true;
        }
        if (bl) {
            int[] intArray = new int[]{this.petId, GameDatabase.gameDatabase[0][this.petId][0]};
            game.WorldManager.levelUpPets.addElement(intArray);
            game.WorldManager.L[0] = (byte)this.level;
            game.WorldManager.L[1] = (byte)this.petId;
            game.WorldManager.I = 0;
        }
    }

    public final void i(byte val) {
        this.W = val;
        switch (val) {
            case 7: {
                val = (byte)(this.c[2] * 90 / 100);
                this.c[2] = val;
                val = (byte)(this.c[4] + 7);
                this.c[4] = val;
                val = (byte)(this.c[1] * 80 / 100);
                this.c[1] = val;
                return;
            }
            case 8: {
                val = (byte)(this.c[2] * 130 / 100);
                this.c[2] = val;
                val = (byte)(this.c[4] + -2);
                this.c[4] = val;
                val = (byte)(this.c[1] * 80 / 100);
                this.c[1] = val;
                return;
            }
            case 9: {
                val = (byte)(this.c[2] * 90 / 100);
                this.c[2] = val;
                val = (byte)(this.c[4] + -2);
                this.c[4] = val;
                val = (byte)(this.c[1] * 130 / 100);
                this.c[1] = val;
            }
        }
    }

    public final boolean K() {
        return this.Y;
    }

    public final void d(boolean flag) {
        this.Y = flag;
    }

    public final int j(byte val) {
        return GameDatabase.gameDatabase[0][this.petId][val];
    }

    public static short a(int n2, int n3, int n4, int n5) {
        switch (n5) {
            case 1: {
                return (short)((GameDatabase.gameDatabase[0][n2][5] + GameDatabase.gameDatabase[0][n2][6] * n3 + GameDatabase.gameDatabase[0][n2][7]) * M[n4 - 1] / 100);
            }
            case 2: {
                return (short)((GameDatabase.gameDatabase[0][n2][8] + GameDatabase.gameDatabase[0][n2][9] * n3 + GameDatabase.gameDatabase[0][n2][10]) * M[n4 - 1] / 100);
            }
            case 3: {
                return (short)((GameDatabase.gameDatabase[0][n2][11] + GameDatabase.gameDatabase[0][n2][12] * n3 / 10 + GameDatabase.gameDatabase[0][n2][13]) * M[n4 - 1] / 100);
            }
            case 4: {
                return (short)((GameDatabase.gameDatabase[0][n2][14] + GameDatabase.gameDatabase[0][n2][15] * n3 / 10 + GameDatabase.gameDatabase[0][n2][16]) * M[n4 - 1] / 100);
            }
        }
        return 0;
    }

    public final int L() {
        return this.d[1] * 100 / this.c[1];
    }

    public final int M() {
        return this.Q * 100 / this.c[1];
    }

    public final int N() {
        return this.Q;
    }

    public final void u(int n2) {
        if (n2 >= this.c[1]) {
            this.Q = this.c[1];
            return;
        }
        this.Q = n2;
    }

    public final int O() {
        return this.currentExp * 100 / this.u();
    }

    public final int v(int n2) {
        return n2 * 100 / this.u();
    }

    public static int a(short sVal, short sVal2) {
        sVal2 = sVal2 >= 50 ? (short)37300 : (short)(sVal2 * 15 * sVal2 - 200);
        return sVal * 100 / sVal2;
    }

    public final int[] P() {
        int[] intArray = new int[9 + (this.O << 1) + 1];
        int[] intArray2 = intArray;
        intArray[0] = this.petId;
        intArray2[1] = this.level;
        intArray2[2] = this.c[5];
        intArray2[3] = this.d[6];
        intArray2[4] = this.c[0];
        intArray2[5] = this.W;
        intArray2[6] = this.d[1];
        intArray2[7] = this.currentExp;
        intArray2[8] = this.E;
        intArray2[9] = this.O;
        for (int i = 0; i < this.O; ++i) {
            intArray2[i + 10] = this.z[i];
            intArray2[10 + intArray2[9] + i] = this.y[i];
        }
        return intArray2;
    }

    public final int[] Q() {
        int[] intArray = new int[(this.O << 1) + 1];
        int[] intArray2 = intArray;
        intArray[0] = this.O;
        for (int i = 0; i < this.O; ++i) {
            intArray2[i + 1] = this.z[i];
            intArray2[intArray2[0] + i + 1] = this.y[i];
        }
        return intArray2;
    }

    public final int R() {
        if (this.j((byte)19) == -1) {
            return 0;
        }
        if (GameDatabase.gameDatabase[0][this.j((byte)19)][2] == 1) {
            return 1;
        }
        if (GameDatabase.gameDatabase[0][this.j((byte)19)][2] == 2) {
            return 1;
        }
        if (GameDatabase.gameDatabase[0][this.j((byte)19)][2] == 3) {
            return 2;
        }
        return 0;
    }

    public final void w(int n2) {
        switch (GameDatabase.gameDatabase[4][n2][5]) {
            case 1: {
                short s2 = (short)(this.c[1] * GameDatabase.gameDatabase[4][n2][6] / 100 + GameDatabase.gameDatabase[4][n2][7]);
                this.u(this.d[1] + s2);
                this.l(s2);
                break;
            }
            case 2: {
                short s3 = GameDatabase.gameDatabase[4][n2][6];
                this.B(s3);
                break;
            }
            case 3: {
                short s4 = (short)(this.c[1] * GameDatabase.gameDatabase[4][n2][6] / 100 + GameDatabase.gameDatabase[4][n2][7]);
                short s5 = GameDatabase.gameDatabase[4][n2][8];
                this.u(this.d[1] + s4);
                this.l(s4);
                this.B(s5);
                break;
            }
            case 4: {
                this.c();
                short s6 = (short)(this.c[1] * GameDatabase.gameDatabase[4][n2][6] / 100 + GameDatabase.gameDatabase[4][n2][7]);
                short s7 = GameDatabase.gameDatabase[4][n2][8];
                this.u(s6);
                this.l(s6);
                this.B(s7);
                break;
            }
            case 5: {
                this.growthRate();
                break;
            }
            case 6: {
                this.d[6] = 2;
            }
        }
        game.Player.getInstance().d(n2, 1, (byte)0);
    }

    public final int x(int n2) {
        if (!this.currentExp() && GameDatabase.gameDatabase[4][n2][5] != 4) {
            return 8;
        }
        switch (GameDatabase.gameDatabase[4][n2][5]) {
            case 0: {
                return 6;
            }
            case 1: {
                if (this.c[1] != this.d[1]) break;
                return 2;
            }
            case 2: {
                n2 = this.O;
                for (int i = 0; i < n2; ++i) {
                    if (this.y[i] >= game.Pet.b(this.z[i], (byte)5)) continue;
                    return -1;
                }
                return 3;
            }
            case 3: {
                n2 = -1;
                if (this.c[1] == this.d[1] || !this.currentExp()) {
                    n2 = 2;
                }
                int n3 = this.O;
                for (int i3 = 0; i3 < n3; ++i3) {
                    if (this.y[i3] >= game.Pet.b(this.z[i3], (byte)5)) continue;
                    return -1;
                }
                if (n2 != 2) break;
                return 7;
            }
            case 4: {
                if (!this.currentExp()) break;
                return 1;
            }
            case 5: {
                for (n2 = 0; n2 < this.w.length; ++n2) {
                    if (!this.p(n2)) continue;
                    return -1;
                }
                return 4;
            }
            case 6: {
                if (this.d[6] < 2) break;
                return 5;
            }
        }
        return -1;
    }

    public final boolean isAlive() {
        return this.d[1] > 0;
    }

    /*
     * Unable to fully structure code
     */
    public final byte a(Pet var1_1) {
        var2_3 = GameDatabase.gameDatabase[0][this.petId][1];
        var3_4 = GameDatabase.gameDatabase[0][var1_1.V][1];
        var4_5 = GameDatabase.gameDatabase[0][this.petId][22];
        var1_2 = GameDatabase.gameDatabase[0][var1_1.V][22];
        var5_6 = false;
        var6_7 = false;
        if (var4_5 == 2 && var1_2 == 2) ** GOTO lbl-1000
        if (var4_5 == 2 && var1_2 != 2) {
            var5_6 = true;
        } else if (var4_5 != 2 && var1_2 == 2) {
            var6_7 = true;
        } else lbl-1000:
        // 2 sources

        {
            var5_6 = true;
            var6_7 = true;
        }
        if (var5_6 && (var2_3 == 0 && var3_4 == 1 || var2_3 == 1 && var3_4 == 2 || var2_3 == 2 && var3_4 == 3 || var2_3 == 3 && var3_4 == 0 || var2_3 == 5 && var3_4 == 6 || var2_3 == 6 && var3_4 == 4 || var2_3 == 4 && var3_4 == 5)) {
            return 0;
        }
        if (var6_7 && (var3_4 == 0 && var2_3 == 1 || var3_4 == 1 && var2_3 == 2 || var3_4 == 2 && var2_3 == 3 || var3_4 == 3 && var2_3 == 0 || var3_4 == 5 && var2_3 == 6 || var3_4 == 6 && var2_3 == 4 || var3_4 == 4 && var2_3 == 5)) {
            return 1;
        }
        return -1;
    }

    /*
     * Unable to fully structure code
     */
    public final int[] b(Pet var1_1) {
        block48: {
            block49: {
                block47: {
                    var2_2 = 0;
                    var3_3 = 5;
                    var4_6 = this.B();
                    var5_7 = GameDatabase.gameDatabase[0][this.petId][1];
                    if (this.growthRate == game.Player.getInstance().W[var5_7] + game.Player.getInstance().X[var5_7] - 1) {
                        var3_3 = 30;
                    }
                    var3_3 += this.d[4] / 2;
                    if (this.f((byte)4)) {
                        var3_3 += GameDatabase.gameDatabase[3][4][5];
                    }
                    if (EngineUtils.randomInt(100) <= var3_3) {
                        var4_6 = var4_6 * 3 / 2;
                        var2_2 = 1;
                    }
                    var5_7 = (byte)GameDatabase.gameDatabase[1][this.D][7];
                    var3_3 = -1;
                    var6_8 = var4_6;
                    switch (this.D) {
                        case 0: 
                        case 6: 
                        case 10: 
                        case 11: 
                        case 12: 
                        case 13: 
                        case 16: 
                        case 17: 
                        case 18: 
                        case 19: 
                        case 20: 
                        case 26: 
                        case 30: 
                        case 31: 
                        case 32: 
                        case 33: 
                        case 36: 
                        case 37: 
                        case 38: 
                        case 39: 
                        case 40: 
                        case 46: 
                        case 50: 
                        case 51: 
                        case 52: 
                        case 54: 
                        case 55: 
                        case 56: 
                        case 57: 
                        case 58: 
                        case 60: 
                        case 61: 
                        case 63: 
                        case 66: 
                        case 68: 
                        case 69: {
                            var4_6 = var4_6 * GameDatabase.gameDatabase[1][this.D][3] / 100;
                            break;
                        }
                        case 1: 
                        case 7: {
                            var4_6 = var4_6 * GameDatabase.gameDatabase[1][this.D][3] / 100 + var4_6 / GameDatabase.gameDatabase[1][this.D][8];
                            break;
                        }
                        case 2: 
                        case 8: 
                        case 22: 
                        case 28: 
                        case 41: 
                        case 47: {
                            var4_6 = var4_6 * GameDatabase.gameDatabase[1][this.D][3] / 100;
                            var3_3 = GameDatabase.gameDatabase[1][this.D][8];
                            break;
                        }
                        case 3: 
                        case 9: {
                            if (var1_1.p(0)) {
                                var4_6 = var4_6 * GameDatabase.gameDatabase[1][this.D][8] / 100;
                                break;
                            }
                            var4_6 = var4_6 * GameDatabase.gameDatabase[1][this.D][3] / 100;
                            break;
                        }
                        case 23: 
                        case 29: {
                            if (var1_1.p(1)) {
                                var4_6 = var4_6 * GameDatabase.gameDatabase[1][this.D][8] / 100;
                                break;
                            }
                            var4_6 = var4_6 * GameDatabase.gameDatabase[1][this.D][3] / 100;
                            break;
                        }
                        case 43: 
                        case 49: {
                            var4_6 = var4_6 * GameDatabase.gameDatabase[1][this.D][3] / 100;
                            var1_1.D();
                            break;
                        }
                        case 53: 
                        case 59: {
                            var7_9 = this.d[1] * 100 / this.c[1];
                            var4_6 = var4_6 * (GameDatabase.gameDatabase[1][this.D][8] - var7_9) / 100;
                            break;
                        }
                        default: {
                            var5_7 = -1;
                        }
                    }
                    if (var6_8 <= 0) {
                        var6_8 = 1;
                    }
                    var8_10 = var3_3;
                    var7_9 = this.D;
                    var6_8 = (short)var6_8;
                    var3_4 = var1_1;
                    if (var5_7 != -1) break block47;
                    v0 = -1;
                    break block48;
                }
                if (!var3_4.f((byte)3)) break block49;
                if (EngineUtils.randomInt(100) <= var8_10 * (100 - GameDatabase.gameDatabase[3][3][5]) / 100) ** GOTO lbl-1000
                v0 = -1;
                break block48;
            }
            if (var3_4.m(14)) {
                v0 = -1;
            } else if (var8_10 != -1 && EngineUtils.randomInt(100) > var8_10) {
                v0 = -1;
            } else lbl-1000:
            // 2 sources

            {
                switch (var5_7) {
                    case 0: {
                        var3_4.w[var5_7][1] = var6_8;
                        break;
                    }
                    case 1: {
                        break;
                    }
                    case 2: {
                        break;
                    }
                    case 3: {
                        var3_4.w[var5_7][1] = var6_8;
                        break;
                    }
                    case 4: {
                        var3_4.w[var5_7][1] = GameDatabase.gameDatabase[1][var7_9][8];
                        break;
                    }
                    case 5: {
                        var3_4.w[var5_7][1] = (short)(var3_4.c[4] * GameDatabase.gameDatabase[1][var7_9][8] / 100);
                        var3_4.d[4] = var6_8 = (int)((short)(var3_4.c[4] - var3_4.w[var5_7][1]));
                        break;
                    }
                    case 6: {
                        var3_4.w[var5_7][1] = GameDatabase.gameDatabase[1][var7_9][8];
                        break;
                    }
                    case 7: {
                        var3_4.w[var5_7][1] = (short)(var3_4.c[3] * GameDatabase.gameDatabase[1][var7_9][8] / 100);
                        var3_4.d[3] = var6_8 = (int)((short)(var3_4.c[3] - var3_4.w[var5_7][1]));
                    }
                }
                var3_4.a(1, (byte)var5_7);
                var3_4.w[var5_7][0] = var3_4.X == 0 && game.Player.getInstance().c((byte)6, (byte)0) == 2 && game.Player.getInstance().c((byte)6, (byte)1) == 1 ? (short)(GameDatabase.gameDatabase[7][var5_7][2] / 2) : GameDatabase.gameDatabase[7][var5_7][2];
                var3_4.w[var5_7][3] = var7_9;
                var3_4.w[var5_7][4] = 1;
                v0 = var7_9 = var5_7;
            }
        }
        if (this.m(0) && this.v[0][0] == 0) {
            var4_6 += this.v[0][2];
        }
        if (this.m(1)) {
            var4_6 += var4_6 * this.v[1][2] / 100;
        }
        if (this.p(6)) {
            var4_6 -= var4_6 * this.w[6][1] / 100;
        }
        if (var1_1.m(6) && EngineUtils.randomInt(100) <= this.v[6][1]) {
            var4_6 = var4_6 * this.v[6][2] / 100;
        }
        if (this.m(8)) {
            var4_6 += var4_6 * this.v[8][1] / 100;
        }
        if (this.X == 0 && game.Player.getInstance().c((byte)3, (byte)0) == 2 && game.Player.getInstance().c((byte)3, (byte)1) == 1 && game.WorldManager.O == 2) {
            var4_6 += var4_6 * GameDatabase.gameDatabase[2][3][5] / 100;
        }
        if (this.X == 0 && game.Player.getInstance().c((byte)6, (byte)0) == 2) {
            var4_6 += var4_6 * GameDatabase.gameDatabase[2][6][5] / 100;
        }
        if (this.a(var1_1) == 0) {
            var4_6 *= 3;
        } else if (this.a(var1_1) == 1) {
            var4_6 = var4_6 * 60 / 100;
        }
        if (var4_6 <= 0) {
            var4_6 = 1;
        } else {
            var3_5 = EngineUtils.randomInt(100);
            var5_7 = (var4_6 << 1) / 100;
            if (var3_5 > 50) {
                if (var5_7 <= 0) {
                    ++var4_6;
                }
            } else if (var5_7 <= 0) {
                --var4_6;
            }
            if (var4_6 <= 0) {
                var4_6 = 1;
            }
        }
        if (var1_1.m(5) && EngineUtils.randomInt(100) <= var1_1.v[5][1]) {
            this.K[5] = (short)var4_6;
            return new int[]{var4_6, var2_2, var7_9};
        }
        return new int[]{var4_6, var2_2, var7_9};
    }

    public final String T() {
        String[] strArray = new String[]{"Mộc hệ", "Thổ hệ", "Thủy hệ", "Hỏa hệ", "Quỷ hệ", "Phong hệ", "Điện hệ"};
        short s2 = GameDatabase.gameDatabase[0][this.petId][1];
        return strArray[s2];
    }

    public static String y(int n2) {
        String[] strArray = new String[]{"Mộc hệ", "Thổ hệ", "Thủy hệ", "Hỏa hệ", "Quỷ hệ", "Phong hệ", "Điện hệ"};
        n2 = GameDatabase.gameDatabase[0][n2][1];
        return strArray[n2];
    }
}

