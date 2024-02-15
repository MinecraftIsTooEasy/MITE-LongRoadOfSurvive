package net.oilcake.mitelros.entity;

import net.minecraft.EntityZombie;
import net.minecraft.GenericAttributes;
import net.minecraft.ItemStack;
import net.minecraft.World;
import net.oilcake.mitelros.item.Items;

public class EntityUnknown extends EntityZombie {
    public EntityUnknown(World par1World) {
        super(par1World);
    }
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(GenericAttributes.followRange).setAttribute(999);
        this.getEntityAttribute(GenericAttributes.movementSpeed).setAttribute(0.23000000417232513);
        this.getEntityAttribute(GenericAttributes.attackDamage).setAttribute(1.0);
    }
    public void onUpdate()
    {
        super.onUpdate();
        if (!getWorld().isRemote)
        {
            if(this.getTarget()!=null){
                if(!this.isNoDespawnRequired() && this.getTarget() != null){
                    this.setDead();
                }
            }
        }
    }
    public void addRandomEquipment() {
        this.setBoots((new ItemStack(Items.bootsCustom_a)).randomizeForMob(this, true));
        this.setLeggings((new ItemStack(Items.leggingsCustom_a)).randomizeForMob(this, true));
        this.setCuirass((new ItemStack(Items.chestplateCustom_a)).randomizeForMob(this, true));
        this.setHelmet((new ItemStack(Items.helmetCustom_a)).randomizeForMob(this, true));
    }
}
