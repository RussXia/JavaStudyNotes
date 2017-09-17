package com.xzy.interview.pattern.dynmaic_proxy.cglib;

import lombok.extern.slf4j.Slf4j;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * Created by RuzzZZ on 17/09/2017.
 */
@Slf4j
public class CglibProxyHandler implements MethodInterceptor {

    private Object target;

    public Object getProxyObject(Object target){
        this.target = target;
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(this.target.getClass());
        enhancer.setCallback(this);
        return enhancer.create();
    }

    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
        log.info("before proxy");
        Object returns = proxy.invokeSuper(obj,args);
        log.info("after proxy");
        return returns;
    }
}
