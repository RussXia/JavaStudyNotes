package com.xzy.learning.concurrent;

import java.util.concurrent.CountDownLatch;

/**
 * volatile测试demo
 * Created by RuzzZZ on 2017/2/8.
 */
public class VolatileDemo implements Runnable {

    private volatile int value1;
    private int value2;
    private final CountDownLatch startLatch;

    public VolatileDemo(int value1, int value2, CountDownLatch startLatch) {
        this.value1 = value1;
        this.value2 = value2;
        this.startLatch = startLatch;
    }

    public int getValue1() {
        return value1;
    }

    public int getValue2() {
        return value2;
    }

    @Override
    public void run() {
        for (int i = 0; i < 100000; i++) {
            value1++;
            value2++;
            System.out.println("The current thread is " + Thread.currentThread().getName() + "\t,i = " + i + ",\t" +
                    "value1 = " + value1 + "\t , value2 = " + value2);
        }
        startLatch.countDown();
    }

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(10);
        VolatileDemo volatileDemo = new VolatileDemo(1, 1, countDownLatch);
        System.out.println(countDownLatch.getCount());
        for (int i = 0; i < 10; i++) {
            new Thread(volatileDemo).start();
        }
        //当所有线程结束后，打出对应的value1和value2
        countDownLatch.await();
        Thread.sleep(5000);
        System.out.println("volatile value1:" + volatileDemo.getValue1());
        System.out.println("value2:" + volatileDemo.getValue2());
    }
}
