package game;

import java.util.Vector;

public final class ScriptEngine implements ScriptEventListener {
   private static ScriptEngine n;
   private BaseScreen o;
   private UIManager p = UIManager.getInstance();
   private Player q;
   protected int a;
   protected int b;
   protected int c;
   private int r;
   protected int d;
   protected int e;
   private int s;
   protected int f;
   protected boolean g;
   private String t = "";
   private byte u;
   private byte v;
   private int w;
   protected int h;
   private int x;
   protected int i;
   private int[] y;
   private int[] z;
   public byte j = -1;
   private static String[] A = new String[]{"Thủy Kimura", "Bích Thủy thành", "Nguyên Mộc Thành", "Niêm Thổ Thành", "Hắc Thạch thành", "Thiên không", "Xa cổ"};
   private static short[] B = new short[]{
      1, 0, 196, 208, 0, 2, 1, 196, 208, 0, 3, 3, 196, 208, 0, 4, 5, 320, 352, 0, 5, 3, 320, 196, 0, 7, 2, 288, 112, 0, 8, 0, 160, 144, 0
   };
   private Vector C = new Vector();
   private int D = 0;
   private int E = 0;
   private int F = 0;
   private int G = 0;
   public int k = 0;
   boolean l = false;
   private int H;
   private int I;
   private int J = 0;
   private int K = 0;
   private int L = 0;
   private int M = 0;
   private String[] N = new String[]{"/data/ui/option.ui", "/data/ui/answer.ui", "/data/ui/wharf1.ui"};
   public short[] m = new short[]{9, 0, 120, 448, 9, 1, 136, 272, 9, 2, 208, 256, 9, 3, 80, 264, 9, 4, 112, 288, 9, 5, 40, 280, 9, 6, 136, 328, 9, 7, 104, 328};
   private String[] O = new String[]{
      "Đạt được 2000 kim tiền", "Đạt được 5 Phong ấn cầu", "Đạt được 5 Bánh Sandwich", "Đạt được 2 Sinh mệnh thạch", "Đạt được 2 huy hiệu"
   };
   private short[][] P = new short[][]{{621, 622}, {623, 624}, {625, 626}, {627, 628}, {629, 630, 631, 632}};
   private short[][] Q = new short[][]{
      {5, 2, 112, 224, 2, 2, 5, 6, 1, 6, 0, 112, 224, 2, 0, 1, 0, 10},
      {4, 0, 48, 176, 2, 2, 3, 6, 3, 6, 0, 112, 224, 2, 2, 1, 0, 10},
      {3, 6, 288, 224, 3, 0, 3, 6, 3, 6, 0, 112, 224, 2, 2, 1, 0, 10},
      {1, 5, 272, 128, 3, 0, 5, 6, 1, 6, 0, 112, 224, 2, 0, 1, 0, 10},
      {1, 5, 272, 128, 3, 2, 0, 0, 0, 3, 6, 288, 224, 3, 0, 0, 0, 0, 4, 0, 48, 176, 2, 0, 0, 0, 0, 5, 2, 112, 224, 2, 2, 0, 0, 0}
   };
   private byte R;
   private byte S;
   private String[] T = new String[]{"Dẫn thưởng", "Tiến hóa", "Dị hóa", "Tài liệu", "Cách mở"};

   public static ScriptEngine a() {
      if (n == null) {
         n = new ScriptEngine();
      }

      return n;
   }

   public ScriptEngine() {
      if (this.q == null) {
         this.q = Player.getInstance();
      }
   }

   public final void b() {
      n = null;
      this.q = null;
   }

   public final void a(BaseScreen var1) {
      if (this.o != null) {
         this.o = null;
      }

      this.o = var1;
      this.g = true;
   }

   public final void c() {
      this.p.openUI("/data/ui/world.ui", 257, this);
      this.u = 0;
   }

   public final void d() {
      this.p.activeView.getComponent(5).setVisible(true);
      this.p.activeView.getComponent(7).setVisible(true);
   }

   private void aS() {
      if (this.p.isTopUI("/data/ui/world.ui")) {
         for (int var1 = 1; var1 <= 7; var1++) {
            if (var1 != 2 && var1 != 3 && var1 != 4) {
               this.p.activeView.getComponent(var1).setVisible(false);
            }
         }
      }
   }

   public final void e() {
      if (this.u < 2 && !OverworldScreen.f && WorldManager.J && this.p.isUIOpen("/data/ui/world.ui")) {
         if (UIManager.isDialogAnimationStep(this.p.getView("/data/ui/world.ui"), 4)) {
            ((ItemListWidget)this.p.getView("/data/ui/world.ui").getComponent(6)).getStyle().text = ((WorldManager)this.o).k;
            this.u = 1;
         } else if (this.u == 1 && ((ItemListWidget)this.p.getView("/data/ui/world.ui").getComponent(1)).getStyle().m.a().getAnimationStepIndex() >= 5) {
            ((ItemListWidget)this.p.getView("/data/ui/world.ui").getComponent(6)).getStyle().text = "";
            this.u = 2;
            WorldManager.J = false;
         }
      }

      this.f();
   }

   public final boolean f() {
      if (this.v < 2 && this.p.isTopUI("/data/ui/openbox.ui")) {
         if (UIManager.isDialogAnimationStep(this.p.activeView, 3) && this.p.activeView.getComponent(1).getStyle().m.a().getAnimationId() == 9) {
            this.p.activeView.getComponent(2).getStyle().text = this.t;
            if (this.p.activeView.getComponent(2).getStyle().b()) {
               if (this.p.isDialogAnimationFinished()) {
                  this.p.activeView.getComponent(1).getStyle().m.a((byte)12, (byte)-1);
                  this.v = 1;
               }

               if (this.o.isKeyPressed(196640)) {
                  this.p.activeView.getComponent(2).getStyle().text = "";
                  this.v = 2;
                  this.g = true;
                  this.aw();
                  return true;
               }
            }
         } else if (this.v == 1) {
            this.p.activeView.getComponent(2).getStyle().text = "";
            if (this.p.isDialogAnimationFinished()) {
               this.v = 2;
               this.g = true;
               this.aw();
               return true;
            }
         }
      }

      return this.g();
   }

   public final boolean g() {
      if (this.v < 2) {
         if (this.p.isTopUI("/data/ui/taskTip.ui")) {
            if (UIManager.isDialogAnimationStep(this.p.activeView, 4) && this.p.activeView.getComponent(1).getStyle().m.a().getAnimationId() == 10) {
               this.p.activeView.getComponent(2).getStyle().text = this.t;
               if (this.p.activeView.getComponent(2).getStyle().b()) {
                  if (this.p.isDialogAnimationFinished()) {
                     this.p.activeView.getComponent(1).getStyle().m.a((byte)13, (byte)-1);
                     this.v = 1;
                  }

                  if (this.o.isKeyPressed(196640)) {
                     this.v = 2;
                     this.g = true;
                     this.br();
                     return true;
                  }
               }
            } else if (this.v == 1) {
               this.p.activeView.getComponent(2).getStyle().text = "";
               if (this.p.isDialogAnimationFinished()) {
                  this.g = true;
                  this.v = 2;
                  this.br();
                  return true;
               }
            }
         }

         this.g = true;
      }

      return false;
   }

   public final void h() {
      this.p.openUI("/data/ui/transmit.ui", 257, this);
      ((MenuWidget)this.p.activeView.getComponent(0)).a.itemCount = A.length;
      ((MenuWidget)this.p.activeView.getComponent(0)).a.a(1);
      this.aT();
   }

   private void aT() {
      this.w = ((MenuWidget)this.p.activeView.getComponent(0)).a.firstVisibleIndex;
      this.h = ((MenuWidget)this.p.activeView.getComponent(0)).a.selectedIndex;

      for (int var1 = 0; var1 < 5; var1++) {
         this.p.activeView.getComponent(var1 + 5).getStyle().text = A[var1 + this.w];
      }

      this.p.activeView.getComponent(13).setY(109 + this.h * 88 / A.length, this.p.activeView.getRoot());
   }

   public final void i() {
      if (this.o.isKeyPressed(4100)) {
         this.p.activeView.navigateSelection(0);
         this.aT();
      } else if (this.o.isKeyPressed(8448)) {
         this.p.activeView.navigateSelection(1);
         this.aT();
      } else if (this.o.isKeyPressed(196640)) {
         WorldManager.a().f = B[this.h * 5];
         WorldManager.a().g = B[this.h * 5 + 1];
         WorldManager.a().h = B[this.h * 5 + 2];
         WorldManager.a().i = B[this.h * 5 + 3];
         WorldManager.w = (byte)B[this.h * 5 + 4];
         WorldManager.a().j = -1;
         GameStateController.getInstance().setScreenMode((byte)9);
      } else {
         if (this.o.isKeyPressed(262144)) {
            this.o.setScreenMode((byte)8);
            this.p.closeUI("/data/ui/transmit.ui");
         }
      }
   }

   public final boolean j() {
      return this.p.isTopUI("/data/ui/openbox.ui") || this.p.isTopUI("/data/ui/taskTip.ui");
   }

   public final void k() {
      String[] var1 = new String[]{"Tùy thân cửa hàng", "Sủng vật", "Lưng bao", "Đồ giám", "Nhiệm vụ", "Lưu dữ liệu"};
      this.aS();
      this.p.openUI("/data/ui/gamemenu.ui", 257, this);
      if (BaseScreen.isVipUnlocked) {
         ((MenuWidget)this.p.activeView.getComponent(0)).a.itemCount = 6;
         this.p.activeView.getComponent(14).getStyle().text = BaseScreen.getString(605 + this.b);
         this.p.activeView.getComponent(15).getStyle().text = var1[0];

         for (int var2 = 0; var2 < 5; var2++) {
            this.p.activeView.getComponent(var2 + 5).getStyle().text = var1[var2 + 1];
         }
      } else {
         this.p.activeView.getComponent(14).getStyle().text = BaseScreen.getString(606 + this.b);
         ((MenuWidget)this.p.activeView.getComponent(0)).a.itemCount = 5;
         this.p.activeView.getComponent(15).getStyle().text = var1[1];

         for (int var3 = 0; var3 < 4; var3++) {
            this.p.activeView.getComponent(var3 + 5).getStyle().text = var1[var3 + 2];
         }

         this.p.activeView.getComponent(9).setVisible(false);
      }

      ((MenuWidget)this.p.activeView.getComponent(0)).a.selectedIndex = this.b;
      this.p.activeView.getComponent(18).getStyle().text = "" + this.q.getArenaPoints();
      this.p.activeView.getComponent(19).getStyle().text = "" + this.q.getGold();
      this.f = 0;
   }

   public final void l() {
      this.o.l();
      if (!BaseScreen.b(this.b, 0) && !this.j() && this.o.isKeyPressed(4100)) {
         this.p.activeView.navigateSelection(0);
      } else if (!BaseScreen.b(this.b, 0) && !this.j() && this.o.isKeyPressed(8448)) {
         this.p.activeView.navigateSelection(1);
      } else if (!this.j() && BaseScreen.I() && this.o.isKeyPressed(196640)) {
         if (BaseScreen.H() && !BaseScreen.b(this.b, 0)) {
            return;
         }

         if (BaseScreen.isVipUnlocked) {
            switch (this.b) {
               case 0:
                  this.o.setScreenMode((byte)14);
                  this.p.closeUI("/data/ui/gamemenu.ui");
                  break;
               case 1:
                  this.c = 0;
                  this.o.m();
                  this.o.setScreenMode((byte)7);
                  this.p.closeUI("/data/ui/gamemenu.ui");
                  break;
               case 2:
                  this.o.m();
                  this.o.setScreenMode((byte)8);
                  this.p.closeUI("/data/ui/gamemenu.ui");
                  break;
               case 3:
                  this.c = 0;
                  this.o.setScreenMode((byte)9);
                  this.p.closeUI("/data/ui/gamemenu.ui");
                  break;
               case 4:
                  this.b = 0;
                  this.o.setScreenMode((byte)10);
                  this.p.closeUI("/data/ui/gamemenu.ui");
                  break;
               case 5:
                  this.p.activeView.getComponent(11).setVisible(false);
                  this.p.activeView.getComponent(12).setVisible(false);
                  this.o.setScreenMode((byte)22);
            }
         } else {
            switch (this.b) {
               case 0:
                  this.c = 0;
                  this.o.m();
                  this.o.setScreenMode((byte)7);
                  this.p.closeUI("/data/ui/gamemenu.ui");
                  break;
               case 1:
                  this.o.m();
                  this.o.setScreenMode((byte)8);
                  this.p.closeUI("/data/ui/gamemenu.ui");
                  break;
               case 2:
                  this.c = 0;
                  this.o.setScreenMode((byte)9);
                  this.p.closeUI("/data/ui/gamemenu.ui");
                  break;
               case 3:
                  this.b = 0;
                  this.o.setScreenMode((byte)10);
                  this.p.closeUI("/data/ui/gamemenu.ui");
                  break;
               case 4:
                  this.p.activeView.getComponent(11).setVisible(false);
                  this.p.activeView.getComponent(12).setVisible(false);
                  this.o.setScreenMode((byte)22);
            }
         }
      } else if (BaseScreen.J() && this.o.isKeyPressed(262144)) {
         this.p.closeUI("/data/ui/gamemenu.ui");
         this.o.setScreenMode((byte)0);
      }

      this.g();
   }

   public final void m() {
      this.aS();
      this.p.openUI("/data/ui/gamesystem.ui", 257, this);
      ((MenuWidget)this.p.activeView.getComponent(0)).a.selectedIndex = this.b;
      this.f = 0;
   }

   public final void n() {
      if (this.o.isKeyPressed(4100)) {
         this.p.activeView.navigateSelection(0);
      } else if (this.o.isKeyPressed(8448)) {
         this.p.activeView.navigateSelection(1);
      } else if (this.o.isKeyPressed(196640)) {
         switch (this.b) {
            case 0:
               this.p.closeUI("/data/ui/gamesystem.ui");
               this.o.setScreenMode((byte)0);
               return;
            case 1:
               this.o.setScreenMode((byte)20);
               this.p.closeUI("/data/ui/gamesystem.ui");
               return;
            case 2:
               this.o.setScreenMode((byte)21);
               this.p.closeUI("/data/ui/gamesystem.ui");
               return;
            case 3:
               if (this.f == 0) {
                  this.p.openUI("/data/ui/option.ui", 257, this);
                  this.c = 1;
                  ((MenuWidget)this.p.activeView.getComponent(0)).a.selectedIndex = this.c;
                  this.p.activeView.getComponent(12).getStyle().text = "";
                  this.p.activeView.getComponent(13).getStyle().text = "Không";
                  this.f = 1;
                  return;
               } else {
                  switch (this.c) {
                     case 0:
                        GameStateController.getInstance().b = 0L;
                        GameStateController.getInstance().a = 0L;
                        Player.getInstance().y = false;
                        GameStateController.getInstance().setScreenMode((byte)7);
                        this.p.closeUI("/data/ui/gamesystem.ui");
                        break;
                     case 1:
                        this.p.closeUI("/data/ui/option.ui");
                        this.f = 0;
                        this.g = true;
                        return;
                  }
               }
         }
      } else {
         if (this.o.isKeyPressed(262144)) {
            if (this.f == 0) {
               this.p.closeUI("/data/ui/gamesystem.ui");
               this.o.setScreenMode((byte)0);
               return;
            }

            if (this.f == 1) {
               this.g = true;
               this.p.closeUI("/data/ui/option.ui");
               this.f = 0;
            }
         }
      }
   }

   public final void o() {
      this.p.openUI("/data/ui/help1.ui", 257, this);
      this.b = 0;
      this.p.activeView.getComponent(6).setVisible(true);
      this.p.activeView.getComponent(7).setVisible(false);
      this.d(this.b);
   }

   private void d(int var1) {
      if (var1 == 0) {
         this.p.activeView.getComponent(5).getStyle().text = "Trợ giúp";
         this.p.activeView.getComponent(8).getStyle().text = "Nhấn nút 2, 4, 6, 8 để di chuyển#nNút 5: công kích, đối thoại, xác nhận#nNút 1, 3: Xem nhiệm vụ#nNút 9: lựa chọn sủng vật cưỡi#nNút 0: Xem bản đồ#nNút mềm trái: menu hệ thống#nNút mềm phải: menu trò chơi";

         for (int var2 = 0; var2 < 28; var2++) {
            this.p.activeView.getComponent(var2 + 9).setVisible(false);
         }
      } else if (var1 > 0) {
         this.p.activeView.getComponent(8).getStyle().text = "";

         for (int var3 = 0; var3 < 14; var3++) {
            this.p.activeView.getComponent(9 + (var3 << 1)).setVisible(true);
            this.p.activeView.getComponent(9 + (var3 << 1) + 1).setVisible(true);
            if ((var1 - 1) * 14 + var3 < 26) {
               this.p.activeView.getComponent(9 + (var3 << 1)).getStyle().m = new SpriteWidget();
               this.p.activeView.getComponent(9 + (var3 << 1)).getStyle().m.a(0);
               this.p.activeView.getComponent(9 + (var3 << 1)).getStyle().m.a = 2;
               this.p.activeView.getComponent(9 + (var3 << 1)).getStyle().m.a(325, false, (byte)-2);
               this.p.activeView.getComponent(9 + (var3 << 1)).getStyle().m.a((var1 - 1) * 14 + var3 + 1);
               if ((var1 - 1) * 14 + var3 <= 10) {
                  this.p.activeView.getComponent(9 + (var3 << 1) + 1).getStyle().text = BaseScreen.getString(var3 + 311);
               } else {
                  this.p.activeView.getComponent(9 + (var3 << 1) + 1).getStyle().text = BaseScreen.getString(333 + ((var1 - 1) * 14 + var3 - 11));
               }
            } else {
               this.p.activeView.getComponent(9 + (var3 << 1)).setVisible(false);
               this.p.activeView.getComponent(9 + (var3 << 1) + 1).setVisible(false);
            }
         }
      }

      this.p.activeView.getComponent(39).getStyle().text = var1 + 1 + "/3";
   }

   public final void p() {
      if (this.o.isKeyPressed(16400)) {
         this.b--;
         if (this.b <= 0) {
            this.b = 0;
         }

         this.d(this.b);
      } else if (this.o.isKeyPressed(32832)) {
         this.b++;
         if (this.b >= 2) {
            this.b = 2;
         }

         this.d(this.b);
      } else {
         if (this.o.isKeyPressed(262144)) {
            this.o.setScreenMode((byte)0);
            this.p.closeUI("/data/ui/help1.ui");
         }
      }
   }

   public final void q() {
      this.p.openUI("/data/ui/help.ui", 257, this);
      this.p.activeView.getComponent(5).getStyle().text = "Quan tại";
      this.p.activeView.getComponent(8).getStyle().text = "Tên trò chơi: Sủng vật Vương quốc - Liệt hỏa#nViệt hóa: BIGAME";
      this.p.activeView.getComponent(6).setVisible(true);
      this.p.activeView.getComponent(7).setVisible(false);

      for (int var1 = 9; var1 < 13; var1++) {
         this.p.activeView.getComponent(var1).setVisible(false);
      }
   }

   public final void r() {
      if (this.o.isKeyPressed(262144)) {
         this.o.setScreenMode((byte)0);
         this.p.closeUI("/data/ui/help.ui");
      }
   }

   public final void s() {
      this.p.openUI("/data/ui/help.ui", 257, this);
      this.p.activeView.getComponent(5).getStyle().text = "Tùy chọn";
      this.p.activeView.getComponent(8).getStyle().text = "";
      this.p.activeView.getComponent(6).setVisible(false);
      this.p.activeView.getComponent(7).setVisible(true);

      for (int var1 = 9; var1 < 13; var1++) {
         this.p.activeView.getComponent(var1).setVisible(true);
      }

      this.aU();
   }

   private void aU() {
      for (int var1 = 1; var1 < 4; var1++) {
         if (var1 <= GameStateController.getInstance().g) {
            this.p.activeView.getComponent(var1 + 9).getStyle().j = -2148;
         } else {
            this.p.activeView.getComponent(var1 + 9).getStyle().j = -8540732;
         }
      }
   }

   public final void t() {
      if (this.o.isKeyPressed(16400)) {
         GameStateController.getInstance().i();
         this.aU();
      } else if (this.o.isKeyPressed(32832)) {
         GameStateController.getInstance().h();
         this.aU();
      } else {
         if (this.o.isKeyPressed(196640)) {
            this.o.setScreenMode((byte)0);
            this.p.closeUI("/data/ui/help.ui");
         }
      }
   }

   public final void u() {
      this.p.openUI("/data/ui/help1.ui", 257, this);
      this.p.closeUI("/data/ui/gamesystem.ui");
      this.r = 0;
      this.p.activeView.getComponent(6).setVisible(true);
      this.p.activeView.getComponent(7).setVisible(false);
      this.d(this.r);
   }

   public final void v() {
      if (this.o.isKeyPressed(16400)) {
         this.r--;
         if (this.r <= 0) {
            this.r = 0;
         }

         this.d(this.r);
      } else if (this.o.isKeyPressed(32832)) {
         this.r++;
         if (this.r >= 2) {
            this.r = 2;
         }

         this.d(this.r);
      } else {
         if (this.o.isKeyPressed(262144)) {
            this.o.setScreenMode((byte)13);
            this.p.closeUI("/data/ui/help1.ui");
         }
      }
   }

   public final void w() {
      this.s();
      this.p.closeUI("/data/ui/gamesystem.ui");
   }

   public final void x() {
      if (this.o.isKeyPressed(16400)) {
         this.p.activeView.navigateSelection(2);
         GameStateController.getInstance().i();
         this.aU();
      } else if (this.o.isKeyPressed(32832)) {
         this.p.activeView.navigateSelection(3);
         GameStateController.getInstance().h();
         this.aU();
      } else {
         if (this.o.isKeyPressed(131072)) {
            this.o.setScreenMode((byte)13);
            this.p.closeUI("/data/ui/help.ui");
         }
      }
   }

   public final void y() {
      this.p.openUI("/data/ui/petstate.ui", 257, this);
      this.f = 0;
      if (this.q.O.size() > 6) {
         ((MenuWidget)this.p.activeView.getComponent(0)).a.a(1);
      } else {
         ((MenuWidget)this.p.activeView.getComponent(0)).a.a(-1);
      }

      this.p.activeView.getComponent(2).getStyle().text = "Ngân hàng Sủng vật";
      this.p.activeView.getComponent(75).setVisible(false);
      this.p.activeView.getComponent(76).setVisible(false);
      this.aV();
   }

   private void aV() {
      ((MenuWidget)this.p.activeView.getComponent(0)).a.itemCount = this.q.O.size();
      this.w = ((MenuWidget)this.p.activeView.getComponent(0)).a.firstVisibleIndex;
      this.h = ((MenuWidget)this.p.activeView.getComponent(0)).a.selectedIndex;
      if (this.q.O.size() >= 6) {
         ((MenuWidget)this.p.activeView.getComponent(0)).a.visibleItemCount = 6;
      } else {
         ((MenuWidget)this.p.activeView.getComponent(0)).a.visibleItemCount = this.q.O.size();
      }

      if (this.h >= this.q.O.size()) {
         this.h = this.q.O.size() - 1;
         ((MenuWidget)this.p.activeView.getComponent(0)).a.selectedIndex = this.h;
      }

      if (this.w > 0 && this.h - this.w < 5) {
         this.w--;
         ((MenuWidget)this.p.activeView.getComponent(0)).a.firstVisibleIndex = this.w;
      }

      for (int var1 = 0; var1 < 6; var1++) {
         if (this.w + var1 < this.q.O.size()) {
            int[] var2 = (int[])this.q.O.elementAt(this.w + var1);
            if (var1 == 0) {
               this.p.activeView.getComponent(14 + var1 * 6).getStyle().text = "" + (this.w + var1 + 1);
            } else {
               this.p.activeView.getComponent(15 + var1 * 6).getStyle().text = "" + (this.w + var1 + 1);
            }

            this.p.activeView.getComponent(16 + var1 * 6).getStyle().text = "#P" + var2[6] * 100 / Pet.a(var2[0], var2[1], var2[4], 1);
            this.p.activeView.getComponent(17 + var1 * 6).getStyle().text = "#P" + Pet.a((short)var2[7], (short)var2[1]);
         } else {
            this.p.activeView.getComponent(16 + var1 * 6).getStyle().text = "#P0";
            this.p.activeView.getComponent(17 + var1 * 6).getStyle().text = "#P0";
         }
      }

      int[] var4 = null;
      if (this.q.O.size() > 0) {
         var4 = (int[])this.q.O.elementAt(this.h);
      }

      if (var4 != null) {
         if (this.p.activeView.getComponent(48).getStyle().m != null) {
            this.p.activeView.getComponent(48).getStyle().m.d();
         } else {
            this.p.activeView.getComponent(48).getStyle().m = new SpriteWidget();
            this.p.activeView.getComponent(48).getStyle().m.a(0);
            this.p.activeView.getComponent(48).getStyle().m.a = 3;
         }

         this.p.activeView.getComponent(48).getStyle().m.a(GameDatabase.getValue((byte)0, (short)var4[0], (byte)17), false, (byte)-1);
         this.p.activeView.getComponent(51).getStyle().text = BaseScreen.getString(GameDatabase.getValue((byte)0, (short)var4[0], (byte)0));
         this.p.activeView.getComponent(52).getStyle().text = BaseScreen.getString(365 + GameDatabase.getValue((byte)0, (short)var4[0], (byte)1));
         if (GameDatabase.getValue((byte)0, (short)var4[0], (byte)19) == -1) {
            this.p.activeView.getComponent(62).getStyle().text = "";
         } else if (GameDatabase.gameDatabase[0][GameDatabase.getValue((byte)0, (short)var4[0], (byte)19)][2] == 1
            || GameDatabase.gameDatabase[0][GameDatabase.getValue((byte)0, (short)var4[0], (byte)19)][2] == 2) {
            this.p.activeView.getComponent(62).getStyle().text = "Có thể tiến hóa";
         } else if (GameDatabase.gameDatabase[0][GameDatabase.getValue((byte)0, (short)var4[0], (byte)19)][2] == 3) {
            this.p.activeView.getComponent(62).getStyle().text = "Có thể dị hoá";
         }

         this.p.activeView.getComponent(61).getStyle().text = Pet.y(var4[0]);
         if (this.p.activeView.getComponent(59).getStyle().m == null) {
            this.p.activeView.getComponent(59).getStyle().m = new SpriteWidget();
            this.p.activeView.getComponent(59).getStyle().m.a(0);
            this.p.activeView.getComponent(59).getStyle().m.a = 2;
            this.p.activeView.getComponent(59).getStyle().m.a(258, false, (byte)-1);
         }

         if (var4[2] != -1) {
            this.p.activeView.getComponent(59).getStyle().m.a(GameDatabase.gameDatabase[3][var4[2]][1]);
            this.p.activeView.getComponent(60).getStyle().text = BaseScreen.getString(GameDatabase.gameDatabase[3][var4[2]][0]);
         } else {
            this.p.activeView.getComponent(59).getStyle().m.a(0);
            this.p.activeView.getComponent(60).getStyle().text = "";
         }

         this.p.activeView.getComponent(65).getStyle().text = "" + var4[1];
         this.p.activeView.getComponent(66).getStyle().text = "" + Pet.a(var4[0], var4[1], var4[4], 2);
         this.p.activeView.getComponent(67).getStyle().text = "" + Pet.a(var4[0], var4[1], var4[4], 3);
         this.p.activeView.getComponent(68).getStyle().text = "" + Pet.a(var4[0], var4[1], var4[4], 4);
         int var6 = var4[4];
         int var5 = GameDatabase.getValue((byte)0, (short)var4[0], (byte)4) - 1;

         for (int var3 = 0; var3 < 5; var3++) {
            this.p.activeView.getComponent(74 - var3).setVisible(true);
            this.p.activeView.getComponent(74 - var3).getStyle().m.a = 3;
            if (var3 > var5) {
               this.p.activeView.getComponent(74 - var3).setVisible(false);
            } else if (var6 > 0) {
               this.p.activeView.getComponent(74 - var3).getStyle().m.a((byte)14, (byte)-1);
               var6--;
            } else {
               this.p.activeView.getComponent(74 - var3).getStyle().m.a((byte)16, (byte)-1);
            }
         }

         if (this.b == 1) {
            this.p.activeView.getComponent(64).getStyle().text = "Lấy ra";
            return;
         }

         if (this.b == 2) {
            this.p.activeView.getComponent(64).getStyle().text = "Phóng sinh";
         }
      }
   }

   public final void z() {
      if (this.f == 0 && this.o.isKeyPressed(4100)) {
         this.p.activeView.navigateSelection(0);
         this.aV();
      } else if (this.f == 0 && this.o.isKeyPressed(8448)) {
         this.p.activeView.navigateSelection(1);
         this.aV();
      }

      if (this.f == 0) {
         if (this.o.isKeyPressed(196640)) {
            if (this.b == 1) {
               if (this.q.partyPetCount >= 6) {
                  this.p.openUI("/data/ui/msgwarm.ui", 257, this);
                  this.a("Ba lô Sủng vật đã đủ", "Nhấn nút 5 để tiếp tục");
                  this.f = 1;
               } else {
                  if (this.q.O.size() <= 0) {
                     return;
                  }

                  this.q.r(this.h);
                  if (this.q.O.size() <= 0) {
                     this.o.setScreenMode((byte)16);
                     this.p.closeUI("/data/ui/petstate.ui");
                  } else {
                     this.aV();
                  }
               }
            } else if (this.b == 2) {
               if (this.q.O.size() <= 0) {
                  return;
               }

               int[] var1 = (int[])this.q.O.elementAt(this.h);
               if (GameDatabase.getValue((byte)0, (short)var1[0], (byte)22) == 2) {
                  this.f = 2;
                  this.E();
                  this.a("Thần thú không thể phóng sinh", "Nhấn nút 5 để tiếp tục");
               } else {
                  this.f = 1;
                  this.p.openUI("/data/ui/msgconfirm.ui", 257, this);
                  this.b("Bạn muốn phóng sinh sủng vật này?", "Xác nhận");
               }
            }
         } else if (this.o.isKeyPressed(786432)) {
            this.o.setScreenMode((byte)16);
            this.p.closeUI("/data/ui/petstate.ui");
         }
      } else if (this.f > 0) {
         if (this.o.isKeyPressed(196640) && this.b == 1 || this.o.isKeyPressed(131072) && this.b == 2) {
            if (this.b == 1) {
               this.p.closeUI("/data/ui/msgwarm.ui");
               this.f = 0;
            } else if (this.b == 2) {
               if (this.f == 1) {
                  this.p.closeUI("/data/ui/msgconfirm.ui");
                  int[] var2 = (int[])this.q.O.elementAt(this.h);
                  this.q.l(var2[2]);
                  this.q.q(this.h);
                  this.aV();
               } else if (this.f == 2) {
                  this.F();
               }

               this.f = 0;
            }
         } else if (this.o.isKeyPressed(786432)) {
            if (this.b == 1) {
               return;
            }

            this.p.closeUI("/data/ui/msgconfirm.ui");
            this.f = 0;
         }
      }

      this.g = true;
   }

   public final void A() {
      this.aS();
      this.p.openUI("/data/ui/shop.ui", 257, this);
      this.b = 0;
      this.p.activeView.getComponent(5).getStyle().text = "Ngân hàng Sủng vật";
      this.p.activeView.getComponent(6).getStyle().text = "Gởi lại";
      this.p.activeView.getComponent(7).getStyle().text = "Lấy ra";
      this.p.activeView.getComponent(9).getStyle().text = "Phóng sinh";
   }

