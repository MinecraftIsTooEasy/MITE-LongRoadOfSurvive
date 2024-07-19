package net.oilcake.mitelros.mixins.block;

import net.minecraft.BlockAnvil;
import net.minecraft.BlockBreakInfo;
import net.minecraft.BlockConstants;
import net.minecraft.BlockFalling;
import net.minecraft.BlockOreStorage;
import net.minecraft.Item;
import net.minecraft.ItemIngot;
import net.minecraft.ItemNugget;
import net.minecraft.Material;
import net.minecraft.TileEntityAnvil;
import net.minecraft.World;
import net.oilcake.mitelros.util.ExperimentalConfig;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;

@Mixin({BlockAnvil.class})
public class BlockAnvilMixin extends BlockFalling {
   @Shadow
   public Material metal_type;

   public BlockAnvilMixin(int par1, Material material, BlockConstants constants) {
      super(par1, material, constants);
   }

   protected void dropXpOnBlockBreak(World par1World, int par2, int par3, int par4, int par5) {
   }

   @Overwrite
   public int dropBlockAsEntityItem(BlockBreakInfo info) {
      TileEntityAnvil tile_entity_anvil = (TileEntityAnvil)info.tile_entity;
      if (!(Boolean)ExperimentalConfig.TagConfig.TagBenchingV2.ConfigValue && !info.wasExploded()) {
         return super.dropBlockAsEntityItem(info.setDamage(tile_entity_anvil.damage));
      } else {
         float centesimal = this.getAnvilDurabilityByCentesimal(tile_entity_anvil.damage);
         if (!((double)centesimal <= 0.5)) {
            return super.dropBlockAsEntityItem(info.setDamage(tile_entity_anvil.damage));
         } else {
            int expecting_nuggets = (int)(279.0F * centesimal);
            if (info.wasExploded()) {
               expecting_nuggets = (int)((double)expecting_nuggets * 0.5);
            }

            while(expecting_nuggets > 80) {
               expecting_nuggets -= 81;
               this.dropBlockAsEntityItem(info, this.getMatchingBlock(BlockOreStorage.class, this.metal_type).blockID);
            }

            while(expecting_nuggets > 8) {
               expecting_nuggets -= 9;
               this.dropBlockAsEntityItem(info, Item.getMatchingItem(ItemIngot.class, this.metal_type).itemID);
            }

            while(expecting_nuggets > 0) {
               --expecting_nuggets;
               this.dropBlockAsEntityItem(info, Item.getMatchingItem(ItemNugget.class, this.metal_type).itemID);
            }

            return 0;
         }
      }
   }

   @Unique
   public float getAnvilDurabilityByCentesimal(int damage) {
      float nowDurability = (float)(this.getDurability() - damage);
      return nowDurability / (float)this.getDurability();
   }

   @Shadow
   public int getMinimumDamageForStage(int stage) {
      return 0;
   }

   @Shadow
   public int getDurability() {
      return 0;
   }
}
