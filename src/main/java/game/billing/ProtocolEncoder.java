package game.billing;

import java.io.PrintStream;
import java.util.Random;

public final class ProtocolEncoder {
   public SessionData a;
   public PrintStream b;

   public ProtocolEncoder() {
      this(System.out);
   }

   private ProtocolEncoder(PrintStream var1) {
      new Random();
      this.b = var1;
      ProtocolEncoder var2 = this;
      this.a = new SessionData(var2, new SecurityHelper((byte)0));
      var2.a.a.put("_G", var2.a.a);
      BillingClient.a(var2);
   }

   public final int a(SessionData var1, int var2) {
      return this.c(var1, var2);
   }

   private int c(SessionData var1, int var2) {
      int var3 = var1.f - var2 - 1;
      Object var4;
      if ((var4 = var1.e[var3]) == null) {
         throw new RuntimeException("call nil");
      }

      if (var4 instanceof BillingListener) {
         return a(var1, (BillingListener)var4, var3 + 1, var3, var2);
      }

      if (!(var4 instanceof NetworkConfig)) {
         throw new RuntimeException("call a non-func");
      }

      var1.a((NetworkConfig)var4, var3 + 1, var3, var2, false, false).b();
      this.a(var1);
      var2 = var1.f - var3;
      var1.c = "";
      return var2;
   }

   private static int a(SessionData var0, BillingListener var1, int var2, int var3, int var4) {
      int var5 = 0;
      SessionData var7;
      NetworkConnection var10 = (var7 = var0).a(null, var2, var3, var4, false, false);
      var7.a();

      try {
         var5 = var1.a(var10, var4);
      } catch (ClassCastException var6) {
         var6.printStackTrace();
         System.out.println("func");
      }

      int var10000 = var10.a();
      boolean var8 = false;
      int var9 = var10000 - var5;
      var10.a(var9, -1, var5);
      var10.c(var5 - 1);
      return var5;
   }

