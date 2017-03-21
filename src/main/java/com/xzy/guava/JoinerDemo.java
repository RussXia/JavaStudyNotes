package com.xzy.guava;

import com.google.common.base.Joiner;

import java.util.ArrayList;
import java.util.List;

/**
 * guava的字符连接器
 * {@link com.google.common.base.Joiner}
 * Created by RuzzZZ on 2017/3/21.
 */
public class JoinerDemo {

    public static void main(String[] args) {
        List<String> strList = new ArrayList<>();
        strList.add("Hello World1");
        strList.add("Hello World2");
        strList.add(null);
        strList.add("Hello World3");
        strList.add(null);
        strList.add("Hello World4");
        strList.add("Hello World5");
        strList.add("Hello World6");
        Joiner joiner = Joiner.on(";").skipNulls();
        String result = joiner.join(strList);
//        String result = Joiner.on(",").skipNulls().join(strList);
//        String result = Joiner.on(",").join(Arrays.asList(1, 5, 7));
        System.out.println(result);
    }
}
