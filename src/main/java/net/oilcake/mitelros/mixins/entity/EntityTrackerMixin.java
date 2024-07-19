package net.oilcake.mitelros.mixins.entity;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import net.minecraft.Entity;
import net.minecraft.EntityArrow;
import net.minecraft.EntityBat;
import net.minecraft.EntityBoat;
import net.minecraft.EntityBrick;
import net.minecraft.EntityDragon;
import net.minecraft.EntityEgg;
import net.minecraft.EntityEnderCrystal;
import net.minecraft.EntityEnderEye;
import net.minecraft.EntityEnderPearl;
import net.minecraft.EntityExpBottle;
import net.minecraft.EntityFallingSand;
import net.minecraft.EntityFireball;
import net.minecraft.EntityFireworkRocket;
import net.minecraft.EntityFishHook;
import net.minecraft.EntityGelatinousSphere;
import net.minecraft.EntityHanging;
import net.minecraft.EntityItem;
import net.minecraft.EntityMinecart;
import net.minecraft.EntityPotion;
import net.minecraft.EntitySmallFireball;
import net.minecraft.EntitySnowball;
import net.minecraft.EntitySquid;
import net.minecraft.EntityTNTPrimed;
import net.minecraft.EntityTracker;
import net.minecraft.EntityTrackerEntry;
import net.minecraft.EntityWeb;
import net.minecraft.EntityWither;
import net.minecraft.EntityXPOrb;
import net.minecraft.IAnimals;
import net.minecraft.ServerPlayer;
import net.oilcake.mitelros.entity.EntityWandFireball;
import net.oilcake.mitelros.entity.EntityWandIceBall;
import net.oilcake.mitelros.entity.EntityWandShockWave;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

@Mixin({EntityTracker.class})
public class EntityTrackerMixin {
   @Shadow
   private Set trackedEntities = new HashSet();

   @Overwrite
   public void addEntityToTracker(Entity par1Entity) {
      if (par1Entity instanceof ServerPlayer) {
         this.addEntityToTracker(par1Entity, 512, 2);
         ServerPlayer var2 = (ServerPlayer)par1Entity;
         Iterator var3 = this.trackedEntities.iterator();

         while(var3.hasNext()) {
            EntityTrackerEntry var4 = (EntityTrackerEntry)var3.next();
            if (var4.myEntity != var2) {
               var4.tryStartWachingThis(var2);
            }
         }
      } else if (par1Entity instanceof EntityFishHook) {
         this.addEntityToTracker(par1Entity, 64, 5, true);
      } else if (par1Entity instanceof EntityArrow) {
         this.addEntityToTracker(par1Entity, 64, 20, false);
      } else if (par1Entity instanceof EntitySmallFireball) {
         this.addEntityToTracker(par1Entity, 64, 10, false);
      } else if (par1Entity instanceof EntityFireball) {
         this.addEntityToTracker(par1Entity, 64, 10, false);
      } else if (par1Entity instanceof EntitySnowball) {
         this.addEntityToTracker(par1Entity, 64, 10, true);
      } else if (par1Entity instanceof EntityEnderPearl) {
         this.addEntityToTracker(par1Entity, 64, 10, true);
      } else if (par1Entity instanceof EntityEnderEye) {
         this.addEntityToTracker(par1Entity, 64, 4, true);
      } else if (par1Entity instanceof EntityEgg) {
         this.addEntityToTracker(par1Entity, 64, 10, true);
      } else if (par1Entity instanceof EntityBrick) {
         this.addEntityToTracker(par1Entity, 64, 10, true);
      } else if (par1Entity instanceof EntityWandFireball) {
         this.addEntityToTracker(par1Entity, 64, 10, true);
      } else if (par1Entity instanceof EntityWandIceBall) {
         this.addEntityToTracker(par1Entity, 64, 10, true);
      } else if (par1Entity instanceof EntityWandShockWave) {
         this.addEntityToTracker(par1Entity, 64, 10, true);
      } else if (par1Entity instanceof EntityWeb) {
         this.addEntityToTracker(par1Entity, 64, 10, true);
      } else if (par1Entity instanceof EntityGelatinousSphere) {
         this.addEntityToTracker(par1Entity, 64, 10, true);
      } else if (par1Entity instanceof EntityPotion) {
         this.addEntityToTracker(par1Entity, 64, 10, true);
      } else if (par1Entity instanceof EntityExpBottle) {
         this.addEntityToTracker(par1Entity, 64, 10, true);
      } else if (par1Entity instanceof EntityFireworkRocket) {
         this.addEntityToTracker(par1Entity, 64, 10, true);
      } else if (par1Entity instanceof EntityItem) {
         this.addEntityToTracker(par1Entity, 64, 20, true);
      } else if (par1Entity instanceof EntityMinecart) {
         this.addEntityToTracker(par1Entity, 80, 3, true);
      } else if (par1Entity instanceof EntityBoat) {
         this.addEntityToTracker(par1Entity, 80, 3, true);
      } else if (par1Entity instanceof EntitySquid) {
         this.addEntityToTracker(par1Entity, 64, 3, true);
      } else if (par1Entity instanceof EntityWither) {
         this.addEntityToTracker(par1Entity, 80, 3, false);
      } else if (par1Entity instanceof EntityBat) {
         this.addEntityToTracker(par1Entity, 80, 3, false);
      } else if (par1Entity instanceof IAnimals) {
         this.addEntityToTracker(par1Entity, 80, 3, true);
      } else if (par1Entity instanceof EntityDragon) {
         this.addEntityToTracker(par1Entity, 160, 3, true);
      } else if (par1Entity instanceof EntityTNTPrimed) {
         this.addEntityToTracker(par1Entity, 160, 10, true);
      } else if (par1Entity instanceof EntityFallingSand) {
         this.addEntityToTracker(par1Entity, 160, 20, true);
      } else if (par1Entity instanceof EntityHanging) {
         this.addEntityToTracker(par1Entity, 160, Integer.MAX_VALUE, false);
      } else if (par1Entity instanceof EntityXPOrb) {
         this.addEntityToTracker(par1Entity, 160, 20, true);
      } else if (par1Entity instanceof EntityEnderCrystal) {
         this.addEntityToTracker(par1Entity, 256, Integer.MAX_VALUE, false);
      }

   }

   @Shadow
   public void addEntityToTracker(Entity par1Entity, int par2, int par3, boolean par4) {
   }

   @Shadow
   public void addEntityToTracker(Entity par1Entity, int par2, int par3) {
   }
}
