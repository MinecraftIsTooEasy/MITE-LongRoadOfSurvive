package net.oilcake.mitelros.mixins.util;

import java.util.LinkedList;
import net.minecraft.AchievementList;
import net.minecraft.Block;
import net.minecraft.BlockBreakInfo;
import net.minecraft.BlockTorch;
import net.minecraft.EnchantmentHelper;
import net.minecraft.EnumDirection;
import net.minecraft.EnumGameType;
import net.minecraft.Item;
import net.minecraft.ItemInWorldManager;
import net.minecraft.ItemStack;
import net.minecraft.ItemSword;
import net.minecraft.Minecraft;
import net.minecraft.Packet53BlockChange;
import net.minecraft.RenderGlobal;
import net.minecraft.ServerPlayer;
import net.minecraft.StatList;
import net.minecraft.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

@Mixin({ItemInWorldManager.class})
public class ItemInWorldManagerMixin {
   @Shadow
   public World theWorld;
   @Shadow
   public ServerPlayer thisPlayerMP;
   @Shadow
   private EnumGameType gameType;
   @Shadow
   private boolean tree_felling_in_progress;

   @Shadow
   private boolean removeBlock(int par1, int par2, int par3) {
      return false;
   }

   @Shadow
   public boolean isCreative() {
      return false;
   }

   @Overwrite
   public boolean tryHarvestBlock(int x, int y, int z) {
      if (this.theWorld.isRemote) {
         Minecraft.setErrorMessage("tryHarvestBlock: called on client?");
      }

      if (this.gameType.isAdventure() && !this.thisPlayerMP.isCurrentToolAdventureModeExempt(x, y, z)) {
         return false;
      } else if (this.gameType.isCreative() && this.thisPlayerMP.getHeldItemStack() != null && this.thisPlayerMP.getHeldItemStack().getItem() instanceof ItemSword) {
         return false;
      } else {
         Block block = this.theWorld.getBlock(x, y, z);
         if (block == null) {
            return false;
         } else {
            block.onBlockAboutToBeBroken((new BlockBreakInfo(this.theWorld, x, y, z)).setHarvestedBy(this.thisPlayerMP));
            if (this.theWorld.getBlock(x, y, z) == null) {
               return false;
            } else {
               BlockBreakInfo block_break_info = (new BlockBreakInfo(this.theWorld, x, y, z)).setHarvestedBy(this.thisPlayerMP);
               block = block_break_info.block;
               if (block == null) {
                  return false;
               } else {
                  boolean player_can_damage_block = this.thisPlayerMP.getCurrentPlayerStrVsBlock(x, y, z, true) > 0.0F;
                  int data = block_break_info.block_id + (block_break_info.getMetadata() << 12);
                  if (block_break_info.wasSilkHarvested()) {
                     data |= RenderGlobal.SFX_2001_WAS_SILK_HARVESTED;
                  }

                  this.theWorld.playAuxSFXAtEntity(this.thisPlayerMP, 2001, x, y, z, data);
                  boolean block_was_removed = this.removeBlock(x, y, z);
                  if (this.isCreative()) {
                     this.thisPlayerMP.playerNetServerHandler.sendPacketToPlayer(new Packet53BlockChange(x, y, z, this.theWorld));
                  } else {
                     ItemStack held_item_stack = this.thisPlayerMP.getHeldItemStack();
                     if (held_item_stack != null) {
                        Item item = held_item_stack.getItem();
                        if (item.onBlockDestroyed(block_break_info)) {
                           this.thisPlayerMP.addStat(StatList.objectUseStats[item.itemID], 1);
                        }
                     }

                     if (block_was_removed && player_can_damage_block) {
                        this.thisPlayerMP.addStat(StatList.mineBlockStatArray[block.blockID], 1);
                        if (block_break_info.wasSilkHarvested()) {
                           block.dropBlockAsItself(block_break_info);
                        } else {
                           block.dropBlockAsEntityItem(block_break_info);
                        }

                        if ((block == Block.wood || block == Block.mushroomCapRed || block == Block.mushroomCapBrown) && !this.tree_felling_in_progress) {
                           int felling = EnchantmentHelper.getTreeFellingModifier(this.thisPlayerMP) * 4;
                           this.tree_felling_in_progress = true;
                           this.fellTree(this.theWorld, x, y, z, felling);
                           this.tree_felling_in_progress = false;
                        }
                     }
                  }

                  int i;
                  if (block_was_removed && !(block instanceof BlockTorch)) {
                     i = this.theWorld.getBlockId(x, y + 1, z);
                     if (Block.blocksList[i] != null) {
                        Block.blocksList[i].onUnderminedByPlayer(this.theWorld, this.thisPlayerMP, x, y + 1, z);
                     }

                     int[] dx = new int[]{0, 1, 0, -1};
                     int[] dz = new int[]{-1, 0, 1, 0};

                     for(int n = 0; n < dx.length; ++n) {
                        int block_id2 = this.theWorld.getBlockId(x + dx[n], y, z + dz[n]);
                        if (Block.blocksList[block_id2] != null) {
                           Block.blocksList[block_id2].onUnderminedByPlayer(this.theWorld, this.thisPlayerMP, x + dx[n], y, z + dz[n]);
                        }
                     }
                  }

                  if (block_was_removed && this.theWorld.isUnderworld() && y < 6) {
                     for(i = 0; i < EnumDirection.values().length; ++i) {
                        EnumDirection direction = EnumDirection.values()[i];
                        Block neighbor = this.theWorld.getNeighborBlock(x, y, z, direction);
                        if (neighbor == Block.mantleOrCore) {
                           this.thisPlayerMP.triggerAchievement(AchievementList.portalToNether);
                           break;
                        }
                     }
                  }

                  return block_was_removed;
               }
            }
         }
      }
   }

   public void fellTree(World world, int pos_x, int pos_y, int pos_z, int chopping) {
      boolean hasNest = true;
      LinkedList hint = new LinkedList();
      hint.addLast(new int[]{0, 0, 0});

      while(hasNest) {
         int[] start = (int[])hint.getFirst();

         for(int y = start[1] + 1; y >= start[1]; --y) {
            for(int x = start[0] - 1; x <= start[0] + 1; ++x) {
               for(int z = start[2] - 1; z <= start[2] + 1; ++z) {
                  int[] next = new int[]{x, y, z};
                  if (!hint.contains(next) && chopping >= 1 && world.getBlockId(pos_x + x, pos_y + y, pos_z + z) == Block.wood.blockID) {
                     hint.addLast(next);
                     this.tryHarvestBlock(pos_x + x, pos_y + y, pos_z + z);
                     chopping -= 4;
                  }

                  if (!hint.contains(next) && chopping >= 1 && world.getBlockId(pos_x + x, pos_y + y, pos_z + z) == Block.mushroomCapBrown.blockID) {
                     hint.addLast(next);
                     this.tryHarvestBlock(pos_x + x, pos_y + y, pos_z + z);
                     --chopping;
                  }

                  if (!hint.contains(next) && chopping >= 1 && world.getBlockId(pos_x + x, pos_y + y, pos_z + z) == Block.mushroomCapRed.blockID) {
                     hint.addLast(next);
                     this.tryHarvestBlock(pos_x + x, pos_y + y, pos_z + z);
                     --chopping;
                  }
               }
            }
         }

         hint.removeFirst();
         if (hint.isEmpty()) {
            hasNest = false;
         }
      }

   }
}
