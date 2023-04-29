package net.oilcake.mitelros.mixins.entity;

import net.minecraft.*;
import net.oilcake.mitelros.item.Materials;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

import java.util.ArrayList;
import java.util.List;

@Mixin(EntityShadow.class)
public class EntityShadowMixin extends EntityShadow{
    public EntityShadowMixin(World par1World) {
        super(par1World);
    }

    @Overwrite
    public boolean isImmuneTo(DamageSource damage_source) {
        boolean temp = !damage_source.hasSilverAspect() && !damage_source.hasMagicAspect() && !damage_source.isSunlight();
        return damage_source.getItemAttackedWith() != null ? temp && !(damage_source.getItemAttackedWith().getMaterialForRepairs() == Materials.nickel) : temp;
    }
}
