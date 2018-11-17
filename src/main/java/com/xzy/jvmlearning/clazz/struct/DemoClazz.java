package com.xzy.jvmlearning.clazz.struct;

/**
 * @author RuzzZZ
 * @since 22/12/2017 8:08 PM
 */
public class DemoClazz {

    private String name;

    public DemoClazz(String name) {
        this.name = name;
    }

    public static void Test() {
        System.out.println("Test");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}