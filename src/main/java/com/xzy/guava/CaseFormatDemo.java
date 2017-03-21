package com.xzy.guava;

import com.google.common.base.CaseFormat;

/**
 * {@link CaseFormat}
 * CaseFormat 被用来方便地在各种 ASCII 大小写规范间转换字符串——比如，编程语言的命名规范。
 * Created by RuzzZZ on 2017/3/21.
 */
public class CaseFormatDemo {

    public static void main(String[] args) {
        //下划线大写-->小写驼峰
        String str1 = CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, "CONSTANT_NAME");
        //横线小写-->大写驼峰
        String str2 = CaseFormat.LOWER_HYPHEN.to(CaseFormat.UPPER_CAMEL, "lower-hyphen");
        //小写下划线-->大写下划线
        String str3 = CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.UPPER_UNDERSCORE, "lower_underscore");
        System.out.println(str1);
        System.out.println(str2);
        System.out.println(str3);
    }
}
