package com.xzy.learning.concurrent;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * Created by RuzzZZ on 2017/2/10.
 */
public class DelayedItem implements Delayed {

    private Integer i;

    private volatile long time;

    private static final long NANO_ORIGIN = System.nanoTime();

    public Integer getI() {
        return i;
    }

    public void setI(Integer i) {
        this.i = i;
    }

    public DelayedItem(Integer i, long time) {
        this.i = i;
        this.time = time;
    }

    public DelayedItem(Integer i) {
        this.i = i;
    }

    private static long now() {
        return System.nanoTime() - NANO_ORIGIN;
    }

    @Override
    public long getDelay(TimeUnit unit) {
        /**
         * todo 2017/2/10 此处看不懂，参照了 {@link javax.swing.TimerQueue.DelayedTimer} 的getDelay()实现
         */
        return unit.convert(time - now(), TimeUnit.NANOSECONDS);
    }

    @Override
    public int compareTo(Delayed o) {
        if (o instanceof DelayedItem) {
            DelayedItem delayedItem = (DelayedItem) o;
            if (delayedItem.getI() < this.getI()) {
                return 1;
            } else if (delayedItem.getI() == this.getI()) {
                return 0;
            } else {
                return -1;
            }
        } else {
            throw new ClassCastException("不是DelayedItem!");
        }
    }
}
