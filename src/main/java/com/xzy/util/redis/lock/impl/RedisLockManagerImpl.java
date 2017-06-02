package com.xzy.util.redis.lock.impl;

import com.xzy.util.redis.lock.RedisLockManager;

import java.util.concurrent.TimeUnit;

/**
 * Created by RuzzZZ on 2017/6/2.
 */
public class RedisLockManagerImpl implements RedisLockManager {
    @Override
    public String tryRedisLock(String lockKey, long timeout, TimeUnit unit) {
        // TODO: 2017/6/2  redis  lock
        return null;
    }

    @Override
    public void unLockRedis(String lockKey, String lockVal) {
        // TODO: 2017/6/2  redis unlock
    }
}
