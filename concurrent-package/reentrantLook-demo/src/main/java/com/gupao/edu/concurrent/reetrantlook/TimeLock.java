package com.gupao.edu.concurrent.reetrantlook;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author 张贵东
 * @version 1.0
 * @Description: 限时等待
 * @date 2017/11/6 8:43
 * @since JDK 1.8
 */
public class TimeLock implements Runnable{
    public static ReentrantLock lock = new ReentrantLock();
    @Override
    public void run() {
        try {
            if (lock.tryLock(5, TimeUnit.SECONDS)){
                Thread.sleep(6000);
            }else {
                System.out.println("Get lock failed");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            if (lock.isHeldByCurrentThread()){
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) {
        TimeLock timeLock = new TimeLock();
        Thread t1 = new Thread(timeLock);
        Thread t2 = new Thread(timeLock);

        t1.start();
        t2.start();
    }
}
