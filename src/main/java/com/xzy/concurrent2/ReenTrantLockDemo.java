package com.xzy.concurrent2;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by RuzzZZ on 19/07/2017.
 */
public class ReenTrantLockDemo implements Runnable {
    public static ReentrantLock lock = new ReentrantLock();
    public static int i = 0;


    @Override
    public void run() {
        for (int j = 0; j < 10000000; j++) {
            /**
             * ReentrantLock是可重入锁，重复得到的是同一把锁。
             * 它有一个获取锁的计数器，所以如果lock了两次，需要unlock两次才可以释放锁。
             * 如果只lock了一次，释放了两次，会抛出{@link java.lang.IllegalMonitorStateException}异常
             * 如果lock了两次，只unlock了两次，则最先拿到锁的线程永远不会释放锁
             */
            lock.lock();
            lock.lock();
            try {
                i++;
            } finally {
                lock.unlock();
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(new ReenTrantLockDemo());
        Thread t2 = new Thread(new ReenTrantLockDemo());
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(i);
    }
}
