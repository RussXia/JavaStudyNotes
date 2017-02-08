package com.xzy.learning.concurrent;

import java.util.concurrent.locks.ReentrantLock;

/**
 * 响应中断锁demo
 * Created by RuzzZZ on 2017/2/7.
 */
public class Buffer {

    private ReentrantLock lock = new ReentrantLock();

    public void write() {
        lock.lock();
        try {
            long startTime = System.currentTimeMillis();
            System.out.println("开始往这个buff写入数据…");
            for (; ; )// 模拟要处理很长时间
            {
                if (System.currentTimeMillis() - startTime > 50000) {
                    break;
                }
            }
            System.out.println("终于写完了");
        } finally {
            lock.unlock();
        }
    }

    public void read() throws InterruptedException {
        lock.lockInterruptibly();// 注意这里，可以响应中断
        try {
            System.out.println("从这个buff读数据");
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        Buffer buff = new Buffer();

        final Writer writer = new Writer(buff);
        final Reader reader = new Reader(buff);

        writer.start();
        reader.start();

        new Thread(new Runnable() {

            @Override
            public void run() {
                long start = System.currentTimeMillis();
                for (; ; ) {
                    //等5秒钟去中断读
                    if (System.currentTimeMillis() - start > 5000) {
                        System.out.println("不等了，尝试中断");
                        reader.interrupt();  //尝试中断读线程
                        break;
                    }
                }
            }
        }).start();
        // 我们期待“读”这个线程能退出等待锁，可是事与愿违，一旦读这个线程发现自己得不到锁，
        // 就一直开始等待了，就算它等死，也得不到锁，因为写线程要21亿秒才能完成 T_T ，即使我们中断它，
        // 它都不来响应下，看来真的要等死了。这个时候，ReentrantLock给了一种机制让我们来响应中断，
        // 让“读”能伸能屈，勇敢放弃对这个锁的等待。我们来改写Buffer这个类，就叫BufferInterruptibly吧，可中断缓存。
    }
}

class Writer extends Thread {

    private Buffer buff;

    public Writer(Buffer buff) {
        this.buff = buff;
    }

    @Override
    public void run() {
        buff.write();
    }
}

class Reader extends Thread {

    private Buffer buff;

    public Reader(Buffer buff) {
        this.buff = buff;
    }

    @Override
    public void run() {

        try {
            buff.read();//这里估计会一直阻塞
        } catch (InterruptedException e) {
            System.out.println("我不读了");
            e.printStackTrace();
        }
        System.out.println("读结束");

    }
}
