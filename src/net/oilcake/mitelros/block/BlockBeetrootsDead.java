package net.oilcake.mitelros.block;

import net.minecraft.BlockCropsDead;

public class BlockBeetrootsDead extends BlockCropsDead {
    protected BlockBeetrootsDead(int block_id) {
        super(block_id, 4);
    }
    public int getGrowthStage(int metadata) {
        int growth = this.getGrowth(metadata);
        if (growth == 6) {
            growth = 5;
        }

        return growth / 2;
    }
}