   public final void B() {
      if (this.o.isKeyPressed(4100)) {
         this.p.activeView.navigateSelection(0);
      } else if (this.o.isKeyPressed(8448)) {
         this.p.activeView.navigateSelection(1);
      } else if (this.o.isKeyPressed(196640)) {
         switch (this.b) {
            case 0:
               this.c = 0;
               this.o.setScreenMode((byte)7);
               this.p.closeUI("/data/ui/shop.ui");
               return;
            case 1:
               this.o.setScreenMode((byte)15);
               this.p.closeUI("/data/ui/shop.ui");
               return;
            case 2:
               this.o.setScreenMode((byte)15);
               this.p.closeUI("/data/ui/shop.ui");
               return;
            case 3:
               OverworldScreen.e = true;
               this.p.closeUI("/data/ui/shop.ui");
               this.o.setScreenMode((byte)0);
         }
      } else {
         if (this.o.isKeyPressed(262144)) {
            OverworldScreen.e = true;
            this.p.closeUI("/data/ui/shop.ui");
            this.o.setScreenMode((byte)0);
         }
      }
   }

   public final void C() {
      this.aS();
      this.p.openUI("/data/ui/shop.ui", 257, this);
      this.b = 0;
   }

   public final void D() {
      this.o.l();
      if (!BaseScreen.b(this.b, 0) && !this.j() && this.f == 0 && this.o.isKeyPressed(4100) && this.aW()) {
         this.p.activeView.navigateSelection(0);
      } else if (!BaseScreen.b(this.b, 0) && !this.j() && this.f == 0 && this.o.isKeyPressed(8448) && this.aW()) {
         this.p.activeView.navigateSelection(1);
      } else if (this.aW() && !this.j() && BaseScreen.I() && this.o.isKeyPressed(196640)) {
         if (BaseScreen.H() && !BaseScreen.b(this.b, 0)) {
            return;
         }

         switch (this.b) {
            case 0:
               this.o.m();
               this.o.setScreenMode((byte)2);
               this.p.closeUI("/data/ui/shop.ui");
               break;
            case 1:
               this.o.setScreenMode((byte)3);
               this.p.closeUI("/data/ui/shop.ui");
               break;
            case 2:
               if (this.f == 0) {
                  if (Player.getInstance().A() == -1) {
                     this.f = 6;
                     this.E();
                     this.a("Toàn bộ trạng thái đã đầy, không cần khôi phục", "Nhấn nút 5 để tiếp tục");
                  } else if (!BaseScreen.isVipUnlocked) {
                     this.f = 3;

                     for (int var1 = 0; var1 < this.q.partyPetCount; var1++) {
                        this.q.petParty[var1].I();
                     }

                     this.b("Ba lô sủng vật trạng thái toàn bộ khôi phục");
                  } else {
                     ScriptEngine var2 = this;
                     this.p.openUI("/data/ui/msgRecover.ui", 257, var2);
                     var2.p.activeView.getComponent(4).getStyle().text = "Có khôi phục trạng thái ba lô sủng vật không?";
                     var2.p.activeView.getComponent(5).getStyle().text = "Cần tiền tài: ";
                     var2.p.activeView.getComponent(6).getStyle().text = "" + Player.getInstance().A();
                     var2.p.activeView.getComponent(8).getStyle().text = "" + Player.getInstance().getGold();
                     this.p.closeUI("/data/ui/shop.ui");
                     this.f = 1;
                  }
               } else if (this.f == 1) {
                  int var3 = Player.getInstance().A();
                  if (!Player.getInstance().hasGold(var3)) {
                     this.f = 2;
                     this.E();
                     this.a("Kim tiền chưa đủ", "Nhấn nút 5 để tiếp tục");
                  } else {
                     this.f = 3;
                     Player.getInstance().addGold(-var3);

                     for (int var4 = 0; var4 < this.q.partyPetCount; var4++) {
                        this.q.petParty[var4].I();
                     }

                     this.b("Ba lô sủng vật trạng thái toàn bộ khôi phục");
                  }

                  this.p.closeUI("/data/ui/msgRecover.ui");
               } else {
                  if (this.f == 2 && BaseScreen.isVipUnlocked) {
                     this.o.setScreenMode((byte)102);
                  }

                  this.f = 0;
                  this.F();
               }
               break;
            case 3:
               OverworldScreen.e = true;
               this.p.closeUI("/data/ui/shop.ui");
               this.o.setScreenMode((byte)0);
         }
      } else if (!this.j() && this.o.isKeyPressed(262144) && BaseScreen.J() && this.aW()) {
         if (this.f == 1) {
            this.p.openUI("/data/ui/shop.ui", 257, this);
            this.p.closeUI("/data/ui/msgRecover.ui");
            this.f = 0;
            this.b = 0;
         } else if (this.f == 0) {
            OverworldScreen.e = true;
            this.p.closeUI("/data/ui/shop.ui");
            this.o.setScreenMode((byte)0);
         }
      }

      if (this.f == 3 && this.ax()) {
         this.f = 4;
         this.p.openUI("/data/ui/shop.ui", 257, this);
         this.H();
         this.a("Đang lưu...");
         this.J();
      } else if (this.f == 4 && ((WorldManager)this.o).k()) {
         this.a("Lưu thành công");
         this.f = 5;
      } else if (this.f == 5) {
         this.I();
         this.b = 0;
         this.f = 0;
      }

      this.f();
      this.g = true;
   }

   public final void a(int var1, byte var2) {
      this.p.openUI("/data/ui/shopbuy.ui", 257, this);
      this.b = 0;
      this.f = 0;
      ((MenuWidget)this.p.activeView.getComponent(0)).a.itemCount = GameDatabase.gameDatabase[var1].length;
      ((MenuWidget)this.p.activeView.getComponent(0)).a.a(1);
      this.p.activeView.getComponent(5).getStyle().text = "Mua";
      if (this.o instanceof WorldManager) {
         this.p.activeView.getComponent(57).setVisible(true);
         this.p.activeView.getComponent(58).setVisible(true);
         this.p.activeView.getComponent(57).getStyle().text = "Mua sắm";
         this.p.activeView.getComponent(58).getStyle().text = "Phản hồi";
         this.p.activeView.getComponent(39).setVisible(false);
         this.p.activeView.getComponent(40).setVisible(false);
      } else if (this.o instanceof BattleScreen) {
         this.p.activeView.getComponent(57).setVisible(false);
         this.p.activeView.getComponent(58).setVisible(false);
         this.p.activeView.getComponent(39).setVisible(true);
         this.p.activeView.getComponent(40).setVisible(true);
         this.p.activeView.getComponent(39).getStyle().text = "Mua sắm";
         this.p.activeView.getComponent(40).getStyle().text = "Phản hồi";
      }

      this.b(var1, var2);
   }

   private void b(int var1, byte var2) {
      this.w = ((MenuWidget)this.p.activeView.getComponent(0)).a.firstVisibleIndex;
      this.h = ((MenuWidget)this.p.activeView.getComponent(0)).a.selectedIndex;

      for (int var3 = 0; var3 < 5; var3++) {
         if (this.p.activeView.getComponent(var3 + 51).getStyle().m == null) {
            this.p.activeView.getComponent(var3 + 51).getStyle().m = new SpriteWidget();
            this.p.activeView.getComponent(var3 + 51).getStyle().m.a(0);
            this.p.activeView.getComponent(var3 + 51).getStyle().m.a = 2;
            this.p.activeView.getComponent(var3 + 51).getStyle().m.a(258, false, (byte)-1);
         }

         short[] var10001 = GameDatabase.gameDatabase[var1][this.w + var3];
         this.p.activeView.getComponent(var3 + 51).getStyle().m.a(var10001[1]);
         this.p.activeView.getComponent(14 + var3 * 5).getStyle().text = BaseScreen.getString(GameDatabase.gameDatabase[var1][this.w + var3][0]);
         if (this.o instanceof WorldManager) {
            if (this.j == 1 || this.j == 3) {
               this.p.activeView.getComponent(15 + var3 * 5).getStyle().text = "" + GameDatabase.gameDatabase[var1][this.w + var3][3];
            } else if (this.j == 2) {
               if (GameDatabase.gameDatabase[var1][this.w + var3][4] == 0) {
                  this.p.activeView.getComponent(15 + var3 * 5).getStyle().text = "" + GameDatabase.gameDatabase[var1][this.w + var3][3] * 3 / 2;
               } else {
                  this.p.activeView.getComponent(15 + var3 * 5).getStyle().text = "" + GameDatabase.gameDatabase[var1][this.w + var3][3];
               }
            }
         } else if (var2 == 0 && var1 == 4 && this.w + var3 == 0) {
            this.p.activeView.getComponent(15 + var3 * 5).getStyle().text = "" + GameDatabase.gameDatabase[var1][this.w + var3][3];
         } else {
            this.p.activeView.getComponent(15 + var3 * 5).getStyle().text = "" + (GameDatabase.gameDatabase[var1][this.w + var3][3] << 1);
         }

         if (GameDatabase.gameDatabase[var1][this.w + var3][4] == 0) {
            this.p.activeView.getComponent(var3 + 45).getStyle().m.a(84);
         } else if (GameDatabase.gameDatabase[var1][this.w + var3][4] == 1) {
            this.p.activeView.getComponent(var3 + 45).getStyle().m.a(83);
         } else if (GameDatabase.gameDatabase[var1][this.w + var3][4] == 2) {
            this.p.activeView.getComponent(var3 + 45).getStyle().m.a(74);
         }
      }

      this.p.activeView.getComponent(56).getStyle().text = BaseScreen.getString(GameDatabase.gameDatabase[var1][this.h][2]);
      this.p.activeView.getComponent(43).getStyle().text = "" + this.q.getArenaPoints();
      this.p.activeView.getComponent(44).getStyle().text = "" + this.q.getGold();
      this.p.activeView.getComponent(38).setY(102 + this.h * 84 / GameDatabase.gameDatabase[var1].length, this.p.activeView.getRoot());
   }

   public final void a(byte var1, byte var2) {
      this.o.l();
      if (!BaseScreen.b(this.b, 0) && this.f <= 1 && this.o.isKeyPressed(4100) && !this.j()) {
         this.p.activeView.navigateSelection(0);
         if (this.f == 0) {
            this.b((int)var1, var2);
         }
      } else if (!BaseScreen.b(this.b, 0) && this.f <= 1 && this.o.isKeyPressed(8448) && !this.j()) {
         this.p.activeView.navigateSelection(1);
         if (this.f == 0) {
            this.b((int)var1, var2);
         }
      } else if (this.f == 1 && this.o.isKeyPressed(16400) && this.c > 0 && !this.j()) {
         this.c--;
         if (this.c <= 0) {
            this.c = 99 - this.q.countItem(this.h, var2);
         }

         this.a(this.c, this.c * GameDatabase.gameDatabase[var1][this.h][3], GameDatabase.gameDatabase[var1][this.h][4], var1);
      } else if (this.f == 1 && this.o.isKeyPressed(32832) && !this.j()) {
         this.c++;
         if (this.c > 99 - this.q.countItem(this.h, var2)) {
            this.c = 1;
         }

         this.a(this.c, this.c * GameDatabase.gameDatabase[var1][this.h][3], GameDatabase.gameDatabase[var1][this.h][4], var1);
      } else if (BaseScreen.I() && this.o.isKeyPressed(196640) && !this.j()) {
         if (BaseScreen.H() && !BaseScreen.b(this.b, 0)) {
            return;
         }

         if (GameDatabase.gameDatabase[var1][this.h][4] == 2) {
            if (this.f == 0) {
               if (BaseScreen.isVipUnlocked) {
                  if (!this.q.a(this.h, 1, (byte)0)) {
                     this.f = 3;
                     this.E();
                     this.a("Đạo cụ đã đủ", "Nhấn nút 5 để tiếp tục");
                  } else {
                     this.o.setScreenMode((byte)101);
                  }
               } else {
                  this.f = 3;
                  this.p.openUI("/data/ui/msgwarm.ui", 257, this);
                  this.a("Công năng còn chưa mở khải", "Nhấn nút 5 để tiếp tục");
               }
            } else {
               this.f = 0;
               this.p.closeUI("/data/ui/msgwarm.ui");
            }
         } else if (var2 == 2 && this.h < 12) {
            this.c = 1;
            if (this.q.a(this.h, this.c, var2)) {
               if (this.f == 0) {
                  this.r = 0;
                  this.b(var1, var2);
               } else if (this.f > 0) {
                  if (BaseScreen.isVipUnlocked) {
                     if (this.f == 4) {
                        this.o.setScreenMode((byte)104);
                     } else if (this.f == 3) {
                        this.o.setScreenMode((byte)102);
                     }
                  }

                  this.f = 0;
                  this.p.closeUI("/data/ui/msgwarm.ui");
               }
            } else if (this.f == 0) {
               this.f = 2;
               this.p.openUI("/data/ui/msgwarm.ui", 257, this);
               this.a("Đạo cụ này đã đủ", "Nhấn nút 5 để tiếp tục");
            } else {
               this.o.m();
               this.f = 0;
               this.p.closeUI("/data/ui/msgwarm.ui");
               this.b((int)var1, var2);
            }
         } else if (this.q.a(this.h, this.c, var2)) {
            if (this.f == 0) {
               this.f = 1;
               this.p.openUI("/data/ui/msgyn.ui", 257, this);
               this.c = 1;
               this.r = 0;
               this.a(this.c, this.c * GameDatabase.gameDatabase[var1][this.h][3], GameDatabase.gameDatabase[var1][this.h][4], var1);
            } else if (this.f == 1) {
               this.b(var1, var2);
            } else if (this.f == 2) {
               WorldManager.a().M.i();
               this.f = 0;
               this.c = 0;
               this.p.closeUI("/data/ui/msgwarm.ui");
               this.b((int)var1, var2);
            } else {
               if (BaseScreen.isVipUnlocked) {
                  if (this.f == 4) {
                     this.p.closeUI("/data/ui/msgyn.ui");
                     this.o.setScreenMode((byte)104);
                  } else if (this.f == 3) {
                     this.p.closeUI("/data/ui/msgyn.ui");
                     this.o.setScreenMode((byte)102);
                  }
               }

               this.f = 0;
               this.c = 0;
               this.p.closeUI("/data/ui/msgwarm.ui");
            }
         } else if (this.f == 0) {
            this.f = 2;
            this.p.openUI("/data/ui/msgwarm.ui", 257, this);
            this.a("Đạo cụ này đã đủ", "Nhấn nút 5 để tiếp tục");
         } else {
            this.f = 0;
            this.p.closeUI("/data/ui/msgwarm.ui");
         }
      } else if (this.o.isKeyPressed(262144) && !this.j() && BaseScreen.J()) {
         if (this.f == 0) {
            if (this.o instanceof WorldManager) {
               if (this.j == 1) {
                  this.o.setScreenMode((byte)1);
               } else if (this.j == 2) {
                  this.o.setScreenMode((byte)14);
               } else if (this.j == 3) {
                  this.o.setScreenMode((byte)27);
               }

               this.p.closeUI("/data/ui/shopbuy.ui");
            } else {
               this.p.closeUI("/data/ui/shopbuy.ui");
               this.o.setScreenMode((byte)20);
            }
         } else if (this.f == 1) {
            this.f = 0;
            this.c = 0;
            this.p.closeUI("/data/ui/msgyn.ui");
         }
      }

      this.f();
   }

   private void b(byte var1, byte var2) {
      if ((!(this.o instanceof WorldManager) || this.j != 1 && this.j != 3 || !this.q.b(this.h, this.c * GameDatabase.gameDatabase[var1][this.h][3], (int)var1))
         && (
            this.j != 2
               || (GameDatabase.gameDatabase[var1][this.h][4] != 0 || !this.q.b(this.h, this.c * GameDatabase.gameDatabase[var1][this.h][3] * 3 / 2, (int)var1))
                  && (GameDatabase.gameDatabase[var1][this.h][4] == 0 || !this.q.b(this.h, this.c * GameDatabase.gameDatabase[var1][this.h][3], (int)var1))
         )) {
         if (this.o instanceof BattleScreen && this.q.b(this.h, this.c * GameDatabase.gameDatabase[var1][this.h][3] << 1, (int)var1)) {
            if (this.r == 0) {
               this.q.c(this.h, this.c, var2);
               if (GameDatabase.gameDatabase[var1][this.h][4] == 0) {
                  this.q.addGold(-this.c * GameDatabase.gameDatabase[var1][this.h][3] << 1);
               } else {
                  this.q.addArenaPoints(-this.c * GameDatabase.gameDatabase[var1][this.h][3] << 1);
               }

               this.p.openUI("/data/ui/msgwarm.ui", 257, this);
               if (var1 == 3 && this.h == 17) {
                  this.a(
                     "Đã thành công mua sắm #2" + BaseScreen.getString(GameDatabase.gameDatabase[var1][this.h][0]) + " * " + 5 * this.c,
                     "Nhấn nút 5 để tiếp tục"
                  );
               } else {
                  this.a(
                     "Đã thành công mua sắm #2" + BaseScreen.getString(GameDatabase.gameDatabase[var1][this.h][0]) + " * " + this.c, "Nhấn nút 5 để tiếp tục"
                  );
               }

               this.f = 2;
               this.c = 1;
            } else {
               this.f = 0;
            }

            this.p.closeUI("/data/ui/msgyn.ui");
         } else {
            if (this.r == 0) {
               this.p.openUI("/data/ui/msgwarm.ui", 257, this);
               if (GameDatabase.gameDatabase[var1][this.h][4] == 0) {
                  this.f = 3;
                  this.a("Kim tiền chưa đủ", "Nhấn nút 5 để tiếp tục");
               } else {
                  this.f = 4;
                  this.a("Số lượng Huy chương chưa đủ", "Nhấn nút 5 để tiếp tục");
               }

               label104:
               if (this.o instanceof WorldManager) {
                  int var3;
                  if (this.j != 1 && this.j != 3) {
                     if (this.j != 2) {
                        break label104;
                     }

                     if (GameDatabase.gameDatabase[var1][this.h][4] == 0) {
                        var3 = this.c * GameDatabase.gameDatabase[var1][this.h][3] * 3 / 2;
                     } else {
                        var3 = this.c * GameDatabase.gameDatabase[var1][this.h][3];
                     }
                  } else {
                     var3 = this.c * GameDatabase.gameDatabase[var1][this.h][3];
                  }

                  this.b(new int[]{var1, var2, this.h, GameDatabase.gameDatabase[var1][this.h][4], var3, this.c});
               } else if (this.o instanceof BattleScreen) {
                  int var4 = this.c * GameDatabase.gameDatabase[var1][this.h][3] << 1;
                  this.b(new int[]{var1, var2, this.h, GameDatabase.gameDatabase[var1][this.h][4], var4, this.c});
               }
            } else {
               this.f = 0;
            }

            this.p.closeUI("/data/ui/msgyn.ui");
         }
      } else {
         if (this.r == 0) {
            this.q.c(this.h, this.c, var2);
            if (GameDatabase.gameDatabase[var1][this.h][4] == 0) {
               if (this.j == 1 || this.j == 3) {
                  this.q.addGold(-this.c * GameDatabase.gameDatabase[var1][this.h][3]);
               } else if (this.j == 2) {
                  this.q.addGold(-this.c * GameDatabase.gameDatabase[var1][this.h][3] * 3 / 2);
               }
            } else if (this.j == 1 || this.j == 3) {
               this.q.addArenaPoints(-this.c * GameDatabase.gameDatabase[var1][this.h][3]);
            } else if (this.j == 2) {
               this.q.addArenaPoints(-this.c * GameDatabase.gameDatabase[var1][this.h][3]);
            }

            this.p.openUI("/data/ui/msgwarm.ui", 257, this);
            if (var1 == 3 && this.h == 17) {
               this.a(
                  "Đã thành công mua sắm #2" + BaseScreen.getString(GameDatabase.gameDatabase[var1][this.h][0]) + " * " + 5 * this.c, "Nhấn nút 5 để tiếp tục"
               );
            } else {
               this.a("Đã thành công mua sắm #2" + BaseScreen.getString(GameDatabase.gameDatabase[var1][this.h][0]) + " * " + this.c, "Nhấn nút 5 để tiếp tục");
            }

            this.f = 2;
            this.c = 1;
         } else {
            this.f = 0;
         }

         this.p.closeUI("/data/ui/msgyn.ui");
      }
   }

   private void b(int[] var1) {
      if (this.C == null) {
         this.C = new Vector();
      } else {
         this.C.removeAllElements();
      }

      this.C.addElement(var1);
   }

   private void a(int var1, int var2, int var3, int var4) {
      if (var4 == 3 && this.h == 17) {
         this.p.activeView.getComponent(9).getStyle().text = "" + var1 * 5;
      } else {
         this.p.activeView.getComponent(9).getStyle().text = "" + var1;
      }

      if (this.o instanceof WorldManager) {
         if (this.j == 1 || this.j == 3) {
            this.p.activeView.getComponent(11).getStyle().text = "" + var2;
         } else if (this.j == 2) {
            if (var3 == 0) {
               this.p.activeView.getComponent(11).getStyle().text = "" + var2 * 3 / 2;
            } else {
               this.p.activeView.getComponent(11).getStyle().text = "" + var2;
            }
         }
      } else {
         this.p.activeView.getComponent(11).getStyle().text = "" + (var2 << 1);
      }

      if (var3 == 0) {
         this.p.activeView.getComponent(12).getStyle().m.a(84);
      } else {
         if (var3 == 1) {
            this.p.activeView.getComponent(12).getStyle().m.a(83);
         }
      }
   }

   public final void E() {
      this.p.openUI("/data/ui/msgwarm.ui", 257, this);
   }

   public final void F() {
      this.p.closeUI("/data/ui/msgwarm.ui");
   }

   public final boolean G() {
      return !this.p.isTopUI("/data/ui/msgwarm.ui");
   }

   public final void a(String var1, String var2) {
      this.p.activeView.getComponent(6).getStyle().text = var2;
      this.p.activeView.getComponent(7).getStyle().text = var1;
   }

   public final void H() {
      this.p.openUI("/data/ui/msgtip.ui", 257, this);
   }

   public final void I() {
      this.p.closeUI("/data/ui/msgtip.ui");
   }

   private boolean aW() {
      return !this.p.isTopUI("/data/ui/msgtip.ui");
   }

   public final void a(String var1) {
      this.p.activeView.getComponent(2).getStyle().text = var1;
   }

   public final void J() {
      this.p.activeView.getComponent(3).setVisible(false);
      this.p.activeView.getComponent(4).setVisible(false);
   }

   public final void K() {
      if (this.f == 0) {
         if (this.o.isKeyPressed(196640)) {
            this.f = 1;
            this.a("Đang lưu...");
            this.J();
            return;
         }

         if (this.o.isKeyPressed(262144)) {
            if (BaseScreen.isVipUnlocked) {
               this.b = 5;
            } else {
               this.b = 4;
            }

            this.o.setScreenMode((byte)6);
            this.p.closeUI("/data/ui/msgtip.ui");
            this.f = 0;
            return;
         }
      } else if (this.f == 1) {
         if (((WorldManager)this.o).k()) {
            this.a("Lưu thành công");
            this.f = 2;
            return;
         }
      } else if (this.f == 2) {
         this.p.closeUI("/data/ui/msgtip.ui");
         this.p.closeUI("/data/ui/gamemenu.ui");
         this.o.setScreenMode((byte)0);
         this.f = 0;
      }
   }

   private void b(String var1, String var2) {
      this.p.activeView.getComponent(2).getStyle().text = var2;
      this.p.activeView.getComponent(4).getStyle().text = var1;
   }

   public final void L() {
      this.p.openUI("/data/ui/shopbuy.ui", 257, this);
      this.b = 0;
      this.f = 0;
      this.p.activeView.getComponent(5).getStyle().text = "Bán ra";
      this.p.activeView.getComponent(39).getStyle().text = "";
      this.p.activeView.getComponent(40).getStyle().text = "";
      this.p.activeView.getComponent(57).getStyle().text = "Bán đi";
      this.p.activeView.getComponent(58).getStyle().text = "Phản hồi";
      this.q.x();
      this.aX();
   }

   private void aX() {
      if (this.q.S.size() > 5) {
         ((MenuWidget)this.p.activeView.getComponent(0)).a.a(1);
      } else {
         ((MenuWidget)this.p.activeView.getComponent(0)).a.a(0);
      }

      ((MenuWidget)this.p.activeView.getComponent(0)).a.itemCount = this.q.S.size();
      this.w = ((MenuWidget)this.p.activeView.getComponent(0)).a.firstVisibleIndex;
      this.h = ((MenuWidget)this.p.activeView.getComponent(0)).a.selectedIndex;
      if (this.h >= this.q.S.size()) {
         this.h = this.q.S.size() - 1;
         ((MenuWidget)this.p.activeView.getComponent(0)).a.selectedIndex = this.h;
      }

      if (this.w > 0 && this.h - this.w < 4) {
         this.w--;
         ((MenuWidget)this.p.activeView.getComponent(0)).a.firstVisibleIndex = this.w;
      }

      for (int var1 = 0; var1 < 5; var1++) {
         if (this.w + var1 < this.q.S.size()) {
            int var2 = ((int[])this.q.S.elementAt(this.w + var1))[0];
            if (this.p.activeView.getComponent(var1 + 51).getStyle().m == null) {
               this.p.activeView.getComponent(var1 + 51).getStyle().m = new SpriteWidget();
               this.p.activeView.getComponent(var1 + 51).getStyle().m.a(0);
               this.p.activeView.getComponent(var1 + 51).getStyle().m.a = 2;
               this.p.activeView.getComponent(var1 + 51).getStyle().m.a(258, false, (byte)-1);
            }

            this.p.activeView.getComponent(var1 + 51).getStyle().m.a(GameDatabase.gameDatabase[4][var2][1]);
            this.p.activeView.getComponent(14 + var1 * 5).getStyle().text = BaseScreen.getString(GameDatabase.gameDatabase[4][var2][0]);
            this.p.activeView.getComponent(15 + var1 * 5).getStyle().text = "" + GameDatabase.gameDatabase[4][var2][3] / 2;
            if (GameDatabase.gameDatabase[4][var2][4] == 0) {
               this.p.activeView.getComponent(var1 + 45).getStyle().m.a(84);
            } else if (GameDatabase.gameDatabase[4][var2][4] == 1) {
               this.p.activeView.getComponent(var1 + 45).getStyle().m.a(83);
            } else if (GameDatabase.gameDatabase[4][var2][4] == 2) {
               this.p.activeView.getComponent(var1 + 45).getStyle().m.a(74);
            }
         } else {
            if (this.p.activeView.getComponent(var1 + 51).getStyle().m != null) {
               this.p.activeView.getComponent(var1 + 51).getStyle().m.d();
            }

            this.p.activeView.getComponent(14 + var1 * 5).getStyle().text = "";
            this.p.activeView.getComponent(15 + var1 * 5).getStyle().text = "";
            this.p.activeView.getComponent(var1 + 45).getStyle().m.a(86);
         }
      }

      if (this.q.S.size() > 0) {
         this.p.activeView.getComponent(56).getStyle().text = BaseScreen.getString(GameDatabase.gameDatabase[4][((int[])this.q.S.elementAt(this.h))[0]][2]);
      } else {
         this.p.activeView.getComponent(56).getStyle().text = "";
      }

      if (this.q.S.size() > 0) {
         this.p.activeView.getComponent(43).getStyle().text = "" + this.q.getArenaPoints();
         this.p.activeView.getComponent(44).getStyle().text = "" + this.q.getGold();
         this.p.activeView.getComponent(38).setY(102 + this.h * 84 / this.q.S.size(), this.p.activeView.getRoot());
      }
   }

   public final void M() {
      if (this.o.isKeyPressed(4100)) {
         this.p.activeView.navigateSelection(0);
      } else if (this.o.isKeyPressed(8448)) {
         this.p.activeView.navigateSelection(1);
      } else if (this.f == 1 && this.o.isKeyPressed(16400) && this.c > 0) {
         int[] var3 = (int[])this.q.S.elementAt(this.h);
         this.c--;
         if (this.c <= 0) {
            this.c = this.q.countItem(var3[0], (byte)0);
         }

         this.a(this.c, this.c * GameDatabase.gameDatabase[4][var3[0]][3] / 2, GameDatabase.gameDatabase[4][var3[0]][4], 4);
      } else if (this.f == 1 && this.o.isKeyPressed(32832)) {
         int[] var2 = (int[])this.q.S.elementAt(this.h);
         this.c++;
         if (this.c > this.q.countItem(var2[0], (byte)0)) {
            this.c = 1;
         }

         this.a(this.c, this.c * GameDatabase.gameDatabase[4][var2[0]][3] / 2, GameDatabase.gameDatabase[4][var2[0]][4], 4);
      } else {
         if (this.o.isKeyPressed(196640) && this.q.S.size() > 0) {
            int[] var1 = (int[])this.q.S.elementAt(this.h);
            if (this.f == 0) {
               this.f = 1;
               this.p.openUI("/data/ui/msgyn.ui", 257, this);
               this.c = 1;
               this.r = 0;
               this.a(this.c, this.c * GameDatabase.gameDatabase[4][var1[0]][3] / 2, GameDatabase.gameDatabase[4][var1[0]][4], 4);
               return;
            }

            if (this.r != 0) {
               this.p.closeUI("/data/ui/msgyn.ui");
               this.f = 0;
               return;
            }

            this.q.d(var1[0], this.c, (byte)0);
            this.q.addGold(this.c * GameDatabase.gameDatabase[4][var1[0]][3] / 2);
         } else {
            if (!this.o.isKeyPressed(262144)) {
               return;
            }

            if (this.f == 0) {
               this.o.setScreenMode((byte)1);
               this.p.closeUI("/data/ui/shopbuy.ui");
               this.b = 1;
               ((MenuWidget)this.p.activeView.getComponent(0)).a.selectedIndex = this.b;
               return;
            }
         }

         this.f = 0;
         this.p.closeUI("/data/ui/msgyn.ui");
         this.q.x();
         this.aX();
      }
   }

   public final void N() {
      this.p.openUI("/data/ui/record.ui", 257, this);
      this.p.closeUI("/data/ui/gamemenu.ui");
      this.p.activeView.getComponent(14).getStyle().text = "" + (this.q.partyPetCount + this.q.O.size());
      this.p.activeView.getComponent(17).getStyle().text = "" + this.q.F;
      this.p.activeView.getComponent(20).getStyle().text = "" + this.q.H;
      this.p.activeView.getComponent(26).getStyle().text = "" + this.q.G;
      int var1 = 0;

      for (int var2 = 0; var2 < this.q.B.length; var2++) {
         if (this.q.c((byte)var2, (byte)0) == 2) {
            var1++;
         }
      }

      this.p.activeView.getComponent(29).getStyle().text = "" + var1;
      long var4 = GameStateController.getInstance().d + GameStateController.getInstance().e - GameStateController.getInstance().f;
      UIStyle var10000 = this.p.activeView.getComponent(31).getStyle();
      WorldManager.a();
      var10000.text = WorldManager.a(var4)[1];
      ((MenuWidget)this.p.activeView.getComponent(0)).b.selectedIndex = this.c;
      this.b = 0;
      this.f = 0;
      this.g = true;
   }

