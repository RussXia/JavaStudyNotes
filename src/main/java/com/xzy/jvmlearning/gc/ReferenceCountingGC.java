package com.xzy.jvmlearning.gc;

/**
 * 创建两个对象，让其互相引用;调用System.gc()方法，发现最后对象被回收了
 * 说明jvm判断对象是否可以gc使用的不是 引用计数算法
 *
 * -Xms30m -Xmx30m -XX:+PrintGCDetails
 * @author RuzzZZ
 * @since 28/11/2017 1:56 PM
 */
public class ReferenceCountingGC {

    private static final int _1M = 1024 * 1024;

    public Object instance = null;

    /**
     * 负责占用内存的成员属性
     */
    private byte[] frag = new byte[2 * _1M];

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
    }

    public static void main(String[] args) throws Throwable {
        ReferenceCountingGC obj1 = new ReferenceCountingGC();
        ReferenceCountingGC obj2 = new ReferenceCountingGC();
        obj1.instance = obj2;
        obj2.instance = obj1;
        obj1 = null;
        obj2 = null;


        System.gc();
    }
}