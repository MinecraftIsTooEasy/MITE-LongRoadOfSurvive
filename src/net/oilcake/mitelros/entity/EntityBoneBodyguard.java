package net.oilcake.mitelros.entity;

import net.minecraft.*;

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
