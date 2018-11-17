package com.xzy.lock;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

public class CASInteger {

    private static final Unsafe unsafe;

    private volatile int value;

    private static final long valueOffset;

    static {
        try {
            //由于unsafe的工厂方法Unsafe.getUnsafe()不给调用,利用反射获得Unsafe对象
            Field field = Unsafe.class.getDeclaredField("theUnsafe");
            field.setAccessible(true);
            unsafe = (Unsafe) field.get(null);
            valueOffset = unsafe.objectFieldOffset(CASInteger.class.getDeclaredField("value"));
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new Error(e);
        }
    }

    public int getValue() {
        return value;
    }

    public CASInteger(int value) {
        this.value = value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public final boolean compareAndSetValue(int expect, int update) {
        return unsafe.compareAndSwapInt(this, valueOffset, expect, update);
    }
}
