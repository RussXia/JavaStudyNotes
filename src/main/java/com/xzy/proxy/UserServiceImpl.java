package com.xzy.proxy;

/**
 * Created by RuzzZZ on 2017/2/15.
 */
public class UserServiceImpl implements UserService {

    @Override
    public String getName(int id) {
        System.out.println("------getName------");
        return "Tom";
    }

    @Override
    public Integer getAge(int id) {
        System.out.println("------getAge------");
        return 10;
    }
}
