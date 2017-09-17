package com.xzy.concurrent2;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by RuzzZZ on 20/07/2017.
 */
public class ConditionDemo implements Runnable {
    public static ReentrantLock lock = new ReentrantLock();
    public static Condition condition = lock.newCondition();


    @Override
    public void run() {

        try {
            lock.lock();
            System.out.println("Thread is waiting on");
            condition.await();
            System.out.println("Thread is going on");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ConditionDemo conditionDemo = new ConditionDemo();
        Thread thread = new Thread(conditionDemo);
        thread.start();
        Thread.sleep(2000);

        lock.lock();
        System.out.println("Main");
        condition.signal();
        lock.unlock();
    }

}
