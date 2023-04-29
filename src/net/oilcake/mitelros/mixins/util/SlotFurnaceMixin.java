package net.oilcake.mitelros.mixins.util;

import net.minecraft.*;
import net.oilcake.mitelros.achivements.AchievementExtend;
import net.oilcake.mitelros.item.Items;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(vq.class)
public class SlotFurnaceMixin extends SlotCraftingBase {


    public SlotFurnaceMixin(EntityPlayer player, IInventory inventory, int slot_index, int display_x, int display_y) {
        super(player, inventory, slot_index, display_x, display_y);
    }

    @Overwrite
    protected void onCrafting(ItemStack item_stack) {
        if (!this.player.worldObj.isRemote) {
            int xp_reward = item_stack.getExperienceReward(this.quantity_taken);
            if (xp_reward > 0) {
                this.player.worldObj.spawnEntityInWorld(new EntityExperienceOrb(this.player.worldObj, this.player.posX, this.player.posY + 0.5, this.player.posZ + 0.5, xp_reward));
            }
        }

        super.onCrafting(item_stack);
        Item item = item_stack.getItem();
        if (item == Item.ingotIron || item == Item.ironNugget) {
            this.player.addStat(AchievementList.acquireIron, 1);
        } else if (item != Item.fishCooked && item != Item.fishLargeCooked) {
            if (item == Item.bread) {
                this.player.addStat(AchievementList.makeBread, 1);
            } else if (item == Item.ingotMithril || item == Item.mithrilNugget) {
                this.player.triggerAchievement(AchievementList.mithrilIngot);
            } else if (item == Item.ingotAdamantium || item == Item.adamantiumNugget) {
                this.player.triggerAchievement(AchievementList.adamantiumIngot);
            } else if (item == Items.UruIngot || item == Items.UruNugget) {
                this.player.triggerAchievement(AchievementExtend.neverEnds);
            }
        } else {
            this.player.addStat(AchievementList.cookFish, 1);
        }

    }
}
