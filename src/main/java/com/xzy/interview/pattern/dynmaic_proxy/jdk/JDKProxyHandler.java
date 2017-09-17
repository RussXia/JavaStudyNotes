package com.xzy.interview.pattern.dynmaic_proxy.jdk;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Created by RuzzZZ on 17/09/2017.
 */
@Slf4j
public class JDKProxyHandler implements InvocationHandler {

    private Object target;

    public JDKProxyHandler(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        log.info("Before taget {} method {} call.", target.getClass().getSimpleName(), method.getName());
        Object returns = method.invoke(target, args);
        log.info("After taget {} method {} call.", target.getClass().getSimpleName(), method.getName());
        return returns;
    }
}
