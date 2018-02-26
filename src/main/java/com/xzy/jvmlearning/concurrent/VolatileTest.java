package com.xzy.jvmlearning.concurrent;

import java.util.concurrent.CountDownLatch;

/**
 * volatile变量自增测试
 *
 * @author RuzzZZ
 * @since 05/02/2018 11:29 AM
 */
public class VolatileTest {

    public static volatile int race = 0;

    public static CountDownLatch countDownLatch = new CountDownLatch(10);

    public static void increase() {
        race++;
    }

    public static void main(String[] args) {
        Thread[] threads = new Thread[20];
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(() -> {
                for (int j = 0; j < 100000; j++) {
                    increase();
                }
                System.out.println("[" + Thread.currentThread().getName() + "] is down!");
                countDownLatch.countDown();
            });
            threads[i].start();
        }
        //所有线程都ok了，输出race的值
        new Thread(() -> {
            try {
                countDownLatch.await();
                System.out.println(race);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
}