package net.oilcake.mitelros.mixins.entity.slime;

import net.oilcake.mitelros.item.Materials;
import net.minecraft.DamageSource;
import net.minecraft.EntityOoze;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(EntityOoze.class)
public class EntityOozeMixin {
    @Overwrite
    public boolean isImmuneTo(DamageSource damage_source) {
        boolean temp = !damage_source.hasFireAspect() && !damage_source.isLavaDamage() && !damage_source.hasMagicAspect() && !damage_source.isSnowball();
        return damage_source.getItemAttackedWith() != null ? temp && !(damage_source.getItemAttackedWith().getMaterialForRepairs() == Materials.nickel) : temp;
    }
}
