package com.xzy.interview.thread;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

@Slf4j
public class CallableApp2 {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ExecutorService threadPool = Executors.newSingleThreadExecutor();
        Callable<String> callable = () -> "Hello World";
        Future<String> future = threadPool.submit(callable);
        Thread.sleep(100);
        log.info("The result is {}",future.get());
        threadPool.shutdown();
    }
}
