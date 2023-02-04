package net.oilcake.mitelros.util.events.command;

import net.minecraft.*;

import java.util.List;

public class CommandWater extends CommandAbstract {
    @Override
    public String getCommandName() {
        return "water";
    }

    @Override
    public int getRequiredPermissionLevel() {
        return 2;
    }
    @Override
    public String getCommandUsage(ICommandListener iCommandListener) {
        return "commands.water.usage";
    }
    @Override
    public List addTabCompletionOptions(ICommandListener par1ICommandSender, String[] par2ArrayOfStr) {
        return par2ArrayOfStr.length == 1 ? getListOfStringsMatchingLastWord(par2ArrayOfStr, "get", "add", "remove") : null;
    }

    @Override
    public void processCommand(ICommandListener iCommandListener, String[] strings) {
        EntityPlayer player = getCommandSenderAsPlayer(iCommandListener);
        //World world = iCommandListener.getEntityWorld();
        switch (strings[0]) {
            case "get":
                iCommandListener.sendChatToPlayer(ChatMessage.createFromText("水数值为" + player.getWater()).setColor(EnumChatFormat.LIGHT_GRAY));
                break;
            case "add":
                player.addWater();
                iCommandListener.sendChatToPlayer(ChatMessage.createFromText("水数值+10为" + player.getWater()).setColor(EnumChatFormat.LIGHT_GRAY));
                break;
            case "remove":
                player.removeWater();
                iCommandListener.sendChatToPlayer(ChatMessage.createFromText("水数值-10为" + player.getWater()).setColor(EnumChatFormat.LIGHT_GRAY));
                break;
            default:
                iCommandListener.sendChatToPlayer(ChatMessage.createFromText("请使用get或add").setColor(EnumChatFormat.LIGHT_GRAY));
                break;
        }
    }
}
