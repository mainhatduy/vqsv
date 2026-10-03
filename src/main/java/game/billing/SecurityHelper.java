package game.billing;

import java.lang.ref.WeakReference;

public class SecurityHelper {
   private Object[] a;
   private Object[] b;
   private int[] c;
   private int d;
   private boolean e;
   private boolean f;

   public void put(Object var1, Object var2) {
      g(var1);
      int var3 = this.c(var1);
      int var4;
      if ((var4 = this.a(var1, var3)) < 0) {
         var4 = this.b(var1, var3);
      }

      this.b[var4] = this.f ? e(var2) : var2;
   }

   public Object a(Object var1) {
      g(var1);
      int var2 = this.c(var1);
      int var3;
      return (var3 = this.a(var1, var2)) >= 0 ? this.d(var3) : null;
   }

   public Object a(int var1) {
      return this.a(ProtocolEncoder.a(var1));
   }

   public Object b(Object var1) {
      int var2 = 0;
      if (var1 != null) {
         int var3 = this.c(var1);
         BillingClient.a((var2 = 1 + this.a(var1, var3)) > 0, "invalid key to 'next'");
      }

      while (var2 != this.a.length) {
         Object var4;
         if ((var4 = this.c(var2)) != null && this.d(var2) != null) {
            return var4;
         }

         var2++;
      }

      return null;
   }

   public int a() {
      int var1 = this.a.length;
      int var2 = 0;

      while (var2 < var1) {
         int var3;
         Integer var4 = ProtocolEncoder.a(var3 = var1 + var2 + 1 >> 1);
         if (this.a(var4) == null) {
            var1 = var3 - 1;
         } else {
            var2 = var3;
         }
      }

      return var2;
   }

   private static int b(int capacity) {
      if (capacity < 4) return 4;
      capacity--;
      capacity |= capacity >> 1;
      capacity |= capacity >> 2;
      capacity |= capacity >> 4;
      capacity |= capacity >> 8;
      capacity |= capacity >> 16;
      return capacity + 1;
   }

   private SecurityHelper() {
      SecurityHelper var1 = this;
      int var2 = b(4);
      var1.a = new Object[var2];
      var1.b = new Object[var2];
      var1.c = new int[var2];
      var1.d = var2;
   }

   public SecurityHelper(byte var1) {
      this();
   }

   private int c(Object key) {
      int hash = key instanceof Integer ? ((Integer) key).intValue()
         : key instanceof String ? key.hashCode() : System.identityHashCode(key);
      return hash & (this.a.length - 1);
   }

   private static Object d(Object var0) {
      return !f(var0) ? var0 : ((WeakReference)var0).get();
   }

   private static Object e(Object var0) {
      return !f(var0) ? var0 : new WeakReference<>(var0);
   }

   private static boolean f(Object var0) {
      return var0 != null && !(var0 instanceof String) && !(var0 instanceof Integer) && !(var0 instanceof Boolean) && !(var0 instanceof BillingListener);
   }

   private Object c(int var1) {
      Object var2 = this.a[var1];
      return this.e ? d(var2) : var2;
   }

   private void setKeyAt(int var1, Object var2) {
      if (this.e) {
         var2 = e(var2);
      }

      this.a[var1] = var2;
   }

   private Object d(int var1) {
      Object var2 = this.b[var1];
      return this.f ? d(var2) : var2;
   }

   private int a(Object var1, int var2) {
      Object var3;
      if ((var3 = this.c(var2)) == null) {
         return -1;
      }

      if (var1 instanceof Integer) {
         int var4 = (Integer)var1;

         while (true) {
            if (var3 instanceof Integer) {
               int var5 = (Integer)var3;
               if (var4 == var5) {
                  return var2;
               }
            }

            if ((var2 = this.c[var2]) == -1) {
               return -1;
            }

            var3 = this.c(var2);
         }
      } else if (var1 instanceof String) {
         while (!var1.equals(var3)) {
            if ((var2 = this.c[var2]) == -1) {
               return -1;
            }

            var3 = this.c(var2);
         }

         return var2;
      } else {
         while (var1 != var3) {
            if ((var2 = this.c[var2]) == -1) {
               return -1;
            }

            var3 = this.c(var2);
         }

         return var2;
      }
   }

   /** Lua-style chained hash table insertion recovered from a.h bytecode. */
   private int b(Object key, int slot) {
      Object occupiedKey;
      while ((occupiedKey = this.c(slot)) != null) {
         try {
            while (this.c(--this.d) != null) {}
         } catch (ArrayIndexOutOfBoundsException fullTable) {
            boolean weakKeys = this.e;
            boolean weakValues = this.f;
            this.a(false, false);
            Object[] oldKeys = this.a;
            Object[] oldValues = this.b;
            int count = 0;
            for (int index = oldKeys.length - 1; index >= 0; index--) {
               if (oldKeys[index] != null && oldValues[index] != null) count++;
            }
            int capacity = 2 * b(count);
            this.a = new Object[capacity];
            this.b = new Object[capacity];
            this.c = new int[capacity];
            this.d = capacity;
            for (int index = oldKeys.length - 1; index >= 0; index--) {
               if (oldKeys[index] != null && oldValues[index] != null) {
                  this.put(oldKeys[index], oldValues[index]);
               }
            }
            this.a(weakKeys, weakValues);
            slot = this.c(key);
            continue;
         }
         int home = this.c(occupiedKey);
         if (home == slot) {
            this.setKeyAt(this.d, key);
            this.c[this.d] = this.c[slot];
            this.c[slot] = this.d;
            return this.d;
         }
         this.a[this.d] = this.a[slot];
         this.b[this.d] = this.b[slot];
         this.c[this.d] = this.c[slot];
         this.setKeyAt(slot, key);
         this.c[slot] = -1;
         while (this.c[home] != slot) home = this.c[home];
         this.c[home] = this.d;
         return slot;
      }
      this.setKeyAt(slot, key);
      this.c[slot] = -1;
      return slot;
   }

   private static void g(Object var0) {
      BillingClient.a(var0 != null, "table index is nil");
   }

   public void a(boolean var1, boolean var2) {
      if (var1 != this.e) {
         this.a(this.a, var1);
         this.e = var1;
      }

      if (var2 != this.f) {
         this.a(this.b, var2);
         this.f = var2;
      }
   }

   private void a(Object[] var1, boolean var2) {
      for (int var3 = var1.length - 1; var3 >= 0; var3--) {
         Object var4 = var1[var3];
         var1[var3] = var2 ? e(var4) : d(var4);
      }
   }
}
