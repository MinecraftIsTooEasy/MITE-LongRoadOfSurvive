//package net.oilcake.mitelros.mixins.world;
//
//import net.minecraft.*;
//import net.xiaoyu233.fml.util.ReflectHelper;
//import org.spongepowered.asm.mixin.Mixin;
//import org.spongepowered.asm.mixin.Overwrite;
//import org.spongepowered.asm.mixin.Shadow;
//import org.spongepowered.asm.mixin.injection.At;
//import org.spongepowered.asm.mixin.injection.Inject;
//import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
//
//import java.util.ArrayList;
//import java.util.Collections;
//import java.util.Map;
//import java.util.Random;
//
//@Mixin(WorldGenStronghold.class)
//public abstract class WorldGenStrongholdMixin extends StructureGenerator{
//    @Shadow
//    private double field_82671_h;
//    @Shadow
//    private int field_82672_i;
//    @Shadow
//    private BiomeBase[] allowedBiomeGenBases;
//    @Shadow
//    private boolean ranBiomeCheck;
//    @Shadow
//    private ChunkCoordIntPair[] structureCoords;
//    @Inject(method = "<init>()V",at = @At("RETURN"))
//    private void injectCtor(CallbackInfo callbackInfo){
//        this.field_82671_h /= 2.0;
//    }
//
//    @Inject(method = "<init>(Ljava/util/Map;)V" ,at = @At("RETURN"))
//    private void ItemInject(Map par1Map, CallbackInfo callbackInfo) {
//        this.field_82671_h /= 2.0;
//    }
//    @Overwrite
//    protected boolean canSpawnStructureAtCoords(int par1, int par2) {
//        if (!this.ranBiomeCheck) {
//            Random var3 = new Random();
//            var3.setSeed(this.worldObj.getSeed());
//            double var4 = var3.nextDouble() * Math.PI * 2.0;
//            int var6 = 1;
//
//            for(int var7 = 0; var7 < this.structureCoords.length; ++var7) {
//                double var8 = (1.25 * (double)var6 + var3.nextDouble()) * this.field_82671_h * (double)var6;
//                int var10 = (int)Math.round(Math.cos(var4) * var8);
//                int var11 = (int)Math.round(Math.sin(var4) * var8);
//                ArrayList var12 = new ArrayList();
//                Collections.addAll(var12, this.allowedBiomeGenBases);
//                ChunkPosition var13 = this.worldObj.getWorldChunkManager().findBiomePosition((var10 << 4) + 8, (var11 << 4) + 8, 112, var12, var3);
//                if (var13 != null) {
//                    var10 = var13.x >> 4;
//                    var11 = var13.z >> 4;
//                }
//
//                this.structureCoords[var7] = new ChunkCoordIntPair(var10, var11);
//                var4 += 6.283185307179586 * (double)var6 / (double)this.field_82672_i;
//                if (var7 == this.field_82672_i) {
//                    var6 += 2 + var3.nextInt(5);
//                    this.field_82672_i += 1 + var3.nextInt(2);
//                }
//            }
//
//            this.ranBiomeCheck = true;
//        }
//
//        ChunkCoordIntPair[] var14 = this.structureCoords;
//        int var15 = var14.length;
//
//        for(int var5 = 0; var5 < var15; ++var5) {
//            ChunkCoordIntPair var16 = var14[var5];
//            if (par1 == var16.chunkXPos && par2 == var16.chunkZPos) {
//                System.out.println("x = " + par1 + " , z = " + par2);
//                return true;
//            }
//        }
//
//        return false;
//    }
//}
