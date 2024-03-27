package net.oilcake.mitelros.mixins.util;

import net.minecraft.StatisticList;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(StatisticList.class)
public class StatisticListMixin {
    @ModifyConstant(method = {
            "initBreakableStats",
            "initStats",
            "initMinableStats",
            "initUsableStats"
    }, constant = @Constant(intValue = 256))
    private static int ExtendedBlockID(int value) {
        return net.oilcake.mitelros.util.Constant.Extended_Block_ID;
    }
}
