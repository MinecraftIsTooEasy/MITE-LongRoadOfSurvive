package net.oilcake.mitelros.block;

import net.minecraft.Block;
import net.minecraft.BlockBreakInfo;
import net.minecraft.BlockConstants;
import net.minecraft.BlockSubtypes;
import net.minecraft.CreativeTabs;
import net.minecraft.Icon;
import net.minecraft.IconRegister;
import net.minecraft.Material;

public class BlockAzuriteLantern extends Block {
   private final BlockSubtypes subtypes = new BlockSubtypes(new String[]{"copper", "silver", "gold", "iron", "nickel", "ancient_metal", "mithril", "tungsten", "adamantium"});

   public BlockAzuriteLantern(int par1, Material material) {
      super(par1, material, (new BlockConstants()).setNeverHidesAdjacentFaces().setNotAlwaysLegal());
      this.setMaxStackSize(16);
      this.setLightValue(0.9375F);
      this.setBounds(true);
      this.setResistance(0.5F);
      this.setCreativeTab(CreativeTabs.tabDecorations);
   }

   private void setBounds(boolean for_all_threads) {
      float var1 = 0.375F;
      float var2 = var1 / 2.0F;
      this.setBlockBounds((double)(0.5F - var2), 0.0, (double)(0.5F - var2), (double)(0.5F + var2), 0.6875, (double)(0.5F + var2), for_all_threads);
   }

   public boolean canBeReplacedBy(int metadata, Block other_block, int other_block_metadata) {
      return false;
   }

   public int dropBlockAsEntityItem(BlockBreakInfo info) {
      return !info.wasExploded() && !info.wasCrushed() ? super.dropBlockAsEntityItem(info) : 0;
   }

   public boolean isStandardFormCube(boolean[] is_standard_form_cube, int metadata) {
      return false;
   }

   public void setBlockBoundsForItemRender(int item_damage) {
      this.setBounds(false);
   }

   public int getRenderBlockPass() {
      return 0;
   }

   public boolean isValidMetadata(int metadata) {
      return metadata >= 0 && metadata < 9;
   }

   public int getBlockSubtypeUnchecked(int metadata) {
      return metadata & 15;
   }

   public void registerIcons(IconRegister par1IconRegister) {
      this.subtypes.setIcons(this.registerIcons(par1IconRegister, this.getTextures(), "lantern/"));
   }

   public Icon getIcon(int side, int metadata) {
      return this.subtypes.getIcon(this.getBlockSubtype(metadata));
   }

   public String[] getTextures() {
      return this.subtypes.getTextures();
   }

   public String[] getNames() {
      return this.subtypes.getNames();
   }
}
