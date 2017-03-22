package com.xzy.io;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Created by RuzzZZ on 2017/3/22.
 */
public class ReadFileDemo {

    public static void main(String[] args) throws IOException {
        //mode：r,rw,rws,rwd
        RandomAccessFile aFile = new RandomAccessFile("Test1.txt", "rw");
        FileChannel inChannel = aFile.getChannel();
        //capacity:48个字符
        ByteBuffer buf = ByteBuffer.allocate(48);
        //1024个字符的CharBuffer
//        CharBuffer buf = CharBuffer.allocate(1024);


        //buffer最终的三个属性:position()-当前位置、limit本次最大位置、capacity缓冲区上限,
        // buffer有三个操作缓冲区的方法:
        // 1.clear:position = 0;limit = capacity;mark = -1;(取消标记) 清空缓冲区
        // 2.flip:limit = position;position = 0;mark = -1;           反转缓冲区
        // 3.rewind: position = 0;mark = -1;                         单纯的把当前游标移到了缓冲区的开头
        int bytesRead = inChannel.read(buf);
        while (bytesRead != -1) {
            System.out.println("Read " + bytesRead);
            buf.flip();
            while (buf.hasRemaining()) {
                System.out.print((char) buf.get());
                //死循环...特别消耗资源...
//                buf.rewind();
            }
            //清空缓冲区，为写数据做准备
            buf.clear();
            bytesRead = inChannel.read(buf);
        }
        aFile.close();
    }
}
