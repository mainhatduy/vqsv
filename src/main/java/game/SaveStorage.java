package game;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import javax.microedition.rms.RecordStore;

public final class SaveStorage {
   private String storeName;
   private RecordStore recordStore;
   private int recordCount;
   private int recordId = 1;
   public boolean a;
   public byte[] extraData;

   public SaveStorage(String var1) {
      this.storeName = var1;
      if (this.getRecordCount() == 0) {
         this.a(1);
      } else {
         if (this.getRecordCount() != 1) {
            this.deleteStore();
            this.a(1);
         }
      }
   }

   private void a(int var1) {
      try {
         byte[] var7 = new byte[]{0};

         for (int var2 = 0; var2 <= 0; var2++) {
            int var5 = var7.length;
            byte[] var4 = var7;
            this.a(this.recordId, var4, 0, var5, 1);
         }
      } catch (Exception var6) {
      }
   }

   private int getRecordCount() {
      this.a(this.recordId, null, 0, 0, 0);
      return this.recordCount;
   }

   public final byte[] read() {
      this.extraData = null;
      byte[] var1;
      if ((var1 = this.a(1, null, 0, 0, 3)) != null && var1.length >= 3 && var1[1] == 44 && var1[2] == 79) {
         try {
            ByteArrayInputStream var5 = new ByteArrayInputStream(var1);
            DataInputStream var4;
            (var4 = new DataInputStream(var5)).readByte();
            var4.readShort();
            byte[] var6 = new byte[var4.readInt()];
            var4.read(var6);
            if (var4.available() > 0) {
               this.extraData = new byte[var4.available()];
               var4.read(this.extraData);
            }

            this.d();
            return var6;
         } catch (Exception var3) {
            var3.printStackTrace();
            return null;
         }
      } else {
         byte[] var2 = new byte[var1.length - 1];
         System.arraycopy(var1, 1, var2, 0, var2.length);
         this.d();
         return var2;
      }
   }

   public final void write(ByteArrayOutputStream var1) {
      byte[] var2 = var1.toByteArray();
      if (!this.a) {
         byte[] var3 = new byte[var2.length + 1];
         System.arraycopy(var2, 0, var3, 1, var2.length);
         var3[0] = 1;
         int var4 = var3.length;
         this.a(this.recordId, var3, 0, var4, 4);
         this.d();
      } else {
         this.extraData = var2;
      }
   }

   private void d() {
      this.a(0, null, 0, 0, 6);
   }

   public final void deleteStore() {
      this.a(0, null, 0, 0, 7);
   }

   private byte[] a(int var1, byte[] var2, int var3, int var4, int var5) {
      try {
         if (var5 == 7 || var5 == 6) {
            if (this.recordStore != null) {
               this.recordStore.closeRecordStore();
               this.recordStore = null;
            }

            if (var5 == 7) {
               RecordStore.deleteRecordStore(this.storeName);
            }

            return null;
         }

         if (this.recordStore == null) {
            this.recordStore = RecordStore.openRecordStore(this.storeName, true);
         }

         switch (var5) {
            case 0:
               this.recordCount = this.recordStore.getNumRecords();
               break;
            case 1:
               this.recordStore.addRecord(var2, 0, var4);
               break;
            case 2:
               this.recordStore.deleteRecord(var1);
               break;
            case 3:
               return this.recordStore.getRecord(var1);
            case 4:
               this.recordStore.setRecord(var1, var2, 0, var4);
         }
      } catch (Exception var6) {
         var6.printStackTrace();
      }

      return null;
   }

   public final void a(byte[] var1) {
      if (this.extraData != null) {
         try {
            ByteArrayOutputStream var2 = new ByteArrayOutputStream();
            DataOutputStream var3;
            (var3 = new DataOutputStream(var2)).writeByte(1);
            var3.writeByte(44);
            var3.writeByte(79);
            var3.writeInt(this.extraData.length);
            var3.write(this.extraData);
            var3.write(var1);
            var3.flush();
            this.extraData = null;
            this.a(this.recordId, var2.toByteArray(), 0, var2.size(), 4);
            this.d();
         } catch (Exception var4) {
            var4.printStackTrace();
         }
      }
   }
}