   public final void O() {
      if (this.o.isKeyPressed(16400)) {
         this.p.activeView.navigateSelection(2);
         this.g = true;
      } else if (this.o.isKeyPressed(32832)) {
         this.p.activeView.navigateSelection(3);
         this.g = true;
      } else if (this.o.isKeyPressed(196640)) {
         if (this.f == 0) {
            switch (this.c) {
               case 0:
                  if (Player.getInstance().k(5)) {
                     this.o.setScreenMode((byte)11);
                  } else {
                     this.E();
                     this.a("Không đạt được sủng vật sách tranh đạo cụ", "Nhấn nút 5 để tiếp tục");
                     this.f = 1;
                  }
                  break;
               case 1:
                  this.o.setScreenMode((byte)12);
            }
         } else {
            this.f = 0;
            this.F();
         }
      } else if (this.o.isKeyPressed(262144) && this.f == 0) {
         if (BaseScreen.isVipUnlocked) {
            this.b = 3;
         } else {
            this.b = 2;
         }

         this.o.setScreenMode((byte)6);
         this.p.closeUI("/data/ui/record.ui");
      }

      this.g = true;
   }

   public final void P() {
      this.p.openUI("/data/ui/petmap.ui", 257, this);
      this.p.closeUI("/data/ui/record.ui");
      this.b = 0;
      this.c = 0;
      this.f = 0;
      ((MenuWidget)this.p.activeView.getComponent(0)).a.a(1);
      this.aZ();
      this.g = true;
   }

   private void aY() {
      ((MenuWidget)this.p.activeView.getComponent(0)).a.firstVisibleIndex = 0;
      ((MenuWidget)this.p.activeView.getComponent(0)).a.selectedIndex = 0;
   }

   private void aZ() {
      ((MenuWidget)this.p.activeView.getComponent(0)).a.itemCount = this.q.elementSpeciesCounts[this.b];
      this.w = ((MenuWidget)this.p.activeView.getComponent(0)).a.firstVisibleIndex;
      this.h = ((MenuWidget)this.p.activeView.getComponent(0)).a.selectedIndex;
      short var1 = GameDatabase.gameDatabase[0][this.q.elementStartIds[this.b] + this.h][17];
      if (this.q.getCollectionFlag((byte)this.b, this.h + this.q.elementStartIds[this.b]) > 0) {
         this.p.activeView.getComponent(21).setVisible(true);
         if (this.p.activeView.getComponent(21).getStyle().m != null) {
            this.p.activeView.getComponent(21).getStyle().m.d();
         } else {
            this.p.activeView.getComponent(21).getStyle().m = new SpriteWidget();
            this.p.activeView.getComponent(21).getStyle().m.a(0);
            this.p.activeView.getComponent(21).getStyle().m.a = 3;
         }

         this.p.activeView.getComponent(21).getStyle().m.a(var1, false, (byte)-1);
         this.p.activeView.getComponent(21).getStyle().m.b();
      } else {
         this.p.activeView.getComponent(21).setVisible(false);
      }

      int var3 = 0;

      for (int var2 = 0; var2 < this.q.elementSpeciesCounts[this.b]; var2++) {
         if (this.q.getCollectionFlag((byte)this.b, this.q.elementStartIds[this.b] + var2) == 2) {
            var3++;
         }
      }

      for (int var4 = 0; var4 < 5; var4++) {
         if (this.p.activeView.getComponent(var4 + 44).getStyle().m == null) {
            this.p.activeView.getComponent(var4 + 44).getStyle().m = new SpriteWidget();
            this.p.activeView.getComponent(var4 + 44).getStyle().m.a(102);
            this.p.activeView.getComponent(var4 + 44).getStyle().m.a = 2;
            this.p.activeView.getComponent(var4 + 44).getStyle().m.a(257, false, (byte)-1);
         }

         if (this.q.getCollectionFlag((byte)this.b, var4 + this.w + this.q.elementStartIds[this.b]) == 2) {
            this.p.activeView.getComponent(var4 + 44).getStyle().m.a(101);
         } else {
            this.p.activeView.getComponent(var4 + 44).getStyle().m.a(102);
         }

         this.p.activeView.getComponent(24 + (var4 << 2) + 3).getStyle().text = BaseScreen.getString(
            GameDatabase.gameDatabase[0][this.q.elementStartIds[this.b] + var4 + this.w][0]
         );
      }

      this.p.activeView.getComponent(20).getStyle().text = BaseScreen.getString(365 + this.b) + var3 + "/" + this.q.elementSpeciesCounts[this.b];
      this.p.activeView.getComponent(23).setY(99 + (this.h << 6) / this.q.elementSpeciesCounts[this.b], this.p.activeView.getRoot());
   }

   public final void Q() {
      if (this.o.isKeyPressed(4100)) {
         this.p.activeView.navigateSelection(0);
         this.aZ();
      } else if (this.o.isKeyPressed(8448)) {
         this.p.activeView.navigateSelection(1);
         this.aZ();
      } else if (this.o.isKeyPressed(16400)) {
         this.p.activeView.navigateSelection(2);
         this.aY();
         this.aZ();
      } else if (this.o.isKeyPressed(32832)) {
         this.p.activeView.navigateSelection(3);
         this.aY();
         this.aZ();
      } else if (!this.o.isKeyPressed(196640) && this.o.isKeyPressed(786432)) {
         if (this.o.previousScreenMode == 8) {
            this.o.setScreenMode((byte)8);
         } else {
            this.c = 0;
            this.o.setScreenMode((byte)9);
         }

         this.p.closeUI("/data/ui/petmap.ui");
      }

      this.g = true;
   }

   public final void R() {
      this.p.openUI("/data/ui/task.ui", 257, this);
      this.p.closeUI("/data/ui/gamemenu.ui");
      ((MenuWidget)this.p.activeView.getComponent(0)).b.selectedIndex = this.b;
      this.c = 0;
      this.r = 0;
      this.ba();
      this.bb();
   }

   private void ba() {
      switch (this.b) {
         case 0:
            if (OverworldScreen.t >= OverworldScreen.r.length / 2 - 1) {
               int var10001 = OverworldScreen.r.length;
               ((MenuWidget)this.p.activeView.getComponent(0)).a.itemCount = var10001 / 2;
               ((MenuWidget)this.p.activeView.getComponent(0)).a.selectedIndex = OverworldScreen.r.length / 2 - 1;
            } else {
               ((MenuWidget)this.p.activeView.getComponent(0)).a.itemCount = OverworldScreen.t + 1;
               ((MenuWidget)this.p.activeView.getComponent(0)).a.selectedIndex = OverworldScreen.t;
            }

            this.p.activeView.getComponent(36).getStyle().text = "";
            this.h = OverworldScreen.t;
            this.w = OverworldScreen.t - 4;
            if (this.h <= 0) {
               this.h = 0;
            }

            if (this.w <= 0) {
               this.w = 0;
            }

            ((MenuWidget)this.p.activeView.getComponent(0)).a.firstVisibleIndex = this.w;
            this.p.activeView.getComponent(37).getStyle().text = "Đầu mối chính hoàn thành độ: ";
            if (OverworldScreen.t >= OverworldScreen.r.length / 2) {
               this.p.activeView.getComponent(38).getStyle().text = OverworldScreen.r[OverworldScreen.r.length - 1];
            } else {
               this.p.activeView.getComponent(38).getStyle().text = OverworldScreen.r[OverworldScreen.r.length / 2 + OverworldScreen.t];
            }

            int var3 = OverworldScreen.t * 1000 / (OverworldScreen.r.length / 2);
            if (!BaseScreen.isVipUnlocked) {
               int var5;
               if ((var5 = var3 % 10) == 0) {
                  var5 = 1;
               }

               this.p.activeView.getComponent(38).getStyle().text = var3 / 50 + "." + var5 + "%";
            } else {
               this.p.activeView.getComponent(38).getStyle().text = var3 / 10 + "." + var3 % 10 + "%";
            }

            if (OverworldScreen.t > 4) {
               ((MenuWidget)this.p.activeView.getComponent(0)).a.a(1);
            } else {
               ((MenuWidget)this.p.activeView.getComponent(0)).a.a(0);
            }

            this.p.activeView.getComponent(8).getStyle().g = 11290624;
            break;
         case 1:
            ((MenuWidget)this.p.activeView.getComponent(0)).a.itemCount = OverworldScreen.u;
            ((MenuWidget)this.p.activeView.getComponent(0)).a.selectedIndex = 0;
            ((MenuWidget)this.p.activeView.getComponent(0)).a.firstVisibleIndex = 0;
            this.p.activeView.getComponent(36).getStyle().text = "";
            this.p.activeView.getComponent(37).getStyle().text = "Chi nhánh hoàn thành độ: ";
            int var1 = 0;

            for (int var2 = 0; var2 < OverworldScreen.s.length; var2++) {
               if (OverworldScreen.s[var2][1] == 3) {
                  var1++;
               }
            }

            System.out.println(" Nhiệm vụ phụ " + OverworldScreen.q.length);
            int var4 = var1 * 1000 / (OverworldScreen.q.length / 2);
            this.p.activeView.getComponent(38).getStyle().text = var4 / 10 + "." + var4 % 10 + "%";
            if (OverworldScreen.u > 5) {
               ((MenuWidget)this.p.activeView.getComponent(0)).a.a(1);
            } else {
               ((MenuWidget)this.p.activeView.getComponent(0)).a.a(0);
            }

            this.p.activeView.getComponent(9).getStyle().g = 11290624;
      }

      this.bb();
   }

   private void bb() {
      this.w = ((MenuWidget)this.p.activeView.getComponent(0)).a.firstVisibleIndex;
      this.h = ((MenuWidget)this.p.activeView.getComponent(0)).a.selectedIndex;

      for (int var1 = 0; var1 < 5; var1++) {
         if (this.b == 0) {
            if (OverworldScreen.t > 0) {
               if (this.w + var1 < OverworldScreen.t) {
                  this.p.activeView.getComponent(10 + var1 * 5 + 2).getStyle().text = "" + (var1 + this.w + 1);
                  this.p.activeView.getComponent(10 + var1 * 5 + 3).getStyle().text = OverworldScreen.r[this.w + var1];
                  this.p.activeView.getComponent(10 + var1 * 5 + 4).getStyle().text = "Hoàn thành";
               } else if (this.w + var1 == OverworldScreen.t && this.w + var1 <= OverworldScreen.r.length / 2 - 1) {
                  this.p.activeView.getComponent(10 + var1 * 5 + 2).getStyle().text = "" + (var1 + this.w + 1);
                  this.p.activeView.getComponent(10 + var1 * 5 + 3).getStyle().text = OverworldScreen.r[this.w + var1];
                  this.p.activeView.getComponent(10 + var1 * 5 + 4).getStyle().text = "";
               } else {
                  this.p.activeView.getComponent(10 + var1 * 5 + 2).getStyle().text = "";
                  this.p.activeView.getComponent(10 + var1 * 5 + 3).getStyle().text = "";
                  this.p.activeView.getComponent(10 + var1 * 5 + 4).getStyle().text = "";
                  this.p.activeView.getComponent(36).getStyle().text = "";
               }
            } else {
               this.p.activeView.getComponent(12).getStyle().text = "1";
               this.p.activeView.getComponent(13).getStyle().text = OverworldScreen.r[0];
               this.p.activeView.getComponent(14).getStyle().text = "";
            }
         } else if (this.b == 1) {
            if (this.w + var1 < OverworldScreen.u) {
               this.p.activeView.getComponent(10 + var1 * 5 + 2).getStyle().text = "" + (var1 + this.w + 1);
               this.p.activeView.getComponent(10 + var1 * 5 + 3).getStyle().text = OverworldScreen.q[OverworldScreen.s[this.w + var1][0]];
               if (OverworldScreen.s[this.w + var1][1] == 3) {
                  this.p.activeView.getComponent(10 + var1 * 5 + 4).getStyle().text = "Hoàn thành";
               } else {
                  this.p.activeView.getComponent(10 + var1 * 5 + 4).getStyle().text = "";
               }
            } else {
               this.p.activeView.getComponent(10 + var1 * 5 + 2).getStyle().text = "";
               this.p.activeView.getComponent(10 + var1 * 5 + 3).getStyle().text = "";
               this.p.activeView.getComponent(10 + var1 * 5 + 4).getStyle().text = "";
            }
         }
      }

      switch (this.b) {
         case 0:
            this.p.activeView.getComponent(36).getStyle().text = OverworldScreen.r[OverworldScreen.r.length / 2 + this.h];
            break;
         case 1:
            if (OverworldScreen.u > 0) {
               this.p.activeView.getComponent(36).getStyle().text = OverworldScreen.q[OverworldScreen.q.length / 2 + OverworldScreen.s[this.h][0]];
            }
      }

      if (((MenuWidget)this.p.activeView.getComponent(0)).a.itemCount > 0) {
         this.p
            .activeView
            .getComponent(40)
            .setY(104 + (this.h << 6) / ((MenuWidget)this.p.activeView.getComponent(0)).a.itemCount, this.p.activeView.getRoot());
      }
   }

   public final void S() {
      if (this.o.isKeyPressed(4100)) {
         this.p.activeView.navigateSelection(0);
         this.bb();
      } else if (this.o.isKeyPressed(8448)) {
         this.p.activeView.navigateSelection(1);
         this.bb();
      } else if (this.o.isKeyPressed(16400)) {
         this.p.activeView.navigateSelection(2);
         this.ba();
      } else if (this.o.isKeyPressed(32832)) {
         this.p.activeView.navigateSelection(3);
         this.ba();
      } else if (this.o.isKeyPressed(983072)) {
         this.p.closeUI("/data/ui/task.ui");
         if (BaseScreen.isVipUnlocked) {
            this.b = 4;
         } else {
            this.b = 3;
         }

         if (this.o.previousScreenMode == 0) {
            this.b = 0;
            this.o.setScreenMode((byte)0);
         } else {
            this.o.setScreenMode((byte)6);
         }
      } else {
         if (this.o.isKeyPressed(10)) {
            this.p.closeUI("/data/ui/task.ui");
            this.o.setScreenMode((byte)0);
         }
      }
   }

   public final void T() {
      this.p.openUI("/data/ui/badge.ui", 257, this);
      this.p.closeUI("/data/ui/record.ui");
      this.b = 0;
      this.f = 0;

      for (int var1 = 0; var1 < 8; var1++) {
         if (this.q.B[var1][0] != 0) {
            this.p.activeView.getComponent(var1 + 25).getStyle().m.a(var1 + 46);
         }
      }

      this.bc();
   }

   private void bc() {
      this.p.activeView.getComponent(13).getStyle().text = BaseScreen.getString(GameDatabase.gameDatabase[2][this.b][0]);
      this.p.activeView.getComponent(14).getStyle().text = BaseScreen.getString(GameDatabase.gameDatabase[2][this.b][2 + this.q.c((byte)this.b, (byte)1)]);
      if (this.q.c((byte)this.b, (byte)0) == 0) {
         this.p.activeView.getComponent(16).getStyle().text = "Chưa đạt";
      } else {
         this.p.activeView.getComponent(16).getStyle().text = "Đã đạt được";
         this.q.c((byte)this.b, (byte)1);
         this.p.activeView.getComponent(33).getStyle().text = "";
      }
   }

   public final void U() {
      if (this.o.isKeyPressed(4100)) {
         this.p.activeView.navigateSelection(0);
         this.bc();
      } else if (this.o.isKeyPressed(8448)) {
         this.p.activeView.navigateSelection(1);
         this.bc();
      } else if (this.o.isKeyPressed(16400)) {
         this.p.activeView.navigateSelection(2);
         this.bc();
      } else if (this.o.isKeyPressed(32832)) {
         this.p.activeView.navigateSelection(3);
         this.bc();
      } else {
         if (this.o.isKeyPressed(786432)) {
            if (this.o.previousScreenMode == 8) {
               this.o.setScreenMode((byte)8);
            } else {
               this.c = 1;
               this.o.setScreenMode((byte)9);
            }

            this.p.closeUI("/data/ui/badge.ui");
         }
      }
   }

   public final void a(int var1) {
      this.p.openUI("/data/ui/smsTip.ui", 257, this);
      if (this.p.activeView.getComponent(6).getStyle().m == null) {
         this.p.activeView.getComponent(6).getStyle().m = new SpriteWidget();
         this.p.activeView.getComponent(6).getStyle().m.a = 2;
         this.p.activeView.getComponent(6).getStyle().m.a(-1);
         this.p.activeView.getComponent(6).getStyle().m.a(257, false, (byte)-1);
         this.p.activeView.getComponent(6).getStyle().m.a(var1 + 46);
      }

      this.p.activeView.getComponent(7).getStyle().text = BaseScreen.getString(var1 + 187) + ":" + BaseScreen.getString(var1 + 195);
      this.p.activeView.getComponent(8).getStyle().text = BaseScreen.getString(377);
   }

   public final void V() {
      this.p.closeUI("/data/ui/smsTip.ui");
   }

   public final void W() {
      this.b = 0;
      this.e(this.c);
   }

   private void e(int var1) {
      int var3 = var1;
      Pet[] var2 = this.q.petParty;
      ScriptEngine var5 = this;
      this.p.openUI("/data/ui/petstate.ui", 257, var5);
      var5.f(var3);
      var5.f = 0;
      if (var5.o instanceof WorldManager) {
         for (int var4 = 0; var4 < 6; var4++) {
            if (var2[var4] != null) {
               var5.p.activeView.getComponent(16 + var4 * 6).getStyle().text = "#P" + var2[var4].L();
               var5.p.activeView.getComponent(17 + var4 * 6).getStyle().text = "#P" + var2[var4].O();
            } else {
               var5.p.activeView.getComponent(16 + var4 * 6).getStyle().text = "#P0";
               var5.p.activeView.getComponent(17 + var4 * 6).getStyle().text = "#P0";
            }
         }

         if (var5.o.previousScreenMode == 16) {
            var5.p.activeView.getComponent(64).getStyle().text = "Gởi lại";
         }

         var5.p.activeView.getComponent(75).setVisible(false);
         var5.p.activeView.getComponent(76).setVisible(false);
      } else if (var5.o instanceof BattleScreen) {
         for (int var6 = 0; var6 < 6; var6++) {
            if (var6 < ((BattleScreen)var5.o).f.length && var2[((BattleScreen)var5.o).f[var6]] != null) {
               var5.p.activeView.getComponent(16 + var6 * 6).getStyle().text = "#P" + var2[((BattleScreen)var5.o).f[var6]].L();
               var5.p.activeView.getComponent(17 + var6 * 6).getStyle().text = "#P" + var2[((BattleScreen)var5.o).f[var6]].O();
            } else {
               var5.p.activeView.getComponent(16 + var6 * 6).getStyle().text = "#P0";
               var5.p.activeView.getComponent(17 + var6 * 6).getStyle().text = "#P0";
            }
         }

         var5.p.activeView.getComponent(63).setVisible(false);
         var5.p.activeView.getComponent(64).setVisible(false);
         if (var5.o.previousScreenMode == 4) {
            var5.p.activeView.getComponent(75).getStyle().text = "Sử dụng";
         } else if (var5.o.screenMode == 5) {
            var5.p.activeView.getComponent(75).getStyle().text = "Xuất chiến";
         }
      }

      ((MenuWidget)var5.p.activeView.getComponent(0)).a.itemCount = var5.q.partyPetCount;
      ((MenuWidget)var5.p.activeView.getComponent(0)).a.visibleItemCount = var5.q.partyPetCount;
      ((MenuWidget)var5.p.activeView.getComponent(0)).a.selectedIndex = var3;
      var5.g = true;
   }

   private void a(Pet[] var1, int var2) {
      if (var1[var2] != null) {
         if (this.p.activeView.getComponent(48).getStyle().m != null) {
            this.p.activeView.getComponent(48).getStyle().m.d();
         } else {
            this.p.activeView.getComponent(48).getStyle().m = new SpriteWidget();
            this.p.activeView.getComponent(48).getStyle().m.a(0);
            this.p.activeView.getComponent(48).getStyle().m.a = 3;
         }

         this.p.activeView.getComponent(48).getStyle().m.a(var1[var2].spriteId, false, (byte)-1);
         this.p.activeView.getComponent(51).getStyle().text = BaseScreen.getString(var1[var2].getSpeciesValue((byte)0));
         this.p.activeView.getComponent(52).getStyle().text = BaseScreen.getString(365 + var1[var2].getSpeciesValue((byte)1));
         if (var1[var2].getSpeciesValue((byte)19) == -1) {
            this.p.activeView.getComponent(62).getStyle().text = "";
         } else if (GameDatabase.gameDatabase[0][var1[var2].getSpeciesValue((byte)19)][2] == 1
            || GameDatabase.gameDatabase[0][var1[var2].getSpeciesValue((byte)19)][2] == 2) {
            this.p.activeView.getComponent(62).getStyle().text = "Có thể tiến hóa";
         } else if (GameDatabase.gameDatabase[0][var1[var2].getSpeciesValue((byte)19)][2] == 3) {
            this.p.activeView.getComponent(62).getStyle().text = "Có thể dị hoá";
         }

         this.p.activeView.getComponent(61).getStyle().text = var1[var2].T();
         if (this.o instanceof BattleScreen) {
            this.p.activeView.getComponent(64).getStyle().text = "Xuất chiến";
         } else if (this.o instanceof WorldManager) {
            this.p.activeView.getComponent(64).getStyle().text = "Xác nhận";
         }

         if (this.p.activeView.getComponent(59).getStyle().m == null) {
            this.p.activeView.getComponent(59).getStyle().m = new SpriteWidget();
            this.p.activeView.getComponent(59).getStyle().m.a(0);
            this.p.activeView.getComponent(59).getStyle().m.a = 2;
            this.p.activeView.getComponent(59).getStyle().m.a(258, false, (byte)-1);
         }

         if (var1[var2].baseStats[5] != -1) {
            short[] var10001 = GameDatabase.gameDatabase[3][var1[var2].baseStats[5]];
            this.p.activeView.getComponent(59).getStyle().m.a(var10001[1]);
            this.p.activeView.getComponent(60).getStyle().text = BaseScreen.getString(GameDatabase.gameDatabase[3][var1[var2].baseStats[5]][0]);
         } else {
            this.p.activeView.getComponent(59).getStyle().m.a(0);
            this.p.activeView.getComponent(60).getStyle().text = "";
         }

         this.p.activeView.getComponent(65).getStyle().text = "" + var1[var2].getLevel();
         this.p.activeView.getComponent(66).getStyle().text = "" + var1[var2].e((byte)2);
         this.p.activeView.getComponent(67).getStyle().text = "" + var1[var2].e((byte)3);
         this.p.activeView.getComponent(68).getStyle().text = "" + var1[var2].e((byte)4);
         int var3 = var1[var2].currentStats[0];
         int var4 = GameDatabase.getValue((byte)0, (short)var1[var2].getPetId(), (byte)4) - 1;

         for (int var5 = 0; var5 < 5; var5++) {
            this.p.activeView.getComponent(74 - var5).setVisible(true);
            this.p.activeView.getComponent(74 - var5).getStyle().m.a(257, false, (byte)-1);
            this.p.activeView.getComponent(74 - var5).getStyle().m.a = 3;
            if (var5 > var4) {
               this.p.activeView.getComponent(74 - var5).setVisible(false);
            } else if (var3 > 0) {
               this.p.activeView.getComponent(74 - var5).getStyle().m.a((byte)14, (byte)-1);
               var3--;
            } else {
               this.p.activeView.getComponent(74 - var5).getStyle().m.a((byte)16, (byte)-1);
            }
         }
      }
   }

   private void f(int var1) {
      if (this.o instanceof WorldManager) {
         this.a(this.q.petParty, var1);
      } else {
         if (this.o instanceof BattleScreen) {
            this.a(this.q.petParty, ((BattleScreen)this.o).f[var1]);
         }
      }
   }

