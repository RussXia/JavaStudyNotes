package com.xzy.interview.pattern.dynmaic_proxy.cglib;

/**
 * Created by RuzzZZ on 17/09/2017.
 */
public class App {

    public static void main(String[] args) throws NoSuchMethodException {
        IUserDao userDao = new UserDao();
        CglibProxyHandler proxy = new CglibProxyHandler();
        IUserDao proxyUserDao = (IUserDao)proxy.getProxyObject(userDao);
        proxyUserDao.create();
    }
}
