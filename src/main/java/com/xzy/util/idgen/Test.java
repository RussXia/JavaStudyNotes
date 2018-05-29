package com.xzy.util.idgen;

import java.util.Date;

public class Test {

    public static void main(String[] args) {
        Long now = System.currentTimeMillis();
        System.out.println(now);
        System.out.println(Long.toBinaryString(now));
        Date date = new Date(now);
        System.out.println(date);
    }
}
