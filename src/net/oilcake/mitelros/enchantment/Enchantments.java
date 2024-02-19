package net.oilcake.mitelros.enchantment;

import net.minecraft.Enchantment;
import net.minecraft.yq;
import net.oilcake.mitelros.util.Constant;
import net.xiaoyu233.fml.util.ReflectHelper;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import static net.minecraft.Enchantment.enchantmentsList;

public class Enchantments {


    public static final Enchantment enchantmentMelting = new EnchantmentMelting(Constant.getNextEnchantmentID(), yq.c,10);
    public static final Enchantment enchantmentAbsorb = new EnchantmentAbsorb(Constant.getNextEnchantmentID(), yq.c,10);
    public static final Enchantment enchantmentVanishing = new EnchantmentVanishing(Constant.getNextEnchantmentID(), yq.c,15);
    public static final Enchantment enchantmentCallofNether = new EnchantmentCallofNether(Constant.getNextEnchantmentID(), yq.b,10);
    public static final Enchantment enchantmentDestroying = new EnchantmentDestroying(Constant.getNextEnchantmentID(), yq.d,20);
    public static final Enchantment enchantmentInfinity = new EnchantmentInfinity(Constant.getNextEnchantmentID(), yq.c,20);
    public static final Enchantment enchantmentArrogance = new EnchantmentArrogance(Constant.getNextEnchantmentID(), yq.c,15);
    public static final Enchantment enchantmentThresher = new EnchantmentThresher(Constant.getNextEnchantmentID(),yq.c, 10);
    public static final Enchantment enchantmentSweeping = new EnchantmentSweeping(Constant.getNextEnchantmentID(),yq.c,10);
//    public static final Enchantment enchantmentProtectFire = new EnchantmentProtectFire(Constant.getNextEnchantmentID(), yq.b,15);

    public static List<Enchantment> enchantmentsListC = new ArrayList<>();
    public static void registerEnchantments(){
        Enchantments.registerEnchantmentsUnsafe(enchantmentMelting, enchantmentAbsorb, enchantmentVanishing, enchantmentDestroying,enchantmentCallofNether,enchantmentInfinity,enchantmentArrogance,enchantmentThresher,enchantmentSweeping);
    }
    public static void registerEnchantmentsUnsafe(Enchantment... enchantments) {
        for (int i = 80, bLength = enchantmentsList.length; i < bLength; i++) {
            if (enchantmentsList[i] == null) {
                for (int j = 0, enchantmentsLength = enchantments.length; j < enchantmentsLength; j++) {
                    enchantmentsList[i + j] = enchantments[j];
                }
                break;
            }
        }
        ArrayList<Enchantment> var0 = new ArrayList<>();
        for (Enchantment enchantment : enchantmentsList) {
            if (enchantment != null) {
                var0.add(enchantment);
            }
        }
        try {
            Field enchantmentsBookList = Enchantment.class.getDeclaredField("enchantmentsBookList");
            ReflectHelper.updateFinalModifiers(enchantmentsBookList);
            enchantmentsBookList.setAccessible(true);
            enchantmentsBookList.set(null,var0.toArray(new Enchantment[0]));
        } catch (IllegalAccessException | NoSuchFieldException e) {
            e.printStackTrace();
        }
    }

}
