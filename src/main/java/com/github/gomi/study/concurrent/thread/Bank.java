package com.github.gomi.study.concurrent.thread;

public class Bank {

    private int money;
    private String name;

    public static synchronized void hoge() {

    }

    public Bank(final String name, final int money) {
        this.name = name;
        this.money = money;
    }

    public synchronized void deposit(final int m) {
        money += m;
    }

    public synchronized boolean withdraw(final int m) {
        if(money >= m) {
            money -= m;
            return true;
        } else {
            return false;
        }
    }

    public String getName() {
        return name;
    }

}
