package com.xzy.jvmlearning;

import java.nio.ByteBuffer;
import java.util.concurrent.TimeUnit;

/**
 * @author RuzzZZ
 * @since 27/11/2017 10:13 AM
 */
public class DirectBufferMemoryOOM {

    private static final int _128M = 1024 * 1024 * 128;

    public static void main(String[] args) throws InterruptedException {
        ByteBuffer bb = ByteBuffer.allocateDirect(_128M);

        TimeUnit.SECONDS.sleep(10);
        System.out.println("ok");
    }
}