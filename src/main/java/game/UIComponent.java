package game;

import javax.microedition.lcdui.Graphics;

public interface UIComponent {
   void setVisible(boolean var1);

   void render(Graphics var1, boolean var2, boolean var3, UIComponent var4, int[] var5);

   void a(boolean var1, boolean var2, UIComponent var3, int[] var4);

   int getId();

   int getX();

   void setX(int var1, UIComponent var2);

   int getY();

   void setY(int var1, UIComponent var2);

   int getWidth();

   void setWidth(int var1, UIComponent var2);

   int getHeight();

   void setHeight(int var1, UIComponent var2);

   UISelectionConfig getSelection();

   UIComponent[] getChildren();

   UIStyle getStyle();

   void setStyle(UIStyle var1);

   int getType();

   int getParentId();

   void updateLayout(UIComponent var1);

   void release();
}
