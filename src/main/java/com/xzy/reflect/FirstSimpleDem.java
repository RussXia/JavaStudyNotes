package com.xzy.reflect;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Created by RuzzZZ on 2017/2/20.
 */
public class FirstSimpleDem {

    public Integer field1;

    public String field2;

    public void sayHi() {

    }

    public void sayHi(String arg1) {

    }

    public void sayHi(String arg1, String arg2) {

    }

    public static void main(String[] args) throws ClassNotFoundException {
//        Class clazz = FirstSimpleDem.class;
        Class clazz = Class.forName("com.xzy.reflect.FirstSimpleDem");
        System.out.println("类名：" + clazz.getSimpleName());
        System.out.println("全限定类名：" + clazz.getName());

        Field[] fields = clazz.getFields();
        for (Field field : fields) {
            System.out.println("==============================Field start==============================");
            System.out.println(field.getName() + "\t" + field.getType().getName());
            System.out.println("==============================Field end==============================");
        }

        Method[] methods = clazz.getMethods();
        for (Method method : methods) {
            System.out.println("==============================Method start==============================");
            System.out.println(method.getName() + ";the parameterTypes");
            Class[] parameterTypes = method.getParameterTypes();
            for (Class param : parameterTypes) {
                System.out.println(param.getName());
            }
            System.out.println("==============================Method end==============================");
        }
    }
}
