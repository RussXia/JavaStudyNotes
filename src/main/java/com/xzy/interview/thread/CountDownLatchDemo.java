package com.xzy.interview.thread;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;

/**
 * Created by RuzzZZ on 18/09/2017.
 */
@Slf4j
public class CountDownLatchDemo {

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch taskCount = new CountDownLatch(1);
        CountDownLatch mainCount = new CountDownLatch(10);
        for (int i = 0; i < 10; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        taskCount.await();
                    } catch (InterruptedException e) {
                        log.error("something error!", e);
                    }
                    log.info("start thread {}", Thread.currentThread().getName());
                    mainCount.countDown();
                }
            }).start();
        }
        taskCount.countDown();
        mainCount.await();
        log.info("main thread");
    }
}
