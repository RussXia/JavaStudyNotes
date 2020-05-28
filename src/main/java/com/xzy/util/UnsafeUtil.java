package com.xzy.util;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * @author xiazhengyue
 * @since 2020-05-21
 */
public class UnsafeUtil {

    private static final Unsafe unsafe;


    static {
        try {
            //由于unsafe的工厂方法Unsafe.getUnsafe()不给调用,利用反射获得Unsafe对象
            Field field = Unsafe.class.getDeclaredField("theUnsafe");
            field.setAccessible(true);
            unsafe = (Unsafe) field.get(null);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new Error(e);
        }
    }

    public static Unsafe getInstance() {
        return unsafe;
    }
}
