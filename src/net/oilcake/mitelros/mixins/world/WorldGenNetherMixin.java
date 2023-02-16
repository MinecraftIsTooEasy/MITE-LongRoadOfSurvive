package net.oilcake.mitelros.mixins.world;

import net.minecraft.*;
import net.oilcake.mitelros.entity.EntityWitherBoneLord;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import java.util.ArrayList;
import java.util.List;

@Mixin(WorldGenNether.class)
public class WorldGenNetherMixin extends StructureGenerator {
    @Shadow
    private List spawnList = new ArrayList();

    public WorldGenNetherMixin() {
        this.spawnList.add(new BiomeMeta(EntityBlaze.class, 100, 2, 3));
        this.spawnList.add(new BiomeMeta(EntityPigZombie.class, 50, 4, 4));
        this.spawnList.add(new BiomeMeta(EntitySkeleton.class, 100, 4, 4));
        this.spawnList.add(new BiomeMeta(EntityMagmaCube.class, 30, 4, 4));
    }
    @Shadow
    public String func_143025_a() {
        return "Fortress";
    }

//    public List getSpawnList() {
//        return this.spawnList;
//    }
    @Shadow
    protected boolean canSpawnStructureAtCoords(int par1, int par2) {
        int var3 = par1 >> 4;
        int var4 = par2 >> 4;
        this.rand.setSeed((long)(var3 ^ var4 << 4) ^ this.worldObj.getSeed());
        this.rand.nextInt();
        return this.rand.nextInt(3) != 0 ? false : (par1 != (var3 << 4) + 4 + this.rand.nextInt(8) ? false : par2 == (var4 << 4) + 4 + this.rand.nextInt(8));
    }
    @Shadow
    protected StructureStart getStructureStart(int par1, int par2) {
        return new WorldGenNetherStart(this.worldObj, this.rand, par1, par2);
    }
}
