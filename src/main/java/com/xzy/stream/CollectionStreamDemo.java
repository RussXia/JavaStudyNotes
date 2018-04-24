package com.xzy.stream;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class CollectionStreamDemo {

    public static void main(String[] args) {

        //对于封装数据类型的流的构造
//        List<Integer> list = Stream.of(1, 9, 3, 7, 5, 11).filter(integer -> integer >= 5).sorted().collect(Collectors.toList());

//        List<Integer> list = Arrays.asList(1, 9, 3, 7, 5, 11).stream().filter(integer -> integer >= 5).sorted().collect(Collectors.toList());

//        Integer[] array = new Integer[]{1, 9, 3, 7, 5, 11};
//        List<Integer> list = Stream.of(array).filter(integer -> integer >= 5).sorted().collect(Collectors.toList());

//        Collection<Integer> integerCollection = new ArrayList<Integer>(){
//            {
//                this.add(1);this.add(9);this.add(3);this.add(7);this.add(5);this.add(11);
//            }
//        };
//        List<Integer> list = integerCollection.stream().filter(integer -> integer >= 5).sorted().collect(Collectors.toList());

        List<Integer> list = Arrays.stream(new Integer[]{1, 9, 3, 7, 5, 11}).filter(integer -> integer >= 5).sorted().collect(Collectors.toList());
        System.out.println(list);

        //对于基本数据类型，目前有三种对应的包装类型Stream:IntStream;LongStram;DoubleSteam
        Boolean flag = IntStream.of(new int[]{1, 3, 4, 5, 6, 7}).allMatch(p -> p > 0);
        System.out.println(flag);

        //使用peek方法可以使peek前得到的流去执行对应的action，可以起到类型遍历的效果
        //它与stream提供的forEach方法最大的不同是，peek是Intermediate操作(未"消费"掉stream),forEach是terminal操作("消费"掉了stream)
        List<Integer> peekCout = Arrays.stream(new Integer[]{1, 9, 3, 7, 5, 11}).filter(integer -> integer >= 5).sorted().peek(p -> System.out.println("peek : " + p))
                .filter(integer -> integer > 8).collect(Collectors.toList());
        System.out.println(peekCout);

        //map:使流对象中的每一个元素a都去调用对应的mapper方法成为另一个元素b
        List<Integer> mapList = Arrays.stream(new Integer[]{1, 9, 3, 7, 5, 11}).map(integer -> integer - 1).collect(Collectors.toList());
        System.out.println(mapList);

        //flatMap:1对多的map
        Stream<List<Integer>> intListStream = Stream.of(
                Arrays.asList(1),
                Arrays.asList(2, 3),
                Arrays.asList(4, 5, 6)
        );
        List<Integer> flatMapResult = intListStream.flatMap(Collection::stream).collect(Collectors.toList());
        System.out.println("size : " + flatMapResult.size() + ", collection : " + flatMapResult);

        /*
         * reduce操作等价于:
         * T result = identity;
         * for (T element : this stream)
         *      result = accumulator.apply(result, element)
         * return result;
         */
        Integer reduceResult = Arrays.stream(new Integer[]{1, 9, 3, 7, 5, 11}).reduce(0, (integer, integer2) -> {
            if(integer > integer2)
                return integer2;
            return integer;
        });
        System.out.println(reduceResult);

        Optional<Integer> reduceResult2 = Arrays.stream(new Integer[]{9, 3, 11, 7, 5, 1}).reduce((integer, integer2) -> {
            if (integer < integer2)
                return integer;
            return integer2;
        });
        System.out.println(reduceResult2.orElse(-1));


        //limit:只保留stream前n个元素;
        //skip:不保留stream的前n个元素
        List<Integer> limitSkipList = Arrays.stream(new Integer[]{9, 1, 3, 7, 5, 11}).limit(4).skip(2).collect(Collectors.toList());
        System.out.println(limitSkipList);


        List<Integer> sortedLimitSkipList = Arrays.stream(new Integer[]{9, 1, 7, 3, 5, 11}).limit(4).skip(2).sorted().collect(Collectors.toList());
        System.out.println(sortedLimitSkipList);

        //maxValue:复杂度O(n)(只需一遍找到最大值即可),sorted找最大值(O(NlogN)(类比冒泡排序)
        Optional<Integer>maxValue = Arrays.stream(new Integer[]{9, 1, 7, 3, 5, 11}).max(Integer::compareTo);
        System.out.println(maxValue.orElse(-1));
    }
}
