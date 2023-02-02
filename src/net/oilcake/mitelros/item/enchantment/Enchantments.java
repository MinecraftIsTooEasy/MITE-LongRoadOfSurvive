package net.oilcake.mitelros.item.enchantment;

import net.minecraft.Enchantment;
import net.oilcake.mitelros.util.Constant;
import net.xiaoyu233.fml.util.ReflectHelper;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import static net.minecraft.Enchantment.enchantmentsList;

public class Enchantments {


    public static List<Enchantment> individualEnchantments = new ArrayList<>();
    public static void registerEnchantments(){
        Enchantments.registerEnchantmentsUnsafe();
    }

    public static void registerEnchantmentsUnsafe(Enchantment... enchantments) {
        for (int i = 0, bLength = enchantmentsList.length; i < bLength; i++) {
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
    private static int getNextEnchantmentID(){
        return Constant.nextEnchantmentID++;
    }
}
