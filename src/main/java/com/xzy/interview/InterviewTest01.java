package com.xzy.interview;

/**
 * Created by RuzzZZ on 07/09/2017.
 */
public class InterviewTest01 {

    private int n = 10;

    static int m = 10;

    public static void main(String[] args) {
        InterviewTest01 test = new InterviewTest01();
        System.out.println(test.n);
    }

    public static void tetsShort2Int(){
        short s1 = 1;
//        s1 = s1 + 1; //编译错误,类型提升
        s1 += 1;
    }

    public static void testChar(){
        char c = '一';

    }
}
