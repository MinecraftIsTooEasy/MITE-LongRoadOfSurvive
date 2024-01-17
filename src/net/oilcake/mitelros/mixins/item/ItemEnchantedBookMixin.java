package net.oilcake.mitelros.mixins.item;

import net.minecraft.*;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

import java.util.Random;
@Mixin(ItemEnchantedBook.class)
public class ItemEnchantedBookMixin extends Item{
    @Overwrite
    public StructurePieceTreasure func_92112_a(Random par1Random, int par2, int par3, int par4) {
        Enchantment var5 = Enchantment.enchantmentsBookList[par1Random.nextInt(Enchantment.enchantmentsBookList.length)];
        while(var5.rarity == yq.d || var5.rarity == yq.c){
            var5 = Enchantment.enchantmentsBookList[par1Random.nextInt(Enchantment.enchantmentsBookList.length)];
        }
        ItemStack var6 = new ItemStack(this.itemID, 1, 0);
        int var7 = MathHelper.getRandomIntegerInRange(par1Random, 1, var5.getNumLevels());
        this.addEnchantment(var6, new EnchantmentInstance(var5, var7));
        return new StructurePieceTreasure(var6, par2, par3, par4);
    }
    @Shadow
    public void addEnchantment(ItemStack par1ItemStack, EnchantmentInstance par2EnchantmentData) {

    }
}
