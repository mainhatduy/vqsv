package game;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Vector;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

public final class WorldManager extends BaseScreen {
   private static WorldManager Z;
   public MapEngine a;
   private ParticleEffect aa;
   public StringTable b;
   public Player c;
   public NpcEntity[] d;
   public Vector e;
   private int ab;
   public int f;
   public int g;
   private int ac;
   public short h;
   public short i;
   public int j;
   public String k;
   public static int[] l = new int[]{0, 2, 9, 17, 25, 38, 45, 47, 60, 67, 75, 90};
   public static Image m = null;
   private static Image ad = null;
   private static Image ae = null;
   public static SkillEffect n = null;
   private static SaveStorage[] af = new SaveStorage[10];
   private static byte[][][] ag = null;
   private static short[][][] ah = null;
   private static boolean[][] ai = null;
   private static String[] aj;
   private static short[][] ak;
   private static Vector al = new Vector();
   private static Vector am = new Vector();
   private static Vector an = new Vector();
   private static Vector ao = new Vector();
   public WorldEntity o;
   private WorldEntity ap;
   public WorldEntity p;
   public static int q = 0;
   public static byte r = 0;
   public static byte s;
   public static byte t;
   public static short u = -1;
   public static short v = -1;
   public static byte w = 0;
   public static boolean x = false;
   public static int y = -1;
   public static int z = 0;
   public static int A = 0;
   public static int B = getScreenWidth();
   public static int C = getScreenHeight();
   private static byte[] aq = new byte[]{9, 10, 11};
   private int[] ar;
   private int[] as;
   private short[] at;
   private static String[] au = new String[]{
      "PK6_RMS_ACTOR",
      "PK6_RMS_WORLD",
      "PK6_RMS_EVENT",
      "PK6_RMS_RMS",
      "PK6_RMS_SMS",
      "PK6_RMS_CNTSMS",
      "PK6_RMS_GOLD",
      "PK6_RMS_POKPET",
      "PK6_RMS_CONITEM",
      "PK6_RMS_PETBALL"
   };
   public static boolean D = false;
   public static Vector E = null;
   public static Vector F;
   public static byte G;
   public static Vector H = null;
   public static byte I = 0;
   public static boolean J = false;
   protected static boolean K = false;
   public static byte[] L = new byte[2];
   private Image[] av;
   public OverworldScreen M;
   public static String[] N;
   private byte[][] aw;
   private short[][] ax;
   private int ay;
   private String az;
   private byte aA;
   private byte aB;
   private byte aC;
   private byte aD;
   private int aE;
   private int aF;
   private int aG;
   private int aH;
   private boolean aI;
   private WorldEntity aJ;
   private WorldEntity[] aK;
   private int aL;
   private int[] aM;
   private int[] aN;
   private int[] aO;
   private int[][] aP;
   private int[] aQ;
   public static byte O = -1;
   private boolean aR;
   private boolean[] aS;
   private byte[] aT;
   private byte aU;
   private byte aV;
   private boolean aW;

   public static WorldManager a() {
      if (Z == null) {
         Z = new WorldManager();
      }

      return Z;
   }

   public WorldManager() {
      TileMapRenderer.a();
      this.e = new Vector();
      this.f = 0;
      this.g = 0;
      this.ac = 0;
      this.h = 128;
      this.i = 256;
      this.j = -1;
      this.k = "Gỗ thô";
      this.o = null;
      this.ap = null;
      this.p = null;
      this.ar = new int[]{21, 35, 50, 0, 45};
      this.as = new int[]{9, 0, 20, 3, 9, 1, 17, 1, 9, 2, 9, 4, 9, 6, 86, 5, 9, 6, 58, 6, 9, 5, 21, 2, 9, 4, 3, 0};
      this.at = new short[]{1, 5, 0, 616, 3, 6, 0, 617, 4, 0, 0, 618, 5, 2, 0, 619, 6, 0, 1, 620};
      this.aw = null;
      this.ax = null;
      this.ay = 0;
      this.az = "Ngoại trừ tiến hóa, sủng vật còn có thể dị hoá, dị hoá sau sủng vật đem càng cụ tính công kích. Mặt khác từng chủ thành liên minh huấn luyện sư cũng sẽ cung cấp tiến hóa cùng dị hoá phục vụ, ngươi có thể thường đi xem.";
      this.aI = false;
      this.aJ = null;
      this.aK = null;
      this.aL = 8;
      this.aM = new int[]{2, 1, 73, 158, 3, 3, 216, 165, 4, 5, 161, 338, 5, 3, 111, 385, 5, 5, 112, 124, 6, 1, 140, 100, 7, 2, 48, 58};
      this.aN = new int[]{1, 5, 265, 113, 3, 6, 281, 192, 4, 0, 24, 144, 5, 2, 88, 175, 6, 0, 55, 190};
      this.aO = new int[]{
         16735795, 5708544, 5693667, 28273, 7796622, 1924393, 16774529, 7760896, 3291479, 10268671, 2038828, 13341951, 4443391, 16777215, 1862959, 13886935
      };
      this.aP = new int[][]{
         {
               0,
               0,
               1,
               0,
               386,
               5,
               5,
               5,
               0,
               1,
               1,
               387,
               5,
               5,
               0,
               5,
               1,
               2,
               388,
               5,
               5,
               0,
               10,
               1,
               3,
               389,
               5,
               5,
               5,
               10,
               1,
               4,
               390,
               5,
               5,
               10,
               10,
               1,
               5,
               391,
               5,
               5,
               10,
               15,
               1,
               6,
               392,
               5,
               5,
               10,
               20,
               -1,
               -1,
               518,
               5,
               5
         },
         {
               0,
               0,
               -1,
               -1,
               517,
               5,
               5,
               0,
               5,
               2,
               0,
               393,
               5,
               5,
               0,
               10,
               2,
               1,
               394,
               5,
               5,
               0,
               15,
               2,
               2,
               395,
               5,
               5,
               5,
               10,
               2,
               3,
               396,
               5,
               5,
               5,
               5,
               2,
               4,
               397,
               5,
               5,
               5,
               15,
               2,
               5,
               398,
               5,
               5,
               5,
               20,
               2,
               6,
               399,
               5,
               5,
               5,
               25,
               2,
               7,
               400,
               5,
               5
         },
         {
               15,
               0,
               -1,
               -1,
               518,
               5,
               5,
               15,
               5,
               3,
               0,
               401,
               5,
               5,
               15,
               10,
               3,
               1,
               402,
               5,
               5,
               10,
               10,
               3,
               2,
               403,
               5,
               5,
               10,
               15,
               3,
               3,
               404,
               5,
               5,
               5,
               15,
               3,
               4,
               405,
               5,
               5,
               0,
               15,
               3,
               5,
               406,
               5,
               5,
               15,
               15,
               3,
               6,
               407,
               5,
               5,
               15,
               20,
               3,
               7,
               408,
               5,
               5
         },
         {
               0,
               15,
               4,
               0,
               409,
               5,
               5,
               5,
               15,
               4,
               1,
               410,
               5,
               5,
               10,
               15,
               4,
               5,
               414,
               5,
               5,
               10,
               20,
               4,
               6,
               415,
               5,
               5,
               15,
               20,
               4,
               7,
               416,
               5,
               5,
               20,
               20,
               4,
               8,
               417,
               5,
               5,
               15,
               15,
               4,
               9,
               418,
               5,
               5,
               20,
               15,
               4,
               10,
               419,
               5,
               5,
               15,
               10,
               4,
               11,
               420,
               5,
               5,
               15,
               5,
               4,
               12,
               421,
               5,
               5,
               0,
               10,
               4,
               2,
               411,
               5,
               5,
               5,
               10,
               4,
               3,
               412,
               5,
               5,
               10,
               10,
               4,
               4,
               413,
               5,
               5,
               15,
               0,
               -1,
               -1,
               524,
               5,
               5
         },
         {
               10,
               5,
               5,
               0,
               422,
               5,
               5,
               5,
               5,
               5,
               1,
               423,
               5,
               5,
               0,
               5,
               5,
               2,
               424,
               5,
               5,
               5,
               0,
               5,
               3,
               425,
               5,
               5,
               15,
               5,
               5,
               4,
               426,
               5,
               5,
               20,
               5,
               5,
               5,
               427,
               5,
               5,
               18,
               0,
               5,
               6,
               428,
               5,
               5,
               10,
               10,
               -1,
               -1,
               522,
               5,
               5
         },
         {0, 5, 6, 0, 429, 5, 5, 0, 0, 6, 1, 430, 5, 5},
         {
               5,
               15,
               7,
               0,
               431,
               5,
               5,
               5,
               10,
               7,
               1,
               432,
               5,
               5,
               5,
               5,
               7,
               2,
               433,
               5,
               5,
               0,
               5,
               7,
               3,
               434,
               5,
               5,
               0,
               0,
               7,
               4,
               435,
               5,
               5,
               0,
               10,
               7,
               5,
               436,
               5,
               5,
               0,
               15,
               7,
               6,
               437,
               5,
               5,
               10,
               5,
               7,
               7,
               438,
               5,
               5,
               10,
               0,
               7,
               8,
               439,
               5,
               5,
               15,
               0,
               7,
               9,
               440,
               5,
               5,
               15,
               5,
               7,
               10,
               441,
               5,
               5,
               10,
               10,
               7,
               11,
               442,
               5,
               5,
               10,
               15,
               7,
               12,
               443,
               5,
               5
         },
         {
               5,
               10,
               8,
               0,
               444,
               5,
               5,
               5,
               15,
               8,
               1,
               445,
               5,
               5,
               0,
               15,
               8,
               2,
               446,
               5,
               5,
               0,
               10,
               8,
               3,
               447,
               5,
               5,
               0,
               5,
               8,
               4,
               448,
               5,
               5,
               5,
               5,
               8,
               5,
               449,
               5,
               5,
               5,
               0,
               8,
               6,
               450,
               5,
               5
         }
      };
      this.aQ = new int[]{3, 5, 2, 6, 4, 5, 5, 5, 5, 3, 1, 2, 4, 4, 2, 4};
      this.aR = false;
      this.aS = new boolean[]{false, false, false, false, false, false, false};
      this.aT = new byte[]{10, 15, 20, 30, 40, 50, 100};
      this.aW = false;
      this.a = MapEngine.getInstance();
      this.aa = ParticleEffect.a();
      this.b = new StringTable();
   }

   public final boolean c() {
      return ak[l[this.f] + this.g][2] != -1;
   }

   private void T() {
      if (ak == null) {
         try {
            InputStream var1;
            ak = EngineUtils.readShortRows(var1 = EngineUtils.openResource("/data/script/petArea.mid"));
            var1.close();
         } catch (IOException var4) {
            var4.printStackTrace();
         }
      }

      if (this.c()) {
         int[] var5 = new int[ak[l[this.f] + this.g].length - 5];

         for (int var2 = 0; var2 < var5.length; var2++) {
            var5[var2] = ak[l[this.f] + this.g][var2 + 5];
         }

         for (int var6 = 0; var6 < var5.length / 4; var6++) {
            int[] var3 = new int[4];
            switch (var5[(var6 << 2) + 1]) {
               case 0:
                  System.arraycopy(var5, var6 << 2, var3, 0, var3.length);
                  al.addElement(var3);
                  break;
               case 1:
                  System.arraycopy(var5, var6 << 2, var3, 0, var3.length);
                  am.addElement(var3);
                  break;
               case 2:
                  System.arraycopy(var5, var6 << 2, var3, 0, var3.length);
                  an.addElement(var3);
               case 3:
               default:
                  break;
               case 4:
                  System.arraycopy(var5, var6 << 2, var3, 0, var3.length);
                  ao.addElement(var3);
            }
         }
      }
   }

   private void U() {
      if (this.aw == null) {
         try {
            InputStream var1 = EngineUtils.openResource("/data/script/petRide.mid");
            this.aw = EngineUtils.readByteRows(var1);
            var1.close();
         } catch (IOException var2) {
            var2.printStackTrace();
         }
      }

      this.c.Q = this.aw[l[this.f] + this.g];
      if (this.c.t >= 0 && !this.c.g(this.c.t)) {
         this.c.s();
      }

      this.aA = this.c.Q[4];
      this.aB = -1;

      for (int var3 = 0; var3 < this.aM.length / 4; var3++) {
         if (this.f == this.aM[var3 << 2] && this.g == this.aM[(var3 << 2) + 1]) {
            this.aB = (byte)var3;
            break;
         }
      }

      this.aC = -1;

      for (int var4 = 0; var4 < this.aN.length / 4; var4++) {
         if (this.f == this.aN[var4 << 2] && this.g == this.aN[(var4 << 2) + 1]) {
            this.aC = (byte)var4;
            return;
         }
      }
   }

