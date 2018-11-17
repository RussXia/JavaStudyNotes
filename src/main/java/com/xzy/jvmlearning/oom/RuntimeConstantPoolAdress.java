package com.xzy.jvmlearning.oom;

/**
 * @author RuzzZZ
 * @since 17/11/2017 4:27 PM
 */
public class RuntimeConstantPoolAdress {

    static final String TEST_WORLD = "test world";

    public static void main(String[] args) {
        String str = new String("test world");
        System.out.println(str.intern() == TEST_WORLD);
        System.out.println(str.intern() == str);
    }
}