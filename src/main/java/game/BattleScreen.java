package game;

import java.io.IOException;
import java.io.InputStream;
import java.util.Vector;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

public final class BattleScreen extends BaseScreen {
   private final byte[] n = new byte[]{2, 4};
   private static BattleScreen o;
   private static Player p;
   private byte q = 0;
   public int a;
   public byte b;
   private byte r;
   public Image c;
   private int[][] s;
   public Pet[] d;
   private byte[] t;
   public byte[] e;
   public byte[] f;
   private byte[] u;
   public byte g = 0;
   public Pet h;
   private Vector v;
   public byte i;
   private boolean w;
   private static Vector x;
   public static Vector j;
   private boolean y;
   public boolean k;
   private boolean z;
   private boolean A;
   private boolean B;
   private byte C;
   private byte[] D;
   private byte[] E;
   private byte F;
   private int G;
   private SkillEffect H;
   private byte I = 0;
   private byte J = 0;
   private byte K = 0;
   private byte L = 0;
   private boolean M;
   private boolean N;
   private byte[] O;
   private int[] Z;
   private boolean aa;
   private byte ab;
   private byte ac;
   private byte ad;
   private byte[] ae;
   private byte[][] af;
   private byte[] ag;
   private byte[] ah;
   private byte[][] ai = new byte[][]{{3, 5, 13}, {0, 1, 2, 3, 8, 9, 10}};
   private WorldEntity aj;
   private boolean ak;
   public static byte l = 0;
   private WorldEntity[] al;
   private static short[][] am;
   private static short[][][] an;
   private static byte[][] ao;
   public static short[][] m;
   private static byte[][] ap;
   private static byte[][] aq;
   private static byte[][] ar;
   private int as;
   private int at;
   private int au;
   private int av;
   private int aw;
   private int ax;
   private String ay = null;
   private static Image[] az;
   private static short[][] aA;
   private Vector aB = new Vector();
   private Vector aC = new Vector();
   private int aD;
   private boolean aE;
   private int aF;
   private byte[] aG;
   private byte[] aH;
   private byte[] aI;

   public static BattleScreen a() {
      if (o == null) {
         o = new BattleScreen();
      }

      return o;
   }

   public BattleScreen() {
      new Vector();
      new Vector();
      this.aD = 0;
      this.aE = false;
      this.aF = 0;
      this.aG = new byte[]{10, 11, 12, 13, 15};
      this.aH = new byte[]{10, 12, 13, 14, 15, 16};
      this.aI = new byte[]{105, 100, 80, 60, 40, 20, 5};
      if (this.v == null) {
         this.v = new Vector();
      }

      if (x == null) {
         x = new Vector();
      }

      if (j == null) {
         j = new Vector();
      }
   }

   public final void f() {
      this.v.removeAllElements();

      for (int var1 = 0; var1 < this.f.length; var1++) {
         this.getBattlePet(var1).C();
         this.getBattlePet(var1).D();
         this.getBattlePet(var1).F = 0;
         this.getBattlePet(var1).d(false);
         this.getBattlePet(var1).G.removeAllElements();
         this.getBattlePet(var1).H.removeAllElements();
         this.getBattlePet(var1).u(this.getBattlePet(var1).currentStats[1]);
      }

      x.removeAllElements();
      j.removeAllElements();

      for (int var2 = 0; var2 < this.d.length; var2++) {
         if (this.d[var2] != null) {
            this.d[var2].deactivate();
            this.d[var2] = null;
         }
      }

      for (int var3 = 0; var3 < az.length; var3++) {
         if (az[var3] != null) {
            az[var3] = null;
         }
      }

      az = null;
      if (this.aj != null) {
         this.aj.spriteRenderer.evictSprite();
         this.aj = null;
      }

      if (this.al != null) {
         for (int var4 = 0; var4 < this.al.length; var4++) {
            this.al[var4].spriteRenderer.evictSprite();
            this.al[var4] = null;
         }

         this.al = null;
      }

      this.u = null;
      this.i = 0;
      this.G = 0;
      this.h = null;
      this.w = false;
      this.y = false;
      this.H = null;
      this.d = null;
      this.c = null;
      this.t = null;
      this.e = null;
      this.D = null;
      this.E = null;
      this.ae = null;
      this.O = null;
      ap = null;
      aq = null;
      WorldManager.r = 0;
      am = null;
      an = null;
      ao = null;
      m = null;
      aA = null;
      ap = null;
      aq = null;
      ar = null;
      this.interactionController.af();
   }

   public final boolean d() {
      this.s();
      this.u = new byte[2];
      p = Player.getInstance();
      this.f = new byte[p.partyPetCount];
      int var1 = 0;

      for (int var2 = 0; var2 < p.partyPetCount; var2++) {
         this.f[var2] = (byte)var2;
         if (this.getBattlePet(var2) != null && this.getBattlePet(var2).isAlive()) {
            var1++;
         }

         this.getBattlePet(var2).j(this.getBattlePet(var2).z());
      }

      if (var1 == 1 && this.a == 1) {
         this.d = new Pet[3];
      } else {
         this.d = new Pet[this.n[this.a]];
      }

      try {
         InputStream var10000 = EngineUtils.openResource("/data/script/pos.mid");
         InputStream var5 = null;
         am = EngineUtils.readShortRows(var10000);
         var5 = EngineUtils.openResource("/data/script/cpos.mid");
         an = new short[3][][];

         for (int var11 = 0; var11 < 3; var11++) {
            an[var11] = EngineUtils.readShortRows(var5);
         }

         ao = EngineUtils.readByteRows(EngineUtils.openResource("/data/script/effect.mid"));
         m = EngineUtils.readShortRows(EngineUtils.openResource("/data/script/speffect.mid"));
         aA = EngineUtils.readShortRows(EngineUtils.openResource("/data/script/blood.mid"));
         ap = EngineUtils.readByteRows(var5 = EngineUtils.openResource("/data/script/bufDebuf.mid"));
         aq = EngineUtils.readByteRows(var5);
         ar = EngineUtils.readByteRows(var5);
         var5.close();
      } catch (IOException var3) {
         Object var4 = null;
         var3.printStackTrace();
      }

      if (this.a == 0) {
         if (this.b == 1) {
            this.r = 2;
         } else {
            this.r = 0;
         }
      } else {
         this.r = 1;
      }

      this.al = new WorldEntity[this.d.length + 2];

      for (int var12 = 0; var12 < this.al.length; var12++) {
         this.al[var12] = new WorldEntity();
         this.al[var12].loadSprite(294, false);
         if (var12 == this.d.length + 1) {
            this.al[var12].setAnimation((byte)2, (byte)-1, false);
            if (this.a == 0) {
               this.al[var12].activate();
            }
         } else if (var12 == this.d.length) {
            this.al[var12].setAnimation((byte)1, (byte)-1, false);
            this.al[var12].activate();
            this.al[var12].setVisible(false);
         } else {
            this.al[var12].setAnimation((byte)0, (byte)-1, false);
            this.al[var12].setPosition(an[this.r][var12][2], an[this.r][var12][3]);
            this.al[var12].activate();
         }
      }

      this.D = new byte[this.d.length];
      this.E = new byte[this.d.length];
      this.n(this.d.length);
      int var13 = 0;

      for (int var8 = 0; var8 < this.d.length; var8++) {
         if (this.a == 0) {
            if (var8 <= 0) {
               switch (this.b) {
                  case 0:
                  case 1:
                     this.m(var8);
                     break;
                  case 2:
                     this.m(var8);
               }
            } else {
               while (!this.getBattlePet(this.f[var13]).isAlive()) {
                  var13++;
               }

               this.a(var8, var13);
               x.addElement(this.getBattlePet(this.f[var13]));
               this.e(0, var13);
            }
         } else if (var8 <= 1) {
            switch (this.b) {
               case 0:
               case 1:
                  this.m(var8);
                  break;
               case 2:
                  this.m(var8);
            }
         } else {
            while (!this.getBattlePet(this.f[var13]).isAlive()) {
               var13++;
            }

            this.a(var8, var13);
            x.addElement(this.getBattlePet(this.f[var13]));
            this.e(var8 - 2, var13);
            var13++;
         }
      }

      this.T();
      az = new Image[3];

      for (int var9 = 0; var9 < az.length; var9++) {
         az[var9] = EngineUtils.loadImage("/data/tex/", "blood_" + var9);
      }

      for (int var10 = 0; var10 < p.partyPetCount; var10++) {
         p.petParty[var10].A = p.petParty[var10].currentStats[1];
      }

      this.setScreenMode((byte)0);
      t();
      return true;
   }

   public final void c() {
      int var1 = 0;

      for (int var2 = 0; var2 < p.partyPetCount; var2++) {
         if (this.getBattlePet(var2) != null && this.getBattlePet(var2).isAlive()) {
            var1++;
         }

         this.getBattlePet(var2).j(this.getBattlePet(var2).z());
      }

      Pet[] var5;
      if (this.a == 0) {
         var5 = new Pet[1];
      } else {
         var5 = new Pet[2];
      }

      for (int var3 = 0; var3 < this.d.length; var3++) {
         if (this.d[var3].r() == 1) {
            var5[var3] = this.d[var3];
         }
      }

      if (var1 == 1 && this.a == 1) {
         this.d = new Pet[3];
      } else {
         this.d = new Pet[this.n[this.a]];
      }

      this.al = new WorldEntity[this.d.length + 2];

      for (int var6 = 0; var6 < this.al.length; var6++) {
         this.al[var6] = new WorldEntity();
         this.al[var6].loadSprite(294, false);
         if (var6 == this.d.length + 1) {
            this.al[var6].setAnimation((byte)2, (byte)-1, false);
            if (this.a == 0) {
               this.al[var6].activate();
            }
         } else if (var6 == this.d.length) {
            this.al[var6].setAnimation((byte)1, (byte)-1, false);
            this.al[var6].activate();
            this.al[var6].setVisible(false);
         } else {
            this.al[var6].setAnimation((byte)0, (byte)-1, false);
            this.al[var6].setPosition(an[this.r][var6][2], an[this.r][var6][3]);
            this.al[var6].activate();
         }
      }

      this.D = new byte[this.d.length];
      this.E = new byte[this.d.length];
      this.G = 0;
      this.n(this.d.length);
      int var7 = 0;

      for (int var4 = 0; var4 < this.d.length; var4++) {
         if (this.a == 0) {
            if (var4 <= 0) {
               this.d[var4] = var5[var4];
            } else {
               while (!this.getBattlePet(this.f[var7]).isAlive()) {
                  var7++;
               }

               this.a(var4, var7);
               x.addElement(this.getBattlePet(this.f[var7]));
               this.e(0, var7);
            }
         } else if (var4 <= 1) {
            this.d[var4] = var5[var4];
         } else {
            while (!this.getBattlePet(this.f[var7]).isAlive()) {
               var7++;
            }

            this.a(var4, var7);
            x.addElement(this.getBattlePet(this.f[var7]));
            this.e(var4 - 2, var7);
            var7++;
         }
      }

      this.T();
   }

   public final void e() {
      if (this.a == 1) {
         int var1 = 0;

         while (((Pet)this.v.elementAt(var1)).r() != 0 || ((Pet)this.v.elementAt(var1)).r() == 0 && !((Pet)this.v.elementAt(var1)).isAlive()) {
            var1++;
         }

         if (this.d[0].isAlive()) {
            this.interactionController.b(this.d[this.e[var1]], this.d[0]);
            return;
         }

         this.interactionController.b(this.d[this.e[var1]], this.d[1]);
      }
   }

   public final void g() {
      this.interactionController = ScriptEngine.a();
      this.interactionController.a(this);
      this.uiManager = UIManager.getInstance();
      if (this.a == 0) {
         this.interactionController.a(this.d[1], this.d[0]);
      } else {
         this.interactionController.a(this.d[2], this.d[0]);
      }
   }

