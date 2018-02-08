package com.gupao.edu.concurrent.locksupport;

import java.util.concurrent.locks.LockSupport;

/**
 * 线程阻塞工具类LockSupport：它可以在线程内容任意位置上阻塞线程
 * 和Thread.suspend()相比，它弥补了resume()在发生前，导致线程无法继续执行的情况
 * 和Object.wait()相比，它不需要先活动某个对象的锁，也不会抛出InterruptedException()异常
 * LockSupport.pack()可以获得许可阻塞线程，LockSupport.unpack()释放线程
 *
 * LockSupport中使用Unsafe来做一些阻塞相关的操作，如park、unpark；
 * 同时也使用Unsafe来支持对Thread中的parkBlocker域的访问。
 * Created by ThinkPad on 2017/11/16.
 */
public class LockSupportDemo {
    public static Object u = new Object();
    static ChangeObjectThread t1 = new ChangeObjectThread("T1");
    static ChangeObjectThread t2 = new ChangeObjectThread("T2");
    public static class ChangeObjectThread extends Thread{
        public ChangeObjectThread(String name){
            super.setName(name);
        }

        @Override
        public void run() {
            synchronized (u){
                System.out.println("In"+getName());
                LockSupport.park();
                //Thread.currentThread().suspend();
                if (Thread.interrupted()){
                    System.out.println(getName()+"被中断了！");
                }
                System.out.println(getName()+"执行结束！");
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        t1.start();
        Thread.sleep(10000);
        t2.start();

        t1.interrupt();
        //LockSupport.unpark(t1);
        LockSupport.unpark(t2);
        //t1.resume();
        //t2.resume();

        t1.join();
        t2.join();
    }
}
