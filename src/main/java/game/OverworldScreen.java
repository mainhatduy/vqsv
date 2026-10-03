package game;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Calendar;
import java.util.TimeZone;
import java.util.Vector;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

public final class OverworldScreen extends BaseScreen {
   private static OverworldScreen v = null;
   private WorldManager w;
   private Player x;
   private BaseScreen y;
   public ScriptSequence[] a;
   private Vector z;
   private byte A = -1;
   public byte[][] b;
   private int B = 0;
   public int c = -1;
   private static Vector C;
   public static Vector d;
   private TileMapRenderer D = TileMapRenderer.a();
   public static boolean e = false;
   public static boolean f = true;
   public static boolean g = false;
   public static boolean h = false;
   public boolean i = true;
   public static boolean j = true;
   public boolean k = true;
   public byte l = 0;
   public static byte m = -1;
   private short[] E;
   private byte[] F;
   private short[] G;
   private short[] H;
   private short[][] I;
   private short[][] J;
   private short[] K;
   private short[] L;
   public byte n = 0;
   public static Image o;
   private String[] M = new String[]{"ikon_1", "ikon_2", "ikon_3", "ikon_4", "ikon_5"};
   private int N;
   private int O;
   private int Z = 0;
   private int aa = 0;
   private byte[] ab;
   private String[] ac;
   private int[] ad;
   private int[] ae;
   private String[] af = null;
   private WorldEntity ag = null;
   private int ah = -1;
   private Calendar ai = null;
   private int[] aj;
   public byte p = 0;
   public static String[] q;
   public static String[] r;
   public static short[][] s = null;
   public static byte t = 0;
   public static byte u = 0;
   private static byte[][] ak;
   private static byte[][] al;

   public OverworldScreen() {
      if (this.w == null) {
         this.w = WorldManager.a();
      }

      if (this.x == null) {
         this.x = Player.getInstance();
      }

      if (this.b == null) {
         this.b = new byte[127][];
      }

      if (s == null) {
         s = new short[200][2];
      }

      if (this.ai == null) {
         this.ai = Calendar.getInstance(TimeZone.getDefault());
      }

      try {
         String[][] var1;
         q = new String[(var1 = EngineUtils.readStringRows(EngineUtils.openResource("/data/script/bTask.mid"))).length];

         for (int var2 = 0; var2 < var1.length; var2++) {
            System.arraycopy(var1[var2], 0, q, var2, var1[var2].length);
         }

         r = new String[(var1 = EngineUtils.readStringRows(EngineUtils.openResource("/data/script/mTask.mid"))).length];

         for (int var6 = 0; var6 < var1.length; var6++) {
            System.arraycopy(var1[var6], 0, r, var6, var1[var6].length);
         }

         InputStream var5;
         ak = EngineUtils.readByteRows(var5 = EngineUtils.openResource("/data/script/bqTask.mid"));
         al = EngineUtils.readByteRows(var5);
         var5.close();
      } catch (IOException var3) {
         var3.printStackTrace();
      }
   }

   public static OverworldScreen a() {
      if (v == null) {
         v = new OverworldScreen();
      }

      return v;
   }

   public final void a(BaseScreen var1) {
      if (this.y != null) {
         this.y = null;
      }

      this.y = var1;
   }

   public final void b() {
      if (this.a != null) {
         ScreenView.a().b();
         this.D.d();
         OverworldScreen var1 = this;

         for (int var2 = 0; var2 < var1.a.length; var2++) {
            if (var1.a[var2].getState() == 0 || var1.a[var2].getState() == 4) {
               boolean var4;
               label218: {
                  ScriptCommand var3 = var1.a[var2].getFirstCommand();
                  var4 = false;
                  switch (var3.getOpcode()) {
                     case 13:
                        if (!EngineUtils.a(
                           var3.getNumericArguments()[0],
                           var3.getNumericArguments()[1],
                           var3.getNumericArguments()[2],
                           var3.getNumericArguments()[3],
                           var1.x.posX,
                           var1.x.posY,
                           var1.x.spriteRenderer.k()
                        )) {
                           break label218;
                        }

                        var1.x.b((byte)0, var1.x.facingDirection);
                        break;
                     case 15:
                        if (var1.b[WorldManager.a(var3.getNumericArguments()[0], var3.getNumericArguments()[1])] != null
                           && (
                              var1.b[WorldManager.a(var3.getNumericArguments()[0], var3.getNumericArguments()[1])][var3.getNumericArguments()[2]] == 3
                                 || var1.b[WorldManager.a(var3.getNumericArguments()[0], var3.getNumericArguments()[1])][var3.getNumericArguments()[2]] == 4
                           )) {
                           var4 = true;
                        }
                        break label218;
                     case 16:
                        if (var3.getNumericArguments()[0] != WorldManager.u) {
                           break label218;
                        }

                        g = true;
                        if (!h) {
                           break label218;
                        }

                        var1.x.b((byte)0, var1.x.facingDirection);
                        h = false;
                        break;
                     case 43:
                        if (WorldManager.a(var3.getNumericArguments()[2], var3.getNumericArguments()[3]) == WorldManager.a(var1.w.f, var1.w.g)
                           && var3.getNumericArguments()[4] == WorldManager.u
                           && var1.a(var3)) {
                           g = true;
                           if (h && var1.a(-1) == -1) {
                              int var18 = var2;
                              OverworldScreen var14 = var1;
                              int var19 = 0;

                              byte var10001;
                              while (true) {
                                 if (var19 >= var14.a.length) {
                                    var10001 = -1;
                                    break;
                                 }

                                 ScriptCommand var8;
                                 if (var14.a[var19].getState() != 3
                                    && var18 != var19
                                    && (var8 = var14.a[var19].getFirstCommand()).getOpcode() == 43
                                    && WorldManager.a(var8.getNumericArguments()[2], var8.getNumericArguments()[3]) == WorldManager.a(var14.w.f, var14.w.g)
                                    && var8.getNumericArguments()[4] == WorldManager.u
                                    && var14.a(var8)) {
                                    var10001 = (byte)var19;
                                    break;
                                 }

                                 var19++;
                              }

                              if (var2 >= var10001) {
                                 var4 = true;
                                 g = false;
                                 var1.x.b((byte)0, var1.x.facingDirection);
                                 if (var3.getNumericArguments()[1] == 1) {
                                    s[u][0] = var3.getNumericArguments()[0];
                                 }
                              }
                           }
                        }
                        break label218;
                     case 44:
                        if (WorldManager.a(var3.getNumericArguments()[2], var3.getNumericArguments()[3]) != WorldManager.a(var1.w.f, var1.w.g)
                           || var3.getNumericArguments()[4] != WorldManager.u
                           || !var1.b(var3)) {
                           break label218;
                        }

                        g = true;
                        if (!h || var2 < var1.a(var2)) {
                           break label218;
                        }

                        var1.x.b((byte)0, var1.x.facingDirection);
                        g = false;
                        break;
                     case 57:
                        if (var1.x.ownerEntity != null
                           && ((NpcEntity)var1.x.ownerEntity).t == 0
                           && ((NpcEntity)var1.x.ownerEntity).v == 11
                           && ((NpcEntity)var1.x.ownerEntity).I == var3.getNumericArguments()[3]
                           && h) {
                           if (var1.w.d[var3.getNumericArguments()[0]].posX == var3.getNumericArguments()[1]
                              && var1.w.d[var3.getNumericArguments()[0]].posY == var3.getNumericArguments()[2]) {
                              ((NpcEntity)var1.x.ownerEntity).d((byte)0);
                           } else {
                              var4 = true;
                           }

                           h = false;
                        }
                        break label218;
                     case 59:
                        int[] var13 = new int[EngineUtils.a(var3.getTextArguments()[0], ',').length];

                        int var17;
                        for (var17 = 0; var17 < var13.length; var17++) {
                           var13[var17] = EngineUtils.b(EngineUtils.a(var3.getTextArguments()[0], ',')[var17]);
                           if (var1.w.d[var13[var17]].getActionState() == 0) {
                              break;
                           }
                        }

                        if (var17 >= var13.length) {
                           var4 = true;
                        }
                        break label218;
                     case 61:
                        int[] var12 = new int[EngineUtils.a(var3.getTextArguments()[0], ',').length];

                        int var16;
                        for (var16 = 0; var16 < var12.length; var16++) {
                           var12[var16] = EngineUtils.b(EngineUtils.a(var3.getTextArguments()[0], ',')[var16]);
                           if (var1.w.d[var12[var16]].getActionState() == 0) {
                              break;
                           }
                        }

                        if (var16 >= var12.length) {
                           var1.x.b((byte)0, var1.x.facingDirection);
                           var4 = true;
                        }
                        break label218;
                     case 69:
                        if (var3.getNumericArguments()[0] != WorldManager.u) {
                           break label218;
                        }
                        break;
                     case 73:
                        String[] var11 = EngineUtils.a(var3.getTextArguments()[1], ',');
                        String[] var15 = EngineUtils.a(var3.getTextArguments()[0], ',');
                        int var10 = 0;

                        while (var10 < var11.length && var1.x.getCollectionFlag(EngineUtils.d(var15[var10]), EngineUtils.d(var11[var10])) >= 2) {
                           var10++;
                        }

                        if (var10 >= var11.length) {
                           var4 = true;
                        }
                        break label218;
                     case 75:
                        if (var1.x.L.size() <= 0) {
                           break label218;
                        }
                        break;
                     case 78:
                        byte[] var5 = EngineUtils.e(var3.getTextArguments()[0]);
                        byte[] var6 = EngineUtils.e(var3.getTextArguments()[1]);
                        byte[] var9 = EngineUtils.e(var3.getTextArguments()[2]);
                        int var7 = 0;

                        while (
                           var7 < var9.length
                              && var1.b[WorldManager.a(var5[var7], var6[var7])] != null
                              && (
                                 var1.b[WorldManager.a(var5[var7], var6[var7])][var9[var7]] == 3
                                    || var1.b[WorldManager.a(var5[var7], var6[var7])][var9[var7]] == 4
                              )
                        ) {
                           var7++;
                        }

                        if (var7 >= var9.length) {
                           var4 = true;
                        }
                        break label218;
                     case 79:
                        if (var1.b[WorldManager.a(var3.getNumericArguments()[0], var3.getNumericArguments()[1])] == null
                           || var1.b[WorldManager.a(var3.getNumericArguments()[0], var3.getNumericArguments()[1])][var3.getNumericArguments()[2]] != 3
                           || var1.x.k(0)
                           || WorldManager.u != var3.getNumericArguments()[3]) {
                           break label218;
                        }

                        g = true;
                        if (!h) {
                           break label218;
                        }

                        h = false;
                        break;
                     case 86:
                        if (var1.b[WorldManager.a(var3.getNumericArguments()[0], var3.getNumericArguments()[1])] == null
                           || var1.b[WorldManager.a(var3.getNumericArguments()[0], var3.getNumericArguments()[1])][var3.getNumericArguments()[2]] != 3) {
                           break label218;
                        }
                  }

                  var4 = true;
               }

               if (var4) {
                  var1.A = (byte)var2;
                  var1.a[var2].setCommandIndex((byte)0);
                  var1.z.addElement(var1.a[var2]);
                  var1.a[var2].setState((byte)1);
               }
            }
         }

         this.n();
      }
   }

