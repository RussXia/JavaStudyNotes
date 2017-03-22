package com.xzy.io;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Created by RuzzZZ on 2017/3/22.
 */
public class WriteFIleDemo {

    public static void main(String[] args) throws IOException {
        File file = new File("Test2.txt");
        if (!file.exists()) {
            file.createNewFile();
        }
        RandomAccessFile aFile = new RandomAccessFile(file, "rw");
        FileChannel channel = aFile.getChannel();

        ByteBuffer buf = ByteBuffer.allocate(1024);
        buf.put("Hello World!".getBytes());
        channel.write(buf);
        channel.close();
    }
}
