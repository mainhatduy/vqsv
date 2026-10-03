package game;

import javax.microedition.lcdui.Graphics;

public final class ItemListWidget implements UIComponent {
   private int componentId;
   private int x;
   private int y;
   private int width;
   private int height;
   private int componentType;
   private UIStyle style;
   private int parentId;
   private byte l;
   private UISelectionConfig selection;
   public byte a = -1;
   public byte b = -1;
   TextPainter textPainter;
   private boolean visible = true;

   public ItemListWidget() {
      this.componentId = -1;
      this.x = 0;
      this.y = 0;
      this.width = 0;
      this.height = 0;
      this.componentType = 1;
      this.style = new UIStyle();
      this.parentId = -1;
      this.selection = null;
      this.l = 9;
      this.visible = true;
   }

   public final void l() {
      Rectangle var1 = new Rectangle(this.x, this.y, this.width, this.height);
      this.style.a(var1);
      this.style.a();
   }

   public final void render(Graphics var1, boolean var2, boolean var3, UIComponent var4, int[] var5) {
      if (this.visible) {
         if (this.style != null) {
            this.style.a(var1, this.x, this.y, this.width, this.height, var2, this.a, this.b, this.textPainter);
         }
      }
   }

   public final void a(boolean var1, boolean var2, UIComponent var3, int[] var4) {
      if (this.style != null) {
         var2 = var1;
         UIStyle var5 = this.style;
         if (var2) {
            if (var5.i != null) {
               var5.i.c();
               return;
            }
         } else if (var5.m != null) {
            var5.m.c();
         }
      }
   }

   public final void setVisible(boolean var1) {
      this.visible = var1;
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
      return null;
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
      if (var1 != null && this.parentId > 0 && this.l != 9) {
         UIComponent var2 = EngineUtils.a(var1, this.parentId);
         switch (this.l) {
            case 0:
               this.x = var2.getX();
               this.y = var2.getY();
               return;
            case 1:
               this.x = var2.getX() + (var2.getWidth() - this.width) / 2;
               this.y = var2.getY();
               this.width = var2.getWidth();
               break;
            case 2:
               this.x = var2.getX() + (var2.getWidth() - this.width);
               this.y = var2.getY();
               return;
            case 3:
               this.x = var2.getX();
               this.y = var2.getY() + (var2.getHeight() - this.height) / 2;
               this.height = var2.getHeight();
               return;
            case 4:
               this.x = var2.getX();
               this.y = var2.getY();
               this.width = var2.getWidth();
               this.height = var2.getHeight();
               return;
            case 5:
               this.x = var2.getX() + (var2.getWidth() - this.width);
               this.y = var2.getY() + (var2.getHeight() - this.height) / 2;
               this.height = var2.getHeight();
               return;
            case 6:
               this.x = var2.getX();
               this.y = var2.getY() + (var2.getHeight() - this.height);
               return;
            case 7:
               this.x = var2.getX() + (var2.getWidth() - this.width) / 2;
               this.y = var2.getY() + (var2.getHeight() - this.height);
               this.width = var2.getWidth();
               return;
            case 8:
               this.x = var2.getX() + (var2.getWidth() - this.width);
               this.y = var2.getY() + (var2.getHeight() - this.height);
               return;
         }
      }
   }

   public final void release() {
      if (this.selection != null) {
         this.selection = null;
      }

      if (this.style != null) {
         this.style.c();
         this.style = null;
      }

      if (this.textPainter != null) {
         this.textPainter = null;
      }
   }
}
