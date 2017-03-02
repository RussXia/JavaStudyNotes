package com.xzy.guava;

import com.google.common.collect.Ordering;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static java.lang.System.out;

/**
 * {@link com.google.common.collect.Ordering}
 * 是一个实现了{@link java.util.Comparator}比较器，它可以用来为构建复杂的比较器，以完成集合排序的功能。
 * Created by RuzzZZ on 2017/3/1.
 */
public class OrderingDemo {

    public static void main(String[] args) {
        Ordering<String> ordering = new Ordering<String>() {
            @Override
            public int compare(String left, String right) {
                return Integer.compare(left.length(), right.length());
            }
        };
        List<String> list = new ArrayList<String>();
        list.add("ABCDE");
        list.add("ABCDEF");
        list.add("ABCDEH");
        list.add("ABC");
        list.add("ABCDEG");
        list.add("ABCD");
        list.add("A");
        list.add("AB");
        //自然排序
//        Collections.sort(list, ordering);
        //反序
//        Collections.sort(list,ordering.reverse());
//        Collections.sort(list, ordering.reversed());
        //使用自然排序，null排在第一个
//        Collections.sort(list,ordering.nullsFirst());
//        Collections.sort(list,ordering.nullsFirst().reversed());
        //结合另一个比较器，另一个比较处理当前比较器相等的情况
        Collections.sort(list, ordering.compound(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                char[] charsO1 = o1.toCharArray();
                char[] charsO2 = o2.toCharArray();
                for (int i = 0; i < charsO1.length && i < charsO2.length; i++) {
                    if(charsO1[i] == charsO2[i])
                        continue;
                    return charsO1[i] - charsO2[i];
                }
                return 0;
            }
        }));
        for (String str : list) {
            out.println(str);
        }
    }
}
