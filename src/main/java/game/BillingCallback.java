package game;

import game.billing.BillingListener;
import game.billing.NetworkConnection;

public final class BillingCallback implements BillingListener {
   private int a;
   private BillingCanvas b;

   public BillingCallback(BillingCanvas var1, int var2) {
      this.b = var1;
      this.a = var2;
   }

   public final int a(NetworkConnection var1, int var2) {
      return this.b.a(this.a, var1, var2);
   }
}
