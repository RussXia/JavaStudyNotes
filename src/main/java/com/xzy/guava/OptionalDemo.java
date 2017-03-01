package com.xzy.guava;

import com.google.common.base.Optional;

import static java.lang.System.out;

/**
 * Optional有两个实现子类(Absent和Present)
 * Absent代表引用为null的情况
 * Present代表存在引用的情况
 * Created by RuzzZZ on 2017/2/28.
 */
public class OptionalDemo {

    public static void main(String[] args) {
//        Optional<Integer> i = Optional.fromNullable(null);    //创建指定的Optional实例，若引用为null，则表示缺失(absent)
        Optional<Integer> i = Optional.of(5);              //创建指定的Optional实例，若引用为null，则快速失败
//        Optional<Integer> i = Optional.absent();              //创建引用缺失的Optional实例
        out.println(i.orNull());

        out.println(i.isPresent());         //如果引用存在，则返回true
        out.println(i.get());               //返回optional锁包含的引用，若引用缺失，则抛出IllegalStateException异常
        out.println(i.asSet().size());

        Optional<Integer> i1 = Optional.absent();
        out.println(i1.or(100));            //返回Optional所包含的引用，如果为null，则返回指定值
        out.println(i1.asSet().size());
        out.println(i1.asSet());

    }

}
