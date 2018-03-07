package com.gupao.edu.concurrent.thread;

import java.util.concurrent.TimeUnit;

/**
 * @author 风骚的GRE
 * @Description TODO
 * @date 2018/3/7.
 */
public class ThreadExample {
        public static void main(String[] args) {
            final Object lock = new Object();
            new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println("Thread A is waiting to get lock");
                    synchronized(lock){
                        try {
                            System.out.println("Thread a get lock");
                            TimeUnit.SECONDS.sleep(1);
                            System.out.println("thread a do wait method");
                            lock.wait();
                            System.out.println("wait end");
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }).start();
            new Thread(new Runnable() {

                @Override
                public void run() {
                    System.out.println("Thread b is waiting to get lock");
                    synchronized (lock) {
                        System.out.println("thread b get lock");
                        try {
                            TimeUnit.SECONDS.sleep(5);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        lock.notify();
                        System.out.println("thread b do notify method");
                    }
                }
            }).start();
        }
    }
