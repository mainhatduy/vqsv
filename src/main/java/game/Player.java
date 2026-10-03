package game;

import java.util.Vector;

public final class Player extends WorldEntity {
   private static Player instance;
   public int t;
   private int Z;
   private int aa;
   public byte u;
   private int ab;
   private boolean ac;
   public int v;
   private int ad;
   private int ae;
   public int w;
   public int x;
   public boolean y;
   public Pet[] petParty;
   public int partyPetCount;
   public byte[][] B;
   public byte[][] collectionFlags;
   public byte[][] collectionOrder;
   public byte[] E;
   public byte F;
   public byte G;
   public byte H;
   public byte I;
   public Vector J;
   public Vector K;
   public Vector L;
   public Vector M;
   public Vector N;
   public Vector O;
   public byte[] P;
   public byte[] Q;
   public short[] quickItemSlots;
   public Vector S;
   public boolean[] T;
   public static boolean U;
   public Vector V;
   private int gold;
   private int arenaPoints;
   public int[] elementStartIds = new int[]{0, 16, 32, 48, 64, 76, 88};
   public int[] elementSpeciesCounts = new int[]{16, 16, 16, 16, 12, 12, 12};
   private static byte[][] ah = new byte[][]{{9, 2, 9, 3, 0}};
   private static short[][] ai = new short[][]{{0, 3, 112, 256}};
   private Vector aj = null;

   public static Player getInstance() {
      if (instance == null) {
         instance = new Player();
      }

      return instance;
   }

   public final void releaseParty() {
      this.partyPetCount = 0;
      instance = null;
   }

   public Player() {
      this.baseStats = new short[3];
      this.currentStats = new short[3];
      this.petParty = new Pet[6];
      this.B = new byte[8][2];
      this.T = new boolean[21];
      this.J = new Vector();
      this.K = new Vector();
      int[] var1 = new int[]{0, 0, 1};
      this.K.addElement(var1);
      this.L = new Vector();
      this.M = new Vector();
      this.N = new Vector();
      this.O = new Vector();
      this.V = new Vector();
      this.collectionFlags = new byte[7][];
      this.E = new byte[7];
      this.collectionOrder = new byte[7][];
      this.quickItemSlots = new short[]{-1, -1, -1, -1, -1};

      for (int var3 = 0; var3 < this.collectionOrder.length; var3++) {
         this.collectionOrder[var3] = new byte[this.elementSpeciesCounts[var3]];

         for (int var2 = 0; var2 < this.collectionOrder[var3].length; var2++) {
            this.collectionOrder[var3][var2] = -1;
         }
      }

      this.collectionFlags[0] = new byte[16];
      this.collectionFlags[1] = new byte[16];
      this.collectionFlags[2] = new byte[16];
      this.collectionFlags[3] = new byte[16];
      this.collectionFlags[4] = new byte[12];
      this.collectionFlags[5] = new byte[12];
      this.collectionFlags[6] = new byte[12];
      this.P = new byte[4];
      this.Q = new byte[4];
      this.u = 0;

      for (int var4 = 0; var4 < 8; var4++) {
         this.B[var4][0] = 0;
         this.B[var4][1] = 0;
      }

      for (int var5 = 0; var5 < 4; var5++) {
         this.P[var5] = 0;
      }

      this.gold = 1000;
      this.arenaPoints = 0;
      this.t = -1;
      this.y = false;
   }

   public final void a(short[] var1) {
      if (this.t == -1) {
         this.loadSprite(0, false);
      }

      this.h(this.t);
      this.posX = var1[0];
      this.posY = var1[1];
      this.b((byte)0, (byte)var1[2]);
      short var2 = var1[3];
      super.baseStats[0] = var2;
      var2 = var1[4];
      super.baseStats[1] = var2;
      var2 = var1[5];
      super.baseStats[2] = var2;
      if (this.t == -1) {
         this.resetCurrentStats();
      }

      this.s = 1;
      this.ad = var1[6];
      this.ae = var1[7];
      this.v = this.C();
      if (this.u == 1) {
         this.replaceSpriteImage(0, 107);
      }

      if (this.targetEntity == null) {
         this.targetEntity = new WorldEntity();
         this.targetEntity.loadSprite(337, false);
      }

      this.targetEntity.setPosition(this.posX, this.posY);
      if (this.spriteRenderer.spriteId == 4) {
         this.targetEntity.setAnimation((byte)0, (byte)0, false);
      } else {
         this.targetEntity.setAnimation((byte)1, (byte)0, false);
      }

      this.targetEntity.activate();
      super.ownerEntity = null;
      this.y = true;
   }

