package com.xzy.stream;

import java.util.stream.LongStream;

public class StreamCompare {

    public static void main(String[] args) {
        long start1 = System.currentTimeMillis();
        long result1 = LongStream.range(0L, 100000000000L).parallel().sum();
        long end1 = System.currentTimeMillis();
        System.out.println("parallel:" + (end1 - start1) + "-" + result1);

        long start2 = System.currentTimeMillis();
        long result2 = LongStream.range(0L, 100000000000L).sum();
        long end2 = System.currentTimeMillis();
        System.out.println("stream:" + (end2 - start2) + "-" + result2);

        long start3 = System.currentTimeMillis();
        long result3 = 0;
        for (long i = 0L; i < 100000000000L; i++) {
            result3 = result3 + i;
        }
        long end3 = System.currentTimeMillis();
        System.out.println("for:" + (end3 - start3) + "-" + result3);


    }
}
