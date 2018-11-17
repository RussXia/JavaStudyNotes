package com.xzy.copy.test;

import lombok.Data;

import java.util.concurrent.atomic.AtomicInteger;

@Data
public class TestBooleanB extends AtomicInteger {

    private boolean testFlag = false;

    private String str;
}
