package net.oilcake.mitelros.mixins.item;

import net.minecraft.*;
import net.oilcake.mitelros.item.Items;
import net.oilcake.mitelros.item.Materials;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(ItemArrow.class)
public class ItemArrowMixin extends Item{
    public ItemArrow itemArrow;
    @Inject(method = "<clinit>",at = @At("RETURN"))
    private static void injectClinit(CallbackInfo callback){
        material_types = new Material[]{Material.rusted_iron, Material.ancient_metal, Materials.nickel, Materials.tungsten};
    }
    @Overwrite
    public void addInformation(ItemStack item_stack, EntityPlayer player, List info, boolean extended_info, Slot slot) {
        if (extended_info) {
            info.add("");
            info.add(EnumChatFormat.BLUE + Translator.getFormatted("item.tooltip.missileDamage", new Object[]{(int)this.getMaterialDamageVsEntity()}));
            info.add(EnumChatFormat.GRAY + Translator.getFormatted("item.tooltip.missileRecovery", new Object[]{(int)(this.getChanceOfRecovery() * 100.0F)}));
            if (this.arrowhead_material == Materials.nickel) {
                //info.add(EnumChatFormat.WHITE + Translator.get("item.tooltip.bonusVsUndead"));
                info.add(EnumChatFormat.LIGHT_GRAY + Translator.get("史莱姆杀手"));
            }
            if(this.arrowhead_material != Material.rusted_iron && this.arrowhead_material != Material.ancient_metal
            &&this.arrowhead_material != Materials.nickel && this.arrowhead_material != Materials.tungsten){
                info.add(EnumChatFormat.RED + Translator.getFormatted("出现崩溃bug中，请勿制作/食用", new Object[0]));
            }
        }
    }


    @Overwrite
    public float getChanceOfRecovery() {
         if (itemArrow == arrowRustedIron) {
            return 0.5F;
        }else if (itemArrow == Items.arrowNickel) {
            return 0.7F;
        } else if (itemArrow != arrowAncientMetal) {
            return itemArrow == Items.arrowTungsten ? 0.9F : 0.3F;
        } else {
            return 0.8F;
        }
    }
    @Shadow
    public float getMaterialDamageVsEntity() {
        return 1F;
    }
    @Shadow
    @Final
    @Mutable
    public static Material[] material_types;
    @Shadow
    @Final
    public Material arrowhead_material;

}
