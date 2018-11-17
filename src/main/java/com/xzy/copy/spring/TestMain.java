package com.xzy.copy.spring;

import com.alibaba.fastjson.JSONObject;
import com.xzy.copy.spring.BaseDomain;
import com.xzy.copy.spring.Book;
import com.xzy.copy.spring.BookRequest;
import org.springframework.beans.BeanUtils;

public class TestMain {

    public static void main(String[] args) {
        BookRequest bookRequest = new BookRequest();
        bookRequest.setAuthor("qqq");
        bookRequest.setBookName("qqq");
        bookRequest.setPageSize(100);

        Book book = new Book();

        BeanUtils.copyProperties(bookRequest, book,BaseDomain.class);

        System.out.println(JSONObject.toJSONString(book));

    }
}
