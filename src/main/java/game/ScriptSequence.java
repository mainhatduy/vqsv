package game;

import java.io.DataInputStream;
import java.util.Vector;

public final class ScriptSequence {
   private ScriptCommand[] commands;
   private byte eventId;
   private Vector activeCommands;
   private byte commandIndex;
   private byte state;
   private int packedSourceRoom;

   public final void read(DataInputStream var1, byte var2, int var3, String[] var4) throws java.io.IOException {
      this.eventId = (byte)var2;
      this.packedSourceRoom = var3;
      int commandCount = var1.readShort();
      this.commands = new ScriptCommand[commandCount];
      this.activeCommands = new Vector();

      for (int var6 = 0; var6 < commandCount; var6++) {
         this.commands[var6] = new ScriptCommand();
         this.commands[var6].read(var1, var4);
         this.activeCommands.addElement(this.commands[var6]);
      }

      this.state = 0;
   }

   public final byte getState() {
      return this.state;
   }

   public final void setState(byte var1) {
      this.state = var1;
   }

   public final byte getEventId() {
      return this.eventId;
   }

   public final void setCommandIndex(byte var1) {
      this.commandIndex = var1;
   }

   public final ScriptCommand getCurrentCommand() {
      return this.commandIndex >= this.activeCommands.size() ? null : (ScriptCommand)this.activeCommands.elementAt(this.commandIndex);
   }

   public final ScriptCommand getFirstCommand() {
      return (ScriptCommand)this.activeCommands.firstElement();
   }

   public final void advance() {
      this.getCurrentCommand();
      this.commandIndex++;
      if (this.commandIndex >= this.activeCommands.size()) {
         this.commandIndex = 0;
      }
   }

   public final int[] getSourceRoom() {
      if (this.packedSourceRoom == -1) {
         return null;
      }

      int[] var1;
      (var1 = new int[2])[0] = this.packedSourceRoom >> 8 & 0xFF;
      var1[1] = this.packedSourceRoom & 0xFF;
      return var1;
   }
}
