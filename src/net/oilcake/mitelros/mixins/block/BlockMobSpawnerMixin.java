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
        this.dropXpOnBlockBreak(info.world, info.x, info.y, info.z, 15 + info.world.rand.nextInt(15) + info.world.rand.nextInt(15));
        if (info.world.isUnderworld() && info.world.rand.nextFloat() < 0.05) {
            EntityLich lich = new EntityLich(info.world);
            lich.setPosition((double)info.x, (double)info.y, (double)info.z);
            lich.refreshDespawnCounter(-9600);
            lich.entityFX(EnumEntityFX.summoned);
            lich.onSpawnWithEgg((GroupDataEntity)null);
            info.world.spawnEntityInWorld(lich);
        }

        return 0;
    }
    @Shadow
    public TileEntity createNewTileEntity(World world) {
        return null;
    }
}