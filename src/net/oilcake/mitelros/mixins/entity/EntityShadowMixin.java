package net.oilcake.mitelros.mixins.entity;

import net.minecraft.DamageSource;
import net.minecraft.EntityShadow;
import net.oilcake.mitelros.item.Materials;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(EntityShadow.class)
public class EntityShadowMixin {
    @Overwrite
    public boolean isImmuneTo(DamageSource damage_source) {
        boolean temp = !damage_source.hasSilverAspect() && !damage_source.hasMagicAspect() && !damage_source.isSunlight();
        return damage_source.getItemAttackedWith() != null ? temp && !(damage_source.getItemAttackedWith().getMaterialForRepairs() == Materials.nickel) : temp;
    }
}
