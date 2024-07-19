package net.oilcake.mitelros.command;

import net.minecraft.ChatMessageComponent;
import net.minecraft.CommandBase;
import net.minecraft.EntityPlayer;
import net.minecraft.EnumChatFormatting;
import net.minecraft.ICommandSender;
import net.minecraft.ItemStack;

public class CommandDamage extends CommandBase {
   public String getCommandName() {
      return "damage";
   }

   public int getRequiredPermissionLevel() {
      return 2;
   }

   public String getCommandUsage(ICommandSender iCommandListener) {
      return "commands.damage.usage";
   }

   public void processCommand(ICommandSender iCommandListener, String[] strings) {
      EntityPlayer player = getCommandSenderAsPlayer(iCommandListener);
      ItemStack holding = player.getHeldItemStack();
      if (holding != null && Integer.parseInt(strings[0]) >= 0 && Integer.parseInt(strings[0]) < holding.getMaxDamage()) {
         holding.setItemDamage(Integer.parseInt(strings[0]));
      } else {
         iCommandListener.sendChatToPlayer(ChatMessageComponent.createFromText("用法:/damage <num>;<num> 为 0 ~ " + player.getHeldItemStack().getMaxDamage() + ";请确认手中物品合法。").setColor(EnumChatFormatting.RED));
      }

   }
}
