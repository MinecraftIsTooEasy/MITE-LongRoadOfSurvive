//package net.oilcake.mitelros.mixins.item;
//
//import net.minecraft.Item;
//import net.minecraft.ItemFood;
//import net.minecraft.ItemGoldenApple;
//import net.minecraft.PotionBrewer;
//import net.oilcake.mitelros.mixins.util.PotionBrewerMixin;
//import org.spongepowered.asm.mixin.Mixin;
//import org.spongepowered.asm.mixin.Shadow;
//import org.spongepowered.asm.mixin.injection.At;
//import org.spongepowered.asm.mixin.injection.Inject;
//import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
//
//@Mixin(ItemGoldenApple.class)
//public class ItemGoldenAppleMixin extends ItemFood {
//
//    @Inject(method = "<init>",at = @At("RETURN"))
//    private void injectCtor(CallbackInfo callback){
//        this.setWater(10);
//    }
//
//
//}
