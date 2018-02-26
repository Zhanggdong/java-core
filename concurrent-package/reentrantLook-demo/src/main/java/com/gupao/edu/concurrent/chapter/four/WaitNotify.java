package com.gupao.edu.concurrent.chapter.four;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @author 风骚的GRE
 * @Description 并发编程艺术：等待/通知机制
 * (1)使用wait()、notify()和notifyAll()时需要先对调用对象加锁
 * (2)调用wait()方法后，线程状态由RUNNING变为WAITING，并将当前线程放置到对象的等待队列
 * (3)notify()或notifyAll()方法调用后，等待线程依旧不会从wait()返回，需要调用notify()或notifAll()
 *  的线程释放锁之后，等待线程才有机会从wait()返回
 *  (4)notify()方法将等待队列中的一个等待线程从等待队列中移到同步队列中，而notifyAll()方法则是将
 *  等待队列中所有的线程全部移到同步队列，被移动的线程状态由WAITING变为BLOCKED。
 *  (5)从wait()方法返回的前提是获得了调用对象的锁
 * @date 2018/2/12.
 */
public class WaitNotify {
    static boolean flag = true;
    static Object lock = new Object();

    public static void main(String[] args) throws InterruptedException {
        Thread waitThread = new Thread(new Wait(),"WaitThread");
        waitThread.start();
        TimeUnit.SECONDS.sleep(1);
        Thread notifyThread = new Thread(new Notify(), "NotifyThread");
        notifyThread.start();
    }
    static class Wait implements Runnable{
        @Override
        public void run() {
            // 加锁，拥有lock的Monitor
            synchronized (lock){
                // 当条件不满足时，继续wait，同时释放了lock的锁
                if (flag){
                    try {
                        System.out.println(Thread.currentThread() + " flag is true. wait @ " + new SimpleDateFormat("HH:mm:ss").format(new Date()));
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    static class Notify implements Runnable{
        @Override
        public void run() {
            // 加锁，拥有lock的Monitor
           synchronized (lock){
                // 获取lock的锁，然后进行通知，通知时不会释放lock的锁，
                // 直到当前线程释放了lock后，WaitThread才能从wait方法中返回
                System.out.println(Thread.currentThread() + " hold lock. notify @ " +
                        new SimpleDateFormat("HH:mm:ss").format(new Date()));
                lock.notifyAll();
                flag = false;
                SleepUtils.second(5);
            }
            // 不获取锁：直接使用notifyAll()方法,会报错
            // Exception in thread "NotifyThread" java.lang.IllegalMonitorStateException
            lock.notifyAll();
            // 再次加锁
            synchronized (lock) {
                System.out.println(Thread.currentThread() + " hold lock again. sleep @ " + new SimpleDateFormat("HH:mm:ss").format(new Date()));
                SleepUtils.second(5);
            }

        }
    }
}

/**  等待/通知的经典范式:
 * 等待方遵循如下原则:
 1）获取对象的锁。
 2）如果条件不满足，那么调用对象的wait()方法，被通知后仍要检查条件。
 3）条件满足则执行对应的逻辑。
 对应的伪代码如下。
 synchronized(对象) {
    while(条件不满足) {
        对象.wait();
    }
    对应的处理逻辑
 }

 通知方遵循如下原则:
 1）获得对象的锁。
 2）改变条件。
 3）通知所有等待在对象上的线程。
 对应的伪代码如下。
 synchronized(对象) {
    改变条件
    对象.notifyAll();
 }
 */
