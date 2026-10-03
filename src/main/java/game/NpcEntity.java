package game;

import javax.microedition.lcdui.Graphics;

public final class NpcEntity extends WorldEntity {
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
   public short I = -1;
   private short[] T = new short[]{8, 9, 2, 96, 320, 0};

   public final void a(short[] var1, int var2) {
      this.I = (short)var2;
      this.t = (byte)var1[0];
      this.spriteRenderer.loadSprite(var1[1], false);
      SpriteRenderer.c();
      this.v = (byte)var1[6];
      if (this.t == 0 && (this.v == 1 || this.v == 18)) {
         super.facingDirection = (byte)(var1[2] % 3);
      }

      this.d((byte)var1[2]);
      this.posX = var1[3];
      this.posY = var1[4];
      if (var1[5] == 1) {
         this.setVisible(true);
      } else {
         this.setVisible(false);
      }

      switch (this.t) {
         case 0:
            this.s = (byte)var1[7];
            this.w = (byte)var1[8];
            NpcEntity var4 = this;
            if (this.w != 0 && var4.G == null && var4.isVisible()) {
               var4.G = new WorldEntity();
               var4.G.loadSprite(259, false);
               var4.G.setAnimation(var4.w, (byte)-1, true);
               var4.G.setPosition(var4.posX, var4.posY - 40);
               var4.G.ownerEntity = var4;
            }

            var4.z();
            this.v();
            if (var1[9] == 0) {
               this.u = false;
            } else {
               this.u = true;
            }

            this.x = var1[11];
            this.y = var1[12];
            this.J = 0;
            this.L = EngineUtils.randomRange(20, 40);
            this.z = 0;
            if (this.v == 12) {
               this.facingDirection = 0;
            } else if (this.v == 13) {
               this.facingDirection = 1;
            }

            if (this.v == 3) {
               if (this.actionState == 4) {
                  this.facingDirection = 1;
               }
            } else if (this.v == 2) {
               if (this.actionState == 5) {
                  this.facingDirection = 2;
               } else if (this.actionState == 3) {
                  this.facingDirection = 0;
               }
            }

            if (this.v == 1 && this.spriteRenderer.spriteId != 226 || this.v == 2 || this.v == 3 || this.v == 17) {
               if (this.targetEntity == null) {
                  this.targetEntity = new WorldEntity();
                  this.targetEntity.loadSprite(337, false);
               }

               this.targetEntity.setPosition(this.posX, this.posY);
               if (this.spriteRenderer.spriteId == 4) {
                  this.targetEntity.setAnimation((byte)0, (byte)0, this.Q);
               } else {
                  this.targetEntity.setAnimation((byte)1, (byte)0, this.Q);
               }

               this.targetEntity.activate();
            }
            break;
         case 1:
            if (var1[1] == 320) {
               this.s = 2;
            } else {
               this.s = 1;
            }

            if (this.isVisible() && var1[0] > 0 && var1[0] <= 3) {
               WorldManager.a().e.addElement(this);
            }

            if (this.v == 3) {
               this.u = true;
            }

            this.C = (byte)var1[7];
            this.N = var1[8];
            this.O = var1[9];
            this.P = var1[10];
            break;
         case 2:
            if (var1[7] == 0) {
               this.Q = false;
            } else {
               this.Q = true;
            }
            break;
         case 3:
            this.s = 1;
            this.D = var1[7];
            this.E = var1[8];
            switch (this.E) {
               case 9:
                  this.E = 1;
                  break;
               case 10:
                  this.E = 0;
                  break;
               case 11:
                  this.E = 2;
                  break;
               case 12:
                  this.E = 3;
            }

            this.F = var1[9];
            this.u = true;
      }

      this.baseStats = new short[3];
      this.currentStats = new short[3];
   }

