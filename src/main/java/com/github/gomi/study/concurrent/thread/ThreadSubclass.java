package com.github.gomi.study.concurrent.thread;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.stream.IntStream;

class ThreadSubclass extends Thread {

    final Logger logger = LoggerFactory.getLogger(this.getClass());

    public void run() {
        IntStream.range(0, 10).forEach(i -> logger.info(Integer.toString(i)));
    }

}

class RunnableImpl implements Runnable {

    final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public void run() {
        IntStream.range(0, 10).forEach(i -> logger.info(Integer.toString(i)));
    }

}
