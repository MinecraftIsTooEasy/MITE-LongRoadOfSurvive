package net.oilcake.mitelros.block.receiver;

import net.minecraft.Block;
import net.minecraft.TileEntity;

public class TileEntityReceiver extends TileEntity {
   public int detect_delay;

   public void updateEntity() {
      if (this.worldObj != null && !this.worldObj.isRemote) {
         Block block = this.getBlockType();
         if (block != null && block instanceof BlockReceiver) {
            ((BlockReceiver)block).updateSignalLevel(this.worldObj, this.xCoord, this.yCoord, this.zCoord);
         }
      }

   }
}
