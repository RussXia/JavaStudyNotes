package com.xzy.jvmlearning.clazz.struct;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author RuzzZZ
 * @since 22/12/2017 8:08 PM
 */
public class LoadClassDemo {

    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchMethodException {
        Class clazz = Class.forName("com.xzy.jvmlearning.clazz.struct.DemoClazz");
        Object obj = clazz.getConstructors()[0].newInstance("name");
        System.out.println("The class name of instance object is " + obj.getClass().getName());
        Method getMethod = clazz.getMethod("getName");
        Method setMethod = clazz.getMethod("setName", String.class);
        setMethod.invoke(obj, "Hello World");
        Object result = getMethod.invoke(obj);
        System.out.println("result : " + result);

    }
}