   public static void a(Graphics var0) {
      if (C != null) {
         for (int var1 = 0; var1 < C.size(); var1++) {
            WorldEntity var2;
            WorldEntity var10000 = var2 = (WorldEntity)C.elementAt(var1);
            var10000.setPosition(var10000.ownerEntity.posX, var2.ownerEntity.posY - 40);
            var2.renderInWorld(var0, MapEngine.getInstance().cameraX, MapEngine.getInstance().cameraY);
         }
      }

      if (d != null) {
         for (int var3 = 0; var3 < d.size(); var3++) {
            ((WorldEntity)d.elementAt(var3)).renderInWorld(var0, MapEngine.getInstance().cameraX, MapEngine.getInstance().cameraY);
         }
      }
   }

   public final void c() {
      if (C != null) {
         for (int var1 = 0; var1 < C.size(); var1++) {
            WorldEntity var2;
            (var2 = (WorldEntity)C.elementAt(var1)).advanceAnimationIfVisible();
            if (var2.spriteRenderer.isLastAnimationStep()) {
               var2.deactivate();
               C.removeElementAt(var1);
               var1--;
            }
         }
      }

      if (d != null) {
         for (int var3 = 0; var3 < d.size(); var3++) {
            ((WorldEntity)d.elementAt(var3)).advanceAnimationIfVisible();
         }
      }

      if (this.ag != null) {
         this.ag.advanceAnimationIfVisible();
      }
   }

   public final void b(Graphics var1) {
      ScreenView.a().a(var1);
      Graphics var3 = var1;
      OverworldScreen var2 = this;
      if (this.ag != null && var2.ah == WorldManager.a(var2.w.f, var2.w.g)) {
         var2.ag.renderInWorld(var3, MapEngine.getInstance().cameraX, MapEngine.getInstance().cameraY);
      }

      this.D.a(var1);
   }

   public final boolean d() {
      return true;
   }

   public final boolean a(DataInputStream var1, int var2, int var3, int var4, String[] var5) {
      try {
         this.a = new ScriptSequence[var4];
         this.z = new Vector();
         C = new Vector();
         int var6 = var2 << 8 | var3;
         if (this.b[WorldManager.l[var2] + var3] == null) {
            this.b[WorldManager.l[var2] + var3] = new byte[var4];
         }

         for (int var7 = 0; var7 < var4; var7++) {
            this.a[var7] = new ScriptSequence();
            this.a[var7].read(var1, (byte)var7, var6, var5);
            this.a[var7].setState(this.b[WorldManager.l[var2] + var3][var7]);
         }
      } catch (IOException var8) {
         System.out.println(" ex = " + var8.toString() + " event init ");
      }

      this.i();
      return false;
   }

   public final void e() {
      this.x = null;
      this.w = null;
      this.b = null;
      s = null;
      this.ai = null;
      v = null;
   }

   public final void f() {
      if (C != null) {
         C.removeAllElements();
         C = null;
      }

      if (d != null) {
         d.removeAllElements();
         d = null;
      }

      this.a = null;
      this.E = null;
      this.F = null;
      this.G = null;
      this.H = null;
      this.I = null;
      this.J = null;
      this.K = null;
      this.L = null;
      this.ab = null;
      this.ac = null;
   }

   public final void setScreenMode(byte var1) {
   }

   private byte a(int var1) {
      for (int var2 = 0; var2 < this.a.length; var2++) {
         ScriptCommand var3;
         if (this.a[var2].getState() != 3
            && var1 != var2
            && (var3 = this.a[var2].getFirstCommand()).getOpcode() == 44
            && WorldManager.a(var3.getNumericArguments()[2], var3.getNumericArguments()[3]) == WorldManager.a(this.w.f, this.w.g)
            && var3.getNumericArguments()[4] == WorldManager.u
            && this.b(var3)) {
            return (byte)var2;
         }
      }

      return -1;
   }

   private static void b(int var0) {
      for (int var1 = 0; var1 < u; var1++) {
         if (s[var1][0] == var0) {
            s[var1][1] = 3;
            return;
         }
      }
   }

   private static int c(int var0) {
      for (int var1 = 0; var1 < u; var1++) {
         if (s[var1][0] == var0) {
            return var1;
         }
      }

      return -1;
   }