   public final void a(int var1, int var2) {
      this.d[var1] = this.getBattlePet(this.f[var2]);
      this.d[var1].d(true);
      this.d[var1].f(0);
      this.d[var1].facingDirection = 0;
      this.d[var1].setPosition(an[this.r][var1][0], an[this.r][var1][1]);
      this.d[var1].activate();
   }

   private void m(int var1) {
      this.d[var1] = new Pet();
      this.d[var1].initPet(this.s[this.u[0]][0], this.s[this.u[0]][1], (short)-1, (byte)2, (short)this.s[this.u[0]][2], (byte)-1);
      this.d[var1].f(1);
      this.d[var1].facingDirection = 1;
      this.d[var1].setPosition(an[this.r][var1][0], an[this.r][var1][1]);
      short var2 = GameDatabase.gameDatabase[0][this.s[this.u[0]][0]][1];
      this.d[var1].learnSkill((byte)(var2 * 10));
      this.d[var1].updateLearnedSkills();
      this.d[var1].activate();
      p.a((byte)this.d[var1].getSpeciesValue((byte)1), this.d[var1].getPetId(), (byte)1);
      this.u[0]++;
   }

   private void n() {
      this.J = this.I;
      this.O = ao[this.h.selectedSkillId];
      if (this.O[this.J * 7 + 1] == 1) {
         this.H = new SkillEffect();
         short var1;
         short var2;
         short var4;
         short var5;
         short var9;
         if (this.O[this.J * 7] == 0) {
            var1 = (short)((Pet)this.h.ownerEntity).posX;
            var2 = (short)((Pet)this.h.ownerEntity).posY;
            var9 = (short)((Pet)this.h.ownerEntity).getPetId();
            var9 = GameDatabase.gameDatabase[0][var9][17];
            var4 = ((Pet)this.h.ownerEntity).p();
            var5 = ((Pet)this.h.ownerEntity).facingDirection;
         } else {
            var1 = (short)this.h.posX;
            var2 = (short)this.h.posY;
            var9 = (short)this.h.getPetId();
            var9 = GameDatabase.gameDatabase[0][var9][17];
            var4 = this.h.p();
            var5 = this.h.facingDirection;
         }

         short[] var6;
         short[] var7 = new short[(var6 = m[this.O[this.J * 7 + 2]]).length + 5];
         System.arraycopy(var6, 1, var7, 6, var6.length - 1);
         short[] var8;
         System.arraycopy(var8 = new short[]{var6[0], var1, var2, var9, var4, var5}, 0, var7, 0, var8.length);
         this.H.a(var7);
         this.H.c(true);
      } else if (this.O[this.J * 7] == 0) {
         ((Pet)this.h.ownerEntity).a(this.O[this.J * 7 + 2], this.O[this.J * 7 + 3]);
      } else {
         this.h.a(this.O[this.J * 7 + 2], this.O[this.J * 7 + 3]);
      }

      this.I++;
   }

   private boolean a(Pet var1) {
      while (!this.b(var1)) {
         for (int var2 = 0; var2 < this.ai[this.ag[this.ad << 1]].length; var2++) {
            if (this.ai[this.ag[this.ad << 1]][var2] == this.ag[(this.ad << 1) + 1]) {
               return false;
            }
         }

         if (this.ag[this.ad << 1] == 0) {
            var1.o(this.ag[(this.ad << 1) + 1]);
            var1.d(this.ag[(this.ad << 1) + 1], this.ah[this.ad]);
         } else if (this.ag[this.ad << 1] == 1) {
            var1.q(this.ag[(this.ad << 1) + 1]);
            var1.c(this.ag[(this.ad << 1) + 1], this.ah[this.ad]);
         }

         if (var1.r() == 0) {
            this.interactionController.a(var1, false);
            this.interactionController.a(var1);
         } else {
            this.interactionController.b(var1, this.aE);
            this.interactionController.b(var1);
         }

         this.ad++;
      }

      return true;
   }

   private void o() {
      Pet var1 = (Pet)this.v.elementAt(this.i);
      this.ac = this.ab;
      this.ae = this.af[this.ad];
      if (this.ae[this.ac << 2] == 1) {
         this.H = new SkillEffect();
         short var2 = (short)var1.posX;
         short var3 = (short)var1.posY;
         short var4 = (short)var1.getPetId();
         var4 = GameDatabase.gameDatabase[0][var4][17];
         short var5 = var1.p();
         short var8 = var1.facingDirection;
         short[] var6;
         short[] var7 = new short[(var6 = m[this.ae[(this.ac << 2) + 1]]).length + 5];
         System.arraycopy(var6, 1, var7, 6, var6.length - 1);
         short[] var9;
         System.arraycopy(var9 = new short[]{var6[0], var2, var3, var4, var5, var8}, 0, var7, 0, var9.length);
         this.H.a(var7);
         this.H.c(true);
      } else {
         var1.a(this.ae[(this.ac << 2) + 1], this.ae[(this.ac << 2) + 2]);
      }

      this.ab++;
   }

   private void a(Pet var1, boolean var2) {
      if (!var1.isAlive()) {
         var1.C();
         var1.D();
         this.interactionController.b(var1);
         this.h(var1);
         this.u[1]++;
      }

      if (this.u[1] >= this.s.length) {
         X();
         this.setScreenMode((byte)8);
      } else if (!var1.isAlive()) {
         for (int var3 = 0; var3 < this.d.length; var3++) {
            if (this.d[var3].m(11) && this.d[this.d[var3].v[11][1]].equals(var1)) {
               this.d[var3].n(11);
            }
         }

         if (this.u[0] < this.s.length) {
            this.m(this.e[this.i]);
            this.g = this.e[this.i];
            this.setScreenMode((byte)15);
         } else {
            this.i++;
            this.p();
         }
      } else {
         if (var2) {
            this.setScreenMode((byte)2);
         }
      }
   }

   private void b(Pet var1, boolean var2) {
      int var3 = 0;

      while (var3 < this.f.length && !this.getBattlePet(this.f[var3]).isAlive()) {
         var3++;
      }

      if (var3 >= this.f.length) {
         this.setScreenMode((byte)9);
      } else if (!var1.isAlive()) {
         for (int var4 = 0; var4 < this.d.length; var4++) {
            if (this.d[var4].m(11) && this.d[this.d[var4].v[11][1]].equals(var1)) {
               this.d[var4].n(11);
            }
         }

         var1.C();
         var1.D();
         this.interactionController.a(var1);
         x.removeElement(var1);
         j.removeElement(var1);
         var1.B = 0;
         var1.d(false);
         var1.F = 0;
         if (this.r()) {
            this.g = this.e[this.i];
            this.setScreenMode((byte)5);
         } else {
            this.i++;
            this.p();
         }
      } else {
         if (var2) {
            if (var1.p(9)) {
               this.setScreenMode((byte)2);
               return;
            }

            this.setScreenMode((byte)20);
         }
      }
   }

   private boolean b(Pet var1) {
      if (this.ad >= this.af.length) {
         this.ad = 0;
         this.ac = this.ab = 0;
         this.af = null;
         this.ag = null;
         this.ah = null;
         this.H = null;
         if (this.screenMode == 12) {
            this.a(var1, true);
         } else if (this.screenMode == 13) {
            this.b(var1, true);
         }

         return true;
      } else {
         return false;
      }
   }

   private void c(Pet var1) {
      this.ad++;
      if (!this.b(var1)) {
         if (this.a(var1)) {
            return;
         }

         this.ac = this.ab = 0;
         this.o();
         if (this.screenMode == 12) {
            if (this.g(var1) == 2) {
               this.ad = 0;
               this.af = null;
               this.ag = null;
               this.ah = null;
            }

            this.a(var1, false);
            return;
         }

         if (this.screenMode == 13) {
            if (this.g(var1) == 1) {
               this.ad = 0;
               this.af = null;
               this.ag = null;
               this.ah = null;
            }

            this.b(var1, false);
         }
      }
   }

   private void n(int var1) {
      this.t = new byte[var1];
      this.e = new byte[var1];

      for (int var2 = 0; var2 < this.t.length; var2++) {
         this.t[var2] = (byte)var2;
      }
   }

   private void e(byte var1) {
      switch (var1) {
         case 0:
            this.aj.setAnimation(var1, (byte)0, true);
            break;
         case 1:
            this.d[0].setVisible(false);
            short var4 = GameDatabase.gameDatabase[0][((Pet)this.h.ownerEntity).getPetId()][17];
            short[] var5 = new short[]{
               8,
               (short)((Pet)this.h.ownerEntity).posX,
               (short)((Pet)this.h.ownerEntity).posY,
               var4,
               0,
               ((Pet)this.h.ownerEntity).facingDirection,
               0,
               9,
               1,
               3,
               0,
               10,
               0,
               0,
               7,
               0,
               -10,
               4,
               0,
               -20
            };
            this.H = new SkillEffect();
            this.H.a(var5);
            this.H.c(true);
            this.H.a();
            this.aj.setAnimation(var1, (byte)-2, true);
            break;
         case 2:
            this.aj.setAnimation(var1, (byte)0, true);
            break;
         case 3:
            this.aj.setAnimation(var1, (byte)-2, true);
            break;
         case 4:
            short var2 = GameDatabase.gameDatabase[0][((Pet)this.h.ownerEntity).getPetId()][17];
            short[] var3 = new short[]{
               8,
               (short)((Pet)this.h.ownerEntity).posX,
               (short)((Pet)this.h.ownerEntity).posY,
               var2,
               0,
               ((Pet)this.h.ownerEntity).facingDirection,
               0,
               8,
               1,
               4,
               1,
               4,
               0,
               -20,
               6,
               0,
               -12,
               8,
               0,
               -4,
               10,
               0,
               0
            };
            this.H = new SkillEffect();
            this.H.a(var3);
            this.H.c(true);
            this.H.a();
            this.aj.setAnimation((byte)1, (byte)-2, true);
      }

      this.q = var1;
   }

   private void a(int var1, boolean var2) {
      this.al[this.d.length + 1].setVisible(var2);
      this.al[this.d.length + 1].setPosition(am[this.a][(var1 << 2) + 2], am[this.a][(var1 << 2) + 3]);
   }

   private void b(int var1, boolean var2) {
      this.al[this.d.length].setVisible(var2);
      this.al[this.d.length].setPosition(am[this.a][(var1 << 2) + 2], am[this.a][(var1 << 2) + 3]);
   }

