package com.xzy.jvmlearning.tools;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * -Xms100m -Xmx100m
 *
 * @author RuzzZZ
 * @since 22/12/2017 10:41 AM
 */
public class JConsoleDemoThreadMonitor {

    public static void createBusyThread(String threadName) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) ;
            }
        }, threadName).start();
    }

    public static void createWaitLockThread(String threadName, final Object lock) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (lock) {
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, threadName).start();
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        br.readLine();
        createBusyThread("busyThread");
        br.readLine();
        createWaitLockThread("lockThread", new Object());
        br.readLine();
    }
}