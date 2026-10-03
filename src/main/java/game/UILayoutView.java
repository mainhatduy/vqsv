package game;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import javax.microedition.lcdui.Graphics;
import javax.microedition.rms.RecordStore;
import javax.microedition.rms.RecordStoreException;
import javax.microedition.rms.RecordStoreNotOpenException;

public final class UILayoutView {
   private UIComponent[] components;
   private int componentCount;
   private MenuWidget root;
   private int[] d;
   private int[] e;
   private int[] f;
   private TextPainter textPainter;
   private ScriptEventListener eventListener;

   public UILayoutView(ScriptEventListener var1) {
      this.components = new UIComponent[200];
      this.componentCount = 0;
      this.d = new int[]{0, 1, 2, 3, 5, 6, 7, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25};
      this.e = null;
      this.f = null;
      this.eventListener = var1;
      this.resetLayout();
   }

   private void resetLayout() {
      this.root = new MenuWidget();
      this.root.a(0);
      this.root.c(-1);
      this.e = EngineUtils.b(100);
      this.f();
   }

   public final MenuWidget getRoot() {
      return this.root;
   }

   public final void setTextPainter(TextPainter var1) {
      this.textPainter = var1;
   }

   public final void loadLayout(String var1, int var2) {
      this.resetLayout();
      byte[] var3;
      EngineUtils.b(var3 = new byte[20000], var1);
      int[] var5 = new int[]{0};
      EngineUtils.readShort(var3, var5);
      EngineUtils.readShort(var3, var5);
      short var4 = EngineUtils.readByte(var3, var5);
      this.root.b(var4);
      var4 = EngineUtils.readShort(var3, var5);
      this.root.a(var4);
      var4 = EngineUtils.readShort(var3, var5);
      this.root.setX(var4, this.root);
      var4 = EngineUtils.readShort(var3, var5);
      this.root.setY(var4, this.root);
      var4 = EngineUtils.readShort(var3, var5);
      this.root.setWidth(var4, this.root);
      var4 = EngineUtils.readShort(var3, var5);
      this.root.setHeight(var4, this.root);
      this.components[this.componentCount] = this.root;
      this.componentCount = 1;
      this.a(var3, var5, this.root, var2, false);
      UILayoutView var6 = this;
      this.e = EngineUtils.b(50);
      var6.a(var6.root, -1);
   }

