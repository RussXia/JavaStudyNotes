package com.xzy.guava;

import com.google.common.collect.Range;

/**
 * {@link com.google.common.collect.Range}
 * 区间demo
 * Created by RuzzZZ on 2017/3/21.
 */
public class RangeDemo {

    /**
     * (a..b)	open(C, C)
     * [a..b]	closed(C, C)
     * [a..b)	closedOpen(C, C)
     * (a..b]	openClosed(C, C)
     * (a..+∞)	greaterThan(C)
     * [a..+∞)	atLeast(C)
     * (-∞..b)	lessThan(C)
     * (-∞..b]	atMost(C)
     * (-∞..+∞)	all()
     */

    public static void main(String[] args) {
        Boolean flag1 = Range.open(1, 3).contains(2);
        System.out.println(flag1);
        Boolean flag2 = Range.open(1, 3).contains(1);
        System.out.println(flag2);
        Boolean flag3 = Range.open(1, 3).contains(3);
        System.out.println(flag3);
        //(-∞..3)
        System.out.println(Range.lessThan(3));
        //encloses:包含
        System.out.println(Range.open(1, 3).encloses(Range.closed(3, 4)));
        //isConnecte:相连
        System.out.println(Range.open(1, 3).isConnected(Range.closed(3, 4)));
        //intersection:交集
        System.out.println(Range.closed(3, 5).intersection(Range.open(5, 10)));
        //(5..5]  isEmpty() -->  true
        //(5..5]  isEmpty() -->  true
        System.out.println(Range.closed(3, 5).intersection(Range.open(5, 10)).isEmpty());
        //span:返回”同时包括两个区间的最小区间”，如果两个区间相连，那就是它们的并集。
        System.out.println(Range.closed(1, 5).span(Range.closed(6, 10)));//returns [1, 10]
        System.out.println(Range.closed(3, 5).span(Range.open(5, 10)));//returns [3, 10)
    }
}
