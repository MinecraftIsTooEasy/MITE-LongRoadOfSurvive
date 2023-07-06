package net.oilcake.mitelros.mixins;

import net.minecraft.*;
import net.oilcake.mitelros.block.BlockFlowerPotExtend;
import net.oilcake.mitelros.block.Blocks;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

import static net.oilcake.mitelros.block.Blocks.flowerPotExtend;

@Mixin(BlockFlowerPot.class)
public class BlockFlowerPotMixin extends Block{
    protected BlockFlowerPotMixin(int par1, Material par2Material, BlockConstants constants) {
        super(par1, par2Material, constants);
    }

    @Overwrite
    public boolean isValidMetadata(int metadata) {
        return metadata >= 0 && metadata < 14;
    }
    @Overwrite
    public static ItemStack getPlantForMeta(int par0) {
        switch (par0) {
            case 1:
                return new ItemStack(Block.plantRed);
            case 2:
                return new ItemStack(Block.plantYellow);
            case 3:
                return new ItemStack(Block.sapling, 1, 0);
            case 4:
                return new ItemStack(Block.sapling, 1, 1);
            case 5:
                return new ItemStack(Block.sapling, 1, 2);
            case 6:
                return new ItemStack(Block.sapling, 1, 3);
            case 7:
                return new ItemStack(Block.mushroomRed);
            case 8:
                return new ItemStack(Block.mushroomBrown);
            case 9:
                return new ItemStack(Block.cactus);
            case 10:
                return new ItemStack(Block.deadBush);
            case 11:
                return new ItemStack(Block.tallGrass, 1, 2);
            case 12:
                return new ItemStack(Block.deadBush, 1, 1);
            case 13:
                return new ItemStack(Blocks.flowerextend);
            default:
                return null;
        }
    }
    @Overwrite
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, EnumFace face, float offset_x, float offset_y, float offset_z) {
        ItemStack item_stack = player.getHeldItemStack();
        if (item_stack == null) {
            return false;
        } else {
            int metadata_for_plant;
            if (BlockFlowerPotMulti.getMetaForPlant(item_stack) != 0) {
                if (player.onServer()) {
                    metadata_for_plant = world.getBlockMetadata(x, y, z);
                    if (metadata_for_plant != 0) {
                        BlockBreakInfo info = new BlockBreakInfo(world, x, y, z);
                        this.dropBlockAsEntityItem(info, getPlantForMeta(metadata_for_plant));
                        world.playSoundAtBlock(x, y, z, "random.pop", 0.1F, ((world.rand.nextFloat() - world.rand.nextFloat()) * 0.7F + 1.0F) * 2.0F);
                    }

                    world.setBlock(x, y, z, flowerPotMulti.blockID, BlockFlowerPotMulti.getMetaForPlant(item_stack), 2);
                    if (!player.inCreativeMode()) {
                        player.convertOneOfHeldItem((ItemStack)null);
                    }
                }

                return true;
            } else if (BlockFlowerPotExtend.getMetaForPlant(item_stack) != 0) {
                if (player.onServer()) {
                    metadata_for_plant = world.getBlockMetadata(x, y, z);
                    if (metadata_for_plant != 0) {
                        BlockBreakInfo info = new BlockBreakInfo(world, x, y, z);
                        this.dropBlockAsEntityItem(info, getPlantForMeta(metadata_for_plant));
                        world.playSoundAtBlock(x, y, z, "random.pop", 0.1F, ((world.rand.nextFloat() - world.rand.nextFloat()) * 0.7F + 1.0F) * 2.0F);
                    }

                    world.setBlock(x, y, z, flowerPotExtend.blockID, BlockFlowerPotExtend.getMetaForPlant(item_stack), 2);
                    if (!player.inCreativeMode()) {
                        player.convertOneOfHeldItem((ItemStack)null);
                    }
                }

                return true;
            } else {
                metadata_for_plant = getMetaForPlant(item_stack);
                if (metadata_for_plant == 0) {
                    return false;
                } else {
                    int metadata = world.getBlockMetadata(x, y, z);
                    if (metadata == metadata_for_plant) {
                        return false;
                    } else {
                        if (player.onServer()) {
                            if (metadata != 0) {
                                BlockBreakInfo info = new BlockBreakInfo(world, x, y, z);
                                this.dropBlockAsEntityItem(info, getPlantForMeta(metadata));
                                world.playSoundAtBlock(x, y, z, "random.pop", 0.1F, ((world.rand.nextFloat() - world.rand.nextFloat()) * 0.7F + 1.0F) * 2.0F);
                            }

                            world.setBlockMetadata(x, y, z, metadata_for_plant, 2);
                            if (!player.inCreativeMode()) {
                                player.convertOneOfHeldItem((ItemStack)null);
                            }
                        }

                        return true;
                    }
                }
            }
        }
    }
    @Overwrite
    public static int getMetaForPlant(ItemStack par0ItemStack) {
        int var1 = par0ItemStack.getItem().itemID;
        if (var1 == Block.plantRed.blockID) {
            return par0ItemStack.getItemSubtype() == 0 ? 1 : 0;
        } else if (var1 == Blocks.flowerextend.blockID) {
            return par0ItemStack.getItemSubtype() == 0 ? 13 : 0;
        } else if (var1 == Block.plantYellow.blockID) {
            return 2;
        } else if (var1 == Block.cactus.blockID) {
            return 9;
        } else if (var1 == Block.mushroomBrown.blockID) {
            return 8;
        } else if (var1 == Block.mushroomRed.blockID) {
            return 7;
        } else if (var1 == Block.deadBush.blockID) {
            return deadBush.isWitherwood(par0ItemStack.getItemSubtype()) ? 12 : 10;
        } else {
            if (var1 == Block.sapling.blockID) {
                switch (par0ItemStack.getItemSubtype()) {
                    case 0:
                        return 3;
                    case 1:
                        return 4;
                    case 2:
                        return 5;
                    case 3:
                        return 6;
                }
            }

            if (var1 == Block.tallGrass.blockID) {
                switch (par0ItemStack.getItemSubtype()) {
                    case 2:
                        return 11;
                }
            }

            return 0;
        }
    }
}
