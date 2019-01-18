package com.ly;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Author: BG320587
 * @Date: 2019/1/18 18:45
 */
@SuppressWarnings("all")

public class SayHello {

   private static final  ExecutorService SERVER = Executors.newCachedThreadPool();

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(8989);

        Socket accept = serverSocket.accept();
        System.out.println("start --------------------------->");
        SERVER.submit(()->{
            try {
                BufferedReader reader = new BufferedReader(new InputStreamReader(accept.getInputStream()));
                String line = null;
                while((line=reader.readLine())!=null && !line.equalsIgnoreCase("bye")){
                    System.out.println("reader data:"+line);
                    accept.getOutputStream().write(line.getBytes());
                    accept.getOutputStream().write("\r\n".getBytes());
                    accept.getOutputStream().flush();
                }
                accept.close();

                System.out.println("close socket...................................");
            } catch (IOException e) {
                e.printStackTrace();
            }

        });

    }



}