   public final void q() {
      if (this.isFollowing() && this.ownerEntity.getActionState() != 0) {
         this.updateFollowing(((WorldEntity)this.ownerEntity).spriteRenderer, this.spriteRenderer);
      } else {
         switch (this.actionState) {
            case 0:
               if (this.P[2] != 2 && this.N()) {
                  this.b((byte)3, this.facingDirection);
                  return;
               }
               break;
            case 1:
               if (this.P[2] != 2) {
                  WorldManager.u = this.K();
                  if (this.L() && this.M()) {
                     this.moveInFacingDirection(this.Z);
                     if (this.aa < 8) {
                        this.aa = this.aa + this.Z;
                     } else {
                        this.aa = 4;
                     }

                     this.O();
                     return;
                  }

                  this.aa = 0;
                  return;
               }

               int var22 = this.posY;
               int var13 = this.posX;
               Player var7 = this;
               boolean var10000;
               switch (this.facingDirection) {
                  case 0:
                     var10000 = MapEngine.getInstance().isOutOfBounds(var13, var22 - 25 + var7.Z);
                     break;
                  case 1:
                     var10000 = MapEngine.getInstance().isOutOfBounds(var13 + var7.Z, var22 - 25);
                     break;
                  case 2:
                     var10000 = MapEngine.getInstance().isOutOfBounds(var13, var22 - 25 - var7.Z);
                     break;
                  case 3:
                     var10000 = MapEngine.getInstance().isOutOfBounds(var13 - var7.Z, var22 - 25);
                     break;
                  default:
                     var10000 = false;
               }

               if (!var10000) {
                  WorldManager.u = this.K();
                  super.ownerEntity = null;

                  for (int var8 = 0; var8 < WorldManager.a().d.length; var8++) {
                     this.w(var8);
                  }

                  this.moveInFacingDirection(super.currentStats[0]);
                  if (this.aa < 8) {
                     this.aa = this.aa + super.currentStats[0];
                  } else {
                     this.aa = 4;
                  }

                  this.O();
                  return;
               }
               break;
            case 2:
               if (this.L() && this.M()) {
                  this.moveInFacingDirection(this.Z);
                  if (this.aa < 8) {
                     this.aa = this.aa + this.Z;
                  } else {
                     this.aa = 4;
                  }

                  this.O();
                  return;
               }

               this.aa = 0;
               return;
            case 3:
            default:
               break;
            case 4:
               return;
            case 5:
               if (this.ab < 16) {
                  switch (this.m) {
                     case 0:
                     case 2:
                        if (this.m == 2) {
                           if (this.posY > this.ownerEntity.posY - 16) {
                              this.b(this.m);
                           }
                        } else if (this.posY < this.ownerEntity.posY - 16) {
                           this.b(this.m);
                        }

                        if (this.k > this.ownerEntity.posX) {
                           if (this.posX <= this.ownerEntity.posX) {
                              int var19 = this.ownerEntity.posX;
                              super.posX = this.ownerEntity.posX;
                           } else {
                              this.b(3);
                           }
                        } else if (this.k < this.ownerEntity.posX) {
                           if (this.posX >= this.ownerEntity.posX) {
                              int var20 = this.ownerEntity.posX;
                              super.posX = this.ownerEntity.posX;
                           } else {
                              this.b(1);
                           }
                        }
                        break;
                     case 1:
                     case 3:
                        if (this.m == 3) {
                           if (this.posX > this.ownerEntity.posX) {
                              this.b(this.m);
                           }
                        } else if (this.posX < this.ownerEntity.posX) {
                           this.b(this.m);
                        }

                        if (this.l > this.ownerEntity.posY - 16) {
                           if (this.posY <= this.ownerEntity.posY - 16) {
                              int var17;
                              super.posY = var17 = this.ownerEntity.posY - 16;
                           } else {
                              this.b(2);
                           }
                        } else if (this.l < this.ownerEntity.posY - 16) {
                           if (this.posY >= this.ownerEntity.posY - 16) {
                              int var18;
                              super.posY = var18 = this.ownerEntity.posY - 16;
                           } else {
                              this.b(0);
                           }
                        }
                  }

                  if (this.ab % 4 == 3) {
                     this.setAnimation((byte)1, (byte)-1, false);
                  } else {
                     this.setAnimation((byte)(this.ab % 4), (byte)-1, false);
                  }

                  byte var21;
                  super.facingDirection = var21 = (byte)(this.ab % 4);
                  this.ab++;
                  return;
               }

               byte var6 = 0;

               for (int var11 = 0; var11 < ah.length; var11++) {
                  byte[] var10001 = ah[var11];
                  if (WorldManager.a().f == var10001[0]) {
                     var10001 = ah[var11];
                     if (WorldManager.a().g == var10001[1]) {
                        WorldManager.a().f = ah[var11][2];
                        WorldManager.a().g = ah[var11][3];
                        var6 = ah[var11][4];
                        break;
                     }
                  }
               }

               for (int var12 = 0; var12 < ai[var6].length / 4; var12++) {
                  if (((NpcEntity)this.ownerEntity).I >= ai[var6][var12 << 2] && ((NpcEntity)this.ownerEntity).I <= ai[var6][(var12 << 2) + 1]) {
                     WorldManager.a().h = ai[var6][(var12 << 2) + 2];
                     WorldManager.a().i = ai[var6][(var12 << 2) + 3];
                     break;
                  }
               }

               U = true;
               WorldManager.a().j = -1;
               GameStateController.getInstance().setScreenMode((byte)9);
               return;
            case 6:
               Player var5 = this;
               boolean var10 = true;
               switch (var5.facingDirection) {
                  case 0:
                     var10 = var5.d(MapEngine.getInstance().getCollisionTile(var5.posX, var5.posY + 16));
                     break;
                  case 1:
                     var10 = var5.d(MapEngine.getInstance().getCollisionTile(var5.posX + 16, var5.posY));
                     break;
                  case 2:
                     var10 = var5.d(MapEngine.getInstance().getCollisionTile(var5.posX, var5.posY - 16));
                     break;
                  case 3:
                     var10 = var5.d(MapEngine.getInstance().getCollisionTile(var5.posX - 16, var5.posY));
               }

               if (var10 && MapEngine.getInstance().getCollisionTile(var5.posX, var5.posY) != 3 ? false : var10) {
                  this.moveInFacingDirection(super.baseStats[2]);
                  return;
               }

               this.moveInFacingDirection(super.currentStats[1]);
               this.b((byte)0, this.facingDirection);
               return;
            case 7:
               if (((NpcEntity)this.ownerEntity).z == 0) {
                  if (this.ab < 7) {
                     this.moveInFacingDirection(4);
                     this.ab++;
                     return;
                  }

                  if (this.ab == 7) {
                     if (this.facingDirection == 3) {
                        this.setAnimation((byte)1, (byte)-1, false);
                     } else {
                        this.setAnimation(this.facingDirection, (byte)-1, false);
                     }

                     byte var16 = this.facingDirection;
                     super.facingDirection = this.facingDirection;
                     ((NpcEntity)this.ownerEntity).p();
                     this.ab++;
                     return;
                  }
               } else if (((NpcEntity)this.ownerEntity).z == 2) {
                  if (this.ab < 8 && this.ab > 0) {
                     this.moveInFacingDirection(4);
                     this.ab--;
                     return;
                  }

                  if (this.ab == 8) {
                     this.b((byte)7, this.facingDirection);
                     this.ab--;
                     return;
                  }

                  ((NpcEntity)this.ownerEntity).z = 0;
                  ((NpcEntity)this.ownerEntity).ownerEntity = null;
                  super.ownerEntity = null;
                  this.b((byte)0, this.facingDirection);
                  return;
               }
               break;
            case 8:
               if (this.ab < 16) {
                  if (this.ab % 4 == 3) {
                     this.setAnimation((byte)1, (byte)-1, false);
                  } else {
                     this.setAnimation((byte)(this.ab % 4), (byte)-1, false);
                  }

                  byte var15;
                  super.facingDirection = var15 = (byte)(this.ab % 4);
                  this.ab++;
                  return;
               }

               int var4 = WorldManager.a().d[WorldManager.a().j].posX - WorldManager.a().d[WorldManager.a().j].posX % super.baseStats[2];
               int var9 = WorldManager.a().d[WorldManager.a().j].posY - WorldManager.a().d[WorldManager.a().j].posY % super.baseStats[2];
               this.setPosition(var4, var9);
               this.targetEntity.setPosition(var4, var9);
               this.b((byte)0, WorldManager.a().d[WorldManager.a().j].C);
               this.moveInFacingDirection(32);
               ParticleEffect.a().a(8);
               ParticleEffect.a().b();
               return;
            case 9:
               if (this.ab < 16) {
                  if (this.ab % 4 == 3) {
                     this.setAnimation((byte)1, (byte)-1, false);
                  } else {
                     this.setAnimation((byte)(this.ab % 4), (byte)-1, false);
                  }

                  byte var14;
                  super.facingDirection = var14 = (byte)(this.ab % 4);
                  this.ab++;
                  return;
               }

               int var1 = WorldManager.a().d[WorldManager.a().j].posX - WorldManager.a().d[WorldManager.a().j].posX % super.baseStats[2];
               int var2 = WorldManager.a().d[WorldManager.a().j].posY - WorldManager.a().d[WorldManager.a().j].posY % super.baseStats[2];
               this.setPosition(var1, var2);
               this.targetEntity.setPosition(var1, var2);
               this.b((byte)10, this.facingDirection);
               ParticleEffect.a().a(8);
               ParticleEffect.a().b();
               return;
            case 10:
               if (this.ab > 0) {
                  if (this.ab % 4 == 3) {
                     this.setAnimation((byte)1, (byte)-1, false);
                  } else {
                     this.setAnimation((byte)(this.ab % 4), (byte)-1, false);
                  }

                  byte var3;
                  super.facingDirection = var3 = (byte)(this.ab % 4);
                  this.ab--;
                  return;
               }

               this.b((byte)0, WorldManager.a().d[WorldManager.a().j].C);
               this.moveInFacingDirection(32);
         }
      }
   }

