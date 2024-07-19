package net.oilcake.mitelros.mixins.entity;

import net.minecraft.Entity;
import net.minecraft.EntityArrow;
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
import net.minecraft.EntityItem;
import net.minecraft.EntityItemFrame;
import net.minecraft.EntityLeashKnot;
import net.minecraft.EntityLiving;
import net.minecraft.EntityMinecart;
import net.minecraft.EntityPainting;
import net.minecraft.EntityPlayer;
import net.minecraft.EntityPotion;
import net.minecraft.EntitySmallFireball;
import net.minecraft.EntitySnowball;
import net.minecraft.EntityTNTPrimed;
import net.minecraft.EntityTrackerEntry;
import net.minecraft.EntityWeb;
import net.minecraft.EntityWitherSkull;
import net.minecraft.EntityXPOrb;
import net.minecraft.IAnimals;
import net.minecraft.Item;
import net.minecraft.MathHelper;
import net.minecraft.Minecraft;
import net.minecraft.Packet;
import net.minecraft.Packet20NamedEntitySpawn;
import net.minecraft.Packet23VehicleSpawn;
import net.minecraft.Packet24MobSpawn;
import net.minecraft.Packet25EntityPainting;
import net.minecraft.Packet26EntityExpOrb;
import net.minecraft.ServerPlayer;
import net.oilcake.mitelros.entity.EntityWandFireball;
import net.oilcake.mitelros.entity.EntityWandIceBall;
import net.oilcake.mitelros.entity.EntityWandShockWave;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

@Mixin({EntityTrackerEntry.class})
public class EntityTrackerEntryMixin {
   @Shadow
   public Entity myEntity;
   @Shadow
   public int lastHeadMotion;

