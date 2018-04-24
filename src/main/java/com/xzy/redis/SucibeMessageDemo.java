package com.xzy.redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPubSub;

public class SucibeMessageDemo {

    public static void main(String[] args) {

        Jedis jedis = new Jedis("127.0.0.1", 6379);
        JedisPubSub jedisPubSub = new JedisPubSub() {
            @Override
            public void onMessage(String channel, String message) {
                System.out.println(message);
            }

            @Override
            public void onPMessage(String pattern, String channel, String message) {
                System.out.println(message);
            }
        };
        jedis.psubscribe(jedisPubSub, "china.*");
    }
}
