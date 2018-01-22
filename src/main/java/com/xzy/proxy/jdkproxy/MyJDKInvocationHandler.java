package com.xzy.proxy.jdkproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by RuzzZZ on 2017/2/15.
 */
public class MyJDKInvocationHandler implements InvocationHandler {

    private Object target;

    public <T> T bind(T target) {
        this.target = target;
        Object proxy = Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), this);
        return (T) proxy;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("Proxy invoke");
        return method.invoke(target, args);
    }

}
