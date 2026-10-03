package game;

import java.util.Vector;
import javax.microedition.lcdui.Font;
import javax.microedition.lcdui.Graphics;

public final class UISelectionConfig {
   public int itemCount;
   public int[] itemIds;
   public int[][][] c;
   public int visibleItemCount;
   private Rectangle[] k;
   public int firstVisibleIndex;
   public int selectedIndex;
   public boolean wrapSelection;
   public int h;
   public int i;
   private int l;
   private int m;
   private Vector n;
   public Vector j;

   public UISelectionConfig(int var1) {
      this.h = 0;
      this.i = 0;
      this.l = -1;
      this.m = -1;
      this.n = new Vector();
      this.j = new Vector();
      this.itemCount = 0;
      this.itemIds = null;
      this.itemIds = EngineUtils.b(50);
      this.visibleItemCount = 0;
      this.k = new Rectangle[20];
      this.firstVisibleIndex = 0;
      this.selectedIndex = 0;
      this.wrapSelection = true;
      this.l = -1;
   }

   public final void a(int var1, UIComponent var2) {
      if (this.itemCount <= 1) {
         this.selectedIndex = 0;
         this.firstVisibleIndex = 0;
      } else {
         if (this.wrapSelection) {
            if (this.h == 0) {
               this.selectedIndex += var1;
               if (this.selectedIndex >= this.itemCount) {
                  this.selectedIndex = this.selectedIndex % this.itemCount;
                  if (this.selectedIndex >= this.firstVisibleIndex + this.visibleItemCount || this.selectedIndex < this.firstVisibleIndex) {
                     this.firstVisibleIndex = this.selectedIndex;
                  }

                  this.a(var2);
                  return;
               }

               if (this.selectedIndex >= this.firstVisibleIndex + this.visibleItemCount) {
                  this.firstVisibleIndex += var1;
                  if (this.firstVisibleIndex + this.visibleItemCount >= this.itemCount) {
                     this.firstVisibleIndex = this.itemCount - this.visibleItemCount;
                  }

                  this.a(var2);
                  return;
               }
            } else if (this.h == 1) {
               this.selectedIndex += var1;
               if (this.selectedIndex >= this.itemCount) {
                  this.selectedIndex = this.selectedIndex % this.itemCount;
               }

               this.firstVisibleIndex = this.selectedIndex - this.i < 0 ? this.itemCount + (this.selectedIndex - this.i) : this.selectedIndex - this.i;
               this.a(var2);
               return;
            }
         } else {
            this.selectedIndex += var1;
            if (this.selectedIndex >= this.itemCount) {
               this.selectedIndex = this.itemCount - 1;
               if (this.itemCount >= this.visibleItemCount) {
                  this.firstVisibleIndex = this.itemCount - this.visibleItemCount;
               }

               this.a(var2);
               return;
            }

            if (this.selectedIndex >= this.firstVisibleIndex + this.visibleItemCount) {
               this.firstVisibleIndex += var1;
               this.a(var2);
            }
         }
      }
   }

   public final void b(int var1, UIComponent var2) {
      if (this.itemCount <= 1) {
         this.selectedIndex = 0;
         this.firstVisibleIndex = 0;
      } else {
         if (this.wrapSelection) {
            if (this.h == 0) {
               this.selectedIndex -= var1;
               if (this.selectedIndex < 0) {
                  this.selectedIndex = this.itemCount + this.selectedIndex % this.itemCount;
                  if (this.selectedIndex >= this.firstVisibleIndex + this.visibleItemCount || this.selectedIndex < this.firstVisibleIndex) {
                     this.firstVisibleIndex = this.itemCount - this.visibleItemCount - (this.itemCount - this.selectedIndex - 1);
                  }

                  this.a(var2);
                  return;
               }

               if (this.selectedIndex < this.firstVisibleIndex) {
                  this.firstVisibleIndex = this.selectedIndex;
                  this.a(var2);
                  return;
               }
            } else if (this.h == 1) {
               this.selectedIndex -= var1;
               if (this.selectedIndex < 0) {
                  this.selectedIndex = this.itemCount + this.selectedIndex % this.itemCount;
               }

               this.firstVisibleIndex = this.selectedIndex - this.i < 0 ? this.itemCount + (this.selectedIndex - this.i) : this.selectedIndex - this.i;
               this.a(var2);
               return;
            }
         } else {
            this.selectedIndex -= var1;
            if (this.selectedIndex < 0) {
               this.selectedIndex = 0;
               this.firstVisibleIndex = 0;
               this.a(var2);
               return;
            }

            if (this.selectedIndex < this.firstVisibleIndex) {
               this.firstVisibleIndex -= var1;
               this.a(var2);
            }
         }
      }
   }

