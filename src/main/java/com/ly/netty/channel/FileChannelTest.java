package com.ly.netty.channel;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class FileChannelTest {
    public static void main(String[] args) throws IOException {

        RandomAccessFile randomAccessFile = new RandomAccessFile(new File("E:\\dream\\ProxySocket\\src\\main\\java\\com\\ly\\SayHello.java"), "rw");
        FileChannel fileChannel = randomAccessFile.getChannel();

        ByteBuffer byteBuffer = ByteBuffer.allocate(100);

       while( fileChannel.read(byteBuffer) >= 0 ){
            readBytes(byteBuffer);
            byteBuffer.compact();
        }
        fileChannel.close();
    }


    public static void  readBytes(ByteBuffer byteBuffer){
        byteBuffer.flip();
        while(byteBuffer.hasRemaining()){
            System.out.print((char)byteBuffer.get());
        }
    }
}
