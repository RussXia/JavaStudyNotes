package com.xzy.io;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.Buffer;
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
        //capacity:48个字符(HeapByteBuffer)
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

        //将所有未读的数据copy到Buffer的开始地方，然后将position放到最后一个未读元素后面(这样就可以进行写操作了)
//        buf.compact();
        //将mark赋值为当前位置-->mark = position;
//        buf.mark();
        //将position移到mark位置: position = m;
//        buf.reset();

        System.out.println();
        //当两个buffer满足以下三个条件时，两个buffer相等
        /**
         * 1.有相同的类型（byte、char、int等）。
         * 2.Buffer中剩余的byte、char等的个数相等。
         * 3.Buffer中所有剩余的byte、char等都相同。(之比较剩余元素，不比较之前的元素)
         */
        ByteBuffer buf1 = ByteBuffer.allocate(48);
        buf1.put("qqqwewgfdsd".getBytes());
        ByteBuffer buf2 = ByteBuffer.allocate(48);
        buf2.put("fffwewgfdsd".getBytes());
        buf1.position(4);
        buf2.position(4);
        System.out.println(buf1.equals(buf2));
    }
}
