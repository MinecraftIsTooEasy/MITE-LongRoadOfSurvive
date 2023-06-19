package net.oilcake.mitelros.mixins.entity.slime;

import net.minecraft.DamageSource;
import net.minecraft.EntityPudding;
import net.oilcake.mitelros.item.Materials;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(EntityPudding.class)
public class EntityPuddingMixin {
    @Overwrite
    public boolean isImmuneTo(DamageSource damage_source) {
//        if (damage_source.getItemAttackedWith() != null) {
//            Material material = damage_source.getItemAttackedWith().getMaterialForRepairs();
//            return !damage_source.hasFireAspect() && !damage_source.isLavaDamage() && !damage_source.hasMagicAspect() && !damage_source.isSnowball() && !(damage_source.getItemAttackedWith().getMaterialForRepairs() == Materials.nickel);
//        }
        boolean temp =!damage_source.isExplosion() && !damage_source.hasFireAspect() && !damage_source.isLavaDamage() && !damage_source.hasMagicAspect() && !damage_source.isSnowball();
        return damage_source.getItemAttackedWith() != null ? temp && !(damage_source.getItemAttackedWith().getMaterialForRepairs() == Materials.nickel) : temp;
    }
}