   public final void b(byte var1, byte var2) {
      while (true) {
         switch (var1) {
            case 0:
               if (this.P[2] != 2 && this.N()) {
                  var1 = 3;
                           continue;
               }

               if (var2 == 3) {
                  this.setAnimation((byte)1, (byte)-1, false);
               } else {
                  this.setAnimation(var2, (byte)-1, false);
               }

               super.facingDirection = var2;
               break;
            case 1:
               if (this.P[2] != 2 && this.N()) {
                  var1 = 2;
                           continue;
               }

               if (super.spriteRenderer.getAnimationId() < 6) {
                  if (var2 == 3) {
                     this.setAnimationAndDirection((byte)4, var2);
                  } else {
                     this.setAnimationAndDirection((byte)(var2 + 3), var2);
                  }
               } else {
                  if (var2 == 3) {
                     this.setAnimation((byte)(var1 * 3 + 1), (byte)-1, false);
                  } else {
                     this.setAnimation((byte)(var1 * 3 + var2), (byte)-1, false);
                  }

                  super.facingDirection = var2;
               }
               break;
            case 2:
               if (this.N()) {
                  if (super.spriteRenderer.getAnimationId() < 9) {
                     if (var2 == 3) {
                        this.setAnimationAndDirection((byte)7, var2);
                     } else {
                        this.setAnimationAndDirection((byte)(var2 + 6), var2);
                     }
                  } else if (var2 == 3) {
                     this.setAnimation((byte)(var1 * 3 + 1), (byte)-1, false);
                  } else {
                     this.setAnimation((byte)(var1 * 3 + var2), (byte)-1, false);
                  }
               } else if (var2 == 3) {
                  this.setAnimation((byte)(this.actionState * 3 + 1), (byte)-1, false);
               } else {
                  this.setAnimation((byte)(this.actionState * 3 + var2), (byte)-1, false);
               }

               super.facingDirection = var2;
               break;
            case 3:
               if (var2 == 3) {
                  this.setAnimation((byte)(var1 * 3 + 1), (byte)-1, false);
               } else {
                  this.setAnimation((byte)(var1 * 3 + var2), (byte)-1, false);
               }

               super.facingDirection = var2;
               break;
            case 4:
               this.setAnimation((byte)(var1 * 3), (byte)-2, false);
               super.facingDirection = var2;
               this.aa = 0;
               break;
            case 5:
               this.ab = 0;
               super.m = var2;
               int var5 = this.posY;
               int var4 = this.posX;
               Player var3 = this;
               super.k = var4;
               var3.l = var5;
               if (var2 == 3) {
                  this.setAnimation((byte)1, (byte)-1, false);
               } else {
                  this.setAnimation(var2, (byte)-1, false);
               }

               super.facingDirection = var2;
               break;
            case 6:
               if (var2 == 3) {
                  this.setAnimation((byte)1, (byte)-1, false);
               } else {
                  this.setAnimation(var2, (byte)-1, false);
               }

               super.facingDirection = var2;
               break;
            case 7:
               if (var2 == 3) {
                  this.setAnimation((byte)4, (byte)-1, false);
               } else {
                  this.setAnimation((byte)(var2 + 3), (byte)-1, false);
               }

               super.facingDirection = var2;
               break;
            case 8:
            case 9:
               this.ab = 0;
               super.facingDirection = var2;
         }

         this.actionState = var1;
         if (this.actionState != 0 && this.actionState != 1) {
            if (WorldManager.a().o != null) {
               WorldManager.a().o.a(false);
            }
         } else if (WorldManager.a().o != null) {
            WorldManager.a().o.a(true);
            return;
         }

         return;
      }
   }

   public final boolean f(int var1) {
      return this.P[var1] != 0;
   }

   public final boolean g(int var1) {
      return this.Q[var1] != 1;
   }

   public final boolean r() {
      return this.t != 2
         || MapEngine.getInstance().getCollisionTile(this.posX + 7, this.posY + 7) == 0
            && MapEngine.getInstance().getCollisionTile(this.posX - 8, this.posY - 8) == 0;
   }

   public final void h(int var1) {
      if (var1 != -1) {
         this.P[var1] = 2;
         this.spriteRenderer.evictSprite();
         this.loadSprite(var1 + 1, false);
         if (this.u == 1) {
            this.replaceSpriteImage(1, 107);
         }

         this.b((byte)0, this.facingDirection);
         if ((this.P[var1] != 2 || var1 != 0) && (this.P[var1] != 2 || var1 != 1)) {
            super.currentStats[0] = 4;
         } else {
            super.currentStats[0] = 8;
         }

         if (this.P[2] == 2 && WorldManager.a().o != null) {
            WorldManager.a().o.deactivate();
         }

         this.Z = super.currentStats[0];
         this.t = var1;
      }
   }

   public final void s() {
      this.spriteRenderer.evictSprite();
      this.loadSprite(0, false);

      for (int var1 = 0; var1 < 4; var1++) {
         if (this.P[var1] == 2) {
            this.P[var1] = 1;
         }
      }

      if (this.u == 1) {
         this.replaceSpriteImage(0, 107);
      }

      if (WorldManager.a().o != null) {
         WorldManager.a().o.activate();
      }

      short var2 = super.baseStats[0];
      super.currentStats[0] = var2;
      this.t = -1;
   }

   public final void t() {
      this.u = 1;
      boolean var1 = false;

      for (int var2 = 0; var2 < 4; var2++) {
         if (this.P[var2] == 2) {
            var1 = true;
            break;
         }
      }

      if (var1) {
         this.replaceSpriteImage(1, 107);
      } else {
         this.replaceSpriteImage(0, 107);
      }
   }

   public final void u() {
      this.u = 0;
      boolean var1 = false;

      for (int var2 = 0; var2 < 4; var2++) {
         if (this.P[var2] == 2) {
            var1 = true;
            break;
         }
      }

      if (var1) {
         this.replaceSpriteImage(1, 100);
      } else {
         this.replaceSpriteImage(0, 100);
      }
   }

   private short K() {
      for (int var1 = 0; var1 < WorldManager.a().d.length; var1++) {
         if (WorldManager.a().d[var1].isVisible()
            && (
               WorldManager.a().d[var1].spriteRenderer.spriteId <= 85
                  || WorldManager.a().d[var1].spriteRenderer.spriteId == 226
                  || WorldManager.a().d[var1].spriteRenderer.spriteId == 92
                  || WorldManager.a().d[var1].spriteRenderer.spriteId == 102
                  || WorldManager.a().d[var1].spriteRenderer.spriteId == 137
            )
            && WorldManager.a().d[var1].t == 0
            && (WorldManager.a().d[var1].v == 1 || WorldManager.a().d[var1].v == 18)
            && this.a(WorldManager.a().d[var1], this.spriteRenderer.k(), WorldManager.a().d[var1].spriteRenderer.k())) {
            if (WorldManager.a().d[var1].u() == 0) {
               WorldManager.a().a(WorldManager.a().d[var1].posX, WorldManager.a().d[var1].posY - 40, WorldManager.a().d[var1]);
               if (WorldManager.a().d[var1].G != null) {
                  WorldManager.a().d[var1].G.deactivate();
               }
            } else if (WorldManager.a().d[var1].u() == 1) {
               WorldManager.a().a(WorldManager.a().d[var1].posX, WorldManager.a().d[var1].posY - 40, WorldManager.a().d[var1]);
               if (OverworldScreen.d != null && OverworldScreen.d.size() > 0) {
                  for (int var2 = 0; var2 < OverworldScreen.d.size(); var2++) {
                     if (((WorldEntity)OverworldScreen.d.elementAt(var2)).ownerEntity.equals(WorldManager.a().d[var1])) {
                        ((WorldEntity)OverworldScreen.d.elementAt(var2)).deactivate();
                        break;
                     }
                  }
               }
            } else {
               WorldManager.a().a(WorldManager.a().d[var1].posX, WorldManager.a().d[var1].posY - 40, WorldManager.a().d[var1]);
               if (WorldManager.a().d[var1].w != 0) {
                  WorldManager.a().d[var1].w();
               }
            }

            return (short)var1;
         }
      }

      WorldManager.a().e();
      OverworldScreen.g = false;
      return -1;
   }

   private boolean w(int var1) {
      switch (WorldManager.a().d[var1].t) {
         case 3:
            short[] var2;
            short var3 = (var2 = WorldManager.a().d[var1].spriteRenderer.k())[0];
            short var4 = var2[1];
            short var5 = (short)(var2[2] + 16);
            short var6 = (short)(var2[3] + 16);
            if (WorldManager.a().d[var1].u && this.a(WorldManager.a().d[var1], this.spriteRenderer.k(), new short[]{var3, var4, var5, var6})) {
               super.ownerEntity = WorldManager.a().d[var1];
            }
         default:
            return true;
      }
   }