   private final void a(SessionData var1) {
      NetworkConnection var2;
      NetworkConfig var3;
      TransactionRecord var4;
      int[] var5 = (var4 = (var3 = (var2 = var1.b()).b).a).a;
      int var10 = var2.e;

      while (true) {
         try {
            int var8;
            int var9 = (var8 = var5[var2.c++]) & 63;
            int var6 = var8 >>> 6 & 0xFF;
            switch (var9) {
               case 0:
                  int var43 = var8 >>> 23 & 511;
                  var2.a(var6, var2.a(var43));
                  break;
               case 1:
                  int var42 = var8 >>> 14;
                  var2.a(var6, var4.b[var42]);
                  break;
               case 2:
                  int var41 = var8 >>> 23 & 511;
                  var8 = var8 >>> 14 & 511;
                  var2.a(var6, var41 == 0 ? Boolean.FALSE : Boolean.TRUE);
                  if (var8 != 0) {
                     var2.c++;
                  }
                  break;
               case 3:
                  int var40 = var8 >>> 23 & 511;
                  var2.a(var6, var40);
                  break;
               case 4:
                  int var39 = var8 >>> 23 & 511;
                  var2.a(var6, var3.c[var39].a());
                  break;
               case 5:
                  int var38 = var8 >>> 14;
                  var2.a(var6, b(var3.b, var4.b[var38]));
                  break;
               case 6:
                  int var37 = var8 >>> 23 & 511;
                  var8 = var8 >>> 14 & 511;
                  Object var84 = var2.a(var37);
                  Object var99 = a(var2, var8, var4);
                  var2.a(var6, b(var84, var99));
                  break;
               case 7:
                  int var36 = var8 >>> 14;
                  Object var83 = var2.a(var6);
                  Object var98 = var4.b[var36];
                  a(var3.b, var98, var83);
                  break;
               case 8:
                  int var35 = var8 >>> 23 & 511;
                  var3.c[var35].a(var2.a(var6));
                  break;
               case 9:
                  int var34 = var8 >>> 23 & 511;
                  var8 = var8 >>> 14 & 511;
                  Object var82 = var2.a(var6);
                  Object var97 = a(var2, var34, var4);
                  Object var111 = a(var2, var8, var4);
                  a(var82, var97, var111);
                  break;
               case 10:
                  SecurityHelper var81 = new SecurityHelper((byte)0);
                  var2.a(var6, var81);
                  break;
               case 11:
                  int var33 = var8 >>> 23 & 511;
                  var8 = var8 >>> 14 & 511;
                  Object var80 = a(var2, var8, var4);
                  Object var96;
                  Object var110 = b(var96 = var2.a(var33), var80);
                  var2.a(var6, var110);
                  var2.a(var6 + 1, var96);
                  break;
               case 12:
               case 13:
               case 14:
               case 15:
               case 16:
               case 17:
                  int var32 = var8 >>> 23 & 511;
                  var8 = var8 >>> 14 & 511;
                  Object var79 = a(var2, var32, var4);
                  Object var95 = a(var2, var8, var4);
                  Integer var120 = null;
                  Integer var58 = null;
                  Integer var109;
                  if ((var109 = BillingClient.b(var79)) != null && (var120 = BillingClient.b(var95)) != null) {
                     var58 = a(var109, var120, var9);
                  }

                  var2.a(var6, var58);
               case 18:
               default:
                  break;
               case 19:
                  int var31 = var8 >>> 23 & 511;
                  Object var78 = var2.a(var31);
                  var2.a(var6, a(!b(var78)));
                  break;
               case 20:
                  int var30 = var8 >>> 23 & 511;
                  Object var77;
                  Integer var94;
                  if ((var77 = var2.a(var30)) instanceof SecurityHelper) {
                     var94 = a(((SecurityHelper)var77).a());
                  } else if (var77 instanceof String) {
                     var94 = a(((String)var77).length());
                  } else {
                     var94 = null;
                  }

                  var2.a(var6, var94);
                  break;
               case 21:
                  int var27 = var8 >>> 23 & 511;
                  var8 = var8 >>> 14 & 511;
                  int var76 = var27;
                  int var92 = var8;
                  Object var108 = var2.a(var92);
                  var92--;

                  while (var76 <= var92) {
                     String var119 = BillingClient.a(var108);
                     if (var108 != null) {
                        var8 = 0;

                        for (int var28 = var92; var76 <= var28; var8++) {
                           Object var64 = var2.a(var28);
                           var28--;
                           if (BillingClient.a(var64) == null) {
                              break;
                           }
                        }

                        if (var8 > 0) {
                           StringBuffer var65 = new StringBuffer();

                           for (int var29 = var92 - var8 + 1; var29 <= var92; var29++) {
                              var65.append(BillingClient.a(var2.a(var29)));
                           }

                           var65.append(var119);
                           var108 = var65.toString();
                           var92 -= var8;
                        }
                     }

                     if (var76 <= var92) {
                        var2.a(var92);
                        var92--;
                     }
                  }

                  var2.a(var6, var108);
                  break;
               case 22:
                  var2.c = var2.c + b(var8);
                  break;
               case 23:
               case 24:
               case 25:
                  int var26 = var8 >>> 23 & 511;
                  var8 = var8 >>> 14 & 511;
                  Object var75 = a(var2, var26, var4);
                  Object var91 = a(var2, var8, var4);
                  if (var75 instanceof Integer && var91 instanceof Integer) {
                     int var107 = a(var75);
                     int var118 = a(var91);
                     if (var9 == 23) {
                        if (var107 == var118 == (var6 == 0)) {
                           var2.c++;
                        }
                     } else if (var9 == 24) {
                        if (var107 < var118 == (var6 == 0)) {
                           var2.c++;
                        }
                     } else if (var107 <= var118 == (var6 == 0)) {
                        var2.c++;
                     }
                  } else if (var75 instanceof String && var91 instanceof String) {
                     if (var9 == 23) {
                        if (var75.equals(var91) == (var6 == 0)) {
                           var2.c++;
                        }
                     } else {
                        String var106 = (String)var75;
                        String var117 = (String)var91;
                        var8 = var106.compareTo(var117);
                        if (var9 == 24) {
                           if (var8 < 0 == (var6 == 0)) {
                              var2.c++;
                           }
                        } else if (var8 <= 0 == (var6 == 0)) {
                           var2.c++;
                        }
                     }
                  } else {
                     boolean var105 = false;
                     if (var75 == var91) {
                        var105 = true;
                     } else if (var9 == 23) {
                        var105 = c(var75, var91);
                     } else {
                        BillingClient.a(String.valueOf(var9) + " not defined for operand");
                     }

                     if (var105 == (var6 == 0)) {
                        var2.c++;
                     }
                  }
                  break;
               case 26:
                  var8 = var8 >>> 14 & 511;
                  if (b(var2.a(var6)) == (var8 == 0)) {
                     var2.c++;
                  }
                  break;
               case 27:
                  int var25 = var8 >>> 23 & 511;
                  var8 = var8 >>> 14 & 511;
                  Object var74;
                  if (b(var74 = var2.a(var25)) != (var8 == 0)) {
                     var2.a(var6, var74);
                  } else {
                     var2.c++;
                  }
                  break;
               case 28:
                  int var23 = var8 >>> 23 & 511;
                  int var49 = var8 >>> 14 & 511;
                  int var73;
                  if ((var73 = var23 - 1) != -1) {
                     var2.c(var6 + var73 + 1);
                  } else {
                     var73 = var2.a() - var6 - 1;
                  }

                  var2.i = var49 != 0;
                  int var90 = var2.d;
                  int var104;
                  int var116 = (var104 = var2.d + var6) + 1;
                  Object var50;
                  if ((var50 = var2.a(var6)) == null) {
                     BillingClient.a(var50 != null, "call nil");
                  }

                  if (var50 instanceof NetworkConfig) {
                     NetworkConnection var24;
                     (var24 = var1.a((NetworkConfig)var50, var116, var104, var73, true, var2.h)).b();
                     var2 = var24;
                     var3 = var24.b;
                     var4 = var24.b.a;
                     var5 = var24.b.a.a;
                     var10 = var2.e;
                  } else {
                     if (!(var50 instanceof BillingListener)) {
                        throw new RuntimeException("Call non-func: " + var50);
                     }

                     a(var1, (BillingListener)var50, var90 + var6 + 1, var90 + var6, var73);
                     if ((var2 = var1.b()).d()) {
                        return;
                     }

                     if (var2.b != null) {
                        var3 = var2.b;
                        var4 = var2.b.a;
                        var5 = var2.b.a.a;
                        var10 = var2.e;
                     }

                     if (var2.i) {
                        var2.c(var4.g);
                     }
                  }
                  break;
               case 29:
                  int var72 = var2.d;
                  var1.b(var72);
                  int var89;
                  if ((var89 = (var8 >>> 23 & 511) - 1) == -1) {
                     var89 = var2.a() - var6 - 1;
                  }

                  var2.i = false;
                  Object var103;
                  if ((var103 = var2.a(var6)) == null) {
                     BillingClient.a(var103 != null, "Tried to call nil");
                  }

                  int var115 = var10 + 1;
                  var1.a(var72 + var6, var10, var89 + 1);
                  var1.a(var10 + var89 + 1);
                  if (var103 instanceof NetworkConfig) {
                     var2.d = var115;
                     var2.f = var89;
                     var2.b = (NetworkConfig)var103;
                     var2.b();
                  } else {
                     if (!(var103 instanceof BillingListener)) {
                        BillingClient.a("Tried to call a non-function: " + var103);
                     }

                     SessionData var48 = var1;
                     a(var1, (BillingListener)var103, var115, var10, var89);
                     var2 = var1.b();
                     var48.a();
                     if (var48 != var1) {
                        if (var48.c() && var1.b == var48) {
                           var1.b = var48.b;
                           var48.b = null;
                           var1.b.b().a(Boolean.TRUE);
                        }

                        if ((var2 = var1.b()).d()) {
                           return;
                        }
                     } else {
                        if (!var2.g) {
                           return;
                        }

                        if ((var2 = var1.b()).i) {
                           var2.c(var2.b.a.g);
                        }
                     }
                  }

                  var3 = var2.b;
                  var4 = var2.b.a;
                  var5 = var2.b.a.a;
                  var10 = var2.e;
                  break;
               case 30:
                  int var22 = (var8 >>> 23 & 511) - 1;
                  int var71 = var2.d;
                  var1.b(var71);
                  if (var22 == -1) {
                     var22 = var2.a() - var6;
                  }

                  var1.a(var2.d + var6, var10, var22);
                  var1.a(var10 + var22);
                  if (!var2.g) {
                     var1.a();
                     return;
                  }

                  var1.a();
                  if ((var2 = var1.b()).b != null) {
                     var3 = var2.b;
                     var4 = var2.b.a;
                     var5 = var2.b.a.a;
                     var10 = var2.e;
                  }

                  if (var2.i) {
                     var2.c(var4.g);
                  }
                  break;
               case 31:
                  int var69 = a(var2.a(var6));
                  int var88 = a(var2.a(var6 + 1));
                  int var102 = a(var2.a(var6 + 2));
                  int var70;
                  Integer var114 = a(var70 = var69 + var102);
                  var2.a(var6, var114);
                  if (var102 > 0 ? var70 > var88 : var70 < var88) {
                     var2.b(var6);
                     break;
                  }

                  int var21 = b(var8);
                  var2.c += var21;
                  var2.a(var6 + 3, var114);
                  break;
               case 32:
                  int var20 = b(var8);
                  int var68 = a(var2.a(var6));
                  int var87 = a(var2.a(var6 + 2));
                  var2.a(var6, a(var68 - var87));
                  var2.c += var20;
                  break;
               case 33:
                  var8 = var8 >>> 14 & 511;
                  var2.c(var6 + 6);
                  var2.a(var6, var6 + 3, 3);
                  this.a(var1, 2);
                  var2.b(var6 + 3 + var8);
                  var2.c();
                  Object var67;
                  if ((var67 = var2.a(var6 + 3)) != null) {
                     var2.a(var6 + 2, var67);
                  } else {
                     var2.c++;
                  }
                  break;
               case 34:
                  int var19 = var8 >>> 23 & 511;
                  int var45 = var8 >>> 14 & 511;
                  if (var19 == 0) {
                     var19 = var2.a() - var6 - 1;
                  }

                  if (var45 == 0) {
                     var45 = var5[var2.c++];
                  }

                  int var66 = (var45 - 1) * 50;
                  SecurityHelper var86 = (SecurityHelper)var2.a(var6);

                  for (int var101 = 1; var101 <= var19; var101++) {
                     Integer var113 = a(var66 + var101);
                     Object var46 = var2.a(var6 + var101);
                     var86.put(var113, var46);
                  }
                  break;
               case 35:
                  var2.d(var6);
                  break;
               case 36:
                  int var17 = var8 >>> 14;
                  TransactionRecord var11 = var4.c[var17];
                  NetworkConfig var85 = new NetworkConfig(var11, var3.b);
                  var2.a(var6, var85);
                  int var100 = var11.f;

                  for (int var112 = 0; var112 < var100; var112++) {
                     var9 = (var8 = var5[var2.c++]) & 63;
                     var17 = var8 >>> 23 & 511;
                     switch (var9) {
                        case 0:
                           var85.c[var112] = var2.e(var17);
                        case 1:
                        case 2:
                        case 3:
                        default:
                           break;
                        case 4:
                           var85.c[var112] = var3.c[var17];
                     }
                  }
                  break;
               case 37:
                  int var7 = (var8 >>> 23 & 511) - 1;
                  var2.b(var6, var7);
            }
         } catch (RuntimeException var15) {
            var15.printStackTrace();

            while (!var1.b().e()) {
               var1.a();
            }

            boolean var12 = true;

            do {
               if ((var2 = var1.b()) == null) {
                  SessionData var13 = var1.b;
                  if (var1.b != null) {
                     var1.b = null;
                     NetworkConnection var14;
                     (var14 = var13.b()).a(Boolean.FALSE);
                     var14.a(var15.getMessage());
                     var14.a(var1.c);
                     var1.g.a = var13;
                     var1 = var13;
                     var5 = (var4 = (var3 = (var2 = var13.b()).b).a).a;
                     var10 = var2.e;
                     var12 = false;
                  }
                  break;
               }

               var1.a();
            } while (var2.g);

            if (var2 != null) {
               var2.d(0);
            }

            if (var12) {
               throw var15;
            }
         }
      }
   }

