package net.oilcake.mitelros.block;

import net.minecraft.*;
import net.oilcake.mitelros.util.ExperimentalConfig;

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
    @Override
    public int dropBlockAsEntityItem(BlockBreakInfo info) {
        if(ExperimentalConfig.TagConfig.TagBenchingV2.ConfigValue){
            if(info.wasExploded()){
                this.dropBlockAsEntityItem(info,Block.cobblestone.blockID);
                this.dropBlockAsEntityItem(info,Item.stick.itemID,0,1,1.3F);
                return 0;
            }else{
                this.dropBlockAsEntityItem(info,Block.cobblestone.blockID,0,8,1.0F);
                this.dropBlockAsEntityItem(info,Block.wood.blockID,0,4,1.0F);
                return 0;
            }
        }else{
            if(info.wasExploded()){
                this.dropBlockAsEntityItem(info,Block.cobblestone.blockID);
                this.dropBlockAsEntityItem(info,Item.stick.itemID,0,1,1.3F);
                return 0;
            }else{
                return super.dropBlockAsEntityItem(info);
            }
        }
    }


}