   private void V() {
      m = null;

      try {
         if (this.ax == null) {
            InputStream var1 = EngineUtils.openResource("/data/script/backPic.mid");
            this.ax = EngineUtils.readShortRows(var1);
            var1.close();
         }
      } catch (IOException var2) {
         var2.printStackTrace();
      }

      for (int var3 = 0; var3 < this.ax.length; var3++) {
         if (this.ax[var3][0] == this.f && this.ax[var3][1] == this.g) {
            if (this.ax[var3][2] == 0) {
               m = EngineUtils.loadImage("/data/img/", "img_" + this.ax[var3][3]);
            } else if (this.ax[var3][2] == 1) {
               BaseScreen.setKeyDelay(this.ax[var3][3] << 16 | this.ax[var3][4] << 8 | this.ax[var3][5]);
            }
            break;
         }

         BaseScreen.setKeyDelay(2996676);
      }

      if (ad == null) {
         ad = EngineUtils.loadImage("/data/tex/", "gold");
      }

      if (ae == null) {
         ae = EngineUtils.loadImage("/data/img/", "img_10023");
      }
   }

   public final boolean d() {
      this.s();
      if (af == null) {
         af = new SaveStorage[10];
      }

      for (int var2 = 0; var2 < af.length; var2++) {
         if (af[var2] == null) {
            af[var2] = new SaveStorage(au[var2]);
         }
      }

      this.M = OverworldScreen.a();
      this.M.a(this);
      this.c = Player.getInstance();
      L[0] = L[1] = -1;
      if (ag == null) {
         ag = new byte[127][][];
         ah = new short[127][][];
         ai = new boolean[127][2];
      }

      if (!this.c.y) {
         if (isGameStarted) {
            this.Y();
            this.aa();
         }

         ac();
      }

      String var4 = "/data/event/";
      int var3 = this.g;
      int var20 = this.f;
      WorldManager var1 = this;
      String var21 = var4 + "scene_" + var20 + ".mid";

      try {
         "".getClass();
         InputStream var22 = ResourceStream.openResource(var21);
         short var5;
         DataInputStream var28;
         short[] var6 = new short[var5 = (var28 = new DataInputStream(var22)).readShort()];

         for (int var7 = 0; var7 < var5; var7++) {
            var6[var7] = var28.readShort();
         }

         short var38 = 0;

         for (int var29 = 0; var29 < var3; var29++) {
            var38 += var6[var29];
         }

         var28.skipBytes(var38);
         var5 = var28.readShort();
         String[] var26 = null;
         if (var5 > 0) {
            var26 = new String[var5];

            for (int var34 = 0; var34 < var5; var34++) {
               var38 = var28.readShort();
               StringBuffer var8 = new StringBuffer();

               for (int var9 = 0; var9 < var38; var9++) {
                  var8.append((char)(var28.read() << 8 | var28.read() & 0xFF));
               }

               var26[var34] = var8.toString();
            }
         }

         byte var35 = var28.readByte();
         StringBuffer var40 = new StringBuffer();

         for (int var43 = 0; var43 < var35; var43++) {
            var40.append((char)(var28.read() << 8 | var28.read() & 0xFF));
         }

         var1.ab = var28.readShort();
         aj = null;
         var28.readShort();
         short var46 = var28.readShort();
         boolean[] var31 = new boolean[2];
         int var11 = var1.g;
         int var10 = var1.f;
         if (ag[l[var10] + var11] == null) {
            var11 = var1.g;
            var10 = var1.f;
            ag[l[var10] + var11] = new byte[var46][3];
            var31[0] = true;
         }

         if (var1.f == 9) {
            var11 = var1.g;
            var10 = var1.f;
            ah[l[var10] + var11] = null;
         }

         var11 = var1.g;
         var10 = var1.f;
         if (ah[l[var10] + var11] == null) {
            var11 = var1.g;
            var10 = var1.f;
            ah[l[var10] + var11] = new short[var46][2];
            var31[1] = true;
         }

         if (var46 > 0) {
            var1.d = new NpcEntity[var46];
            short var36 = -1;

            for (int var41 = 0; var41 < var46; var41++) {
               try {
                  var1.d[var41] = new NpcEntity();
                  short[] var44;
                  (var44 = new short[var28.readShort()])[0] = var28.readByte();
                  var44[1] = var28.readShort();
                  var36 = var44[1];
                  var44[2] = var28.readShort();
                  var44[3] = var28.readShort();
                  var44[4] = var28.readShort();
                  var44[5] = var28.readByte();
                  var44[6] = var28.readByte();
                  switch (var44[0]) {
                     case 0:
                        var44[7] = var28.readByte();
                        var44[8] = var28.readByte();
                        var44[9] = var28.readByte();
                        var44[10] = var28.readByte();
                        var44[11] = var28.readShort();
                        var44[12] = var28.readShort();
                        if (var31[0]) {
                           var11 = var1.g;
                           var10 = var1.f;
                           ag[l[var10] + var11][var41][0] = (byte)var44[2];
                           var11 = var1.g;
                           var10 = var1.f;
                           ag[l[var10] + var11][var41][1] = (byte)var44[5];
                        } else {
                           if (var44[6] != 7 && var44[6] != 6) {
                              var11 = var1.g;
                              var10 = var1.f;
                              var44[2] = ag[l[var10] + var11][var41][0];
                           }

                           var11 = var1.g;
                           var10 = var1.f;
                           var44[5] = ag[l[var10] + var11][var41][1];
                        }

                        if (var31[1]) {
                           var11 = var1.g;
                           var10 = var1.f;
                           ah[l[var10] + var11][var41][0] = var44[3];
                           var11 = var1.g;
                           var10 = var1.f;
                           ah[l[var10] + var11][var41][1] = var44[4];
                        } else {
                           var11 = var1.g;
                           var10 = var1.f;
                           var44[3] = ah[l[var10] + var11][var41][0];
                           var11 = var1.g;
                           var10 = var1.f;
                           var44[4] = ah[l[var10] + var11][var41][1];
                        }

                        if (var44[1] != 324) {
                           var1.d[var41].a(var44, var41);
                           if (!var31[0]) {
                              if (var44[6] == 1) {
                                 if (var44[6] != 7 && var44[6] != 6) {
                                    var11 = var1.g;
                                    var10 = var1.f;
                                    var44[2] = ag[l[var10] + var11][var41][0];
                                 }

                                 NpcEntity var10000 = var1.d[var41];
                                 var11 = var1.g;
                                 var10 = var1.f;
                                 var10000.facingDirection = ag[l[var10] + var11][var41][2];
                                 var1.d[var41].d((byte)var44[2]);
                              }
                           } else {
                              var11 = var1.g;
                              var10 = var1.f;
                              ag[l[var10] + var11][var41][2] = var1.d[var41].facingDirection;
                           }
                        }
                        break;
                     case 1:
                        var44[7] = var28.readByte();
                        var44[8] = var28.readShort();
                        var44[9] = var28.readShort();
                        var44[10] = var28.readShort();
                        if (var31[0] && var44[6] == 3) {
                           var11 = var1.g;
                           var10 = var1.f;
                           ag[l[var10] + var11][var41][0] = (byte)var44[2];
                           var11 = var1.g;
                           var10 = var1.f;
                           ag[l[var10] + var11][var41][1] = (byte)var44[5];
                        }

                        if (var44[6] == 3) {
                           var11 = var1.g;
                           var10 = var1.f;
                           var44[2] = ag[l[var10] + var11][var41][0];
                           var11 = var1.g;
                           var10 = var1.f;
                           var44[5] = ag[l[var10] + var11][var41][1];
                        }

                        var1.d[var41].a(var44, var41);
                        break;
                     case 2:
                        var44[7] = var28.readShort();
                        if (var44[7] == 1) {
                           var44[8] = var28.readByte();
                           var44[9] = var28.readByte();
                           var44[10] = var28.readByte();
                           var44[11] = var28.readByte();
                           var44[12] = var28.readByte();
                        }

                        var1.d[var41].a(var44, var41);
                        break;
                     case 3:
                        var44[7] = var28.readByte();
                        var44[8] = var28.readByte();
                        var44[9] = var28.readByte();
                        var44[10] = var28.readShort();
                        var44[11] = var28.readShort();
                        if (var31[0]) {
                           var11 = var1.g;
                           var10 = var1.f;
                           ag[l[var10] + var11][var41][0] = (byte)var44[2];
                        } else {
                           var11 = var1.g;
                           var10 = var1.f;
                           var44[2] = ag[l[var10] + var11][var41][0];
                        }

                        var1.d[var41].a(var44, var41);
                  }
               } catch (Exception var12) {
                  System.out.println(" k = " + var41 + " e = " + var12 + " actorId = " + var36);
               }
            }

            aj = new String[var38 = var28.readShort()];

            for (int var45 = 0; var45 < var38; var45++) {
               StringBuffer var32 = new StringBuffer();
               byte var37 = var28.readByte();

               for (int var47 = 0; var47 < var37; var47++) {
                  var32.append((char)(var28.readByte() << 8 | var28.readByte() & 0xFF));
               }

               aj[var45] = var32.toString();
            }
         }

         var1.ay = 1;
         var5 = var28.readShort();
         var1.ay = 2;
         if (var5 > 0) {
            var1.M.a(var28, var1.f, var1.g, var5, var26);
         }

         var28.close();
         var22.close();
      } catch (Exception var13) {
         System.out.println(" initRoom = " + var13 + " bug = " + var1.ay);
      }

      var1 = this;
      if (this.p == null) {
         var1.p = new WorldEntity();
         var1.p.loadSprite(259, false);
      }

      this.T();
      this.U();
      this.V();
      var1 = this;
      this.k = getString(384 + l[var1.f] + var1.g);
      var1.a.loadMap(var1.ab);
      var1.a.centerCamera(0, 0);
      var1.b.a(var1.a);
      this.ak();
      if (x) {
         OverworldScreen.f = false;
         var1 = this;
         if (!this.c.y) {
            if (isGameStarted) {
               var1.d(var1.c);
            } else {
               short[] var23 = new short[]{var1.h, var1.i, w, 4, 4, 8, 40, 100, 0};
               var1.c.a(var23);
            }
         } else if (var1.j >= 0) {
            var20 = var1.d[var1.j].posX - var1.d[var1.j].posX % var1.c.baseStats[2];
            var3 = var1.d[var1.j].posY - var1.d[var1.j].posY % var1.c.baseStats[2];
            var1.c.setPosition(var20, var3);
            var1.c.targetEntity.setPosition(var20, var3);
            var1.c.b((byte)0, var1.d[var1.j].C);
            if (var1.d[var1.j].spriteRenderer.spriteId == 222) {
               var1.c.moveInFacingDirection(24);
            } else {
               var1.c.moveInFacingDirection(32);
            }
         } else {
            short[] var25 = new short[]{var1.h, var1.i, w, 4, 4, 8, 40, 100, 0};
            var1.c.a(var25);
         }

         var1.b.a(var1.c);
         var1.c.B();
         var1.c.activate();
         var1.a((WorldEntity)var1.c);
         var1 = this;
         this.aa.a(var1.c, true);
         var1.b.a(var1.aa);
         var1.b.b();
      } else {
         if (!this.c.y) {
            short[] var18 = new short[]{this.h, this.i, w, 4, 4, 8, 40, 100, 0};
            this.c.a(var18);
            this.c.initializeStarterPet();
         }

         if (y == -1) {
            this.aa.a(z, A, true);
            this.b.a(this.aa);
            this.b.b();
         } else {
            this.aa.a(this.d[y], true);
            this.b.a(this.aa);
            this.b.b();
         }

         x = true;
      }

      this.W();
      if (this.f == 3 && this.g == 7) {
         if (this.c.x > 0) {
            this.c.x = 0;
            this.c.c(0);
         }

         if (this.av == null) {
            this.av = new Image[4];

            for (int var19 = 0; var19 < this.av.length; var19++) {
               this.av[var19] = EngineUtils.loadImage("/data/tex/", "down" + var19);
            }
         }

         this.c.s();
         this.c.h(0);
      }

      if (this.f == 5 && this.g == 6 || this.f == 4 && (this.g == 3 || this.g == 4)) {
         if (this.c.B[0][0] == 2) {
            ScreenView.a().a(this.aa.posX, this.aa.posY - this.ar[this.c.t + 1], getScreenWidth(), getScreenHeight(), 110, 110);
         } else {
            ScreenView.a().a(this.aa.posX, this.aa.posY - this.ar[this.c.t + 1], getScreenWidth(), getScreenHeight(), 50, 50);
         }
      } else {
         ScreenView.a().c();
      }

      this.interactionController = ScriptEngine.a();
      this.interactionController.a(this);
      this.uiManager = UIManager.getInstance();
      this.M.i();
      this.M.b();
      J = true;
      this.setScreenMode((byte)0);
      t();
      return true;
   }

