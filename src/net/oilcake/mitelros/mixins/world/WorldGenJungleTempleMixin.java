package net.oilcake.mitelros.mixins.world;

import net.minecraft.Item;
import net.minecraft.StructurePieceTreasure;
import net.minecraft.WorldGenJungleTemple;
import net.oilcake.mitelros.item.Items;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(WorldGenJungleTemple.class)
public class WorldGenJungleTempleMixin {
    @Final
    private static final StructurePieceTreasure[] junglePyramidsChestContents = new StructurePieceTreasure[]{
            new StructurePieceTreasure(Item.shardDiamond.itemID, 0, 1, 3, 3),
            new StructurePieceTreasure(Item.goldNugget.itemID, 0, 2, 7, 15),
            new StructurePieceTreasure(Item.shardEmerald.itemID, 0, 1, 3, 2),
            new StructurePieceTreasure(Item.bone.itemID, 0, 4, 6, 20),
            new StructurePieceTreasure(Item.rottenFlesh.itemID, 0, 3, 7, 16),
            new StructurePieceTreasure(Item.saddle.itemID, 0, 1, 1, 3),
            new StructurePieceTreasure(Item.horseArmorIron.itemID, 0, 1, 1, 1),
            new StructurePieceTreasure(Item.horseArmorSilver.itemID, 0, 1, 1, 1),
            new StructurePieceTreasure(Item.horseArmorGold.itemID, 0, 1, 1, 1),
            new StructurePieceTreasure(Items.totemofpreserve.itemID, 0, 1, 1, 1),
    };
}
