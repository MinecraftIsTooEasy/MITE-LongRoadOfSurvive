package net.oilcake.mitelros.mixins.block;

import net.minecraft.*;
import net.oilcake.mitelros.item.Materials;
import net.oilcake.mitelros.util.ExperimentalConfig;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Random;

@Mixin(BlockWorkbench.class)
public class BlockWorkbenchMixin extends Block{
    protected IIcon[] front_icons = new IIcon[17];
    protected IIcon[] side_icons = new IIcon[17];

    private final Random random = new Random();

    protected BlockWorkbenchMixin(int par1, Material par2Material, BlockConstants constants) {
        super(par1, par2Material, constants);
    }

    public int dropBlockAsEntityItem(BlockBreakInfo info) {
        if(ExperimentalConfig.TagConfig.TagBenchingV2.ConfigValue || info.wasExploded()){
            if(info.wasExploded()){
                int quantity_drops = 2 + (int) (this.random.nextFloat() * 4);
                if (info.getMetadata() < 4) {
                    this.dropBlockAsEntityItem(info, Item.chipFlint.itemID, 0, quantity_drops / 2, 1.0F);
                } else if (info.getMetadata() == 13 || info.getMetadata() == 14 || info.getMetadata() == 15) {
                    if(this.random.nextInt(16) == 0){
                        this.dropBlockAsEntityItem(info, Block.obsidian.blockID);
                    } else{
                        this.dropBlockAsEntityItem(info, Item.shardObsidian.itemID, 0, quantity_drops, 1.0F);
                    }
                } else {
                    this.dropBlockAsEntityItem(info,Item.sinew.itemID, 0, 1, 1.5F);
                    if(info.getMetadata() == 4){
                        if(this.random.nextInt(8) == 0){
                            this.dropBlockAsEntityItem(info, Item.getMatchingItem(ItemIngot.class, Material.copper));
                        } else {
                            this.dropBlockAsEntityItem(info, Item.getMatchingItem(ItemNugget.class, Material.copper).itemID, 0, quantity_drops, 1.0F);
                        }
                    } else if (info.getMetadata() == 5) {
                        if(this.random.nextInt(8) == 0){
                            this.dropBlockAsEntityItem(info, Item.getMatchingItem(ItemIngot.class, Material.silver));
                        } else {
                            this.dropBlockAsEntityItem(info, Item.getMatchingItem(ItemNugget.class, Material.silver).itemID, 0, quantity_drops, 1.0F);
                        }
                    } else if (info.getMetadata() == 6) {
                        if(this.random.nextInt(8) == 0){
                            this.dropBlockAsEntityItem(info, Item.getMatchingItem(ItemIngot.class, Material.gold));
                        } else {
                            this.dropBlockAsEntityItem(info, Item.getMatchingItem(ItemNugget.class, Material.gold).itemID, 0, quantity_drops, 1.0F);
                        }
                    } else if (info.getMetadata() == 7) {
                        if(this.random.nextInt(6) == 0){
                            this.dropBlockAsEntityItem(info, Item.getMatchingItem(ItemIngot.class, Material.iron));
                        } else {
                            this.dropBlockAsEntityItem(info, Item.getMatchingItem(ItemNugget.class, Material.iron).itemID, 0, quantity_drops, 1.0F);
                        }
                    } else if (info.getMetadata() == 8) {
                        if(this.random.nextInt(4) == 0){
                            this.dropBlockAsEntityItem(info, Item.getMatchingItem(ItemIngot.class, Material.ancient_metal));
                        } else {
                            this.dropBlockAsEntityItem(info, Item.getMatchingItem(ItemNugget.class, Material.ancient_metal).itemID, 0, quantity_drops, 1.0F);
                        }
                    } else if (info.getMetadata() == 9) {
                        if(this.random.nextInt(3) == 0){
                            this.dropBlockAsEntityItem(info, Item.getMatchingItem(ItemIngot.class, Material.mithril));
                        } else {
                            this.dropBlockAsEntityItem(info, Item.getMatchingItem(ItemNugget.class, Material.mithril).itemID, 0, quantity_drops, 1.0F);
                        }
                    } else if (info.getMetadata() == 10) {
                        this.random.nextInt(1);
                        this.dropBlockAsEntityItem(info, Item.getMatchingItem(ItemIngot.class, Material.adamantium));
                    } else if (info.getMetadata() == 11) {
                        if(this.random.nextInt(6) == 0){
                            this.dropBlockAsEntityItem(info, Item.getMatchingItem(ItemIngot.class, Materials.nickel));
                        } else {
                            this.dropBlockAsEntityItem(info, Item.getMatchingItem(ItemNugget.class, Materials.nickel).itemID, 0, quantity_drops, 1.0F);
                        }
                    } else if (info.getMetadata() == 12) {
                        if(this.random.nextInt(2) == 0){
                            this.dropBlockAsEntityItem(info, Item.getMatchingItem(ItemIngot.class, Materials.tungsten));
                        } else {
                            this.dropBlockAsEntityItem(info, Item.getMatchingItem(ItemNugget.class, Materials.tungsten).itemID, 0, quantity_drops, 1.0F);
                        }
                    }
                }
                this.dropBlockAsEntityItem(info, Item.stick.itemID, 0, 1, 1.5F);
            } else{
                if (info.getMetadata() < 4) {
                    this.dropBlockAsEntityItem(info, Item.knifeFlint.itemID);
                    this.dropBlockAsEntityItem(info, Block.wood.blockID, info.getMetadata(),1,1.0F);
                } else if (info.getMetadata() > 12) {
                    this.dropBlockAsEntityItem(info, Item.knifeObsidian.itemID);
                    this.dropBlockAsEntityItem(info, Block.wood.blockID, info.getMetadata() - 13,1,1.0F);
                } else {
                    this.dropBlockAsEntityItem(info,Item.leather.itemID, 0, 1, 1.0F);
                    if(info.getMetadata() == 4){
                        this.dropBlockAsEntityItem(info, Item.getMatchingItem(ItemIngot.class, Material.copper));
                    } else if (info.getMetadata() == 5) {
                        this.dropBlockAsEntityItem(info, Item.getMatchingItem(ItemIngot.class, Material.silver));
                    } else if (info.getMetadata() == 6) {
                        this.dropBlockAsEntityItem(info, Item.getMatchingItem(ItemIngot.class, Material.gold));
                    } else if (info.getMetadata() == 7) {
                        this.dropBlockAsEntityItem(info, Item.getMatchingItem(ItemIngot.class, Material.iron));
                    } else if (info.getMetadata() == 8) {
                        this.dropBlockAsEntityItem(info, Item.getMatchingItem(ItemIngot.class, Material.ancient_metal));
                    } else if (info.getMetadata() == 9) {
                        this.dropBlockAsEntityItem(info, Item.getMatchingItem(ItemIngot.class, Material.mithril));
                    } else if (info.getMetadata() == 10) {
                        this.dropBlockAsEntityItem(info, Item.getMatchingItem(ItemIngot.class, Material.adamantium));
                    } else if (info.getMetadata() == 11) {
                        this.dropBlockAsEntityItem(info, Item.getMatchingItem(ItemIngot.class, Materials.nickel));
                    } else if (info.getMetadata() == 12) {
                        this.dropBlockAsEntityItem(info, Item.getMatchingItem(ItemIngot.class, Materials.tungsten));
                    }
                    this.dropBlockAsEntityItem(info, Block.planks.blockID,0);
                    this.dropBlockAsEntityItem(info, Item.stick.itemID);
                }
            }
            return 0;
        }
        else{
            return super.dropBlockAsEntityItem(info);
        }
    }

    protected void dropXpOnBlockBreak(World par1World, int par2, int par3, int par4, int par5) {
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

