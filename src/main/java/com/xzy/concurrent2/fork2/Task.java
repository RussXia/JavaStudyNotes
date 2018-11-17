package com.xzy.concurrent2.fork2;

import java.util.concurrent.RecursiveAction;

public class Task extends RecursiveAction {

    private static final int THRESHOLD = 10;

    private int first, last;

    private int[] arr;

    public Task(int first, int last, int[] arr) {
        this.first = first;
        this.last = last;
        this.arr = arr;
    }

    @Override
    protected void compute() {
        if (last - first <= THRESHOLD) {
            StreamDemo.occupyResource();
            return;
        }
        int middle = (last + first) / 2;
        Task t1 = new Task(first, middle + 1, arr);
        Task t2 = new Task(middle + 1, last, arr);
        invokeAll(t1, t2);
    }
}
