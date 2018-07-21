package com.xzy.reflect;

import com.xzy.serializable.CarSnapInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;


/**
 * Created by RuzzZZ on 2017/2/17.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class QueryTransportPriceForm implements Serializable {
    private static final long serialVersionUID = 3072661494960809183L;
    private String userId;
    private Long startId;
    private String name;
    private Integer test;
    private List<CarSnapInfo> carInfoList;

    public String testStr;

    public String sayHello(String str) {
        int sum = 0;
        for (int i = 0; i < 1000; i++) {
            sum += i;
        }
        return "Hello" + str;
    }
}
