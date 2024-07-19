package net.oilcake.mitelros.command;

import ink.huix.iinjected.EntityPlayerKt;
import net.minecraft.BiomeGenBase;
import net.minecraft.ChatMessageComponent;
import net.minecraft.CommandBase;
import net.minecraft.EntityPlayer;
import net.minecraft.EnumChatFormatting;
import net.minecraft.ICommandSender;

public class CommandTestFreeze extends CommandBase {
   public String getCommandName() {
      return "testfreeze";
   }

   public int getRequiredPermissionLevel() {
      return 0;
   }

   public String getCommandUsage(ICommandSender iCommandListener) {
      return "commands.testfreeze.usage";
   }

   public void processCommand(ICommandSender iCommandListener, String[] strings) {
      EntityPlayer player = getCommandSenderAsPlayer(iCommandListener);
      BiomeGenBase biome = player.worldObj.getBiomeGenForCoords(player.getBlockPosX(), player.getBlockPosZ());
      iCommandListener.sendChatToPlayer(ChatMessageComponent.createFromText("玩家当前体温为" +
              player.getBodyTemperature() + "℃，玩家目前可接受温度范围为：" +
              player.getTemperatureTolerance()[0] + " ~ " + player.getTemperatureTolerance()[1] + "，环境温度为" +
              player.getEnvironmentTemperature(biome.temperature, player.getWorld().getTotalWorldTime()) + "℃。").setColor(EnumChatFormatting.WHITE));
   }
}
