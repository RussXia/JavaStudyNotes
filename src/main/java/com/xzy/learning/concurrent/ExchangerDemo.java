package com.xzy.learning.concurrent;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Exchanger;

/**
 * Exchanger支持两个线程之间交换数据(只支持两个线程)
 * 只有在两个线程都调用exchange()方法后，才开始交换数据或者使用的空间。
 * Exchanger 可能被视为 SynchronousQueue 的双向形式。
 * @author RuzzZZ
 * @since  1.0
 */
public class ExchangerDemo {

    public static void main(String[] args) throws InterruptedException {
        Exchanger<String> exchanger = new Exchanger<String>();
        CountDownLatch countDownLatch = new CountDownLatch(2);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    String str = "qqqq";
                    System.out.println(Thread.currentThread().getName()+"---------"+str);
                    str = exchanger.exchange(str);
                    System.out.println(Thread.currentThread().getName()+"---------"+str);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    countDownLatch.countDown();
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    String str = "wwww";
                    System.out.println(Thread.currentThread().getName()+"---------"+str);
                    str = exchanger.exchange(str);
                    System.out.println(Thread.currentThread().getName()+"---------"+str);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    countDownLatch.countDown();
                }
            }
        }).start();
        countDownLatch.await();
        System.out.println(Thread.currentThread().getName()+"---------Over");
    }
}