   private void W() {
      for (int var1 = 0; var1 < this.d.length; var1++) {
         this.d[var1].f();
         this.b.a(this.d[var1]);
      }
   }

   public final void a(int var1, int var2, WorldEntity var3) {
      if (!this.p.i()) {
         this.p.spriteRenderer.setAnimationWithoutReset((byte)13, (byte)-1);
         this.p.s = 0;
         this.p.activate();
         this.b.a(this.p);
         this.p.setPosition(var1, var2);
         this.p.ownerEntity = var3;
      }
   }

   public final void e() {
      if (this.p != null) {
         this.p.deactivate();
         this.b.b(this.p);
      }
   }

   public final void a(int var1) {
      if (this.o == null) {
         this.o = new WorldEntity();
         this.o.loadSprite(var1, false);
         this.o.s = 1;
      }
   }

   public final void a(WorldEntity var1) {
      if (this.o != null) {
         this.ap = var1;
         this.o.ownerEntity = var1;
         this.o.startFollowing(var1.spriteRenderer.getAnimationId());
         this.o.activate();
         this.b.a(this.o);
      }
   }

   public final void g() {
      if (this.o != null) {
         this.b.b(this.o);
         this.o = null;
      }
   }

   private boolean a(Player var1) {
      try {
         ByteArrayOutputStream var2 = new ByteArrayOutputStream();
         DataOutputStream var3 = new DataOutputStream(var2);
         if (this.f == 9) {
            var3.writeShort(this.interactionController.m[(this.g << 2) + 2]);
            var3.writeShort(this.interactionController.m[(this.g << 2) + 3]);
            var3.writeByte(2);
         } else if (this.f == 3 && this.g == 7) {
            var3.writeShort(240);
            var3.writeShort(40);
            var3.writeByte(0);
         } else {
            var3.writeShort(var1.posX);
            var3.writeShort(var1.posY);
            var3.writeByte(var1.facingDirection);
         }

         for (int var4 = 0; var4 < var1.B.length; var4++) {
            for (int var5 = 0; var5 < var1.B[var4].length; var5++) {
               var3.writeByte(var1.B[var4][var5]);
            }
         }

         for (int var8 = 0; var8 < var1.P.length; var8++) {
            var3.writeByte(var1.P[var8]);
         }

         for (int var9 = 0; var9 < var1.collectionFlags.length; var9++) {
            for (int var20 = 0; var20 < var1.collectionFlags[var9].length; var20++) {
               var3.writeByte(var1.collectionFlags[var9][var20]);
            }
         }

         for (int var10 = 0; var10 < var1.E.length; var10++) {
            var3.writeByte(var1.E[var10]);
         }

         for (int var11 = 0; var11 < var1.collectionOrder.length; var11++) {
            for (int var21 = 0; var21 < var1.collectionOrder[var11].length; var21++) {
               var3.writeByte(this.c.collectionOrder[var11][var21]);
            }
         }

         var3.writeByte(this.c.H);
         var3.writeByte(this.c.G);
         var3.writeByte(this.c.F);
         var3.writeByte(this.c.I);

         for (int var12 = 0; var12 < this.c.quickItemSlots.length; var12++) {
            var3.writeByte(this.c.quickItemSlots[var12]);
         }

         if (!this.j()) {
            return false;
         }

         if (!this.ah()) {
            return false;
         }

         if (!this.af()) {
            return false;
         }

         var3.writeInt(var1.L.size());

         for (int var13 = 0; var13 < var1.L.size(); var13++) {
            int[] var22 = (int[])var1.L.elementAt(var13);
            var3.writeInt(var22.length);

            for (int var6 = 0; var6 < var22.length; var6++) {
               var3.writeInt(var22[var6]);
            }
         }

         var3.writeInt(var1.M.size());

         for (int var14 = 0; var14 < var1.M.size(); var14++) {
            int[] var23 = (int[])var1.M.elementAt(var14);
            var3.writeInt(var23.length);

            for (int var26 = 0; var26 < var23.length; var26++) {
               var3.writeInt(var23[var26]);
            }
         }

         var3.writeInt(var1.N.size());

         for (int var15 = 0; var15 < var1.N.size(); var15++) {
            int[] var24 = (int[])var1.N.elementAt(var15);
            var3.writeInt(var24.length);

            for (int var27 = 0; var27 < var24.length; var27++) {
               var3.writeInt(var24[var27]);
            }
         }

         for (int var16 = 0; var16 < var1.T.length; var16++) {
            var3.writeBoolean(var1.T[var16]);
         }

         if (!this.ad()) {
            return false;
         }

         if (F == null) {
            F = new Vector();
         }

         var3.writeByte(F.size());

         for (int var17 = 0; var17 < F.size(); var17++) {
            String var25 = (String)F.elementAt(var17);
            var3.writeByte(EngineUtils.d(var25));
         }

         for (int var18 = 0; var18 < this.aS.length; var18++) {
            var3.writeBoolean(this.aS[var18]);
         }

         if (this.o == null) {
            var3.writeByte(-1);
         } else {
            var3.writeByte(this.o.spriteRenderer.spriteId);
         }

         var3.write(this.c.u);
         var3.writeInt(q);
         var3.writeBoolean(K);
         long var19 = GameStateController.getInstance().d + GameStateController.getInstance().e - GameStateController.getInstance().f;
         var3.writeLong(var19);
         var3.writeByte(this.c.t);
         af[0].write(var2);
         var2.close();
         var3.close();
         return true;
      } catch (Exception var7) {
         return false;
      }
   }

   private boolean b(Player var1) {
      try {
         ByteArrayInputStream var2 = new ByteArrayInputStream(af[0].read());
         DataInputStream var3 = new DataInputStream(var2);
         this.h = var3.readShort();
         this.i = var3.readShort();
         short var4 = var3.readByte();

         for (int var5 = 0; var5 < var1.B.length; var5++) {
            for (int var6 = 0; var6 < var1.B[var5].length; var6++) {
               var1.B[var5][var6] = var3.readByte();
            }
         }

         for (int var10 = 0; var10 < var1.P.length; var10++) {
            var1.P[var10] = var3.readByte();
         }

         for (int var11 = 0; var11 < var1.collectionFlags.length; var11++) {
            for (int var19 = 0; var19 < var1.collectionFlags[var11].length; var19++) {
               var1.collectionFlags[var11][var19] = var3.readByte();
            }
         }

         for (int var12 = 0; var12 < var1.E.length; var12++) {
            var1.E[var12] = var3.readByte();
         }

         for (int var13 = 0; var13 < var1.collectionOrder.length; var13++) {
            for (int var20 = 0; var20 < var1.collectionOrder[var13].length; var20++) {
               this.c.collectionOrder[var13][var20] = var3.readByte();
            }
         }

         this.c.H = var3.readByte();
         this.c.G = var3.readByte();
         this.c.F = var3.readByte();
         this.c.I = var3.readByte();

         for (int var14 = 0; var14 < this.c.quickItemSlots.length; var14++) {
            this.c.quickItemSlots[var14] = var3.readByte();
         }

         this.aj();
         this.ai();
         this.ag();
         int var15 = var3.readInt();
         var1.L.removeAllElements();

         for (int var21 = 0; var21 < var15; var21++) {
            int[] var7 = new int[var3.readInt()];

            for (int var8 = 0; var8 < var7.length; var8++) {
               var7[var8] = var3.readInt();
            }

            var1.L.addElement(var7);
         }

         var15 = var3.readInt();
         var1.M.removeAllElements();

         for (int var22 = 0; var22 < var15; var22++) {
            int[] var26 = new int[var3.readInt()];

            for (int var31 = 0; var31 < var26.length; var31++) {
               var26[var31] = var3.readInt();
            }

            var1.M.addElement(var26);
         }

         var15 = var3.readInt();
         var1.N.removeAllElements();

         for (int var23 = 0; var23 < var15; var23++) {
            int[] var27 = new int[var3.readInt()];

            for (int var32 = 0; var32 < var27.length; var32++) {
               var27[var32] = var3.readInt();
            }

            var1.N.addElement(var27);
         }

         for (int var24 = 0; var24 < var1.T.length; var24++) {
            var1.T[var24] = var3.readBoolean();
         }

         this.ae();
         if (E == null) {
            E = new Vector();
         }

         E.removeAllElements();
         byte var18;
         int[] var25 = new int[var18 = var3.readByte()];

         for (int var28 = 0; var28 < var18; var28++) {
            var25[var28] = var3.readByte();
            if (this.c.petParty[var25[var28]] != null) {
               this.c.petParty[var25[var28]].w();
               E.addElement(this.c.petParty[var25[var28]]);
            }
         }

         for (int var29 = 0; var29 < this.aS.length; var29++) {
            this.aS[var29] = var3.readBoolean();
         }

         byte var30;
         if ((var30 = var3.readByte()) != -1) {
            this.a(var30);
         }

         this.c.u = var3.readByte();
         q = var3.readInt();
         K = var3.readBoolean();
         GameStateController var10000 = GameStateController.getInstance();
         var10000.d = var10000.d + var3.readLong();
         var1.t = var3.readByte();
         var1.a(new short[]{this.h, this.i, var4, 4, 4, 8, 40, 100, 0});
         var2.close();
         var3.close();
         return true;
      } catch (Exception var9) {
         System.out.println(" ex = " + var9);
         return false;
      }
   }

   private boolean X() {
      try {
         ByteArrayOutputStream var1 = new ByteArrayOutputStream();
         DataOutputStream var2;
         (var2 = new DataOutputStream(var1)).writeInt(this.f);
         var2.writeInt(this.g);

         for (int var3 = 0; var3 < ag.length; var3++) {
            if (ag[var3] == null) {
               var2.writeShort(-1);
            } else {
               var2.writeShort(ag[var3].length);

               for (int var4 = 0; var4 < ag[var3].length; var4++) {
                  if (ag[var3][var4] == null) {
                     var2.writeByte(-1);
                  } else {
                     var2.writeByte(ag[var3][var4].length);

                     for (int var5 = 0; var5 < ag[var3][var4].length; var5++) {
                        var2.writeByte(ag[var3][var4][var5]);
                     }
                  }
               }
            }
         }

         for (int var7 = 0; var7 < ah.length; var7++) {
            if (ah[var7] == null) {
               var2.writeShort(-1);
            } else {
               var2.writeShort(ah[var7].length);

               for (int var8 = 0; var8 < ah[var7].length; var8++) {
                  if (ah[var7][var8] == null) {
                     var2.writeByte(-1);
                  } else {
                     var2.writeByte(ah[var7][var8].length);

                     for (int var9 = 0; var9 < ah[var7][var8].length; var9++) {
                        var2.writeShort(ah[var7][var8][var9]);
                     }
                  }
               }
            }
         }

         af[1].write(var1);
         var1.close();
         var2.close();
         return true;
      } catch (IOException var6) {
         return false;
      }
   }

   private boolean Y() {
      try {
         ByteArrayInputStream var1 = new ByteArrayInputStream(af[1].read());
         DataInputStream var2 = new DataInputStream(var1);
         this.f = var2.readInt();
         this.g = var2.readInt();

         for (int var4 = 0; var4 < ag.length; var4++) {
            short var3;
            if ((var3 = var2.readShort()) == -1) {
               ag[var4] = null;
            } else {
               ag[var4] = new byte[var3][];

               for (int var5 = 0; var5 < ag[var4].length; var5++) {
                  byte var7;
                  if ((var7 = var2.readByte()) == -1) {
                     ag[var4][var5] = null;
                  } else {
                     ag[var4][var5] = new byte[var7];

                     for (int var8 = 0; var8 < ag[var4][var5].length; var8++) {
                        ag[var4][var5][var8] = var2.readByte();
                     }
                  }
               }
            }
         }

         for (int var12 = 0; var12 < ah.length; var12++) {
            short var9;
            if ((var9 = var2.readShort()) == -1) {
               ah[var12] = null;
            } else {
               ah[var12] = new short[var9][];

               for (int var13 = 0; var13 < ah[var12].length; var13++) {
                  byte var10;
                  if ((var10 = var2.readByte()) == -1) {
                     ah[var12][var13] = null;
                  } else {
                     ah[var12][var13] = new short[var10];

                     for (int var11 = 0; var11 < ah[var12][var13].length; var11++) {
                        ah[var12][var13][var11] = var2.readShort();
                     }
                  }
               }
            }
         }

         var1.close();
         var2.close();
         return true;
      } catch (IOException var6) {
         System.out.println(" sceneId ex = " + var6);
         return false;
      }
   }

