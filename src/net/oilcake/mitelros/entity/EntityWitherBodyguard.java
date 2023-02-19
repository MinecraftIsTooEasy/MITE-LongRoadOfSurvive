package net.oilcake.mitelros.entity;

import net.minecraft.*;
import net.oilcake.mitelros.item.Items;

import java.util.ArrayList;
import java.util.List;

public class EntityWitherBodyguard extends EntitySkeleton {
    public EntityWitherBodyguard(World par1World) {
        super(par1World);
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
//    protected void dropFewItems(boolean recently_hit_by_player, DamageSource damage_source) {
//        if (recently_hit_by_player){
//            super.dropFewItems(recently_hit_by_player, damage_source);
//        }
//    }
    @Override
    public void dropContainedItems() {

    }

    @Override
    protected void dropEquipment(boolean recently_hit_by_player, int par2) {

    }
    public void addRandomWeapon() {
        List items = new ArrayList();
        items.add(new RandomItemListEntry(Items.tungstenDagger, 2));
        if (!Minecraft.isInTournamentMode()) {
            items.add(new RandomItemListEntry(Items.tungstenSword, 1));
            items.add(new RandomItemListEntry(Items.tungstenWarHammer, 1));
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
}