   private boolean L() {
      Object var3 = null;
      super.ownerEntity = null;
      boolean var1 = true;
      if (this.aj != null) {
         this.aj.removeAllElements();
      }

      for (int var2 = 0; var2 < WorldManager.a().d.length; var2++) {
         this.w(var2);
         if (WorldManager.a().d[var2].u && this.a(WorldManager.a().d[var2], this.spriteRenderer.k(), WorldManager.a().d[var2].spriteRenderer.k())) {
            switch (WorldManager.a().d[var2].t) {
               case 0:
                  switch (WorldManager.a().d[var2].v) {
                     case 0:
                        return false;
                     case 1:
                     case 2:
                     case 3:
                     case 12:
                     case 13:
                     default:
                        continue;
                     case 4:
                     case 11:
                        if (WorldManager.a().d[var2].getActionState() != 2 && WorldManager.a().d[var2].isVisible()) {
                           super.ownerEntity = WorldManager.a().d[var2];
                           return false;
                        }
                        continue;
                     case 5:
                        if (WorldManager.a().d[var2].getActionState() != 2) {
                           if (this.B[5][0] == 2) {
                              super.ownerEntity = WorldManager.a().d[var2];
                           }

                           return false;
                        }
                     case 6:
                        if (WorldManager.a().d[var2].getActionState() != 2) {
                           if (this.P[3] != 2) {
                              if (this.B[2][0] == 2) {
                                 super.ownerEntity = WorldManager.a().d[var2];
                                 if (this.aj == null) {
                                    this.aj = new Vector();
                                 }

                                 this.aj.addElement(WorldManager.a().d[var2]);
                                 WorldManager.a().d[var2].f(20);
                              }

                              var1 = false;
                           } else {
                              WorldManager.a().d[var2].d((byte)1);
                              WorldManager.a().b.c(WorldManager.a().d[var2]);
                           }
                        }
                        continue;
                     case 7:
                        if (WorldManager.a().d[var2].getActionState() != 2) {
                           if (this.P[3] != 2) {
                              if (this.B[1][0] == 2) {
                                 super.ownerEntity = WorldManager.a().d[var2];
                                 if (this.aj == null) {
                                    this.aj = new Vector();
                                 }

                                 this.aj.addElement(WorldManager.a().d[var2]);
                                 WorldManager.a().d[var2].f(30);
                              }

                              var1 = false;
                           } else {
                              WorldManager.a().d[var2].d((byte)1);
                              WorldManager.a().b.c(WorldManager.a().d[var2]);
                           }
                        }
                        continue;
                     case 8:
                        if (WorldManager.a().d[var2].isVisible()) {
                           if ((NpcEntity)WorldManager.a().d[var2].ownerEntity != null
                              && ((NpcEntity)WorldManager.a().d[var2].ownerEntity).B > ((NpcEntity)WorldManager.a().d[var2].ownerEntity).A) {
                              return false;
                           }

                           for (int var4 = 0; var4 < WorldManager.a().d.length; var4++) {
                              if (WorldManager.a().d[var4].u
                                 && !WorldManager.a().d[var4].equals(WorldManager.a().d[var2])
                                 && WorldManager.a().d[var4].t == 0
                                 && (WorldManager.a().d[var4].v == 8 || WorldManager.a().d[var4].v == 11)) {
                                 switch (this.facingDirection) {
                                    case 0:
                                       if (EngineUtils.a(
                                          WorldManager.a().d[var2].posX,
                                          WorldManager.a().d[var2].posY + 8,
                                          WorldManager.a().d[var4].posX,
                                          WorldManager.a().d[var4].posY,
                                          WorldManager.a().d[var2].spriteRenderer.k(),
                                          WorldManager.a().d[var4].spriteRenderer.k()
                                       )) {
                                          return false;
                                       }
                                       break;
                                    case 1:
                                       if (EngineUtils.a(
                                          WorldManager.a().d[var2].posX + 8,
                                          WorldManager.a().d[var2].posY,
                                          WorldManager.a().d[var4].posX,
                                          WorldManager.a().d[var4].posY,
                                          WorldManager.a().d[var2].spriteRenderer.k(),
                                          WorldManager.a().d[var4].spriteRenderer.k()
                                       )) {
                                          return false;
                                       }
                                       break;
                                    case 2:
                                       if (EngineUtils.a(
                                          WorldManager.a().d[var2].posX,
                                          WorldManager.a().d[var2].posY - 8,
                                          WorldManager.a().d[var4].posX,
                                          WorldManager.a().d[var4].posY,
                                          WorldManager.a().d[var2].spriteRenderer.k(),
                                          WorldManager.a().d[var4].spriteRenderer.k()
                                       )) {
                                          return false;
                                       }
                                       break;
                                    case 3:
                                       if (EngineUtils.a(
                                          WorldManager.a().d[var2].posX - 8,
                                          WorldManager.a().d[var2].posY,
                                          WorldManager.a().d[var4].posX,
                                          WorldManager.a().d[var4].posY,
                                          WorldManager.a().d[var2].spriteRenderer.k(),
                                          WorldManager.a().d[var4].spriteRenderer.k()
                                       )) {
                                          return false;
                                       }
                                 }
                              }
                           }

                           WorldManager.a().d[var2].d((byte)1);
                           NpcEntity var10000 = WorldManager.a().d[var2];
                           var3 = this.facingDirection;
                           var10000.facingDirection = this.facingDirection;
                           return false;
                        }
                        continue;
                     case 9:
                        if (MapEngine.getInstance().getCollisionTile(this.posX, this.posY) == 2
                           || MapEngine.getInstance().getCollisionTile(this.posX, this.posY) == 1) {
                           return false;
                        }

                        if (this.facingDirection == 3 || this.facingDirection == 1) {
                           this.ab = 0;
                           this.b((byte)7, this.facingDirection);
                           WorldManager.a().d[var2].ownerEntity = this;
                           super.ownerEntity = WorldManager.a().d[var2];
                           return false;
                        }
                        continue;
                     case 10:
                        if (MapEngine.getInstance().getCollisionTile(this.posX, this.posY) == 2
                           || MapEngine.getInstance().getCollisionTile(this.posX, this.posY) == 1) {
                           return false;
                        }

                        if (this.facingDirection == 0 || this.facingDirection == 2) {
                           this.ab = 0;
                           this.b((byte)7, this.facingDirection);
                           WorldManager.a().d[var2].ownerEntity = this;
                           super.ownerEntity = WorldManager.a().d[var2];
                           return false;
                        }
                        continue;
                     case 14:
                        return false;
                     case 15:
                        if (WorldManager.a().d[var2].getActionState() != 2) {
                           if (this.T[6]) {
                              super.ownerEntity = WorldManager.a().d[var2];
                              return false;
                           }

                           WorldManager.a().d[var2].d((byte)1);
                           WorldManager.a().b.c(WorldManager.a().d[var2]);
                        }
                        continue;
                     case 16:
                        super.ownerEntity = WorldManager.a().d[var2];
                        continue;
                  }
               case 1:
                  if (WorldManager.a().d[var2].v == 3) {
                     return false;
                  }
               case 2:
               default:
                  break;
               case 3:
                  return false;
            }
         }
      }

      return var1;
   }

