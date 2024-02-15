package net.oilcake.mitelros.mixins.world;

import net.minecraft.Block;
import net.minecraft.Item;
import net.minecraft.StructurePieceTreasure;
import net.minecraft.WorldGenWitchHut;
import net.oilcake.mitelros.item.Items;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(WorldGenWitchHut.class)
public class WorldGenWitchHutMixin{
    @Final
    private static final StructurePieceTreasure[]chest_contents = new StructurePieceTreasure[]{
            new StructurePieceTreasure(Item.glassBottle.itemID, 0, 1, 2, 3),
            new StructurePieceTreasure(Block.mushroomRed.blockID, 0, 1, 4, 8),
            new StructurePieceTreasure(Block.mushroomBrown.blockID, 0, 1, 3, 5),
            new StructurePieceTreasure(Item.bowlEmpty.itemID, 0, 1, 1, 3),
            new StructurePieceTreasure(Item.reed.itemID, 0, 1, 5, 8),
            new StructurePieceTreasure(Item.chipFlint.itemID, 0, 1, 3, 5),
            new StructurePieceTreasure(Item.knifeFlint.itemID, 0, 1, 1, 3),
            new StructurePieceTreasure(Item.hatchetFlint.itemID, 0, 1, 1, 3),
            new StructurePieceTreasure(Item.stick.itemID, 0, 1, 3, 5),
            new StructurePieceTreasure(Item.rottenFlesh.itemID, 0, 1, 3, 5),
            new StructurePieceTreasure(Item.silk.itemID, 0, 1, 2, 3),
            new StructurePieceTreasure(Item.chickenRaw.itemID, 0, 1, 2, 3),
            new StructurePieceTreasure(Item.feather.itemID, 0, 1, 2, 3),
            new StructurePieceTreasure(Item.leather.itemID, 0, 1, 2, 3),
            new StructurePieceTreasure(Block.cloth.blockID, 0, 1, 2, 3),
            new StructurePieceTreasure(Item.bone.itemID, 0, 1, 2, 3),
            new StructurePieceTreasure(Item.sugar.itemID, 0, 1, 3, 5),
            new StructurePieceTreasure(Item.pumpkinSeeds.itemID, 0, 1, 2, 3),
            new StructurePieceTreasure(Item.appleRed.itemID, 0, 1, 2, 3),
            new StructurePieceTreasure(Item.carrot.itemID, 0, 1, 2, 3),
            new StructurePieceTreasure(Item.potato.itemID, 0, 1, 2, 3),
            new StructurePieceTreasure(Item.onion.itemID, 0, 1, 2, 3),
            new StructurePieceTreasure(Items.beetroot.itemID, 0, 1, 2, 3),
            new StructurePieceTreasure(Block.plantYellow.blockID, 0, 1, 2, 3),
            new StructurePieceTreasure(Block.plantRed.blockID, 2, 1, 2, 3),
            new StructurePieceTreasure(Item.potion.itemID, 8227, 1, 1, 1),
            new StructurePieceTreasure(Item.potion.itemID, 8261, 1, 1, 1),
            new StructurePieceTreasure(Item.potion.itemID, 16388, 1, 1, 1),
            new StructurePieceTreasure(Item.potion.itemID, 16424, 1, 1, 1),
            new StructurePieceTreasure(Item.potion.itemID, 16426, 1, 1, 1),
            new StructurePieceTreasure(Item.potion.itemID, 16460, 1, 1, 1)};

}
