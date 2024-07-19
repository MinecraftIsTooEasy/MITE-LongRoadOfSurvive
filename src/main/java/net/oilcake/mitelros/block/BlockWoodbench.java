package net.oilcake.mitelros.block;

import java.util.Random;
import net.minecraft.Block;
import net.minecraft.BlockBreakInfo;
import net.minecraft.BlockHardness;
import net.minecraft.BlockWorkbench;
import net.minecraft.CreativeTabs;
import net.minecraft.EntityLivingBase;
import net.minecraft.EntityPlayer;
import net.minecraft.EnumFace;
import net.minecraft.Icon;
import net.minecraft.IconRegister;
import net.minecraft.Item;
import net.minecraft.ItemStack;
import net.minecraft.Material;
import net.minecraft.StringHelper;
import net.minecraft.World;
import net.oilcake.mitelros.util.ExperimentalConfig;

public class BlockWoodbench extends BlockWorkbench {
   private Icon workbenchIconTop;
   private Icon icon_flint_top;
   private Icon icon_obsidian_top;
   protected Icon[] front_icons = new Icon[8];
   protected Icon[] side_icons = new Icon[8];
   public static final Material[] tool_materials;
   private final Random random;

   protected BlockWoodbench(int par1) {
      super(par1);
      this.setHardness(BlockHardness.workbench);
      this.setCreativeTab(CreativeTabs.tabDecorations);
      this.random = new Random();
   }

   public String getMetadataNotes() {
      String[] array = new String[this.getNumSubBlocks()];

      for(int i = 0; i < array.length; ++i) {
         array[i] = i + "=" + getToolMaterial(i).getCapitalizedName() + " Tools";
      }

      return StringHelper.implode(array, ", ", true, true);
   }

   public Icon getIcon(int side, int metadata) {
      if (metadata < 4) {
         return side == 1 ? this.icon_flint_top : Block.wood.getIcon(side, metadata);
      } else {
         return side == 1 ? this.icon_obsidian_top : Block.wood.getIcon(side, metadata - 4);
      }
   }

   public static Material getToolMaterial(int metadata) {
      return tool_materials[metadata / 4];
   }

   public int getBlockSubtypeUnchecked(int metadata) {
      return metadata;
   }

   public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, EnumFace face, float offset_x, float offset_y, float offset_z) {
      if (player.onServer() && world.isAirOrPassableBlock(x, y + 1, z, false)) {
         Block block_above = world.getBlock(x, y + 1, z);
         if (block_above == null || !block_above.hidesAdjacentSide(world, x, y + 1, z, this, 1)) {
            player.displayGUIWorkbench(x, y, z);
         }
      }

      return true;
   }

   public boolean playerSwingsOnBlockActivated(boolean empty_handed) {
      return false;
   }

   public boolean isPortable(World world, EntityLivingBase entity_living_base, int x, int y, int z) {
      return false;
   }

   public int dropBlockAsEntityItem(BlockBreakInfo info) {
      if (!(Boolean)ExperimentalConfig.TagConfig.TagBenchingV2.ConfigValue && !info.wasExploded()) {
         return super.dropBlockAsEntityItem(info);
      } else {
         int metadata = info.getMetadata();
         if (info.wasExploded()) {
            int quantity_drops = 2 + (int)(this.random.nextFloat() * 4.0F);
            if (info.getMetadata() < 4) {
               this.dropBlockAsEntityItem(info, Item.chipFlint.itemID, 0, quantity_drops / 2, 1.0F);
            } else if (this.random.nextInt(16) == 0) {
               this.dropBlockAsEntityItem(info, Block.obsidian.blockID);
            } else {
               this.dropBlockAsEntityItem(info, Item.shardObsidian.itemID, 0, quantity_drops, 1.0F);
            }
         } else if (info.getMetadata() < 4) {
            this.dropBlockAsEntityItem(info, Item.knifeFlint.itemID);
            this.dropBlockAsEntityItem(info, Block.wood.blockID, info.getMetadata(), 1, 1.0F);
         } else {
            this.dropBlockAsEntityItem(info, Item.knifeObsidian.itemID);
            this.dropBlockAsEntityItem(info, Block.wood.blockID, info.getMetadata() - 13, 1, 1.0F);
         }

         return 0;
      }
   }

   protected void dropXpOnBlockBreak(World par1World, int par2, int par3, int par4, int par5) {
   }

   public static ItemStack getBlockComponent(int metadata) {
      return new ItemStack(Block.wood, 1, metadata % 4);
   }

   public boolean isValidMetadata(int metadata) {
      return metadata >= 0 && metadata < tool_materials.length * 4;
   }

   public void registerIcons(IconRegister par1IconRegister) {
      this.icon_flint_top = par1IconRegister.registerIcon("crafting_table/flint/top");
      this.icon_obsidian_top = par1IconRegister.registerIcon("crafting_table/obsidian/top");
      this.workbenchIconTop = par1IconRegister.registerIcon("crafting_table_top");
   }

   static {
      tool_materials = new Material[]{Material.flint, Material.obsidian};
   }
}
