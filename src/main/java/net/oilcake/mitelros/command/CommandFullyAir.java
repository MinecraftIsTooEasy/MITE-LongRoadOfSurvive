package net.oilcake.mitelros.command;

import net.minecraft.ChatMessageComponent;
import net.minecraft.CommandBase;
import net.minecraft.EntityPlayer;
import net.minecraft.EnumChatFormatting;
import net.minecraft.ICommandSender;
import net.minecraft.World;

public class CommandFullyAir extends CommandBase {
   public String getCommandName() {
      return "fullyair";
   }

   public int getRequiredPermissionLevel() {
      return 2;
   }

   public String getCommandUsage(ICommandSender iCommandListener) {
      return "commands.fullyair.usage";
   }

   public void processCommand(ICommandSender iCommandListener, String[] strings) {
      EntityPlayer player = getCommandSenderAsPlayer(iCommandListener);
      World world = iCommandListener.getEntityWorld();
      int X = Integer.parseInt(strings[0]);
      int Y = Integer.parseInt(strings[1]);
      int Z = Integer.parseInt(strings[2]);
      iCommandListener.sendChatToPlayer(ChatMessageComponent.createFromText("清除了" + X * Y * Z + "个方块").setColor(EnumChatFormatting.WHITE));

      for(int tempX = -X; tempX <= X; ++tempX) {
         for(int tempY = -Y; tempY <= Y; ++tempY) {
            for(int tempZ = -Z; tempZ <= Z; ++tempZ) {
               world.setBlockToAir(player.getBlockPosX() + tempX, player.getBlockPosY() + tempY, player.getBlockPosZ() + tempZ);
            }
         }
      }

   }
}
