package com.xzy.stream;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Consumer;
import java.util.stream.Collectors;

/**
 * @description: sort
 * @author: xzy
 * @create: 2018-09-05 16:43
 **/
public class SortDemo {

    public static void main(String[] args) {
        List<Book> bookList = new ArrayList<>();
        {
            Book book = new Book(1L, 3L, "1aaaa");
            bookList.add(book);
        }
        {
            Book book = new Book(2L, 2L, "2aaaa");
            bookList.add(book);
        }
        {
            Book book = new Book(3L, 3L, "3aaaa");
            bookList.add(book);
        }
        {
            Book book = new Book(4L, 2L, "4aaaa");
            bookList.add(book);
        }
        {
            Book book = new Book(5L, 4L, "5aaaa");
            bookList.add(book);
        }
        bookList = bookList.stream().filter(book -> book.getCategoryId()==100).collect(Collectors.toList());
        bookList.forEach(book -> {
            System.out.println("book");
        });

    }
}

@Data
@AllArgsConstructor
class Book {
    Long id;
    Long categoryId;
    String name;
}