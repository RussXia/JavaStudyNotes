package com.xzy.concurrent2;

import com.alibaba.fastjson.JSONObject;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

public class ForkJoinDemo extends RecursiveTask<Long> {
    static final int THRESHOLD = 100;
    int[] array;    //all input
    int start;
    int end;

    public ForkJoinDemo(int[] array, int start, int end) {
        this.array = array;
        this.start = start;
        this.end = end;
    }

    @Override
    protected Long compute() {
        if (end - start <= THRESHOLD) {
            // 如果任务足够小,直接计算:
            long sum = 0;
            for (int i = start; i < end; i++) {
                sum += array[i];
            }
//            try {
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {
//            }
            System.out.println(String.format(Thread.currentThread().getName() + "-compute %d~%d = %d", start, end, sum));
            return sum;
        }
        // 任务太大,一分为二:
        int middle = (end + start) / 2;
        System.out.println(String.format("split %d~%d ==> %d~%d, %d~%d", start, end, start, middle, middle, end));
        ForkJoinDemo subTask1 = new ForkJoinDemo(this.array, start, middle);
        ForkJoinDemo subTask2 = new ForkJoinDemo(this.array, middle, end);
        invokeAll(subTask1, subTask2);
        Long subResult1 = subTask1.join();
        Long subResult2 = subTask2.join();
        Long result = subResult1 + subResult2;
        System.out.println("result = " + subResult1 + " + " + subResult2 + " ==> " + result);
        return result;
    }

    public static void main(String[] args) {
        int[] array = new int[320000];
        Random random = new Random();
        Arrays.fill(array, random.nextInt(100));

        System.out.println(JSONObject.toJSONString(array));
        ForkJoinPool fjp = new ForkJoinPool(3200); // 最大并发数4
        ForkJoinTask<Long> task = new ForkJoinDemo(array, 0, array.length);
        long startTime = System.currentTimeMillis();
        Long result = fjp.invoke(task);
        long endTime = System.currentTimeMillis();
        System.out.println("Fork/join sum: " + result + " in " + (endTime - startTime) + " ms.");


        startTime = System.currentTimeMillis();
        long sum = 0L;
        for (int i = 0; i < array.length; i++) {
            sum += array[i];
        }
        endTime = System.currentTimeMillis();
        System.out.println("simple sum: " + sum + " in " + (endTime - startTime) + " ms.");
    }

}
