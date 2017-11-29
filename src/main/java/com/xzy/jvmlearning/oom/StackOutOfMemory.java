package com.xzy.jvmlearning.oom;

/**
 * -Xss2m -XX:+PrintGCDetails
 *
 * @author RuzzZZ
 * @since 16/11/2017 11:19 AM
 */
public class StackOutOfMemory {

    private void startThread() {
        while (true) {
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    while (true) {

                    }
                }
            });
            thread.start();
        }
    }

    public static void main(String[] args) {
        StackOutOfMemory stackOutOfMemory = new StackOutOfMemory();
        stackOutOfMemory.startThread();
    }
}