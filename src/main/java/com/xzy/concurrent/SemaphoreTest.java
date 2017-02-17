package com.xzy.concurrent;

import java.util.concurrent.*;

/**
 * 计数器，作用类似于阀门。
 * 最多只能达到permits个允许项工作，超过的则阻塞等待
 * 支持公平锁
 * Created by RuzzZZ on 2017/2/8.
 */
public class SemaphoreTest {

    public static void main(String[] args) {
        ExecutorService exec = new ThreadPoolExecutor(10, 50, 6, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(1000));
        //只允许5个线程同时访问
        final Semaphore semp = new Semaphore(5);
        for (int index = 0; index < 10; index++) {
            final int num = index;
            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    try {
                        semp.acquire();
                        System.out.println("线程" + Thread.currentThread().getName() + "获得许可：" + num);
                        //模拟耗时的任务
                        Thread.sleep(1000);
                        //释放许可
                        semp.release();
                        System.out.println("线程" + Thread.currentThread().getName() + "释放许可：" + num);
                        System.out.println("当前允许进入的任务个数：" + semp.availablePermits());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            };
            exec.execute(runnable);
        }
        exec.shutdown();
    }
}
