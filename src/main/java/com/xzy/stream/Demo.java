package com.xzy.stream;

import java.util.Random;
import java.util.stream.IntStream;

/**
 * @description: ddd
 * @author: xzy
 * @create: 2018-09-14 17:59
 **/
public class Demo {

    public static void main(String[] args) {
        Random random = new Random();
        IntStream.range(1, 1000).forEach(value -> System.out.println(random.nextBoolean()));
    }
}
