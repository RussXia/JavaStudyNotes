package com.xzy.interview.pattern.static_proxy;

import lombok.extern.slf4j.Slf4j;

/**
 * Created by RuzzZZ on 16/09/2017.
 */
@Slf4j
public class UserDao implements IUserDao {
    @Override
    public void save() {
        log.info("--- Save Data ---");
    }
}