   public final void X() {
      if (this.f == 0) {
         if (!BaseScreen.b(this.b, 0) && !this.j() && this.o.isKeyPressed(4100)) {
            this.p.activeView.navigateSelection(0);
         } else if (!BaseScreen.b(this.b, 0) && !this.j() && this.o.isKeyPressed(8448)) {
            this.p.activeView.navigateSelection(1);
         } else if (BaseScreen.I() && !this.j() && this.o.isKeyPressed(196640)) {
            if (BaseScreen.H() && !BaseScreen.b(this.b, 0)) {
               return;
            }

            if (this.o instanceof BattleScreen) {
               int var9;
               if ((var9 = ((BattleScreen)this.o).a(this.b)) == 0) {
                  this.f = 2;
                  this.p.openUI("/data/ui/msgwarm.ui", 257, this);
                  this.a("Sủng vật này không thể tham chiến", "Nhấn nút 5 để tiếp tục");
                  this.p.closeUI("/data/ui/petsetting.ui");
               } else if (var9 == 1) {
                  this.f = 2;
                  this.p.openUI("/data/ui/msgwarm.ui", 257, this);
                  this.a("Sủng vật này đã đặt ở vị trí chiến đấu", "Nhấn nút 5 để tiếp tục");
                  this.p.closeUI("/data/ui/petsetting.ui");
               } else if (var9 == -1) {
                  ((BattleScreen)this.o).a(((BattleScreen)this.o).g, 0);
                  this.a = 0;
                  this.o.setScreenMode((byte)15);
                  this.p.closeUI("/data/ui/petsetting.ui");
                  this.p.closeUI("/data/ui/petstate.ui");
               }
            } else if (this.o instanceof WorldManager) {
               if (this.o.previousScreenMode == 16) {
                  if (this.q.z()) {
                     if (this.q.o(this.b)) {
                        this.q.l(this.q.petParty[this.b].baseStats[5]);
                        this.q.petParty[this.b].baseStats[5] = -1;
                        this.q.b(this.q.petParty[this.b].toSaveData());
                        this.q.m(this.b);
                        if (this.b >= this.q.partyPetCount) {
                           this.b--;
                        }

                        this.e(this.b);
                     } else {
                        this.f = 1;
                        this.p.openUI("/data/ui/msgwarm.ui", 257, this);
                        this.a("Ba lô phải lưu ít nhất 1 sủng vật", "Nhấn nút 5 để tiếp tục");
                     }
                  } else {
                     this.f = 1;
                     this.p.openUI("/data/ui/msgwarm.ui", 257, this);
                     this.a("Ngân hàng đã đầy, không thể gởi lại", "Nhấn nút 5 để tiếp tục");
                  }
               } else if (this.o.previousScreenMode == 6 || this.o.previousScreenMode == 0) {
                  this.c = 0;
                  this.o.m();
                  this.f = 1;
                  this.p.openUI("/data/ui/petsetting.ui", 257, this);
                  ((MenuWidget)this.p.activeView.getComponent(0)).a.selectedIndex = this.c;
                  if (this.q.petParty[this.b].getEvolutionKind() == 2) {
                     this.p.activeView.getComponent(9).getStyle().text = "Dị hoá";
                     ((MenuWidget)this.p.activeView.getComponent(0)).a.itemCount = 6;
                     ((MenuWidget)this.p.activeView.getComponent(0)).a.visibleItemCount = 6;
                  } else if (this.q.petParty[this.b].getEvolutionKind() == 1) {
                     this.p.activeView.getComponent(9).getStyle().text = "Tiến hóa";
                     ((MenuWidget)this.p.activeView.getComponent(0)).a.itemCount = 6;
                     ((MenuWidget)this.p.activeView.getComponent(0)).a.visibleItemCount = 6;
                  } else {
                     this.p.activeView.getComponent(9).getStyle().text = "";
                     ((MenuWidget)this.p.activeView.getComponent(0)).a.itemCount = 5;
                     ((MenuWidget)this.p.activeView.getComponent(0)).a.visibleItemCount = 5;
                  }
               } else if (this.o.previousScreenMode == 27) {
                  if ((this.d != 1 || this.q.petParty[this.b].getEvolutionKind() != 1) && (this.d != 2 || this.q.petParty[this.b].getEvolutionKind() != 2)) {
                     this.f = 4;
                     this.E();
                     if (this.d == 1) {
                        this.a("Sủng vật này không thể tiến hóa", "Nhấn nút 5 để tiếp tục");
                     } else if (this.d == 2) {
                        this.a("Sủng vật này không thể dị hoá", "Nhấn nút 5 để tiếp tục");
                     } else {
                        this.a("Không thể vào hóa cùng dị hoá", "Nhấn nút 5 để tiếp tục");
                     }
                  } else {
                     this.bg();
                  }
               }
            }
         } else if (OverworldScreen.J() && !this.j() && this.o.isKeyPressed(262144)) {
            if (this.o instanceof WorldManager) {
               if (this.o.previousScreenMode == 16) {
                  this.o.setScreenMode((byte)16);
               } else if (this.o.previousScreenMode == 6) {
                  if (BaseScreen.isVipUnlocked) {
                     this.b = 1;
                  } else {
                     this.b = 0;
                  }

                  this.o.setScreenMode((byte)6);
               } else if (this.o.previousScreenMode == 27) {
                  this.o.setScreenMode((byte)27);
               } else if (this.o.previousScreenMode == 0) {
                  this.o.setScreenMode((byte)23);
               }

               this.p.closeUI("/data/ui/petstate.ui");
            } else if (this.o instanceof BattleScreen) {
               if (((BattleScreen)this.o).previousScreenMode == 7 || ((BattleScreen)this.o).previousScreenMode == 13) {
                  return;
               }

               this.p.closeUI("/data/ui/petstate.ui");
               BattleScreen.a().k = false;
               this.a = 0;
               this.o.setScreenMode((byte)20);
            }
         }
      } else if (this.f == 1) {
         if (!BaseScreen.b(this.c, 0) && !this.j() && this.o.isKeyPressed(4100)) {
            this.p.activeView.navigateSelection(0);
         } else if (!BaseScreen.b(this.c, 0) && !this.j() && this.o.isKeyPressed(8448)) {
            this.p.activeView.navigateSelection(1);
         } else if (BaseScreen.I() && !this.j() && this.o.isKeyPressed(196640)) {
            if (BaseScreen.H() && !BaseScreen.b(this.c, 0)) {
               return;
            }

            if (this.o.previousScreenMode == 16) {
               this.o.setScreenMode((byte)16);
               this.p.closeUI("/data/ui/msgwarm.ui");
               this.p.closeUI("/data/ui/petstate.ui");
               this.f = 0;
            } else if (this.o.previousScreenMode == 6 || this.o.previousScreenMode == 0) {
               switch (this.c) {
                  case 0:
                     ScriptEngine var5 = this;
                     this.f = 2;
                     var5.r = 0;
                     var5.p.openUI("/data/ui/choice.ui", 257, var5);
                     var5.p.closeUI("/data/ui/petsetting.ui");
                     var5.p.closeUI("/data/ui/petstate.ui");
                     var5.p.activeView.getComponent(8).getStyle().text = "Đạo cụ";
                     var5.p.activeView.getComponent(9).getStyle().text = "Số lượng";
                     if (var5.o instanceof WorldManager) {
                        var5.p.activeView.getComponent(5).setVisible(false);
                        var5.p.activeView.getComponent(6).setVisible(false);
                        var5.p.activeView.getComponent(59).setVisible(true);
                        var5.p.activeView.getComponent(60).setVisible(true);
                        var5.p.activeView.getComponent(59).getStyle().text = "Sử dụng";
                     } else {
                        var5.p.activeView.getComponent(5).setVisible(true);
                        var5.p.activeView.getComponent(6).setVisible(true);
                        var5.p.activeView.getComponent(59).setVisible(false);
                        var5.p.activeView.getComponent(60).setVisible(false);
                        var5.p.activeView.getComponent(5).getStyle().text = "Sử dụng";
                     }

                     var5.be();
                     var5.g = true;
                     break;
                  case 1:
                     if (!this.q.petParty[this.b].isAlive()) {
                        this.f = 2;
                        this.p.openUI("/data/ui/msgwarm.ui", 257, this);
                        this.a("Sủng vật này không thể tham chiến", "Nhấn nút 5 để tiếp tục");
                        this.p.closeUI("/data/ui/petsetting.ui");
                        this.b = 0;
                     } else if (this.b == 0) {
                        this.f = 2;
                        this.b = 0;
                        this.p.openUI("/data/ui/msgwarm.ui", 257, this);
                        this.a("Sủng vật này đã xuất chiến", "Nhấn nút 5 để tiếp tục");
                        this.p.closeUI("/data/ui/petsetting.ui");
                     } else {
                        this.q.p(this.b);
                        this.f = 0;
                        this.b = 0;
                        this.e(this.b);
                        this.p.closeUI("/data/ui/petsetting.ui");
                        ((MenuWidget)this.p.activeView.getComponent(0)).a.selectedIndex = 0;
                        ((MenuWidget)this.p.activeView.getComponent(0)).a.firstVisibleIndex = 0;
                     }
                     break;
                  case 2:
                     this.o.m();
                     ScriptEngine var4 = this;
                     this.f = 2;
                     var4.r = 0;
                     var4.p.openUI("/data/ui/choice.ui", 257, var4);
                     var4.p.closeUI("/data/ui/petsetting.ui");
                     var4.p.closeUI("/data/ui/petstate.ui");
                     var4.p.activeView.getComponent(8).getStyle().text = "Vật phẩm trang sức";
                     var4.p.activeView.getComponent(9).getStyle().text = "Trạng thái";
                     if (var4.o instanceof WorldManager) {
                        var4.p.activeView.getComponent(5).setVisible(false);
                        var4.p.activeView.getComponent(6).setVisible(false);
                        var4.p.activeView.getComponent(59).setVisible(true);
                        var4.p.activeView.getComponent(60).setVisible(true);
                        var4.p.activeView.getComponent(59).getStyle().text = "Mang theo";
                     } else {
                        var4.p.activeView.getComponent(5).setVisible(true);
                        var4.p.activeView.getComponent(6).setVisible(true);
                        var4.p.activeView.getComponent(59).setVisible(false);
                        var4.p.activeView.getComponent(60).setVisible(false);
                        var4.p.activeView.getComponent(5).getStyle().text = "Mang theo";
                     }

                     var4.bd();
                     var4.g = true;
                     break;
                  case 3:
                     if (GameDatabase.getValue((byte)0, (short)this.q.petParty[this.b].getPetId(), (byte)22) == 2) {
                        this.f = 3;
                        this.E();
                        this.p.closeUI("/data/ui/petsetting.ui");
                        this.a("Thần thú không thể phóng sinh", "Nhấn nút 5 để tiếp tục");
                     } else {
                        this.f = 2;
                        this.p.openUI("/data/ui/msgconfirm.ui", 257, this);
                        this.p.closeUI("/data/ui/petsetting.ui");
                        this.b("Bạn muốn phóng sinh sủng vật này?", "Xác nhận");
                     }
                     break;
                  case 4:
                     ScriptEngine var1 = this;
                     this.f = 2;
                     var1.r = 0;
                     var1.p.openUI("/data/ui/skill.ui", 257, var1);
                     var1.p.closeUI("/data/ui/petsetting.ui");
                     var1.p.closeUI("/data/ui/petstate.ui");
                     var1.p.activeView.getComponent(12).getStyle().text = BaseScreen.getString(var1.q.petParty[var1.b].getSpeciesValue((byte)0));
                     var1.p.activeView.getComponent(14).getStyle().text = "" + var1.q.petParty[var1.b].getLevel();
                     if (var1.p.activeView.getComponent(16).getStyle().m != null) {
                        var1.p.activeView.getComponent(16).getStyle().m.d();
                     } else {
                        var1.p.activeView.getComponent(16).getStyle().m = new SpriteWidget();
                        var1.p.activeView.getComponent(16).getStyle().m.a(0);
                        var1.p.activeView.getComponent(16).getStyle().m.a = 3;
                     }

                     var1.p.activeView.getComponent(16).getStyle().m.a(var1.q.petParty[var1.b].spriteId, false, (byte)-1);
                     int var2 = var1.q.petParty[var1.b].E();

                     for (int var3 = 0; var3 < var2; var3++) {
                        var1.p.activeView.getComponent(var3 + 18).getStyle().text = BaseScreen.getString(
                           GameDatabase.gameDatabase[1][var1.q.petParty[var1.b].t(var3)][1]
                        );
                     }

                     var1.bf();
                     var1.g = true;
                     break;
                  case 5:
                     this.o.m();
                     this.bg();
               }
            }
         } else if (OverworldScreen.J() && !this.j() && this.o.isKeyPressed(262144)) {
            if (this.o.previousScreenMode == 16) {
               return;
            }

            this.f = 0;
            this.p.closeUI("/data/ui/petsetting.ui");
         }
      } else if (this.f >= 2) {
         if (this.o instanceof BattleScreen) {
            if (this.o.isKeyPressed(196640)) {
               this.f = 0;
               this.p.closeUI("/data/ui/msgwarm.ui");
            }
         } else if (this.o.previousScreenMode == 6 || this.o.previousScreenMode == 0) {
            label334:
            switch (this.c) {
               case 0:
                  ScriptEngine var8 = this;
                  if (this.f == 2 && var8.o.isKeyPressed(4100)) {
                     var8.p.activeView.navigateSelection(0);
                  } else if (var8.f == 2 && var8.o.isKeyPressed(8448)) {
                     var8.p.activeView.navigateSelection(1);
                  } else if (var8.o.isKeyPressed(196640)) {
                     if (var8.q.J.size() > 0) {
                        if (var8.f == 2) {
                           var8.f = 3;
                           int[] var11;
                           switch ((var11 = (int[])var8.q.J.elementAt(var8.r))[0]) {
                              case 13:
                              case 14:
                                 var8.p.openUI("/data/ui/msgwarm.ui", 257, var8);
                                 var8.a("Đạo cụ này không thể sử dụng", "Nhấn nút 5 để tiếp tục");
                                 break label334;
                              default:
                                 switch (var8.q.petParty[var8.b].x(var11[0])) {
                                    case 0:
                                       var8.p.openUI("/data/ui/msgwarm.ui", 257, var8);
                                       var8.a("Sủng vật này đã tử vong, không thể sử dụng", "Nhấn nút 5 để tiếp tục");
                                       break label334;
                                    case 1:
                                       var8.p.openUI("/data/ui/msgwarm.ui", 257, var8);
                                       var8.a("Sủng vật này không có, không thể sử dụng", "Nhấn nút 5 để tiếp tục");
                                       break label334;
                                    case 2:
                                       var8.p.openUI("/data/ui/msgwarm.ui", 257, var8);
                                       var8.a("Máu đầy, không cần sử dụng", "Nhấn nút 5 để tiếp tục");
                                       break label334;
                                    case 3:
                                       var8.p.openUI("/data/ui/msgwarm.ui", 257, var8);
                                       var8.a("Kỹ năng giá trị đã đầy, không cần sử dụng", "Nhấn nút 5 để tiếp tục");
                                       break label334;
                                    case 4:
                                       var8.p.openUI("/data/ui/msgwarm.ui", 257, var8);
                                       var8.a("Trên người đều bị lợi hiệu quả", "Nhấn nút 5 để tiếp tục");
                                       break label334;
                                    case 5:
                                       var8.p.openUI("/data/ui/msgwarm.ui", 257, var8);
                                       var8.a("Trong hưng phấn, không thể dùng", "Nhấn nút 5 để tiếp tục");
                                       break label334;
                                    case 6:
                                    default:
                                       var8.q.petParty[var8.b].w(var11[0]);
                                       var8.e(var8.b);
                                       var8.f = 4;
                                       var8.p.openUI("/data/ui/msgwarm.ui", 257, var8);
                                       var8.a("Thành công sử dụng đạo cụ", "Nhấn nút 5 để tiếp tục");
                                       var8.p.closeUI("/data/ui/choice.ui");
                                       break label334;
                                    case 7:
                                       var8.p.openUI("/data/ui/msgwarm.ui", 257, var8);
                                       var8.a("Máu và kỹ năng đều đã đầy, không cần sử dụng", "Nhấn nút 5 để tiếp tục");
                                       break label334;
                                    case 8:
                                       var8.p.openUI("/data/ui/msgwarm.ui", 257, var8);
                                       var8.a("Sủng vật đã chết, không thể sử dụng", "Nhấn nút 5 để tiếp tục");
                                 }
                           }
                        } else if (var8.f == 3) {
                           var8.f = 2;
                           var8.p.closeUI("/data/ui/msgwarm.ui");
                        } else if (var8.f == 4) {
                           var8.f = 0;
                           var8.p.closeUI("/data/ui/msgwarm.ui");
                        }
                     }
                  } else if (var8.f == 2 && var8.o.isKeyPressed(262144)) {
                     var8.e(var8.b);
                     var8.p.closeUI("/data/ui/choice.ui");
                  }
                  break;
               case 1:
                  if (this.o.isKeyPressed(196640)) {
                     this.f = 0;
                     this.p.closeUI("/data/ui/msgwarm.ui");
                  }
                  break;
               case 2:
                  ScriptEngine var7 = this;
                  if (!BaseScreen.b(this.b, 0) && !var7.j() && var7.f == 2 && var7.o.isKeyPressed(4100)) {
                     var7.p.activeView.navigateSelection(0);
                     var7.bd();
                  } else if (!BaseScreen.b(var7.b, 0) && !var7.j() && var7.f == 2 && var7.o.isKeyPressed(8448)) {
                     var7.p.activeView.navigateSelection(1);
                     var7.bd();
                  } else if (BaseScreen.I() && !var7.j() && var7.o.isKeyPressed(196640) && var7.q.L.size() > 0) {
                     if (!BaseScreen.H() || BaseScreen.b(var7.b, 0)) {
                        if (var7.f == 2) {
                           int[] var10 = (int[])var7.q.L.elementAt(var7.h);
                           if (var7.q.petParty[var7.b].baseStats[5] == var10[0]) {
                              var7.q.l(var7.q.petParty[var7.b].baseStats[5]);
                              var7.q.petParty[var7.b].baseStats[5] = -1;
                              var7.bd();
                              var7.E();
                              var7.a("Thành công dỡ xuống", "Nhấn nút 5 để tiếp tục");
                           } else {
                              var7.q.f(var10[0], var7.b);
                              var7.bd();
                              var7.E();
                              var7.a("Thành công mang theo", "Nhấn nút 5 để tiếp tục");
                           }

                           var7.f = 3;
                        } else {
                           var7.f = 2;
                           var7.o.m();
                           var7.e(var7.b);
                           var7.F();
                           var7.p.closeUI("/data/ui/choice.ui");
                        }
                     }
                  } else if (OverworldScreen.J() && !var7.j() && var7.f == 2 && var7.o.isKeyPressed(262144)) {
                     var7.e(var7.b);
                     var7.p.closeUI("/data/ui/choice.ui");
                  }
                  break;
               case 3:
                  if (this.o.isKeyPressed(131072) && this.f == 2 || this.o.isKeyPressed(131104) && this.f == 3) {
                     if (this.f == 2) {
                        if (this.q.o(this.b)) {
                           this.q.l(this.q.petParty[this.b].baseStats[5]);
                           this.q.petParty[this.b].baseStats[5] = -1;
                           this.q.m(this.b);
                           if (this.b >= this.q.partyPetCount) {
                              this.b--;
                           }

                           ((WorldManager)this.o).M.i();
                           this.e(this.b);
                           this.p.closeUI("/data/ui/msgconfirm.ui");
                           this.f = 0;
                        } else {
                           this.f = 3;
                           this.p.openUI("/data/ui/msgwarm.ui", 257, this);
                           this.a("Ba lô phải lưu ít nhất 1 sủng vật", "Nhấn nút 5 để tiếp tục");
                           this.p.closeUI("/data/ui/msgconfirm.ui");
                        }
                     } else {
                        this.f = 0;
                        this.p.closeUI("/data/ui/msgwarm.ui");
                     }
                  } else if (this.o.isKeyPressed(786432) && this.f <= 2) {
                     this.f = 0;
                     this.p.closeUI("/data/ui/msgconfirm.ui");
                  }
                  break;
               case 4:
                  ScriptEngine var6 = this;
                  if (this.o.isKeyPressed(4100)) {
                     var6.p.activeView.navigateSelection(0);
                     var6.bf();
                  } else if (var6.o.isKeyPressed(8448)) {
                     var6.p.activeView.navigateSelection(1);
                     var6.bf();
                  } else if (var6.o.isKeyPressed(16400)) {
                     var6.p.activeView.navigateSelection(2);
                     var6.bf();
                  } else if (var6.o.isKeyPressed(32832)) {
                     var6.p.activeView.navigateSelection(3);
                     var6.bf();
                  } else if (var6.o.isKeyPressed(262144)) {
                     var6.e(var6.b);
                     var6.p.closeUI("/data/ui/skill.ui");
                  }
                  break;
               case 5:
                  this.bh();
            }
         } else if (this.f <= 3) {
            this.bh();
         } else if (this.f == 4 && this.o.isKeyPressed(196640)) {
            this.f = 0;
            this.p.closeUI("/data/ui/msgwarm.ui");
         }
      }

      this.g = true;
      this.g();
   }

   private void bd() {
      if (this.q.L.size() > 5) {
         ((MenuWidget)this.p.activeView.getComponent(0)).a.a(1);
      } else {
         ((MenuWidget)this.p.activeView.getComponent(0)).a.a(0);
      }

      ((MenuWidget)this.p.activeView.getComponent(0)).a.itemCount = this.q.L.size();
      this.w = ((MenuWidget)this.p.activeView.getComponent(0)).a.firstVisibleIndex;
      this.h = ((MenuWidget)this.p.activeView.getComponent(0)).a.selectedIndex;
      if (this.h >= this.q.L.size()) {
         this.h = this.q.L.size() - 1;
         ((MenuWidget)this.p.activeView.getComponent(0)).a.selectedIndex = this.h;
      }

      if (this.w > 0 && this.h - this.w < 4) {
         this.w--;
         ((MenuWidget)this.p.activeView.getComponent(0)).a.firstVisibleIndex = this.w;
      }

      if (this.q.L.size() > 0) {
         if (this.q.petParty[this.b].baseStats[5] == ((int[])this.q.L.elementAt(this.h))[0]) {
            if (this.o instanceof WorldManager) {
               this.p.activeView.getComponent(59).getStyle().text = "Dỡ xuống";
            } else {
               this.p.activeView.getComponent(5).getStyle().text = "Dỡ xuống";
            }
         } else if (this.o instanceof WorldManager) {
            this.p.activeView.getComponent(59).getStyle().text = "Mang theo";
         } else {
            this.p.activeView.getComponent(5).getStyle().text = "Mang theo";
         }

         for (int var1 = 0; var1 < 5; var1++) {
            if (this.w + var1 < this.q.L.size()) {
               int[] var2 = (int[])this.q.L.elementAt(this.w + var1);
               if (this.p.activeView.getComponent(var1 + 54).getStyle().m == null) {
                  this.p.activeView.getComponent(var1 + 54).getStyle().m = new SpriteWidget();
                  this.p.activeView.getComponent(var1 + 54).getStyle().m.a(0);
                  this.p.activeView.getComponent(var1 + 54).getStyle().m.a = 2;
                  this.p.activeView.getComponent(var1 + 54).getStyle().m.a(258, false, (byte)-1);
               }

               this.p.activeView.getComponent(var1 + 54).getStyle().m.a(GameDatabase.gameDatabase[3][var2[0]][1]);
               this.p.activeView.getComponent(13 + var1 * 5).getStyle().text = BaseScreen.getString(GameDatabase.gameDatabase[3][var2[0]][0]);
               if (Player.getInstance().petParty[this.b].baseStats[5] == var2[0]) {
                  this.p.activeView.getComponent(14 + var1 * 5).getStyle().text = "Đã mang theo";
               } else if (var2[1] == 1) {
                  this.p.activeView.getComponent(14 + var1 * 5).getStyle().text = "Bị mang theo";
               } else {
                  this.p.activeView.getComponent(14 + var1 * 5).getStyle().text = "";
               }
            } else {
               if (this.p.activeView.getComponent(var1 + 54).getStyle().m != null) {
                  this.p.activeView.getComponent(var1 + 54).getStyle().m.d();
               }

               this.p.activeView.getComponent(13 + var1 * 5).getStyle().text = "";
               this.p.activeView.getComponent(14 + var1 * 5).getStyle().text = "";
            }
         }

         if (this.q.L.size() > 0) {
            this.p.activeView.getComponent(53).getStyle().text = BaseScreen.getString(GameDatabase.gameDatabase[3][((int[])this.q.L.elementAt(this.h))[0]][2]);
         } else {
            this.p.activeView.getComponent(53).getStyle().text = "";
         }

         if (this.q.L.size() > 0) {
            this.p.activeView.getComponent(51).setY(98 + this.h * 62 / this.q.L.size(), this.p.activeView.getRoot());
         } else {
            this.p.activeView.getComponent(51).setY(98, this.p.activeView.getRoot());
         }
      }
   }

   private void be() {
      if (this.q.J.size() > 5) {
         ((MenuWidget)this.p.activeView.getComponent(0)).a.a(1);
      } else {
         ((MenuWidget)this.p.activeView.getComponent(0)).a.a(0);
      }

      ((MenuWidget)this.p.activeView.getComponent(0)).a.itemCount = this.q.J.size();
      this.w = ((MenuWidget)this.p.activeView.getComponent(0)).a.firstVisibleIndex;
      this.h = ((MenuWidget)this.p.activeView.getComponent(0)).a.selectedIndex;
      if (this.h >= this.q.J.size()) {
         this.h = this.q.J.size() - 1;
         ((MenuWidget)this.p.activeView.getComponent(0)).a.selectedIndex = this.h;
      }

      if (this.w > 0 && this.h - this.w < 4) {
         this.w--;
         ((MenuWidget)this.p.activeView.getComponent(0)).a.firstVisibleIndex = this.w;
      }

      for (int var1 = 0; var1 < 5; var1++) {
         if (this.w + var1 < this.q.J.size()) {
            int[] var2 = (int[])this.q.J.elementAt(this.w + var1);
            if (this.p.activeView.getComponent(var1 + 54).getStyle().m == null) {
               this.p.activeView.getComponent(var1 + 54).getStyle().m = new SpriteWidget();
               this.p.activeView.getComponent(var1 + 54).getStyle().m.a(0);
               this.p.activeView.getComponent(var1 + 54).getStyle().m.a = 2;
               this.p.activeView.getComponent(var1 + 54).getStyle().m.a(258, false, (byte)-1);
            }

            this.p.activeView.getComponent(var1 + 54).getStyle().m.a(GameDatabase.gameDatabase[4][var2[0]][1]);
            this.p.activeView.getComponent(13 + var1 * 5).getStyle().text = BaseScreen.getString(GameDatabase.gameDatabase[4][var2[0]][0]);
            this.p.activeView.getComponent(14 + var1 * 5).getStyle().text = "" + var2[1];
         } else {
            if (this.p.activeView.getComponent(var1 + 54).getStyle().m != null) {
               this.p.activeView.getComponent(var1 + 54).getStyle().m.d();
            }

            this.p.activeView.getComponent(13 + var1 * 5).getStyle().text = "";
            this.p.activeView.getComponent(14 + var1 * 5).getStyle().text = "";
         }
      }

      if (this.q.J.size() > 0) {
         this.p.activeView.getComponent(53).getStyle().text = BaseScreen.getString(GameDatabase.gameDatabase[4][((int[])this.q.J.elementAt(this.h))[0]][2]);
      } else {
         this.p.activeView.getComponent(53).getStyle().text = "";
      }

      if (this.q.J.size() > 0) {
         this.p.activeView.getComponent(51).setY(98 + this.h * 72 / this.q.J.size(), this.p.activeView.getRoot());
      } else {
         this.p.activeView.getComponent(51).setY(98, this.p.activeView.getRoot());
      }
   }

   private void bf() {
      if (this.q.petParty[this.b].t(this.r) != -1) {
         String[] var1 = new String[]{"Nhất định", "Nhất định"};
         this.p.activeView.getComponent(9).getStyle().text = BaseScreen.a(GameDatabase.gameDatabase[1][this.q.petParty[this.b].t(this.r)][2], var1);
      } else {
         this.p.activeView.getComponent(9).getStyle().text = "";
      }
   }

   private void bg() {
      this.f = 2;
      this.r = 0;
      this.p.openUI("/data/ui/evolve.ui", 257, this);
      this.p.closeUI("/data/ui/petsetting.ui");
      this.p.closeUI("/data/ui/petstate.ui");
      if (this.p.activeView.getComponent(10).getStyle().m == null) {
         this.p.activeView.getComponent(10).getStyle().m = new SpriteWidget();
         this.p.activeView.getComponent(10).getStyle().m.a(0);
         this.p.activeView.getComponent(10).getStyle().m.a = 3;
      }

      this.p.activeView.getComponent(10).getStyle().m.a(this.q.petParty[this.b].spriteId, false, (byte)-1);
      short var1 = (short)(GameDatabase.getValue((byte)0, (byte)this.q.petParty[this.b].getPetId(), (byte)20) + 12);
      short var2 = GameDatabase.getValue((byte)0, (byte)this.q.petParty[this.b].getPetId(), (byte)21);
      this.p.activeView.getComponent(38).getStyle().text = BaseScreen.getString(
         GameDatabase.getValue((byte)0, (byte)this.q.petParty[this.b].getPetId(), (byte)0)
      );
      this.p.activeView.getComponent(40).getStyle().text = "" + this.q.petParty[this.b].getLevel();
      this.p.activeView.getComponent(45).getStyle().text = BaseScreen.getString(GameDatabase.getValue((byte)3, var1, (byte)0));
      this.p.activeView.getComponent(46).getStyle().text = this.q.countItem(var1, (byte)2) + "/" + var2;
      var1 = GameDatabase.getValue((byte)0, (byte)this.q.petParty[this.b].getPetId(), (byte)19);
      Pet var6;
      (var6 = new Pet()).initPet(var1, (byte)this.q.petParty[this.b].getLevel(), (short)-1, (byte)-1, (short)-1, (byte)-1);

      for (int var5 = 0; var5 < 4; var5++) {
         UIStyle var10000 = this.p.activeView.getComponent(var5 + 19).getStyle();
         StringBuffer var10001 = new StringBuffer();
         Pet var10002 = this.q.petParty[this.b];
         byte var3 = (byte)(var5 + 1);
         var10000.text = var10001.append(var10002.baseStats[var3]).toString();
         var10000 = this.p.activeView.getComponent(var5 + 31).getStyle();
         var10001 = new StringBuffer();
         var3 = (byte)(var5 + 1);
         var10000.text = var10001.append(var6.baseStats[var3]).toString();
      }

      this.g = true;
   }

   private void bh() {
      if (WorldManager.n != null) {
         if (!WorldManager.n.i()) {
            short var7 = GameDatabase.getValue((byte)0, (byte)this.q.petParty[this.b].getPetId(), (byte)19);
            String var9 = BaseScreen.getString(GameDatabase.getValue((byte)0, var7, (byte)0));
            ScriptEngine var8 = this;
            short var11 = GameDatabase.getValue((byte)0, (byte)var8.q.petParty[var8.b].getPetId(), (byte)19);
            short var13 = GameDatabase.getValue((byte)0, var11, (byte)17);
            var8.p.activeView.getComponent(10).setVisible(true);
            var8.p.activeView.getComponent(10).getStyle().m.a(var13, false, (byte)-1);
            var8.p.activeView.getComponent(38).getStyle().text = BaseScreen.getString(GameDatabase.getValue((byte)0, var11, (byte)0));
            Pet var14 = new Pet();
            short var5 = GameDatabase.getValue((byte)0, var11, (byte)3);
            int var6 = -1;
            if (var8.q.petParty[var8.b].baseStats[0] >= var5) {
               var6 = (byte)var8.q.petParty[var8.b].baseStats[0];
            }

            var14.initPet(
               var11, var8.q.petParty[var8.b].getLevel(), var8.q.petParty[var8.b].baseStats[5], (byte)var8.q.petParty[var8.b].currentStats[6], (short)var6, (byte)-1
            );
            var14.restoreProgress(var14.baseStats[1], var8.q.petParty[var8.b].z(), var8.q.petParty[var8.b].E);
            var14.b(var8.q.petParty[var8.b].toSkillSaveData());
            var8.q.a((byte)var8.q.petParty[var8.b].getSpeciesValue((byte)1), var11, (byte)2);
            var8.q.petParty[var8.b].restore(var14.toSaveData());
            var5 = (short)(GameDatabase.getValue((byte)0, (byte)var8.q.petParty[var8.b].getPetId(), (byte)20) + 12);
            var13 = GameDatabase.getValue((byte)0, (byte)var8.q.petParty[var8.b].getPetId(), (byte)21);
            var11 = GameDatabase.getValue((byte)0, (byte)var8.q.petParty[var8.b].getPetId(), (byte)19);
            var6 = var8.q.countItem(var5, (byte)2);
            if (var11 == -1) {
               var8.p.activeView.getComponent(42).getStyle().text = "";
               var8.p.activeView.getComponent(45).getStyle().text = "";
               var8.p.activeView.getComponent(46).getStyle().text = "";
            } else {
               var8.p.activeView.getComponent(45).getStyle().text = BaseScreen.getString(GameDatabase.getValue((byte)3, var5, (byte)0));
               var8.p.activeView.getComponent(46).getStyle().text = var6 + "/" + var13;
            }

            if (this.q.petParty[this.b].getEvolutionKind() == 2) {
               this.f = 3;
               this.p.openUI("/data/ui/msgwarm.ui", 257, this);
               this.a("Dị hoá thành #2" + var9, "Nhấn nút 5 để tiếp tục");
            } else {
               this.f = 3;
               this.p.openUI("/data/ui/msgwarm.ui", 257, this);
               this.a("Tiến hóa thành #2" + var9, "Nhấn nút 5 để tiếp tục");
            }

            WorldManager.n = null;
         }
      } else {
         if (BaseScreen.I() && !this.j() && this.o.isKeyPressed(196640)) {
            if (this.f == 2) {
               short var1 = (short)(GameDatabase.getValue((byte)0, (byte)this.q.petParty[this.b].getPetId(), (byte)20) + 12);
               short var2 = GameDatabase.getValue((byte)0, (byte)this.q.petParty[this.b].getPetId(), (byte)21);
               short var3;
               if ((var3 = GameDatabase.getValue((byte)0, (byte)this.q.petParty[this.b].getPetId(), (byte)19)) == -1) {
                  this.f = 3;
                  this.E();
                  this.a("Không thể lại tiến hóa hoặc dị hoá", "Nhấn nút 5 để tiếp tục");
                  return;
               }

               short var4 = GameDatabase.getValue((byte)0, var3, (byte)17);
               if (this.q.petParty[this.b].getLevel() >= Pet.evolutionLevelThresholds[GameDatabase.getValue((byte)0, var3, (byte)2) - 1]) {
                  if (this.q.countItem(var1, (byte)2) >= var2) {
                     this.p.activeView.getComponent(10).setVisible(false);
                     WorldManager.n = new SkillEffect();
                     short[] var10 = new short[]{0, 0, 10, 0, 0, this.q.petParty[this.b].spriteId, 0, 0, var4, 0, 0};
                     WorldManager.n.a(var10);
                     WorldManager.n.c(true);
                     WorldManager.n.a();
                     this.q.d(var1, var2, (byte)2);
                     return;
                  }

                  this.f = 3;
                  this.p.openUI("/data/ui/msgwarm.ui", 257, this);
                  if (this.q.petParty[this.b].getEvolutionKind() == 2) {
                     this.a("Tài liệu chưa đủ, không thể dị hoá", "Nhấn nút 5 để tiếp tục");
                     return;
                  }

                  this.a("Tài liệu chưa đủ, không thể tiến hóa", "Nhấn nút 5 để tiếp tục");
                  return;
               }

               this.f = 3;
               this.p.openUI("/data/ui/msgwarm.ui", 257, this);
               this.a(
                  "Còn chưa tới" + Pet.evolutionLevelThresholds[GameDatabase.getValue((byte)0, var3, (byte)2) - 1] + " cấp, không thể vào hóa",
                  "Nhấn nút 5 để tiếp tục"
               );
               return;
            }

            if (this.f == 3) {
               if (this.o.previousScreenMode == 6 || this.o.previousScreenMode == 0) {
                  this.f = 2;
                  this.p.closeUI("/data/ui/msgwarm.ui");
                  this.o.m();
                  return;
               }

               if (this.o.previousScreenMode == 27) {
                  this.e(this.b);
                  this.f = 0;
                  this.c = 0;
                  this.p.closeUI("/data/ui/msgwarm.ui");
                  this.p.closeUI("/data/ui/evolve.ui");
                  return;
               }
            }
         } else if (this.f < 3 && this.o.isKeyPressed(262144) && !this.j() && BaseScreen.J()) {
            this.f = 0;
            this.e(this.b);
            this.p.closeUI("/data/ui/evolve.ui");
         }
      }
   }

   public final void Y() {
      this.p.openUI("/data/ui/bag.ui", 257, this);
      this.b = 0;
      this.bi();
      this.p.activeView.navigateSelection(5);
      this.p.activeView.getComponent(14).getStyle().text = "Vật phẩm";
      this.b = 0;
   }

   private void bi() {
      ((MenuWidget)this.p.activeView.getComponent(8 + this.b * 39)).a.firstVisibleIndex = 0;
      ((MenuWidget)this.p.activeView.getComponent(8 + this.b * 39)).a.selectedIndex = 0;
      this.bj();
   }

