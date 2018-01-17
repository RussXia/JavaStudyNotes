package com.xzy.jvmlearning.tools;

import java.util.ArrayList;
import java.util.List;

/**
 * -Xms100m -Xmx100m -XX:+UseSerialGC
 * @author RuzzZZ
 * @since 21/12/2017 7:11 PM
 */
public class JConsoleDemoFillHeap {

    static class OOMObject {
        public byte[] _64kB = new byte[64 * 1024];
    }

    public static void fileHeap(int limit) throws InterruptedException {
        List<OOMObject> list = new ArrayList<>();
        for (int i = 0; i < limit; i++) {
            Thread.sleep(50L);
            list.add(new OOMObject());
        }
        System.gc();
    }

    public static void main(String[] args) throws InterruptedException {
        Thread.sleep(2000L);
        fileHeap(1000);
    }
}