   private void n() {
      int var1 = 0;

      while (var1 < this.z.size()) {
         ScriptSequence var2;
         label1081: {
            ScriptCommand var3;
            switch ((var3 = (var2 = (ScriptSequence)this.z.elementAt(var1)).getCurrentCommand()).getOpcode()) {
               case 0:
               case 15:
               case 26:
               case 27:
               case 28:
               case 33:
               case 43:
               case 44:
               case 57:
               case 59:
               case 61:
               case 68:
               case 69:
               case 73:
               case 75:
               case 78:
               case 79:
               case 86:
               default:
                  break label1081;
               case 1:
                  if (var2.getState() != 5) {
                     ScreenView.a().c(0, 9);
                     this.D.a(var3.getNumericArguments()[1], var3.getNumericArguments()[2]);
                     this.D.a((byte)(var3.getNumericArguments()[0] / 10 - 1), var3.getTextArguments()[0], var3.getNumericArguments()[0] % 10);
                     this.D.a(true);
                     var2.setState((byte)5);
                     break label1081;
                  }

                  if (!TileMapRenderer.a || !this.y.isKeyPressed(1)) {
                     break label1081;
                  }

                  this.D.b();
                  if (TileMapRenderer.b) {
                     break label1081;
                  }

                  ScreenView.a().a = -1;
                  this.D.c();
                  break;
               case 2:
                  if (var3.getNumericArguments()[0] == -1) {
                     this.x.activate();
                     this.x.b((byte)0, EngineUtils.d(EngineUtils.a(var3.getTextArguments()[1], ',')[0]));
                     break label1081;
                  }

                  int var33 = 0;

                  while (true) {
                     if (var33 >= var3.getNumericArguments()[0]) {
                        break label1081;
                     }

                     this.w.d[EngineUtils.c(EngineUtils.a(var3.getTextArguments()[0], ',')[var33])]
                        .setFacingDirection(EngineUtils.d(EngineUtils.a(var3.getTextArguments()[1], ',')[var33]));
                     if (this.w.d[EngineUtils.c(EngineUtils.a(var3.getTextArguments()[0], ',')[var33])].v == 1) {
                        this.w.d[EngineUtils.c(EngineUtils.a(var3.getTextArguments()[0], ',')[var33])].d((byte)0);
                     }

                     this.w.d[EngineUtils.c(EngineUtils.a(var3.getTextArguments()[0], ',')[var33])].activate();
                     var33++;
                  }
               case 3:
                  if (var3.getNumericArguments()[0] == -1) {
                     this.x.deactivate();
                     break label1081;
                  }

                  int var49 = 0;

                  while (true) {
                     if (var49 >= var3.getNumericArguments()[0]) {
                        break label1081;
                     }

                     short var32 = EngineUtils.c(EngineUtils.a(var3.getTextArguments()[0], ',')[var49]);
                     this.w.d[var32].deactivate();
                     var49++;
                  }
               case 4:
                  if (var2.getState() != 5 || !EngineUtils.hasDialoguePages()) {
                     this.x.b((byte)0, this.x.facingDirection);
                     this.w.interactionController.a(var3.getTextArguments()[0], var3.getTextArguments()[1], var3.getNumericArguments()[1]);
                     var2.setState((byte)5);
                  } else if (this.w.interactionController.c(var3.getNumericArguments()[1], -1) && this.y.isKeyPressed(196640)) {
                     WorldManager.a().e();
                     if (EngineUtils.b < EngineUtils.b()) {
                        EngineUtils.c();
                        this.w.interactionController.b(EngineUtils.b);
                     } else {
                        if (WorldManager.u != -1 && this.w.d[WorldManager.u].spriteRenderer.spriteId <= 85 && this.w.d[WorldManager.u].u() == 0) {
                           WorldManager.a()
                              .a(WorldManager.a().d[WorldManager.u].posX, WorldManager.a().d[WorldManager.u].posY - 40, WorldManager.a().d[WorldManager.u]);
                        }

                        g = false;
                        h = false;
                        this.w.interactionController.aC();
                        var2.setState((byte)1);
                     }
                  }
                  break label1081;
               case 5:
                  WorldEntity var31;
                  (var31 = new WorldEntity()).loadSprite(259, false);
                  var31.setAnimation((byte)var3.getNumericArguments()[2], (byte)-1, true);
                  if (var3.getNumericArguments()[0] == 0) {
                     var31.setPosition(
                        this.x.getPosX(), this.x.getPosY() - this.x.spriteRenderer.getFrameBounds(this.x.getActionState(), this.x.facingDirection)[3]
                     );
                     var31.setOwnerEntity(this.x);
                  } else if (var3.getNumericArguments()[0] == 1) {
                     if (var3.getNumericArguments()[3] == 0 && var3.getNumericArguments()[4] == 0) {
                        var31.setPosition(this.w.d[var3.getNumericArguments()[1]].getPosX(), this.w.d[var3.getNumericArguments()[1]].getPosY());
                        var31.setOwnerEntity(this.w.d[var3.getNumericArguments()[1]]);
                     } else {
                        var31.setPosition(var3.getNumericArguments()[3], var3.getNumericArguments()[4]);
                     }
                  }

                  var31.activate();
                  C.addElement(var31);
                  break label1081;
               case 6:
                  this.b[WorldManager.l[this.w.f] + this.w.g][var2.getEventId()] = 3;
                  WorldManager.a().f = var3.getNumericArguments()[0];
                  WorldManager.a().g = var3.getNumericArguments()[1];
                  if (var3.getNumericArguments()[3] == 1) {
                     this.w.j = var3.getNumericArguments()[2];
                  } else {
                     this.w.j = -1;
                  }

                  GameStateController.getInstance().setScreenMode((byte)22);
                  break label1081;
               case 7:
                  if (var2.getState() != 5) {
                     this.E = new short[var3.getNumericArguments()[0]];

                     for (int var30 = 0; var30 < this.E.length; var30++) {
                        this.E[var30] = EngineUtils.c(EngineUtils.a(var3.getTextArguments()[0], ',')[var30]);
                        byte var48 = EngineUtils.d(EngineUtils.a(var3.getTextArguments()[2], ',')[var30]);
                        if (this.E[var30] == -1) {
                           this.x.b(EngineUtils.d(EngineUtils.a(var3.getTextArguments()[1], ',')[0]), var48);
                        } else {
                           this.w.d[this.E[var30]].setFacingDirection(var48);
                           this.w.d[this.E[var30]].d(EngineUtils.d(EngineUtils.a(var3.getTextArguments()[1], ',')[var30]));
                        }
                     }

                     this.B = 0;
                     var2.setState((byte)5);
                     break label1081;
                  }

                  for (int var29 = 0; var29 < this.E.length; var29++) {
                     if (this.E[var29] == -1) {
                        if (this.x.isLastAnimationStep()) {
                           this.x.b((byte)0, this.x.facingDirection);
                           this.B++;
                        }
                     } else if (this.w.d[this.E[var29]].isLastAnimationStep()) {
                        this.w.d[this.E[var29]].d((byte)0);
                        this.B++;
                     }
                  }

                  if (this.B < this.E.length) {
                     break label1081;
                  }

                  this.B = 0;
                  break;
               case 8:
                  this.x.activate();
                  WorldManager.u = -1;
                  this.x.setPosition(var3.getNumericArguments()[0], var3.getNumericArguments()[1]);
                  this.x.targetEntity.setPosition(var3.getNumericArguments()[0], var3.getNumericArguments()[1]);
                  this.x.b((byte)0, this.x.facingDirection);
                  break label1081;
               case 9:
                  if (var2.getState() != 5) {
                     boolean var28 = false;
                     if (var3.getNumericArguments()[0] == 12 || var3.getNumericArguments()[0] == 13) {
                        ScreenView.a().c(0, var3.getNumericArguments()[0]);
                        ScreenView.a()
                           .a(
                              var3.getNumericArguments()[1],
                              var3.getNumericArguments()[2],
                              var3.getNumericArguments()[3],
                              var3.getNumericArguments()[4],
                              var3.getNumericArguments()[5]
                           );
                     } else if (var3.getNumericArguments()[0] == 10) {
                        ScreenView.a().c(0, var3.getNumericArguments()[0]);
                        ScreenView.a().d(var3.getNumericArguments()[1], var3.getNumericArguments()[2]);
                     } else if (var3.getNumericArguments()[0] == 15 || var3.getNumericArguments()[0] == 14) {
                        ScreenView.a().c(0, var3.getNumericArguments()[0]);
                        ScreenView.a()
                           .a(
                              this.M[var3.getNumericArguments()[1]],
                              var3.getNumericArguments()[2],
                              var3.getNumericArguments()[3],
                              var3.getNumericArguments()[4]
                           );
                     } else if (var3.getNumericArguments()[0] == 16) {
                        if (var3.getNumericArguments()[1] == 0) {
                           String[] var44 = new String[]{"star0", "star1", "star2", "star3"};
                           ScreenView.a().a(16, (byte)var3.getNumericArguments()[2], (byte)7, var44);
                        } else if (var3.getNumericArguments()[1] == 1) {
                           String[] var45 = new String[]{"fire0", "fire1", "fire2"};
                           ScreenView.a().a(16, (byte)var3.getNumericArguments()[2], (byte)0, var45);
                        } else if (var3.getNumericArguments()[1] == 2) {
                           String[] var46 = new String[]{"fire0", "fire1", "fire2"};
                           ScreenView.a().a(17, (byte)var3.getNumericArguments()[2], (byte)0, var46);
                        } else {
                           var28 = true;
                           ScreenView.a().a(-1, (byte)var3.getNumericArguments()[2], (byte)0, null);
                           var2.setState((byte)1);
                        }
                     } else if (var3.getNumericArguments()[0] == 17) {
                        ScreenView.a().c(var3.getNumericArguments()[1], var3.getNumericArguments()[0]);
                        ScreenView.a()
                           .a(var3.getNumericArguments()[2], var3.getNumericArguments()[3], var3.getNumericArguments()[4], var3.getNumericArguments()[5]);
                     } else {
                        int var47 = var3.getNumericArguments()[1] << 24
                           | var3.getNumericArguments()[2] << 16
                           | var3.getNumericArguments()[3] << 8
                           | var3.getNumericArguments()[4];
                        ScreenView.a().c(var47, var3.getNumericArguments()[0]);
                     }

                     if (!var28) {
                        var2.setState((byte)5);
                     }
                     break label1081;
                  }

                  if (ScreenView.a().c && (var3.getNumericArguments()[0] == 12 || var3.getNumericArguments()[0] == 13)) {
                     var2.setState((byte)1);
                     break label1081;
                  }

                  if (var3.getNumericArguments()[0] != 16 && !ScreenView.a().b) {
                     break label1081;
                  }
                  break;
               case 10:
                  if (var2.getState() != 5) {
                     if (var3.getNumericArguments()[0] == -1) {
                        this.F = new byte[1];
                        this.x.setFacingDirection(EngineUtils.d(EngineUtils.a(var3.getTextArguments()[1], ',')[0]));
                        this.x.b((byte)0, this.x.facingDirection);
                        this.x.setCurrentStat((byte)0, EngineUtils.d(EngineUtils.a(var3.getTextArguments()[2], ',')[0]));
                        this.F[0] = EngineUtils.d(EngineUtils.a(var3.getTextArguments()[3], ',')[0]);
                     } else {
                        this.E = new short[var3.getNumericArguments()[0]];
                        this.F = new byte[var3.getNumericArguments()[0]];

                        for (int var27 = 0; var27 < this.E.length; var27++) {
                           this.E[var27] = EngineUtils.c(EngineUtils.a(var3.getTextArguments()[0], ',')[var27]);
                           if (this.E[var27] != -1) {
                              this.w.d[this.E[var27]].setFacingDirection(EngineUtils.d(EngineUtils.a(var3.getTextArguments()[1], ',')[var27]));
                              this.w.d[this.E[var27]].setCurrentStat((byte)0, EngineUtils.d(EngineUtils.a(var3.getTextArguments()[2], ',')[var27]));
                              this.w.d[this.E[var27]].d((byte)0);
                           } else {
                              this.x.setFacingDirection(EngineUtils.d(EngineUtils.a(var3.getTextArguments()[1], ',')[var27]));
                              this.x.setCurrentStat((byte)0, EngineUtils.d(EngineUtils.a(var3.getTextArguments()[2], ',')[var27]));
                              this.x.b((byte)0, this.x.facingDirection);
                           }

                           this.F[var27] = EngineUtils.d(EngineUtils.a(var3.getTextArguments()[3], ',')[var27]);
                        }
                     }

                     this.B = 0;
                     var2.setState((byte)5);
                     break label1081;
                  }

                  if (var3.getNumericArguments()[0] == -1) {
                     if (this.x.getActionState() == 0) {
                        this.x.b((byte)1, this.x.facingDirection);
                        break label1081;
                     }

                     this.F[0]--;
                     if (this.F[0] > 0) {
                        break label1081;
                     }

                     this.x.b((byte)0, this.x.facingDirection);
                     if (this.x.P[0] != 2 && this.x.P[1] != 2) {
                        this.x.setCurrentStat((byte)0, (short)4);
                     } else {
                        this.x.setCurrentStat((byte)0, (short)8);
                     }

                     var2.setState((byte)1);
                     break label1081;
                  }

                  for (int var26 = 0; var26 < this.E.length; var26++) {
                     if ((this.E[var26] == -1 || this.w.d[this.E[var26]].getActionState() != 0) && (this.E[var26] != -1 || this.x.getActionState() != 0)) {
                        this.F[var26]--;
                        if (this.F[var26] <= 0) {
                           this.B++;
                           this.F[var26] = 0;
                           if (this.E[var26] != -1) {
                              this.w.d[this.E[var26]].d((byte)0);
                              this.w.d[this.E[var26]].setCurrentStat((byte)0, (short)4);
                           } else {
                              this.x.b((byte)0, this.x.facingDirection);
                              if (this.x.P[0] != 2 && this.x.P[1] != 2) {
                                 this.x.setCurrentStat((byte)0, (short)4);
                              } else {
                                 this.x.setCurrentStat((byte)0, (short)8);
                              }
                           }
                        }
                     } else if (this.F[var26] > 0) {
                        if (this.E[var26] != -1) {
                           this.w.d[this.E[var26]].d((byte)3);
                        } else {
                           this.x.b((byte)1, this.x.facingDirection);
                        }
                     }
                  }

                  if (this.B < this.E.length) {
                     break label1081;
                  }
                  break;
               case 11:
                  if (var2.getState() != 5) {
                     boolean var25 = false;
                     if (var3.getNumericArguments()[6] == 0) {
                        var25 = true;
                     }

                     ParticleEffect.a().a(var3.getNumericArguments()[7]);
                     if (var3.getNumericArguments()[2] == 1) {
                        ParticleEffect var10000 = ParticleEffect.a();
                        short var10001 = var3.getNumericArguments()[4];
                        short var10002 = var3.getNumericArguments()[5];
                        var3.getNumericArguments();
                        var3.getNumericArguments();
                        var10000.a(var10001, var10002, var25);
                     } else if (var3.getNumericArguments()[2] == 0) {
                        if (var3.getNumericArguments()[3] == -1) {
                           ParticleEffect var81 = ParticleEffect.a();
                           Player var83 = this.x;
                           var3.getNumericArguments();
                           var3.getNumericArguments();
                           var81.a(var83, var25);
                        } else {
                           ParticleEffect var82 = ParticleEffect.a();
                           NpcEntity var84 = this.w.d[var3.getNumericArguments()[3]];
                           var3.getNumericArguments();
                           var3.getNumericArguments();
                           var82.a(var84, var25);
                        }
                     }

                     this.x.b((byte)0, this.x.facingDirection);
                     var2.setState((byte)5);
                     break label1081;
                  }

                  if (!ParticleEffect.a().c()) {
                     break label1081;
                  }
                  break;
               case 12:
                  this.B++;
                  if (var2.getState() != 5) {
                     var2.setState((byte)5);
                     break label1081;
                  }

                  if (this.B < var3.getNumericArguments()[0]) {
                     break label1081;
                  }

                  this.B = 0;
                  break;
               case 13:
                  if (EngineUtils.a(
                     var3.getNumericArguments()[0],
                     var3.getNumericArguments()[1],
                     var3.getNumericArguments()[2],
                     var3.getNumericArguments()[3],
                     this.x.posX,
                     this.x.posY,
                     this.x.spriteRenderer.k()
                  )) {
                     var2.setState((byte)1);
                     this.x.b((byte)0, this.x.facingDirection);
                  } else {
                     var2.setState((byte)6);
                  }
                  break label1081;
               case 14:
                  var2.setState((byte)3);
                  break label1081;
               case 16:
                  if (var3.getNumericArguments()[0] == WorldManager.u) {
                     g = true;
                     if (h) {
                        h = false;
                        var2.setState((byte)2);
                     }
                  } else {
                     var2.setState((byte)6);
                  }
                  break label1081;
               case 17:
                  if (var2.getState() != 5) {
                     if (var3.getNumericArguments()[0] == 0) {
                        if (this.x.a(var3.getNumericArguments()[1], var3.getNumericArguments()[2], (byte)0)) {
                           short var23 = GameDatabase.gameDatabase[4][var3.getNumericArguments()[1]][0];
                           this.y.interactionController.a("Đạt được: " + BaseScreen.getString(var23), var3.getNumericArguments()[2]);
                           this.x.c(var3.getNumericArguments()[1], var3.getNumericArguments()[2], (byte)0);
                        } else {
                           this.y.interactionController.b("Ba lô đã đủ đạo cụ này");
                        }
                     } else if (this.x.b(var3.getNumericArguments()[1], var3.getNumericArguments()[2], (byte)0)) {
                        short var24 = GameDatabase.gameDatabase[4][var3.getNumericArguments()[1]][0];
                        this.y.interactionController.a("Mất: " + BaseScreen.getString(var24), var3.getNumericArguments()[2]);
                        this.x.d(var3.getNumericArguments()[1], var3.getNumericArguments()[2], (byte)0);
                     }

                     var2.setState((byte)5);
                     break label1081;
                  }

                  if (!this.y.interactionController.ax()) {
                     break label1081;
                  }
                  break;
               case 18:
                  if (var2.getState() != 5) {
                     if (var3.getNumericArguments()[0] == 0) {
                        if (this.x.a(var3.getNumericArguments()[1], var3.getNumericArguments()[2], (byte)2)) {
                           short var21 = GameDatabase.gameDatabase[3][var3.getNumericArguments()[1]][0];
                           this.y.interactionController.a("Đạt được: " + BaseScreen.getString(var21), var3.getNumericArguments()[2]);
                           this.x.c(var3.getNumericArguments()[1], var3.getNumericArguments()[2], (byte)2);
                        } else {
                           this.y.interactionController.b("Ba lô đã đủ đạo cụ này");
                        }
                     } else if (var3.getNumericArguments()[0] == 1) {
                        short var22 = GameDatabase.gameDatabase[3][var3.getNumericArguments()[1]][0];
                        this.y.interactionController.a("Mất: " + BaseScreen.getString(var22), var3.getNumericArguments()[2]);
                        this.x.d(var3.getNumericArguments()[1], var3.getNumericArguments()[2], (byte)2);
                     }

                     var2.setState((byte)5);
                     break label1081;
                  }

                  if (!this.y.interactionController.ax()) {
                     break label1081;
                  }
                  break;
               case 19:
                  if (var2.getState() != 5) {
                     short var20 = GameDatabase.gameDatabase[5][var3.getNumericArguments()[0]][0];
                     this.y.interactionController.a("Đạt được: " + BaseScreen.getString(var20), var3.getNumericArguments()[1]);
                     int var43;
                     if ((var43 = this.x.d(var3.getNumericArguments()[0], var3.getNumericArguments()[1])) != -1) {
                        if (var43 == 1) {
                           this.y.interactionController.b("Ba lô đã đủ loại đạo cụ này");
                        } else {
                           this.x.c(var3.getNumericArguments()[0], var3.getNumericArguments()[1]);
                        }
                     } else if (var3.getNumericArguments()[0] == 0) {
                        this.x.e(var3.getNumericArguments()[0], -1);
                     } else {
                        this.x.i(var3.getNumericArguments()[0]);
                     }

                     var2.setState((byte)5);
                     break label1081;
                  }

                  if (!this.y.interactionController.ax()) {
                     break label1081;
                  }
                  break;
               case 20:
                  if (var2.getState() != 5) {
                     if (var3.getNumericArguments()[0] == 1) {
                        this.y.interactionController.b("Mất: " + var3.getTextArguments()[0]);
                        this.x.T[var3.getNumericArguments()[1]] = false;
                     } else {
                        this.y.interactionController.b("Đạt được: " + var3.getTextArguments()[0]);
                        this.x.T[var3.getNumericArguments()[1]] = true;
                     }

                     var2.setState((byte)5);
                     break label1081;
                  }

                  if (!this.y.interactionController.ax()) {
                     break label1081;
                  }
                  break;
               case 21:
                  WorldManager.x = false;
                  WorldManager.y = var3.getNumericArguments()[2];
                  if (var3.getNumericArguments()[1] == 1) {
                     WorldManager.z = var3.getNumericArguments()[3];
                     WorldManager.A = var3.getNumericArguments()[4];
                     WorldManager.B = var3.getNumericArguments()[5];
                     WorldManager.C = var3.getNumericArguments()[6];
                  }
                  break label1081;
               case 22:
                  WorldManager.x = true;
                  WorldManager.w = (byte)var3.getNumericArguments()[1];
                  WorldManager.a().h = var3.getNumericArguments()[2];
                  WorldManager.a().i = var3.getNumericArguments()[3];
                  WorldManager.B = var3.getNumericArguments()[4];
                  WorldManager.C = var3.getNumericArguments()[5];
                  WorldManager.a().j = -1;
                  break label1081;
               case 23:
                  this.b[WorldManager.a(var3.getNumericArguments()[0], var3.getNumericArguments()[1])][var3.getNumericArguments()[2]] = 3;
                  if (var3.getNumericArguments()[0] == this.w.f && var3.getNumericArguments()[1] == this.w.g) {
                     this.a[var3.getNumericArguments()[2]].setState((byte)3);
                     if (this.z.size() > 0) {
                        this.z.removeElement(this.a[var3.getNumericArguments()[2]]);
                        var1--;
                     }
                  }
                  break label1081;
               case 24:
                  if (var2.getState() != 5) {
                     ScreenView.a().c(0, 11);
                     ScreenView.a().a(var3.getNumericArguments()[0], var3.getNumericArguments()[1], var3.getNumericArguments()[2]);
                     var2.setState((byte)5);
                     break label1081;
                  }

                  if (!ScreenView.a().b) {
                     break label1081;
                  }
                  break;
               case 25:
                  f = var3.getNumericArguments()[0] == 0;
                  break label1081;
               case 29:
                  if (var2.getState() != 5) {
                     short var19 = var3.getNumericArguments()[0];
                     if (var3.getNumericArguments()[0] == -1) {
                        var19 = 1;
                     }

                     this.E = new short[var19];
                     this.K = new short[var19];
                     this.L = new short[var19];
                     this.G = new short[var19];
                     this.H = new short[var19];

                     for (int var42 = 0; var42 < this.E.length; var42++) {
                        this.E[var42] = EngineUtils.c(EngineUtils.a(var3.getTextArguments()[0], ',')[var42]);
                        this.K[var42] = EngineUtils.c(EngineUtils.a(var3.getTextArguments()[1], ',')[var42]);
                        this.L[var42] = EngineUtils.c(EngineUtils.a(var3.getTextArguments()[2], ',')[var42]);
                        this.G[var42] = EngineUtils.c(EngineUtils.a(var3.getTextArguments()[3], ',')[var42]);
                        this.H[var42] = EngineUtils.c(EngineUtils.a(var3.getTextArguments()[4], ',')[var42]);
                     }

                     var2.setState((byte)5);
                     break label1081;
                  }

                  boolean var18 = true;

                  for (int var74 = 0; var74 < this.E.length; var74++) {
                     if (this.G[var74] > 0 || this.H[var74] > 0) {
                        var18 = false;
                        this.G[var74]--;
                        this.H[var74]--;
                        if (var3.getNumericArguments()[0] == -1) {
                           int var40 = this.x.getPosX() + this.K[var74];
                           int var64 = this.x.getPosY() + this.L[var74];
                           this.x.setPosition(var40, var64);
                           if (this.x.targetEntity != null) {
                              this.x.targetEntity.setPosition(var40, var64);
                           }
                        } else {
                           int var41 = this.w.d[this.E[var74]].getPosX() + this.K[var74];
                           int var65 = this.w.d[this.E[var74]].getPosY() + this.L[var74];
                           this.w.d[this.E[var74]].setPosition(var41, var65);
                           if (this.w.d[this.E[var74]].targetEntity != null) {
                              this.w.d[this.E[var74]].targetEntity.setPosition(var41, var65);
                           }
                        }
                     }
                  }

                  if (var18) {
                     var2.setState((byte)1);
                  }
                  break label1081;
               case 30:
                  if (var2.getState() != 5) {
                     this.E = new short[var3.getNumericArguments()[0]];
                     String[] var17 = EngineUtils.a(var3.getTextArguments()[2], ',');

                     for (int var38 = 0; var38 < this.E.length; var38++) {
                        this.E[var38] = EngineUtils.c(var17[var38]);
                     }

                     String[][] var39 = new String[this.E.length][];
                     String[][] var63 = new String[this.E.length][];

                     for (int var72 = 0; var72 < var63.length; var72++) {
                        var39[var72] = EngineUtils.a(EngineUtils.a(var3.getTextArguments()[0], '#')[var72], ',');
                        var63[var72] = EngineUtils.a(EngineUtils.a(var3.getTextArguments()[1], '#')[var72], ',');
                     }

                     this.I = new short[this.E.length][];
                     this.J = new short[this.E.length][];

                     for (int var73 = 0; var73 < this.E.length; var73++) {
                        this.I[var73] = new short[var39[var73].length];
                        this.J[var73] = new short[var63[var73].length];

                        for (int var80 = 0; var80 < this.I[var73].length; var80++) {
                           this.I[var73][var80] = EngineUtils.c(var39[var73][var80]);
                           this.J[var73][var80] = EngineUtils.c(var63[var73][var80]);
                        }
                     }

                     this.B = 0;
                     var2.setState((byte)5);
                     break label1081;
                  }

                  for (int var16 = 0; var16 < this.E.length; var16++) {
                     this.w.d[this.E[var16]].setPosition(this.I[var16][this.B], this.J[var16][this.B]);
                  }

                  this.B++;
                  if (this.B < this.I[0].length) {
                     break label1081;
                  }
                  break;
               case 31:
                  if (var2.getState() != 5) {
                     if (var3.getNumericArguments()[0] == 0) {
                        if (var3.getNumericArguments()[1] == 0) {
                           this.x.addGold(var3.getNumericArguments()[2]);
                           this.y.interactionController.b("Đạt được: " + var3.getNumericArguments()[2] + " kim tiền");
                        } else if (var3.getNumericArguments()[1] == 1) {
                           this.x.addArenaPoints(var3.getNumericArguments()[2]);
                           this.y.interactionController.b("Đạt được: " + var3.getNumericArguments()[2] + "Huy hiệu");
                        }
                     } else if (var3.getNumericArguments()[0] == 1) {
                        if (var3.getNumericArguments()[1] == 0) {
                           this.x.addGold(-var3.getNumericArguments()[2]);
                           this.y.interactionController.b("Mất: " + var3.getNumericArguments()[2] + " kim tiền");
                        } else if (var3.getNumericArguments()[1] == 1) {
                           this.x.addArenaPoints(-var3.getNumericArguments()[2]);
                           this.y.interactionController.b("Mất: " + var3.getNumericArguments()[2] + " huy hiệu");
                        }
                     }

                     var2.setState((byte)5);
                     break label1081;
                  }

                  if (!this.w.interactionController.ax()) {
                     break label1081;
                  }
                  break;
               case 32:
                  this.w.e();
                  BattleScreen.a().a = var3.getNumericArguments()[0];
                  BattleScreen.a().b = (byte)var3.getNumericArguments()[1];
                  BattleScreen.a().c = Image.createImage(BaseScreen.getScreenWidth(), BaseScreen.getScreenHeight());
                  Graphics var15 = BattleScreen.a().c.getGraphics();
                  this.w.b.b(var15);
                  this.x.b((byte)0, this.x.facingDirection);
                  var2.setState((byte)1);
                  GameStateController.getInstance().setScreenMode((byte)12);
                  break label1081;
               case 34:
                  if (var2.getState() != 5) {
                     var3.getNumericArguments();
                     this.N = var3.getNumericArguments()[2];
                     this.O = var3.getNumericArguments()[3];
                     this.B = var3.getNumericArguments()[4];
                     var2.setState((byte)5);
                     break label1081;
                  }

                  this.B--;
                  this.N = this.N - this.O;
                  if (this.B > 0) {
                     break label1081;
                  }

                  this.B = 0;
                  break;
               case 35:
                  if (var2.getState() == 5) {
                     int var37;
                     if ((var37 = this.y.interactionController.c(this.aa)) != -1) {
                        var2.setCommandIndex((byte)(this.ab[var37] - 2));
                        var2.setState((byte)1);
                     }
                     break label1081;
                  }

                  this.Z = var3.getNumericArguments()[0];
                  this.aa = var3.getNumericArguments()[1];
                  this.ac = EngineUtils.a(var3.getTextArguments()[0], ',');
                  this.ab = new byte[EngineUtils.a(var3.getTextArguments()[1], ',').length];
                  String var36 = var3.getTextArguments()[2];

                  for (int var62 = 0; var62 < this.ab.length; var62++) {
                     this.ab[var62] = EngineUtils.d(EngineUtils.a(var3.getTextArguments()[1], ',')[var62]);
                  }

                  this.y.interactionController.a(this.aa, this.Z, this.ac, var36);
                  var2.setState((byte)5);
                  break label1081;
               case 36:
                  if (var2.getState() != 5) {
                     byte var35 = this.x.y();
                     if (var3.getNumericArguments()[0] == 0) {
                        if (var35 == 0) {
                           this.x
                              .a(
                                 var3.getNumericArguments()[1],
                                 var3.getNumericArguments()[2],
                                 (byte)var3.getNumericArguments()[4],
                                 (byte)var3.getNumericArguments()[3],
                                 new int[]{1, var3.getNumericArguments()[5], var3.getNumericArguments()[6]}
                              );
                        } else if (var35 == 1) {
                           this.y.interactionController.b("Ba lô đã đủ, đã để vào ngân hàng");
                           short var61 = Pet.b(var3.getNumericArguments()[1], var3.getNumericArguments()[2], var3.getNumericArguments()[3]);
                           this.x
                              .a(
                                 var3.getNumericArguments()[1],
                                 var3.getNumericArguments()[2],
                                 (byte)var3.getNumericArguments()[4],
                                 (byte)var3.getNumericArguments()[3],
                                 var61,
                                 0,
                                 new int[]{1, var3.getNumericArguments()[5], var3.getNumericArguments()[6]}
                              );
                        } else {
                           this.y.interactionController.b("Không có không gian, đã phóng sinh");
                        }
                     } else if (var3.getNumericArguments()[0] == 1) {
                        this.x.n(var3.getNumericArguments()[1]);
                     }

                     var2.setState((byte)5);
                     break label1081;
                  }

                  if (!this.y.interactionController.ax()) {
                     break label1081;
                  }
                  break;
               case 37:
                  BattleScreen.a().a(new int[][]{{var3.getNumericArguments()[0], var3.getNumericArguments()[1], var3.getNumericArguments()[2]}});
                  break label1081;
               case 38:
                  g = false;

                  for (int var60 = 0; var60 < EngineUtils.a(var3.getTextArguments()[0], ',').length; var60++) {
                     if (EngineUtils.d(EngineUtils.a(var3.getTextArguments()[0], ',')[var60]) == WorldManager.u) {
                        g = true;
                        if (h) {
                           WorldManager.u = -1;
                           byte var5 = EngineUtils.d(EngineUtils.a(var3.getTextArguments()[1], ',')[var60]);
                           var2.setCommandIndex((byte)(var5 - 1));
                           WorldManager.a().e();
                           g = false;
                           h = false;
                        }
                        break;
                     }
                  }

                  var2.setState((byte)6);
                  break label1081;
               case 39:
                  int var59 = 0;

                  while (true) {
                     if (var59 >= this.x.partyPetCount) {
                        break label1081;
                     }

                     this.x.petParty[var59].I();
                     var59++;
                  }
               case 40:
                  if (var2.getState() != 5) {
                     this.y.interactionController.c(var3.getTextArguments()[0]);
                     var2.setState((byte)5);
                     break label1081;
                  }

                  if (!this.y.interactionController.ay()) {
                     break label1081;
                  }
                  break;
               case 41:
                  var2.setCommandIndex((byte)(var3.getNumericArguments()[0] - 2));
                  break label1081;
               case 42:
                  var2.setState((byte)4);
                  break label1081;
               case 45:
                  if (var2.getState() != 5) {
                     this.y.interactionController.c(var3.getTextArguments()[0]);
                     t = (byte)var3.getNumericArguments()[0];
                     var2.setState((byte)5);
                     break label1081;
                  }

                  if (!this.y.interactionController.ay()) {
                     break label1081;
                  }
                  break;
               case 46:
                  if (var2.getState() != 5) {
                     this.y.interactionController.H();
                     this.y.interactionController.a(var3.getTextArguments()[0]);
                     var2.setState((byte)5);
                     break label1081;
                  }

                  if (this.y.interactionController.f == 0) {
                     if (this.y.isKeyPressed(196640)) {
                        this.y.interactionController.f = 1;
                        this.y.interactionController.a("Đang lưu...");
                        this.y.interactionController.J();
                     } else if (this.y.isKeyPressed(262144)) {
                        var2.setState((byte)1);
                        this.y.interactionController.I();
                        this.y.interactionController.f = 0;
                     }
                     break label1081;
                  }

                  if (this.y.interactionController.f == 1) {
                     this.b[WorldManager.a(this.w.f, this.w.g)][var2.getEventId()] = 3;
                     if (((WorldManager)this.y).k()) {
                        this.y.interactionController.a("Lưu thành công");
                        this.y.interactionController.f = 2;
                     }
                     break label1081;
                  }

                  if (this.y.interactionController.f != 2) {
                     break label1081;
                  }

                  this.y.interactionController.I();
                  this.y.interactionController.f = 0;
                  break;
               case 47:
                  if (this.l != -1) {
                     var2.setCommandIndex((byte)(var3.getNumericArguments()[this.l] - 2));
                  }
                  break label1081;
               case 48:
                  if (var2.getState() != 5) {
                     this.D.a(var3.getNumericArguments()[1], var3.getNumericArguments()[2]);
                     this.D.a((byte)(var3.getNumericArguments()[0] / 10 - 1), var3.getTextArguments()[0], var3.getNumericArguments()[0] % 10);
                     if (var3.getNumericArguments()[5] == 1) {
                        this.D.a(true);
                     }

                     this.D.b(var3.getNumericArguments()[3], var3.getNumericArguments()[4]);
                     var2.setState((byte)5);
                     break label1081;
                  }

                  if (!this.D.e()) {
                     var2.setState((byte)1);
                     break label1081;
                  }

                  if (!TileMapRenderer.a || !this.y.isKeyPressed(1)) {
                     break label1081;
                  }

                  this.D.b();
                  if (TileMapRenderer.b) {
                     break label1081;
                  }

                  ScreenView.a().a = -1;
                  this.D.c();
                  break;
               case 49:
                  if (var2.getState() == 5) {
                     int var58;
                     if ((var58 = this.y.interactionController.aD()) != -1) {
                        if (var58 == 0 && var2.getFirstCommand().getNumericArguments()[1] == 1) {
                           s[u][1] = 1;
                           u++;
                        }

                        var2.setCommandIndex((byte)(this.ab[var58] - 2));
                        var2.setState((byte)1);
                     }
                     break label1081;
                  }

                  this.ad = new int[2];
                  this.ae = new int[2];
                  this.af = new String[2];
                  this.ac = new String[2];

                  for (int var56 = 0; var56 < 2; var56++) {
                     this.ad[var56] = var3.getNumericArguments()[var56 << 1];
                     this.ae[var56] = var3.getNumericArguments()[(var56 << 1) + 1];
                     this.af[var56] = var3.getTextArguments()[var56];
                  }

                  this.ab = new byte[EngineUtils.a(var3.getTextArguments()[2], ',').length];

                  for (int var57 = 0; var57 < this.ab.length; var57++) {
                     this.ab[var57] = EngineUtils.d(EngineUtils.a(var3.getTextArguments()[2], ',')[var57]);
                     this.ac[var57] = EngineUtils.a(var3.getTextArguments()[3], ',')[var57];
                  }

                  this.y.interactionController.a(this.ad, this.ae, this.af, this.ac);
                  var2.setState((byte)5);
                  break label1081;
               case 50:
                  if (var3.getNumericArguments()[0] == 0) {
                     this.x.u();
                  } else {
                     this.x.t();
                  }
                  break label1081;
               case 51:
                  this.w.interactionController.aB();
                  this.D.a(var3.getNumericArguments()[1], var3.getNumericArguments()[2]);
                  this.D.a((byte)(var3.getNumericArguments()[0] / 10 - 1), var3.getTextArguments()[0], var3.getNumericArguments()[0] % 10);
                  this.D.b(var3.getNumericArguments()[3], var3.getNumericArguments()[4]);
                  break label1081;
               case 52:
                  if (var3.getNumericArguments()[0] == 0) {
                     this.i = true;
                  } else {
                     this.i = false;
                  }

                  if (var3.getNumericArguments()[1] == 0) {
                     j = true;
                  } else {
                     j = false;
                  }
                  break label1081;
               case 53:
                  if (var2.getState() != 5) {
                     if (var3.getNumericArguments()[1] == 0) {
                        this.x.a((byte)var3.getNumericArguments()[0], (byte)var3.getNumericArguments()[1], (byte)2);

                        for (int var55 = 0; var55 < WorldManager.a().d.length; var55++) {
                           if (WorldManager.a().d[var55].t == 0 && WorldManager.a().d[var55].v == 1) {
                              WorldManager.a().d[var55].v();
                           }
                        }
                     } else if (var3.getNumericArguments()[1] == 1) {
                        this.x.a((byte)var3.getNumericArguments()[0], (byte)var3.getNumericArguments()[1], (byte)1);
                     }

                     this.y.interactionController.a(var3.getNumericArguments()[0]);
                     var2.setState((byte)5);
                     break label1081;
                  }

                  if (!this.w.isKeyPressed(1)) {
                     break label1081;
                  }

                  this.y.interactionController.V();
                  break;
               case 54:
                  short var54;
                  int[][] var71 = new int[var54 = var3.getNumericArguments()[0]][3];

                  for (int var79 = 0; var79 < var54; var79++) {
                     var71[var79][0] = EngineUtils.b(EngineUtils.a(var3.getTextArguments()[0], ',')[var79]);
                     var71[var79][1] = EngineUtils.b(EngineUtils.a(var3.getTextArguments()[1], ',')[var79]);
                     var71[var79][2] = EngineUtils.b(EngineUtils.a(var3.getTextArguments()[2], ',')[var79]);
                  }

                  BattleScreen.a().a(var71);
                  break label1081;
               case 55:
                  if (var3.getNumericArguments()[0] == 0) {
                     if (this.ag == null) {
                        this.ag = new WorldEntity();
                        this.ag.loadSprite(340, false);
                        this.ag.activate();
                        this.ah = WorldManager.a(var3.getNumericArguments()[3], var3.getNumericArguments()[4]);
                     }

                     this.ag.setPosition(var3.getNumericArguments()[1], var3.getNumericArguments()[2]);
                  } else if (var3.getNumericArguments()[0] == 1 && this.ag != null) {
                     this.ag.deactivate();
                     this.ag = null;
                     this.ah = -1;
                  }
                  break label1081;
               case 56:
                  short var53 = var3.getNumericArguments()[1];
                  if (var3.getNumericArguments()[0] == 0) {
                     int var70 = 0;

                     while (true) {
                        if (var70 >= var53) {
                           break label1081;
                        }

                        short var78 = EngineUtils.c(EngineUtils.a(var3.getTextArguments()[0], ',')[var70]);
                        byte var14 = EngineUtils.d(EngineUtils.a(var3.getTextArguments()[1], ',')[var70]);
                        this.w.d[var78].setFacingDirection(var14);
                        if (this.w.d[var78].v == 1) {
                           this.w.d[var78].d((byte)0);
                        }

                        this.w.d[var78].activate();
                        this.w.a(var78, 1, (byte)1, true);
                        this.w.a(var78, 2, var14, true);
                        this.w.d[var78].r();
                        var70++;
                     }
                  }

                  if (var3.getNumericArguments()[0] != 1) {
                     break label1081;
                  }

                  int var69 = 0;

                  while (true) {
                     if (var69 >= var53) {
                        break label1081;
                     }

                     short var77 = EngineUtils.c(EngineUtils.a(var3.getTextArguments()[0], ',')[var69]);
                     if (this.w.d[var77].v == 1) {
                        this.w.d[var77].d((byte)0);
                     }

                     this.w.d[var77].deactivate();
                     this.w.a(var77, 1, (byte)0, true);
                     this.w.d[var77].r();
                     var69++;
                  }
               case 58:
                  if (((NpcEntity)this.x.ownerEntity).getActionState() == 1 && ((NpcEntity)this.x.ownerEntity).spriteRenderer.isLastAnimationStep()) {
                     ((NpcEntity)this.x.ownerEntity).d((byte)0);
                     this.w.d[var3.getNumericArguments()[0]].setPosition(var3.getNumericArguments()[1], var3.getNumericArguments()[2]);
                     if ((NpcEntity)this.w.d[var3.getNumericArguments()[0]].ownerEntity != null) {
                        ((NpcEntity)this.w.d[var3.getNumericArguments()[0]].ownerEntity).q();
                        this.w.d[var3.getNumericArguments()[0]].setOwnerEntity(null);
                     }
                  }
                  break label1081;
               case 60:
                  if (var2.getState() != 5) {
                     this.E = new short[var3.getNumericArguments()[0]];

                     for (int var52 = 0; var52 < this.E.length; var52++) {
                        this.E[var52] = EngineUtils.c(EngineUtils.a(var3.getTextArguments()[0], ',')[var52]);
                        this.w.d[this.E[var52]].d(EngineUtils.d(EngineUtils.a(var3.getTextArguments()[1], ',')[var52]));
                        if (this.w.d[this.E[var52]].t == 0 && this.w.d[this.E[var52]].v == 6 && this.w.d[this.E[var52]].getActionState() == 2) {
                           WorldManager.a().b.c(WorldManager.a().d[this.E[var52]]);
                        }
                     }

                     this.B = 0;
                     var2.setState((byte)5);
                     break label1081;
                  }

                  for (int var51 = 0; var51 < this.E.length; var51++) {
                     if (this.w.d[this.E[var51]].isLastAnimationStep()) {
                        this.B++;
                     }
                  }

                  if (this.B < this.E.length) {
                     break label1081;
                  }

                  this.B = 0;
                  break;
               case 62:
                  int[] var50 = new int[EngineUtils.a(var3.getTextArguments()[0], ',').length];
                  int var76 = -1;
                  var2.setState((byte)6);

                  for (int var68 = 0; var68 < var50.length; var68++) {
                     var50[var68] = EngineUtils.b(EngineUtils.a(var3.getTextArguments()[0], ',')[var68]);
                     if (this.w.d[var50[var68]].getActionState() == 2) {
                        var76 = var50[var68];
                        break;
                     }
                  }

                  if (var76 < 0) {
                     break label1081;
                  }

                  if (var76 == var3.getNumericArguments()[0]) {
                     var2.setCommandIndex((byte)(var3.getNumericArguments()[1] - 2));
                     var2.setState((byte)1);
                     break label1081;
                  }

                  var2.setCommandIndex((byte)(var3.getNumericArguments()[2] - 2));
                  break;
               case 63:
                  if (var3.getNumericArguments()[0] == 0) {
                     this.x.h(var3.getNumericArguments()[1]);
                  } else {
                     this.x.s();
                  }

                  this.k = var3.getNumericArguments()[2] != 0;
                  break label1081;
               case 64:
                  if (var3.getNumericArguments()[0] == 0) {
                     this.w.a(var3.getNumericArguments()[1]);
                     if (var3.getNumericArguments()[2] == -1) {
                        this.w.a(this.x);
                     } else {
                        this.w.a(this.w.d[var3.getNumericArguments()[2]]);
                     }
                  } else {
                     this.w.g();
                  }
                  break label1081;
               case 65:
                  if (var2.getState() != 5 && !isVipUnlocked) {
                     this.y.setScreenMode((byte)100);
                     var2.setState((byte)5);
                     break label1081;
                  }

                  if (isVipUnlocked) {
                     var2.setCommandIndex((byte)(var3.getNumericArguments()[0] - 2));
                  } else {
                     var2.setCommandIndex((byte)(var3.getNumericArguments()[1] - 2));
                  }
                  break;
               case 66:
                  BaseScreen.U = (byte)var3.getNumericArguments()[0];
                  BaseScreen.c(0, 3);
                  break label1081;
               case 67:
                  WorldManager.v = var3.getNumericArguments()[0];
                  break label1081;
               case 70:
                  if (var2.getState() != 5) {
                     e = false;
                     this.c = var3.getNumericArguments()[0];
                     switch (var3.getNumericArguments()[0]) {
                        case 0:
                        case 1:
                           this.w.setScreenMode((byte)1);
                           break;
                        case 2:
                           this.w.setScreenMode((byte)16);
                     }

                     var2.setState((byte)5);
                     break label1081;
                  }

                  if (!e) {
                     break label1081;
                  }

                  this.c = -1;
                  break;
               case 71:
                  if (this.x.F >= var3.getNumericArguments()[0]) {
                     var2.setCommandIndex((byte)(var3.getNumericArguments()[1] - 2));
                  } else {
                     var2.setCommandIndex((byte)(var3.getNumericArguments()[2] - 2));
                  }
                  break label1081;
               case 72:
                  String[] var6 = EngineUtils.a(var3.getTextArguments()[0], ',');
                  String[] var67 = EngineUtils.a(var3.getTextArguments()[1], ',');
                  WorldEntity[] var75 = new WorldEntity[var6.length];
                  int var13 = 0;

                  while (true) {
                     if (var13 >= var6.length) {
                        break label1081;
                     }

                     var75[var13] = new WorldEntity();
                     var75[var13].loadSprite(259, false);
                     if (EngineUtils.b(var6[var13]) == -1) {
                        var75[var13].setAnimation(EngineUtils.d(var67[var13]), (byte)-1, true);
                        var75[var13].activate();
                        var75[var13].setPosition(this.x.getPosX(), this.x.getPosY() - 40);
                        var75[var13].setOwnerEntity(this.x);
                     } else {
                        var75[var13].setAnimation(EngineUtils.d(var67[var13]), (byte)-1, true);
                        var75[var13].activate();
                        var75[var13].setPosition(this.w.d[EngineUtils.b(var6[var13])].getPosX(), this.w.d[EngineUtils.b(var6[var13])].getPosY() - 40);
                        var75[var13].setOwnerEntity(this.w.d[EngineUtils.b(var6[var13])]);
                     }

                     C.addElement(var75[var13]);
                     var13++;
                  }
               case 74:
                  if (((int[])this.x.K.elementAt(0))[1] > 0) {
                     var2.setCommandIndex((byte)(var3.getNumericArguments()[0] - 2));
                  } else {
                     var2.setCommandIndex((byte)(var3.getNumericArguments()[1] - 2));
                  }
                  break label1081;
               case 76:
                  this.b[WorldManager.l[this.w.f] + this.w.g][var2.getEventId()] = 3;
                  WorldManager.a().f = var3.getNumericArguments()[0];
                  WorldManager.a().g = var3.getNumericArguments()[1];
                  WorldManager.a().j = -1;
                  this.w.setScreenMode((byte)29);
                  break label1081;
               case 77:
                  this.b[WorldManager.a(var3.getNumericArguments()[0], var3.getNumericArguments()[1])][var3.getNumericArguments()[2]] = 4;
                  if (var3.getNumericArguments()[0] == this.w.f && var3.getNumericArguments()[1] == this.w.g) {
                     this.a[var3.getNumericArguments()[2]].setState((byte)4);
                  }
                  break label1081;
               case 80:
                  if (var2.getState() != 5) {
                     if (var3.getNumericArguments()[0] == 0) {
                        this.B = 0;
                        this.n = 4;
                     } else if (var3.getNumericArguments()[0] == 1) {
                        GameStateController.getInstance().c = GameStateController.getInstance().b;
                        long var66;
                        if (EngineUtils.a(var66 = GameStateController.getInstance().c - GameStateController.getInstance().a)[2] <= 70L) {
                           byte var11;
                           if ((var11 = this.x.y()) == 0) {
                              this.y.interactionController.b("Đạt được #2Lục hành điểu");
                              this.x.a(54, 5, (byte)2, (short)-1, new int[]{1, 30, 45});
                           } else if (var11 == 1) {
                              this.y.interactionController.b("Đạt được #2Lục hành điểu#0 ba lô đã đủ, đã để vào ngân hàng");
                              int quality = EngineUtils.randomRange(GameDatabase.gameDatabase[0][54][3], GameDatabase.gameDatabase[0][54][3]);
                              this.x.a(54, 5, (byte)2, (byte)quality, 0, 0, new int[]{1, 30, 45});
                           } else {
                              this.y.interactionController.b("Không có không gian, đã phóng sinh");
                           }
                        } else if (EngineUtils.a(var66)[2] <= 80L) {
                           this.x.addGold(1000);
                           this.y.interactionController.b("Thưởng 1000 kim");
                        } else if (EngineUtils.a(var66)[2] <= 130L) {
                           this.x.addGold(750);
                           this.y.interactionController.b("Thưởng 750 kim");
                        } else if (EngineUtils.a(var66)[2] <= 200L) {
                           this.x.addGold(600);
                           this.y.interactionController.b("Thưởng 600 kim");
                        }

                        GameStateController.getInstance().b = 0L;
                        GameStateController.getInstance().a = 0L;
                     }

                     var2.setState((byte)5);
                     break label1081;
                  }

                  if (var3.getNumericArguments()[0] == 0) {
                     this.B++;
                     if (this.n > 0) {
                        if (this.B / 10 != 0 && this.B % 10 == 0) {
                           this.n--;
                        }
                        break label1081;
                     }

                     this.n = 0;
                     GameStateController.getInstance().a = System.currentTimeMillis();
                     GameStateController.getInstance().b = GameStateController.getInstance().a;
                     GameStateController.getInstance().c = 0L;
                  } else {
                     if (var3.getNumericArguments()[0] != 1 || !this.y.interactionController.ax()) {
                        break label1081;
                     }

                     if (this.p == 0) {
                        this.aj = this.j();
                     }

                     this.p++;
                  }
                  break;
               case 81:
                  if (var3.getNumericArguments()[0] == 0) {
                     if (this.x.hasGold(var3.getNumericArguments()[1])) {
                        var2.setCommandIndex((byte)(var3.getNumericArguments()[2] - 2));
                     } else {
                        var2.setCommandIndex((byte)(var3.getNumericArguments()[3] - 2));
                     }
                  } else if (var3.getNumericArguments()[0] == 1) {
                     if (this.x.hasArenaPoints(var3.getNumericArguments()[1])) {
                        var2.setCommandIndex((byte)(var3.getNumericArguments()[2] - 2));
                     } else {
                        var2.setCommandIndex((byte)(var3.getNumericArguments()[3] - 2));
                     }
                  }
                  break label1081;
               case 82:
                  short var7 = var3.getNumericArguments()[0];
                  byte[] var8 = EngineUtils.e(var3.getTextArguments()[0]);
                  int var10 = 0;

                  while (true) {
                     if (var10 >= var7) {
                        break label1081;
                     }

                     this.w.d[var8[var10]].r();
                     this.w.d[var8[var10]].s();
                     var10++;
                  }
               case 83:
                  if (var2.getState() != 5) {
                     this.y.setScreenMode((byte)30);
                     var2.setState((byte)5);
                     break label1081;
                  }

                  var2.setCommandIndex((byte)(var3.getNumericArguments()[m] - 2));
                  break;
               case 84:
                  if (var2.getState() != 5 || !EngineUtils.hasDialoguePages()) {
                     int[] var4 = null;
                     if (var3.getNumericArguments()[2] == 1) {
                        var4 = new int[]{this.p, 5 - this.p};
                     } else if (var3.getNumericArguments()[2] == 0) {
                        var4 = new int[]{this.x.I, this.x.quickItemSlots.length - this.x.I};
                     }

                     String var9 = a(var3.getTextArguments()[1], var4);
                     this.w.interactionController.a(var3.getTextArguments()[0], var9, var3.getNumericArguments()[1]);
                     var2.setState((byte)5);
                  } else if (this.w.interactionController.c(var3.getNumericArguments()[1], -1) && this.y.isKeyPressed(196640)) {
                     WorldManager.a().e();
                     if (EngineUtils.b < EngineUtils.b()) {
                        EngineUtils.c();
                        this.w.interactionController.b(EngineUtils.b);
                     } else {
                        if (WorldManager.u != -1 && this.w.d[WorldManager.u].spriteRenderer.spriteId <= 85 && this.w.d[WorldManager.u].u() == 0) {
                           WorldManager.a()
                              .a(WorldManager.a().d[WorldManager.u].posX, WorldManager.a().d[WorldManager.u].posY - 40, WorldManager.a().d[WorldManager.u]);
                        }

                        g = false;
                        h = false;
                        this.w.interactionController.aC();
                        var2.setState((byte)1);
                     }
                  }
                  break label1081;
               case 85:
                  if (this.p >= 0 && this.p < 5) {
                     var2.setCommandIndex((byte)(var3.getNumericArguments()[0] - 2));
                     break label1081;
                  }

                  var2.setCommandIndex((byte)(var3.getNumericArguments()[1] - 2));
                  break label1081;
               case 87:
                  if (var2.getState() != 5) {
                     if (var3.getNumericArguments()[0] == 0) {
                        this.x
                           .a(
                              var3.getNumericArguments()[7],
                              var3.getNumericArguments()[1],
                              var3.getNumericArguments()[2],
                              (byte)var3.getNumericArguments()[4],
                              (byte)var3.getNumericArguments()[3],
                              new int[]{1, var3.getNumericArguments()[5], var3.getNumericArguments()[6]}
                           );
                     } else if (var3.getNumericArguments()[0] == 1) {
                        this.x.n(var3.getNumericArguments()[1]);
                     }

                     var2.setState((byte)5);
                     break label1081;
                  }

                  if (!this.y.interactionController.ax()) {
                     break label1081;
                  }
                  break;
               case 88:
                  if (this.x.y() == 2) {
                     var2.setCommandIndex((byte)(var3.getNumericArguments()[0] - 2));
                  } else {
                     var2.setCommandIndex((byte)(var3.getNumericArguments()[1] - 2));
                  }
            }

            var2.setState((byte)1);
         }

         if (var2.getState() != 5 && var2.getState() != 6) {
            var2.advance();
         }

         if (var2.getState() != 3 && var2.getState() != 4) {
            var1++;
         } else {
            h = false;
            this.z.removeElement(var2);
            int var34 = WorldManager.a(var2.getSourceRoom()[0], var2.getSourceRoom()[1]);
            if (this.b[var34] != null) {
               this.b[var34][var2.getEventId()] = var2.getState();
            }

            if (var2.getState() == 3 && var2.getFirstCommand().getOpcode() == 44 && var2.getFirstCommand().getNumericArguments()[1] == 1) {
               b(var2.getFirstCommand().getNumericArguments()[0]);
            }

            this.i();
         }
      }
   }

