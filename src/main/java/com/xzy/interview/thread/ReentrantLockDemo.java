package com.xzy.interview.thread;

import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockDemo {

    public static ReentrantLock lock = new ReentrantLock(true);

    public static void main(String[] args) {
        System.out.println();
        for (int i = 0; i < 200; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    lock.lock();
                    try {
                        System.out.println(Thread.currentThread().getName() + " locked success!sleep 100 ms");
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        lock.unlock();
                        System.out.println(Thread.currentThread().getName() + " unlocked");
                    }
                }
            }).start();
        }
    }
}
