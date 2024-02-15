package net.oilcake.mitelros.mixins.util;

import net.minecraft.DamageSource;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(DamageSource.class)
public class DamageSourceMixin {
    public DamageSource setFireAspectC(boolean has_fire_aspect){
        return this.setFireAspect(has_fire_aspect);
    }

    @Shadow
    protected DamageSource setFireAspect(boolean has_fire_aspect) {return null;}

}
