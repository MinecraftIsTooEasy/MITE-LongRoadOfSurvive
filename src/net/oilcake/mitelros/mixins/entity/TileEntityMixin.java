package net.oilcake.mitelros.mixins.entity;

import net.minecraft.TileEntity;
import net.oilcake.mitelros.block.enchantreserver.TileEntityEnchantReserver;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
@Mixin(TileEntity.class)
public class TileEntityMixin {
    @Shadow
    private static void addMapping(Class par0Class, String par1Str) {
    }

    @Inject(method = "<clinit>",at = @At(value = "RETURN"))
    private static void registerEnchantReserver(CallbackInfo c){
        addMapping(TileEntityEnchantReserver.class, "EnchantReserver");
    }
}
