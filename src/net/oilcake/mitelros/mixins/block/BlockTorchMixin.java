package net.oilcake.mitelros.mixins.block;

import net.minecraft.*;
import net.oilcake.mitelros.block.Blocks;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

import java.util.Random;

@Mixin(BlockTorch.class)
public abstract class BlockTorchMixin extends BlockMounted{
    public BlockTorchMixin(int id, Material material, BlockConstants constants) {
        super(id, material, constants);
    }

    @Overwrite
    public boolean updateTick(World world, int x, int y, int z, Random random)
    {
        if (super.updateTick(world, x, y, z, random))
        {
            return true;
        } else if(world.isPrecipitating(true)){
            boolean willExtinguish = false;
            for(int dx = -1; dx <= 1; dx++){
                for(int dz = -1; dz <= 1; dz++){
                    if(world.canLightningStrikeAt(x + dx,y,z + dz)){
                        willExtinguish = true;
                    }
                }
            }
            if(willExtinguish && random.nextInt(8) == 0){
                world.setBlock(x, y, z, Blocks.torchWoodIdle.blockID, world.getBlockMetadata(x,y,z), 2);
            }
            return false;
        } else {
            int ran = random.nextInt(512);
            if(ran == 0 && world.getBlockId(x,y,z) == Block.torchWood.blockID){
                world.setBlock(x, y, z, Blocks.torchWoodIdle.blockID, world.getBlockMetadata(x,y,z), 2);
            }
            return false;
        }
    }
}
