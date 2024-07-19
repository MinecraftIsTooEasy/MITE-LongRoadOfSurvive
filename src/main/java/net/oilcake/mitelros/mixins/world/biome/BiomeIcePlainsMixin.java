package net.oilcake.mitelros.mixins.world.biome;

import java.util.Random;
import net.minecraft.BiomeGenBase;
import net.minecraft.BiomeGenSnow;
import net.minecraft.Block;
import net.minecraft.EntityDireWolf;
import net.minecraft.EntitySkeleton;
import net.minecraft.EntityWolf;
import net.minecraft.SpawnListEntry;
import net.minecraft.World;
import net.oilcake.mitelros.entity.EntityStray;
import net.oilcake.mitelros.util.ExperimentalConfig;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({BiomeGenSnow.class})
public class BiomeIcePlainsMixin extends BiomeGenBase {
   protected BiomeIcePlainsMixin(int par1) {
      super(par1);
   }

   @Inject(
      method = {"<init>(I)V"},
      at = {@At("RETURN")}
   )
   public void injectCtor(CallbackInfo callbackInfo) {
      this.removeEntityFromSpawnableLists(EntitySkeleton.class);
      this.spawnableMonsterList.add(new SpawnListEntry(EntityStray.class, 100, 1, 4));
      if ((Boolean)ExperimentalConfig.TagConfig.TagCreaturesV2.ConfigValue) {
         this.RegenHostileAnimals();
      }

   }

   private void RegenHostileAnimals() {
      this.removeEntityFromSpawnableLists(EntityWolf.class);
      this.spawnableCreatureList.add(new SpawnListEntry(EntityWolf.class, 8, 4, 8));
      this.removeEntityFromSpawnableLists(EntityDireWolf.class);
      this.spawnableCreatureList.add(new SpawnListEntry(EntityDireWolf.class, 2, 4, 6));
   }

   public void decorate(World par1World, Random par2Random, int par3, int par4) {
      super.decorate(par1World, par2Random, par3, par4);
      int var5 = 3 + par2Random.nextInt(6);

      for(int var6 = 0; var6 < var5; ++var6) {
         int var7 = par3 + par2Random.nextInt(16);
         int var8 = par2Random.nextInt(28) + 4;
         int var9 = par4 + par2Random.nextInt(16);
         int var10 = par1World.getBlockId(var7, var8, var9);
         if (var10 == Block.stone.blockID) {
            par1World.setBlock(var7, var8, var9, Block.oreEmerald.blockID, 0, 2);
         }
      }

   }
}
