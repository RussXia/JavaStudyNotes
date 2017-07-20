package com.xzy.concurrent2;

import java.util.Objects;

public class Hosee {
    private static Integer count = 0;
    private static final String LOCK = "LOCK";

    private class Producer implements Runnable {
        @Override
        public void run() {
            while (true) {
                try {
                    Thread.sleep(30);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                synchronized (LOCK) {
                    Integer FULL = 10;
                    while (Objects.equals(count, FULL)) {
                        try {
                            LOCK.wait();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    count++;
                    System.out.println(Thread.currentThread().getName() + "生产者生产，目前总共有" + count);
                    LOCK.notifyAll();
                }
            }
        }
    }


    private class Consumer implements Runnable {
        @Override
        public void run() {
            while (true) {
                try {
                    Thread.sleep(30);
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
                synchronized (LOCK) {
                    while (count == 0) {
                        try {
                            LOCK.wait();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    count--;
                    System.out.println(Thread.currentThread().getName() + "消费者消费，目前总共有" + count);
                    LOCK.notifyAll();
                }
            }
        }
    }

    public static void main(String[] args) throws Exception {
        Hosee hosee = new Hosee();
        new Thread(hosee.new Producer()).start();
//        new Thread(hosee.new Consumer()).start();
        new Thread(hosee.new Producer()).start();
        new Thread(hosee.new Consumer()).start();
        new Thread(hosee.new Producer()).start();
        new Thread(hosee.new Consumer()).start();
        new Thread(hosee.new Producer()).start();
        new Thread(hosee.new Consumer()).start();
    }
}