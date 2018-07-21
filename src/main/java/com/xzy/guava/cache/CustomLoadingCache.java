package com.xzy.guava.cache;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Slf4j
public class CustomLoadingCache extends LoadingCacheTemplate<Integer, String> {

    @Override
    protected String loadKey(Integer integer) {
        try {
            Thread.sleep(10000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("loading key : " + integer);
        return "Test1111" + integer;
    }


    public static void main(String[] args) throws InterruptedException {
        CustomLoadingCache cache = new CustomLoadingCache();
        //测试获取值
//        System.out.println(String.format("get value from cache.Key - %s \t Value - %s", 100, cache.get(100)));
//        System.out.println(String.format("get value from cache.Key - %s \t Value - %s", 101, cache.get(101)));
//        System.out.println(String.format("get value from cache.Key - %s \t Value - %s", 100, cache.get(100)));
//        System.out.println(String.format("get value from cache.Key - %s \t Value - %s", 102, cache.get(102)));
//        System.out.println(String.format("get value from cache.Key - %s \t Value - %s", 103, cache.get(103)));
//        System.out.println(String.format("get value from cache.Key - %s \t Value - %s", 100, cache.get(100)));
//        System.out.println(String.format("get value from cache.Key - %s \t Value - %s", 101, cache.get(101)));

        //测试过期时间
//        cache.get(100);
//        cache.get(101);
//        cache.get(102);
//
//        Thread.sleep(1000);
//        cache.get(100);
//        Thread.sleep(1000);
//        cache.get(100);
//        cache.get(101);
//        Thread.sleep(1000);
//        cache.get(102);


        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(10, 50, 6, TimeUnit.SECONDS, new ArrayBlockingQueue<>(100));
        Runnable task = () -> {
            System.out.println(Thread.currentThread().getName() + "start task");
            System.out.println(Thread.currentThread().getName() + "- start task -" + cache.get(100));
            try {
                Thread.sleep(10000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "after sleep");
            System.out.println(Thread.currentThread().getName() + "- after sleep -" + cache.get(100));
        };
        for (int i = 0; i < 50; i++) {
            threadPoolExecutor.submit(task);
        }
        while (!threadPoolExecutor.isShutdown()) {
            if (threadPoolExecutor.getActiveCount() == 0) {
                threadPoolExecutor.shutdown();
            }
            Thread.yield();
        }
        System.out.println("over");


    }
}
