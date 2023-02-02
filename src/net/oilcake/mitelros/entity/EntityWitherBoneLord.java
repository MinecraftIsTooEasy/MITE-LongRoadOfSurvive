package net.oilcake.mitelros.entity;

import net.minecraft.*;
import net.oilcake.mitelros.item.*;

import java.util.ArrayList;
import java.util.List;

public class EntityWitherBoneLord extends EntityBoneLord {

    public EntityWitherBoneLord(World par1World) {
        super(par1World);
    }
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.setEntityAttribute(GenericAttributes.followRange, 128.0);
        this.setEntityAttribute(GenericAttributes.movementSpeed, 0.27000001072883606);
        this.setEntityAttribute(GenericAttributes.attackDamage, 8.0);
        this.setEntityAttribute(GenericAttributes.maxHealth, 20.0);
    }

    public void addRandomWeapon() {
        List items = new ArrayList();
        items.add(new RandomItemListEntry(Item.swordIron, 2));
        if (!Minecraft.isInTournamentMode()) {
            items.add(new RandomItemListEntry(Items.tungstenBattleAxe, 1));
            items.add(new RandomItemListEntry(Items.tungstenSword, 1));
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
            if (result.entityLostHealth() && target instanceof EntityLiving) {
                target.getAsEntityLivingBase().addPotionEffect(new MobEffect(MobEffectList.wither.id, 850));
            }
            return result;
        } else {
            return result;
        }
    }

    public int getExperienceValue() {
        return super.getExperienceValue() * 3;
    }
}
