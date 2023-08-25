package net.oilcake.mitelros.item;

import net.minecraft.CreativeModeTab;
import net.minecraft.Item;
import net.minecraft.Material;

public class ItemPieces extends Item {
    public ItemPieces(int id, Material material, String texture) {
        super(id, material, texture);
        this.setMaxStackSize(32);
        this.setCraftingDifficultyAsComponent(20.0F);
        this.setCreativeTab(CreativeModeTab.tabMaterials);
    }
}