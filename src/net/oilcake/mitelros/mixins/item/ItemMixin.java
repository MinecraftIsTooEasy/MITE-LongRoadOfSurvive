package net.oilcake.mitelros.mixins.item;

import net.oilcake.mitelros.item.Items;
import net.oilcake.mitelros.item.Materials;
import net.minecraft.*;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(Item.class)
public class ItemMixin{
    public Item item;
    private int water;
    public final int getWater() {
        return this.water;
    }
    public Item setWater(int water)
    {
        this.water = water;
        return item;
    }

    public Item setPotionEffectC(String par1Str) {
        this.potionEffect = par1Str;
        return item;
    }
    // must not to do this
//    public final Item setFoodValue(int satiation, int nutrition, int sugar_content, boolean has_protein, boolean has_essential_fats, boolean has_phytonutrients) {
//        this.satiation = satiation;
//        this.nutrition = nutrition;
//        this.sugar_content = sugar_content;
//        this.has_protein = has_protein;
//        this.has_essential_fats = has_essential_fats;
//        this.has_phytonutrients = has_phytonutrients;
//        this.water = nutrition;
//        if (satiation > 0 || nutrition > 0) {
//            this.setCreativeTab(CreativeModeTab.tabFood);
//        }
//
//        return item;
//    }
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
            } else if (material_for_repairs == Materials.tungsten) {
                return Items.tungstenNugget;
            }
            else {
                return material_for_repairs == Material.ancient_metal ? Item.ancientMetalNugget : null;
            }
        } else {
            return Item.ironNugget;
        }
    }
    @Shadow
    private int satiation;
    @Shadow
    private int nutrition;
    @Shadow
    private int sugar_content;
    @Shadow
    private boolean has_protein;
    @Shadow
    private boolean has_essential_fats;
    @Shadow
    private boolean has_phytonutrients;
    @Shadow
    public Material getMaterialForRepairs() {
        return null;
    }
    @Shadow
    private String potionEffect;
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
