//package net.oilcake.mitelros.mixins.block;
//
//import net.minecraft.*;
//import org.spongepowered.asm.mixin.Mixin;
//import org.spongepowered.asm.mixin.Overwrite;
//import org.spongepowered.asm.mixin.Shadow;
//
//@Mixin(BlockFurnace.class)
//public class BlockFurnaceMixin {
//    @Shadow
//    private static boolean keepFurnaceInventory;
//    @Shadow
//    public final EnumDirection getDirectionFacing(int metadata) {
//        return null;
//    }
//
//    @Overwrite
//    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, EnumFace face, float offset_x, float offset_y, float offset_z) {
//        if (player.onServer() && world.isAirOrPassableBlock(x, y + 1, z, true)) {
//            return false;
//        } else {
//            if (player.onServer()) {
//                TileEntityFurnace tile_entity = (TileEntityFurnace)world.getBlockTileEntity(x, y, z);
//                if (tile_entity != null) {
//                    player.displayGUIWorkbench(x, y-2, z);
//                }
//            }
//
//            return true;
//        }
//    }
//
//}
