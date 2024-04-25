package net.oilcake.mitelros.mixins;

import net.minecraft.*;
import net.oilcake.mitelros.item.Items;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(ItemGlassBottle.class)
public class ItemGlassBottleMixin extends Item{
//    @Overwrite
//    public boolean onItemRightClick(EntityPlayer player, float partial_tick, boolean ctrl_is_down) {
//        RaycastCollision rc = player.getSelectedObject(partial_tick, true);
//        if (rc != null && rc.isBlock()) {
//            if (rc.getBlockHitMaterial() != Material.water && rc.getNeighborOfBlockHitMaterial() != Material.water) {
//                return false;
//            } else {
//                if (player.onServer()) {
//                    player.convertOneOfHeldItem(new ItemStack(Items.SuspiciousPotion, 1, 0));
//                }
//
//                return true;
//            }
//        } else {
//            return false;
//        }
//    }
    @Redirect(method = "onItemRightClick(Lnet/minecraft/EntityPlayer;FZ)Z", at = @At(value = "NEW", target = "(Lnet/minecraft/Item;II)Lnet/minecraft/ItemStack;", ordinal = 0))
    private ItemStack ctorResult(Item item, int stack_size, int sub){
        return new ItemStack(Items.SuspiciousPotion, 1, 0);
    }
}
