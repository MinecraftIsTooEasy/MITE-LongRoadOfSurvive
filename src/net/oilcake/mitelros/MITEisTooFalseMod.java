package net.oilcake.mitelros;

import net.oilcake.mitelros.mixins.MinecraftMixin;
import net.oilcake.mitelros.util.Constant;
import net.oilcake.mitelros.util.SpecializedTPAConfig;
import net.oilcake.mitelros.util.StuckTagConfig;
import net.oilcake.mitelros.util.events.ITFEvent;
import net.xiaoyu233.fml.AbstractMod;
import net.xiaoyu233.fml.classloading.Mod;
import net.xiaoyu233.fml.config.InjectionConfig;
import net.xiaoyu233.fml.reload.event.MITEEvents;
import org.spongepowered.asm.mixin.MixinEnvironment;

import javax.annotation.Nonnull;

@Mod
public class MITEisTooFalseMod extends AbstractMod {

    @Override
    public void preInit() {
    }
    @Nonnull
    @Override
    public InjectionConfig getInjectionConfig() {
        return InjectionConfig.Builder.of("MITE-ITF", MinecraftMixin.class.getPackage(), MixinEnvironment.Phase.INIT).setRequired().build();
    }

    @Override
    public String modId() {
        return "MITEIsTooFalse";
    }

    @Override
    public int modVerNum() {
        return Constant.VER_NUM;
    }

    @Override
    public String modVerStr() {
        return Constant.VERSION;
    }
    @Override
    public void postInit() {
        StuckTagConfig.loadConfigs();
        MITEEvents.MITE_EVENT_BUS.register(new ITFEvent());
//        SpecializedTPAConfig.FileInit();
    }
}
