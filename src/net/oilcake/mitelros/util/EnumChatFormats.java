package net.oilcake.mitelros.util;

import com.google.common.collect.Lists;
import net.minecraft.EnumChatFormat;
import sun.reflect.ConstructorAccessor;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

public class EnumChatFormats {
    private static ConstructorAccessor constructorAccessor;
    private static int ordinary = EnumChatFormat.values().length;

    static {
        try {
            Constructor<?> ctor = EnumChatFormat.class.getDeclaredConstructors()[1];
            Method acquireConstructorAccessor;
            acquireConstructorAccessor = Constructor.class.getDeclaredMethod("acquireConstructorAccessor");
            acquireConstructorAccessor.setAccessible(true);
            acquireConstructorAccessor.invoke(ctor);
            Field field = Constructor.class.getDeclaredField("constructorAccessor");
            field.setAccessible(true);
            constructorAccessor = (ConstructorAccessor) field.get(ctor);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException | NoSuchFieldException e) {
            e.printStackTrace();
        }
    }

    private EnumChatFormats(){}
    /**
     * Enums
     * */
    public static final List<EnumChatFormat> VALUES = Lists.newArrayList(EnumChatFormat.values());
    public static final EnumChatFormat GLODC = newEnumChatFormat("GOLDC",'s',255,170,0);
    private static EnumChatFormat newEnumChatFormat(String name,char c, int r, int g, int b){
        try {
            EnumChatFormat format =  (EnumChatFormat) constructorAccessor.newInstance(new Object[]{name,ordinary++,c,r,g,b});
            VALUES.add(format);
            return format;
        } catch (InstantiationException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }
}
