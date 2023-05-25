package net.oilcake.mitelros.mixins.block;

import net.minecraft.*;
import net.oilcake.mitelros.block.Blocks;
import net.oilcake.mitelros.item.Items;
import net.oilcake.mitelros.item.Materials;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Random;

@Mixin(BlockWorkbench.class)
public class BlockWorkbenchMixin extends Block{

    protected IIcon[] front_icons = new IIcon[17];
    protected IIcon[] side_icons = new IIcon[17];

    protected BlockWorkbenchMixin(int par1, Material par2Material, BlockConstants constants) {
        super(par1, par2Material, constants);
    }
    public int dropBlockAsEntityItem(BlockBreakInfo info) {
        return super.dropBlockAsEntityItem(info);
    }
    @Inject(method = "<clinit>", at = @At("RETURN"))
    private static void injectClinit(CallbackInfo callback) {
        tool_materials = new Material[]{Material.flint,Material.copper, Material.silver, Material.gold,Material.iron, Material.ancient_metal, Material.mithril, Material.adamantium,
                Materials.nickel, Materials.tungsten, Material.obsidian};
    }

    @Overwrite
    public IIcon a(int side, int metadata) {
        if (metadata < 4) {
            return side == 1 ? this.icon_flint_top : Block.wood.a(side, metadata);
        } else if (metadata >= 13) {
            return side == 1 ? this.icon_obsidian_top : Block.wood.a(side, metadata - 13);
        } else if (side == 0) {
            return Block.planks.m(side);
        } else if (side == 1) {
            return this.displayOnCreativeTab;
        } else {
            return side != 2 && side != 3 ? this.side_icons[metadata] : this.front_icons[metadata];
        }
    }
    @Overwrite
    public static Material getToolMaterial(int metadata) {
        if (metadata > 12) {
            return tool_materials[10];
        } else {
            return metadata < 4 ? tool_materials[0] : tool_materials[metadata - 3];
        }
    }
    @Overwrite
    public static ItemStack getBlockComponent(int metadata) {
        Material tool_material = getToolMaterial(metadata);
        if (tool_material == Material.flint) {
            return new ItemStack(Block.wood, 1, metadata);
        } else {
            return tool_material == Material.obsidian ? new ItemStack(Block.wood, 1, metadata - 11) : null;
        }
    }
    @Overwrite
    public boolean isValidMetadata(int metadata) {
        return metadata >= 0 && metadata < 17;
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
    private IIcon displayOnCreativeTab;
    @Shadow
    private IIcon icon_flint_top;
    @Shadow
    private IIcon icon_obsidian_top;
}

