package com.xzy.jvmlearning.concurrent;

import java.util.Vector;

/**
 * @author RuzzZZ
 * @since 06/02/2018 1:45 PM
 */
public class VectorTest {

    private static Vector<Integer> vector = new Vector<>();

    public static void main(String[] args) {
        while (true) {
            for (int i = 0; i < 10; i++) {
                vector.add(i);
            }
            Thread removeThread = new Thread(() -> {
                try {
                    for (int i = 0; i < vector.size(); i++) {
                        vector.remove(i);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    System.exit(0);
                }
            });
            Thread printThread = new Thread(() -> {
                try {
                    for (int i = 0; i < vector.size(); i++) {
                        System.out.println(vector.get(i));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    System.exit(0);
                }

            });
            removeThread.start();
            printThread.start();
            while (Thread.activeCount() > 20) {

            }
        }
    }
}