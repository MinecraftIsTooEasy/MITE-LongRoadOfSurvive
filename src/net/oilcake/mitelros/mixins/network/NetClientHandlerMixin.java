package net.oilcake.mitelros.mixins.network;

import net.minecraft.*;
import net.oilcake.mitelros.block.enchantreserver.GuiEnchantReserver;
import net.oilcake.mitelros.util.network.PacketDecreaseWater;
import net.oilcake.mitelros.util.network.PacketEnchantReserverInfo;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.SoftOverride;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;


@Mixin(NetClientHandler.class)
public class NetClientHandlerMixin extends ConnectionMixin{
//    private static Class[] classes = new Class[]{ContainerPlayer.class, ContainerWorkbench.class, MITEConstant.class, MITEContainerCrafting.class, ClientPlayer.class, EntityPlayer.class, bex.class,
//            FoodMetaData.class, Minecraft.class, bew.class, NetClientHandler.class, ClientPlayerController.class, Packet13PlayerLookMove.class, Packet27PlayerInput.class, Packet82AddHunger.class,
//            Packet85SimpleSignal.class, Packet202Abilities.class, PlayerAbilities.class, NetworkManager.class, PacketDecreaseWater.class, PacketEnchantReserverInfo.class};
//
//    @Inject(method = "<clinit>", at = @At("RETURN"))
//    private static void injectClinit(CallbackInfo callbackInfo) {
//        for (Class aClass : classes) {
//            class_hash_sum += getHash(aClass);
//        }
//    }

    @Shadow
    private static int class_hash_sum = 0;
    @Shadow
    private static int getHash(Class _class) {
        return 1;
    }

    @Shadow
    private Minecraft h;

    @Overwrite
    public void handleUpdateHealth(Packet8UpdateHealth par1Packet8UpdateHealth) {
        this.h.h.n(par1Packet8UpdateHealth.healthMP);
        this.h.h.getFoodStats().setSatiation(par1Packet8UpdateHealth.satiation, false);
        this.h.h.getFoodStats().setNutrition(par1Packet8UpdateHealth.nutrition, false);
        this.h.h.getFoodStats().setSatiationWater(par1Packet8UpdateHealth.water, false);
        if (this.h.h.vision_dimming < par1Packet8UpdateHealth.vision_dimming) {
            this.h.h.vision_dimming = par1Packet8UpdateHealth.vision_dimming;
        }

    }

    @SoftOverride
    public void processEnchantReserverInfo(PacketEnchantReserverInfo packet) {
        awe openingGUI = this.h.n;
        if (openingGUI instanceof GuiEnchantReserver) {
            ((GuiEnchantReserver)openingGUI).setEnchantInfo(packet.getEXP());
        }
    }
}
