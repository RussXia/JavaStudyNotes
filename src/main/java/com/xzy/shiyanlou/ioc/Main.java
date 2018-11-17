package com.xzy.shiyanlou.ioc;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

/**
 * @author RuzzZZ
 * @since 25/01/2018 10:21 AM
 */
public class Main {

    public static void ioc(String className, Shiyanlou s, String methodName, String name) throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        Class clazz = Class.forName(className);
        Object obj = clazz.newInstance();
        clazz.getMethod("set",Shiyanlou.class).invoke(obj, s);
        System.out.println(obj.toString());
        clazz.getMethod(methodName, String.class).invoke(obj, name);
    }

    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        ioc("com.xzy.shiyanlou.ioc.TestDemo", new ShiyanlouImpl(), "sayHello", "World");
    }
}