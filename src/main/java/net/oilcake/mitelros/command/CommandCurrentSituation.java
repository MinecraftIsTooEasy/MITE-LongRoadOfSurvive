package net.oilcake.mitelros.command;

import java.util.List;
import net.minecraft.ChatMessageComponent;
import net.minecraft.CommandBase;
import net.minecraft.EnumChatFormatting;
import net.minecraft.ICommandSender;
import net.minecraft.ServerPlayer;

public class CommandCurrentSituation extends CommandBase {
   public String getCommandName() {
      return "getcurrentsituation";
   }

   public String getCommandUsage(ICommandSender iCommandListener) {
      return "commands.getcurrentsituation.usage";
   }

   public List addTabCompletionOptions(ICommandSender par1ICommandSender, String[] par2ArrayOfStr) {
      return par2ArrayOfStr.length == 1 ? getListOfStringsMatchingLastWord(par2ArrayOfStr, new String[]{"insulin", "protein", "phytonutrients"}) : null;
   }

   public void processCommand(ICommandSender iCommandListener, String[] strings) {
      ServerPlayer player = getCommandSenderAsPlayer(iCommandListener);
      switch (strings[0]) {
         case "insulin":
            iCommandListener.sendChatToPlayer(ChatMessageComponent.createFromText("目前玩家的胰岛素抗性为" + player.getInsulinResistance()).setColor(EnumChatFormatting.WHITE));
            break;
         case "protein":
            iCommandListener.sendChatToPlayer(ChatMessageComponent.createFromText("目前玩家的蛋白质为" + player.getProtein()).setColor(EnumChatFormatting.WHITE));
            break;
         case "phytonutrients":
            iCommandListener.sendChatToPlayer(ChatMessageComponent.createFromText("目前玩家的植物营养素为" + player.getPhytonutrients()).setColor(EnumChatFormatting.WHITE));
            break;
         default:
            iCommandListener.sendChatToPlayer(ChatMessageComponent.createFromText("用法:/getcurrentsituation <insulin|protein|phytonutrients>").setColor(EnumChatFormatting.RED));
      }

   }
}
