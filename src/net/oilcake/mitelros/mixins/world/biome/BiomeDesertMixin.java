package net.oilcake.mitelros.mixins.world.biome;

import net.minecraft.BiomeBase;
import net.minecraft.BiomeDesert;
import net.minecraft.BiomeMeta;
import net.minecraft.EntityZombie;
import net.oilcake.mitelros.entity.EntityHusk;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(BiomeDesert.class)
public class BiomeDesertMixin extends BiomeBase {
    protected BiomeDesertMixin(int par1) {
        super(par1);
    }
    @Inject(method = "<init>",at = @At("RETURN"))
    public void injectCtor(CallbackInfo callbackInfo) {
        this.removeEntityFromSpawnableLists(EntityZombie.class);
        this.spawnableMonsterList.add(new BiomeMeta(EntityHusk.class, 100, 1, 4));
    }

//    @Overwrite
//    public void decorate(World par1World, Random par2Random, int par3, int par4) {
//        super.decorate(par1World, par2Random, par3, par4);
//        if (par2Random.nextInt(1000) == 0) {
//            int var5 = par3 + par2Random.nextInt(16) + 8;
//            int var6 = par4 + par2Random.nextInt(16) + 8;
//            WorldGenDesertWell var7 = new WorldGenDesertWell();
//            var7.generate(par1World, par2Random, var5, par1World.getHeightValue(var5, var6) + 1, var6);
//        }
//
//        WorldGenMinable genMinable = (new WorldGenMinable(Blocks.blockNickelOre.blockID, 8 , Block.stone.blockID)).setMinableBlockMetadata(0);
//        int count = par2Random.nextInt(Constant.nickelNUM) + 1;
//        for(int temp = 0; temp < count; ++temp) {
//            int x = par3 + par2Random.nextInt(16);
//            int y = par2Random.nextInt(36)+12;
//            int z = par4 + par2Random.nextInt(16);
//            genMinable.generate(par1World, par2Random, x, y, z);
//        }
//    }
}