   private void bj() {
      switch (this.b) {
         case 0:
            this.bk();
            break;
         case 1:
            ScriptEngine var5 = this;
            if (this.q.L.size() > 5) {
               ((MenuWidget)var5.p.activeView.getComponent(47)).a.a(1);
            } else {
               ((MenuWidget)var5.p.activeView.getComponent(47)).a.a(0);
            }

            ((MenuWidget)var5.p.activeView.getComponent(47)).a.itemCount = var5.q.L.size();
            var5.w = ((MenuWidget)var5.p.activeView.getComponent(47)).a.firstVisibleIndex;
            var5.h = ((MenuWidget)var5.p.activeView.getComponent(47)).a.selectedIndex;
            var5.p.activeView.getComponent(7).setVisible(false);

            for (int var6 = 0; var6 < 5; var6++) {
               if (var5.w + var6 < var5.q.L.size()) {
                  int[] var7 = (int[])var5.q.L.elementAt(var5.w + var6);
                  if (var5.p.activeView.getComponent(59 + var6 * 5).getStyle().m == null) {
                     var5.p.activeView.getComponent(59 + var6 * 5).getStyle().m = new SpriteWidget();
                     var5.p.activeView.getComponent(59 + var6 * 5).getStyle().m.a(0);
                     var5.p.activeView.getComponent(59 + var6 * 5).getStyle().m.a = 2;
                     var5.p.activeView.getComponent(59 + var6 * 5).getStyle().m.a(258, false, (byte)-1);
                  }

                  if (var5.p.activeView.getComponent(59 + var6 * 5).getStyle().i == null) {
                     var5.p.activeView.getComponent(59 + var6 * 5).getStyle().i = new SpriteWidget();
                     var5.p.activeView.getComponent(59 + var6 * 5).getStyle().i.a(0);
                     var5.p.activeView.getComponent(59 + var6 * 5).getStyle().i.a = 2;
                     var5.p.activeView.getComponent(59 + var6 * 5).getStyle().i.a(258, false, (byte)-1);
                  }

                  var5.p.activeView.getComponent(59 + var6 * 5).getStyle().m.a(GameDatabase.gameDatabase[3][var7[0]][1]);
                  var5.p.activeView.getComponent(59 + var6 * 5).getStyle().i.a(GameDatabase.gameDatabase[3][var7[0]][1]);
                  var5.p.activeView.getComponent(60 + var6 * 5).getStyle().text = BaseScreen.getString(GameDatabase.gameDatabase[3][var7[0]][0]);
                  if (var7[1] == 1) {
                     var5.p.activeView.getComponent(61 + var6 * 5).getStyle().text = "Đã mang theo";
                  } else {
                     var5.p.activeView.getComponent(61 + var6 * 5).getStyle().text = "";
                  }
               } else {
                  if (var5.p.activeView.getComponent(59 + var6 * 5).getStyle().m != null) {
                     var5.p.activeView.getComponent(59 + var6 * 5).getStyle().m.d();
                  }

                  var5.p.activeView.getComponent(60 + var6 * 5).getStyle().text = "";
                  var5.p.activeView.getComponent(61 + var6 * 5).getStyle().text = "";
               }
            }

            if (var5.q.L.size() > 0) {
               var5.p.activeView.getComponent(85).getStyle().text = BaseScreen.getString(
                  GameDatabase.gameDatabase[3][((int[])var5.q.L.elementAt(var5.h))[0]][2]
               );
            } else {
               var5.p.activeView.getComponent(85).getStyle().text = "";
            }

            if (var5.q.L.size() > 0) {
               var5.p.activeView.getComponent(84).setY(127 + var5.h * 72 / var5.q.L.size(), var5.p.activeView.getRoot());
            } else {
               var5.p.activeView.getComponent(84).setY(127, var5.p.activeView.getRoot());
            }
            break;
         case 2:
            ScriptEngine var4 = this;
            if (this.q.M.size() > 5) {
               ((MenuWidget)var4.p.activeView.getComponent(86)).a.a(1);
            } else {
               ((MenuWidget)var4.p.activeView.getComponent(86)).a.a(0);
            }

            ((MenuWidget)var4.p.activeView.getComponent(86)).a.itemCount = var4.q.M.size();
            var4.w = ((MenuWidget)var4.p.activeView.getComponent(86)).a.firstVisibleIndex;
            var4.h = ((MenuWidget)var4.p.activeView.getComponent(86)).a.selectedIndex;
            var4.p.activeView.getComponent(7).setVisible(false);

            for (int var2 = 0; var2 < 5; var2++) {
               if (var4.w + var2 < var4.q.M.size()) {
                  int[] var3 = (int[])var4.q.M.elementAt(var4.w + var2);
                  if (var4.p.activeView.getComponent(98 + var2 * 5).getStyle().m == null) {
                     var4.p.activeView.getComponent(98 + var2 * 5).getStyle().m = new SpriteWidget();
                     var4.p.activeView.getComponent(98 + var2 * 5).getStyle().m.a(0);
                     var4.p.activeView.getComponent(98 + var2 * 5).getStyle().m.a = 2;
                     var4.p.activeView.getComponent(98 + var2 * 5).getStyle().m.a(258, false, (byte)-1);
                  }

                  if (var4.p.activeView.getComponent(98 + var2 * 5).getStyle().i == null) {
                     var4.p.activeView.getComponent(98 + var2 * 5).getStyle().i = new SpriteWidget();
                     var4.p.activeView.getComponent(98 + var2 * 5).getStyle().i.a(0);
                     var4.p.activeView.getComponent(98 + var2 * 5).getStyle().i.a = 2;
                     var4.p.activeView.getComponent(98 + var2 * 5).getStyle().i.a(258, false, (byte)-1);
                  }

                  var4.p.activeView.getComponent(98 + var2 * 5).getStyle().m.a(GameDatabase.gameDatabase[3][var3[0]][1]);
                  var4.p.activeView.getComponent(98 + var2 * 5).getStyle().i.a(GameDatabase.gameDatabase[3][var3[0]][1]);
                  if (var3[0] == 17) {
                     var4.p.activeView.getComponent(99 + var2 * 5).getStyle().text = "Chìa khóa vàng";
                  } else {
                     var4.p.activeView.getComponent(99 + var2 * 5).getStyle().text = BaseScreen.getString(GameDatabase.gameDatabase[3][var3[0]][0]);
                  }

                  var4.p.activeView.getComponent(100 + var2 * 5).getStyle().text = "" + var3[1];
               } else {
                  if (var4.p.activeView.getComponent(98 + var2 * 5).getStyle().m != null) {
                     var4.p.activeView.getComponent(98 + var2 * 5).getStyle().m.d();
                  }

                  var4.p.activeView.getComponent(99 + var2 * 5).getStyle().text = "";
                  var4.p.activeView.getComponent(100 + var2 * 5).getStyle().text = "";
               }
            }

            if (var4.q.M.size() > 0) {
               var4.p.activeView.getComponent(124).getStyle().text = BaseScreen.getString(
                  GameDatabase.gameDatabase[3][((int[])var4.q.M.elementAt(var4.h))[0]][2]
               );
            } else {
               var4.p.activeView.getComponent(124).getStyle().text = "";
            }

            if (var4.q.M.size() > 0) {
               var4.p.activeView.getComponent(123).setY(127 + var4.h * 72 / var4.q.M.size(), var4.p.activeView.getRoot());
            } else {
               var4.p.activeView.getComponent(123).setY(127, var4.p.activeView.getRoot());
            }
            break;
         case 3:
            this.bl();
            if (this.h < 0 || this.q.N.size() <= 0) {
               return;
            }

            int[] var1 = (int[])this.q.N.elementAt(this.h);
            this.p.activeView.getComponent(164).setVisible(false);
            this.p.activeView.getComponent(165).setVisible(false);
            switch (var1[0]) {
               case 0:
                  if (this.q.k(var1[0])) {
                     this.p.activeView.getComponent(7).setVisible(true);
                     this.p.activeView.getComponent(7).getStyle().text = "Ấp trứng";
                     this.p.activeView.getComponent(164).setVisible(true);
                     this.p.activeView.getComponent(165).setVisible(true);
                     if (this.q.I == 0) {
                        this.p.activeView.getComponent(164).getStyle().text = "#P" + WorldManager.q * 100 / 10;
                        this.p.activeView.getComponent(165).getStyle().text = WorldManager.q + "/10";
                     } else {
                        this.p.activeView.getComponent(164).getStyle().text = "#P" + WorldManager.q * 100 / 30;
                        this.p.activeView.getComponent(165).getStyle().text = WorldManager.q + "/30";
                     }
                  } else {
                     this.p.activeView.getComponent(7).setVisible(false);
                  }
                  break;
               case 1:
               case 2:
               case 3:
               case 4:
                  this.p.activeView.getComponent(7).setVisible(false);
                  break;
               case 5:
               case 6:
               case 10:
                  this.p.activeView.getComponent(7).getStyle().text = "Mở ra";
                  break;
               case 7:
               case 8:
               case 9:
                  this.p.activeView.getComponent(7).getStyle().text = "Sử dụng";
            }
      }

      this.g = true;
   }

   private void bk() {
      int var1;
      if ((var1 = this.q.K.size() + this.q.J.size()) > 5) {
         ((MenuWidget)this.p.activeView.getComponent(8)).a.a(1);
      } else {
         ((MenuWidget)this.p.activeView.getComponent(8)).a.a(0);
      }

      ((MenuWidget)this.p.activeView.getComponent(8)).a.itemCount = var1;
      this.w = ((MenuWidget)this.p.activeView.getComponent(8)).a.firstVisibleIndex;
      this.h = ((MenuWidget)this.p.activeView.getComponent(8)).a.selectedIndex;
      this.p.activeView.getComponent(7).setVisible(true);
      this.p.activeView.getComponent(7).getStyle().text = "Sử dụng";

      for (int var2 = 0; var2 < 5; var2++) {
         if (this.w + var2 < var1) {
            int[] var3;
            if (this.w + var2 < this.q.K.size()) {
               var3 = (int[])this.q.K.elementAt(this.w + var2);
            } else {
               var3 = (int[])this.q.J.elementAt(this.w + var2 - this.q.K.size());
            }

            if (this.p.activeView.getComponent(18 + var2 * 5).getStyle().m == null) {
               this.p.activeView.getComponent(18 + var2 * 5).getStyle().m = new SpriteWidget();
               this.p.activeView.getComponent(18 + var2 * 5).getStyle().m.a(0);
               this.p.activeView.getComponent(18 + var2 * 5).getStyle().m.a = 2;
               this.p.activeView.getComponent(18 + var2 * 5).getStyle().m.a(258, false, (byte)-1);
            }

            if (this.p.activeView.getComponent(18 + var2 * 5).getStyle().i == null) {
               this.p.activeView.getComponent(18 + var2 * 5).getStyle().i = new SpriteWidget();
               this.p.activeView.getComponent(18 + var2 * 5).getStyle().i.a(0);
               this.p.activeView.getComponent(18 + var2 * 5).getStyle().i.a = 2;
               this.p.activeView.getComponent(18 + var2 * 5).getStyle().i.a(258, false, (byte)-1);
            }

            this.p.activeView.getComponent(18 + var2 * 5).getStyle().m.a(GameDatabase.gameDatabase[4][var3[0]][1]);
            this.p.activeView.getComponent(18 + var2 * 5).getStyle().i.a(GameDatabase.gameDatabase[4][var3[0]][1]);
            this.p.activeView.getComponent(19 + var2 * 5).getStyle().text = BaseScreen.getString(GameDatabase.gameDatabase[4][var3[0]][0]);
            this.p.activeView.getComponent(20 + var2 * 5).getStyle().text = "" + var3[1];
         } else {
            if (this.p.activeView.getComponent(18 + var2 * 5).getStyle().m != null) {
               this.p.activeView.getComponent(18 + var2 * 5).getStyle().m.d();
            }

            this.p.activeView.getComponent(19 + var2 * 5).getStyle().text = "";
            this.p.activeView.getComponent(20 + var2 * 5).getStyle().text = "";
         }
      }

      if (var1 > 0) {
         if (this.h < this.q.K.size()) {
            this.p.activeView.getComponent(46).getStyle().text = BaseScreen.getString(GameDatabase.gameDatabase[4][((int[])this.q.K.elementAt(this.h))[0]][2]);
         } else {
            this.p.activeView.getComponent(46).getStyle().text = BaseScreen.getString(
               GameDatabase.gameDatabase[4][((int[])this.q.J.elementAt(this.h - this.q.K.size()))[0]][2]
            );
         }
      } else {
         this.p.activeView.getComponent(46).getStyle().text = "";
      }

      if (var1 > 0) {
         this.p.activeView.getComponent(43).setY(127 + this.h * 72 / var1, this.p.activeView.getRoot());
      } else {
         this.p.activeView.getComponent(43).setY(127, this.p.activeView.getRoot());
      }
   }

   private void bl() {
      if (this.q.N.size() > 5) {
         ((MenuWidget)this.p.activeView.getComponent(125)).a.a(1);
      } else {
         ((MenuWidget)this.p.activeView.getComponent(125)).a.a(0);
      }

      ((MenuWidget)this.p.activeView.getComponent(125)).a.itemCount = this.q.N.size();
      this.w = ((MenuWidget)this.p.activeView.getComponent(125)).a.firstVisibleIndex;
      this.h = ((MenuWidget)this.p.activeView.getComponent(125)).a.selectedIndex;

      for (int var1 = 0; var1 < 5; var1++) {
         if (this.w + var1 < this.q.N.size()) {
            int[] var2 = (int[])this.q.N.elementAt(this.w + var1);
            if (this.p.activeView.getComponent(137 + var1 * 5).getStyle().m == null) {
               this.p.activeView.getComponent(137 + var1 * 5).getStyle().m = new SpriteWidget();
               this.p.activeView.getComponent(137 + var1 * 5).getStyle().m.a(0);
               this.p.activeView.getComponent(137 + var1 * 5).getStyle().m.a = 2;
               this.p.activeView.getComponent(137 + var1 * 5).getStyle().m.a(258, false, (byte)-1);
            }

            if (this.p.activeView.getComponent(137 + var1 * 5).getStyle().i == null) {
               this.p.activeView.getComponent(137 + var1 * 5).getStyle().i = new SpriteWidget();
               this.p.activeView.getComponent(137 + var1 * 5).getStyle().i.a(0);
               this.p.activeView.getComponent(137 + var1 * 5).getStyle().i.a = 2;
               this.p.activeView.getComponent(137 + var1 * 5).getStyle().i.a(258, false, (byte)-1);
            }

            this.p.activeView.getComponent(137 + var1 * 5).getStyle().m.a(GameDatabase.gameDatabase[5][var2[0]][1]);
            this.p.activeView.getComponent(137 + var1 * 5).getStyle().i.a(GameDatabase.gameDatabase[5][var2[0]][1]);
            this.p.activeView.getComponent(138 + var1 * 5).getStyle().text = BaseScreen.getString(GameDatabase.gameDatabase[5][var2[0]][0]);
            switch (var2[0]) {
               case 0:
                  if (this.q.k(var2[0])) {
                     this.p.activeView.getComponent(163).getStyle().text = BaseScreen.getString(GameDatabase.gameDatabase[5][var2[0]][2]);
                     if (WorldManager.a().r()) {
                        this.p.activeView.getComponent(139 + var1 * 5).getStyle().text = "Hoàn thành";
                     } else {
                        this.p.activeView.getComponent(139 + var1 * 5).getStyle().text = "1 cái";
                     }
                  } else {
                     this.p.activeView.getComponent(163).getStyle().text = BaseScreen.getString(634);
                     this.p.activeView.getComponent(139 + var1 * 5).getStyle().text = "0 cái";
                  }
                  break;
               default:
                  this.p.activeView.getComponent(139 + var1 * 5).getStyle().text = "";
            }
         } else {
            if (this.p.activeView.getComponent(137 + var1 * 5).getStyle().m != null) {
               this.p.activeView.getComponent(137 + var1 * 5).getStyle().m.d();
            }

            this.p.activeView.getComponent(138 + var1 * 5).getStyle().text = "";
            this.p.activeView.getComponent(139 + var1 * 5).getStyle().text = "";
         }
      }

      if (this.q.N.size() > 0) {
         int var3;
         if ((var3 = ((int[])this.q.N.elementAt(this.h))[0]) != 0) {
            this.p.activeView.getComponent(163).getStyle().text = BaseScreen.getString(GameDatabase.gameDatabase[5][var3][2]);
            this.p.activeView.getComponent(7).setVisible(true);
         }

         if (var3 == 0) {
            if (((int[])this.q.N.elementAt(this.h))[1] == 1) {
               this.p.activeView.getComponent(7).getStyle().text = "Đóng cửa";
            } else {
               this.p.activeView.getComponent(7).getStyle().text = "Mở ra";
            }
         } else if (var3 <= 0 && var3 > 4) {
            if (var3 == 10) {
               this.p.activeView.getComponent(7).getStyle().text = "Gia tốc";
            } else {
               this.p.activeView.getComponent(7).getStyle().text = "Sử dụng";
            }
         } else if (this.q.t == var3 - 1) {
            this.p.activeView.getComponent(7).getStyle().text = "Triệu hồi";
         } else {
            this.p.activeView.getComponent(7).getStyle().text = "Triệu hoán";
         }
      } else {
         this.p.activeView.getComponent(163).getStyle().text = "";
         this.p.activeView.getComponent(7).setVisible(false);
      }

      if (this.q.N.size() > 0) {
         this.p.activeView.getComponent(162).setY(127 + this.h * 72 / this.q.N.size(), this.p.activeView.getRoot());
      } else {
         this.p.activeView.getComponent(162).setY(127, this.p.activeView.getRoot());
      }
   }

   public final void Z() {
      if (this.f == 0 && this.o.isKeyPressed(4100)) {
         this.p.activeView.navigateSelection(0);
         this.f(this.c);
      } else if (this.f == 0 && this.o.isKeyPressed(8448)) {
         this.p.activeView.navigateSelection(1);
         this.f(this.c);
      } else if (this.o.isKeyPressed(196640)) {
         this.bo();
      } else {
         if (this.f == 0 && this.o.isKeyPressed(262144)) {
            this.o.setScreenMode((byte)8);
            this.p.closeUI("/data/ui/petstate.ui");
         }
      }
   }

   public final void aa() {
      if (this.o.isKeyPressed(4100)) {
         this.p.activeView.navigateSelection(0);
         this.f(this.b);
      } else if (this.o.isKeyPressed(8448)) {
         this.p.activeView.navigateSelection(1);
         this.f(this.b);
      } else if (this.o.isKeyPressed(196640)) {
         this.q.f(this.s, this.b);
         this.o.setScreenMode((byte)8);
      } else {
         if (this.o.isKeyPressed(262144)) {
            this.o.setScreenMode((byte)8);
            this.p.closeUI("/data/ui/petstate.ui");
         }
      }
   }

   public final void ab() {
      if (this.o.isKeyPressed(4100)) {
         this.p.activeView.navigateSelection(0);
         this.f(this.b);
      } else if (this.o.isKeyPressed(8448)) {
         this.p.activeView.navigateSelection(1);
         this.f(this.b);
      } else {
         if (this.o.isKeyPressed(196640)) {
            if (this.f == 0) {
               if (this.q.petParty[this.b].getLevel() < 50) {
                  this.p.openUI("/data/ui/msgwarm.ui", 257, this);
                  this.a("Chỉ có thể cho 50 cấp sủng vật sử dụng", "Nhấn nút 5 để tiếp tục");
                  this.f = 2;
                  return;
               }

               if (this.q.e(this.s, this.b)) {
                  this.p.openUI("/data/ui/msgwarm.ui", 257, this);
                  this.a("Sử dụng thành công", "Nhấn nút 5 để tiếp tục");
                  this.f = 1;
                  return;
               }
            } else {
               if (this.f == 1) {
                  this.f = 0;
                  this.o.setScreenMode((byte)8);
                  this.p.closeUI("/data/ui/msgwarm.ui");
                  this.p.closeUI("/data/ui/petstate.ui");
                  return;
               }

               if (this.f == 2) {
                  this.f = 0;
                  this.p.closeUI("/data/ui/msgwarm.ui");
                  return;
               }
            }
         } else if (this.o.isKeyPressed(262144) && this.f == 0) {
            this.o.setScreenMode((byte)8);
            this.p.closeUI("/data/ui/petstate.ui");
         }
      }
   }

   public final void ac() {
      this.o.l();
      if (this.f == 0 && this.o.isKeyPressed(16400) && !this.j() && !BaseScreen.b(this.b, 1)) {
         this.p.activeView.navigateSelection(7);
         this.p.activeView.navigateSelection(2);
         this.p.activeView.navigateSelection(5);
         this.bi();
         this.o.m();
      } else if (this.f == 0 && this.o.isKeyPressed(32832) && !this.j() && !BaseScreen.b(this.b, 1)) {
         this.p.activeView.navigateSelection(7);
         this.p.activeView.navigateSelection(3);
         this.p.activeView.navigateSelection(5);
         this.bi();
         this.o.m();
      } else if (this.f == 0 && this.o.isKeyPressed(4100) && !this.j() && !BaseScreen.b(this.h, 0)) {
         this.p.activeView.navigateSelection(0);
      } else if (this.f == 0 && this.o.isKeyPressed(8448) && !this.j() && !BaseScreen.b(this.h, 0)) {
         this.p.activeView.navigateSelection(1);
      } else if (this.o.isKeyPressed(196640) && !this.j() && BaseScreen.I()) {
         if (this.f == 0) {
            if (BaseScreen.H() && !BaseScreen.b(this.h, 0)) {
               return;
            }

            label208:
            switch (this.b) {
               case 0:
                  int[] var5;
                  if (this.h >= this.q.K.size()) {
                     if (this.q.J.size() <= 0) {
                        return;
                     }

                     var5 = (int[])this.q.J.elementAt(this.h - this.q.K.size());
                  } else {
                     var5 = (int[])this.q.K.elementAt(this.h);
                  }

                  switch (var5[0]) {
                     case 0:
                     case 1:
                     case 2:
                     case 3:
                        if (this.f == 0) {
                           this.p.openUI("/data/ui/msgwarm.ui", 257, this);
                           this.a("Không thể sử dụng", "Nhấn nút 5 để tiếp tục");
                           this.f = 1;
                        } else {
                           this.p.closeUI("/data/ui/msgwarm.ui");
                           this.f = 0;
                        }
                        break label208;
                     case 4:
                     case 5:
                     case 6:
                     case 7:
                     case 8:
                     case 9:
                     case 10:
                     case 11:
                     case 12:
                     default:
                        this.s = var5[0];
                        this.o.setScreenMode((byte)17);
                        this.p.closeUI("/data/ui/bag.ui");
                        break label208;
                     case 13:
                        if (this.f == 0) {
                           if (this.q.x <= 0) {
                              if (WorldManager.a().f == 3 && WorldManager.a().g == 7) {
                                 this.E();
                                 this.a("Nơi này không cách nào sử dụng tránh quái hoàn", "Nhấn nút 5 để tiếp tục");
                                 this.f = 1;
                              } else if (this.q.b(var5[0], 1, (byte)0)) {
                                 this.q.d(var5[0], 1, (byte)0);
                                 this.q.x = GameDatabase.gameDatabase[4][var5[0]][6];
                                 this.q.w = 0;
                                 int var7 = this.q.K.size() + this.q.J.size();
                                 if (this.h >= var7) {
                                    this.h = var7 - 1;
                                    ((MenuWidget)this.p.activeView.getComponent(8)).a.selectedIndex = this.h;
                                 }

                                 if (this.w > 0 && this.h - this.w < 4) {
                                    this.w--;
                                    ((MenuWidget)this.p.activeView.getComponent(8)).a.firstVisibleIndex = this.w;
                                 }

                                 this.bk();
                                 this.E();
                                 this.q.c(1);
                                 this.a("Thành công sử dụng đạo cụ, cũng có thời gian ngắn tránh quái hiệu quả", "Nhấn nút 5 để tiếp tục");
                                 this.f = 1;
                              }
                           } else {
                              this.E();
                              this.a("Đã có được thời gian ngắn tránh quái hiệu quả", "Nhấn nút 5 để tiếp tục");
                              this.f = 1;
                           }
                        }
                        break label208;
                     case 14:
                        if (this.f == 0) {
                           if (this.q.k(0) && (this.q.I == 0 && WorldManager.q < 10 || this.q.I > 0 && WorldManager.q < 30)) {
                              if (this.q.b(var5[0], 1, (byte)0)) {
                                 if (this.q.I == 0) {
                                    WorldManager.q = 10;
                                 } else {
                                    WorldManager.q = 30;
                                 }

                                 this.q.d(var5[0], 1, (byte)0);
                                 int var6 = this.q.K.size() + this.q.J.size();
                                 if (this.h >= var6) {
                                    this.h = var6 - 1;
                                    ((MenuWidget)this.p.activeView.getComponent(8)).a.selectedIndex = this.h;
                                 }

                                 if (this.w > 0 && this.h - this.w < 4) {
                                    this.w--;
                                    ((MenuWidget)this.p.activeView.getComponent(8)).a.firstVisibleIndex = this.w;
                                 }

                                 this.bk();
                                 this.p.openUI("/data/ui/msgwarm.ui", 257, this);
                                 this.a("Thành công sử dụng, tranh thủ thời gian đi ấp trứng trứng sủng vật a!", "Nhấn nút 5 để tiếp tục");
                                 this.f = 1;
                              }
                           } else {
                              this.p.openUI("/data/ui/msgwarm.ui", 257, this);
                              this.a("Không có trứng có thể ấp trứng", "Nhấn nút 5 để tiếp tục");
                              this.f = 1;
                           }
                        }
                        break label208;
                  }
               case 3:
                  int[] var1;
                  switch ((var1 = (int[])this.q.N.elementAt(this.h))[0]) {
                     case 0:
                        if (this.q.k(var1[0])) {
                           if (WorldManager.a().r()) {
                              if (this.q.y() == 2) {
                                 this.E();
                                 this.a("Không gian không đủ, thỉnh thanh lý không gian lại ấp trứng", "Nhấn nút 5 để tiếp tục");
                                 this.f = 1;
                              } else {
                                 WorldManager.q = 0;
                                 if (WorldManager.a().M.b[WorldManager.a(4, 5)] != null) {
                                    WorldManager.a().M.b[WorldManager.a(4, 5)][15] = 4;
                                    if (WorldManager.a().f == 4 && WorldManager.a().g == 5) {
                                       WorldManager.a().M.a[15].setState((byte)4);
                                    }
                                 }

                                 this.q.j(var1[0]);
                                 this.bl();
                                 this.E();
                                 this.a("Ấp trứng thành công", "Nhấn nút 5 để tiếp tục");
                                 this.f = 2;
                              }
                           } else {
                              this.E();
                              this.a("Vẫn chưa thể ấp trứng", "Nhấn nút 5 để tiếp tục");
                              this.f = 1;
                           }
                        }
                     case 1:
                     case 2:
                     case 3:
                     case 4:
                     default:
                        break;
                     case 5:
                        this.o.setScreenMode((byte)11);
                        this.p.closeUI("/data/ui/bag.ui");
                        break;
                     case 6:
                        this.o.setScreenMode((byte)12);
                        this.p.closeUI("/data/ui/bag.ui");
                        break;
                     case 7:
                     case 8:
                     case 9:
                        this.s = var1[0];
                        this.o.setScreenMode((byte)19);
                        this.p.closeUI("/data/ui/bag.ui");
                        break;
                     case 10:
                        this.o.setScreenMode((byte)24);
                        this.p.closeUI("/data/ui/bag.ui");
                  }
            }
         } else if (this.f == 1 || this.f == 2) {
            if (this.f != 2) {
               this.o.m();
               this.f = 0;
            } else {
               if (this.q.I == 0) {
                  byte var8 = this.g(58);
                  this.q.a((short)58);
                  if (var8 == 0) {
                     this.c("Ấp trứng tìm được #2" + BaseScreen.getString(GameDatabase.gameDatabase[0][58][0]) + "#0 để vào ba lô");
                  } else if (var8 == 1) {
                     this.c("Ấp trứng tìm được #2" + BaseScreen.getString(GameDatabase.gameDatabase[0][58][0]) + "#0 để vào ngân hàng");
                  } else {
                     this.c("Không có không gian, đã phóng sinh");
                  }
               } else {
                  int var9 = EngineUtils.a(new int[]{76, 52, 28, 4, 0}, EngineUtils.randomInt(100));
                  short[] var2 = new short[]{0, 56, 58, 95, 72};
                  byte var3 = this.g(var2[var9]);
                  int var4 = 0;

                  while (var4 < this.q.I && this.q.quickItemSlots[var4] != var2[var9]) {
                     var4++;
                  }

                  if (var4 >= this.q.I) {
                     this.q.a(var2[var9]);
                  }

                  if (var3 == 0) {
                     this.c("Ấp trứng tìm được #2" + BaseScreen.getString(GameDatabase.gameDatabase[0][var2[var9]][0]) + "#0 để vào ba lô");
                  } else if (var3 == 1) {
                     this.c("Ấp trứng tìm được #2" + BaseScreen.getString(GameDatabase.gameDatabase[0][var2[var9]][0]) + "#0 để vào ngân hàng");
                  } else {
                     this.c("Không có không gian, đã phóng sinh");
                  }
               }

               this.f = 3;
            }

            this.F();
         }
      } else if (this.f == 0 && this.o.isKeyPressed(262144) && !this.j() && BaseScreen.J()) {
         if (BaseScreen.isVipUnlocked) {
            this.b = 2;
         } else {
            this.b = 1;
         }

         this.o.setScreenMode((byte)6);
         this.p.closeUI("/data/ui/bag.ui");
      }

      if (this.f == 3 && !this.j()) {
         this.o.m();
         this.bj();
         this.f = 0;
      }

      this.f();
      this.g = true;
   }

   private byte g(int var1) {
      int[][] var2 = new int[][]{{60, 20, 0}, {75, 50, 20, 0}};
      byte var3 = -1;
      byte var4 = 0;
      if (GameDatabase.gameDatabase[0][var1][4] == 5) {
         if (GameDatabase.gameDatabase[0][var1][3] == 2) {
            var3 = 1;
            var4 = 2;
         } else if (GameDatabase.gameDatabase[0][var1][3] == 3) {
            var3 = 0;
            var4 = 3;
         }
      }

      int var5 = GameDatabase.gameDatabase[0][var1][1] * 10;
      short var6 = GameDatabase.gameDatabase[1][var5][5];
      byte var7 = this.q.y();
      if (var3 == -1) {
         if (var7 == 0) {
            this.q.a(var1, 5, (byte)2, (short)-1, new int[]{1, var5, var6});
         } else if (var7 == 1) {
            int var8 = EngineUtils.randomRange(GameDatabase.gameDatabase[0][var1][3], GameDatabase.gameDatabase[0][var1][3]);
            this.q.a(var1, 5, (byte)2, (byte)var8, Pet.b(var1, 5, var8), -1, new int[]{1, var5, var6});
         }
      } else {
         byte var9 = (byte)(var4 + (byte)EngineUtils.a(var2[var3], EngineUtils.randomInt(100)));
         if (var7 == 0) {
            this.q.a(var1, 5, (byte)2, var9, new int[]{1, var5, var6});
         } else if (var7 == 1) {
            this.q.a(var1, 5, (byte)2, var9, Pet.b(var1, 5, var9), -1, new int[]{1, var5, var6});
         }
      }

      return var7;
   }

   public final void ad() {
      this.aS();
      this.p.openUI("/data/ui/ride.ui", 257, this);
      this.b = 0;
      this.bm();
   }

   private void bm() {
      for (int var1 = 0; var1 < 4; var1++) {
         if (this.p.activeView.getComponent(var1 + 4).getStyle().m == null) {
            this.p.activeView.getComponent(var1 + 4).getStyle().m = new SpriteWidget();
            this.p.activeView.getComponent(var1 + 4).getStyle().m.a(0);
            this.p.activeView.getComponent(var1 + 4).getStyle().m.a = 3;
            this.p.activeView.getComponent(var1 + 4).getStyle().m.a(260, false, (byte)-1);
         }

         if (this.p.activeView.getComponent(var1 + 16).getStyle().m == null) {
            this.p.activeView.getComponent(var1 + 16).getStyle().m = new SpriteWidget();
            this.p.activeView.getComponent(var1 + 16).getStyle().m.a(131);
            this.p.activeView.getComponent(var1 + 16).getStyle().m.a = 2;
            this.p.activeView.getComponent(var1 + 16).getStyle().m.a(257, false, (byte)0);
         }

         if (this.q.f(var1)) {
            if (this.b == var1) {
               this.p.activeView.getComponent(var1 + 4).getStyle().m.a((byte)var1, (byte)-1);
               if (this.b == 0) {
                  this.p.activeView.getComponent(var1 + 8).getStyle().text = "Lục đi điểu";
               } else if (this.b == 1) {
                  this.p.activeView.getComponent(var1 + 8).getStyle().text = "Hư không hành giả";
               } else if (this.b == 2) {
                  this.p.activeView.getComponent(var1 + 8).getStyle().text = "Hải âu";
               } else if (this.b == 3) {
                  this.p.activeView.getComponent(var1 + 8).getStyle().text = "Nham sơn long";
               }
            } else {
               this.p.activeView.getComponent(var1 + 4).getStyle().m.a((byte)(var1 + 8), (byte)-1);
               this.p.activeView.getComponent(var1 + 8).getStyle().text = "";
            }

            if (!this.q.g(var1)) {
               this.p.activeView.getComponent(var1 + 16).setVisible(true);
            } else {
               this.p.activeView.getComponent(var1 + 16).setVisible(false);
            }
         } else {
            this.p.activeView.getComponent(var1 + 16).setVisible(false);
            this.p.activeView.getComponent(var1 + 4).getStyle().m.a((byte)(var1 + 4), (byte)-1);
            this.p.activeView.getComponent(var1 + 8).getStyle().text = "";
         }
      }
   }

