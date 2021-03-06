package com.xzy.concurrent;

/**
 * 线程方法之suspend(挂起)resume(恢复)，stop(没有演示)
 * 目前已弃用(极不安全，建议使用标志位来标识)
 * Created by RuzzZZ on 2017/2/8.
 */
public class DeprecatedSuspendResume implements Runnable {

    //volatile关键字，表示该变量可能在被一个线程使用的同时，被另一个线程修改
    private volatile int firstVal;
    private volatile int secondVal;

    //判断二者是否相等
    public boolean areValuesEqual() {
        return (firstVal == secondVal);
    }

    public void run() {
        try {
            firstVal = 0;
            secondVal = 0;
            workMethod();
        } catch (InterruptedException x) {
            System.out.println("interrupted while in workMethod()");
        }
    }

    private void workMethod() throws InterruptedException {
        int val = 1;
        while (true) {
            stepOne(val);
            stepTwo(val);
            val++;
            Thread.sleep(200);  //再次循环钱休眠200毫秒
        }
    }

    //赋值后，休眠300毫秒，从而使线程有机会在stepOne操作和stepTwo操作之间被挂起
    private void stepOne(int newVal) throws InterruptedException {
        firstVal = newVal;
        Thread.sleep(300);  //模拟长时间运行的情况
    }

    private void stepTwo(int newVal) {
        secondVal = newVal;
    }

    public static void main(String[] args) {
        DeprecatedSuspendResume dsr = new DeprecatedSuspendResume();
        Thread t = new Thread(dsr);
        t.start();

        //休眠1秒，让其他线程有机会获得执行
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        for (int i = 0; i < 10; i++) {
            //挂起线程
            t.suspend();
            System.out.println("dsr.areValuesEqual()=" + dsr.areValuesEqual());
            //恢复线程
            t.resume();
            try {
                //线程随机休眠0~2秒
                Thread.sleep((long) (Math.random() * 2000.0));
            } catch (InterruptedException ex) {
                //略
                ex.printStackTrace();
            }
        }
        System.exit(0); //中断应用程序
    }
}