package game.billing;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;

public final class TransactionRecord {
   public int[] a;
   public Object[] b;
   public TransactionRecord[] c;
   public int d;
   public boolean e;
   private String h;
   public int f;
   public int g;

   public TransactionRecord() {
   }

   private TransactionRecord(DataInputStream var1, String var2) throws IOException {
      this.h = a(var1);
      if (this.h == null) {
         this.h = var2;
      }

      var1.readInt();
      var1.readInt();
      this.f = var1.read();
      this.d = var1.read();
      int var6 = var1.read();
      this.e = (var6 & 2) != 0;
      this.g = var1.read();
      int var7 = var1.readInt();
      this.a = new int[var7];

      for (int var3 = 0; var3 < var7; var3++) {
         this.a[var3] = var1.readInt();
      }

      int var10 = var1.readInt();
      this.b = new Object[var10];

      for (int var8 = 0; var8 < var10; var8++) {
         Serializable var4 = null;
         int var5;
         switch (var5 = var1.read()) {
            case 0:
               break;
            case 1:
               var4 = var1.read() == 0 ? Boolean.FALSE : Boolean.TRUE;
               break;
            case 2:
            default:
               throw new IOException("unknown constant type: " + var5);
            case 3:
               var4 = ProtocolEncoder.a(var1.readInt());
               break;
            case 4:
               var4 = a(var1);
         }

         this.b[var8] = var4;
      }

      int var9 = var1.readInt();
      this.c = new TransactionRecord[var9];

      for (int var11 = 0; var11 < var9; var11++) {
         this.c[var11] = new TransactionRecord(var1, this.h);
      }

      var1.readInt();
      var1.readInt();
      var1.readInt();
   }

   public final String toString() {
      return this.h;
   }

   private static String a(DataInputStream var0) throws IOException {
      short var1;
      if ((var1 = var0.readShort()) == 0) {
         return "";
      }

      a(var1 < 65536, "Too long str: " + var1);
      byte[] var2 = new byte[var1];
      int var3 = 0;
      int var4 = var1;

      for (int var5 = 0; var5 < 100 && var4 > 0; var5++) {
         int bytesRead = var0.read(var2, var3, var4);
         var3 += bytesRead;
         var4 -= bytesRead;
      }

      a(var4 == 0, "strload");
      return new String(var2, "utf-8");
   }

   private static void a(boolean var0, String var1) throws IOException {
      if (!var0) {
         throw new IOException("Couldn't load bytecode:" + var1);
      }
   }

   public static NetworkConfig a(InputStream var0, SecurityHelper var1) throws IOException {
      if (!(var0 instanceof DataInputStream)) {
         var0 = new DataInputStream(var0);
      }

      DataInputStream var2;
      a((var2 = (DataInputStream)var0).read() == 27, "Signature 1");
      var2.read();
      var2.read();
      var2.read();
      var2.read();
      var2.read();
      var2.read();
      var2.read();
      var2.read();
      var2.read();
      var2.read();
      var2.read();
      TransactionRecord var3 = new TransactionRecord(var2, null);
      return new NetworkConfig(var3, var1);
   }
}