   private void a(byte[] var1, int[] var2, MenuWidget var3, int var4, boolean var5) {
      byte var6;
      if ((var6 = EngineUtils.readByte(var1, var2)) > 0) {
         byte[][] var7 = new byte[var6][4];

         for (int var8 = 0; var8 < var6; var8++) {
            var7[var8][0] = EngineUtils.readByte(var1, var2);
            var7[var8][1] = EngineUtils.readByte(var1, var2);
            var7[var8][2] = EngineUtils.readByte(var1, var2);
            var7[var8][3] = EngineUtils.readByte(var1, var2);
         }

         var3.a(var7);
      }

      int var20 = EngineUtils.readByte(var1, var2);

      for (int var24 = 0; var24 < var20; var24++) {
         var6 = EngineUtils.readByte(var1, var2);
         UISelectionConfig var9;
         (var9 = new UISelectionConfig(var6)).wrapSelection = EngineUtils.readByte(var1, var2) != 0;
         var9.visibleItemCount = EngineUtils.readShort(var1, var2);
         var9.itemCount = EngineUtils.readShort(var1, var2);
         var9.h = EngineUtils.readByte(var1, var2);
         var9.i = EngineUtils.readByte(var1, var2);

         for (int var10 = 0; var10 < var9.itemCount; var10++) {
            short var11 = EngineUtils.readShort(var1, var2);
            var9.itemIds[var10] = var11;
            short var12;
            byte[] var13 = new byte[var12 = EngineUtils.readShort(var1, var2)];

            for (int var14 = 0; var14 < var12; var14++) {
               var13[var14] = EngineUtils.readByte(var1, var2);
            }

            var9.j.addElement(EngineUtils.decodeUtf16(var13));
         }

         var9.c = new int[var9.visibleItemCount + this.d.length][][];

         for (int var31 = 0; var31 < var9.c.length; var31++) {
            var9.c[var31] = new int[0][];
         }

         short var32 = EngineUtils.readShort(var1, var2);

         for (int var36 = 0; var36 < var32; var36++) {
            short var44 = EngineUtils.readShort(var1, var2);
            short var50;
            int[][] var55 = new int[var50 = EngineUtils.readShort(var1, var2)][];

            for (int var15 = 0; var15 < var50; var15++) {
               var55[var15] = new int[5];
               var55[var15][0] = EngineUtils.readShort(var1, var2);
               var55[var15][1] = EngineUtils.readShort(var1, var2);
               var55[var15][2] = EngineUtils.readShort(var1, var2);
               var55[var15][3] = EngineUtils.readShort(var1, var2);
               var55[var15][4] = EngineUtils.readShort(var1, var2);
            }

            var9.c[var44] = var55;
         }

         if (var6 == 0) {
            var3.a = var9;
         } else {
            var3.b = var9;
         }
      }

      short var25 = EngineUtils.readShort(var1, var2);

      for (int var19 = 0; var19 < var25; var19++) {
         byte var26;
         if ((var26 = EngineUtils.readByte(var1, var2)) == 0) {
            MenuWidget var35;
            (var35 = new MenuWidget()).b(var26);
            var35.a(EngineUtils.readShort(var1, var2));
            var35.setX(EngineUtils.readShort(var1, var2), this.root);
            var35.setY(EngineUtils.readShort(var1, var2), this.root);
            var35.setWidth(EngineUtils.readShort(var1, var2), this.root);
            var35.setHeight(EngineUtils.readShort(var1, var2), this.root);
            var35.c(var3.getId());
            if (var3.a != null) {
               for (int var42 = 0; var42 < var3.a.itemIds.length; var42++) {
                  if (var3.a.itemIds[var42] == var35.getId()) {
                     var35.a(var3.a);
                     break;
                  }
               }
            }

            if (var3.b != null) {
               for (int var43 = 0; var43 < var3.b.itemIds.length; var43++) {
                  if (var3.b.itemIds[var43] == var35.getId()) {
                     var35.a(var3.b);
                     break;
                  }
               }
            }

            this.components[this.componentCount] = var35;
            this.componentCount++;
            var3.getChildren()[var19] = var35;
            this.a(var1, var2, (MenuWidget)var3.getChildren()[var19], var4, var5);
         } else if (var26 == 1) {
            ItemListWidget var34;
            (var34 = new ItemListWidget()).b(var26);
            var34.a(EngineUtils.readShort(var1, var2));
            var34.setX(EngineUtils.readShort(var1, var2), this.root);
            var34.setY(EngineUtils.readShort(var1, var2), this.root);
            var34.setWidth(EngineUtils.readShort(var1, var2), this.root);
            var34.setHeight(EngineUtils.readShort(var1, var2), this.root);
            var34.l();
            var34.textPainter = this.textPainter;
            short var41;
            byte[] var49 = new byte[var41 = EngineUtils.readShort(var1, var2)];

            for (int var53 = 0; var53 < var41; var53++) {
               var49[var53] = EngineUtils.readByte(var1, var2);
            }

            var34.getStyle().text = EngineUtils.decodeUtf16(var49);
            var34.getStyle().b = EngineUtils.readByte(var1, var2);
            var34.getStyle().c = EngineUtils.readByte(var1, var2);
            var34.getStyle().d = EngineUtils.readByte(var1, var2) != 0;
            var34.getStyle().e = EngineUtils.readInt(var1, var2);
            var34.getStyle().f = EngineUtils.readInt(var1, var2);
            var34.getStyle().g = EngineUtils.readInt(var1, var2);
            short var54 = EngineUtils.readShort(var1, var2);
            byte var57 = EngineUtils.readByte(var1, var2);
            if (var54 < 0) {
               var34.getStyle().i = null;
            } else {
               var34.getStyle().i = new SpriteWidget();
               var34.getStyle().i.a = var57;
               var34.getStyle().i.a(var54);
            }

            var34.getStyle().j = EngineUtils.readInt(var1, var2);
            var34.getStyle().k = EngineUtils.readInt(var1, var2);
            var34.getStyle().l = EngineUtils.readInt(var1, var2);
            short var59 = EngineUtils.readShort(var1, var2);
            var20 = EngineUtils.readByte(var1, var2);
            if (var59 < 0) {
               var34.getStyle().m = null;
            } else {
               var34.getStyle().m = new SpriteWidget();
               var34.getStyle().m.a(var59);
               var34.getStyle().m.a = (byte)var20;
            }

            var34.getStyle().h = EngineUtils.readByte(var1, var2);
            if (var34.getStyle().i != null) {
               var34.getStyle().i.a(var4, var5, var34.getStyle().h);
            }

            if (var34.getStyle().m != null) {
               var34.getStyle().m.a(var4, var5, var34.getStyle().h);
            }

            var34.c(var3.getId());
            if (var3.a != null) {
               for (int var29 = 0; var29 < var3.a.itemIds.length; var29++) {
                  if (var3.a.itemIds[var29] == var34.getId()) {
                     var34.a(var3.a);
                     break;
                  }
               }
            }

            if (var3.b != null) {
               for (int var30 = 0; var30 < var3.b.itemIds.length; var30++) {
                  if (var3.b.itemIds[var30] == var34.getId()) {
                     var34.a(var3.b);
                     break;
                  }
               }
            }

            this.components[this.componentCount] = var34;
            this.componentCount++;
            var3.getChildren()[var19] = var34;
            var34.a = EngineUtils.readByte(var1, var2);
            var34.b = EngineUtils.readByte(var1, var2);
         } else if (var26 == 2) {
            MessageBox var33;
            (var33 = new MessageBox()).q(EngineUtils.readShort(var1, var2));
            var33.setX(EngineUtils.readShort(var1, var2), this.root);
            var33.setY(EngineUtils.readShort(var1, var2), this.root);
            var33.a((int)EngineUtils.readByte(var1, var2));
            var33.b(EngineUtils.readByte(var1, var2));
            var33.c(EngineUtils.readByte(var1, var2));
            var33.d(EngineUtils.readByte(var1, var2));
            var33.e(EngineUtils.readByte(var1, var2));
            var33.f(EngineUtils.readByte(var1, var2));
            var33.g(EngineUtils.readByte(var1, var2));
            var33.h(EngineUtils.readByte(var1, var2));
            var33.k(EngineUtils.readByte(var1, var2));
            var33.l(EngineUtils.readByte(var1, var2));
            var33.m(EngineUtils.readByte(var1, var2));
            var33.n(EngineUtils.readByte(var1, var2));
            var33.o(EngineUtils.readByte(var1, var2));
            var33.p(EngineUtils.readByte(var1, var2));
            var33.i(EngineUtils.readByte(var1, var2));
            var33.j(EngineUtils.readByte(var1, var2));
            var33.a = EngineUtils.readInt(var1, var2);
            short var37 = EngineUtils.readShort(var1, var2);
            short var45 = EngineUtils.readByte(var1, var2);
            if (var37 < 0) {
               var33.b = null;
            } else {
               var33.b = new SpriteWidget();
               var33.b.a(var37);
               var33.b.a = (byte)var45;
               var33.b.a(var4, var5, (byte)var45);
            }

            var37 = EngineUtils.readShort(var1, var2);
            var45 = EngineUtils.readByte(var1, var2);
            if (var37 < 0) {
               var33.c = null;
            } else {
               var33.c = new SpriteWidget();
               var33.c.a(var37);
               var33.c.a = (byte)var45;
               var33.c.a(var4, var5, (byte)var45);
            }

            var33.r(EngineUtils.readShort(var1, var2));
            byte var51;
            if ((var51 = EngineUtils.readByte(var1, var2)) == 0) {
               var33.e = var33.n();
            } else if (var51 == 1) {
               int var10001 = var33.l();
               int rows = var33.m();
               var20 = var10001;
               UIHotspot[] var39 = new UIHotspot[var10001 * rows];

               for (int var47 = 0; var47 != var20 * rows; var47++) {
                  var39[var47] = new UIHotspot();
               }

               var33.e = var39;
               short var56 = EngineUtils.readShort(var1, var2);

               for (int var58 = 0; var58 < var56; var58++) {
                  short var22 = EngineUtils.readShort(var1, var2);
                  short var28 = EngineUtils.readShort(var1, var2);
                  byte var40 = EngineUtils.readByte(var1, var2);
                  var45 = EngineUtils.readShort(var1, var2);
                  short value51 = EngineUtils.readShort(var1, var2);
                  short var16 = EngineUtils.readShort(var1, var2);
                  short var17 = EngineUtils.readShort(var1, var2);
                  var33.e[var22] = new UIHotspot(var28, var40, var45, value51, var16, var17);
               }
            }

            var33.s(var3.getId());
            this.components[this.componentCount] = var33;
            this.componentCount++;
            var3.getChildren()[var19] = var33;
         }
      }
   }

