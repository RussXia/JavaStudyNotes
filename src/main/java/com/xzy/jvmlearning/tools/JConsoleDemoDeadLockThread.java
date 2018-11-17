package com.xzy.jvmlearning.tools;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * -XX:+UseSerialGC
 * @author RuzzZZ
 * @since 22/12/2017 11:39 AM
 */
public class JConsoleDemoDeadLockThread {

    static class DeadLockThread implements Runnable {

        private final Object lockA;

        private final Object lockB;

        public DeadLockThread(Object lockA, Object lockB) {
            this.lockA = lockA;
            this.lockB = lockB;
        }

        @Override
        public void run() {
            synchronized (lockA) {
                try {
                    Thread.sleep(100L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (lockB) {
                    System.out.println(Thread.currentThread().getName() + "Lock Over");
                }
            }
        }
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        br.readLine();
        Object lockA = new Object();
        Object lockB = new Object();
        DeadLockThread deadLockThread1 = new DeadLockThread(lockA, lockB);
        DeadLockThread deadLockThread2 = new DeadLockThread(lockB, lockA);
        new Thread(deadLockThread1, "deadLockThread1").start();
        new Thread(deadLockThread2, "deadLockThread2").start();
    }
}