package net.oilcake.mitelros.mixins.block;

import net.minecraft.Block;
import net.minecraft.ContainerWorkbench;
import net.minecraft.EntityPlayer;
import net.minecraft.MITEContainerCrafting;
import net.oilcake.mitelros.block.Blocks;
import net.oilcake.mitelros.block.EnumBenchType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({ContainerWorkbench.class})
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

   @Inject(
      method = {"<init>(Lnet/minecraft/EntityPlayer;III)V"},
      at = {@At("RETURN")}
   )
   public void injectInit(CallbackInfo callbackInfo) {
      int id = this.world.getBlockId(this.x, this.y, this.z);
      if (id == Blocks.woodbench.blockID) {
         this.benchType = EnumBenchType.WOOD.ordinal();
      } else if (id == Blocks.metalbench.blockID) {
         this.benchType = EnumBenchType.METAL.ordinal();
      } else {
         this.benchType = EnumBenchType.NORMAL.ordinal();
      }

   }

   @Overwrite
   public boolean canInteractWith(EntityPlayer par1EntityPlayer) {
      return this.world.getBlockId(this.x, this.y, this.z) != Block.workbench.blockID && this.world.getBlockId(this.x, this.y, this.z) != Blocks.metalbench.blockID && this.world.getBlockId(this.x, this.y, this.z) != Blocks.woodbench.blockID ? false : par1EntityPlayer.getDistanceSq((double)this.x + 0.5, (double)this.y + 0.5, (double)this.z + 0.5) <= 64.0;
   }
}
