package net.oilcake.mitelros.entity;

import net.minecraft.*;
import net.oilcake.mitelros.item.Items;

public class EntityStalkerCreeper extends EntityCreeper {
    private boolean burntoDeath = false;

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
        if (par1DamageSource.isFireDamage() || par1DamageSource.isLavaDamage() || par1DamageSource.isExplosion()){
            this.burntoDeath = true;
        }
    }
    @Override
    public void onDeathUpdate(){
        super.onDeathUpdate();
        if (this.deathTime == 20 && this.burntoDeath) {
            if (!this.worldObj.isRemote) {
                boolean var2 = this.worldObj.getGameRules().getGameRuleBooleanValue("mobGriefing");
                float explosion_size_vs_blocks = this.explosionRadius * 0.715F;
                float explosion_size_vs_living_entities = this.explosionRadius * 1.1F;
                if (this.getPowered()) {
                    this.worldObj.createExplosion(this, this.posX, this.posY + (double)(this.height / 4.0F), this.posZ, explosion_size_vs_blocks * 2.0F, explosion_size_vs_living_entities * 2.0F, var2);
                } else {
                    this.worldObj.createExplosion(this, this.posX, this.posY + (double)(this.height / 4.0F), this.posZ, explosion_size_vs_blocks, explosion_size_vs_living_entities, var2);
                }
                this.entityFX(EnumEntityFX.frags);
            }
        }
    }
}
