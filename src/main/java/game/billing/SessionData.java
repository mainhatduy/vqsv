package game.billing;

import java.util.Vector;

public final class SessionData {
   public SecurityHelper a;
   public SessionData b;
   public String c = "";
   public Vector d;
   public Object[] e;
   public int f;
   private NetworkConnection[] h;
   private int i;
   public ProtocolEncoder g;

   public SessionData() {
   }

   public SessionData(ProtocolEncoder var1, SecurityHelper var2) {
      this.g = var1;
      this.a = var2;
      this.e = new Object[10];
      this.h = new NetworkConnection[10];
      this.d = new Vector();
   }

   public final NetworkConnection a(NetworkConfig var1, int var2, int var3, int var4, boolean var5, boolean var6) {
      this.d(this.i + 1);
      NetworkConnection var7;
      (var7 = this.b()).d = var2;
      var7.e = var3;
      var7.f = var4;
      var7.g = var5;
      var7.h = var6;
      var7.b = var1;
      return var7;
   }

   public final void a() {
      if (this.c()) {
         throw new RuntimeException("Stack underflow");
      }

      this.d(this.i - 1);
   }

   private void d(int var1) {
      if (var1 > this.i) {
         SessionData var2 = this;
         if (var1 > 100) {
            throw new RuntimeException("Stack overflow");
         }

         int var4;
         int var3 = var4 = var2.h.length;

         while (var3 <= var1) {
            var3 <<= 1;
         }

         if (var3 > var4) {
            NetworkConnection[] var5 = new NetworkConnection[var3];
            System.arraycopy(var2.h, 0, var5, 0, var4);
            var2.h = var5;
         }
      } else {
         this.a(var1, this.i - 1);
      }

      this.i = var1;
   }

   private void a(int var1, int var2) {
      while (var1 <= var2) {
         if (this.h[var1] != null) {
            this.h[var1].b = null;
         }

         var1++;
      }
   }

   public final void a(int var1) {
      if (this.f < var1) {
         SessionData var2 = this;
         if (var1 > 1000) {
            throw new RuntimeException("Stack overflow");
         }

         int var4;
         int var3 = var4 = var2.e.length;

         while (var3 <= var1) {
            var3 <<= 1;
         }

         if (var3 > var4) {
            Object[] var5 = new Object[var3];
            System.arraycopy(var2.e, 0, var5, 0, var4);
            var2.e = var5;
         }
      } else {
         this.b(var1, this.f - 1);
      }

      this.f = var1;
   }

   public final void a(int var1, int var2, int var3) {
      if (var3 > 0 && var1 != var2) {
         System.arraycopy(this.e, var1, this.e, var2, var3);
      }
   }

   private void b(int var1, int var2) {
      while (var1 <= var2) {
         this.e[var1] = null;
         var1++;
      }
   }

   public final void b(int var1) {
      int var2 = this.d.size();

      while (--var2 >= 0) {
         PropertyRef var3;
         if ((var3 = (PropertyRef)this.d.elementAt(var2)).b < var1) {
            return;
         }

         var3.c = this.e[var3.b];
         var3.a = null;
         this.d.removeElementAt(var2);
      }
   }

   public final NetworkConnection b() {
      if (this.c()) {
         return null;
      }

      NetworkConnection var1;
      if ((var1 = this.h[this.i - 1]) == null) {
         var1 = new NetworkConnection(this);
         this.h[this.i - 1] = var1;
      }

      return var1;
   }

   public final NetworkConnection c(int var1) {
      BillingClient.a(var1 >= 0, "Level must be non-negative");
      int var2;
      BillingClient.a((var2 = this.i - var1 - 1) >= 0, "Level too high");
      return this.h[var2];
   }

   public final boolean c() {
      return this.i == 0;
   }
}
