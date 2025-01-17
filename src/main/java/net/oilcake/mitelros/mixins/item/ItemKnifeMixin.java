package net.oilcake.mitelros.mixins.item;

import net.minecraft.AchievementList;
import net.minecraft.Block;
import net.minecraft.EntityPlayer;
import net.minecraft.ItemDagger;
import net.minecraft.ItemKnife;
import net.minecraft.ItemStack;
import net.minecraft.Material;
import net.minecraft.RaycastCollision;
import net.oilcake.mitelros.block.Blocks;
import org.spongepowered.asm.mixin.Mixin;

@Mixin({ItemKnife.class})
public class ItemKnifeMixin extends ItemDagger {
   protected ItemKnifeMixin(int par1, Material material) {
      super(par1, material);
   }

   public boolean onItemRightClick(EntityPlayer player, float partial_tick, boolean ctrl_is_down) {
      RaycastCollision rc = player.getSelectedObject(partial_tick, false);
      if (rc == null) {
         return false;
      } else if (!rc.isBlock()) {
         return false;
      } else if (rc.getBlockHit() == Block.wood && this.getToolMaterial() == Material.flint && player.getHeldItemStack().getItemDamage() == 0) {
         if (player.onServer()) {
            rc.world.setBlock(rc.block_hit_x, rc.block_hit_y, rc.block_hit_z, Blocks.woodbench.blockID, rc.world.getBlockMetadata(rc.block_hit_x, rc.block_hit_y, rc.block_hit_z) & 3, 2);
         }

         if (player.onClient()) {
            player.swingArm();
         } else {
            rc.world.playSoundAtEntity(player, "dig.wood", 1.0F, itemRand.nextFloat() * 0.4F + 0.8F);
            player.setHeldItemStack((ItemStack)null);
            player.triggerAchievement(AchievementList.buildWorkBench);
         }

         return true;
      } else if (rc.getBlockHit() == Block.wood && this.getToolMaterial() == Material.obsidian && player.getHeldItemStack().getItemDamage() == 0) {
         if (player.onServer()) {
            rc.world.setBlock(rc.block_hit_x, rc.block_hit_y, rc.block_hit_z, Blocks.woodbench.blockID, (rc.world.getBlockMetadata(rc.block_hit_x, rc.block_hit_y, rc.block_hit_z) & 3) + 4, 2);
         }

         if (player.onClient()) {
            player.swingArm();
         } else {
            rc.world.playSoundAtEntity(player, "dig.wood", 1.0F, itemRand.nextFloat() * 0.4F + 0.8F);
            player.setHeldItemStack((ItemStack)null);
            player.triggerAchievement(AchievementList.buildWorkBench);
         }

         return true;
      } else {
         return rc.isNeighborAirBlock() && rc.canPlayerEditNeighborOfBlockHit(player, player.getHeldItemStack()) ? false : false;
      }
   }
}
