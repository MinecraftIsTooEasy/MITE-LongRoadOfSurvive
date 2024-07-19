package net.oilcake.mitelros.mixins.entity;

import net.minecraft.EntityAISeekLitTorch;
import net.minecraft.EntityAnimalWatcher;
import net.minecraft.EntityLivingData;
import net.minecraft.EntityZombie;
import net.minecraft.Item;
import net.minecraft.World;
import net.oilcake.mitelros.item.Items;
import net.oilcake.mitelros.util.StuckTagConfig;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin({EntityZombie.class})
public class EntityZombieMixin extends EntityAnimalWatcher {
   @Shadow
   private boolean is_smart;
   @Shadow
   Item[] rare_drops_villager;

   public EntityZombieMixin(World world) {
      super(world);
   }

   @Inject(
      method = {"<init>(Lnet/minecraft/World;)V"},
      at = {@At("RETURN")}
   )
   public void injectCtor(CallbackInfo callbackInfo) {
      this.is_smart = true;
      this.rare_drops_villager = new Item[]{Item.seeds, Item.pumpkinSeeds, Item.melonSeeds, Item.carrot, Item.potato, Item.onion, Items.seedsBeetroot};
   }

   @Inject(
      method = {"onUpdate()V"},
      at = {@At("RETURN")}
   )
   public void ModifyAIInjector(CallbackInfo callbackInfo) {
      if ((Boolean)StuckTagConfig.TagConfig.TagWorshipDark.ConfigValue) {
         this.tryDisableNearbyLightSource();
      }

   }

   @Inject(
      method = {"onSpawnWithEgg(Lnet/minecraft/EntityLivingData;)Lnet/minecraft/EntityLivingData;"},
      at = {@At("RETURN")},
      cancellable = true
   )
   public void ModifyAIInjector(EntityLivingData par1EntityLivingData, CallbackInfoReturnable callbackInfo) {
      if ((Boolean)StuckTagConfig.TagConfig.TagWorshipDark.ConfigValue) {
         this.tasks.addTask(4, new EntityAISeekLitTorch(this, 1.0F));
      }

   }
}
