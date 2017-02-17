package com.xzy.learning.proxy.cglibproxy;

import com.xzy.learning.proxy.UserService;
import com.xzy.learning.proxy.UserServiceImpl;
import com.xzy.learning.proxy.jdkproxy.MyJDKInvocationHandler;
import net.sf.cglib.proxy.Enhancer;

import java.lang.reflect.Proxy;

/**
 * Created by RuzzZZ on 2017/2/17.
 */
public class CglibInvocationHandlerTest {

    public static void main(String[] args) throws NoSuchMethodException {

        MyCglibInvocationHandler handler = new MyCglibInvocationHandler();
        //cglib主要针对的是实现类，cglib主要针对的是实现类(实际上就是继承该类)
        UserService userService = (UserService) handler.getProxy(UserService.class);
        userService.getName(1);
        System.out.println("=========================over=========================");

    }
}
