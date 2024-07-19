package net.oilcake.mitelros.command;

import net.minecraft.ChatMessageComponent;
import net.minecraft.CommandBase;
import net.minecraft.EntityPlayer;
import net.minecraft.EnumChatFormatting;
import net.minecraft.ICommandSender;

public class CommandTeleport extends CommandBase {
   public String getCommandName() {
      return "teleport";
   }

   public int getRequiredPermissionLevel() {
      return 2;
   }

   public String getCommandUsage(ICommandSender iCommandListener) {
      return "commands.teleport.usage";
   }

   public void processCommand(ICommandSender iCommandListener, String[] strings) {
      EntityPlayer player = getCommandSenderAsPlayer(iCommandListener);
      player.setPositionAndUpdate((double)Integer.parseInt(strings[0]), (double)Integer.parseInt(strings[1]), (double)Integer.parseInt(strings[2]));
      iCommandListener.sendChatToPlayer(ChatMessageComponent.createFromText("已传送至" + Integer.parseInt(strings[0]) + Integer.parseInt(strings[1]) + Integer.parseInt(strings[2])).setColor(EnumChatFormatting.WHITE));
   }
}
