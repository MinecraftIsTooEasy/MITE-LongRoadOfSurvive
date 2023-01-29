package net.oilcake.mitelros.mixins.item;


import net.minecraft.ItemRock;
import net.minecraft.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(ItemRock.class)
public class ItemRockMixin {
    @Overwrite
    public static int getExperienceValueWhenSacrificed(ItemStack item_stack) {
            return 0;
    }
}
