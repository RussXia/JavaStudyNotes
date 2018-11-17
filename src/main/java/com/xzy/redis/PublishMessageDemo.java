package com.xzy.redis;

import redis.clients.jedis.Jedis;

public class PublishMessageDemo {


    public static void main(String[] args) {
        Jedis jedis = new Jedis("127.0.0.1",6379);
        jedis.publish("china.beijing", "中国北京");
        jedis.publish("china.jinan", "中国济南");
        jedis.publish("china.suzhou", "中国苏州");
        jedis.publish("china.shanghai", "中国上海");
        jedis.publish("china.hangzhou", "中国杭州");
        jedis.close();
    }
}
