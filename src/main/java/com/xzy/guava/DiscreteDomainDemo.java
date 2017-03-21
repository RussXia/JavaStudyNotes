package com.xzy.guava;

import com.google.common.collect.ContiguousSet;
import com.google.common.collect.DiscreteDomain;
import com.google.common.collect.ImmutableSortedSet;
import com.google.common.collect.Range;

/**
 * {@link com.google.common.collect.DiscreteDomain}
 * 离散域：部分（但不是全部）可比较类型是离散的，即区间的上下边界都是可枚举的。
 * Created by RuzzZZ on 2017/3/21.
 */
public class DiscreteDomainDemo {

    public static void main(String[] args) {
        //set包含[2, 3, 4]
        ImmutableSortedSet<Integer> numSet = ContiguousSet.create(Range.open(1, 5), DiscreteDomain.integers());
        for (Integer num : numSet) {
            System.out.println(num);
        }
        System.out.println("========================================================");
        //set包含[1, 2, ..., Integer.MAX_VALUE]
        ContiguousSet<Integer> set = ContiguousSet.create(Range.greaterThan(0), DiscreteDomain.integers());
        System.out.println(set.size());
    }
}
