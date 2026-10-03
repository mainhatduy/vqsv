package game.billing;

public final class PropertyRef {
   public SessionData a;
   public int b;
   public Object c;

   public final Object a() {
      return this.a == null ? this.c : this.a.e[this.b];
   }

   public final void a(Object var1) {
      if (this.a == null) {
         this.c = var1;
      } else {
         this.a.e[this.b] = var1;
      }
   }
}
