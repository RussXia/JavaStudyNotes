package com.xzy.learning.proxy.jdkproxy;

import com.xzy.learning.proxy.UserService;
import com.xzy.learning.proxy.UserServiceImpl;

import java.lang.reflect.Proxy;

/**
 * Created by RuzzZZ on 2017/2/15.
 */
public class JDKProxyHandlerTest {

    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();
        MyJDKInvocationHandler handler = new MyJDKInvocationHandler(userService);
        //jdk主要针对的是接口。cglib主要针对的是实现类
        UserService userServiceProxy = (UserService) Proxy.newProxyInstance(userService.getClass().getClassLoader(),
                userService.getClass().getInterfaces(), handler);
        System.out.println("Proxy Service   :   " + userServiceProxy.getName(1));

        System.out.println("=========================分割线=========================");

        System.out.println("Normal Service  :   " + userService.getName(1));
    }
}
