package net.oilcake.mitelros.mixins.world.biome;

import java.util.Random;
import net.minecraft.BiomeGenBase;
import net.minecraft.BiomeGenHell;
import net.minecraft.EntityDemonSpider;
import net.minecraft.EntityHellhound;
import net.minecraft.EntityInfernalCreeper;
import net.minecraft.Minecraft;
import net.minecraft.SpawnListEntry;
import net.minecraft.World;
import net.oilcake.mitelros.entity.EntityEvil;
import net.oilcake.mitelros.entity.EntityPigmanLord;
import net.oilcake.mitelros.world.WorldGenSulphur;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({BiomeGenHell.class})
public class BiomeHellMixin extends BiomeGenBase {
   protected BiomeHellMixin(int par1) {
      super(par1);
   }

   @Inject(
      method = {"<init>(I)V"},
      at = {@At("RETURN")}
   )
   public void injectCtor(CallbackInfo callbackInfo) {
      this.spawnableMonsterList.add(new SpawnListEntry(EntityInfernalCreeper.class, 20, 1, 3));
      this.spawnableMonsterList.add(new SpawnListEntry(EntityDemonSpider.class, 20, 1, 4));
      this.spawnableMonsterList.add(new SpawnListEntry(EntityHellhound.class, 20, 1, 4));
      this.spawnableMonsterList.add(new SpawnListEntry(EntityEvil.class, 50, 1, 4));
      this.spawnableMonsterList.add(new SpawnListEntry(EntityPigmanLord.class, 5, 1, 1));
   }

   public void decorate(World par1World, Random par2Random, int par3, int par4) {
      super.decorate(par1World, par2Random, par3, par4);
      if (par2Random.nextInt(256) == 0) {
         int var5 = par3 + par2Random.nextInt(16) + 8;
         int var6 = par4 + par2Random.nextInt(16) + 8;
         WorldGenSulphur var7 = new WorldGenSulphur();
         if (par2Random.nextInt(8) == 0) {
            var7.setSuperLarge();
            if (var7.generate(par1World, par2Random, var5, par1World.getHeightValue(var5, var6) + 1, var6) && Minecraft.inDevMode()) {
               System.out.println("Generate Sulphur at " + var5 + " " + var6 + " , superlarge.");
            }
         } else if (var7.generate(par1World, par2Random, var5, par1World.getHeightValue(var5, var6) + 1, var6) && Minecraft.inDevMode()) {
            System.out.println("Generate Sulphur at " + var5 + " " + var6);
         }
      }

   }
}
