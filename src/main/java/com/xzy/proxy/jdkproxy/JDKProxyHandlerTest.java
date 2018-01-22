package com.xzy.proxy.jdkproxy;

import com.xzy.proxy.UserService;
import com.xzy.proxy.UserServiceImpl;

import java.lang.reflect.Proxy;

/**
 * Created by RuzzZZ on 2017/2/15.
 */
public class JDKProxyHandlerTest {

    public static void main(String[] args) {
        MyJDKInvocationHandler handler = new MyJDKInvocationHandler();
        UserService userService = handler.bind(new UserServiceImpl());
        //jdk主要针对的是接口。cglib主要针对的是实现类
        UserService userServiceProxy = (UserService) Proxy.newProxyInstance(userService.getClass().getClassLoader(),
                userService.getClass().getInterfaces(), handler);
        System.out.println("Proxy Service   :   " + userServiceProxy.getName(1));
    }
}
