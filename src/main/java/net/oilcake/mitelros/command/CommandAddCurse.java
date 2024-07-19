package net.oilcake.mitelros.command;

import net.minecraft.ChatMessageComponent;
import net.minecraft.CommandBase;
import net.minecraft.Curse;
import net.minecraft.EntityWitch;
import net.minecraft.EnumChatFormatting;
import net.minecraft.ICommandSender;
import net.minecraft.ServerPlayer;

public class CommandAddCurse extends CommandBase {
   public String getCommandName() {
      return "curse";
   }

   public String getCommandUsage(ICommandSender iCommandListener) {
      return "commands.curse.usage";
   }

   public int getRequiredPermissionLevel() {
      return 2;
   }

   public void processCommand(ICommandSender iCommandListener, String[] strings) {
      if (Integer.parseInt(strings[0]) >= 0 && Integer.parseInt(strings[0]) <= Curse.cursesList.length) {
         ServerPlayer player = getCommandSenderAsPlayer(iCommandListener);
         EntityWitch temp = new EntityWitch(player.worldObj);
         player.worldObj.getAsWorldServer().addCurse(player.getAsEntityPlayerMP(), temp, Curse.cursesList[Integer.parseInt(strings[0])], 0);
         player.learnCurseEffect();
      } else {
         iCommandListener.sendChatToPlayer(ChatMessageComponent.createFromText("用法:/curse <num>;<num> 为 0 ~ " + Curse.cursesList.length).setColor(EnumChatFormatting.RED));
      }

   }
}
