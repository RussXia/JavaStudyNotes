package com.xzy.collections;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;

import static java.lang.System.out;

/**
 * Created by RuzzZZ on 2017/3/17.
 */
public class IteratorMap {

    public static void main(String[] args) {
        Map<String, Integer> map = new HashMap<>();
        map.put("Hello1", 1);
        map.put("Hello2", 2);
        map.put("Hello3", 3);
        map.put("Hello4", 4);
        map.put("Hello5", 5);
        map.put("Hello6", 6);
        map.put("Hello7", 7);

        out.println("================遍历方法一================");
        map.forEach(new BiConsumer<String, Integer>() {
            @Override
            public void accept(String s, Integer integer) {
                out.println(s + ":" + integer);
            }
        });

        out.println("================遍历方法二(1)================");
        Iterator iterator = map.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry entry = (Map.Entry) iterator.next();
            out.println(entry.getKey() + ":" + entry.getValue());
        }
        out.println("================遍历方法二(2)================");
        Set<Map.Entry<String,Integer>> entrySet = map.entrySet();
        for(Map.Entry entry : entrySet) {
            out.println(entry.getKey() + ":" +entry.getValue());
        }
        out.println("================遍历方法三================");
        //todo keySet 遍历

    }
}
