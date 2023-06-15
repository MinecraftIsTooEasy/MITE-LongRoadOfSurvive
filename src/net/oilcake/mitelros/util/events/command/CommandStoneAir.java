package net.oilcake.mitelros.util.events.command;

import net.minecraft.*;

public class CommandStoneAir extends CommandAbstract {
    @Override
    public String getCommandName() {
        return "stoneair";
    }

    @Override
    public int getRequiredPermissionLevel() {
        return 2;
    }
    @Override
    public String getCommandUsage(ICommandListener iCommandListener) {
        return "commands.stoneair.usage";
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
        //决定要清除的方块id
        int block_id_to_clear = Integer.parseInt(strings[3]);
        int executes = 0;
        for (int tempX =- X; tempX <= X; ++tempX) {
            for (int tempY =- Y; tempY <= Y; ++tempY) {
                for (int tempZ =- Z; tempZ <= Z; ++tempZ) {
                    int block_id = world.getBlockId(player.getBlockPosX() + tempX, player.getBlockPosY() + tempY, player.getBlockPosZ() + tempZ);
                    if(block_id == block_id_to_clear){
                        world.setBlockToAir(player.getBlockPosX() + tempX, player.getBlockPosY() + tempY, player.getBlockPosZ() + tempZ);
                        executes++;
                    }
                }
            }
        }
        iCommandListener.sendChatToPlayer(ChatMessage.createFromText("清除了" + executes + "个方块").setColor(EnumChatFormat.WHITE));
    }
}
