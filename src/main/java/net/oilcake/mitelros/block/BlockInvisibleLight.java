package net.oilcake.mitelros.block;

import java.util.Random;
import net.minecraft.Block;
import net.minecraft.BlockBreakInfo;
import net.minecraft.BlockConstants;
import net.minecraft.Icon;
import net.minecraft.IconRegister;
import net.minecraft.Material;
import net.minecraft.World;

public class BlockInvisibleLight extends Block {
   protected BlockInvisibleLight(int id) {
      super(id, Material.air, (new BlockConstants()).setNeverHidesAdjacentFaces().setNotAlwaysLegal());
      this.disableStats();
   }

   public String getMetadataNotes() {
      return null;
   }

   public boolean canCollideCheck(int par1, boolean par2) {
      return false;
   }

   public boolean isValidMetadata(int metadata) {
      return metadata == 0;
   }

   public void onBlockAdded(World par1World, int par2, int par3, int par4) {
   }

   public boolean updateTick(World par1World, int par2, int par3, int par4, Random par5Random) {
      return true;
   }

   public void registerIcons(IconRegister par1IconRegister) {
      this.blockIcon = par1IconRegister.registerIcon(this.getTextureName());
   }

   public boolean isLegalAt(World world, int x, int y, int z, int metadata) {
      return false;
   }

   public boolean isLegalOn(int metadata, Block block_below, int block_below_metadata) {
      return false;
   }

   public Icon c(int par1) {
      return this.blockIcon;
   }

   public Icon getIcon(int par1, int par2) {
      return this.blockIcon;
   }

   public boolean canBeCarried() {
      return false;
   }

   public int dropBlockAsEntityItem(BlockBreakInfo info) {
      return 0;
   }

   public boolean isSolid(boolean[] is_solid, int metadata) {
      return false;
   }

   public boolean isStandardFormCube(boolean[] is_standard_form_cube, int metadata) {
      return false;
   }

   public boolean canBeReplacedBy(int metadata, Block other_block, int other_block_metadata) {
      return true;
   }
}
