package net.oilcake.mitelros.mixins.world;

import net.minecraft.ComponentNetherBridgeCorridor2;
import net.minecraft.Item;
import net.minecraft.StructureComponent;
import net.minecraft.WeightedRandomChestContent;
import net.oilcake.mitelros.item.Items;
import org.spongepowered.asm.mixin.Mixin;

@Mixin({ComponentNetherBridgeCorridor2.class})
public abstract class WorldGenNetherBridgeCorridor1Mixin extends StructureComponent {
   private static final WeightedRandomChestContent[] chest_contents;

   static {
      chest_contents = new WeightedRandomChestContent[]{new WeightedRandomChestContent(Item.diamond.itemID, 0, 1, 2, 5), new WeightedRandomChestContent(Items.tungstenIngot.itemID, 0, 1, 1, 5), new WeightedRandomChestContent(Items.tungstenNugget.itemID, 0, 3, 7, 10), new WeightedRandomChestContent(Item.ingotGold.itemID, 0, 2, 5, 10), new WeightedRandomChestContent(Item.goldNugget.itemID, 0, 5, 10, 20), new WeightedRandomChestContent(Items.tungstenSword.itemID, 0, 1, 1, 5), new WeightedRandomChestContent(Items.tungstenWarHammer.itemID, 0, 1, 1, 5), new WeightedRandomChestContent(Items.tungstenHelmetChain.itemID, 0, 1, 1, 5), new WeightedRandomChestContent(Items.tungstenChestplateChain.itemID, 0, 1, 1, 5), new WeightedRandomChestContent(Items.tungstenLeggingsChain.itemID, 0, 1, 1, 5), new WeightedRandomChestContent(Items.tungstenBootsChain.itemID, 0, 1, 1, 5), new WeightedRandomChestContent(Items.tungstenHelmet.itemID, 0, 1, 1, 2), new WeightedRandomChestContent(Items.tungstenChestplate.itemID, 0, 1, 1, 2), new WeightedRandomChestContent(Items.tungstenLeggings.itemID, 0, 1, 1, 2), new WeightedRandomChestContent(Items.tungstenBoots.itemID, 0, 1, 1, 2), new WeightedRandomChestContent(Item.warHammerGold.itemID, 0, 1, 1, 10), new WeightedRandomChestContent(Item.swordGold.itemID, 0, 1, 1, 10), new WeightedRandomChestContent(Item.battleAxeGold.itemID, 0, 1, 1, 10), new WeightedRandomChestContent(Item.helmetGold.itemID, 0, 1, 1, 10), new WeightedRandomChestContent(Item.plateGold.itemID, 0, 1, 1, 10), new WeightedRandomChestContent(Item.legsGold.itemID, 0, 1, 1, 10), new WeightedRandomChestContent(Item.bootsGold.itemID, 0, 1, 1, 10), new WeightedRandomChestContent(Items.ignitionGold.itemID, 0, 1, 1, 18), new WeightedRandomChestContent(Items.ignitionTungsten.itemID, 0, 1, 1, 2), new WeightedRandomChestContent(Item.netherStalkSeeds.itemID, 0, 3, 7, 5), new WeightedRandomChestContent(Item.saddle.itemID, 0, 1, 1, 20), new WeightedRandomChestContent(Item.horseArmorGold.itemID, 0, 1, 1, 10), new WeightedRandomChestContent(Item.horseArmorCopper.itemID, 0, 1, 1, 2), new WeightedRandomChestContent(Item.horseArmorIron.itemID, 0, 1, 1, 1), new WeightedRandomChestContent(Items.totemofdestroy.itemID, 0, 1, 1, 5)};
   }
}