   private Rectangle a(UIComponent var1) {
      if (var1.getType() != 0) {
         return new Rectangle(var1.getX(), var1.getY(), var1.getWidth(), var1.getHeight());
      }

      MenuWidget var2;
      if ((var2 = (MenuWidget)var1).getChildren() != null && var2.getChildren()[0] != null) {
         int var8 = this.a(var2.getChildren()[0]).x;
         int var3 = this.a(var2.getChildren()[0]).x + this.a(var2.getChildren()[0]).width;
         int var4 = this.a(var2.getChildren()[0]).y;
         int var5 = this.a(var2.getChildren()[0]).y + this.a(var2.getChildren()[0]).height;

         for (int var6 = 0; var6 < var2.getChildren().length && var2.getChildren()[var6] != null; var6++) {
            Rectangle var7 = this.a(var2.getChildren()[var6]);
            if (var8 > var7.x) {
               var8 = var7.x;
            }

            if (var3 < var7.x + var7.width) {
               var3 = var7.x + var7.width;
            }

            if (var4 > var7.y) {
               var4 = var7.y;
            }

            if (var5 < var7.y + var7.height) {
               var5 = var7.y + var7.height;
            }
         }

         return new Rectangle(var8, var4, var3 - var8, var5 - var4);
      } else {
         return new Rectangle(var1.getX(), var1.getY(), var1.getWidth(), var1.getHeight());
      }
   }

