package com.xzy.copy;

import java.lang.reflect.InvocationTargetException;

public class BeanUtilsTestDemo {

    public static void main(String[] args) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        TestA testA = new TestA();
        testA.setTestStr("test12321");
        testA.setTestDiff("10");
        TestB testB1 = new TestB();
        TestB testB2 = new TestB();
        TestB testB3 = new TestB();
        org.springframework.beans.BeanUtils.copyProperties(testA, testB1);
        System.out.println(testB1);
        org.apache.commons.beanutils.BeanUtils.copyProperties(testB2, testA);
        System.out.println(testB2);
        org.apache.commons.beanutils.PropertyUtils.copyProperties(testB3, testA);
        System.out.println(testB3);
    }
}
