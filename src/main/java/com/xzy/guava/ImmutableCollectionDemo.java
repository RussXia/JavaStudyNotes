package com.xzy.guava;

import com.google.common.collect.HashMultiset;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Multiset;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by RuzzZZ on 2017/3/3.
 */
public class ImmutableCollectionDemo {

    public static void main(String[] args) {
        //checkElementsNotNull(elements)  如果元素为空，抛NullPointerException
        System.out.println("*******************************************************");
        final ImmutableList<String> immutableList = ImmutableList.of("Hello","World","ABCDSE");
        System.out.println(immutableList);

        System.out.println("*******************************************************");
        final ImmutableSet<String> immutableSet = ImmutableSet.of("a","d","c","b","d","c");
        System.out.println(immutableSet);

        System.out.println("*******************************************************");
        ImmutableList immutableList1 = ImmutableList.copyOf(immutableSet);
        System.out.println(immutableList1);
        List<String> list = new ArrayList<>();
        list.add("q");
        list.add("f");
        list.add("g");
        list.add("z");
        //建议将Immutable相关变量定义为final的，因为Immutable类型的变量是可以改变的
        immutableList1 = ImmutableList.copyOf(list);
        System.out.println(immutableList1);
//        test(immutableList);

        Multiset<String> multiset = HashMultiset.create();
        multiset.add("qqq");
        multiset.add("qqqwww");
        multiset.add("qqqwww");
        multiset.add("qqqwww");
        multiset.add("qqqwww");
        multiset.add("qqqwwd");
        multiset.add("qqqwwf");
        multiset.add("qqq");
        System.out.println(multiset.count("qqq"));
        System.out.println(multiset.count("qqqwww"));
        System.out.println(multiset);

    }

    private static void test(List<String> list){
        //Immutable不可变
        //调用add方法，throw new UnsupportedOperationException();
        list.add("Hello");
    }
}
