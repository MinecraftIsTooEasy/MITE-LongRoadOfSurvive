package net.oilcake.mitelros.entity;

import net.minecraft.*;
import net.oilcake.mitelros.item.Items;

public class EntityBoneBodyguard extends EntitySkeleton {
    public EntityBoneBodyguard(World par1World) {
        super(par1World);
    }

    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.setEntityAttribute(GenericAttributes.followRange, 64.0);
        this.setEntityAttribute(GenericAttributes.maxHealth, 6.0);
        this.setEntityAttribute(GenericAttributes.movementSpeed, 0.28999999165534973);
        this.setEntityAttribute(GenericAttributes.attackDamage, 5.0);
    }
    public void addRandomWeapon() {
        this.setHeldItemStack((new ItemStack((Item)(this.getSkeletonType() == 2 ? (this.rand.nextInt(20) == 0 ? Item.battleAxeRustedIron :Item.swordRustedIron) : Item.bow))).randomizeForMob(this, true));
    }
    public void attackEntityWithRangedAttack(EntityLiving par1EntityLivingBase, float par2) {
        EntityArrow var3 = new EntityArrow(this.worldObj, this, par1EntityLivingBase, 1.6F, (float)(14 - this.worldObj.difficultySetting * 4), Item.arrowRustedIron, false);
        int var4 = EnchantmentManager.getEnchantmentLevel(Enchantment.power.effectId, this.getHeldItemStack());
        int var5 = EnchantmentManager.getEnchantmentLevel(Enchantment.punch.effectId, this.getHeldItemStack());
        double damage = (double)(par2 * 3.0F) + this.rand.nextGaussian() * 0.25 + (double)((float)this.worldObj.difficultySetting * 0.11F);
        var3.setDamage(damage);
        if (var4 > 0) {
            var3.setDamage(var3.getDamage() + (double)var4 * 0.5 + 0.5);
        }

        if (var5 > 0) {
            var3.setKnockbackStrength(var5);
        }

        if (EnchantmentManager.getEnchantmentLevel(Enchantment.flame.effectId, this.getHeldItemStack()) > 0 || this.getSkeletonType() == 1 || this.isBurning() && this.rand.nextInt(3) == 0) {
            var3.setFire(100);
        }

        this.playSound("random.bow", 1.0F, 1.0F / (this.getRNG().nextFloat() * 0.4F + 0.8F));
        this.worldObj.spawnEntityInWorld(var3);
    }
    protected void addRandomEquipment() {
        this.addRandomWeapon();
        this.setBoots((new ItemStack(Item.bootsChainRustedIron)).randomizeForMob(this, true));
        this.setLeggings((new ItemStack(Item.legsChainRustedIron)).randomizeForMob(this, true));
        this.setCuirass((new ItemStack(Item.plateChainRustedIron)).randomizeForMob(this, true));
        this.setHelmet((new ItemStack(Item.helmetChainRustedIron)).randomizeForMob(this, true));
    }

    public int getExperienceValue() {
        return super.getExperienceValue() * 2;
    }
}
