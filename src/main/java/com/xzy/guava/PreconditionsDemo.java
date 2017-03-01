package com.xzy.guava;


import com.google.common.base.Optional;
import com.google.common.base.Preconditions;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import static java.lang.System.out;

/**
 * {@link com.google.common.base.Preconditions} 前置条件判断工具
 * Created by RuzzZZ on 2017/3/1.
 */
public class PreconditionsDemo {

    private static Random random = new Random();

    public static void main(String[] args) {
        try {
            out.println(Preconditions.checkNotNull(Optional.absent().orNull(),
                    "CheckNotNull failed![%s,%s]", "Hello World", new Date()));
        } catch (NullPointerException e) {
            e.printStackTrace();
            out.println(String.format("[Messsage : %s]",e.getMessage()));
        }
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(i);
        }
        //如果index < 0 || index >= size，则throw new IndexOutOfBoundsException
        //如果前置条件通过，则返回index
        out.println(Preconditions.checkElementIndex(random.nextInt(10),list.size()));

    }
}
