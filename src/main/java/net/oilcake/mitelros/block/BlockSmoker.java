package net.oilcake.mitelros.block;

import net.minecraft.Block;
import net.minecraft.BlockBreakInfo;
import net.minecraft.BlockFurnace;
import net.minecraft.IconRegister;
import net.minecraft.Item;
import net.minecraft.Material;
import net.oilcake.mitelros.util.ExperimentalConfig;

public class BlockSmoker extends BlockFurnace {
   protected BlockSmoker(int par1, boolean par2) {
      super(par1, Material.stone, par2);
   }

   public void registerIcons(IconRegister mt) {
      this.furnaceIconFront = mt.registerIcon(this.isActive ? "smoker/smoker_front_on" : "smoker/smoker_front_off");
      this.furnaceIconTop = mt.registerIcon("smoker/smoker_top");
      this.blockIcon = mt.registerIcon("smoker/smoker_side");
   }

   public int getMaxHeatLevel() {
      return 2;
   }

   public int getIdleBlockID() {
      return Blocks.blockSmokerIdle.blockID;
   }

   public int getActiveBlockID() {
      return Blocks.blockSmokerBurning.blockID;
   }

   public int dropBlockAsEntityItem(BlockBreakInfo info) {
      if ((Boolean)ExperimentalConfig.TagConfig.TagBenchingV2.ConfigValue) {
         if (info.wasExploded()) {
            this.dropBlockAsEntityItem(info, Block.cobblestone.blockID);
            this.dropBlockAsEntityItem(info, Item.stick.itemID, 0, 1, 1.3F);
            return 0;
         } else {
            this.dropBlockAsEntityItem(info, Block.cobblestone.blockID, 0, 8, 1.0F);
            this.dropBlockAsEntityItem(info, Block.wood.blockID, 0, 4, 1.0F);
            return 0;
         }
      } else if (info.wasExploded()) {
         this.dropBlockAsEntityItem(info, Block.cobblestone.blockID);
         this.dropBlockAsEntityItem(info, Item.stick.itemID, 0, 1, 1.3F);
         return 0;
      } else {
         return super.dropBlockAsEntityItem(info);
      }
   }
}
