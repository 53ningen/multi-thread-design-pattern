package com.github.gomi.study.concurrent.thread;

class TestClass {

    static synchronized void staticMethodWithSynchronized() {
        // do something
    }

     static void staticMethodWithSynchronizedBlock() {
         synchronized (TestClass.class) {
             //do something
         }
     }

    synchronized void methodWithSynchronized() {
        // do something
    }

    void methodWithSynchronizedBlock() {
        synchronized (this) {
            // do something
        }
    }

}
