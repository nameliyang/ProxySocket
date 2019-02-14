package com.ly.netty.channel;

import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;

public class EventHandler {

    SocketChannel socketChannel;

    Selector selector;

    ByteBuffer byteBuffer = ByteBuffer.allocate(10);


    private static final String END_TOKEN = "\r\n";


    public EventHandler(Selector selector){
        this.selector = selector;
    }

    public void registerChannel(SocketChannel socketChannel){
        this.socketChannel = socketChannel;
    }

    public void onReaderEvent(SelectionKey selectionKey) throws IOException {

        while(socketChannel.read(byteBuffer)>0){
            byteBuffer.flip();
        }
    }

    public void onWriteEvent(){
    }

    public static void main(String[] args) {
        System.out.println(File.separator);
    }

}
