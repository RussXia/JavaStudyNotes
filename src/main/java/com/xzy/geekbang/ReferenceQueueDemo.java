package com.xzy.geekbang;

import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;

/**
 * jdk version:1.8.0_161
 * JVM options:
 * -Xmx1m -XX:+PrintGCDetails -XX:+PrintGCTimeStamps -XX:+PrintReferenceGC
 */
public class ReferenceQueueDemo {

    public static void main(String[] args) throws InterruptedException {
        Object counter = new TestObject();
        ReferenceQueue refQueue = new ReferenceQueue();
        WeakReference<Object> softReference = new WeakReference<>(counter, refQueue);
        counter = null;
        System.gc();
        byte[] bytes = new byte[500];
        bytes = null;
        System.gc();
        Thread.sleep(1000);
        bytes = new byte[500];
        System.gc();
        bytes = new byte[500];
        System.gc();
        bytes = new byte[500];
        System.gc();
        try {
            Reference<Object> ref = refQueue.remove(1000L);
            Thread.sleep(1000L);
            if (ref != null) {
                System.out.println("dddd");
            }
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }
}
