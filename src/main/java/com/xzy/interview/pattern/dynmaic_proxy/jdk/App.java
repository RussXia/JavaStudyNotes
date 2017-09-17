package com.xzy.interview.pattern.dynmaic_proxy.jdk;


import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Proxy;

/**
 * Created by RuzzZZ on 17/09/2017.
 */
@Slf4j
public class App {

    public static void main(String[] args) throws Throwable {
        //需要被代理的对象
        UserDao userDao = new UserDao();
        //代理实例
        Object proxy = Proxy.newProxyInstance(IUserDao.class.getClassLoader(),
                new Class[]{IUserDao.class}, new JDKProxyHandler(userDao));
        //动态代理,3个参数:1.代理对象 2.需要被调用的方法 3.需要传入的参数
        Proxy.getInvocationHandler(proxy).invoke(proxy, IUserDao.class.getMethod("save", String.class), new Object[]{"Xia"});
        log.info("========= second method ============");
        Proxy.getInvocationHandler(proxy).invoke(proxy, IUserDao.class.getMethod("create"), null);
    }
}
