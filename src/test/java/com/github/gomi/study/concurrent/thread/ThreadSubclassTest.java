package com.github.gomi.study.concurrent.thread;

import org.junit.Test;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

public class ThreadSubclassTest {

    @Test
    public void threadSubclassTest() {
        final ThreadSubclass threadSubclass = new ThreadSubclass();
        threadSubclass.run();
        threadSubclass.start();
        try {
            threadSubclass.join();
        } catch (InterruptedException e) {
            threadSubclass.logger.error("interrupted", e);
        }
    }

    @Test
    public void runnableImplTest() {
        final RunnableImpl runnable = new RunnableImpl();
        final Thread thread = new Thread(runnable);
        thread.run();
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            runnable.logger.error("interrupted", e);
        }
    }

    @Test
    public void runnableImplTestWithThreadFacotory() {
        final ThreadFactory factory = Executors.defaultThreadFactory();
        final Thread thread = factory.newThread(new RunnableImpl());
        thread.run();
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            LoggerFactory.getLogger(this.getClass()).error("interrupted", e);
        }
    }

}