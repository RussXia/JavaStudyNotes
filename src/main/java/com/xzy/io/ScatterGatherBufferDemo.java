package com.xzy.io;

import com.google.common.base.Charsets;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

import static java.lang.System.out;

/**
 * Created by RuzzZZ on 2017/3/23.
 */
public class ScatterGatherBufferDemo {

    public static void main(String[] args) throws IOException {
        //scatter demo:channel-->buffers
//        scatterDemo();

        //gather demo:buffers-->channel
        gatherDemo();
    }

    public static void scatterDemo() throws IOException {
        ByteBuffer header = ByteBuffer.allocate(48);
        ByteBuffer body = ByteBuffer.allocate(1024);
        ByteBuffer[] buffers = {header, body};
        RandomAccessFile aFile = new RandomAccessFile("Test1.txt", "rw");
        FileChannel inChannel = aFile.getChannel();
        //当一个buffer被写满以后，channel继续向另一个buffer写数据
        inChannel.read(buffers);
        for (ByteBuffer buffer : buffers) {
            buffer.flip();
            while (buffer.hasRemaining()) {
                out.print((char) buffer.get());
            }
        }
        inChannel.close();
        aFile.close();
    }

    public static void gatherDemo() throws IOException {
        ByteBuffer header = ByteBuffer.allocate(48);
        ByteBuffer body = ByteBuffer.allocate(128);
        ByteBuffer[] buffers = {header, body};
        RandomAccessFile aFile = new RandomAccessFile("Test1.txt", "rw");
        FileChannel inChannel = aFile.getChannel();
        header.put("Test\n".getBytes(Charsets.UTF_8));
        body.put("Hello World".getBytes(Charsets.UTF_8));
        for(ByteBuffer buffer : buffers){
            buffer.flip();
        }
        inChannel.write(buffers);
        inChannel.close();
        aFile.close();
    }
}
