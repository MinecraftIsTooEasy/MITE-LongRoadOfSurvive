package net.oilcake.mitelros.mixins.item;

import net.minecraft.Block;
import net.minecraft.BlockRedstoneTorch;
import net.minecraft.BlockWorkbench;
import net.minecraft.Item;
import net.minecraft.ItemBlock;
import net.minecraft.ItemStack;
import net.minecraft.Material;
import net.minecraft.Translator;
import net.oilcake.mitelros.block.BlockMetalbench;
import net.oilcake.mitelros.block.BlockTorchIdle;
import net.oilcake.mitelros.block.BlockWoodbench;
import net.oilcake.mitelros.block.Blocks;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin({ItemBlock.class})
public class ItemBlockMixin extends Item {

   @Shadow
   public Block getBlock() {
      return null;
   }

   @ModifyVariable(
      method = {"getItemStackForStatsIcon()Lnet/minecraft/ItemStack;"},
      at = @At("RETURN"),
      ordinal = 1
   )
   private int ExtendedFlowerPotID(int id) {
      Block block = this.getBlock();
      if (block == Blocks.flowerPotExtend) {
         id = Item.flowerPot.itemID;
      }

      return id;
   }

   @Overwrite
   public String getItemDisplayName(ItemStack item_stack) {
      if (item_stack != null && this.getBlock() == Block.workbench) {
         return Translator.get("tile.toolbench." + BlockWorkbench.getToolMaterial(item_stack.getItemSubtype()).getName() + ".name");
      } else if (item_stack != null && this.getBlock() == Blocks.metalbench) {
         return Translator.get("tile.toolbench." + BlockMetalbench.getToolMaterial(item_stack.getItemSubtype()).getName() + ".name");
      } else {
         return item_stack != null && this.getBlock() == Blocks.woodbench ? Translator.get("tile.toolbench." + BlockWoodbench.getToolMaterial(item_stack.getItemSubtype()).getName() + ".name") : super.getItemDisplayName(item_stack);
      }
   }

   @Overwrite
   public int getBurnTime(ItemStack item_stack) {
      if (!this.canBurnAsFuelSource()) {
         return 0;
      } else {
         Block block = this.getBlock();
         if (block == Block.wood) {
            return 1600;
         } else if (block != Block.planks && block != Block.woodDoubleSlab && block != Block.woodenButton) {
            if (block != Block.woodSingleSlab && block != Block.sapling && block != Block.deadBush) {
               if (block == Block.torchWood) {
                  return 800;
               } else if (block instanceof BlockRedstoneTorch) {
                  return 100;
               } else if (block instanceof BlockTorchIdle) {
                  return 25;
               } else if (block.blockMaterial == Material.wood) {
                  return 400;
               } else {
                  return block == Block.coalBlock ? 16000 : 0;
               }
            } else {
               return 200;
            }
         } else {
            return 400;
         }
      }
   }
}
