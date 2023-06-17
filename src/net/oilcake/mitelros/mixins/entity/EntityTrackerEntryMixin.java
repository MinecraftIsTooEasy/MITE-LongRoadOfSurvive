package net.oilcake.mitelros.mixins.entity;

import net.minecraft.*;
import net.oilcake.mitelros.entity.EntityWandFireball;
import net.oilcake.mitelros.entity.EntityWandIceBall;
import net.oilcake.mitelros.entity.EntityWandShockWave;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(EntityTrackerEntry.class)
public class EntityTrackerEntryMixin {

    @Shadow
    public Entity trackedEntity;
    @Shadow
    public int lastHeadMotion;

    @Overwrite
    private Packet getSpawnPacket() {
        if (this.trackedEntity.isDead) {
            this.trackedEntity.worldObj.getWorldLogAgent().logWarning("Fetching addPacket for removed entity");
        }

        if (this.trackedEntity instanceof EntityItem) {
            return new Packet23VehicleSpawn(this.trackedEntity, 2, 1);
        } else if (this.trackedEntity instanceof ServerPlayer) {
            return new Packet20NamedEntitySpawn((EntityPlayer)this.trackedEntity);
        } else if (this.trackedEntity instanceof EntityMinecartAbstract) {
            EntityMinecartAbstract var9 = (EntityMinecartAbstract)this.trackedEntity;
            return new Packet23VehicleSpawn(this.trackedEntity, 10, var9.getMinecartType());
        } else if (this.trackedEntity instanceof EntityBoat) {
            return new Packet23VehicleSpawn(this.trackedEntity, 1);
        } else if (!(this.trackedEntity instanceof IAnimal) && !(this.trackedEntity instanceof EntityEnderDragon)) {
            if (this.trackedEntity instanceof EntityFishingHook) {
                EntityPlayer var8 = ((EntityFishingHook)this.trackedEntity).angler;
                return new Packet23VehicleSpawn(this.trackedEntity, 90, var8 != null ? var8.entityId : this.trackedEntity.entityId);
            } else if (this.trackedEntity instanceof EntityArrow) {
                Entity var7 = ((EntityArrow)this.trackedEntity).shootingEntity;
                Packet23VehicleSpawn packet = new Packet23VehicleSpawn(this.trackedEntity, 60, var7 != null ? var7.entityId : this.trackedEntity.entityId);
                packet.arrow_item_id = ((EntityArrow)this.trackedEntity).item_arrow.itemID;
                packet.launcher_was_enchanted = ((EntityArrow)this.trackedEntity).launcher_was_enchanted;
                packet.arrow_stuck_in_block = ((EntityArrow)this.trackedEntity).isInGround();
                return packet;
            } else if (this.trackedEntity instanceof EntitySnowball) {
                return new Packet23VehicleSpawn(this.trackedEntity, 61);
            } else if (this.trackedEntity instanceof EntityPotion) {
                return new Packet23VehicleSpawn(this.trackedEntity, 73, ((EntityPotion)this.trackedEntity).getPotionType());
            } else if (this.trackedEntity instanceof EntityThrownExpBottle) {
                return new Packet23VehicleSpawn(this.trackedEntity, 75);
            } else if (this.trackedEntity instanceof EntityEnderPearl) {
                return new Packet23VehicleSpawn(this.trackedEntity, 65);
            } else if (this.trackedEntity instanceof EntityEnderSignal) {
                return new Packet23VehicleSpawn(this.trackedEntity, 72);
            } else if (this.trackedEntity instanceof EntityFireworks) {
                return new Packet23VehicleSpawn(this.trackedEntity, 76);
            } else {
                Packet23VehicleSpawn var2;
                if (this.trackedEntity instanceof EntityFireball) {
                    EntityFireball var6 = (EntityFireball)this.trackedEntity;
                    var2 = null;
                    byte var3 = 63;
                    if (this.trackedEntity instanceof EntitySmallFireball) {
                        var3 = 64;
                    } else if (this.trackedEntity instanceof EntityWitherSkull) {
                        var3 = 66;
                    }

                    if (var6.shootingEntity != null) {
                        var2 = new Packet23VehicleSpawn(this.trackedEntity, var3, ((EntityFireball)this.trackedEntity).shootingEntity.entityId);
                    } else {
                        var2 = new Packet23VehicleSpawn(this.trackedEntity, var3, 0);
                    }

                    var2.approx_motion_x = (float)var6.accelerationX;
                    var2.approx_motion_y = (float)var6.accelerationY;
                    var2.approx_motion_z = (float)var6.accelerationZ;
                    return var2;
                } else if (this.trackedEntity instanceof EntityEgg) {
                    return new Packet23VehicleSpawn(this.trackedEntity, 62);
                } else if (this.trackedEntity instanceof EntityBrick) {
                    return new Packet23VehicleSpawn(this.trackedEntity, ((EntityBrick)this.trackedEntity).getModelItem() == Item.netherrackBrick ? 501 : 500);
                }


                else if (this.trackedEntity instanceof EntityWandIceBall) {
                    return new Packet23VehicleSpawn(this.trackedEntity, 100);
                } else if (this.trackedEntity instanceof EntityWandFireball) {
                    return new Packet23VehicleSpawn(this.trackedEntity, 101);
                } else if (this.trackedEntity instanceof EntityWandShockWave) {
                    return new Packet23VehicleSpawn(this.trackedEntity, 102);
                }


                else if (this.trackedEntity instanceof EntityGelatinousSphere) {
                    EntityGelatinousSphere sphere = (EntityGelatinousSphere)this.trackedEntity;
                    return new Packet23VehicleSpawn(this.trackedEntity, 600 + sphere.getModelSubtype());
                } else if (this.trackedEntity instanceof EntityWeb) {
                    return new Packet23VehicleSpawn(this.trackedEntity, 700);
                } else if (this.trackedEntity instanceof EntityTNTPrimed) {
                    return new Packet23VehicleSpawn(this.trackedEntity, 50);
                } else if (this.trackedEntity instanceof EntityEnderCrystal) {
                    return new Packet23VehicleSpawn(this.trackedEntity, 51);
                } else if (this.trackedEntity instanceof EntityFallingBlock) {
                    EntityFallingBlock var5 = (EntityFallingBlock)this.trackedEntity;
                    return new Packet23VehicleSpawn(this.trackedEntity, 70, var5.blockID | var5.metadata << 16);
                } else if (this.trackedEntity instanceof EntityPainting) {
                    return new Packet25EntityPainting((EntityPainting)this.trackedEntity);
                } else if (this.trackedEntity instanceof EntityItemFrame) {
                    EntityItemFrame var4 = (EntityItemFrame)this.trackedEntity;
                    var2 = new Packet23VehicleSpawn(this.trackedEntity, 71, var4.hangingDirection);
                    var2.setUnscaledPositionWithIntegers(var4.xPosition, var4.yPosition, var4.zPosition);
                    return var2;
                } else if (this.trackedEntity instanceof EntityLeash) {
                    EntityLeash var1 = (EntityLeash)this.trackedEntity;
                    var2 = new Packet23VehicleSpawn(this.trackedEntity, 77);
                    var2.setUnscaledPositionWithIntegers(var1.xPosition, var1.yPosition, var1.zPosition);
                    return var2;
                } else if (this.trackedEntity instanceof EntityExperienceOrb) {
                    return new Packet26AddExpOrb((EntityExperienceOrb)this.trackedEntity);
                } else {
                    throw new IllegalArgumentException("Don't know how to add " + this.trackedEntity.getClass() + "!");
                }
            }
        } else if (this.trackedEntity instanceof EntityInsentient) {
            this.lastHeadMotion = MathHelper.floor_float(this.trackedEntity.getRotationYawHead() * 256.0F / 360.0F);
            return new Packet24MobSpawn((EntityInsentient)this.trackedEntity);
        } else {
            Minecraft.setErrorMessage("getPacketForThisEntity: entity not handled: " + this.trackedEntity);
            throw new IllegalArgumentException("Don't know how to add " + this.trackedEntity.getClass() + "!");
        }
    }
}
