package com.xzy.copy.reflect;

import com.esotericsoftware.reflectasm.ConstructorAccess;
import com.esotericsoftware.reflectasm.MethodAccess;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MethodInvokeCompareDemo {

    public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, InstantiationException, InvocationTargetException {
        {//反射调用
            ConstructorAccess<QueryTransportPriceForm> constructorAccess = ConstructorAccess.get(QueryTransportPriceForm.class);
            QueryTransportPriceForm priceForm = constructorAccess.newInstance();

            MethodAccess methodAccess = MethodAccess.get(QueryTransportPriceForm.class);
            methodAccess.invoke(priceForm, "setUserId", "qqq");
        }
        {
            ConstructorAccess<QueryTransportPriceForm> constructorAccess = ConstructorAccess.get(QueryTransportPriceForm.class);
            QueryTransportPriceForm priceForm = constructorAccess.newInstance();
            MethodAccess methodAccess = MethodAccess.get(QueryTransportPriceForm.class);
            int index = methodAccess.getIndex("setTest");
            Long start = System.currentTimeMillis();
            for (int i = 0; i < Integer.MAX_VALUE; i++) {
                methodAccess.invoke(priceForm, index, 10);
            }
            Long end = System.currentTimeMillis();
            System.out.println("ReflectASM : " + (end - start));
        }

        {
            Class<QueryTransportPriceForm> clazz = QueryTransportPriceForm.class;
            QueryTransportPriceForm priceForm = clazz.newInstance();
            Method method = clazz.getMethod("setTest", Integer.class);
            method.setAccessible(true);
            Long start = System.currentTimeMillis();
            for (int i = 0; i < Integer.MAX_VALUE; i++) {
                method.invoke(priceForm, 10);
            }
            Long end = System.currentTimeMillis();
            System.out.println("Java Reflect : " + (end - start));
        }

    }
}
