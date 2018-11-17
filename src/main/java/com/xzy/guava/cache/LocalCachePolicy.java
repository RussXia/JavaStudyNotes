package com.xzy.guava.cache;

public interface LocalCachePolicy {

    /**
     * 缓存容量
     * @return
     */
    default int maximumSize() {
        return 1000;
    }

    /**
     * 过期时间
     * 到该时间后, 所有的并发请求会阻塞, 只有一个穿透重新load, load完成后, 所有阻塞的请求陆续获取新值返回
     * 有加锁解锁操作, 性能稍低
     * 但是一旦过期, 所有请求都能获得最新值
     * @return
     */
    default long expire() {
        return 1000*2;
    }
}
