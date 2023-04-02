package net.oilcake.mitelros.entity;

import net.minecraft.*;
import net.oilcake.mitelros.item.Items;

import java.util.ArrayList;
import java.util.List;

public class EntityZombieLord extends EntityRevenant {
    public EntityZombieLord(World par1World) {
        super(par1World);
    }
    public void addRandomWeapon() {
        List items = new ArrayList();
        items.add(new RandomItemListEntry(Items.VibraniumSword, 2));
        RandomItemListEntry entry = (RandomItemListEntry)WeightedRandom.getRandomItem(this.rand, items);
        this.setHeldItemStack((new ItemStack(entry.item)).randomizeForMob(this, true));
    }

    protected void addRandomEquipment() {
        this.addRandomWeapon();
        this.setBoots((new ItemStack(Items.VibraniumBoots)).randomizeForMob(this, true));
        this.setLeggings((new ItemStack(Items.VibraniumLeggings)).randomizeForMob(this, true));
        this.setCuirass((new ItemStack(Items.VibraniumChestplate)).randomizeForMob(this, true));
        this.setHelmet((new ItemStack(Items.VibraniumHelmet)).randomizeForMob(this, true));
    }
}
