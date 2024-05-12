package net.oilcake.mitelros.mixins.block;

import jdk.nashorn.internal.codegen.CompilerConstants;
import net.minecraft.*;
import net.oilcake.mitelros.block.Blocks;
import net.oilcake.mitelros.block.EnumBenchType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ContainerWorkbench.class)
public abstract class ContainerWorkbenchMixin extends MITEContainerCrafting {
    @Shadow
    private int x;
    @Shadow
    private int y;
    @Shadow
    private int z;
    public int benchType;
    public ContainerWorkbenchMixin(EntityPlayer player) {
        super(player);
    }
    @Inject(method = "<init>", at = @At("RETURN"))
    public void injectInit(CallbackInfo callbackInfo){
        int id = this.world.getBlockId(x,y,z);
        if(id == Blocks.woodbench.blockID){
            benchType = EnumBenchType.WOOD.ordinal();
        }else if(id == Blocks.metalbench.blockID){
            benchType = EnumBenchType.METAL.ordinal();
        }else {
            benchType = EnumBenchType.NORMAL.ordinal();
        }
    }
    @Overwrite
    public boolean canInteractWith(EntityPlayer par1EntityPlayer) {
        return this.world.getBlockId(this.x, this.y, this.z) != Block.workbench.blockID && this.world.getBlockId(this.x, this.y, this.z) != Blocks.metalbench.blockID && this.world.getBlockId(this.x, this.y, this.z) != Blocks.woodbench.blockID ? false : par1EntityPlayer.getDistanceSq((double)this.x + 0.5, (double)this.y + 0.5, (double)this.z + 0.5) <= 64.0;
    }
}
