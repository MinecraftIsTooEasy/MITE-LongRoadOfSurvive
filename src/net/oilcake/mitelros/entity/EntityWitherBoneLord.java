package net.oilcake.mitelros.entity;

import net.minecraft.*;
import net.oilcake.mitelros.item.Items;

import java.util.ArrayList;
import java.util.List;

public class EntityWitherBoneLord extends EntityBoneLord {

    public EntityWitherBoneLord(World par1World) {
        super(par1World);
        this.setSkeletonType(1);
    }
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.setEntityAttribute(GenericAttributes.followRange, 128.0);
        this.setEntityAttribute(GenericAttributes.movementSpeed, 0.27000001072883606);
        this.setEntityAttribute(GenericAttributes.attackDamage, 8.0);
        this.setEntityAttribute(GenericAttributes.maxHealth, 20.0);
    }
//    @Override
//    public void dropContainedItems() {
//
//    }
//
//    @Override
//    protected void dropEquipment(boolean recently_hit_by_player, int par2) {
//
//    }
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
        items.add(new RandomItemListEntry(Items.tungstenSword, 2));
        if (!Minecraft.isInTournamentMode()) {
            if (this.worldObj.getDayOfWorld() >= 25 && !Minecraft.isInTournamentMode()) {
                items.add(new RandomItemListEntry(Items.tungstenBattleAxe, 1));
            }

            if (this.worldObj.getDayOfWorld() >= 50 && !Minecraft.isInTournamentMode()) {
                items.add(new RandomItemListEntry(Items.tungstenWarHammer, 1));
            }
        }

        RandomItemListEntry entry = (RandomItemListEntry)WeightedRandom.getRandomItem(this.rand, items);
        this.setHeldItemStack((new ItemStack(entry.item)).randomizeForMob(this, true));
    }

    protected void addRandomEquipment() {
        this.addRandomWeapon();
        this.setBoots((new ItemStack(Items.tungstenBoots)).randomizeForMob(this, true));
        this.setLeggings((new ItemStack(Items.tungstenLeggings)).randomizeForMob(this, true));
        this.setCuirass((new ItemStack(Items.tungstenChestplate)).randomizeForMob(this, true));
        this.setHelmet((new ItemStack(Items.tungstenHelmet)).randomizeForMob(this, true));
    }

    public EntityDamageResult attackEntityAsMob(Entity target) {
        EntityDamageResult result = super.attackEntityAsMob(target);
        if (result != null && !result.entityWasDestroyed()) {
            if (result.entityLostHealth() && target instanceof EntityPlayer) {
                target.getAsEntityLivingBase().addPotionEffect(new MobEffect(MobEffectList.wither.id, 850));
            }
            return result;
        } else {
            return result;
        }
    }
    public Class getTroopClass() {
        return EntityWitherBodyguard.class;
    }

    public int getExperienceValue() {
        return super.getExperienceValue() * 2;
    }
    public boolean isHarmedByFire() {
        return false;
    }

    public boolean isHarmedByLava() {
        return false;
    }
}
