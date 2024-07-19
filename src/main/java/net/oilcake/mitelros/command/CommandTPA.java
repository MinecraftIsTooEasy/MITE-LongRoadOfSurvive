package net.oilcake.mitelros.command;

import java.util.List;
import net.minecraft.CommandBase;
import net.minecraft.ICommandSender;
import net.minecraft.server.MinecraftServer;

public class CommandTPA extends CommandBase {
   public String getCommandName() {
      return "tpa";
   }

   public int getRequiredPermissionLevel() {
      return -1;
   }

   public String getCommandUsage(ICommandSender iCommandListener) {
      return "commands.tpa.usage";
   }

   public void processCommand(ICommandSender iCommandListener, String[] strings) {
      notifyAdmins(iCommandListener, "Command '" + this.getCommandName() + "' not available!", new Object[0]);
   }

   public List addTabCompletionOptions(ICommandSender par1ICommandSender, String[] par2ArrayOfStr) {
      return par2ArrayOfStr.length != 1 && par2ArrayOfStr.length != 2 ? null : getListOfStringsMatchingLastWord(par2ArrayOfStr, MinecraftServer.getServer().getAllUsernames());
   }

   public boolean isUsernameIndex(String[] par1ArrayOfStr, int par2) {
      return par2 == 0;
   }
}
