package net.oilcake.mitelros.mixins.block;

import net.minecraft.Block;
import net.minecraft.BlockBreakInfo;
import net.minecraft.BlockConstants;
import net.minecraft.BlockDirectionalWithTileEntity;
import net.minecraft.BlockFurnace;
import net.minecraft.Item;
import net.minecraft.Material;
import net.oilcake.mitelros.util.ExperimentalConfig;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

@Mixin({BlockFurnace.class})
public abstract class BlockFurnaceMixin extends BlockDirectionalWithTileEntity {
   protected BlockFurnaceMixin(int id, Material material, BlockConstants constants) {
      super(id, material, constants);
   }

   @Overwrite
   public int dropBlockAsEntityItem(BlockBreakInfo info) {
      Block furnace_block;
      Block model_block;
      if ((Boolean)ExperimentalConfig.TagConfig.TagBenchingV2.ConfigValue) {
         furnace_block = Block.getBlock(this.getIdleBlockID());
         if (furnace_block == Block.furnaceClayIdle) {
            return !info.wasExploded() ? super.dropBlockAsEntityItem(info, Item.clay.itemID, 0, 16, 1.0F) : super.dropBlockAsEntityItem(info, Item.clay.itemID, 0, 4, 1.25F);
         } else if (furnace_block == Block.furnaceHardenedClayIdle) {
            return !info.wasExploded() ? super.dropBlockAsEntityItem(info, Item.brick.itemID, 0, 32, 1.0F) : super.dropBlockAsEntityItem(info, Item.brick.itemID, 0, 4, 1.25F);
         } else {
            if (furnace_block == Block.furnaceSandstoneIdle) {
               model_block = Block.sandStone;
            } else if (furnace_block == Block.furnaceIdle) {
               model_block = Block.cobblestone;
            } else if (furnace_block == Block.furnaceObsidianIdle) {
               model_block = Block.obsidian;
            } else {
               if (furnace_block != Block.furnaceNetherrackIdle) {
                  return 0;
               }

               model_block = Block.netherrack;
            }

            return info.wasExploded() ? model_block.dropBlockAsEntityItem(info.setBlock(model_block, 0)) : model_block.dropBlockAsEntityItem(info, model_block.blockID, 0, 8, 1.0F);
         }
      } else if (info.wasExploded()) {
         furnace_block = Block.getBlock(this.getIdleBlockID());
         if (furnace_block == Block.furnaceClayIdle) {
            return 0;
         } else {
            if (furnace_block == Block.furnaceSandstoneIdle) {
               model_block = Block.sandStone;
            } else if (furnace_block == Block.furnaceIdle) {
               model_block = Block.cobblestone;
            } else if (furnace_block == Block.furnaceObsidianIdle) {
               model_block = Block.obsidian;
            } else {
               if (furnace_block != Block.furnaceNetherrackIdle) {
                  return 0;
               }

               model_block = Block.netherrack;
            }

            return model_block.dropBlockAsEntityItem(info.setBlock(model_block, 0));
         }
      } else {
         return super.dropBlockAsEntityItem(info);
      }
   }

   @Shadow
   public abstract int getIdleBlockID();

   @Overwrite
   public float getCraftingDifficultyAsComponent(int metadata) {
      return 1920.0F;
   }
}