   public final void setScreenMode(byte var1) {
      this.previousScreenMode = this.screenMode;
      this.screenMode = (byte)var1;
      switch (var1) {
         case 0:
            this.i = 0;

            while (((Pet)this.v.elementAt(this.i)).r() != 0) {
               this.i++;
            }
            break;
         case 1:
            if (this.i >= this.v.size()) {
               this.i = 0;
            }

            for (this.h = (Pet)this.v.elementAt(this.i); this.h.J || !this.h.isAlive(); this.h = (Pet)this.v.elementAt(this.i)) {
               this.i++;
               if (this.h.J) {
                  this.h.J = false;
               }

               if (this.i >= this.v.size()) {
                  this.w = true;
                  this.i = 0;
                  break;
               }
            }

            if (this.h.p(2) && this.h.r() == 0) {
               boolean var11 = false;

               for (int var13 = 0; var13 < this.h.skillUsesRemaining.length; var13++) {
                  if (this.h.skillUsesRemaining[var13] != 0) {
                     var11 = true;
                  }
               }

               if (!var11) {
                  this.interactionController.c("Không còn tinh lực, không cách nào chiến đấu");
                  this.i++;
                  if (this.i >= this.v.size()) {
                     this.w = true;
                     this.i = 0;
                     return;
                  }
               }

               return;
            }
            break;
         case 2:
            return;
         case 3:
            this.interactionController.e((Pet)this.v.elementAt(this.i));
            return;
         case 4:
            this.interactionController.aj();
            return;
         case 5:
            this.interactionController.c = 0;
            this.interactionController.W();
            return;
         case 6:
            this.C = 0;
            this.interactionController.b((Pet)this.h.G.elementAt(this.C), false);
            this.interactionController.b(this.h, (Pet)this.h.G.elementAt(this.C));
            this.interactionController.b((Pet)this.h.G.elementAt(this.C));
            this.a(Integer.parseInt((String)this.h.H.elementAt(this.C)), true);
            return;
         case 7:
            if (this.h.r() == 0) {
               this.interactionController.a(this.h, false);
               this.interactionController.a(this.h);
            } else {
               this.interactionController.b(this.h, false);
               this.interactionController.b(this.h);
            }

            if (((Pet)this.h.ownerEntity).r() == 1) {
               this.interactionController.b((Pet)this.h.ownerEntity, false);
               this.interactionController.b((Pet)this.h.ownerEntity);
            } else {
               this.interactionController.a((Pet)this.h.ownerEntity, false);
               this.interactionController.a((Pet)this.h.ownerEntity);
            }

            this.z = false;
            this.A = false;
            this.n();
            BattleScreen var10 = this;
            if (this.h.r() != ((Pet)var10.h.ownerEntity).r() || var10.h.p(8)) {
               this.Z = this.h.calculateDamage((Pet)this.h.ownerEntity);
            }

            switch (this.h.selectedSkillId) {
               case 52:
               case 58:
                  if (EngineUtils.randomInt(100) > 30) {
                     this.aa = false;
                     break;
                  }
               default:
                  this.aa = true;
            }

            if (this.O[this.J * 7] == 0) {
               this.h.setBattleAnimationState((byte)1);
               return;
            }

            this.h.setBattleAnimationState((byte)0);
            return;
         case 8:
            WorldManager.a().M.l = 0;

            for (int var12 = 0; var12 < x.size(); var12++) {
               int healthLoss = ((Pet)x.elementAt(var12)).A - ((Pet)x.elementAt(var12)).currentStats[1];
               if (isVipUnlocked) {
                  healthLoss = healthLoss % 20 / 100;
               } else {
                  healthLoss = healthLoss % 50 / 100;
               }

               if (healthLoss > 0) {
                  ((Pet)x.elementAt(var12)).l(healthLoss);
                  ((Pet)x.elementAt(var12)).u(((Pet)x.elementAt(var12)).currentStats[1]);
               }
            }

            this.al[0].setPosition(am[0][6], am[0][7]);
            this.interactionController.a(am[0][4], am[0][5]);
            return;
         case 9:
            BattleScreen var6 = this;
            if (WorldManager.a().M.i) {
               var6.setScreenMode((byte)24);
            } else {
               for (int var7 = 0; var7 < p.partyPetCount; var7++) {
                  p.petParty[var7].l(1);
                  p.petParty[var7].u(1);
                  p.petParty[var7].activate();
               }

               GameStateController.getInstance().setScreenMode((byte)10);
            }

            WorldManager.a().M.l = 1;
            WorldManager.a().M.i = true;
            return;
         case 10:
            return;
         case 11:
            this.interactionController.a(4, (byte)0);
            return;
         case 12:
         case 13:
            if (this.h.r() == 0) {
               this.interactionController.a(this.h, false);
               this.interactionController.a(this.h);
            } else {
               this.interactionController.b(this.h, false);
               this.interactionController.b(this.h);
            }

            Pet var5;
            if ((var5 = (Pet)this.v.elementAt(this.i)).m(13) || var5.m(14)) {
               var5.C();
            }

            this.af = new byte[var5.r(0) + var5.r(1)][];
            this.ag = new byte[this.af.length << 1];
            this.ah = new byte[this.af.length];
            int var2 = 0;

            for (int var16 = 0; var16 < 3; var16++) {
               if (var5.x[0][var16] != -1) {
                  this.ah[var2] = (byte)var16;
                  this.af[var2] = ap[ar[0][var5.x[0][var16]]];
                  this.ag[var2 << 1] = 0;
                  this.ag[(var2 << 1) + 1] = var5.x[0][var16];
                  var2++;
               }
            }

            for (int var17 = 0; var17 < 3; var17++) {
               if (var5.x[1][var17] != -1) {
                  this.ah[var2] = (byte)var17;
                  this.af[var2] = aq[ar[1][var5.x[1][var17]]];
                  this.ag[var2 << 1] = 1;
                  this.ag[(var2 << 1) + 1] = var5.x[1][var17];
                  var2++;
               }
            }

            this.ac = this.ab = 0;
            if (!this.a(var5)) {
               this.o();
               return;
            }
            break;
         case 14:
            return;
         case 15:
            this.interactionController.a = 0;
            this.y = true;
            this.G = this.g;
            this.E[this.G] = 0;
            this.v.setElementAt(this.d[this.g], this.t[this.g]);
            this.d[this.g].J = true;
            this.b(this.g, false);
            this.i++;
            return;
         case 16:
            this.interactionController.c = 0;
            this.interactionController.l = false;
            this.interactionController.W();
            return;
         case 17:
            Pet var14;
            this.h.ownerEntity = var14 = this.d[0];
            if (this.aj == null) {
               this.aj = new WorldEntity();
               this.aj.loadSprite(269, false);
            }

            this.aj.setPosition(this.h.posX, this.h.posY);
            this.aj.activate();
            this.e((byte)0);
            this.ak = false;
            int var15 = this.b((int)l);
            if (EngineUtils.randomInt(100) < var15) {
               this.ak = true;
            } else {
               this.ak = false;
            }

            if (U == 0 && V == 5) {
               this.ak = false;
            }

            this.interactionController.f = 0;
            return;
         case 18:
            return;
         case 19:
         case 25:
         case 26:
         case 27:
         case 28:
         case 29:
         case 30:
         case 31:
         case 32:
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
         case 100:
         case 103:
         default:
            break;
         case 20:
            this.h = (Pet)this.v.elementAt(this.i);

            for (int var4 = 0; var4 < this.d.length; var4++) {
               if (this.d[var4].r() == 1 && this.d[var4].isAlive()) {
                  this.interactionController.b(this.d[var4], false);
                  this.interactionController.b(this.d[var4]);
               }
            }

            this.b(this.e[this.i], true);
            this.interactionController.c(this.h);
            if (this.d[0].isAlive()) {
               this.interactionController.b(this.h, this.d[0]);
               return;
            }

            this.interactionController.b(this.h, this.d[1]);
            return;
         case 21:
            Pet var3;
            this.h.ownerEntity = var3 = this.d[0];
            this.interactionController.ah();
            return;
         case 22:
            this.interactionController.an();
            return;
         case 23:
            this.interactionController.ap();
            return;
         case 24:
            this.interactionController.aE();
            return;
         case 101:
            this.interactionController.aH();
            break;
         case 102:
            this.interactionController.aJ();
            return;
         case 104:
            this.interactionController.aI();
            return;
      }
   }

