package net.oilcake.mitelros.block;

import net.minecraft.*;
import net.oilcake.mitelros.util.ExperimentalConfig;
import org.spongepowered.asm.mixin.Overwrite;

public class BlockBlastFurnace extends BlockFurnace {
    private Material material;

    protected BlockBlastFurnace(int par1,Material par2, boolean par3) {
        super(par1, par2, par3);
        material = par2;
    }
    protected void dropXpOnBlockBreak(World par1World, int par2, int par3, int par4, int par5) {

    }

//    public static int getMaterialLevel (Material material) {
//        if(material == Material.stone) {
//            level = 1;
//        } else if(material == Material.obsidian) {
//            level = 2;
//        } else if(material == Material.netherrack) {
//            level = 3;
//        }
//        return level;
//    }
//    public static Material getMaterial (Material material) {
//        if(level == 1) {
//            material = Material.stone;
//        } else if(level == 2) {
//            material = Material.obsidian;
//        } else if(level == 3) {
//            material = Material.netherrack;
//        }
//        return material;
//    }


    @Override
    public void a(mt mt) {
        this.cW = mt.a("blastfurnace/" + material + "/side");
        this.e = mt.a(this.isActive ? "blastfurnace/" + material + "/on" : "blastfurnace/" + material + "/off");
        this.d = mt.a("blastfurnace/" + material + "/top");
    }

    @Override
    public int getIdleBlockID() {
        return material == Material.stone ? Blocks.blastFurnaceStoneIdle.blockID :
                (material == Material.obsidian ? Blocks.blastFurnaceObsidianIdle.blockID :
                         (material == Material.netherrack ? Blocks.blastFurnaceNetherrackIdle.blockID : 0));
    }

    @Override
    public int getActiveBlockID() {
        return material == Material.stone ? Blocks.blastFurnaceStoneBurning.blockID :
                (material == Material.obsidian ? Blocks.blastFurnaceObsidianBurning.blockID :
                        (material == Material.netherrack ? Blocks.blastFurnaceNetherrackBurning.blockID : 0));
    }

    @Override
    public int getMaxHeatLevel() {
        return material == Material.stone ? 2 : (material == Material.obsidian ? 3 :(material == Material.netherrack ? 4 : 0));
    }
    @Override
    public int dropBlockAsEntityItem(BlockBreakInfo info) {
        Block furnace_block = Block.getBlock(this.getIdleBlockID());
        if(ExperimentalConfig.TagConfig.TagBenchingV2.ConfigValue){
            if(!info.wasExploded()){
                if(furnace_block == Blocks.blastFurnaceNetherrackIdle){
                    this.dropBlockAsEntityItem(info,Block.netherrack.blockID,0,8,1.0F);
                    this.dropBlockAsEntityItem(info,Item.ingotAdamantium.itemID,0,3,1.0F);
                }else if(furnace_block == Blocks.blastFurnaceObsidianIdle){
                    this.dropBlockAsEntityItem(info,Block.obsidian.blockID,0,8,1.0F);
                    this.dropBlockAsEntityItem(info,Item.ingotMithril.itemID,0,3,1.0F);
                }else if(furnace_block == Blocks.blastFurnaceStoneIdle){
                    this.dropBlockAsEntityItem(info,Block.cobblestone.blockID,0,8,1.0F);
                    this.dropBlockAsEntityItem(info,Item.ingotIron.itemID,0,3,1.0F);
                }else{
                    return 0;
                }
            }
            else{
                if(furnace_block == Blocks.blastFurnaceNetherrackIdle){
                    this.dropBlockAsEntityItem(info,Block.netherrack.blockID,0,1,1.3F);
                    this.dropBlockAsEntityItem(info,Item.ingotAdamantium.itemID,0,1,1.3F);
                }else if(furnace_block == Blocks.blastFurnaceObsidianIdle){
                    this.dropBlockAsEntityItem(info,Block.obsidian.blockID,0,1,1.0F);
                    this.dropBlockAsEntityItem(info,Item.shardObsidian.itemID,0,2,1.3F);
                    this.dropBlockAsEntityItem(info,Item.ingotMithril.itemID,0,1,1.3F);
                    this.dropBlockAsEntityItem(info,Item.mithrilNugget.itemID,0,2,1.3F);
                }else if(furnace_block == Blocks.blastFurnaceStoneIdle){
                    this.dropBlockAsEntityItem(info,Block.cobblestone.blockID,0,1,1.3F);
                    this.dropBlockAsEntityItem(info,Item.ingotIron.itemID,0,1,0.5F);
                    this.dropBlockAsEntityItem(info,Item.ironNugget.itemID,0,2,1.3F);
                }else{
                    return 0;
                }
            }
            return 0;
        } else{
            if(!info.wasExploded()){
                return super.dropBlockAsEntityItem(info);
            }
            else{
                if(furnace_block == Blocks.blastFurnaceNetherrackIdle){
                    this.dropBlockAsEntityItem(info,Block.netherrack.blockID,0,1,1.3F);
                    this.dropBlockAsEntityItem(info,Item.ingotAdamantium.itemID,0,1,1.3F);
                }else if(furnace_block == Blocks.blastFurnaceObsidianIdle){
                    this.dropBlockAsEntityItem(info,Block.obsidian.blockID,0,1,1.0F);
                    this.dropBlockAsEntityItem(info,Item.shardObsidian.itemID,0,2,1.3F);
                    this.dropBlockAsEntityItem(info,Item.ingotMithril.itemID,0,1,1.3F);
                    this.dropBlockAsEntityItem(info,Item.mithrilNugget.itemID,0,2,1.3F);
                }else if(furnace_block == Blocks.blastFurnaceStoneIdle){
                    this.dropBlockAsEntityItem(info,Block.cobblestone.blockID,0,1,1.3F);
                    this.dropBlockAsEntityItem(info,Item.ingotIron.itemID,0,1,0.5F);
                    this.dropBlockAsEntityItem(info,Item.ironNugget.itemID,0,2,1.3F);
                }else{
                    return 0;
                }
            }
        }
        return 0;
    }
}
