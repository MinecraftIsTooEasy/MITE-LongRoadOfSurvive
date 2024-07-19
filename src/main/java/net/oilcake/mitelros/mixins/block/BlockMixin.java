package net.oilcake.mitelros.mixins.block;

import net.minecraft.Block;
import net.minecraft.Item;
import net.minecraft.ItemMultiTextureTile;
import net.minecraft.Material;
import net.minecraft.Minecraft;
import net.minecraft.StepSound;
import net.oilcake.mitelros.block.BlockFlowerExtend;
import net.oilcake.mitelros.block.Blocks;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({Block.class})
public abstract class BlockMixin {
   public Block block;

   @Inject(
      method = {"<clinit>()V"},
      at = {@At("RETURN")}
   )
   private static void injectClinit(CallbackInfo callback) {
      Item.itemsList[Blocks.flowerextend.blockID] = (new ItemMultiTextureTile(Blocks.flowerextend, BlockFlowerExtend.types)).setUnlocalizedName("flowers");
   }


   public Block getMatchingBlock(Class item_class, Material material) {
      Block matching_block = null;

      for(int i = 0; i < 512; ++i) {
         Block block = Block.getBlock(i);
         if (block != null && block.getClass() == item_class && block.blockMaterial == material) {
            if (matching_block == null) {
               matching_block = block;
            } else {
               Minecraft.setErrorMessage("getMatchingItem: more than one item matched " + item_class + ", " + material);
            }
         }
      }

      return matching_block;
   }
}
