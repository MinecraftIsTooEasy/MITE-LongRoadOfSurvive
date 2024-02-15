package net.oilcake.mitelros.mixins.item;

import com.google.common.collect.Multimap;
import net.minecraft.*;
import net.oilcake.mitelros.item.ItemGoldenAppleLegend;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import static net.xiaoyu233.fml.util.ReflectHelper.dyCast;

@Mixin(ItemStack.class)
public class ItemStackMixin{

    public int getWater() {
        return this.getItem().getWater();
    }

    @Overwrite
    public List getTooltip(EntityPlayer par1EntityPlayer, boolean par2, Slot slot) {
        ArrayList var3 = new ArrayList();
        Item var4 = Item.itemsList[this.itemID];
        String var5 = EnumChatFormat.WHITE + this.getMITEStyleDisplayName();
        boolean is_map = this.itemID == Item.map.itemID;
        if (par2 && par1EntityPlayer.inCreativeMode() && !is_map) {
            String var6 = "";
            if (var5.length() > 0) {
                var5 = var5 + " (";
                var6 = ")";
            }

            if (this.getHasSubtypes()) {
                var5 = var5 + String.format("#%04d/%d%s", this.itemID, this.subtype, var6);
            } else {
                var5 = var5 + String.format("#%04d%s", this.itemID, var6);
            }

            if (this.hasSignature()) {
                var5 = var5 + " [" + this.getSignature() + "]";
            }
        } else if (!this.hasDisplayName() && is_map) {
            if (ItemWorldMap.isBeingExtended(dyCast(this))) {
                var5 = "Extended Map";
            } else {
                var5 = var5 + " #" + this.subtype;
            }
        }

        var3.add(var5);
        if (var4.hasQuality()) {
            var3.add(EnumChatFormat.GRAY + this.getQuality().getDescriptor());
        }

        var4.addInformationBeforeEnchantments(dyCast(this), par1EntityPlayer, var3, par2, slot);
        int experience_cost;
        int required_heat_level;
        int hypothetical_level;
        if (this.hasTagCompound()) {
            NBTTagList var14 = this.getEnchantmentTagList();
            if (var14 != null) {
                if (var14.tagCount() > 0) {
                    var3.add("");
                }

                for(experience_cost = 0; experience_cost < var14.tagCount(); ++experience_cost) {
                    required_heat_level = ((NBTTagCompound)var14.tagAt(experience_cost)).getShort("id");
                    hypothetical_level = ((NBTTagCompound)var14.tagAt(experience_cost)).getShort("lvl");
                    if (Enchantment.enchantmentsList[required_heat_level] != null) {
                        if(Enchantment.enchantmentsList[required_heat_level].isReverse()){
                            var3.add(EnumChatFormat.RED + Enchantment.enchantmentsList[required_heat_level].getTranslatedName(hypothetical_level, dyCast(this)));
                        }else{
                            var3.add(EnumChatFormat.AQUA + Enchantment.enchantmentsList[required_heat_level].getTranslatedName(hypothetical_level, dyCast(this)));
                        }
                    }
                }
            }
        }

        var4.addInformation(dyCast(this), par1EntityPlayer, var3, par2, slot);
        if (this.hasTagCompound() && this.stackTagCompound.hasKey("display")) {
            NBTTagCompound var17 = this.stackTagCompound.getCompoundTag("display");
            if (var17.hasKey("color") && par2) {
                var3.add("");
                var3.add("Dyed Color: #" + Integer.toHexString(var17.getInteger("color")).toUpperCase());
            }

            if (var17.hasKey("Lore")) {
                NBTTagList var19 = var17.getTagList("Lore");
                if (var19.tagCount() > 0) {
                    for(required_heat_level = 0; required_heat_level < var19.tagCount(); ++required_heat_level) {
                        var3.add(EnumChatFormat.DARK_PURPLE + "" + EnumChatFormat.ITALIC + ((NBTTagString)var19.tagAt(required_heat_level)).data);
                    }
                }
            }
        }

        Multimap var16 = this.getAttributeModifiers();
        if (par2 && !var16.isEmpty()) {
            var3.add("");
            Iterator var15 = var16.entries().iterator();

            while(var15.hasNext()) {
                Map.Entry var18 = (Map.Entry)var15.next();
                AttributeModifier var21 = (AttributeModifier)var18.getValue();
                double var10 = var21.getAmount();
                double var12;
                if (var21.getOperation() != 1 && var21.getOperation() != 2) {
                    var12 = var21.getAmount();
                } else {
                    var12 = var21.getAmount() * 100.0;
                }

                if (var10 > 0.0) {
                    var3.add(EnumChatFormat.BLUE + LocaleI18n.translateToLocalFormatted("attribute.modifier.plus." + var21.getOperation(), new Object[]{field_111284_a.format(var12), LocaleI18n.translateToLocal("attribute.name." + (String)var18.getKey())}));
                } else if (var10 < 0.0) {
                    var12 *= -1.0;
                    var3.add(EnumChatFormat.RED + LocaleI18n.translateToLocalFormatted("attribute.modifier.take." + var21.getOperation(), new Object[]{field_111284_a.format(var12), LocaleI18n.translateToLocal("attribute.name." + (String)var18.getKey())}));
                }
            }
        }

        if (par2 && var4 instanceof ItemTool) {
            ItemTool tool = (ItemTool)var4;
            if (tool.getToolMaterial() == Material.silver) {
                var3.add(EnumChatFormat.WHITE + Translator.get("item.tooltip.bonusVsUndead"));
            }
        }

        if (par2 && this.getQuality() != null) {
            float modifier = this.getQuality().getDurabilityModifier();
            if (modifier < 1.0F) {
                var3.add(EnumChatFormat.RED + Translator.getFormatted("item.tooltip.durabilityPenalty", new Object[]{(int)((1.0F - modifier) * 100.0F)}));
            } else if (modifier > 1.0F) {
                var3.add(EnumChatFormat.BLUE + Translator.getFormatted("item.tooltip.durabilityBonus", new Object[]{(int)((modifier - 1.0F) * 100.0F)}));
            }
        }

        if (this.isArtifact()) {
            var3.add("");
            var3.add(EnumChatFormat.AQUA + "Artifact");
        }

        if (this.hasTagCompound() && par2 && this.stackTagCompound.hasKey("flavor_text")) {
            String text = this.stackTagCompound.getString("flavor_text");
            List text_lines = Minecraft.O.l.c(text, 120);
            var3.add("");

            for(hypothetical_level = 0; hypothetical_level < text_lines.size(); ++hypothetical_level) {
                var3.add(EnumChatFormat.DARK_GRAY + "" + EnumChatFormat.ITALIC + (String)text_lines.get(hypothetical_level));
            }
        }

        if (par2 && (Minecraft.O.u.x || par1EntityPlayer.inCreativeMode()) && this.isItemStackDamageable()) {
            var3.add("");
            if (this.isItemDamaged()) {
                var3.add(Translator.get("item.tooltip.durability") + " " + (this.getMaxDamage() - this.getItemDamageForDisplay()) + " / " + this.getMaxDamage());
            } else {
                var3.add(Translator.get("item.tooltip.durability") + " " + this.getMaxDamage());
            }
        }

        if (slot instanceof SlotResult) {
            experience_cost = ((ClientPlayer)par1EntityPlayer).crafting_experience_cost;
            if (experience_cost == 0 && par1EntityPlayer.getAsEntityClientPlayerMP().crafting_experience_cost_tentative > 0) {
                experience_cost = par1EntityPlayer.getAsEntityClientPlayerMP().crafting_experience_cost_tentative;
            }

            SlotResult slot_crafting = (SlotResult)slot;
            if (experience_cost == 0 && slot_crafting.getNumCraftingResultsC(par1EntityPlayer) > 1) {
                var3.add("");
                Item item = this.getItem();
                if (item.hasQuality()) {
                    Translator.addToList(EnumChatFormat.YELLOW, "container.crafting.differentQuality", var3);
                } else if (item instanceof ItemRunestone) {
                    Translator.addToList(EnumChatFormat.YELLOW, "container.crafting.differentRunestone", var3);
                }
            } else if (experience_cost > 0) {
                hypothetical_level = par1EntityPlayer.getExperienceLevel(par1EntityPlayer.experience - experience_cost);
                int level_cost = par1EntityPlayer.getExperienceLevel() - hypothetical_level;
                var3.add("");
                if (level_cost == 0) {
                    Translator.addToList(EnumChatFormat.YELLOW, "container.crafting.qualityCostLessThanOneLevel", var3);
                } else if (level_cost == 1) {
                    Translator.addToList(EnumChatFormat.YELLOW, "container.crafting.qualityCostOneLevel", var3);
                } else {
                    Translator.addToListFormatted(EnumChatFormat.YELLOW, "container.crafting.qualityCostMoreThanOneLevel", var3, new Object[]{level_cost});
                }
            }
        } else if (slot != null && slot.inventory instanceof TileEntityFurnace) {
            TileEntityFurnace tile_entity_furnace = (TileEntityFurnace)slot.inventory;
            if (tile_entity_furnace.getStackInSlot(0) == dyCast(this)) {
                required_heat_level = TileEntityFurnace.getHeatLevelRequired(this.itemID);
                hypothetical_level = tile_entity_furnace.heat_level > 0 ? tile_entity_furnace.heat_level : tile_entity_furnace.getFuelHeatLevel();
                if (hypothetical_level > 0 && hypothetical_level < required_heat_level) {
                    var3.add(EnumChatFormat.RED + Translator.get("container.furnace.needsMoreHeat"));
                }
                if (hypothetical_level > 0 && hypothetical_level > required_heat_level + 1) {
                    var3.add(EnumChatFormat.RED + Translator.get("container.furnace.tooHot"));
                }
                if (tile_entity_furnace.getInputItemStack().getItem() instanceof ItemFood && tile_entity_furnace.isBlastFurnace()){
                    var3.add(EnumChatFormat.YELLOW + Translator.get("container.furnace.wontFit"));
                }
                if (!(tile_entity_furnace.getInputItemStack().getItem() instanceof ItemFood) && tile_entity_furnace.isSmoker()){
                    var3.add(EnumChatFormat.YELLOW + Translator.get("container.furnace.wontFit"));
                }
            }
        }
        return var3;
    }
    @Overwrite
    public boolean isEnchantable() {
        if (this.getItem() == Item.book) {
            return true;
        } else if (!ItemPotion.isBottleOfWater(this.getItem().getItemStackForStatsIcon()) && !ItemGoldenApple.isUnenchantedGoldenApple(this.getItem().getItemStackForStatsIcon()) && !ItemGoldenAppleLegend.isUnenchantedGoldenApple(this.getItem().getItemStackForStatsIcon())) {
            if (this.getMaxStackSize() != 1) {
                return false;
            } else if (!this.isItemStackDamageable()) {
                return false;
            } else {
                return this.getItem().getItemEnchantability() > 0 && !this.isItemEnchanted();
            }
        } else {
            return true;
        }
    }


