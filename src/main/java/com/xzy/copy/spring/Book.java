package com.xzy.copy.spring;

import com.xzy.copy.spring.BaseDomain;
import lombok.Data;

@Data
public class Book extends BaseDomain {

    private Integer bookId;

    private String bookName;

    private String author;

    private Person person;
}
