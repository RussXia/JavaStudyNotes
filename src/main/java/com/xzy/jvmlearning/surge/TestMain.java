package com.xzy.jvmlearning.surge;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * @author RuzzZZ
 * @since 30/01/2018 3:53 PM
 */
public class TestMain {


    //需要展示资料照片的资方ID
    private static final List<Long> SHOW_PIC_CAPITAL_ID = new ArrayList<Long>() {
        {
            this.add(143669558027247616L);  //力达
            this.add(22L);  //京东
            this.add(6L);   //元通
            this.add(6363581702691831808L); //元通
        }
    };


    public static void main(String[] args) {
        System.out.println(SHOW_PIC_CAPITAL_ID.contains(143669558027247616L));
    }
}