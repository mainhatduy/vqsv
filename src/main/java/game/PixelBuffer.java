package game;

public final class PixelBuffer {
   public int[] a;
   public int b;
   public int c;
   public int d;
   public int e;
   public int f;

   public final void a(int[] var1, int var2, int var3) {
      this.a = var1;
      this.b = var2;
      this.c = var3;
      this.f = var1.length;
   }

   public final PixelBuffer a() {
      PixelBuffer var1;
      (var1 = new PixelBuffer()).a = new int[this.a.length];
      System.arraycopy(this.a, 0, var1.a, 0, var1.a.length);
      var1.b = this.b;
      var1.c = this.c;
      var1.f = this.a.length;
      var1.d = this.d;
      var1.e = this.e;
      return var1;
   }
}
