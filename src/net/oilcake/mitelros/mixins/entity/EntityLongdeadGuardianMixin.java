package net.oilcake.mitelros.mixins.entity;

import net.minecraft.*;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(EntityLongdeadGuardian.class)
public class EntityLongdeadGuardianMixin extends EntityLongdead {
    ItemStack stowed_item_stack;
    public EntityLongdeadGuardianMixin(World world) {
        super(world);
    }
    @Overwrite
    public void addRandomWeapon() {
        super.addRandomWeapon();
        if (this.getHeldItem() instanceof ItemBow) {
            this.stowed_item_stack = (new ItemStack(Item.daggerAncientMetal)).randomizeForMob(this, true);
        }
        if(this.getHeldItem() instanceof ItemSword){
            this.stowed_item_stack = (new ItemStack(Item.bowAncientMetal)).randomizeForMob(this, true);
        }
    }
}
