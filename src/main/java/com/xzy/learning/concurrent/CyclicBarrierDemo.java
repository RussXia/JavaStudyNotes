package com.xzy.learning.concurrent;


import java.util.concurrent.CyclicBarrier;
//静态导包
import static java.lang.System.out;

/**
 * CyclicBarrier相比于CountDownLatch有一个Barrier(界限)的存在
 * CyclicBarrier初始化时规定一个数目，然后计算调用了CyclicBarrier.await()进入等待的线程数。
 * 当线程数达到了这个数目时，所有进入等待状态的线程被唤醒并继续。
 * <p>
 * Created by RuzzZZ on 2017/2/7.
 * @auther RuzzZZ
 */

/**
 * CyclicBarrier等待所有的线程一起完成后再执行某个动作。这个功能CountDownLatch也同样可以实现。
 * 但是CountDownLatch更多时候是在等待某个事件的发生。
 * 在CyclicBarrier中，所有的线程调用await方法，等待其他线程都执行完。
 * @author RuzzZZ
 */
public class CyclicBarrierDemo {

    private static final int THREAD_NUM = 10;

    private static class Worker implements Runnable {

        private CyclicBarrier cyclicBarrier;

        private Worker(CyclicBarrier cyclicBarrier) {
            this.cyclicBarrier = cyclicBarrier;
        }

        public void run() {
            out.println("等待所有线程到齐，当前线程为：" + Thread.currentThread().getName());
            try {
                cyclicBarrier.await();
            } catch (Exception e) {
                e.printStackTrace();
            }
            out.println("线程开始执行，当前线程为：" + Thread.currentThread().getName());
        }
    }

    public static void main(String[] args) {
//        CyclicBarrier cyclicBarrier = new CyclicBarrier(THREAD_NUM);
        //当到达界限后，开始执行runnable接口的run()方法
        CyclicBarrier cyclicBarrier = new CyclicBarrier(THREAD_NUM, new Runnable() {
            public void run() {
                System.out.println("所有线程已经到齐，准备开始执行");
            }
        });
        for(int i=0;i<THREAD_NUM;i++){
            new Thread(new Worker(cyclicBarrier)).start();
        }
    }
}
