package com.xzy.test;

/**
 * volitile关键字的测试
 * Created by RuzzZZ on 29/08/2017.
 */
public class VolitileDemo {

    private static int count = 0;

    private static void increment() {
        try {
            Thread.sleep(10L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        count++;
    }

    public static void main(String[] args) throws InterruptedException {

        for (int i = 0; i < 1000; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    VolitileDemo.increment();
                }
            }).start();
        }
        Thread.sleep(10000L);
        System.out.println("运行结果:Counter.count=" + VolitileDemo.count);
    }
}
