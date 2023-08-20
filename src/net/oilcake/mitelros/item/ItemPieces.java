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
    //.setXPReward(1) 铜 silver iron nickel
    //.setXPReward(2) gold
    //.setXPReward(4) mithril
    //.setXPReward(7) tungsten
    //.setXPReward(10) adamantium
    //.setXPReward(15) urn
}