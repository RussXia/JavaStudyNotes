package com.xzy.interview.pattern.static_proxy;

/**
 * Created by RuzzZZ on 16/09/2017.
 */
public class App {

    public static void main(String[] args) {
        UserDao userDao = new UserDao();
        UserDaoProxy proxy = new UserDaoProxy(userDao);
        proxy.save();
    }
}
