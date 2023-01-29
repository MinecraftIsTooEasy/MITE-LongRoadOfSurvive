package net.oilcake.mitelros.mixins.item;

import net.oilcake.mitelros.item.Items;
import net.oilcake.mitelros.item.Materials;
import net.minecraft.Item;
import net.minecraft.ItemNugget;
import net.minecraft.Material;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(ItemNugget.class)
public class ItemNuggetMixin {
    @Overwrite
    public ItemNugget getForMaterial(Material material) {
        return material == Material.copper ? Item.copperNugget : (material == Material.silver ? Item.silverNugget : (material == Material.gold ? Item.goldNugget : (material == Material.iron ? Item.ironNugget : (material == Material.mithril ? Item.mithrilNugget : (material == Material.adamantium ? Item.adamantiumNugget : (material == Material.ancient_metal ? Item.ancientMetalNugget :
                (material == Materials.nickel ? Items.nickelNugget : null)))))));
    }

}
