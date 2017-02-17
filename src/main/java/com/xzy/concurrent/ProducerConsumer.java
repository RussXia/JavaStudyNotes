package com.xzy.concurrent;

/**
 * Created by RuzzZZ on 2017/2/8.
 */
public class ProducerConsumer {

    static class Resource{
        private int number = 0;
        /**
         * 增加公共资源
         */
        public synchronized void increace() {
            while (number != 0) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            number++;
            System.out.println("increace--->The current Thread is :"+Thread.currentThread().getName()
                    +", Number : "+number);
            notify();
        }
        /**
         * 减少公共资源
         */
        public synchronized void decreace() {
            while (number == 0) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            number--;
            System.out.println("decreace--->The current Thread is :"+Thread.currentThread().getName()
                    +", Number : "+number);
            notify();
        }
    }

    static class Producer implements Runnable{
        //Producer和Consumer共享一个resource
        private Resource resource;

        public Producer(Resource resource) {
            this.resource = resource;
        }

        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                resource.increace();
            }
        }
    }

    static class Consumer implements Runnable{
        //Producer和Consumer共享一个resource
        private Resource resource;

        public Consumer(Resource resource) {
            this.resource = resource;
        }

        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                resource.decreace();
            }
        }
    }

    public static void main(String[] args){
        Resource resource = new Resource();
        new Thread(new Producer(resource)).start();
        new Thread(new Consumer(resource)).start();
        new Thread(new Producer(resource)).start();
        new Thread(new Consumer(resource)).start();
        new Thread(new Producer(resource)).start();
        new Thread(new Consumer(resource)).start();
    }
}
