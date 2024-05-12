//package net.oilcake.mitelros.mixins.block;
//
//import net.minecraft.*;
//import net.oilcake.mitelros.block.BlockMetalbench;
//import net.oilcake.mitelros.block.BlockWoodbench;
//import net.oilcake.mitelros.block.Blocks;
//import net.oilcake.mitelros.item.Materials;
//import net.oilcake.mitelros.util.ExperimentalConfig;
//import net.xiaoyu233.fml.util.ReflectHelper;
//import org.spongepowered.asm.mixin.*;
//import org.spongepowered.asm.mixin.injection.At;
//import org.spongepowered.asm.mixin.injection.Inject;
//import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
//
//import java.util.Random;
//
//@Mixin(BlockWorkbench.class)
//public class BlockWorkbenchMixin extends Block{
//    protected IIcon[] front_icons = new IIcon[17];
//    protected IIcon[] side_icons = new IIcon[17];
//
//    private final Random random = new Random();
//
//    protected BlockWorkbenchMixin(int par1, Material par2Material, BlockConstants constants) {
//        super(par1, par2Material, constants);
//    }
//    @Inject(method = "<init>(I)V", at = @At("RETURN"))
//    private void injectInit(int par1, CallbackInfo callbackInfo){
//        this.setMinHarvestLevel(1);
//    }
//
//    public int dropBlockAsEntityItem(BlockBreakInfo info) {
//        return 0;
//    }
//
//    protected void dropXpOnBlockBreak(World par1World, int par2, int par3, int par4, int par5) {
//    }
//
//    @Inject(method = "<clinit>", at = @At("RETURN"))
//    private static void injectClinit(CallbackInfo callback) {
//        tool_materials = new Material[]{
//                Material.flint,
//                Material.obsidian,
//                Material.copper,
//                Material.silver,
//                Material.gold,
//                Material.iron,
//                Materials.nickel,
//                Material.ancient_metal,
//                Material.mithril,
//                Materials.tungsten,
//                Material.adamantium
//        };
//    }
//    @Overwrite
//    public static ItemStack getBlockComponent(int metadata) {
//        Material tool_material = getToolMaterial(metadata);
//        if (tool_material == Material.flint) {
//            return new ItemStack(Block.wood, 1, metadata);
//        }else if (tool_material == Material.obsidian) {
//            return new ItemStack(Block.wood, 1, metadata - 4);
//        } else {
//            return null;
//        }
//    }
//    @Overwrite
//    public static Material getToolMaterial(int metadata) {
//        System.out.println(metadata);
//        if(metadata >= 17){
//            return Material.rusted_iron;
//        }
//        if(metadata < 8){
//            return BlockWoodbench.getToolMaterial(metadata);
//        }else {
//            return BlockMetalbench.getToolMaterial(metadata - 8);
//        }
//    }
//    @Overwrite
//    public boolean isValidMetadata(int metadata) {
//        return metadata >= 0 && metadata < 17;
//    }
//    @Overwrite
//    public IIcon a(int side, int metadata) {
//        return Block.planks.m(side);
//    }
//
//    @Shadow
//    @Mutable
//    @Final
//    private static Material[] tool_materials;
//    @Shadow
//    private IIcon displayOnCreativeTab;
//    @Shadow
//    private IIcon icon_flint_top;
//    @Shadow
//    private IIcon icon_obsidian_top;
//}
//