   private static final Object a(NetworkConnection var0, int var1, TransactionRecord var2) {
      int var3;
      return (var3 = var1 - 256) < 0 ? var0.a(var1) : var2.b[var3];
   }

   private static final int b(int var0) {
      return (var0 >>> 14) - 131071;
   }

   private static final Integer a(Integer var0, Integer var1, int var2) {
      int var4 = var0;
      int var5 = var1;
      int var3 = 0;
      switch (var2) {
         case 12:
            var3 = var4 + var5;
            break;
         case 13:
            var3 = var4 - var5;
            break;
         case 14:
            var3 = var4 * var5;
            break;
         case 15:
            var3 = var4 / var5;
            break;
         case 16:
            var3 = var4 % var5;
      }

      return a(var3);
   }

   public final Object a(Object var1, Object var2) {
      int var3 = this.a.f;
      this.a.a(var3 + 1 + 3);
      Object[] stack = this.a.e;
      stack[var3] = var1;
      stack[var3 + 1] = null;
      stack[var3 + 2] = null;
      stack[var3 + 3] = null;
      var1 = this.a;
      int var5 = this.c(this.a, 3);
      Object var7 = null;
      if (var5 > 0) {
         var7 = this.a.e[var3];
      }

      this.a.a(var3);
      return var7;
   }

