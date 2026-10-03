package game;

import java.io.DataInputStream;

public final class ScriptCommand {
   private short opcode;
   private short[] numericArguments;
   private String[] textArguments;

   public final void read(DataInputStream var1, String[] var2) throws java.io.IOException {
      this.opcode = var1.readShort();
      byte var3 = var1.readByte();
      byte var4 = var1.readByte();
      this.numericArguments = new short[var4];

      for (int var5 = 0; var5 < var4; var5++) {
         this.numericArguments[var5] = var1.readShort();
      }

      if (var2 != null) {
         this.textArguments = new String[var3 - var4];

         for (int var7 = 0; var7 < var3 - var4; var7++) {
            short var6 = var1.readShort();
            this.textArguments[var7] = var2[var6];
         }
      }
   }

   public final short getOpcode() {
      return this.opcode;
   }

   public final short[] getNumericArguments() {
      return this.numericArguments;
   }

   public final String[] getTextArguments() {
      return this.textArguments;
   }
}
