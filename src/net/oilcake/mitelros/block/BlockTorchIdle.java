package net.oilcake.mitelros.block;

import net.minecraft.BlockBreakInfo;
import net.minecraft.BlockTorch;
import net.minecraft.EnumParticle;
import net.minecraft.World;

import java.util.Random;

public class BlockTorchIdle extends BlockTorch {
    protected BlockTorchIdle(int par1) {
        super(par1);
    }
    public int dropBlockAsEntityItem(BlockBreakInfo info) {
        return 0;
    }
    @Override
    public boolean updateTick(World world, int x, int y, int z, Random random)
    {
        if (super.updateTick(world, x, y, z, random))
        {
            return true;
        }
        else {
            int ran = random.nextInt(512);
            if(ran == 0 && world.getBlockId(x,y,z) == Blocks.torchWoodIdle.blockID){
                world.setBlock(x, y, z, Blocks.torchWoodDistinguished.blockID, world.getBlockMetadata(x,y,z), 2);
            }
            return false;
        }
    }
    public void b(World par1World, int par2, int par3, int par4, Random par5Random) {
        if(par1World.getBlockId(par2, par3, par4) == Blocks.torchWoodDistinguished.blockID){
            return;
        }
        int var6 = par1World.getBlockMetadata(par2, par3, par4);
        double var7 = (double)par2 + 0.5;
        double var9 = (double)par3 + 0.7;
        double var11 = (double)par4 + 0.5;
        double var13 = 0.2199999988079071;
        double var15 = 0.27000001072883606;
        if (var6 == 1) {
            par1World.spawnParticle(EnumParticle.smoke, var7 - var15, var9 + var13, var11, 0.0, 0.0, 0.0);
            par1World.spawnParticle(EnumParticle.flame, var7 - var15, var9 + var13, var11, 0.0, 0.0, 0.0);
        } else if (var6 == 2) {
            par1World.spawnParticle(EnumParticle.smoke, var7 + var15, var9 + var13, var11, 0.0, 0.0, 0.0);
            par1World.spawnParticle(EnumParticle.flame, var7 + var15, var9 + var13, var11, 0.0, 0.0, 0.0);
        } else if (var6 == 3) {
            par1World.spawnParticle(EnumParticle.smoke, var7, var9 + var13, var11 - var15, 0.0, 0.0, 0.0);
            par1World.spawnParticle(EnumParticle.flame, var7, var9 + var13, var11 - var15, 0.0, 0.0, 0.0);
        } else if (var6 == 4) {
            par1World.spawnParticle(EnumParticle.smoke, var7, var9 + var13, var11 + var15, 0.0, 0.0, 0.0);
            par1World.spawnParticle(EnumParticle.flame, var7, var9 + var13, var11 + var15, 0.0, 0.0, 0.0);
        } else {
            par1World.spawnParticle(EnumParticle.smoke, var7, var9, var11, 0.0, 0.0, 0.0);
            par1World.spawnParticle(EnumParticle.flame, var7, var9, var11, 0.0, 0.0, 0.0);
        }

    }
}
