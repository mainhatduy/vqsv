package game.billing;

public final class NetworkConnection {
   public SessionData a;
   public NetworkConfig b;
   public int c;
   public int d;
   int e;
   public int f;
   boolean g;
   public boolean h;
   boolean i;

   public NetworkConnection(SessionData var1) {
      this.a = var1;
   }

   public final void a(int var1, Object var2) {
      this.a.e[this.d + var1] = var2;
   }

   public final Object a(int var1) {
      return this.a.e[this.d + var1];
   }

   public final int a(Object var1) {
      int var2 = this.a();
      this.c(var2 + 1);
      this.a(var2, var1);
      return 1;
   }

   public final void a(int var1, int var2, int var3) {
      this.a.a(this.d + var1, this.d + var2, var3);
   }

   public final void a(int var1, int var2) {
      while (var1 <= var2) {
         this.a.e[this.d + var1] = null;
         var1++;
      }
   }

   public final void b(int var1) {
      if (this.a() < var1) {
         this.c(var1);
      }

      this.a(var1, this.a() - 1);
   }

   public final void c(int var1) {
      this.a.a(this.d + var1);
   }

   public final void d(int var1) {
      this.a.b(this.d + var1);
   }

   public final PropertyRef e(int var1) {
      int var2 = this.d + var1;
      SessionData var5 = this.a;
      int var3 = this.a.d.size();

      while (--var3 >= 0) {
         PropertyRef var4;
         if ((var4 = (PropertyRef)var5.d.elementAt(var3)).b == var2) {
            return var4;
         }

         if (var4.b < var2) {
            break;
         }
      }

      PropertyRef var6;
      (var6 = new PropertyRef()).a = var5;
      var6.b = var2;
      var5.d.insertElementAt(var6, var3 + 1);
      return var6;
   }

   public final int a() {
      return this.a.f - this.d;
   }

   public final void b() {
      if (this.e()) {
         this.c = 0;
         if (this.b.a.e) {
            this.d = this.d + this.f;
            this.c(this.b.a.g);
            int var1 = Math.min(this.f, this.b.a.d);
            this.a(-this.f, 0, var1);
            return;
         }

         this.c(this.b.a.g);
      }
   }

   public final void c() {
      if (this.e()) {
         this.c(this.b.a.g);
      }
   }

   public final void b(int var1, int var2) {
      int var3 = this.b.a.d;
      int var4;
      if ((var4 = this.f - var3) < 0) {
         var4 = 0;
      }

      if (var2 == -1) {
         var2 = var4;
         this.c(var1 + var2);
      }

      if (var4 > var2) {
         var4 = var2;
      }

      this.a(-this.f + var3, var1, var4);
      if (var2 - var4 > 0) {
         this.a(var1 + var4, var1 + var2 - 1);
      }
   }

   public final boolean d() {
      return !this.e();
   }

   public final boolean e() {
      return this.b != null;
   }
}
