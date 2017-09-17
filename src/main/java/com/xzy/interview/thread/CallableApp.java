package com.xzy.interview.thread;

import lombok.extern.slf4j.Slf4j;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

@Slf4j
public class CallableApp {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        Callable<Integer> callable = new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                return new Random().nextInt(100);
            }
        };
        FutureTask<Integer> task = new FutureTask<>(callable);
        //启动线程
        new Thread(task).start();
        //主线程暂停一会，保证子线程执行完成
        Thread.sleep(100L);
        log.info("The result is {}",task.get());
    }
}
