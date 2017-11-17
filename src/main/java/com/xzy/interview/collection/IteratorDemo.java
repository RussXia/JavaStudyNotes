package com.xzy.interview.collection;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Slf4j
public class IteratorDemo {

    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("Hello1");
        list.add("Hello4");
        list.add("Hello3");
        list.add("Hello2");

        for (String aList : list) {
            log.info("{}\n", aList);
        }
        Iterator<String> iterator = list.iterator();
        while(iterator.hasNext()){
            log.info("{}\n",iterator.next());
        }
    }
}