   public final void d(byte var1) {
      switch (this.t) {
         case 0:
            if (this.v == 8) {
               this.setAnimation((byte)0, (byte)-1, false);
               this.J = 0;
               this.actionState = var1;
               return;
            }

            if (this.v != 1 && this.v != 18) {
               this.setAnimation(var1, (byte)-1, false);
               this.actionState = var1;
               WorldManager.a().a(this.I, 0, this.actionState, true);
               return;
            }

            this.actionState = (byte)(var1 / 3);
            if (this.actionState == 0) {
               if (this.facingDirection == 3) {
                  this.setAnimation((byte)1, (byte)-1, false);
                  return;
               }

               this.setAnimation(this.facingDirection, (byte)-1, false);
               return;
            }

            if (this.actionState == 1) {
               if (this.facingDirection == 3) {
                  this.setAnimation((byte)(this.actionState * 3 + 1), (byte)-1, false);
                  return;
               }

               this.setAnimation((byte)(this.actionState * 3 + this.facingDirection), (byte)-1, false);
               return;
            }
            break;
         case 1:
            if (this.v == 0) {
               switch (var1) {
                  case 0:
                     this.setAnimation(var1, (byte)-1, false);
                     break;
                  case 1:
                     this.setAnimation(var1, (byte)-2, false);
                     break;
                  case 2:
                     this.setAnimation(var1, (byte)-1, false);
                     break;
                  case 3:
                     this.setAnimation(var1, (byte)-2, false);
               }
            } else {
               this.setAnimation(var1, (byte)-1, false);
               if (this.v == 3) {
                  WorldManager.a().a(this.I, 0, var1, true);
               }
            }

            this.actionState = var1;
            return;
         case 2:
            this.actionState = var1;
            return;
         case 3:
            this.setAnimation(var1, (byte)-2, false);
            this.actionState = var1;
            WorldManager.a().a(this.I, 0, this.actionState, true);
      }
   }

