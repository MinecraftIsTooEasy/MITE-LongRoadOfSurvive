package net.oilcake.mitelros.mixins.entity.slime;

import net.minecraft.DamageSource;
import net.minecraft.EntityCubic;
import net.minecraft.EntityOoze;
import net.minecraft.World;
import net.oilcake.mitelros.item.Materials;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(EntityOoze.class)
public abstract class EntityOozeMixin extends EntityCubic{
    public EntityOozeMixin(World par1World) {
        super(par1World);
    }

    @Overwrite
    public boolean isImmuneTo(DamageSource damage_source) {
        boolean temp = !damage_source.isExplosion() && !damage_source.isLavaDamage() && !damage_source.hasMagicAspect() && !damage_source.isSnowball();
        return damage_source.getItemAttackedWith() != null ? temp && !(damage_source.getItemAttackedWith().getMaterialForRepairs() == Materials.nickel) : temp;
    }

    @Overwrite
    protected void setSize(int size) {
        super.setSize(Math.min(size, 4));
    }


}