   public final void b() {
      if (this.inputEnabled) {
         this.snapshotInput();
         switch (this.screenMode) {
            case 0:
               this.F++;
               this.D[this.G] = this.E[this.G];
               this.d[this.G].setPosition(an[this.r][this.G][this.E[this.G] << 2], an[this.r][this.G][(this.E[this.G] << 2) + 1]);
               this.al[this.G].setPosition(an[this.r][this.G][(this.E[this.G] << 2) + 2], an[this.r][this.G][(this.E[this.G] << 2) + 3]);
               if (this.a == 1 && this.E[this.G] > an[this.r][this.G].length / 4 - 3 && this.G % 2 == 0 && this.E.length > this.G + 1) {
                  this.E[this.G + 1]++;
                  this.D[this.G + 1] = this.E[this.G + 1];
                  this.d[this.G + 1].setPosition(an[this.r][this.G + 1][this.E[this.G + 1] << 2], an[this.r][this.G + 1][(this.E[this.G + 1] << 2) + 1]);
                  this.al[this.G + 1].setPosition(an[this.r][this.G + 1][(this.E[this.G + 1] << 2) + 2], an[this.r][this.G + 1][(this.E[this.G + 1] << 2) + 3]);
               }

               if (this.a == 0) {
                  this.interactionController.a(this.d[1], this.d[0], this.d[this.G], this.E[this.G] + 1, an[this.r][this.G].length / 4);
               }

               if (this.F > 1) {
                  this.E[this.G]++;
                  this.F = 0;
               }

               if (this.E[this.G] > an[this.r][this.G].length / 4 - 1) {
                  this.E[this.G] = (byte)(an[this.r][this.G].length / 4 - 1);
                  this.D[this.G] = this.E[this.G];
                  this.G++;
                  if (this.G > this.d.length - 1) {
                     this.G = this.d.length - 1;
                     this.setScreenMode((byte)20);
                  }
               }
               break;
            case 1:
               if (this.interactionController.ay()) {
                  if (this.h.r() == 1 && this.b == 0 && (this.h.getPetId() == 33 || this.h.getPetId() == 59) && this.h.currentStats[1] < this.h.baseStats[1]) {
                     this.setScreenMode((byte)10);
                     return;
                  }

                  if (!this.w) {
                     if (this.h.r() == 1) {
                        if (d(this.h)) {
                           this.setScreenMode((byte)12);
                        } else {
                           this.setScreenMode((byte)2);
                        }
                     } else {
                        this.setScreenMode((byte)2);
                     }
                  } else {
                     if (this.y) {
                        this.T();
                        this.i = 0;
                        this.y = false;
                     }

                     this.i = 0;

                     while (((Pet)this.v.elementAt(this.i)).r() != 0 || ((Pet)this.v.elementAt(this.i)).r() == 0 && !((Pet)this.v.elementAt(this.i)).isAlive()) {
                        this.i++;
                     }

                     if (d((Pet)this.v.elementAt(this.i))) {
                        this.setScreenMode((byte)13);
                     } else {
                        this.setScreenMode((byte)20);
                     }

                     this.w = false;
                  }
               }

               this.interactionController.g();
               this.al[this.d.length].advanceAnimationIfVisible();
               break;
            case 2:
               if (this.h.r() == 1) {
                  byte var10 = (byte)e(this.h);
                  if (this.h.p(9)) {
                     this.f(this.h);
                  } else {
                     boolean var21 = true;
                     if (Pet.b(var10, (byte)9) == 0 && this.h.p(8) && EngineUtils.randomInt(100) > GameDatabase.getValue((byte)1, var10, (byte)8)) {
                        this.f(this.h);
                        var21 = false;
                     }

                     if (var21) {
                        this.b(var10);
                     }
                  }

                  int var22 = EngineUtils.randomInt(this.h.G.size());
                  Pet var27 = (Pet)this.h.G.elementAt(var22);
                  this.h.I = Byte.parseByte((String)this.h.H.elementAt(var22));
                  this.interactionController.b(this.h, var27);
                  this.h.a(var10, var27);
                  this.setScreenMode((byte)7);
               } else if (this.h.p(9)) {
                  this.f(this.h);
                  int var11 = EngineUtils.randomInt(this.h.G.size());
                  Pet var23 = (Pet)this.h.G.elementAt(var11);
                  this.h.I = Byte.parseByte((String)this.h.H.elementAt(var11));
                  byte var28 = (byte)e(this.h);
                  this.h.a(var28, var23);
                  this.interactionController.b(this.h, var23);
                  this.setScreenMode((byte)7);
               } else {
                  boolean var12 = true;
                  if (Pet.b(this.h.H(), (byte)9) == 0 && this.h.p(8) && EngineUtils.randomInt(100) > GameDatabase.getValue((byte)1, this.h.H(), (byte)8)) {
                     this.f(this.h);
                     var12 = false;
                  }

                  if (var12) {
                     if ((Pet)this.h.ownerEntity != null && !((Pet)this.h.ownerEntity).isAlive()) {
                        for (int var24 = 0; var24 < this.h.G.size(); var24++) {
                           if (((Pet)this.h.G.elementAt(var24)).isAlive()) {
                              this.h.I = Byte.parseByte((String)this.h.H.elementAt(var24));
                              this.h.a(this.h.H(), (Pet)this.h.G.elementAt(var24));
                           }
                        }
                     } else {
                        this.h.a(this.h.H(), (Pet)this.h.ownerEntity);
                     }
                  }

                  this.interactionController.b(this.h, (Pet)this.h.ownerEntity);
                  this.setScreenMode((byte)7);
               }
               break;
            case 3:
               this.interactionController.f((Pet)this.v.elementAt(this.i));
               break;
            case 4:
               this.interactionController.ak();
               break;
            case 5:
               this.interactionController.X();
               break;
            case 6:
               if (this.isKeyPressed(4100)) {
                  if (this.a == 1) {
                     this.C--;
                     if (this.C <= 0) {
                        this.C = 0;
                     }

                     this.a(Integer.parseInt((String)this.h.H.elementAt(this.C)), true);
                     this.interactionController.b((Pet)this.h.G.elementAt(this.C), false);
                     this.interactionController.b(this.h, (Pet)this.h.G.elementAt(this.C));
                     this.interactionController.b((Pet)this.h.G.elementAt(this.C));
                  }
               } else if (this.isKeyPressed(8448)) {
                  if (this.a == 1) {
                     this.C++;
                     if (this.C >= this.h.G.size() - 1) {
                        this.C = (byte)(this.h.G.size() - 1);
                     }

                     this.a(Integer.parseInt((String)this.h.H.elementAt(this.C)), true);
                     this.interactionController.b((Pet)this.h.G.elementAt(this.C), false);
                     this.interactionController.b(this.h, (Pet)this.h.G.elementAt(this.C));
                     this.interactionController.b((Pet)this.h.G.elementAt(this.C));
                  }
               } else if (this.isKeyPressed(16400)) {
                  if (this.a == 1) {
                     this.C--;
                     if (this.C <= 0) {
                        this.C = 0;
                     }

                     this.a(Integer.parseInt((String)this.h.H.elementAt(this.C)), true);
                     this.interactionController.b((Pet)this.h.G.elementAt(this.C), false);
                     this.interactionController.b(this.h, (Pet)this.h.G.elementAt(this.C));
                     this.interactionController.b((Pet)this.h.G.elementAt(this.C));
                  }
               } else if (this.isKeyPressed(32832)) {
                  if (this.a == 1) {
                     this.C++;
                     if (this.C >= this.h.G.size() - 1) {
                        this.C = (byte)(this.h.G.size() - 1);
                     }

                     this.a(Integer.parseInt((String)this.h.H.elementAt(this.C)), true);
                     this.interactionController.b((Pet)this.h.G.elementAt(this.C), false);
                     this.interactionController.b(this.h, (Pet)this.h.G.elementAt(this.C));
                     this.interactionController.b((Pet)this.h.G.elementAt(this.C));
                  }
               } else if (this.isKeyPressed(196640)) {
                  this.i();
               } else if (this.isKeyPressed(786432)) {
                  this.a(Integer.parseInt((String)this.h.H.elementAt(this.C)), false);
                  this.setScreenMode((byte)3);
               }

               this.al[this.d.length].advanceAnimationIfVisible();
               this.al[this.d.length + 1].advanceAnimationIfVisible();
               break;
            case 7:
               if (this.h.activeSkillEffect != null) {
                  if (this.h.p() == 0) {
                     if (this.h.activeSkillEffect.i()) {
                        if (this.h.activeSkillEffect.a.isLastAnimationStep()) {
                           this.h.activeSkillEffect.b();
                           this.h.activeSkillEffect = null;
                           if (this.I <= this.O.length / 7 - 1 && !this.W()) {
                              this.n();
                              if (this.h.activeSkillEffect != null) {
                                 this.h.activeSkillEffect.a();
                              }

                              if (this.H != null) {
                                 this.M = true;
                              }
                           } else {
                              this.A = true;
                              this.B = true;
                           }
                        } else if (this.O[this.J * 7 + 4] != -1 && this.h.activeSkillEffect.a(this.O[this.J * 7 + 4])) {
                           this.h.activeSkillEffect.b();
                           if (this.I < this.O.length / 7 - 1 || this.W()) {
                              this.n();
                              if (this.H != null) {
                                 this.M = true;
                              }
                           }
                        } else if (this.O[this.J * 7 + 5] != -1 && this.h.activeSkillEffect.a(this.O[this.J * 7 + 5])) {
                           this.h.setBattleAnimationState(this.O[this.J * 7 + 6]);
                        }
                     } else {
                        this.N = false;
                        this.h.activeSkillEffect.a();
                     }
                  } else if (this.h.p() == 1 && this.h.spriteRenderer.isLastAnimationStep()) {
                     this.h.setBattleAnimationState((byte)0);
                  }
               } else if (((Pet)this.h.ownerEntity).activeSkillEffect != null) {
                  if (((Pet)this.h.ownerEntity).activeSkillEffect.i()) {
                     if (((Pet)this.h.ownerEntity).activeSkillEffect.d()) {
                        ((Pet)this.h.ownerEntity).activeSkillEffect.b();
                        if (this.I <= this.O.length / 7 - 1 && !this.W()) {
                           if (this.O[this.I * 7] == 1) {
                              ((Pet)this.h.ownerEntity).setBattleAnimationState((byte)2);
                           } else {
                              this.K = 0;
                              ((Pet)this.h.ownerEntity).activeSkillEffect = null;
                              this.n();
                              if (((Pet)this.h.ownerEntity).activeSkillEffect != null) {
                                 ((Pet)this.h.ownerEntity).activeSkillEffect.a();
                              }

                              if (this.H != null) {
                                 this.M = true;
                              }
                           }
                        } else {
                           ((Pet)this.h.ownerEntity).setBattleAnimationState((byte)2);
                        }
                     } else {
                        if (this.O[this.J * 7 + 5] != -1) {
                           this.K = this.J;
                        }

                        if (this.O[this.K * 7 + 5] != -1 && ((Pet)this.h.ownerEntity).activeSkillEffect.a(this.O[this.K * 7 + 5])) {
                           ((Pet)this.h.ownerEntity).setBattleAnimationState(this.O[this.K * 7 + 6]);
                           this.K = 0;
                        }

                        if (this.O[this.J * 7 + 4] != -1 && ((Pet)this.h.ownerEntity).activeSkillEffect.a(this.O[this.J * 7 + 4])) {
                           this.n();
                           if (this.H != null) {
                              this.M = true;
                           }
                        }
                     }
                  } else if ((this.h.p() != 1 || !this.h.isLastAnimationStep()) && !this.N) {
                     if (((Pet)this.h.ownerEntity).p() == 2 && ((Pet)this.h.ownerEntity).isLastAnimationStep()) {
                        this.z = true;
                        ((Pet)this.h.ownerEntity).activeSkillEffect = null;
                        if (this.I <= this.O.length / 7 - 1 && !this.W()) {
                           this.n();
                           if (this.H != null) {
                              this.M = true;
                           }
                        } else {
                           this.A = true;
                        }
                     }
                  } else {
                     this.h.setBattleAnimationState((byte)0);
                     ((Pet)this.h.ownerEntity).activeSkillEffect.a();
                     this.N = false;
                  }
               }

               if (this.H != null && !this.H.i() && (this.h.p() == 1 && this.h.isLastAnimationStep() || this.M || this.h.p() == 0)) {
                  if (this.J == 0) {
                     this.N = true;
                  }

                  this.h.setBattleAnimationState((byte)0);
                  this.H.a();
                  this.L = this.J;
                  if (this.O[this.J * 7] == 0) {
                     ((Pet)this.h.ownerEntity).setVisible(false);
                  } else {
                     this.h.setVisible(false);
                  }
               }

               if (this.H != null && this.H.i() && !this.H.e()) {
                  this.H = null;
                  this.M = false;
                  if (this.O[this.L * 7] == 0) {
                     ((Pet)this.h.ownerEntity).setVisible(true);
                  } else {
                     this.h.setVisible(true);
                  }

                  if (((Pet)this.h.ownerEntity).activeSkillEffect == null && this.h.activeSkillEffect == null) {
                     if (this.I <= this.O.length / 7 - 1 && !this.W()) {
                        if (this.O[this.I * 7] == 1) {
                           this.z = true;
                        }

                        this.n();
                        if (this.H != null) {
                           this.M = true;
                        }
                     } else {
                        if (this.O[this.J * 7] == 0) {
                           this.z = true;
                        }

                        this.B = true;
                        this.A = true;
                     }
                  }

                  this.L = 0;
               }

               if (this.z) {
                  this.B = false;
                  BattleScreen var9 = this;
                  boolean var10000;
                  if (GameDatabase.gameDatabase[1][var9.h.selectedSkillId][3] == 0) {
                     var9.interactionController.k = 0;
                     if (((Pet)var9.h.ownerEntity).r() == 0) {
                        var9.interactionController.a((Pet)var9.h.ownerEntity);
                     } else {
                        var9.interactionController.b((Pet)var9.h.ownerEntity);
                     }

                     ((Pet)var9.h.ownerEntity).setBattleAnimationState((byte)0);
                     var10000 = true;
                  } else if (((Pet)var9.h.ownerEntity).p() == 3) {
                     var10000 = true;
                  } else {
                     label872: {
                        if (!var9.aE) {
                           int var18 = var9.h.getLevel();
                           if (var9.h.r() == 0 && p.c((byte)4, (byte)0) == 2 && p.c((byte)4, (byte)1) == 1) {
                              var18 += GameDatabase.gameDatabase[2][4][6];
                           }

                           if (var9.h.m(4)) {
                              var18 = ((Pet)var9.h.ownerEntity).getLevel() - (var18 - var9.h.w[4][1]) << 1;
                           } else {
                              var18 = ((Pet)var9.h.ownerEntity).getLevel() - var18 << 1;
                           }

                           if (var9.h.f((byte)9)) {
                              var18 = 0;
                           }

                           if (var18 <= 0) {
                              var18 = 0;
                           } else if (var18 >= 20) {
                              var18 = 20;
                           }

                           if (EngineUtils.randomInt(100) >= var18) {
                              ((Pet)var9.h.ownerEntity).k(var9.Z[0]);
                              if (var9.h.f((byte)10) && ((Pet)var9.h.ownerEntity).getCurrentHp((byte)1) <= GameDatabase.gameDatabase[3][10][5]) {
                                 ((Pet)var9.h.ownerEntity).setCurrentStat((byte)1, GameDatabase.gameDatabase[3][10][5]);
                              }

                              if (var9.Z[1] == 1) {
                                 var9.a(
                                    "-" + var9.Z[0],
                                    (byte)0,
                                    1,
                                    ((Pet)var9.h.ownerEntity).r(),
                                    ((Pet)var9.h.ownerEntity).getPosX(),
                                    ((Pet)var9.h.ownerEntity).getPosY(),
                                    15,
                                    19
                                 );
                              } else {
                                 var9.a(
                                    "-" + var9.Z[0],
                                    (byte)0,
                                    0,
                                    ((Pet)var9.h.ownerEntity).r(),
                                    ((Pet)var9.h.ownerEntity).getPosX(),
                                    ((Pet)var9.h.ownerEntity).getPosY(),
                                    9,
                                    12
                                 );
                              }

                              if (var9.Z[2] != -1) {
                                 var9.a(
                                    BaseScreen.getString(GameDatabase.gameDatabase[7][var9.Z[2]][0]),
                                    (byte)1,
                                    0,
                                    ((Pet)var9.h.ownerEntity).r(),
                                    ((Pet)var9.h.ownerEntity).getPosX(),
                                    ((Pet)var9.h.ownerEntity).getPosY(),
                                    9,
                                    12
                                 );
                              }
                           } else {
                              var9.a(
                                 "Né tránh",
                                 (byte)1,
                                 0,
                                 ((Pet)var9.h.ownerEntity).r(),
                                 ((Pet)var9.h.ownerEntity).getPosX(),
                                 ((Pet)var9.h.ownerEntity).getPosY(),
                                 9,
                                 12
                              );
                           }

                           var9.aE = true;
                           var9.interactionController.k = 0;
                           if (((Pet)var9.h.ownerEntity).r() == 0) {
                              var9.interactionController.a((Pet)var9.h.ownerEntity);
                           } else {
                              var9.interactionController.b((Pet)var9.h.ownerEntity);
                           }
                        }

                        boolean var20 = var9.V();
                        if (((Pet)var9.h.ownerEntity).r() == 0) {
                           if (var9.interactionController.a((Pet)var9.h.ownerEntity, false) && var20) {
                              var9.U();
                              var9.aE = false;
                              var10000 = true;
                              break label872;
                           }
                        } else if (var9.interactionController.b((Pet)var9.h.ownerEntity, false) && var20) {
                           var9.U();
                           var9.aE = false;
                           var10000 = true;
                           break label872;
                        }

                        var10000 = false;
                     }
                  }

                  if (var10000 && (((Pet)this.h.ownerEntity).isAlive() || this.c((Pet)this.h.ownerEntity, true))) {
                     this.B = true;
                     this.z = false;
                  }
               }

               if (this.B && this.A && this.q()) {
                  this.B = false;
                  this.A = false;
               }
               break;
            case 8:
               this.interactionController.am();
            case 9:
            case 14:
            case 18:
            case 25:
            case 26:
            case 27:
            case 28:
            case 29:
            case 30:
            case 31:
            case 32:
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
            case 100:
            case 103:
            default:
               break;
            case 10:
               if (!this.interactionController.j() && this.c(this.h, false)) {
                  this.interactionController.c(getString(GameDatabase.gameDatabase[0][this.h.getPetId()][0]) + "Chạy trốn");
               }

               if (this.interactionController.g()) {
                  GameStateController.getInstance().setScreenMode((byte)10);
               }
               break;
            case 11:
               this.interactionController.a((byte)4, (byte)0);
               break;
            case 12:
            case 13:
               BattleScreen var8;
               Pet var16;
               var8 = this;
               label689:
               if ((var16 = (Pet)this.v.elementAt(var8.i)).activeSkillEffect != null) {
                  if (var16.activeSkillEffect.i()) {
                     if (!var16.activeSkillEffect.a.isLastAnimationStep()) {
                        if (var8.ae[(var8.ac << 2) + 3] != -1 && var16.activeSkillEffect.a(var8.ae[(var8.ac << 2) + 3]) && var8.ab < var8.ae.length / 4) {
                           var8.o();
                        }
                        break label689;
                     }

                     var16.activeSkillEffect.b();
                     var16.activeSkillEffect = null;
                     if (var8.ab > var8.ae.length / 4 - 1) {
                        if (var8.H == null) {
                           var8.A = true;
                        }
                        break label689;
                     }

                     var8.o();
                     if (var16.activeSkillEffect == null) {
                        break label689;
                     }
                  }

                  var16.activeSkillEffect.a();
               }

               if (var8.H != null && !var8.H.i()) {
                  var8.H.a();
                  var16.setVisible(false);
               }

               if (var8.H != null && var8.H.i() && !var8.H.e()) {
                  var8.H = null;
                  var16.setVisible(true);
                  if (var8.ab > var8.ae.length / 4 - 1) {
                     var8.A = true;
                  } else {
                     var8.o();
                  }
               }

               if (var8.A) {
                  Pet var26 = var16;
                  BattleScreen var17 = var8;
                  boolean var4 = false;
                  int var5 = var26.N();
                  int var6 = 0;
                  if (!var17.aE) {
                     if (var17.ag[var17.ad << 1] == 0) {
                        var6 = var26.o(var17.ag[(var17.ad << 1) + 1]);
                        var26.d(var17.ag[(var17.ad << 1) + 1], var17.ah[var17.ad]);
                     } else if (var17.ag[var17.ad << 1] == 1) {
                        var26.q(var17.ag[(var17.ad << 1) + 1]);
                        var26.c(var17.ag[(var17.ad << 1) + 1], var17.ah[var17.ad]);
                     }

                     if (var26.currentStats[1] < var5) {
                        var17.a("" + (var26.currentStats[1] - var5), (byte)0, 0, var26.r(), var26.posX, var26.posY, 9, 12);
                     }

                     if (var6 > 0) {
                        var17.a("+" + var6, (byte)0, 2, var26.r(), var26.posX, var26.posY, 9, 12);
                     }

                     var17.aE = true;
                     var17.interactionController.k = 0;
                     if (var26.r() == 0) {
                        var17.interactionController.a(var26);
                     } else {
                        var17.interactionController.b(var26);
                     }
                  }

                  boolean var29 = var17.V();
                  if (var26.r() == 0) {
                     if (var5 < var26.currentStats[1]) {
                        if (var17.interactionController.a(var26, true) && var29) {
                           var17.aE = false;
                           var4 = true;
                           var17.c(var26);
                        }
                     } else if (var17.interactionController.a(var26, false) && var29) {
                        var17.aE = false;
                        var4 = true;
                        var17.c(var26);
                     }
                  } else if (var5 < var26.currentStats[1]) {
                     if (var17.interactionController.b(var26, true) && var29) {
                        var17.aE = false;
                        var4 = true;
                        var17.c(var26);
                     }
                  } else if (var17.interactionController.b(var26, false) && var29) {
                     var17.aE = false;
                     var4 = true;
                     var17.c(var26);
                  }

                  if (var4) {
                     var8.A = false;
                  }
               }
               break;
            case 15:
               if (this.a == 0) {
                  this.interactionController.a(this.d[1], this.d[0], this.E[this.G] + 1, an[this.r][this.G].length / 4);
               }

               if (this.F > 0) {
                  this.E[this.G]++;
                  this.F = 0;
               } else {
                  this.F++;
                  if (this.E[this.G] > an[this.r][this.G].length / 4 - 3) {
                     this.D[this.G] = this.E[this.G];
                  }

                  this.d[this.G].setPosition(an[this.r][this.G][this.E[this.G] << 2], an[this.r][this.G][(this.E[this.G] << 2) + 1]);
               }

               if (this.E[this.G] > an[this.r][this.G].length / 4 - 1) {
                  this.D[this.G] = this.E[this.G] = (byte)(an[this.r][this.G].length / 4 - 1);
                  boolean var7 = true;
                  if (this.k) {
                     while (this.i < this.v.size() && (!((Pet)this.v.elementAt(this.i)).isAlive() || ((Pet)this.v.elementAt(this.i)).r() != 0)) {
                        this.i++;
                     }

                     if (this.i >= this.v.size()) {
                        this.i = 0;
                        this.setScreenMode((byte)1);
                     } else if (d((Pet)this.v.elementAt(this.i))) {
                        this.setScreenMode((byte)13);
                     } else {
                        var7 = false;
                        this.setScreenMode((byte)20);
                     }

                     this.k = false;
                  } else {
                     if (this.a == 0) {
                        this.i = (byte)this.v.size();
                     }

                     if (this.i >= this.v.size()) {
                        for (int var14 = 0; var14 < this.d.length; var14++) {
                           this.d[var14].J = false;
                        }

                        if (this.y) {
                           this.T();
                           this.y = false;
                        }

                        if (this.previousScreenMode != 12 && this.previousScreenMode != 13) {
                           boolean var15 = false;
                           if (this.h.m(12) && this.h.K[12] == 2) {
                              this.h.K[12]--;
                              if (!((Pet)this.h.ownerEntity).isAlive()) {
                                 var15 = true;
                                 this.h.K[12]--;
                              } else {
                                 this.i--;
                                 this.setScreenMode((byte)2);
                              }
                           } else {
                              int var25 = EngineUtils.randomInt(100);
                              if ((this.h.selectedSkillId == 63 || this.h.selectedSkillId == 69)
                                 && var25 <= GameDatabase.gameDatabase[1][this.h.selectedSkillId][8]
                                 && ((Pet)this.h.ownerEntity).isAlive()) {
                                 this.i--;
                                 this.setScreenMode((byte)2);
                              } else {
                                 var15 = true;
                              }
                           }

                           if (var15) {
                              this.i = 0;

                              while (
                                 ((Pet)this.v.elementAt(this.i)).r() != 0
                                    || ((Pet)this.v.elementAt(this.i)).r() == 0 && !((Pet)this.v.elementAt(this.i)).isAlive()
                              ) {
                                 this.i++;
                              }

                              if (d((Pet)this.v.elementAt(this.i))) {
                                 this.setScreenMode((byte)13);
                              } else {
                                 var7 = false;
                                 this.setScreenMode((byte)20);
                              }
                           }
                        } else {
                           this.i = 0;

                           while (
                              ((Pet)this.v.elementAt(this.i)).r() != 0
                                 || ((Pet)this.v.elementAt(this.i)).r() == 0 && !((Pet)this.v.elementAt(this.i)).isAlive()
                           ) {
                              this.i++;
                           }

                           if (d((Pet)this.v.elementAt(this.i))) {
                              this.setScreenMode((byte)13);
                           } else {
                              var7 = false;
                              this.setScreenMode((byte)20);
                           }
                        }
                     } else if (this.previousScreenMode != 13 && this.previousScreenMode != 12) {
                        boolean var2;
                        label923: {
                           var2 = false;
                           if (this.h.m(12) && this.h.K[12] == 2) {
                              this.h.K[12]--;
                              if (((Pet)this.h.ownerEntity).isAlive()) {
                                 this.i--;
                                 this.setScreenMode((byte)2);
                                 break label923;
                              }

                              this.h.K[12]--;
                           } else {
                              int var3 = EngineUtils.randomInt(100);
                              if ((this.h.selectedSkillId == 63 || this.h.selectedSkillId == 69)
                                 && var3 <= GameDatabase.gameDatabase[1][this.h.selectedSkillId][8]
                                 && ((Pet)this.h.ownerEntity).isAlive()) {
                                 this.i--;
                                 this.setScreenMode((byte)2);
                                 break label923;
                              }
                           }

                           var2 = true;
                        }

                        if (var2) {
                           this.setScreenMode((byte)1);
                        }
                     } else {
                        this.setScreenMode((byte)1);
                     }
                  }

                  if (var7) {
                     this.e();
                     if (this.d[this.g].r() == 0) {
                        this.interactionController.a(this.d[this.g], false);
                        this.interactionController.a(this.d[this.g]);
                     } else {
                        this.interactionController.b(this.d[this.g], false);
                        this.interactionController.b(this.d[this.g]);
                     }
                  }
               }
               break;
            case 16:
               this.interactionController.al();
               break;
            case 17:
               if (this.interactionController.f == 0) {
                  if (this.q == 0 && this.aj.isLastAnimationStep()) {
                     this.e((byte)1);
                  } else if (this.q == 1 && this.aj.isLastAnimationStep()) {
                     if (!this.H.e()) {
                        this.e((byte)2);
                     }
                  } else if (this.q == 2 && this.aj.isLastAnimationStep()) {
                     if (this.ak) {
                        this.e((byte)3);
                     } else {
                        this.e((byte)4);
                     }
                  } else if (this.q == 3 && this.aj.isLastAnimationStep()) {
                     byte var1;
                     if ((var1 = p.y()) == 0) {
                        this.interactionController.f = 1;
                        this.interactionController
                           .b("Bắt thành công #2" + BaseScreen.getString(GameDatabase.gameDatabase[0][((Pet)this.h.ownerEntity).getPetId()][0]));
                        p.a(((Pet)this.h.ownerEntity).toSaveData());
                     } else if (var1 == 1) {
                        this.interactionController.f = 2;
                        this.interactionController
                           .b("Bắt thành công #2" + BaseScreen.getString(GameDatabase.gameDatabase[0][((Pet)this.h.ownerEntity).getPetId()][0]));
                        p.b(((Pet)this.h.ownerEntity).toSaveData());
                     } else {
                        this.interactionController.f = 1;
                        this.interactionController.b("Không còn không gian, sủng vật này đã phóng sinh");
                     }
                  } else if (this.q == 4 && this.aj.isLastAnimationStep() && !this.H.e()) {
                     this.H = null;
                     this.d[0].setVisible(true);
                     this.aj.deactivate();
                     this.h.J = true;
                     if (this.ak) {
                        this.interactionController.b("Ngân hàng và Ba lô đều đã đầy");
                        this.interactionController.f = 3;
                     } else {
                        this.i++;
                        this.setScreenMode((byte)1);
                     }
                  }

                  this.aj.advanceAnimationIfVisible();
               } else {
                  if (this.interactionController.ax()) {
                     if (this.interactionController.f == 3) {
                        this.interactionController.f = 0;
                        this.h.J = true;
                        this.i++;
                        this.setScreenMode((byte)1);
                     } else if (this.interactionController.f == 2) {
                        this.interactionController.b("Sủng vật ba lô đã đủ, đã để vào ngân hàng");
                        this.interactionController.f = 4;
                     } else if (this.interactionController.f == 4 || this.interactionController.f == 1) {
                        this.interactionController.f = 0;
                        WorldManager.a().M.l = -1;
                        this.l();
                        GameStateController.getInstance().setScreenMode((byte)10);
                     }
                  }

                  this.interactionController.f();
               }
               break;
            case 19:
               if (this.isKeyPressed(786432)) {
                  this.setScreenMode((byte)18);
               }
               break;
            case 20:
               this.al[this.d.length].advanceAnimationIfVisible();
               this.interactionController.d(this.h);
               break;
            case 21:
               this.interactionController.ai();
               break;
            case 22:
               this.interactionController.ao();
               break;
            case 23:
               this.interactionController.aq();
               break;
            case 24:
               this.interactionController.aF();
               break;
            case 101:
            case 102:
            case 104:
               this.interactionController.aM();
         }

         for (int var13 = 0; var13 < this.d.length; var13++) {
            this.d[var13].updateBattleEffects();
         }

         this.uiManager.update();
      }
   }

