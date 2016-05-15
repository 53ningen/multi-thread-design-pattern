package com.github.gomi.study.concurrent;

import org.junit.Test;

import java.util.concurrent.Semaphore;

public class SingleThreadedExecutionMainTest {

    @Test
    public void singleThreadExecutionMainTest() throws Exception {
        SingleThreadedExecutionMain.main();
    }

    @Test
    public void semaphoreTest() {
    }

}

class BoundedResource {

    private final Semaphore semaphore;

    BoundedResource(int permits) {
        this.semaphore = new Semaphore(permits);
    }

    void use() throws InterruptedException {
        semaphore.acquire();
        try {
            // do something
        } finally {
            semaphore.release();
        }
    }

}
