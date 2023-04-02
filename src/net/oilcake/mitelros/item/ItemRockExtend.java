package net.oilcake.mitelros.item;

import net.minecraft.Item;
import net.minecraft.ItemRock;
import net.minecraft.ItemStack;
import net.minecraft.Material;
import org.spongepowered.asm.mixin.Overwrite;

public class ItemRockExtend extends ItemRock {
    protected ItemRockExtend(int id, Material material, String texture) {
        super(id, material, texture);
    }
}
