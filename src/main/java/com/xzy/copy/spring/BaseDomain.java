package com.xzy.copy.spring;

import lombok.Data;

import java.io.Serializable;

@Data
public class BaseDomain implements Serializable {

    private Integer pageNo;
    private Integer pageSize;
}
