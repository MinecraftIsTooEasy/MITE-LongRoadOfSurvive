package net.oilcake.mitelros.mixins.item;

import net.minecraft.*;
import net.oilcake.mitelros.item.Items;
import net.oilcake.mitelros.item.Materials;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

import static net.xiaoyu233.fml.util.ReflectHelper.dyCast;

@Mixin(ItemArrow.class)
public class ItemArrowMixin extends Item{
    @Inject(method = "<clinit>",at = @At("RETURN"))
    private static void injectClinit(CallbackInfo callback){
        material_types = new Material[]{Material.flint,Material.obsidian,Material.copper,Material.silver,Material.gold,Material.iron,Material.rusted_iron, Material.ancient_metal,Material.mithril,Material.adamantium,Materials.nickel,Materials.tungsten,Materials.magical};
    }
    @Overwrite
    public void addInformation(ItemStack item_stack, EntityPlayer player, List info, boolean extended_info, Slot slot) {
        if (extended_info) {
            info.add("");
            info.add(EnumChatFormat.BLUE + Translator.getFormatted("item.tooltip.missileDamage", new Object[]{(int)this.getMaterialDamageVsEntity()}));
            info.add(EnumChatFormat.GRAY + Translator.getFormatted("item.tooltip.missileRecovery", new Object[]{(int)(this.getChanceOfRecovery() * 100.0F)}));
            if (this.arrowhead_material == Materials.nickel) {
                info.add(EnumChatFormat.LIGHT_GRAY + Translator.getFormatted("itemtool.tooltip.slimeresistance"));
            }
        }
    }


    @Overwrite
    public float getChanceOfRecovery() {
        if (dyCast(this) == arrowFlint) {
            return 0.3F;
        }else if (dyCast(this) == Item.arrowObsidian) {
            return 0.4F;
        }else if (dyCast(this) == Item.arrowCopper) {
            return 0.6F;
        }else if (dyCast(this) == Item.arrowSilver) {
            return 0.6F;
        }else if (dyCast(this) == Item.arrowGold) {
            return 0.5F;
        }else if (dyCast(this) == Item.arrowRustedIron) {
            return 0.5F;
        }else if (dyCast(this) == Item.arrowIron) {
            return 0.7F;
        }else if (dyCast(this) == Items.arrowNickel) {
            return 0.7F;
        }else if (dyCast(this) == Item.arrowMithril) {
            return 0.8F;
        }else if (dyCast(this) == Items.arrowAncientMetal) {
            return 0.8F;
        }else if (dyCast(this) == Items.arrowTungsten) {
            return 0.9F;
        }else if (dyCast(this) == Item.arrowAdamantium) {
            return 0.9F;
        }else if (dyCast(this) == Items.arrowMagical) {
            return 0.0F;
        }else {
            return 0.7F;
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
