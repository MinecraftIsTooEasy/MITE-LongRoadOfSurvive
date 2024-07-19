package net.oilcake.mitelros.mixins.world.biome;

import java.util.Random;
import net.minecraft.BiomeGenBase;
import net.minecraft.BiomeGenUnderworld;
import net.minecraft.Block;
import net.minecraft.EntityCreeper;
import net.minecraft.Minecraft;
import net.minecraft.SpawnListEntry;
import net.minecraft.World;
import net.oilcake.mitelros.entity.EntityBoneBodyguard;
import net.oilcake.mitelros.entity.EntityRetinueZombie;
import net.oilcake.mitelros.entity.EntitySpiderKing;
import net.oilcake.mitelros.entity.EntityStalkerCreeper;
import net.oilcake.mitelros.world.WorldGenAzurite;
import net.oilcake.mitelros.world.WorldGenUnderworldCastle;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({BiomeGenUnderworld.class})
public class BiomeGenUnderworldMixin extends BiomeGenBase {
   protected BiomeGenUnderworldMixin(int par1) {
      super(par1);
   }

   @Inject(
      method = {"<init>(I)V"},
      at = {@At("RETURN")}
   )
   public void injectCtor(CallbackInfo callbackInfo) {
      this.removeEntityFromSpawnableLists(EntityCreeper.class);
      this.removeEntityFromSpawnableLists(EntityBoneBodyguard.class);
      this.removeEntityFromSpawnableLists(EntityRetinueZombie.class);
      this.spawnableMonsterList.add(new SpawnListEntry(EntityStalkerCreeper.class, 100, 1, 2));
      this.spawnableMonsterList.add(new SpawnListEntry(EntitySpiderKing.class, 5, 1, 1));
   }

   @Shadow
   private void placeMycelium(World world, int chunk_origin_x, int chunk_origin_z) {
   }

   public void decorate(World par1World, Random par2Random, int par3, int par4) {
      this.placeMycelium(par1World, par3, par4);
      super.decorate(par1World, par2Random, par3, par4);
      int var5;
      int var6;
      if (par2Random.nextInt(256) == 0) {
         if (par2Random.nextInt(16) == 0) {
            var5 = par3 + par2Random.nextInt(16) + 8;
            var6 = par4 + par2Random.nextInt(16) + 8;
            WorldGenUnderworldCastle var7 = new WorldGenUnderworldCastle();
            if (var7.generate(par1World, par2Random, var5, par1World.getHeightValue(var5, var6) + 1, var6) && Minecraft.inDevMode()) {
               System.out.println("Generate Castle at " + var5 + " " + var6 + ".");
            }
         } else {
            var5 = par3 + par2Random.nextInt(16) + 8;
            var6 = par4 + par2Random.nextInt(16) + 8;
            WorldGenAzurite var7 = new WorldGenAzurite();
            if (par2Random.nextInt(8) == 0) {
               var7.setSuperLarge();
               if (var7.generate(par1World, par2Random, var5, par1World.getHeightValue(var5, var6) + 1, var6) && Minecraft.inDevMode()) {
                  System.out.println("Generate Azurite at " + var5 + " " + var6 + " , superlarge.");
               }
            } else if (var7.generate(par1World, par2Random, var5, par1World.getHeightValue(var5, var6) + 1, var6) && Minecraft.inDevMode()) {
               System.out.println("Generate Azurite at " + var5 + " " + var6);
            }
         }
      }

      var5 = 8 + par2Random.nextInt(24);

      for(var6 = 0; var6 < var5; ++var6) {
         int var7 = par3 + par2Random.nextInt(16);
         int var8 = par2Random.nextInt(60) + 4;
         int var9 = par4 + par2Random.nextInt(16);
         int var10 = par1World.getBlockId(var7, var8, var9);
         if (var10 == Block.stone.blockID) {
            par1World.setBlock(var7, var8, var9, Block.lavaStill.blockID, 0, 2);
         }

         if (var8 < 32) {
            var7 = par3 + par2Random.nextInt(16);
            var9 = par4 + par2Random.nextInt(16);
            if (var10 == Block.stone.blockID) {
               par1World.setBlock(var7, var8, var9, Block.lavaStill.blockID, 0, 2);
            }
         }
      }

   }
}
