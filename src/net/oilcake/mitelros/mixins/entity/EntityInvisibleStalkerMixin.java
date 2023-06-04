package net.oilcake.mitelros.mixins.entity;

import net.minecraft.EntityInvisibleStalker;
import net.minecraft.EntityMonster;
import net.minecraft.GroupDataEntity;
import net.minecraft.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(EntityInvisibleStalker.class)
public class EntityInvisibleStalkerMixin extends EntityMonster {
    public EntityInvisibleStalkerMixin(World world) {
        super(world);
    }
    @Overwrite
    public GroupDataEntity onSpawnWithEgg(GroupDataEntity par1EntityLivingData) {
        this.setCanPickUpLoot(true);
        return super.onSpawnWithEgg(par1EntityLivingData);
    }
}
