package com.xzy.redis;

import redis.clients.jedis.Jedis;

import java.util.UUID;

public class DisturbutedRedisLock {

    public static void main(String[] args) {
        Jedis jedis = new Jedis("172.16.71.2",16379);
        String response = jedis.set("lock_test_1", UUID.randomUUID().toString(),"NX","PX",300000);
        System.out.println(response);
    }
}
