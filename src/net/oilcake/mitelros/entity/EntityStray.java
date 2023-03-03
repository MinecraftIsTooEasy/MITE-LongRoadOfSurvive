package net.oilcake.mitelros.entity;

import net.minecraft.*;
import net.oilcake.mitelros.item.Items;

public class EntityStray extends EntitySkeleton {

    public EntityStray(World par1World) {
        super(par1World);
        this.tasks.addTask(3, new PathfinderGoalLeapAtTarget(this, 0.35F));
    }
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.setEntityAttribute(GenericAttributes.followRange, 64.0);
        this.setEntityAttribute(GenericAttributes.maxHealth, 6.0);
        this.setEntityAttribute(GenericAttributes.movementSpeed, 0.28999999165534973);
        this.setEntityAttribute(GenericAttributes.attackDamage, 5.0);
    }
    public void addRandomWeapon() {
        this.setHeldItemStack((new ItemStack((Item)(this.getSkeletonType() == 2 ? (this.rand.nextInt(20) == 0 ? Item.battleAxeRustedIron :Item.daggerRustedIron) : Item.bow))).randomizeForMob(this, true));
    }

    protected void addRandomEquipment() {
        this.addRandomWeapon();
        this.setBoots((new ItemStack(Items.WolfBoots)).randomizeForMob(this, false));
        this.setLeggings((new ItemStack(Items.WolfLeggings)).randomizeForMob(this, false));
        this.setCuirass((new ItemStack(Items.WolfChestplate)).randomizeForMob(this, false));
        this.setHelmet((new ItemStack(Items.WolfHelmet)).randomizeForMob(this, false));
    }
}
