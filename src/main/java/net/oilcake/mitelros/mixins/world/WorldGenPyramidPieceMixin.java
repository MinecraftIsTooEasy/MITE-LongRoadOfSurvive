package net.oilcake.mitelros.mixins.world;

import net.minecraft.ComponentScatteredFeatureDesertPyramid;
import net.minecraft.Item;
import net.minecraft.WeightedRandomChestContent;
import net.oilcake.mitelros.item.Items;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;

@Mixin({ComponentScatteredFeatureDesertPyramid.class})
public class WorldGenPyramidPieceMixin {
   @Final
   private static final WeightedRandomChestContent[] itemsToGenerateInTemple;

   static {
      itemsToGenerateInTemple = new WeightedRandomChestContent[]{new WeightedRandomChestContent(Item.shardDiamond.itemID, 0, 1, 3, 3), new WeightedRandomChestContent(Item.goldNugget.itemID, 0, 2, 7, 15), new WeightedRandomChestContent(Item.shardEmerald.itemID, 0, 1, 3, 2), new WeightedRandomChestContent(Item.bone.itemID, 0, 4, 6, 20), new WeightedRandomChestContent(Item.rottenFlesh.itemID, 0, 3, 7, 16), new WeightedRandomChestContent(Item.saddle.itemID, 0, 1, 1, 3), new WeightedRandomChestContent(Item.bowlEmpty.itemID, 0, 1, 1, 3), new WeightedRandomChestContent(Item.paper.itemID, 0, 1, 1, 1), new WeightedRandomChestContent(Item.flowerPot.itemID, 0, 1, 1, 1), new WeightedRandomChestContent(Item.horseArmorIron.itemID, 0, 1, 1, 1), new WeightedRandomChestContent(Item.horseArmorSilver.itemID, 0, 1, 1, 1), new WeightedRandomChestContent(Item.horseArmorGold.itemID, 0, 1, 1, 1), new WeightedRandomChestContent(Items.totemofpreserve.itemID, 0, 1, 1, 1)};
   }
}