   public final void render(Graphics var1) {
      Graphics var2;
      MenuWidget var3;
      UILayoutView var4;
      Graphics var5;
      UISelectionConfig var10000;
      UILayoutView var14;
      label91: {
         var3 = this.root;
         var2 = var1;
         var14 = this;
         MenuWidget var6 = var3;
         var5 = var2;
         var4 = this;
         if ((var6 = var6).a != null || var6.b != null) {
            if (var6.a == null && var6.b != null) {
               var10000 = var6.b;
               break label91;
            }

            if (var6.a != null && var6.b == null) {
               var10000 = var6.a;
               break label91;
            }
         }

         var10000 = null;
      }

      UISelectionConfig var20 = var10000;
      if (var10000 != null && var20.visibleItemCount < var20.itemCount) {
         UILayoutView var7;
         UILayoutView var25 = var7 = var4;
         UISelectionConfig var15 = var20;
         UILayoutView var21 = var25;
         int var8 = 0;
         int var9 = 0;
         int var10 = 0;
         int var11 = 0;
         if (var15.itemIds[0] != -1) {
            Rectangle var12;
            var8 = (var12 = var21.a(var7.getComponent(var15.itemIds[0]))).x;
            var9 = var12.x + var12.width;
            var10 = var12.y;
            var11 = var12.y + var12.height;

            for (int var13 = 1; var13 != var15.visibleItemCount; var13++) {
               var12 = var21.a(var7.getComponent(var15.itemIds[var13]));
               if (var8 > var12.x) {
                  var8 = var12.x;
               }

               if (var9 < var12.x + var12.width) {
                  var9 = var12.x + var12.width;
               }

               if (var10 > var12.y) {
                  var10 = var12.y;
               }

               if (var11 < var12.y + var12.height) {
                  var11 = var12.y + var12.height;
               }
            }
         }

         new Rectangle(var8, var10, var9 - var8, var11 - var10);
         Rectangle var16 = new Rectangle();
         Rectangle var22 = new Rectangle();
         var5.setColor(255, 255, 255);
         var5.fillRect(var16.x, var16.y, var16.width, var16.height);
         var5.setColor(245, 222, 179);
         var5.drawRect(var16.x, var16.y, var16.width, var16.height);
         var5.setColor(95, 158, 160);
         var5.fillRect(var22.x, var22.y, var22.width, var22.height);
      }

      for (int var17 = 0; var17 < var3.getChildren().length && var3.getChildren()[var17] != null; var17++) {
         if (var3.getChildren()[var17].getSelection() != null) {
            boolean var18 = false;
            int var23;
            if ((var23 = EngineUtils.a(var14.f)) > 0 && var14.f[var23 - 1] == var3.getId()) {
               var18 = true;
            }

            var3.getChildren()[var17].getSelection().a(var2, var3.getChildren()[var17].getId(), var18, var14.f, true, var14.root);
         } else {
            var3.getChildren()[var17].render(var2, false, true, var14.root, var14.f);
         }
      }
   }

