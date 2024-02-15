package net.oilcake.mitelros.mixins.entity;

import net.minecraft.*;
import net.oilcake.mitelros.item.enchantment.Enchantments;
import net.oilcake.mitelros.util.Constant;
import net.oilcake.mitelros.util.ExperimentalConfig;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(EntityMonster.class)
public class EntityMonsterMixin extends EntityCreature {
//    @Overwrite
//    protected void attackEntity(Entity par1Entity, float par2) {
//        if (this.attackTime <= 0 && par2 < 1.75F && par1Entity.boundingBox.maxY > this.boundingBox.minY && par1Entity.boundingBox.minY < this.boundingBox.maxY) {
//            if (this.getHeldItemStack() != null) {
//                this.swingArm();
//            }
//            if(EnchantmentManager.hasEnchantment(par1Entity.getAsPlayer().getHeldItemStack(), Enchantments.enchantmentDestorying)){
//                par1Entity.worldObj.createExplosion(par1Entity, par1Entity.posX, par1Entity.posY, par1Entity.posZ, 10, 10, true);
//            }
//
//
//            this.attackTime = 20;
//            this.attackEntityAsMob(par1Entity);
//        }
//
//    }


    @Overwrite
    public static EntityDamageResult attackEntityAsMob(EntityInsentient attacker, Entity target) {
        if (attacker.isDecoy()) {
            return null;
        } else if (target instanceof EntityPlayer && target.getAsPlayer().isImmuneByGrace()) {
            return null;
        } else {
            ItemStack held_item = attacker.getHeldItemStack();
            Damage damage = new Damage(DamageSource.causeMobDamage(attacker), (float)attacker.getEntityAttribute(GenericAttributes.attackDamage).getAttributeValue());
            if (attacker.isFrenzied()) {
                damage.addAmount((float)attacker.getEntityAttributeBaseValue(GenericAttributes.attackDamage) * 0.5F);
            }
            if(EnchantmentManager.hasEnchantment(held_item, Enchantments.enchantmentDestroying)){
                int destorying = EnchantmentManager.getEnchantmentLevel(Enchantments.enchantmentDestroying, held_item);
                target.worldObj.createExplosionC(target, target.posX, target.posY, target.posZ, destorying, destorying);
                //System.out.println("判断为enchantmentDestorying");
                //target.setFire(120);
            }

            int knockback_bonus = 0;
            if (target.isEntityLivingBase()) {
                damage.addAmount(EnchantmentWeaponDamage.getDamageModifiers(held_item, target.getAsEntityLivingBase()));
                knockback_bonus += EnchantmentManager.getKnockbackModifier(attacker, target.getAsEntityLivingBase());
            }


            int fire_aspect = EnchantmentManager.getFireAspectModifier(attacker);
            EntityDamageResult result = target.attackEntityFrom(damage.setFireAspect(fire_aspect > 0));
            if (result == null) {
                return result;
            } else {
                if (result.entityWasNegativelyAffected()) {
                    if (knockback_bonus > 0) {
                        target.addVelocity((double)(-MathHelper.sin(attacker.rotationYaw * 3.1415927F / 180.0F) * (float)knockback_bonus * 0.5F), 0.1, (double)(MathHelper.cos(attacker.rotationYaw * 3.1415927F / 180.0F) * (float)knockback_bonus * 0.5F));
                        attacker.motionX *= 0.6;
                        attacker.motionZ *= 0.6;
                    }

                    if (fire_aspect > 0) {
                        target.setFire(fire_aspect * 4);
                    }

                    if (attacker.isBurning() && !attacker.hasHeldItem() && attacker.getRand().nextFloat() < (float)attacker.worldObj.difficultySetting * 0.3F) {
                        target.setFire(2 * attacker.worldObj.difficultySetting);
                    }

                    if (target.isEntityLivingBase()) {
                        if (attacker.worldObj.isRemote) {
                            System.out.println("EntityMob.attackEntityAsMob() is calling EnchantmentThorns.func_92096_a() on client");
                            Minecraft.temp_debug = "mob";
                        }

                        EnchantmentThorns.func_92096_a(attacker, target.getAsEntityLivingBase(), attacker.getRand());
//                        EnchantmentProtectFire.PerformProtect(attacker, target.getAsEntityLivingBase(), attacker.getRand());

                        int stunning = EnchantmentManager.getStunModifier(attacker, target.getAsEntityLivingBase());
                        if ((double)stunning > Math.random() * 10.0) {
                            target.getAsEntityLivingBase().addPotionEffect(new MobEffect(MobEffectList.moveSlowdown.id, stunning * 50, stunning * 5));
                        }

                        attacker.heal((float)EnchantmentManager.getVampiricTransfer(attacker, target.getAsEntityLivingBase(), result.getAmountOfHealthLost()), EnumEntityFX.vampiric_gain);
                    }

                    if (target instanceof EntityPlayer) {
                        attacker.refreshDespawnCounter(-9600);
                    }
                }

                return result;
            }
        }
    }
    public EntityMonsterMixin(World par1World) {
        super(par1World);
    }
    private boolean modified_attribute = false;
    @Overwrite
    public void onUpdate() {
        super.onUpdate();
        if (!this.worldObj.isRemote && !this.modified_attribute && this.getHealth() > 0 && ExperimentalConfig.TagConfig.FinalChallenge.ConfigValue){
            this.setEntityAttribute(GenericAttributes.maxHealth, this.getMaxHealth() * ( 1.0F + (float) Constant.CalculateCurrentDiff() / 16.0F ));
            double attack_damage = this.getEntityAttributeValue(GenericAttributes.attackDamage);
            if(this.getHeldItemStack() != null && this.getHeldItemStack().getItem() instanceof ItemTool){
                attack_damage -= this.getHeldItemStack().getItemAsTool().getMaterialDamageVsEntity();
                attack_damage -= this.getHeldItemStack().getItemAsTool().getBaseDamageVsEntity();
            }
            this.setEntityAttribute(GenericAttributes.attackDamage, attack_damage * (1.0F + (float) Constant.CalculateCurrentDiff() / 32.0F ));
            this.setHealth(this.getMaxHealth());
            this.modified_attribute = true;
        }
        if (!this.worldObj.isRemote && this.worldObj.difficultySetting == 0) {
            this.setDead();
        }

    }
    public void writeEntityToNBT(NBTTagCompound par1NBTTagCompound) {
        super.writeEntityToNBT(par1NBTTagCompound);
        par1NBTTagCompound.setBoolean("modified_attribute", (boolean) this.modified_attribute);
    }

    public void readEntityFromNBT(NBTTagCompound par1NBTTagCompound) {
        super.readEntityFromNBT(par1NBTTagCompound);
        this.modified_attribute = par1NBTTagCompound.getBoolean("modified_attribute");
    }

}
