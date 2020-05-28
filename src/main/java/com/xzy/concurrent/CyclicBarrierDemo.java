package com.xzy.concurrent;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

//静态导包

/**
 * CyclicBarrier相比于CountDownLatch有一个Barrier(界限)的存在
 * CyclicBarrier初始化时规定一个数目，然后计算调用了CyclicBarrier.await()进入等待的线程数。
 * 当线程数达到了这个数目时，所有进入等待状态的线程被唤醒并继续。
 * <p>
 * Created by RuzzZZ on 2017/2/7.
 *
 * @auther RuzzZZ
 */

/**
 * CyclicBarrier等待所有的线程一起完成后再执行某个动作。这个功能CountDownLatch也同样可以实现。
 * 但是CountDownLatch更多时候是在等待某个事件的发生。
 * 在CyclicBarrier中，所有的线程调用await方法，等待其他线程都执行完。
 *
 * @author RuzzZZ
 */
public class CyclicBarrierDemo {

    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(2);
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        //任务A
        executorService.submit(() -> {
            try {
                System.out.println("Task A step 1");
                cyclicBarrier.await();
                System.out.println("Task A step 2");
                cyclicBarrier.await();
                System.out.println("Task A step 3");
            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }
        });
        //任务B
        executorService.submit(() -> {
            try {
                System.out.println("Task B step 1");
                cyclicBarrier.await();
                System.out.println("Task B step 2");
                cyclicBarrier.await();
                System.out.println("Task B step 3");
            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }
        });
        executorService.shutdown();
    }
}