   public final void update() {
      MenuWidget var2 = this.root;
      UILayoutView var1 = this;

      for (int var3 = 0; var3 < var2.getChildren().length && var2.getChildren()[var3] != null; var3++) {
         if (var2.getChildren()[var3].getSelection() != null) {
            var2.getChildren()[var3].getSelection().a(var2.getChildren()[var3].getId(), var1.f, true, var1.root);
         } else {
            var2.getChildren()[var3].a(false, true, var1.root, var1.f);
         }
      }
   }

   public final UIComponent getComponent(int var1) {
      return EngineUtils.a(this.root, var1);
   }

   public final boolean navigateSelection(int var1) {
      int var5 = 0;
      int[] var4 = this.e;
      UIComponent var3 = this.root;

      UIComponent var20;
      while (true) {
         int var2;
         if ((var2 = EngineUtils.a(var4)) == 0) {
            var20 = var3;
            break;
         }

         if (var5 == var2 - 1) {
            var20 = var3.getChildren()[var4[var5]];
            break;
         }

         var20 = var3.getChildren()[var4[var5]];
         var5++;
         var4 = var4;
         var3 = var20;
      }

      UIComponent var12 = var20;
      boolean var10;
      if (var20.getType() == 2) {
         MessageBox var15 = (MessageBox)var12;
         int var13 = var1;
         UILayoutView var9 = this;
         boolean var17 = false;
         switch (var13) {
            case 0:
               var17 = var15.a((byte)0);
               int[] var26 = new int[]{-1, -1, 0};
               int[] var32 = new int[]{-1, -1, -1, -1};
               var9.eventListener.onScriptEvent(var26);
               break;
            case 1:
               var17 = var15.a((byte)1);
               int[] var25 = new int[]{-1, -1, 1};
               int[] var31 = new int[]{-1, -1, -1, -1};
               var9.eventListener.onScriptEvent(var25);
               break;
            case 2:
               var17 = var15.a((byte)2);
               int[] var24 = new int[]{-1, -1, 2};
               int[] var30 = new int[]{-1, -1, -1, -1};
               var9.eventListener.onScriptEvent(var24);
               break;
            case 3:
               var17 = var15.a((byte)3);
               int[] var23 = new int[]{-1, -1, 3};
               int[] var29 = new int[]{-1, -1, -1, -1};
               var9.eventListener.onScriptEvent(var23);
            case 4:
            case 6:
            default:
               break;
            case 5:
               int[] var22 = new int[]{-1, -1, 4};
               int[] var28 = new int[]{-1, -1, -1, -1};
               var9.eventListener.onScriptEvent(var22);
               var17 = true;
               break;
            case 7:
               if (var17 = var9.e()) {
                  var15.d = false;
                  int[] var10001 = new int[]{-1, -1, 7};
                  int[] var10002 = new int[]{-1, -1, -1, -1};
                  var9.eventListener.onScriptEvent(var10001);
               } else {
                  int[] var21 = new int[]{-1, -1, 5};
                  int[] var27 = new int[]{-1, -1, -1, -1};
                  var9.eventListener.onScriptEvent(var21);
               }
         }

         var10 = var17;
      } else {
         MenuWidget menu = (MenuWidget)var12;
         int var14 = var1;
         UILayoutView var11 = this;
         boolean var18 = false;
         boolean var19 = false;
         byte[][] var6;
         if (menu.l() != null) {
            var6 = menu.l();
         } else {
            var6 = new byte[][]{{0, 0, 1, -1}, {1, 1, 1, -1}, {2, 2, 1, -1}, {3, 3, 1, -1}, {5, 4, -1, -1}, {7, 5, -1, -1}};
         }

         int[] var7 = new int[3];
         if (menu.b != null) {
            var7[1] = menu.b.selectedIndex;
         } else {
            var7[1] = -1;
         }

         if (menu.a != null) {
            var7[0] = menu.a.selectedIndex;
         } else {
            var7[0] = -1;
         }

         for (int var8 = 0; var8 < var6.length; var8++) {
            if (var6[var8][0] == var14) {
               var19 = true;
               var18 = false;
               switch (var6[var8][1]) {
                  case 0:
                     var7[2] = 0;
                     if (menu.a == null) {
                        int[] var44 = new int[]{-1, -1, -1, -1};
                        var11.eventListener.onScriptEvent(var7);
                        break;
                     }

                     if (var6[var8][3] != -1 && menu.a.selectedIndex % (var6[var8][3] + 1) == 0) {
                        menu.a.a(var6[var8][3], var11.root);
                     } else {
                        menu.a.b(var6[var8][2], var11.root);
                     }

                     var18 = true;
                     var7[0] = menu.a.selectedIndex;
                     int[] var43 = new int[]{-1, -1, -1, -1};
                     var11.eventListener.onScriptEvent(var7);
                     break;
                  case 1:
                     var7[2] = 1;
                     if (menu.a == null) {
                        int[] var42 = new int[]{-1, -1, -1, -1};
                        var11.eventListener.onScriptEvent(var7);
                        break;
                     }

                     if (var6[var8][3] != -1 && (menu.a.selectedIndex + 1) % (var6[var8][3] + 1) == 0) {
                        menu.a.b(var6[var8][3], var11.root);
                     } else {
                        menu.a.a(var6[var8][2], var11.root);
                     }

                     var18 = true;
                     var7[0] = menu.a.selectedIndex;
                     int[] var41 = new int[]{-1, -1, -1, -1};
                     var11.eventListener.onScriptEvent(var7);
                     break;
                  case 2:
                     var7[2] = 2;
                     if (menu.b == null) {
                        int[] var40 = new int[]{-1, -1, -1, -1};
                        var11.eventListener.onScriptEvent(var7);
                        break;
                     }

                     if (var6[var8][3] != -1 && menu.b.selectedIndex % (var6[var8][3] + 1) == 0) {
                        menu.b.a(var6[var8][3], var11.root);
                     } else {
                        menu.b.b(var6[var8][2], var11.root);
                     }

                     var18 = true;
                     var7[1] = menu.b.selectedIndex;
                     int[] var39 = new int[]{-1, -1, -1, -1};
                     var11.eventListener.onScriptEvent(var7);
                     break;
                  case 3:
                     var7[2] = 3;
                     if (menu.b == null) {
                        int[] var38 = new int[]{-1, -1, -1, -1};
                        var11.eventListener.onScriptEvent(var7);
                        break;
                     }

                     if (var6[var8][3] != -1 && (menu.b.selectedIndex + 1) % (var6[var8][3] + 1) == 0) {
                        menu.b.b(var6[var8][3], var11.root);
                     } else {
                        menu.b.a(var6[var8][2], var11.root);
                     }

                     var18 = true;
                     var7[1] = menu.b.selectedIndex;
                     int[] var37 = new int[]{-1, -1, -1, -1};
                     var11.eventListener.onScriptEvent(var7);
                     break;
                  case 4:
                     if (menu.b != null) {
                        if (menu.b.selectedIndex >= EngineUtils.a(menu.b.itemIds)) {
                           var18 = var11.a(menu, menu.b.itemIds[menu.b.selectedIndex - menu.b.firstVisibleIndex]);
                        } else {
                           var18 = var11.a(menu, menu.b.itemIds[menu.b.selectedIndex]);
                        }
                     }

                     if (!var18 && menu.a != null) {
                        if (menu.a.selectedIndex >= EngineUtils.a(menu.a.itemIds)) {
                           var18 = var11.a(menu, menu.a.itemIds[menu.a.selectedIndex - menu.a.firstVisibleIndex]);
                        } else {
                           var18 = var11.a(menu, menu.a.itemIds[menu.a.selectedIndex]);
                        }
                     }

                     if (var18) {
                        var7[2] = 6;
                        int[] var35 = new int[]{-1, -1, -1, -1};
                        var11.eventListener.onScriptEvent(var7);
                     } else {
                        var7[2] = 4;
                        int[] var36 = new int[]{-1, -1, -1, -1};
                        var11.eventListener.onScriptEvent(var7);
                     }
                     break;
                  case 5:
                     if (var18 = var11.e()) {
                        var7[2] = 7;
                        int[] var33 = new int[]{-1, -1, -1, -1};
                        var11.eventListener.onScriptEvent(var7);
                     } else {
                        var7[2] = 5;
                        int[] var34 = new int[]{-1, -1, -1, -1};
                        var11.eventListener.onScriptEvent(var7);
                     }
               }

               if (var18) {
                  break;
               }
            }
         }

         if (!var19) {
            switch (var14) {
               case 14:
               case 15:
               case 16:
               case 17:
               case 18:
               case 19:
               case 20:
               case 21:
               case 22:
               case 23:
                  var7[2] = var14 - 6;
                  int[] var45 = new int[]{-1, -1, -1, -1};
                  var11.eventListener.onScriptEvent(var7);
            }
         }

         var10 = var18;
      }

      return var10;
   }

