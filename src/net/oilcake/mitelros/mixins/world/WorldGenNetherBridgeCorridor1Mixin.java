package net.oilcake.mitelros.mixins.world;

import net.minecraft.*;
import net.oilcake.mitelros.item.Items;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

import javax.annotation.OverridingMethodsMustInvokeSuper;
import java.util.Random;

@Mixin(agz.class)
public abstract class WorldGenNetherBridgeCorridor1Mixin extends StructurePiece{
    private static final StructurePieceTreasure[] chest_contents;
    static {
        chest_contents = new StructurePieceTreasure[]{
                new StructurePieceTreasure(Item.diamond.itemID, 0, 1, 2, 5),
                new StructurePieceTreasure(Items.tungstenIngot.itemID, 0, 1, 1, 5),
                new StructurePieceTreasure(Items.tungstenNugget.itemID, 0, 3, 7, 10),
                new StructurePieceTreasure(Item.ingotGold.itemID, 0, 2, 5, 10),
                new StructurePieceTreasure(Item.goldNugget.itemID, 0, 5, 10, 20),
                new StructurePieceTreasure(Items.tungstenSword.itemID, 0, 1, 1, 5),
                new StructurePieceTreasure(Items.tungstenWarHammer.itemID, 0, 1, 1, 5),
                new StructurePieceTreasure(Items.tungstenHelmetChain.itemID, 0, 1, 1, 5),
                new StructurePieceTreasure(Items.tungstenChestplateChain.itemID, 0, 1, 1, 5),
                new StructurePieceTreasure(Items.tungstenLeggingsChain.itemID, 0, 1, 1, 5),
                new StructurePieceTreasure(Items.tungstenBootsChain.itemID, 0, 1, 1, 5),
                new StructurePieceTreasure(Items.tungstenHelmet.itemID, 0, 1, 1, 2),
                new StructurePieceTreasure(Items.tungstenChestplate.itemID, 0, 1, 1, 2),
                new StructurePieceTreasure(Items.tungstenLeggings.itemID, 0, 1, 1, 2),
                new StructurePieceTreasure(Items.tungstenBoots.itemID, 0, 1, 1, 2),
                new StructurePieceTreasure(Item.warHammerGold.itemID, 0, 1, 1, 10),
                new StructurePieceTreasure(Item.swordGold.itemID, 0, 1, 1, 10),
                new StructurePieceTreasure(Item.battleAxeGold.itemID, 0, 1, 1, 10),
                new StructurePieceTreasure(Item.helmetGold.itemID, 0, 1, 1, 10),
                new StructurePieceTreasure(Item.plateGold.itemID, 0, 1, 1, 10),
                new StructurePieceTreasure(Item.legsGold.itemID, 0, 1, 1, 10),
                new StructurePieceTreasure(Item.bootsGold.itemID, 0, 1, 1, 10),
                new StructurePieceTreasure(Items.ignitionGold.itemID, 0, 1, 1, 18),
                new StructurePieceTreasure(Items.ignitionTungsten.itemID, 0, 1, 1, 2),
                new StructurePieceTreasure(Item.netherStalkSeeds.itemID, 0, 3, 7, 5),
                new StructurePieceTreasure(Item.saddle.itemID, 0, 1, 1, 20),
                new StructurePieceTreasure(Item.horseArmorGold.itemID, 0, 1, 1, 10),
                new StructurePieceTreasure(Item.horseArmorCopper.itemID, 0, 1, 1, 2),
                new StructurePieceTreasure(Item.horseArmorIron.itemID, 0, 1, 1, 1),
                new StructurePieceTreasure(Items.totemofdestroy.itemID, 0, 1, 1, 5),
        };

    }
}
