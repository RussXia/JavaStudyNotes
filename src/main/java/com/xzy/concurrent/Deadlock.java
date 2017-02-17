package com.xzy.concurrent;

import static java.lang.System.out;

/**
 * 死锁demo
 * Created by RuzzZZ on 2017/2/8.
 */
public class Deadlock implements Runnable {

    private Deadlock other;

    public void setOther(Deadlock other) {
        this.other = other;
    }

    private synchronized void checkOtherLock(Deadlock other) throws InterruptedException {
        out.println("CheckOtherLock start.Current thread name is :" + Thread.currentThread().getName());
        //线程先休息一下，保证两个线程都进入同步方法中
        Thread.sleep(100L);
        //doSomething()是同步方法，所以它会尝试去获取other对象的锁
        //当两个线程互相争夺锁时，理论上是会互相阻塞的，所以都无法执行doSomething()方法
        other.doSomething();
        out.println("CheckOtherLock over.Current thread name is :" + Thread.currentThread().getName());
    }

    private synchronized void doSomething() {
        out.println("THis is doSomething() method!");
    }

    @Override
    public void run() {
        try {
            this.checkOtherLock(other);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Deadlock deadlock1 = new Deadlock();
        Deadlock deadlock2 = new Deadlock();
        deadlock1.setOther(deadlock2);
        deadlock2.setOther(deadlock1);
        out.println("start");
        new Thread(deadlock1).start();
        new Thread(deadlock2).start();
    }
}