   private boolean e() {
      boolean var1 = false;
      int var2 = -1;

      for (int var3 = EngineUtils.a(this.f) - 2; var3 >= 0; var3--) {
         UIComponent var4;
         if (((MenuWidget)(var4 = EngineUtils.a(this.root, this.f[var3]))).a != null || ((MenuWidget)var4).b != null) {
            var1 = true;
            var2 = var4.getId();
            break;
         }
      }

      if (var1) {
         this.e = this.c(var2);
         if (this.e == null) {
            this.e = EngineUtils.b(50);
         }

         this.f();
      }

      return var1;
   }

   private boolean a(UIComponent var1, int var2) {
      if (var1.getType() == 1) {
         return false;
      }

      int var3;
      if ((var3 = this.a(var1, var2, true)) == -1) {
         return false;
      }

      this.e = this.c(var3);
      if (this.e == null) {
         this.e = EngineUtils.b(50);
      }

      this.f();
      return true;
   }

   private void f() {
      this.f = EngineUtils.b(50);
      UIComponent var1 = this.root;
      int var2 = 0;
      var2++;
      this.f[0] = var1.getId();

      for (int var3 = 0; var3 < this.e.length && this.e[var3] != -1; var3++) {
         var1 = var1.getChildren()[this.e[var3]];
         this.f[var2++] = var1.getId();
      }
   }