   public final void o() {
      label353:
      switch (this.t) {
         case 0:
            NpcEntity var3 = this;
            switch (this.v) {
               case 1:
                  if (var3.actionState == 1) {
                     short var7;
                     var3.moveInFacingDirection(var7 = var3.currentStats[0]);
                     if (WorldManager.a().p != null
                        && WorldManager.a().p.ownerEntity != null
                        && WorldManager.a().p.ownerEntity.equals(var3)
                        && Player.getInstance().spriteRenderer != null
                        && !Player.getInstance().a(var3, Player.getInstance().spriteRenderer.k(), var3.spriteRenderer.k())) {
                        WorldManager.a().e();
                     }
                  }

                  if (WorldManager.a().p != null
                     && WorldManager.a().p.ownerEntity != null
                     && WorldManager.a().p.ownerEntity.equals(var3)
                     && Player.getInstance().spriteRenderer != null
                     && (!var3.isVisible() || !Player.getInstance().a(var3, Player.getInstance().spriteRenderer.k(), var3.spriteRenderer.k()))) {
                     WorldManager.u = -1;
                     WorldManager.a().e();
                  }

                  if (var3.G != null
                     && var3.isVisible()
                     && var3.G.ownerEntity.equals(var3)
                     && Player.getInstance().spriteRenderer != null
                     && !Player.getInstance().a(var3, Player.getInstance().spriteRenderer.k(), var3.spriteRenderer.k())) {
                     var3.z();
                  }

                  if (OverworldScreen.d != null
                     && OverworldScreen.d.size() > 0
                     && var3.S == 1
                     && Player.getInstance().spriteRenderer != null
                     && !Player.getInstance().a(var3, Player.getInstance().spriteRenderer.k(), var3.spriteRenderer.k())) {
                     for (int var8 = 0; var8 < OverworldScreen.d.size(); var8++) {
                        if (((WorldEntity)OverworldScreen.d.elementAt(var8)).ownerEntity.equals(var3)) {
                           ((WorldEntity)OverworldScreen.d.elementAt(var8)).activate();
                           break label353;
                        }
                     }
                  }
                  break label353;
               case 2:
                  if (var3.y()) {
                     byte[] var6 = new byte[]{0, 1, 2, 3, 5};
                     var3.d(var6[EngineUtils.randomInt(5)]);
                     if (var3.actionState == 3 || var3.actionState == 0) {
                        var3.facingDirection = 0;
                     } else if (var3.actionState == 5 || var3.actionState == 2) {
                        var3.facingDirection = 2;
                     } else if (EngineUtils.randomInt(2) == 0) {
                        var3.facingDirection = 3;
                     } else {
                        var3.facingDirection = 1;
                     }
                  }

                  if (var3.actionState == 3) {
                     if (var3.J >= 64) {
                        var3.d((byte)0);
                     } else {
                        var3.moveInFacingDirection(4);
                        var3.J += 4;
                     }
                  } else if (var3.actionState == 5) {
                     if (var3.J <= 0) {
                        var3.d((byte)2);
                     } else {
                        var3.moveInFacingDirection(4);
                        var3.J -= 4;
                     }
                  }
                  break label353;
               case 3:
                  if (var3.y()) {
                     byte[] var5 = new byte[]{0, 1, 2, 4};
                     var3.d(var5[EngineUtils.randomInt(4)]);
                     if (var3.actionState == 0) {
                        var3.facingDirection = 0;
                     } else if (var3.actionState == 2) {
                        var3.facingDirection = 2;
                     } else if (EngineUtils.randomInt(2) == 0) {
                        var3.facingDirection = 3;
                     } else {
                        var3.facingDirection = 1;
                     }
                  }

                  if (var3.actionState == 4) {
                     if (var3.facingDirection == 1) {
                        if (var3.J >= 64) {
                           var3.d((byte)0);
                        } else {
                           var3.moveInFacingDirection(4);
                           var3.J += 4;
                        }
                     } else if (var3.facingDirection == 3) {
                        if (var3.J <= 0) {
                           var3.d((byte)2);
                        } else {
                           var3.moveInFacingDirection(4);
                           var3.J -= 4;
                        }
                     }
                  }
                  break label353;
               case 4:
               case 5:
               case 6:
               case 7:
               case 15:
                  if (var3.H != null
                     && var3.isVisible()
                     && var3.H.ownerEntity.equals(var3)
                     && (var3.actionState != 0 || !Player.getInstance().a(var3, Player.getInstance().spriteRenderer.k(), var3.spriteRenderer.k()))) {
                     var3.x();
                  }

                  if (var3.actionState == 1 && var3.spriteRenderer.isLastAnimationStep()) {
                     var3.d((byte)2);
                     if (var3.v == 6 && var3.v == 7) {
                        WorldManager.a().a(var3.I, 0, var3.actionState, false);
                     } else {
                        WorldManager.a().a(var3.I, 0, var3.actionState, true);
                     }

                     int var2;
                     if ((var3.v == 7 || var3.v == 6) && (var2 = EngineUtils.randomInt(2)) > 0) {
                        Player.getInstance().addGold(var2);
                        int[] var4 = new int[]{var2, var3.posX, var3.posY - 20, 0};
                        Player.getInstance().V.addElement(var4);
                     }

                     OverworldScreen.h = false;
                  }
                  break label353;
               case 8:
                  if (var3.actionState == 1) {
                     if (var3.J < 2 && var3.a(var3.facingDirection, 8, (byte)0)) {
                        var3.J++;
                        var3.moveInFacingDirection(8);
                        var3.r();
                     } else {
                        var3.d((byte)0);
                     }
                  }
                  break label353;
               case 9:
               case 10:
                  if (var3.z == 1) {
                     if ((var3.spriteRenderer.spriteId == 302 || var3.spriteRenderer.spriteId == 298) && var3.a(var3.facingDirection, 4, (byte)1)) {
                        var3.moveInFacingDirection(4);
                        ((WorldEntity)var3.ownerEntity).moveInFacingDirection(4);
                     } else if (var3.a(var3.facingDirection, 4, (byte)2)) {
                        var3.moveInFacingDirection(4);
                        ((WorldEntity)var3.ownerEntity).moveInFacingDirection(4);
                     } else {
                        var3.r();
                        var3.z = 2;
                        var3.d((byte)0);
                     }
                  }
               case 11:
               default:
                  break label353;
               case 12:
                  if (var3.isVisible()) {
                     if (EngineUtils.a(Player.getInstance().posX, Player.getInstance().posY, var3.posX, var3.posY, var3.spriteRenderer.k())) {
                        if (Player.getInstance().getActionState() != 8) {
                           Player.getInstance().b((byte)8, var3.facingDirection);
                        }
                     } else if (var3.a(var3.facingDirection, 4, (byte)0)) {
                        var3.moveInFacingDirection(4);
                     } else if (var3.facingDirection == 2) {
                        var3.facingDirection = 0;
                     } else {
                        var3.facingDirection = 2;
                     }
                  }
                  break label353;
               case 13:
                  if (var3.isVisible()) {
                     if (EngineUtils.a(Player.getInstance().posX, Player.getInstance().posY, var3.posX, var3.posY, var3.spriteRenderer.k())) {
                        if (Player.getInstance().getActionState() != 8) {
                           Player.getInstance().b((byte)8, var3.facingDirection);
                        }
                     } else if (var3.a(var3.facingDirection, 4, (byte)0)) {
                        var3.moveInFacingDirection(4);
                     } else if (var3.facingDirection == 3) {
                        var3.facingDirection = 1;
                     } else {
                        var3.facingDirection = 3;
                     }
                  }
                  break label353;
               case 14:
                  var3.q();
                  if (var3.M < 4) {
                     var3.M++;
                  } else {
                     var3.M = 0;
                  }
                  break label353;
               case 16:
                  if (EngineUtils.a(Player.getInstance().posX, Player.getInstance().posY, var3.posX, var3.posY, var3.spriteRenderer.k())
                     && Player.getInstance().getActionState() != 5) {
                     Player.getInstance().b((byte)5, Player.getInstance().facingDirection);
                  }
                  break label353;
            }
         case 1:
            if (this.v == 0) {
               if (this.actionState == 0
                  && EngineUtils.a(
                     Player.getInstance().posX,
                     Player.getInstance().posY,
                     this.posX,
                     this.posY,
                     Player.getInstance().spriteRenderer.k(),
                     this.spriteRenderer.k()
                  )) {
                  this.d((byte)1);
               } else if (this.actionState == 2
                  && !EngineUtils.a(
                     Player.getInstance().posX,
                     Player.getInstance().posY,
                     this.posX,
                     this.posY,
                     Player.getInstance().spriteRenderer.k(),
                     this.spriteRenderer.k()
                  )) {
                  this.d((byte)3);
               } else if (this.actionState == 1 && this.spriteRenderer.isLastAnimationStep()) {
                  this.d((byte)2);
               } else if (this.actionState == 3 && this.spriteRenderer.isLastAnimationStep()) {
                  this.d((byte)0);
               }
            }

            if (this.v == 0 && this.spriteRenderer.isLastAnimationStep() || this.v == 1 || this.v == 3 && this.getActionState() == 2) {
               if (this.spriteRenderer.spriteId == 320 && !this.isVisible()) {
                  return;
               }

               if ((
                     Player.getInstance().o == this.R[this.C] && this.spriteRenderer.spriteId != 320 && this.spriteRenderer.spriteId != 310
                        || this.spriteRenderer.spriteId == 320
                        || this.spriteRenderer.spriteId == 310
                  )
                  && EngineUtils.a(
                     Player.getInstance().posX,
                     Player.getInstance().posY,
                     this.posX,
                     this.posY,
                     Player.getInstance().spriteRenderer.k(),
                     this.spriteRenderer.j()
                  )) {
                  WorldManager.a().f = this.N;
                  WorldManager.a().g = this.O;
                  WorldManager.a().j = this.P;
                  GameStateController.getInstance().setScreenMode((byte)9);
               }
            } else if (this.v == 2) {
               if ((Player.getInstance().o == this.R[this.C] && this.spriteRenderer.spriteId != 320 || this.spriteRenderer.spriteId == 320)
                  && EngineUtils.a(
                     Player.getInstance().posX,
                     Player.getInstance().posY,
                     this.posX,
                     this.posY,
                     Player.getInstance().spriteRenderer.k(),
                     this.spriteRenderer.j()
                  )) {
                  for (int var1 = 0; var1 < this.T.length / 6; var1++) {
                     if (this.T[var1 * 6] == this.I && this.T[var1 * 6 + 1] == WorldManager.a().f && this.T[var1 * 6 + 2] == WorldManager.a().g) {
                        WorldManager.a().h = this.T[var1 * 6 + 3];
                        WorldManager.a().i = this.T[var1 * 6 + 4];
                        WorldManager.w = (byte)this.T[var1 * 6 + 5];
                        break;
                     }
                  }

                  WorldManager.a().f = this.N;
                  WorldManager.a().g = this.O;
                  WorldManager.a().j = -1;
                  GameStateController.getInstance().setScreenMode((byte)9);
               }
            } else if (this.v == 4
               && Player.getInstance().getActionState() != 9
               && Player.getInstance().getActionState() != 10
               && EngineUtils.a(
                  Player.getInstance().posX, Player.getInstance().posY, this.posX, this.posY, Player.getInstance().spriteRenderer.k(), this.spriteRenderer.j()
               )) {
               Player.getInstance().setPosition(this.posX, this.posY);
               Player.getInstance().targetEntity.setPosition(this.posX, this.posY);
               Player.getInstance().b((byte)9, this.facingDirection);
               WorldManager.a().j = this.P;
            }
         case 2:
         case 3:
      }

      this.f();
   }

