package net.oilcake.mitelros.block.observer;

import net.minecraft.Block;
import net.minecraft.BlockConstants;
import net.minecraft.BlockDirectionalWithTileEntity;
import net.minecraft.CreativeTabs;
import net.minecraft.EntityLivingBase;
import net.minecraft.EnumDirection;
import net.minecraft.Icon;
import net.minecraft.IconRegister;
import net.minecraft.Material;
import net.minecraft.StringHelper;
import net.minecraft.TileEntity;
import net.minecraft.World;

public class BlockObserver extends BlockDirectionalWithTileEntity {
   private Icon TEXTURE_TOP;
   private Icon TEXTURE_TOP_ON;
   private Icon TEXTURE_FRONT;
   private Icon TEXTURE_SIDE;

   public BlockObserver(int id, Material material) {
      super(id, material, new BlockConstants());
      this.setCreativeTab(CreativeTabs.tabRedstone);
      this.setMaxStackSize(4);
      this.setLightOpacity(0);
   }

   public boolean isPortable(World world, EntityLivingBase entity_living_base, int x, int y, int z) {
      return true;
   }

   public TileEntity createNewTileEntity(World world) {
      return new TileEntityObserver();
   }

   public boolean isActivated(int metadata) {
      return (metadata & 8) != 0;
   }

   public static void updateState(boolean par0, World par1World, int par2, int par3, int par4) {
      int var5 = par1World.getBlockMetadata(par2, par3, par4);
      TileEntity var6 = par1World.getBlockTileEntity(par2, par3, par4);
      BlockObserver observer = (BlockObserver)Block.blocksList[par1World.getBlockId(par2, par3, par4)];
      if (par0) {
         par1World.setBlock(par2, par3, par4, observer.blockID, (var5 & 7) + 8, 0);
      } else {
         par1World.setBlock(par2, par3, par4, observer.blockID, var5 & 7, 0);
      }

      if (var6 != null) {
         var6.validate();
         par1World.setBlockTileEntity(par2, par3, par4, var6);
      }

   }

   public Icon getIcon(int side, int metadata) {
      if (side == 0) {
         return this.isActivated(metadata) ? this.TEXTURE_TOP_ON : this.TEXTURE_TOP;
      } else if (side == 1) {
         return this.isActivated(metadata) ? this.TEXTURE_TOP_ON : this.TEXTURE_TOP;
      } else {
         return side != (metadata & 7) ? this.TEXTURE_SIDE : this.TEXTURE_FRONT;
      }
   }

   public void registerIcons(IconRegister mt) {
      this.TEXTURE_TOP = mt.registerIcon("resonance_generator/top");
      this.TEXTURE_TOP_ON = mt.registerIcon("resonance_generator/top_on");
      this.TEXTURE_FRONT = mt.registerIcon("resonance_generator/front");
      this.TEXTURE_SIDE = mt.registerIcon("resonance_generator/side");
   }

   public final EnumDirection getDirectionFacing(int metadata) {
      return this.getDirectionFacingStandard6(metadata & 7, false);
   }

   public int getMetadataForDirectionFacing(int metadata, EnumDirection direction) {
      int facing = direction == EnumDirection.NORTH ? 2 : (direction == EnumDirection.SOUTH ? 3 : (direction == EnumDirection.WEST ? 4 : (direction == EnumDirection.EAST ? 5 : -1)));
      if (this.isActivated(metadata)) {
         facing += 8;
      }

      return facing;
   }

   public String getMetadataNotes() {
      String[] array = new String[4];

      for(int i = 0; i < array.length; ++i) {
         array[i] = i + 2 + "=" + this.getDirectionFacing(i + 2).getDescriptor(true);
      }

      return StringHelper.implode(array, ", ", true, false) + ", bit 8 set if activated.";
   }

   public boolean isValidMetadata(int metadata) {
      return metadata > 1 && metadata < 6 || metadata > 9 && metadata < 14;
   }
}
