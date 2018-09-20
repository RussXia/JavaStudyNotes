package com.xzy.copy.copier;

import com.alibaba.fastjson.JSONObject;
import net.sf.cglib.beans.BeanMap;

import java.math.BigDecimal;

public class BulkMapDemo {

    public static void main(String[] args) {
        LoanApplyEntity entity = new LoanApplyEntity("ttt32321", new BigDecimal("323.33"), new BigDecimal("323.31111"), "testst");
        BeanMap beanMap = BeanMap.create(entity);
        beanMap.forEach((k, v) -> {
            System.out.println(k + "\t" + v);
        });

        System.out.println();

        JSONObject jsonObject = JSONObject.parseObject(JSONObject.toJSONString(entity));
        jsonObject.forEach((k, v) -> {
            System.out.println(k + "\t" + v);
        });
    }
}
