package net.oilcake.mitelros.mixins.block;

import net.minecraft.*;
import net.oilcake.mitelros.block.BlockFlowerExtend;
import net.oilcake.mitelros.block.Blocks;
import org.lwjgl.Sys;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

import static net.minecraft.Block.*;

@Mixin(Block.class)
public abstract class BlockMixin{

    @Inject(method = "<clinit>", at = @At("RETURN"))
    private static void injectClinit(CallbackInfo callback) {
        Item.itemsList[Blocks.flowerextend.blockID] = (new ItemMultiTexture(Blocks.flowerextend, BlockFlowerExtend.types)).setUnlocalizedName("flowers");
    }

    @ModifyConstant(method = {"<clinit>", "getBlock(Ljava/lang/String;)Lnet/minecraft/Block;",}, constant = @Constant(intValue = 256))
    private static int ExtendedBlockID(int value) {
        return net.oilcake.mitelros.util.Constant.Extended_Block_ID;
    }

    public Block getMatchingBlock(Class item_class, Material material) {
        Block matching_block = null;
        for(int i = 0; i < net.oilcake.mitelros.util.Constant.Extended_Block_ID; ++i) {
            Block block = getBlock(i);
            if (block != null && block.getClass() == item_class && block.blockMaterial == material) {
                if (matching_block == null) {
                    matching_block = block;
                } else {
                    Minecraft.setErrorMessage("getMatchingItem: more than one item matched " + item_class + ", " + material);
                }
            }
        }
        return matching_block;
    }
    @Shadow
    public boolean isValidMetadata(int metadata) {
        return false;
    }
    @Shadow
    public int getItemSubtype(int metadata) {
        return 0;
    }
    public Block block;

    @Shadow protected Block setResistance(float par1){
        return null;
    };

    public Block setBlockHardness(float resistance) {
        return this.setHardness(resistance);
    }

    public Block setExplosionResistance(float v) {
        return this.setResistance(v);
    }

    public Block setBlockLightLevel(float v){
        return this.setLightValue(v);
    }

    @Shadow
    protected Block setHardness(float par1) {
        return null;
    }

    @Shadow
    protected Block setLightValue(float exp) {
        return null;
    }

    public Block setResourceLocation(String location) {
        return this.setTextureName(location);
    }

    @Shadow
    protected Block setStepSound(StepSound par1StepSound) {
        return null;
    }

    public Block setStepSound_(StepSound stepSound) {
        return this.setStepSound(stepSound);
    }

    @Shadow
    protected Block setTextureName(String par1Str) {
        return null;
    }

}