    @Shadow
    public int getMaxStackSize() {
        return 0;
    }
    @Shadow
    public boolean isItemEnchanted() {
        return false;
    }

    public boolean isItemStackEqualC(ItemStack par1ItemStack, boolean ignore_stack_size, boolean ignore_quality, boolean ignore_damage_but_not_subtype, boolean ignore_tag_compound) {
        return this.isItemStackEqual(par1ItemStack, ignore_stack_size, ignore_quality, ignore_damage_but_not_subtype, ignore_tag_compound);
    }
    @Shadow
    public EnumQuality getQuality() {
        return null;
    }
    @Shadow
    private EnumQuality quality;
    @Shadow
    @Final
    public static DecimalFormat field_111284_a;
    @Shadow
    public Multimap getAttributeModifiers() {return null;}
    @Shadow
    protected boolean isItemStackEqual(ItemStack par1ItemStack, boolean ignore_stack_size, boolean ignore_quality, boolean ignore_damage_but_not_subtype, boolean ignore_tag_compound) {
        return false;
    }
    @Shadow
    public String getMITEStyleDisplayName() {return null;}
    @Shadow
    public boolean hasSignature() {return false;}
    @Shadow
    public int getSignature() {return 1;}
    @Shadow
    public boolean hasTagCompound() {
        return false;
    }
    @Shadow
    public NBTTagList getEnchantmentTagList() {return null;}
    @Shadow
    public boolean hasDisplayName() {return false;}
    @Shadow
    public int getMaxDamage() {return 1;}
    @Shadow
    public Item getItem() {
        return null;
    }
    @Shadow
    public boolean isItemStackDamageable() {
        return false;
    }
    @Shadow
    public static boolean areItemStackTagsEqual(ItemStack par0ItemStack, ItemStack par1ItemStack) {
        return false;
    }
    @Shadow
    public boolean getHasSubtypes() {
        return false;
    }
    @Shadow
    public int getItemDamageForDisplay() {
        return 1;
    }
    @Shadow
    public int getItemSubtype() {
        return 1;
    }
    @Shadow
    public int getItemDamage() {
        return 1;
    }
    @Shadow
    private boolean is_artifact;
    public boolean getIsArtifact(){
        return is_artifact;
    }
    @Shadow
    public int stackSize;
    @Shadow
    public int animationsToGo;
    @Shadow
    public int itemID;
    @Shadow
    public NBTTagCompound stackTagCompound;
    @Shadow
    private int subtype;
    @Shadow
    private int damage;
    @Shadow
    public boolean isItemDamaged() {return false;}
    @Shadow
    public boolean isArtifact() {return false;}
}
