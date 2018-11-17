package com.xzy.copy.test;

import org.apache.commons.beanutils.PropertyUtils;

import java.lang.reflect.InvocationTargetException;

public class TestDemo2 {

    public static void main(String[] args) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        TestBooleanA testBooleanA = new TestBooleanA();
        testBooleanA.setIsDeleted(true);
        testBooleanA.setStr("test111");
        System.out.println(PropertyUtils.describe(testBooleanA));
    }
}
