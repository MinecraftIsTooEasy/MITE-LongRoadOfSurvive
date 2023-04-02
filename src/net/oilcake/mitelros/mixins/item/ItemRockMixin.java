package net.oilcake.mitelros.mixins.item;


import net.minecraft.*;
import net.oilcake.mitelros.block.Blocks;
import net.oilcake.mitelros.item.Items;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

import static net.minecraft.Item.*;

@Mixin(ItemRock.class)
public class ItemRockMixin {
    @Overwrite
    public static int getExperienceValueWhenSacrificed(ItemStack item_stack)
    {
        Item item = item_stack.getItem();
        return item == Items.lapis ? 25 : (item == netherQuartz ? 50 : (item == emerald ? 250 : (item == diamond ? 500 : 0)));
    }
    @Overwrite
    public static boolean onItemRightClick(EntityPlayer player, ItemStack item_stack, float partial_tick, boolean ctrl_is_down) {
        RaycastCollision rc = player.getSelectedObject(partial_tick, false);
        if(rc!=null){
            if(rc.isBlock()){
                if(rc.getBlockHitID()== Blocks.blockEnchantReserver.blockID){
                    int xp_value = getExperienceValueWhenSacrificed(item_stack);
                    if (xp_value < 1) {
                        return false;
                    } else {
                        if (player.onServer()) {
                            player.causeBreakingItemEffect(item_stack.getItem(), player.inventory.currentItem);
                            player.convertOneOfHeldItem((ItemStack)null);
                            player.addExperience(xp_value);
                        } else {
                            player.bobItem();
                        }
                        return true;
                    }
                }
            }
            return false;
        }
        else {
            return false;
        }
    }
}
