package com.xzy.test;

import com.xzy.serializable.CarSnapInfo;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class SImpleTest {

    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, NoSuchFieldException {
        CarSnapInfo carSnapInfo = new CarSnapInfo();
        Class clazz = carSnapInfo.getClass();
        Field field = clazz.getDeclaredField("modelName");
        field.set(carSnapInfo,"qqq");
        System.out.println(field.get(carSnapInfo));
    }
}
