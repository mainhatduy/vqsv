package game;

public abstract class BaseInputHandler {
   protected boolean inputEnabled;
   private BaseInputHandler inputDelegate;
   private int heldKeys;
   private int pressedKeys;
   private int releasedKeys;
   private int frameHeldKeys;
   private int framePressedKeys;
   private int frameReleasedKeys;
   private int pointerX = -1;
   private int pointerY = -1;

   public void onKeyPressed(int var1) {
      int var2 = keyCodeToMask(var1);
      this.pressedKeys |= var2;
      this.heldKeys |= var2;
      if (this.inputDelegate != null) {
         this.inputDelegate.onKeyPressed(var1);
      }
   }

   public void onKeyReleased(int var1) {
      int var2 = keyCodeToMask(var1);
      this.releasedKeys |= var2;
      this.heldKeys &= ~var2;
      if (this.inputDelegate != null) {
         this.inputDelegate.onKeyReleased(var1);
      }
   }

   public void setInputEnabled(boolean var1) {
      this.resetInputState();
      this.inputEnabled = var1;
   }

   public void resetInputState() {
      this.heldKeys = 0;
      this.pressedKeys = 0;
      this.releasedKeys = 0;
      this.frameHeldKeys = 0;
      this.framePressedKeys = 0;
      this.frameReleasedKeys = 0;
      if (this.inputDelegate != null) {
         this.inputDelegate.resetInputState();
      }
   }

   public void onPointerEvent(int var1, int var2) {
      this.pointerX = var1;
      this.pointerY = var2;
      if (this.inputDelegate != null) {
         this.inputDelegate.onPointerEvent(var1, var2);
      }
   }

   private static int keyCodeToMask(int var0) {
      switch (var0) {
         case -22:
         case -7:
            return 262144;
         case -21:
         case -6:
            return 131072;
         case -20:
         case -19:
         case -18:
         case -17:
         case -16:
         case -15:
         case -14:
         case -13:
         case -12:
         case -11:
         case -10:
         case -9:
         case -8:
         case 0:
         case 1:
         case 2:
         case 3:
         case 4:
         case 5:
         case 6:
         case 7:
         case 8:
         case 9:
         case 10:
         case 11:
         case 12:
         case 13:
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
         case 24:
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
         case 36:
         case 37:
         case 38:
         case 39:
         case 40:
         case 41:
         case 43:
         case 44:
         case 45:
         case 46:
         case 47:
         default:
            return 0;
         case -5:
            return 65536;
         case -4:
            return 32768;
         case -3:
            return 16384;
         case -2:
            return 8192;
         case -1:
            return 4096;
         case 35:
            return 2048;
         case 42:
            return 1024;
         case 48:
            return 1;
         case 49:
            return 2;
         case 50:
            return 4;
         case 51:
            return 8;
         case 52:
            return 16;
         case 53:
            return 32;
         case 54:
            return 64;
         case 55:
            return 128;
         case 56:
            return 256;
         case 57:
            return 512;
      }
   }

   public boolean isKeyPressed(int var1) {
      return (this.framePressedKeys & var1) != 0;
   }

   public boolean consumeLeftSoftPointer() {
      if (this.pointerX >= 40 && this.pointerX <= 85 && this.pointerY >= 228 && this.pointerY <= 248) {
         this.pointerX = -1;
         this.pointerY = -1;
         return true;
      } else {
         return false;
      }
   }

   public boolean hasNavigationKeyRelease() {
      return (this.frameReleasedKeys & 61780) != 0;
   }

   public boolean isKeyHeld(int var1) {
      return (this.frameHeldKeys & var1) != 0;
   }

   protected void snapshotInput() {
      this.frameHeldKeys = this.heldKeys;
      this.framePressedKeys = this.pressedKeys;
      this.frameReleasedKeys = this.releasedKeys;
      this.pressedKeys = 0;
      this.releasedKeys = 0;
   }

   protected void setInputDelegate(BaseInputHandler var1) {
      if (this.inputDelegate != null) {
         this.inputDelegate.setInputEnabled(false);
         this.inputDelegate = null;
      }

      if (var1 != null) {
         var1.setInputEnabled(true);
         this.inputDelegate = var1;
      }
   }
}
