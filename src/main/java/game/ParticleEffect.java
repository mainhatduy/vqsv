package game;

public final class ParticleEffect extends BaseEntity {
   private static ParticleEffect t;
   public byte a = -1;
   public byte b = -1;
   private int u;
   private int v;
   private int w;
   private boolean x = true;

   public ParticleEffect() {
      this.b = 0;
      this.a = 0;
   }

   public static ParticleEffect a() {
      if (t == null) {
         t = new ParticleEffect();
      }

      return t;
   }

   public final void a(int var1) {
      this.w = var1;
   }

   public final void b() {
      this.x = false;
   }

   public final void a(int var1, int var2, boolean var3) {
      this.d((byte)0);
      this.x = (boolean)var3;
      if (this.x) {
         this.posX = var1;
         this.posY = var2;
      } else {
         super.q = var1;
         super.r = var2;
      }
   }

   public final void a(BaseEntity var1, boolean var2) {
      this.d((byte)1);
      super.ownerEntity = var1;
      this.x = var2;
      if (this.x) {
         this.posX = this.ownerEntity.posX;
         this.posY = this.ownerEntity.posY;
      }
   }

   public final boolean c() {
      return this.x;
   }

   public final void d(byte var1) {
      this.b = this.a;
      this.a = var1;
   }

   public final void d() {
      switch (this.a) {
         case 0:
            if (!this.x && this.moveTowards(this.w, this.q, this.r)) {
               this.x = true;
               return;
            }
            break;
         case 1:
            if (this.x) {
               this.posX = this.ownerEntity.posX;
               this.posY = this.ownerEntity.posY;
               return;
            }

            if (this.moveTowards(this.w, this.ownerEntity.posX, this.ownerEntity.posY)) {
               this.x = true;
               return;
            }
            break;
         case 2:
            if (this.x) {
               return;
            }

            if (this.u > 0) {
               this.u--;
               return;
            }

            if (this.posX == ((int[])null)[0] && this.posY == ((int[])null)[1]) {
               if (this.v < ((int[])null).length) {
                  this.v++;
                  this.u = ((int[])null)[5];
                  return;
               }

               this.x = true;
               return;
            }

            this.moveTowards(((int[])null)[2], ((int[])null)[0], ((int[])null)[1]);
      }
   }
}
