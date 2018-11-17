package com.xzy.util.idgen;

public class GenTest {


    public static void main(String[] args) {
        SnowFlakeIDGenerator snowFlakeIDGenerator = new SnowFlakeIDGenerator(32);
        System.out.println(Long.toBinaryString(32));
        for (int i = 0; i < 100; i++) {
            Long value = snowFlakeIDGenerator.generateKey().longValue();
            System.out.println(value);
            System.out.println(Long.toBinaryString(value));
            System.out.println();
        }
    }
}
