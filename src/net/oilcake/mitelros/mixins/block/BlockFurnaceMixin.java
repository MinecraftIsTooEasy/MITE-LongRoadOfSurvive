package net.oilcake.mitelros.mixins.block;

import net.minecraft.*;
import net.oilcake.mitelros.util.ExperimentalConfig;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(BlockFurnace.class)
public abstract class BlockFurnaceMixin extends BlockDirectionalWithTileEntity {
    protected BlockFurnaceMixin(int id, Material material, BlockConstants constants) {
        super(id, material, constants);
    }

    @Overwrite
    public int dropBlockAsEntityItem(BlockBreakInfo info) {
        if(ExperimentalConfig.TagConfig.TagBenchingV2.ConfigValue){
            Block furnace_block = Block.getBlock(this.getIdleBlockID());
            if (furnace_block == Block.furnaceClayIdle) {
                if(!info.wasExploded())
                    return super.dropBlockAsEntityItem(info, Item.clay.itemID, 0, 16, 1.0F);
                return super.dropBlockAsEntityItem(info, Item.clay.itemID, 0, 4, 1.25F);
            } else if(furnace_block == Block.furnaceHardenedClayIdle) {
                if(!info.wasExploded())
                    return super.dropBlockAsEntityItem(info, Item.brick.itemID, 0, 32, 1.0F);
                return super.dropBlockAsEntityItem(info, Item.brick.itemID, 0, 4, 1.25F);
            } else {
                Block model_block;
                if (furnace_block == Block.furnaceSandstoneIdle) {
                    model_block = Block.sandStone;
                } else if (furnace_block == Block.furnaceIdle) {
                    model_block = Block.cobblestone;
                } else if (furnace_block == Block.furnaceObsidianIdle) {
                    model_block = Block.obsidian;
                } else if(furnace_block == Block.furnaceNetherrackIdle) {
                    model_block = Block.netherrack;
                } else {
                    return 0;
                }
                if(info.wasExploded()) {
                    return model_block.dropBlockAsEntityItem(info.setBlock(model_block, 0));
                }
                return model_block.dropBlockAsEntityItem(info,model_block.blockID,0,8,1.0F);
            }
        } else{
            if (info.wasExploded()) {
                Block furnace_block = Block.getBlock(this.getIdleBlockID());
                if (furnace_block == Block.furnaceClayIdle) {
                    return 0;
                } else {
                    Block model_block;
                    if (furnace_block == Block.furnaceSandstoneIdle) {
                        model_block = Block.sandStone;
                    } else if (furnace_block == Block.furnaceIdle) {
                        model_block = Block.cobblestone;
                    } else if (furnace_block == Block.furnaceObsidianIdle) {
                        model_block = Block.obsidian;
                    } else {
                        if (furnace_block != Block.furnaceNetherrackIdle) {
                            return 0;
                        }

                        model_block = Block.netherrack;
                    }

                    return model_block.dropBlockAsEntityItem(info.setBlock(model_block, 0));
                }
            } else {
                return super.dropBlockAsEntityItem(info);
            }
        }
    }
    @Shadow
    public abstract int getIdleBlockID();

    @Overwrite
    public float getCraftingDifficultyAsComponent(int metadata) {
        return 1920.0F;
    }

}
