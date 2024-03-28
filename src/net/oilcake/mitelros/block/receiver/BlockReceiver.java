package net.oilcake.mitelros.block.receiver;

import net.minecraft.*;
import net.oilcake.mitelros.block.Blocks;

public class BlockReceiver extends BlockContainer{
    protected IIcon SIDE_ON;
    protected IIcon SIDE_OFF;
    @Override
    public void a(mt mt) {
        SIDE_ON = mt.a("resonance_converter/side_on");
        SIDE_OFF = mt.a("resonance_converter/side");
    }
    public BlockReceiver(int par1) {
        super(par1, Material.stone, new BlockConstants());
        this.setCreativeTab(CreativeModeTab.tabRedstone);
        this.setMaxStackSize(4);
        this.setLightOpacity(0);
    }
    public boolean isValidMetadata(int metadata) {
        return metadata >= 0 && metadata < 16;
    }
    public IIcon a(int side, int metadata) {
        return metadata > 0 ? SIDE_ON : SIDE_OFF;
    }

    public boolean isPortable(World world, EntityLiving entity_living_base, int x, int y, int z) {
        return true;
    }
    public boolean canProvidePower() {
        return true;
    }

    public int isProvidingWeakPower(IBlockAccess par1IBlockAccess, int par2, int par3, int par4, int par5) {
        return par1IBlockAccess.getBlockMetadata(par2, par3, par4);
    }

    public int dropBlockAsEntityItem(BlockBreakInfo info) {
        this.dropBlockAsEntityItem(info, new ItemStack(Blocks.blockReceiver.blockID,1));
        return 0;
    }
    public String getMetadataNotes() {
        return "All bits used to track signal level.";
    }
    public void updateSignalLevel(World par1World, int par2, int par3, int par4){
        for(int dx = -2; dx <= 2; dx++){
            for(int dy = -2; dy <= 2; dy++){
                for(int dz = -2; dz <= 2; dz++){
                    if(par1World.getBlockId(par2 + dx, par3 + dy, par4 + dz) == Blocks.blockObserver.blockID){
                        if((par1World.getBlockMetadata(par2 + dx, par3 + dy, par4 + dz) & 8) != 0){
                            par1World.setBlockMetadata(par2, par3, par4, 9 - Math.abs(dx) - Math.abs(dy) - Math.abs(dz), 3);
                        }else {
                            par1World.setBlockMetadata(par2, par3, par4, 0, 3);
                        }
                    }
                }
            }
        }
    }
    public TileEntity createNewTileEntity(World par1World) {
        return new TileEntityReceiver();
    }
}
