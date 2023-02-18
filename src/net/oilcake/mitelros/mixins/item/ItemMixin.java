package net.oilcake.mitelros.mixins.item;

import net.minecraft.*;
import net.oilcake.mitelros.item.Items;
import net.oilcake.mitelros.item.Materials;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(Item.class)
public class ItemMixin{

    private int water;

//    @Inject(method = "<init>", at = @At("RETURN"))
//    protected void injectInit(CallbackInfo callbackInfo){
//        this.setWater(1);
//    }
    public Item item;

    public final Item setFoodValueForWather(int satiation, int nutrition, int sugar_content, boolean has_protein, boolean has_essential_fats, boolean has_phytonutrients, int water) {
        this.satiation = satiation;
        this.nutrition = nutrition;
        this.sugar_content = sugar_content;
        this.has_protein = has_protein;
        this.has_essential_fats = has_essential_fats;
        this.has_phytonutrients = has_phytonutrients;
        this.water = water;

        if (satiation > 0 || nutrition > 0) {
            this.setCreativeTab(CreativeModeTab.tabFood);
        }

        return item;
    }

//    @Overwrite
//    public void addInformation(ItemStack item_stack, EntityPlayer player, List info, boolean extended_info, Slot slot) {
//        if (extended_info) {
//            int satiation = this.getSatiation(player);
//            int nutrition = this.getNutrition();
//            int water = this.getWater();
//            if (this.satiation > 0 || nutrition > 0) {
//                info.add("");
//                if (item instanceof ItemBlock) {
//                    ItemBlock item_block = (ItemBlock) item;
//                    if (item_block.getBlock() == Block.mushroomRed) {
//                        info.add(EnumChatFormat.RED + Translator.getFormatted("item.tooltip.satiation", new Object[]{satiation}));
//                        info.add(EnumChatFormat.RED + Translator.getFormatted("item.tooltip.nutrition", new Object[]{nutrition}));
//                        info.add(EnumChatFormat.BLUE + Translator.getFormatted(water + "的" + "含水量", new Object[]{water}));
//                        return;
//                    }
//                }
//
//                if (this.satiation > 0) {
//                    info.add((this.sugar_content > 0 && player.isInsulinResistant() ? player.getInsulinResistanceLevel().getColorC() : EnumChatFormat.BROWN) + Translator.getFormatted("item.tooltip.satiation", new Object[]{satiation}));
//                }
//
//                if (nutrition > 0) {
//                    info.add(EnumChatFormat.BROWN + Translator.getFormatted("item.tooltip.nutrition", new Object[]{nutrition}));
//                }
//                if (water > 0) {
//                    info.add(EnumChatFormat.BLUE + Translator.getFormatted("+" + water + "的" + "含水量", new Object[]{water}));
//                }
//                if (water < 0) {
//                    info.add(EnumChatFormat.BLUE + Translator.getFormatted("-" + water + "的" + "含水量", new Object[]{water}));
//
//                }
//            }
//        }
//    }

    public final int getWater() {
        return this.water;
    }
    public Item setWater(int water) {
        this.water = water;
        return item;
    }

    public Item setPotionEffectC(String par1Str) {
        this.setPotionEffect(par1Str);
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
    protected Item setPotionEffect(String par1Str) {
        return item;
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
    @Shadow
    @Final
    public int getSatiation(EntityPlayer player) {
        return 1;
    }
    @Shadow
    public int getNutrition() {
        return 1;
    }

}