   private boolean M() {
      this.Z = super.currentStats[0];
      int var1 = this.posX - 8;
      int var2 = this.posY - 8;
      int var3 = this.posX + 7;
      int var4 = this.posY + 7;
      byte[] var5 = new byte[]{-1, -1, -1, -1, -1};
      switch (this.facingDirection) {
         case 2:
            if (MapEngine.getInstance().isOutOfBounds(this.posX, var2 - this.Z)) {
               return false;
            }

            var5[0] = MapEngine.getInstance().getCollisionTile(var1, var2 - this.Z);
            var5[1] = MapEngine.getInstance().getCollisionTile(var3, var2 - this.Z);
            var5[2] = MapEngine.getInstance().getCollisionTile(this.posX, var2 - this.Z);
            if (this.d(var5[0]) || this.d(var5[1])) {
               if (!this.d(var5[0])) {
                  this.Z = super.currentStats[1];
                  return this.d(var5[1], (byte)1);
               }

               if (!this.d(var5[1])) {
                  this.Z = super.currentStats[1];
                  return this.d(var5[0], (byte)3);
               }

               return this.d(var5[2], (byte)2);
            }

            if (MapEngine.getInstance().getCollisionTile(this.posX, this.posY) == 3) {
               return false;
            }

            var5[0] = MapEngine.getInstance().getCollisionTile(this.posX - 16, var2 - this.Z);
            var5[1] = MapEngine.getInstance().getCollisionTile(this.posX + 16, var2 - this.Z);
            var5[3] = MapEngine.getInstance().getCollisionTile(this.posX - 16, this.posY);
            var5[4] = MapEngine.getInstance().getCollisionTile(this.posX + 16, this.posY);
            this.Z = super.currentStats[1];
            if (!this.d(var5[0])) {
               return this.d(var5[1], (byte)1);
            }

            if (!this.d(var5[1])) {
               return this.d(var5[0], (byte)3);
            }

            if (this.d(var5[4])) {
               return this.d(var5[1], (byte)1);
            }

            if (this.d(var5[3])) {
               return this.d(var5[0], (byte)3);
            }
         case 0:
            if (MapEngine.getInstance().isOutOfBounds(this.posX, var4 + this.Z)) {
               return false;
            }

            var5[0] = MapEngine.getInstance().getCollisionTile(var1, var4 + this.Z);
            var5[1] = MapEngine.getInstance().getCollisionTile(var3, var4 + this.Z);
            var5[2] = MapEngine.getInstance().getCollisionTile(this.posX, var4 + this.Z);
            if (this.d(var5[0]) || this.d(var5[1])) {
               if (!this.d(var5[0])) {
                  this.Z = super.currentStats[1];
                  return this.d(var5[1], (byte)1);
               }

               if (!this.d(var5[1])) {
                  this.Z = super.currentStats[1];
                  return this.d(var5[0], (byte)3);
               }

               return this.d(var5[2], (byte)0);
            }

            if (MapEngine.getInstance().getCollisionTile(this.posX, this.posY) == 3) {
               return false;
            }

            var5[0] = MapEngine.getInstance().getCollisionTile(var1 - 16, var4 + this.Z);
            var5[1] = MapEngine.getInstance().getCollisionTile(var3 + 16, var4 + this.Z);
            var5[3] = MapEngine.getInstance().getCollisionTile(this.posX - 16, this.posY);
            var5[4] = MapEngine.getInstance().getCollisionTile(this.posX + 16, this.posY);
            this.Z = super.currentStats[1];
            if (!this.d(var5[0])) {
               return this.d(var5[1], (byte)1);
            }

            if (!this.d(var5[1])) {
               return this.d(var5[0], (byte)3);
            }

            if (this.d(var5[4])) {
               return this.d(var5[1], (byte)1);
            }

            if (this.d(var5[3])) {
               return this.d(var5[0], (byte)3);
            }
         case 3:
            if (MapEngine.getInstance().isOutOfBounds(var1 - this.Z, this.posY)) {
               return false;
            }

            var5[0] = MapEngine.getInstance().getCollisionTile(var1 - this.Z, var2);
            var5[1] = MapEngine.getInstance().getCollisionTile(var1 - this.Z, var4);
            var5[2] = MapEngine.getInstance().getCollisionTile(var1 - this.Z, this.posY);
            if (this.d(var5[0]) || this.d(var5[1])) {
               if (!this.d(var5[0])) {
                  this.Z = super.currentStats[1];
                  return this.d(var5[1], (byte)0);
               }

               if (!this.d(var5[1])) {
                  this.Z = super.currentStats[1];
                  return this.d(var5[0], (byte)2);
               }

               return this.d(var5[2], (byte)3);
            }

            if (MapEngine.getInstance().getCollisionTile(this.posX, this.posY) == 3) {
               return false;
            }

            var5[0] = MapEngine.getInstance().getCollisionTile(var1 - this.Z, var2 - 16);
            var5[1] = MapEngine.getInstance().getCollisionTile(var1 - this.Z, var4 + 16);
            var5[3] = MapEngine.getInstance().getCollisionTile(this.posX, this.posY - 16);
            var5[4] = MapEngine.getInstance().getCollisionTile(this.posX, this.posY + 16);
            this.Z = super.currentStats[1];
            if (!this.d(var5[0])) {
               return this.d(var5[1], (byte)0);
            }

            if (!this.d(var5[1])) {
               return this.d(var5[0], (byte)2);
            }

            if (this.d(var5[4])) {
               return this.d(var5[1], (byte)0);
            }

            if (this.d(var5[3])) {
               return this.d(var5[0], (byte)2);
            }
         case 1:
            if (MapEngine.getInstance().isOutOfBounds(var3 + this.Z, this.posY)) {
               return false;
            }

            var5[0] = MapEngine.getInstance().getCollisionTile(var3 + this.Z, var2);
            var5[1] = MapEngine.getInstance().getCollisionTile(var3 + this.Z, var4);
            var5[2] = MapEngine.getInstance().getCollisionTile(var3 + this.Z, this.posY);
            if (this.d(var5[0]) || this.d(var5[1])) {
               if (!this.d(var5[0])) {
                  this.Z = super.currentStats[1];
                  return this.d(var5[1], (byte)0);
               }

               if (!this.d(var5[1])) {
                  this.Z = super.currentStats[1];
                  return this.d(var5[0], (byte)2);
               }

               return this.d(var5[2], (byte)1);
            }

            if (MapEngine.getInstance().getCollisionTile(this.posX, this.posY) == 3) {
               return false;
            }

            var5[0] = MapEngine.getInstance().getCollisionTile(var3 + this.Z, var2 - 16);
            var5[1] = MapEngine.getInstance().getCollisionTile(var3 + this.Z, var4 + 16);
            var5[3] = MapEngine.getInstance().getCollisionTile(this.posX, this.posY - 16);
            var5[4] = MapEngine.getInstance().getCollisionTile(this.posX, this.posY + 16);
            this.Z = super.currentStats[1];
            if (!this.d(var5[0])) {
               return this.d(var5[1], (byte)0);
            }

            if (!this.d(var5[1])) {
               return this.d(var5[0], (byte)2);
            }

            if (this.d(var5[4])) {
               return this.d(var5[1], (byte)0);
            }

            if (this.d(var5[3])) {
               return this.d(var5[0], (byte)2);
            }
      }

      return true;
   }

   private boolean d(byte var1) {
      switch (var1) {
         case 1:
            return false;
         case 2:
            if (this.B[3][0] != 2) {
               return false;
            }

            return true;
         default:
            return true;
      }
   }

   public final boolean a(NpcEntity var1, short[] var2, short[] var3) {
      if (var3 == null) {
         return false;
      }

      switch (this.facingDirection) {
         case 0:
            if (var1.v == 14) {
               return a(var1, var3, var2, this.posX, this.posY + super.currentStats[0]);
            }

            if (EngineUtils.a(this.posX, this.posY + super.currentStats[0], var1.posX, var1.posY, var2, var3)) {
               return true;
            }
            break;
         case 1:
            if (var1.v == 14) {
               return a(var1, var3, var2, this.posX + super.currentStats[0], this.posY);
            }

            if (EngineUtils.a(this.posX + super.currentStats[0], this.posY, var1.posX, var1.posY, var2, var3)) {
               return true;
            }
            break;
         case 2:
            if (var1.v == 14) {
               return a(var1, var3, var2, this.posX, this.posY - super.currentStats[0]);
            }

            if (EngineUtils.a(this.posX, this.posY - super.currentStats[0], var1.posX, var1.posY, var2, var3)) {
               return true;
            }
            break;
         case 3:
            if (var1.v == 14) {
               return a(var1, var3, var2, this.posX - super.currentStats[0], this.posY);
            }

            if (EngineUtils.a(this.posX - super.currentStats[0], this.posY, var1.posX, var1.posY, var2, var3)) {
               return true;
            }
      }

      return false;
   }

