package com.xzy.lock;

import com.xzy.lock.CASInteger;

public class CASIntegerTest {

    public static void main(String[] args) {
        //true:100
        CASInteger casInteger = new CASInteger(10);
        boolean flag = casInteger.compareAndSetValue(10, 100);
        System.out.println(flag + ":" + casInteger.getValue());
        //true:101
        flag = casInteger.compareAndSetValue(100, 101);
        System.out.println(flag + ":" + casInteger.getValue());
        //false:101
        flag = casInteger.compareAndSetValue(100, 101);
        System.out.println(flag + ":" + casInteger.getValue());

    }
}
