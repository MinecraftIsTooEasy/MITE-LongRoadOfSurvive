package net.oilcake.mitelros.mixins.entity.player;

import net.minecraft.*;
import net.oilcake.mitelros.item.enchantment.Enchantments;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(PlayerInventory.class)
public class PlayerInventoryMixin {
    @Shadow
    public ItemStack[] mainInventory;
    @Shadow
    public ItemStack[] armorInventory;
    @Shadow
    public EntityPlayer player;

    public void vanishingItems() {
        int var1;
        for(var1 = 0; var1 < this.mainInventory.length; ++var1) {
            if (this.mainInventory[var1] != null && EnchantmentManager.hasEnchantment(this.mainInventory[var1], Enchantments.enchantmentVanishing)) {
                //this.player.dropPlayerItemWithRandomChoice(this.mainInventory[var1], true);
                this.destroyInventoryItemStack(this.mainInventory[var1]);
                this.mainInventory[var1] = null;
            }
        }

        for(var1 = 0; var1 < this.armorInventory.length; ++var1) {
            if (this.armorInventory[var1] != null && EnchantmentManager.hasEnchantment(this.armorInventory[var1], Enchantments.enchantmentVanishing)) {
                //this.player.dropPlayerItemWithRandomChoice(this.armorInventory[var1], true);
                this.destroyInventoryItemStack(this.mainInventory[var1]);
                this.armorInventory[var1] = null;
            }
        }

        this.player.sendPacket(new Packet85SimpleSignal(EnumSignal.clear_inventory));
    }
    @Shadow
    public void destroyInventoryItemStack(ItemStack item_stack) {}
}
