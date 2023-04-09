package net.oilcake.mitelros.util.events.command;

import net.minecraft.*;

public class CommandFullyAir extends CommandAbstract {
    @Override
    public String getCommandName() {
        return "fullyair";
    }

    @Override
    public int getRequiredPermissionLevel() {
        return 2;
    }
    @Override
    public String getCommandUsage(ICommandListener iCommandListener) {
        return "commands.fullyair.usage";
    }

    @Override
    public void processCommand(ICommandListener iCommandListener, String[] strings) {
        EntityPlayer player = getCommandSenderAsPlayer(iCommandListener);
        World world = iCommandListener.getEntityWorld();



        //决定X
        int X = Integer.parseInt(strings[0]);
        //决定Y
        int Y = Integer.parseInt(strings[1]);
        //决定Z
        int Z = Integer.parseInt(strings[2]);

        iCommandListener.sendChatToPlayer(ChatMessage.createFromText("清除了" + X*Y*Z + "个方块").setColor(EnumChatFormat.WHITE));

        for (int tempX =- X; tempX <= X; ++tempX) {
            for (int tempY =- Y; tempY <= Y; ++tempY) {
                for (int tempZ =- Z; tempZ <= Z; ++tempZ) {
                    world.setBlockToAir(player.getBlockPosX() + tempX, player.getBlockPosY() + tempY, player.getBlockPosZ() + tempZ);
                }
            }
        }
    }
}
