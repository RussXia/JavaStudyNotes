package com.xzy.copy.test;

import com.alibaba.fastjson.JSONObject;

import java.lang.reflect.InvocationTargetException;

public class TestMain {

    public static void main(String[] args) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        TestBooleanA testBooleanA = new TestBooleanA();
        testBooleanA.setIsDeleted(true);
        testBooleanA.getIsDeleted();

        testBooleanA.setStr("test111");
        TestBooleanB testBooleanB = new TestBooleanB();
        System.out.println(testBooleanB.getClass());
        TestBooleanB testBooleanC = new TestBooleanB();
        TestBooleanB testBooleanD = new TestBooleanB();
        org.springframework.beans.BeanUtils.copyProperties(testBooleanA, testBooleanB);
        org.apache.commons.beanutils.BeanUtils.copyProperties(testBooleanC, testBooleanA);
        org.apache.commons.beanutils.PropertyUtils.copyProperties(testBooleanD, testBooleanA);
        System.out.println(JSONObject.toJSONString(testBooleanB));
        System.out.println(JSONObject.toJSONString(testBooleanC));
        System.out.println(JSONObject.toJSONString(testBooleanD));
    }
}
