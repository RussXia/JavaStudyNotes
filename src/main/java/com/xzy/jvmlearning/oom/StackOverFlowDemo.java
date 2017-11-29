package com.xzy.jvmlearning.oom;

/**
 * -Xss2m -XX:+PrintGCDetails
 *
 * @author RuzzZZ
 * @since 16/11/2017 11:02 AM
 */
public class StackOverFlowDemo {

    //栈的深度
    private static long depth = 1L;

    public static void main(String[] args) {
        try {
            test();
        } catch (Throwable e) {
            System.out.println("depth : " + depth);
            throw e;
        }
    }

    private static void test() {
        depth++;
        test();
    }
}