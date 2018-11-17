package com.xzy.jvmlearning.clazz.loading.demo1;

/**
 * -XX:+TraceClassLoading
 *  通过子类引用父类的静态字段，不会导致子类的初始化
 * @author RuzzZZ
 * @since 10/01/2018 3:32 PM
 */
public class ClazzLoading {

    public static void main(String[] args) {
        System.out.println(SubClass.Value);
    }

}