package com.xzy.concurrent.locksupport;

import java.util.concurrent.locks.LockSupport;

public class LockDemo {

    public static void main(String[] args) throws InterruptedException {
        parkDemo2();
    }

    public static void parkDemo2() throws InterruptedException {
        Thread threadA = new Thread(() -> {
            System.out.println("Thread A start park");
            LockSupport.park();
            System.out.println("Thread A unPark");
        }, "ThreadA");
        Thread threadB = new Thread(() -> {
            LockSupport.unpark(threadA);
            System.out.println("Give ThreadA a Permit");
        }, "ThreadB");
        threadB.start();
        Thread.sleep(100);
        threadA.start();

    }

    public static void parkDemo1() {
        Thread threadA = new Thread(() -> {
            System.out.println("Thread A start park");
            LockSupport.park();
            System.out.println("Thread A unPark");
        }, "ThreadA");

        threadA.start();

        Thread threadB = new Thread(() -> {
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            LockSupport.unpark(threadA);
        }, "ThreadB");
        threadB.start();
    }
}
