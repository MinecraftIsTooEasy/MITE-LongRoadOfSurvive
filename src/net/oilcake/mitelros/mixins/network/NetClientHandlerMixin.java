package net.oilcake.mitelros.mixins.network;

import net.minecraft.*;
import net.oilcake.mitelros.block.enchantreserver.GuiEnchantReserver;
import net.oilcake.mitelros.entity.EntityWandFireball;
import net.oilcake.mitelros.entity.EntityWandIceBall;
import net.oilcake.mitelros.entity.EntityWandShockWave;
import net.oilcake.mitelros.util.network.PacketDecreaseWater;
import net.oilcake.mitelros.util.network.PacketEnchantReserverInfo;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.SoftOverride;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;


@Mixin(NetClientHandler.class)
public class NetClientHandlerMixin extends ConnectionMixin{
//    private static Class[] classes = new Class[]{ContainerPlayer.class, ContainerWorkbench.class, MITEConstant.class, MITEContainerCrafting.class, ClientPlayer.class, EntityPlayer.class, bex.class,
//            FoodMetaData.class, Minecraft.class, bew.class, NetClientHandler.class, ClientPlayerController.class, Packet13PlayerLookMove.class, Packet27PlayerInput.class, Packet82AddHunger.class,
//            Packet85SimpleSignal.class, Packet202Abilities.class, PlayerAbilities.class, NetworkManager.class, PacketDecreaseWater.class, PacketEnchantReserverInfo.class};
//
//    @Inject(method = "<clinit>", at = @At("RETURN"))
//    private static void injectClinit(CallbackInfo callbackInfo) {
//        for (Class aClass : classes) {
//            class_hash_sum += getHash(aClass);
//        }
//    }
    @Overwrite
    public void handleVehicleSpawn(Packet23VehicleSpawn par1Packet23VehicleSpawn) {
        double var2;
        double var4;
        double var6;
        if (par1Packet23VehicleSpawn.position_set_using_unscaled_integers) {
            var2 = (double)par1Packet23VehicleSpawn.unscaled_pos_x;
            var4 = (double)par1Packet23VehicleSpawn.unscaled_pos_y;
            var6 = (double)par1Packet23VehicleSpawn.unscaled_pos_z;
        } else {
            var2 = SpatialScaler.getPosX(par1Packet23VehicleSpawn.scaled_pos_x);
            var4 = SpatialScaler.getPosY(par1Packet23VehicleSpawn.scaled_pos_y);
            var6 = SpatialScaler.getPosZ(par1Packet23VehicleSpawn.scaled_pos_z);
        }

        Entity var8 = null;
        if (par1Packet23VehicleSpawn.type == 10) {
            var8 = EntityMinecartAbstract.createMinecart(this.i, var2, var4, var6, par1Packet23VehicleSpawn.throwerEntityId);
        } else if (par1Packet23VehicleSpawn.type == 90) {
            Entity var9 = this.a(par1Packet23VehicleSpawn.throwerEntityId);
            if (var9 instanceof EntityPlayer) {
                var8 = new EntityFishingHook(this.i, var2, var4, var6, (EntityPlayer)var9);
            }

            par1Packet23VehicleSpawn.throwerEntityId = 0;
        } else if (par1Packet23VehicleSpawn.type == 60) {
            if (par1Packet23VehicleSpawn.arrow_item_id == -1) {
                var8 = null;
            } else {
                var2 = par1Packet23VehicleSpawn.exact_pos_x;
                var4 = par1Packet23VehicleSpawn.exact_pos_y;
                var6 = par1Packet23VehicleSpawn.exact_pos_z;
                var8 = new EntityArrow(this.i, var2, var4, var6, (ItemArrow)Item.itemsList[par1Packet23VehicleSpawn.arrow_item_id], par1Packet23VehicleSpawn.launcher_was_enchanted);
                if (par1Packet23VehicleSpawn.arrow_stuck_in_block) {
                    ((EntityArrow)var8).setInGround();
                }

                EntityArrow arrow = (EntityArrow)var8;
                arrow.xTile = par1Packet23VehicleSpawn.arrow_tile_x;
                arrow.yTile = par1Packet23VehicleSpawn.arrow_tile_y;
                arrow.zTile = par1Packet23VehicleSpawn.arrow_tile_z;
                arrow.setInTile(par1Packet23VehicleSpawn.arrow_in_tile);
                arrow.setInData(par1Packet23VehicleSpawn.arrow_in_data);
            }
        } else if (par1Packet23VehicleSpawn.type == 61) {
            var8 = new EntitySnowball(this.i, var2, var4, var6);
        } else if (par1Packet23VehicleSpawn.type == 71) {
            var8 = new EntityItemFrame(this.i, par1Packet23VehicleSpawn.unscaled_pos_x, par1Packet23VehicleSpawn.unscaled_pos_y, par1Packet23VehicleSpawn.unscaled_pos_z, par1Packet23VehicleSpawn.throwerEntityId);
            par1Packet23VehicleSpawn.throwerEntityId = 0;
        } else if (par1Packet23VehicleSpawn.type == 77) {
            var8 = new EntityLeash(this.i, par1Packet23VehicleSpawn.unscaled_pos_x, par1Packet23VehicleSpawn.unscaled_pos_y, par1Packet23VehicleSpawn.unscaled_pos_z);
            par1Packet23VehicleSpawn.throwerEntityId = 0;
        } else if (par1Packet23VehicleSpawn.type == 65) {
            var8 = new EntityEnderPearl(this.i, var2, var4, var6);
        } else if (par1Packet23VehicleSpawn.type == 72) {
            var8 = new EntityEnderSignal(this.i, var2, var4, var6);
        } else if (par1Packet23VehicleSpawn.type == 76) {
            var8 = new EntityFireworks(this.i, var2, var4, var6, (ItemStack)null);
        } else if (par1Packet23VehicleSpawn.type == 63) {
            var8 = new EntityLargeFirebal(this.i, var2, var4, var6, (double)par1Packet23VehicleSpawn.approx_motion_x, (double)par1Packet23VehicleSpawn.approx_motion_y, (double)par1Packet23VehicleSpawn.approx_motion_z);
            par1Packet23VehicleSpawn.throwerEntityId = 0;
        } else if (par1Packet23VehicleSpawn.type == 64) {
            var8 = new EntitySmallFireball(this.i, var2, var4, var6, (double)par1Packet23VehicleSpawn.approx_motion_x, (double)par1Packet23VehicleSpawn.approx_motion_y, (double)par1Packet23VehicleSpawn.approx_motion_z);
            par1Packet23VehicleSpawn.throwerEntityId = 0;
        } else if (par1Packet23VehicleSpawn.type == 66) {
            var8 = new EntityWitherSkull(this.i, var2, var4, var6, (double)par1Packet23VehicleSpawn.approx_motion_x, (double)par1Packet23VehicleSpawn.approx_motion_y, (double)par1Packet23VehicleSpawn.approx_motion_z);
            par1Packet23VehicleSpawn.throwerEntityId = 0;
        } else if (par1Packet23VehicleSpawn.type == 62) {
            var8 = new EntityEgg(this.i, var2, var4, var6);
        } else if (par1Packet23VehicleSpawn.type == 500) {
            var8 = new EntityBrick(this.i, var2, var4, var6, Item.brick);
        } else if (par1Packet23VehicleSpawn.type == 501) {
            var8 = new EntityBrick(this.i, var2, var4, var6, Item.netherrackBrick);
        } else if (MathHelper.isInRange(par1Packet23VehicleSpawn.type, 600, 699)) {
            var8 = new EntityGelatinousSphere(this.i, var2, var4, var6, Item.slimeBall, par1Packet23VehicleSpawn.type - 600);
        } else if (par1Packet23VehicleSpawn.type == 700) {
            var8 = new EntityWeb(this.i, var2, var4, var6);
        } else if (par1Packet23VehicleSpawn.type == 73) {
            var8 = new EntityPotion(this.i, var2, var4, var6, par1Packet23VehicleSpawn.throwerEntityId);
            par1Packet23VehicleSpawn.throwerEntityId = 0;
        } else if (par1Packet23VehicleSpawn.type == 75) {
            var8 = new EntityThrownExpBottle(this.i, var2, var4, var6);
            par1Packet23VehicleSpawn.throwerEntityId = 0;
        } else if (par1Packet23VehicleSpawn.type == 1) {
            var8 = new EntityBoat(this.i, var2, var4, var6);
        } else if (par1Packet23VehicleSpawn.type == 50) {
            var8 = new EntityTNTPrimed(this.i, var2, var4, var6, (EntityLiving)null);
        } else if (par1Packet23VehicleSpawn.type == 51) {
            var8 = new EntityEnderCrystal(this.i, var2, var4, var6);
        } else if (par1Packet23VehicleSpawn.type == 2) {
            var8 = new EntityItem(this.i, var2, var4, var6);
        } else if (par1Packet23VehicleSpawn.type == 70) {
            var8 = new EntityFallingBlock(this.i, (double)MathHelper.floor_double(var2) + 0.5, (double)MathHelper.floor_double(var4) + 0.5, (double)MathHelper.floor_double(var6) + 0.5, par1Packet23VehicleSpawn.throwerEntityId & '\uffff', par1Packet23VehicleSpawn.throwerEntityId >> 16);
            par1Packet23VehicleSpawn.throwerEntityId = 0;
        } else if (par1Packet23VehicleSpawn.type == 100) {
            var8 = new EntityWandIceBall(this.i, var2, var4, var6);
        } else if (par1Packet23VehicleSpawn.type == 101) {
            var8 = new EntityWandFireball(this.i, var2, var4, var6);
        } else if (par1Packet23VehicleSpawn.type == 102) {
            var8 = new EntityWandShockWave(this.i, var2, var4, var6);
        }

        if (var8 != null) {
            ((Entity)var8).rotationYaw = SpatialScaler.getRotation(par1Packet23VehicleSpawn.scaled_yaw);
            ((Entity)var8).rotationPitch = SpatialScaler.getRotation(par1Packet23VehicleSpawn.scaled_pitch);
            if (var8 instanceof EntityBoat) {
                ((EntityBoat)var8).a(((Entity)var8).posX, ((Entity)var8).posY, ((Entity)var8).posZ, ((Entity)var8).rotationYaw, ((Entity)var8).rotationPitch, 3);
                ((Entity)var8).prevRotationYaw = ((Entity)var8).rotationYaw;
            }

            Entity[] var12 = ((Entity)var8).getParts();
            if (var12 != null) {
                int var10 = par1Packet23VehicleSpawn.entityId - ((Entity)var8).entityId;

                for(int var11 = 0; var11 < var12.length; ++var11) {
                    var12[var11].entityId += var10;
                }
            }

            ((Entity)var8).entityId = par1Packet23VehicleSpawn.entityId;
            this.i.a(par1Packet23VehicleSpawn.entityId, (Entity)var8);
            if (par1Packet23VehicleSpawn.throwerEntityId > 0) {
                if (par1Packet23VehicleSpawn.type == 60) {
                    Entity var13 = this.a(par1Packet23VehicleSpawn.throwerEntityId);
                    if (var13 instanceof EntityLiving) {
                        EntityArrow var14 = (EntityArrow)var8;
                        var14.shootingEntity = var13;
                    }

                    ((Entity)var8).h(par1Packet23VehicleSpawn.exact_motion_x, par1Packet23VehicleSpawn.exact_motion_y, par1Packet23VehicleSpawn.exact_motion_z);
                    return;
                }

                ((Entity)var8).h((double)par1Packet23VehicleSpawn.approx_motion_x, (double)par1Packet23VehicleSpawn.approx_motion_y, (double)par1Packet23VehicleSpawn.approx_motion_z);
            }
        }

    }


