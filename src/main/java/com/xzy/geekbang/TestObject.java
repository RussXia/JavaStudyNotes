package com.xzy.geekbang;

public class TestObject {

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        System.out.println("TestObject.finalize!");
    }
}
