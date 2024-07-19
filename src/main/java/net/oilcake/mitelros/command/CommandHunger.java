package net.oilcake.mitelros.command;

import java.util.List;
import net.minecraft.ChatMessageComponent;
import net.minecraft.CommandBase;
import net.minecraft.EntityPlayer;
import net.minecraft.EnumChatFormatting;
import net.minecraft.ICommandSender;

public class CommandHunger extends CommandBase {
   public String getCommandName() {
      return "hunger";
   }

   public String getCommandUsage(ICommandSender iCommandListener) {
      return "commands.hunger.usage";
   }

   public List addTabCompletionOptions(ICommandSender par1ICommandSender, String[] par2ArrayOfStr) {
      return par2ArrayOfStr.length == 1 ? getListOfStringsMatchingLastWord(par2ArrayOfStr, new String[]{"client", "server"}) : null;
   }

   public void processCommand(ICommandSender iCommandListener, String[] strings) {
      EntityPlayer player = getCommandSenderAsPlayer(iCommandListener);
      float hunger = Float.parseFloat(strings[1]);
      if (strings[1] != null) {
         switch (strings[0]) {
            case "client":
               player.addHungerClientSide(hunger);
               iCommandListener.sendChatToPlayer(ChatMessageComponent.createFromText("目前玩家的饥饿为: " + player.getNutrition() + ", 饱和为: " + player.getSatiation() + ".").setColor(EnumChatFormatting.WHITE));
               break;
            case "server":
               player.addHungerServerSide(hunger);
               iCommandListener.sendChatToPlayer(ChatMessageComponent.createFromText("目前玩家的饥饿为: " + player.getNutrition() + ", 饱和为: " + player.getSatiation() + ".").setColor(EnumChatFormatting.WHITE));
               break;
            default:
               iCommandListener.sendChatToPlayer(ChatMessageComponent.createFromText("用法:/hunger <client|server> <num>").setColor(EnumChatFormatting.RED));
         }
      } else {
         iCommandListener.sendChatToPlayer(ChatMessageComponent.createFromText("用法:/hunger <client|server> <num>").setColor(EnumChatFormatting.RED));
      }

   }
}
