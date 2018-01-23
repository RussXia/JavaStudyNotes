package com.xzy.jvmlearning.clazz.loading.classloader;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.reflect.Constructor;

/**
 * @author RuzzZZ
 * @since 17/01/2018 4:36 PM
 */
public class MethodHandlerTest {


    static class ClassA {
        public ClassA() {
        }

        public void println(String str) {
            System.out.println("ClassA--" + str);
        }
    }

    static class ClassB extends ClassA{
        public ClassB() {
        }

        @Override
        public void println(String str) {
            System.out.println("ClassB--" + str);
        }
    }

    public static void main(String[] args) throws Throwable {
        Object obj = ClassA.class.newInstance();
        getPrintlnVirtualMH(obj).invokeExact("Hello World");
        System.out.println("--------------New Line--------------");
        Object obj2 = ClassB.class.newInstance();
        getPrintlnSpecialMH(obj2).invokeExact("Hello World");
    }

    /**
     * 获取虚方法的MethodHandle
     * @param reveiver
     * @return
     */
    private static MethodHandle getPrintlnVirtualMH(Object reveiver) throws NoSuchMethodException, IllegalAccessException {
        /**
         * MethodType:代表"方法类型",包含了方法的返回值(methodType的第一个参数),以及后续的可变参数
         */
        MethodType methodType = MethodType.methodType(void.class, String.class);
        MethodHandle methodHandle = MethodHandles.lookup().findVirtual(reveiver.getClass(), "println", methodType);
        return methodHandle.bindTo(reveiver);
    }

    private static MethodHandle getPrintlnSpecialMH(Object reveiver) throws NoSuchMethodException, IllegalAccessException {
        MethodType methodType = MethodType.methodType(void.class, String.class);
        MethodHandle methodHandle = MethodHandles.lookup().findVirtual(reveiver.getClass().getSuperclass(), "println", methodType);
        return methodHandle.bindTo(reveiver);
    }
}