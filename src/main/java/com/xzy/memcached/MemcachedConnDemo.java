package com.xzy.memcached;

import com.alibaba.fastjson.JSONObject;
import net.spy.memcached.CASValue;
import net.spy.memcached.MemcachedClient;
import net.spy.memcached.internal.OperationFuture;

import java.net.InetSocketAddress;

public class MemcachedConnDemo {

    public static final String KEY = "test";

    public static void main(String[] args) {
        try {
            // 本地连接 Memcached 服务
            MemcachedClient mcc = new MemcachedClient(new InetSocketAddress("127.0.0.1", 11211));
            System.out.println("Connection to server sucessful.");
            OperationFuture<Boolean> future = mcc.set(KEY, 0, "100");
            System.out.println(future.get());
            System.out.println(JSONObject.toJSONString(future.getStatus()));

            System.out.println();

            Object testInt = mcc.get(KEY);
            System.out.println(testInt);

            long incrResult = mcc.incr(KEY, 10);
            System.out.println("incrResult:" + incrResult);

            CASValue<Object> casValue = mcc.gets(KEY);
            System.out.println(casValue.getCas());
            System.out.println(casValue.getValue());
            // 关闭连接
            mcc.shutdown();

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            System.out.println("exit!");
        }
    }
}
