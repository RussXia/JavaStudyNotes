package com.xzy.guava;

import com.google.common.collect.ImmutableList;

import java.util.List;

/**
 * Created by RuzzZZ on 2017/3/3.
 */
public class ImmutableCollectionList {

    public static void main(String[] args) {
        //checkElementsNotNull(elements)  如果元素为空，抛NullPointerException
        ImmutableList<String> immutableList = ImmutableList.of("Hello","World");
        System.out.println(immutableList);
        test(immutableList);
    }

    private static void test(List<String> list){
        //Immutable不可变
        //调用add方法，throw new UnsupportedOperationException();
        list.add("Hello");
    }
}