   public static final Object b(Object var0, Object var1) {
      if (!(var0 instanceof SecurityHelper)) {
         if (var0 instanceof String && var1 instanceof Integer) {
            int var4 = (Integer)var1;
            return ((String)var0).substring(var4 - 1, var4);
         } else {
            return null;
         }
      } else {
         if (var1 instanceof Integer) {
            int var2 = (Integer)var1;
            Object var3;
            if ((var3 = ((SecurityHelper)var0).a(var2)) != null) {
               return var3;
            }
         }

         return ((SecurityHelper)var0).a(var1);
      }
   }

   private static void a(Object var0, Object var1, Object var2) {
      try {
         ((SecurityHelper)var0).put(var1, var2);
      } catch (Exception var3) {
         var3.printStackTrace();
         System.out.println(var1);
         System.out.println(var2);
         System.out.println(var0);
      }
   }

   public final int b(SessionData var1, int var2) {
      SessionData var3 = var1;
      NetworkConnection var4 = var1.b();
      var3.c = "";
      int var5 = var3.f - var2 - 1;
      Throwable var12;
      String var10;

      try {
         int var14 = this.c(var1, var2);
         int var11 = var5 + var14 + 1;
         var3.a(var11);
         var3.a(var5, var5 + 1, var14);
         var3.e[var5] = Boolean.TRUE;
         return var14 + 1;
      } catch (BillingException var8) {
         var12 = var8;
         var10 = null;
         var8.printStackTrace();
      } catch (Throwable var9) {
         var12 = var9;
         var10 = var9.getMessage();
         var9.printStackTrace();
      }

      BillingClient.a(true, "Thread changed in pcall");
      if (var4 != null) {
         var4.d(0);
      }

      NetworkConnection var6 = var4;
      SessionData var13 = var3;

      NetworkConnection var7;
      while ((var7 = var13.b()) != null && var7 != var6) {
         var13.a();
      }

      if (var10 instanceof String) {
         var10 = var10;
      }

      var3.a(var5 + 4);
      var3.e[var5] = Boolean.FALSE;
      var3.e[var5 + 1] = var10;
      var3.e[var5 + 2] = var3.c;
      var3.e[var5 + 3] = var12;
      var3.c = "";
      return 4;
   }

   public static boolean c(Object var0, Object var1) {
      if (var0 != null && var1 != null) {
         if (var0 instanceof Integer && var1 instanceof Integer) {
            return ((Integer)var0).intValue() == ((Integer)var1).intValue();
         } else {
            return var0 == var1;
         }
      } else {
         return var0 == var1;
      }
   }

   private static int a(Object var0) {
      return (Integer)var0;
   }

   public static Integer a(int var0) {
      return new Integer(var0);
   }

   private static boolean b(Object var0) {
      return var0 != null && var0 != Boolean.FALSE;
   }

   private static Boolean a(boolean var0) {
      return var0 ? Boolean.TRUE : Boolean.FALSE;
   }
}
