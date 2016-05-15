Java言語で学ぶデザインパターン 〜マルチスレッド編〜 読書メモ (1)

結城先生のデザパタ本マルチスレッド編がサクッと読めそうだったので読み進めているので、自分用メモ。ほとんど知っている内容ではあったけど、体系的に知識が入っていなかったので、よい確認になる...。

## Java 言語のスレッド
### スレッドの起動方法

スレッドを起動する方法は2つある
 
1. `Thread` クラスのサブクラスのインスタンスを使ってスレッドを起動する
2. `Runnable` インターフェースの実装クラスのインスタンスを使ってスレッドを起動する


```java
class ThreadSubclass extends Thread {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public void run() {
        IntStream.range(0, 10).forEach(i -> logger.info(Integer.toString(i)));
    }

}

class RunnableImpl implements Runnable {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public void run() {
        IntStream.range(0, 10).forEach(i -> logger.info(Integer.toString(i)));
    }

}
```

それぞれ `run()` メソッドを実装している。これらを直接呼び出した場合、呼び出したスレッドで処理が実行される。別のスレッドで実行させたい場合は、`start()` を呼び出す必要がある。以下のテストを実行すると、`run()` の呼び出しはメインスレッドから行なわれているのが観測できる。

```java
@Test
    public void threadSubclassTest() {
        final ThreadSubclass threadSubclass = new ThreadSubclass();
        threadSubclass.run();
        threadSubclass.start();
        try {
            threadSubclass.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
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
            e.printStackTrace();
        }
    }
```

スレッドの生成には `ThreadFactory` なるものを使うこともできる。これにより `Thread` を `new` せずに済む。

```java
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
```

