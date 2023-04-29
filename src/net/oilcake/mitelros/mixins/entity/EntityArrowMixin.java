package net.oilcake.mitelros.mixins.entity;

import net.minecraft.*;
import net.oilcake.mitelros.entity.EntityStray;
import net.oilcake.mitelros.entity.EntityWitherBodyguard;
import net.oilcake.mitelros.item.Items;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

import java.util.Random;
@Mixin(EntityArrow.class)
public class EntityArrowMixin extends EntityArrow implements un {
    @Shadow
    public int xTile = -1;
    @Shadow
    public int yTile = -1;
    @Shadow
    public int zTile = -1;
    @Shadow
    private int inTile;
    @Shadow
    private int inData;
    @Shadow
    public int canBePickedUp;
    @Shadow
    public int arrowShake;
    @Shadow
    public Entity shootingEntity;
    @Shadow
    private int ticksInGround;
    @Shadow
    private int ticksInAir;
    @Shadow
    private double damage = 2.0;
    @Shadow
    private boolean inGround;
    @Shadow
    private int knockbackStrength;
    @Shadow
    public ItemArrow item_arrow;
    @Shadow
    public boolean launcher_was_enchanted;
    @Shadow
    protected float speed_before_collision_sq;
    @Shadow
    private boolean was_burning;
    @Shadow
    private int ticks_until_next_fizz_sound;
    @Shadow
    public boolean shot_by_dispenser;
    @Shadow
    private Entity last_entity_harmed;

    public EntityArrowMixin(World par1World) {
        super(par1World);
    }

    @Shadow
    protected void entityInit() {
    }
    @Shadow
    public ItemStack getLauncher() {
        return null;
    }

    @Shadow
    public void readEntityFromNBT(NBTTagCompound nbtTagCompound) {
    }

    @Overwrite
    public void setThrowableHeading(double par1, double par3, double par5, float velocity, float par8) {
        ItemStack launcher = this.getLauncher();


        if (launcher != null && launcher.getItem() == Items.bowTungsten && this.shootingEntity instanceof EntityPlayer) {
            velocity *= 1.35F;
        }

        if (launcher != null && launcher.getItem() == Item.bowMithril && this.shootingEntity instanceof EntityPlayer) {
            velocity *= 1.25F;
        }

        if (launcher != null && launcher.getItem() == Item.bowAncientMetal && this.shootingEntity instanceof EntityPlayer) {
            velocity *= 1.1F;
        }

        float var9 = MathHelper.sqrt_double(par1 * par1 + par3 * par3 + par5 * par5);
        par1 /= var9;
        par3 /= var9;
        par5 /= var9;
        par1 += super.rand.nextGaussian() * (double)(super.rand.nextBoolean() ? -1 : 1) * 1.856746317E-314D * (double)par8;
        par3 += super.rand.nextGaussian() * (double)(super.rand.nextBoolean() ? -1 : 1) * 1.856746317E-314D * (double)par8;
        par5 += super.rand.nextGaussian() * (double)(super.rand.nextBoolean() ? -1 : 1) * 1.856746317E-314D * (double)par8;
        par1 *= velocity;
        par3 *= velocity;
        par5 *= velocity;
        super.motionX = par1;
        super.motionY = par3;
        super.motionZ = par5;
        float var10 = MathHelper.sqrt_double(par1 * par1 + par5 * par5);
        super.prevRotationYaw = super.rotationYaw = (float)(Math.atan2(par1, par5) * 0.0D / 6.984873503E-315D);
        super.prevRotationPitch = super.rotationPitch = (float)(Math.atan2(par3, var10) * 0.0D / 6.984873503E-315D);
        this.ticksInGround = 0;
    }

