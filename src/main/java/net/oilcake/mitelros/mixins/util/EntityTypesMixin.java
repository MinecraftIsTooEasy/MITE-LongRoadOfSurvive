package net.oilcake.mitelros.mixins.util;

import net.minecraft.Entity;
import net.minecraft.EntityList;
import net.minecraft.World;
import net.oilcake.mitelros.entity.EntityAgarician;
import net.oilcake.mitelros.entity.EntityBoneBodyguard;
import net.oilcake.mitelros.entity.EntityClusterSpider;
import net.oilcake.mitelros.entity.EntityEvil;
import net.oilcake.mitelros.entity.EntityGhost;
import net.oilcake.mitelros.entity.EntityHusk;
import net.oilcake.mitelros.entity.EntityLich;
import net.oilcake.mitelros.entity.EntityLichShadow;
import net.oilcake.mitelros.entity.EntityPigmanLord;
import net.oilcake.mitelros.entity.EntityRetinueZombie;
import net.oilcake.mitelros.entity.EntitySpiderKing;
import net.oilcake.mitelros.entity.EntityStalkerCreeper;
import net.oilcake.mitelros.entity.EntityStray;
import net.oilcake.mitelros.entity.EntityUndeadGuard;
import net.oilcake.mitelros.entity.EntityUnknown;
import net.oilcake.mitelros.entity.EntityWandFireball;
import net.oilcake.mitelros.entity.EntityWandIceBall;
import net.oilcake.mitelros.entity.EntityWandShockWave;
import net.oilcake.mitelros.entity.EntityWitherBodyguard;
import net.oilcake.mitelros.entity.EntityWitherBoneLord;
import net.oilcake.mitelros.entity.EntityZombieLord;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({EntityList.class})
public class EntityTypesMixin {
   @Shadow
   public static void addMapping(Class par0Class, String par1Str, int par2) {
   }

   @Shadow
   private static void addMapping(Class par0Class, String par1Str, int par2, int par3, int par4) {
   }

   @Shadow
   public static Entity createEntityByID(int par0, World par1World) {
      return null;
   }

   @Inject(
      method = {"<clinit>()V"},
      at = {@At("RETURN")}
   )
   private static void injectClinit(CallbackInfo callbackInfo) {
      addMapping(EntityWitherBoneLord.class, "EntityWitherBoneLord", 541, 1314564, 13003008);
      addMapping(EntityClusterSpider.class, "EntityClusterSpider", 542, 15474675, 5051227);
      addMapping(EntityWitherBodyguard.class, "EntityWitherBodyguard", 543, 1314564, 7039851);
      addMapping(EntitySpiderKing.class, "EntitySpiderKing", 544, 3419431, 15790120);
      addMapping(EntityStray.class, "EntityStray", 545, 10862020, 871004);
      addMapping(EntityHusk.class, "EntityHusk", 546, 9798412, 3940871);
      addMapping(EntityPigmanLord.class, "EntityPigManlord", 547, 15373203, 5066061);
      addMapping(EntityLich.class, "EntityLich", 548, 13422277, 14008320);
      addMapping(EntityLichShadow.class, "EntityLichShadow", 549, 13422277, 7699821);
      addMapping(EntityStalkerCreeper.class, "EntityStalkerCreeper", 550, 10921638, 0);
      addMapping(EntityWandFireball.class, "EntityWandFireball", 551);
      addMapping(EntityWandIceBall.class, "EntityWandIceball", 552);
      addMapping(EntityWandShockWave.class, "EntityWandShockWave", 553);
      addMapping(EntityZombieLord.class, "EntityZombieLord?", 554, 44975, 7969893);
      addMapping(EntityRetinueZombie.class, "EntityZombieRetinue", 555, 44975, 7969893);
      addMapping(EntityBoneBodyguard.class, "EntityBoneBodyguard", 556, 12698049, 4802889);
      addMapping(EntityGhost.class, "EntityGhost", 557, 9539985, 6629376);
      addMapping(EntityEvil.class, "EntityEvil", 558, 9539985, 14008320);
      addMapping(EntityUndeadGuard.class, "EntityUndeadGuard", 559, 12698049, 4802889);
      addMapping(EntityAgarician.class, "EntityAgarician", 560, 10489616, 12040119);
      addMapping(EntityUnknown.class, "null", 1895);
   }
}
