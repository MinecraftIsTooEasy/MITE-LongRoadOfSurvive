package net.oilcake.mitelros.block.observer;

import net.minecraft.*;

import java.util.Random;

public class BlockObserver extends BlockDirectionalWithTileEntity{
    private IIcon TEXTURE_TOP;
    private IIcon TEXTURE_TOP_ON;
    private IIcon TEXTURE_FRONT;
    private IIcon TEXTURE_SIDE;

    public BlockObserver(int id, Material material) {
        super(id, material, new BlockConstants());
        this.setCreativeTab(CreativeModeTab.tabRedstone);
        this.setMaxStackSize(4);
        this.setLightOpacity(0);
    }
    public boolean isPortable(World world, EntityLiving entity_living_base, int x, int y, int z) {
        return true;
    }

    @Override
    public TileEntity createNewTileEntity(World world) {
        return new TileEntityObserver();
    }

    public boolean isActivated(int metadata) {
        return (metadata & 8) != 0;
    }
    public static void updateState(boolean par0, World par1World, int par2, int par3, int par4){
        int var5 = par1World.getBlockMetadata(par2, par3, par4);
        TileEntity var6 = par1World.getBlockTileEntity(par2, par3, par4);
        BlockObserver observer = (BlockObserver) Block.blocksList[par1World.getBlockId(par2, par3, par4)];
        if (par0) {
            par1World.setBlock(par2, par3, par4, observer.blockID, (var5 & 7) + 8, 0);
        } else {
            par1World.setBlock(par2, par3, par4, observer.blockID, var5 & 7, 0);
        }
        if (var6 != null) {
            var6.validate();
            par1World.setBlockTileEntity(par2, par3, par4, var6);
        }
    }
    @Override
    public IIcon a(int side, int metadata) {
        if(side == 0){
            return isActivated(metadata) ? TEXTURE_TOP_ON : TEXTURE_TOP;
        }else if(side == 1){
            return isActivated(metadata) ? TEXTURE_TOP_ON : TEXTURE_TOP;
        }else {
            return side != (metadata & 7) ? TEXTURE_SIDE : TEXTURE_FRONT;
        }
    }
    @Override
    public void a(mt mt) {
        TEXTURE_TOP = mt.a("resonance_generator/top");
        TEXTURE_TOP_ON = mt.a("resonance_generator/top_on");
        TEXTURE_FRONT = mt.a("resonance_generator/front");
        TEXTURE_SIDE = mt.a("resonance_generator/side");
    }

    @Override
    public final EnumDirection getDirectionFacing(int metadata) {
        return this.getDirectionFacingStandard6(metadata & 7, false);
    }

    @Override
    public int getMetadataForDirectionFacing(int metadata, EnumDirection direction) {
        int facing = direction == EnumDirection.NORTH ? 2 : (direction == EnumDirection.SOUTH ? 3 : (direction == EnumDirection.WEST ? 4 : (direction == EnumDirection.EAST ? 5 : -1)));
        if(isActivated(metadata)){
            facing += 8;
        }
        return facing;
    }

    public String getMetadataNotes() {
        String[] array = new String[4];

        for(int i = 0; i < array.length; ++i) {
            array[i] = i + 2 + "=" + this.getDirectionFacing(i + 2).getDescriptor(true);
        }

        return StringHelper.implode(array, ", ", true, false) + ", bit 8 set if activated.";
    }

    public boolean isValidMetadata(int metadata) {
        return (metadata > 1 && metadata < 6) || (metadata > 9 && metadata < 14);
    }
}
