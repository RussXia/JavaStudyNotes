package com.xzy.collections;

import java.util.ArrayList;
import java.util.List;

import static java.lang.System.out;
/**
 * list的subList操作要慎用！！！！
 * 因为它返回的是一个list的引用。所以对该list的修改会直接影响原list
 * Created by RuzzZZ on 2017/3/17.
 */
public class SubListDemo {

    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("qqqq1");
        list.add("qqqq2");
        list.add("qqqq3");
        list.add("qqqq4");
        list.add("qqqq5");
        list.add("qqqq6");
        list.add("qqqq7");
        list.add("qqqq8");
        List<String> subList = list.subList(0, list.size());
        subList.clear();
        subList.add("Hello1");
        subList.add("Hello2");
        subList.add("Hello3");
        out.println(list.size());
        out.println(subList.size());

    }

}