   private void a(Graphics var1) {
      var1.setColor(16777215);

      for (int var2 = 0; var2 < this.d.length; var2++) {
         this.d[var2].render(var1);
      }
   }

   private void a(Graphics var1, boolean var2) {
      for (int var3 = 0; var3 < this.d.length; var3++) {
         this.al[var3].renderInWorld(var1, 0, 0);
      }

      if (var2) {
         this.al[this.d.length].renderInWorld(var1, 0, 0);
         if (this.a == 1) {
            this.al[this.d.length + 1].renderInWorld(var1, 0, 0);
         }
      }
   }

   private void a(String var1, byte var2, int var3, int var4, int var5, int var6, int var7, int var8) {
      this.ay = var1;
      this.as = var5;
      this.at = var6;
      this.aw = var3;
      this.ax = var4;
      this.au = var7;
      this.av = var8;
      this.aC.addElement(this.ay);
      this.aB.addElement(new int[]{var2, this.aw, this.ax, -1, this.au, this.av});
   }

   private void c(Graphics var1) {
      for (int var4 = 0; var4 < this.aB.size(); var4++) {
         int[] var2 = (int[])this.aB.elementAt(var4);
         String var3 = (String)this.aC.elementAt(var4);
         switch (var2[0]) {
            case 0:
               if (var2[2] == 0) {
                  a(var1, az[var2[1]], var3, this.as + aA[var2[0]][var2[3] << 1] + 30, this.at + aA[var2[0]][(var2[3] << 1) + 1] - 30, var2[4], var2[5]);
               } else {
                  a(var1, az[var2[1]], var3, this.as - aA[var2[0]][var2[3] << 1] - 30, this.at + aA[var2[0]][(var2[3] << 1) + 1] - 30, var2[4], var2[5]);
               }
               break;
            case 1:
               if (var2[2] == 0) {
                  EngineUtils.a(var1, var3, 16704699, this.as - 10, this.at + aA[var2[0]][(var2[3] << 1) + 1] - 30, this.uiManager.textPainter, 2);
               } else {
                  EngineUtils.a(var1, var3, 16704699, this.as + 10, this.at + aA[var2[0]][(var2[3] << 1) + 1] - 30, this.uiManager.textPainter, 2);
               }
         }
      }
   }

