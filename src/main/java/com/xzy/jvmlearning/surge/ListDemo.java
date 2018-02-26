package com.xzy.jvmlearning.surge;

/**
 * @author RuzzZZ
 * @since 26/01/2018 5:01 PM
 */
public class ListDemo {

    public static void main(String[] args) {
        Integer a = 1;
        Integer b = 2;
        Integer c = 3;
        Integer d = 3;
        Integer e = 321;
        Integer f = 321;
        Long g = 3L;
        System.out.println(c == d);     //T
        System.out.println(e == f);     //F
        System.out.println(c.equals(a + b));        //T
        System.out.println(c == (a + b));       //T
        System.out.println(g.equals(a + b));        //F
    }
}