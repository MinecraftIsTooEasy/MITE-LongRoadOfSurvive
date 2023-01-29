//package net.oilcake.mitelros.mixins.world;
//
//import net.minecraft.*;
//import net.oilcake.mitelros.block.Blocks;
//import org.spongepowered.asm.mixin.Mixin;
//import org.spongepowered.asm.mixin.Overwrite;
//import org.spongepowered.asm.mixin.Shadow;
//import org.spongepowered.asm.mixin.injection.At;
//import org.spongepowered.asm.mixin.injection.Inject;
//import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
//
//import java.util.Random;
//
//@Mixin(BiomeDecorator.class)
//public class BiomeDecoratorMixin {
//    @Shadow
//    public boolean generateLakes;
//    @Shadow
//    protected WorldGenMinable adamantiteGen;
//    @Shadow
//    protected WorldGenerator bigMushroomGen;
//    @Shadow
//    protected BiomeBase biome;
//    @Shadow
//    protected WorldGenPlants bush_gen;
//    @Shadow
//    protected WorldGenerator cactusGen;
//    @Shadow
//    protected int chunk_X;
//    @Shadow
//    protected int chunk_Z;
//    @Shadow
//    protected WorldGenerator clayGen = new WorldGenClay(4);
//    @Shadow
//    protected int clayPerChunk;
//    @Shadow
//    protected WorldGenMinable coalGen;
//    @Shadow
//    protected WorldGenMinable copperGen;
//    @Shadow
//    protected World currentWorld;
//    @Shadow
//    protected WorldGenMinable diamondGen;
//    @Shadow
//    protected WorldGenMinable dirtGen;
//    @Shadow
//    protected int flowersPerChunk;
//    @Shadow
//    protected WorldGenMinable goldGen;
//    @Shadow
//    protected int grassPerChunk;
//    @Shadow
//    protected WorldGenerator gravelAsSandGen;
//    @Shadow
//    protected WorldGenMinable gravelGen;
//    @Shadow
//    protected WorldGenMinable ironGen;
//    @Shadow
//    protected WorldGenMinable lapisGen;
//    @Shadow
//    protected WorldGenMinable mithrilGen;
//    @Shadow
//    protected WorldGenerator mushroomBrownGen;
//    @Shadow
//    protected WorldGenerator mushroomRedGen;
//    @Shadow
//    protected WorldGenFlowers plantRedGen;
//    @Shadow
//    protected WorldGenerator plantYellowGen;
//    @Shadow
//    protected Random randomGenerator;
//    @Shadow
//    protected WorldGenMinable redstoneGen;
//    @Shadow
//    protected WorldGenerator reedGen;
//    @Shadow
//    protected WorldGenerator sandGen;
//    @Shadow
//    protected int sandPerChunk;
//    @Shadow
//    protected int sandPerChunk2;
//    @Shadow
//    protected WorldGenMinable silverGen;
//    @Shadow
//    protected WorldGenMinable silverfishGen;
//    @Shadow
//    protected int treesPerChunk;
//    @Shadow
//    protected WorldGenerator waterlilyGen;
//    @Shadow
//    protected int waterlilyPerChunk;
//    private WorldGenMinable nickelGen;
//
//    public BiomeDecoratorMixin(BiomeBase par1BiomeGenBase) {this.sandGen = new WorldGenSand(7, Block.sand.blockID);
//        this.gravelAsSandGen = new WorldGenSand(6, Block.gravel.blockID);
//        this.plantYellowGen = new WorldGenFlowers(Block.plantYellow.blockID);
//        this.plantRedGen = new WorldGenFlowers(Block.plantRed.blockID);
//        this.mushroomBrownGen = new WorldGenFlowers(Block.mushroomBrown.blockID);
//        this.mushroomRedGen = new WorldGenFlowers(Block.mushroomRed.blockID);
//        this.bigMushroomGen = new WorldGenHugeMushroom();
//        this.reedGen = new WorldGenReed();
//        this.cactusGen = new WorldGenCactus();
//        this.waterlilyGen = new WorldGenWaterLily();
//        this.flowersPerChunk = 2;
//        this.grassPerChunk = 1;
//        this.sandPerChunk = 1;
//        this.sandPerChunk2 = 3;
//        this.clayPerChunk = 1;
//        this.generateLakes = true;
//        this.biome = par1BiomeGenBase;
//        this.dirtGen = new WorldGenMinable(Block.dirt.blockID, 32);
//        this.gravelGen = new WorldGenMinable(Block.gravel.blockID, 32);
//        this.coalGen = new WorldGenMinable(Block.oreCoal.blockID, 16);
//        this.copperGen = new WorldGenMinable(Block.oreCopper.blockID, 6);
//        this.silverGen = new WorldGenMinable(Block.oreSilver.blockID, 6);
//        this.goldGen = new WorldGenMinable(Block.oreGold.blockID, 4);
//        this.ironGen = new WorldGenMinable(Block.oreIron.blockID, 6);
//        this.mithrilGen = new WorldGenMinable(Block.oreMithril.blockID, 3);
//        this.adamantiteGen = new WorldGenMinable(Block.oreAdamantium.blockID, 2);
//        this.redstoneGen = new WorldGenMinable(Block.oreRedstone.blockID, 5);
//        this.diamondGen = new WorldGenMinable(Block.oreDiamond.blockID, 3);
//        this.lapisGen = new WorldGenMinable(Block.oreLapis.blockID, 2);
//        this.silverfishGen = new WorldGenMinable(Block.silverfish.blockID, 2);
//        this.bush_gen = new WorldGenPlants(Block.bush);
//
//        this.nickelGen = new WorldGenMinable(Blocks.blockNickelOre.blockID, 3);
//    }
//    @Shadow
//    protected void genMinable(int frequency, WorldGenMinable world_gen_minable, boolean vein_size_increases_with_depth) {}
//    @Shadow
//    protected void genMinable(int frequency, WorldGenMinable world_gen_minable) {}
//
//
//    @Overwrite
//    protected void generateOres() {
//        if (this.currentWorld.isOverworld()) {
//            this.genMinable(200, this.dirtGen);
//            this.genMinable(200, this.gravelGen);
//            this.genMinable(50, this.coalGen);
//            this.genMinable(40, this.copperGen, true);
//            this.genMinable(10, this.silverGen, true);
//            this.genMinable(20, this.goldGen, true);
//            this.genMinable(60, this.ironGen, true);
//            this.genMinable(10, this.mithrilGen, true);
//            this.genMinable(5, this.silverfishGen, true);
//            this.genMinable(10, this.redstoneGen);
//            this.genMinable(5, this.diamondGen);
//            this.genMinable(5, this.lapisGen);
//
//
//            this.genMinable(15, this.nickelGen, true);
//        } else if (this.currentWorld.isUnderworld()) {
//            this.genMinable(300, this.gravelGen);
//            this.genMinable(40, this.copperGen, true);
//            this.genMinable(10, this.silverGen, true);
//            this.genMinable(20, this.goldGen, true);
//            this.genMinable(60, this.ironGen, true);
//            this.genMinable(10, this.mithrilGen, true);
//            this.genMinable(5, this.adamantiteGen, true);
//            this.genMinable(10, this.redstoneGen);
//            this.genMinable(5, this.diamondGen);
//            this.genMinable(5, this.lapisGen);
//
//            this.genMinable(30, this.nickelGen, true);
//            if (this.currentWorld.underworld_y_offset != 0) {
//                this.genMinable(50, this.silverfishGen);
//            }
//        } else if (!this.currentWorld.isTheEnd()) {
//            Minecraft.setErrorMessage("generateOres: don't know how to handle world " + this.currentWorld);
//        }
//
//    }
//
//
//}
