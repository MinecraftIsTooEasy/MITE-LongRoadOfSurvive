package net.oilcake.mitelros.command;

import net.minecraft.ChatMessageComponent;
import net.minecraft.CommandBase;
import net.minecraft.Entity;
import net.minecraft.EntityList;
import net.minecraft.EntityLiving;
import net.minecraft.EntityLivingData;
import net.minecraft.EntityPlayer;
import net.minecraft.EnumChatFormatting;
import net.minecraft.ICommandSender;
import net.minecraft.World;

public class CommandXsummon extends CommandBase {
   public String getCommandName() {
      return "xsummon";
   }

   public int getRequiredPermissionLevel() {
      return 2;
   }

   public String getCommandUsage(ICommandSender iCommandListener) {
      return "commands.xsummon.usage";
   }

   public void processCommand(ICommandSender iCommandListener, String[] strings) {
      EntityPlayer player = getCommandSenderAsPlayer(iCommandListener);
      World world = iCommandListener.getEntityWorld();
      Entity entity = EntityList.createEntityByID(Integer.parseInt(strings[0]), world);
      if (entity != null) {
         entity.setPosition(player.posX, player.posY, player.posZ);
         if (entity instanceof EntityLiving) {
            ((EntityLiving)entity).onSpawnWithEgg((EntityLivingData)null);
         }

         world.spawnEntityInWorld(entity);
         iCommandListener.sendChatToPlayer(ChatMessageComponent.createFromText("已生成实体 " + entity).setColor(EnumChatFormatting.WHITE));
      } else {
         iCommandListener.sendChatToPlayer(ChatMessageComponent.createFromText("无法生成实体:ID为 " + Integer.valueOf(strings[0]) + " 的实体不存在!").setColor(EnumChatFormatting.RED));
      }

   }
}
