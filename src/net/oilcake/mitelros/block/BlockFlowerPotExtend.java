package net.oilcake.mitelros.block;

import net.minecraft.*;

public class BlockFlowerPotExtend extends BlockFlowerPot{
    public BlockFlowerPotExtend(int par1) {
        super(par1);
    }
    public String getMetadataNotes() {
        String[] types = BlockFlowerExtend.types;
        String[] array = new String[16];

        for(int i = 0; i < 16; ++i) {
            if (this.isValidMetadata(i)) {
                StringHelper.addToStringArray(i + "=" + StringHelper.capitalize(types[i]), array);
            }
        }

        return StringHelper.implode(array, ", ", true, false) + " (empty and rose-filled pots are always BlockFlowerPot)";
    }
    public boolean isValidMetadata(int metadata) {
        return metadata != 0 && Blocks.flowerextend.isValidMetadata(metadata);
    }
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, EnumFace face, float offset_x, float offset_y, float offset_z) {
        ItemStack item_stack = player.getHeldItemStack();
        if (item_stack == null) {
            return false;
        } else {
            int metadata_for_plant;
            if (BlockFlowerPot.getMetaForPlant(item_stack) != 0) {
                if (player.onServer()) {
                    metadata_for_plant = world.getBlockMetadata(x, y, z);
                    if (metadata_for_plant != 0) {
                        BlockBreakInfo info = new BlockBreakInfo(world, x, y, z);
                        this.dropBlockAsEntityItem(info, getPlantForMeta(metadata_for_plant));
                        world.playSoundAtBlock(x, y, z, "random.pop", 0.1F, ((world.rand.nextFloat() - world.rand.nextFloat()) * 0.7F + 1.0F) * 2.0F);
                    }

                    world.setBlock(x, y, z, flowerPot.blockID, BlockFlowerPot.getMetaForPlant(item_stack), 2);
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
    public boolean canBeCarried() {
        return false;
    }
    public int dropBlockAsEntityItem(BlockBreakInfo info) {
        if (!info.wasExploded() && !info.wasCrushed()) {
            int num_drops;
            return (num_drops = super.dropBlockAsEntityItem(info, Item.flowerPot)) > 0 ? num_drops + this.dropBlockAsEntityItem(info, getPlantForMeta(info.getMetadata())) : 0;
        } else {
            return 0;
        }
    }
    public static ItemStack getPlantForMeta(int metadata) {
        return metadata == 0 ? null : new ItemStack(Blocks.flowerextend, 1, metadata);
    }
    public static int getMetaForPlant(ItemStack item_stack) {
        return item_stack.itemID == Blocks.flowerextend.blockID ? item_stack.getItemSubtype() : 0;
    }
}
