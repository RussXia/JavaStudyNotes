package com.xzy.util.redis.lock;

import java.util.concurrent.TimeUnit;

/**
 * redis锁的相关方法
 * Created by RuzzZZ on 2017/6/2.
 */
public interface RedisLockManager {
    /**
     * 获取锁:
     * <p>锁的时间如果超时会删除锁,在之后可以不用再次调用<code>unLockRedis()</code></p>
     * <p>如果锁没有超时,获取锁的时间也没有超时,会继续等待</p>
     *
     * @param lockKey
     * @param timeout
     * @param unit
     * @return
     */
    String tryRedisLock(String lockKey, long timeout, TimeUnit unit);

    /**
     * 释放锁
     *
     * @param lockKey
     */
    void unLockRedis(String lockKey, String lockVal);
}
