package net.oilcake.mitelros.command;

import net.minecraft.ChatMessageComponent;
import net.minecraft.CommandBase;
import net.minecraft.EntityPlayer;
import net.minecraft.EnumChatFormatting;
import net.minecraft.ICommandSender;
import net.minecraft.World;

public class CommandStoneAir extends CommandBase {
   public String getCommandName() {
      return "stoneair";
   }

   public int getRequiredPermissionLevel() {
      return 2;
   }

   public String getCommandUsage(ICommandSender iCommandListener) {
      return "commands.stoneair.usage";
   }

   public void processCommand(ICommandSender iCommandListener, String[] strings) {
      EntityPlayer player = getCommandSenderAsPlayer(iCommandListener);
      World world = iCommandListener.getEntityWorld();
      int X = Integer.parseInt(strings[0]);
      int Y = Integer.parseInt(strings[1]);
      int Z = Integer.parseInt(strings[2]);
      int block_id_to_clear = Integer.parseInt(strings[3]);
      int executes = 0;

      for(int tempX = -X; tempX <= X; ++tempX) {
         for(int tempY = -Y; tempY <= Y; ++tempY) {
            for(int tempZ = -Z; tempZ <= Z; ++tempZ) {
               int block_id = world.getBlockId(player.getBlockPosX() + tempX, player.getBlockPosY() + tempY, player.getBlockPosZ() + tempZ);
               if (block_id == block_id_to_clear) {
                  world.setBlockToAir(player.getBlockPosX() + tempX, player.getBlockPosY() + tempY, player.getBlockPosZ() + tempZ);
                  ++executes;
               }
            }
         }
      }

      iCommandListener.sendChatToPlayer(ChatMessageComponent.createFromText("清除了" + executes + "个方块").setColor(EnumChatFormatting.WHITE));
   }
}
