package net.oilcake.mitelros.mixins.world.biome;

import net.minecraft.*;
import net.oilcake.mitelros.entity.EntityStray;
import net.oilcake.mitelros.util.ExperimentalConfig;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Random;

@Mixin(BiomeTaiga.class)
public class BiomeTaigaMixin extends BiomeBase {
    protected BiomeTaigaMixin(int par1) {
        super(par1);
    }
    @Inject(method = "<init>",at = @At("RETURN"))
    public void injectCtor(CallbackInfo callbackInfo) {
        this.removeEntityFromSpawnableLists(EntitySkeleton.class);
        this.spawnableMonsterList.add(new BiomeMeta(EntityStray.class, 100, 1, 4));
        if(ExperimentalConfig.TagConfig.TagCreaturesV2.ConfigValue){
            this.RegenHostileAnimals();
        }
    }
    private void RegenHostileAnimals(){
        this.removeEntityFromSpawnableLists(EntityWolf.class);
        this.spawnableCreatureList.add(new BiomeMeta(EntityWolf.class, 10, 4, 8));
        this.removeEntityFromSpawnableLists(EntityDireWolf.class);
        this.spawnableCreatureList.add(new BiomeMeta(EntityDireWolf.class, 5, 4, 6));
    }


    public void decorate(World par1World, Random par2Random, int par3, int par4) {
        super.decorate(par1World, par2Random, par3, par4);
        int var5 = 3 + par2Random.nextInt(6);

        for (int var6 = 0; var6 < var5; ++var6) {
            int var7 = par3 + par2Random.nextInt(16);
            int var8 = par2Random.nextInt(28) + 4;
            int var9 = par4 + par2Random.nextInt(16);
            int var10 = par1World.getBlockId(var7, var8, var9);
            if (var10 == Block.stone.blockID) {
                par1World.setBlock(var7, var8, var9, Block.oreEmerald.blockID, 0, 2);
            }
        }
//        WorldGenMinable genMinable = (new WorldGenMinable(Blocks.blockNickelOre.blockID, 8, Block.stone.blockID)).setMinableBlockMetadata(0);
//        int count = par2Random.nextInt(Constant.nickelNUM) + 1;
//        for (int temp = 0; temp < count; ++temp) {
//            int x = par3 + par2Random.nextInt(16);
//            int y = par2Random.nextInt(36)+12;
//            int z = par4 + par2Random.nextInt(16);
//            genMinable.generate(par1World, par2Random, x, y, z);
//        }
    }
}