   private int a(UIComponent var1, int var2, boolean var3) {
      if (var1.getType() == 2 && var2 == -1) {
         if (var3) {
            ((MessageBox)var1).d = true;
         }

         return var1.getId();
      } else {
         if ((((MenuWidget)var1).a != null || ((MenuWidget)var1).b != null) && var2 == -1) {
            return var1.getId();
         }

         for (int var4 = 0; var4 < var1.getChildren().length && var1.getChildren()[var4] != null; var4++) {
            int var5;
            if (var1.getChildren()[var4].getType() != 1
               && (var2 == -1 || var1.getChildren()[var4].getId() == var2)
               && (var5 = this.a(var1.getChildren()[var4], -1, var3)) != -1) {
               return var5;
            }
         }

         return -1;
      }
   }

   private int[] c(int var1) {
      int[] var2 = EngineUtils.b(50);

      for (UIComponent var5 = EngineUtils.a(this.root, var1); var5.getParentId() != -1; var5 = EngineUtils.a(this.root, var5.getParentId())) {
         UIComponent var3 = EngineUtils.a(this.root, var5.getParentId());

         for (int var4 = 0; var4 < var3.getChildren().length && var3.getChildren()[var4] != null; var4++) {
            if (var3.getChildren()[var4].getId() == var5.getId()) {
               EngineUtils.b(var2, var4);
               break;
            }
         }
      }

      return var2;
   }

