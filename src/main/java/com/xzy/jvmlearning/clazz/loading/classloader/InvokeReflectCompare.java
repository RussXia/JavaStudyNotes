package com.xzy.jvmlearning.clazz.loading.classloader;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.reflect.Method;

/**
 * @author RuzzZZ
 * @since 18/01/2018 11:33 AM
 */
public class InvokeReflectCompare {

    private static final int MAX_THRESHOLD = Integer.MAX_VALUE;

    static class Test {
        public String returnHello() {
            int i1 = 1321344123/12132;
            int i2 = 1321344123/12132;
            int i3 = 1321344123/12132;
            int i4 = 1321344123/12132;
            int i5 = 1321344123/12132;
            int i6 = 1321344123/12132;
            int i7 = 1321344123/12132;
            return "Hello";
        }
    }

    public static void main(String[] args) throws Throwable {
        //直接调用
        Test test = new Test();
        Long start = System.currentTimeMillis();
        for (int i = 0; i < MAX_THRESHOLD; i++) {
            String str = test.returnHello();
        }
        Long end = System.currentTimeMillis();
        System.out.println("direct call spend :" + (end - start));
        //反射
        Object obj = Test.class.newInstance();
        Method method = Test.class.getMethod("returnHello");
        start = System.currentTimeMillis();
        for (int i = 0; i < MAX_THRESHOLD; i++) {
            String str = (String)method.invoke(obj);
        }
        end = System.currentTimeMillis();
        System.out.println("reflect call spend :" + (end - start));
        //句柄模拟
        Object obj2 = Test.class.newInstance();
        MethodType methodType = MethodType.methodType(String.class);
        MethodHandle methodHandle = MethodHandles.lookup().findVirtual(Test.class, "returnHello", methodType).bindTo(obj2);
        start = System.currentTimeMillis();
        for (int i = 0; i < MAX_THRESHOLD; i++) {
            String str = (String)methodHandle.invokeExact();
        }
        end = System.currentTimeMillis();
        System.out.println("invoke call spend :" + (end - start));
    }
}