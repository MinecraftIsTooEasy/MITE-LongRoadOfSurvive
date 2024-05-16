package net.oilcake.mitelros.mixins.block;

import net.minecraft.*;
import net.oilcake.mitelros.block.BlockMetalbench;
import net.oilcake.mitelros.block.BlockWoodbench;
import net.oilcake.mitelros.block.EnumBenchType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;


@Mixin(awy.class)
public class GUIContainerMixin {
    @Shadow
    public Container e;
    @Redirect(method = "drawItemStackTooltip(Lnet/minecraft/ItemStack;IILnet/minecraft/Slot;)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/BlockWorkbench;getToolMaterial(I)Lnet/minecraft/Material;"))
    public Material redirectBenchMaterial(int par0, ItemStack itemStack, int par1, int par2, Slot slot){
        ContainerWorkbench container_workbench = (ContainerWorkbench)this.e;
        int metadata = container_workbench.getBlockMetadata();
        int benchType = container_workbench.benchType;
        Material tool_material;
        if(benchType == EnumBenchType.WOOD.ordinal()){
            tool_material = BlockWoodbench.getToolMaterial(metadata);
        }else if(benchType == EnumBenchType.METAL.ordinal()){
            tool_material = BlockMetalbench.getToolMaterial(metadata);
        }else{
            tool_material = BlockWorkbench.getToolMaterial(metadata);
        }
        return tool_material;
    }
}