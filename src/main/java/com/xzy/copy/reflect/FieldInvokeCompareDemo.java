package com.xzy.copy.reflect;

import com.esotericsoftware.reflectasm.ConstructorAccess;
import com.esotericsoftware.reflectasm.FieldAccess;

import java.lang.reflect.Field;

public class FieldInvokeCompareDemo {

    public static final String INPUT_STR = "qqq";

    public static final int LOOP_TIMES = 10000;

    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException, InstantiationException {
        {
            ConstructorAccess<QueryTransportPriceForm> constructorAccess = ConstructorAccess.get(QueryTransportPriceForm.class);
            QueryTransportPriceForm priceForm = constructorAccess.newInstance();
            FieldAccess fieldAccess = FieldAccess.get(QueryTransportPriceForm.class);
            int index = fieldAccess.getIndex("testStr");
            Long start = System.currentTimeMillis();
            for (int i = 0; i < LOOP_TIMES; i++) {
                fieldAccess.set(priceForm, index, INPUT_STR);
            }
            Long end = System.currentTimeMillis();
            System.out.println("Reflect ASM:    " + (end - start));
        }

        {
            Class clazz = QueryTransportPriceForm.class;
            QueryTransportPriceForm priceForm = (QueryTransportPriceForm) clazz.newInstance();
            Field field = clazz.getField("testStr");
            Long start = System.currentTimeMillis();
            for (int i = 0; i < LOOP_TIMES; i++) {
                field.set(priceForm, INPUT_STR);
            }
            Long end = System.currentTimeMillis();
            System.out.println("Native Reflect: " + (end - start));
        }

    }
}
