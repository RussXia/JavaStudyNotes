package com.xzy.guava;

import com.google.common.base.Objects;

import static java.lang.System.out;

/**
 * {@link com.google.common.base.Objects}
 * 提供了对象之间比较的equals方法(避免空指针异常)
 * 对一串对象进行哈是运算。
 * Objects.toStringHelper,firstNonNull等方法目前已被废弃
 * (在21.0的jar包版本中已被彻底移除,20.0依然存在，此处不再演示)
 * Created by RuzzZZ on 2017/3/1.
 */
public class ObjectsDemo {

    private static class Demo{
        Integer i1 = 1;
        Integer i2 = 2;
        Integer i3 = 3;
        Integer i4 = 4;
        Integer i5 = 5;
    }

    public static void main(String[] args){
        Demo demo = null;
        out.println(Objects.equal(demo,null));
        demo = new Demo();
        out.println(Objects.equal(demo,null));
        out.println("************************************************************");

        out.println(String.format("散列计算结果:[%s]",Objects.hashCode(demo.i1,demo.i2,demo.i3,demo.i4,demo.i5)));
        out.println(String.format("hashcode():[%s]",demo.hashCode()));
        out.println("************************************************************");
    }
}
