package com.xzy.concurrent2;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * tryLock(long timeout, TimeUnit unit) 在等待timeOut时间后，如果没有获取得到锁，返回false;否则返回true
 * tryLock();立即尝试获取锁，获取失败，不等待，直接返回false(lock()如果没有获取得到锁，则会一致)
 * Created by RuzzZZ on 19/07/2017.
 */
public class TryLockDemo implements Runnable {
    //true：公平锁，false:非公平锁；公平锁不会产生饥饿现象，但是性能不如非公平锁
    public static ReentrantLock lock = new ReentrantLock(false);

    @Override
    public void run() {
        try {
            if (lock.tryLock(5, TimeUnit.SECONDS)) {
                System.out.println(Thread.currentThread().getName() + "get lock successed");
                Thread.sleep(6000);
            } else {
                System.out.println(Thread.currentThread().getName() + "get lock failed");
            }
        } catch (Exception e) {
        } finally {
            if (lock.isHeldByCurrentThread()) {
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) {
        TryLockDemo t = new TryLockDemo();
        Thread t1 = new Thread(t);
        Thread t2 = new Thread(t);
        t1.start();
        t2.start();
    }
}
