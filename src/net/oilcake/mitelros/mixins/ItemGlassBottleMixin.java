package net.oilcake.mitelros.mixins;

import net.minecraft.*;
import net.oilcake.mitelros.item.Items;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(ItemGlassBottle.class)
public class ItemGlassBottleMixin extends Item{
    @Overwrite
    public boolean onItemRightClick(EntityPlayer player, float partial_tick, boolean ctrl_is_down) {
        RaycastCollision rc = player.getSelectedObject(partial_tick, true);
        if (rc != null && rc.isBlock()) {
            if (rc.getBlockHitMaterial() != Material.water && rc.getNeighborOfBlockHitMaterial() != Material.water) {
                return false;
            } else {
                if (player.onServer()) {
                    player.convertOneOfHeldItem(new ItemStack(Items.SuspiciousPotion, 1, 0));
                }

                return true;
            }
        } else {
            return false;
        }
    }
}
