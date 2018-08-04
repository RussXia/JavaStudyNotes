package com.xzy.concurrent2.fork2;

import java.util.concurrent.ForkJoinPool;
import java.util.stream.Stream;

public class StreamDemo {

    public static void main(String[] args) {
        {
            long start = System.currentTimeMillis();
            long res = Stream.of(1, 3, 5, 7, 8, 9, 11, 17, 19).map(integer -> occupyResource()).count();
            long end = System.currentTimeMillis();
            System.out.println("serial stream : " + (end - start));
        }

        {
            long start = System.currentTimeMillis();
            long res = Stream.of(1, 3, 5, 7, 8, 9, 11, 17, 19).parallel().map(integer -> occupyResource()).count();
            long end = System.currentTimeMillis();
            System.out.println("parallel stream : " + (end - start));
        }
        {
            long start = System.currentTimeMillis();
            int[] arr = {1, 3, 5, 7, 8, 9, 11, 17, 19};
            for (int i = 0; i < arr.length; i++) {
                occupyResource();
            }
            long end = System.currentTimeMillis();
            System.out.println("simple for each : " + (end - start));
        }
        {
            int[] arr = {1, 3, 5, 7, 8, 9, 11, 17, 19};
            long start = System.currentTimeMillis();
            ForkJoinPool fjp = new ForkJoinPool();
            System.out.println("parallel num : " + fjp.getParallelism());
            Task task = new Task(0,arr.length,arr);
            fjp.execute(task);
            while(!task.isDone()) { }
            fjp.shutdown();
            long end = System.currentTimeMillis();
            System.out.println("fork join : " + (end - start));
        }

    }


    public static int occupyResource() {
        int sum = 0;
        for (int i = 0; i < 10000; i++) {
            for (int j = 0; j < 100000; j++) {
                sum += j;
            }
        }
        return sum;
    }
}
