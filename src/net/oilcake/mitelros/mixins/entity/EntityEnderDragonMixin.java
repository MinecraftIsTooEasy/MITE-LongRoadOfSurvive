package net.oilcake.mitelros.mixins.entity;

import net.minecraft.*;
import net.oilcake.mitelros.block.Blocks;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(EntityEnderDragon.class)
public class EntityEnderDragonMixin extends EntityInsentient{
    public EntityEnderDragonMixin(World par1World) {
        super(par1World);
    }
    @Overwrite
    private boolean destroyBlocksInAABB(AxisAlignedBB par1AxisAlignedBB) {
        int var2 = MathHelper.floor_double(par1AxisAlignedBB.minX);
        int var3 = MathHelper.floor_double(par1AxisAlignedBB.minY);
        int var4 = MathHelper.floor_double(par1AxisAlignedBB.minZ);
        int var5 = MathHelper.floor_double(par1AxisAlignedBB.maxX);
        int var6 = MathHelper.floor_double(par1AxisAlignedBB.maxY);
        int var7 = MathHelper.floor_double(par1AxisAlignedBB.maxZ);
        boolean var8 = false;
        boolean var9 = false;

        for(int var10 = var2; var10 <= var5; ++var10) {
            for(int var11 = var3; var11 <= var6; ++var11) {
                for(int var12 = var4; var12 <= var7; ++var12) {
                    int var13 = this.worldObj.getBlockId(var10, var11, var12);
                    if (var13 != 0) {
                        if (var13 != Blocks.oreUru.blockID && var13 != Block.obsidian.blockID && var13 != Block.whiteStone.blockID && var13 != Block.bedrock.blockID && this.worldObj.getGameRules().getGameRuleBooleanValue("mobGriefing")) {
                            var9 = this.worldObj.setBlockToAir(var10, var11, var12) || var9;
                        } else {
                            var8 = true;
                        }
                    }
                }
            }
        }

        if (var9) {
            double var16 = par1AxisAlignedBB.minX + (par1AxisAlignedBB.maxX - par1AxisAlignedBB.minX) * (double)this.rand.nextFloat();
            double var17 = par1AxisAlignedBB.minY + (par1AxisAlignedBB.maxY - par1AxisAlignedBB.minY) * (double)this.rand.nextFloat();
            double var14 = par1AxisAlignedBB.minZ + (par1AxisAlignedBB.maxZ - par1AxisAlignedBB.minZ) * (double)this.rand.nextFloat();
            this.worldObj.spawnParticle(EnumParticle.largeexplode, var16, var17, var14, 0.0, 0.0, 0.0);
        }

        return var8;
    }
}
