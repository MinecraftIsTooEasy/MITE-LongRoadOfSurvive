package net.oilcake.mitelros.mixins.entity;

import net.minecraft.Block;
import net.minecraft.EntityWitch;
import net.minecraft.Item;
import net.oilcake.mitelros.item.Items;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(EntityWitch.class)
public class EntityWitchMixin {
    private static final int[] witchDrops = new int[]{
        Item.glowstone.itemID, Item.sugar.itemID, Item.redstone.itemID, Item.spiderEye.itemID, Item.glassBottle.itemID, Item.gunpowder.itemID, Item.stick.itemID, Item.stick.itemID, Item.knifeFlint.itemID, Item.ironNugget.itemID, Item.seeds.itemID, Item.pumpkinSeeds.itemID, Item.carrot.itemID, Item.potato.itemID, Item.onion.itemID, Block.plantYellow.blockID, Block.plantRed.blockID, Item.potion.itemID, Items.seedsBeetroot.itemID};

}
