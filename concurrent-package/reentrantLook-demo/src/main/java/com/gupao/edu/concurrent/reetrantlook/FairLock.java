package com.gupao.edu.concurrent.reetrantlook;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author 张贵东
 * @version 1.0
 * @Description: 公平锁，使用重入锁可以实现公平锁：new ReetrantLock(boolean fair)
 * @date 2017/11/7 8:01
 * @since JDK 1.8
 */
public class FairLock implements Runnable{
    private static ReentrantLock lock = new ReentrantLock(true);

    @Override
    public void run() {
        while (true){
            try{
                lock.lock();
                System.out.println(Thread.currentThread().getName()+":获得锁");
            }finally {
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) {
        FairLock r1 = new FairLock();
        Thread t1 = new Thread(r1,"Thread_t1");
        Thread t2 = new Thread(r1,"Thread_t2");

        t1.start();
        t2.start();
    }
}
