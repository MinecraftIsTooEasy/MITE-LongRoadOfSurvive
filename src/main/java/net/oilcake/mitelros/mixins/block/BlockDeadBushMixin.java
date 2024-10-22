package net.oilcake.mitelros.mixins.block;

import net.minecraft.Block;
import net.minecraft.BlockBreakInfo;
import net.minecraft.BlockDeadBush;
import net.minecraft.BlockFlower;
import net.minecraft.Item;
import net.minecraft.Material;
import net.oilcake.mitelros.item.Items;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

@Mixin({BlockDeadBush.class})
public class BlockDeadBushMixin extends BlockFlower {
   protected BlockDeadBushMixin(int id, Material material) {
      super(id, material);
   }

   @Overwrite
   public int dropBlockAsEntityItem(BlockBreakInfo info) {
      if (this.isWitherwood(info.getMetadata())) {
         return !info.wasNotLegal() && !info.wasSelfDropped() ? this.dropBlockAsEntityItem(info, Items.wither_branch.itemID, 0, 1, 0.5F) : super.dropBlockAsEntityItem(info);
      } else {
         if (info.wasNotLegal()) {
            info.world.destroyBlock(info, false);
         }

         return this.dropBlockAsEntityItem(info, Item.stick.itemID, 0, 1, 0.05F);
      }
   }

   @Shadow
   public static boolean isWitherwood(Block block, int metadata) {
      return block == deadBush && block.getBlockSubtype(metadata) == 1;
   }

   @Shadow
   public boolean isWitherwood(int metadata) {
      return isWitherwood(this, metadata);
   }
}
