package net.oilcake.mitelros.util.events.command;

import net.minecraft.*;

import java.util.List;

public class CommandAddCurrentSituation extends CommandAbstract {

    @Override
    public String getCommandName() {
        return "addcurrentsituation";
    }

    @Override
    public String getCommandUsage(ICommandListener iCommandListener) {
        return "commands.addcurrentsituation.usage";
    }
    @Override
    public List addTabCompletionOptions(ICommandListener par1ICommandSender, String[] par2ArrayOfStr) {
        return par2ArrayOfStr.length == 1 ? getListOfStringsMatchingLastWord(par2ArrayOfStr, "insulin","protein", "phytonutrients") : null;
    }
    @Override
    public void processCommand(ICommandListener iCommandListener, String[] strings) {
        ServerPlayer player = getCommandSenderAsPlayer(iCommandListener);
        if(strings[1]!=null){
            switch (strings[0]) {
                case "insulin":
                    player.addInsulinResistance(Integer.parseInt(strings[1]));
                    iCommandListener.sendChatToPlayer(ChatMessage.createFromText("目前玩家的胰岛素抗性为" + player.getInsulinResistance()).setColor(EnumChatFormat.WHITE));
                    break;
                case "protein":
                    player.addProtein(Integer.parseInt(strings[1]));
                    iCommandListener.sendChatToPlayer(ChatMessage.createFromText("目前玩家的蛋白质为" + player.getProtein()).setColor(EnumChatFormat.WHITE));
                    break;
                case "phytonutrients":
                    player.addPhytonutrients(Integer.parseInt(strings[1]));
                    iCommandListener.sendChatToPlayer(ChatMessage.createFromText("目前玩家的植物营养素为" + player.getPhytonutrients()).setColor(EnumChatFormat.WHITE));
                    break;
                default:
                    iCommandListener.sendChatToPlayer(ChatMessage.createFromText("用法:/addcurrentsituation <insulin|protein|phytonutrients> <num>").setColor(EnumChatFormat.RED));
                    break;
            }
        }
        else{
            iCommandListener.sendChatToPlayer(ChatMessage.createFromText("用法:/addcurrentsituation <insulin|protein|phytonutrients> <num>").setColor(EnumChatFormat.RED));
        }
    }
}
