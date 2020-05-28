package com.xzy.copy.spring;

import lombok.Data;

@Data
public class Book extends BaseDomain {

    private Integer bookId;

    private String bookName;

    private String author;

    private Person person;
}
