package com.xzy.concurrent;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by RuzzZZ on 2017/2/17.
 */
public class ThreadLocalDemo implements Runnable {

    private static ThreadLocal<Integer> threadLocal = new ThreadLocal<>();

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            //为threadLocal对象设置值，并输出该值
            threadLocal.set(i);
            System.out.println(Thread.currentThread().getName() + "\t\t" + threadLocal.get());
        }
    }

    public static void main(String[] args) {
        ExecutorService exec = null;
        try {
            ThreadLocalDemo demo = new ThreadLocalDemo();
            exec = new ThreadPoolExecutor(20, 50, 6000,
                    TimeUnit.MILLISECONDS, new ArrayBlockingQueue<Runnable>(1000));
            //从线程池中启动是个线程
            for (int i = 0; i < 10; i++) {
                exec.execute(demo);
            }
        } finally {
            exec.shutdown();
        }

        //结论：ThreadLocal对象:每个线程操作的都是属于自己线程的变量副本，而不会影响其他线程的变量副本。
        //ThreadLocal和Thread的同步机制的比较:ThreadLocal-->空间换时间,Thread-->时间换空间
    }
}
