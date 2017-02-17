package com.xzy.genericity.demo;

/**
 * Created by RuzzZZ on 2017/2/14.
 */
public enum AccountType {

    SAVING,FIXED,CURRENT;
    private AccountType(){
        System.out.println("AccountType constructor");
    }
}
