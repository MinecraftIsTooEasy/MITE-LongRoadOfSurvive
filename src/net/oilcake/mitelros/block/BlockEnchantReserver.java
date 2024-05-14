package net.oilcake.mitelros.block;

import net.minecraft.*;
import net.oilcake.mitelros.block.enchantreserver.TileEntityEnchantReserver;

import java.util.List;

public class BlockEnchantReserver extends Block implements IContainer {
    private IIcon TEXTURE_TOP;
    private IIcon TEXTURE_BOTTOM;
    private IIcon TEXTURE_SIDE;

    protected BlockEnchantReserver(int par1) {
        super(par1, Material.anvil, new BlockConstants());
        this.setCreativeTab(CreativeModeTab.tabDecorations);
        this.setMaxStackSize(1);
        this.setLightOpacity(0);
        this.setLightValue(0.75F);
    }

    @Override
    public IIcon a(int side, int metadata) {
        switch (side) {
            case 1:
                return TEXTURE_TOP;
            case 0:
                return TEXTURE_BOTTOM;
            case 2:
            case 3:
            case 5:
            case 4:
                return TEXTURE_SIDE;
        }
        return super.a(side, metadata);
    }

    @Override
    public void a(mt mt) {
        TEXTURE_TOP = mt.a("enchant_reserver/top");
        TEXTURE_BOTTOM = mt.a("enchant_reserver/bottom");
        TEXTURE_SIDE = mt.a("enchant_reserver/side");
    }


    @Override
    public void getItemStacks(int id, CreativeModeTab creative_tabs, List list) {
        super.getItemStacks(id, creative_tabs, list);
    }

    public TileEntity createNewTileEntity(World world) {
        return new TileEntityEnchantReserver();
    }

    public void addItemBlockMaterials(ItemBlock item_block) {
        item_block.addMaterial(Material.iron);
    }
    public boolean isPortable(World world, EntityLiving entity_living_base, int x, int y, int z) {
        return true;
    }

    public void breakBlock(World world, int x, int y, int z, int block_id, int metadata) {
        super.breakBlock(world, x, y, z, block_id, metadata);
        TileEntityEnchantReserver tileEntityEnchantReserver = (TileEntityEnchantReserver) world.getBlockTileEntity(x, y, z);
        tileEntityEnchantReserver.dropAllItems();
        int var1;
        EntityExperienceOrb var3;
        if (!tileEntityEnchantReserver.getWorldObj().isRemote)
        {
            for (var1 = Math.max(tileEntityEnchantReserver.getEXP(), 0) ; var1 > 0; tileEntityEnchantReserver.getWorldObj().spawnEntityInWorld(var3))
            {
                int var2 = EntityExperienceOrb.getXPSplit(var1);
                var1 -= var2;
                var3 = new EntityExperienceOrb(tileEntityEnchantReserver.getWorldObj(), x, y + 0.5D, z, var2);
            }
        }
        world.removeBlockTileEntity(x, y, z);
    }

    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, EnumFace face, float offset_x, float offset_y, float offset_z) {
        if (!world.isAirOrPassableBlock(x, y + 1, z, false)) {
            return false;
        } else {
            if (player.onServer()) {
                TileEntityEnchantReserver tile_entity = (TileEntityEnchantReserver)world.getBlockTileEntity(x, y, z);
                if (tile_entity != null && !tile_entity.isUsing()) {
                    player.displayGUIEnchantReserver(x, y, z,tile_entity.getSlots());
                }else {
                    return false;
                }
            }

            return true;
        }
    }
}




