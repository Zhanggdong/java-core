package com.gupao.edu.concurrent.cyclicbarrier;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

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

 该例子场景：司令员召集士兵集合，然后去执行任务，然后对外宣布
 * Created by ThinkPad on 2017/11/15.
 */
public class CyclicBarrierDemo {
    public static class Soldier implements Runnable{
        private String soldier;
        private final CyclicBarrier cyclicBarrier;

        public Soldier(CyclicBarrier cyclicBarrier,String soldier) {
            this.soldier = soldier;
            this.cyclicBarrier = cyclicBarrier;
        }

        @Override
        public void run() {
            try {
                //等待所有士兵集合
                cyclicBarrier.await();
                doWork();
                //等待所有士兵完成所有工作
                cyclicBarrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }

        }

        private void doWork() {
            try {
                Thread.sleep(Math.abs(new Random().nextInt()%10000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("任务完成...");
        }
    }

    public static class BarrierRun implements Runnable{
        boolean flag;
        int N;

        public BarrierRun(boolean flag, int n) {
            this.flag = flag;
            N = n;
        }

        @Override
        public void run() {
            if (flag){
                System.out.println("司令:[士兵"+N+"个，任务完成！]");
            }else {
                System.out.println("司令:[士兵"+N+"个，集合完毕！]");
                flag = true;
            }
        }
    }

    public static void main(String[] args) {
        final int N = 10;
        Thread[] allSoldier = new Thread[10];
        boolean flag = false;
        CyclicBarrier cyclicBarrier = new CyclicBarrier(N,new BarrierRun(false,N));
        //设置屏障点，主要是为了执行这个方法
        System.out.println("集合队伍");
        for (int i = 0;i<10;i++){
            System.out.println("士兵"+i+"报道！");
            allSoldier[i] = new Thread(new Soldier(cyclicBarrier,"士兵"+i));
            allSoldier[i].start();
        }
    }
}
