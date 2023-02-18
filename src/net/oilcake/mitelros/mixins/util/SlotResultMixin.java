package net.oilcake.mitelros.mixins.util;

import net.minecraft.EntityPlayer;
import net.minecraft.SlotResult;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(SlotResult.class)
public class SlotResultMixin {
    public int getNumCraftingResultsC(EntityPlayer player){
        return this.getNumCraftingResults(player);
    }

    @Shadow
    protected int getNumCraftingResults(EntityPlayer player) {return 1;}
}
