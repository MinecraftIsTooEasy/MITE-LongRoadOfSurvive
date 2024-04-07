package net.oilcake.mitelros.entity;

import net.minecraft.*;
import net.oilcake.mitelros.item.Items;

public class EntityHusk extends EntityZombie {
    Item[] rare_drops;
    public EntityHusk(World par1World) {
        super(par1World);
        this.rare_drops = new Item[]{Item.copperNugget, Item.silverNugget, Item.goldNugget, Item.ironNugget};
    }
    public void setVillager(boolean villager, int profession) {
//        Minecraft.setErrorMessage("setVillager: why setting villager for husk?");
//        (new Exception()).printStackTrace();
    }
    public boolean isVillager() {
        return false;
    }
    public boolean catchesFireInSunlight() {
        return false;
    }

    public EntityDamageResult attackEntityAsMob(Entity target) {
        EntityDamageResult result = super.attackEntityAsMob(target);
        if (result != null && !result.entityWasDestroyed()) {
            if (result.entityLostHealth() && target instanceof EntityPlayer) {
                target.getAsEntityLivingBase().addPotionEffect(new MobEffect(MobEffectList.hunger.id, 650));
            }
            return result;
        } else {
            return result;
        }
    }
    protected void dropFewItems(boolean recently_hit_by_player, DamageSource damage_source) {
        if (this.rand.nextFloat() < (recently_hit_by_player ? 0.5F : 0.25F)) {
            this.dropItem(Item.rottenFlesh.itemID, 1);
        }
        int num_drops = this.rand.nextInt(2);
        int fortune = damage_source.getLootingModifier();
        if (fortune > 0) {
            num_drops += this.rand.nextInt(fortune + 1);
        }
        if (num_drops > 0 && !recently_hit_by_player) {
            num_drops -= this.rand.nextInt(num_drops + 1);
        }
        for(int i = 0; i < num_drops; ++i) {
            this.dropItem(Items.sulphur, 1);
        }
    }
}
