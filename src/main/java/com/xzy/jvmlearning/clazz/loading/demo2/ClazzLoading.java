package com.xzy.jvmlearning.clazz.loading.demo2;

/**
 * -XX:+TraceCLassLoading
 * 常量在编译阶段会被存入调用类的常量池中
 * 本质上并没有直接引用到定义常量的类，因此不会触发定义常量的类的初始化
 *
 * @author RuzzZZ
 * @since 10/01/2018 4:47 PM
 */
public class ClazzLoading {

    public static void main(String[] args) {
        System.out.println(ConstClass.HELLO_WORLD);
    }
}