   public final void p() {
      if (this.v == 9) {
         this.d((byte)1);
         if ((this.spriteRenderer.spriteId == 302 || this.spriteRenderer.spriteId == 298) && this.a((byte)1, 4, (byte)1)) {
            super.facingDirection = 1;
         } else if (this.a((byte)1, 4, (byte)2)) {
            super.facingDirection = 1;
         } else {
            super.facingDirection = 3;
         }
      } else if ((this.spriteRenderer.spriteId == 302 || this.spriteRenderer.spriteId == 298) && this.a((byte)2, 4, (byte)1)) {
         this.d((byte)2);
         super.facingDirection = 2;
      } else if (this.a((byte)2, 4, (byte)2)) {
         this.d((byte)2);
         super.facingDirection = 2;
      } else {
         this.d((byte)1);
         super.facingDirection = 0;
      }

      this.z = 1;
   }

   public final void q() {
      this.A = 0;

      while (true) {
         boolean var10000;
         label85: {
            Object var1 = null;
            byte var10001 = super.spriteRenderer.getAnimationId();
            int var10002 = 16 * (this.A + 1);
            var1 = false;
            int var3 = var10002;
            byte var2 = var10001;
            var1 = this;
            byte var4 = 0;
            switch (var2) {
               case 0:
                  var4 = MapEngine.getInstance().getCollisionTile(((NpcEntity)var1).posX, ((NpcEntity)var1).posY + var3);

                  for (int var10 = 0; var10 < WorldManager.a().d.length; var10++) {
                     if (WorldManager.a().d[var10].v != ((NpcEntity)var1).v
                        && WorldManager.a().d[var10].spriteRenderer.k() != null
                        && EngineUtils.a(
                           ((NpcEntity)var1).posX,
                           ((NpcEntity)var1).posY + var3,
                           WorldManager.a().d[var10].posX,
                           WorldManager.a().d[var10].posY,
                           WorldManager.a().d[var10].spriteRenderer.k()
                        )) {
                        WorldManager.a().d[var10].ownerEntity = (BaseEntity)var1;
                        var10000 = false;
                        break label85;
                     }
                  }
                  break;
               case 1:
                  var4 = MapEngine.getInstance().getCollisionTile(((NpcEntity)var1).posX + var3, ((NpcEntity)var1).posY);

                  for (int var9 = 0; var9 < WorldManager.a().d.length; var9++) {
                     if (WorldManager.a().d[var9].v != ((NpcEntity)var1).v
                        && WorldManager.a().d[var9].spriteRenderer.k() != null
                        && EngineUtils.a(
                           ((NpcEntity)var1).posX + var3,
                           ((NpcEntity)var1).posY,
                           WorldManager.a().d[var9].posX,
                           WorldManager.a().d[var9].posY,
                           WorldManager.a().d[var9].spriteRenderer.k()
                        )) {
                        WorldManager.a().d[var9].ownerEntity = (BaseEntity)var1;
                        var10000 = false;
                        break label85;
                     }
                  }
                  break;
               case 2:
                  var4 = MapEngine.getInstance().getCollisionTile(((NpcEntity)var1).posX, ((NpcEntity)var1).posY - var3);

                  for (int var8 = 0; var8 < WorldManager.a().d.length; var8++) {
                     if (WorldManager.a().d[var8].v != ((NpcEntity)var1).v
                        && WorldManager.a().d[var8].spriteRenderer.k() != null
                        && EngineUtils.a(
                           ((NpcEntity)var1).posX,
                           ((NpcEntity)var1).posY - var3,
                           WorldManager.a().d[var8].posX,
                           WorldManager.a().d[var8].posY,
                           WorldManager.a().d[var8].spriteRenderer.k()
                        )) {
                        WorldManager.a().d[var8].ownerEntity = (BaseEntity)var1;
                        var10000 = false;
                        break label85;
                     }
                  }
                  break;
               case 3:
                  var4 = MapEngine.getInstance().getCollisionTile(((NpcEntity)var1).posX - var3, ((NpcEntity)var1).posY);

                  for (int var7 = 0; var7 < WorldManager.a().d.length; var7++) {
                     if (WorldManager.a().d[var7].v != ((NpcEntity)var1).v
                        && WorldManager.a().d[var7].spriteRenderer.k() != null
                        && EngineUtils.a(
                           ((NpcEntity)var1).posX - var3,
                           ((NpcEntity)var1).posY,
                           WorldManager.a().d[var7].posX,
                           WorldManager.a().d[var7].posY,
                           WorldManager.a().d[var7].spriteRenderer.k()
                        )) {
                        WorldManager.a().d[var7].ownerEntity = (BaseEntity)var1;
                        var10000 = false;
                        break label85;
                     }
                  }
            }

            var10000 = var4 == 0;
         }

         if (!var10000) {
            return;
         }

         this.A++;
      }
   }

