package net.oilcake.mitelros.mixins.block.tileentity;

import net.minecraft.*;
import net.oilcake.mitelros.achivements.AchievementExtend;
import net.oilcake.mitelros.block.Blocks;
import net.oilcake.mitelros.item.ItemGoldenAppleLegend;
import net.oilcake.mitelros.item.Items;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.Random;

@Mixin(SlotArmor.class)
public class ContainerEnchantmentMixin extends Container {
    @Shadow
    public IInventory tableInventory;
    @Shadow
    public int[] enchantLevels = new int[3];
    @Shadow
    private int posX;
    @Shadow
    private int posY;
    @Shadow
    private int posZ;
    @Shadow
    private Random rand = new Random();

    public ContainerEnchantmentMixin(EntityPlayer player) {
        super(player);
    }

    @Shadow
    public boolean canInteractWith(EntityPlayer entityPlayer) {
        return false;
    }
    @Overwrite
    public boolean enchantItem(EntityPlayer par1EntityPlayer, int par2) {
        ItemStack var3 = this.tableInventory.getStackInSlot(0);
        int experience_cost = Enchantment.getExperienceCost(this.enchantLevels[par2]);
        if (this.enchantLevels[par2] <= 0 || var3 == null || par1EntityPlayer.experience < experience_cost && !par1EntityPlayer.capabilities.isCreativeMode) {
            return false;
        } else {
            if (!this.world.isRemote) {
                if (ItemPotion.isBottleOfWater(var3)) {
                    par1EntityPlayer.addExperience(-experience_cost);
                    this.tableInventory.setInventorySlotContents(0, new ItemStack(Item.expBottle));
                    return true;
                }
                if (ItemGoldenAppleLegend.isUnenchantedGoldenApple(var3)){
                    par1EntityPlayer.addExperience(-experience_cost);
                    this.tableInventory.setInventorySlotContents(0, new ItemStack(Items.Goldenapplelegend, 1, 1));
                    par1EntityPlayer.triggerAchievement(AchievementExtend.decimator);
                    return true;
                }
                if (ItemGoldenApple.isUnenchantedGoldenApple(var3)) {
                    par1EntityPlayer.addExperience(-experience_cost);
                    this.tableInventory.setInventorySlotContents(0, new ItemStack(Item.appleGold, 1, 1));
                    return true;
                }

                List var4 = EnchantmentManager.buildEnchantmentList(this.rand, var3, this.enchantLevels[par2]);
                boolean var5 = var3.itemID == Item.book.itemID;
                if (var4 != null) {
                    par1EntityPlayer.addExperience(-experience_cost);
                    if (var5) {
                        var3.itemID = Item.enchantedBook.itemID;
                    }

                    int var6 = var5 ? this.rand.nextInt(var4.size()) : -1;

                    for(int var7 = 0; var7 < var4.size(); ++var7) {
                        EnchantmentInstance var8 = (EnchantmentInstance)var4.get(var7);
                        if (!var5 || var7 == var6) {
                            if (var5) {
                                Item.enchantedBook.addEnchantment(var3, var8);
                            } else {
                                var3.addEnchantment(var8.enchantmentobj, var8.enchantmentLevel);
                            }
                        }
                    }

                    this.getSlot(0).onSlotChanged();
                }
            }

            return true;
        }
    }

    @Nonnull
    @Overwrite
    public int calcEnchantmentLevelsForSlot(Random random, int slotIndex, int numAccessibleBookshelves, ItemStack itemStack) {
        Item item = itemStack.getItem();
        if (!ItemPotion.isBottleOfWater(itemStack) && !ItemGoldenApple.isUnenchantedGoldenApple(itemStack) && !ItemGoldenAppleLegend.isUnenchantedGoldenApple(itemStack)) {
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
            return item.itemID == Items.Goldenapplelegend.itemID ? 25 : 2;
        }
    }


}
