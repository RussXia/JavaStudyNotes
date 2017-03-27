package com.xzy.io;


import java.io.IOException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Set;

/**
 * Created by RuzzZZ on 2017/3/24.
 */
public class SocketChannelDemo {

    public static void main(String[] args) throws IOException {
        Selector selector = Selector.open();
        //必须使用selectable的channel
        //Channel必须是非阻塞的。所以FileChannel不适用Selector，因为FileChannel不能切换为非阻塞模式。Socket channel可以正常使用。
        SocketChannel channel1 = SocketChannel.open();
        SocketChannel channel2 = SocketChannel.open();
        SocketChannel channel3 = SocketChannel.open();

        //注册channel到selector上
        channel1.configureBlocking(false);
        channel2.configureBlocking(false);
        channel3.configureBlocking(false);
        //selector,The interest set for the resulting key
        SelectionKey selectionKey = channel1.register(selector, SelectionKey.OP_READ);
        channel2.register(selector, SelectionKey.OP_WRITE);
        channel3.register(selector, SelectionKey.OP_WRITE);


        //channel1:监听多个事件
        //channel.register(selector, SelectionKey.OP_READ | SelectionKey.OP_WRITE);
        int interetOps = selectionKey.interestOps();

        int accept = interetOps & SelectionKey.OP_ACCEPT;
        int connect = interetOps & SelectionKey.OP_CONNECT;
        int read = interetOps & SelectionKey.OP_READ;
        int write = interetOps & SelectionKey.OP_WRITE;

        System.out.println("accept : " + accept);
        System.out.println("connect : " + connect);
        System.out.println("read : " + read);
        System.out.println("write : " + write);
        System.out.println(selectionKey.readyOps());

        //多个监听事件
        System.out.println("===========keys==============");
        Set<SelectionKey> selectionKeys = selector.keys();
        for (SelectionKey key : selectionKeys) {
            System.out.println(key.interestOps());
        }
//        Channel channel = selectionKey.channel();
//        Selector selector1 = selectionKey.selector();

        //attaching objects
        //可以attach一个Buffer，本次为了看效果，attach一个String
//        Buffer buf = ByteBuffer.allocate(128);
//        selectionKey.attach(buf);
        System.out.println("============attach==============");
        String str = "Hello world";
        selectionKey.attach(str);
        System.out.println(selectionKey.attachment());
    }
}