   private boolean Z() {
      try {
         ByteArrayOutputStream var1 = new ByteArrayOutputStream();
         DataOutputStream var2 = new DataOutputStream(var1);

         for (int var3 = 0; var3 < this.M.b.length; var3++) {
            if (this.M.b[var3] == null) {
               var2.writeByte(-1);
            } else {
               var2.writeByte(this.M.b[var3].length);

               for (int var4 = 0; var4 < this.M.b[var3].length; var4++) {
                  var2.writeByte(this.M.b[var3][var4]);
               }
            }
         }

         var2.writeByte(OverworldScreen.t);
         var2.writeByte(OverworldScreen.u);

         for (int var6 = 0; var6 < OverworldScreen.u; var6++) {
            var2.writeShort(OverworldScreen.s[var6][0]);
            var2.writeShort(OverworldScreen.s[var6][1]);
         }

         int[] var7;
         if ((var7 = this.M.k()) == null) {
            var2.writeByte(-1);
         } else {
            var2.writeByte(var7.length);

            for (int var8 = 0; var8 < var7.length; var8++) {
               var2.writeInt(var7[var8]);
            }

            var2.writeByte(this.M.p);
         }

         af[2].write(var1);
         var1.close();
         var2.close();
         return true;
      } catch (IOException var5) {
         return false;
      }
   }

   private boolean aa() {
      try {
         ByteArrayInputStream var1 = new ByteArrayInputStream(af[2].read());
         DataInputStream var2 = new DataInputStream(var1);

         for (int var4 = 0; var4 < this.M.b.length; var4++) {
            byte var3;
            if ((var3 = var2.readByte()) == -1) {
               this.M.b[var4] = null;
            } else {
               this.M.b[var4] = new byte[var3];

               for (int var8 = 0; var8 < this.M.b[var4].length; var8++) {
                  this.M.b[var4][var8] = var2.readByte();
               }
            }
         }

         OverworldScreen.t = var2.readByte();
         OverworldScreen.u = var2.readByte();

         for (int var10 = 0; var10 < OverworldScreen.u; var10++) {
            OverworldScreen.s[var10][0] = var2.readShort();
            OverworldScreen.s[var10][1] = var2.readShort();
         }

         byte var11;
         if ((var11 = var2.readByte()) != -1) {
            int[] var9 = new int[var11];
            int[] var5 = this.M.j();

            for (int var6 = 0; var6 < var11; var6++) {
               var9[var6] = var2.readInt();
            }

            this.M.a(var9);
            boolean var12 = false;
            if (var5[0] > var9[0] || var5[1] > var9[1] || var5[2] > var9[2] || var5[3] - var9[3] >= 20) {
               var12 = true;
            }

            this.M.p = var2.readByte();
            if (var12) {
               this.M.p = 0;
            }
         }

         x = true;
         var1.close();
         var2.close();
         return true;
      } catch (IOException var7) {
         return false;
      }
   }

   public static boolean h() {
      try {
         ByteArrayOutputStream var0 = new ByteArrayOutputStream();
         DataOutputStream var1 = new DataOutputStream(var0);
         isGameStarted = true;
         var1.writeBoolean(isGameStarted);
         var1.writeBoolean(x);
         var1.writeBoolean(D);
         var1.writeByte(G);
         af[3].write(var0);
         var0.close();
         var1.close();
         return true;
      } catch (IOException var2) {
         return false;
      }
   }

   public static boolean i() {
      if (af[3] == null) {
         af[3] = new SaveStorage(au[3]);
      }

      try {
         ByteArrayInputStream var0 = new ByteArrayInputStream(af[3].read());
         DataInputStream var1;
         isGameStarted = (var1 = new DataInputStream(var0)).readBoolean();
         x = var1.readBoolean();
         D = var1.readBoolean();
         G = var1.readByte();
         var0.close();
         var1.close();
         return true;
      } catch (IOException var2) {
         isGameStarted = false;
         return false;
      }
   }

   private static boolean ab() {
      try {
         ByteArrayOutputStream var0 = new ByteArrayOutputStream();
         DataOutputStream var1;
         (var1 = new DataOutputStream(var0)).writeBoolean(isVipUnlocked);
         af[4].write(var0);
         var0.close();
         var1.close();
         return true;
      } catch (IOException var2) {
         return false;
      }
   }

   private static boolean ac() {
      try {
         ByteArrayInputStream var0 = new ByteArrayInputStream(af[4].read());
         DataInputStream var1 = new DataInputStream(var0);
         isVipUnlocked = true;
         var0.close();
         var1.close();
         return true;
      } catch (IOException var2) {
         return true;
      }
   }

   private boolean ad() {
      try {
         ByteArrayOutputStream var1 = new ByteArrayOutputStream();
         DataOutputStream var2;
         (var2 = new DataOutputStream(var1)).writeInt(this.c.getGold());
         var2.writeInt(this.c.getArenaPoints());
         af[6].write(var1);
         var1.close();
         var2.close();
         return true;
      } catch (IOException var3) {
         return false;
      }
   }

   private boolean ae() {
      try {
         ByteArrayInputStream var1 = new ByteArrayInputStream(af[6].read());
         DataInputStream var2 = new DataInputStream(var1);
         this.c.resetGold();
         this.c.resetArenaPoints();
         this.c.addGold(var2.readInt());
         this.c.addArenaPoints(var2.readInt());
         var1.close();
         var2.close();
         return true;
      } catch (IOException var3) {
         return false;
      }
   }

   private boolean af() {
      try {
         ByteArrayOutputStream var1 = new ByteArrayOutputStream();
         DataOutputStream var2;
         (var2 = new DataOutputStream(var1)).writeInt(this.c.K.size());

         for (int var3 = 0; var3 < this.c.K.size(); var3++) {
            int[] var4 = (int[])this.c.K.elementAt(var3);
            var2.writeInt(var4.length);

            for (int var5 = 0; var5 < var4.length; var5++) {
               var2.writeInt(var4[var5]);
            }
         }

         af[9].write(var1);
         var1.close();
         var2.close();
         return true;
      } catch (IOException var6) {
         return false;
      }
   }

   private boolean ag() {
      try {
         ByteArrayInputStream var1 = new ByteArrayInputStream(af[9].read());
         DataInputStream var2;
         int var3 = (var2 = new DataInputStream(var1)).readInt();
         this.c.K.removeAllElements();

         for (int var4 = 0; var4 < var3; var4++) {
            int[] var5 = new int[var2.readInt()];

            for (int var6 = 0; var6 < var5.length; var6++) {
               var5[var6] = var2.readInt();
            }

            this.c.K.addElement(var5);
         }

         var1.close();
         var2.close();
         return true;
      } catch (IOException var7) {
         return false;
      }
   }

   private boolean ah() {
      try {
         ByteArrayOutputStream var1 = new ByteArrayOutputStream();
         DataOutputStream var2;
         (var2 = new DataOutputStream(var1)).writeInt(this.c.J.size());

         for (int var3 = 0; var3 < this.c.J.size(); var3++) {
            int[] var4 = (int[])this.c.J.elementAt(var3);
            var2.writeInt(var4.length);

            for (int var5 = 0; var5 < var4.length; var5++) {
               var2.writeInt(var4[var5]);
            }
         }

         af[8].write(var1);
         var1.close();
         var2.close();
         return true;
      } catch (IOException var6) {
         return false;
      }
   }

   private boolean ai() {
      try {
         ByteArrayInputStream var1 = new ByteArrayInputStream(af[8].read());
         DataInputStream var2;
         int var3 = (var2 = new DataInputStream(var1)).readInt();
         this.c.J.removeAllElements();

         for (int var4 = 0; var4 < var3; var4++) {
            int[] var5 = new int[var2.readInt()];

            for (int var6 = 0; var6 < var5.length; var6++) {
               var5[var6] = var2.readInt();
            }

            this.c.J.addElement(var5);
         }

         var1.close();
         var2.close();
         return true;
      } catch (IOException var7) {
         return false;
      }
   }

   public final boolean j() {
      try {
         ByteArrayOutputStream var1 = new ByteArrayOutputStream();
         DataOutputStream var2;
         (var2 = new DataOutputStream(var1)).writeByte(this.c.partyPetCount);

         for (int var3 = 0; var3 < this.c.partyPetCount; var3++) {
            int[] var4 = this.c.petParty[var3].toSaveData();
            var2.writeInt(var4.length);

            for (int var5 = 0; var5 < var4.length; var5++) {
               var2.writeInt(var4[var5]);
            }
         }

         af[7].write(var1);
         var1.close();
         var2.close();
         return true;
      } catch (IOException var6) {
         return false;
      }
   }

   private boolean aj() {
      try {
         ByteArrayInputStream var1 = new ByteArrayInputStream(af[7].read());
         DataInputStream var2;
         byte var3 = (var2 = new DataInputStream(var1)).readByte();

         for (int var4 = 0; var4 < this.c.partyPetCount; var4++) {
            this.c.petParty[var4] = null;
         }

         this.c.partyPetCount = 0;

         for (int var8 = 0; var8 < var3; var8++) {
            int[] var5 = new int[var2.readInt()];

            for (int var6 = 0; var6 < var5.length; var6++) {
               var5[var6] = var2.readInt();
            }

            this.c.a(var5);
         }

         var1.close();
         var2.close();
         return true;
      } catch (IOException var7) {
         return false;
      }
   }

   public final boolean k() {
      if (!this.c(this.c)) {
         return false;
      } else if (!this.X()) {
         return false;
      } else if (!this.Z()) {
         return false;
      } else {
         return !h() ? false : ab();
      }
   }

   public final boolean n() {
      if (!ab()) {
         return false;
      } else {
         return !this.ad() ? false : this.af();
      }
   }

   public static void o() {
      ag = null;
      ah = null;

      for (int var0 = 0; var0 < 10; var0++) {
         if (var0 != 4 && af[var0] != null) {
            af[var0].deleteStore();
            af[var0] = null;
         }
      }

      af = null;
   }

   public final boolean p() {
      this.M.a(this);
      this.interactionController.a(this);
      OverworldScreen.f = false;
      J = true;
      this.setScreenMode((byte)0);
      this.uiManager.closeUI("/data/ui/battle.ui");
      return true;
   }

   private void ak() {
      for (int var1 = 0; var1 < this.d.length; var1++) {
         if (this.d[var1].t == 0 && this.d[var1].v == 14) {
            NpcEntity var2;
            (var2 = this.d[var1]).A = 0;

            while (true) {
               byte var10001 = var2.spriteRenderer.getAnimationId();
               int var5 = 16 * (var2.A + 1);
               byte var4 = var10001;
               NpcEntity var3 = var2;
               byte var6 = 0;
               switch (var4) {
                  case 0:
                     var6 = MapEngine.getInstance().getCollisionTile(var3.posX, var3.posY + var5);
                     break;
                  case 1:
                     var6 = MapEngine.getInstance().getCollisionTile(var3.posX + var5, var3.posY);
                     break;
                  case 2:
                     var6 = MapEngine.getInstance().getCollisionTile(var3.posX, var3.posY - var5);
                     break;
                  case 3:
                     var6 = MapEngine.getInstance().getCollisionTile(var3.posX - var5, var3.posY);
               }

               if (var6 != 0) {
                  var2.B = var2.A;
                  var2.A = 0;
                  break;
               }

               var2.A++;
            }
         }
      }
   }

   public final void f() {
      this.b.a();
      this.a.release();
      if (this.d != null) {
         for (int var1 = 0; var1 < this.d.length; var1++) {
            NpcEntity var2;
            (var2 = this.d[var1]).deactivateTarget();
            if (var2.targetEntity != null) {
               var2.targetEntity.spriteRenderer.releaseSprite();
               var2.targetEntity = null;
            }

            if (var2.I != -1 && var2.spriteRenderer.spriteId != 0 && var2.spriteRenderer.spriteId != 8) {
               var2.spriteRenderer.evictSprite();
               var2.spriteRenderer = null;
            }

            if (var2.G != null) {
               var2.G.spriteRenderer.evictSprite();
               var2.G = null;
            }

            if (var2.H != null) {
               var2.H.spriteRenderer.evictSprite();
               var2.H = null;
            }

            var2.I = -1;
            this.d[var1] = null;
         }

         this.d = null;
      }

      m = null;
      ad = null;
      ak = null;
      this.aw = null;
      this.ax = null;
      this.av = null;
      this.p.spriteRenderer.evictSprite();
      this.p = null;
      this.e.removeAllElements();
      al.removeAllElements();
      an.removeAllElements();
      ao.removeAllElements();
      am.removeAllElements();
      if (H != null) {
         H.removeAllElements();
         H = null;
      }

      this.uiManager.releaseAll();
      this.M.f();
      u = -1;
   }

