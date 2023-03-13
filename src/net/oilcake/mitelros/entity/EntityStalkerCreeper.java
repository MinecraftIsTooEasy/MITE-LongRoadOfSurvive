package net.oilcake.mitelros.entity;

import net.minecraft.*;
import net.oilcake.mitelros.item.Items;

public class EntityStalkerCreeper extends EntityCreeper {

    public EntityStalkerCreeper(World par1World) {
        super(par1World);
        this.setSize(this.width * getScale(), this.height * getScale());
        this.explosionRadius *= 1.5F;
    }
    public float getNaturalDefense(DamageSource damage_source) {
        return super.getNaturalDefense(damage_source) + (damage_source.bypassesMundaneArmor() ? 0.0F : 1.0F);
    }
    public int getFragParticle() {
        return Items.fragStalkerCreeper.itemID;
    }

    public int getExperienceValue() {
        return super.getExperienceValue() * 2;
    }

    public void onDeath(DamageSource par1DamageSource) {
        super.onDeath(par1DamageSource);
        if (par1DamageSource.getResponsibleEntity() instanceof EntitySkeleton) {
            int var2 = Item.recordUnderworld.itemID + this.rand.nextInt(Item.recordLegends.itemID - Item.recordUnderworld.itemID + 1);
            this.dropItem(var2, 1);
        }

    }
}
