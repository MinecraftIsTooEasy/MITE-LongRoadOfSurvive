//package net.oilcake.mitelros.mixins.block;
//
//import net.minecraft.*;
//import net.oilcake.mitelros.block.Blocks;
//import net.oilcake.mitelros.item.Items;
//import net.oilcake.mitelros.item.Materials;
//import org.spongepowered.asm.mixin.*;
//import org.spongepowered.asm.mixin.injection.At;
//import org.spongepowered.asm.mixin.injection.Inject;
//import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
//
//import static net.oilcake.mitelros.item.Items.arrowTungsten;
//
//@Mixin(BlockWorkbench.class)
//public class BlockWorkbenchMixin extends Block{
//    @Shadow
//    @Mutable
//    @Final
//    private static Material[] tool_materials;
//    @Shadow
//    protected IIcon[] side_icons;
//    @Shadow
//    private IIcon displayOnCreativeTab;
//    @Shadow
//    private IIcon[] front_icons;
//    @Shadow
//    private IIcon icon_flint_top;
//    @Shadow
//    private IIcon icon_obsidian_top;
//
////    @Inject(method = "<init>",at = @At("RETURN"))
////    private void injectInit(CallbackInfo callback){
////        this.setMinHarvestLevel(getMinHarvestLevel());
////    }
//
//    public int dropBlockAsEntityItem(BlockBreakInfo info) {
//        return this.dropBlockAsEntityItem(info, this.getItemNugget().itemID, 0, 4, 3.0F);
//    }
////    public int getMinHarvestLevel() {
////        if (this.getMaterial() == Material.flint) {
////            return 1;
////        } else if (this.getMaterial() == Material.obsidian) {
////            return 1;
////        } else if (this.getMaterial() == Material.silver) {
////            return Item.silverNugget.getHardestMetalMaterial().getMinHarvestLevel();
////        }  else if (this.getMaterial()== Materials.nickel) {
////            return Items.nickelNugget.getHardestMetalMaterial().getMinHarvestLevel();
////        } else if (this.getMaterial() == Material.mithril) {
////            return Item.mithrilNugget.getHardestMetalMaterial().getMinHarvestLevel();
////        } else if (this.getMaterial() == Materials.tungsten){
////            return Items.tungstenNugget.getHardestMetalMaterial().getMinHarvestLevel();
////        }
////        return this.getMaterial() == Material.ancient_metal ? Item.ancientMetalNugget.getHardestMetalMaterial().getMinHarvestLevel() : 0;
////    }
//
//
//    public Item getItemNugget() {
//        if (this.getMaterial() == Material.flint) {
//            return Item.chipFlint;
//        } else if (this.getMaterial() == Material.obsidian) {
//            return Item.shardObsidian;
//        } else if (this.getMaterial() == Material.silver) {
//            return Item.silverNugget;
//        }  else if (this.getMaterial()== Materials.nickel) {
//            return Items.nickelNugget;
//        } else if (this.getMaterial() == Material.mithril) {
//            return Item.mithrilNugget;
//        } else if (this.getMaterial() == Materials.tungsten){
//            return Items.tungstenNugget;
//        }
//        return this.getMaterial() == Material.ancient_metal ? Item.ancientMetalNugget : null;
//    }
//
//    public Material getMaterial() {
//        Material material = null;
//        for (int var2 = 4; var2 < this.front_icons.length - 3; ++var2) {
//            material = BlockWorkbench.getToolMaterial(var2);
//        }
//        return material;
//    }
//
//
//
//    @Inject(method = "<clinit>",at = @At("RETURN"))
//    private static void injectClinit(CallbackInfo callback){
//        tool_materials = new Material[]{Material.flint, Material.silver, Material.ancient_metal, Material.mithril,
//                 Materials.nickel, Materials.tungsten, Material.obsidian};
//    }
//
////    @Override
////    @SoftOverride
////    public String getItemDisplayName(ItemStack itemStack) {
////        return Translator.get("tile.toolbench." + BlockWorkbench.getToolMaterial(itemStack.getItemSubtype()).getName() + ".name");
////    }
//    protected BlockWorkbenchMixin(int par1, Material par2Material, BlockConstants constants) {
//        super(par1, par2Material, constants);
//    }
//
//    @Overwrite
//    public IIcon a(int side, int metadata) {
//        if (metadata < 4) {
//            return side == 1 ? this.icon_flint_top : Block.wood.a(side, metadata);
//        } else if (metadata > 11) {
//            return side == 1 ? this.icon_obsidian_top : Block.wood.a(side, metadata - 11);
//        } else if (side == 0) {
//            return Block.planks.m(side);
//        } else if (side == 1) {
//            return this.displayOnCreativeTab;
//        } else {
//            return side != 2 && side != 3 ? this.side_icons[metadata] : this.front_icons[metadata];
//        }
//    }
//
//    @Overwrite
//    public void a(mt par1IconRegister) {
//        this.icon_flint_top = par1IconRegister.a("crafting_table/flint/top");
//        this.icon_obsidian_top = par1IconRegister.a("crafting_table/obsidian/top");
//        this.displayOnCreativeTab = par1IconRegister.a("crafting_table_top");
//
//        for(int i = 4; i < this.front_icons.length - 3; ++i) {
//            this.front_icons[i] = par1IconRegister.a("crafting_table/" + BlockWorkbench.getToolMaterial(i).toString() + "/front");
//            this.side_icons[i] = par1IconRegister.a("crafting_table/" + BlockWorkbench.getToolMaterial(i).toString() + "/side");
//        }
//
//    }
//}
// Code 0.0.9
//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//Code 0.0.8 below

