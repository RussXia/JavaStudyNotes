package com.xzy.learning.genericity.demo;

/**
 * Created by RuzzZZ on 2017/2/14.
 */
public class TestMain {

    public static void main(String[] args){

        StringBuffer a = new StringBuffer("A");
        StringBuffer b = new StringBuffer("B");
        operate(a,b);
        System.out.println(a+"."+b);

    }

    public static void operate(StringBuffer a,StringBuffer b){
        a.append(b);
        b = a;
    }
}