   public final void b(Graphics var1) {
      if (this.inputEnabled) {
         if (this.c != null) {
            var1.drawImage(this.c, 0, 0, 20);
         } else {
            var1.setColor(0);
            var1.fillRect(0, 0, BaseScreen.getScreenWidth(), BaseScreen.getScreenHeight());
         }

         switch (this.screenMode) {
            case 0:
               this.a(var1, false);
               this.a(var1);
               break;
            case 1:
            case 10:
               this.a(var1, false);
               this.a(var1);
               break;
            case 2:
               this.a(var1, false);
               this.a(var1);
               break;
            case 3:
               var1.setColor(16777215);
               break;
            case 4:
               var1.setColor(16777215);
               break;
            case 5:
               var1.setColor(16777215);
               var1.drawString(getString(GameDatabase.gameDatabase[0][this.getBattlePet(this.f[0]).getPetId()][0]), getScreenWidth() >> 1, 200, 17);
               break;
            case 6:
               if (this.a == 1) {
                  this.a(var1, true);
               } else {
                  this.a(var1, false);
               }

               this.a(var1);
               break;
            case 7:
               this.a(var1, false);
               if (this.H != null) {
                  this.H.a(var1);
               }

               this.a(var1);
               this.c(var1);
               break;
            case 8:
               this.al[0].renderInWorld(var1, 0, 0);
               if (this.interactionController.i < j.size()) {
                  ((Pet)j.elementAt(this.interactionController.i)).render(var1);
               }
            case 9:
            case 11:
            case 14:
            case 16:
            case 18:
            case 19:
            case 21:
            default:
               break;
            case 12:
            case 13:
               this.a(var1, false);
               if (this.H != null) {
                  this.H.a(var1);
               }

               this.a(var1);
               this.c(var1);
               break;
            case 15:
               this.a(var1, false);
               this.a(var1);
               break;
            case 17:
               this.a(var1, false);
               this.a(var1);
               if (this.H != null && this.H.c()) {
                  this.H.a(var1);
                  this.aj.renderInWorld(var1, 0, 0);
               } else {
                  this.aj.renderInWorld(var1, 0, 0);
               }
               break;
            case 20:
               this.a(var1, true);
               this.a(var1);
         }

         this.uiManager.render(var1);
      }
   }

   public final void h() {
      this.i++;
      if (this.i < this.v.size()) {
         while (((Pet)this.v.elementAt(this.i)).r() != 0 || ((Pet)this.v.elementAt(this.i)).r() == 0 && !((Pet)this.v.elementAt(this.i)).isAlive()) {
            this.i++;
            if (this.i >= this.v.size()) {
               break;
            }
         }
      }

      if (this.i >= this.v.size()) {
         this.setScreenMode((byte)1);
      } else if (d((Pet)this.v.elementAt(this.i))) {
         this.setScreenMode((byte)13);
      } else {
         this.setScreenMode((byte)20);
      }
   }

   private void p() {
      if (this.i < this.v.size()) {
         this.setScreenMode((byte)1);
      } else {
         if (this.y) {
            this.T();
            this.y = false;
         }

         for (int var1 = 0; var1 < this.v.size(); var1++) {
            ((Pet)this.v.elementAt(var1)).J = false;
         }

         this.i = 0;

         while (((Pet)this.v.elementAt(this.i)).r() != 0 || ((Pet)this.v.elementAt(this.i)).r() == 0 && !((Pet)this.v.elementAt(this.i)).isAlive()) {
            this.i++;
         }

         if (d((Pet)this.v.elementAt(this.i))) {
            this.setScreenMode((byte)13);
         } else {
            this.setScreenMode((byte)20);
         }
      }
   }