    @Shadow
    public void writeEntityToNBT(NBTTagCompound nbtTagCompound) {
    }
    public void onUpdate() {
        super.onUpdate();
        this.speed_before_collision_sq = (float)(this.motionX * this.motionX + this.motionY * this.motionY + this.motionZ * this.motionZ);
        if (this.prevRotationPitch == 0.0F && this.prevRotationYaw == 0.0F) {
            float var1 = MathHelper.sqrt_double(this.motionX * this.motionX + this.motionZ * this.motionZ);
            this.prevRotationYaw = this.rotationYaw = (float)(Math.atan2(this.motionX, this.motionZ) * 180.0 / Math.PI);
            this.prevRotationPitch = this.rotationPitch = (float)(Math.atan2(this.motionY, (double)var1) * 180.0 / Math.PI);
        }

        int var16 = this.worldObj.getBlockId(this.xTile, this.yTile, this.zTile);
        AxisAlignedBB bb;
        if (var16 > 0) {
            bb = Block.blocksList[var16].getCollisionBoundsCombined(this.worldObj, this.xTile, this.yTile, this.zTile, this, true);
            if (bb != null && bb.isVecInside(this.worldObj.getWorldVec3Pool().getVecFromPool(this.posX, this.posY, this.posZ))) {
                this.inGround = true;
            }
        }

        if (this.arrowShake > 0) {
            --this.arrowShake;
        }

        if (!this.worldObj.isRemote && this.was_burning) {
            Block block = this.inGround ? Block.getBlock(this.inTile) : null;
            if (block != null && block.blockMaterial.isFreezing()) {
                if (!this.isWet()) {
                    this.causeQuenchEffect();
                }

                this.extinguish();
            } else if (this.isInWater()) {
                this.causeQuenchEffect();
            } else if (this.isWet() && --this.ticks_until_next_fizz_sound <= 0) {
                this.spawnSingleSteamParticle(true);
                this.ticks_until_next_fizz_sound = this.rand.nextInt(17) + 3;
            }
        }

        this.was_burning = this.isBurning();
        if (this.inGround) {
            int var18 = this.worldObj.getBlockId(this.xTile, this.yTile, this.zTile);
            int var19 = this.worldObj.getBlockMetadata(this.xTile, this.yTile, this.zTile);
            if (var18 == this.inTile && (var19 == this.inData || this.inTile == Block.grass.blockID)) {
                ++this.ticksInGround;
                if (this.ticksInGround >= (this.shootingEntity instanceof EntityPlayer ? 24000 : 1000)) {
                    this.setDead();
                }
            } else {
                this.inGround = false;
                Random rand = new Random((long)this.entityId);
                this.motionX *= (double)(rand.nextFloat() * 0.2F);
                this.motionY *= (double)(rand.nextFloat() * 0.2F);
                this.motionZ *= (double)(rand.nextFloat() * 0.2F);
                this.ticksInGround = 0;
                this.ticksInAir = 0;
            }
        } else {
            ++this.ticksInAir;
            if (this.onServer()) {
                bb = this.boundingBox.copy();
                if (this.worldObj.isBoundingBoxBurning(bb.contract(0.001, 0.001, 0.001), true)) {
                    this.setFire(8);
                } else if (this.worldObj.isBoundingBoxBurning(bb.contract(0.001, 0.001, 0.001).translate(this.motionX / 2.0, this.motionY / 2.0, this.motionZ / 2.0), true)) {
                    this.setFire(8);
                }
            }

            Vec3D current_pos = this.worldObj.getVec3(this.posX, this.posY, this.posZ);
            Vec3D future_pos = this.worldObj.getVec3(this.posX + this.motionX, this.posY + this.motionY, this.posZ + this.motionZ);
            Raycast raycast = (new Raycast(this.worldObj, current_pos, future_pos)).setForPiercingProjectile(this).performVsBlocks();
            RaycastCollision var4 = raycast.getBlockCollision();
            RaycastCollision block_collision = var4;
            if (var4 != null) {
                raycast.setLimitToBlockCollisionPoint();
            }

            if (raycast.performVsEntities().hasEntityCollisions()) {
                var4 = raycast.getNearestCollision();
            }

            if (var4 != null && var4.getEntityHit() instanceof EntityPlayer) {
                EntityPlayer var21 = (EntityPlayer)var4.getEntityHit();
                if (var21.capabilities.disableDamage || this.shootingEntity instanceof EntityPlayer && !((EntityPlayer)this.shootingEntity).canAttackPlayer(var21)) {
                    var4 = null;
                }
            }

            if (var4 == null || !var4.isEntity()) {
                this.last_entity_harmed = null;
            }

            float var27;
            float var11;
            float var20;
            if (var4 != null) {
                if (!var4.isEntity()) {
                    this.handleCollisionWithBlock(var4);
                } else {
                    Entity entity_hit = var4.getEntityHit();
                    var20 = MathHelper.sqrt_double(this.motionX * this.motionX + this.motionY * this.motionY + this.motionZ * this.motionZ);
                    var11 = 1.0F;
                    if (entity_hit instanceof EntityLiving) {
                        EntityLiving entity_living_base = (EntityLiving)entity_hit;
                        if (entity_living_base.isEntityUndead() && this.item_arrow.getArrowheadMaterial() == Material.silver) {
                            var11 *= 1.25F;
                        }

                        if (entity_hit instanceof EntitySkeleton) {
                            var11 *= 0.25F;
                        }
                    }

                    float var24 = var20 * (float)this.damage * var11;
                    float min_damage;
                    if (this.shootingEntity instanceof EntitySkeleton) {
                        min_damage = this.item_arrow.getDamage() * 2.0F + 2.0F;
                        if (this.shootingEntity.getClass() == EntityLongdead.class) {
                            min_damage += 2.0F;
                        } else if (this.shootingEntity.getClass() == EntityLongdeadGuardian.class) {
                            min_damage += 3.0F;
                        } else if (this.shootingEntity.getClass() == EntityWitherBodyguard.class) {
                            min_damage += 3.0F;
                        } else if (this.shootingEntity.getClass() == EntityStray.class) {
                            min_damage += 1.0F;
                        }

                        if (var24 < min_damage) {
                            var24 = min_damage;
                        } else if (!this.launcher_was_enchanted) {
                            var24 = min_damage;
                        }
                    } else if (this.shot_by_dispenser) {
                        min_damage = this.item_arrow.getDamage() * 2.0F + 2.0F;
                        if (var24 < min_damage) {
                            var24 = min_damage;
                        }
                    }

                    if (this.getIsCritical()) {
                        var24 *= 1.5F + this.rand.nextFloat() * 0.5F;
                    }

                    DamageSource var22 = null;
                    if (this.shootingEntity == null) {
                        var22 = DamageSource.causeArrowDamage(this, this);
                    } else {
                        var22 = DamageSource.causeArrowDamage(this, this.shootingEntity);
                    }

                    if (this.isBurning() && !(entity_hit instanceof EntityEnderman)) {
                        if (entity_hit instanceof EntityGelatinousCube) {
                            if (this.onServer()) {
                                if (this.getVelocity() < 1.0F) {
                                    entity_hit.attackEntityFrom(new Damage(DamageSource.inFire, 1.0F));
                                    this.extinguish(true);
                                } else {
                                    ++var24;
                                    entity_hit.entityFX(EnumEntityFX.steam_with_hiss);
                                }
                            }
                        } else {
                            entity_hit.setFire(5);
                        }
                    }

                    if (entity_hit instanceof EntityGelatinousCube && ((EntityGelatinousCube)entity_hit).isAcidic() && this.item_arrow.isHarmedByAcid()) {
                        if (this.onServer()) {
                            this.entityFX(EnumEntityFX.steam_with_hiss);
                        }

                        this.setDead();
                    }

                    if (entity_hit instanceof EntitySkeleton && this.shootingEntity instanceof EntitySkeleton) {
                        this.setDead();
                    }

                    Damage damage = new Damage(var22, var24);
                    boolean entity_immune_to_arrow = entity_hit.isEntityInvulnerable() || entity_hit.isImmuneTo(damage.getSource());
                    if (entity_hit != this.last_entity_harmed) {
                        if (!(this.getVelocity() < 1.0F) && !entity_immune_to_arrow) {
                            if (this.onServer()) {
                                EntityDamageResult result = entity_hit.attackEntityFrom(damage);
                                if (result == null && entity_hit instanceof EntityPhaseSpider) {
                                    if (block_collision != null) {
                                        this.handleCollisionWithBlock(block_collision);
                                    }
                                } else if (result != null && result.entityWasNegativelyAffected()) {
                                    this.last_entity_harmed = entity_hit;
                                    if (entity_hit instanceof EntityLiving) {
                                        if (this.shootingEntity instanceof EntityLiving) {
                                            ((EntityLiving)this.shootingEntity).setLastAttackTarget(entity_hit);
                                        }

                                        EntityLiving var25 = (EntityLiving)entity_hit;
                                        var25.setArrowCountInEntity(var25.getArrowCountInEntity() + 1);
                                        ItemStack launcher = this.getLauncher();
                                        if (launcher != null && this.rand.nextFloat() < launcher.getEnchantmentLevelFraction(Enchantment.poison)) {
                                            var25.addPotionEffect(new MobEffect(MobEffectList.poison.id, 160 + launcher.getEnchantmentLevelFractionOfInteger(Enchantment.poison, 240), 0));
                                        }

                                        if (this.knockbackStrength > 0) {
                                            var27 = MathHelper.sqrt_double(this.motionX * this.motionX + this.motionZ * this.motionZ);
                                            if (var27 > 0.0F) {
                                                entity_hit.addVelocity(this.motionX * (double)this.knockbackStrength * 0.6000000238418579 / (double)var27, 0.1, this.motionZ * (double)this.knockbackStrength * 0.6000000238418579 / (double)var27);
                                            }
                                        }

                                        if (this.shootingEntity != null) {
                                            if (this.worldObj.isRemote) {
                                                System.out.println("EntityArrow.onUpdate() is calling EnchantmentThorns.func_92096_a() on client");
                                                Minecraft.temp_debug = "arrow";
                                            }

                                            EnchantmentThorns.func_92096_a(this.shootingEntity, var25, this.rand);
//                                            EnchantmentProtectFire.PerformProtect(this.shootingEntity,var25,this.rand);
                                        }

                                        if (this.shootingEntity != null && entity_hit != this.shootingEntity && entity_hit instanceof EntityPlayer && this.shootingEntity instanceof ServerPlayer) {
                                            ((ServerPlayer)this.shootingEntity).playerNetServerHandler.sendPacket(new Packet70Bed(6, 0));
                                        }
                                    }

                                    this.playSound("random.bowhit", 1.0F, 1.2F / (this.rand.nextFloat() * 0.2F + 0.9F));
                                    if (!(entity_hit instanceof EntityEnderman)) {
                                        this.setDead();
                                    }

                                    if (this.shootingEntity != null && entity_hit instanceof EntityPlayer) {
                                        this.shootingEntity.refreshDespawnCounter(-9600);
                                    }
                                } else {
                                    this.bounceBack();
                                }
                            }
                        } else {
                            this.bounceBack();
                        }
                    }
                }
            }

            if (this.getIsCritical()) {
                for(int var9 = 0; var9 < 4; ++var9) {
                    this.worldObj.spawnParticle(EnumParticle.crit, this.posX + this.motionX * (double)var9 / 4.0, this.posY + this.motionY * (double)var9 / 4.0, this.posZ + this.motionZ * (double)var9 / 4.0, -this.motionX, -this.motionY + 0.2, -this.motionZ);
                }
            }

            this.posX += this.motionX;
            this.posY += this.motionY;
            this.posZ += this.motionZ;
            var20 = MathHelper.sqrt_double(this.motionX * this.motionX + this.motionZ * this.motionZ);
            this.rotationYaw = (float)(Math.atan2(this.motionX, this.motionZ) * 180.0 / Math.PI);

            for(this.rotationPitch = (float)(Math.atan2(this.motionY, (double)var20) * 180.0 / Math.PI); this.rotationPitch - this.prevRotationPitch < -180.0F; this.prevRotationPitch -= 360.0F) {
            }

            while(this.rotationPitch - this.prevRotationPitch >= 180.0F) {
                this.prevRotationPitch += 360.0F;
            }

            while(this.rotationYaw - this.prevRotationYaw < -180.0F) {
                this.prevRotationYaw -= 360.0F;
            }

            while(this.rotationYaw - this.prevRotationYaw >= 180.0F) {
                this.prevRotationYaw += 360.0F;
            }

            this.rotationPitch = this.prevRotationPitch + (this.rotationPitch - this.prevRotationPitch) * 0.2F;
            this.rotationYaw = this.prevRotationYaw + (this.rotationYaw - this.prevRotationYaw) * 0.2F;
            float var23 = 0.99F;
            var11 = 0.05F;
            if (this.isInWater()) {
                for(int var26 = 0; var26 < 4; ++var26) {
                    var27 = 0.25F;
                    this.worldObj.spawnParticle(EnumParticle.bubble, this.posX - this.motionX * (double)var27, this.posY - this.motionY * (double)var27, this.posZ - this.motionZ * (double)var27, this.motionX, this.motionY, this.motionZ);
                }

                var23 = 0.8F;
            }

            this.motionX *= (double)var23;
            this.motionY *= (double)var23;
            this.motionZ *= (double)var23;
            this.motionY -= (double)var11;
            this.setPosition(this.posX, this.posY, this.posZ);
            this.doBlockCollisions();
        }

    }
    @Shadow
    public boolean getIsCritical() {
        return false;
    }
    @Shadow
    private void bounceBack() {
    }
    @Shadow
    public float getVelocity() {
        return 0;
    }
    @Shadow
    private void handleCollisionWithBlock(RaycastCollision var4) {
    }
}