    @Overwrite
    public void handleUpdateHealth(Packet8UpdateHealth par1Packet8UpdateHealth) {
        this.h.h.n(par1Packet8UpdateHealth.healthMP);
        this.h.h.getFoodStats().setSatiation(par1Packet8UpdateHealth.satiation, false);
        this.h.h.getFoodStats().setNutrition(par1Packet8UpdateHealth.nutrition, false);
        this.h.h.getFoodStats().setSatiationWater(par1Packet8UpdateHealth.water, false);
        this.h.h.setPhytonutrients(par1Packet8UpdateHealth.phytonutrients);
        this.h.h.setProtein(par1Packet8UpdateHealth.protein);
        if (this.h.h.vision_dimming < par1Packet8UpdateHealth.vision_dimming) {
            this.h.h.vision_dimming = par1Packet8UpdateHealth.vision_dimming;
        }

    }
//    public void handleReadFreezingCoolDown(PacketReadFreezeCooldown par1PacketReadFreezeCooldown){
//        this.h.h.setFreezingCooldown(par1PacketReadFreezeCooldown.GetFreezingCooldown());
//    }

    @SoftOverride
    public void processEnchantReserverInfo(PacketEnchantReserverInfo packet) {
        awe openingGUI = this.h.n;
        if (openingGUI instanceof GuiEnchantReserver) {
            ((GuiEnchantReserver)openingGUI).setEnchantInfo(packet.getEXP());
        }
    }
    @Shadow
    private static int class_hash_sum = 0;
    @Shadow
    private static int getHash(Class _class) {
        return 1;
    }

    @Shadow
    private Entity a(int par1) {
        return null;
    }
    @Shadow
    private bdd i;
    @Shadow
    private Minecraft h;
}
