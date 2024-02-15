package net.oilcake.mitelros.mixins.entity;

import net.minecraft.*;
import net.oilcake.mitelros.item.Items;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Calendar;

@Mixin(EntitySkeleton.class)
public abstract class EntitySkeletonMixin extends EntityMonster implements IRangedEntity{
    @Shadow
    private int frenzied_by_bone_lord_countdown;
    int num_arrows;

    public EntitySkeletonMixin(World par1World) {
        super(par1World);
    }

    @Inject(method = "<init>",at = @At("RETURN"))
    public void injectCtor(CallbackInfo callbackInfo) {
        this.num_arrows = this.rand.nextInt(3) + (this.isLongdead() ? 6 : 2) + (this.isLongdeadGuardian() ? 2 : 0);
        this.tasks.addTask(3, new PathfinderGoalAvoidPlayer(this, EntityWolf.class, 10.0F, 1.0, 1.2));
    }
    @Overwrite
    public void readEntityFromNBT(NBTTagCompound par1NBTTagCompound) {
        super.readEntityFromNBT(par1NBTTagCompound);
        if (par1NBTTagCompound.hasKey("SkeletonType")) {
            byte var2 = par1NBTTagCompound.getByte("SkeletonType");
            this.setSkeletonType(var2);
        }
        this.setCombatTask();
        this.num_arrows = par1NBTTagCompound.getByte("num_arrows");
    }
    @Overwrite
    public void onLivingUpdate() {
        if (this.worldObj.isRemote && this.getSkeletonType() == 1) {
            this.setSize(0.72F, 2.34F);
        }
        if (this.frenzied_by_bone_lord_countdown > 0) {
            this.setFrenziedByBoneLordCountdown(this.frenzied_by_bone_lord_countdown - 1);
        }
        if (this.num_arrows == 0 && this.getHeldItemStack() != null) {
            if(this.getHeldItemStack().getItem() instanceof ItemBow){
                this.setHeldItemStack(null);
            }
        }
        if(this.getHeldItemStack() == null && this.getSkeletonType() == 0){
            this.setSkeletonType(2);
            this.setCombatTask();
        }
        super.onLivingUpdate();
    }
    @Overwrite
    public void writeEntityToNBT(NBTTagCompound par1NBTTagCompound) {
        super.writeEntityToNBT(par1NBTTagCompound);
        par1NBTTagCompound.setByte("SkeletonType", (byte)this.getSkeletonType());
        par1NBTTagCompound.setByte("num_arrows", (byte)this.num_arrows);
    }
    @Overwrite
    public void setCombatTask() {
        this.tasks.removeTask(this.aiAttackOnCollide);
        this.tasks.removeTask(this.aiArrowAttack);
        ItemStack var1 = this.getHeldItemStack();
        if (var1 != null && var1.getItem() instanceof ItemBow && this.num_arrows > 0) {
            this.tasks.addTask(4, this.aiArrowAttack);
            this.tasks.addTask(3, new EntityAISeekFiringPosition(this, 1.0F, true));
        } else {
            this.tasks.addTask(4, this.aiAttackOnCollide);
        }

    }

//    @Overwrite
//    protected void dropFewItems(boolean recently_hit_by_player, DamageSource damage_source) {
//        int looting = damage_source.getLootingModifier();
//        int num_drops;
//        int i;
//        if (this.getSkeletonType() == 1) {
//            num_drops = this.rand.nextInt(3 + looting) - 1;
//            if (num_drops > 0 && !recently_hit_by_player) {
//                num_drops -= this.rand.nextInt(num_drops + 1);
//            }
//
//            if(Item.getItem(num_drops) instanceof ItemArmor || Item.getItem(num_drops) instanceof ItemTool){
//                num_drops = 0;
//            }
//
//            for(i = 0; i < num_drops; ++i) {
//                this.dropItem(Item.coal.itemID, 1);
//            }
//
//            if (recently_hit_by_player && !this.has_taken_massive_fall_damage && this.rand.nextInt(this.getBaseChanceOfRareDrop()) < 5 + looting * 2) {
//                this.dropItemStack(new ItemStack(Item.skull.itemID, 1, 1), 0.0F);
//            }
//        } else if (this.getSkeletonType() != 2) {
//            num_drops = this.rand.nextInt(2 + looting);
//            if (num_drops > 0 && !recently_hit_by_player) {
//                num_drops -= this.rand.nextInt(num_drops + 1);
//            }
//
//            if (this.isLongdead() && num_drops > 0) {
//                num_drops = this.rand.nextInt(3) == 0 ? 1 : 0;
//            }
//
//            for(i = 0; i < num_drops; ++i) {
//                this.dropItem(this.isLongdead() ? Item.arrowAncientMetal.itemID : Item.arrowRustedIron.itemID, 1);
//            }
//        }
//
//        num_drops = this.rand.nextInt(3);
//        if (num_drops > 0 && !recently_hit_by_player) {
//            num_drops -= this.rand.nextInt(num_drops + 1);
//        }
//
//        for(i = 0; i < num_drops; ++i) {
//            this.dropItem(Item.bone.itemID, 1);
//        }
//    }