   public final void setScreenMode(byte var1) {
      this.previousScreenMode = this.screenMode;
      switch (var1) {
         case 0:
            BaseScreen.c(1, -1);
            BaseScreen.c(0, 0);
            BaseScreen.U = -1;
            BaseScreen.V = 0;
            if (!OverworldScreen.f) {
               if (J) {
                  this.interactionController.c();
               } else {
                  this.interactionController.d();
               }
            }

            this.c.b((byte)0, this.c.facingDirection);
            break;
         case 1:
            this.interactionController.j = 1;
            this.interactionController.C();
            break;
         case 2:
            if (u != -1 && this.d[u] != null && this.d[u].spriteRenderer.spriteId == 24) {
               this.interactionController.a(4, (byte)0);
            } else if (u != -1 && this.d[u] != null && this.d[u].spriteRenderer.spriteId == 20) {
               this.interactionController.a(3, (byte)2);
            }
            break;
         case 3:
            this.interactionController.L();
         case 4:
         case 33:
         case 34:
         case 35:
         case 36:
         case 37:
         case 38:
         case 39:
         case 40:
         case 41:
         case 42:
         case 43:
         case 44:
         case 45:
         case 46:
         case 47:
         case 48:
         case 49:
         case 50:
         case 51:
         case 52:
         case 53:
         case 54:
         case 55:
         case 56:
         case 57:
         case 58:
         case 59:
         case 60:
         case 61:
         case 62:
         case 63:
         case 64:
         case 65:
         case 66:
         case 67:
         case 68:
         case 69:
         case 70:
         case 71:
         case 72:
         case 73:
         case 74:
         case 75:
         case 76:
         case 77:
         case 78:
         case 79:
         case 80:
         case 81:
         case 82:
         case 83:
         case 84:
         case 85:
         case 86:
         case 87:
         case 88:
         case 89:
         case 90:
         case 91:
         case 92:
         case 93:
         case 94:
         case 95:
         case 96:
         case 97:
         case 98:
         case 99:
         case 103:
         default:
            break;
         case 5:
            this.interactionController.ad();
            break;
         case 6:
            this.interactionController.k();
            break;
         case 7:
            this.interactionController.c = 0;
            this.interactionController.W();
            break;
         case 8:
            this.interactionController.Y();
            break;
         case 9:
            this.interactionController.N();
            break;
         case 10:
            this.interactionController.R();
            break;
         case 11:
            this.interactionController.P();
            break;
         case 12:
            this.interactionController.T();
            break;
         case 13:
            this.interactionController.m();
            break;
         case 14:
            this.interactionController.az();
            break;
         case 15:
            this.interactionController.y();
            break;
         case 16:
            this.interactionController.A();
            break;
         case 17:
            this.interactionController.l = false;
         case 18:
         case 19:
            this.interactionController.c = 0;
            this.interactionController.W();
            break;
         case 20:
            this.interactionController.u();
            break;
         case 21:
            this.interactionController.w();
            break;
         case 22:
            this.interactionController.H();
            this.interactionController.a("Có lưu dữ liệu không?");
            break;
         case 23:
            if (this.previousScreenMode == 7) {
               this.interactionController.a("", this.az, -1);
            } else if (this.d != null) {
               if (this.d[u].spriteRenderer.spriteId == 68) {
                  this.interactionController.a(aj[this.d[u].y], "Muốn lên thuyền đi đâu?", 1);
               } else if (this.d[u].x < 0) {
                  this.interactionController.a(aj[this.d[u].y], N[0], 1);
               } else {
                  this.interactionController.a(aj[this.d[u].y], N[this.d[u].x], 1);
               }
            }
            break;
         case 24:
            this.interactionController.h();
            break;
         case 25:
            this.interactionController.ar();
            break;
         case 26:
            this.interactionController.j = 2;
            this.interactionController.a(4, (byte)0);
            break;
         case 27:
            this.interactionController.aQ();
            break;
         case 28:
            byte var6 = 0;

            while (var6 < this.at.length / 4 && (this.at[var6 << 2] != this.f || this.at[(var6 << 2) + 1] != this.g)) {
               var6++;
            }

            this.interactionController.a(var6, this.at[(var6 << 2) + 2], this.at[(var6 << 2) + 3]);
            break;
         case 29:
            ScreenView.a().c(0, 2);
            break;
         case 30:
            this.interactionController.aO();
            break;
         case 31:
            this.interactionController.f = 0;
            WorldManager var2 = this;
            int var3 = 0;
            var2.al();
            if (var2.aV >= var2.aU) {
               var3 = 1;
            }

            this.aW = var3 != 0;
            if (this.aW) {
               if (this.aU == this.aT.length - 1) {
                  this.interactionController.a(aj[this.d[u].y], getString(613), 1);
               } else if (this.aU == this.aT.length - 2) {
                  this.interactionController.a(aj[this.d[u].y], getString(612), 1);
               } else {
                  int[] var4 = new int[]{this.aT[this.aU], this.aT[this.aU + 1]};
                  this.interactionController.a(aj[this.d[u].y], a(611, var4), 1);
               }
            } else if (this.aU < this.aT.length) {
               String var10001 = aj[this.d[u].y];
               byte var5 = this.aT[this.aU];
               this.interactionController
                  .a(
                     var10001,
                     (var3 = BaseScreen.getString(614).indexOf("%s")) == -1
                        ? BaseScreen.getString(614)
                        : BaseScreen.getString(614).substring(0, var3) + var5 + BaseScreen.getString(614).substring(var3 + 2),
                     1
                  );
            } else {
               this.interactionController.a(aj[this.d[u].y], getString(615), 1);
            }
            break;
         case 32:
            this.interactionController.j = 3;
            this.interactionController.a(3, (byte)2);
            break;
         case 100:
            this.interactionController.aG();
            break;
         case 101:
            this.interactionController.aH();
            break;
         case 102:
            this.interactionController.aJ();
            break;
         case 104:
            this.interactionController.aI();
      }

      this.interactionController.g = true;
      this.screenMode = var1;
      this.resetInputState();
   }

