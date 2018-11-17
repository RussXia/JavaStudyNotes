package com.xzy.guava;

import com.google.common.util.concurrent.RateLimiter;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by RuzzZZ on 18/09/2017.
 */
public class RateLimiterDemo {

    private static RateLimiter rateLimiter = RateLimiter.create(100.0);

    private static volatile int count = 0;

    public static void main(String[] args) {
//        noRateLimit();
        rateLimit();
    }

    private static void noRateLimit() {
        Long start = System.currentTimeMillis();
        for (int i = 0; i < 10; i++) {
            System.out.println("call execute.." + i);

        }
        Long end = System.currentTimeMillis();

        System.out.println(end - start);

    }

    private static void rateLimit() {
        Long start = System.currentTimeMillis();
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(50, 100, 1000L, TimeUnit.MICROSECONDS, new ArrayBlockingQueue<>(1000));
        for (int i = 0; i < 1000; i++) {
            final int flag = i;
            threadPoolExecutor.execute(() -> {
                Boolean requireFlag = rateLimiter.tryAcquire();
                if (requireFlag) {
                    count++;
                    System.out.println("call execute.." + flag + "");
                }
            });
        }
        threadPoolExecutor.shutdown();

        Long end = System.currentTimeMillis();
        System.out.println(end - start);
        System.out.println(count);
    }
}
