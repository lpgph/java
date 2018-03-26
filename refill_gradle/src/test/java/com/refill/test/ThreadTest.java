package com.refill.test;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ThreadTest {

    public static void main(String[] args) {
        Account account = new Account();
        //创建线程池，设置最大并发100
        ExecutorService service = Executors.newFixedThreadPool(100);

        for (int i = 1; i <= 100; i++) {
            //存钱
            //实现Runnable接口或继承Thread执行方法
//            service.execute(new AddMoneyThread(account, 1));
            //实现Callable接口执行方法
            service.submit(new AddMoneyThread(account,1));
        }
        //关闭线程池
        service.shutdown();
        //等待终结
        while (!service.isTerminated()) {
        }
        System.out.println("账户余额：" + account.getBalance());
    }
}

/**
 * 账户
 */
class Account {
    //添加账户锁
    private Lock accountLock = new ReentrantLock();
    //账户余额
    private double balance;

    /**
     * 存款 调用同步操作
     *
     * @param money 　存入金额
     */
    public synchronized void deposit(double money) {
        //进行上锁
        accountLock.lock();
        try {
            //新的存款
            double newBalance = balance + money;
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            balance = newBalance;
        } finally {
            //解锁
            accountLock.unlock();
        }

    }

    public double getBalance() {
        return balance;
    }
}

/**
 * 存钱线程　
 * 方法一：　实现Runnable接口实现多线程
 */
//class AddMoneyThread implements Runnable {
//    //存入账户
//    private Account account;
//    //存入金钱
//    private double money;
//
//    public AddMoneyThread(Account account, double money) {
//        this.account = account;
//        this.money = money;
//    }
//
//    @Override
//    public void run() {
//        //对银行账户进行同步
//        synchronized (account) {
//            //存入金额
//            account.deposit(money);
//        }
//    }
//
//}

/**
 * 存钱线程　
 * 方法二：　继承Thread（不推荐）实现多线程
 */
//class AddMoneyThread extends Thread {
//    //存入账户
//    private Account account;
//    //存入金钱
//    private double money;
//
//    public AddMoneyThread(Account account, double money) {
//        this.account = account;
//        this.money = money;
//    }
//
//    @Override
//    public void run() {
//        //对银行账户进行同步
//        synchronized (account) {
//            //存入金额
//            account.deposit(money);
//        }
//    }
//
//}

/**
 * 存钱线程　
 * 方法三：　实现Callable接口实现多线程
 */
class AddMoneyThread implements Callable {
    //存入账户
    private Account account;
    //存入金钱
    private double money;

    AddMoneyThread(Account account, double money) {
        this.account = account;
        this.money = money;
    }

    @Override
    public Object call() throws Exception {
        //对银行账户进行同步
        synchronized (account) {
            //存入金额
            account.deposit(money);
        }
        // 线程结束后返回金额
        return account.getBalance();
    }
}