   public final void b() {
      if (this.inputEnabled) {
         this.snapshotInput();
         switch (this.screenMode) {
            case 0:
               WorldManager var10 = this;
               if (!this.M.h() && var10.c.getActionState() < 5 && !var10.interactionController.j() && var10.interactionController.G()) {
                  if (var10.isKeyHeld(4100)) {
                     var10.c.b((byte)1, (byte)2);
                  } else if (var10.isKeyHeld(8448)) {
                     var10.c.b((byte)1, (byte)0);
                  } else if (var10.isKeyHeld(16400)) {
                     var10.c.b((byte)1, (byte)3);
                  } else if (var10.isKeyHeld(32832)) {
                     var10.c.b((byte)1, (byte)1);
                  }

                  if (var10.isKeyPressed(65568)) {
                     if (u != -1) {
                        var10.c.b((byte)0, var10.c.facingDirection);
                        if (OverworldScreen.g) {
                           OverworldScreen.h = true;
                           OverworldScreen.g = false;
                        } else {
                           if (var10.d[u].spriteRenderer.spriteId <= 85) {
                              var10.d[u].m = var10.d[u].facingDirection;
                              switch (var10.c.facingDirection) {
                                 case 0:
                                    var10.d[u].facingDirection = 2;
                                    break;
                                 case 1:
                                    var10.d[u].facingDirection = 3;
                                    break;
                                 case 2:
                                    var10.d[u].facingDirection = 0;
                                    break;
                                 case 3:
                                    var10.d[u].facingDirection = 1;
                              }

                              var10.d[u].d((byte)0);
                           }

                           if (var10.d[u].spriteRenderer.spriteId == 17) {
                              var10.interactionController.d = 0;
                              var10.setScreenMode((byte)27);
                           } else {
                              var10.setScreenMode((byte)23);
                           }
                        }

                        a().e();
                     } else if ((NpcEntity)var10.c.ownerEntity != null && ((NpcEntity)var10.c.ownerEntity).t == 3) {
                        var10.c.v();
                     } else {
                        var10.c.w();
                     }
                  }

                  if (var10.hasNavigationKeyRelease()) {
                     var10.c.b((byte)0, var10.c.facingDirection);
                  }

                  if (var10.isKeyPressed(262144)) {
                     var10.m();
                     var10.interactionController.b = 0;
                     var10.setScreenMode((byte)6);
                  } else if (var10.isKeyPressed(131072)) {
                     var10.interactionController.b = 0;
                     var10.setScreenMode((byte)13);
                  } else if (var10.isKeyPressed(1)) {
                     WorldManager var2 = var10;
                     boolean var17 = true;

                     for (int var4 = 0; var4 < aq.length; var4++) {
                        if (aq[var4] == var2.f) {
                           var17 = false;
                           break;
                        }
                     }

                     if (var17) {
                        for (int var22 = 0; var22 < var2.aP[var2.aA].length / 7; var22++) {
                           if (var2.aP[var2.aA][var22 * 7 + 2] == var2.f && var2.aP[var2.aA][var22 * 7 + 3] == var2.g) {
                              var2.aD = (byte)var22;
                              break;
                           }
                        }

                        var2.aG = (getScreenWidth() >> 1) - (var2.aP[var2.aA][var2.aD * 7] << 4) - 40;
                        var2.aH = (getScreenHeight() >> 1) - (var2.aP[var2.aA][var2.aD * 7 + 1] << 3) - 20;
                        var2.aI = true;
                        if (var2.aJ == null) {
                           var2.aJ = new WorldEntity();
                           var2.aJ.loadSprite(0, false);
                           var2.aJ.setAnimation((byte)3, (byte)-1, false);
                           var2.aJ.activate();
                        }

                        if (var2.aK == null) {
                           var2.aK = new WorldEntity[4];

                           for (int var23 = 0; var23 < var2.aK.length; var23++) {
                              var2.aK[var23] = new WorldEntity();
                              var2.aK[var23].loadSprite(223, false);
                              if (var23 <= 1) {
                                 var2.aK[var23].setPosition(getScreenWidth() >> 1, 20 + var23 * (getScreenHeight() - 20));
                              } else {
                                 var2.aK[var23].setPosition(10 + var23 % 2 * (getScreenWidth() - 20), getScreenHeight() >> 1);
                              }

                              var2.aK[var23].setAnimation((byte)var23, (byte)-1, false);
                              var2.c(var23);
                           }
                        }

                        BattleScreen.a().c = Image.createImage(BaseScreen.getScreenWidth(), BaseScreen.getScreenHeight());
                        Graphics var24 = BattleScreen.a().c.getGraphics();
                        var2.c.b((byte)0, var2.c.facingDirection);
                        var2.b.b(var24);
                        var2.setScreenMode((byte)4);
                     } else {
                        var2.interactionController.b("Khu này không có bản đồ");
                     }
                  } else if (var10.isKeyPressed(2)) {
                     var10.interactionController.b = 0;
                     var10.setScreenMode((byte)10);
                  } else if (var10.isKeyPressed(8)) {
                     var10.interactionController.b = 1;
                     var10.setScreenMode((byte)10);
                  } else if (var10.isKeyPressed(512)) {
                     if (var10.f == 3 && var10.g == 7) {
                        break;
                     }

                     if (var10.c.t >= 0 && var10.M.k) {
                        if (var10.c.r()) {
                           var10.c.s();
                        }
                     } else if (var10.M.k) {
                        var10.setScreenMode((byte)5);
                     }
                  }
               }

               var10.c.q();

               for (int var12 = 0; var12 < var10.d.length; var12++) {
                  if (var10.d[var12] != null) {
                     var10.d[var12].o();
                  }
               }

               if (var10.o != null && var10.o.i()) {
                  var10.o.updateFollowing(var10.ap.spriteRenderer, var10.o.spriteRenderer);
               }

               var10.c.o = var10.c.facingDirection;
               var10.b.b();
               WorldManager var13 = var10;
               if (var10.c.D()) {
                  boolean var31;
                  label508: {
                     WorldManager var18 = var13;
                     byte var25 = MapEngine.getInstance().getCollisionTile(Player.getInstance().posX, Player.getInstance().posY);
                     int[] var5 = null;
                     O = var25;
                     switch (var25) {
                        case 0:
                           if (al.size() <= 0) {
                              var31 = false;
                              break label508;
                           }

                           var5 = (int[])al.elementAt(EngineUtils.randomInt(al.size()));
                           break;
                        case 1:
                           if (am.size() <= 0) {
                              var31 = false;
                              break label508;
                           }

                           var5 = (int[])am.elementAt(EngineUtils.randomInt(am.size()));
                           break;
                        case 2:
                           if (an.size() <= 0) {
                              var31 = false;
                              break label508;
                           }

                           var5 = (int[])an.elementAt(EngineUtils.randomInt(an.size()));
                           break;
                        case 3:
                           var31 = false;
                           break label508;
                        case 4:
                           if (ao.size() <= 0) {
                              var31 = false;
                              break label508;
                           }

                           var5 = (int[])ao.elementAt(EngineUtils.randomInt(ao.size()));
                     }

                     int[] var26 = var5;
                     WorldManager var19 = var18;
                     int var29 = -1;
                     if (var26[2] != -1) {
                        var29 = EngineUtils.randomRange(var26[2], var26[3]);
                     }

                     if (!var19.c()) {
                        var31 = false;
                     } else {
                        int var6 = EngineUtils.randomRange(ak[l[var19.f] + var19.g][3], ak[l[var19.f] + var19.g][4]);
                        var19.c.a((byte)GameDatabase.gameDatabase[0][var26[0]][1], var26[0], (byte)1);
                        BattleScreen.a().a(new int[][]{{var26[0], var6, var29}});
                        var31 = true;
                     }
                  }

                  if (!var31) {
                     var13.c.v = var13.c.C();
                  } else {
                     if (var13.f == 3 && var13.g == 7) {
                        OverworldScreen.j = false;
                     } else {
                        OverworldScreen.j = true;
                     }

                     WorldManager var20 = var13;
                     BattleScreen.a().a = 0;
                     BattleScreen.a().b = 0;
                     BattleScreen.a().c = Image.createImage(BaseScreen.getScreenWidth(), BaseScreen.getScreenHeight());
                     Graphics var27 = BattleScreen.a().c.getGraphics();
                     var20.b.b(var27);
                     var20.c.b((byte)0, var20.c.facingDirection);
                     var20.c.v = var20.c.C();
                     GameStateController.getInstance().setScreenMode((byte)12);
                  }
               }

               if (G == 1 && isVipUnlocked) {
                  var10.setScreenMode((byte)25);
               }

               if (!var10.M.h() && !var10.interactionController.G() && !K && L[0] != -1 && var10.isKeyPressed(32)) {
                  U = 4;
                  K = true;
                  var10.interactionController.c = 0;
                  var10.setScreenMode((byte)7);
                  var10.interactionController.F();
               }

               if (!var10.interactionController.j() && r == 0 && var10.r()) {
                  var10.interactionController.b("Có thể tiến hành sản xuất trứng sủng vật");
                  r = 1;
               }

               var10.M.c();
               var10.interactionController.e();
               var13 = var10;

               for (int var21 = 0; var21 < var13.c.V.size(); var21++) {
                  int[] var28;
                  (var28 = (int[])var13.c.V.elementAt(var21))[3] += 5;
                  if (var28[3] > 30) {
                     var13.c.V.removeElementAt(var21);
                     var21--;
                  }
               }

               if (!var10.interactionController.j()) {
                  var10.M.b();
                  var10.l();
               }
               break;
            case 1:
               this.interactionController.D();
               break;
            case 2:
               if ((u == -1 || this.d[u] == null || this.d[u].spriteRenderer.spriteId != 24) && this.M.c != 0) {
                  if (u != -1 && this.d[u] != null && this.d[u].spriteRenderer.spriteId == 20 || this.M.c == 1) {
                     this.interactionController.a((byte)3, (byte)2);
                  }
               } else {
                  this.interactionController.a((byte)4, (byte)0);
               }
               break;
            case 3:
               this.interactionController.M();
               break;
            case 4:
               if (this.aI) {
                  if (this.aE == this.aG && this.aF == this.aH) {
                     this.aI = false;
                  }

                  int var8;
                  if ((var8 = EngineUtils.a(this.aE, this.aF, this.aG, this.aH)) < this.aL) {
                     this.aE = this.aG;
                     this.aF = this.aH;
                  } else {
                     this.aE = this.aE + (this.aG - this.aE) * this.aL / var8;
                     this.aF = this.aF + (this.aH - this.aF) * this.aL / var8;
                  }
               }

               if (!this.aI) {
                  if (this.isKeyHeld(16400)) {
                     if (this.aE < 0) {
                        this.aE = this.aE + this.aL;
                     }
                  } else if (this.isKeyHeld(32832)) {
                     if (this.aE + (this.aQ[this.aA << 1] << 4) * 5 > getScreenWidth()) {
                        this.aE = this.aE - this.aL;
                     }
                  } else if (this.isKeyHeld(4100)) {
                     if (this.aF < 0) {
                        this.aF = this.aF + this.aL;
                     }
                  } else if (this.isKeyHeld(8448)) {
                     if (this.aF + (this.aQ[(this.aA << 1) + 1] << 3) * 5 > getScreenHeight() - 30) {
                        this.aF = this.aF - this.aL;
                     }
                  } else if (this.isKeyPressed(262145)) {
                     BattleScreen.a().c = null;
                     this.setScreenMode((byte)0);
                  }
               }

               this.aJ.advanceAnimationIfVisible();

               for (int var9 = 0; var9 < this.aK.length; var9++) {
                  this.c(var9);
                  this.aK[var9].advanceAnimationIfVisible();
               }
               break;
            case 5:
               this.interactionController.ae();
               break;
            case 6:
               this.interactionController.l();
               break;
            case 7:
               this.interactionController.X();
               this.l();
               break;
            case 8:
               this.interactionController.ac();
               break;
            case 9:
               this.interactionController.O();
               break;
            case 10:
               this.interactionController.S();
               break;
            case 11:
               this.interactionController.Q();
               break;
            case 12:
               this.interactionController.U();
               break;
            case 13:
               this.interactionController.n();
               break;
            case 14:
               this.interactionController.aA();
               break;
            case 15:
               this.interactionController.z();
               break;
            case 16:
               this.interactionController.B();
               break;
            case 17:
               this.interactionController.Z();
               break;
            case 18:
               this.interactionController.aa();
               break;
            case 19:
               this.interactionController.ab();
               break;
            case 20:
               this.interactionController.v();
               break;
            case 21:
               this.interactionController.x();
               break;
            case 22:
               this.interactionController.K();
               break;
            case 23:
               WorldManager var7 = this;
               if (this.interactionController.c(t, s) && var7.isKeyPressed(196640)) {
                  if (EngineUtils.b < EngineUtils.b()) {
                     EngineUtils.c();
                     var7.interactionController.b(EngineUtils.b);
                  } else {
                     label529: {
                        var7.interactionController.aC();
                        if (var7.previousScreenMode != 7) {
                           if (var7.d[u].spriteRenderer.spriteId <= 85) {
                              NpcEntity var30 = var7.d[u];
                              byte var16 = var7.d[u].m;
                              var30.facingDirection = var7.d[u].m;
                           }

                           var7.d[u].d((byte)0);
                           var7.c.b((byte)0, var7.c.facingDirection);
                           if (var7.d[u].spriteRenderer.spriteId == 24 || var7.d[u].spriteRenderer.spriteId == 20) {
                              var7.setScreenMode((byte)1);
                              break label529;
                           }

                           if (var7.d[u].spriteRenderer.spriteId == 25) {
                              var7.setScreenMode((byte)16);
                              break label529;
                           }

                           if (var7.d[u].spriteRenderer.spriteId == 68) {
                              var7.setScreenMode((byte)28);
                              break label529;
                           }

                           if (u != -1) {
                              a().a(a().d[u].posX, a().d[u].posY - 40, a().d[u]);
                           }
                        }

                        var7.setScreenMode((byte)0);
                     }
                  }
               }

               var7.b.b();
               break;
            case 24:
               this.interactionController.i();
               break;
            case 25:
               this.interactionController.as();
               break;
            case 26:
               this.interactionController.a((byte)4, (byte)0);
               break;
            case 27:
               this.interactionController.aR();
               break;
            case 28:
               this.interactionController.aN();
               break;
            case 29:
               ScreenView.a().b();
               if (ScreenView.a().b) {
                  ScreenView.a().a = -1;
                  GameStateController.getInstance().setScreenMode((byte)23);
               }
               break;
            case 30:
               this.interactionController.aP();
               break;
            case 31:
               WorldManager var1 = this;
               if (this.interactionController.c(t, s) && !var1.interactionController.j() && var1.isKeyPressed(196640)) {
                  if (EngineUtils.b < EngineUtils.b()) {
                     EngineUtils.c();
                     var1.interactionController.b(EngineUtils.b);
                  } else {
                     var1.interactionController.aC();
                     if (var1.d[u].spriteRenderer.spriteId <= 85) {
                        NpcEntity var10000 = var1.d[u];
                        byte var3 = var1.d[u].m;
                        var10000.facingDirection = var1.d[u].m;
                     }

                     var1.d[u].d((byte)0);
                     var1.c.b((byte)0, var1.c.facingDirection);
                     var1.interactionController.f = 1;
                     if (var1.aW) {
                        var1.aS[var1.aU] = true;
                        if (var1.aU < var1.aT.length - 1) {
                           var1.c.addArenaPoints(1);
                           var1.interactionController.b("Đạt được 1 huy hiệu");
                        } else if (var1.c.c((byte)7, (byte)0) == 0) {
                           var1.interactionController.b("Đạt được hoàng kim huy hiệu");
                           var1.c.a((byte)7, (byte)0, (byte)2);
                           OverworldScreen.t = (byte)(OverworldScreen.r.length / 2);
                        }
                     }
                  }
               }

               var1.interactionController.f();
               if (var1.interactionController.f == 1 && var1.interactionController.ax()) {
                  var1.interactionController.d = 0;
                  var1.setScreenMode((byte)27);
               }

               var1.b.b();
               break;
            case 32:
               this.interactionController.a((byte)3, (byte)2);
            case 33:
            case 34:
            case 35:
            case 36:
            case 37:
            case 38:
            case 39:
            case 40:
            case 41:
            case 42:
            case 43:
            case 44:
            case 45:
            case 46:
            case 47:
            case 48:
            case 49:
            case 50:
            case 51:
            case 52:
            case 53:
            case 54:
            case 55:
            case 56:
            case 57:
            case 58:
            case 59:
            case 60:
            case 61:
            case 62:
            case 63:
            case 64:
            case 65:
            case 66:
            case 67:
            case 68:
            case 69:
            case 70:
            case 71:
            case 72:
            case 73:
            case 74:
            case 75:
            case 76:
            case 77:
            case 78:
            case 79:
            case 80:
            case 81:
            case 82:
            case 83:
            case 84:
            case 85:
            case 86:
            case 87:
            case 88:
            case 89:
            case 90:
            case 91:
            case 92:
            case 93:
            case 94:
            case 95:
            case 96:
            case 97:
            case 98:
            case 99:
            case 103:
            default:
               break;
            case 100:
            case 101:
            case 102:
            case 104:
               this.interactionController.aM();
         }

         if (this.screenMode == 0 && !this.M.h() && I == 0 && H != null && H.size() > 0) {
            if (this.ac >= H.size()) {
               H.removeAllElements();
               this.ac = 0;
               I = 1;
            } else if (this.interactionController.ax()) {
               int[] var11 = (int[])H.elementAt(this.ac);
               String var15 = "Tiến hóa";
               if (GameDatabase.gameDatabase[0][GameDatabase.getValue((byte)0, (short)var11[0], (byte)19)][2] == 3) {
                  var15 = "Dị hoá";
               }

               if (!K && L[0] != -1) {
                  if (this.ac == H.size() - 1) {
                     this.interactionController.E();
                     this.interactionController.a("Nhấn #2" + getString(var11[1]) + "#0 đạt tới có thể" + var15 + " điều kiện", "Nhấn nút 5 để tiếp tục");
                  } else {
                     this.interactionController.b("#2" + getString(var11[1]) + "#0 có thể" + var15);
                  }
               } else {
                  this.interactionController.b("#2" + getString(var11[1]) + "#0 có thể" + var15);
               }

               this.ac++;
            }
         }

         this.uiManager.update();
         if (n != null) {
            n.e();
         }
      }
   }

   public static void a(Graphics var0, int var1, int var2, int var3, int var4) {
      var0.setColor(getKeyDelay());
      int var5 = (var1 << 4) - MapEngine.getInstance().cameraX;
      int var6 = (var2 << 4) - MapEngine.getInstance().cameraY;
      var0.fillRect(var5, var6, var3 - var1 << 4, var4 - var2 << 4);
   }

