//package net.oilcake.mitelros.block;
//
//import net.minecraft.*;
//
//import java.util.Random;
//
//public class BlockInvisibleLight extends Block {
//    protected BlockInvisibleLight(int id) {
//        super(id, Material.air, (new BlockConstants()).setNeverHidesAdjacentFaces().setNotAlwaysLegal());
//        this.setTickRandomly(true);
//        this.disableStats();
//    }
//    public String getMetadataNotes() {
//        return null;
//    }
//    public boolean canCollideCheck(int par1, boolean par2) {
//        return false;
//    }
//
//    public boolean isValidMetadata(int metadata) {
//        return metadata == 0;
//    }
//
//    public void onBlockAdded(World par1World, int par2, int par3, int par4) {
//        par1World.scheduleBlockUpdate(par2, par3, par4, this.blockID, 2);
//    }
//
//    public boolean updateTick(World par1World, int par2, int par3, int par4, Random par5Random) {
//        par1World.setBlockToAir(par2, par3, par4);
//        return true;
//    }
//
//    public void a(mt par1IconRegister) {
//        this.cW = par1IconRegister.a(this.E());
//    }
//
//    public IIcon c(int par1) {
//        return this.cW;
//    }
//
//    public IIcon a(int par1, int par2) {
//        return this.cW;
//    }
//    public boolean canBeCarried()
//    {
//        return false;
//    }
//
//    public int dropBlockAsEntityItem(BlockBreakInfo info)
//    {
//        return 0;
//    }
//
//    public boolean isSolid(boolean[] is_solid, int metadata)
//    {
//        return false;
//    }
//
//    public boolean isStandardFormCube(boolean[] is_standard_form_cube, int metadata)
//    {
//        return false;
//    }
//}
