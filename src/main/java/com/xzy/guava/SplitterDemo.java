package com.xzy.guava;

import com.google.common.base.Splitter;

import java.util.function.Consumer;

/**
 * guava的拆分器
 * {@link com.google.common.base.Splitter}
 * Created by RuzzZZ on 2017/3/21.
 */
public class SplitterDemo {

    public static void main(String[] args) {
        String splitStr = ",a,,b,";
        //jdk自带的分割运算工具极其的差
        String[] strArr = splitStr.split(",");
        for (String str : strArr) {
            //strArr[0]="",strArr[1]="a",strArr[2]="",strArr[3]="b",
            System.out.println(str);
        }
        System.out.println("=============================guava=============================");
        Iterable<String> iterableStr = Splitter.on(",")
                .trimResults()
                .omitEmptyStrings()
                .split(splitStr);
        iterableStr.forEach(new Consumer<String>() {
            @Override
            public void accept(String s) {
                System.out.println(s);
            }
        });
    }
}
