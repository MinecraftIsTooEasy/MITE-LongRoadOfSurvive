package net.oilcake.mitelros.mixins.item;

import net.minecraft.Block;
import net.minecraft.ItemDoor;
import net.minecraft.Material;
import net.oilcake.mitelros.block.Blocks;
import net.oilcake.mitelros.item.Materials;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(ItemDoor.class)
public class ItemDoorMixin {
    @Overwrite
    public Block getBlock() {
        if (this.door_material == Material.wood) {
            return Block.doorWood;
        } else if (this.door_material == Material.copper) {
            return Block.doorCopper;
        } else if (this.door_material == Material.silver) {
            return Block.doorSilver;
        } else if (this.door_material == Material.gold) {
            return Block.doorGold;
        } else if (this.door_material == Material.iron) {
            return Block.doorIron;
        } else if (this.door_material == Material.mithril) {
            return Block.doorMithril;
        } else if (this.door_material == Material.adamantium) {
            return Block.doorAdamantium;
        } else if (this.door_material == Materials.nickel) {
            return Blocks.doorNickel;
        } else if (this.door_material == Materials.tungsten) {
            return Blocks.doorTungsten;
        }
        else {
            return this.door_material == Material.ancient_metal ? Block.doorAncientMetal : null;
        }
    }
    @Shadow
    private Material door_material;
}