package net.oilcake.mitelros.mixins.entity;

import net.minecraft.*;
import net.oilcake.mitelros.item.Materials;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

import java.util.ArrayList;
import java.util.List;

@Mixin(EntityWight.class)
public class EntityWightMixin extends EntityWight {
    public EntityWightMixin(World par1World) {
        super(par1World);
    }

    @Overwrite
    public boolean isImmuneTo(DamageSource damage_source) {
        boolean temp = !damage_source.hasFireAspect() && !damage_source.isLavaDamage() && !damage_source.hasSilverAspect() && !damage_source.hasMagicAspect();
        return damage_source.getItemAttackedWith() != null ? temp && !(damage_source.getItemAttackedWith().getMaterialForRepairs() == Materials.nickel) : temp;
    }
}
