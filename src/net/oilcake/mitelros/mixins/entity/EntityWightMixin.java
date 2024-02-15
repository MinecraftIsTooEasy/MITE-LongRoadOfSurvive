package net.oilcake.mitelros.mixins.entity;

import net.minecraft.EntityMonster;
import net.minecraft.EntityWight;
import net.minecraft.GroupDataEntity;
import net.minecraft.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(EntityWight.class)
public class EntityWightMixin extends EntityMonster{
    public EntityWightMixin(World par1World) {
        super(par1World);
    }
//    @Overwrite
//    public boolean isImmuneTo(DamageSource damage_source) {
//        boolean temp = !damage_source.hasFireAspect() && !damage_source.isLavaDamage() && !damage_source.hasSilverAspect() && !damage_source.hasMagicAspect();
//        return damage_source.getItemAttackedWith() != null ? temp && !(damage_source.getItemAttackedWith().getMaterialForRepairs() == Materials.nickel) : temp;
//    }
    @Overwrite
    public GroupDataEntity onSpawnWithEgg(GroupDataEntity par1EntityLivingData) {
        this.setCanPickUpLoot(true);
        return super.onSpawnWithEgg(par1EntityLivingData);
    }
}