   private void a(UIComponent var1) {
      if (this.l == 1) {
         if (this.m == 1) {
            for (int var5 = 0; var5 < this.visibleItemCount; var5++) {
               int var6 = ((int[])null).length > ((String[])this.n.elementAt((this.firstVisibleIndex + var5) % this.itemCount)).length
                  ? ((String[])this.n.elementAt((this.firstVisibleIndex + var5) % this.itemCount)).length
                  : ((int[])null).length;

               for (int var7 = 0; var7 < var6; var7++) {
                  EngineUtils.a(var1, ((int[])null)[var7]).getStyle().text = ((String[])this.n.elementAt((this.firstVisibleIndex + var5) % this.itemCount))[var7];
               }
            }

            return;
         }

         if (this.m == 2) {
            for (int var2 = 0; var2 < this.visibleItemCount; var2++) {
               int var3 = ((int[])null).length > ((UIStyle[])this.n.elementAt((this.firstVisibleIndex + var2) % this.itemCount)).length
                  ? ((UIStyle[])this.n.elementAt((this.firstVisibleIndex + var2) % this.itemCount)).length
                  : ((int[])null).length;

               for (int var4 = 0; var4 < var3; var4++) {
                  EngineUtils.a(var1, ((int[])null)[var4]).setStyle(((UIStyle[])this.n.elementAt((this.firstVisibleIndex + var2) % this.itemCount))[var4]);
               }
            }
         }
      }
   }

   public final void a(Graphics var1, int var2, boolean var3, int[] var4, boolean var5, UIComponent var6) {
      if (this.k != null) {
         this.k = new Rectangle[20];

         for (int var7 = 0; var7 < this.visibleItemCount; var7++) {
            UIComponent var8 = EngineUtils.a(var6, this.itemIds[var7]);
            Rectangle var9 = new Rectangle(var8.getX(), var8.getY(), var8.getWidth(), var8.getHeight());
            this.k[var7] = var9;
         }
      }

      int var15 = -1;

      for (int var16 = 0; var16 < this.itemIds.length && this.itemIds[var16] != -1; var16++) {
         if (this.itemIds[var16] == var2) {
            var15 = var16;
            break;
         }
      }

      int[] var17 = EngineUtils.b(50);
      if (this.l == 1) {
         int var18 = 0;

         while (var18 < this.visibleItemCount) {
            var17[var18] = var18++;
         }
      } else if (this.l == -1) {
         for (int var19 = 0; var19 < this.visibleItemCount; var19++) {
            var17[var19] = (this.firstVisibleIndex + var19) % this.itemIds.length;
         }
      }

      for (int var20 = 0; var20 < var17.length && var17[var20] != -1; var20++) {
         if (var15 == var17[var20]) {
            UIComponent var14;
            int var10 = (var14 = EngineUtils.a(var6, var2)).getX() - this.k[var20].x;
            int var11 = var14.getY() - this.k[var20].y;
            int var12 = var14.getWidth();
            int var13 = var14.getHeight();
            EngineUtils.a(var14, -var10, -var11, var6);
            var14.setWidth(this.k[var20].width, var6);
            var14.setHeight(this.k[var20].height, var6);
            if (!var3 || this.itemCount <= 0) {
               var14.render(var1, false, var5, var6, var4);
            } else if (this.l == 1) {
               if (this.selectedIndex == (this.firstVisibleIndex + var17[var20]) % this.itemCount) {
                  var14.render(var1, true, var5, var6, var4);
               } else {
                  var14.render(var1, false, var5, var6, var4);
               }
            } else if (this.selectedIndex == var17[var20]) {
               var14.render(var1, true, var5, var6, var4);
            } else {
               var14.render(var1, false, var5, var6, var4);
            }

            EngineUtils.a(var14, var10, var11, var6);
            var14.setWidth(var12, var6);
            var14.setHeight(var13, var6);
         }
      }
   }

