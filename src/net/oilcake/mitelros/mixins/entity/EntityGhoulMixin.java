package net.oilcake.mitelros.mixins.entity;

import net.minecraft.EntityAnimalWatcher;
import net.minecraft.EntityGhoul;
import net.minecraft.GroupDataEntity;
import net.minecraft.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(EntityGhoul.class)
public class EntityGhoulMixin extends EntityAnimalWatcher {

    public EntityGhoulMixin(World world) {
        super(world);
    }
    @Overwrite
    public GroupDataEntity onSpawnWithEgg(GroupDataEntity par1EntityLivingData) {
        this.setCanPickUpLoot(true);
        return super.onSpawnWithEgg(par1EntityLivingData);
    }
}
