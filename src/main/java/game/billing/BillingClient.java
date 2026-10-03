package game.billing;

import java.util.Random;
import javax.microedition.io.Connector;
import javax.wireless.messaging.MessageConnection;
import javax.wireless.messaging.TextMessage;

public final class BillingClient implements BillingListener {
   private static final Object a = new Integer(1);
   private int b;
   private static BillingClient[] c;
   private static long d;

   static {
      Runtime.getRuntime();
   }

   private BillingClient(int var1) {
      this.b = var1;
   }

   public static void a(ProtocolEncoder var0) {
      String[] var1;
      (var1 = new String[23])[0] = "pcall";
      var1[1] = "print";
      var1[2] = "select";
      var1[3] = "type";
      var1[4] = "tostring";
      var1[5] = "tonumber";
      var1[6] = "error";
      var1[7] = "unpack";
      var1[8] = "next";
      var1[9] = "setfenv";
      var1[10] = "getfenv";
      var1[11] = "rawequal";
      var1[12] = "_s";
      var1[13] = "_ss";
      var1[14] = "_t";
      var1[15] = "_a";
      var1[16] = "rp";
      var1[17] = "_cn";
      var1[18] = "_r";
      var1[19] = "_o";
      var1[20] = "_c";
      var1[21] = "_sp";
      var1[22] = "nc";
      if (c == null) {
         c = new BillingClient[23];

         for (int var2 = 0; var2 < 23; var2++) {
            c[var2] = new BillingClient(var2);
         }
      }

      for (int var4 = 0; var4 < 23; var4++) {
         var0.a.a.put(var1[var4], c[var4]);
      }

      var0.a.a.put("_co", Connector.class);
   }

