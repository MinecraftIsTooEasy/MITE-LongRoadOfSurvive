package net.oilcake.mitelros.block;

import net.minecraft.*;

public class BlockBlastFurnace extends BlockFurnace {
    private Material material;

    protected BlockBlastFurnace(int par1,Material par2, boolean par3) {
        super(par1, par2, par3);
        material = par2;
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
}
