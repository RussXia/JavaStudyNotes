package com.xzy.concurrent2;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class Test {

    static class SumTask extends RecursiveTask<Integer> {

        private static final int SEQUENTIAL_THRESHOLD = 10_0000;

        private List<Integer> data;

        public SumTask(List<Integer> data) {
            this.data = data;
        }

        @Override
        protected Integer compute() {
            if (data.size() <= SEQUENTIAL_THRESHOLD) { // base case
                Integer sum = computeSumDirectly();
                //System.out.format("Sum of %s: %d\n", data.toString(), sum);
                return sum;
            } else { // recursive case
                // Calculate new range
                int mid = data.size() / 2;
                SumTask firstSubTask =
                        new SumTask(data.subList(0, mid));
                SumTask secondSubTask =
                        new SumTask(data.subList(mid, data.size()));

                // queue the first task
                firstSubTask.fork();

                // Return the sum of all subtasks
                return secondSubTask.compute()
                        +
                        firstSubTask.join();
            }
        }

        private Integer computeSumDirectly() {
            Integer sum = 0;
            for (Integer l : data) {
                sum += l;
            }
            return sum;
        }
    }

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        Random random = new Random();
        random.setSeed(0x123456789L);
        for (int i = 0; i < 100_000_000; i++) {
            list.add(random.nextInt(100));
        }
        long start = System.currentTimeMillis();
        int sum = list.parallelStream().mapToInt(Integer::intValue).sum();
        long end = System.currentTimeMillis();
        System.out.println("stream  " + sum + "-" + (end - start));

        start = System.currentTimeMillis();
        int sum1 = 0;
        for (Integer integer : list) {
            sum1 += integer;
        }
        end = System.currentTimeMillis();
        System.out.println("foreach " + sum1 + "-" + (end - start));

        ForkJoinPool forkJoinPool = new ForkJoinPool(Runtime.getRuntime().availableProcessors());
        System.out.println(forkJoinPool.getParallelism());
        SumTask task = new SumTask(list);
        forkJoinPool.invoke(task);
    }
}