   private void c(int var1) {
      switch (var1) {
         case 0:
            if (this.aF >= 0) {
               this.aK[var1].deactivate();
               return;
            }

            this.aK[var1].activate();
            return;
         case 1:
            if (this.aF + (this.aQ[(this.aA << 1) + 1] << 3) * 5 <= getScreenHeight()) {
               this.aK[var1].deactivate();
               return;
            }

            this.aK[var1].activate();
            return;
         case 2:
            if (this.aE >= 0) {
               this.aK[var1].deactivate();
               return;
            }

            this.aK[var1].activate();
            return;
         case 3:
            if (this.aE + (this.aQ[this.aA << 1] << 4) * 5 <= getScreenWidth()) {
               this.aK[var1].deactivate();
               return;
            } else {
               this.aK[var1].activate();
            }
      }
   }

   private void a(Graphics var1) {
      try {
         if (this.screenMode == 4) {
            Graphics var9 = var1;
            WorldManager var8 = this;
            var9.drawImage(BattleScreen.a().c, 0, 0, 20);

            for (int var10 = 0; var10 < var8.aP[var8.aA].length / 7; var10++) {
               if (var8.f == var8.aP[var8.aA][var10 * 7 + 2] && var8.g == var8.aP[var8.aA][var10 * 7 + 3]) {
                  var9.setColor(188, 122, 255);
               } else {
                  var9.setColor(var8.aO[var8.aA << 1]);
               }

               var9.fillRoundRect(
                  var8.aE + (var8.aP[var8.aA][var10 * 7] << 4),
                  var8.aF + (var8.aP[var8.aA][var10 * 7 + 1] << 3),
                  var8.aP[var8.aA][var10 * 7 + 5] << 4,
                  var8.aP[var8.aA][var10 * 7 + 6] << 3,
                  12,
                  12
               );
               var9.setColor(0);
               var9.drawRoundRect(
                  var8.aE + (var8.aP[var8.aA][var10 * 7] << 4),
                  var8.aF + (var8.aP[var8.aA][var10 * 7 + 1] << 3),
                  var8.aP[var8.aA][var10 * 7 + 5] << 4,
                  var8.aP[var8.aA][var10 * 7 + 6] << 3,
                  12,
                  12
               );
               if (var10 == var8.aD) {
                  var8.aJ
                     .setPosition(
                        var8.aE + (var8.aP[var8.aA][var8.aD * 7] << 4) + 16 * var8.aP[var8.aA][var8.aD * 7 + 5] / 2,
                        var8.aF + (var8.aP[var8.aA][var8.aD * 7 + 1] << 3) + 8 * var8.aP[var8.aA][var8.aD * 7 + 6] / 2 + 20
                     );
                  var8.aJ.renderInWorld(var9, 0, 0);
               }

               EngineUtils.a(
                  var9,
                  getString(var8.aP[var8.aA][var10 * 7 + 4]),
                  var8.aO[(var8.aA << 1) + 1],
                  var8.aE + (var8.aP[var8.aA][var10 * 7] << 4) + 16 * var8.aP[var8.aA][var10 * 7 + 5] / 2,
                  var8.aF + (var8.aP[var8.aA][var10 * 7 + 1] << 3) + 8 * var8.aP[var8.aA][var10 * 7 + 6] / 2 - TextLayoutHelper.a / 2,
                  var8.uiManager.textPainter,
                  -1
               );
            }

            var9.setColor(65280);

            for (int var7 = 0; var7 < var8.e.size(); var7++) {
               int[] var10001 = var8.aP[var8.aA];
               int var10002 = var8.aD * 7;
               int var11 = (((NpcEntity)var8.e.elementAt(var7)).posX * var10001[var10002 + 5] << 4) / MapEngine.getInstance().mapPixelWidth
                  + (var8.aP[var8.aA][var8.aD * 7] << 4)
                  + var8.aE;
               var10001 = var8.aP[var8.aA];
               var10002 = var8.aD * 7;
               int var14 = (((NpcEntity)var8.e.elementAt(var7)).posY * var10001[var10002 + 6] << 3) / MapEngine.getInstance().mapPixelHeight
                  + (var8.aP[var8.aA][var8.aD * 7 + 1] << 3)
                  + var8.aF;
               if (((NpcEntity)var8.e.elementAt(var7)).getActionState() != 0 && ((NpcEntity)var8.e.elementAt(var7)).getActionState() != 1) {
                  var9.fillRect(var11, var14 - 5, 3, 9);
               } else {
                  var9.fillRect(var11, var14 - 2, 9, 3);
               }
            }

            if (var8.aB != -1) {
               int var12 = (var8.aM[(var8.aB << 2) + 2] * var8.aP[var8.aA][var8.aD * 7 + 5] << 4) / MapEngine.getInstance().mapPixelWidth
                  + (var8.aP[var8.aA][var8.aD * 7] << 4)
                  + var8.aE;
               int var15 = (var8.aM[(var8.aB << 2) + 3] * var8.aP[var8.aA][var8.aD * 7 + 6] << 3) / MapEngine.getInstance().mapPixelHeight
                  + (var8.aP[var8.aA][var8.aD * 7 + 1] << 3)
                  + var8.aF;
               var9.setColor(16711680);
               var9.fillRect(var12, var15, 6, 6);
            }

            if (var8.aC != -1) {
               int var13 = (var8.aN[(var8.aC << 2) + 2] * var8.aP[var8.aA][var8.aD * 7 + 5] << 4) / MapEngine.getInstance().mapPixelWidth
                  + (var8.aP[var8.aA][var8.aD * 7] << 4)
                  + var8.aE;
               int var16 = (var8.aN[(var8.aC << 2) + 3] * var8.aP[var8.aA][var8.aD * 7 + 6] << 3) / MapEngine.getInstance().mapPixelHeight
                  + (var8.aP[var8.aA][var8.aD * 7 + 1] << 3)
                  + var8.aF;
               var9.setColor(2758133);
               var9.fillRect(var13, var16, 6, 6);
            }

            var9.setColor(1862801);
            var9.fillRect(0, getScreenHeight() - 30, getScreenWidth(), 30);
            var9.setColor(65280);
            var9.fillRect(25, getScreenHeight() - 22, 16, 16);
            var9.drawString("Cửa ra vào", 45, getScreenHeight() - 25, 20);
            var9.setColor(2758133);
            var9.fillRect(90, getScreenHeight() - 22, 16, 16);
            var9.drawString("Bến tàu", 115, getScreenHeight() - 25, 20);
            var9.setColor(16711680);
            var9.fillRect(155, getScreenHeight() - 22, 16, 16);
            var9.drawString("Đạo quán vào cửa", 175, getScreenHeight() - 25, 20);
         } else {
            if (this.screenMode == 0 || this.screenMode == 23 || this.interactionController.g) {
               this.b.a(var1);
               ScreenView.a().c(var1);
               if (this.interactionController.g) {
                  this.interactionController.g = false;
               }
            }

            if (ScreenView.a().d != -1) {
               if (this.aa.ownerEntity instanceof Player) {
                  ScreenView.a().b(this.aa.posX - MapEngine.getInstance().cameraX, this.aa.posY - MapEngine.getInstance().cameraY - this.ar[this.c.t + 1]);
               } else {
                  ScreenView.a().b(this.aa.posX - MapEngine.getInstance().cameraX, this.aa.posY - MapEngine.getInstance().cameraY - 20);
               }

               ScreenView.a().b(var1);
            }

            OverworldScreen.a(var1);
            this.uiManager.render(var1);
            if (n != null) {
               n.a(var1);
            }

            Graphics var3 = var1;
            WorldManager var2 = this;

            for (int var4 = 0; var4 < var2.c.V.size(); var4++) {
               int[] var5 = (int[])var2.c.V.elementAt(var4);
               EngineUtils.a(var3, "+" + var5[0], 16704699, var5[1] + 12 - var2.a.cameraX, var5[2] - var5[3] - var2.a.cameraY, var2.uiManager.textPainter, 2);
               var3.drawImage(ad, var5[1] - var2.a.cameraX - 6, var5[2] - var5[3] - var2.a.cameraY, 20);
            }

            if (!this.interactionController.j() && !H()) {
               this.M.b(var1);
            }

            if (this.f == 3 && this.g == 7 && this.screenMode == 0) {
               if (this.M.n > 0) {
                  if (this.av != null) {
                     var1.drawImage(this.av[this.M.n - 1], getScreenWidth() >> 1, getScreenHeight() >> 1, 3);
                     return;
                  }
               } else if (GameStateController.getInstance().b != 0L) {
                  var1.setColor(896);
                  var1.setFont(getMediumFont());
                  var1.drawString(a(GameStateController.getInstance().b - GameStateController.getInstance().a)[0], 10, 40, 20);
               }
            }
         }
      } catch (Exception var6) {
         DebugLogger.a(var6, "res = " + this.screenMode);
      }
   }

   public final void q() {
   }

   public static int a(int var0, int var1) {
      return l[var0] + var1;
   }

   public final void a(int var1, int var2, byte var3, boolean var4) {
      int var6 = this.g;
      int var5 = this.f;
      if (ag[l[var5] + var6][var1] != null) {
         var6 = this.g;
         var5 = this.f;
         ai[l[var5] + var6][0] = var4;
         var6 = this.g;
         var5 = this.f;
         ag[l[var5] + var6][var1][var2] = var3;
      }
   }

   public final void a(int var1, int var2, int var3) {
      int var5 = this.g;
      int var4 = this.f;
      if (ah[l[var4] + var5][var1] != null) {
         var5 = this.g;
         var4 = this.f;
         ah[l[var4] + var5][var1][var2] = (short)var3;
      }
   }

   public final boolean b(int var1) {
      for (int var2 = 0; var2 < this.as.length / 4; var2++) {
         if (this.as[var2 << 2] == this.f
            && this.as[(var2 << 2) + 1] == this.g
            && var1 == this.as[(var2 << 2) + 2]
            && this.c.B[this.as[(var2 << 2) + 3]][0] == 2) {
            return true;
         }
      }

      return false;
   }

   public final boolean r() {
      return this.c.I == 0 && q >= 10 || this.c.I > 0 && q >= 30;
   }

   private void al() {
      this.aU = (byte)this.aS.length;

      for (int var1 = 0; var1 < this.aS.length; var1++) {
         if (!this.aS[var1]) {
            this.aU = (byte)var1;
            break;
         }
      }

      boolean var3 = false;

      for (int var2 = this.aT.length - 1; var2 >= 0; var2--) {
         if (this.c.F >= this.aT[var2]) {
            this.aV = (byte)var2;
            var3 = true;
            break;
         }
      }

      if (!var3) {
         this.aV = -1;
      }
   }

