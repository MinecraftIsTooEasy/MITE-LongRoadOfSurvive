package net.oilcake.mitelros.mixins.item;

import net.minecraft.*;
import net.oilcake.mitelros.item.Materials;
import net.oilcake.mitelros.util.StuckTagConfig;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;

@Mixin(ItemArmor.class)
public abstract class ItemArmorMixin extends Item implements IDamageableItem {
    @Shadow
    @Final
    private boolean is_leather;

    @Inject(method = "<init>",at = @At("RETURN"))
    public void injectCtor(CallbackInfo callbackInfo) {
        this.is_leather = this.effective_material == Material.leather || this.effective_material == Materials.wolf_fur;
        this.setCraftingDifficultyAsComponent(this.effective_material.getDurability() * 100.0F * this.getNumComponentsForDurability());
    }
    @Inject(method = "addInformation", at = @At(value = "TAIL"))
    private void InjectInformation(ItemStack item_stack, EntityPlayer player, List info, boolean extended_info, Slot slot,CallbackInfo callbackInfo){
        if (extended_info) {
            if (item_stack != null && item_stack.getMaterialForRepairs() == Materials.nickel) {
                info.add(EnumChatFormat.LIGHT_GRAY + Translator.getFormatted("itemarmor.tooltip.slimeresistance"));
            }
        }
    }
    @Inject(method = "getMultipliedDurability", at = @At(value = "RETURN"), cancellable = true)
    private void InjectFixDurability(CallbackInfoReturnable<Integer> callbackInfo){
        int a = callbackInfo.getReturnValue();
        callbackInfo.setReturnValue(a <= 0 ? 1 : a);
        callbackInfo.cancel();
    }
    @Inject(method = "getMaterialProtection", at = @At(value = "RETURN"), cancellable = true)
    private void InjectNewProtection(CallbackInfoReturnable<Integer> callbackInfo){
        int protection = callbackInfo.getReturnValue();
        if(protection == 0){
            if(this.effective_material == Materials.wolf_fur){
                protection = 3;
            } else if(this.effective_material == Materials.vibranium){
                protection = 6;
            } else if (this.effective_material == Materials.nickel){
                protection = 8;
            } else if (this.effective_material == Materials.tungsten || this.effective_material == Materials.ancient_metal_sacred){
                protection = 9;
            } else if (this.effective_material == Materials.uru){
                protection = 10;
            }
            if (this.is_chain_mail) {
                protection -= 2;
            }
        }
        if(protection < 0){
            protection = 0;
        }
        callbackInfo.setReturnValue(protection);
        callbackInfo.cancel();
    }
    @Inject(method = "getMultipliedProtection", at = @At(value = "RETURN"), cancellable = true)
    private void InjectQualityEnhance(ItemStack item_stack, CallbackInfoReturnable<Float> callbackInfo){
        float protection = callbackInfo.getReturnValue();
        if(item_stack != null){
            protection *= 0.875F + (item_stack.getQuality().ordinal() * 0.0625F);
            callbackInfo.setReturnValue(protection);
            callbackInfo.cancel();
        }
    }
    @Overwrite
    public final float getDamageFactor(ItemStack item_stack, EntityLiving owner) {
        if (owner != null && !owner.isEntityPlayer()) {
            return StuckTagConfig.TagConfig.TagInstinctSurvival.ConfigValue ? 0.75F : 0.5F;
        } else if (owner instanceof EntityPlayer && item_stack.getMaxDamage() > 1 && item_stack.getItemDamage() >= item_stack.getMaxDamage() - 1) {
            return 0.0F;
        } else {
            float armor_damage_factor = StuckTagConfig.TagConfig.TagArmament.ConfigValue ? (4.0F - (float)item_stack.getItemDamage() / (float)item_stack.getItem().getMaxDamage(item_stack) * 4.0F) : (2.0F - (float)item_stack.getItemDamage() / (float)item_stack.getItem().getMaxDamage(item_stack) * 2.0F);
            if (armor_damage_factor > 1.0F) {
                armor_damage_factor = 1.0F;
            }
            return armor_damage_factor;
        }
    }
    @Shadow
    private Material effective_material;
    @Shadow
    @Final
    private boolean is_chain_mail;
    @Shadow
    public final float getProtectionAfterDamageFactor(ItemStack item_stack, EntityLiving owner) {
        return 1.0F;
    }
}
