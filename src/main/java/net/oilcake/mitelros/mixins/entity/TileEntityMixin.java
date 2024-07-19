package net.oilcake.mitelros.mixins.entity;

import net.minecraft.TileEntity;
import net.oilcake.mitelros.block.enchantreserver.TileEntityEnchantReserver;
import net.oilcake.mitelros.block.observer.TileEntityObserver;
import net.oilcake.mitelros.block.receiver.TileEntityReceiver;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({TileEntity.class})
public class TileEntityMixin {
   @Shadow
   private static void addMapping(Class par0Class, String par1Str) {
   }

   @Inject(
      method = {"<clinit>()V"},
      at = {@At("RETURN")}
   )
   private static void registerTileEntity(CallbackInfo c) {
      addMapping(TileEntityEnchantReserver.class, "EnchantReserver");
      addMapping(TileEntityObserver.class, "Observer");
      addMapping(TileEntityReceiver.class, "Receiver");
   }
}