   public final void ae() {
      if (!this.j() && this.o.isKeyPressed(16400)) {
         this.p.activeView.navigateSelection(2);
      } else if (!this.j() && this.o.isKeyPressed(32832)) {
         this.p.activeView.navigateSelection(3);
      } else if (!this.j() && this.o.isKeyPressed(512)) {
         this.p.closeUI("/data/ui/ride.ui");
         this.o.setScreenMode((byte)0);
      } else if (!this.j() && this.o.isKeyPressed(196640)) {
         if (this.q.f(this.b)) {
            if (this.q.g(this.b)) {
               this.q.h(this.b);
               this.p.closeUI("/data/ui/ride.ui");
               this.o.setScreenMode((byte)0);
            } else {
               this.b("Nơi này không thể sử dụng sủng vật cưỡi");
            }
         } else {
            this.b("Chưa có sủng vật cưỡi này");
         }
      } else if (!this.j() && this.o.isKeyPressed(262144)) {
         this.p.closeUI("/data/ui/ride.ui");
         this.o.setScreenMode((byte)0);
      }

      this.f();
      this.g = true;
   }

   public final void a(Pet var1, Pet var2) {
      this.p.openUI("/data/ui/battle.ui", 257, this);
      this.a = 0;
      this.e = 0;
      this.a(var1, false);
      this.b(var2, false);
      ScriptEngine var3 = this;
      this.p.activeView.getComponent(59).getStyle().text = "100%";
      var3.p.activeView.getComponent(58).getStyle().text = "100%";
      ((BattleScreen)var3.o).e();
      this.p.closeUI("/data/ui/world.ui");
   }

   public final void b(Pet var1, Pet var2) {
      if (var1.getElementalAffinity(var2) == 0) {
         if (var1.r() == 0) {
            this.p.activeView.getComponent(59).getStyle().text = "300%";
            this.p.activeView.getComponent(58).getStyle().text = "60%";
         } else {
            this.p.activeView.getComponent(59).getStyle().text = "60%";
            this.p.activeView.getComponent(58).getStyle().text = "300%";
         }
      } else if (var1.getElementalAffinity(var2) == 1) {
         if (var1.r() == 0) {
            this.p.activeView.getComponent(59).getStyle().text = "60%";
            this.p.activeView.getComponent(58).getStyle().text = "300%";
         } else {
            this.p.activeView.getComponent(59).getStyle().text = "300%";
            this.p.activeView.getComponent(58).getStyle().text = "60%";
         }
      } else {
         this.p.activeView.getComponent(59).getStyle().text = "100%";
         this.p.activeView.getComponent(58).getStyle().text = "100%";
      }
   }

   public final void a(Pet var1, Pet var2, Pet var3, int var4, int var5) {
      if (var1.getElementalAffinity(var2) == 0) {
         if (var3.r() == 0) {
            if ((var4 = var4 * (200 / var5)) == var5 && var4 != 200) {
               var4 = 200;
            }

            this.p.activeView.getComponent(59).getStyle().text = var4 + 100 + "%";
            return;
         }

         if (var3.r() == 1) {
            if ((var4 = var4 * (40 / var5)) == var5 && var4 != 40) {
               var4 = 40;
            }

            this.p.activeView.getComponent(58).getStyle().text = 100 - var4 + "%";
            return;
         }
      } else if (var1.getElementalAffinity(var2) == 1) {
         if (var3.r() == 0) {
            if ((var4 = var4 * (40 / var5)) == var5 && var4 != 40) {
               var4 = 40;
            }

            this.p.activeView.getComponent(59).getStyle().text = 100 - var4 + "%";
            return;
         }

         if (var3.r() == 1) {
            if ((var4 = var4 * (200 / var5)) == var5 && var4 != 200) {
               var4 = 200;
            }

            this.p.activeView.getComponent(58).getStyle().text = var4 + 100 + "%";
            return;
         }
      } else {
         this.p.activeView.getComponent(59).getStyle().text = "100%";
         this.p.activeView.getComponent(58).getStyle().text = "100%";
      }
   }

   public final void a(Pet var1, Pet var2, int var3, int var4) {
      this.D = this.E = 0;
      if (var1.getElementalAffinity(var2) == 0) {
         this.D += var3 * (200 / var4);
         if (this.D == var4 && this.D != 200) {
            this.D = 200;
         }

         this.p.activeView.getComponent(59).getStyle().text = 100 + this.D + "%";
         this.E += var3 * (40 / var4);
         if (this.E == var4 && this.E != 40) {
            this.E = 40;
         }

         this.p.activeView.getComponent(58).getStyle().text = 100 - this.E + "%";
      } else if (var1.getElementalAffinity(var2) == 1) {
         this.D += var3 * (40 / var4);
         if (this.D == var4 && this.D != 40) {
            this.D = 40;
         }

         this.p.activeView.getComponent(59).getStyle().text = 100 - this.D + "%";
         this.E += var3 * (200 / var4);
         if (this.E == var4 && this.E != 200) {
            this.E = 200;
         }

         this.p.activeView.getComponent(58).getStyle().text = 100 + this.E + "%";
      } else {
         this.p.activeView.getComponent(59).getStyle().text = "100%";
         this.p.activeView.getComponent(58).getStyle().text = "100%";
      }
   }

   public final boolean a(Pet var1, boolean var2) {
      int var3 = 0;
      if (this.F == 0 && (var3 = Math.abs(var1.N() - var1.currentStats[1]) / 11) <= 1) {
         var3 = 1;
      }

      int var4 = var1.N();
      short var5 = var1.currentStats[1];
      if (var4 != var5) {
         this.G++;
         if (this.G < 4) {
            if (var2) {
               this.p.activeView.getComponent(55).getStyle().text = "#P" + var1.L();
               this.p.activeView.getComponent(11).getStyle().text = "#P" + var1.M();
            } else {
               this.p.activeView.getComponent(55).getStyle().text = "#P" + var1.M();
               this.p.activeView.getComponent(11).getStyle().text = "#P" + var1.L();
            }

            return false;
         }
      }

      this.F += var3;
      if (var2) {
         if ((var4 = var4 + this.F) >= var5) {
            var4 = var5;
         }

         var1.u(var4);
         this.p.activeView.getComponent(41).getStyle().text = "#P" + var1.L();
         this.p.activeView.getComponent(11).getStyle().text = "#P" + var1.M();
         this.p.activeView.getComponent(55).getStyle().text = "#P" + var1.M();
      } else {
         if ((var4 = var4 - this.F) <= var5) {
            var4 = var5;
         }

         var1.u(var4);
         this.p.activeView.getComponent(41).getStyle().text = "#P" + var1.M();
         this.p.activeView.getComponent(55).getStyle().text = "#P" + var1.L();
         this.p.activeView.getComponent(11).getStyle().text = "#P" + var1.L();
      }

      this.p.activeView.getComponent(38).getStyle().text = var1.N() + "/" + var1.baseStats[1];
      this.p.activeView.getComponent(9).getStyle().text = "#P" + var1.O();
      this.p.activeView.getComponent(40).getStyle().text = var1.z() + "/" + var1.getExpToNextLevel();
      this.p.activeView.getComponent(12).getStyle().text = BaseScreen.getString(var1.getSpeciesValue((byte)0));
      this.p.activeView.getComponent(13).getStyle().text = "lv" + var1.getLevel();
      this.p.activeView.getComponent(17).getStyle().m.a(94 + var1.getSpeciesValue((byte)1));
      if (var4 == var5) {
         this.F = 0;
         this.G = 0;
         this.k = 0;
         return true;
      } else {
         return false;
      }
   }

   public final void a(Pet var1) {
      for (int var2 = 0; var2 < 6; var2++) {
         if (this.p.activeView.getComponent(var2 + 26).getStyle().m == null) {
            this.p.activeView.getComponent(var2 + 26).getStyle().m = new SpriteWidget();
            this.p.activeView.getComponent(var2 + 26).getStyle().m.a = 2;
            this.p.activeView.getComponent(var2 + 26).getStyle().m.a(0);
            this.p.activeView.getComponent(var2 + 26).getStyle().m.a(325, false, (byte)0);
         }

         if (this.p.activeView.getComponent(var2 + 43).getStyle().m == null) {
            this.p.activeView.getComponent(var2 + 43).getStyle().m = new SpriteWidget();
            this.p.activeView.getComponent(var2 + 43).getStyle().m.a = 2;
            this.p.activeView.getComponent(var2 + 43).getStyle().m.a(145);
            this.p.activeView.getComponent(var2 + 43).getStyle().m.a(257, false, (byte)0);
         }

         this.p.activeView.getComponent(var2 + 43).getStyle().m.a(145);
         this.p.activeView.getComponent(var2 + 26).getStyle().m.a(0);
      }

      for (int var3 = 0; var3 < 3; var3++) {
         if (var1.x[0][var3] != -1 && var1.v[var1.x[0][var3]][0] > 0) {
            short[] var10002 = var1.v[var1.x[0][var3]];
            this.p.activeView.getComponent(43 + this.k).getStyle().m.a(134 + var10002[0]);
            this.p.activeView.getComponent(26 + this.k).getStyle().m.a(var1.x[0][var3] + 12);
            this.k++;
         }

         if (var1.x[1][var3] != -1 && var1.w[var1.x[1][var3]][0] > 0) {
            short[] var4 = var1.w[var1.x[1][var3]];
            this.p.activeView.getComponent(43 + this.k).getStyle().m.a(134 + var4[0]);
            this.p.activeView.getComponent(26 + this.k).getStyle().m.a(var1.x[1][var3] + 1);
            this.k++;
         }
      }
   }

   private void g(Pet var1) {
      this.p.activeView.getComponent(11).getStyle().text = "#P" + var1.L();
      this.p.activeView.getComponent(38).getStyle().text = var1.N() + "/" + var1.baseStats[1];
      this.p.activeView.getComponent(16).getStyle().text = "lv" + var1.getLevel();
   }

   public final boolean b(Pet var1, boolean var2) {
      int var3 = 0;
      if (this.F == 0 && (var3 = Math.abs(var1.N() - var1.currentStats[1]) / 11) <= 1) {
         var3 = 1;
      }

      int var4 = var1.N();
      short var5 = var1.currentStats[1];
      if (var4 != var5) {
         this.G++;
         if (this.G < 4) {
            if (var2) {
               this.p.activeView.getComponent(56).getStyle().text = "#P" + var1.L();
               this.p.activeView.getComponent(14).getStyle().text = "#P" + var1.M();
            } else {
               this.p.activeView.getComponent(56).getStyle().text = "#P" + var1.M();
               this.p.activeView.getComponent(14).getStyle().text = "#P" + var1.L();
            }

            return false;
         }
      }

      this.F += var3;
      if (var2) {
         if ((var4 = var4 + this.F) >= var5) {
            var4 = var5;
         }

         var1.u(var4);
         this.p.activeView.getComponent(42).getStyle().text = "#P" + var1.L();
         this.p.activeView.getComponent(14).getStyle().text = "#P" + var1.M();
         this.p.activeView.getComponent(56).getStyle().text = "#P" + var1.M();
      } else {
         if ((var4 = var4 - this.F) <= var5) {
            var4 = var5;
         }

         var1.u(var4);
         this.p.activeView.getComponent(42).getStyle().text = "#P" + var1.M();
         this.p.activeView.getComponent(14).getStyle().text = "#P" + var1.L();
         this.p.activeView.getComponent(56).getStyle().text = "#P" + var1.L();
      }

      this.p.activeView.getComponent(39).getStyle().text = var1.N() + "/" + var1.baseStats[1];
      if (this.q.getCollectionFlag((byte)var1.getSpeciesValue((byte)1), var1.getPetId()) == 2) {
         this.p.activeView.getComponent(19).getStyle().m.a(101);
      } else {
         this.p.activeView.getComponent(19).getStyle().m.a(102);
      }

      this.p.activeView.getComponent(15).getStyle().text = BaseScreen.getString(var1.getSpeciesValue((byte)0));
      this.p.activeView.getComponent(16).getStyle().text = "lv" + var1.getLevel();
      this.p.activeView.getComponent(18).getStyle().m.a(94 + var1.getSpeciesValue((byte)1));
      if (var4 == var5) {
         this.F = 0;
         this.G = 0;
         this.k = 0;
         return true;
      } else {
         return false;
      }
   }

   public final void b(Pet var1) {
      for (int var2 = 0; var2 < 6; var2++) {
         if (this.p.activeView.getComponent(var2 + 32).getStyle().m == null) {
            this.p.activeView.getComponent(var2 + 32).getStyle().m = new SpriteWidget();
            this.p.activeView.getComponent(var2 + 32).getStyle().m.a = 2;
            this.p.activeView.getComponent(var2 + 32).getStyle().m.a(0);
            this.p.activeView.getComponent(var2 + 32).getStyle().m.a(325, false, (byte)0);
         }

         if (this.p.activeView.getComponent(var2 + 49).getStyle().m == null) {
            this.p.activeView.getComponent(var2 + 49).getStyle().m = new SpriteWidget();
            this.p.activeView.getComponent(var2 + 49).getStyle().m.a = 2;
            this.p.activeView.getComponent(var2 + 49).getStyle().m.a(145);
            this.p.activeView.getComponent(var2 + 49).getStyle().m.a(257, false, (byte)0);
         }

         this.p.activeView.getComponent(var2 + 49).getStyle().m.a(145);
         this.p.activeView.getComponent(var2 + 32).getStyle().m.a(0);
      }

      for (int var3 = 0; var3 < 3; var3++) {
         if (var1.x[0][var3] != -1 && var1.v[var1.x[0][var3]][0] > 0) {
            short[] var10002 = var1.v[var1.x[0][var3]];
            this.p.activeView.getComponent(49 + this.k).getStyle().m.a(134 + var10002[0]);
            this.p.activeView.getComponent(32 + this.k).getStyle().m.a(var1.x[0][var3] + 12);
            this.k++;
         }

         if (var1.x[1][var3] != -1 && var1.w[var1.x[1][var3]][0] > 0) {
            short[] var4 = var1.w[var1.x[1][var3]];
            this.p.activeView.getComponent(49 + this.k).getStyle().m.a(134 + var4[0]);
            this.p.activeView.getComponent(32 + this.k).getStyle().m.a(var1.x[1][var3] + 1);
            this.k++;
         }
      }
   }

   public final void af() {
      this.a = 0;
      this.y = null;
      this.p.closeUI("/data/ui/battle.ui");
   }

   private void a(boolean var1) {
      this.p.activeView.getComponent(20 + this.a).setVisible(var1);
   }

   public final void ag() {
      ((MenuWidget)this.p.activeView.getComponent(0)).b.selectedIndex = this.a;
      this.a(true);
   }

   public final void c(Pet var1) {
      this.f = 0;
      this.a(var1, false);
      this.ag();
   }

   public final void d(Pet var1) {
      ((BattleScreen)this.o).l();
      if (!BaseScreen.b(this.a, 1) && this.f == 0 && !this.j() && this.o.isKeyPressed(16400)) {
         this.p.activeView.navigateSelection(2);
      } else if (!BaseScreen.b(this.a, 1) && this.f == 0 && !this.j() && this.o.isKeyPressed(32832)) {
         this.p.activeView.navigateSelection(3);
      } else if (!this.j() && this.o.isKeyPressed(196640)) {
         switch (this.a) {
            case 0:
               this.a(false);
               this.o.setScreenMode((byte)3);
               break;
            case 1:
               if (((BattleScreen)this.o).b == 2) {
                  this.b("Trận chiến này không cho bắt sủng vật");
               } else if (this.q.y() == 2) {
                  this.b("Không gian không đủ, không cách nào bắt được");
               } else {
                  this.b = 0;
                  this.a(false);
                  ((BattleScreen)this.o).m();
                  this.o.setScreenMode((byte)21);
               }
               break;
            case 2:
               if (this.f == 0) {
                  if (var1.p(2)) {
                     this.p.openUI("/data/ui/msgwarm.ui", 257, this);
                     this.a("Trạng thái bị quấn, không thể sử dụng đạo cụ", "Nhấn nút 5 để tiếp tục");
                     this.f = 1;
                  } else {
                     this.a(false);
                     this.o.setScreenMode((byte)4);
                  }
               } else {
                  this.p.closeUI("/data/ui/msgwarm.ui");
                  this.f = 0;
               }
               break;
            case 3:
               if (this.f == 0) {
                  if (var1.p(2)) {
                     this.p.openUI("/data/ui/msgwarm.ui", 257, this);
                     this.a("Trạng thái bị quấn, không thể đổi sủng vật", "Nhấn nút 5 để tiếp tục");
                     this.f = 1;
                  } else {
                     this.a(false);
                     ((BattleScreen)this.o).g = ((BattleScreen)this.o).e[((BattleScreen)this.o).i];
                     BattleScreen.a().k = true;
                     this.o.setScreenMode((byte)5);
                  }
               } else {
                  this.p.closeUI("/data/ui/msgwarm.ui");
                  this.f = 0;
               }
               break;
            case 4:
               this.a(false);
               this.o.setScreenMode((byte)11);
               break;
            case 5:
               if (this.f == 0) {
                  if (var1.p(2)) {
                     this.p.openUI("/data/ui/msgwarm.ui", 257, this);
                     this.a("Trạng thái bị quấn, không thể chạy trốn", "Nhấn nút 5 để tiếp tục");
                     this.f = 1;
                  } else if (((BattleScreen)this.o).b <= 0 && OverworldScreen.j) {
                     boolean var3 = false;
                     if (((BattleScreen)this.o).h.getLevel() > ((BattleScreen)this.o).d[0].getLevel()) {
                        var3 = true;
                     } else if (((BattleScreen)this.o).h.getLevel() == ((BattleScreen)this.o).d[0].getLevel()) {
                        if (EngineUtils.randomInt(100) <= 95) {
                           var3 = true;
                        }
                     } else {
                        int var2 = ((BattleScreen)this.o).d[0].getLevel() - ((BattleScreen)this.o).h.getLevel();
                        if ((var2 = 95 - var2 * 10) <= 15) {
                           var2 = 15;
                        }

                        if (EngineUtils.randomInt(100) < var2) {
                           var3 = true;
                        }
                     }

                     if (var3) {
                        this.a(false);
                        GameStateController.getInstance().setScreenMode((byte)10);
                     } else {
                        this.f = 2;
                        this.b("Chạy trốn thất bại");
                     }
                  } else {
                     this.a(false);
                     this.f = 3;
                     this.b("Trận chiến này không thể trốn chạy");
                  }
               } else {
                  this.p.closeUI("/data/ui/msgwarm.ui");
                  this.f = 0;
               }
         }
      }

      this.f();
      if (this.f >= 2 && this.ax()) {
         if (this.f == 2) {
            ((BattleScreen)this.o).h.J = true;
            ((BattleScreen)this.o).i++;
            this.o.setScreenMode((byte)1);
         } else {
            this.a(true);
         }

         this.f = 0;
      }
   }

   public final void e(Pet var1) {
      this.p.openUI("/data/ui/choiceskill.ui", 257, this);
      ((MenuWidget)this.p.activeView.getComponent(0)).a.itemCount = var1.E();
      if (this.e >= var1.E()) {
         this.e = var1.E() - 1;
      }

      if (var1.E() > 5) {
         ((MenuWidget)this.p.activeView.getComponent(0)).a.a(1);
      } else {
         ((MenuWidget)this.p.activeView.getComponent(0)).a.a(-1);
      }

      this.p.activeView.getComponent(5).getStyle().text = "Sử dụng";
      ((MenuWidget)this.p.activeView.getComponent(0)).a.selectedIndex = this.e;
      this.h(var1);
      this.f = 0;
   }

   private void h(Pet var1) {
      this.w = ((MenuWidget)this.p.activeView.getComponent(0)).a.firstVisibleIndex;
      this.h = ((MenuWidget)this.p.activeView.getComponent(0)).a.selectedIndex;
      int var2 = var1.E();

      for (int var3 = 0; var3 < 5; var3++) {
         if (var3 >= var2) {
            this.p.activeView.getComponent(13 + var3 * 5).getStyle().text = "";
            this.p.activeView.getComponent(14 + var3 * 5).getStyle().text = "";
         } else {
            this.p.activeView.getComponent(13 + var3 * 5).getStyle().text = BaseScreen.getString(GameDatabase.gameDatabase[1][var1.t(this.w + var3)][1]);
            this.p.activeView.getComponent(14 + var3 * 5).getStyle().text = var1.skillUsesRemaining[this.w + var3]
               + "/"
               + GameDatabase.gameDatabase[1][var1.t(this.w + var3)][5];
         }
      }

      this.h(var1.learnedSkillIds[this.e]);
      this.p.activeView.getComponent(51).setY(98 + this.h * 72 / var2, this.p.activeView.getRoot());
   }

   private void h(int var1) {
      this.p.activeView.getComponent(53).getStyle().text = BaseScreen.getString(GameDatabase.gameDatabase[1][var1][2]);
   }

   public final void f(Pet var1) {
      if (this.ay()) {
         if (this.f == 0 && this.o.isKeyPressed(4100)) {
            this.p.activeView.navigateSelection(0);
            this.h(var1);
         } else if (this.f == 0 && this.o.isKeyPressed(8448)) {
            this.p.activeView.navigateSelection(1);
            this.h(var1);
         } else if (this.o.isKeyPressed(196640)) {
            if (this.f == 0) {
               if (var1.s(this.e)) {
                  this.p.closeUI("/data/ui/choiceskill.ui");
                  ((BattleScreen)this.o).b(var1.learnedSkillIds[this.e]);
                  int var10000 = ((BattleScreen)this.o).a;
                  ((BattleScreen)this.o).getClass();
                  if (var10000 == 0) {
                     ((BattleScreen)this.o).i();
                  } else {
                     this.o.setScreenMode((byte)6);
                  }
               } else {
                  this.f = 1;
                  this.p.openUI("/data/ui/msgwarm.ui", 257, this);
                  this.a("Kỹ năng giá trị chưa đủ", "Nhấn nút 5 để tiếp tục");
               }
            } else {
               this.f = 0;
               this.p.closeUI("/data/ui/msgwarm.ui");
               if (var1.p(2) && var1.r() == 0) {
                  boolean var2 = false;

                  for (int var3 = 0; var3 < var1.skillUsesRemaining.length; var3++) {
                     if (var1.skillUsesRemaining[var3] != 0) {
                        var2 = true;
                     }
                  }

                  if (!var2) {
                     this.p.closeUI("/data/ui/choiceskill.ui");
                     this.c("Không có kỹ năng giá trị, không cách nào chiến đấu");
                     ((BattleScreen)this.o).h();
                  }
               }
            }
         } else if (this.o.isKeyPressed(262144) && this.f == 0) {
            this.p.closeUI("/data/ui/choiceskill.ui");
            this.o.setScreenMode((byte)20);
         }
      }

      this.g();
   }

   public final void ah() {
      this.f = 0;
      this.p.openUI("/data/ui/choice.ui", 257, this);
      this.p.activeView.getComponent(8).getStyle().text = "Pokemon ball";
      this.p.activeView.getComponent(9).getStyle().text = "Tỉ lệ bắt";
      this.p.activeView.getComponent(5).getStyle().text = "Sử dụng";
      ((MenuWidget)this.p.activeView.getComponent(0)).a.selectedIndex = this.b;
      ((MenuWidget)this.p.activeView.getComponent(0)).a.a(0);
      ((MenuWidget)this.p.activeView.getComponent(0)).a.itemCount = this.q.K.size();

      for (int var1 = 0; var1 < this.q.K.size(); var1++) {
         int[] var2 = (int[])this.q.K.elementAt(var1);
         if (this.p.activeView.getComponent(var1 + 54).getStyle().m == null) {
            this.p.activeView.getComponent(var1 + 54).getStyle().m = new SpriteWidget();
            this.p.activeView.getComponent(var1 + 54).getStyle().m.a(0);
            this.p.activeView.getComponent(var1 + 54).getStyle().m.a = 2;
            this.p.activeView.getComponent(var1 + 54).getStyle().m.a(258, false, (byte)-1);
         }

         this.p.activeView.getComponent(var1 + 54).getStyle().m.a(GameDatabase.gameDatabase[4][var2[0]][1]);
         this.p.activeView.getComponent(13 + var1 * 5).getStyle().text = BaseScreen.getString(GameDatabase.gameDatabase[4][var2[0]][0]);
         this.p.activeView.getComponent(14 + var1 * 5).getStyle().text = ((BattleScreen)this.o).b(var2[0]) + "%";
      }

      this.p.activeView.getComponent(59).setVisible(false);
      this.p.activeView.getComponent(60).setVisible(false);
      this.bn();
   }

   private void bn() {
      int[] var1 = (int[])this.q.K.elementAt(this.b);
      this.p.activeView.getComponent(53).getStyle().text = "Số lượng: " + var1[1] + " cái ";
   }

   public final void ai() {
      this.o.l();
      if (!BaseScreen.b(this.b, 0) && this.f == 0 && this.o.isKeyPressed(4100) && !this.j()) {
         this.p.activeView.navigateSelection(0);
         this.bn();
      } else if (!BaseScreen.b(this.b, 0) && this.f == 0 && this.o.isKeyPressed(8448) && !this.j()) {
         this.p.activeView.navigateSelection(1);
         this.bn();
      } else if (this.o.isKeyPressed(196640) && !this.j() && BaseScreen.I()) {
         if (BaseScreen.H() && !BaseScreen.b(this.b, 0)) {
            return;
         }

         if (this.f == 0) {
            int[] var1 = (int[])this.q.K.elementAt(this.b);
            if (!this.q.b(var1[0], 1, (byte)0)) {
               this.p.openUI("/data/ui/msgwarm.ui", 257, this);
               this.a("Số lượng Pokemon ball không đủ", "Nhấn nút 5 để tiếp tục");
               this.f = 1;
            } else {
               this.f = 0;
               BattleScreen.l = (byte)var1[0];
               this.o.m();
               this.q.d(var1[0], 1, (byte)0);
               this.o.setScreenMode((byte)17);
               this.p.closeUI("/data/ui/choice.ui");
            }
         } else if (this.f == 1) {
            if (BaseScreen.isVipUnlocked && ((int[])this.q.K.elementAt(this.b))[0] == 0) {
               this.p.closeUI("/data/ui/choice.ui");
               this.o.setScreenMode((byte)101);
            }

            this.f = 0;
            this.p.closeUI("/data/ui/msgwarm.ui");
         }
      } else if (OverworldScreen.J() && this.f == 0 && this.o.isKeyPressed(262144) && !this.j()) {
         this.p.closeUI("/data/ui/choice.ui");
         this.o.setScreenMode((byte)20);
      }

      this.g();
   }

   public final void aj() {
      this.s = 0;
      this.f = 0;
      this.b = 0;
      this.p.openUI("/data/ui/choice.ui", 257, this);
      this.p.activeView.getComponent(8).getStyle().text = "Đạo cụ";
      this.p.activeView.getComponent(9).getStyle().text = "Số lượng";
      this.p.activeView.getComponent(5).getStyle().text = "Sử dụng";
      this.p.activeView.getComponent(59).setVisible(false);
      this.p.activeView.getComponent(60).setVisible(false);
      this.be();
   }

   public final void ak() {
      if (this.f == 0 && this.o.isKeyPressed(4100)) {
         this.p.activeView.navigateSelection(0);
      } else if (this.f == 0 && this.o.isKeyPressed(8448)) {
         this.p.activeView.navigateSelection(1);
      } else {
         if (this.o.isKeyPressed(196640)) {
            if (this.q.J.size() <= 0) {
               return;
            }

            this.s = ((int[])this.q.J.elementAt(this.h))[0];
            if (this.f == 0) {
               switch (GameDatabase.gameDatabase[4][this.s][5]) {
                  case 7:
                  case 8:
                  case 9:
                  case 10:
                     this.p.openUI("/data/ui/msgwarm.ui", 257, this);
                     this.a("Trong chiến đấu không thể sử dụng", "Nhấn nút 5 để tiếp tục");
                     this.f = 1;
                     return;
                  default:
                     this.o.setScreenMode((byte)16);
                     this.p.closeUI("/data/ui/choice.ui");
                     return;
               }
            }

            if (this.f == 1) {
               this.p.closeUI("/data/ui/msgwarm.ui");
               this.f = 0;
               return;
            }
         } else if (this.f == 0 && this.o.isKeyPressed(262144)) {
            this.p.closeUI("/data/ui/choice.ui");
            this.o.setScreenMode((byte)20);
         }
      }
   }

   private void bo() {
      if (this.f == 0) {
         this.f = 1;
         int var1;
         if (this.o instanceof WorldManager) {
            var1 = this.q.petParty[this.c].x(this.s);
         } else {
            var1 = this.q.petParty[((BattleScreen)this.o).f[this.c]].x(this.s);
         }

         switch (var1) {
            case 0:
               this.p.openUI("/data/ui/msgwarm.ui", 257, this);
               this.a("Sủng vật này đã tử vong, không thể sử dụng", "Nhấn nút 5 để tiếp tục");
               return;
            case 1:
               this.p.openUI("/data/ui/msgwarm.ui", 257, this);
               this.a("Sủng vật này không có, không thể sử dụng", "Nhấn nút 5 để tiếp tục");
               return;
            case 2:
               this.p.openUI("/data/ui/msgwarm.ui", 257, this);
               this.a("Máu đầy, không cần sử dụng", "Nhấn nút 5 để tiếp tục");
               return;
            case 3:
               this.p.openUI("/data/ui/msgwarm.ui", 257, this);
               this.a("Kỹ năng giá trị đã đầy, không cần sử dụng", "Nhấn nút 5 để tiếp tục");
               return;
            case 4:
               this.p.openUI("/data/ui/msgwarm.ui", 257, this);
               this.a("Trên người đều bị lợi hiệu quả", "Nhấn nút 5 để tiếp tục");
               return;
            case 5:
               this.p.openUI("/data/ui/msgwarm.ui", 257, this);
               this.a("Trong hưng phấn, không thể dùng", "Nhấn nút 5 để tiếp tục");
               return;
            case 6:
            default:
               if (this.q.b(this.s, 1, (byte)0)) {
                  if (this.o instanceof WorldManager) {
                     this.q.petParty[this.c].w(this.s);
                  } else {
                     ((BattleScreen)this.o).h.J = true;
                     this.q.petParty[((BattleScreen)this.o).f[this.c]].w(this.s);
                  }

                  this.e(this.c);
                  this.f = 1;
                  this.l = true;
                  this.p.openUI("/data/ui/msgwarm.ui", 257, this);
                  this.a("Thành công sử dụng đạo cụ", "Nhấn nút 5 để tiếp tục");
                  return;
               }

               this.f = 2;
               this.E();
               this.a("Đã không có đạo này cụ, thỉnh mua sắm", "Nhấn nút 5 để tiếp tục");
               return;
            case 7:
               this.p.openUI("/data/ui/msgwarm.ui", 257, this);
               this.a("Máu và kỹ năng đều đã đầy, không cần sử dụng", "Nhấn nút 5 để tiếp tục");
               return;
            case 8:
               this.p.openUI("/data/ui/msgwarm.ui", 257, this);
               this.a("Sủng vật đã chết, không thể sử dụng", "Nhấn nút 5 để tiếp tục");
         }
      } else if (this.f == 1) {
         this.f = 0;
         this.p.closeUI("/data/ui/msgwarm.ui");
      } else {
         if (this.f == 2) {
            this.f = 0;
            this.p.closeUI("/data/ui/msgwarm.ui");
            this.p.closeUI("/data/ui/petstate.ui");
            if (this.o instanceof WorldManager) {
               this.o.setScreenMode((byte)8);
               return;
            }

            if (BattleScreen.a().h.equals(((BattleScreen)this.o).getBattlePet(this.c))) {
               this.g(((BattleScreen)this.o).d(this.c));
            }

            if (((BattleScreen)this.o).h.J) {
               ((BattleScreen)this.o).i++;
               ((BattleScreen)this.o).setScreenMode((byte)1);
               return;
            }

            ((BattleScreen)this.o).setScreenMode((byte)4);
         }
      }
   }

