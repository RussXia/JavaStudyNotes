package com.xzy.copy.reflect;

import com.esotericsoftware.reflectasm.ConstructorAccess;
import com.esotericsoftware.reflectasm.MethodAccess;

public class ReflectASMDemo {

    public static void main(String[] args) {
        //反射调用
        ConstructorAccess<QueryTransportPriceForm> constructorAccess = ConstructorAccess.get(QueryTransportPriceForm.class);
        QueryTransportPriceForm priceForm = constructorAccess.newInstance();

        MethodAccess methodAccess = MethodAccess.get(QueryTransportPriceForm.class);
        methodAccess.invoke(priceForm,"setUserId","qqq");

        //field
//        FieldAccess fieldAccess = FieldAccess.get(QueryTransportPriceForm.class);
//        fieldAccess.set(priceForm,"name","www");

        System.out.println(priceForm);
    }
}
