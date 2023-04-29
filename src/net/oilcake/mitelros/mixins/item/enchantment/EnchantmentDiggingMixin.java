package net.oilcake.mitelros.mixins.item.enchantment;

import net.minecraft.*;
import net.oilcake.mitelros.item.Items;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(EnchantmentDigging.class)
public class EnchantmentDiggingMixin {
    @Overwrite
    public boolean canEnchantItem(Item item) {
        return item.getClass() == ItemPickaxe.class || item instanceof ItemAxe || item instanceof ItemShovel || item instanceof ItemHoe || (item == Items.UruWarHammer);
    }
}
