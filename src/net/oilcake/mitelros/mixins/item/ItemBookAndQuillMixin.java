package net.oilcake.mitelros.mixins.item;

import net.minecraft.Item;
import net.minecraft.ItemBookAndQuill;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ItemBookAndQuill.class)
public class ItemBookAndQuillMixin extends Item {
    @Inject(method = "<init>",at = @At("RETURN"))
    public void injectCtor(CallbackInfo callbackInfo) {
        this.setCraftingDifficultyAsComponent(10000.0F);
    }
}
