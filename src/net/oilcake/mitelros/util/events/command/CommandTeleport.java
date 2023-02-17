package net.oilcake.mitelros.util.events.command;

import net.minecraft.*;

public class CommandTeleport extends CommandAbstract {
    @Override
    public String getCommandName() {
        return "teleport";
    }

    @Override
    public int getRequiredPermissionLevel() {
        return 2;
    }
    @Override
    public String getCommandUsage(ICommandListener iCommandListener) {
        return "commands.teleport.usage";
    }

    @Override
    public void processCommand(ICommandListener iCommandListener, String[] strings) {
        EntityPlayer player = getCommandSenderAsPlayer(iCommandListener);

        player.setPositionAndUpdate(Integer.parseInt(strings[0]), Integer.parseInt(strings[1]), Integer.parseInt(strings[2]));

        iCommandListener.sendChatToPlayer(ChatMessage.createFromText("已传送至" + Integer.parseInt(strings[0]) + Integer.parseInt(strings[1]) + Integer.parseInt(strings[2]) ).setColor(EnumChatFormat.WHITE));

    }
}
