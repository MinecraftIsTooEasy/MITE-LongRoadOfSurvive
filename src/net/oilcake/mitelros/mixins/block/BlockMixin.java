package net.oilcake.mitelros.mixins.block;

import net.minecraft.*;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

import static net.minecraft.Block.*;

@Mixin(Block.class)
public class BlockMixin{
    public Block block;

//    @Overwrite
//    public int dropBlockAsEntityItem(BlockBreakInfo info) {
//        if (info.block != block) {
//            Minecraft.setErrorMessage("dropBlockAsEntityItem: info.block!=this");
//        }
//
//        if (info.wasSilkHarvested()) {
//            Minecraft.setErrorMessage("dropBlockAsEntityItem: This function is not meant to be used for silk harvested blocks " + this);
//            (new Exception()).printStackTrace();
//        }
//
//        if (info.wasCrushed()) {
//            return 0;
//        } else {
//            if (info.wasExploded()) {
//                if (block == brick) {
//                    return this.dropBlockAsEntityItem(info, Item.brick.itemID, 0, 1, 1.5F);
//                }
//
//                if (block == cobblestone || this == cobblestoneMossy) {
//                    return this.dropBlockAsEntityItem((BlockBreakInfo)info, (Block)gravel);
//                }
//
//                if (block == blockLapis) {
//                    return this.dropBlockAsEntityItem(info, Item.dyePowder.itemID, 4, 9, 0.5F);
//                }
//
//                if (block.blockMaterial == Material.cloth) {
//                    return this.dropBlockAsEntityItem(info, Item.silk);
//                }
//
//                if (block.blockMaterial == Material.wood) {
//                    return this.dropBlockAsEntityItem(info, Item.stick);
//                }
//
//                if (block.blockMaterial == Material.hardened_clay) {
//                    return 0;
//                }
//
//                if (block.blockMaterial == Material.stone) {
//                    return this.dropBlockAsEntityItem(info, cobblestone);
//                }
//
//                if (block.blockMaterial == Material.netherrack) {
//                    return 0;
//                }
//            }
//
//            return block == coalBlock ? this.dropBlockAsEntityItem(info, Item.coal.itemID, 0, 9, info.wasExploded() ? 0.5F : 1.0F) : this.dropBlockAsEntityItem(info, this.createStackedBlock(info.getMetadata()));
//        }
//    }
    @Shadow
    public ItemStack createStackedBlock(int metadata) {
        return null;
    }


    @Shadow protected Block setResistance(float par1){
        return null;
    };

    public String getItemDisplayName(ItemStack itemStack){
        return ("" + LocaleI18n.translateToLocal(itemStack.getItem().getUnlocalizedNameInefficiently(itemStack) + ".name")).trim();
    }

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
