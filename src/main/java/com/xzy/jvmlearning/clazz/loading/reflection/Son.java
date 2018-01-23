package com.xzy.jvmlearning.clazz.loading.reflection;

/**
 * @author RuzzZZ
 * @since 18/01/2018 2:06 PM
 */
public class Son extends Father{

    public Son() {
    }

    @Override
    public void sayHello() {
        System.out.println("I am son!");
    }
}