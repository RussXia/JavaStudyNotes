package com.xzy.concurrent;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import static java.lang.System.out;

/**
 * 使用LinkedBlockingQueue实现一个生产者和消费者模型
 * Created by RuzzZZ on 2017/2/10.
 */
public class LinkedBlockingQueueDemo {

    public static void main(String[] args) {
        final BlockingQueue<Integer> queue = new LinkedBlockingQueue<Integer>(3);
        final Random random = new Random();
        //start procuder thread
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while(true){
                        int i = random.nextInt(100);
                        //put(E e):waiting if necessary for space to become available.
                        queue.put(i);   //Links node at end of queue.
                        out.println("Producer : i = " + i + " ,Queue.size : " + queue.size());
                        //to avoid too much output
                        Thread.sleep(500L);
                        if (3 == queue.size()) {
                            out.println("The queue is full!");
                        }
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        //start counsumer thread
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while(true){
                        //waiting if necessary until an element becomes available.
                        //好多阻塞非阻塞的方法。。根据名字根本去分不出来。。感觉只能用之前追下源码，看看是阻塞的还是非阻塞的
                        Integer i = queue.take();   //Retrieves and removes the head of this queue.Typical fifo
                        out.println("Consumer : i = " + i+ " ,Queue.size : " + queue.size());
                        Thread.sleep(600L);
                        if (0 == queue.size()) {
                            out.println("The queue is empty!");
                        }
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
