package com.xzy.concurrent;

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

    private static class Printer implements Runnable {
        private final CountDownLatch count;

        private Printer(CountDownLatch count) {
            this.count = count;
        }

        public void run() {
            System.out.println(Thread.currentThread().getName() + " ready");
            count.countDown();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch count = new CountDownLatch(10);
        for (int i = 0; i < 10; i++) {
            //开启10个线程
            new Thread(new Printer(count), "work_thread-" + i).start();
        }
        new Thread(() -> {
            try {
                count.await();
                System.out.println(Thread.currentThread().getName() + ":-- all ready");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "personal-thread").start();
        //阻塞主线程，等待结束信号变为0时进行工作
        count.await();
        System.out.println(Thread.currentThread().getName() + ":所有线程准备开始!");
        Thread.sleep(2000);
    }

}