    @Overwrite
    public GroupDataEntity onSpawnWithEgg(GroupDataEntity par1EntityLivingData) {
        par1EntityLivingData = super.onSpawnWithEgg(par1EntityLivingData);
        int skeleton_type = this.forced_skeleton_type >= 0 ? this.forced_skeleton_type : this.getRandomSkeletonType(this.worldObj);
        if (skeleton_type == 1) {
            if(this.isBoneLord()){
                this.setCurrentItemOrArmor(1, (new ItemStack(Items.tungstenBoots)).randomizeForMob(this, false));
                this.setCurrentItemOrArmor(2, (new ItemStack(Items.tungstenLeggings)).randomizeForMob(this, false));
                this.setCurrentItemOrArmor(3, (new ItemStack(Items.tungstenChestplate)).randomizeForMob(this, false));
                this.setCurrentItemOrArmor(4, (new ItemStack(Items.tungstenHelmet)).randomizeForMob(this, false));
                this.getEntityAttribute(GenericAttributes.attackDamage).setAttribute(8.0);
            } else{
                this.setCurrentItemOrArmor(1, (new ItemStack(Items.tungstenBootsChain)).randomizeForMob(this, false));
                this.setCurrentItemOrArmor(2, (new ItemStack(Items.tungstenLeggingsChain)).randomizeForMob(this, false));
                this.setCurrentItemOrArmor(3, (new ItemStack(Items.tungstenChestplateChain)).randomizeForMob(this, false));
                this.setCurrentItemOrArmor(4, (new ItemStack(Items.tungstenHelmetChain)).randomizeForMob(this, false));
                this.getEntityAttribute(GenericAttributes.attackDamage).setAttribute(6.0);
            }
            if(this.rand.nextInt(24)==0){
                this.Is_Wizard = true;
                this.setCurrentItemOrArmor(0, (new ItemStack(Items.LavaWand)).randomizeForMob(this, false));
                this.tasks.addTask(3, new PathfinderGoalAvoidPlayer(this, EntityPlayer.class, 9.0F, 1.0, 1.0));
                this.tasks.addTask(4, this.aiArrowAttack);
            } else{
                this.setCurrentItemOrArmor(0, (new ItemStack(Items.tungstenSword)).randomizeForMob(this, false));
                this.tasks.addTask(4, this.aiAttackOnCollide);
            }
            this.setSkeletonType(1);
        } else {
            if (skeleton_type == 2) {
                this.setSkeletonType(2);
                this.tasks.addTask(4, this.aiAttackOnCollide);
            } else if (skeleton_type == 0) {
                this.tasks.addTask(4, this.aiArrowAttack);
            } else {
                Minecraft.setErrorMessage("onSpawnWithEgg: Unrecognized skeleton type " + skeleton_type);
            }

            this.addRandomEquipment();
        }

        this.setCanPickUpLoot(true);
        if (this.getEquipmentInSlot(4) == null) {
            Calendar var2 = this.worldObj.getCurrentDate();
            if (var2.get(2) + 1 == 10 && var2.get(5) == 31 && this.rand.nextFloat() < 0.25F) {
                this.setCurrentItemOrArmor(4, new ItemStack(this.rand.nextFloat() < 0.1F ? Block.pumpkinLantern : Block.pumpkin));
                this.equipmentDropChances[4] = 0.0F;
            }
        }
        return par1EntityLivingData;
    }
    @Shadow
    public boolean isLongdead() {
        return false;
    }
    @Shadow
    public void setSkeletonType(int par1) {}
    @Shadow
    private PathfinderGoalArrowAttack aiArrowAttack;
    @Shadow
    private PathfinderGoalMeleeAttack aiAttackOnCollide;
    @Shadow
    public int forced_skeleton_type;
    @Shadow
    public int getRandomSkeletonType(World world) {
    return -1;
    }
    @Shadow
    protected void addRandomEquipment() {
    }
    @Overwrite
    public void addRandomWeapon() {
        if (this.getSkeletonType() == 2 && this.rand.nextInt(20) == 0) {
            int day_of_world = this.worldObj.getDayOfWorld();
            if (day_of_world >= 10) {
                this.setCurrentItemOrArmor(0, (new ItemStack(day_of_world >= 20 && !this.rand.nextBoolean() ? Item.swordRustedIron : Item.daggerRustedIron)).randomizeForMob(this, false));
                return;
            }
        }

        this.setCurrentItemOrArmor(0, (new ItemStack((Item)(this.getSkeletonType() == 2 ? Item.clubWood : Item.bow))).randomizeForMob(this, true));
    }
    @Shadow
    public int getSkeletonType() {
        return this.dataWatcher.getWatchableObjectByte(13);
    }

