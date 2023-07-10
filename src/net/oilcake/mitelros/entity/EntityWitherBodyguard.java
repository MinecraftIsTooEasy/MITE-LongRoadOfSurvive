package net.oilcake.mitelros.entity;

import net.minecraft.*;
import net.oilcake.mitelros.item.Items;

import java.util.ArrayList;
import java.util.List;

public class EntityWitherBodyguard extends EntitySkeleton {
    ItemStack stowed_item_stack;
    int num_arrows;
    public EntityWitherBodyguard(World par1World) {
        super(par1World);
        this.setSkeletonType(1);
        this.setCanPickUpLoot(false);
        this.num_arrows = 6;
    }
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.setEntityAttribute(GenericAttributes.followRange, 128.0);
        this.setEntityAttribute(GenericAttributes.movementSpeed, 0.27000001072883606);
        this.setEntityAttribute(GenericAttributes.attackDamage, 6.0);
        this.setEntityAttribute(GenericAttributes.maxHealth, 12.0);
    }
    public boolean isHoldingRangedWeapon() {
        return this.getHeldItem() instanceof ItemBow;
    }
    public void onLivingUpdate() {
        super.onLivingUpdate();
        if (this.stowed_item_stack != null && (this.getHeldItemStack() == null || this.getTicksExistedWithOffset() % 10 == 0)) {
            if (this.getHeldItemStack() == null) {
                this.swapHeldItemStackWithStowed();
            } else {
                Entity target = this.getTarget();
                if (target != null && this.canSeeTarget(true)) {
                    double distance = (double)this.getDistanceToEntity(target);
                    if (this.isHoldingRangedWeapon()) {
                        if (distance < 5.0) {
                            this.swapHeldItemStackWithStowed();
                        }
                    } else if (distance > 6.0 && this.rand.nextBoolean()) {
                        this.swapHeldItemStackWithStowed();
                    }
                }
            }
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
    }
    public void writeEntityToNBT(NBTTagCompound par1NBTTagCompound) {
        super.writeEntityToNBT(par1NBTTagCompound);
        par1NBTTagCompound.setByte("SkeletonType", (byte)this.getSkeletonType());
        par1NBTTagCompound.setByte("num_arrows", (byte)this.num_arrows);
    }
    public void readEntityFromNBT(NBTTagCompound par1NBTTagCompound) {
        super.readEntityFromNBT(par1NBTTagCompound);
        if (par1NBTTagCompound.hasKey("SkeletonType")) {
            byte var2 = par1NBTTagCompound.getByte("SkeletonType");
            this.setSkeletonType(var2);
        }
        this.setCombatTask();
        this.num_arrows = par1NBTTagCompound.getByte("num_arrows");
    }
    public void attackEntityWithRangedAttack(EntityLiving par1EntityLivingBase, float par2) {
        EntityArrow var3 = new EntityArrow(this.worldObj, this, par1EntityLivingBase, 1.6F, (float)(14 - this.worldObj.difficultySetting * 4), Items.arrowTungsten, false);
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
        var3.setFire(100);
        this.playSound("random.bow", 1.0F, 1.0F / (this.getRNG().nextFloat() * 0.4F + 0.8F));
        this.worldObj.spawnEntityInWorld(var3);
        this.num_arrows--;
    }
    @Override
    protected void dropFewItems(boolean recently_hit_by_player, DamageSource damage_source) {
        int looting = damage_source.getLootingModifier();
        int num_drops;
        int i;
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
    }
    public void addRandomWeapon() {
        List items = new ArrayList();
        items.add(new RandomItemListEntry(Items.tungstenDagger, 2));
        if (!Minecraft.isInTournamentMode()) {
            items.add(new RandomItemListEntry(Items.tungstenSword, 1));
            items.add(new RandomItemListEntry(Items.tungstenBattleAxe, 1));
        }
        if(this.rand.nextInt(8)==0){
            this.stowed_item_stack = (new ItemStack(Items.bowTungsten)).randomizeForMob(this, true);
        }
        RandomItemListEntry entry = (RandomItemListEntry)WeightedRandom.getRandomItem(this.rand, items);
        this.setHeldItemStack((new ItemStack(entry.item)).randomizeForMob(this, true));
    }
    protected void addRandomEquipment() {
        this.addRandomWeapon();
        this.setBoots((new ItemStack(Items.tungstenBootsChain)).randomizeForMob(this, true));
        this.setLeggings((new ItemStack(Items.tungstenLeggingsChain)).randomizeForMob(this, true));
        this.setCuirass((new ItemStack(Items.tungstenChestplateChain)).randomizeForMob(this, true));
        this.setHelmet((new ItemStack(Items.tungstenHelmetChain)).randomizeForMob(this, true));
    }
    public EntityDamageResult attackEntityAsMob(Entity target) {
        EntityDamageResult result = super.attackEntityAsMob(target);
        if (result != null && !result.entityWasDestroyed()) {
            if (result.entityLostHealth() && target instanceof EntityPlayer) {
                target.getAsEntityLivingBase().addPotionEffect(new MobEffect(MobEffectList.wither.id, 300));
            }
            return result;
        } else {
            return result;
        }
    }
    public void swapHeldItemStackWithStowed() {
        ItemStack item_stack = this.stowed_item_stack;
        this.stowed_item_stack = this.getHeldItemStack();
        this.setHeldItemStack(item_stack);
    }

    public boolean isHarmedByFire() {
        return false;
    }

    public boolean isHarmedByLava() {
        return false;
    }
}
