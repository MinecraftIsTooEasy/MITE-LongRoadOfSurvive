package net.oilcake.mitelros.mixins.block.tileentity;

import net.minecraft.*;
import net.oilcake.mitelros.block.Blocks;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

import javax.annotation.Nonnull;
import java.util.Random;

@Mixin(SlotArmor.class)
public class ContainerEnchantmentMixin extends Container {
    @Shadow
    private int posX;
    @Shadow
    private int posY;
    @Shadow
    private int posZ;

    public ContainerEnchantmentMixin(EntityPlayer player) {
        super(player);
    }

    @Shadow
    public boolean canInteractWith(EntityPlayer entityPlayer) {
        return false;
    }


    @Nonnull
    @Overwrite
    public int calcEnchantmentLevelsForSlot(Random random, int slotIndex, int numAccessibleBookshelves, ItemStack itemStack) {
        Item item = itemStack.getItem();
        if (!ItemPotion.isBottleOfWater(itemStack) && !ItemGoldenApple.isUnenchantedGoldenApple(itemStack)) {
            if (item.getItemEnchantability() <= 0) {
                return 0;
            } else {
                if (numAccessibleBookshelves > 24) {
                    numAccessibleBookshelves = 24;
                }
                boolean Enhanced = super.world.getBlock(this.posX, this.posY - 1, this.posZ) == Blocks.blockEnchantEnhancer;
                Block enchantment_table_block = super.world.getBlock(this.posX, this.posY, this.posZ);
                int enchantment_table_power = (1 + numAccessibleBookshelves) * (enchantment_table_block == Block.enchantmentTableEmerald ? 2 : 4) * (Enhanced ? 2 : 1);
                int enchantment_levels = EnchantmentManager.getEnchantmentLevelsAlteredByItemEnchantability(enchantment_table_power, item);
                float fraction = (1.0F + (float)slotIndex) / 3.0F;
                if (slotIndex < 2) {
                    fraction += (random.nextFloat() - 0.5F) * 0.2F;
                }

                return Math.max(Math.round((float)enchantment_levels * fraction), 1);
            }
        } else {
            return 2;
        }
    }


}
