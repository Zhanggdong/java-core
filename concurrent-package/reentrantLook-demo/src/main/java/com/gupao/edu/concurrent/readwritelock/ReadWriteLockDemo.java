package com.gupao.edu.concurrent.readwritelock;

import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 读写锁：进行读写分离，当读操作远远大于写操作时，该类型的锁就能发挥最大的功效
 * 在进行读写操作时，如果是读读操作，就不用互斥等待，读写、写写、写读需互斥等待
 * 非公平策略获取读锁逻辑：
 * 1、如果当前全局处于无锁状态，则当前线程获取读锁
 * 2、如果当前全局处于读锁状态，且队列中没有等到线程，则当前线程获取读锁
 * 3、如果当前全局处于写锁占用状态（并且不是当前线程占有），则当前线程如队尾
 * 4、如果当前线程处于读锁状态，且等待队列中第一个等待线程想要获取写锁，那么当前线程获取到
 *    读锁的条件为：当前线程获取了写锁，还未释放；
 *    当前线程获取了读锁，这一次只是重入而已；
 *    其他情况当前线程入队尾；
 * 之所以这样处理一方面是为了效率，另一方面是为了避免想要获取写锁的线程饥饿，老是得不到机会
 * 5、如果当前全局处于读锁状态，且等待队列中，第一个线程不是写锁，则当前线程可以抢占读锁
 *
 * 非公平策略获取写锁逻辑：
 * 1、如果当前全局处于无锁状态，则当前线程获取写锁
 * 2、如果当前全局处于读锁状态，则当前线程入队尾
 * 3、如果当前全局处于写锁状态，除非是重入获取写锁，否则入队尾
 * Created by ThinkPad on 2017/11/13.
 */
public class ReadWriteLockDemo {

    private static Lock lock = new ReentrantLock();
    //读写锁
    private static ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    //读锁
    private static Lock readLock = readWriteLock.readLock();
    //写锁
    private static Lock writeLock = readWriteLock.writeLock();
    //操作顺序
    private int value;

    public Object handleRead(Lock lock) throws InterruptedException {
        try {
            lock.lock();//模拟读操作
            Thread.sleep(1000);//读的操作耗时越多，读写锁的优势就越明显
            System.out.println("当前的值:"+value);
            return value;
        }finally {
            lock.unlock();
        }
    }

    public void handleWrite(Lock lock,int index) throws InterruptedException {
        try {
            lock.lock();//模拟写操作
            Thread.sleep(1000);
            value = index;
            System.out.printf("写入的值:"+value);
        }finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        final ReadWriteLockDemo demo = new ReadWriteLockDemo();
        Runnable readRunnable = new Runnable() {
            @Override
            public void run() {
                try {
                    //通过对普通的锁和读写锁的测试，发现读写锁的整个过程耗时更短，性能提升明显
                    demo.handleRead(readLock);
                    //demo.handleRead(lock);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        Runnable writeRunnable = new Runnable() {
            @Override
            public void run() {
                try {
                    demo.handleWrite(readLock,new Random().nextInt());
                    //demo.handleWrite(lock,new Random().nextInt());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        for (int i = 0;i<18;i++){
            new Thread(readRunnable).start();
        }

        for (int i = 18;i<20;i++){
            new Thread(writeRunnable).start();
        }
    }
}
