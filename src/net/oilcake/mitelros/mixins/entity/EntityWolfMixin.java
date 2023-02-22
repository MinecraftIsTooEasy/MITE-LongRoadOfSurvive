package net.oilcake.mitelros.mixins.entity;

import net.minecraft.*;
import net.oilcake.mitelros.item.Items;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(EntityWolf.class)
public class EntityWolfMixin extends EntityTameableAnimal {
    @Overwrite
    protected void dropFewItems(boolean recently_hit_by_player, DamageSource damage_source) {
        this.dropItem(Items.Wolf_fur.itemID, 1 + rand.nextInt(2));
    }
    public EntityWolfMixin(World par1World) {
        super(par1World);
    }

    @Shadow
    public EntityAgeable createChild(EntityAgeable entityAgeable) {
        return null;
    }

    @Shadow
    protected int getTamingOutcome(EntityPlayer entityPlayer) {
        return 0;
    }
}
