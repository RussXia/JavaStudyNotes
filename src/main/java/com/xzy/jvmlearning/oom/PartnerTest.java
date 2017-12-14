package com.xzy.jvmlearning.oom;

import com.alibaba.fastjson.JSONObject;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * @author RuzzZZ
 * @since 07/12/2017 1:49 PM
 */
public class PartnerTest {

    public static void main(String[] args) {
        String str = new String("Hello");

        Map<String, Object> map = new HashMap<>();

        map.put("result",Collections.singletonMap("url","www.baidu.com"));
        map.put("resultCode", 1);
        System.out.println(JSONObject.toJSONString(map));
    }
}