   public final void al() {
      if (this.f == 0 && this.o.isKeyPressed(4100)) {
         this.p.activeView.navigateSelection(0);
      } else if (this.f == 0 && this.o.isKeyPressed(8448)) {
         this.p.activeView.navigateSelection(1);
      } else if (this.o.isKeyPressed(196640)) {
         this.bo();
      } else {
         if (this.f == 0 && this.o.isKeyPressed(262144)) {
            if (this.l) {
               if (BattleScreen.a().h.equals(((BattleScreen)this.o).getBattlePet(this.c))) {
                  this.g(((BattleScreen)this.o).d(this.c));
               }

               if (((BattleScreen)this.o).h.J) {
                  ((BattleScreen)this.o).i++;
                  ((BattleScreen)this.o).setScreenMode((byte)1);
               } else {
                  ((BattleScreen)this.o).setScreenMode((byte)4);
               }

               this.p.closeUI("/data/ui/petstate.ui");
               return;
            }

            ((BattleScreen)this.o).setScreenMode((byte)4);
            this.p.closeUI("/data/ui/petstate.ui");
         }
      }
   }

   public final void a(int var1, int var2) {
      if (this.i >= BattleScreen.j.size()) {
         this.i = 0;
         GameStateController.getInstance().setScreenMode((byte)10);
      } else {
         label21:
         while (true) {
            Pet var3 = (Pet)BattleScreen.j.elementAt(this.i);

            while (this.i < BattleScreen.j.size() && var3.isMaxLevel()) {
               this.i++;
               if (this.i < BattleScreen.j.size()) {
                  continue label21;
               }
            }

            this.H = var1;
            this.I = var2;
            var3.activate();
            var3.setPosition(var1, var2);
            this.x = 0;
            return;
         }
      }
   }

   public final void am() {
      if (this.i >= BattleScreen.j.size()) {
         this.i = 0;
         GameStateController.getInstance().setScreenMode((byte)10);
      } else {
         if (this.x <= 0) {
            this.J += 8;
         }

         Pet var1;
         int var2 = (var1 = (Pet)BattleScreen.j.elementAt(this.i)).A() + this.J;
         int var3 = var1.getExpToNextLevel();
         int var4 = var1.z();
         if (var2 >= var3) {
            var2 = var3;
         } else if (var2 >= var4) {
            var2 = var4;
         }

         if (this.o.isKeyPressed(196640)) {
            if (var4 >= var3) {
               var2 = var3;
               this.p.activeView.getComponent(40).getStyle().text = var2 + "/" + var2;
               this.p.activeView.getComponent(9).getStyle().text = "#P" + var1.v(var2);
               var1.j(0);
               this.x = 0;
               ((BattleScreen)this.o).setScreenMode((byte)22);
            } else if (var2 < var4) {
               this.J = 0;
               var2 = var4;
               var1.j(var2);
               this.p.activeView.getComponent(40).getStyle().text = var2 + "/" + var1.getExpToNextLevel();
               this.p.activeView.getComponent(9).getStyle().text = "#P" + var1.v(var2);
            } else {
               this.p.activeView.getComponent(40).getStyle().text = var4 + "/" + var1.getExpToNextLevel();
               this.p.activeView.getComponent(9).getStyle().text = "#P" + var1.v(var4);
               var1.j(var2);
               this.i++;

               while (this.i < BattleScreen.j.size() && ((Pet)BattleScreen.j.elementAt(this.i)).isMaxLevel()) {
                  this.i++;
               }

               if (this.i >= BattleScreen.j.size()) {
                  this.i = 0;
                  GameStateController.getInstance().setScreenMode((byte)10);
               } else {
                  ((Pet)BattleScreen.j.elementAt(this.i)).setPosition(this.H, this.I);
               }

               this.x = 0;
               this.J = 0;
            }
         } else {
            this.p.activeView.getComponent(40).getStyle().text = var2 + "/" + var1.getExpToNextLevel();
            this.p.activeView.getComponent(9).getStyle().text = "#P" + var1.v(var2);
            Pet var6 = var1;
            ScriptEngine var5 = this;
            this.p.activeView.getComponent(12).getStyle().text = BaseScreen.getString(var6.getSpeciesValue((byte)0));
            var5.p.activeView.getComponent(13).getStyle().text = "lv" + var6.getLevel();
            var5.p.activeView.getComponent(17).getStyle().m.a(94 + var6.getSpeciesValue((byte)1));
            if (var2 >= var3) {
               var1.j(0);
               ((BattleScreen)this.o).setScreenMode((byte)22);
            } else {
               if (var2 < var4) {
                  return;
               }

               this.x++;
               var1.j(var2);
               if (this.x >= 10) {
                  this.i++;

                  while (this.i < BattleScreen.j.size() && ((Pet)BattleScreen.j.elementAt(this.i)).isMaxLevel()) {
                     this.i++;
                  }

                  if (this.i >= BattleScreen.j.size()) {
                     this.i = 0;
                     GameStateController.getInstance().setScreenMode((byte)10);
                  } else {
                     ((Pet)BattleScreen.j.elementAt(this.i)).setPosition(this.H, this.I);
                  }

                  this.x = 0;
               }
            }

            this.J = 0;
         }
      }
   }

   public final void an() {
      Pet var1 = (Pet)BattleScreen.j.elementAt(this.i);
      String[] var2 = new String[4];

      for (int var3 = 0; var3 < 4; var3++) {
         StringBuffer var10002 = new StringBuffer();
         byte var4 = (byte)(var3 + 1);
         var2[var3] = var10002.append(var1.baseStats[var4]).toString();
      }

      var1.levelUp();
      this.g(var1);
      this.p.openUI("/data/ui/levelUp.ui", 257, this);

      for (int var5 = 0; var5 < 4; var5++) {
         this.p.activeView.getComponent(var5 + 19).getStyle().text = var2[var5];
      }

      if (var1.E() < 5 && var1.E() < var1.getLevel() / 10 + 1) {
         this.y = var1.findLearnableSkills();
         this.p.activeView.getComponent(51).getStyle().text = "Có thể học tập kỹ năng mới";
      } else {
         this.p.activeView.getComponent(51).getStyle().text = "";
      }

      this.p.activeView.getComponent(38).getStyle().text = BaseScreen.getString(GameDatabase.gameDatabase[0][var1.getPetId()][0]);
      this.p.activeView.getComponent(40).getStyle().text = "" + var1.getLevel();
      if (this.p.activeView.getComponent(10).getStyle().m == null) {
         this.p.activeView.getComponent(10).getStyle().m = new SpriteWidget();
         this.p.activeView.getComponent(10).getStyle().m.a = 3;
         this.p.activeView.getComponent(10).getStyle().m.a(0);
         this.p.activeView.getComponent(10).getStyle().m.a(var1.spriteId, false, (byte)-1);
      }

      for (int var6 = 0; var6 < 4; var6++) {
         UIStyle var10000 = this.p.activeView.getComponent(var6 + 31).getStyle();
         StringBuffer var10001 = new StringBuffer();
         byte var7 = (byte)(var6 + 1);
         var10000.text = var10001.append(var1.baseStats[var7]).toString();
      }
   }

   public final void ao() {
      this.K++;
      if (this.K > 40) {
         this.K = 0;
         if (this.y != null) {
            ((BattleScreen)this.o).setScreenMode((byte)23);
         } else if (this.i + 1 >= BattleScreen.j.size()) {
            if (((Pet)BattleScreen.j.elementAt(this.i)).z() > 0) {
               this.o.setScreenMode((byte)8);
            } else {
               this.i = 0;
               GameStateController.getInstance().setScreenMode((byte)10);
            }

            this.p.closeUI("/data/ui/levelUp.ui");
         } else {
            this.o.setScreenMode((byte)8);
            this.p.closeUI("/data/ui/levelUp.ui");
         }
      }

      if (this.o.isKeyPressed(196640)) {
         this.K = 0;
         if (this.y != null) {
            this.o.setScreenMode((byte)23);
            return;
         }

         if (this.i + 1 >= BattleScreen.j.size()) {
            if (((Pet)BattleScreen.j.elementAt(this.i)).z() > 0) {
               this.o.setScreenMode((byte)8);
            } else {
               this.i = 0;
               GameStateController.getInstance().setScreenMode((byte)10);
            }

            this.p.closeUI("/data/ui/levelUp.ui");
            return;
         }

         this.o.setScreenMode((byte)8);
         this.p.closeUI("/data/ui/levelUp.ui");
      }
   }

   public final void ap() {
      this.p.openUI("/data/ui/choiceskill.ui", 257, this);
      this.p.closeUI("/data/ui/levelUp.ui");
      this.b = 0;
      this.f = 0;
      ((MenuWidget)this.p.activeView.getComponent(0)).a.itemCount = this.y.length;
      if (this.y.length > 5) {
         ((MenuWidget)this.p.activeView.getComponent(0)).a.a(1);
      } else {
         ((MenuWidget)this.p.activeView.getComponent(0)).a.a(-1);
      }

      if (this.p.activeView.getComponent(5).getStyle().m == null) {
         this.p.activeView.getComponent(5).getStyle().m = new SpriteWidget();
         this.p.activeView.getComponent(5).getStyle().m.a = 3;
         this.p.activeView.getComponent(5).getStyle().m.a(0);
         this.p.activeView.getComponent(5).getStyle().m.a(257, false, (byte)-1);
      }

      this.p.activeView.getComponent(5).getStyle().m.a((byte)11, (byte)-1);
      this.p.activeView.getComponent(6).setVisible(false);
      this.bp();
      if (!WorldManager.D) {
         this.b("Có thể nhấn #1nút mềm trái#0 để học tập kỹ năng");
         WorldManager.D = true;
      }
   }

   private void bp() {
      this.w = ((MenuWidget)this.p.activeView.getComponent(0)).a.firstVisibleIndex;
      this.h = ((MenuWidget)this.p.activeView.getComponent(0)).a.selectedIndex;

      for (int var1 = 0; var1 < 5; var1++) {
         if (var1 >= this.y.length) {
            this.p.activeView.getComponent(13 + var1 * 5).getStyle().text = "";
            this.p.activeView.getComponent(14 + var1 * 5).getStyle().text = "";
         } else {
            this.p.activeView.getComponent(13 + var1 * 5).getStyle().text = BaseScreen.getString(GameDatabase.gameDatabase[1][this.y[this.w + var1]][1]);
            this.p.activeView.getComponent(14 + var1 * 5).getStyle().text = "" + GameDatabase.gameDatabase[1][this.y[this.w + var1]][5];
         }
      }

      this.h(this.y[this.h]);
      this.p.activeView.getComponent(51).setY(98 + this.h * 62 / this.y.length, this.p.activeView.getRoot());
   }

   public final void aq() {
      if (!this.j() && this.o.isKeyPressed(4100) && this.f == 0) {
         this.p.activeView.navigateSelection(0);
         this.bp();
      } else if (!this.j() && this.o.isKeyPressed(8448) && this.f == 0) {
         this.p.activeView.navigateSelection(1);
         this.bp();
      } else if (!this.j() && this.f == 0 && (this.o.isKeyPressed(131072) || this.o.consumeLeftSoftPointer()) || this.f == 1 && this.o.isKeyPressed(196640)) {
         if (this.f == 0) {
            this.f = 1;
            this.p.openUI("/data/ui/msgwarm.ui", 257, this);
            this.a("Học tập" + BaseScreen.getString(GameDatabase.gameDatabase[1][this.y[this.b]][1]), "Nhấn nút 5 để tiếp tục");
         } else if (this.f == 1) {
            Pet var1;
            (var1 = (Pet)BattleScreen.j.elementAt(this.i)).learnSkill((byte)this.y[this.h]);
            this.y = null;
            if (this.i + 1 >= BattleScreen.j.size() && var1.z() <= 0) {
               this.i = 0;
               GameStateController.getInstance().setScreenMode((byte)10);
            } else {
               this.o.setScreenMode((byte)8);
            }

            this.p.closeUI("/data/ui/msgwarm.ui");
            this.p.closeUI("/data/ui/choiceskill.ui");
         }
      }

      this.f();
   }

   public final void ar() {
      this.f = 0;
      this.b("Ba lô sủng vật đều thăng 5 cấp");
   }

   public final void as() {
      if (this.f == 0) {
         if (this.ax()) {
            this.f = 1;
            if (WorldManager.E.size() <= 0) {
               this.o.setScreenMode((byte)14);
            }
         }
      } else if (this.f == 1) {
         this.p.closeUI("/data/ui/bodyShop.ui");
         this.bq();
         this.g = true;
      } else {
         ScriptEngine var1 = this;
         label89:
         if (this.f >= 3) {
            if (var1.f == 5) {
               var1.f = 6;
               var1.H();
               var1.a("Đang lưu...");
               var1.J();
            } else if (var1.f == 6) {
               WorldManager.G = 2;
               WorldManager.h();
               if (((WorldManager)var1.o).j()) {
                  var1.a("Lưu thành công");
                  var1.f = 7;
               }
            } else if (var1.f == 7) {
               var1.p.closeUI("/data/ui/msgtip.ui");
               var1.f = 0;
               if (var1.o.previousScreenMode == 14) {
                  var1.o.setScreenMode((byte)14);
               } else {
                  var1.o.setScreenMode((byte)0);
               }
               break label89;
            }

            if (!var1.j() && var1.o.isKeyPressed(4100) && var1.f == 3) {
               var1.p.activeView.navigateSelection(0);
               var1.bp();
            } else if (!var1.j() && var1.o.isKeyPressed(8448) && var1.f == 3) {
               var1.p.activeView.navigateSelection(1);
               var1.bp();
            } else if (!var1.j() && var1.f == 3 && (var1.o.isKeyPressed(131072) || var1.o.consumeLeftSoftPointer())
               || var1.f == 4 && var1.o.isKeyPressed(196640)) {
               if (var1.f == 3) {
                  var1.f = 4;
                  var1.p.openUI("/data/ui/msgwarm.ui", 257, var1);
                  var1.a("Học tập" + BaseScreen.getString(GameDatabase.gameDatabase[1][var1.y[var1.h]][1]), "Nhấn nút 5 để tiếp tục");
               } else if (var1.f == 4) {
                  ((Pet)WorldManager.E.elementAt(var1.i)).learnSkill((byte)var1.y[var1.h]);
                  var1.y = null;
                  var1.i++;
                  if (var1.i >= WorldManager.E.size()) {
                     var1.i = 0;
                     var1.f = 5;
                  } else {
                     var1.bq();
                  }

                  var1.p.closeUI("/data/ui/msgwarm.ui");
                  var1.p.closeUI("/data/ui/choiceskill.ui");
               }
            }
         } else if (var1.o.isKeyPressed(196640)) {
            ScriptEngine var2 = var1;
            var1.p.openUI("/data/ui/choiceskill.ui", 257, var2);
            var2.p.closeUI("/data/ui/levelUp.ui");
            var2.b = 0;
            var2.f = 3;
            ((MenuWidget)var2.p.activeView.getComponent(0)).a.itemCount = var2.y.length;
            if (var2.y.length > 5) {
               ((MenuWidget)var2.p.activeView.getComponent(0)).a.a(1);
            } else {
               ((MenuWidget)var2.p.activeView.getComponent(0)).a.a(-1);
            }

            if (var2.p.activeView.getComponent(5).getStyle().m == null) {
               var2.p.activeView.getComponent(5).getStyle().m = new SpriteWidget();
               var2.p.activeView.getComponent(5).getStyle().m.a = 3;
               var2.p.activeView.getComponent(5).getStyle().m.a(0);
               var2.p.activeView.getComponent(5).getStyle().m.a(257, false, (byte)-1);
            }

            var2.p.activeView.getComponent(5).getStyle().m.a((byte)11, (byte)-1);
            var2.p.activeView.getComponent(6).setVisible(false);
            var2.bp();
            var1.g = true;
         }
      }

      this.f();
   }

   private void bq() {
      this.f = 2;
      Pet var1 = (Pet)WorldManager.E.elementAt(this.i);
      this.p.openUI("/data/ui/levelUp.ui", 257, this);

      for (int var2 = 0; var2 < 4; var2++) {
         this.p.activeView.getComponent(var2 + 19).getStyle().text = "" + var1.i((int)((byte)(var2 + 1 - 1)));
      }

      if (var1.E() < 5 && var1.E() < var1.getLevel() / 10 + 1) {
         this.y = var1.findLearnableSkills();
         this.p.activeView.getComponent(51).getStyle().text = "Nhấn nút 5 học tập kỹ năng mới";
      } else {
         this.p.activeView.getComponent(51).getStyle().text = "";
      }

      this.p.activeView.getComponent(38).getStyle().text = BaseScreen.getString(GameDatabase.gameDatabase[0][var1.getPetId()][0]);
      this.p.activeView.getComponent(40).getStyle().text = "" + var1.getLevel();
      if (this.p.activeView.getComponent(10).getStyle().m == null) {
         this.p.activeView.getComponent(10).getStyle().m = new SpriteWidget();
         this.p.activeView.getComponent(10).getStyle().m.a = 3;
         this.p.activeView.getComponent(10).getStyle().m.a(0);
         this.p.activeView.getComponent(10).getStyle().m.a(var1.spriteId, false, (byte)-1);
      }

      for (int var4 = 0; var4 < 4; var4++) {
         UIStyle var10000 = this.p.activeView.getComponent(var4 + 31).getStyle();
         StringBuffer var10001 = new StringBuffer();
         byte var3 = (byte)(var4 + 1);
         var10000.text = var10001.append(var1.baseStats[var3]).toString();
      }
   }

   public final void at() {
      this.p.openUI("/data/ui/npcEnemy.ui", 296, this);
      if (this.p.activeView.getComponent(1).getStyle().m == null) {
         this.p.activeView.getComponent(1).getStyle().m = new SpriteWidget();
         this.p.activeView.getComponent(1).getStyle().m.a = 2;
         this.p.activeView.getComponent(1).getStyle().m.a(296, false, (byte)0);
         this.p.activeView.getComponent(1).getStyle().m.a(0);
      }

      this.p.activeView.getComponent(36).setVisible(false);
   }

   private void a(int var1, int var2, int var3) {
      if (var3 != -1 && this.p.activeView.getComponent(var3).getStyle().m != null) {
         this.p.activeView.getComponent(var3).setVisible(false);
      }

      if (this.p.activeView.getComponent(var1).getStyle().m == null) {
         this.p.activeView.getComponent(var1).getStyle().m = new SpriteWidget();
         this.p.activeView.getComponent(var1).getStyle().m.a = 2;
         this.p.activeView.getComponent(var1).getStyle().m.a(296, false, (byte)0);
         this.p.activeView.getComponent(var1).getStyle().m.a(0);
      }

      this.p.activeView.getComponent(var1).getStyle().m.a(var2);
   }

   public final void b(int var1, int var2) {
      switch (var1) {
         case 0:
            this.p.activeView.getComponent(1).getStyle().m.a(var2);
            return;
         case 1:
            for (int var9 = 2; var9 < 4; var9++) {
               if (this.p.activeView.getComponent(var9).getStyle().m == null) {
                  this.p.activeView.getComponent(var9).getStyle().m = new SpriteWidget();
                  this.p.activeView.getComponent(var9).getStyle().m.a = 2;
                  this.p.activeView.getComponent(var9).getStyle().m.a(0);
               }

               if (var9 % 2 == 1) {
                  this.p.activeView.getComponent(var9).getStyle().m.a(0, false, (byte)-1);
               } else if (WorldManager.u == -1) {
                  if (WorldManager.v == -1) {
                     this.p.activeView.getComponent(var9).getStyle().m.a(WorldManager.a().d[8].spriteRenderer.spriteId, false, (byte)-1);
                  } else {
                     this.p.activeView.getComponent(var9).getStyle().m.a(WorldManager.a().d[WorldManager.v].spriteRenderer.spriteId, false, (byte)-1);
                  }
               } else {
                  this.p.activeView.getComponent(var9).getStyle().m.a(WorldManager.a().d[WorldManager.u].spriteRenderer.spriteId, false, (byte)-1);
               }

               this.p.activeView.getComponent(var9).getStyle().m.a(1);
            }

            this.p.activeView.getComponent(1).getStyle().m.a(var2);
            return;
         case 2:
            for (int var8 = 2; var8 < 4; var8++) {
               if (this.p.activeView.getComponent(var8).getStyle().m != null) {
                  this.p.activeView.getComponent(var8).setVisible(false);
               }

               if (this.p.activeView.getComponent(var8 + 32).getStyle().m == null) {
                  this.p.activeView.getComponent(var8 + 32).getStyle().m = new SpriteWidget();
                  this.p.activeView.getComponent(var8 + 32).getStyle().m.a = 2;
                  this.p.activeView.getComponent(var8 + 32).getStyle().m.a(0);
               }

               if (var8 % 2 == 1) {
                  this.p.activeView.getComponent(var8 + 32).getStyle().m.a(0, false, (byte)-1);
               } else if (WorldManager.u == -1) {
                  if (WorldManager.v == -1) {
                     this.p.activeView.getComponent(var8 + 32).getStyle().m.a(WorldManager.a().d[8].spriteRenderer.spriteId, false, (byte)-1);
                  } else {
                     this.p.activeView.getComponent(var8 + 32).getStyle().m.a(WorldManager.a().d[WorldManager.v].spriteRenderer.spriteId, false, (byte)-1);
                  }
               } else {
                  this.p.activeView.getComponent(var8 + 32).getStyle().m.a(WorldManager.a().d[WorldManager.u].spriteRenderer.spriteId, false, (byte)-1);
               }

               this.p.activeView.getComponent(var8 + 32).getStyle().m.a(1);
            }

            this.p.activeView.getComponent(1).getStyle().m.a(var2);
            return;
         case 3:
            for (int var7 = 2; var7 < 4; var7++) {
               if (this.p.activeView.getComponent(var7 + 32).getStyle().m != null) {
                  this.p.activeView.getComponent(var7 + 32).setVisible(false);
               }

               if (this.p.activeView.getComponent(var7 + 2).getStyle().m == null) {
                  this.p.activeView.getComponent(var7 + 2).getStyle().m = new SpriteWidget();
                  this.p.activeView.getComponent(var7 + 2).getStyle().m.a = 2;
                  this.p.activeView.getComponent(var7 + 2).getStyle().m.a(0);
               }

               if (var7 % 2 == 1) {
                  this.p.activeView.getComponent(var7 + 2).getStyle().m.a(0, false, (byte)-1);
               } else if (WorldManager.u == -1) {
                  if (WorldManager.v == -1) {
                     this.p.activeView.getComponent(var7 + 2).getStyle().m.a(WorldManager.a().d[8].spriteRenderer.spriteId, false, (byte)-1);
                  } else {
                     this.p.activeView.getComponent(var7 + 2).getStyle().m.a(WorldManager.a().d[WorldManager.v].spriteRenderer.spriteId, false, (byte)-1);
                  }
               } else {
                  this.p.activeView.getComponent(var7 + 2).getStyle().m.a(WorldManager.a().d[WorldManager.u].spriteRenderer.spriteId, false, (byte)-1);
               }

               this.p.activeView.getComponent(var7 + 2).getStyle().m.a(1);
            }

            this.p.activeView.getComponent(1).getStyle().m.a(var2);
            this.L = BattleScreen.a().j();
            this.M = this.q.partyPetCount;
            if (var2 - 3 < this.L) {
               this.a(6, 6, -1);
            }

            if (var2 - 3 < this.M) {
               this.a(18, 6, -1);
               return;
            }
            break;
         case 4:
            if (var2 - 3 < this.L) {
               this.a(6 + (var2 - 3 << 1), 6, 6 + (var2 - 4 << 1));
            } else {
               this.a(6 + (var2 - 3 << 1), 5, 6 + (var2 - 4 << 1));
            }

            if (var2 - 4 < this.L) {
               this.a(7 + (var2 - 4 << 1), 6, 6 + (var2 - 4 << 1));
            } else {
               this.a(7 + (var2 - 4 << 1), 5, 6 + (var2 - 4 << 1));
            }

            if (var2 - 4 < this.M) {
               this.a(19 + (var2 - 4 << 1), 6, 18 + (var2 - 4 << 1));
            } else {
               this.a(19 + (var2 - 4 << 1), 5, 18 + (var2 - 4 << 1));
            }

            if (var2 - 3 < this.M) {
               this.a(18 + (var2 - 3 << 1), 6, 18 + (var2 - 4 << 1));
               return;
            }

            this.a(18 + (var2 - 3 << 1), 5, 18 + (var2 - 4 << 1));
            return;
         case 5:
            if (var2 - 4 < this.L) {
               this.a(7 + (var2 - 4 << 1), 6, 6 + (var2 - 4 << 1));
            } else {
               this.a(7 + (var2 - 4 << 1), 5, 6 + (var2 - 4 << 1));
            }

            if (var2 - 4 < this.M) {
               this.a(19 + (var2 - 4 << 1), 6, 18 + (var2 - 4 << 1));
               return;
            }

            this.a(19 + (var2 - 4 << 1), 5, 18 + (var2 - 4 << 1));
            return;
         case 6:
            this.a(30, 8, -1);
            this.a(31, 7, -1);
            return;
         case 7:
            this.a(32, 8, 30);
            this.a(33, 7, 31);
            return;
         case 8:
            this.p.activeView.getComponent(36).setVisible(true);
            return;
         case 9:
            this.p.activeView.getComponent(36).setVisible(false);
            return;
         case 10:
            this.a(1, 4, 32);
            this.a(1, 4, 33);

            for (int var5 = 4; var5 < 6; var5++) {
               this.p.activeView.getComponent(var5).setVisible(false);
            }

            for (int var6 = 7; var6 < 19; var6 += 2) {
               this.p.activeView.getComponent(var6).setX(172 + 17 * (var6 - 7) / 2, this.p.activeView.getRoot());
               this.p.activeView.getComponent(var6 + 12).setX(-30 + 17 * (var6 - 7) / 2, this.p.activeView.getRoot());
            }

            return;
         case 11:
            for (int var3 = 4; var3 < 6; var3++) {
               this.p.activeView.getComponent(var3).setVisible(false);
            }

            for (int var4 = 7; var4 < 19; var4 += 2) {
               this.p.activeView.getComponent(var4).setVisible(false);
               this.p.activeView.getComponent(var4 + 12).setVisible(false);
            }

            this.a(1, 0, -1);
      }
   }

   private void e(String var1) {
      this.t = var1;
      if (this.p.activeView.getComponent(1).getStyle().m == null) {
         this.p.activeView.getComponent(1).getStyle().m = new SpriteWidget();
         this.p.activeView.getComponent(1).getStyle().m.a(0);
         this.p.activeView.getComponent(1).getStyle().m.a = 3;
         this.p.activeView.getComponent(1).getStyle().m.a(257, false, (byte)-2);
      }

      this.p.activeView.getComponent(1).getStyle().m.a((byte)9, (byte)-2);
      this.v = 0;
   }

   public final void au() {
      this.p.openUI("/data/ui/openbox.ui", 257, this);
      this.e("Không có cái chìa khóa, có thể đến tài liệu cửa hàng mua sắm");
   }

   public final void av() {
      this.p.openUI("/data/ui/openbox.ui", 257, this);
      this.e("Đạo cụ đã đủ");
   }

   public final void a(String var1, int var2) {
      this.p.openUI("/data/ui/openbox.ui", 257, this);
      this.e(var1 + " x " + var2);
   }

   public final void b(String var1) {
      this.p.openUI("/data/ui/openbox.ui", 257, this);
      this.e(var1);
   }

   public final void aw() {
      if (this.p.isTopUI("/data/ui/openbox.ui")) {
         this.p.closeUI("/data/ui/openbox.ui");
      }
   }

   public final boolean ax() {
      return !this.p.isTopUI("/data/ui/openbox.ui");
   }

   public final void c(String var1) {
      this.p.openUI("/data/ui/taskTip.ui", 257, this);
      String var2 = var1;
      ScriptEngine var3 = this;
      this.t = var2;
      if (var3.p.activeView.getComponent(1).getStyle().m == null) {
         var3.p.activeView.getComponent(1).getStyle().m = new SpriteWidget();
         var3.p.activeView.getComponent(1).getStyle().m.a(0);
         var3.p.activeView.getComponent(1).getStyle().m.a = 3;
         var3.p.activeView.getComponent(1).getStyle().m.a(257, false, (byte)-2);
      }

      var3.p.activeView.getComponent(1).getStyle().m.a((byte)10, (byte)-2);
      var3.v = 0;
   }

   private void br() {
      if (this.p.isTopUI("/data/ui/taskTip.ui")) {
         this.p.closeUI("/data/ui/taskTip.ui");
      }
   }

   public final boolean ay() {
      return !this.p.isTopUI("/data/ui/taskTip.ui");
   }

   public final void az() {
      this.c = 0;
      this.f = 0;
      this.p.openUI("/data/ui/bodyShop.ui", 257, this);
      this.bs();
   }

   private void bs() {
      String var1 = "";
      switch (this.c) {
         case 0:
            var1 = "Tùy thời mua sắm các loại đạo cụ, già trẻ không gạt.";
            break;
         case 1:
            int[] var4 = new int[]{2, 1, 2};
            var1 = BaseScreen.getString(602) + BaseScreen.a(604, var4);
            break;
         case 2:
            int[] var3 = new int[]{2, 1, 2};
            var1 = BaseScreen.getString(603) + BaseScreen.a(604, var3);
            break;
         case 3:
            int[] var2 = new int[]{2, 1, 2};
            var1 = BaseScreen.getString(601) + BaseScreen.a(604, var2);
      }

      this.p.activeView.getComponent(11).getStyle().text = var1;
      if (this.c > 0) {
         this.o.d((byte)0);
         this.bt();
      }
   }

   private void bt() {
      switch (this.c) {
         case 1:
            this.o.c((byte)3);
            return;
         case 2:
            this.o.c((byte)4);
            return;
         case 3:
            this.o.c((byte)2);
      }
   }

