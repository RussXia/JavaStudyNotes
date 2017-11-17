package com.xzy.shiyanlou;

/**
 * @author RuzzZZ
 * @since 13/11/2017 10:57 AM
 */
public class TestApp {

    public static void main(String[] args) {
        AObject aObject = new AObject();
        aObject.setAInt(100);
        aObject.setAStr("Hello");

        BObject bObject = new BObject();
        bObject.setAInt(100);
        bObject.setAStr("Hello");
        bObject.setBInt(101);
        bObject.setBStr("Hello World");

        System. gc();

        System.out.println(aObject.equals(bObject));
        System.out.println(bObject.equals(aObject));

        System.out.println();

        System.out.println(aObject.hashCode());
        System.out.println(bObject.hashCode());

        System. gc();

        System.out.println();
    }
}