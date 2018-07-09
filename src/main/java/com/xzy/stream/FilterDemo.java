package com.xzy.stream;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class FilterDemo {

    public static void main(String[] args) {
        List<Person> personList = new ArrayList<>();
        Person person1 = new Person(1L, "xia1");
        personList.add(person1);
        Person person2 = new Person(2L, "xia2");
        personList.add(person2);
        Person person3 = new Person(3L, "xia3");
        personList.add(person3);
        Person person4 = new Person(4L, "xia1");
        personList.add(person4);

        List<Person> distinctPersonList = personList.stream().filter(distinctByKey(Person::getName)).collect(Collectors.toList());
        System.out.println(distinctPersonList);

    }

    public static <T> Predicate<T> distinctByKey(Function<? super T, ?> keyExtractor) {
        Set<Object> seen = ConcurrentHashMap.newKeySet();
        return t -> seen.add(keyExtractor.apply(t));
    }
}


@Data
@AllArgsConstructor
class Person {
    Long id;
    String name;
}