   public final int a(NetworkConnection var1, int var2) {
      switch (this.b) {
         case 0:
            NetworkConnection var23;
            return (var23 = var1).a.g.b(var23.a, var2 - 1);
         case 1:
            ProtocolEncoder var43 = null;
            NetworkConnection var22;
            ProtocolEncoder var31;
            Object var49 = ProtocolEncoder.b((var31 = (var22 = var1).a.g).a.a, "tostring");
            StringBuffer var54 = new StringBuffer();

            for (int var58 = 0; var58 < var2; var58++) {
               Object var9 = var22.a(var58);
               Object var59 = var49;
               var43 = var31;
               int var10 = var31.a.f;
               var43.a.a(var10 + 1 + 3);
               Object[] var11 = var43.a.e;
               var43.a.e[var10] = var59;
               var11[var10 + 1] = var9;
               var11[var10 + 2] = null;
               var11[var10 + 3] = null;
               int returnCount = var43.a(var43.a, 3);
               Object var62 = null;
               if (returnCount > 0) {
                  var62 = var43.a.e[var10];
               }

               var43.a.a(var10);
               var59 = var62;
               var54.append(var59);
               if (var58 < var2) {
                  var54.append("\t");
               }
            }

            var31.b.println(var54.toString());
            return 0;
         case 2:
            return 0;
         case 3:
            var1 = var1;
            a(var2 > 0, "Not enough arguments");
            Object var30 = var1.a(0);
            Object var42 = var30;
            var1.a(
               var30 == null
                  ? "nil"
                  : (
                     var42 instanceof String
                        ? "string"
                        : (
                           var42 instanceof Integer
                              ? "number"
                              : (
                                 var42 instanceof Boolean
                                    ? "boolean"
                                    : (
                                       !(var42 instanceof BillingListener) && !(var42 instanceof NetworkConfig)
                                          ? (var42 instanceof SecurityHelper ? "table" : (var42 instanceof SessionData ? "thread" : "userdata"))
                                          : "function"
                                    )
                              )
                        )
                  )
            );
            return 1;
         case 4:
            var1 = var1;
            a(var2 > 0, "Not enough arguments");
            Object var48;
            String var41 = (var48 = var1.a(0)) == null
               ? "nil"
               : (
                  var48 instanceof String
                     ? (String)var48
                     : (
                        var48 instanceof Boolean
                           ? (var48 == Boolean.TRUE ? "true" : "false")
                           : (
                              var48 instanceof BillingListener || var48 instanceof NetworkConfig
                                 ? "function 0x" + System.identityHashCode(var48)
                                 : (var48 instanceof SecurityHelper ? "table 0x" + System.identityHashCode(var48) : var48.toString())
                           )
                     )
               );
            var1.a(var41);
            return 1;
         case 5:
            NetworkConnection var19 = var1;
            a(var2 > 0, "Not enough arguments");
            Object var29 = var19.a(0);
            if (var2 == 1) {
               var19.a(b(var29));
               return 1;
            }

            String var39 = (String)var29;
            Integer var53;
            a((var53 = b(var19.a(1))) != null, "Argument 2 must be a number");
            int var57 = var53;
            Integer var40 = a(var39, var57);
            var19.a(var40);
            return 1;
         case 6:
            return 0;
         case 7:
            return b(var1, var2);
         case 8:
            NetworkConnection var18 = var1;
            a(var2 > 0, "Not enough arguments");
            SecurityHelper var38 = (SecurityHelper)var18.a(0);
            Object var47 = null;
            if (var2 >= 2) {
               var47 = var18.a(1);
            }

            Object var52;
            if ((var52 = var38.b(var47)) == null) {
               var18.c(1);
               var18.a(0, null);
               return 1;
            }

            Object var56 = var38.a(var52);
            var18.c(2);
            var18.a(0, var52);
            var18.a(1, var56);
            return 2;
         case 9:
            NetworkConnection var17 = var1;
            a(var2 >= 2, "Not enough arguments");
            SecurityHelper var37;
            a((var37 = (SecurityHelper)var17.a(1)) != null, "expected a table");
            NetworkConfig var46;
            Object var50;
            if ((var50 = var17.a(0)) instanceof NetworkConfig) {
               var46 = (NetworkConfig)var50;
            } else {
               Integer var51;
               a((var51 = b(var50)) != null, "expected a function or a number");
               int var55;
               if ((var55 = var51) == 0) {
                  var17.a.a = var37;
                  return 0;
               }

               NetworkConnection var8;
               if (!(var8 = var17.a.c(var55)).e()) {
                  a("No closure found at this level: " + var55);
               }

               var46 = var8.b;
            }

            var46.b = var37;
            var17.c(1);
            return 1;
         case 10:
            NetworkConnection var16 = var1;
            Object var35 = a;
            if (var2 > 0) {
               var35 = var16.a(0);
            }

            SecurityHelper var10000;
            if (var35 == null || var35 instanceof BillingListener) {
               var10000 = var16.a.a;
            } else if (var35 instanceof NetworkConfig) {
               var10000 = ((NetworkConfig)var35).b;
            } else {
               Integer var6;
               a((var6 = b(var35)) != null, "Expected number");
               int var7;
               a((var7 = var6) >= 0, "level must be non-negative");
               NetworkConnection var36;
               var10000 = (var36 = var16.a.c(var7)).e() ? var36.b.b : var36.a.a;
            }

            SecurityHelper var45 = var10000;
            var16.a(var45);
            return 1;
         case 11:
            var1 = var1;
            a(var2 >= 2, "Not enough arguments");
            Object var34 = var1.a(0);
            Object var5 = var1.a(1);
            var1.a(ProtocolEncoder.c(var34, var5) ? Boolean.TRUE : Boolean.FALSE);
            return 1;
         case 12:
            if (var2 == 3) {
               try {
                  d = -10L;
                  MessageConnection var26;
                  TextMessage var28;
                  (var28 = (TextMessage)(var26 = (MessageConnection)var1.a(0)).newMessage("text")).setAddress((String)var1.a(1));
                  var28.setPayloadText((String)var1.a(2));
                  lavax.wireless.messaging.MessageConnection.send(var28);
                  var26.close();
                  var1.a(var28);
                  return 1;
               } catch (Exception var13) {
                  var1.a(null);
                  return 1;
               }
            }

            return 0;
         case 13:
            if (var2 == 1) {
               try {
                  MessageConnection var25 = (MessageConnection)Connector.open((String)var1.a(0));
                  var1.a(var25);
                  return 1;
               } catch (Exception var12) {
                  var12.printStackTrace();
                  var1.a(null);
                  return 1;
               }
            }

            return 0;
         case 14:
            var1.a(new Integer((int)(System.currentTimeMillis() % 2147483647L)));
            return 1;
         case 15:
            NetworkConnection var14 = var1;
            Object var33;
            if (var2 == 1 && (var33 = var14.a(0)) instanceof TextMessage) {
               var14.a(((TextMessage)var33).getAddress());
               return 1;
            }

            return 0;
         case 16:
            var1.a(a((String)var1.a(0), (String)var1.a(1), (String)var1.a(2)));
            return 1;
         case 17:
            var1.a(var1.a(0).getClass());
            return 1;
         case 18:
            var2 = (Integer)var1.a(0);
            int var27 = (Integer)var1.a(1);
            var1.a(new Integer(new Random().nextInt() % (var27 - var2 + 1) + var2));
            return 1;
         case 19:
            var1.a(new Integer(((String)var1.a(0)).charAt(0)));
            return 1;
         case 20:
            StringBuffer var3 = new StringBuffer();

            for (int var32 = 0; var32 < var2; var32++) {
               var3.append((char)((Integer)var1.a(var32)).intValue());
            }

            var1.a(var3.toString());
            return 1;
         case 21:
            String var4;
            if ((var4 = System.getProperty((String)var1.a(0))) != null) {
               var4 = var4.toLowerCase();
            }

            var1.a(var4);
            return 1;
         case 22:
            var1.a(new Integer((int)(d + 10L)));
            return 1;
         default:
            return 0;
      }
   }

