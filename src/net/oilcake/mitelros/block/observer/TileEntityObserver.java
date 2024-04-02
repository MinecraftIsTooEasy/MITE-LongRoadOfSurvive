package net.oilcake.mitelros.block.observer;

import net.minecraft.*;

public class TileEntityObserver extends TileEntity {
    public int prev_meta;
    public int prev_id;
    public int detect_delay;
    public TileEntityObserver(){

    }
    public void writeToNBT(NBTTagCompound par1NBTTagCompound) {
        super.writeToNBT(par1NBTTagCompound);
        par1NBTTagCompound.setInteger("prev_meta", this.prev_meta);
        par1NBTTagCompound.setInteger("prev_id", this.prev_id);
        par1NBTTagCompound.setInteger("detect_delay", this.detect_delay);
    }

    public void readFromNBT(NBTTagCompound par1NBTTagCompound) {
        super.readFromNBT(par1NBTTagCompound);
        this.prev_meta = par1NBTTagCompound.getInteger("prev_meta");
        this.prev_id = par1NBTTagCompound.getInteger("prev_id");
        this.detect_delay = par1NBTTagCompound.getInteger("detect_delay");
    }
    public boolean needsActivation() {
        EnumFace facing = getFace(this.getObserver().getDirectionFacing(this.worldObj.getBlockMetadata(this.xCoord, this.yCoord, this.zCoord)));
        World var10000 = this.worldObj;
        int[] neighbor_coords = World.getNeighboringBlockCoords(this.xCoord, this.yCoord, this.zCoord, facing);
        return this.worldObj.getBlockId(neighbor_coords[0], neighbor_coords[1], neighbor_coords[2]) != prev_id || this.worldObj.getBlockMetadata(neighbor_coords[0], neighbor_coords[1], neighbor_coords[2]) != prev_meta;
    }
    public BlockObserver getObserver() {
        return (BlockObserver) this.getBlockType();
    }
    final EnumFace getFace(EnumDirection direction) {
        if (direction == EnumDirection.UP) {
            return EnumFace.TOP;
        } else if (direction == EnumDirection.DOWN) {
            return EnumFace.BOTTOM;
        } else if (direction == EnumDirection.SOUTH) {
            return EnumFace.SOUTH;
        } else if (direction == EnumDirection.NORTH) {
            return EnumFace.NORTH;
        } else {
            return direction == EnumDirection.EAST ? EnumFace.EAST : EnumFace.WEST;
        }
    }
    @Override
    public void updateEntity() {
//        if(detect_delay > 0){
//            detect_delay--;
//            return;
//        }
        if(!this.worldObj.isRemote && needsActivation()){
            BlockObserver.updateState(true, this.worldObj, this.xCoord, this.yCoord, this.zCoord);
//            System.out.println("switch on.");
//            this.worldObj.playSoundEffect((double)((float)this.xCoord + 0.5F), (double)((float)this.yCoord + 0.5F), (double)((float)this.zCoord + 0.5F), "imported.random.sizzle");
//            this.detect_delay = 20;
        }else if(!this.worldObj.isRemote && !needsActivation() && this.getObserver().isActivated(this.worldObj.getBlockMetadata(this.xCoord, this.yCoord, this.zCoord))){
            BlockObserver.updateState(false, this.worldObj, this.xCoord, this.yCoord, this.zCoord);
//            System.out.println("switch off.");
//            this.detect_delay = 20;
        }
        EnumFace facing = getFace(this.getObserver().getDirectionFacing(this.worldObj.getBlockMetadata(this.xCoord, this.yCoord, this.zCoord)));
        World var10000 = this.worldObj;
        int[] neighbor_coords = World.getNeighboringBlockCoords(this.xCoord, this.yCoord, this.zCoord, facing);
        this.prev_id = this.worldObj.getBlockId(neighbor_coords[0], neighbor_coords[1], neighbor_coords[2]);
        this.prev_meta = this.worldObj.getBlockMetadata(neighbor_coords[0], neighbor_coords[1], neighbor_coords[2]);
    }
}
