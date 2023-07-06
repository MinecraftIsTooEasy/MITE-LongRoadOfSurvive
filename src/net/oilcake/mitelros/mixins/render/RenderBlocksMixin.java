package net.oilcake.mitelros.mixins.render;

import net.minecraft.*;
import net.oilcake.mitelros.block.Blocks;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(bfr.class)
public class RenderBlocksMixin {
    @Shadow
    public IBlockAccess a;
    @Shadow
    private boolean f;
    @Overwrite
    private boolean a(BlockFlowerPot par1BlockFlowerPot, int par2, int par3, int par4) {
        this.p(par1BlockFlowerPot, par2, par3, par4);
        bfq var5 = bfq.a;
        var5.c(par1BlockFlowerPot.e(this.a, par2, par3, par4));
        float var6 = 1.0F;
        int var7 = par1BlockFlowerPot.c(this.a, par2, par3, par4);
        IIcon var8 = this.a(par1BlockFlowerPot, 0);
        float var9 = (float)(var7 >> 16 & 255) / 255.0F;
        float var10 = (float)(var7 >> 8 & 255) / 255.0F;
        float var11 = (float)(var7 & 255) / 255.0F;
        float var12;
        float var14;
        if (EntityRenderer.a) {
            var12 = (var9 * 30.0F + var10 * 59.0F + var11 * 11.0F) / 100.0F;
            float var13 = (var9 * 30.0F + var10 * 70.0F) / 100.0F;
            var14 = (var9 * 30.0F + var11 * 70.0F) / 100.0F;
            var9 = var12;
            var10 = var13;
            var11 = var14;
        }

        var5.a(var6 * var9, var6 * var10, var6 * var11);
        var12 = 0.1865F;
        this.f(par1BlockFlowerPot, (double)((float)par2 - 0.5F + var12), (double)par3, (double)par4, var8);
        this.e(par1BlockFlowerPot, (double)((float)par2 + 0.5F - var12), (double)par3, (double)par4, var8);
        this.d(par1BlockFlowerPot, (double)par2, (double)par3, (double)((float)par4 - 0.5F + var12), var8);
        this.c(par1BlockFlowerPot, (double)par2, (double)par3, (double)((float)par4 + 0.5F - var12), var8);
        this.b(par1BlockFlowerPot, (double)par2, (double)((float)par3 - 0.5F + var12 + 0.1875F), (double)par4, this.b(Block.dirt));
        int var19 = this.a.getBlockMetadata(par2, par3, par4);
        float var15;
        float var16;
        if (this.a.getBlockId(par2, par3, par4) == Block.flowerPotMulti.blockID) {
            if (var19 == 0) {
                return true;
            }

            var14 = 0.0F;
            var15 = 4.0F;
            var16 = 0.0F;
            var5.c(var14 / 16.0F, var15 / 16.0F, var16 / 16.0F);
            this.b(Block.plantRed, par2, par3, par4);
            var5.c(-var14 / 16.0F, -var15 / 16.0F, -var16 / 16.0F);
        } else if (this.a.getBlockId(par2, par3, par4) == Blocks.flowerPotExtend.blockID) {
            if (var19 == 0) {
                return true;
            }

            var14 = 0.0F;
            var15 = 4.0F;
            var16 = 0.0F;
            var5.c(var14 / 16.0F, var15 / 16.0F, var16 / 16.0F);
            this.b(Blocks.flowerextend, par2, par3, par4);
            var5.c(-var14 / 16.0F, -var15 / 16.0F, -var16 / 16.0F);
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
                    break;
                case 3:
                case 4:
                case 5:
                case 6:
                default:
                    break;
                case 13:
                    var17 = Blocks.flowerextend;
                    break;
                case 7:
                    var17 = Block.mushroomRed;
                    break;
                case 8:
                    var17 = Block.mushroomBrown;
                    break;
            }

            var5.c(var14 / 16.0F, var15 / 16.0F, var16 / 16.0F);
            if (var17 != null) {
                this.a.getWorld().setBlockMetadata(par2, par3, par4, 0, 4);
                this.b((Block)var17, par2, par3, par4);
                this.a.getWorld().setBlockMetadata(par2, par3, par4, var19, 4);
            } else if (var19 == 9) {
                this.f = true;
                float var18 = 0.125F;
                this.a((double)(0.5F - var18), 0.0, (double)(0.5F - var18), (double)(0.5F + var18), 0.25, (double)(0.5F + var18));
                this.p(Block.cactus, par2, par3, par4);
                this.a((double)(0.5F - var18), 0.25, (double)(0.5F - var18), (double)(0.5F + var18), 0.5, (double)(0.5F + var18));
                this.p(Block.cactus, par2, par3, par4);
                this.a((double)(0.5F - var18), 0.5, (double)(0.5F - var18), (double)(0.5F + var18), 0.75, (double)(0.5F + var18));
                this.p(Block.cactus, par2, par3, par4);
                this.f = false;
                this.a(0.0, 0.0, 0.0, 1.0, 1.0, 1.0);
            } else if (var19 == 3) {
                this.a(Block.sapling, 0, (double)par2, (double)par3, (double)par4, 0.75F);
            } else if (var19 == 5) {
                this.a(Block.sapling, 2, (double)par2, (double)par3, (double)par4, 0.75F);
            } else if (var19 == 4) {
                this.a(Block.sapling, 1, (double)par2, (double)par3, (double)par4, 0.75F);
            } else if (var19 == 6) {
                this.a(Block.sapling, 3, (double)par2, (double)par3, (double)par4, 0.75F);
            } else if (var19 == 11) {
                var7 = Block.tallGrass.c(this.a, par2, par3, par4);
                var9 = (float)(var7 >> 16 & 255) / 255.0F;
                var10 = (float)(var7 >> 8 & 255) / 255.0F;
                var11 = (float)(var7 & 255) / 255.0F;
                var5.a(var6 * var9, var6 * var10, var6 * var11);
                this.a(Block.tallGrass, 2, (double)par2, (double)par3, (double)par4, 0.75F);
            } else if (var19 == 10 || var19 == 12) {
                this.a(Block.deadBush, var19 == 12 ? 1 : 0, (double)par2, (double)par3, (double)par4, 0.75F);
            }

            var5.c(-var14 / 16.0F, -var15 / 16.0F, -var16 / 16.0F);
        }

        return true;
    }
    @Shadow
    public boolean b(Block par1Block, int par2, int par3, int par4) {
        return false;
    }
    @Shadow
    public boolean p(Block par1Block, int par2, int par3, int par4) {
        return false;
    }
    @Shadow
    public IIcon a(Block par1Block, int par2) {
        return null;
    }
    @Shadow
    public void f(Block par1Block, double par2, double par4, double par6, IIcon par8Icon) {}
    @Shadow
    public void e(Block par1Block, double par2, double par4, double par6, IIcon par8Icon) {}
    @Shadow
    public void d(Block par1Block, double par2, double par4, double par6, IIcon par8Icon) {}
    @Shadow
    public void c(Block par1Block, double par2, double par4, double par6, IIcon par8Icon) {}
    @Shadow
    public void b(Block par1Block, double par2, double par4, double par6, IIcon par8Icon) {}
    @Shadow
    public IIcon b(Block par1Block) {
        return null;
    }
    @Shadow
    public void a(double par1, double par3, double par5, double par7, double par9, double par11) {}
    @Shadow
    public void a(Block par1Block, int par2, double par3, double par5, double par7, float par9) {}
}
