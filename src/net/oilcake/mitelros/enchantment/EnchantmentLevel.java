package net.oilcake.mitelros.enchantment;

import net.minecraft.EnumEquipmentMaterial;
import net.minecraft.EnumQuality;
import net.minecraft.yq;
import sun.reflect.ConstructorAccessor;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class EnchantmentLevel {
    private static ConstructorAccessor constructorAccessor;
    private static int ordinary = yq.values().length;
    static {
        try {
            Constructor<?> CTOR = yq.b.getClass().getDeclaredConstructors()[0];
            Method acquireConstructorAccessor;
            acquireConstructorAccessor = Constructor.class.getDeclaredMethod("acquireConstructorAccessor");
            acquireConstructorAccessor.setAccessible(true);
            acquireConstructorAccessor.invoke(CTOR);
            Field field = Constructor.class.getDeclaredField("constructorAccessor");
            field.setAccessible(true);
            constructorAccessor = (ConstructorAccessor) field.get(CTOR);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException | NoSuchFieldException e) {
            throw new NumberFormatException();
        }
    }

    public static final yq Null = newEnumEnchantmentLevel(0, "Null", 0);

    private static final yq newEnumEnchantmentLevel(int color_index, String name, int standard_weight){
        try {
            return (yq) constructorAccessor.newInstance(new Object[]{name, ordinary++, color_index, name, standard_weight});
        } catch (InstantiationException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }


}