package net.oilcake.mitelros.mixins.block;

import net.minecraft.Block;
import net.minecraft.BlockWorkbench;
import net.minecraft.IIcon;
import net.minecraft.Material;
import net.minecraft.mt;
import net.oilcake.mitelros.item.Materials;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({BlockWorkbench.class})
public class BlockWorkbenchMixin extends BlockMixin {
    @Shadow
    @Mutable
    @Final
    private static Material[] tool_materials;
    @Shadow
    protected IIcon[] side_icons;
    @Shadow
    private IIcon displayOnCreativeTab;
    @Shadow
    private IIcon[] front_icons;
    @Shadow
    private IIcon icon_flint_top;
    @Shadow
    private IIcon icon_obsidian_top;

    public BlockWorkbenchMixin() {
    }

    @Inject(
            method = {"<clinit>"},
            at = {@At("RETURN")}
    )
    private static void injectClinit(CallbackInfo callback) {
        tool_materials = new Material[]{Material.flint, Material.copper, Material.silver, Material.gold, Material.iron, Material.ancient_metal, Material.mithril, Material.adamantium, Materials.nickel, Material.obsidian};
    }

    @Overwrite
    public IIcon a(int side, int metadata) {
        if (metadata < 4) {
            return side == 1 ? this.icon_flint_top : Block.wood.a(side, metadata);
        } else if (metadata > 11) {
            return side == 1 ? this.icon_obsidian_top : Block.wood.a(side, metadata - 11);
        } else if (side == 0) {
            return Block.planks.m(side);
        } else if (side == 1) {
            return this.displayOnCreativeTab;
        } else {
            return side != 2 && side != 3 ? this.side_icons[metadata] : this.front_icons[metadata];
        }
    }

    @Overwrite
    public void a(mt par1IconRegister) {
        this.icon_flint_top = par1IconRegister.a("crafting_table/flint/top");
        this.icon_obsidian_top = par1IconRegister.a("crafting_table/obsidian/top");
        this.displayOnCreativeTab = par1IconRegister.a("crafting_table_top");

        for(int i = 4; i < this.front_icons.length - 3; ++i) {
            this.front_icons[i] = par1IconRegister.a("crafting_table/" + BlockWorkbench.getToolMaterial(i).toString() + "/front");
            this.side_icons[i] = par1IconRegister.a("crafting_table/" + BlockWorkbench.getToolMaterial(i).toString() + "/side");
        }

    }
}

