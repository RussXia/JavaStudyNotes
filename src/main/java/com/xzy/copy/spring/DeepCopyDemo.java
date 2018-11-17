package com.xzy.copy.spring;


import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.BeanUtils;

public class DeepCopyDemo {

    public static void main(String[] args) {
        Book book = new Book();
        Person person = new Person();
        person.setAge(100);
        person.setName("dddd");
        book.setPerson(person);
        book.setBookName("ddddd");
        book.setPageSize(101);

        BookRequest request = new BookRequest();
        request.setPageNo(333);

        BeanUtils.copyProperties(book, request);

        System.out.println(book.getBookName() == request.getBookName());

        System.out.println(JSONObject.toJSONString(request));
        System.out.println(request.getPerson().getName());

        request.getPerson().setName("eeeee");
        System.out.println(request.getPerson().getName());
        System.out.println(book.getPerson().getName());

        System.out.println(request.getPerson().hashCode());
        System.out.println(request.getPerson().hashCode());

        request.setBookName("haha");
        System.out.println(request.getBookName());
        System.out.println(book.getBookName());

        request.setPageSize(199);
        System.out.println(request.getPageSize());
        System.out.println(book.getPageSize());
    }
}
