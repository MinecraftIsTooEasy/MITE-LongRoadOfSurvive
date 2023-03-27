package net.oilcake.mitelros.item;

import net.minecraft.CreativeModeTab;
import net.minecraft.ItemRock;
import net.minecraft.Material;

public class ItemShardAT extends ItemRock {
    protected ItemShardAT(int id, Material material) {
        super(id, material, "shards/" + material.getName());
        this.setMaxStackSize(64);
        this.setCraftingDifficultyAsComponent(ItemRock.getCraftingDifficultyAsComponent(material) / (float)(material == Material.flint ? 4 : 9));
        this.setCreativeTab(CreativeModeTab.tabMaterials);
    }
}
