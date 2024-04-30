package net.oilcake.mitelros.mixins.world.biome;

import net.minecraft.*;
import net.minecraft.BiomeDesert;
import net.minecraft.BiomeMeta;
import net.minecraft.EntityZombie;
import net.oilcake.mitelros.entity.EntityHusk;
import net.oilcake.mitelros.world.WorldGenAzurite;
import net.oilcake.mitelros.world.WorldGenSulphur;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Random;

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
    @Inject(method = "decorate", at = @At(value = "INVOKE", target = "Lnet/minecraft/WorldGenDesertWell;generate(Lnet/minecraft/World;Ljava/util/Random;III)Z", ordinal = 0),cancellable = true)
    public void GenSulphur(World par1World, Random par2Random, int par3, int par4, CallbackInfo callbackInfo) {
        if(par2Random.nextInt(8) > 0){
            int var5 = par3 + par2Random.nextInt(16) + 8;
            int var6 = par4 + par2Random.nextInt(16) + 8;
            WorldGenSulphur var7 = new WorldGenSulphur();
            if (par2Random.nextInt(8) == 0) {
                var7.setSuperLarge();
                if(var7.generate(par1World, par2Random, var5, par1World.getHeightValue(var5, var6) + 1, var6))
                    if(Minecraft.inDevMode())
                        System.out.println("Generate Sulphur at " + var5 + " " + var6 + " , superlarge.");
            } else {
                if(var7.generate(par1World, par2Random, var5, par1World.getHeightValue(var5, var6) + 1, var6))
                    if(Minecraft.inDevMode())
                        System.out.println("Generate Sulphur at " + var5 + " " + var6);
            }
            callbackInfo.cancel();
        }
    }
    @Redirect(method = "decorate", at = @At(value = "INVOKE", target = "Ljava/util/Random;nextInt(I)I", ordinal = 0))
    public int RedirectRandom(Random rand, int par0, World par1World, Random par2Random, int par3, int par4) {
        return par2Random.nextInt(125);
    }
}