   private boolean y() {
      this.K++;
      if (this.K >= this.L) {
         this.K = 0;
         this.L = EngineUtils.randomRange(20, 40);
         return true;
      } else {
         return false;
      }
   }

   private boolean a(byte var1, int var2, byte var3) {
      byte var4 = 0;
      switch (var1) {
         case 0:
            var4 = MapEngine.getInstance().getCollisionTile(this.posX, this.posY + var2);
            break;
         case 1:
            var4 = MapEngine.getInstance().getCollisionTile(this.posX + var2 + this.spriteRenderer.k()[2] / 2, this.posY);
            break;
         case 2:
            var4 = MapEngine.getInstance().getCollisionTile(this.posX, this.posY - this.spriteRenderer.k()[3] - var2);
            break;
         case 3:
            var4 = MapEngine.getInstance().getCollisionTile(this.posX - var2 - this.spriteRenderer.k()[2] / 2, this.posY);
      }

      return var4 == var3;
   }

   public final void b(Graphics var1, int var2, int var3) {
      switch (super.spriteRenderer.getAnimationId()) {
         case 0:
            var1.setColor(65280);
            var1.fillRect(this.posX - var2 - (this.M + 5) / 2, this.posY - this.spriteRenderer.j()[3] - var3 + 20, this.M + 5, this.A + 1 << 4);
            var1.setColor(16777215);
            var1.fillRect(this.posX - var2 - (this.M + 3) / 2, this.posY - this.spriteRenderer.j()[3] - var3 + 20, this.M + 3, this.A + 1 << 4);
            return;
         case 1:
            var1.setColor(65280);
            var1.fillRect(this.posX - var2 + 7, this.posY - this.spriteRenderer.j()[3] - var3 - (this.M + 5) / 2 + 13, this.A << 4, this.M + 5);
            var1.setColor(16777215);
            var1.fillRect(this.posX - var2 + 7, this.posY - this.spriteRenderer.j()[3] - var3 - (this.M + 3) / 2 + 13, this.A << 4, this.M + 3);
         default:
            return;
         case 2:
            var1.setColor(65280);
            var1.fillRect(this.posX - var2 - (this.M + 5) / 2, this.posY - this.spriteRenderer.j()[3] - var3 - (this.A << 4) + 8, this.M + 5, this.A << 4);
            var1.setColor(16777215);
            var1.fillRect(this.posX - var2 - (this.M + 3) / 2, this.posY - this.spriteRenderer.j()[3] - var3 - (this.A << 4) + 8, this.M + 3, this.A << 4);
            return;
         case 3:
            var1.setColor(65280);
            var1.fillRect(this.posX - var2 - 8 - (this.A << 4), this.posY - this.spriteRenderer.j()[3] - var3 - (this.M + 5) / 2 + 13, this.A << 4, this.M + 5);
            var1.setColor(16777215);
            var1.fillRect(this.posX - var2 - 8 - (this.A << 4), this.posY - this.spriteRenderer.j()[3] - var3 - (this.M + 3) / 2 + 13, this.A << 4, this.M + 3);
      }
   }

