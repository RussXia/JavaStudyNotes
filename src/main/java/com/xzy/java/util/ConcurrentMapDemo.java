package com.xzy.java.util;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * {@link com.google.common.cache.Cache} 与 {@link ConcurrentMap}的区别：
 * ConcurrentMap会一直保存所有添加的元素，直到显式地移除。
 * Cache通常设定会自动回收元素，其中LoadingCache不会回收元素，只能手动释放或evicted
 * (until either evicted or manually invalidated)
 * Created by RuzzZZ on 2017/3/3.
 */
public class ConcurrentMapDemo {
    /**
     * ConcurrentHashMap        HashMap的线程安全版本
     * ConcurrentSkipListMap    TreeMap的线程安全版本
     */
    public static void main(String[] args) {
        ConcurrentMap<String, String> concurrentMap = new ConcurrentHashMap();
        //putIfAbsent是线程安全的操作
        //putIfAbsent如果对应的key不存在值，则保存，并返回null；如果存在，则返回当前的null，不保存
        System.out.println(concurrentMap.putIfAbsent("Hello", "123"));
        System.out.println(concurrentMap.putIfAbsent("Hello", "1234"));
        //getOrDefault 如果对应的key不存在，则返回默认值；如果存在，则返回对应的value
        System.out.println(concurrentMap.getOrDefault("Hello", "Test1"));
        System.out.println(concurrentMap.getOrDefault("Hello1", "Test1"));
        //当前的concurrentMap的key-value是{["Hello":"123"]}
        //remove(key,value)操作，只有当key-value都匹配的时候，才会执行remove
        System.out.println(concurrentMap.remove("Hello", "1234"));
        System.out.println(concurrentMap.remove("Hello", "123"));

        concurrentMap.putIfAbsent("Hello", "123");
        //replace(key,oldValue,newValue):与remove(key,value)操作类似
        //只有当key和oldValue的值完全对应上的时候，才会为其赋新值
        System.out.println(concurrentMap.replace("Hello", "1234", "12345"));
        System.out.println(concurrentMap.replace("Hello", "123", "1234"));
    }
}
