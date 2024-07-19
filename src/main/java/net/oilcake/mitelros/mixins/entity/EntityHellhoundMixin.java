package net.oilcake.mitelros.mixins.entity;

import net.minecraft.DamageSource;
import net.minecraft.EntityHellhound;
import net.minecraft.EntityWolf;
import net.minecraft.World;
import net.oilcake.mitelros.item.Items;
import org.spongepowered.asm.mixin.Mixin;

@Mixin({EntityHellhound.class})
public class EntityHellhoundMixin extends EntityWolf {
   public EntityHellhoundMixin(World par1World) {
      super(par1World);
   }

   protected void dropFewItems(boolean recently_hit_by_player, DamageSource damage_source) {
      int looting = damage_source.getLootingModifier();

      for(int i = 0; i < 1 + this.rand.nextInt(2 + looting); ++i) {
         this.dropItem(Items.hellhoundFur.itemID, 1);
      }

   }
}
