package net.oilcake.mitelros.mixins.render;

import net.minecraft.*;
import net.oilcake.mitelros.entity.*;
import net.oilcake.mitelros.entity.EntityWandFireball;
import net.oilcake.mitelros.util.render.*;
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
        this.q.put(EntityClusterSpider.class, new RenderClusterSpider(0.4F));
        this.q.put(EntityWitherBodyguard.class, new RenderWitherBodyguard());
        this.q.put(EntitySpiderKing.class, new RenderSpiderKing(1.45F));
        this.q.put(EntityPigmanLord.class, new RenderPigmanLord());
        this.q.put(EntityStray.class,new RenderStray());
        this.q.put(EntityHusk.class,new RenderHusk());
        this.q.put(EntityLich.class,new RenderLich());
        this.q.put(EntityLichShadow.class,new RenderLichShadow());
        this.q.put(EntityStalkerCreeper.class,new RenderStalkerCreeper());
        this.q.put(EntityWandFireball.class, new bgx(Item.fireballCharge));
        for (bgm o : this.q.values()) {
            o.a(dyCast(bgl.class, this));
        }
    }
}
