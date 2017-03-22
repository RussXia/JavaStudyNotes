package com.xzy.io;

import com.google.common.base.Charsets;

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
        // TODO: 2017/3/22 乱码问题
        buf.put("Hello World!".getBytes(Charsets.UTF_8))
           .put("\t\nThis is a Test".getBytes(Charsets.UTF_8))
           .put((byte)22);
        channel.write(buf);
        channel.close();
    }
}
