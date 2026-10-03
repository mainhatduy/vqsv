package game;

import java.util.Vector;
import javax.microedition.lcdui.Graphics;

public final class Pet extends WorldEntity {
   private static short[] qualityPercentages = new short[]{90, 95, 100, 110, 125};
   public static final byte[] evolutionLevelThresholds = new byte[]{12, 30, 5};
   SkillEffect activeSkillEffect;
   short[][] v;
   short[][] w;
   byte[][] x;
   private byte[] N;
   short[] skillUsesRemaining;
   byte[] learnedSkillIds;
   private byte learnedSkillCount;
   private short[] P;
   protected int A = 0;
   private int Q;
   private int R = 0;
   private int currentExp = 0;
   protected int B = 0;
   private int level = 0;
   protected short spriteId;
   private byte U = 0;
   private int speciesId = 0;
   private byte statVariant;
   protected byte selectedSkillId;
   private int X = 0;
   private boolean Y;
   protected short E;
   protected byte F;
   protected Vector G = new Vector();
   protected Vector H = new Vector();
   public byte I = 0;
   protected boolean J;
   protected short[] K;
   protected SkillEffect specialAttackEffect = null;
   private byte Z = 0;

   public Pet() {
      this.baseStats = new short[23];
      this.currentStats = new short[23];
      this.skillUsesRemaining = new short[5];
      this.learnedSkillIds = new byte[5];
      this.P = new short[4];

      for (int var1 = 0; var1 < this.learnedSkillIds.length; var1++) {
         this.learnedSkillIds[var1] = -1;
      }

      this.K = new short[16];
      this.v = new short[16][5];
      this.w = new short[11][5];
      this.x = new byte[][]{{-1, -1, -1}, {-1, -1, -1}};
      this.N = new byte[2];
   }

   public final void initPet(int var1, int var2, short var3, byte var4, short var5, byte var6) {
      this.speciesId = var1;
      this.level = var2;
      if (var5 == -1) {
         short var7 = (short)EngineUtils.randomRange(GameDatabase.gameDatabase[0][this.speciesId][3], GameDatabase.gameDatabase[0][this.speciesId][3]);
         super.baseStats[0] = var7;
      } else {
         short var8 = var5;
         super.baseStats[0] = var8;
      }

      short var9 = (short)(
         (
               GameDatabase.gameDatabase[0][this.speciesId][5]
                  + GameDatabase.gameDatabase[0][this.speciesId][6] * var2
                  + GameDatabase.gameDatabase[0][this.speciesId][7]
            )
            * qualityPercentages[super.baseStats[0] - 1]
            / 100
      );
      super.baseStats[1] = var9;
      var9 = (short)(
         (
               GameDatabase.gameDatabase[0][this.speciesId][8]
                  + GameDatabase.gameDatabase[0][this.speciesId][9] * var2
                  + GameDatabase.gameDatabase[0][this.speciesId][10]
            )
            * qualityPercentages[super.baseStats[0] - 1]
            / 100
      );
      super.baseStats[2] = var9;
      var9 = (short)(
         (
               GameDatabase.gameDatabase[0][this.speciesId][11]
                  + GameDatabase.gameDatabase[0][this.speciesId][12] * var2 / 10
                  + GameDatabase.gameDatabase[0][this.speciesId][13]
            )
            * qualityPercentages[super.baseStats[0] - 1]
            / 100
      );
      super.baseStats[3] = var9;
      var9 = (short)(
         (
               GameDatabase.gameDatabase[0][this.speciesId][14]
                  + GameDatabase.gameDatabase[0][this.speciesId][15] * var2 / 10
                  + GameDatabase.gameDatabase[0][this.speciesId][16]
            )
            * qualityPercentages[super.baseStats[0] - 1]
            / 100
      );
      super.baseStats[4] = var9;
      super.baseStats[5] = var3;
      var9 = var4;
      super.baseStats[6] = var9;
      this.i(var6);
      this.resetCurrentStats();
      this.spriteId = GameDatabase.gameDatabase[0][this.speciesId][17];
      this.u(super.currentStats[1]);
   }

   public final void restore(int[] var1) {
      this.initPet(var1[0], var1[1], (short)var1[2], (byte)var1[3], (short)var1[4], (byte)var1[5]);
      this.U();
      this.restoreProgress((short)var1[6], var1[7], var1[8]);
      int[] var2 = new int[var1.length - 9];

      for (int var3 = 0; var3 < var2.length; var3++) {
         var2[var3] = var1[var3 + 9];
      }

      this.b(var2);
   }

   public final void restoreProgress(short var1, int var2, int var3) {
      this.U();
      var1 = var1;
      super.currentStats[1] = var1;
      this.u(super.currentStats[1]);
      this.currentExp = var2;
      this.E = (byte)var3;
   }

   public final void activate() {
      super.activate();
      if (this.spriteRenderer == null) {
         this.spriteRenderer = new SpriteRenderer();
      }

      this.spriteRenderer.loadSprite(this.spriteId, false);
      this.setBattleAnimationState((byte)0);
   }

   public final void deactivate() {
      super.deactivate();
      if (this.spriteRenderer != null) {
         this.spriteRenderer.releaseSprite();
         this.spriteRenderer = null;
      }
   }

   public final void a(short var1, byte var2) {
      byte var3 = this.facingDirection;
      this.activeSkillEffect = null;
      this.activeSkillEffect = new SkillEffect();
      this.activeSkillEffect.a(new short[]{var1, var2, var3});
      this.activeSkillEffect.setPosition(this.posX, this.posY);
      if (var1 == 20 && var2 == 3 || var1 == 22 && var2 == 4) {
         int[] var4 = this.spriteRenderer.getFrameBounds(0, var3);
         this.activeSkillEffect.setPosition(this.posX, this.posY - var4[3]);
      }

      this.activeSkillEffect.c(true);
   }

   private void z(int var1) {
      this.specialAttackEffect = new SkillEffect();
      short[] var3;
      short[] var2 = new short[(var3 = BattleScreen.m[var1]).length + 5];
      System.arraycopy(var3, 1, var2, 6, var3.length - 1);
      short[] var4;
      System.arraycopy(
         var4 = new short[]{var3[0], (short)super.posX, (short)super.posY, GameDatabase.gameDatabase[0][this.speciesId][17], 0, this.facingDirection},
         0,
         var2,
         0,
         var4.length
      );
      this.specialAttackEffect.a(var2);
      this.specialAttackEffect.c(true);
   }

