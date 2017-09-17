package com.xzy.concurrent2;

import java.util.concurrent.*;

/**
 * Created by RuzzZZ on 20/07/2017.
 */
public class SemaphoreDemo {

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = new ThreadPoolExecutor(10, 50, 6, TimeUnit.SECONDS, new ArrayBlockingQueue<>(100));
        final Semaphore semaphore = new Semaphore(5);
        for (int i = 0; i < 20; i++) {
            executorService.submit(() -> {
                try {
                    semaphore.acquire();
                    System.out.println(Thread.currentThread().getName() + "Get permit");
                    Thread.sleep(100);
                    semaphore.release();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    System.out.println("Exit " + Thread.currentThread().getName() + ",Permit is " + semaphore.availablePermits());
                }

            });
        }
        executorService.shutdown();
    }


}
