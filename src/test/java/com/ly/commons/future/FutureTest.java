package com.ly.commons.future;

import com.ly.commons.future.impl.DefaultTaskPromise;

import java.util.concurrent.ExecutionException;

/**
 * @author: BG320587
 * @date: 2019/5/7 15:35
 */
public class FutureTest {


    public static void main(String[] args) {

        DefaultTaskPromise promise = new DefaultTaskPromise();
        TaskFuture future = promise.getFuture();

        new Thread(() -> {
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            future.cancel(true);
        }).start();

        Object o = null;
        try {
            o = promise.get();
            System.out.println(o);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }


    }
}
