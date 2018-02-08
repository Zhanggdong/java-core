package com.gupao.edu.concurrent.reetrantlook;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author 张贵东
 * @version 1.0
 * @Description: 重入锁的好搭档，Condition条件
 * @date 2017/11/7 8:15
 * @since JDK 1.8
 */
public class ReetranLockCondition implements Runnable{

    private static ReentrantLock lock = new ReentrantLock();
    private static Condition condition = lock.newCondition();

    @Override
    public void run() {
        try {
            lock.lock();
            condition.await();
            System.out.println("Thread is going on!");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ReetranLockCondition r1 = new ReetranLockCondition();
        Thread t1 = new Thread(r1);
        t1.start();
        Thread.sleep(1000);
        //通知t1线程继续执行
        lock.lock();
        condition.signal();
        lock.unlock();
    }
}
