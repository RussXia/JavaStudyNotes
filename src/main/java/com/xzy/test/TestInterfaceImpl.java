package com.xzy.test;

/**
 * Created by RuzzZZ on 2017/2/24.
 */
public class TestInterfaceImpl implements TestInterface {


    public static void main(String[] args){
        TestInterfaceImpl testInterface = new TestInterfaceImpl();
        testInterface.f();

        try{
            throw new RuntimeException("打点vterwthfddssf");
        } catch (Exception e){
            System.out.println(e.getMessage());
            System.out.println(e.getLocalizedMessage());
        }
    }
}
