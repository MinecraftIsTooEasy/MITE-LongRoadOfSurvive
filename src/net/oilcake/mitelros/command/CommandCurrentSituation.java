package net.oilcake.mitelros.command;

import net.minecraft.*;

import java.util.List;

public class CommandCurrentSituation extends CommandAbstract {

    @Override
    public String getCommandName() {
        return "getcurrentsituation";
    }

    @Override
    public String getCommandUsage(ICommandListener iCommandListener) {
        return "commands.getcurrentsituation.usage";
    }
    @Override
    public List addTabCompletionOptions(ICommandListener par1ICommandSender, String[] par2ArrayOfStr) {
        return par2ArrayOfStr.length == 1 ? getListOfStringsMatchingLastWord(par2ArrayOfStr, "insulin","protein", "phytonutrients") : null;
    }
    @Override
    public void processCommand(ICommandListener iCommandListener, String[] strings) {
        ServerPlayer player = getCommandSenderAsPlayer(iCommandListener);
        switch (strings[0]) {
            case "insulin":
                iCommandListener.sendChatToPlayer(ChatMessage.createFromText("目前玩家的胰岛素抗性为" + player.getInsulinResistance()).setColor(EnumChatFormat.WHITE));
                break;
            case "protein":
                iCommandListener.sendChatToPlayer(ChatMessage.createFromText("目前玩家的蛋白质为" + player.getProtein()).setColor(EnumChatFormat.WHITE));
                break;
            case "phytonutrients":
                iCommandListener.sendChatToPlayer(ChatMessage.createFromText("目前玩家的植物营养素为" + player.getPhytonutrients()).setColor(EnumChatFormat.WHITE));
                break;
            default:
                iCommandListener.sendChatToPlayer(ChatMessage.createFromText("用法:/getcurrentsituation <insulin|protein|phytonutrients>").setColor(EnumChatFormat.RED));
                break;
        }
    }
}
