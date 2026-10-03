package game;

import javax.microedition.lcdui.Graphics;

public final class MenuWidget implements UIComponent {
   private int componentId;
   private int x;
   private int y;
   private int width;
   private int height;
   private int componentType;
   private byte[][] i;
   private UIStyle style;
   private int parentId;
   private byte l;
   private UISelectionConfig selection;
   private UIComponent[] children;
   public UISelectionConfig a;
   public UISelectionConfig b;
   private boolean visible = true;

   public MenuWidget() {
      this.componentId = -1;
      this.x = 0;
      this.y = 0;
      this.width = 0;
      this.height = 0;
      this.componentType = 0;
      this.style = new UIStyle();
      this.selection = null;
      this.children = new UIComponent[60];
      this.a = null;
      this.b = null;
      this.parentId = -1;
      this.i = null;
      this.l = 9;
      this.visible = true;
   }

   public final void setVisible(boolean var1) {
      this.visible = var1;
   }

   public final void a(byte[][] var1) {
      this.i = var1;
   }

   public final byte[][] l() {
      return this.i;
   }

   public final void render(Graphics var1, boolean var2, boolean var3, UIComponent var4, int[] var5) {
      if (this.visible) {
         for (int var6 = 0; var6 < this.children.length && this.children[var6] != null; var6++) {
            if (this.children[var6].getSelection() != null) {
               boolean var7 = false;
               int var8;
               if ((var8 = EngineUtils.a(var5)) > 0 && var5[var8 - 1] == this.componentId) {
                  var7 = true;
               }

               this.children[var6].getSelection().a(var1, this.children[var6].getId(), var7, var5, var3, var4);
            } else {
               this.children[var6].render(var1, var2, var3, var4, var5);
            }
         }
      }
   }

   public final void a(boolean var1, boolean var2, UIComponent var3, int[] var4) {
      for (int var5 = 0; var5 < this.children.length && this.children[var5] != null; var5++) {
         if (this.children[var5].getSelection() != null) {
            this.children[var5].getSelection().a(this.children[var5].getId(), var4, var2, var3);
         } else {
            this.children[var5].a(var1, var2, var3, var4);
         }
      }
   }

   public final int getId() {
      return this.componentId;
   }

   public final void a(int var1) {
      this.componentId = var1;
   }

   public final int getX() {
      return this.x;
   }

   public final void setX(int var1, UIComponent var2) {
      this.x = var1;
      this.updateLayout(var2);
   }

   public final int getY() {
      return this.y;
   }

   public final void setY(int var1, UIComponent var2) {
      this.y = var1;
      this.updateLayout(var2);
   }

   public final int getWidth() {
      return this.width;
   }

   public final void setWidth(int var1, UIComponent var2) {
      this.width = var1;
      this.updateLayout(var2);
   }

   public final int getHeight() {
      return this.height;
   }

   public final void setHeight(int var1, UIComponent var2) {
      this.height = var1;
      this.updateLayout(var2);
   }

   public final UISelectionConfig getSelection() {
      return this.selection;
   }

   public final void a(UISelectionConfig var1) {
      this.selection = var1;
   }

   public final UIComponent[] getChildren() {
      return this.children;
   }

   public final UIStyle getStyle() {
      return this.style;
   }

   public final void setStyle(UIStyle var1) {
      this.style = var1;
   }

   public final int getType() {
      return this.componentType;
   }

   public final void b(int var1) {
      this.componentType = var1;
   }

   public final int getParentId() {
      return this.parentId;
   }

   public final void c(int var1) {
      this.parentId = var1;
   }

   public final void updateLayout(UIComponent var1) {
      if (var1 != null) {
         if (this.parentId > 0 && this.l != 9) {
            UIComponent var2 = EngineUtils.a(var1, this.parentId);
            switch (this.l) {
               case 0:
                  this.x = var2.getX();
                  this.y = var2.getY();
                  break;
               case 1:
                  this.x = var2.getX() + (var2.getWidth() - this.width) / 2;
                  this.y = var2.getY();
                  this.width = var2.getWidth();
                  break;
               case 2:
                  this.x = var2.getX() + (var2.getWidth() - this.width);
                  this.y = var2.getY();
                  break;
               case 3:
                  this.x = var2.getX();
                  this.y = var2.getY() + (var2.getHeight() - this.height) / 2;
                  this.height = var2.getHeight();
                  break;
               case 4:
                  this.x = var2.getX();
                  this.y = var2.getY();
                  this.width = var2.getWidth();
                  this.height = var2.getHeight();
                  break;
               case 5:
                  this.x = var2.getX() + (var2.getWidth() - this.width);
                  this.y = var2.getY() + (var2.getHeight() - this.height) / 2;
                  this.height = var2.getHeight();
                  break;
               case 6:
                  this.x = var2.getX();
                  this.y = var2.getY() + (var2.getHeight() - this.height);
                  break;
               case 7:
                  this.x = var2.getX() + (var2.getWidth() - this.width) / 2;
                  this.y = var2.getY() + (var2.getHeight() - this.height);
                  this.width = var2.getWidth();
                  break;
               case 8:
                  this.x = var2.getX() + (var2.getWidth() - this.width);
                  this.y = var2.getY() + (var2.getHeight() - this.height);
            }
         }

         if (this.children != null) {
            for (int var3 = 0; var3 < this.children.length && this.children[var3] != null; var3++) {
               this.children[var3].updateLayout(var1);
            }
         }
      }
   }

   public final void release() {
      if (this.b != null) {
         this.b.a();
         this.b = null;
      }

      if (this.a != null) {
         this.a.a();
         this.a = null;
      }

      if (this.children != null) {
         for (int var1 = 0; var1 < this.children.length; var1++) {
            if (this.children[var1] != null) {
               this.children[var1].release();
            }

            this.children[var1] = null;
         }

         this.children = null;
      }

      if (this.selection != null) {
         this.selection = null;
      }

      if (this.i != null) {
         this.i = null;
      }

      if (this.style != null) {
         this.style.c();
         this.style = null;
      }
   }
}
