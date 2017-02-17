package com.xzy.concurrent;

import java.util.Random;
import java.util.concurrent.DelayQueue;

import static java.lang.System.out;

/**
 * Created by RuzzZZ on 2017/2/10.
 */
public class DelayQueueDemo {

    public static void main(String[] args) {
        DelayQueue<DelayedItem> queue = new DelayQueue<>();
        Random random = new Random(System.currentTimeMillis());
        //Producer
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(random.nextInt(1000)+1);
                        out.println("add element into queue!");
                        queue.offer(new DelayedItem(random.nextInt(10),random.nextInt(1000)+1));
                        if(queue.size()>30){
                            out.println("producer quit");
                            break;
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
        //Consumer
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
//                        out.println("ready to get element from queue!");
                        DelayedItem delayedItem = null;
                        delayedItem = queue.take();
                        out.println("consumer : i=" + delayedItem.getI());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }
}
