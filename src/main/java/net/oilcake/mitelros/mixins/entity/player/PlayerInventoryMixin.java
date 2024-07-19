package net.oilcake.mitelros.mixins.entity.player;

import ink.huix.iinjected.InventoryPlayerKt;
import net.minecraft.EnchantmentHelper;
import net.minecraft.EntityPlayer;
import net.minecraft.EnumSignal;
import net.minecraft.InventoryPlayer;
import net.minecraft.ItemStack;
import net.minecraft.Packet85SimpleSignal;
import net.oilcake.mitelros.enchantment.Enchantments;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;

@Mixin(InventoryPlayer.class)
public class PlayerInventoryMixin implements InventoryPlayerKt {
   @Shadow
   public ItemStack[] mainInventory;
   @Shadow
   public ItemStack[] armorInventory;
   @Shadow
   public EntityPlayer player;

   @Override
   @Unique
   public void vanishingItems() {
      int var1;
      for(var1 = 0; var1 < this.mainInventory.length; ++var1) {
         if (this.mainInventory[var1] != null && EnchantmentHelper.hasEnchantment(this.mainInventory[var1], Enchantments.enchantmentVanishing)) {
            this.destroyInventoryItemStack(this.mainInventory[var1]);
            this.mainInventory[var1] = null;
         }
      }

      for(var1 = 0; var1 < this.armorInventory.length; ++var1) {
         if (this.armorInventory[var1] != null && EnchantmentHelper.hasEnchantment(this.armorInventory[var1], Enchantments.enchantmentVanishing)) {
            this.destroyInventoryItemStack(this.armorInventory[var1]);
            this.armorInventory[var1] = null;
         }
      }

      this.player.sendPacket(new Packet85SimpleSignal(EnumSignal.clear_inventory));
   }

   @Shadow
   public void destroyInventoryItemStack(ItemStack item_stack) {
   }
}
