package net.oilcake.mitelros.mixins.render;

import net.minecraft.Block;
import net.minecraft.BlockFlowerPot;
import net.minecraft.BlockPlant;
import net.minecraft.EntityRenderer;
import net.minecraft.IBlockAccess;
import net.minecraft.Icon;
import net.minecraft.RenderBlocks;
import net.minecraft.Tessellator;
import net.oilcake.mitelros.block.Blocks;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

@Mixin({RenderBlocks.class})
public class RenderBlocksMixin {
   @Shadow
   public IBlockAccess blockAccess;
   @Shadow
   private boolean renderAllFaces;

   @Overwrite
   private boolean renderBlockFlowerpot(BlockFlowerPot par1BlockFlowerPot, int par2, int par3, int par4) {
      this.renderStandardBlock(par1BlockFlowerPot, par2, par3, par4);
      Tessellator var5 = Tessellator.instance;
      var5.setBrightness(par1BlockFlowerPot.getMixedBrightnessForBlock(this.blockAccess, par2, par3, par4));
      float var6 = 1.0F;
      int var7 = par1BlockFlowerPot.colorMultiplier(this.blockAccess, par2, par3, par4);
      Icon var8 = this.getBlockIconFromSide(par1BlockFlowerPot, 0);
      float var9 = (float)(var7 >> 16 & 255) / 255.0F;
      float var10 = (float)(var7 >> 8 & 255) / 255.0F;
      float var11 = (float)(var7 & 255) / 255.0F;
      float var12;
      float var14;
      if (EntityRenderer.anaglyphEnable) {
         var12 = (var9 * 30.0F + var10 * 59.0F + var11 * 11.0F) / 100.0F;
         float var13 = (var9 * 30.0F + var10 * 70.0F) / 100.0F;
         var14 = (var9 * 30.0F + var11 * 70.0F) / 100.0F;
         var9 = var12;
         var10 = var13;
         var11 = var14;
      }

      var5.setColorOpaque_F(var6 * var9, var6 * var10, var6 * var11);
      var12 = 0.1865F;
      this.renderFaceXPos(par1BlockFlowerPot, (double)((float)par2 - 0.5F + var12), (double)par3, (double)par4, var8);
      this.renderFaceXNeg(par1BlockFlowerPot, (double)((float)par2 + 0.5F - var12), (double)par3, (double)par4, var8);
      this.renderFaceZPos(par1BlockFlowerPot, (double)par2, (double)par3, (double)((float)par4 - 0.5F + var12), var8);
      this.renderFaceZNeg(par1BlockFlowerPot, (double)par2, (double)par3, (double)((float)par4 + 0.5F - var12), var8);
      this.renderFaceYPos(par1BlockFlowerPot, (double)par2, (double)((float)par3 - 0.5F + var12 + 0.1875F), (double)par4, this.getBlockIcon(Block.dirt));
      int var19 = this.blockAccess.getBlockMetadata(par2, par3, par4);
      float var15;
      float var16;
      if (this.blockAccess.getBlockId(par2, par3, par4) == Block.flowerPotMulti.blockID) {
         if (var19 == 0) {
            return true;
         }

         var14 = 0.0F;
         var15 = 4.0F;
         var16 = 0.0F;
         var5.addTranslation(var14 / 16.0F, var15 / 16.0F, var16 / 16.0F);
         this.renderBlockByRenderType(Block.plantRed, par2, par3, par4);
         var5.addTranslation(-var14 / 16.0F, -var15 / 16.0F, -var16 / 16.0F);
      } else if (this.blockAccess.getBlockId(par2, par3, par4) == Blocks.flowerPotExtend.blockID) {
         if (var19 == 0) {
            return true;
         }

         var14 = 0.0F;
         var15 = 4.0F;
         var16 = 0.0F;
         var5.addTranslation(var14 / 16.0F, var15 / 16.0F, var16 / 16.0F);
         this.renderBlockByRenderType(Blocks.flowerextend, par2, par3, par4);
         var5.addTranslation(-var14 / 16.0F, -var15 / 16.0F, -var16 / 16.0F);
      } else if (var19 != 0) {
         var14 = 0.0F;
         var15 = 4.0F;
         var16 = 0.0F;
         BlockPlant var17 = null;
         switch (var19) {
            case 1:
               var17 = Block.plantRed;
               break;
            case 2:
               var17 = Block.plantYellow;
            case 3:
            case 4:
            case 5:
            case 6:
            case 9:
            case 10:
            case 11:
            case 12:
            default:
               break;
            case 7:
               var17 = Block.mushroomRed;
               break;
            case 8:
               var17 = Block.mushroomBrown;
               break;
            case 13:
               var17 = Blocks.flowerextend;
         }

         var5.addTranslation(var14 / 16.0F, var15 / 16.0F, var16 / 16.0F);
         if (var17 != null) {
            this.blockAccess.getWorld().setBlockMetadataWithNotify(par2, par3, par4, 0, 4);
            this.renderBlockByRenderType((Block)var17, par2, par3, par4);
            this.blockAccess.getWorld().setBlockMetadataWithNotify(par2, par3, par4, var19, 4);
         } else if (var19 == 9) {
            this.renderAllFaces = true;
            float var18 = 0.125F;
            this.setRenderBounds((double)(0.5F - var18), 0.0, (double)(0.5F - var18), (double)(0.5F + var18), 0.25, (double)(0.5F + var18));
            this.renderStandardBlock(Block.cactus, par2, par3, par4);
            this.setRenderBounds((double)(0.5F - var18), 0.25, (double)(0.5F - var18), (double)(0.5F + var18), 0.5, (double)(0.5F + var18));
            this.renderStandardBlock(Block.cactus, par2, par3, par4);
            this.setRenderBounds((double)(0.5F - var18), 0.5, (double)(0.5F - var18), (double)(0.5F + var18), 0.75, (double)(0.5F + var18));
            this.renderStandardBlock(Block.cactus, par2, par3, par4);
            this.renderAllFaces = false;
            this.setRenderBounds(0.0, 0.0, 0.0, 1.0, 1.0, 1.0);
         } else if (var19 == 3) {
            this.drawCrossedSquares(Block.sapling, 0, (double)par2, (double)par3, (double)par4, 0.75F);
         } else if (var19 == 5) {
            this.drawCrossedSquares(Block.sapling, 2, (double)par2, (double)par3, (double)par4, 0.75F);
         } else if (var19 == 4) {
            this.drawCrossedSquares(Block.sapling, 1, (double)par2, (double)par3, (double)par4, 0.75F);
         } else if (var19 == 6) {
            this.drawCrossedSquares(Block.sapling, 3, (double)par2, (double)par3, (double)par4, 0.75F);
         } else if (var19 == 11) {
            var7 = Block.tallGrass.colorMultiplier(this.blockAccess, par2, par3, par4);
            var9 = (float)(var7 >> 16 & 255) / 255.0F;
            var10 = (float)(var7 >> 8 & 255) / 255.0F;
            var11 = (float)(var7 & 255) / 255.0F;
            var5.setColorOpaque_F(var6 * var9, var6 * var10, var6 * var11);
            this.drawCrossedSquares(Block.tallGrass, 2, (double)par2, (double)par3, (double)par4, 0.75F);
         } else if (var19 == 10 || var19 == 12) {
            this.drawCrossedSquares(Block.deadBush, var19 == 12 ? 1 : 0, (double)par2, (double)par3, (double)par4, 0.75F);
         }

         var5.addTranslation(-var14 / 16.0F, -var15 / 16.0F, -var16 / 16.0F);
      }

      return true;
   }

