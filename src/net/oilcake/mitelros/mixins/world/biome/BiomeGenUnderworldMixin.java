package net.oilcake.mitelros.mixins.world.biome;

import net.minecraft.*;
import net.oilcake.mitelros.block.Blocks;
import net.oilcake.mitelros.entity.EntitySpiderKing;
import net.oilcake.mitelros.entity.EntityWitherBoneLord;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Random;

@Mixin(BiomeGenUnderworld.class)
public class BiomeGenUnderworldMixin extends BiomeBase {
    protected BiomeGenUnderworldMixin(int par1) {
        super(par1);
    }
    @Inject(method = "<init>",at = @At("RETURN"))
    public void injectCtor(CallbackInfo callbackInfo) {
        this.removeEntityFromSpawnableLists(EntitySkeleton.class);
        this.removeEntityFromSpawnableLists(EntityZombie.class);
        this.removeEntityFromSpawnableLists(EntityGhoul.class);
        this.removeEntityFromSpawnableLists(EntityRevenant.class);
        this.removeEntityFromSpawnableLists(EntityBoneLord.class);
        this.spawnableMonsterList.add(new BiomeMeta(EntityCaveSpider.class, 40, 1, 2));
        this.spawnableMonsterList.add(new BiomeMeta(EntityLongdead.class, 40, 1, 2));
        this.spawnableMonsterList.add(new BiomeMeta(EntityAncientBoneLord.class, 5, 1, 1));
        this.spawnableMonsterList.add(new BiomeMeta(EntityWitherBoneLord.class,5,1,1));
        this.spawnableMonsterList.add(new BiomeMeta(EntitySpiderKing.class,5,1,1));
    }

    @Shadow
    private void placeMycelium(World world, int chunk_origin_x, int chunk_origin_z) {
    }

    public void decorate(World par1World, Random par2Random, int par3, int par4) {
        this.placeMycelium(par1World, par3, par4);
        super.decorate(par1World, par2Random, par3, par4);
        int var5 = 8 + par2Random.nextInt(24);
        for (int var6 = 0; var6 < var5; ++var6) {
            int var7 = par3 + par2Random.nextInt(16);
            int var8 = par2Random.nextInt(60) + 4;
            int var9 = par4 + par2Random.nextInt(16);
            int var10 = par1World.getBlockId(var7, var8, var9);
            if (var10 == Block.stone.blockID) {
                par1World.setBlock(var7, var8, var9, Block.lavaStill.blockID, 0, 2);
            }
            if(var8 < 32){
                var7 = par3 + par2Random.nextInt(16);
                var9 = par4 + par2Random.nextInt(16);
                if (var10 == Block.stone.blockID) {
                    par1World.setBlock(var7, var8, var9, Block.lavaStill.blockID, 0, 2);
                }
            }
        }
//
//        WorldGenMinable genMinableNickel = (new WorldGenMinable(Blocks.blockNickelOre.blockID, par2Random.nextInt(5) + 3 , Block.stone.blockID)).setMinableBlockMetadata(0);
//        int countNickel = par2Random.nextInt(14) + 1;
//        for(int temp = 0; temp < countNickel; ++temp) {
//            int x = par3 + par2Random.nextInt(16);
//            int y = par2Random.nextInt(255);
//            int z = par4 + par2Random.nextInt(16);
//            genMinableNickel.generate(par1World, par2Random, x, y, z);
//        }
//        WorldGenMinable genMinable = (new WorldGenMinable(Blocks.blockTungstenOre.blockID, par2Random.nextInt(3) + 3, Block.stone.blockID)).setMinableBlockMetadata(0);
//        int countTungsten = par2Random.nextInt(5) + 1;
//        for(int temp = 0; temp < countTungsten; ++temp) {
//            int x = par3 + par2Random.nextInt(16);
//            int y = par2Random.nextInt(90) + 20;
//            int z = par4 + par2Random.nextInt(16);
//            genMinable.generate(par1World, par2Random, x, y, z);
//        }
    }
}