    @Shadow public abstract boolean isLongdeadGuardian();
    @Shadow public boolean setFrenziedByBoneLordCountdown(int frenzied_by_bone_lord_countdown) {
        return false;
    }

    @Shadow public abstract void setHeldItemStack(ItemStack item_stack);

    @Shadow public abstract boolean isBoneLord();

    @Shadow public abstract boolean isAncientBoneLord();

    @Overwrite
    public void attackEntityWithRangedAttack(EntityLiving par1EntityLivingBase, float par2) {
        EntityArrow var3 = new EntityArrow(this.worldObj, this, par1EntityLivingBase, 1.6F, (float)(14 - this.worldObj.difficultySetting * 4), this.isLongdead() ? Item.arrowAncientMetal : Item.arrowRustedIron, false);
        int var4 = EnchantmentManager.getEnchantmentLevel(Enchantment.power.effectId, this.getHeldItemStack());
        int var5 = EnchantmentManager.getEnchantmentLevel(Enchantment.punch.effectId, this.getHeldItemStack());
        double damage = (double)(par2 * 2.0F) + this.rand.nextGaussian() * 0.25 + (double)((float)this.worldObj.difficultySetting * 0.11F);
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
        this.num_arrows--;
    }
    protected boolean Is_Wizard = false;
    @Overwrite
    protected void dropFewItems(boolean recently_hit_by_player, DamageSource damage_source) {
        int looting = damage_source.getLootingModifier();
        int num_drops;
        int i;
        if(Is_Wizard){
            num_drops = 1 + this.rand.nextInt(2);
            if (!recently_hit_by_player) {
                num_drops = 0;
            }
            for(i = 0; i < num_drops; ++i) {
                this.dropItem(Item.blazePowder.itemID, 1);
            }
            for(i = 0; i < num_drops; ++i) {
                this.dropItem(Item.netherStalkSeeds.itemID, 1);
            }
        }
        if (this.getSkeletonType() == 1) {
            num_drops = this.rand.nextInt(3 + looting) - 1;
            if (num_drops > 0 && !recently_hit_by_player) {
                num_drops -= this.rand.nextInt(num_drops + 1);
            }

            for(i = 0; i < num_drops; ++i) {
                this.dropItem(Item.coal.itemID, 1);
            }

            if (recently_hit_by_player && !this.has_taken_massive_fall_damage && this.rand.nextInt(this.getBaseChanceOfRareDrop()) < 5 + looting * 2) {
                this.dropItemStack(new ItemStack(Item.skull.itemID, 1, 1), 0.0F);
            }
        } else if (this.getSkeletonType() != 2) {
            num_drops = Math.min(this.num_arrows, this.rand.nextInt(2 + looting));
            if (num_drops > 0 && !recently_hit_by_player) {
                num_drops -= this.rand.nextInt(num_drops + 1);
            }

            if (this.isLongdead() && num_drops > 0) {
                num_drops = this.rand.nextInt(3) == 0 ? 1 : 0;
            }

            for(i = 0; i < num_drops; ++i) {
                this.dropItem(this.isLongdead() ? Item.arrowAncientMetal.itemID : Item.arrowRustedIron.itemID, 1);
            }
        }

        num_drops = this.rand.nextInt(3);
        if (num_drops > 0 && !recently_hit_by_player) {
            num_drops -= this.rand.nextInt(num_drops + 1);
        }
        for(i = 0; i < num_drops; ++i) {
            this.dropItem(Item.bone.itemID, 1);
        }
    }
    public void setNum_arrows(int arrows){
        this.num_arrows = arrows;
    }
}