   private boolean q() {
      BattleScreen var1 = this;
      if (!this.aE) {
         int var2 = 0;
         if (GameDatabase.gameDatabase[1][var1.h.selectedSkillId][9] == 0) {
            var1.aF = var1.h.N();
         } else {
            var1.aF = ((Pet)var1.h.ownerEntity).N();
         }

         byte var3 = var1.h.selectedSkillId;
         switch (var1.h.selectedSkillId) {
            case 11:
            case 17:
               var2 = (short)(var1.h.B() * GameDatabase.gameDatabase[1][var3][8] / 100);
               var1.h.u(var1.h.currentStats[1]);
               if (var2 <= 0) {
                  var2 = 1;
               }

               var1.h.l(var2);
               break;
            case 21:
            case 27:
            case 42:
            case 48:
            case 62:
            case 68:
               var1.h.a((byte)GameDatabase.gameDatabase[1][var3][7], -1, var3);
               break;
            case 52:
            case 58:
               if (var1.aa) {
                  var2 = var1.Z[0] * GameDatabase.gameDatabase[1][var3][8] / 100;
                  var1.h.u(var1.h.currentStats[1]);
                  var1.h.l(var1.Z[0] * GameDatabase.gameDatabase[1][var3][8] / 100);
               }
            case 64:
               var1.h.a((byte)GameDatabase.gameDatabase[1][var3][7], var1.h.I, var3);
               break;
            default:
               if (GameDatabase.gameDatabase[1][var3][6] == 1) {
                  var2 = ((Pet)var1.h.ownerEntity).a((byte)GameDatabase.gameDatabase[1][var3][7], -1, var3);
               }
         }

         byte var4 = (byte)GameDatabase.gameDatabase[1][var3][6];
         if (GameDatabase.gameDatabase[1][var1.h.selectedSkillId][9] == 0) {
            if (var1.h.f((byte)8) && EngineUtils.randomInt(100) <= GameDatabase.gameDatabase[3][8][5]) {
               var1.h.u(var1.h.currentStats[1]);
               var1.h.l((short)(var1.Z[0] * GameDatabase.gameDatabase[3][8][6] / 100));
            }

            if (((Pet)var1.h.ownerEntity).m(2)) {
               int var5 = var1.Z[0] * ((Pet)var1.h.ownerEntity).v[2][2] / 100;
               var1.h.k(var5);
            }

            short var13;
            if (((Pet)var1.h.ownerEntity).m(5) && (var13 = var1.h.K[5]) > 0) {
               var1.h.k(var13);
               var1.h.K[5] = 0;
            }

            if (var1.h.currentStats[1] < var1.aF) {
               var1.a("" + (var1.h.currentStats[1] - var1.aF), (byte)0, 0, var1.h.r(), var1.h.posX, var1.h.posY, 9, 12);
            } else if (var2 > 0) {
               var1.a("+" + var2, (byte)0, 2, var1.h.r(), var1.h.posX, var1.h.posY, 9, 12);
            }
         } else if (var2 > 0) {
            var1.a("+" + var2, (byte)0, 2, ((Pet)var1.h.ownerEntity).r(), ((Pet)var1.h.ownerEntity).posX, ((Pet)var1.h.ownerEntity).posY, 9, 12);
         }

         if (var4 == 1) {
            short var14 = GameDatabase.gameDatabase[1][var3][7];
            switch (var3) {
               case 21:
               case 27:
               case 42:
               case 48:
               case 62:
               case 64:
               case 68:
                  var1.a(BaseScreen.getString(GameDatabase.gameDatabase[6][var14][0]), (byte)1, 2, var1.h.r(), var1.h.posX, var1.h.posY, 9, 12);
                  break;
               default:
                  var1.a(
                     BaseScreen.getString(GameDatabase.gameDatabase[6][var14][0]),
                     (byte)1,
                     2,
                     ((Pet)var1.h.ownerEntity).r(),
                     ((Pet)var1.h.ownerEntity).posX,
                     ((Pet)var1.h.ownerEntity).posY,
                     9,
                     12
                  );
            }
         }

         if (GameDatabase.gameDatabase[1][var1.h.selectedSkillId][9] == 0) {
            var1.interactionController.k = 0;
            if (var1.h.r() == 0) {
               var1.interactionController.a(var1.h);
            } else {
               var1.interactionController.b(var1.h);
            }
         } else {
            var1.interactionController.k = 0;
            if (((Pet)var1.h.ownerEntity).r() == 0) {
               var1.interactionController.a((Pet)var1.h.ownerEntity);
            } else {
               var1.interactionController.b((Pet)var1.h.ownerEntity);
            }
         }

         var1.aE = true;
      }

      boolean var9 = var1.V();
      if (((Pet)var1.h.ownerEntity).r() == var1.h.r() && !var1.h.p(9) ? !var1.a((Pet)var1.h.ownerEntity, var9, var1.aF) : !var1.a(var1.h, var9, var1.aF)) {
         return false;
      }

      this.I = 0;
      this.J = 0;
      int var10 = 0;

      while (var10 < this.f.length && !this.getBattlePet(this.f[var10]).isAlive()) {
         var10++;
      }

      if ((((Pet)this.h.ownerEntity).r() != 1 || ((Pet)this.h.ownerEntity).isAlive()) && (this.h.r() != 1 || this.h.isAlive())) {
         if (((Pet)this.h.ownerEntity).r() == 0 && !((Pet)this.h.ownerEntity).isAlive() || this.h.r() == 0 && !this.h.isAlive()) {
            ((Pet)this.h.ownerEntity).C();
            ((Pet)this.h.ownerEntity).D();
            this.interactionController.a((Pet)this.h.ownerEntity);
            x.removeElement((Pet)this.h.ownerEntity);
            j.removeElement((Pet)this.h.ownerEntity);
            ((Pet)this.h.ownerEntity).B = 0;
            ((Pet)this.h.ownerEntity).d(false);
            ((Pet)this.h.ownerEntity).F = 0;
         }
      } else {
         ((Pet)this.h.ownerEntity).C();
         ((Pet)this.h.ownerEntity).D();
         this.interactionController.b((Pet)this.h.ownerEntity);
         this.h((Pet)this.h.ownerEntity);
         this.u[1]++;
      }

      byte var6;
      if (var10 >= this.f.length) {
         var6 = 2;
      } else if (this.u[1] >= this.s.length) {
         var6 = 1;
      } else {
         var6 = 0;
      }

      switch (var6) {
         case 0:
            boolean var7 = false;
            if (((Pet)this.h.ownerEntity).isAlive() && this.h.isAlive()) {
               var7 = true;
            } else {
               for (int var11 = 0; var11 < this.d.length; var11++) {
                  if (this.d[var11].m(11) && this.d[this.d[var11].v[11][1]].equals((Pet)this.h.ownerEntity)) {
                     this.d[var11].n(11);
                  }
               }

               if ((((Pet)this.h.ownerEntity).r() != 1 || ((Pet)this.h.ownerEntity).isAlive()) && (this.h.r() != 1 || this.h.isAlive())) {
                  if (!this.r()) {
                     var7 = true;
                  } else {
                     if (this.h.r() == 0 && !this.h.isAlive()) {
                        this.g = this.e[this.i];
                     } else {
                        this.g = this.h.I;
                     }

                     this.setScreenMode((byte)5);
                  }
               } else if (this.u[0] >= this.s.length) {
                  var7 = true;
               } else {
                  if (this.h.r() == 1 && !this.h.isAlive()) {
                     this.g = this.e[this.i];
                  } else {
                     this.g = this.h.I;
                  }

                  this.m(this.g);
                  this.setScreenMode((byte)15);
               }
            }

            if (var7) {
               if (this.h.m(12) && this.h.K[12] == 2) {
                  this.h.K[12]--;
                  if (!((Pet)this.h.ownerEntity).isAlive()) {
                     this.h.K[12]--;
                     this.i++;
                     this.p();
                  } else {
                     this.setScreenMode((byte)2);
                  }
               } else {
                  int var12 = EngineUtils.randomInt(100);
                  if ((this.h.selectedSkillId == 63 || this.h.selectedSkillId == 69) && var12 <= GameDatabase.gameDatabase[1][this.h.selectedSkillId][8]) {
                     if (((Pet)this.h.ownerEntity).isAlive()) {
                        this.setScreenMode((byte)2);
                        break;
                     }

                     this.h.K[12]--;
                  }

                  this.i++;
                  this.p();
               }
            }
            break;
         case 1:
            X();
            this.setScreenMode((byte)8);
            break;
         case 2:
            this.setScreenMode((byte)9);
      }

      if (p.c((byte)5, (byte)0) == 2 && p.c((byte)5, (byte)1) == 1) {
         for (int var8 = 0; var8 < this.d.length; var8++) {
            if (this.d[var10].r() == 0 && this.d[var10].isAlive()) {
               this.d[var10].y();
            }
         }
      }

      return true;
   }

   private static boolean d(Pet var0) {
      return var0.r(0) > 0 || var0.r(1) > 0;
   }

   private static int e(Pet var0) {
      byte var1 = var0.learnedSkillIds[0];
      int[] var2 = new int[]{50, 20, 15, 10, 5, 5, 5, 5, 5, 5};
      int var3 = EngineUtils.randomInt(100);

      for (int var4 = 0; var4 < var0.learnedSkillIds.length; var4++) {
         if (var0.learnedSkillIds[var4] != -1 && var0.skillUsesRemaining[var4] > 0 && var3 < var2[var4]) {
            var1 = var0.learnedSkillIds[var4];
         }
      }

      return var1;
   }

   public final void b(byte var1) {
      this.h.G.removeAllElements();
      this.h.H.removeAllElements();
      switch (Pet.b(var1, (byte)9)) {
         case 0:
            for (int var3 = 0; var3 < this.d.length; var3++) {
               if (this.d[var3].r() != this.h.r() && this.d[var3].isAlive()) {
                  this.h.G.addElement(this.d[var3]);
                  this.h.H.addElement("" + var3);
               }
            }
         default:
            return;
         case 1:
            for (int var2 = 0; var2 < this.d.length; var2++) {
               if (this.d[var2].r() == this.h.r() && this.d[var2].isAlive()) {
                  this.h.G.addElement(this.d[var2]);
                  this.h.H.addElement("" + var2);
               }
            }
      }
   }

   private void f(Pet var1) {
      var1.G.removeAllElements();
      var1.H.removeAllElements();

      for (int var2 = 0; var2 < this.d.length; var2++) {
         if (this.d[var2].isAlive() && !this.d[var2].equals(var1)) {
            var1.G.addElement(this.d[var2]);
            var1.H.addElement("" + var2);
         }
      }
   }

   public final void i() {
      this.h.ownerEntity = (Pet)this.h.G.elementAt(this.C);
      this.h.I = Byte.parseByte((String)this.h.H.elementAt(this.C));
      this.h.h(((Pet)this.v.elementAt(this.i)).learnedSkillIds[this.interactionController.e]);
      this.a(Integer.parseInt((String)this.h.H.elementAt(this.C)), false);
      this.h();
   }

   private int g(Pet var1) {
      if (!var1.isAlive()) {
         if (var1.r() == 0) {
            if (this.r()) {
               return 1;
            }
         } else if (this.u[0] < this.s.length) {
            return 2;
         }
      }

      return 0;
   }

   private boolean r() {
      int var1 = 0;

      for (int var2 = 0; var2 < p.partyPetCount; var2++) {
         if (this.getBattlePet(var2).isAlive() && !this.getBattlePet(var2).K()) {
            var1++;
         }
      }

      return var1 > 0;
   }

   private void e(int var1, int var2) {
      byte var3 = this.f[var2];
      this.f[var2] = this.f[var1];
      this.f[var1] = var3;
   }

   public final int a(int var1) {
      if (!this.getBattlePet(this.f[var1]).isAlive()) {
         return 0;
      }

      if (this.getBattlePet(this.f[var1]).K()) {
         return 1;
      }

      byte var2 = this.f[var1];
      var1--;

      while (var1 >= 0) {
         this.f[var1 + 1] = this.f[var1];
         var1--;
      }

      this.f[0] = var2;
      int var4 = 0;

      while (var4 < x.size() && !x.elementAt(var4).equals(this.getBattlePet(this.f[0]))) {
         var4++;
      }

      if (var4 >= x.size()) {
         x.addElement(this.getBattlePet(this.f[0]));
      }

      this.getBattlePet(this.f[0]).J = true;
      this.getBattlePet(this.f[0]).d(true);
      this.h.d(false);
      this.h.F = 0;

      for (int var5 = 0; var5 < this.d.length; var5++) {
         if (this.d[var5].m(11) && this.d[this.d[var5].v[11][1]].equals(this.h)) {
            this.d[var5].n(11);
         }
      }

      return -1;
   }

   private void T() {
      this.v.removeAllElements();
      int var2 = -1;

      for (int var3 = 0; var3 < this.t.length - 1; var3++) {
         for (int var4 = var3 + 1; var4 < this.t.length; var4++) {
            if (this.d[var3].baseStats[4] < this.d[var4].baseStats[4]) {
               byte var1 = this.t[var3];
               this.t[var3] = this.t[var4];
               this.t[var4] = var1;
            }
         }
      }

      for (int var8 = 0; var8 < this.t.length; var8++) {
         if (this.d[var8].f((byte)7)) {
            var2 = var8;
            this.t[var8] = 0;
            break;
         }
      }

      if (var2 != -1) {
         int var9 = 1;
         int[] var12 = new int[this.t.length - 1];

         for (int var5 = 0; var5 < this.t.length; var5++) {
            if (var5 != var2) {
               var12[var9 - 1] = var5;
               this.t[var5] = (byte)var9;
               var9++;
            }
         }

         for (int var13 = 0; var13 < var12.length - 1; var13++) {
            for (int var7 = var13 + 1; var7 < var12.length; var7++) {
               if (this.d[var12[var13]].baseStats[4] < this.d[var12[var7]].baseStats[4]) {
                  byte var6 = this.t[var12[var13]];
                  this.t[var12[var13]] = this.t[var12[var7]];
                  this.t[var12[var7]] = var6;
               }
            }
         }
      }

      for (int var10 = 0; var10 < this.t.length; var10++) {
         this.e[this.t[var10]] = (byte)var10;
      }

      for (int var11 = 0; var11 < this.e.length; var11++) {
         this.v.addElement(this.d[this.e[var11]]);
      }
   }

   private void U() {
      if (this.h.f((byte)10) && ((Pet)this.h.ownerEntity).currentStats[1] <= GameDatabase.gameDatabase[3][10][5]) {
         Pet var10000 = (Pet)this.h.ownerEntity;
         short var1 = GameDatabase.gameDatabase[3][10][5];
         var10000.currentStats[1] = var1;
      }

      if (((Pet)this.h.ownerEntity).currentStats[1] <= 0) {
         ((Pet)this.h.ownerEntity).setBattleAnimationState((byte)3);
      } else {
         ((Pet)this.h.ownerEntity).setBattleAnimationState((byte)0);
      }
   }

