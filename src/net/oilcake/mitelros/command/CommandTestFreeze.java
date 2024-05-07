package net.oilcake.mitelros.command;

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
        iCommandListener.sendChatToPlayer(ChatMessage.createFromText("玩家当前体温为" + player.BodyTemperature + "℃，玩家目前可接受温度范围为：" + player.getTemperatureTolerance()[0] + " ~ " + player.getTemperatureTolerance()[1] + "，环境温度为" + player.getEnvironmentTemperature(biome.temperature,player.getWorld().getTotalWorldTime()) + "℃。").setColor(EnumChatFormat.WHITE));
    }
}