   private static boolean a(NpcEntity var0, short[] var1, short[] var2, int var3, int var4) {
      switch (var0.spriteRenderer.getAnimationId()) {
         case 0:
            if (EngineUtils.a(var0.posX + var1[0], var0.posY + var1[1], var1[2], var1[3] + (var0.A << 4), var3, var4, var2)) {
               return true;
            }
            break;
         case 1:
            if (EngineUtils.a(var0.posX + var1[0], var0.posY + var1[1], var1[2] + (var0.A << 4), var1[3], var3, var4, var2)) {
               return true;
            }
            break;
         case 2:
            if (EngineUtils.a(var0.posX + var1[0], var0.posY + var1[1] - (var0.A << 4), var1[2], var1[3] + (var0.A << 4), var3, var4, var2)) {
               return true;
            }
            break;
         case 3:
            if (EngineUtils.a(var0.posX + var1[0] - (var0.A << 4), var0.posY + var1[1], var1[2] + (var0.A << 4), var1[3], var3, var4, var2)) {
               return true;
            }
      }

      return false;
   }

   private boolean N() {
      boolean var1 = true;
      switch (this.facingDirection) {
         case 0:
            var1 = this.d(MapEngine.getInstance().getCollisionTile(this.posX, this.posY + super.currentStats[0]));
            break;
         case 1:
            var1 = this.d(MapEngine.getInstance().getCollisionTile(this.posX + super.currentStats[0], this.posY));
            break;
         case 2:
            var1 = this.d(MapEngine.getInstance().getCollisionTile(this.posX, this.posY - super.currentStats[0]));
            break;
         case 3:
            var1 = this.d(MapEngine.getInstance().getCollisionTile(this.posX - super.currentStats[0], this.posY));
      }

      return var1 && MapEngine.getInstance().getCollisionTile(this.posX, this.posY) != 2 ? false : var1;
   }

   private boolean d(byte var1, byte var2) {
      switch (var1) {
         case -1:
         case 0:
            this.b((byte)1, var2);
            break;
         case 1:
            if (MapEngine.getInstance().getCollisionTile(this.posX, this.posY) == 2) {
               this.b((byte)2, var2);
            }

            return false;
         case 2:
            if (!this.d((byte)2)) {
               return false;
            }

            this.b((byte)2, var2);
            break;
         case 3:
            this.b((byte)6, var2);
         case 4:
      }

      return true;
   }

   public final void v() {
      if (((NpcEntity)this.ownerEntity).getActionState() == 1) {
         WorldManager.a().interactionController.aw();
         WorldManager.a().interactionController.b("Bảo rương này đã trống");
      } else if (((NpcEntity)this.ownerEntity).v == 0) {
         ((NpcEntity)this.ownerEntity).d((byte)1);
         if (this.a(((NpcEntity)this.ownerEntity).F, ((NpcEntity)this.ownerEntity).D, (byte)((NpcEntity)this.ownerEntity).E)) {
            this.c(((NpcEntity)this.ownerEntity).F, ((NpcEntity)this.ownerEntity).D, (byte)((NpcEntity)this.ownerEntity).E);
            String var2 = null;
            if (((NpcEntity)this.ownerEntity).E == 0) {
               var2 = BaseScreen.getString(GameDatabase.gameDatabase[4][((NpcEntity)this.ownerEntity).F][0]);
            } else if (((NpcEntity)this.ownerEntity).E == 2) {
               var2 = BaseScreen.getString(GameDatabase.gameDatabase[3][((NpcEntity)this.ownerEntity).F][0]);
            }

            WorldManager.a().interactionController.a("Đạt được: " + var2, ((NpcEntity)this.ownerEntity).D);
         } else {
            WorldManager.a().interactionController.av();
         }

         this.b((byte)0, this.facingDirection);
      } else {
         if (((NpcEntity)this.ownerEntity).v == 1) {
            if (this.b(17, 1, (byte)2)) {
               ((NpcEntity)this.ownerEntity).d((byte)1);
               this.d(17, 1, (byte)2);
               if (this.a(((NpcEntity)this.ownerEntity).F, ((NpcEntity)this.ownerEntity).D, (byte)((NpcEntity)this.ownerEntity).E)) {
                  this.c(((NpcEntity)this.ownerEntity).F, ((NpcEntity)this.ownerEntity).D, (byte)((NpcEntity)this.ownerEntity).E);
                  String var1 = null;
                  if (((NpcEntity)this.ownerEntity).E == 0) {
                     var1 = BaseScreen.getString(GameDatabase.gameDatabase[4][((NpcEntity)this.ownerEntity).F][0]);
                  } else if (((NpcEntity)this.ownerEntity).E == 2) {
                     var1 = BaseScreen.getString(GameDatabase.gameDatabase[3][((NpcEntity)this.ownerEntity).F][0]);
                  }

                  WorldManager.a().interactionController.a("Đạt được: " + var1, ((NpcEntity)this.ownerEntity).D);
               } else {
                  WorldManager.a().interactionController.av();
               }
            } else {
               WorldManager.a().interactionController.au();
            }

            this.b((byte)0, this.facingDirection);
         }
      }
   }

   public final boolean w() {
      if (this.ownerEntity == null) {
         return false;
      }

      if (((NpcEntity)this.ownerEntity).getActionState() != 0) {
         return false;
      }

      OverworldScreen.h = true;
      if (((NpcEntity)this.ownerEntity).v == 7 || ((NpcEntity)this.ownerEntity).v == 6) {
         for (int var1 = 0; var1 < this.aj.size(); var1++) {
            NpcEntity var2;
            (var2 = (NpcEntity)this.aj.elementAt(var1)).d((byte)1);
            var2.x();
         }

         this.aj.removeAllElements();
      } else if (((NpcEntity)this.ownerEntity).v != 16) {
         ((NpcEntity)this.ownerEntity).d((byte)1);
      }

      return true;
   }

   private static boolean a(int var0, int var1, Vector var2) {
      for (int var4 = 0; var4 < var2.size(); var4++) {
         int[] var3;
         if ((var3 = (int[])var2.elementAt(var4))[0] == var0) {
            if (var3[1] < 99) {
               return true;
            }

            return false;
         }
      }

      return var1 <= 99;
   }

   private static boolean b(int var0, int var1, Vector var2) {
      for (int var4 = 0; var4 < var2.size(); var4++) {
         int[] var3;
         if ((var3 = (int[])var2.elementAt(var4))[0] == var0) {
            if (var3[1] - var1 >= 0) {
               return true;
            }

            return false;
         }
      }

      return false;
   }

   private static boolean c(int var0, int var1, Vector var2) {
      for (int var4 = 0; var4 < var2.size(); var4++) {
         int[] var3;
         if ((var3 = (int[])var2.elementAt(var4))[0] == var0) {
            var3[1] += var1;
            if (var3[1] >= 99) {
               var3[1] = 99;
            }

            return true;
         }
      }

      int[] var5 = new int[]{var0, var1, 0};
      var2.addElement(var5);
      return false;
   }

   private static boolean d(int var0, int var1, Vector var2) {
      for (int var4 = 0; var4 < var2.size(); var4++) {
         int[] var3;
         if ((var3 = (int[])var2.elementAt(var4))[0] == var0) {
            var3[1] -= var1;
            if (var3[1] <= 0 && var3[2] == 0) {
               var2.removeElementAt(var4);
            }

            return true;
         }
      }

      return false;
   }

   public final boolean a(int var1, int var2, byte var3) {
      switch (var3) {
         case 0:
            if (GameDatabase.gameDatabase[4][var1][5] == 0) {
               return a(var1, var2, this.K);
            }

            return a(var1, var2, this.J);
         case 2:
            if (var1 >= 12) {
               return a(var1, var2, this.M);
            } else {
               var2 = var1;
               Player var4 = this;

               for (int var6 = 0; var6 < var4.L.size(); var6++) {
                  if (((int[])var4.L.elementAt(var6))[0] == var2) {
                     return false;
                  }
               }

               return true;
            }
         default:
            return false;
      }
   }

   public final boolean b(int var1, int var2, byte var3) {
      switch (var3) {
         case 0:
            if (GameDatabase.gameDatabase[4][var1][5] == 0) {
               return b(var1, var2, this.K);
            }

            return b(var1, var2, this.J);
         case 2:
            return b(var1, var2, this.M);
         default:
            return false;
      }
   }

