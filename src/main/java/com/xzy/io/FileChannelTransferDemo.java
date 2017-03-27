package com.xzy.io;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Created by RuzzZZ on 2017/3/23.
 */
public class FileChannelTransferDemo {


    public static void main(String[] args) throws IOException {
        // TODO: 2017/3/24 有点没搞懂transfer这个玩意，后面在学 
        transferFromDemo();

        transferToDemo();
    }

    public static void transferFromDemo() throws IOException {
        RandomAccessFile fromFile = new RandomAccessFile("Test1.txt", "rw");
        FileChannel fromChannel = fromFile.getChannel();
        RandomAccessFile toFile = new RandomAccessFile("Test2.txt", "rw");
        FileChannel toChannel = toFile.getChannel();
        long position = 0;
        long count = fromChannel.size();
        //Transfers bytes into this channel's file from the given readable byte channel.
        //toChannel本来指向的是Test2.tx，现在和
        toChannel.transferFrom(fromChannel, position, count);
        ByteBuffer buf = ByteBuffer.allocate(128);
        int bytesRead = fromChannel.read(buf);
        while (bytesRead != -1) {
            buf.flip();
            while (buf.hasRemaining()) {
                System.out.print((char) buf.get());
            }
            //清空缓冲区，为写数据做准备
            buf.clear();
            bytesRead = toChannel.read(buf);
        }
        System.out.println();
        System.out.println("=======over========");
    }

    public static void transferToDemo() throws IOException {
        RandomAccessFile fromFile = new RandomAccessFile("fromFile.txt", "rw");
        FileChannel      fromChannel = fromFile.getChannel();
        RandomAccessFile toFile = new RandomAccessFile("toFile.txt", "rw");
        FileChannel      toChannel = toFile.getChannel();
        long position = 0;
        long count = fromChannel.size();
        fromChannel.transferTo(position, count, toChannel);

    }
}
