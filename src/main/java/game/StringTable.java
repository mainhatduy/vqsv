package game;

import java.util.Vector;
import javax.microedition.lcdui.Graphics;

public final class StringTable {
   private MapEngine a;
   private Vector b;
   private Vector c = new Vector();
   private Vector d;
   private ParticleEffect e;

   public StringTable() {
      this.b = new Vector();
      this.d = new Vector();
   }

   public final void a(MapEngine var1) {
      this.a = var1;
   }

   public final void a(BaseEntity var1) {
      switch (var1.s) {
         case 0:
            this.b.addElement(var1);
            return;
         case 1:
            this.c.addElement(var1);
            return;
         case 2:
            this.d.addElement(var1);
      }
   }

   public final void b(BaseEntity var1) {
      switch (var1.s) {
         case 0:
            this.b.removeElement(var1);
            return;
         case 1:
            this.c.removeElement(var1);
            return;
         case 2:
            this.d.removeElement(var1);
      }
   }

   public final void c(BaseEntity var1) {
      this.b(var1);
      this.d.addElement(var1);
   }

   public final void a(ParticleEffect var1) {
      this.e = var1;
   }

   public final void a() {
      this.a = null;
      this.e = null;
      this.c.removeAllElements();
      this.b.removeAllElements();
      this.d.removeAllElements();
   }

   public final void b() {
      this.e.d();
      this.a.centerCamera(this.e.posX, this.e.posY);
      this.a.updateVisibleBounds();

      for (int var1 = 0; var1 < this.b.size(); var1++) {
         ((WorldEntity)this.b.elementAt(var1)).advanceAnimationIfVisible();
      }

      for (int var5 = 0; var5 < this.c.size(); var5++) {
         for (int var2 = 0; var2 < this.c.size() - var5 - 1; var2++) {
            BaseEntity var3 = (BaseEntity)this.c.elementAt(var2);
            BaseEntity var4 = (BaseEntity)this.c.elementAt(var2 + 1);
            if (var3.posY > var4.posY) {
               this.c.setElementAt(var4, var2);
               this.c.setElementAt(var3, var2 + 1);
            }
         }
      }

      for (int var6 = 0; var6 < this.c.size(); var6++) {
         WorldEntity var8;
         (var8 = (WorldEntity)this.c.elementAt(var6)).advanceAnimationIfVisible();
         if (var8 instanceof NpcEntity) {
            if (((NpcEntity)var8).G != null && ((NpcEntity)var8).G.i()) {
               ((NpcEntity)var8).G.advanceAnimationIfVisible();
            } else if (((NpcEntity)var8).H != null && ((NpcEntity)var8).H.i()) {
               ((NpcEntity)var8).H.advanceAnimationIfVisible();
            }
         }
      }

      for (int var7 = 0; var7 < this.d.size(); var7++) {
         ((WorldEntity)this.d.elementAt(var7)).advanceAnimationIfVisible();
      }
   }

