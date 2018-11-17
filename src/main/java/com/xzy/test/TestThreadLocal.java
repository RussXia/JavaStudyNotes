package com.xzy.test;

public class TestThreadLocal {

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new Task());
        thread.start();
        Thread.sleep(1000L);
    }

    static class Task implements Runnable {

        private ThreadLocal<String> threadLocal = new ThreadLocal<>();

        @Override
        public void run() {
            threadLocal.set("test123");
            System.out.println(Thread.currentThread().getName() + "-" + threadLocal.get());
            new Thread(() -> System.out.println(Thread.currentThread().getName() + "-" + threadLocal.get())).start();
        }
    }
}
