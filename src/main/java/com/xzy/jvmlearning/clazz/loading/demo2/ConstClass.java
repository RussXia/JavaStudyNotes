package com.xzy.jvmlearning.clazz.loading.demo2;

/**
 * @author RuzzZZ
 * @since 10/01/2018 4:45 PM
 */
public class ConstClass {

    static {
        System.out.println("ConstClass init!");
    }

    public static final String HELLO_WORLD = "Hello World";
}