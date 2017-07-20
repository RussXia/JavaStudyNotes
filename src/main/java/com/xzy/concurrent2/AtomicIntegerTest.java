package com.xzy.concurrent2;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * AtomicInteger等类是无锁类！
 * 原子操作,AtomicInteger和AtomicLong、AtomicBoolean、AtomicReference类似，此处不做过多演示了
 *
 * AtomicInteger里面有很多CAS操作
 * CAS算法的过程是这样：它包含3个参数CAS(Value,Expected,NewValue)。
 * V表示要更新的变量，E表示预期值，N表示新值。
 * 仅当V值等于E值时，才会将V的值设为N，如果V值和E值不同，则说明已经有其他线程做了更新，则当前线程什么都不做。
 * 最后，CAS返回当前V的真实值。
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