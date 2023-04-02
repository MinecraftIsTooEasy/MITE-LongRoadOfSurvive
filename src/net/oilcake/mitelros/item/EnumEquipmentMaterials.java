package net.oilcake.mitelros.item;

import net.minecraft.Enchantment;
import net.minecraft.EnumEquipmentMaterial;
import net.minecraft.EnumQuality;
import sun.reflect.ConstructorAccessor;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class EnumEquipmentMaterials {
    private static ConstructorAccessor constructorAccessor;
    private static int ordinary = EnumEquipmentMaterial.values().length;
    static {
        try {
            Constructor<?> CTOR = EnumEquipmentMaterial.silver.getClass().getDeclaredConstructors()[0];
            Method acquireConstructorAccessor;
            acquireConstructorAccessor = Constructor.class.getDeclaredMethod("acquireConstructorAccessor");
            acquireConstructorAccessor.setAccessible(true);
            acquireConstructorAccessor.invoke(CTOR);
            Field field = Constructor.class.getDeclaredField("constructorAccessor");
            field.setAccessible(true);
            constructorAccessor = (ConstructorAccessor) field.get(CTOR);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException | NoSuchFieldException e) {
            e.printStackTrace();
        }
    }
    public static final EnumEquipmentMaterial nickel = newEnumEquipmentMaterial(8.0F, 30,EnumQuality.masterwork, "nickel");
    public static final EnumEquipmentMaterial tungsten = newEnumEquipmentMaterial(128.0F,50,EnumQuality.legendary,"tungsten");
    public static final EnumEquipmentMaterial wolf_fur = newEnumEquipmentMaterial(2.0F,20,EnumQuality.excellent,"wolf_fur");
    public static final EnumEquipmentMaterial maid = newEnumEquipmentMaterial(0.0625F,250,EnumQuality.average,"maid");

    public static final EnumEquipmentMaterial vibranium = newEnumEquipmentMaterial(4.0F,0,EnumQuality.poor,"vibranium");
    public static final EnumEquipmentMaterial magical = newEnumEquipmentMaterial(0.125F,100,EnumQuality.wretched,"magical");
    private static final EnumEquipmentMaterial newEnumEquipmentMaterial(float durability, int enchantability, EnumQuality max_quality, String name){
        try {
            return (EnumEquipmentMaterial) constructorAccessor.newInstance(new Object[]{name,ordinary++,durability,enchantability,max_quality,name});
        } catch (InstantiationException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }
}
