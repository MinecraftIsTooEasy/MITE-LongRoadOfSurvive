package net.oilcake.mitelros.mixins.block;


import net.minecraft.*;
import net.oilcake.mitelros.entity.EntityLich;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(BlockMobSpawner.class)
public class BlockMobSpawnerMixin extends BlockContainer {
    protected BlockMobSpawnerMixin(int par1, Material par2Material, BlockConstants block_constants) {
        super(par1, par2Material, block_constants);
    }
    @Overwrite
    public int dropBlockAsEntityItem(BlockBreakInfo info) {
        this.dropXpOnBlockBreak(info.world, info.x, info.y, info.z, 15 + info.world.rand.nextInt(30));
        return 0;
    }
    @Shadow
    public TileEntity createNewTileEntity(World world) {
        return null;
    }
}