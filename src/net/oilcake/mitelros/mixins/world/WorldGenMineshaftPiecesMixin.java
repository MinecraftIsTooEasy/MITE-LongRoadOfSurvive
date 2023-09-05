package net.oilcake.mitelros.mixins.world;

import net.minecraft.Block;
import net.minecraft.Item;
import net.minecraft.StructurePieceTreasure;
import net.minecraft.WorldGenMineshaftPieces;
import net.oilcake.mitelros.item.Items;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(WorldGenMineshaftPieces.class)
public class WorldGenMineshaftPiecesMixin {

    private static final StructurePieceTreasure[] mineshaftChestContents = new StructurePieceTreasure[]{
            new StructurePieceTreasure(Item.appleRed.itemID, 0, 1, 3, 3),
            new StructurePieceTreasure(Item.bread.itemID, 0, 1, 3, 15),
            new StructurePieceTreasure(Item.carrot.itemID, 0, 1, 2, 2),
            new StructurePieceTreasure(Item.potato.itemID, 0, 1, 2, 2),
            new StructurePieceTreasure(Item.onion.itemID, 0, 1, 2, 2),
            new StructurePieceTreasure(Items.beetroot.itemID, 0, 1, 2, 2),
            new StructurePieceTreasure(Item.cheese.itemID, 0, 1, 3, 3),
            new StructurePieceTreasure(Block.mushroomBrown.blockID, 0, 1, 3, 5),
            new StructurePieceTreasure(Block.mushroomRed.blockID, 0, 1, 3, 5),
            new StructurePieceTreasure(Item.bowlEmpty.itemID, 0, 1, 1, 3),
            new StructurePieceTreasure(Item.copperNugget.itemID, 0, 1, 5, 5),
            new StructurePieceTreasure(Item.silverNugget.itemID, 0, 1, 5, 5),
            new StructurePieceTreasure(Item.goldNugget.itemID, 0, 1, 5, 5),
            new StructurePieceTreasure(Item.ingotCopper.itemID, 0, 1, 5, 5),
            new StructurePieceTreasure(Item.ingotSilver.itemID, 0, 1, 3, 3),
            new StructurePieceTreasure(Item.ingotGold.itemID, 0, 1, 3, 2),
            new StructurePieceTreasure(Item.ingotIron.itemID, 0, 1, 5, 5),
            new StructurePieceTreasure(Item.chainRustedIron.itemID, 0, 1, 5, 5),
            new StructurePieceTreasure(Items.lapis.itemID, 0, 2, 5, 5),
            new StructurePieceTreasure(Item.redstone.itemID, 0, 2, 5, 5),
            new StructurePieceTreasure(Item.shardEmerald.itemID, 0, 1, 6, 5),
            new StructurePieceTreasure(Item.shardDiamond.itemID, 0, 1, 4, 3),
            new StructurePieceTreasure(Item.emerald.itemID, 0, 1, 2, 3),
            new StructurePieceTreasure(Item.diamond.itemID, 0, 1, 2, 1),
            new StructurePieceTreasure(Item.coal.itemID, 0, 2, 5, 10),
            new StructurePieceTreasure(Item.bootsLeather.itemID, 0, 1, 1, 1),
            new StructurePieceTreasure(Item.saddle.itemID, 0, 1, 1, 3),
            new StructurePieceTreasure(Block.torchWood.blockID, 0, 1, 6, 10),
            new StructurePieceTreasure(Item.flintAndSteel.itemID, 0, 1, 1, 1),
            new StructurePieceTreasure(Items.ignitionCopper.itemID, 0, 1, 1, 2),
            new StructurePieceTreasure(Items.ignitionSilver.itemID, 0, 1, 1, 2),
            new StructurePieceTreasure(Block.tnt.blockID, 0, 1, 3, 5),
            new StructurePieceTreasure(Item.bucketCopperEmpty.itemID, 0, 1, 1, 2),
            new StructurePieceTreasure(Item.bucketIronEmpty.itemID, 0, 1, 1, 1),
            new StructurePieceTreasure(Item.shovelWood.itemID, 0, 1, 1, 3),
            new StructurePieceTreasure(Item.shovelRustedIron.itemID, 0, 1, 1, 2),
            new StructurePieceTreasure(Item.shovelCopper.itemID, 0, 1, 1, 2),
            new StructurePieceTreasure(Item.shovelIron.itemID, 0, 1, 1, 1),
            new StructurePieceTreasure(Item.hatchetRustedIron.itemID, 0, 1, 1, 2),
            new StructurePieceTreasure(Item.hatchetCopper.itemID, 0, 1, 1, 2),
            new StructurePieceTreasure(Item.hatchetIron.itemID, 0, 1, 1, 1),
            new StructurePieceTreasure(Item.axeRustedIron.itemID, 0, 1, 1, 2),
            new StructurePieceTreasure(Item.axeCopper.itemID, 0, 1, 1, 2),
            new StructurePieceTreasure(Item.axeIron.itemID, 0, 1, 1, 1),
            new StructurePieceTreasure(Item.mattockRustedIron.itemID, 0, 1, 1, 2),
            new StructurePieceTreasure(Item.mattockCopper.itemID, 0, 1, 1, 2),
            new StructurePieceTreasure(Item.mattockIron.itemID, 0, 1, 1, 1),
            new StructurePieceTreasure(Item.pickaxeRustedIron.itemID, 0, 1, 1, 2),
            new StructurePieceTreasure(Item.pickaxeCopper.itemID, 0, 1, 1, 2),
            new StructurePieceTreasure(Item.pickaxeIron.itemID, 0, 1, 1, 1),
            new StructurePieceTreasure(Item.warHammerRustedIron.itemID, 0, 1, 1, 2),
            new StructurePieceTreasure(Item.warHammerCopper.itemID, 0, 1, 1, 2),
            new StructurePieceTreasure(Item.warHammerIron.itemID, 0, 1, 1, 1),
            new StructurePieceTreasure(Block.rail.blockID, 0, 2, 5, 1)};

}
