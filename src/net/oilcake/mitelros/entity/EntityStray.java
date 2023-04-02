package net.oilcake.mitelros.entity;

import net.minecraft.*;
import net.oilcake.mitelros.item.Items;

import java.util.Calendar;
import java.util.TimeZone;

public class EntityStray extends EntitySkeleton {
    private Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("GMT+8"));
    private int spawnCounter;

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
    public void onUpdate(){
        super.onUpdate();
        if(!getWorld().isRemote){
            spawnCounter++;
            if (spawnCounter > 300) {
                if(this.getHeldItemStack()!=null){
                    if(this.getTarget()!=null && this.getHeldItemStack().itemID==Items.FreezeWand.itemID){
                        this.getTarget().addPotionEffect(new MobEffect(MobEffectList.moveSlowdown.id, 350, 0));
                    }
                    spawnCounter = 0;
                }

            }
        }
    }
    public void addRandomWeapon() {
        if(this.getSkeletonType() == 2 && this.rand.nextInt(24)==0){
            this.setHeldItemStack(new ItemStack((Item)(Items.FreezeWand)));
            this.Is_Wizard = true;
        }else{
            this.setHeldItemStack((new ItemStack((Item)(this.getSkeletonType() == 2 ? (this.rand.nextInt(20) == 0 ? Item.battleAxeRustedIron :Item.daggerRustedIron) : Item.bow))).randomizeForMob(this, true));
        }
    }
    public void attackEntityWithRangedAttack(EntityLiving par1EntityLivingBase, float par2) {
        EntityArrow var3 = new EntityArrow(this.worldObj, this, par1EntityLivingBase, 1.6F, (float)(14 - this.worldObj.difficultySetting * 4), Item.arrowIron, false);
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
        Calendar var5 = this.worldObj.getCurrentDate();
        if (var5.get(2) + 1 == 4 && var5.get(5) == 1){
            this.setBoots((new ItemStack(Items.MaidBoots)).randomizeForMob(this, false));
            this.setLeggings((new ItemStack(Items.MaidLeggings)).randomizeForMob(this, false));
            this.setCuirass((new ItemStack(Items.MaidChestplate)).randomizeForMob(this, false));
            this.setHelmet((new ItemStack(Items.MaidHelmet)).randomizeForMob(this, false));
        } else{
            this.setBoots((new ItemStack(Items.WolfBoots)).randomizeForMob(this, false));
            this.setLeggings((new ItemStack(Items.WolfLeggings)).randomizeForMob(this, false));
            this.setCuirass((new ItemStack(Items.WolfChestplate)).randomizeForMob(this, false));
            this.setHelmet((new ItemStack(Items.WolfHelmet)).randomizeForMob(this, false));
        }

    }
}
