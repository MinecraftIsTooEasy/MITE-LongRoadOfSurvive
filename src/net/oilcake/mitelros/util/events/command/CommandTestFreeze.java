package net.oilcake.mitelros.util.events.command;

import net.minecraft.*;

import static net.minecraft.EntityPlayer.*;

public class CommandTestFreeze extends CommandAbstract {
    @Override
    public String getCommandName() {
        return "testfreeze";
    }

    @Override
    public int getRequiredPermissionLevel() {
        return 2;
    }
    @Override
    public String getCommandUsage(ICommandListener iCommandListener) {
        return "commands.freeze.usage";
    }

    @Override
    public void processCommand(ICommandListener iCommandListener, String[] strings) {
        EntityPlayer player = getCommandSenderAsPlayer(iCommandListener);
        BiomeBase biome = player.worldObj.getBiomeGenForCoords(player.getBlockPosX(), player.getBlockPosZ());

        if(player.InFreeze()){
            iCommandListener.sendChatToPlayer(ChatMessage.createFromText("当前温度为:"+biome.temperature+"，玩家寒冷度为"+player.getFreezingCooldown()).setColor(EnumChatFormat.WHITE));
        }
        else{
            iCommandListener.sendChatToPlayer(ChatMessage.createFromText("当前温度为:"+biome.temperature+"，玩家寒冷度为"+player.getFreezingCooldown()).setColor(EnumChatFormat.WHITE));
        }
    }
}
