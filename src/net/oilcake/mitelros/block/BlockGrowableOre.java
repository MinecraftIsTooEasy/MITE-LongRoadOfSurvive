package net.oilcake.mitelros.block;

import net.minecraft.BlockOre;
import net.minecraft.Material;
import net.minecraft.World;
import net.oilcake.mitelros.block.Blocks;

import java.util.Random;

public class BlockGrowableOre extends BlockOre {
    public BlockGrowableOre(int par1, Material vein_material, int min_harvest_level) {
        super(par1, vein_material, min_harvest_level);
        this.setTickRandomly(true);
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
            if(ran == 0 && world.isAirBlock(x, y + 1, z)){
                world.setBlock(x, y + 1, z, Blocks.azuriteCluster.blockID, 0, 2);
            }
            return false;
        }
    }
}
