package com.gupao.edu.concurrent.chapter.four;

import java.util.concurrent.TimeUnit;

/**
 * @author 风骚的GRE
 * @Description 并发编程艺术：安全地终止线程,中断状态是线程的一个标识位，而中断操作是一种简便的线程间交互方式，
 * 而这种交互方式最适合用来取消或停止任务{@link Interrupted} 除了中断以外，还可以利用一个boolean变量来控制是否
 * 需要停止任务并终止该线程{@link Shutdown}
 * 创建了一个线程 CountThread，它不断地进行变量累加，而主线程尝试对其进行中断操作和停止操作
 * @date 2018/2/11.
 */
public class Shutdown {
    public static void main(String[] args) throws InterruptedException {
        Runner one = new Runner();
        Thread countThread = new Thread(one,"CountThread");
        countThread.start();
        // 睡眠1秒，main线程对CountThread进行中断，使CountThread能够感知中断而结束
        TimeUnit.SECONDS.sleep(1000);
        countThread.interrupt();
        Runner two = new Runner();
        countThread = new Thread(two, "CountThread");
        countThread.start();
        // 睡眠1秒，main线程对Runner two进行取消，使CountThread能够感知on为false而结束
        TimeUnit.SECONDS.sleep(1);
        two.cancel();
    }
    static class Runner implements Runnable{
        private long i;
        private volatile boolean on = true;
        @Override
        public void run() {
            while (on && !Thread.currentThread().isInterrupted()){
                i++;
            }
            System.out.println("Count i = " + i);
        }
        public void cancel() {
            on = false;
        }
    }
}
