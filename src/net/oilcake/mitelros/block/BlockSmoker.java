package net.oilcake.mitelros.block;

import net.minecraft.BlockFurnace;
import net.minecraft.CreativeModeTab;
import net.minecraft.Material;
import net.minecraft.mt;

public class BlockSmoker extends BlockFurnace{
    protected BlockSmoker(int par1, boolean par2) {
        super(par1, Material.stone, par2);
        this.setCreativeTab(CreativeModeTab.tabDecorations);
    }

    @Override
    public void a(mt mt) {
        this.e = mt.a(this.isActive ? "smoker/smoker_front_on" : "smoker/smoker_front_off");
        this.d = mt.a("smoker/smoker_top");
        //TEXTURE_BOTOTM = mt.a("smoker/smoker_bottom");
        this.cW = mt.a("smoker/smoker_side");
    }

    @Override
    public int getMaxHeatLevel() {
        return 2;
    }
    @Override
    public int getIdleBlockID() {
        return Blocks.blockSmokerIdle.blockID;
    }

    @Override
    public int getActiveBlockID() {
        return Blocks.blockSmokerBurning.blockID;
    }
}