   public final void release() {
      this.root.release();
      this.d = null;
      this.e = null;
      this.f = null;
      this.eventListener = null;
      this.components = null;
   }

   public UILayoutView() {
   }

   private static RecordStore a(String var0, String var1, String var2, String var3, int var4) {
      RecordStore var5 = null;

      try {
         String var10 = var3;
         String var9 = var2;
         String var8 = var1;
         String var7 = var0;
         if (var9 == null || var9.length() == 0) {
            var9 = "00";
         }

         if (var10 != null) {
            var10.length();
         }

         StringBuffer var11;
         (var11 = new StringBuffer()).append("dcn").append(var7).append(var8).append(var9).append(var4);
         var5 = RecordStore.openRecordStore(var11.toString(), true, 1, true);
      } catch (Exception var6) {
         var6.printStackTrace();
      }

      return var5;
   }

   public static void a(CarrierHelper var0, String var1, String var2, String var3, String var4, int var5) {
      RecordStore var19;
      if ((var19 = a(var1, var2, var3, var4, var5)) != null) {
         try {
            ByteArrayOutputStream var20 = new ByteArrayOutputStream();
            DataOutputStream var21;
            (var21 = new DataOutputStream(var20)).writeInt(var0.i());
            if (var0.i() < var5) {
               var21.writeInt(var0.d());
               var21.writeUTF(var0.c());
               var21.writeUTF(var0.b());
               var21.writeUTF(var0.g());
               var21.writeInt(var0.a());
               var21.writeUTF(var0.f());
               var21.writeUTF(var0.e());
               var21.writeBoolean(var0.h());
               var21.writeInt(var0.j());
               var21.writeLong(var0.k());
            }

            byte[] var18 = var20.toByteArray();
            if (var19.getNumRecords() == 0) {
               var19.addRecord(var18, 0, var18.length);
            } else {
               var19.setRecord(1, var18, 0, var18.length);
            }

            return;
         } catch (Exception var16) {
            var16.printStackTrace();
         } finally {
            try {
               var19.closeRecordStore();
            } catch (RecordStoreException var15) {
               var15.printStackTrace();
            }
         }
      }
   }
}
