package com.xzy.concurrent2.fork;

import java.util.List;
import java.util.concurrent.RecursiveAction;

public class Task extends RecursiveAction {
    private static final long serialVersionUID = 5184589233703895297L;

    private static final int THRESHOLD = 10;

    private List<Product> products;
    private int first;
    private int last;
    //产品价格的增长
    private double increment;

    public Task(List<Product> products, int first, int last, double increment) {
        this.products = products;
        this.first = first;
        this.last = last;
        this.increment = increment;
    }


    private void updatePrices() {
        for (int i = first; i < last; i++) {
            Product product = products.get(i);
            product.setPrice(product.getPrice() * (1 + increment));
        }
    }

    @Override
    protected void compute() {
        if (last - first <= THRESHOLD) {
            updatePrices();
        }
        int middle = (last + first) / 2;
        System.out.printf("Task: Pending tasks: %s\n", getQueuedTaskCount());
        Task t1 = new Task(products, first, middle + 1, increment);
        Task t2 = new Task(products, middle + 1, last, increment);
        invokeAll(t1, t2);
    }
}
