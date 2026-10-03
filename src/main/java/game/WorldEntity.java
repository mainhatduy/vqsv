package game;

import javax.microedition.lcdui.Graphics;

public class WorldEntity extends BaseEntity {
   public SpriteRenderer spriteRenderer;
   public WorldEntity targetEntity = null;

   public WorldEntity() {
      this.spriteRenderer = new SpriteRenderer();
   }

   public boolean loadSprite(int var1, boolean var2) {
      return this.spriteRenderer.loadSprite(var1, var2);
   }

   public final void replaceSpriteImage(int var1, int var2) {
      this.spriteRenderer.replaceImage(var1, var2, true);
   }

   public final boolean setAnimation(byte var1, byte var2, boolean var3) {
      return this.spriteRenderer.setAnimation(var1, var2, var3);
   }

   public final void setAnimationAndDirection(byte var1, byte var2) {
      this.spriteRenderer.setAnimationWithoutReset(var1, (byte)-1);
      super.facingDirection = var2;
   }

   public final boolean advanceAnimationIfVisible() {
      return !this.visible ? false : this.spriteRenderer.advanceAnimation();
   }

   public final boolean isLastAnimationStep() {
      return this.spriteRenderer.isLastAnimationStep();
   }

   public final void renderInWorld(Graphics var1, int var2, int var3) {
      if (this.visible) {
         if (this.facingDirection == 3) {
            this.spriteRenderer.drawCurrentFrame(var1, this.posX - var2, this.posY - var3, (byte)1);
         } else {
            this.spriteRenderer.drawCurrentFrame(var1, this.posX - var2, this.posY - var3, (byte)0);
         }
      }
   }

   public void activate() {
      this.a(true);
      this.setVisible(true);
      this.c(true);
   }

   public void deactivate() {
      this.a(false);
      this.setVisible(false);
      this.c(false);
   }

   public final void deactivateTarget() {
      if (this.targetEntity != null) {
         this.targetEntity.deactivate();
      }
   }

   public void moveInFacingDirection(int var1) {
      switch (this.facingDirection) {
         case 0:
            this.movePosY(var1);
            break;
         case 1:
            this.movePosX(var1);
            break;
         case 2:
            this.movePosY(-var1);
            break;
         case 3:
            this.movePosX(-var1);
      }

      if (this.targetEntity != null) {
         this.targetEntity.setPosition(this.posX, this.posY);
      }
   }

   public final void b(int var1) {
      switch (var1) {
         case 0:
            this.movePosY(4);
            break;
         case 1:
            this.movePosX(4);
            break;
         case 2:
            this.movePosY(-4);
            break;
         case 3:
            this.movePosX(-4);
      }

      if (this.targetEntity != null) {
         this.targetEntity.setPosition(this.posX, this.posY);
      }
   }

   public final void f() {
      if (this.visible) {
         if (this.spriteRenderer.i()) {
            this.c(true);
         } else if (EngineUtils.a(
            WorldManager.a().a.cameraX,
            WorldManager.a().a.cameraY,
            BaseScreen.getScreenWidth(),
            BaseScreen.getScreenHeight(),
            this.posX,
            this.posY,
            this.spriteRenderer.j()
         )) {
            this.c(true);
         } else {
            this.c(false);
         }
      }
   }

   public final void c(int var1) {
      this.spriteRenderer.a(var1);
   }
}