   @Shadow
   public boolean renderBlockByRenderType(Block par1Block, int par2, int par3, int par4) {
      return false;
   }

   @Shadow
   public boolean renderStandardBlock(Block par1Block, int par2, int par3, int par4) {
      return false;
   }

   @Shadow
   public Icon getBlockIconFromSide(Block par1Block, int par2) {
      return null;
   }

   @Shadow
   public void renderFaceXPos(Block par1Block, double par2, double par4, double par6, Icon par8Icon) {
   }

   @Shadow
   public void renderFaceXNeg(Block par1Block, double par2, double par4, double par6, Icon par8Icon) {
   }

   @Shadow
   public void renderFaceZPos(Block par1Block, double par2, double par4, double par6, Icon par8Icon) {
   }

   @Shadow
   public void renderFaceZNeg(Block par1Block, double par2, double par4, double par6, Icon par8Icon) {
   }

   @Shadow
   public void renderFaceYPos(Block par1Block, double par2, double par4, double par6, Icon par8Icon) {
   }

   @Shadow
   public Icon getBlockIcon(Block par1Block) {
      return null;
   }

   @Shadow
   public void setRenderBounds(double par1, double par3, double par5, double par7, double par9, double par11) {
   }

   @Shadow
   public void drawCrossedSquares(Block par1Block, int par2, double par3, double par5, double par7, float par9) {
   }
}
