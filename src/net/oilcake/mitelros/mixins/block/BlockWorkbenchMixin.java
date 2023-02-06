package net.oilcake.mitelros.mixins.block;

import net.minecraft.*;
import net.oilcake.mitelros.item.Materials;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Random;

@Mixin(BlockWorkbench.class)
public class BlockWorkbenchMixin extends Block{

    protected BlockWorkbenchMixin(int par1, Material par2Material, BlockConstants constants) {
        super(par1, par2Material, constants);
    }


//    @Inject(method = "<init>", at = @At("INVOKE"))
//    private void injectInit(int par1, CallbackInfo callback) {
//        this.setMinHarvestLevel(4);
//    }



    public void breakBlock(World world, int x, int y, int z, int block_id, int metadata) {
        super.breakBlock(world, x, y, z, block_id, metadata);
        Random random = new Random();
        BlockBreakInfo info = new BlockBreakInfo(world, x, y, z);
        if (random.nextInt(2) == 0) {
            this.dropBlockAsEntityItem(info, Item.silk.itemID, 0, 4, 0.5F);
        }
//        BlockBreakInfo info = new BlockBreakInfo(world, x, y, z);
//        this.dropBlockAsEntityItem(info, Item.silk.itemID, 0, 2, 1.0F);
//        for(int i = 0; i < Block.workbench.getNumSubBlocks(); ++i) {
//            Material material = BlockWorkbench.getToolMaterial(i);
//            Random random = new Random();
//            if (material == Material.flint) {
//                this.dropBlockAsEntityItem(info, Item.chipFlint.itemID, 0, random.nextInt(4), 0);
//            } else if (material == Material.obsidian) {
//                this.dropBlockAsEntityItem(info, Item.shardObsidian.itemID, 0, random.nextInt(4), 0);
//            } else if (material == Material.silver) {
//                this.dropBlockAsEntityItem(info, Item.silverNugget.itemID, 0, random.nextInt(4), 0);
//            }  else if (material== Materials.nickel) {
//                this.dropBlockAsEntityItem(info, Items.nickelNugget.itemID, 0, random.nextInt(4), 0);
//            } else if (material == Material.mithril) {
//                this.dropBlockAsEntityItem(info, Items.tungstenNugget.itemID, 0, random.nextInt(4), 0);
//            } else if (material == Materials.tungsten){
//                this.dropBlockAsEntityItem(info, Items.mithrilNugget.itemID, 0, random.nextInt(4), 0);
//            }else{
//                this.dropBlockAsEntityItem(info, Block.stone.blockID, 0, 3, 3.0F);
//            }
//        }
        world.removeBlockTileEntity(x, y, z);
    }

    public int dropBlockAsEntityItem(BlockBreakInfo info) {
        return this.dropBlockAsEntityItem(info, Block.planks.blockID, 0, 4, 0.5F);
    }
    @Inject(method = "<clinit>", at = @At("RETURN"))
    private static void injectClinit(CallbackInfo callback) {
        tool_materials = new Material[]{Material.flint, Material.silver, Material.gold, Material.ancient_metal, Material.mithril, Material.adamantium,
                Materials.nickel, Materials.tungsten, Material.obsidian};
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
}