   public final void aA() {
      switch (this.c) {
         case 0:
            if (this.o.isKeyPressed(4100) && this.f == 0) {
               this.p.activeView.navigateSelection(0);
               this.bs();
               return;
            }

            if (this.o.isKeyPressed(8448) && this.f == 0) {
               this.p.activeView.navigateSelection(1);
               this.bs();
               return;
            }

            if (this.o.isKeyPressed(196640)) {
               this.o.setScreenMode((byte)26);
               this.p.closeUI("/data/ui/bodyShop.ui");
               return;
            }

            if (this.o.isKeyPressed(786432)) {
               this.b = 0;
               this.o.setScreenMode((byte)6);
               this.p.closeUI("/data/ui/bodyShop.ui");
               return;
            }
            break;
         default:
            switch (this.o.N()) {
               case 0:
                  if (this.o.isKeyPressed(4100) && this.f == 0) {
                     this.p.activeView.navigateSelection(0);
                     this.bs();
                     return;
                  }

                  if (this.o.isKeyPressed(8448) && this.f == 0) {
                     this.p.activeView.navigateSelection(1);
                     this.bs();
                     return;
                  }

                  if (this.f == 0 && this.o.isKeyPressed(196640) || this.f == 1 && this.o.isKeyPressed(65568)) {
                     if (this.f != 0) {
                        this.f = 0;
                        this.p.closeUI("/data/ui/msgwarm.ui");
                        return;
                     }

                     this.bt();
                     if (this.o.L() == 3) {
                        if (WorldManager.H != null) {
                           WorldManager.H.removeAllElements();
                        }

                        int var1 = 0;

                        while (var1 < Player.getInstance().partyPetCount && Player.getInstance().petParty[var1].getLevel() >= 50) {
                           var1++;
                        }

                        if (var1 >= Player.getInstance().partyPetCount) {
                           this.f = 1;
                           this.p.openUI("/data/ui/msgwarm.ui", 257, this);
                           this.a("Trong ba lô sủng vật đều đã max level", "Nhấn nút 5 để tiếp tục");
                           return;
                        }
                     }

                     if (this.o.O() > 1) {
                        this.o.d((byte)1);
                        return;
                     }

                     this.o.g(1);
                     return;
                  }

                  if (this.o.isKeyPressed(786432) && this.f == 0) {
                     this.b = 0;
                     this.o.setScreenMode((byte)6);
                     this.p.closeUI("/data/ui/bodyShop.ui");
                     return;
                  }
                  break;
               case 1:
                  if (this.o.isKeyPressed(196640)) {
                     this.o.h(1);
                     return;
                  }

                  if (this.o.isKeyPressed(262144)) {
                     this.o.h(2);
                     return;
                  }
                  break;
               case 2:
                  if (this.o.M()) {
                     if (this.o.L() == 3) {
                        this.o.setScreenMode((byte)25);
                     }

                     this.o.d((byte)5);
                  } else {
                     this.o.d((byte)1);
                  }

                  this.f = 0;
                  return;
               case 3:
                  if (this.o.isKeyPressed(393216)) {
                     this.o.h(1);
                  }
            }
      }
   }

   private boolean bu() {
      if (this.f == 0) {
         this.f = 1;
         this.H();
         this.a("Đang lưu...");
         this.J();
      } else if (this.f == 1) {
         if (this.o.L() == 3) {
            if (WorldManager.a().k()) {
               this.a("Lưu thành công");
               this.f = 2;
            }
         } else if (WorldManager.a().n()) {
            this.a("Lưu thành công");
            this.f = 2;
         }
      } else if (this.f == 2) {
         this.p.closeUI("/data/ui/msgtip.ui");
         this.f = 3;
      } else if (this.f == 3) {
         return true;
      }

      return false;
   }

   public final void aB() {
      this.p.openUI("/data/ui/dialog.ui", 257, this);
      this.p.activeView.getComponent(12).setVisible(false);
      this.p.activeView.getComponent(13).setVisible(false);
   }

   public final void a(String var1, String var2, int var3) {
      this.p.openUI("/data/ui/dialog.ui", 257, this);
      EngineUtils.a(var2, BaseScreen.getFontHeight(), this.p.activeView.getComponent(14).getWidth(), BaseScreen.getSmallFont(), this.o.uiManager.textPainter);
      EngineUtils.c(this.p.activeView.getComponent(14).getHeight());
      this.p.activeView.getComponent(14).getStyle().text = EngineUtils.d(1);
      WorldManager.t = (byte)var3;
      WorldManager.s = -1;
      this.p.activeView.getComponent(8).setVisible(false);
      this.p.activeView.getComponent(11).setVisible(false);
      this.p.activeView.getComponent(12).setVisible(true);
      this.p.activeView.getComponent(13).setVisible(true);
      if (var3 == -1) {
         this.p.activeView.getComponent(12).setVisible(false);
         this.p.activeView.getComponent(13).setVisible(false);
      }

      switch (var3) {
         case 0:
            this.p.activeView.getComponent(13).setVisible(false);
            this.p.activeView.getComponent(12).getStyle().text = var1;
            return;
         case 1:
            this.p.activeView.getComponent(12).setVisible(false);
            this.p.activeView.getComponent(13).getStyle().text = var1;
      }
   }

   public final void b(int var1) {
      this.p.activeView.getComponent(14).getStyle().text = EngineUtils.d(var1);
   }

   public final void aC() {
      this.p.closeUI("/data/ui/dialog.ui");
   }

   public final boolean c(int var1, int var2) {
      if (var2 == -1) {
         return true;
      }

      switch (var1) {
         case 0:
            if (this.p.activeView.getComponent(11).getStyle().m.a().isLastAnimationStep()) {
               return true;
            }
            break;
         case 1:
            if (this.p.activeView.getComponent(8).getStyle().m.a().isLastAnimationStep()) {
               return true;
            }
      }

      this.g = true;
      return false;
   }

   public final void a(int var1, int var2, String[] var3, String var4) {
      this.b = 0;
      this.p.openUI(this.N[var1], 257, this);
      ((MenuWidget)this.p.activeView.getComponent(0)).a.itemCount = var2;
      switch (var1) {
         case 0:
            for (int var7 = 0; var7 < var3.length; var7++) {
               this.p.activeView.getComponent(var7 + 12).getStyle().text = var3[var7];
            }

            return;
         case 1:
            this.p.activeView.getComponent(5).getStyle().text = var4;

            for (int var6 = 0; var6 < var3.length; var6++) {
               this.p.activeView.getComponent(9 + (var6 << 2)).getStyle().text = var3[var6];
            }

            return;
         case 2:
            this.p.activeView.getComponent(10).setVisible(false);
            this.p.activeView.getComponent(8).getStyle().text = "Trò chơi";
            this.p.activeView.getComponent(9).getStyle().text = "Xác nhận";

            for (int var5 = 0; var5 < var3.length; var5++) {
               this.p.activeView.getComponent(var5 + 5).getStyle().text = var3[var5];
            }
      }
   }

   public final int c(int var1) {
      if (this.o.isKeyPressed(4100)) {
         this.p.activeView.navigateSelection(0);
         this.b = this.z[0];
      } else if (this.o.isKeyPressed(8448)) {
         this.p.activeView.navigateSelection(1);
         this.b = this.z[0];
      } else if (this.o.isKeyPressed(196640)) {
         int var2 = var1;
         ScriptEngine var3 = this;
         this.p.closeUI(var3.N[var2]);
         return this.b;
      }

      return -1;
   }

   public final void a(int[] var1, int[] var2, String[] var3, String[] var4) {
      this.b = 0;
      this.p.openUI("/data/ui/taskOption.ui", 257, this);

      for (int var5 = 0; var5 < var4.length; var5++) {
         this.p.activeView.getComponent(var5 + 17).getStyle().text = var4[var5];
      }

      for (int var6 = 0; var6 < var1.length; var6++) {
         if (this.p.activeView.getComponent((var6 << 1) + 13).getStyle().m == null) {
            this.p.activeView.getComponent((var6 << 1) + 13).getStyle().m = new SpriteWidget();
            this.p.activeView.getComponent((var6 << 1) + 13).getStyle().m.a = 2;
            if (var1[var6] >= 3 && var1[var6] < 5) {
               this.p.activeView.getComponent((var6 << 1) + 13).getStyle().m.a(-1);
               this.p.activeView.getComponent((var6 << 1) + 13).getStyle().m.a(257, false, (byte)0);
            } else {
               this.p.activeView.getComponent((var6 << 1) + 13).getStyle().m.a(0);
               this.p.activeView.getComponent((var6 << 1) + 13).getStyle().m.a(258, false, (byte)0);
            }
         }

         switch (var1[var6]) {
            case 0:
               this.p.activeView.getComponent((var6 << 1) + 13).getStyle().m.a(GameDatabase.gameDatabase[4][var2[var6]][1]);
               this.p.activeView.getComponent((var6 << 1) + 14).getStyle().text = var3[var6];
               break;
            case 1:
               this.p.activeView.getComponent((var6 << 1) + 13).getStyle().m.a(GameDatabase.gameDatabase[3][var2[var6]][1]);
               this.p.activeView.getComponent((var6 << 1) + 14).getStyle().text = var3[var6];
               break;
            case 2:
               this.p.activeView.getComponent((var6 << 1) + 13).getStyle().m.a(GameDatabase.gameDatabase[5][var2[var6]][1]);
               this.p.activeView.getComponent((var6 << 1) + 14).getStyle().text = var3[var6];
               break;
            case 3:
               this.p.activeView.getComponent((var6 << 1) + 13).getStyle().m.a(84);
               this.p.activeView.getComponent((var6 << 1) + 14).getStyle().text = var3[var6];
               break;
            case 4:
               this.p.activeView.getComponent((var6 << 1) + 13).getStyle().m.a(83);
               this.p.activeView.getComponent((var6 << 1) + 14).getStyle().text = var3[var6];
            case 5:
            default:
               break;
            case 6:
               this.p.activeView.getComponent(21).getStyle().text = "#2"
                  + BaseScreen.getString(GameDatabase.getValue((byte)0, (short)var2[var6], (byte)0))
                  + " #0"
                  + var3[var6];
         }
      }
   }

   public final int aD() {
      if (this.o.isKeyPressed(4100)) {
         this.p.activeView.navigateSelection(0);
         this.b = this.z[0];
      } else if (this.o.isKeyPressed(8448)) {
         this.p.activeView.navigateSelection(1);
         this.b = this.z[0];
      } else {
         if (this.o.isKeyPressed(196640)) {
            this.p.closeUI("/data/ui/taskOption.ui");
            return this.b;
         }

         if (this.o.isKeyPressed(262144)) {
            this.p.closeUI("/data/ui/taskOption.ui");
            return 1;
         }
      }

      return -1;
   }

   public final void aE() {
      this.bw();
      this.c("Có dùng 10000 kim tiền để khôi phục trạng thái của tất cả sủng vật trong ba lô không?", "Tại chỗ sống lại");
   }

   public final void aF() {
      if (!this.o.isKeyPressed(196640)) {
         if (this.f == 0 && this.o.isKeyPressed(786432)) {
            this.bv();
            this.bx();
         }
      } else if (this.f == 0) {
         if (!this.q.hasGold(10000)) {
            this.E();
            this.a("Kim tiền chưa đủ", "Nhấn nút 5 để tiếp tục");
            this.f = 1;
         } else {
            this.q.addGold(-10000);

            for (int var2 = 0; var2 < this.q.partyPetCount; var2++) {
               this.q.petParty[var2].I();
               this.q.petParty[var2].u(this.q.petParty[var2].currentStats[1]);
            }

            BattleScreen.a().c();
            this.o.setScreenMode((byte)0);
            this.bx();
         }
      } else {
         for (int var1 = 0; var1 < this.q.partyPetCount; var1++) {
            this.q.petParty[var1].l(1);
            this.q.petParty[var1].u(1);
            this.q.petParty[var1].activate();
         }

         if (BaseScreen.isVipUnlocked) {
            this.o.setScreenMode((byte)102);
         } else {
            this.bv();
         }

         this.F();
      }
   }

   private void bv() {
      byte var1 = -1;
      if (WorldManager.a().f == 9 && WorldManager.a().g != 0) {
         var1 = (byte)WorldManager.a().g;
      }

      if (var1 == -1) {
         WorldManager.a();
         if (WorldManager.i()) {
            WorldManager.a().f();
            this.q.y = false;
            GameStateController.getInstance().setScreenMode((byte)9);
         } else {
            GameStateController.getInstance().setScreenMode((byte)7);
         }
      } else {
         if (WorldManager.a().g == 0) {
            short[] var2 = new short[]{15, 194, 433, 16, 142, 357, 17, 97, 268, 18, 183, 224};

            for (int var3 = 0; var3 < WorldManager.a().d.length; var3++) {
               for (int var4 = 0; var4 < var2.length / 3; var4++) {
                  if (WorldManager.a().d[var3].I == var2[var4 * 3]) {
                     WorldManager.a().d[var3].setPosition(var2[var4 * 3 + 1], var2[var4 * 3 + 2]);
                  }
               }
            }
         }

         WorldManager.u = -1;

         for (int var5 = 0; var5 < this.q.partyPetCount; var5++) {
            this.q.petParty[var5].l(1);
            this.q.petParty[var5].u(1);
            this.q.petParty[var5].activate();
         }

         WorldManager.a().f = this.m[var1 << 2];
         WorldManager.a().g = this.m[(var1 << 2) + 1];
         Player.getInstance().setPosition(this.m[(var1 << 2) + 2], this.m[(var1 << 2) + 3]);
         short var10001 = this.m[(var1 << 2) + 2];
         Player.getInstance().targetEntity.setPosition(var10001, this.m[(var1 << 2) + 3]);
         Player.getInstance().facingDirection = 2;
         GameStateController.getInstance().setScreenMode((byte)10);
      }
   }

   public final void aG() {
      this.o.c((byte)0);
      this.o.d((byte)0);
      this.bw();
      int[] var1 = new int[]{4, 1, 4};
      String var2 = BaseScreen.getString(599) + BaseScreen.a(604, var1);
      this.c(var2, "Kích hoạt");
   }

   public final void aH() {
      this.o.c((byte)1);
      this.o.d((byte)0);
      this.bw();
      int[] var1 = new int[]{2, 1, 2};
      String var2 = BaseScreen.getString(600) + BaseScreen.a(604, var1);
      this.c(var2, "Mua sắm tất trúng cầu");
   }

   public final void aI() {
      this.f = 0;
      this.o.c((byte)4);
      this.o.d((byte)0);
      this.bw();
      int[] var1 = new int[]{2, 1, 2};
      String var2 = BaseScreen.getString(603) + BaseScreen.a(604, var1);
      this.c(var2, "Mua sắm huy hiệu");
   }

   public final void aJ() {
      this.f = 0;
      this.o.c((byte)2);
      this.o.d((byte)0);
      this.bw();
      int[] var1 = new int[]{2, 1, 2};
      String var2 = BaseScreen.getString(601) + BaseScreen.a(604, var1);
      this.c(var2, "Mua sắm kim tiền");
   }

   private void bw() {
      this.p.openUI("/data/ui/smsInfo.ui", 257, this);
      if (this.o instanceof WorldManager) {
         this.p.activeView.getComponent(6).setVisible(true);
         this.p.activeView.getComponent(7).setVisible(true);
         this.p.activeView.getComponent(10).setVisible(false);
         this.p.activeView.getComponent(11).setVisible(false);
      } else {
         this.p.activeView.getComponent(6).setVisible(false);
         this.p.activeView.getComponent(7).setVisible(false);
         this.p.activeView.getComponent(10).setVisible(true);
         this.p.activeView.getComponent(11).setVisible(true);
         this.p.activeView.getComponent(10).getStyle().text = "Xác nhận";
         this.p.activeView.getComponent(11).getStyle().text = "Phản hồi";
      }
   }

   private void c(String var1, String var2) {
      this.p.activeView.getComponent(8).getStyle().text = var1;
      this.p.activeView.getComponent(5).getStyle().text = var2;
   }

   private void bx() {
      this.p.closeUI("/data/ui/smsInfo.ui");
   }

   public final void aK() {
      if (!this.p.isUIOpen("/data/ui/smsTip.ui")) {
         this.p.openUI("/data/ui/smsTip.ui", 257, this);
      }

      for (int var1 = 0; var1 < 3; var1++) {
         this.p.activeView.getComponent(var1 + 6).setVisible(false);
      }

      this.g = true;
   }

   public final void d(String var1) {
      this.g = true;
      this.p.activeView.getComponent(5).getStyle().text = var1;
   }

   public final void aL() {
      this.p.closeUI("/data/ui/smsTip.ui");
   }

   public final void aM() {
      switch (this.o.N()) {
         case 0:
            if (!this.o.isKeyPressed(16400) && !this.o.isKeyPressed(32832)) {
               if (this.o.isKeyPressed(196640)) {
                  if (this.o.O() > 1) {
                     this.o.d((byte)1);
                     return;
                  }

                  this.o.g(1);
                  return;
               }

               if (this.o.isKeyPressed(786432)) {
                  this.bx();
                  this.o.d((byte)5);
                  this.o.setScreenMode(this.o.previousScreenMode);
                  return;
               }
            }
            break;
         case 1:
            if (this.o.isKeyPressed(196640)) {
               this.o.h(1);
               return;
            }

            if (this.o.isKeyPressed(262144)) {
               this.o.h(2);
               return;
            }
            break;
         case 2:
            boolean var1 = false;
            if (this.o.screenMode != 100) {
               var1 = true;
            } else {
               if (this.x >= this.O.length && this.ax()) {
                  var1 = true;
               } else if (this.ax()) {
                  this.b(this.O[this.x]);
                  this.x++;
               }

               this.f();
            }

            if (var1) {
               this.x = 0;
               if (this.o.M()) {
                  this.bx();
                  this.aL();
                  this.o.setScreenMode(this.o.previousScreenMode);
               } else {
                  this.o.d((byte)5);
               }

               this.f = 0;
               return;
            }
            break;
         case 3:
            if (this.o.isKeyPressed(393216)) {
               this.o.h(1);
            }
      }
   }

   public final void a(byte var1, int var2, int var3) {
      this.c = 0;
      this.R = var1;
      this.S = (byte)var2;
      switch (var2) {
         case 0:
            this.p.openUI("/data/ui/wharf1.ui", 257, this);
            this.p.activeView.getComponent(8).getStyle().text = BaseScreen.getString(var3);

            for (int var5 = 0; var5 < this.P[var1].length; var5++) {
               this.p.activeView.getComponent(var5 + 5).getStyle().text = BaseScreen.getString(this.P[var1][var5]);
            }
            break;
         case 1:
            this.p.openUI("/data/ui/wharf2.ui", 257, this);
            this.p.activeView.getComponent(10).getStyle().text = BaseScreen.getString(var3);

            for (int var4 = 0; var4 < this.P[var1].length; var4++) {
               this.p.activeView.getComponent(var4 + 5).getStyle().text = BaseScreen.getString(this.P[var1][var4]);
            }
      }

      this.p.activeView.getComponent(5 + this.P[var1].length).getStyle().text = "Không ra hàng";
   }

   public final void aN() {
      if (this.o.isKeyPressed(4100) && !this.j()) {
         this.p.activeView.navigateSelection(0);
      } else if (this.o.isKeyPressed(8448) && !this.j()) {
         this.p.activeView.navigateSelection(1);
      } else if (this.o.isKeyPressed(196640) && !this.j()) {
         if (this.c == this.Q[this.R].length / 9) {
            switch (this.S) {
               case 0:
                  this.p.closeUI("/data/ui/wharf1.ui");
                  break;
               case 1:
                  this.p.closeUI("/data/ui/wharf2.ui");
            }

            this.o.setScreenMode((byte)0);
            if (WorldManager.u != -1 && WorldManager.a().d[WorldManager.u].u() == 0) {
               WorldManager.a().a(WorldManager.a().d[WorldManager.u].posX, WorldManager.a().d[WorldManager.u].posY - 40, WorldManager.a().d[WorldManager.u]);
            }
         } else {
            label54: {
               short var10001 = this.Q[this.R][this.c * 9 + 6];
               short[] var10002 = this.Q[this.R];
               if (WorldManager.a().M.b[WorldManager.a(var10001, var10002[this.c * 9 + 7])] != null) {
                  var10001 = this.Q[this.R][this.c * 9 + 6];
                  var10002 = this.Q[this.R];
                  if (WorldManager.a().M.b[WorldManager.a(var10001, var10002[this.c * 9 + 7])][this.Q[this.R][this.c * 9 + 8]] == 3) {
                     WorldManager.a().f = this.Q[this.R][this.c * 9];
                     WorldManager.a().g = this.Q[this.R][this.c * 9 + 1];
                     WorldManager.a().h = this.Q[this.R][this.c * 9 + 2];
                     WorldManager.a().i = this.Q[this.R][this.c * 9 + 3];
                     WorldManager.w = (byte)this.Q[this.R][this.c * 9 + 4];
                     WorldManager.a().j = -1;
                     this.o.setScreenMode((byte)29);
                     switch (this.S) {
                        case 0:
                           this.p.closeUI("/data/ui/wharf1.ui");
                           break label54;
                        case 1:
                           this.p.closeUI("/data/ui/wharf2.ui");
                        default:
                           break label54;
                     }
                  }
               }

               this.b("Đường thủy chưa mở");
            }
         }
      } else if (this.o.isKeyPressed(262144) && !this.j()) {
         if (WorldManager.u != -1 && WorldManager.a().d[WorldManager.u].u() == 0) {
            WorldManager.a().a(WorldManager.a().d[WorldManager.u].posX, WorldManager.a().d[WorldManager.u].posY - 40, WorldManager.a().d[WorldManager.u]);
         }

         switch (this.S) {
            case 0:
               this.p.closeUI("/data/ui/wharf1.ui");
               break;
            case 1:
               this.p.closeUI("/data/ui/wharf2.ui");
         }

         this.o.setScreenMode((byte)0);
      }

      this.f();
   }

   public final void aO() {
      this.b = 0;
      this.p.openUI("/data/ui/shopbuy.ui", 257, this);
      this.b = 0;
      this.f = 0;
      ((MenuWidget)this.p.activeView.getComponent(0)).a.itemCount = 1;
      ((MenuWidget)this.p.activeView.getComponent(0)).a.a(0);
      this.p.activeView.getComponent(41).setVisible(false);
      this.p.activeView.getComponent(43).setVisible(false);
      this.p.activeView.getComponent(5).getStyle().text = "Mua";
      this.p.activeView.getComponent(57).setVisible(true);
      this.p.activeView.getComponent(58).setVisible(true);
      this.p.activeView.getComponent(57).getStyle().text = "Mua sắm";
      this.p.activeView.getComponent(58).getStyle().text = "Phản hồi";
      this.p.activeView.getComponent(39).setVisible(false);
      this.p.activeView.getComponent(40).setVisible(false);
      ScriptEngine var1 = this;
      this.w = ((MenuWidget)this.p.activeView.getComponent(0)).a.firstVisibleIndex;
      var1.h = ((MenuWidget)var1.p.activeView.getComponent(0)).a.selectedIndex;
      if (var1.p.activeView.getComponent(51).getStyle().m == null) {
         var1.p.activeView.getComponent(51).getStyle().m = new SpriteWidget();
         var1.p.activeView.getComponent(51).getStyle().m.a(0);
         var1.p.activeView.getComponent(51).getStyle().m.a = 2;
         var1.p.activeView.getComponent(51).getStyle().m.a(258, false, (byte)-1);
      }

      var1.p.activeView.getComponent(51).getStyle().m.a(GameDatabase.gameDatabase[5][0][1]);
      var1.p.activeView.getComponent(14).getStyle().text = BaseScreen.getString(GameDatabase.gameDatabase[5][0][0]);
      var1.p.activeView.getComponent(15).getStyle().text = "5000";
      var1.p.activeView.getComponent(45).getStyle().m.a(84);
      var1.p.activeView.getComponent(56).getStyle().text = "Ấp trứng ra sủng vật";
      var1.p.activeView.getComponent(44).getStyle().text = "" + var1.q.getGold();
      var1.p.activeView.getComponent(38).setY(102 + var1.h * 84 / GameDatabase.gameDatabase[5].length, var1.p.activeView.getRoot());
   }

   private void by() {
      this.p.closeUI("/data/ui/shopbuy.ui");
   }

   public final int aP() {
      if (this.o.isKeyPressed(196640)) {
         if (this.f == 0) {
            if (this.q.hasGold(5000)) {
               if (this.q.k(0)) {
                  this.E();
                  this.a("Đã có trứng sủng vật, không cần mua sắm", "Nhấn nút 5 để tiếp tục");
                  this.f = 2;
               } else {
                  this.q.e(0, -1);
                  this.E();
                  this.a("Đã thành công mua sắm #2 trứng sủng vật", "Nhấn nút 5 để tiếp tục");
                  this.f = 2;
               }
            } else {
               this.E();
               this.a("Kim tiền chưa đủ", "Nhấn nút 5 để tiếp tục");
               this.f = 1;
            }
         } else if (this.f > 0) {
            this.F();
            if (this.f == 1) {
               this.o.setScreenMode((byte)102);
            } else if (this.f == 2) {
               OverworldScreen.m = 0;
               this.by();
               this.o.setScreenMode((byte)0);
            }
         }
      } else if (this.o.isKeyPressed(262144) && this.f == 0) {
         OverworldScreen.m = 1;
         this.by();
         this.o.setScreenMode((byte)0);
      }

      return -1;
   }

   public final void aQ() {
      this.p.openUI("/data/ui/wharf2.ui", 257, this);
      ((MenuWidget)this.p.activeView.getComponent(0)).a.selectedIndex = this.d;
      this.f = 0;
      this.p.activeView.getComponent(10).getStyle().text = "Tiện lợi điếm";
      this.p.activeView.getComponent(12).getStyle().text = "Tiến vào";

      for (int var1 = 0; var1 < this.T.length; var1++) {
         this.p.activeView.getComponent(var1 + 5).getStyle().text = this.T[var1];
      }
   }

   public final void aR() {
      if (this.o.isKeyPressed(4100) && !this.j()) {
         this.p.activeView.navigateSelection(0);
      } else if (this.o.isKeyPressed(8448) && !this.j()) {
         this.p.activeView.navigateSelection(1);
      } else if (this.o.isKeyPressed(196640) && !this.j()) {
         if (this.f == 0) {
            switch (this.d) {
               case 0:
                  this.p.closeUI("/data/ui/wharf2.ui");
                  this.o.setScreenMode((byte)31);
                  return;
               case 1:
               case 2:
                  WorldManager.a();
                  if (WorldManager.K) {
                     this.p.closeUI("/data/ui/wharf2.ui");
                     this.c = 0;
                     this.o.setScreenMode((byte)7);
                     return;
                  }

                  this.E();
                  this.f = 1;
                  this.a("Công năng theo đạo học sau mở ra", "Nhấn nút 5 để tiếp tục");
                  return;
               case 3:
                  this.p.closeUI("/data/ui/wharf2.ui");
                  this.o.setScreenMode((byte)32);
                  return;
               case 4:
                  this.p.closeUI("/data/ui/wharf2.ui");
                  this.o.setScreenMode((byte)0);
            }
         } else {
            this.f = 0;
            this.F();
            this.g = true;
         }
      } else {
         if (this.f == 0 && this.o.isKeyPressed(262144) && !this.j()) {
            this.p.closeUI("/data/ui/wharf2.ui");
            this.o.setScreenMode((byte)0);
         }
      }
   }

   public final void onScriptEvent(int[] var1) {
      this.z = var1;
      if (this.o instanceof WorldManager) {
         switch (((WorldManager)this.o).screenMode) {
            case 0:
               return;
            case 1:
               int[] var41 = var1;
               Object var22 = null;
               this.b = var41[0];
               return;
            case 2:
            case 26:
            case 32:
               this.d(var1);
               return;
            case 3:
               int[] var40 = var1;
               ScriptEngine var21 = this;
               if (this.f == 0) {
                  var21.b = var40[0];
                  var21.aX();
                  return;
               }

               var21.r = var40[0];
               return;
            case 4:
               return;
            case 5:
               int[] var39 = var1;
               ScriptEngine var20 = this;
               this.b = var39[1];
               var20.bm();
               return;
            case 6:
               int[] var38 = var1;
               ScriptEngine var19 = this;
               this.b = var38[0];
               if (BaseScreen.isVipUnlocked) {
                  var19.p.activeView.getComponent(14).getStyle().text = BaseScreen.getString(605 + var19.b);
                  return;
               }

               var19.p.activeView.getComponent(14).getStyle().text = BaseScreen.getString(606 + var19.b);
               return;
            case 7:
               this.c(var1);
               return;
            case 8:
               int[] var37 = var1;
               ScriptEngine var18 = this;
               if (var37[0] >= 0) {
                  var18.c = var37[0];
               }

               if (var37[1] >= 0) {
                  var18.b = var37[1];
               }

               var18.bj();
               return;
            case 9:
               int[] var36 = var1;
               Object var17 = null;
               this.c = var36[1];
               return;
            case 10:
               int[] var35 = var1;
               ScriptEngine var16 = this;
               this.b = var35[1];
               switch (var16.b) {
                  case 0:
                     var16.c = var35[0];
                     return;
                  case 1:
                     var16.r = var35[0];
                  default:
                     return;
               }
            case 11:
               int[] var34 = var1;
               ScriptEngine var15 = this;
               this.c = var34[0];
               var15.b = var34[1];
               return;
            case 12:
               int[] var33 = var1;
               Object var14 = null;
               this.b = var33[1];
               return;
            case 13:
               if (this.f == 0) {
                  this.b = var1[0];
                  return;
               }

               this.c = var1[0];
               return;
            case 14:
               int[] var32 = var1;
               Object var13 = null;
               this.c = var32[0];
               return;
            case 15:
               int[] var31 = var1;
               Object var12 = null;
               this.c = var31[0];
               return;
            case 16:
               int[] var30 = var1;
               Object var11 = null;
               this.b = var30[0];
               return;
            case 17:
            case 18:
            case 19:
               int[] var29 = var1;
               Object var10 = null;
               this.c = var29[0];
               return;
            case 20:
               this.c = var1[1];
               return;
            case 24:
               int[] var28 = var1;
               Object var9 = null;
               this.c = var28[0];
               return;
            case 27:
               this.d = var1[0];
            case 21:
            case 22:
            case 23:
            case 25:
            case 29:
            case 30:
            case 31:
            default:
               return;
            case 28:
               int[] var27 = var1;
               Object var8 = null;
               this.c = var27[0];
         }
      } else {
         if (this.o instanceof BattleScreen) {
            switch (((BattleScreen)this.o).screenMode) {
               case 2:
                  return;
               case 3:
                  int[] var26 = var1;
                  Object var7 = null;
                  this.e = var26[0];
                  return;
               case 4:
                  int[] var25 = var1;
                  ScriptEngine var6 = this;
                  this.b = var25[0];
                  var6.be();
                  return;
               case 5:
                  this.c(var1);
                  return;
               case 6:
                  return;
               case 7:
                  return;
               case 8:
                  return;
               case 9:
                  return;
               case 10:
                  return;
               case 11:
                  this.d(var1);
                  return;
               case 12:
                  return;
               case 13:
                  return;
               case 14:
                  return;
               case 15:
                  return;
               case 16:
                  int[] var24 = var1;
                  ScriptEngine var5 = this;
                  this.c = var24[0];
                  var5.f(var5.c);
                  return;
               case 17:
                  return;
               case 18:
                  return;
               case 19:
                  return;
               case 20:
                  int[] var23 = var1;
                  ScriptEngine var4 = this;
                  this.a = var23[1];
                  var4.a(true);
                  return;
               case 21:
                  int[] var3 = var1;
                  Object var2 = null;
                  this.b = var3[0];
               case 23:
                  this.b = var1[0];
               case 22:
            }
         }
      }
   }

   private void c(int[] var1) {
      if (this.f == 0) {
         this.b = var1[0];
         this.f(this.b);
      } else if (this.f == 1) {
         this.c = var1[0];
      } else {
         if (this.f == 2) {
            this.r = var1[0];
            switch (this.c) {
               case 0:
                  this.be();
            }
         }
      }
   }

   private void d(int[] var1) {
      if (this.f == 0) {
         this.b = var1[0];
      } else {
         this.r = var1[0];
      }
   }
}
