package com.xzy.okhttp;

import java.io.IOException;

public class OAShopMobile {

    public static void main(String[] args) throws IOException {
        String response = OkHttpGetExample.doOAPost("http://oa.meitu.com/club/flash_sale/sku_info","{\"product_id\":530}");
        System.out.println(response);
    }
}
