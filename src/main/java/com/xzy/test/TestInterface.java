package com.xzy.test;

/**
 * Created by RuzzZZ on 2017/2/24.
 */
public interface TestInterface {

    default void f(){
        System.out.println("Hello ");
    }
}
