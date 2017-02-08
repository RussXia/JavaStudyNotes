package com.xzy.learning.concurrent;

import java.util.concurrent.CountDownLatch;

/**
 * 假设我们要打印1-100，最后再输出“Ok“。1-100的打印顺序不要求统一，只需保证“Ok“是在最后出现即可。
 * CountDownLatch源代码上有其使用demo
 * 解决思路：分10个线程工作，每个输出10个数字
 * 所有线程等待相同的开始信号，同时开始争抢执行权利。
 * 当前线程结束时，将结束信号-1
 * 主线程等待到结束信号后，输出ok
 *
 * @author RuzzZZ
 * @see CountDownLatch
 */
public class CountDownLatchDemo {

    private static final int COUNT = 10;

    private static class Printer implements Runnable {
        private final CountDownLatch startLatch;
        private final CountDownLatch doneLatch;
        private int beginIndex;

        private Printer(int beginIndex, CountDownLatch startLatch, CountDownLatch doneLatch) {
            this.startLatch = startLatch;
            this.doneLatch = doneLatch;
            this.beginIndex = beginIndex;
        }

        public void run() {
            try {
                //所有线程等待信号，同时开始任务
                startLatch.await();
                beginIndex = beginIndex * 10 + 1;
                for (int i = 0; i < 10; i++) {
                    System.out.println(beginIndex + i);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                //线程结束后，将doneLatch进行-1
                doneLatch.countDown();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch startLatch = new CountDownLatch(1);
        CountDownLatch doneLatch = new CountDownLatch(COUNT);
        for (int i = 0; i < 10; i++) {
            //开启10个线程
            new Thread(new Printer(i, startLatch, doneLatch)).start();
        }
        System.out.println("所有线程准备开始!");
        startLatch.countDown();
        //阻塞主线程，等待结束信号变为0时进行工作
        doneLatch.await();
        System.out.println("所有线程准备开始!");
    }

}
