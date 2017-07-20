package com.xzy.concurrent2;

import org.testng.annotations.Test;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

import static org.testng.AssertJUnit.assertEquals;

/**
 * 1.Updater只能修改它可见范围内的变量。因为Updater使用反射得到这个变量。如果变量不可见，就会出错。比如如果某变量申明为private，就是不可行的。
 * 2.为了确保变量被正确的读取，它必须是volatile类型的。如果我们原有代码中未申明这个类型，那么简单得申明一下就行，这不会引起什么问题。
 * 3.由于CAS操作会通过对象实例中的偏移量直接进行赋值，因此，它不支持static字段（Unsafe.objectFieldOffset()不支持静态变量）。
 * Created by RuzzZZ on 19/07/2017.
 */
public class AtomicIntegerFieldUpdaterDemo {

    class DemoData {
        public volatile int value1 = 1;
        volatile int value2 = 2;
        protected volatile int value3 = 3;
        private volatile int value4 = 4;
    }

    AtomicIntegerFieldUpdater<DemoData> getUpdateField(String fieldName) {
        return AtomicIntegerFieldUpdater.newUpdater(DemoData.class, fieldName);
    }

    @Test
    public void testAtomicIntegerFieldUpdater() {
        DemoData data = new DemoData();
        AtomicIntegerFieldUpdaterDemo demo = new AtomicIntegerFieldUpdaterDemo();
        //将value1设置为3
        assertEquals(demo.getUpdateField("value1").compareAndSet(data, 1, 3), true);
        assertEquals(demo.getUpdateField("value1").get(data), 3);

    }
}
