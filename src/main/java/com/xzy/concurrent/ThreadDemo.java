package com.xzy.concurrent;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author xiazhengyue
 * @since 2019-07-18
 */
@Slf4j
public class ThreadDemo {

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(ThreadDemo::action, "t1");
        Thread t2 = new Thread(ThreadDemo::action2, "t2");
        Thread t3 = new Thread(ThreadDemo::action, "t3");
        t1.start();
        t2.start();
        t3.start();
        t1.join();

        t2.setUncaughtExceptionHandler((thread, throwable) -> {
            log.error(thread.getName() + "-" + throwable);
        });

        ExecutorService executorService = Executors.newFixedThreadPool(10);
        t1.stop();
    }

    private static void action() {
        System.out.println(Thread.currentThread().getName() + " is doing action!");
    }

    private static void action2() {
        try {
            Thread.sleep(1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + " is doing action!");
        throw new RuntimeException("。。。。");
    }

}
