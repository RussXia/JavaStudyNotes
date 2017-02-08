package com.xzy.learning.concurrent;

/**
 * Created by RuzzZZ on 2017/2/8.
 */
public class VolatileDemo implements Runnable{

    private volatile int value1;
    private volatile int value2;

    public VolatileDemo(int value1, int value2) {
        this.value1 = value1;
        this.value2 = value2;
    }

    @Override
    public void run() {
        synchronized (VolatileDemo.class){
            value1 ++;

        }
    }
}
