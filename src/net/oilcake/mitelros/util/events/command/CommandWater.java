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
        return par2ArrayOfStr.length == 1 ? getListOfStringsMatchingLastWord(par2ArrayOfStr, "get", "add") : null;
    }

    @Override
    public void processCommand(ICommandListener iCommandListener, String[] strings) {
        EntityPlayer player = getCommandSenderAsPlayer(iCommandListener);
        //World world = iCommandListener.getEntityWorld();
        switch (strings[0]) {
            case "get":
                iCommandListener.sendChatToPlayer(ChatMessage.createFromText("水数值为" + player.getWater()).setColor(EnumChatFormat.WHITE));
                break;
            case "add":
                player.addWater(Integer.parseInt(strings[1]));
                iCommandListener.sendChatToPlayer(ChatMessage.createFromText("水数值现在为" + player.getWater()).setColor(EnumChatFormat.WHITE));
                break;
            default:
                iCommandListener.sendChatToPlayer(ChatMessage.createFromText("用法:/water <get|add>").setColor(EnumChatFormat.RED));
                break;
        }
    }
}
