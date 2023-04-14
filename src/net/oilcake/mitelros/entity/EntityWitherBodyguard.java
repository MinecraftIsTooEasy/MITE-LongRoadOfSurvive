package net.oilcake.mitelros.entity;

import net.minecraft.*;
import net.oilcake.mitelros.item.Items;

import java.util.ArrayList;
import java.util.List;

public class EntityWitherBodyguard extends EntitySkeleton {
    ItemStack stowed_item_stack;
    public EntityWitherBodyguard(World par1World) {
        super(par1World);
        this.setCanPickUpLoot(false);
    }
    public int getRandomSkeletonType(World world) {
        return 2;
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
            this.stowed_item_stack = (new ItemStack(Item.bowAncientMetal)).randomizeForMob(this, true);
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
