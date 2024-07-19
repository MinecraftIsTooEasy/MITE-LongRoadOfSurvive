package net.oilcake.mitelros.mixins.block;

import java.util.Random;
import net.minecraft.Block;
import net.minecraft.BlockConstants;
import net.minecraft.BlockMounted;
import net.minecraft.BlockTorch;
import net.minecraft.Material;
import net.minecraft.World;
import net.oilcake.mitelros.block.Blocks;
import net.oilcake.mitelros.util.ExperimentalConfig;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin({BlockTorch.class})
public abstract class BlockTorchMixin extends BlockMounted {
   public BlockTorchMixin(int id, Material material, BlockConstants constants) {
      super(id, material, constants);
   }

   @Overwrite
   public boolean updateTick(World world, int x, int y, int z, Random random) {
      if (super.updateTick(world, x, y, z, random)) {
         return true;
      } else if (!world.isPrecipitating(true)) {
         int ran = random.nextInt(512);
         if (ran == ((Boolean)ExperimentalConfig.TagConfig.TagTorchExtinguish.ConfigValue ? 0 : -1) && world.getBlockId(x, y, z) == Block.torchWood.blockID) {
            world.setBlock(x, y, z, Blocks.torchWoodIdle.blockID, world.getBlockMetadata(x, y, z), 2);
         }

         return false;
      } else {
         boolean willExtinguish = false;

         for(int dx = -1; dx <= 1; ++dx) {
            for(int dz = -1; dz <= 1; ++dz) {
               if (world.canLightningStrikeAt(x + dx, y, z + dz)) {
                  willExtinguish = true;
               }
            }
         }

         if (willExtinguish && random.nextInt(8) == 0 && world.getBlockId(x, y, z) == Block.torchWood.blockID) {
            world.setBlock(x, y, z, Blocks.torchWoodIdle.blockID, world.getBlockMetadata(x, y, z), 2);
         }

         return false;
      }
   }
}
