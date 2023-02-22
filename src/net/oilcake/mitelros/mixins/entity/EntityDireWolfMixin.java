package net.oilcake.mitelros.mixins.entity;

import net.minecraft.DamageSource;
import net.minecraft.EntityDireWolf;
import net.minecraft.EntityWolf;
import net.minecraft.World;
import net.oilcake.mitelros.item.Items;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(EntityDireWolf.class)
public class EntityDireWolfMixin extends EntityWolf {

    protected void dropFewItems(boolean recently_hit_by_player, DamageSource damage_source) {
        this.dropItem(Items.Wolf_fur.itemID, 2 + rand.nextInt(1));
    }
    public EntityDireWolfMixin(World par1World) {
        super(par1World);
    }

}
