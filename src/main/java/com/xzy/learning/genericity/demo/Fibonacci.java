package com.xzy.learning.genericity.demo;

import com.xzy.learning.genericity.util.Generator;

/**
 * Created by RuzzZZ on 2017/1/23.
 */
public class Fibonacci implements Generator<Integer> {
    private int count = 0;

    public Integer next() {
        return fib(count++);
    }

    private int fib(int n) {
        if (n < 2)
            return 1;
        return fib(n - 2) + fib(n - 1);
    }

    public static void main(String[] args) {
//        Fibonacci fib = new Fibonacci();
//        for (int i = 0; i < 20; i++)
//            System.out.println(fib.next());
    }
}
