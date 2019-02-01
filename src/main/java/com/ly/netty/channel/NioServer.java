package com.ly.netty.channel;

import java.io.IOException;
import java.net.ServerSocket;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;

public class NioServer implements  Runnable{

    public NioServer() throws IOException {
        selector =   Selector.open();
    }

    Selector selector ;

    public static void main(String[] args) throws IOException {
        new Thread(new NioServer()).start();
    }

    @Override
    public void run() {
        try {
            ServerSocket serverSocket = new ServerSocket(8080);
            ServerSocketChannel serverSocketChannel = serverSocket.getChannel();
            serverSocketChannel.configureBlocking(true);
            SelectionKey selectionKey = serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
            Set<SelectionKey> selectionKeys  = null;
            while(selector.select()>0){
                selectionKeys = selector.selectedKeys();
                Iterator<SelectionKey> iterator = selectionKeys.iterator();
                while(iterator.hasNext()){
                    SelectionKey key = iterator.next();
                    handlerKey(key);
                    iterator.remove();
                }
            }
            handlerKey(selectionKey);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void handlerKey(SelectionKey selectionKey)   {
        try{
            if(selectionKey.isAcceptable()){
                ServerSocketChannel serverSocketChannel = (ServerSocketChannel) selectionKey.channel();
                SocketChannel socketChannel = serverSocketChannel.accept();
                socketChannel.configureBlocking(false);
                EventHandler eventHandler = new EventHandler(selector);
                eventHandler.registerChannel(socketChannel);
                socketChannel.register(selector, SelectionKey.OP_READ,eventHandler);
            }else if(selectionKey.isReadable()){
                EventHandler handler = (EventHandler) selectionKey.attachment();
                handler.onReaderEvent(selectionKey);
            }else if(selectionKey.isWritable()){
                selectionKey.interestOps(SelectionKey.OP_READ);
                EventHandler handler = (EventHandler) selectionKey.attachment();
                handler.onWriteEvent();
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }





}
