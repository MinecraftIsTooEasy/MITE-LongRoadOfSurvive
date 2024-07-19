package net.oilcake.mitelros.block;

import net.minecraft.Block;
import net.minecraft.BlockBreakInfo;
import net.minecraft.BlockFurnace;
import net.minecraft.IconRegister;
import net.minecraft.Item;
import net.minecraft.Material;
import net.minecraft.World;
import net.oilcake.mitelros.util.ExperimentalConfig;

public class BlockBlastFurnace extends BlockFurnace {
   private Material material;

   protected BlockBlastFurnace(int par1, Material par2, boolean par3) {
      super(par1, par2, par3);
      this.material = par2;
   }

   protected void dropXpOnBlockBreak(World par1World, int par2, int par3, int par4, int par5) {
   }

   public void registerIcons(IconRegister mt) {
      this.blockIcon = mt.registerIcon("blastfurnace/" + this.material + "/side");
      this.furnaceIconFront = mt.registerIcon(this.isActive ? "blastfurnace/" + this.material + "/on" : "blastfurnace/" + this.material + "/off");
      this.furnaceIconTop = mt.registerIcon("blastfurnace/" + this.material + "/top");
   }

   public int getIdleBlockID() {
      return this.material == Material.stone ? Blocks.blastFurnaceStoneIdle.blockID : (this.material == Material.obsidian ? Blocks.blastFurnaceObsidianIdle.blockID : (this.material == Material.netherrack ? Blocks.blastFurnaceNetherrackIdle.blockID : 0));
   }

   public int getActiveBlockID() {
      return this.material == Material.stone ? Blocks.blastFurnaceStoneBurning.blockID : (this.material == Material.obsidian ? Blocks.blastFurnaceObsidianBurning.blockID : (this.material == Material.netherrack ? Blocks.blastFurnaceNetherrackBurning.blockID : 0));
   }

   public int getMaxHeatLevel() {
      return this.material == Material.stone ? 2 : (this.material == Material.obsidian ? 3 : (this.material == Material.netherrack ? 4 : 0));
   }

   public int dropBlockAsEntityItem(BlockBreakInfo info) {
      Block furnace_block = Block.getBlock(this.getIdleBlockID());
      if ((Boolean)ExperimentalConfig.TagConfig.TagBenchingV2.ConfigValue) {
         if (!info.wasExploded()) {
            if (furnace_block == Blocks.blastFurnaceNetherrackIdle) {
               this.dropBlockAsEntityItem(info, Block.netherrack.blockID, 0, 8, 1.0F);
               this.dropBlockAsEntityItem(info, Item.ingotAdamantium.itemID, 0, 3, 1.0F);
            } else if (furnace_block == Blocks.blastFurnaceObsidianIdle) {
               this.dropBlockAsEntityItem(info, Block.obsidian.blockID, 0, 8, 1.0F);
               this.dropBlockAsEntityItem(info, Item.ingotMithril.itemID, 0, 3, 1.0F);
            } else {
               if (furnace_block != Blocks.blastFurnaceStoneIdle) {
                  return 0;
               }

               this.dropBlockAsEntityItem(info, Block.cobblestone.blockID, 0, 8, 1.0F);
               this.dropBlockAsEntityItem(info, Item.ingotIron.itemID, 0, 3, 1.0F);
            }
         } else if (furnace_block == Blocks.blastFurnaceNetherrackIdle) {
            this.dropBlockAsEntityItem(info, Block.netherrack.blockID, 0, 1, 1.3F);
            this.dropBlockAsEntityItem(info, Item.ingotAdamantium.itemID, 0, 1, 1.3F);
         } else if (furnace_block == Blocks.blastFurnaceObsidianIdle) {
            this.dropBlockAsEntityItem(info, Block.obsidian.blockID, 0, 1, 1.0F);
            this.dropBlockAsEntityItem(info, Item.shardObsidian.itemID, 0, 2, 1.3F);
            this.dropBlockAsEntityItem(info, Item.ingotMithril.itemID, 0, 1, 1.3F);
            this.dropBlockAsEntityItem(info, Item.mithrilNugget.itemID, 0, 2, 1.3F);
         } else {
            if (furnace_block != Blocks.blastFurnaceStoneIdle) {
               return 0;
            }

            this.dropBlockAsEntityItem(info, Block.cobblestone.blockID, 0, 1, 1.3F);
            this.dropBlockAsEntityItem(info, Item.ingotIron.itemID, 0, 1, 0.5F);
            this.dropBlockAsEntityItem(info, Item.ironNugget.itemID, 0, 2, 1.3F);
         }

         return 0;
      } else if (!info.wasExploded()) {
         return super.dropBlockAsEntityItem(info);
      } else {
         if (furnace_block == Blocks.blastFurnaceNetherrackIdle) {
            this.dropBlockAsEntityItem(info, Block.netherrack.blockID, 0, 1, 1.3F);
            this.dropBlockAsEntityItem(info, Item.ingotAdamantium.itemID, 0, 1, 1.3F);
         } else if (furnace_block == Blocks.blastFurnaceObsidianIdle) {
            this.dropBlockAsEntityItem(info, Block.obsidian.blockID, 0, 1, 1.0F);
            this.dropBlockAsEntityItem(info, Item.shardObsidian.itemID, 0, 2, 1.3F);
            this.dropBlockAsEntityItem(info, Item.ingotMithril.itemID, 0, 1, 1.3F);
            this.dropBlockAsEntityItem(info, Item.mithrilNugget.itemID, 0, 2, 1.3F);
         } else {
            if (furnace_block != Blocks.blastFurnaceStoneIdle) {
               return 0;
            }

            this.dropBlockAsEntityItem(info, Block.cobblestone.blockID, 0, 1, 1.3F);
            this.dropBlockAsEntityItem(info, Item.ingotIron.itemID, 0, 1, 0.5F);
            this.dropBlockAsEntityItem(info, Item.ironNugget.itemID, 0, 2, 1.3F);
         }

         return 0;
      }
   }
}
