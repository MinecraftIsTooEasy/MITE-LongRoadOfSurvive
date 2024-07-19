package net.oilcake.mitelros.mixins.world;

import net.minecraft.Block;
import net.minecraft.ComponentScatteredFeatureSwampHut;
import net.minecraft.Item;
import net.minecraft.WeightedRandomChestContent;
import net.oilcake.mitelros.item.Items;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;

@Mixin({ComponentScatteredFeatureSwampHut.class})
public class WorldGenWitchHutMixin {
   @Final
   private static final WeightedRandomChestContent[] chest_contents;

   static {
      chest_contents = new WeightedRandomChestContent[]{new WeightedRandomChestContent(Item.glassBottle.itemID, 0, 1, 2, 3), new WeightedRandomChestContent(Block.mushroomRed.blockID, 0, 1, 4, 8), new WeightedRandomChestContent(Block.mushroomBrown.blockID, 0, 1, 3, 5), new WeightedRandomChestContent(Item.bowlEmpty.itemID, 0, 1, 1, 3), new WeightedRandomChestContent(Item.reed.itemID, 0, 1, 5, 8), new WeightedRandomChestContent(Item.chipFlint.itemID, 0, 1, 3, 5), new WeightedRandomChestContent(Item.knifeFlint.itemID, 0, 1, 1, 3), new WeightedRandomChestContent(Item.hatchetFlint.itemID, 0, 1, 1, 3), new WeightedRandomChestContent(Item.stick.itemID, 0, 1, 3, 5), new WeightedRandomChestContent(Item.rottenFlesh.itemID, 0, 1, 3, 5), new WeightedRandomChestContent(Item.silk.itemID, 0, 1, 2, 3), new WeightedRandomChestContent(Item.chickenRaw.itemID, 0, 1, 2, 3), new WeightedRandomChestContent(Item.feather.itemID, 0, 1, 2, 3), new WeightedRandomChestContent(Item.leather.itemID, 0, 1, 2, 3), new WeightedRandomChestContent(Block.cloth.blockID, 0, 1, 2, 3), new WeightedRandomChestContent(Item.bone.itemID, 0, 1, 2, 3), new WeightedRandomChestContent(Item.sugar.itemID, 0, 1, 3, 5), new WeightedRandomChestContent(Item.pumpkinSeeds.itemID, 0, 1, 2, 3), new WeightedRandomChestContent(Item.appleRed.itemID, 0, 1, 2, 3), new WeightedRandomChestContent(Item.carrot.itemID, 0, 1, 2, 3), new WeightedRandomChestContent(Item.potato.itemID, 0, 1, 2, 3), new WeightedRandomChestContent(Item.onion.itemID, 0, 1, 2, 3), new WeightedRandomChestContent(Items.beetroot.itemID, 0, 1, 2, 3), new WeightedRandomChestContent(Block.plantYellow.blockID, 0, 1, 2, 3), new WeightedRandomChestContent(Block.plantRed.blockID, 2, 1, 2, 3), new WeightedRandomChestContent(Item.potion.itemID, 8227, 1, 1, 1), new WeightedRandomChestContent(Item.potion.itemID, 8261, 1, 1, 1), new WeightedRandomChestContent(Item.potion.itemID, 16388, 1, 1, 1), new WeightedRandomChestContent(Item.potion.itemID, 16424, 1, 1, 1), new WeightedRandomChestContent(Item.potion.itemID, 16426, 1, 1, 1), new WeightedRandomChestContent(Item.potion.itemID, 16460, 1, 1, 1)};
   }
}
