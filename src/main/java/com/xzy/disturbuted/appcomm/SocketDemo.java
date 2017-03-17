package com.xzy.disturbuted.appcomm;

/**
 * Created by RuzzZZ on 2017/3/10.
 * TCP/IP+BIO
 * socket连接-客户端demo
 */
public class SocketDemo {

    public static Integer changePriceAdjustWay(Integer priceAdjustWay) {
        if (priceAdjustWay != null && priceAdjustWay == 4) {
            return 5;
        } else if (priceAdjustWay != null && priceAdjustWay == 5) {
            return 4;
        }
        return priceAdjustWay;
    }

    public static void main(String[] args) {
        System.out.println(changePriceAdjustWay(null));
    }
}
