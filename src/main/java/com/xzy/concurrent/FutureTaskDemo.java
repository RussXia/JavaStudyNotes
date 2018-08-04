package com.xzy.concurrent;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * FutureTask实质：
 * 另起一个单独的线程，执行计算，使得线程有一个隐形的返回值。
 * 当调用get()方法时，会阻塞等待!!!
 * Created by RuzzZZ on 2017/2/9.
 */
public class FutureTaskDemo {

    public static void main(String[] args) {
        ExecutorService exec= Executors.newCachedThreadPool();

        //FutrueTask的构造参数是一个Callable接口
        FutureTask<String> task=new FutureTask<>(() -> {
            //模拟大量计算
            Thread.sleep(5000L);
            return Thread.currentThread().getName();//这里可以是一个异步操作
        });

        try {
            exec.execute(task);
            System.out.println("After execute task,befor call FutureTask.get()!");
            //取得异步计算的结果，如果没有返回，就会一直阻塞等待
            String result=task.get();
            System.out.printf("get:%s%n",result);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        } finally {
            exec.shutdown();
        }
    }
}
