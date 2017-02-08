package com.xzy.learning.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 一般来说，CachedTheadPool 在程序执行过程中通常会创建与所需数量相同的线程，
 * 然后在它回收旧线程时停止创建新线程，因此它是合理的 Executor 的首选，
 * 只有当这种方式会引发问题时（比如需要大量长时间面向连接的线程时），才需要考虑用 FixedThreadPool。
 * (该段话摘自《Thinking in Java》第四版)
 * Created by RuzzZZ on 2017/2/7.
 */
public class TestThreadPool {
    /**
     * newCachedThreadPool():能reuse就reuse，没有就new一个新的线程假如到线程池中
     * newFixedThreadPool():与newCachedThreadPool类似，能reuse就用，但不能随时建新的线程。
     * newScheduledThreadPool():调度型线程池，这个池子里的线程可以按 schedule 依次 delay 执行，或周期执行
     * newSingleThreadExecutor():单例线程，任意时间池中只能有一个线程
     * cache池、fixed池、single池底层使用的是同一个threadFactory
     */
    public static void main(String[] args) {
//        ExecutorService executorService = Executors.newCachedThreadPool();
        ExecutorService executorService = Executors.newFixedThreadPool(5);
//      ExecutorService executorService = Executors.newSingleThreadExecutor();
        for (int i = 0; i < 20; i++) {
            executorService.execute(new TestRunnable());
//            System.out.println("************* a" + i + " *************");
        }
        executorService.shutdown();
    }

    private static class TestRunnable implements Runnable {
        public void run() {
            System.out.println(Thread.currentThread().getName() + "线程被调用了。");
        }
    }
}