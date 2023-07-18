package net.oilcake.mitelros.block;

import net.minecraft.*;

import java.util.List;

public class BlockEnchantEnhancer extends Block {
    private IIcon TEXTURE_TOP;
    private IIcon TEXTURE_BOTOTM;
    private IIcon TEXTURE_SIDE;
    protected BlockEnchantEnhancer(int par1) {
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
                return TEXTURE_BOTOTM;
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
        TEXTURE_TOP = mt.a("enchant_enhancer/enchant_enhancer_top");
        TEXTURE_BOTOTM = mt.a("enchant_enhancer/enchant_enhancer_bottom");
        TEXTURE_SIDE = mt.a("enchant_enhancer/enchant_enhancer_side");
    }


    @Override
    public void getItemStacks(int id, CreativeModeTab creative_tabs, List list) {
        super.getItemStacks(id, creative_tabs, list);
    }
    public boolean isPortable(World world, EntityLiving entity_living_base, int x, int y, int z) {
        return true;
    }
}