   public final void setBattleAnimationState(byte var1) {
      label24:
      switch (var1) {
         case 0:
            this.spriteRenderer.setAnimation(var1, (byte)-1, true);
            break;
         case 1:
            this.spriteRenderer.setAnimation(var1, (byte)0, true);
            switch (this.speciesId) {
               case 0:
                  this.z(27);
                  break label24;
               case 10:
                  this.Z = 1;
                  this.z(28);
                  break label24;
               case 62:
                  this.z(24);
                  break label24;
               case 75:
                  this.z(20);
                  break label24;
               case 87:
                  this.z(21);
                  break label24;
               case 91:
                  this.z(26);
                  break label24;
               case 92:
                  this.z(25);
                  break label24;
               case 97:
               case 98:
                  this.z(23);
               default:
                  break label24;
            }
         case 2:
            this.spriteRenderer.setAnimation(var1, (byte)0, true);
            break;
         case 3:
            if (BattleScreen.a().b == 0) {
               this.deactivate();
               Pet var2 = this;
               short[] var3 = new short[]{16, 0, 0, 4};
               var2.specialAttackEffect = new SkillEffect();
               short[] var4 = new short[var3.length + 5];
               System.arraycopy(var3, 1, var4, 6, var3.length - 1);
               short[] var5;
               System.arraycopy(
                  var5 = new short[]{var3[0], (short)var2.posX, (short)var2.posY, GameDatabase.gameDatabase[0][var2.speciesId][17], 0, var2.facingDirection},
                  0,
                  var4,
                  0,
                  var5.length
               );
               var2.specialAttackEffect.a(var4);
               var2.specialAttackEffect.c(true);
               var2.specialAttackEffect.a();
            }
            break;
         case 4:
            this.spriteRenderer.setAnimation(var1, (byte)-1, true);
      }

      this.U = var1;
   }

   public final void updateBattleEffects() {
      this.advanceAnimationIfVisible();
      if (this.activeSkillEffect != null) {
         this.activeSkillEffect.e();
      }

      if (this.specialAttackEffect != null) {
         this.specialAttackEffect.e();
      }
   }

   public final void render(Graphics var1) {
      if (this.specialAttackEffect != null && this.U == 1) {
         switch (this.speciesId) {
            case 0:
               if (this.spriteRenderer.isAnimationStep(1)) {
                  this.specialAttackEffect.a();
               }
               break;
            case 10:
               if (this.spriteRenderer.isAnimationStep(1)) {
                  this.specialAttackEffect.a();
               }
               break;
            case 62:
               if (this.spriteRenderer.isAnimationStep(1)) {
                  this.specialAttackEffect.a();
               }
               break;
            case 75:
               if (this.spriteRenderer.isAnimationStep(1)) {
                  this.specialAttackEffect.a();
               }
               break;
            case 87:
               if (this.spriteRenderer.isAnimationStep(1)) {
                  this.specialAttackEffect.a();
               }
               break;
            case 91:
               if (this.spriteRenderer.isAnimationStep(1)) {
                  this.specialAttackEffect.a();
               }
               break;
            case 92:
               if (this.spriteRenderer.isAnimationStep(1)) {
                  this.specialAttackEffect.a();
               }
               break;
            case 97:
            case 98:
               if (this.spriteRenderer.isAnimationStep(1)) {
                  this.specialAttackEffect.a();
               }
         }
      }

      if (this.specialAttackEffect != null && this.Z == 0) {
         this.specialAttackEffect.a(var1);
      }

      Graphics var3 = var1;
      Pet var2 = this;
      if (this.visible) {
         var2.spriteRenderer.drawCurrentFrame(var3, var2.posX, var2.posY, var2.facingDirection);
      }

      if (this.specialAttackEffect != null && this.Z == 1) {
         this.specialAttackEffect.a(var1);
      }

      if (this.activeSkillEffect != null) {
         this.activeSkillEffect.a(var1);
      }
   }

   public final byte p() {
      return this.U;
   }

   public final int getPetId() {
      return this.speciesId;
   }

