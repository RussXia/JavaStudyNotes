package com.xzy.interview.pattern.static_proxy;

import lombok.extern.slf4j.Slf4j;

/**
 * Created by RuzzZZ on 16/09/2017.
 */
@Slf4j
public class UserDaoProxy implements IUserDao {

    //代理对象
    private IUserDao target;

    public UserDaoProxy(IUserDao target) {
        this.target = target;
    }

    @Override
    public void save() {
        log.info("before call save()");
        target.save();
        log.info("after call save()");
    }
}
