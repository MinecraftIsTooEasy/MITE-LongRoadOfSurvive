package net.oilcake.mitelros.block;

import net.minecraft.BlockThinFence;
import net.minecraft.Material;

public class NewBlockThinFence extends BlockThinFence {
    protected NewBlockThinFence(int par1, Material par4Material) {
        super(par1, "bars/" + par4Material + "_bars", "bars/" + par4Material + "_bars", par4Material, false);
        this.setStepSound(soundMetalFootstep);
    }
}
