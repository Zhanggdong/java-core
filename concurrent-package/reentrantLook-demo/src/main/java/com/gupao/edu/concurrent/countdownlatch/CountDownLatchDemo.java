package com.gupao.edu.concurrent.countdownlatch;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 同步计数器：
 CyclicBarrier和CountDownLatch 都位于java.util.concurrent 这个包下

 CountDownLatch 	            CyclicBarrier
 减计数方式 	                加计数方式
 计算为0时释放所有等待的线程 	计数达到指定值时释放所有等待线程
 计数为0时，无法重置 	        计数达到指定值时，计数置为0重新开始
 调用countDown()方法计数减一，  调用await()方法计数加1，若加1后的值
 调用await()方法只进行阻塞，    不等于构造方法的值，则线程阻塞
 对计数没任何影响
 不可重复利用 	                可重复利用
 * Created by ThinkPad on 2017/11/15.
 */
public class CountDownLatchDemo implements Runnable{
    private static CountDownLatch end = new CountDownLatch(10);
    private static CountDownLatchDemo demo = new CountDownLatchDemo();
    @Override
    public void run() {
        //模拟检查任务
        try {
            Thread.sleep(1000);
            System.out.println("Check complete");
            end.countDown();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ExecutorService exec = Executors.newFixedThreadPool(10);
        for (int i = 0;i<10;i++){
            exec.submit(demo);
        }

        //等待检查
        end.await();
        System.out.println("fire");
        exec.shutdown();
    }
}