   public final void r() {
      WorldManager.a().a(this.I, 0, this.posX);
      WorldManager.a().a(this.I, 1, this.posY);
   }

   public final void s() {
      byte var1 = 0;
      if (this.isVisible()) {
         var1 = 1;
      }

      WorldManager.a().a(this.I, 1, var1, true);
      WorldManager.a().a(this.I, 0, this.actionState, true);
      WorldManager.a().a(this.I, 2, this.facingDirection, true);
   }

   public final void moveInFacingDirection(int var1) {
      super.moveInFacingDirection(var1);
   }

   public final void t() {
      this.S = 1;
   }

   public final byte u() {
      return this.S;
   }

   public final void f(int var1) {
      if (this.H == null && this.isVisible()) {
         this.H = new WorldEntity();
         this.H.loadSprite(259, false);
         this.H.setAnimation((byte)7, (byte)-1, true);
         this.H.setPosition(this.posX, this.posY - var1);
         this.H.ownerEntity = this;
      }

      NpcEntity var2 = this;
      if (this.H != null) {
         var2.H.activate();
      }
   }

   public final void v() {
      if (WorldManager.a().b(this.I)) {
         this.w();
      }
   }

   private void z() {
      if (this.G != null) {
         this.G.activate();
      }
   }

   public final void deactivate() {
      super.deactivate();
      this.w();
      this.deactivateTarget();
      this.x();
   }

   public final void w() {
      if (this.G != null) {
         this.G.deactivate();
      }
   }

   public final void x() {
      if (this.H != null) {
         this.H.deactivate();
      }
   }

   public final void setPosition(int var1, int var2) {
      super.setPosition(var1, var2);
      if (this.targetEntity != null && this.targetEntity.isVisible()) {
         this.targetEntity.setPosition(var1, var2);
      }
   }
}
