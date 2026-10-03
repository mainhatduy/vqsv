package game;

public class BaseEntity {
   public short[] baseStats;
   public short[] currentStats;
   protected boolean e;
   protected boolean visible;
   protected boolean g;
   protected byte actionState;
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
   private short followDistance = 10;
   private int[][] followHistory;
   private boolean following = false;

   public final void setCurrentStat(byte var1, short var2) {
      this.currentStats[var1] = var2;
   }

   public final short getCurrentHp(byte var1) {
      return this.currentStats[1];
   }

   public void resetCurrentStats() {
      for (int var1 = 0; var1 < this.baseStats.length; var1++) {
         int var2 = var1;
         short var3 = this.baseStats[var2];
         var2 = var1;
         this.currentStats[var2] = var3;
      }
   }

   public final byte getActionState() {
      return this.actionState;
   }

   public final void a(boolean var1) {
      this.e = var1;
   }

   public final boolean i() {
      return this.e;
   }

   public final void setVisible(boolean var1) {
      this.visible = var1;
   }

   public final boolean isVisible() {
      return this.visible;
   }

   public final void c(boolean var1) {
      this.g = var1;
   }

   public final boolean k() {
      return this.g;
   }

   public void setPosition(int var1, int var2) {
      this.posX = var1;
      this.posY = var2;
   }

   public final void setFacingDirection(byte var1) {
      this.facingDirection = var1;
   }

   public final int getPosX() {
      return this.posX;
   }

   public final int getPosY() {
      return this.posY;
   }

   public final void movePosX(int var1) {
      this.posX += var1;
   }

   public final void movePosY(int var1) {
      this.posY += var1;
   }

   public void b(int var1) {
      this.posX += var1;
      this.posY += 4;
   }

   public final boolean moveTowards(int var1, int var2, int var3) {
      if (this.posX == var2 && this.posY == var3) {
         return true;
      }

      int var4;
      if ((var4 = EngineUtils.a(this.posX, this.posY, var2, var3)) < var1) {
         this.posX = var2;
         this.posY = var3;
      } else {
         this.movePosX((var2 - this.posX) * var1 / var4);
         this.movePosY((var3 - this.posY) * var1 / var4);
      }

      return false;
   }

   public final void updateFollowing(SpriteRenderer var1, SpriteRenderer var2) {
      if (this.following) {
         if (this.ownerEntity.actionState != 0) {
            this.followHistory[0][0] = this.ownerEntity.posX;
            this.followHistory[0][1] = this.ownerEntity.posY;
            this.followHistory[0][2] = var1.animationId;
            this.followHistory[0][3] = this.ownerEntity.facingDirection;

            for (int var4 = this.followDistance; var4 > 0; var4--) {
               this.followHistory[var4][0] = this.followHistory[var4 - 1][0];
               this.followHistory[var4][1] = this.followHistory[var4 - 1][1];
               this.followHistory[var4][2] = this.followHistory[var4 - 1][2];
               this.followHistory[var4][3] = this.followHistory[var4 - 1][3];
               if (var4 % this.followDistance == 0) {
                  this.setPosition(this.followHistory[var4][0], this.followHistory[var4][1]);
                  if (this.followHistory[var4][3] == 3) {
                     var2.setAnimation((byte)this.followHistory[var4][2], (byte)1, false);
                  } else {
                     var2.setAnimation((byte)this.followHistory[var4][2], (byte)this.followHistory[var4][3], false);
                  }

                  this.facingDirection = (byte)this.followHistory[var4][3];
               }
            }
         }
      }
   }

   public final void startFollowing(byte var1) {
      this.following = true;
      this.followHistory = new int[this.followDistance + 1][4];

      for (int var2 = 0; var2 < this.followDistance + 1; var2++) {
         this.followHistory[var2][0] = this.ownerEntity.posX;
         this.followHistory[var2][1] = this.ownerEntity.posY;
         this.followHistory[var2][3] = this.ownerEntity.facingDirection;
      }

      if (var1 >= 0) {
         for (int var3 = 0; var3 < this.followDistance + 1; var3++) {
            this.followHistory[var3][2] = var1;
         }
      }

      switch (this.ownerEntity.facingDirection) {
         case 0:
            this.followHistory[10][1] = this.followHistory[10][1] - this.followDistance;
            break;
         case 1:
            this.followHistory[10][0] = this.followHistory[10][0] - this.followDistance;
            break;
         case 2:
            this.followHistory[10][1] = this.followHistory[10][1] + this.followDistance;
            break;
         case 3:
            this.followHistory[10][0] = this.followHistory[10][0] + this.followDistance;
      }

      this.setPosition(this.followHistory[10][0], this.followHistory[10][1]);
   }

   public final boolean isFollowing() {
      return this.following;
   }

   public final void setOwnerEntity(BaseEntity var1) {
      this.ownerEntity = var1;
   }
}
