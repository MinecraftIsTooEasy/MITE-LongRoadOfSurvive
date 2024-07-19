package net.oilcake.mitelros.block.receiver;

import net.minecraft.BlockBreakInfo;
import net.minecraft.BlockConstants;
import net.minecraft.BlockContainer;
import net.minecraft.CreativeTabs;
import net.minecraft.EntityLivingBase;
import net.minecraft.IBlockAccess;
import net.minecraft.Icon;
import net.minecraft.IconRegister;
import net.minecraft.ItemStack;
import net.minecraft.Material;
import net.minecraft.TileEntity;
import net.minecraft.World;
import net.oilcake.mitelros.block.Blocks;

public class BlockReceiver extends BlockContainer {
   protected Icon SIDE_ON;
   protected Icon SIDE_OFF;

   public void registerIcons(IconRegister mt) {
      this.SIDE_ON = mt.registerIcon("resonance_converter/side_on");
      this.SIDE_OFF = mt.registerIcon("resonance_converter/side");
   }

   public BlockReceiver(int par1) {
      super(par1, Material.stone, new BlockConstants());
      this.setCreativeTab(CreativeTabs.tabRedstone);
      this.setMaxStackSize(4);
      this.setLightOpacity(0);
   }

   public boolean isValidMetadata(int metadata) {
      return metadata >= 0 && metadata < 16;
   }

   public Icon getIcon(int side, int metadata) {
      return metadata > 0 ? this.SIDE_ON : this.SIDE_OFF;
   }

   public boolean isPortable(World world, EntityLivingBase entity_living_base, int x, int y, int z) {
      return true;
   }

   public boolean canProvidePower() {
      return true;
   }

   public int isProvidingWeakPower(IBlockAccess par1IBlockAccess, int par2, int par3, int par4, int par5) {
      return par1IBlockAccess.getBlockMetadata(par2, par3, par4);
   }

   public int dropBlockAsEntityItem(BlockBreakInfo info) {
      this.dropBlockAsEntityItem(info, new ItemStack(Blocks.blockReceiver.blockID, 1));
      return 0;
   }

   public String getMetadataNotes() {
      return "All bits used to track signal level.";
   }

   public void updateSignalLevel(World par1World, int par2, int par3, int par4) {
      for(int dx = -2; dx <= 2; ++dx) {
         for(int dy = -2; dy <= 2; ++dy) {
            for(int dz = -2; dz <= 2; ++dz) {
               if (par1World.getBlockId(par2 + dx, par3 + dy, par4 + dz) == Blocks.blockObserver.blockID) {
                  if ((par1World.getBlockMetadata(par2 + dx, par3 + dy, par4 + dz) & 8) != 0) {
                     par1World.setBlockMetadataWithNotify(par2, par3, par4, 9 - Math.abs(dx) - Math.abs(dy) - Math.abs(dz), 3);
                  } else {
                     par1World.setBlockMetadataWithNotify(par2, par3, par4, 0, 3);
                  }
               }
            }
         }
      }

   }

   public TileEntity createNewTileEntity(World par1World) {
      return new TileEntityReceiver();
   }
}
