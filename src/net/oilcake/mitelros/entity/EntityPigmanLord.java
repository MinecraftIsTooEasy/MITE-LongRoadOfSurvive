package net.oilcake.mitelros.entity;

import net.minecraft.*;

import java.util.ArrayList;
import java.util.List;

public class EntityPigmanLord extends EntityPigZombie {
    private int angerLevel;
    private int randomSoundDelay;
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.setEntityAttribute(GenericAttributes.maxHealth, 30.0);
        this.setEntityAttribute(GenericAttributes.followRange, 32.0);
        this.setEntityAttribute(GenericAttributes.attackDamage, 9.0);
        this.setEntityAttribute(GenericAttributes.movementSpeed, 0.2);
    }

    public EntityPigmanLord(World par1World) {
        super(par1World);
    }
    protected boolean isAIEnabled() {
        return true;
    }
    protected EntityPlayer findPlayerToAttack(float max_distance) {
        if (this.angerLevel < 1) {
            max_distance /= 4.0F;
        }

        Entity previous_target = this.getEntityToAttack();
        EntityPlayer target = super.findPlayerToAttack(max_distance);
        if (target != null && target != previous_target) {
            this.becomeAngryAt(target);
        }

        return target;
    }
    private void becomeAngryAt(Entity par1Entity) {
        this.entityToAttack = par1Entity;
        this.angerLevel = 400 + this.rand.nextInt(400);
        this.randomSoundDelay = this.rand.nextInt(40);
    }
    public void addRandomWeapon() {
        List items = new ArrayList();
        items.add(new RandomItemListEntry(Item.swordGold, 2));
        if (this.worldObj.getDayOfWorld() >= 10 && !Minecraft.isInTournamentMode()) {
            items.add(new RandomItemListEntry(Item.battleAxeGold, 1));
        }

        if (this.worldObj.getDayOfWorld() >= 20 && !Minecraft.isInTournamentMode()) {
            items.add(new RandomItemListEntry(Item.warHammerGold, 1));
        }

        RandomItemListEntry entry = (RandomItemListEntry)WeightedRandom.getRandomItem(this.rand, items);
        this.setHeldItemStack((new ItemStack(entry.item)).randomizeForMob(this, true));
    }

    public void addRandomEquipment() {
        this.addRandomWeapon();
        this.setBoots((new ItemStack(Item.bootsGold)).randomizeForMob(this, true));
        this.setLeggings((new ItemStack(Item.legsGold)).randomizeForMob(this, true));
        this.setCuirass((new ItemStack(Item.plateGold)).randomizeForMob(this, true));
        this.setHelmet((new ItemStack(Item.helmetGold)).randomizeForMob(this, true));
    }
    public int getExperienceValue() {
        return super.getExperienceValue() * 2;
    }

}