   public final boolean c(int var1, int var2, byte var3) {
      switch (var3) {
         case 0:
            if (GameDatabase.gameDatabase[4][var1][5] == 0) {
               return c(var1, var2, this.K);
            }

            return c(var1, var2, this.J);
         case 2:
            if (var1 >= 12) {
               if (var1 == 17) {
                  return c(var1, var2 * 5, this.M);
               }

               return c(var1, var2, this.M);
            }

            var2 = var1;
            Player var4 = this;
            int[] var6 = new int[]{var2, 0, 0};
            var4.L.addElement(var6);
            return true;
         default:
            return false;
      }
   }

   public final boolean d(int var1, int var2, byte var3) {
      switch (var3) {
         case 0:
            if (GameDatabase.gameDatabase[4][var1][5] == 0) {
               return d(var1, var2, this.K);
            }

            return d(var1, var2, this.J);
         case 2:
            return d(var1, var2, this.M);
         default:
            return false;
      }
   }

   public final int countItem(int var1, byte var2) {
      switch (var2) {
         case 0:
            if (GameDatabase.gameDatabase[4][var1][5] == 0) {
               for (int var7 = 0; var7 < this.K.size(); var7++) {
                  int[] var5;
                  if ((var5 = (int[])this.K.elementAt(var7))[0] == var1) {
                     return var5[1];
                  }
               }
            } else {
               for (int var8 = 0; var8 < this.J.size(); var8++) {
                  int[] var6;
                  if ((var6 = (int[])this.J.elementAt(var8))[0] == var1) {
                     return var6[1];
                  }
               }
            }
            break;
         case 2:
            for (int var3 = 0; var3 < this.M.size(); var3++) {
               int[] var4;
               if ((var4 = (int[])this.M.elementAt(var3))[0] == var1) {
                  return var4[1];
               }
            }
      }

      return 0;
   }

   public final void x() {
      if (this.S == null) {
         this.S = new Vector();
      } else {
         this.S.removeAllElements();
      }

      for (int var1 = 0; var1 < this.K.size(); var1++) {
         int[] var2 = (int[])this.K.elementAt(var1);
         if (GameDatabase.gameDatabase[4][var2[0]][4] == 0) {
            this.S.addElement(var2);
         }
      }

      for (int var3 = 0; var3 < this.J.size(); var3++) {
         int[] var4 = (int[])this.J.elementAt(var3);
         if (GameDatabase.gameDatabase[4][var4[0]][4] == 0) {
            this.S.addElement(var4);
         }
      }
   }

   public final boolean i(int var1) {
      int[] var2;
      if (var1 == 0) {
         var2 = new int[]{var1, 0, 0};
      } else {
         var2 = new int[]{var1, 1, 0};
         if (var1 == 1 || var1 == 2 || var1 == 3 || var1 == 4) {
            this.P[var1 - 1] = 1;
         }
      }

      this.N.addElement(var2);
      return true;
   }

   public final void c(int var1, int var2) {
      for (int var4 = 0; var4 < this.N.size(); var4++) {
         int[] var3;
         if ((var3 = (int[])this.N.elementAt(var4))[0] == var1) {
            var3[2] += var2;
            if (var3[2] >= 99) {
               var3[2] = 99;
            }

            return;
         }
      }

      int[] var5 = new int[]{var1, 0, var2};
      this.N.addElement(var5);
   }

   public final int d(int var1, int var2) {
      if (0 >= this.N.size()) {
         return var2 > 99 ? 1 : -1;
      } else {
         int[] var3;
         if ((var3 = (int[])this.N.elementAt(0))[0] == var1 && (var1 == 7 || var1 == 9 || var1 == 8)) {
            return var3[1] <= 99 ? 0 : 1;
         } else {
            return -1;
         }
      }
   }

   public final boolean e(int var1, int var2) {
      int var4 = 0;

      while (var4 < this.N.size()) {
         int[] var3;
         if ((var3 = (int[])this.N.elementAt(var4))[0] != var1 || var1 != 7 && var1 != 9 && var1 != 8) {
            if (var3[0] != var1 || var3[1] != 0) {
               var4++;
               continue;
            }

            this.N.setElementAt(new int[]{var3[0], 1, var3[2]}, var4);
            break;
         }

         this.petParty[var2].i((byte)var1);
         if (var3[2] > 0) {
            var3[2]--;
            this.N.setElementAt(new int[]{var3[0], 0, var3[2]}, var4);
         } else if (var3[2] <= 0) {
            this.N.removeElementAt(var4);
         }

         return true;
      }

      return false;
   }

   public final void j(int var1) {
      for (int var3 = 0; var3 < this.N.size(); var3++) {
         int[] var2;
         if ((var2 = (int[])this.N.elementAt(var3))[0] == var1 && var2[1] == 1) {
            this.N.setElementAt(new int[]{var2[0], 0, var2[2]}, var3);
            return;
         }
      }
   }

   public final boolean k(int var1) {
      for (int var2 = 0; var2 < this.N.size(); var2++) {
         int[] var3;
         if ((var3 = (int[])this.N.elementAt(var2))[0] == var1 && var3[1] == 1) {
            return true;
         }
      }

      return false;
   }

   public final boolean l(int var1) {
      for (int var2 = 0; var2 < this.L.size(); var2++) {
         int[] var3;
         if ((var3 = (int[])this.L.elementAt(var2))[0] == var1) {
            var3[1] = 0;
            return true;
         }
      }

      return false;
   }

   public final void f(int var1, int var2) {
      if (this.petParty[var2].baseStats[5] >= 0) {
         this.l(this.petParty[var2].baseStats[5]);
         this.petParty[var2].baseStats[5] = -1;
      }

      boolean var4 = false;
      Player var3 = this;
      int var10 = 0;

      boolean var10000;
      while (true) {
         if (var10 >= var3.L.size()) {
            var10000 = false;
            break;
         }

         int[] var5;
         if ((var5 = (int[])var3.L.elementAt(var10))[0] == var1 && var5[1] == 1) {
            var10000 = true;
            break;
         }

         var10++;
      }

      if (var10000) {
         this.l(var1);
         boolean var6 = false;

         for (int var11 = 0; var11 < this.partyPetCount; var11++) {
            if (this.petParty[var11].baseStats[5] == var1) {
               this.petParty[var11].baseStats[5] = -1;
               var6 = true;
               break;
            }
         }

         if (!var6) {
            for (int var12 = 0; var12 < this.O.size(); var12++) {
               int[] var7;
               if ((var7 = (int[])this.O.elementAt(var2))[2] == var1) {
                  var7[2] = -1;
                  break;
               }
            }
         }
      }

      var3 = this;

      for (int var13 = 0; var13 < var3.L.size(); var13++) {
         int[] var14;
         if ((var14 = (int[])var3.L.elementAt(var13))[0] == var1) {
            var14[1] = 1;
            break;
         }
      }

      Pet var15 = this.petParty[var2];
      short var9 = (short)var1;
      var15.baseStats[5] = var9;
   }

   public final byte y() {
      if (this.partyPetCount < 6) {
         return 0;
      } else {
         return (byte)(this.O.size() < 100 ? 1 : 2);
      }
   }

   public final boolean z() {
      return this.O.size() < 100;
   }

   public final void a(int var1, int var2, byte var3, short var4, int[] var5) {
      this.petParty[this.partyPetCount] = new Pet();
      this.petParty[this.partyPetCount].initPet(var1, var2, (short)-1, var3, var4, (byte)-1);
      this.petParty[this.partyPetCount].b(var5);
      this.a((byte)this.petParty[this.partyPetCount].getSpeciesValue((byte)1), var1, (byte)2);
      this.partyPetCount++;
   }

   public final void a(int var1, int var2, int var3, byte var4, short var5, int[] var6) {
      this.petParty[this.partyPetCount] = new Pet();
      System.arraycopy(this.petParty, var1, this.petParty, var1 + 1, this.partyPetCount - var1);
      this.petParty[var1] = null;
      this.petParty[var1] = new Pet();
      this.petParty[var1].initPet(var2, var3, (short)-1, var4, var5, (byte)-1);
      this.petParty[var1].b(var6);
      this.partyPetCount++;
   }

