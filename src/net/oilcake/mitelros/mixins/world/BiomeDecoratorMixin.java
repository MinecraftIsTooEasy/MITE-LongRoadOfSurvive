package net.oilcake.mitelros.mixins.world;

import net.minecraft.*;
import net.oilcake.mitelros.block.Blocks;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Random;

@Mixin(BiomeDecorator.class)
public class BiomeDecoratorMixin {

    private WorldGenMinable nickelGen;
    private WorldGenMinable tungstenGen;
    @Inject(method = "<init>", at = @At("RETURN"))
    public void BiomeDecorator(CallbackInfo callbackInfo) {
        this.nickelGen = new WorldGenMinable(Blocks.oreNickel.blockID, 6);
        this.tungstenGen = new WorldGenMinable(Blocks.oreTungsten.blockID, 3);
    }


    protected void generateOres() {
        if (this.currentWorld.isOverworld()) {
            this.genMinable(200, this.dirtGen);
            this.genMinable(200, this.gravelGen);
            this.genMinable(50, this.coalGen);
            this.genMinable(40, this.copperGen, true);
            this.genMinable(10, this.silverGen, true);
            this.genMinable(20, this.goldGen, true);
            this.genMinable(60, this.ironGen, true);
            this.genMinable(10, this.mithrilGen, true);
            this.genMinable(5, this.silverfishGen, true);
            this.genMinable(10, this.redstoneGen);
            this.genMinable(5, this.diamondGen);
            this.genMinable(5, this.lapisGen);

            this.genMinable(2, tungstenGen);
            this.genMinable(30, nickelGen, true);
        } else if (this.currentWorld.isUnderworld()) {
            this.genMinable(300, this.gravelGen);
            this.genMinable(40, this.copperGen, true);
            this.genMinable(10, this.silverGen, true);
            this.genMinable(20, this.goldGen, true);
            this.genMinable(60, this.ironGen, true);
            this.genMinable(10, this.mithrilGen, true);
            this.genMinable(5, this.adamantiteGen);
            this.genMinable(10, this.redstoneGen);
            this.genMinable(5, this.diamondGen);
            this.genMinable(5, this.lapisGen);

            this.genMinable(5, tungstenGen, true);
            this.genMinable(30, nickelGen, true);
            if (this.currentWorld.underworld_y_offset != 0) {
                this.genMinable(50, this.silverfishGen);
            }
        } else if (!this.currentWorld.isTheEnd()) {
            Minecraft.setErrorMessage("generateOres: don't know how to handle world " + this.currentWorld);
        }
    }
    @Overwrite
    protected void genMinable(int frequency, WorldGenMinable world_gen_minable, boolean vein_size_increases_with_depth) {
        int resource_multiplier = 1;
        frequency *= resource_multiplier;
        if (this.currentWorld.underworld_y_offset != 0 && world_gen_minable != this.gravelGen) {
            frequency *= 8;
//            if (world_gen_minable == this.adamantiteGen) {
//                frequency *= 2;
//            }
        }

        while(frequency-- > 0) {
            if (this.randomGenerator.nextInt(10) == 0) {
                int x = this.chunk_X + this.randomGenerator.nextInt(16);
                int y = world_gen_minable.getRandomVeinHeight(this.currentWorld, this.randomGenerator);
                int z = this.chunk_Z + this.randomGenerator.nextInt(16);
                if (y >= 0) {
                    world_gen_minable.generate(this.currentWorld, this.randomGenerator, x, y, z, vein_size_increases_with_depth);
                }
            }
        }

    }
    @Shadow
    protected Random randomGenerator;
    @Shadow
    protected int chunk_X;
    @Shadow
    protected int chunk_Z;
    @Shadow
    protected WorldGenMinable adamantiteGen;
    @Shadow
    protected WorldGenMinable coalGen;
    @Shadow
    protected WorldGenMinable copperGen;
    @Shadow
    protected World currentWorld;
    @Shadow
    protected WorldGenMinable diamondGen;
    @Shadow
    protected WorldGenMinable dirtGen;
    @Shadow
    protected WorldGenMinable goldGen;
    @Shadow
    protected WorldGenMinable gravelGen;
    @Shadow
    protected WorldGenMinable ironGen;
    @Shadow
    protected WorldGenMinable lapisGen;
    @Shadow
    protected WorldGenMinable mithrilGen;
    @Shadow
    protected WorldGenMinable redstoneGen;
    @Shadow
    protected WorldGenMinable silverGen;
    @Shadow
    protected WorldGenMinable silverfishGen;


//    public void genMinableC(int frequency, WorldGenMinable world_gen_minable, boolean vein_size_increases_with_depth) {
//        this.genMinable(frequency, world_gen_minable, vein_size_increases_with_depth);
//    }
    @Shadow
    protected void genMinable(int frequency, WorldGenMinable world_gen_minable) {}

}
