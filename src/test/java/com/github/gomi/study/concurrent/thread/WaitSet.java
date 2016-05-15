package com.github.gomi.study.concurrent.thread;

import org.junit.Test;

public class WaitSet {

    @Test
    public void waitSetTest() throws Exception {
        testMethod();
    }

    private final Object obj = new Object();

    private void testMethod() throws Exception {
        final Thread thread = new Thread(() -> {
            try {
                Thread.sleep(1000);
            } catch (final Exception e) {
                e.printStackTrace();
            }
            synchronized (obj) {
                obj.notify(); // obj のウェイトセットに入ってるスレッドを1つだけ起こす
            }
        });
        thread.start();
        synchronized (obj) {
            obj.wait(); // main thread が obj の ウェイトセットに入る
        }
    }

}
