package net.oilcake.mitelros.mixins.util;

import net.minecraft.Damage;
import net.minecraft.DamageSource;
import net.minecraft.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(Damage.class)
public class DamageMixin {

    public Entity getResponsibleEntityC() {
        return this.getResponsibleEntity();
    }
    @Shadow
    Entity getResponsibleEntity() {
        return null;
    }
}
