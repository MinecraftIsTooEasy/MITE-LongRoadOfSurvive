package net.oilcake.mitelros.block;

import java.util.List;
import net.minecraft.Block;
import net.minecraft.BlockConstants;
import net.minecraft.CreativeTabs;
import net.minecraft.EntityLivingBase;
import net.minecraft.Icon;
import net.minecraft.IconRegister;
import net.minecraft.Material;
import net.minecraft.World;

public class BlockEnchantEnhancer extends Block {
   private Icon TEXTURE_TOP;
   private Icon TEXTURE_BOTOTM;
   private Icon TEXTURE_SIDE;

   protected BlockEnchantEnhancer(int par1) {
      super(par1, Material.anvil, new BlockConstants());
      this.setCreativeTab(CreativeTabs.tabDecorations);
      this.setMaxStackSize(1);
      this.setLightOpacity(0);
      this.setLightValue(0.75F);
   }

   public Icon getIcon(int side, int metadata) {
      switch (side) {
         case 0:
            return this.TEXTURE_BOTOTM;
         case 1:
            return this.TEXTURE_TOP;
         case 2:
         case 3:
         case 4:
         case 5:
            return this.TEXTURE_SIDE;
         default:
            return super.getIcon(side, metadata);
      }
   }

   public void registerIcons(IconRegister mt) {
      this.TEXTURE_TOP = mt.registerIcon("enchant_enhancer/top");
      this.TEXTURE_BOTOTM = mt.registerIcon("enchant_enhancer/bottom");
      this.TEXTURE_SIDE = mt.registerIcon("enchant_enhancer/side");
   }

   public void getItemStacks(int id, CreativeTabs creative_tabs, List list) {
      super.getItemStacks(id, creative_tabs, list);
   }

   public boolean isPortable(World world, EntityLivingBase entity_living_base, int x, int y, int z) {
      return true;
   }
}
