package com.xzy.interview.thread;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 线程进入读锁的前提条件：
 * <p>没有其他线程的写锁，</p>
 * <p>没有写请求或者有写请求，但调用线程和持有锁的线程是同一个</p>
 * <p>
 * 线程进入写锁的前提条件：
 * <p>没有其他线程的读锁</p>
 * <p>没有其他线程的写锁</p>
 * <p>
 * 这个锁机制的特性了：
 * (a).重入方面其内部的WriteLock可以获取ReadLock，但是反过来ReadLock想要获得WriteLock则永远都不要想。
 * (b).WriteLock可以降级为ReadLock，顺序是：先获得WriteLock再获得ReadLock，然后释放WriteLock，这时候线程将保持Readlock的持有。反过来ReadLock想要升级为WriteLock则不可能，为什么？参看(a)，呵呵.
 * (c).ReadLock可以被多个线程持有并且在作用时排斥任何的WriteLock，而WriteLock则是完全的互斥。这一特性最为重要，因为对于高读取频率而相对较低写入的数据结构，使用此类锁同步机制则可以提高并发量。
 * (d).不管是ReadLock还是WriteLock都支持Interrupt，语义与ReentrantLock一致。
 * (e).WriteLock支持Condition并且与ReentrantLock语义一致，而ReadLock则不能使用Condition，否则抛出UnsupportedOperationException异常。
 */
public class ReentrantReadWriteLockDemo {

    public static ReentrantReadWriteLock lock = new ReentrantReadWriteLock(true);

    public static Map<Integer, String> map = new HashMap<>();

    static {
        map.put(1, "Hello1");
        map.put(2, "Hello2");
        map.put(3, "Hello3");
        map.put(4, "Hello4");
        map.put(5, "Hello5");
        map.put(6, "Hello6");
        map.put(7, "Hello7");
        map.put(8, "Hello8");
        map.put(9, "Hello9");
        map.put(10, "Hello10");
        map.put(11, "Hello11");
        map.put(12, "Hello12");
        map.put(13, "Hello13");
        map.put(14, "Hello14");
        map.put(15, "Hello15");
        map.put(16, "Hello16");
        map.put(17, "Hello17");
        map.put(18, "Hello18");
        map.put(19, "Hello19");
        map.put(20, "Hello20");
        map.put(21, "Hello21");
    }

    static int i, j = 0;

    public static void main(String[] args) {
        for (; j < 10; j++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    if (lock.writeLock().tryLock()) {
                        System.out.println(Thread.currentThread().getName() + " writeLocked success!");
                        try {
                            Thread.sleep(20);
                            map.put(j, "Hello-" + j);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        } finally {
                            lock.writeLock().unlock();
                            System.out.println(Thread.currentThread().getName() + " released writeLock!");
                        }
                    } else {
                        System.out.println(Thread.currentThread().getName() + " writeLocked failed!");
                    }
                }
            }).start();
        }

        for (; i < 2000; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    if (lock.readLock().tryLock()) {
                        System.out.println(Thread.currentThread().getName() + " readLocked success!");
                        try {
                            Thread.sleep(20);
                            map.get(i);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        } finally {
                            lock.readLock().unlock();
                            System.out.println(Thread.currentThread().getName() + " released readLock!");
                        }
                    } else {
                        System.out.println(Thread.currentThread().getName() + " readLocked failed!");
                    }
                }
            }).start();
        }

        while (lock.isWriteLocked()) {
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        for (Integer i : map.keySet()) {
            System.out.print(i + " " + map.get(i) + "\t");
        }
    }
}