   private boolean c(Pet var1, boolean var2) {
      if (this.b == 0 && var2) {
         if (var1.specialAttackEffect != null && !var1.specialAttackEffect.i()) {
            return true;
         }
      } else {
         int startX = var1.posX;
         if (var1.r() == 0) {
            this.aD -= 10;
         } else {
            this.aD += 10;
         }

         int var4 = startX + this.aD;
         if (Math.abs(this.aD) >= 100) {
            var1.deactivate();
            this.aD = 0;
            return true;
         }

         var1.setPosition(var4, var1.posY);
      }

      return false;
   }

   private boolean V() {
      int var1;
      for (var1 = 0; var1 < this.aB.size(); var1++) {
         int[] var2;
         (var2 = (int[])this.aB.elementAt(var1))[3]++;
         if (var2[3] >= aA[var2[0]].length / 2) {
            this.aB.removeElementAt(var1);
            this.aC.removeElementAt(var1);
            var1--;
         }
      }

      return var1 <= 0;
   }

   private boolean W() {
      if (this.Z != null) {
         return this.Z[2] != -1 ? false : this.O[this.O.length - 1] == this.I;
      } else {
         return !this.aa;
      }
   }

   private boolean a(Pet var1, boolean var2, int var3) {
      if (var1.r() == 0) {
         if (var3 < var1.currentStats[1]) {
            if (this.interactionController.a(var1, true) && var2) {
               this.aE = false;
               return true;
            }
         } else if (this.interactionController.a(var1, false) && var2) {
            this.aE = false;
            return true;
         }
      } else if (var3 < var1.currentStats[1]) {
         if (this.interactionController.b(var1, true) && var2) {
            this.aE = false;
            return true;
         }
      } else if (this.interactionController.b(var1, false) && var2) {
         this.aE = false;
         return true;
      }

      return false;
   }

   private static void X() {
      for (int var0 = 0; var0 < j.size(); var0++) {
         if (((Pet)j.elementAt(var0)).isAlive()) {
            ((Pet)j.elementAt(var0)).g(((Pet)j.elementAt(var0)).B);
            ((Pet)j.elementAt(var0)).B = 0;
            ((Pet)j.elementAt(var0)).d(false);
         } else {
            j.removeElementAt(var0);
            var0--;
         }
      }

      if (p.c((byte)0, (byte)0) == 2 && p.c((byte)0, (byte)1) == 1) {
         for (int var1 = 0; var1 < p.partyPetCount; var1++) {
            if (p.petParty[var1].isAlive()) {
               p.petParty[var1]
                  .u(p.petParty[var1].currentStats[1] + GameDatabase.gameDatabase[0][p.petParty[var1].getPetId()][5] * GameDatabase.gameDatabase[2][0][6] / 100);
               p.petParty[var1].l(GameDatabase.gameDatabase[0][p.petParty[var1].getPetId()][5] * GameDatabase.gameDatabase[2][0][6] / 100);
            }
         }
      }
   }

   private void h(Pet var1) {
      int var2;
      int var9 = (((var2 = var1.getLevel()) << 1) * var2 + 50) * this.aG[var1.baseStats[0] - 1] / 10 + 400;
      int var3;
      int[] var4 = new int[var3 = x.size()];
      Pet var5 = null;
      byte var6 = 0;

      for (int var8 = 0; var8 < var3; var8++) {
         if ((var5 = (Pet)x.elementAt(var8)).getLevel() - var2 >= 6) {
            var6 = this.aI[6];
         } else if (var5.getLevel() - var2 > 0) {
            var6 = this.aI[var5.getLevel() - var2];
         } else if (var5.getLevel() == var2) {
            var6 = this.aI[1];
         } else if (var5.getLevel() < var2) {
            var6 = this.aI[0];
         }

         int var7 = var9 / var3 * this.aH[var3 - 1] * var6 / 1000;
         if (var5.f((byte)5)) {
            var7 = var7 * (GameDatabase.gameDatabase[3][5][5] + 100) / 100;
         }

         var5.B += var7;
         var4[var8] = var7;
         if (!j.contains(var5)) {
            j.addElement(var5);
         }
      }

      for (int var13 = 0; var13 < p.partyPetCount; var13++) {
         if (this.getBattlePet(var13).isAlive() && !x.contains(this.getBattlePet(var13))) {
            if (p.c((byte)7, (byte)0) == 2) {
               if (var5.getLevel() - var2 >= 6) {
                  var6 = this.aI[6];
               } else if (var5.getLevel() - var2 > 0) {
                  var6 = this.aI[var5.getLevel() - var2];
               } else if (var5.getLevel() == var2) {
                  var6 = this.aI[1];
               } else if (var5.getLevel() < var2) {
                  var6 = this.aI[0];
               }

               int var11 = var9 / var3 * this.aH[var3 - 1] * var6 / 3000;
               this.getBattlePet(var13).B += var11;
               this.getBattlePet(var13).activate();
               if (!j.contains(this.getBattlePet(var13))) {
                  j.addElement(this.getBattlePet(var13));
               }
            } else if (this.getBattlePet(var13).f((byte)6)) {
               if (var5.getLevel() - var2 >= 6) {
                  var6 = this.aI[6];
               } else if (var5.getLevel() - var2 > 0) {
                  var6 = this.aI[var5.getLevel() - var2];
               } else if (var5.getLevel() == var2) {
                  var6 = this.aI[1];
               } else if (var5.getLevel() < var2) {
                  var6 = this.aI[0];
               }

               int var12 = var9 / var3 * this.aH[var3 - 1] * var6 / 1000;
               this.getBattlePet(var13).B += var12;
               this.getBattlePet(var13).activate();
               if (!j.contains(this.getBattlePet(var13))) {
                  j.addElement(this.getBattlePet(var13));
               }
            }
         }
      }

      for (int var10 = 0; var10 < x.size(); var10++) {
         Pet var14;
         if (!(var14 = (Pet)x.elementAt(var10)).K()) {
            x.removeElement(var14);
         }
      }

      if (p.k(0)) {
         if (p.I == 0) {
            if (var5.getLevel() >= 30 && ++WorldManager.q >= 10) {
               WorldManager.q = 10;
               return;
            }
         } else if (var5.getLevel() >= 40 && ++WorldManager.q >= 30) {
            WorldManager.q = 30;
         }
      }
   }

   public final int b(int var1) {
      if (var1 == 0) {
         return 100;
      }

      byte var2 = 0;
      if (((Pet)this.h.ownerEntity).m(1)) {
         var2 = 1;
      }

      if (((Pet)this.h.ownerEntity).m(2)) {
         var2 = 2;
      }

      if (((Pet)this.h.ownerEntity).m(10)) {
         var2 = 3;
      }

      if (this.h.f((byte)11)) {
         var2 = 4;
      }

      int var3 = 1;
      short var4 = ((Pet)this.h.ownerEntity).currentStats[1];
      short var5 = ((Pet)this.h.ownerEntity).baseStats[1];
      if (var4 <= var5 * 15 / 100) {
         var3 = 85;
      } else if (var4 <= var5 * 50 / 100) {
         var3 = 45;
      } else if (var4 <= var5) {
         var3 = 20;
      }

      var3 = var3 * GameDatabase.gameDatabase[4][var1][6] / 100;
      int[] var12 = new int[]{110, 100, 95, 80, 70};
      var3 = var3 * var12[((Pet)this.h.ownerEntity).baseStats[0] - 1] / 100;
      int[] var13 = new int[]{10, 11, 12, 12, 12};
      int var10 = var3 * var13[var2] / 10;
      if (this.h.f((byte)11)) {
         var10 = var10 * (100 + GameDatabase.gameDatabase[3][11][5]) / 100;
      }

      int[] var6 = new int[]{1000, 500, 1, 1000};
      int var11 = var10 * var6[GameDatabase.gameDatabase[0][((Pet)this.h.ownerEntity).getPetId()][22]] / 1000;
      if (((Pet)this.h.ownerEntity).getLevel() >= 20) {
         byte[] var7 = new byte[]{0, 15, 35, 65};
         if (var11 >= var7[var1]) {
            var11 = var7[var1];
         }
      }

      if (var11 >= 100) {
         var11 = 100;
      } else if (var11 <= 0) {
         var11 = 1;
      }

      return var11;
   }

   public final Pet getBattlePet(int var1) {
      return var1 > this.f.length - 1 ? null : p.petParty[var1];
   }

   public final Pet d(int var1) {
      return var1 > this.f.length - 1 ? null : p.petParty[this.f[var1]];
   }

   public final void a(int[][] var1) {
      this.s = var1;
   }

   public final int j() {
      return this.s.length;
   }

   public final int k() {
      return this.s[0][0];
   }

   public final void l() {
      switch (U) {
         case 0:
            if (V == 0) {
               if (this.h != null) {
                  int var2 = this.d[0].baseStats[1] * 50 / 100;
                  if (this.d[0].currentStats[1] <= var2) {
                     c(0, 1);
                     V++;
                     this.interactionController.c("Di Lặc thỏ thỏ đã bị thương, nhanh sử dụng #2 phong ấn cầu #1 tiến hành bắt được a");
                  }

                  return;
               }
            } else if (V == 1) {
               if (this.interactionController.ay()) {
                  V++;
                  this.interactionController.a = 1;
                  c(2, 1);
                  c(1, 1);
                  this.interactionController.ag();
                  this.interactionController.c("Hãy nhấn #2nút 5");
                  return;
               }
            } else {
               if (V == 3) {
                  V++;
                  c(2, 0);
                  c(1, 1);
                  this.interactionController.c("Hãy lựa chọn phong ấn cầu");
                  return;
               }

               if (V == 5) {
                  V++;
                  this.interactionController.c("Đáng tiếc đã bắt trượt, thử dùng loại xịn #2Tất trúng cầu#1 xem sao!");
                  return;
               }

               if (V == 6) {
                  if (this.interactionController.ay()) {
                     c(1, 0);
                     this.interactionController.a = 1;
                     this.interactionController.ag();
                     this.interactionController.b = 0;
                     V++;
                     this.setScreenMode((byte)21);
                     return;
                  }
               } else if (V == 8) {
                  c(1, -1);
                  c(0, 0);
                  U = -1;
                  V = 0;
                  return;
               }
            }
            break;
         case 2:
            if (V == 0) {
               c(0, 0);
               V++;
               this.interactionController.c("Tranh thủ thời gian lựa chọn #2Tất trúng cầu#1 để bắt sủng vật");
               return;
            }

            if (V == 2) {
               if (this.h != null) {
                  int var1 = this.d[0].baseStats[1] * 50 / 100 + 2;
                  if (this.d[0].currentStats[1] <= var1) {
                     V--;
                     this.interactionController.c("Lựa chọn #2Tất trúng cầu#1 để bắt sủng vật");
                  }
               }

               if (p.getCollectionFlag((byte)1, 29) == 2) {
                  U = -1;
                  V = 0;
                  return;
               }
            }
            break;
         case 5:
            if (V == 0) {
               this.interactionController.a = 1;
               V++;
               this.interactionController.ag();
               this.interactionController.c("Tranh thủ thời gian lựa chọn #2Tất trúng cầu#1 để bắt sủng vật");
            }
      }
   }

   public final void m() {
      switch (U) {
         case 0:
            if (V == 2 || V == 4 || V == 7) {
               V++;
               return;
            }
            break;
         case 5:
            if (V == 1) {
               V++;
               c(1, 0);
               c(0, 1);
               return;
            }

            if (V == 2) {
               c(0, 0);
               U = -1;
               V = 0;
            }
      }
   }
}
