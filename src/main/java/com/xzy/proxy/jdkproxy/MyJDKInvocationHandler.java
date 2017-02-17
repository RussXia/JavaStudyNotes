package com.xzy.proxy.jdkproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Created by RuzzZZ on 2017/2/15.
 */
public class MyJDKInvocationHandler implements InvocationHandler {

    private Object target;

    public MyJDKInvocationHandler(Object target) {
        super();
        this.target = target;
    }

    public MyJDKInvocationHandler() {
        super();
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if("getName".equals(method.getName())){
            System.out.println("-------before " + method.getName() + "-------");
            Object result = method.invoke(target, args);
            System.out.println("-------after " + method.getName() + "-------");
            return result;
        }else{
            Object result = method.invoke(target, args);
            return result;
        }
    }

}
