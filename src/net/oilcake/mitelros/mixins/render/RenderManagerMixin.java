package net.oilcake.mitelros.mixins.render;

import net.minecraft.Entity;
import net.minecraft.bgl;
import net.minecraft.bgm;
import net.oilcake.mitelros.entity.EntityWitherBoneLord;
import net.oilcake.mitelros.render.RenderWitherBoneLord;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.HashMap;
import java.util.Map;

import static net.xiaoyu233.fml.util.ReflectHelper.dyCast;

@Mixin(bgl.class)
public class RenderManagerMixin {
    @Shadow
    private final Map<Class<? extends Entity>, bgm> q = new HashMap<>();
    @Inject(
            method = "<init>",
            at = @At(value = "RETURN"))
    private void injectRegister(CallbackInfo callback) {
        this.q.put(EntityWitherBoneLord.class, new RenderWitherBoneLord());
        for (bgm o : this.q.values()) {
            o.a(dyCast(bgl.class, this));
        }
    }
}
