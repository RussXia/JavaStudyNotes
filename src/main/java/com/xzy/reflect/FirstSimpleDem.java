package com.xzy.reflect;

import java.lang.reflect.Method;

/**
 * Created by RuzzZZ on 2017/2/20.
 */
public class FirstSimpleDem {

    public void sayHi() {

    }

    public void sayHi(String arg1) {

    }

    public void sayHi(String arg1, String arg2) {

    }

    public static void main(String[] args) throws ClassNotFoundException {
//        Class clazz = FirstSimpleDem.class;
        Class clazz = Class.forName("com.xzy.reflect.FirstSimpleDem");
        Method[] methods = clazz.getMethods();
        for (Method method : methods) {
            System.out.println("==============================start==============================");
            System.out.println(method.getName() + ";the parameterTypes");
            Class[] parameterTypes = method.getParameterTypes();
            for (Class param : parameterTypes) {
                System.out.println(param.getName());
            }
            System.out.println("==============================end==============================");
        }
    }
}
