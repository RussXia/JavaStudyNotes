package com.xzy.guava;

import com.google.common.cache.*;

import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * Created by RuzzZZ on 2017/3/3.
 */
public class CacheDemo {

    private static Random random = new Random(100);

    public static void main(String[] args) throws InterruptedException {
        RemovalListener<? super Object, ? super Object> listener = new RemovalListener<Object, Object>() {
            @Override
            public void onRemoval(RemovalNotification<Object, Object> notification) {
                /**
                 * EXPLICIT、REPLACED、COLLECTED、EXPIRED、SIZE过期的几种原因,详情看源码{@link RemovalCause}
                 */
                System.out.println(String.format("The key %s, value %s in the cache is invalid! The cause is %s",
                        notification.getKey(), notification.getValue(), notification.getCause()));
            }
        };
        LoadingCache<Object, Object> cache = CacheBuilder.newBuilder()
                .maximumSize(5)
                .expireAfterWrite(5, TimeUnit.SECONDS)
                .removalListener(listener).build(
                        new CacheLoader<Object, Object>() {
                            @Override
                            public Object load(Object key) throws Exception {
                                String strProValue = "hello " + key + "!";
                                return strProValue;
                            }
                        });
        long start = System.currentTimeMillis();
        int flag = 1;
//        System.out.println(TimeUnit.SECONDS.toMillis(40));
        while (true) {
            cache.put(flag, flag);
            flag++;
            long current = System.currentTimeMillis();
            if (current - start > TimeUnit.SECONDS.toMillis(40)) {
                System.out.println("It's has been 40 seconds over!");
                break;
            }
            //每隔1s插入一个元素
            Thread.sleep(TimeUnit.SECONDS.toMillis(1));
            try {
                cache.get(random.nextInt(30));
            } catch (ExecutionException e) {
            }
        }
    }
}
