package net.oilcake.mitelros.mixins.entity;

import net.minecraft.*;
import net.oilcake.mitelros.item.Items;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(EntityHorse.class)
public class EntityHorseMixin extends EntityAnimal implements IInventoryListener {
    public EntityHorseMixin(World par1World) {
        super(par1World);
    }

    @Shadow
    public EntityAgeable createChild(EntityAgeable entityAgeable) {
        return null;
    }
    @Shadow
    public int getHorseType() {
        return 0;
    }

    @Overwrite
    protected void dropFewItems(boolean recently_hit_by_player, DamageSource damage_source) {
        int num_drops = this.rand.nextInt(3) + 1;

        int i;
        for(i = 0; i < num_drops; ++i) {
            this.dropItem(this.getDropItemId(), 1);
        }

        if (this.getHorseType() <= 2) {
            num_drops = 1 + this.rand.nextInt(1 + damage_source.getButcheringModifier());
            if (this.getHorseType() == 0) {
                num_drops += this.rand.nextInt(2);
            }

            for(i = 0; i < num_drops; ++i) {
                this.dropItem(this.isBurning() ? Items.horse_meat_cooked.itemID : Items.horse_meat.itemID, 1);
            }

        }
    }
    @Overwrite
    protected int getDropItemId() {
        boolean var1 = this.rand.nextInt(4) == 0;
        int var2 = this.getHorseType();
        return var2 == 4 ? Item.bone.itemID : (var2 == 3 ? (var1 ? 0 : Item.rottenFlesh.itemID) : Item.leather.itemID);
    }

    @Shadow
    public void onInventoryChanged(InventorySubcontainer inventorySubcontainer) {

    }
}
