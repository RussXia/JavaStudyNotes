package com.xzy.interview.thread;


import java.util.concurrent.Executors;

public class SynchronizedDemo {

    public synchronized static void method1() throws InterruptedException {
        synchronized (SynchronizedDemo.class) {
            System.out.println("method1 begin at:" + System.currentTimeMillis());
            Thread.sleep(3000);
            System.out.println("method1 end at:" + System.currentTimeMillis());
        }
    }

    public void method2() throws InterruptedException {
        synchronized (SynchronizedDemo.class) {
            for (int i = 0; i < 8; i++) {
                System.out.println("method2 running");
                Thread.sleep(200);
            }
        }
    }

    private static SynchronizedDemo instance = new SynchronizedDemo();

    public static void main(String[] args) {
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    SynchronizedDemo.method1();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    instance.method2();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        thread1.start();
        thread2.start();
    }
}