   @Overwrite
   private Packet getPacketForThisEntity() {
      if (this.myEntity.isDead) {
         this.myEntity.worldObj.getWorldLogAgent().logWarning("Fetching addPacket for removed entity");
      }

      if (this.myEntity instanceof EntityItem) {
         return new Packet23VehicleSpawn(this.myEntity, 2, 1);
      } else if (this.myEntity instanceof ServerPlayer) {
         return new Packet20NamedEntitySpawn((EntityPlayer)this.myEntity);
      } else if (this.myEntity instanceof EntityMinecart) {
         EntityMinecart var9 = (EntityMinecart)this.myEntity;
         return new Packet23VehicleSpawn(this.myEntity, 10, var9.getMinecartType());
      } else if (this.myEntity instanceof EntityBoat) {
         return new Packet23VehicleSpawn(this.myEntity, 1);
      } else if (!(this.myEntity instanceof IAnimals) && !(this.myEntity instanceof EntityDragon)) {
         if (this.myEntity instanceof EntityFishHook) {
            EntityPlayer var8 = ((EntityFishHook)this.myEntity).angler;
            return new Packet23VehicleSpawn(this.myEntity, 90, var8 != null ? var8.entityId : this.myEntity.entityId);
         } else if (this.myEntity instanceof EntityArrow) {
            Entity var7 = ((EntityArrow)this.myEntity).shootingEntity;
            Packet23VehicleSpawn packet = new Packet23VehicleSpawn(this.myEntity, 60, var7 != null ? var7.entityId : this.myEntity.entityId);
            packet.arrow_item_id = ((EntityArrow)this.myEntity).item_arrow.itemID;
            packet.launcher_was_enchanted = ((EntityArrow)this.myEntity).launcher_was_enchanted;
            packet.arrow_stuck_in_block = ((EntityArrow)this.myEntity).isInGround();
            return packet;
         } else if (this.myEntity instanceof EntitySnowball) {
            return new Packet23VehicleSpawn(this.myEntity, 61);
         } else if (this.myEntity instanceof EntityPotion) {
            return new Packet23VehicleSpawn(this.myEntity, 73, ((EntityPotion)this.myEntity).getPotionType());
         } else if (this.myEntity instanceof EntityExpBottle) {
            return new Packet23VehicleSpawn(this.myEntity, 75);
         } else if (this.myEntity instanceof EntityEnderPearl) {
            return new Packet23VehicleSpawn(this.myEntity, 65);
         } else if (this.myEntity instanceof EntityEnderEye) {
            return new Packet23VehicleSpawn(this.myEntity, 72);
         } else if (this.myEntity instanceof EntityFireworkRocket) {
            return new Packet23VehicleSpawn(this.myEntity, 76);
         } else {
            Packet23VehicleSpawn var2;
            if (this.myEntity instanceof EntityFireball) {
               EntityFireball var6 = (EntityFireball)this.myEntity;
               var2 = null;
               byte var3 = 63;
               if (this.myEntity instanceof EntitySmallFireball) {
                  var3 = 64;
               } else if (this.myEntity instanceof EntityWitherSkull) {
                  var3 = 66;
               }

               if (var6.shootingEntity != null) {
                  var2 = new Packet23VehicleSpawn(this.myEntity, var3, ((EntityFireball)this.myEntity).shootingEntity.entityId);
               } else {
                  var2 = new Packet23VehicleSpawn(this.myEntity, var3, 0);
               }

               var2.approx_motion_x = (float)var6.accelerationX;
               var2.approx_motion_y = (float)var6.accelerationY;
               var2.approx_motion_z = (float)var6.accelerationZ;
               return var2;
            } else if (this.myEntity instanceof EntityEgg) {
               return new Packet23VehicleSpawn(this.myEntity, 62);
            } else if (this.myEntity instanceof EntityBrick) {
               return new Packet23VehicleSpawn(this.myEntity, ((EntityBrick)this.myEntity).getModelItem() == Item.netherrackBrick ? 501 : 500);
            } else if (this.myEntity instanceof EntityWandIceBall) {
               return new Packet23VehicleSpawn(this.myEntity, 100);
            } else if (this.myEntity instanceof EntityWandFireball) {
               return new Packet23VehicleSpawn(this.myEntity, 101);
            } else if (this.myEntity instanceof EntityWandShockWave) {
               return new Packet23VehicleSpawn(this.myEntity, 102);
            } else if (this.myEntity instanceof EntityGelatinousSphere) {
               EntityGelatinousSphere sphere = (EntityGelatinousSphere)this.myEntity;
               return new Packet23VehicleSpawn(this.myEntity, 600 + sphere.getModelSubtype());
            } else if (this.myEntity instanceof EntityWeb) {
               return new Packet23VehicleSpawn(this.myEntity, 700);
            } else if (this.myEntity instanceof EntityTNTPrimed) {
               return new Packet23VehicleSpawn(this.myEntity, 50);
            } else if (this.myEntity instanceof EntityEnderCrystal) {
               return new Packet23VehicleSpawn(this.myEntity, 51);
            } else if (this.myEntity instanceof EntityFallingSand) {
               EntityFallingSand var5 = (EntityFallingSand)this.myEntity;
               return new Packet23VehicleSpawn(this.myEntity, 70, var5.blockID | var5.metadata << 16);
            } else if (this.myEntity instanceof EntityPainting) {
               return new Packet25EntityPainting((EntityPainting)this.myEntity);
            } else if (this.myEntity instanceof EntityItemFrame) {
               EntityItemFrame var4 = (EntityItemFrame)this.myEntity;
               var2 = new Packet23VehicleSpawn(this.myEntity, 71, var4.hangingDirection);
               var2.setUnscaledPositionWithIntegers(var4.xPosition, var4.yPosition, var4.zPosition);
               return var2;
            } else if (this.myEntity instanceof EntityLeashKnot) {
               EntityLeashKnot var1 = (EntityLeashKnot)this.myEntity;
               var2 = new Packet23VehicleSpawn(this.myEntity, 77);
               var2.setUnscaledPositionWithIntegers(var1.xPosition, var1.yPosition, var1.zPosition);
               return var2;
            } else if (this.myEntity instanceof EntityXPOrb) {
               return new Packet26EntityExpOrb((EntityXPOrb)this.myEntity);
            } else {
               throw new IllegalArgumentException("Don't know how to add " + this.myEntity.getClass() + "!");
            }
         }
      } else if (this.myEntity instanceof EntityLiving) {
         this.lastHeadMotion = MathHelper.floor_float(this.myEntity.getRotationYawHead() * 256.0F / 360.0F);
         return new Packet24MobSpawn((EntityLiving)this.myEntity);
      } else {
         Minecraft.setErrorMessage("getPacketForThisEntity: entity not handled: " + this.myEntity);
         throw new IllegalArgumentException("Don't know how to add " + this.myEntity.getClass() + "!");
      }
   }
}
