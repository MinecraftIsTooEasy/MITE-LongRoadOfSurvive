package net.oilcake.mitelros.entity;


import net.minecraft.*;

import java.util.ArrayList;
import java.util.List;

public class EntityMinerZombie extends EntityZombie {
    private int conversionTime;
    public EntityMinerZombie(World world) {
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
        this.setEntityAttribute(GenericAttributes.followRange, 128.0);
        this.setEntityAttribute(GenericAttributes.movementSpeed, 0.25999999046325684);
        this.setEntityAttribute(GenericAttributes.attackDamage, 5.0);
        this.setEntityAttribute(field_110186_bp, this.rand.nextDouble() * 0.10000000149011612);
        this.setEntityAttribute(GenericAttributes.maxHealth, 20.0);
    }

    public void addRandomWeapon() {
        List items = new ArrayList();
        items.add(new RandomItemListEntry(Item.daggerRustedIron, 2));
        if (this.worldObj.getDayOfWorld() >= 10 && !Minecraft.isInTournamentMode()) {
            items.add(new RandomItemListEntry(Item.pickaxeRustedIron, 1));
        }

        if (this.worldObj.getDayOfWorld() >= 20 && !Minecraft.isInTournamentMode()) {
            items.add(new RandomItemListEntry(Item.warHammerRustedIron, 1));
        }

        RandomItemListEntry entry = (RandomItemListEntry)WeightedRandom.getRandomItem(this.rand, items);
        this.setHeldItemStack((new ItemStack(entry.item)).randomizeForMob(this, true));
    }

    protected void addRandomEquipment() {
        this.addRandomWeapon();
        this.setBoots((new ItemStack(Item.bootsChainRustedIron)).randomizeForMob(this, true));
        this.setLeggings((new ItemStack(Item.legsChainRustedIron)).randomizeForMob(this, true));
        this.setCuirass((new ItemStack(Item.plateChainRustedIron)).randomizeForMob(this, true));
        this.setHelmet((new ItemStack(Item.helmetChainRustedIron)).randomizeForMob(this, true));
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
