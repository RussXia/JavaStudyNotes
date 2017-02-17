package com.xzy.concurrent;

import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import static java.lang.System.out;

/**
 * 可调度式线程池，
 * Created by RuzzZZ on 2017/2/10.
 */
public class ScheduledThreadPoolDemo {


    public static void main(String[] args){
        //ScheduledThreadPoolExecutor exec=new ScheduledThreadPoolExecutor(10);
        //推荐使用Executors类创建线程池
        ScheduledExecutorService scheduledExec = Executors.newScheduledThreadPool(10);
        //Runnable command,long initialDelay,long period,TimeUnit unit
        //5秒一次打印
        scheduledExec.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                out.println(new Date()+"\t:"+":--1Hello World\t"+Thread.currentThread().getName());
            }
        },1000l,5000l, TimeUnit.MILLISECONDS);
//        out.println("This is main thread!");
        //2秒一次打印
        scheduledExec.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                out.println(new Date()+"\t:"+":--2Hello World\t"+Thread.currentThread().getName());
            }
        },1000l,2000l, TimeUnit.MILLISECONDS);
        /*
        Demo output
        Fri Feb 10 16:02:48 CST 2017	::--2Hello World	pool-1-thread-2
        Fri Feb 10 16:02:48 CST 2017	::--1Hello World	pool-1-thread-1
        Fri Feb 10 16:02:50 CST 2017	::--2Hello World	pool-1-thread-2
        Fri Feb 10 16:02:52 CST 2017	::--2Hello World	pool-1-thread-3
        Fri Feb 10 16:02:53 CST 2017	::--1Hello World	pool-1-thread-1
        Fri Feb 10 16:02:54 CST 2017	::--2Hello World	pool-1-thread-4
        Fri Feb 10 16:02:56 CST 2017	::--2Hello World	pool-1-thread-5
        */
        //从上面的数据我们可以看出，两个scheduled是互不影响的
        //ScheduledThreadPool会定期的从线程池中获取一个线程，然后执行
    }

}
