package com.xzy.jvmlearning.clazz.loading.reflection;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Method;

/**
 * @author RuzzZZ
 * @since 18/01/2018 2:07 PM
 */
@Slf4j
public class InvokeAccessiableTrue {

    public static void main(String[] args) throws Throwable {

        Object obj = Demo.class.newInstance();

        Method sayHello = Demo.class.getDeclaredMethod("sayHello");
        Method sayHi = Demo.class.getDeclaredMethod("sayHi");

        System.out.println("reflection:" + sayHello.invoke(obj));

        //sayHi方法为私有方法，直接调用会出现IllegalAccessException
        try {
            System.out.println("reflection:" + sayHi.invoke(obj));
        } catch (Exception e) {
            log.error("reflection failed:", e);
        }

        //设置sayHi方法的可见性
        sayHi.setAccessible(true);
        System.out.println("reflection:" + sayHi.invoke(obj));
    }
}