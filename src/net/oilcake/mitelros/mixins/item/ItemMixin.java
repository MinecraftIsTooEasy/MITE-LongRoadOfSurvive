package net.oilcake.mitelros.mixins.item;

import net.oilcake.mitelros.item.Items;
import net.oilcake.mitelros.item.Materials;
import net.minecraft.*;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(Item.class)
public class ItemMixin {
    @Shadow
    public Material getMaterialForRepairs() {
        return null;
    }
    @Overwrite
    public Item getRepairItem() {
        Material material_for_repairs = this.getMaterialForRepairs();
        if (material_for_repairs == Material.copper) {
            return Item.copperNugget;
        } else if (material_for_repairs == Material.silver) {
            return Item.silverNugget;
        } else if (material_for_repairs == Material.gold) {
            return Item.goldNugget;
        } else if (material_for_repairs != Material.iron && material_for_repairs != Material.rusted_iron) {
            if (material_for_repairs == Material.mithril) {
                return Item.mithrilNugget;
            } else if (material_for_repairs == Material.adamantium) {
                return Item.adamantiumNugget;
            } else if (material_for_repairs == Materials.nickel) {
                return Items.nickelNugget;
            }else {
                return material_for_repairs == Material.ancient_metal ? Item.ancientMetalNugget : null;
            }
        } else {
            return Item.ironNugget;
        }
    }


    public String getResourceLocationPrefix() {
        return "";
    }
    public float getStrVsBlock(Block block, int metadata, ItemStack itemStack, EntityPlayer user) {
        return 0.0F;
    }
    @Shadow
    public Item setCreativeTab(CreativeModeTab table) {
        return null;
    }
    public void setCreativeTable(CreativeModeTab table) {
        this.setCreativeTab(table);
    }
    @Shadow
    public Item setMaxStackSize(int maxStackSize) {
        return null;
    }
    public void setResourceLocation(String location) {
        this.setTextureName(location);
    }
    @Shadow
    public Item setTextureName(String location) {
        return null;
    }
}
