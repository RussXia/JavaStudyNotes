package com.xzy.interview.pattern.dynmaic_proxy.jdk;

import lombok.extern.slf4j.Slf4j;

/**
 * Created by RuzzZZ on 16/09/2017.
 */
@Slf4j
public class UserDao implements IUserDao {
    @Override
    public void save(String name) {
        log.info("--- Save Data ---");
        log.info("------ name is {} ------", name);
    }

    @Override
    public void create() {
        log.info("--- Save Data ---");
    }
}