   public final void f(int var1) {
      this.X = var1;
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

   protected final void g(int var1) {
      if (this.level < 50) {
         this.currentExp += var1;
         if (this.currentExp < 0) {
            this.currentExp = 0;
         }
      }
   }

   public final int getExpToNextLevel() {
      return this.level >= 50 ? A(50) : A(this.level + 1);
   }

   public final void levelUp() {
      this.level++;
      this.g(-A(this.level));
      this.checkEvolution();

      for (int var1 = 0; var1 < this.learnedSkillIds.length; var1++) {
         if (this.learnedSkillIds[var1] != -1) {
            this.skillUsesRemaining[var1] = GameDatabase.gameDatabase[1][this.learnedSkillIds[var1]][5];
         }
      }

      this.V();
   }

   public final void h(int var1) {
      this.level += var1;
      this.checkEvolution();
      this.V();
   }

   private static int A(int var0) {
      return var0 * 15 * var0 - 200;
   }

   private void U() {
      if (Player.getInstance().c((byte)1, (byte)0) == 2 && Player.getInstance().c((byte)1, (byte)1) == 1) {
         short var1 = (short)(super.baseStats[1] * GameDatabase.gameDatabase[2][1][6] / 100);
         var1 = (short)(super.baseStats[1] + var1);
         super.baseStats[1] = var1;
      }

      if (Player.getInstance().c((byte)2, (byte)0) == 2 && Player.getInstance().c((byte)2, (byte)1) == 1) {
         short var3 = (short)(super.baseStats[3] * GameDatabase.gameDatabase[2][2][6] / 100);
         var3 = (short)(super.baseStats[3] + var3);
         super.baseStats[3] = var3;
      }
   }

   private void V() {
      short var1 = (short)(
         (
               GameDatabase.gameDatabase[0][this.speciesId][5]
                  + GameDatabase.gameDatabase[0][this.speciesId][6] * this.level
                  + GameDatabase.gameDatabase[0][this.speciesId][7]
            )
            * qualityPercentages[super.baseStats[0] - 1]
            / 100
      );
      super.baseStats[1] = var1;
      var1 = (short)(
         (
               GameDatabase.gameDatabase[0][this.speciesId][8]
                  + GameDatabase.gameDatabase[0][this.speciesId][9] * this.level
                  + GameDatabase.gameDatabase[0][this.speciesId][10]
            )
            * qualityPercentages[super.baseStats[0] - 1]
            / 100
      );
      super.baseStats[2] = var1;
      var1 = (short)(
         (
               GameDatabase.gameDatabase[0][this.speciesId][11]
                  + GameDatabase.gameDatabase[0][this.speciesId][12] * this.level / 10
                  + GameDatabase.gameDatabase[0][this.speciesId][13]
            )
            * qualityPercentages[super.baseStats[0] - 1]
            / 100
      );
      super.baseStats[3] = var1;
      var1 = (short)(
         (
               GameDatabase.gameDatabase[0][this.speciesId][14]
                  + GameDatabase.gameDatabase[0][this.speciesId][15] * this.level / 10
                  + GameDatabase.gameDatabase[0][this.speciesId][16]
            )
            * qualityPercentages[super.baseStats[0] - 1]
            / 100
      );
      super.baseStats[4] = var1;
      this.resetCurrentStats();
      this.u(super.currentStats[1]);
   }

   public static short b(int var0, int var1, int var2) {
      return (short)(
         (GameDatabase.gameDatabase[0][var0][5] + GameDatabase.gameDatabase[0][var0][6] * var1 + GameDatabase.gameDatabase[0][var0][7])
            * qualityPercentages[var2 - 1]
            / 100
      );
   }

   public final void w() {
      for (int var1 = 0; var1 < this.P.length; var1++) {
         this.P[var1] = a(this.speciesId, this.level - 5, super.baseStats[0], var1 + 1);
      }
   }

   public final void x() {
      for (int var1 = 0; var1 < this.P.length; var1++) {
         byte var2 = (byte)(var1 + 1);
         this.P[var1] = super.baseStats[var2];
      }
   }

   public final short i(int var1) {
      return this.P[var1];
   }

   public final void y() {
      if (this.F < 20) {
         this.F++;
         short var1 = (short)(super.baseStats[2] + super.baseStats[2] * this.F / 100);
         super.currentStats[2] = var1;
         var1 = (short)(super.baseStats[3] + super.baseStats[3] * this.F / 100);
         super.currentStats[3] = var1;
         var1 = (short)(super.baseStats[4] + super.baseStats[4] * this.F / 100);
         super.currentStats[4] = var1;
      }
   }

   public final int z() {
      return this.currentExp;
   }

   public final void j(int var1) {
      this.R = var1;
   }

   public final int A() {
      return this.R;
   }

   public final int B() {
      if (((Pet)this.ownerEntity).X == 0 && Player.getInstance().c((byte)4, (byte)0) == 2) {
         short var1 = (short)(this.ownerEntity.baseStats[3] * (100 + GameDatabase.gameDatabase[2][4][5]) / 100);
         this.ownerEntity.currentStats[3] = var1;
      }

      int var2;
      if (((Pet)this.ownerEntity).f((byte)2)) {
         var2 = super.currentStats[2] - this.ownerEntity.currentStats[3] * (100 + GameDatabase.gameDatabase[3][2][5]) / 100;
      } else {
         var2 = super.currentStats[2] - this.ownerEntity.currentStats[3];
      }

      if (this.f((byte)0)) {
         if (super.currentStats[1] <= GameDatabase.gameDatabase[3][0][5] * super.baseStats[1] / 100) {
            var2 = super.currentStats[2] * (100 + GameDatabase.gameDatabase[3][0][6]) / 100 - this.ownerEntity.currentStats[3];
         }
      } else if (this.f((byte)1)) {
         var2 = super.currentStats[2] * (100 + GameDatabase.gameDatabase[3][1][5]) / 100 - this.ownerEntity.currentStats[3];
      }

      return var2;
   }

   public final int e(byte var1) {
      int var2 = super.baseStats[var1];
      switch (var1) {
         case 2:
            if (this.f((byte)0)) {
               if (super.currentStats[1] <= GameDatabase.gameDatabase[3][0][5] * super.baseStats[1] / 100) {
                  var2 = super.currentStats[2] * (100 + GameDatabase.gameDatabase[3][0][6]) / 100;
               }
            } else if (this.f((byte)1)) {
               var2 = super.baseStats[2] * (100 + GameDatabase.gameDatabase[3][1][5]) / 100;
            }
            break;
         case 3:
            if (this.f((byte)2)) {
               var2 = super.currentStats[3] * (100 + GameDatabase.gameDatabase[3][2][5]) / 100;
            }
         case 4:
      }

      return var2;
   }

   public final void k(int var1) {
      int var2 = var1;
      if (var1 <= 0) {
         var2 = 1;
      }

      this.u(super.currentStats[1]);
      short var3 = (short)(super.currentStats[1] - var2);
      super.currentStats[1] = var3;
      if (super.currentStats[1] <= 0) {
         super.currentStats[1] = 0;
      }
   }

   public final void l(int var1) {
      short var2 = (short)(super.currentStats[1] + var1);
      super.currentStats[1] = var2;
      if (super.currentStats[1] >= super.baseStats[1]) {
         var2 = super.baseStats[1];
         super.currentStats[1] = var2;
      }
   }

   private void B(int var1) {
      for (int var2 = 0; var2 < this.learnedSkillIds.length; var2++) {
         if (this.learnedSkillIds[var2] != -1) {
            this.skillUsesRemaining[var2] = (short)(this.skillUsesRemaining[var2] + var1);
            if (this.skillUsesRemaining[var2] >= GameDatabase.gameDatabase[1][this.learnedSkillIds[var2]][5]) {
               this.skillUsesRemaining[var2] = GameDatabase.gameDatabase[1][this.learnedSkillIds[var2]][5];
            }
         }
      }
   }

   public final boolean m(int var1) {
      return this.v[var1][4] == 1;
   }

   public final int a(byte var1, int var2, int var3) {
      short var4 = 0;
      if (var1 == -1) {
         return 0;
      }

      switch (var1) {
         case 0:
            this.v[var1][1] = (short)(super.baseStats[3] * GameDatabase.gameDatabase[6][var1][3] / 100);
            this.v[var1][2] = (short)(GameDatabase.gameDatabase[6][var1][4] * this.B() / 100);
            short var13 = (short)(super.baseStats[3] + this.v[var1][1]);
            super.currentStats[3] = var13;
            break;
         case 1:
            this.v[var1][1] = (short)(super.baseStats[3] * GameDatabase.gameDatabase[6][var1][3] / 100);
            this.v[var1][2] = GameDatabase.gameDatabase[6][var1][4];
            short var12 = (short)(super.baseStats[3] - this.v[var1][1]);
            super.currentStats[3] = var12;
            break;
         case 2:
            this.v[var1][1] = (short)(super.baseStats[3] * GameDatabase.gameDatabase[6][var1][3] / 100);
            this.v[var1][2] = GameDatabase.gameDatabase[6][var1][4];
            short var11 = (short)(super.baseStats[3] + this.v[var1][1]);
            super.currentStats[3] = var11;
            break;
         case 3:
            this.v[var1][1] = (short)(super.baseStats[1] * GameDatabase.gameDatabase[6][var1][3] / 100);
            var4 = this.v[var1][1];
            this.u(super.currentStats[1]);
            this.l(this.v[var1][1]);
            break;
         case 4:
            this.K[4] = (short)var3;
            this.v[var1][1] = (short)(super.baseStats[3] * GameDatabase.gameDatabase[1][var3][8] / 100);
            short var10 = (short)(super.baseStats[3] + this.v[var1][1]);
            super.currentStats[3] = var10;
            break;
         case 5:
            this.v[var1][1] = GameDatabase.gameDatabase[6][var1][3];
            break;
         case 6:
            this.v[var1][1] = GameDatabase.gameDatabase[6][var1][3];
            this.v[var1][2] = GameDatabase.gameDatabase[6][var1][4];
            break;
         case 7:
            this.K[7] = (short)var3;
            this.v[var1][1] = (short)(super.baseStats[4] * GameDatabase.gameDatabase[1][var3][8] / 100);
            short var9 = (short)(super.baseStats[4] + this.v[var1][1]);
            super.currentStats[4] = var9;
            break;
         case 8:
            this.v[var1][1] = GameDatabase.gameDatabase[6][var1][3];
            break;
         case 9:
            this.v[var1][1] = (short)(super.baseStats[4] * GameDatabase.gameDatabase[6][var1][3] / 100);
            this.v[var1][2] = (short)(super.baseStats[3] * GameDatabase.gameDatabase[6][var1][4] / 100);
            short var7 = (short)(super.baseStats[4] + this.v[var1][1]);
            super.currentStats[4] = var7;
            var7 = (short)(super.baseStats[3] - this.v[var1][2]);
            super.currentStats[3] = var7;
            break;
         case 10:
            this.v[var1][1] = (short)(super.baseStats[2] * GameDatabase.gameDatabase[6][var1][3] / 100);
            short var6 = (short)(super.baseStats[2] + this.v[var1][1]);
            super.currentStats[2] = var6;
            break;
         case 11:
            this.v[var1][1] = (short)var2;
            Pet var14 = BattleScreen.a().d[var2];

            for (int var5 = 0; var5 < var14.N[0]; var5++) {
               this.a(var14.x[0][var5], var14.v[var14.x[0][var5]][1], BattleScreen.a().d[var2].K[var5]);
            }

            var14.D();
            break;
         case 12:
            this.K[12] = 1;
            break;
         case 13:
            this.v[var1][1] = (short)(super.baseStats[1] * GameDatabase.gameDatabase[6][var1][3] / 100);
            var4 = this.v[var1][1];
            this.u(super.currentStats[1]);
            this.l(this.v[var1][1]);
            this.C();
            break;
         case 14:
            this.C();
            break;
         case 15:
            this.v[var1][1] = (short)(var2 * GameDatabase.gameDatabase[6][var1][3]);
      }

      this.a(0, var1);
      this.v[var1][0] = GameDatabase.gameDatabase[6][var1][2];
      this.v[var1][4] = 1;
      return var4;
   }

   public final void n(int var1) {
      this.v[var1][4] = 0;

      for (int var4 = 2; var4 <= 4; var4++) {
         int var2 = var4;
         short var3 = super.baseStats[var2];
         var2 = var4;
         super.currentStats[var2] = var3;
      }
   }

   public final int o(int var1) {
      short var2 = 0;
      switch (var1) {
         case 0:
         case 5:
         case 6:
         case 8:
         case 14:
         case 15:
         default:
            break;
         case 1:
            short var11 = (short)(super.baseStats[3] - this.v[var1][1]);
            super.currentStats[3] = var11;
            break;
         case 2:
            short var10 = (short)(super.baseStats[3] + this.v[var1][1]);
            super.currentStats[3] = var10;
            break;
         case 3:
            var2 = this.v[var1][1];
            this.u(super.currentStats[1]);
            this.l(this.v[var1][1]);
            break;
         case 4:
            short var9 = (short)(super.currentStats[3] + this.v[var1][1]);
            super.currentStats[3] = var9;
            break;
         case 7:
            short var8 = (short)(super.baseStats[4] + this.v[var1][1]);
            super.currentStats[4] = var8;
            break;
         case 9:
            short var6 = (short)(super.baseStats[4] + this.v[var1][1]);
            super.currentStats[4] = var6;
            var6 = (short)(super.baseStats[3] - this.v[var1][2]);
            super.currentStats[3] = var6;
            break;
         case 10:
            short var5 = (short)(super.baseStats[2] + this.v[var1][1]);
            super.currentStats[2] = var5;
            break;
         case 11:
            short[] var10001 = this.v[11];
            Pet var4 = BattleScreen.a().d[var10001[1]];

            for (int var3 = 0; var3 < var4.N[0]; var3++) {
               byte var12 = var4.x[0][var3];
               short var10002 = var4.v[var4.x[0][var3]][1];
               short[] var10004 = this.v[11];
               this.a(var12, var10002, BattleScreen.a().d[var10004[1]].K[var3]);
            }

            var4.D();
            break;
         case 12:
            this.K[12] = 2;
            break;
         case 13:
            var2 = this.v[var1][1];
            this.u(super.currentStats[1]);
            this.l(this.v[var1][1]);
      }

      return var2;
   }

   public final boolean p(int var1) {
      return this.w[var1][4] == 1;
   }

   public final void C() {
      for (int var1 = 0; var1 < 11; var1++) {
         if (this.w[var1][4] == 1) {
            this.C(var1);
         }
      }

      for (int var2 = 0; var2 < 3; var2++) {
         this.e(1, var2);
      }
   }

   public final void D() {
      for (int var1 = 0; var1 < 16; var1++) {
         if (this.v[var1][4] == 1) {
            this.n(var1);
         }
      }

      for (int var2 = 0; var2 < 3; var2++) {
         this.e(0, var2);
      }
   }

   private void C(int var1) {
      this.w[var1][4] = 0;

      for (int var4 = 2; var4 <= 4; var4++) {
         int var2 = var4;
         short var3 = super.baseStats[var2];
         var2 = var4;
         super.currentStats[var2] = var3;
      }
   }

   private void e(int var1, int var2) {
      this.x[var1][var2] = -1;
      if (this.N[var1] > 0) {
         this.N[var1]--;
      }
   }

   public final void q(int var1) {
      switch (var1) {
         case 0:
            short var7 = this.w[0][1];
            short var6 = GameDatabase.gameDatabase[1][this.w[0][3]][8];
            this.k(var7 / var6);
            if (!this.isAlive()) {
               this.setBattleAnimationState((byte)3);
            }

            return;
         case 1:
            return;
         case 2:
            return;
         case 3:
            if (this.w[var1][0] <= 1) {
               short var2 = this.w[var1][1];
               short var5 = GameDatabase.gameDatabase[1][this.w[var1][3]][8];
               this.k(var2 * var5 / 100);
               if (!this.isAlive()) {
                  this.setBattleAnimationState((byte)3);
               }

               return;
            }
            break;
         case 4:
            return;
         case 5:
            short var4 = (short)(super.baseStats[4] - this.w[var1][1]);
            super.currentStats[4] = var4;
            return;
         case 6:
            return;
         case 7:
            short var3 = (short)(super.baseStats[3] - this.w[var1][1]);
            super.currentStats[3] = var3;
         case 8:
         case 9:
         case 10:
      }
   }

   public final void c(int var1, int var2) {
      if (this.p(var1)) {
         if (this.w[var1][0] > 0) {
            this.w[var1][0]--;
         }

         if (this.w[var1][0] <= 0) {
            this.C(var1);
            this.e(1, var2);
         }
      }
   }

   public final void d(int var1, int var2) {
      if (this.m(var1)) {
         if (this.v[var1][0] > 0) {
            this.v[var1][0]--;
         }

         if (this.v[var1][0] <= 0) {
            this.n(var1);
            this.e(0, var2);
         }
      }
   }

   private void a(int var1, byte var2) {
      int var3;
      for (var3 = 0; var3 < 3; var3++) {
         if (this.x[var1][var3] == -1) {
            int var4;
            for (var4 = 0; var4 < 3; var4++) {
               if (this.x[var1][var4] == var2) {
                  return;
               }
            }

            if (var4 >= 3) {
               this.x[var1][var3] = var2;
               if (this.N[var1] < 3) {
                  this.N[var1]++;
               }
               break;
            }
         }
      }

      if (var3 >= 3) {
         this.x[var1][0] = var2;
      }
   }

   public final byte r(int var1) {
      return this.N[var1];
   }

   public final boolean f(byte var1) {
      return super.baseStats[5] == var1;
   }

   public final int E() {
      return this.learnedSkillCount;
   }

   public final int[] findLearnableSkills() {
      int[] var1 = null;
      Vector var2 = new Vector();
      short var4 = GameDatabase.gameDatabase[0][this.speciesId][18];
      short var5 = GameDatabase.gameDatabase[0][this.speciesId][1];
      int var6 = this.W();

      for (int var7 = var5 * 10; var7 < var5 * 10 + 10; var7++) {
         short var10000 = GameDatabase.gameDatabase[1][var7][4];
         boolean var3 = false;
         if (var10000 <= GameDatabase.gameDatabase[8][var4][var6]) {
            int var8 = 0;

            while (var8 < this.learnedSkillIds.length && var7 != this.learnedSkillIds[var8]) {
               var8++;
            }

            if (var8 >= this.learnedSkillIds.length) {
               var2.addElement(String.valueOf(var7));
            }
         }
      }

      if (var2.size() > 0) {
         var1 = new int[var2.size()];

         for (int var9 = 0; var9 < var1.length; var9++) {
            var1[var9] = Integer.parseInt((String)var2.elementAt(var9));
         }
      }

      return var1;
   }

   public final void learnSkill(byte var1) {
      for (int var2 = 0; var2 < this.learnedSkillIds.length; var2++) {
         if (this.learnedSkillIds[var2] == -1) {
            this.learnedSkillIds[var2] = var1;
            this.learnedSkillCount++;
            this.skillUsesRemaining[var2] = GameDatabase.gameDatabase[1][var1][5];
            return;
         }
      }
   }

   public final void updateLearnedSkills() {
      short var1 = GameDatabase.gameDatabase[0][this.speciesId][1];
      if (this.level <= 5) {
         int var6 = var1 * 10;
         boolean var7 = true;

         for (int var8 = 0; var8 < this.learnedSkillIds.length; var8++) {
            if (var6 == this.learnedSkillIds[var8]) {
               var7 = false;
               break;
            }
         }

         if (var7) {
            this.learnSkill((byte)var6);
         }
      } else if (this.learnedSkillCount < this.W() + 1) {
         int[] var2;
         int var3 = (var2 = this.findLearnableSkills()).length;

         for (int var4 = 0; var4 < var2.length; var4++) {
            int var5 = EngineUtils.randomInt(var3);
            this.learnSkill((byte)var2[var5]);
            if (this.learnedSkillCount >= this.level / 10 + 1) {
               break;
            }

            while (var5 < var3 - 1) {
               var2[var5] = var2[var5 + 1];
               var5++;
            }

            var3--;
         }
      }
   }

   public final boolean s(int var1) {
      return var1 == -1 ? false : this.skillUsesRemaining[var1] > 0;
   }

   public final void a(byte var1, Pet var2) {
      super.ownerEntity = var2;
      this.selectedSkillId = var1;

      for (int var3 = 0; var3 < this.learnedSkillIds.length; var3++) {
         if (this.learnedSkillIds[var3] == var1) {
            this.skillUsesRemaining[var3]--;
            if (this.m(12) && this.K[12] == 1) {
               this.skillUsesRemaining[var3]++;
            }

            if (this.m(8)) {
               this.skillUsesRemaining[var3]--;
            }
         }
      }
   }

   public final void b(int[] var1) {
      this.learnedSkillCount = (byte)var1[0];

      for (int var2 = 0; var2 < var1[0]; var2++) {
         this.learnedSkillIds[var2] = (byte)var1[var2 + 1];
         this.skillUsesRemaining[var2] = (short)var1[var1[0] + 1 + var2];
      }
   }

   public final byte t(int var1) {
      return var1 <= this.learnedSkillIds.length - 1 && var1 >= 0 ? this.learnedSkillIds[var1] : -1;
   }

   public static short b(byte var0, byte var1) {
      return GameDatabase.gameDatabase[1][var0][var1];
   }

   private int W() {
      int[] var1 = new int[]{5, 10, 20, 30, 40};
      int var2 = 0;

      for (int var3 = 0; var3 < var1.length; var3++) {
         if (this.level >= var1[var3]) {
            var2 = var3;
         }
      }

      return var2;
   }

   public final void h(byte var1) {
      this.selectedSkillId = var1;
   }

   public final byte H() {
      return this.selectedSkillId;
   }

   public final void resetCurrentStats() {
      this.U();
      super.resetCurrentStats();
      this.u(super.baseStats[1]);
   }

   public final void I() {
      for (int var1 = 0; var1 < this.learnedSkillIds.length; var1++) {
         if (this.learnedSkillIds[var1] != -1) {
            this.skillUsesRemaining[var1] = GameDatabase.gameDatabase[1][this.learnedSkillIds[var1]][5];
         }
      }

      this.resetCurrentStats();
      this.activate();
   }

   public final void checkEvolution() {
      if (WorldManager.H == null) {
         WorldManager.H = new Vector();
      }

      short var1;
      if ((var1 = GameDatabase.getValue((byte)0, (short)this.speciesId, (byte)19)) != -1) {
         short var2 = GameDatabase.getValue((byte)0, (short)this.speciesId, (byte)21);
         int var3 = GameDatabase.getValue((byte)0, (short)this.speciesId, (byte)20) + 12;
         boolean var4 = false;
         if (!WorldManager.K
            && this.getEvolutionKind() > 0
            && this.level >= evolutionLevelThresholds[GameDatabase.getValue((byte)0, var1, (byte)2) - 1]
            && Player.getInstance().countItem(var3, (byte)2) >= var2) {
            var4 = true;
         } else if (this.getEvolutionKind() > 0 && this.level >= evolutionLevelThresholds[GameDatabase.getValue((byte)0, var1, (byte)2) - 1]) {
            var4 = true;
         }

         if (var4) {
            int[] var5 = new int[]{this.speciesId, GameDatabase.gameDatabase[0][this.speciesId][0]};
            WorldManager.H.addElement(var5);
            WorldManager.L[0] = (byte)this.level;
            WorldManager.L[1] = (byte)this.speciesId;
            WorldManager.I = 0;
         }
      }
   }

   public final void i(byte variant) {
      this.statVariant = variant;
      switch (variant) {
         case 7:
            this.baseStats[2] = (short)(this.baseStats[2] * 90 / 100);
            this.baseStats[4] = (short)(this.baseStats[4] + 7);
            this.baseStats[1] = (short)(this.baseStats[1] * 80 / 100);
            return;
         case 8:
            this.baseStats[2] = (short)(this.baseStats[2] * 130 / 100);
            this.baseStats[4] = (short)(this.baseStats[4] - 2);
            this.baseStats[1] = (short)(this.baseStats[1] * 80 / 100);
            return;
         case 9:
            this.baseStats[2] = (short)(this.baseStats[2] * 90 / 100);
            this.baseStats[4] = (short)(this.baseStats[4] - 2);
            this.baseStats[1] = (short)(this.baseStats[1] * 130 / 100);
      }
   }

   public final boolean K() {
      return this.Y;
   }

   public final void d(boolean var1) {
      this.Y = var1;
   }

   public final int getSpeciesValue(byte var1) {
      return GameDatabase.gameDatabase[0][this.speciesId][var1];
   }

   public static short a(int var0, int var1, int var2, int var3) {
      switch (var3) {
         case 1:
            return (short)(
               (GameDatabase.gameDatabase[0][var0][5] + GameDatabase.gameDatabase[0][var0][6] * var1 + GameDatabase.gameDatabase[0][var0][7])
                  * qualityPercentages[var2 - 1]
                  / 100
            );
         case 2:
            return (short)(
               (GameDatabase.gameDatabase[0][var0][8] + GameDatabase.gameDatabase[0][var0][9] * var1 + GameDatabase.gameDatabase[0][var0][10])
                  * qualityPercentages[var2 - 1]
                  / 100
            );
         case 3:
            return (short)(
               (GameDatabase.gameDatabase[0][var0][11] + GameDatabase.gameDatabase[0][var0][12] * var1 / 10 + GameDatabase.gameDatabase[0][var0][13])
                  * qualityPercentages[var2 - 1]
                  / 100
            );
         case 4:
            return (short)(
               (GameDatabase.gameDatabase[0][var0][14] + GameDatabase.gameDatabase[0][var0][15] * var1 / 10 + GameDatabase.gameDatabase[0][var0][16])
                  * qualityPercentages[var2 - 1]
                  / 100
            );
         default:
            return 0;
      }
   }

   public final int L() {
      return super.currentStats[1] * 100 / super.baseStats[1];
   }

   public final int M() {
      return this.Q * 100 / super.baseStats[1];
   }

   public final int N() {
      return this.Q;
   }

   public final void u(int var1) {
      if (var1 >= super.baseStats[1]) {
         this.Q = super.baseStats[1];
      } else {
         this.Q = var1;
      }
   }

   public final int O() {
      return this.currentExp * 100 / this.getExpToNextLevel();
   }

   public final int v(int var1) {
      return var1 * 100 / this.getExpToNextLevel();
   }

   public static int a(short experience, short level) {
      int threshold = level >= 50 ? 37300 : level * 15 * level - 200;
      return experience * 100 / threshold;
   }

   public final int[] toSaveData() {
      int[] var1;
      (var1 = new int[9 + (this.learnedSkillCount << 1) + 1])[0] = this.speciesId;
      var1[1] = this.level;
      var1[2] = super.baseStats[5];
      var1[3] = super.currentStats[6];
      var1[4] = super.baseStats[0];
      var1[5] = this.statVariant;
      var1[6] = super.currentStats[1];
      var1[7] = this.currentExp;
      var1[8] = this.E;
      var1[9] = this.learnedSkillCount;

      for (int var2 = 0; var2 < this.learnedSkillCount; var2++) {
         var1[var2 + 10] = this.learnedSkillIds[var2];
         var1[10 + var1[9] + var2] = this.skillUsesRemaining[var2];
      }

      return var1;
   }

   public final int[] toSkillSaveData() {
      int[] var1;
      (var1 = new int[(this.learnedSkillCount << 1) + 1])[0] = this.learnedSkillCount;

      for (int var2 = 0; var2 < this.learnedSkillCount; var2++) {
         var1[var2 + 1] = this.learnedSkillIds[var2];
         var1[var1[0] + var2 + 1] = this.skillUsesRemaining[var2];
      }

      return var1;
   }

   public final int getEvolutionKind() {
      if (this.getSpeciesValue((byte)19) == -1) {
         return 0;
      } else if (GameDatabase.gameDatabase[0][this.getSpeciesValue((byte)19)][2] == 1) {
         return 1;
      } else if (GameDatabase.gameDatabase[0][this.getSpeciesValue((byte)19)][2] == 2) {
         return 1;
      } else {
         return GameDatabase.gameDatabase[0][this.getSpeciesValue((byte)19)][2] == 3 ? 2 : 0;
      }
   }

   public final void w(int var1) {
      switch (GameDatabase.gameDatabase[4][var1][5]) {
         case 1:
            short var5 = (short)(super.baseStats[1] * GameDatabase.gameDatabase[4][var1][6] / 100 + GameDatabase.gameDatabase[4][var1][7]);
            this.u(super.currentStats[1] + var5);
            this.l(var5);
            break;
         case 2:
            short var7 = GameDatabase.gameDatabase[4][var1][6];
            this.B(var7);
            break;
         case 3:
            short var4 = (short)(super.baseStats[1] * GameDatabase.gameDatabase[4][var1][6] / 100 + GameDatabase.gameDatabase[4][var1][7]);
            short var6 = GameDatabase.gameDatabase[4][var1][8];
            this.u(super.currentStats[1] + var4);
            this.l(var4);
            this.B(var6);
            break;
         case 4:
            this.activate();
            short var2 = (short)(super.baseStats[1] * GameDatabase.gameDatabase[4][var1][6] / 100 + GameDatabase.gameDatabase[4][var1][7]);
            short var3 = GameDatabase.gameDatabase[4][var1][8];
            this.u(var2);
            this.l(var2);
            this.B(var3);
            break;
         case 5:
            this.C();
            break;
         case 6:
            super.currentStats[6] = 2;
      }

      Player.getInstance().d(var1, 1, (byte)0);
   }

   public final int x(int var1) {
      if (!this.isAlive() && GameDatabase.gameDatabase[4][var1][5] != 4) {
         return 8;
      }

      switch (GameDatabase.gameDatabase[4][var1][5]) {
         case 0:
            return 6;
         case 1:
            if (super.baseStats[1] == super.currentStats[1]) {
               return 2;
            }
            break;
         case 2:
            byte var6 = this.learnedSkillCount;

            for (int var7 = 0; var7 < var6; var7++) {
               if (this.skillUsesRemaining[var7] < b(this.learnedSkillIds[var7], (byte)5)) {
                  return -1;
               }
            }

            return 3;
         case 3:
            byte var5 = -1;
            if (super.baseStats[1] == super.currentStats[1] || !this.isAlive()) {
               var5 = 2;
            }

            byte var2 = this.learnedSkillCount;

            for (int var3 = 0; var3 < var2; var3++) {
               if (this.skillUsesRemaining[var3] < b(this.learnedSkillIds[var3], (byte)5)) {
                  return -1;
               }
            }

            if (var5 == 2) {
               return 7;
            }
            break;
         case 4:
            if (this.isAlive()) {
               return 1;
            }
            break;
         case 5:
            for (int var4 = 0; var4 < this.w.length; var4++) {
               if (this.p(var4)) {
                  return -1;
               }
            }

            return 4;
         case 6:
            if (super.currentStats[6] >= 2) {
               return 5;
            }
      }

      return -1;
   }

   public final boolean isAlive() {
      return super.currentStats[1] > 0;
   }

   public final byte getElementalAffinity(Pet var1) {
      short var2;
      short var3;
      boolean var5;
      boolean var6;
      label114: {
         var2 = GameDatabase.gameDatabase[0][this.speciesId][1];
         var3 = GameDatabase.gameDatabase[0][var1.speciesId][1];
         short var4 = GameDatabase.gameDatabase[0][this.speciesId][22];
         short var7 = GameDatabase.gameDatabase[0][var1.speciesId][22];
         var5 = false;
         var6 = false;
         if (var4 != 2 || var7 != 2) {
            if (var4 == 2 && var7 != 2) {
               var5 = true;
               break label114;
            }

            if (var4 != 2 && var7 == 2) {
               var6 = true;
               break label114;
            }
         }

         var5 = true;
         var6 = true;
      }

      if (!var5
         || (var2 != 0 || var3 != 1)
            && (var2 != 1 || var3 != 2)
            && (var2 != 2 || var3 != 3)
            && (var2 != 3 || var3 != 0)
            && (var2 != 5 || var3 != 6)
            && (var2 != 6 || var3 != 4)
            && (var2 != 4 || var3 != 5)) {
         return (byte)(!var6
               || (var3 != 0 || var2 != 1)
                  && (var3 != 1 || var2 != 2)
                  && (var3 != 2 || var2 != 3)
                  && (var3 != 3 || var2 != 0)
                  && (var3 != 5 || var2 != 6)
                  && (var3 != 6 || var2 != 4)
                  && (var3 != 4 || var2 != 5)
            ? -1
            : 1);
      } else {
         return 0;
      }
   }

   public final int[] calculateDamage(Pet var1) {
      byte var2 = 0;
      int var3 = 5;
      int var4 = this.B();
      short var5 = GameDatabase.gameDatabase[0][this.speciesId][1];
      if (this.spriteId == Player.getInstance().elementStartIds[var5] + Player.getInstance().elementSpeciesCounts[var5] - 1) {
         var3 = 30;
      }

      int var9 = var3 + super.currentStats[4] / 2;
      if (this.f((byte)4)) {
         var9 += GameDatabase.gameDatabase[3][4][5];
      }

      if (EngineUtils.randomInt(100) <= var9) {
         var4 = var4 * 3 / 2;
         var2 = 1;
      }

      byte var13 = (byte)GameDatabase.gameDatabase[1][this.selectedSkillId][7];
      short var10 = -1;
      int var6 = var4;
      switch (this.selectedSkillId) {
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
         case 69:
            var4 = var4 * GameDatabase.gameDatabase[1][this.selectedSkillId][3] / 100;
            break;
         case 1:
         case 7:
            var4 = var4 * GameDatabase.gameDatabase[1][this.selectedSkillId][3] / 100 + var4 / GameDatabase.gameDatabase[1][this.selectedSkillId][8];
            break;
         case 2:
         case 8:
         case 22:
         case 28:
         case 41:
         case 47:
            var4 = var4 * GameDatabase.gameDatabase[1][this.selectedSkillId][3] / 100;
            var10 = GameDatabase.gameDatabase[1][this.selectedSkillId][8];
            break;
         case 3:
         case 9:
            if (var1.p(0)) {
               var4 = var4 * GameDatabase.gameDatabase[1][this.selectedSkillId][8] / 100;
            } else {
               var4 = var4 * GameDatabase.gameDatabase[1][this.selectedSkillId][3] / 100;
            }
            break;
         case 4:
         case 5:
         case 14:
         case 15:
         case 21:
         case 24:
         case 25:
         case 27:
         case 34:
         case 35:
         case 42:
         case 44:
         case 45:
         case 48:
         case 62:
         case 64:
         case 65:
         case 67:
         default:
            var13 = -1;
            break;
         case 23:
         case 29:
            if (var1.p(1)) {
               var4 = var4 * GameDatabase.gameDatabase[1][this.selectedSkillId][8] / 100;
            } else {
               var4 = var4 * GameDatabase.gameDatabase[1][this.selectedSkillId][3] / 100;
            }
            break;
         case 43:
         case 49:
            var4 = var4 * GameDatabase.gameDatabase[1][this.selectedSkillId][3] / 100;
            var1.D();
            break;
         case 53:
         case 59:
            int var7 = super.currentStats[1] * 100 / super.baseStats[1];
            var4 = var4 * (GameDatabase.gameDatabase[1][this.selectedSkillId][8] - var7) / 100;
      }

      if (var6 <= 0) {
         var6 = 1;
      }

      short var10002 = (short)var6;
      short var10003 = this.selectedSkillId;
      short var8 = var10;
      short var19 = var10003;
      short var16 = var10002;
      int var14 = var13;
      Pet var11 = var1;
      byte var10000;
      if (var14 == -1) {
         var10000 = -1;
      } else {
         label153: {
            if (var11.f((byte)3)) {
               if (EngineUtils.randomInt(100) > var8 * (100 - GameDatabase.gameDatabase[3][3][5]) / 100) {
                  var10000 = -1;
                  break label153;
               }
            } else {
               if (var11.m(14)) {
                  var10000 = -1;
                  break label153;
               }

               if (var8 != -1 && EngineUtils.randomInt(100) > var8) {
                  var10000 = -1;
                  break label153;
               }
            }

            switch (var14) {
               case 0:
                  var11.w[var14][1] = var16;
               case 1:
               case 2:
               case 8:
               case 9:
               case 10:
               default:
                  break;
               case 3:
                  var11.w[var14][1] = var16;
                  break;
               case 4:
                  var11.w[var14][1] = GameDatabase.gameDatabase[1][var19][8];
                  break;
               case 5:
                  var11.w[var14][1] = (short)(var11.baseStats[4] * GameDatabase.gameDatabase[1][var19][8] / 100);
                  var16 = (short)(var11.baseStats[4] - var11.w[var14][1]);
                  var11.currentStats[4] = var16;
                  break;
               case 6:
                  var11.w[var14][1] = GameDatabase.gameDatabase[1][var19][8];
                  break;
               case 7:
                  var11.w[var14][1] = (short)(var11.baseStats[3] * GameDatabase.gameDatabase[1][var19][8] / 100);
                  var16 = (short)(var11.baseStats[3] - var11.w[var14][1]);
                  var11.currentStats[3] = var16;
            }

            var11.a(1, (byte)var14);
            if (var11.X == 0 && Player.getInstance().c((byte)6, (byte)0) == 2 && Player.getInstance().c((byte)6, (byte)1) == 1) {
               var11.w[var14][0] = (short)(GameDatabase.gameDatabase[7][var14][2] / 2);
            } else {
               var11.w[var14][0] = GameDatabase.gameDatabase[7][var14][2];
            }

            var11.w[var14][3] = var19;
            var11.w[var14][4] = 1;
            var10000 = (byte)var14;
         }
      }

      byte var20 = (byte)var10000;
      if (this.m(0) && this.v[0][0] == 0) {
         var4 += this.v[0][2];
      }

      if (this.m(1)) {
         var4 += var4 * this.v[1][2] / 100;
      }

      if (this.p(6)) {
         var4 -= var4 * this.w[6][1] / 100;
      }

      if (var1.m(6) && EngineUtils.randomInt(100) <= this.v[6][1]) {
         var4 = var4 * this.v[6][2] / 100;
      }

      if (this.m(8)) {
         var4 += var4 * this.v[8][1] / 100;
      }

      if (this.X == 0 && Player.getInstance().c((byte)3, (byte)0) == 2 && Player.getInstance().c((byte)3, (byte)1) == 1 && WorldManager.O == 2) {
         var4 += var4 * GameDatabase.gameDatabase[2][3][5] / 100;
      }

      if (this.X == 0 && Player.getInstance().c((byte)6, (byte)0) == 2) {
         var4 += var4 * GameDatabase.gameDatabase[2][6][5] / 100;
      }

      if (this.getElementalAffinity(var1) == 0) {
         var4 *= 3;
      } else if (this.getElementalAffinity(var1) == 1) {
         var4 = var4 * 60 / 100;
      }

      if (var4 <= 0) {
         var4 = 1;
      } else {
         var3 = EngineUtils.randomInt(100);
         var14 = (var4 << 1) / 100;
         if (var3 > 50) {
            if (var14 <= 0) {
               var4++;
            }
         } else if (var14 <= 0) {
            var4--;
         }

         if (var4 <= 0) {
            var4 = 1;
         }
      }

      if (var1.m(5) && EngineUtils.randomInt(100) <= var1.v[5][1]) {
         this.K[5] = (short)var4;
         return new int[]{var4, var2, var20};
      } else {
         return new int[]{var4, var2, var20};
      }
   }

   public final String T() {
      String[] var1 = new String[]{"Mộc hệ", "Thổ hệ", "Thủy hệ", "Hỏa hệ", "Quỷ hệ", "Phong hệ", "Điện hệ"};
      short var2 = GameDatabase.gameDatabase[0][this.speciesId][1];
      return var1[var2];
   }

   public static String y(int var0) {
      String[] var1 = new String[]{"Mộc hệ", "Thổ hệ", "Thủy hệ", "Hỏa hệ", "Quỷ hệ", "Phong hệ", "Điện hệ"};
      short var2 = GameDatabase.gameDatabase[0][var0][1];
      return var1[var2];
   }
}
