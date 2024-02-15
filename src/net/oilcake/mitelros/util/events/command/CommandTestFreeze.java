package net.oilcake.mitelros.util.events.command;

import net.minecraft.*;

public class CommandTestFreeze extends CommandAbstract {
    public CommandTestFreeze(){
    }
    @Override
    public String getCommandName() {
        return "testfreeze";
    }

    @Override
    public int getRequiredPermissionLevel() {
        return 0;
    }
    @Override
    public String getCommandUsage(ICommandListener iCommandListener) {
        return "commands.testfreeze.usage";
    }

    @Override
    public void processCommand(ICommandListener iCommandListener, String[] strings) {
        EntityPlayer player = getCommandSenderAsPlayer(iCommandListener);
        BiomeBase biome = player.worldObj.getBiomeGenForCoords(player.getBlockPosX(), player.getBlockPosZ());

        if(player.InFreeze()){
            iCommandListener.sendChatToPlayer(ChatMessage.createFromText("当前温度为:"+biome.temperature+"，玩家寒冷度为"+player.getFreezingCooldown()+"，玩家受到寒冷影响").setColor(EnumChatFormat.WHITE));
        }
        else{
            iCommandListener.sendChatToPlayer(ChatMessage.createFromText("当前温度为:"+biome.temperature+"，玩家寒冷度为"+player.getFreezingCooldown()+"，玩家未受到寒冷影响").setColor(EnumChatFormat.WHITE));
        }
    }
}
