package com.ly;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * @author: BG320587
 * @date: 2019/5/6 13:16
 */
public class SerialExecutor implements Executor {

    final Queue<Runnable> tasks = new ArrayDeque<>();
    final Executor executor;
    Runnable active;

    SerialExecutor(Executor executor) {
        this.executor = executor;
    }

    public synchronized void execute(final Runnable r) {
        tasks.offer(() -> {
            try {
                r.run();
            } finally {
                scheduleNext();
            }
        });

        if (active == null) {
            scheduleNext();
        }
    }

    private synchronized void scheduleNext() {
        if ((active = tasks.poll()) != null) {
            executor.execute(active);
        }
    }

    public static void main(String[] args) {
        SerialExecutor serialExecutor = new SerialExecutor(Executors.newCachedThreadPool());
        serialExecutor.execute(() -> System.out.println("helloWorld1"));
        serialExecutor.execute(() -> System.out.println("helloWorld2"));
    }

}
