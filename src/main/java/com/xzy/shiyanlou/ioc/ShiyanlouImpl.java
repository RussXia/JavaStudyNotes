package com.xzy.shiyanlou.ioc;

/**
 * @author RuzzZZ
 * @since 25/01/2018 10:21 AM
 */
public class ShiyanlouImpl implements Shiyanlou {

    @Override
    public String toUp(String str) {
        return str == null ? null : str.toUpperCase();
    }
}