   public final byte g() {
      return this.A;
   }

   public final boolean h() {
      if (this.a == null) {
         return false;
      }

      for (int var1 = 0; var1 < this.z.size(); var1++) {
         ScriptSequence var2;
         if ((var2 = (ScriptSequence)this.z.elementAt(var1)).getState() != 2 && var2.getState() != 6) {
            return true;
         }
      }

      return false;
   }

   private boolean a(ScriptCommand var1) {
      boolean var2 = false;
      if (var1.getNumericArguments()[7] == -1
         || var1.getNumericArguments()[7] != -1
            && this.b[WorldManager.a(var1.getNumericArguments()[5], var1.getNumericArguments()[6])] != null
            && this.b[WorldManager.a(var1.getNumericArguments()[5], var1.getNumericArguments()[6])][var1.getNumericArguments()[7]] == 3) {
         label58:
         switch (var1.getNumericArguments()[8]) {
            case 0:
               if (!this.x.T[var1.getNumericArguments()[9]]) {
                  return var2;
               }
               break;
            case 1:
               return true;
            case 2:
               if (this.x.O.size() + this.x.partyPetCount < var1.getNumericArguments()[9]) {
                  return var2;
               }

               for (int var3 = 0; var3 < this.x.partyPetCount; var3++) {
                  if (this.x.petParty[var3].getLevel() == var1.getNumericArguments()[10]) {
                     var2 = true;
                     break;
                  }
               }

               if (var2) {
                  return var2;
               }

               for (int var4 = 0; var4 < this.x.O.size(); var4++) {
                  if (((int[])this.x.O.elementAt(var4))[1] == var1.getNumericArguments()[10]) {
                     break label58;
                  }
               }

               return var2;
            case 3:
               if (this.x.F < var1.getNumericArguments()[9]) {
                  return var2;
               }
               break;
            case 4:
               if (this.x.getCollectionFlag((byte)var1.getNumericArguments()[9], var1.getNumericArguments()[10]) != 2) {
                  return var2;
               }
               break;
            case 5:
               if (t <= var1.getNumericArguments()[9]) {
                  return var2;
               }
               break;
            case 6:
               if (t != var1.getNumericArguments()[9]) {
                  return var2;
               }
         }

         var2 = true;
      }

      return var2;
   }

