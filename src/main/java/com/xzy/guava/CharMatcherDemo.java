package com.xzy.guava;

import com.google.common.base.CharMatcher;
import com.google.common.base.Charsets;

/**
 * Created by RuzzZZ on 2017/3/21.
 */
public class CharMatcherDemo {

    public static void main(String[] args) {
        String str = "12123 abcDDaSEFDaa";
        //Charsets字符集！！！
        byte[] bytes = str.getBytes(Charsets.UTF_8);
        //删除所有的小写字母
        System.out.println(CharMatcher.javaLowerCase().removeFrom(str));
        //不支持这种链式操作！！！！
//        System.out.println(CharMatcher.javaDigit().whitespace().removeFrom(str));

        System.out.println(CharMatcher.javaUpperCase().retainFrom(str));
        /**
         * 一个according to Unicode，一个accroding to {@linkplain Character#isDigit(char)
         * 都说 only care to match ASCII digits。。并不了解什么区别
         */
        System.out.println(CharMatcher.digit().retainFrom(str));
        System.out.println(CharMatcher.javaDigit().retainFrom(str));

    }
}
