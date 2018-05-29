package com.xzy.util.idgen;

import com.google.common.base.Preconditions;

/**
 * 分布式ID生成器
 * 64bit
 * 第一位默认为0(不适用)+41位时间戳+10位工作机器ID()+12位序列号
 * 强依赖于系统时间，如果时间回拨(机器同步时间时、2017年闰秒等情况下可能发生)，直接抛出异常
 */
public class SnowFlakeIDGenerator {

    /**
     * 起始的时间戳,2018-1-1 00:00:00:000
     */
    private static final long START_TIME = 1514736000000L;

    /**
     * workId可以占的位数
     */
    private static final long WORKER_ID_BITS = 10L;

    /**
     * 序列号占用的位数
     */
    private static final long SEQUENCE_BITS = 12L;

    /**
     * workId需要左移的位数
     */
    private static final long WORKER_ID_LEFT_SHIFT_BITS = SEQUENCE_BITS;

    /**
     * 时间戳需要左移的位数
     */
    private static final long TIMESTAMP_LEFT_SHIFT_BITS = WORKER_ID_LEFT_SHIFT_BITS + WORKER_ID_BITS;

    /**
     * 最小的workId
     */
    private static final long MIN_WORKER_ID = 0L;

    /**
     * 最大的workId
     */
    private static final long MAX_WORKER_ID = 1L << WORKER_ID_BITS;

    /**
     * 最大序列上限
     */
    private static final long SEQUENCE_MASK = (1 << SEQUENCE_BITS) - 1;

    /**
     * 可以考虑使用zk的持久顺序节点特性设置workId
     * 也可以通过启动参数指定,或者考虑使用机器的IP(截取后10位)
     */
    private Long workerId;

    /**
     * 当前序列号
     */
    private long sequence;

    /**
     * 上次的生成器时间,起初设置为开始时间
     */
    private long lastTime = START_TIME;

    /**
     * 可以通过spring的构造器注入bean
     *
     * @param workerId 工作机器ID
     */
    public SnowFlakeIDGenerator(long workerId) {
        Preconditions.checkArgument(workerId >= MIN_WORKER_ID && workerId <= MAX_WORKER_ID, "illegal workerId : %s", workerId);
        this.workerId = workerId;
    }

    public synchronized Number generateKey() {
        long currentMillis = System.currentTimeMillis();
        // TODO: 2018/5/18 短毫秒数内，考虑自旋方式；长毫秒的话，考虑使用备用workId
        Preconditions.checkState(lastTime <= currentMillis, "Clock is moving backwards, last time is %d milliseconds, current time is %d milliseconds", lastTime, currentMillis);
        if (lastTime == currentMillis) {
            //sequence最大不能超过SEQUENCE_MASK，如果超过了,等1ms进入下一个序列
            if (0L == (sequence = ++sequence & SEQUENCE_MASK)) {
                currentMillis = waitNextMillis(lastTime);
            }
        } else {
            sequence = 0;
        }
        lastTime = currentMillis;
        //计算生成的key,左移22位，其中前12位是workId，后10位是序列化(序列号最大就是10位)
        return ((currentMillis - START_TIME) << TIMESTAMP_LEFT_SHIFT_BITS) | (workerId << WORKER_ID_LEFT_SHIFT_BITS) | sequence;
    }

    private long waitNextMillis(final long lastTime) {
        long time = System.currentTimeMillis();
        while (time <= lastTime) {
            time = System.currentTimeMillis();
        }
        return time;
    }
}
