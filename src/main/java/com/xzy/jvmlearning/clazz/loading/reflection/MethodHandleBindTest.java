package com.xzy.jvmlearning.clazz.loading.reflection;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;

/**
 * MethodHandle的基本用法
 * @author RuzzZZ
 * @since 18/01/2018 2:38 PM
 */
public class MethodHandleBindTest {

    public static void main(String[] args) throws Throwable {
        MethodType methodType = MethodType.methodType(void.class);
        MethodHandle methodHandle = MethodHandles.lookup().findVirtual(Father.class, "sayHello", methodType);

        //MethodHandle支持多态
        methodHandle.bindTo(new Son()).invoke();
        methodHandle.bindTo(new Father()).invoke();
    }
}