package net.oilcake.mitelros.mixins.entity;

import net.minecraft.Damage;
import net.minecraft.DamageSource;
import net.minecraft.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(Damage.class)
public class DamageMixin {
    @Shadow
    private DamageSource source;

    public Entity getResponsibleEntityC() {
        return this.source.getResponsibleEntity();
    }
}
