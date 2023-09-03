package net.oilcake.mitelros.entity;

import net.minecraft.*;

import java.util.ArrayList;
import java.util.List;

public class EntityEvil extends EntityGhost{

    public EntityEvil(World par1World) {
        super(par1World);
    }
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.setEntityAttribute(GenericAttributes.followRange, 128.0);
        this.setEntityAttribute(GenericAttributes.movementSpeed, 0.3);
        this.setEntityAttribute(GenericAttributes.attackDamage, 7.0);
        this.setEntityAttribute(GenericAttributes.maxHealth, 30.0);
    }
    protected float getSoundVolume(String sound) {
        return 1.25F;
    }
    public void addRandomWeapon() {
        List items = new ArrayList();
        items.add(new RandomItemListEntry(Item.swordGold, 1));
        if (this.worldObj.getDayOfWorld() >= 10 && !Minecraft.isInTournamentMode()) {
            items.add(new RandomItemListEntry(Item.battleAxeGold, 3));
        }
        RandomItemListEntry entry = (RandomItemListEntry)WeightedRandom.getRandomItem(this.rand, items);
        this.setHeldItemStack((new ItemStack(entry.item)).randomizeForMob(this, true));
    }
    protected void addRandomEquipment() {
        this.addRandomWeapon();
        this.setCuirass((new ItemStack(Item.plateGold)).randomizeForMob(this, true));
        this.setHelmet((new ItemStack(Item.helmetGold)).randomizeForMob(this, true));
    }
}
