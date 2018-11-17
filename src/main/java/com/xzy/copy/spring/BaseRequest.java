package com.xzy.copy.spring;

import lombok.Data;

import java.io.Serializable;

@Data
public class BaseRequest implements Serializable  {

    private Integer pageNo;

    private Integer pageSize;
}