package com.xzy.concurrent2.fork;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;

public class Main {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ProductListGenerator generator = new ProductListGenerator();
        List<Product> products = generator.generate(100_000);
        Task task = new Task(products, 0, products.size(), 0.20);

        ForkJoinPool pool = new ForkJoinPool();
        System.out.println(pool.getParallelism());
        pool.execute(task);
        while(!task.isDone()) { }
        System.out.println(products.get(0).getPrice());

    }
}
