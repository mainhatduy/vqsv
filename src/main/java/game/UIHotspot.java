package game;

public final class UIHotspot {
   public SpriteWidget a;
   private int b;
   private int c;
   private int d;
   private int e;
   private int f;

   public final int a() {
      return this.f;
   }

   public final int b() {
      return this.b;
   }

   public final void a(int var1) {
      this.b = var1;
   }

   public final int c() {
      return this.c;
   }

   public final void b(int var1) {
      this.c = var1;
   }

   public final int d() {
      return this.d;
   }

   public final void c(int var1) {
      this.d = var1;
   }

   public final int e() {
      return this.e;
   }

   public final void d(int var1) {
      this.e = var1;
   }

   public UIHotspot() {
      this.f = 0;
      this.a = null;
   }

   public UIHotspot(int var1, int var2, int var3, int var4) {
      this.a = null;
      this.b = var1;
      this.c = var2;
      this.d = var3;
      this.e = var4;
      this.f = 1;
   }

   public UIHotspot(int var1, byte var2, int var3, int var4, int var5, int var6) {
      if (var1 != -1) {
         this.a = new SpriteWidget();
         this.a.a(var1);
         this.a.a = var2;
      } else {
         this.a = null;
      }

      this.b = var3;
      this.c = var4;
      this.d = var5;
      this.e = var6;
      this.f = 1;
   }

   public UIHotspot(byte var1) {
      this.a = null;
      this.b = 0;
      this.c = 0;
      this.d = 0;
      this.e = 0;
      this.f = 0;
   }
}
