package com.gupao.edu.concurrent.reetrantlook;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * 信号量
 * Created by ThinkPad on 2017/11/13.
 */
public class SemapDemo implements Runnable{
    final Semaphore semp = new Semaphore(5);

    @Override
    public void run() {
        try{
            semp.acquire();
            //耗时操作
            Thread.sleep(2000);
            System.out.println(Thread.currentThread().getId()+":done!");

        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            semp.release();
        }
    }

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(20);
        for (int i=0;i<20;i++){
            executorService.submit(new SemapDemo());
        }
    }
}