   public final void a(Graphics var1) {
      this.a.renderLayer(var1, 1, 1);
      this.a.renderLayer(var1, 2, 1);

      for (int var2 = 0; var2 < this.d.size(); var2++) {
         if (((WorldEntity)this.d.elementAt(var2)).k()) {
            ((WorldEntity)this.d.elementAt(var2)).renderInWorld(var1, this.a.cameraX, this.a.cameraY);
         }
      }

      short var5 = 0;

      try {
         if (WorldManager.a().c.P[2] == 2) {
            var5 = 1;

            for (int var3 = 0; var3 < this.c.size(); var3++) {
               var5 = 2;
               var5 = 2;
               if (((WorldEntity)this.c.elementAt(var3)).k()) {
                  if ((WorldEntity)this.c.elementAt(var3) instanceof Player) {
                     continue;
                  }

                  var5 = 3;
                  ((WorldEntity)this.c.elementAt(var3)).renderInWorld(var1, this.a.cameraX, this.a.cameraY);
               }

               if ((WorldEntity)this.c.elementAt(var3) instanceof NpcEntity && ((NpcEntity)this.c.elementAt(var3)).v == 14) {
                  var5 = 4;
                  ((NpcEntity)this.c.elementAt(var3)).b(var1, this.a.cameraX, this.a.cameraY);
               }

               var5 = 501;
               if ((WorldEntity)this.c.elementAt(var3) instanceof NpcEntity) {
                  if (((NpcEntity)this.c.elementAt(var3)).G != null && ((NpcEntity)this.c.elementAt(var3)).G.isVisible()) {
                     var5 = 5;
                     ((NpcEntity)this.c.elementAt(var3)).G.renderInWorld(var1, this.a.cameraX, this.a.cameraY);
                  } else if (((NpcEntity)this.c.elementAt(var3)).H != null && ((NpcEntity)this.c.elementAt(var3)).H.isVisible()) {
                     var5 = 6;
                     ((NpcEntity)this.c.elementAt(var3)).H.renderInWorld(var1, this.a.cameraX, this.a.cameraY);
                  }
               }
            }

            var5 = 7;
            byte var7 = MapEngine.getInstance().getCollisionTile(Player.getInstance().posX, Player.getInstance().posY);
            var5 = 8;
            if (var7 != 1 && WorldManager.a().c.targetEntity != null && WorldManager.a().c.isVisible()) {
               var5 = 9;
               WorldManager.a().c.targetEntity.renderInWorld(var1, this.a.cameraX, this.a.cameraY);
            }

            var5 = 10;
            WorldManager.a().c.renderInWorld(var1, this.a.cameraX, this.a.cameraY);
         } else {
            for (int var8 = 0; var8 < this.c.size(); var8++) {
               var5 = 110;
               var5 = 11;
               if (((WorldEntity)this.c.elementAt(var8)).k()) {
                  var5 = 12;
                  if (((WorldEntity)this.c.elementAt(var8)).targetEntity != null && ((WorldEntity)this.c.elementAt(var8)).isVisible()) {
                     var5 = 13;
                     ((WorldEntity)this.c.elementAt(var8)).targetEntity.renderInWorld(var1, this.a.cameraX, this.a.cameraY);
                  }

                  var5 = 14;
                  ((WorldEntity)this.c.elementAt(var8)).renderInWorld(var1, this.a.cameraX, this.a.cameraY);
               }

               if ((WorldEntity)this.c.elementAt(var8) instanceof NpcEntity && ((NpcEntity)this.c.elementAt(var8)).v == 14) {
                  var5 = 15;
                  ((NpcEntity)this.c.elementAt(var8)).b(var1, this.a.cameraX, this.a.cameraY);
               }

               var5 = 16;
               if ((WorldEntity)this.c.elementAt(var8) instanceof NpcEntity && ((NpcEntity)this.c.elementAt(var8)).k()) {
                  var5 = 17;
                  if (((NpcEntity)this.c.elementAt(var8)).G != null && ((NpcEntity)this.c.elementAt(var8)).G.isVisible()) {
                     var5 = 18;
                     ((NpcEntity)this.c.elementAt(var8)).G.renderInWorld(var1, this.a.cameraX, this.a.cameraY);
                  } else if (((NpcEntity)this.c.elementAt(var8)).H != null && ((NpcEntity)this.c.elementAt(var8)).H.isVisible()) {
                     var5 = 19;
                     ((NpcEntity)this.c.elementAt(var8)).H.renderInWorld(var1, this.a.cameraX, this.a.cameraY);
                  }
               }
            }
         }
      } catch (Exception var4) {
         DebugLogger.a(var4, "" + var5);
      }

      this.a.renderLayer(var1, 3, 1);

      for (int var9 = 0; var9 < this.b.size(); var9++) {
         WorldEntity var6;
         if ((var6 = (WorldEntity)this.b.elementAt(var9)).k()) {
            var6.renderInWorld(var1, this.a.cameraX, this.a.cameraY);
         }
      }
   }

   public final void b(Graphics var1) {
      WorldManager.a();
      WorldManager.a(var1, 0, 0, BaseScreen.getScreenWidth(), BaseScreen.getScreenHeight());
      this.a.renderLayer(var1, 1, 1);
      this.a.renderLayer(var1, 2, 1);

      for (int var2 = 0; var2 < this.d.size(); var2++) {
         if (((WorldEntity)this.d.elementAt(var2)).k() && this.d.elementAt(var2) instanceof NpcEntity && ((NpcEntity)this.d.elementAt(var2)).v == 0) {
            ((WorldEntity)this.d.elementAt(var2)).renderInWorld(var1, this.a.cameraX, this.a.cameraY);
         }
      }

      for (int var4 = 0; var4 < this.c.size(); var4++) {
         if (((WorldEntity)this.c.elementAt(var4)).k() && this.c.elementAt(var4) instanceof NpcEntity && ((NpcEntity)this.c.elementAt(var4)).v == 0) {
            ((WorldEntity)this.c.elementAt(var4)).renderInWorld(var1, this.a.cameraX, this.a.cameraY);
         }
      }

      this.a.renderLayer(var1, 3, 1);

      for (int var5 = 0; var5 < this.b.size(); var5++) {
         if (((WorldEntity)this.b.elementAt(var5)).k() && this.b.elementAt(var5) instanceof NpcEntity && ((NpcEntity)this.b.elementAt(var5)).v == 0) {
            ((WorldEntity)this.b.elementAt(var5)).renderInWorld(var1, this.a.cameraX, this.a.cameraY);
         }
      }

      for (int var6 = 0; var6 < BaseScreen.getScreenWidth() / GameDatabase.backgroundImage.getWidth(); var6++) {
         for (int var3 = 0; var3 < BaseScreen.getScreenHeight() / GameDatabase.backgroundImage.getHeight(); var3++) {
            var1.drawImage(GameDatabase.backgroundImage, var6 * GameDatabase.backgroundImage.getWidth(), var3 * GameDatabase.backgroundImage.getHeight(), 20);
         }
      }
   }
}