   public final void l() {
      switch (U) {
         case 1:
            if (V == 0) {
               c(0, 1);
               if (isVipUnlocked) {
                  c(1, 1);
               } else {
                  c(1, 0);
               }

               V++;
               this.setScreenMode((byte)6);
               return;
            }

            if (V == 1) {
               V++;
               this.interactionController.c("Hãy lựa chọn #2Sủng vật");
               return;
            }

            if (V == 3) {
               c(1, 0);
               String var3 = getString(GameDatabase.gameDatabase[0][this.c.petParty[BaseScreen.K()].getPetId()][0]);
               V++;
               this.interactionController.c("Hãy lựa chọn #2" + var3);
               return;
            }

            if (V == 4) {
               if (this.interactionController.ay() && BaseScreen.b(this.interactionController.b, 0)) {
                  V++;
                  this.interactionController.c("Hãy nhấn #2nút 5");
                  return;
               }
            } else {
               if (V == 6) {
                  V++;
                  this.interactionController.c("Hãy lựa chọn #2Vật phẩm trang sức");
                  return;
               }

               if (V == 8) {
                  c(1, 0);
                  V++;
                  this.interactionController.c("Nhấn #2nút 5#1 trang thượng vật phẩm trang sức");
                  return;
               }

               if (V == 10) {
                  this.interactionController.c("Nhấn #2nút mềm phải#0 để quay lại");
                  c(1, -1);
                  c(0, 2);
                  V++;
                  return;
               }
            }
         case 2:
         case 5:
         default:
            break;
         case 3:
            if (V == 0) {
               V++;
               c(1, 0);
               c(0, 1);
               this.setScreenMode((byte)1);
               return;
            }

            if (V == 1) {
               if (b(this.interactionController.b, 0)) {
                  V++;
                  this.interactionController.c("Hãy nhấn vào mục #2Mua sắm");
                  return;
               }
            } else {
               if (V == 3) {
                  V++;
                  this.interactionController.c("Trước tiên hãy mua #2Hồng sắc ốc biển#1");
                  return;
               }

               if (V == 4) {
                  if (this.interactionController.ay()) {
                     c(1, 1);
                     V++;
                     return;
                  }
               } else if (V == 5) {
                  if (b(this.interactionController.b, 0)) {
                     V++;
                     this.interactionController.c("Nhấn #2nút 5#1 mua sắm");
                     return;
                  }
               } else if (V == 7) {
                  this.interactionController.c("Hãy nhấn #2nút mềm phải#1 để quay lại");
                  c(1, -1);
                  c(0, 2);
                  V++;
                  return;
               }
            }
            break;
         case 4:
            if (V == 0) {
               c(0, 1);

               for (int var1 = 0; var1 < this.c.partyPetCount; var1++) {
                  if (this.c.petParty[var1].getLevel() == L[0] && this.c.petParty[var1].getPetId() == L[1]) {
                     c(1, var1);
                     break;
                  }
               }

               V++;
               String var2 = getString(GameDatabase.getValue((byte)0, (short)this.c.petParty[BaseScreen.K()].getPetId(), (byte)0));
               this.interactionController.c("Hãy lựa chọn #2" + var2 + "#0 tiến hành tiến hóa");
               return;
            }

            if (V == 1) {
               if (b(this.interactionController.b, 0) && this.interactionController.ay()) {
                  V++;
                  this.interactionController.c("Hãy nhấn #2nút 5#0 để tiếp tục");
                  return;
               }
            } else if (V == 3) {
               if (b(this.interactionController.c, 0)) {
                  V++;
                  this.interactionController.c("Nhấn #2nút 5#0 để vào mục Tiến hóa");
                  return;
               }
            } else if (V == 5) {
               V++;
               this.interactionController.c("Nhấn #2nút mềm trái#0 để Tiến hóa");
               return;
            }
            break;
         case 6:
            if (V == 0) {
               V++;
               c(0, 1);
               c(1, 2);
               this.setScreenMode((byte)6);
               return;
            }

            if (V == 1) {
               V++;
               this.interactionController.c("Hãy lựa chọn #2Ba lô#0");
               return;
            }

            if (V == 2) {
               if (this.interactionController.ay() && b(this.interactionController.b, 0)) {
                  this.interactionController.c("Nhấn #2nút mềm trái#0 vào Tuyển hạng");
                  V++;
                  return;
               }
            } else {
               if (V == 4) {
                  V++;
                  this.interactionController.c("Hãy sử dụng #2Gia tốc dược#0");
                  return;
               }

               if (V == 5) {
                  if (this.interactionController.ay() && b(this.interactionController.h, 0)) {
                     V++;
                     this.interactionController.c("Nhấn #2nút mềm trái#0 sử dụng");
                     return;
                  }
               } else {
                  if (V == 7) {
                     V++;
                     c(0, 3);
                     c(2, 1);
                     c(1, 3);
                     this.interactionController.c("Hãy lựa chọn #2Đặc thù đạo cụ#0 ấp trứng trứng sủng vật");
                     return;
                  }

                  if (V == 9) {
                     if (this.interactionController.ay() && b(this.interactionController.h, 0)) {
                        c(0, 1);
                        this.interactionController.c("Nhấn #2nút mềm trái#0 để Ấp trứng");
                        V++;
                        return;
                     }
                  } else if (V == 11) {
                     V++;
                     c(0, 2);
                     c(1, -1);
                     this.interactionController.c("Nhấn #2nút mềm phải#0 để quay lại");
                  }
               }
            }
      }
   }

   public final void m() {
      switch (U) {
         case 1:
            if (V == 2 || V == 7) {
               V++;
               return;
            }

            if (V == 5) {
               c(1, 2);
               V++;
               return;
            }

            if (V == 9) {
               V++;
               int var3 = this.g;
               int var1 = this.f;
               this.M.b[l[var1] + var3][this.M.g()] = 3;
               if (this.M.a != null) {
                  this.M.a[this.M.g()].setState((byte)3);
                  return;
               }
            }
         case 2:
         case 5:
         default:
            break;
         case 3:
            if (V == 6 || V == 2) {
               V++;
               return;
            }
            break;
         case 4:
            if (V == 2) {
               c(1, 5);
               V++;
               return;
            }

            if (V == 4) {
               V++;
               return;
            }

            if (V == 6) {
               this.interactionController.c("Nhấn #2nút mềm phải#0 để quay lại");
               c(1, -1);
               c(0, 2);
               V++;
               return;
            }
            break;
         case 6:
            if (V == 3) {
               for (int var2 = 0; var2 < this.c.J.size() + this.c.K.size(); var2++) {
                  if (var2 >= this.c.K.size()) {
                     if (this.c.J.size() <= 0) {
                        break;
                     }

                     if (((int[])this.c.J.elementAt(var2 - this.c.K.size()))[0] == 14) {
                        c(1, var2);
                        break;
                     }
                  }
               }

               V++;
               return;
            }

            if (V == 6 || V == 10) {
               V++;
               return;
            }

            if (V == 8 && b(this.interactionController.b, 1)) {
               this.interactionController.c("Hãy lựa chọn #2Trứng sủng vật#0 để ấp trứng");
               c(2, 0);
               c(1, 0);
               V++;
            }
      }
   }

   protected static String[] a(long var0) {
      String[] var2;
      (var2 = new String[2])[1] = var2[0] = "0'00\"000";
      long var3 = var0 % 1000L;
      long var11;
      long var5 = (var11 = var0 / 1000L) % 60L;
      long var7 = (var0 = var11 / 60L) % 60L;
      long var9 = var0 / 60L;
      String var15;
      if (var3 < 10L) {
         var15 = "00" + var3;
      } else if (var3 < 100L) {
         var15 = "0" + var3;
      } else {
         var15 = "" + var3;
      }

      String var4;
      if (var5 < 10L) {
         var4 = "0" + var5 + "\"";
      } else {
         var4 = var5 + "\"";
      }

      String var13 = var0 + "'";
      var2[0] = var13 + var4 + var15;
      String var1 = var9 + "'";
      String var14 = var7 + "\"";
      if (var5 < 10L) {
         var4 = "0" + var5;
      } else {
         var4 = "" + var5;
      }

      var2[1] = var1 + var14 + var4;
      return var2;
   }

   public final void b(Graphics var1) {
      try {
         if (super.screenMode != 4) {
            this.a(var1);
         } else {
            int[] var3;
            int var4 = (var3 = this.aP[this.aA]).length;
            int var5 = this.aD * 7;
            var1.drawImage(BattleScreen.a().c, 0, 0, 20);

            for (int var2 = 0; var2 < var4; var2 += 7) {
               if (this.f == var3[var2 + 2] && this.g == var3[var2 + 3]) {
                  var1.setColor(188, 122, 255);
               } else {
                  var1.setColor(this.aO[this.aA << 1]);
               }

               var1.fillRoundRect(this.aE + (var3[var2] << 4), this.aF + (var3[var2 + 1] << 3), var3[var2 + 5] << 4, var3[var2 + 6] << 3, 12, 12);
               var1.setColor(0);
               var1.drawRoundRect(this.aE + (var3[var2] << 4), this.aF + (var3[var2 + 1] << 3), var3[var2 + 5] << 4, var3[var2 + 6] << 3, 12, 12);
               if (var2 == var5) {
                  this.aJ.setPosition(this.aE + (var3[var5] << 4) + 16 * var3[var5 + 5] / 2, this.aF + (var3[var5 + 1] << 3) + 8 * var3[var5 + 6] / 2 + 20);
                  this.aJ.renderInWorld(var1, 0, 0);
               }

               String[] var6;
               int var7 = (var6 = TextLayoutHelper.a(getString(var3[var2 + 4]), var3[var2 + 5] << 4)).length;

               for (int var8 = 0; var8 < var7; var8++) {
                  EngineUtils.a(
                     var1,
                     var6[var8],
                     this.aO[(this.aA << 1) + 1],
                     this.aE + (var3[var2] << 4) + 16 * var3[var2 + 5] / 2,
                     this.aF + (var3[var2 + 1] << 3) + 8 * var3[var2 + 6] / 2 + (var8 - var7 / 2) * (TextLayoutHelper.a + 1),
                     super.uiManager.textPainter,
                     -1
                  );
               }
            }

            var1.setColor(65280);

            for (int var16 = 0; var16 < this.e.size(); var16++) {
               NpcEntity var17;
               int var10 = ((var17 = (NpcEntity)this.e.elementAt(var16)).posX * var3[var5 + 5] << 4) / MapEngine.getInstance().mapPixelWidth
                  + (var3[var5] << 4)
                  + this.aE;
               int var13 = (var17.posY * var3[var5 + 6] << 3) / MapEngine.getInstance().mapPixelHeight + (var3[var5 + 1] << 3) + this.aF;
               if (var17.getActionState() != 0 && var17.getActionState() != 1) {
                  var1.fillRect(var10, var13 - 5, 3, 9);
               } else {
                  var1.fillRect(var10, var13 - 2, 9, 3);
               }
            }

            if (this.aB != -1) {
               int var11 = (this.aM[(this.aB << 2) + 2] * var3[var5 + 5] << 4) / MapEngine.getInstance().mapPixelWidth + (var3[var5] << 4) + this.aE;
               int var14 = (this.aM[(this.aB << 2) + 3] * var3[var5 + 6] << 3) / MapEngine.getInstance().mapPixelHeight + (var3[var5 + 1] << 3) + this.aF;
               var1.setColor(16711680);
               var1.fillRect(var11, var14, 6, 6);
            }

            if (this.aC != -1) {
               int var12 = (this.aN[(this.aC << 2) + 2] * var3[var5 + 5] << 4) / MapEngine.getInstance().mapPixelWidth + (var3[var5] << 4) + this.aE;
               int var15 = (this.aN[(this.aC << 2) + 3] * var3[var5 + 6] << 3) / MapEngine.getInstance().mapPixelHeight + (var3[var5 + 1] << 3) + this.aF;
               var1.setColor(2758133);
               var1.fillRect(var12, var15, 6, 6);
            }

            var1.setColor(1862801);
            var1.fillRect(0, BaseScreen.getScreenHeight() - 30, BaseScreen.getScreenWidth(), 30);
            var1.setColor(65280);
            var1.fillRect(25, BaseScreen.getScreenHeight() - 22, 16, 16);
            TextLayoutHelper.a(var1, "Cửa ra vào", 45, BaseScreen.getScreenHeight() - 18);
            var1.setColor(2758133);
            var1.fillRect(90, BaseScreen.getScreenHeight() - 22, 16, 16);
            TextLayoutHelper.a(var1, "Bến tàu", 115, BaseScreen.getScreenHeight() - 18);
            var1.setColor(16711680);
            var1.fillRect(155, BaseScreen.getScreenHeight() - 22, 16, 16);
            TextLayoutHelper.a(var1, "Cửa đạo quán", 175, BaseScreen.getScreenHeight() - 18);
         }
      } catch (Exception var9) {
      }
   }

   private boolean c(Player var1) {
      af[0].a = true;
      if (this.a(var1)) {
         af[0].a = false;

         try {
            ByteArrayOutputStream var2 = new ByteArrayOutputStream();
            DataOutputStream var3;
            (var3 = new DataOutputStream(var2)).writeInt(var1.O.size());

            for (int var4 = 0; var4 < var1.O.size(); var4++) {
               int[] var6 = (int[])var1.O.elementAt(var4);
               var3.writeInt(var6.length);

               for (int var5 = 0; var5 < var6.length; var5++) {
                  var3.writeInt(var6[var5]);
               }
            }

            var3.flush();
            af[0].a(var2.toByteArray());
            return true;
         } catch (Exception var7) {
            var7.printStackTrace();
            return false;
         }
      } else {
         af[0].a = false;
         return false;
      }
   }

   private boolean d(Player var1) {
      if (!this.b(var1)) {
         return false;
      }

      if (af[0].extraData != null) {
         try {
            ByteArrayInputStream var2 = new ByteArrayInputStream(af[0].extraData);
            int var3;
            DataInputStream var8;
            if ((var3 = (var8 = new DataInputStream(var2)).readInt()) > 0) {
               var1.O.removeAllElements();

               for (int var4 = 0; var4 < var3; var4++) {
                  int[] var5 = new int[var8.readInt()];

                  for (int var6 = 0; var6 < var5.length; var6++) {
                     var5[var6] = var8.readInt();
                  }

                  var1.O.addElement(var5);
               }
            }
         } catch (Exception var7) {
            var7.printStackTrace();
            return false;
         }
      }

      return true;
   }
}
