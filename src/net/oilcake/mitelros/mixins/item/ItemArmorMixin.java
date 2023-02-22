package net.oilcake.mitelros.mixins.item;

import net.minecraft.*;
import net.oilcake.mitelros.item.Materials;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

import java.util.List;

@Mixin(ItemArmor.class)
public class ItemArmorMixin extends Item {
    @Overwrite
    public void addInformation(ItemStack item_stack, EntityPlayer player, List info, boolean extended_info, Slot slot) {
        if (extended_info) {
            info.add("");
            float protection = this.getProtectionAfterDamageFactor(item_stack, player);
            int decimal_places = protection < 1.0F ? 2 : 1;
            info.add(EnumChatFormat.BLUE + Translator.getFormatted("item.tooltip.protectionBonus", new Object[]{StringHelper.formatFloat(protection, decimal_places, decimal_places)}));

            if (item_stack != null && item_stack.getMaterialForRepairs() == Materials.nickel) {
                info.add(EnumChatFormat.LIGHT_GRAY + Translator.getFormatted("itemarmor.tooltip.slimeresistance"));
            }
        }
    }

    @Overwrite
    public int getMaterialProtection() {
        int protection;
        if (this.effective_material == Material.leather) {
            protection = 2;
        } else if(this.effective_material == Materials.wolf_fur){
            protection = 5;
        } else if (this.effective_material == Material.rusted_iron) {
            protection = 6;
        } else if (this.effective_material == Material.copper) {
            protection = 7;
        } else if (this.effective_material == Material.silver) {
            protection = 7;
        } else if (this.effective_material == Material.gold) {
            protection = 6;
        } else if (this.effective_material != Material.iron && this.effective_material != Material.ancient_metal && this.effective_material != Materials.nickel) {
            if (this.effective_material == Material.mithril || this.effective_material == Materials.tungsten) {
                protection = 9;
            } else {
                if (this.effective_material != Material.adamantium) {
                    return 0;
                }

                protection = 10;
            }
        } else {
            protection = 8;
        }

        if (this.is_chain_mail) {
            protection -= 2;
        }

        return protection;
    }
    @Shadow
    public final float getProtectionAfterDamageFactor(ItemStack item_stack, EntityLiving owner) {
        return 1F;
    }
    @Shadow
    private Material effective_material;
    @Shadow
    @Final
    private boolean is_chain_mail;
}
