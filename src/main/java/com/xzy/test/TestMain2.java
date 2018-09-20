package com.xzy.test;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * @description: ddd
 * @author: xzy
 * @create: 2018-09-07 15:47
 **/
public class TestMain2 {

    public static void main(String[] args) throws UnsupportedEncodingException {
        String encode = URLEncoder.encode("{\"out_trade_no':\"E956A9E7A37A4ECCB4AEFB4796728C\",\"passback_params\":\"测", "UTF-8");
        System.out.println(encode);
    }
}
