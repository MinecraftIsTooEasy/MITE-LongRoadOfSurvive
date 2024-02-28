package net.oilcake.mitelros.entity;


import net.minecraft.*;

import java.util.ArrayList;
import java.util.List;

public class EntityRetinueZombie extends EntityZombie {
    private int conversionTime;
    public EntityRetinueZombie(World world) {
        super(world);
        this.tasks.addTask(3, new EntityAISeekLitTorch(this, 1.0F));
    }
    public void onUpdate() {
        if (!this.worldObj.isRemote && this.isConverting()) {
            int var1 = this.getConversionTimeBoost();
            this.conversionTime -= var1;
            if (this.conversionTime <= 0) {
                this.convertToVillager();
            }
        }
        this.tryDisableNearbyLightSource();
        super.onUpdate();
    }

    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.setEntityAttribute(GenericAttributes.movementSpeed, 0.25999999046325684);
        this.setEntityAttribute(GenericAttributes.attackDamage, 5.0);
        this.setEntityAttribute(field_110186_bp, this.rand.nextDouble() * 0.10000000149011612);
        this.setEntityAttribute(GenericAttributes.maxHealth, 20.0);
    }

    public void addRandomWeapon() {
        List items = new ArrayList();
        if(this.worldObj.getDayOfWorld() < 24){
            items.add(new RandomItemListEntry(Item.clubWood, 2));
            items.add(new RandomItemListEntry(Item.daggerRustedIron, 1));
        }
        if(this.worldObj.getDayOfWorld() >= 24){
            items.add(new RandomItemListEntry(Item.swordRustedIron, 1));
        }
        if (this.worldObj.getDayOfWorld() >= 12 && !Minecraft.isInTournamentMode()) {
            items.add(new RandomItemListEntry(Item.pickaxeRustedIron, 1));
        }
        if (this.worldObj.getDayOfWorld() >= 24 && !Minecraft.isInTournamentMode()) {
            items.add(new RandomItemListEntry(Item.warHammerRustedIron, 1));
        }
        RandomItemListEntry entry = (RandomItemListEntry)WeightedRandom.getRandomItem(this.rand, items);
        this.setHeldItemStack((new ItemStack(entry.item)).randomizeForMob(this, true));
    }

    protected void addRandomEquipment() {
        List helmet = new ArrayList();
        helmet.add(new RandomItemListEntry(Item.helmetLeather, 6));
        if (this.worldObj.getDayOfWorld() >= 12 && !Minecraft.isInTournamentMode()) {
            helmet.add(new RandomItemListEntry(Item.helmetChainRustedIron, 3));
            helmet.add(new RandomItemListEntry(Item.helmetChainCopper, 3));
        }
        if (this.worldObj.getDayOfWorld() >= 24 && !Minecraft.isInTournamentMode()) {
            helmet.add(new RandomItemListEntry(Item.helmetCopper, 1));
            helmet.add(new RandomItemListEntry(Item.helmetRustedIron, 1));
        }

        List plate = new ArrayList();
        plate.add(new RandomItemListEntry(Item.plateLeather, 6));
        if (this.worldObj.getDayOfWorld() >= 12 && !Minecraft.isInTournamentMode()) {
            plate.add(new RandomItemListEntry(Item.plateChainRustedIron, 3));
            plate.add(new RandomItemListEntry(Item.plateChainCopper, 3));
        }
        if (this.worldObj.getDayOfWorld() >= 24 && !Minecraft.isInTournamentMode()) {
            plate.add(new RandomItemListEntry(Item.plateCopper, 1));
            plate.add(new RandomItemListEntry(Item.plateRustedIron, 1));
        }

        List legs = new ArrayList();
        legs.add(new RandomItemListEntry(Item.legsLeather, 6));
        if (this.worldObj.getDayOfWorld() >= 12 && !Minecraft.isInTournamentMode()) {
            legs.add(new RandomItemListEntry(Item.legsChainRustedIron, 3));
            legs.add(new RandomItemListEntry(Item.legsChainCopper, 3));
        }
        if (this.worldObj.getDayOfWorld() >= 24 && !Minecraft.isInTournamentMode()) {
            legs.add(new RandomItemListEntry(Item.legsCopper, 1));
            legs.add(new RandomItemListEntry(Item.legsRustedIron, 1));
        }

        List boots = new ArrayList();
        boots.add(new RandomItemListEntry(Item.bootsLeather, 6));
        if (this.worldObj.getDayOfWorld() >= 12 && !Minecraft.isInTournamentMode()) {
            boots.add(new RandomItemListEntry(Item.bootsChainRustedIron, 3));
            boots.add(new RandomItemListEntry(Item.bootsChainCopper, 3));
        }
        if (this.worldObj.getDayOfWorld() >= 24 && !Minecraft.isInTournamentMode()) {
            boots.add(new RandomItemListEntry(Item.bootsCopper, 1));
            boots.add(new RandomItemListEntry(Item.bootsRustedIron, 1));
        }
        if (this.worldObj.getDayOfWorld() > 31){
            helmet.remove(0);
            plate.remove(0);
            legs.remove(0);
            boots.remove(0);
        }
        RandomItemListEntry entry;
        entry = (RandomItemListEntry)WeightedRandom.getRandomItem(this.rand, helmet);
        this.setHelmet((new ItemStack(entry.item)).randomizeForMob(this, true));
        entry = (RandomItemListEntry)WeightedRandom.getRandomItem(this.rand, plate);
        this.setCuirass((new ItemStack(entry.item)).randomizeForMob(this, true));
        entry = (RandomItemListEntry)WeightedRandom.getRandomItem(this.rand, legs);
        this.setLeggings((new ItemStack(entry.item)).randomizeForMob(this, true));
        entry = (RandomItemListEntry)WeightedRandom.getRandomItem(this.rand, boots);
        this.setBoots((new ItemStack(entry.item)).randomizeForMob(this, true));
        this.addRandomWeapon();
    }

    public int getExperienceValue() {
        return super.getExperienceValue() * 2;
    }
    protected void dropFewItems(boolean recently_hit_by_player, DamageSource damage_source) {
        if (this.rand.nextFloat() < (recently_hit_by_player ? 0.5F : 0.25F)) {
            this.dropItem(Item.rottenFlesh.itemID, 1);
        }
        if (recently_hit_by_player && !this.has_taken_massive_fall_damage && this.rand.nextInt(5) == 0) {
            if(this.rand.nextInt(4) > 0){
                this.dropItem(Item.copperNugget.itemID, 1);
            }
            else {
                this.dropItem(Item.ironNugget.itemID, 1);
            }
        }
    }
}
