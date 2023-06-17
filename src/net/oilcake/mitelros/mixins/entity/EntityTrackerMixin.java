package net.oilcake.mitelros.mixins.entity;

import net.minecraft.*;
import net.oilcake.mitelros.entity.EntityWandFireball;
import net.oilcake.mitelros.entity.EntityWandIceBall;
import net.oilcake.mitelros.entity.EntityWandShockWave;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

@Mixin(EntityTracker.class)
public class EntityTrackerMixin {

    @Shadow
    private Set trackedEntities = new HashSet();

    @Overwrite
    public void trackEntity(Entity par1Entity) {
        if (par1Entity instanceof ServerPlayer) {
            this.trackEntity(par1Entity, 512, 2);
            ServerPlayer var2 = (ServerPlayer)par1Entity;
            Iterator var3 = this.trackedEntities.iterator();

            while(var3.hasNext()) {
                EntityTrackerEntry var4 = (EntityTrackerEntry)var3.next();
                if (var4.trackedEntity != var2) {
                    var4.updatePlayerEntity(var2);
                }
            }
        } else if (par1Entity instanceof EntityFishingHook) {
            this.trackEntity(par1Entity, 64, 5, true);
        } else if (par1Entity instanceof EntityArrow) {
            this.trackEntity(par1Entity, 64, 20, false);
        } else if (par1Entity instanceof EntitySmallFireball) {
            this.trackEntity(par1Entity, 64, 10, false);
        } else if (par1Entity instanceof EntityFireball) {
            this.trackEntity(par1Entity, 64, 10, false);
        } else if (par1Entity instanceof EntitySnowball) {
            this.trackEntity(par1Entity, 64, 10, true);
        } else if (par1Entity instanceof EntityEnderPearl) {
            this.trackEntity(par1Entity, 64, 10, true);
        } else if (par1Entity instanceof EntityEnderSignal) {
            this.trackEntity(par1Entity, 64, 4, true);
        } else if (par1Entity instanceof EntityEgg) {
            this.trackEntity(par1Entity, 64, 10, true);
        } else if (par1Entity instanceof EntityBrick) {
            this.trackEntity(par1Entity, 64, 10, true);
        }

         else if (par1Entity instanceof EntityWandFireball) {
            this.trackEntity(par1Entity, 64, 10, true);
        } else if (par1Entity instanceof EntityWandIceBall) {
            this.trackEntity(par1Entity, 64, 10, true);
        } else if (par1Entity instanceof EntityWandShockWave) {
            this.trackEntity(par1Entity, 64, 10, true);
        }


        else if (par1Entity instanceof EntityWeb) {
            this.trackEntity(par1Entity, 64, 10, true);
        } else if (par1Entity instanceof EntityGelatinousSphere) {
            this.trackEntity(par1Entity, 64, 10, true);
        } else if (par1Entity instanceof EntityPotion) {
            this.trackEntity(par1Entity, 64, 10, true);
        } else if (par1Entity instanceof EntityThrownExpBottle) {
            this.trackEntity(par1Entity, 64, 10, true);
        } else if (par1Entity instanceof EntityFireworks) {
            this.trackEntity(par1Entity, 64, 10, true);
        } else if (par1Entity instanceof EntityItem) {
            this.trackEntity(par1Entity, 64, 20, true);
        } else if (par1Entity instanceof EntityMinecartAbstract) {
            this.trackEntity(par1Entity, 80, 3, true);
        } else if (par1Entity instanceof EntityBoat) {
            this.trackEntity(par1Entity, 80, 3, true);
        } else if (par1Entity instanceof EntitySquid) {
            this.trackEntity(par1Entity, 64, 3, true);
        } else if (par1Entity instanceof EntityWither) {
            this.trackEntity(par1Entity, 80, 3, false);
        } else if (par1Entity instanceof EntityBat) {
            this.trackEntity(par1Entity, 80, 3, false);
        } else if (par1Entity instanceof IAnimal) {
            this.trackEntity(par1Entity, 80, 3, true);
        } else if (par1Entity instanceof EntityEnderDragon) {
            this.trackEntity(par1Entity, 160, 3, true);
        } else if (par1Entity instanceof EntityTNTPrimed) {
            this.trackEntity(par1Entity, 160, 10, true);
        } else if (par1Entity instanceof EntityFallingBlock) {
            this.trackEntity(par1Entity, 160, 20, true);
        } else if (par1Entity instanceof EntityHanging) {
            this.trackEntity(par1Entity, 160, Integer.MAX_VALUE, false);
        } else if (par1Entity instanceof EntityExperienceOrb) {
            this.trackEntity(par1Entity, 160, 20, true);
        } else if (par1Entity instanceof EntityEnderCrystal) {
            this.trackEntity(par1Entity, 256, Integer.MAX_VALUE, false);
        }
    }
    @Shadow
    public void trackEntity(Entity par1Entity, int par2, int par3, boolean par4) {

    }
    @Shadow
    public void trackEntity(Entity par1Entity, int par2, int par3) {

    }

}