   public final void a(int var1, int[] var2, boolean var3, UIComponent var4) {
      if (this.k != null) {
         this.k = new Rectangle[20];

         for (int var5 = 0; var5 < this.visibleItemCount; var5++) {
            UIComponent var6 = EngineUtils.a(var4, this.itemIds[var5]);
            Rectangle var7 = new Rectangle(var6.getX(), var6.getY(), var6.getWidth(), var6.getHeight());
            this.k[var5] = var7;
         }
      }

      int var13 = -1;

      for (int var14 = 0; var14 < this.itemIds.length && this.itemIds[var14] != -1; var14++) {
         if (this.itemIds[var14] == var1) {
            var13 = var14;
            break;
         }
      }

      int[] var15 = EngineUtils.b(50);
      if (this.l == 1) {
         int var16 = 0;

         while (var16 < this.visibleItemCount) {
            var15[var16] = var16++;
         }
      } else if (this.l == -1) {
         for (int var17 = 0; var17 < this.visibleItemCount; var17++) {
            var15[var17] = (this.firstVisibleIndex + var17) % this.itemIds.length;
         }
      }

      for (int var18 = 0; var18 < var15.length && var15[var18] != -1; var18++) {
         if (var13 == var15[var18]) {
            UIComponent var12;
            int var8 = (var12 = EngineUtils.a(var4, var1)).getX() - this.k[var18].x;
            int var9 = var12.getY() - this.k[var18].y;
            int var10 = var12.getWidth();
            int var11 = var12.getHeight();
            EngineUtils.a(var12, -var8, -var9, var4);
            var12.setWidth(this.k[var18].width, var4);
            var12.setHeight(this.k[var18].height, var4);
            var12.a(var3, var3, var4, var2);
            EngineUtils.a(var12, var8, var9, var4);
            var12.setWidth(var10, var4);
            var12.setHeight(var11, var4);
         }
      }
   }

   public final void a() {
      if (this.j != null) {
         this.j = null;
      }

      if (this.itemIds != null) {
         this.itemIds = null;
      }

      if (this.c != null) {
         this.c = null;
      }

      if (this.n != null) {
         this.n = null;
      }

      if (this.k != null) {
         this.k = null;
      }
   }

   public final void a(int var1) {
      if (var1 != 1 && var1 != -1) {
         this.l = -1;
      } else {
         this.l = var1;
      }
   }

   public UISelectionConfig() {
   }

   public static int a(String var0, Font var1) {
      int var2 = 0;

      for (int var4 = 0; var4 < var0.length(); var4++) {
         char var3 = var0.charAt(var4);
         var2 += var1.charWidth(var3);
      }

      return var2;
   }

   public static int[] a(Graphics var0, String var1, Font var2, int var3, int var4, int var5, int var6) {
      int var9 = 0;
      int var10 = 0;

      while (true) {
         Font var8 = var2;
         String var7 = var1;
         int var11 = 0;
         int var13 = 0;

         int var10000;
         while (true) {
            if (var13 >= var7.length()) {
               var10000 = 0;
               break;
            }

            char var12;
            if ((var12 = var7.charAt(var13)) == '\n') {
               var10000 = var13 + 1;
               break;
            }

            if ((var11 += var8.charWidth(var12)) > var3) {
               var10000 = var13;
               break;
            }

            var13++;
         }

         int var15 = var10000;
         if (var10000 == 0) {
            var0.drawString(var1, 5, var6, 0);
            return new int[]{var6, var10};
         }

         if (var1.charAt(var15 - 1) == '\n') {
            var7 = var1.substring(0, var15 - 1);
         } else {
            var7 = var1.substring(0, var15);
         }

         if (var9 >= var5 && var6 < var4) {
            var0.drawString(var7, 5, var6, 0);
            var6 += var2.getHeight();
         }

         if (var6 >= var4) {
            var10++;
         }

         var9++;
         var1 = var1.substring(var15, var1.length());
      }
   }
}
