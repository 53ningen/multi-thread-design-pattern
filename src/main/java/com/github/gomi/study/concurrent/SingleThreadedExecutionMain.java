package com.github.gomi.study.concurrent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.stream.IntStream;


public class SingleThreadedExecutionMain {

    private static final Counter counter = new Counter(0);

    public static void main() throws Exception {
        final Runnable runnable = () -> {
            IntStream.range(0, 100).forEach(i -> counter.increment());
        };
        final Thread thread1 = new Thread(runnable);
        final Thread thread2 = new Thread(runnable);
        final Thread thread3 = new Thread(runnable);
        thread1.start();
        thread2.start();
        thread3.start();

        thread1.join();
        thread2.join();
        thread3.join();
    }

}

class Counter {

    private int count;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    Counter(final Integer count) {
        this.count = count;
    }

    public void increment() {
        count++;
        logger.info("count: {}", count);
    }

}