   public final void a(int[] var1) {
      this.petParty[this.partyPetCount] = new Pet();
      this.petParty[this.partyPetCount].initPet(var1[0], var1[1], (short)var1[2], (byte)var1[3], (short)var1[4], (byte)var1[5]);
      this.petParty[this.partyPetCount].restoreProgress((short)var1[6], var1[7], var1[8]);
      int[] var2 = new int[var1.length - 9];

      for (int var3 = 0; var3 < var2.length; var3++) {
         var2[var3] = var1[var3 + 9];
      }

      this.petParty[this.partyPetCount].b(var2);
      this.a((byte)this.petParty[this.partyPetCount].getSpeciesValue((byte)1), var1[0], (byte)2);
      this.partyPetCount++;
   }

   public final void m(int var1) {
      for (this.petParty[var1] = null; var1 < this.partyPetCount - 1; var1++) {
         this.petParty[var1] = this.petParty[var1 + 1];
         this.petParty[var1 + 1] = null;
      }

      this.partyPetCount--;
   }

   public final void n(int var1) {
      for (int var2 = 0; var2 < this.partyPetCount; var2++) {
         if (this.petParty[var2].getPetId() == var1) {
            this.m(var2);
            return;
         }
      }
   }

   public final boolean o(int var1) {
      int var2 = 0;

      for (int var3 = 0; var3 < this.partyPetCount; var3++) {
         if (var3 != var1 && this.petParty[var3].isAlive()) {
            var2++;
         }
      }

      return var2 > 0;
   }

   public final int A() {
      int[] var1 = new int[this.partyPetCount];

      for (int var2 = 0; var2 < this.partyPetCount; var2++) {
         var1[var2] = this.petParty[var2].baseStats[1] - this.petParty[var2].currentStats[1];
      }

      int var4 = var1[0];

      for (int var3 = 1; var3 < var1.length; var3++) {
         if (var4 < var1[var3]) {
            var4 = var1[var3];
         }
      }

      return var4 == 0 ? -1 : var4;
   }

   public final void p(int var1) {
      Pet var2 = this.petParty[var1];

      while (var1 > 0) {
         this.petParty[var1] = this.petParty[var1 - 1];
         var1--;
      }

      this.petParty[0] = var2;
   }

   public final void a(int var1, int var2, byte var3, short var4, int var5, int var6, int[] var7) {
      int[] var8;
      (var8 = new int[9 + var7.length])[0] = var1;
      var8[1] = var2;
      var8[2] = -1;
      var8[3] = var3;
      var8[4] = var4;
      var8[5] = -1;
      var8[6] = var5;
      var8[7] = 0;
      var8[8] = var6;
      System.arraycopy(var7, 0, var8, 9, var7.length);
      this.O.addElement(var8);
      this.a((byte)GameDatabase.gameDatabase[0][var1][1], var1, (byte)2);
   }

   public final void b(int[] var1) {
      this.O.addElement(var1);
      this.a((byte)GameDatabase.gameDatabase[0][var1[0]][1], var1[0], (byte)2);
   }

   public final void q(int var1) {
      this.O.removeElementAt(var1);
   }

   public final void r(int var1) {
      Pet[] var10000 = this.petParty;
      int var10001 = this.partyPetCount;
      Player var2 = this;
      Pet var4 = new Pet();
      int[] var6 = (int[])var2.O.elementAt(var1);
      var4.initPet(var6[0], var6[1], (short)var6[2], (byte)var6[3], (short)var6[4], (byte)var6[5]);
      var4.restoreProgress((short)var6[6], var6[7], var6[8]);
      int[] var3 = new int[var6.length - 9];

      for (int var5 = 0; var5 < var3.length; var5++) {
         var3[var5] = var6[var5 + 9];
      }

      var4.b(var3);
      var10000[var10001] = var4;
      this.partyPetCount++;
      this.q(var1);
   }

   public final void a(short var1) {
      this.quickItemSlots[this.I] = var1;
      this.I++;
   }

   public final void a(byte var1, byte var2, byte var3) {
      this.B[var1][var2] = var3;
      if (this.B[0][0] == 2) {
         ScreenView.a().a(GameDatabase.getValue((byte)2, (short)0, (byte)5) / 2, GameDatabase.getValue((byte)2, (short)0, (byte)5) / 2);
      }
   }

   public final byte c(byte var1, byte var2) {
      return this.B[var1][var2];
   }

   public final void a(byte var1, int var2, byte var3) {
      Player var4 = this;
      int var5 = 0;

      boolean var10000;
      while (true) {
         if (var5 >= var4.E[var1]) {
            var10000 = true;
            break;
         }

         if (var4.collectionOrder[var1][var5] == var2) {
            var10000 = false;
            break;
         }

         var5++;
      }

      if (var10000) {
         this.collectionOrder[var1][this.E[var1]] = (byte)var2;
         this.E[var1]++;
         if (var3 == 2) {
            this.F++;
            if (GameDatabase.gameDatabase[0][var2][22] == 2) {
               this.G++;
            } else if (GameDatabase.gameDatabase[0][var2][22] == 1) {
               this.H++;
            }
         }

         this.collectionFlags[var1][var2 - this.elementStartIds[var1]] = var3;
      } else {
         if (this.getCollectionFlag(var1, var2) <= 1) {
            if (var3 == 2) {
               this.F++;
               if (GameDatabase.gameDatabase[0][var2][22] == 2) {
                  this.G++;
               } else if (GameDatabase.gameDatabase[0][var2][22] == 1) {
                  this.H++;
               }
            }

            this.collectionFlags[var1][var2 - this.elementStartIds[var1]] = var3;
         }
      }
   }

   public final byte getCollectionFlag(byte var1, int var2) {
      return this.collectionFlags[var1][var2 - this.elementStartIds[var1]];
   }

   public final void B() {
      this.ac = true;
   }

   public final int C() {
      return WorldManager.a().f == 4 && WorldManager.a().g == 1 ? EngineUtils.randomRange(4, 8) : EngineUtils.randomRange(this.ad, this.ae);
   }

   private void O() {
      if (!WorldManager.a().M.h() && OverworldScreen.t != 0) {
         this.w--;
         if (this.w <= 0) {
            WorldManager.a().q();
            this.w = 0;
         }

         this.x--;
         if (this.x == 0) {
            this.spriteRenderer.a(0);
            this.x = -1;
         }

         if (WorldManager.a().c() && this.x <= 0) {
            if (this.ac && this.v > 0 && this.P[1] != 2 && this.P[3] != 2) {
               this.v--;
            }
         }
      }
   }

   public final boolean D() {
      return this.v <= 0;
   }

   public final int getGold() {
      return this.gold;
   }

   public final void addGold(int var1) {
      this.gold += var1;
   }

   public final void resetGold() {
      this.gold = 0;
   }

   public final boolean hasGold(int var1) {
      return this.gold >= var1;
   }

   public final int getArenaPoints() {
      return this.arenaPoints;
   }

   public final void addArenaPoints(int var1) {
      this.arenaPoints += var1;
   }

   public final void resetArenaPoints() {
      this.arenaPoints = 0;
   }

   public final boolean hasArenaPoints(int var1) {
      return this.arenaPoints >= var1;
   }

   public final boolean b(int var1, int var2, int var3) {
      return GameDatabase.gameDatabase[var3][var1][4] == 0 ? this.hasGold(var2) : this.hasArenaPoints(var2);
   }

   public final void initializeStarterPet() {
      this.a(68, 7, (byte)2, (short)2, new int[]{1, 40, 45});
      this.i(0);
   }

   public final boolean loadSprite(int var1, boolean var2) {
      super.loadSprite(var1, var2);
      if (this.x > 0) {
         this.spriteRenderer.a(1);
      }

      return true;
   }

   public final void J() {
      this.b((byte)0, this.facingDirection);
   }
}