   private boolean b(ScriptCommand var1) {
      boolean var2 = false;
      if (var1.getNumericArguments()[7] == -1
         || var1.getNumericArguments()[7] != -1
            && this.b[WorldManager.a(var1.getNumericArguments()[5], var1.getNumericArguments()[6])] != null
            && this.b[WorldManager.a(var1.getNumericArguments()[5], var1.getNumericArguments()[6])][var1.getNumericArguments()[7]] == 3) {
         switch (var1.getNumericArguments()[8]) {
            case 0:
               if (this.x.getCollectionFlag((byte)var1.getNumericArguments()[9], var1.getNumericArguments()[10]) == 2) {
                  var2 = true;
               }
               break;
            case 1:
               if (this.x.T[var1.getNumericArguments()[9]]) {
                  var2 = true;
               }
               break;
            case 2:
            case 4:
               if (this.b[WorldManager.a(var1.getNumericArguments()[5], var1.getNumericArguments()[6])] != null
                  && this.b[WorldManager.a(var1.getNumericArguments()[5], var1.getNumericArguments()[6])][var1.getNumericArguments()[7]] == 3) {
                  var2 = true;
               }
               break;
            case 3:
               if (this.x.b(var1.getNumericArguments()[9], var1.getNumericArguments()[10], (byte)0)) {
                  var2 = true;
               }
               break;
            case 5:
               if (this.x.F >= var1.getNumericArguments()[9]) {
                  var2 = true;
               }
               break;
            case 6:
               byte[] var5 = new byte[]{0, 1, 2, 3};

               for (int var3 = 0; var3 < this.x.partyPetCount; var3++) {
                  for (int var4 = 0; var4 < var5.length; var4++) {
                     if (var5[var4] != -1 && var5[var4] == GameDatabase.getValue((byte)0, (short)this.x.petParty[var3].getPetId(), (byte)1)) {
                        var5[var4] = -1;
                        break;
                     }
                  }
               }

               int var6 = 0;

               while (var6 < var5.length && var5[var6] == -1) {
                  var6++;
               }

               if (var6 >= var5.length) {
                  var2 = true;
               }
         }
      }

      return var2;
   }

