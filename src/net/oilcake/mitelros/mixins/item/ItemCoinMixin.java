package net.oilcake.mitelros.mixins.item;

import net.minecraft.Item;
import net.minecraft.ItemCoin;
import net.minecraft.Material;
import net.oilcake.mitelros.item.Items;
import net.oilcake.mitelros.item.Materials;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(ItemCoin.class)
public class ItemCoinMixin extends Item {
    @Overwrite
    public int getExperienceValue() {
        Material material = this.getExclusiveMaterial();
        if (material == Material.ancient_metal) {
            return 500;
        }
        if (material == Material.mithril) {
            return 2500;
        }
        if (material == Material.adamantium) {
            return 10000;
        }
        if (material == Materials.nickel) {
            return 50;
        }
        if (material == Materials.tungsten){
            return 5000;
        }
        return material == Material.copper ? 5 : (material == Material.silver ? 25 : (material == Material.gold ? 100 : 0));
    }

    @Overwrite
    public static ItemCoin getForMaterial(Material material) {
        if (material == Material.ancient_metal) {
            return coinAncientMetal;
        }
        if (material == Material.mithril) {
            return coinMithril;
        }
        if (material == Material.adamantium) {
            return coinAdamantium;
        }
        if (material == Materials.nickel) {
            return Items.nickelCoin;
        }
        if (material == Materials.tungsten){
            return Items.tungstenCoin;
        }
        return material == Material.copper ? coinCopper : (material == Material.silver ? coinSilver : (material == Material.gold ? coinGold : null));
    }

    @Overwrite
    public Item getNuggetPeer() {
        Material material = this.getExclusiveMaterial();
        if (material == Material.ancient_metal) {
            return ancientMetalNugget;
        }
        if (material == Material.mithril) {
            return mithrilNugget;
        }
        if (material == Material.adamantium) {
            return adamantiumNugget;
        }
        if (material == Materials.nickel) {
            return Items.nickelNugget;
        }
        if (material == Materials.tungsten){
            return Items.tungstenNugget;
        }
        return material == Material.copper ? Item.copperNugget : (material == Material.silver ? Item.silverNugget : (material == Material.gold ? Item.goldNugget : null));
    }
}
