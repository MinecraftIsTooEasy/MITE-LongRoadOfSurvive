package net.oilcake.mitelros.mixins.world;

import net.minecraft.*;
import net.oilcake.mitelros.item.Items;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

import java.util.Random;
@Mixin(agx.class)

public class WorldGenNetherBridgeCorridor2Mixin extends StructurePiece {
    private static final StructurePieceTreasure[] chest_contents;
    @Shadow
    private boolean field_111021_b;
    @Shadow
    protected void func_143012_a(NBTTagCompound nbtTagCompound) {

    }

    @Shadow
    protected void func_143011_b(NBTTagCompound nbtTagCompound) {

    }
    @Overwrite
    public boolean addComponentParts(World par1World, Random par2Random, StructureBoundingBox par3StructureBoundingBox) {
        this.fillWithBlocks(par1World, par3StructureBoundingBox, 0, 0, 0, 4, 1, 4, Block.netherBrick.blockID, Block.netherBrick.blockID, false);
        this.fillWithBlocks(par1World, par3StructureBoundingBox, 0, 2, 0, 4, 5, 4, 0, 0, false);
        this.fillWithBlocks(par1World, par3StructureBoundingBox, 4, 2, 0, 4, 5, 4, Block.netherBrick.blockID, Block.netherBrick.blockID, false);
        this.fillWithBlocks(par1World, par3StructureBoundingBox, 4, 3, 1, 4, 4, 1, Block.netherFence.blockID, Block.netherFence.blockID, false);
        this.fillWithBlocks(par1World, par3StructureBoundingBox, 4, 3, 3, 4, 4, 3, Block.netherFence.blockID, Block.netherFence.blockID, false);
        this.fillWithBlocks(par1World, par3StructureBoundingBox, 0, 2, 0, 0, 5, 0, Block.netherBrick.blockID, Block.netherBrick.blockID, false);
        this.fillWithBlocks(par1World, par3StructureBoundingBox, 0, 2, 4, 3, 5, 4, Block.netherBrick.blockID, Block.netherBrick.blockID, false);
        this.fillWithBlocks(par1World, par3StructureBoundingBox, 1, 3, 4, 1, 4, 4, Block.netherFence.blockID, Block.netherBrick.blockID, false);
        this.fillWithBlocks(par1World, par3StructureBoundingBox, 3, 3, 4, 3, 4, 4, Block.netherFence.blockID, Block.netherBrick.blockID, false);
        int var4;
        int var5;
        if (this.field_111021_b) {
            var4 = this.getYWithOffset(2);
            var5 = this.getXWithOffset(3, 3);
            int var6 = this.getZWithOffset(3, 3);
            if (par3StructureBoundingBox.isVecInside(var5, var4, var6)) {
                this.field_111021_b = false;
                this.generateStructureChestContents(par1World, par3StructureBoundingBox, par2Random, 3, 2, 3, Block.chest.blockID, chest_contents, 2 + par2Random.nextInt(4), (float[])null, par2Random.nextInt(2) == 0 ? EnumDirection.WEST : EnumDirection.SOUTH);
            }
        }

        this.fillWithBlocks(par1World, par3StructureBoundingBox, 0, 6, 0, 4, 6, 4, Block.netherBrick.blockID, Block.netherBrick.blockID, false);

        for(var4 = 0; var4 <= 4; ++var4) {
            for(var5 = 0; var5 <= 4; ++var5) {
                this.fillCurrentPositionBlocksDownwards(par1World, Block.netherBrick.blockID, 0, var4, -1, var5, par3StructureBoundingBox);
            }
        }

        return true;
    }

    static {
        chest_contents = new StructurePieceTreasure[]{
                new StructurePieceTreasure(Item.diamond.itemID, 0, 1, 2, 5),
                new StructurePieceTreasure(Items.tungstenIngot.itemID, 0, 1, 1, 5),
                new StructurePieceTreasure(Items.tungstenNugget.itemID, 0, 3, 7, 10),
                new StructurePieceTreasure(Item.ingotGold.itemID, 0, 2, 5, 10),
                new StructurePieceTreasure(Item.goldNugget.itemID, 0, 5, 10, 20),
                new StructurePieceTreasure(Items.tungstenSword.itemID, 0, 1, 1, 5),
                new StructurePieceTreasure(Items.tungstenWarHammer.itemID, 0, 1, 1, 5),
                new StructurePieceTreasure(Items.tungstenHelmetChain.itemID, 0, 1, 1, 5),
                new StructurePieceTreasure(Items.tungstenChestplateChain.itemID, 0, 1, 1, 5),
                new StructurePieceTreasure(Items.tungstenLeggingsChain.itemID, 0, 1, 1, 5),
                new StructurePieceTreasure(Items.tungstenBootsChain.itemID, 0, 1, 1, 5),
                new StructurePieceTreasure(Items.tungstenHelmet.itemID, 0, 1, 1, 2),
                new StructurePieceTreasure(Items.tungstenChestplate.itemID, 0, 1, 1, 2),
                new StructurePieceTreasure(Items.tungstenLeggings.itemID, 0, 1, 1, 2),
                new StructurePieceTreasure(Items.tungstenBoots.itemID, 0, 1, 1, 2),
                new StructurePieceTreasure(Item.warHammerGold.itemID, 0, 1, 1, 10),
                new StructurePieceTreasure(Item.swordGold.itemID, 0, 1, 1, 10),
                new StructurePieceTreasure(Item.battleAxeGold.itemID, 0, 1, 1, 10),
                new StructurePieceTreasure(Item.helmetGold.itemID, 0, 1, 1, 10),
                new StructurePieceTreasure(Item.plateGold.itemID, 0, 1, 1, 10),
                new StructurePieceTreasure(Item.legsGold.itemID, 0, 1, 1, 10),
                new StructurePieceTreasure(Item.bootsGold.itemID, 0, 1, 1, 10),
                new StructurePieceTreasure(Items.ignitionGold.itemID, 0, 1, 1, 18),
                new StructurePieceTreasure(Items.ignitionTungsten.itemID, 0, 1, 1, 2),
                new StructurePieceTreasure(Item.netherStalkSeeds.itemID, 0, 3, 7, 5),
                new StructurePieceTreasure(Item.saddle.itemID, 0, 1, 1, 20),
                new StructurePieceTreasure(Item.horseArmorGold.itemID, 0, 1, 1, 10),
                new StructurePieceTreasure(Item.horseArmorCopper.itemID, 0, 1, 1, 2),
                new StructurePieceTreasure(Item.horseArmorIron.itemID, 0, 1, 1, 1),
                new StructurePieceTreasure(Items.totemofdestroy.itemID, 0, 1, 1, 5),
        };

    }
}
