package net.oilcake.mitelros.mixins.block.tileentity;

import net.minecraft.*;
import net.oilcake.mitelros.block.Blocks;
import net.oilcake.mitelros.item.Items;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(TileEntityBeacon.class)
public abstract class TileEntityBeaconMixin extends TileEntity {
    private float c;
    private boolean isBeaconActive;
    private int levels = -1;
    @Overwrite
    private void updateState() {
        if (!this.worldObj.canBlockSeeTheSky(this.xCoord, this.yCoord + 1, this.zCoord)) {
            this.isBeaconActive = false;
            this.levels = 0;
        } else {
            this.isBeaconActive = true;
            this.levels = 0;

            for(int var1 = 1; var1 <= 4; this.levels = var1++) {
                int var2 = this.yCoord - var1;
                if (var2 < 0) {
                    break;
                }

                boolean var3 = true;

                for(int var4 = this.xCoord - var1; var4 <= this.xCoord + var1 && var3; ++var4) {
                    for(int var5 = this.zCoord - var1; var5 <= this.zCoord + var1; ++var5) {
                        int var6 = this.worldObj.getBlockId(var4, var2, var5);
                        if (var6 != Block.blockEmerald.blockID && var6 != Block.blockDiamond.blockID && var6 != Block.blockCopper.blockID && var6 != Block.blockSilver.blockID && var6 != Block.blockGold.blockID && var6 != Block.blockIron.blockID && var6 != Block.blockMithril.blockID && var6 != Block.blockAdamantium.blockID && var6 != Block.blockAncientMetal.blockID && var6 != Blocks.blockNickel.blockID && var6 != Blocks.blockTungsten.blockID) {
                            var3 = false;
                            break;
                        }
                    }
                }

                if (!var3) {
                    break;
                }
            }

            if (this.levels == 0) {
                this.isBeaconActive = false;
            }
        }
    }
    @Overwrite
    public boolean isItemValidForSlot(int par1, ItemStack par2ItemStack) {
        return par2ItemStack.itemID == Item.emerald.itemID || par2ItemStack.itemID == Item.diamond.itemID || par2ItemStack.itemID == Item.ingotGold.itemID || par2ItemStack.itemID == Item.ingotIron.itemID
                || par2ItemStack.itemID == Item.ingotMithril.itemID || par2ItemStack.itemID == Item.ingotAdamantium.itemID || par2ItemStack.itemID == Item.ingotAncientMetal.itemID || par2ItemStack.itemID == Item.ingotCopper.itemID
                || par2ItemStack.itemID == Item.ingotSilver.itemID || par2ItemStack.itemID == Items.nickelIngot.itemID || par2ItemStack.itemID == Items.tungstenIngot.itemID || par2ItemStack.itemID == Items.UruIngot.itemID;
    }
}
