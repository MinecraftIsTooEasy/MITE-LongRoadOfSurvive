package net.oilcake.mitelros.command;

import java.util.List;
import net.minecraft.ChatMessageComponent;
import net.minecraft.CommandBase;
import net.minecraft.EntityPlayer;
import net.minecraft.EnumChatFormatting;
import net.minecraft.ICommandSender;

public class CommandWater extends CommandBase {
   public String getCommandName() {
      return "water";
   }

   public int getRequiredPermissionLevel() {
      return 2;
   }

   public String getCommandUsage(ICommandSender iCommandListener) {
      return "commands.water.usage";
   }

   public List addTabCompletionOptions(ICommandSender par1ICommandSender, String[] par2ArrayOfStr) {
      return par2ArrayOfStr.length == 1 ? getListOfStringsMatchingLastWord(par2ArrayOfStr, new String[]{"get", "add"}) : null;
   }

   public void processCommand(ICommandSender iCommandListener, String[] strings) {
      EntityPlayer player = getCommandSenderAsPlayer(iCommandListener);
      switch (strings[0]) {
         case "get":
            iCommandListener.sendChatToPlayer(ChatMessageComponent.createFromText("水数值为" + player.getWater()).setColor(EnumChatFormatting.WHITE));
            break;
         case "add":
            player.addWater(Integer.parseInt(strings[1]));
            iCommandListener.sendChatToPlayer(ChatMessageComponent.createFromText("水数值现在为" + player.getWater()).setColor(EnumChatFormatting.WHITE));
            break;
         default:
            iCommandListener.sendChatToPlayer(ChatMessageComponent.createFromText("用法:/water <get|add>").setColor(EnumChatFormatting.RED));
      }

   }
}