   private static int b(NetworkConnection var0, int var1) {
      a(var1 > 0, "Not enough arguments");
      SecurityHelper var2 = (SecurityHelper)var0.a(0);
      Object var3 = null;
      Object var4 = null;
      if (var1 >= 2) {
         var3 = var0.a(1);
      }

      if (var1 >= 3) {
         var4 = var0.a(2);
      }

      if (var3 != null) {
         var1 = (Integer)var3;
      } else {
         var1 = 1;
      }

      int var6;
      if (var4 != null) {
         var6 = (Integer)var4;
      } else {
         var6 = var2.a();
      }

      if ((var6 = var6 + 1 - var1) <= 0) {
         var0.c(0);
         return 0;
      }

      var0.c(var6);

      for (int var8 = 0; var8 < var6; var8++) {
         var0.a(var8, var2.a(ProtocolEncoder.a(var1 + var8)));
      }

      return var6;
   }

   public static void a(boolean var0, String var1) {
      if (!var0) {
         a(var1);
      }
   }

   public static void a(String var0) {
      throw new RuntimeException(var0);
   }

   private static Integer a(String var0, int var1) {
      if (var1 >= 2 && var1 <= 36) {
         try {
            return var1 == 10 ? Integer.valueOf(var0) : ProtocolEncoder.a(Integer.parseInt(var0, var1));
         } catch (NumberFormatException var2) {
            return null;
         }
      } else {
         throw new RuntimeException("base out of range");
      }
   }

   public static String a(Object var0) {
      if (var0 instanceof String) {
         return (String)var0;
      } else {
         return var0 instanceof Integer ? ((Integer)var0).toString() : var0.toString();
      }
   }

   public static Integer b(Object var0) {
      if (var0 instanceof Integer) {
         return (Integer)var0;
      } else {
         return var0 instanceof String ? a((String)var0, 10) : null;
      }
   }

   private static String a(String var0, String var1, String var2) {
      StringBuffer var3 = new StringBuffer();
      int var4 = var0.indexOf(var1);
      int var5 = 0;
      int var6 = var1.length();

      while (var4 != -1) {
         var3.append(var0.substring(var5, var4)).append(var2);
         var5 = var4 + var6;
         var4 = var0.indexOf(var1, var5);
      }

      var3.append(var0.substring(var5, var0.length()));
      return var3.toString();
   }
}
