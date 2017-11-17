package com.xzy.guava;

import com.google.common.util.concurrent.RateLimiter;

/**
 * Created by RuzzZZ on 18/09/2017.
 */
public class RateLimiterDemo {

    public static void main(String[] args) {
        noRateLimit();
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
        RateLimiter rateLimiter = RateLimiter.create(2);
        for (int i = 0; i < 10; i++) {
            double acquire = rateLimiter.acquire();
            System.out.println("call execute.." + i + "\tacquire : " + acquire);

        }
        Long end = System.currentTimeMillis();

        System.out.println(end - start);
    }
}