   public final void i() {
      if (d == null) {
         d = new Vector();
      }

      d.removeAllElements();
      Vector var2 = new Vector();

      for (int var3 = 0; var3 < al.length; var3++) {
         if (WorldManager.a(al[var3][0], al[var3][1]) == WorldManager.a(WorldManager.a().f, WorldManager.a().g)
            && (this.a[al[var3][2]].getState() == 0 || this.a[al[var3][2]].getState() == 4)) {
            ScriptCommand var1 = this.a[al[var3][2]].getFirstCommand();
            if (!var2.contains("" + var1.getNumericArguments()[4])) {
               if (this.b(var1)) {
                  WorldEntity var4;
                  (var4 = new WorldEntity()).loadSprite(259, false);
                  var4.setAnimation((byte)1, (byte)-1, true);
                  var4.setPosition(this.w.d[var1.getNumericArguments()[4]].posX, this.w.d[var1.getNumericArguments()[4]].posY - 40);
                  this.w.d[var1.getNumericArguments()[4]].t();
                  var4.ownerEntity = this.w.d[var1.getNumericArguments()[4]];
                  var4.activate();
                  d.addElement(var4);
                  var2.addElement("" + var1.getNumericArguments()[4]);
               } else {
                  int var8 = c(var1.getNumericArguments()[0]);
                  if (var1.getNumericArguments()[1] == 0
                        && this.b[WorldManager.a(ak[var3][0], ak[var3][1])][ak[var3][2]] == 3
                        && this.b[WorldManager.a(al[var3][0], al[var3][1])][al[var3][2]] != 3
                     || var1.getNumericArguments()[1] == 1 && var8 != -1 && s[var8][1] == 1) {
                     WorldEntity var9;
                     (var9 = new WorldEntity()).loadSprite(259, false);
                     var9.setAnimation((byte)15, (byte)-1, true);
                     var9.setPosition(this.w.d[var1.getNumericArguments()[4]].posX, this.w.d[var1.getNumericArguments()[4]].posY - 40);
                     this.w.d[var1.getNumericArguments()[4]].t();
                     var9.ownerEntity = this.w.d[var1.getNumericArguments()[4]];
                     var9.activate();
                     d.addElement(var9);
                     var2.addElement("" + var1.getNumericArguments()[4]);
                  }
               }
            }
         }
      }

      for (int var7 = 0; var7 < ak.length; var7++) {
         if (WorldManager.a(ak[var7][0], ak[var7][1]) == WorldManager.a(WorldManager.a().f, WorldManager.a().g)
            && (this.a[ak[var7][2]].getState() == 0 || this.a[ak[var7][2]].getState() == 4)) {
            ScriptCommand var6 = this.a[ak[var7][2]].getFirstCommand();
            if (!var2.contains("" + var6.getNumericArguments()[4]) && this.a(var6)) {
               WorldEntity var10;
               (var10 = new WorldEntity()).loadSprite(259, false);
               var10.setAnimation((byte)7, (byte)-1, true);
               var10.setPosition(this.w.d[var6.getNumericArguments()[4]].posX, this.w.d[var6.getNumericArguments()[4]].posY - 40);
               this.w.d[var6.getNumericArguments()[4]].t();
               var10.ownerEntity = this.w.d[var6.getNumericArguments()[4]];
               var10.activate();
               d.addElement(var10);
            }
         }
      }
   }

   public final int[] j() {
      int[] var1;
      (var1 = new int[4])[0] = this.ai.get(1);
      var1[1] = this.ai.get(2);
      var1[2] = this.ai.get(5);
      var1[3] = this.ai.get(11);
      return this.aj;
   }

   public final int[] k() {
      return this.aj;
   }

   public final void a(int[] var1) {
      this.aj = var1;
   }
}
