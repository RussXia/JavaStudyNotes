package com.xzy.copy.spring;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class BookRequest extends BaseRequest {

    private Integer bookId;

    private String bookName;

    private String author;

    private Person person;
}
