package com.gupao.edu.concurrent.reetrantlook;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author 张贵东
 * @version 1.0
 * @Description: 重如锁：与synchronized相比，重如锁有着更加显示的操作，
 * 程序猿必须手动的指定何时获得锁和释放锁，在同一个线程中锁是可以反复进入的
 * @date 2017/11/6 8:17
 * @since JDK 1.8
 */
public class ReetrantLook implements Runnable{

    private static ReentrantLock lock = new ReentrantLock();

    public static int i = 0;

    @Override
    public void run() {
        for (int j=0;j<1000000;j++){
            lock.lock();
            lock.lock();
            try {
                i++;
            }finally {
                lock.unlock();
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ReetrantLook t1 = new ReetrantLook();
        Thread thread1 = new Thread(t1);
        Thread thread2 = new Thread(t1);

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();

        System.out.println(i);
    }
}
