package net.oilcake.mitelros.block;

import net.minecraft.*;
import net.oilcake.mitelros.item.Materials;
import net.oilcake.mitelros.util.ExperimentalConfig;
import org.spongepowered.asm.mixin.Overwrite;

import java.util.Random;

public class BlockMetalbench extends BlockWorkbench{
    private IIcon workbenchIconTop;
    private IIcon icon_flint_top;
    private IIcon icon_obsidian_top;
    protected IIcon[] front_icons = new IIcon[9];
    protected IIcon[] side_icons = new IIcon[9];
    public static final Material[] tool_materials = new Material[] {
            Material.copper,
            Material.silver,
            Material.gold,
            Material.iron,
            Materials.nickel,
            Material.ancient_metal,
            Material.mithril,
            Materials.tungsten,
            Material.adamantium};
    private final Random random;
    protected BlockMetalbench(int par1) {
        super(par1);
        this.setHardness(BlockHardness.workbench);
        this.setCreativeTab(CreativeModeTab.tabDecorations);
        this.random = new Random();
    }

    public String getMetadataNotes() {
        String[] array = new String[this.getNumSubBlocks()];

        for(int i = 0; i < array.length; ++i) {
            array[i] = i + "=" + getToolMaterial(i).getCapitalizedName() + " Tools";
        }

        return StringHelper.implode(array, ", ", true, true);
    }

    public IIcon a(int side, int metadata) {
        if (side == 0) {
            return Block.planks.m(side);
        } else if (side == 1) {
            return this.workbenchIconTop;
        } else {
            return side != 2 && side != 3 ? this.side_icons[metadata] : this.front_icons[metadata];
        }
    }

    public static Material getToolMaterial(int metadata) {
        return tool_materials[metadata];
    }

    public int getBlockSubtypeUnchecked(int metadata) {
        return metadata;
    }

    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, EnumFace face, float offset_x, float offset_y, float offset_z) {
        if (player.onServer() && world.isAirOrPassableBlock(x, y + 1, z, false)) {
            Block block_above = world.getBlock(x, y + 1, z);
            if (block_above == null || !block_above.hidesAdjacentSide(world, x, y + 1, z, this, 1)) {
                player.displayGUIWorkbench(x, y, z);
            }
        }

        return true;
    }
    public boolean playerSwingsOnBlockActivated(boolean empty_handed) {
        return false;
    }

    public boolean isPortable(World world, EntityLiving entity_living_base, int x, int y, int z) {
        return true;
    }

    public int dropBlockAsEntityItem(BlockBreakInfo info) {
        if (!(Boolean) ExperimentalConfig.TagConfig.TagBenchingV2.ConfigValue && !info.wasExploded()) {
            return super.dropBlockAsEntityItem(info);
        } else {
            int metadata = info.getMetadata();
            if (info.wasExploded()) {
                int quantity_drops = 2 + (int)(this.random.nextFloat() * 4.0F);
                this.dropBlockAsEntityItem(info, Item.sinew.itemID, 0, 1, 1.5F);
                if (this.random.nextInt(9 - metadata) == 0) {
                    this.dropBlockAsEntityItem(info, Item.getMatchingItem(ItemIngot.class, tool_materials[metadata]));
                } else {
                    this.dropBlockAsEntityItem(info, Item.getMatchingItem(ItemNugget.class, tool_materials[metadata]).itemID, 0, quantity_drops, 1.0F);
                }
                this.dropBlockAsEntityItem(info, Item.stick.itemID, 0, 1, 1.5F);
            } else {
                this.dropBlockAsEntityItem(info, Item.leather.itemID, 0, 1, 1.0F);
                this.dropBlockAsEntityItem(info, Item.getMatchingItem(ItemIngot.class, tool_materials[metadata]));
                this.dropBlockAsEntityItem(info, Block.planks.blockID, 0);
                this.dropBlockAsEntityItem(info, Item.stick.itemID);
            }
            return 0;
        }
    }

    protected void dropXpOnBlockBreak(World par1World, int par2, int par3, int par4, int par5) {
    }

    public static ItemStack getBlockComponent(int metadata) {
        return null;
    }

    public boolean isValidMetadata(int metadata) {
        return metadata >= 0 && metadata < tool_materials.length;
    }

    public void a(mt par1IconRegister) {
        this.icon_flint_top = par1IconRegister.a("crafting_table/flint/top");
        this.icon_obsidian_top = par1IconRegister.a("crafting_table/obsidian/top");
        this.workbenchIconTop = par1IconRegister.a("crafting_table_top");

        for(int i = 0; i < this.front_icons.length; ++i) {
            this.front_icons[i] = par1IconRegister.a("crafting_table/" + getToolMaterial(i).toString() + "/front");
            this.side_icons[i] = par1IconRegister.a("crafting_table/" + getToolMaterial(i).toString() + "/side");
        }

    }
}
