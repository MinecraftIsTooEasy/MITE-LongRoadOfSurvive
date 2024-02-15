package net.oilcake.mitelros.mixins.world;

import net.minecraft.Item;
import net.minecraft.StructurePieceTreasure;
import net.minecraft.WorldGenStrongholdChestCorridor;
import net.oilcake.mitelros.item.Items;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(WorldGenStrongholdChestCorridor.class)
public class WorldGenStrongholdChestCorridorMixin {
    private static final StructurePieceTreasure[] strongholdChestContents = new StructurePieceTreasure[]{
            new StructurePieceTreasure(Item.bread.itemID, 0, 1, 3, 15),
            new StructurePieceTreasure(Item.appleRed.itemID, 0, 1, 3, 15),
            new StructurePieceTreasure(Items.beetroot.itemID, 0, 1, 1, 5),
            new StructurePieceTreasure(Item.carrot.itemID, 0, 1, 1, 5),
            new StructurePieceTreasure(Item.potato.itemID, 0, 1, 1, 5),
            new StructurePieceTreasure(Item.onion.itemID, 0, 1, 1, 5),
            new StructurePieceTreasure(Item.appleGold.itemID, 0, 1, 1, 1),
            new StructurePieceTreasure(Item.enderPearl.itemID, 0, 1, 1, 10),
            new StructurePieceTreasure(Item.emerald.itemID, 0, 1, 3, 3),
            new StructurePieceTreasure(Item.diamond.itemID, 0, 1, 2, 2),
            new StructurePieceTreasure(Item.ingotCopper.itemID, 0, 1, 5, 5),
            new StructurePieceTreasure(Item.ingotSilver.itemID, 0, 1, 3, 3),
            new StructurePieceTreasure(Item.ingotGold.itemID, 0, 1, 3, 2),
            new StructurePieceTreasure(Item.ingotIron.itemID, 0, 1, 5, 5),
            new StructurePieceTreasure(Item.redstone.itemID, 0, 3, 5, 5),
            new StructurePieceTreasure(Item.pickaxeCopper.itemID, 0, 1, 1, 5),
            new StructurePieceTreasure(Item.pickaxeIron.itemID, 0, 1, 1, 5),
            new StructurePieceTreasure(Item.daggerCopper.itemID, 0, 1, 1, 3),
            new StructurePieceTreasure(Item.daggerSilver.itemID, 0, 1, 1, 3),
            new StructurePieceTreasure(Item.daggerIron.itemID, 0, 1, 1, 3),
            new StructurePieceTreasure(Item.swordCopper.itemID, 0, 1, 1, 5),
            new StructurePieceTreasure(Item.swordSilver.itemID, 0, 1, 1, 5),
            new StructurePieceTreasure(Item.swordIron.itemID, 0, 1, 1, 5),
            new StructurePieceTreasure(Item.battleAxeCopper.itemID, 0, 1, 1, 2),
            new StructurePieceTreasure(Item.battleAxeIron.itemID, 0, 1, 1, 1),
            new StructurePieceTreasure(Item.warHammerCopper.itemID, 0, 1, 1, 2),
            new StructurePieceTreasure(Item.warHammerIron.itemID, 0, 1, 1, 1),
            new StructurePieceTreasure(Item.plateChainCopper.itemID, 0, 1, 1, 5),
            new StructurePieceTreasure(Item.helmetChainCopper.itemID, 0, 1, 1, 5),
            new StructurePieceTreasure(Item.legsChainCopper.itemID, 0, 1, 1, 5),
            new StructurePieceTreasure(Item.bootsChainCopper.itemID, 0, 1, 1, 5),
            new StructurePieceTreasure(Item.plateChainIron.itemID, 0, 1, 1, 3),
            new StructurePieceTreasure(Item.helmetChainIron.itemID, 0, 1, 1, 3),
            new StructurePieceTreasure(Item.legsChainIron.itemID, 0, 1, 1, 3),
            new StructurePieceTreasure(Item.bootsChainIron.itemID, 0, 1, 1, 3),
            new StructurePieceTreasure(Item.plateCopper.itemID, 0, 1, 1, 5),
            new StructurePieceTreasure(Item.helmetCopper.itemID, 0, 1, 1, 5),
            new StructurePieceTreasure(Item.legsCopper.itemID, 0, 1, 1, 5),
            new StructurePieceTreasure(Item.bootsCopper.itemID, 0, 1, 1, 5),
            new StructurePieceTreasure(Item.plateIron.itemID, 0, 1, 1, 3),
            new StructurePieceTreasure(Item.helmetIron.itemID, 0, 1, 1, 3),
            new StructurePieceTreasure(Item.legsIron.itemID, 0, 1, 1, 3),
            new StructurePieceTreasure(Item.bootsIron.itemID, 0, 1, 1, 3),
            new StructurePieceTreasure(Item.saddle.itemID, 0, 1, 1, 1),
            new StructurePieceTreasure(Item.horseArmorCopper.itemID, 0, 1, 1, 1),
            new StructurePieceTreasure(Item.horseArmorIron.itemID, 0, 1, 1, 1),
            new StructurePieceTreasure(Item.shears.itemID, 0, 1, 1, 1),
            new StructurePieceTreasure(Items.totemofpreserve.itemID, 0, 1, 1, 5),
    };
}
