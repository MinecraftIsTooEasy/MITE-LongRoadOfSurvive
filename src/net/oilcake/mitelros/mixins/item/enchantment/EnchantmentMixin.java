package net.oilcake.mitelros.mixins.item.enchantment;

import net.minecraft.Enchantment;
import net.oilcake.mitelros.item.enchantment.Enchantments;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Enchantment.class)
public class EnchantmentMixin {
    @Shadow
    @Final
    public static Enchantment[] enchantmentsList;

    @Inject(method = "<clinit>",at = @At("RETURN"))
    private static void injectClinit(CallbackInfo callbackInfo){
        Enchantments.registerEnchantments();
//        for (Enchantment enchantment : enchantmentsList) {
//            if (enchantment != null && !Enchantments.enchantmentsListC.contains(enchantment)){
//                Enchantments.enchantmentsListC.add(enchantment);
//            }
//        }
    }
    public boolean isReverse(){
        return false;
    }
}
