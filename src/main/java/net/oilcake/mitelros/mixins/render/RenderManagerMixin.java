package net.oilcake.mitelros.mixins.render;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import net.minecraft.Item;
import net.minecraft.Render;
import net.minecraft.RenderManager;
import net.minecraft.RenderSnowball;
import net.oilcake.mitelros.client.render.RenderAgarician;
import net.oilcake.mitelros.client.render.RenderClusterSpider;
import net.oilcake.mitelros.client.render.RenderHusk;
import net.oilcake.mitelros.client.render.RenderLich;
import net.oilcake.mitelros.client.render.RenderLichShadow;
import net.oilcake.mitelros.client.render.RenderPigmanLord;
import net.oilcake.mitelros.client.render.RenderSpiderKing;
import net.oilcake.mitelros.client.render.RenderStalkerCreeper;
import net.oilcake.mitelros.client.render.RenderStray;
import net.oilcake.mitelros.client.render.RenderUnknown;
import net.oilcake.mitelros.client.render.RenderWitherBodyguard;
import net.oilcake.mitelros.client.render.RenderWitherBoneLord;
import net.oilcake.mitelros.entity.EntityAgarician;
import net.oilcake.mitelros.entity.EntityClusterSpider;
import net.oilcake.mitelros.entity.EntityHusk;
import net.oilcake.mitelros.entity.EntityLich;
import net.oilcake.mitelros.entity.EntityLichShadow;
import net.oilcake.mitelros.entity.EntityPigmanLord;
import net.oilcake.mitelros.entity.EntitySpiderKing;
import net.oilcake.mitelros.entity.EntityStalkerCreeper;
import net.oilcake.mitelros.entity.EntityStray;
import net.oilcake.mitelros.entity.EntityUnknown;
import net.oilcake.mitelros.entity.EntityWandFireball;
import net.oilcake.mitelros.entity.EntityWandIceBall;
import net.oilcake.mitelros.entity.EntityWandShockWave;
import net.oilcake.mitelros.entity.EntityWitherBodyguard;
import net.oilcake.mitelros.entity.EntityWitherBoneLord;
import net.xiaoyu233.fml.util.ReflectHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({RenderManager.class})
public class RenderManagerMixin {
   @Shadow
   private final Map entityRenderMap = new HashMap();

   @Inject(
      method = {"<init>()V"},
      at = {@At("RETURN")}
   )
   private void injectRegister(CallbackInfo callback) {
      this.entityRenderMap.put(EntityWitherBoneLord.class, new RenderWitherBoneLord());
      this.entityRenderMap.put(EntityClusterSpider.class, new RenderClusterSpider(0.4F));
      this.entityRenderMap.put(EntityWitherBodyguard.class, new RenderWitherBodyguard());
      this.entityRenderMap.put(EntitySpiderKing.class, new RenderSpiderKing(1.45F));
      this.entityRenderMap.put(EntityPigmanLord.class, new RenderPigmanLord());
      this.entityRenderMap.put(EntityStray.class, new RenderStray());
      this.entityRenderMap.put(EntityHusk.class, new RenderHusk());
      this.entityRenderMap.put(EntityLich.class, new RenderLich());
      this.entityRenderMap.put(EntityLichShadow.class, new RenderLichShadow());
      this.entityRenderMap.put(EntityStalkerCreeper.class, new RenderStalkerCreeper());
      this.entityRenderMap.put(EntityWandFireball.class, new RenderSnowball(Item.fireballCharge));
      this.entityRenderMap.put(EntityWandIceBall.class, new RenderSnowball(Item.snowball));
      this.entityRenderMap.put(EntityWandShockWave.class, new RenderSnowball(Item.eyeOfEnder));
      this.entityRenderMap.put(EntityUnknown.class, new RenderUnknown());
      this.entityRenderMap.put(EntityAgarician.class, new RenderAgarician());
      Iterator var2 = this.entityRenderMap.values().iterator();

      while(var2.hasNext()) {
         Render o = (Render)var2.next();
         o.setRenderManager((RenderManager)ReflectHelper.dyCast(RenderManager.class, this));
      }

   }
}
