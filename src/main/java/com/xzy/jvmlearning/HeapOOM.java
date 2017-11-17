package com.xzy.jvmlearning;

import java.util.ArrayList;
import java.util.List;

/**
 * -Xms20m -Xmx20m -Xmn10m -XX:+PrintGCDetails -XX:SurvivorRatio=8 -XX:+HeapDumpOnOutOfMemoryError
 *
 * @author RuzzZZ
 * @since 15/11/2017 6:37 PM
 */
public class HeapOOM {

    public static void main(String[] args) {
        List<Object> list = new ArrayList<>();
        while (true) {
            list.add(new Object());
        }
    }
}