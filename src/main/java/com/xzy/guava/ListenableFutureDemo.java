package com.xzy.guava;

import com.google.common.util.concurrent.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;

/**
 * Created by RuzzZZ on 2017/3/21.
 */
public class ListenableFutureDemo {


    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ListeningExecutorService executorService = MoreExecutors.listeningDecorator(Executors.newFixedThreadPool(10));
        ListenableFuture<String> listenableFuture1 = executorService.submit(new Callable<String>() {
            @Override
            public String call() throws Exception {
//                throw new RuntimeException("强制失败!");
                System.out.println("sleep over");
                return Thread.currentThread().getName();
            }
        });
        System.out.println("distribute task1 over");
        //添加task1的结束监听(onSuccess.onFailure)
        Futures.addCallback(listenableFuture1, new FutureCallback() {

            @Override
            public void onSuccess(Object result) {
                System.out.println("Successs");
            }

            @Override
            public void onFailure(Throwable t) {
                System.out.println("Failure");
            }
        });
        ListenableFuture<Long> listenableFuture2 = executorService.submit(new Callable<Long>() {
            @Override
            public Long call() throws Exception {
                Thread.sleep(20000);
                return Thread.currentThread().getId();
            }
        });
        System.out.println("distribute task2 over");
        List<ListenableFuture<?>> tasks = new ArrayList<>();
        tasks.add(listenableFuture1);
        tasks.add(listenableFuture2);
        //ListenableFuture extends Future
        //所以在调用get方法的时候，会阻塞等待
        ListenableFuture<List<Object>> result = Futures.allAsList(tasks);
        System.out.println("get Result");
        for (Object obj : result.get()) {
            if (obj != null) {
                System.out.println(obj);
            }
        }

        executorService.shutdown();
    }
}
