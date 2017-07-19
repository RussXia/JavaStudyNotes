package com.xzy.concurrent2;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 原子操作,AtomicInteger和AtomicLong、AtomicBoolean、AtomicReference类似，此处不做过多演示了
 * Created by RuzzZZ on 18/07/2017.
 */
public class AtomicIntegerTest {

    private static ExecutorService exec = new ThreadPoolExecutor(10, 20, 1000,
            TimeUnit.MILLISECONDS, new ArrayBlockingQueue<>(1000));

    private static volatile Integer integer = 100;

    @Test
    public void testAtomicInteger() throws InterruptedException {
        AtomicInteger atomicInteger = new AtomicInteger(100);
        try {
            for (int i = 0; i < 10; i++) {
                exec.execute(() -> System.out.println(Thread.currentThread().getName() + "-a : " + atomicInteger.addAndGet(10)));
            }
//            for (int i = 0; i < 10; i++) {
//                exec.execute(() -> System.out.println(Thread.currentThread().getName() + "-b : " + (integer + 10)));
//            }
        } finally {
            exec.shutdown();
        }
        Assert.assertEquals(atomicInteger.get(), 200);
    }
}