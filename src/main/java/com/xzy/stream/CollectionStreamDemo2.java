package com.xzy.stream;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class CollectionStreamDemo2 {

    public static void main(String[] args) {
//        upperCaseNameAndSort();
//        findNameStartWithC();
//        intRangeIteator();
        countRangeNum();
    }

    /**
     * 把所有的姓名大写、排序，再输出
     */
    public static void upperCaseNameAndSort(){
        String[] names = { "Fred Edwards", "Anna Cox", "Deborah Patterson", "Ruth Torres", "Shawn Powell",
                "Rose Thompson", "Rachel Barnes", "Eugene Ramirez", "Earl Flores", "Janice Reed", "Sarah Miller",
                "Patricia Kelly", "Carl Hall", "Craig Wright", "Martha Phillips", "Thomas Howard", "Steve Martinez",
                "Diana Bailey", "Kathleen Hughes", "Russell Anderson", "Theresa Perry" };
       List<String> list =  Arrays.stream(names).map(String::toUpperCase).sorted().collect(Collectors.toList());
        System.out.println(list);
    }

    /**
     * 从一堆姓名列表中找出以字母“C”开头的姓名
     */
    public static void findNameStartWithC(){
        String[] names = { "Fred Edwards", "Anna Cox", "Deborah Patterson", "Ruth Torres", "Shawn Powell",
                "Rose Thompson", "Rachel Barnes", "Eugene Ramirez", "Earl Flores", "Janice Reed", "Sarah Miller",
                "Patricia Kelly", "Carl Hall", "Craig Wright", "Martha Phillips", "Thomas Howard", "Steve Martinez",
                "Diana Bailey", "Kathleen Hughes", "Russell Anderson", "Theresa Perry" };
        Arrays.stream(names).filter(s -> s.startsWith("C")).collect(Collectors.toList()).forEach(System.out::println);

    }

    /**
     * 遍历0-9
     */
    public static void intRangeIteator(){
        IntStream.range(0,10).forEach(System.out::println);
    }

    /**
     * 统计列表元素个数
     */
    public static void countRangeNum(){
        System.out.println(IntStream.range(10,100).count());
    }

    public static void avaRangeNum(){
        System.out.println(IntStream.range(10,100).count());
    }
}
