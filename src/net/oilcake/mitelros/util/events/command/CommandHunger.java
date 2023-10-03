package net.oilcake.mitelros.util.events.command;

import net.minecraft.*;

import java.util.List;

public class CommandHunger extends CommandAbstract {
    @Override
    public String getCommandName() {
        return "hunger";
    }

    @Override
    public String getCommandUsage(ICommandListener iCommandListener) {
        return "commands.hunger.usage";
    }
    @Override
    public List addTabCompletionOptions(ICommandListener par1ICommandSender, String[] par2ArrayOfStr) {
        return par2ArrayOfStr.length == 1 ? getListOfStringsMatchingLastWord(par2ArrayOfStr, "client","server") : null;
    }
    @Override
    public void processCommand(ICommandListener iCommandListener, String[] strings) {
        EntityPlayer player = getCommandSenderAsPlayer(iCommandListener);
        float hunger = Float.parseFloat(strings[1]);
        if(strings[1]!=null){
            switch (strings[0]) {
                case "client":
                    player.addHungerClientSide(hunger);
                    iCommandListener.sendChatToPlayer(ChatMessage.createFromText("目前玩家的饥饿为: " + player.getNutrition() + ", 饱和为: "+ player.getSatiation() + ".").setColor(EnumChatFormat.WHITE));
                    break;
                case "server":
                    player.addHungerServerSide(hunger);
                    iCommandListener.sendChatToPlayer(ChatMessage.createFromText("目前玩家的饥饿为: " + player.getNutrition() + ", 饱和为: "+ player.getSatiation() + ".").setColor(EnumChatFormat.WHITE));
                    break;
                default:
                    iCommandListener.sendChatToPlayer(ChatMessage.createFromText("用法:/hunger <client|server> <num>").setColor(EnumChatFormat.RED));
                    break;
            }
        }
        else{
            iCommandListener.sendChatToPlayer(ChatMessage.createFromText("用法:/hunger <client|server> <num>").setColor(EnumChatFormat.RED));
        }
    }
}
