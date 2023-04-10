package net.oilcake.mitelros.entity;

import net.minecraft.*;
import net.oilcake.mitelros.item.Items;

import java.util.ArrayList;
import java.util.List;

public class EntityPigmanLord extends EntityPigZombie {
    private int angerLevel;
    private int randomSoundDelay;
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.setEntityAttribute(GenericAttributes.maxHealth, 30.0);
        this.setEntityAttribute(GenericAttributes.followRange, 128.0);
        this.setEntityAttribute(GenericAttributes.attackDamage, 9.0);
    }

    public EntityPigmanLord(World par1World) {
        super(par1World);
    }
    protected EntityPlayer findPlayerToAttack(float max_distance) {
        Entity previous_target = this.getEntityToAttack();
        EntityPlayer target = super.findPlayerToAttack(max_distance);
        if (target != null && target != previous_target) {
            this.becomeAngryAt(target);
        }

        return target;
    }
    private void becomeAngryAt(Entity par1Entity) {
        this.entityToAttack = par1Entity;
        this.angerLevel = 4000;
        this.randomSoundDelay = this.rand.nextInt(40);
    }
    public void addRandomWeapon() {
        List items = new ArrayList();
        items.add(new RandomItemListEntry(Items.tungstenSword, 2));
        if (!Minecraft.isInTournamentMode()) {
            items.add(new RandomItemListEntry(Items.tungstenBattleAxe, 1));
            items.add(new RandomItemListEntry(Items.tungstenWarHammer, 1));
        }
        RandomItemListEntry entry = (RandomItemListEntry)WeightedRandom.getRandomItem(this.rand, items);
        this.setHeldItemStack((new ItemStack(entry.item)).randomizeForMob(this, true));
    }

    public void addRandomEquipment() {
        this.addRandomWeapon();
        this.setBoots((new ItemStack(Items.tungstenBoots)).randomizeForMob(this, true));
        this.setLeggings((new ItemStack(Items.tungstenLeggings)).randomizeForMob(this, true));
        this.setCuirass((new ItemStack(Items.tungstenChestplate)).randomizeForMob(this, true));
        this.setHelmet((new ItemStack(Items.tungstenHelmet)).randomizeForMob(this, true));
    }
    public int getExperienceValue() {
        return super.getExperienceValue() * 2;
    }


}
