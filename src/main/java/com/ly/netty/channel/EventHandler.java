package com.ly.netty.channel;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;

public class EventHandler {

    SocketChannel socketChannel;

    Selector selector;

    ByteBuffer byteBuffer = ByteBuffer.allocate(10);


    public EventHandler(Selector selector){
        this.selector = selector;
    }


    public void registerChannel(SocketChannel socketChannel){
        this.socketChannel = socketChannel;
    }

    public void onReaderEvent(SelectionKey selectionKey) throws IOException {
        int read = socketChannel.read(byteBuffer);
    }

    public void onWriteEvent(){

    }

}
