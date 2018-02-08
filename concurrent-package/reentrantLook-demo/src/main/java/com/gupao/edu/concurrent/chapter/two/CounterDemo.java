package com.gupao.edu.concurrent.chapter.two;

import sun.plugin.dom.css.Counter;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author 风骚的GRE
 * @Description 并发编程艺术：使用循环CAS实现原则操作
 * @date 2018/2/7.
 */
public class CounterDemo {
    private AtomicInteger atomicI = new AtomicInteger(0);
    private int i = 0;
    public static void main(String[] args) {
        final CounterDemo cas = new CounterDemo();
        List<Thread> ts = new ArrayList<>(600);
        long start = System.currentTimeMillis();
        for (int j = 0;j<100;j++){
            Thread t = new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int i = 0;i<1000;i++){
                        cas.count();
                        cas.safeCount();
                    }
                }
            });
            ts.add(t);
        }
        for (Thread t :ts){
            t.start();
        }
        // 等待所有线程执行完成
        for (Thread t :ts){
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(cas.i);
        System.out.println(cas.atomicI.get());
        System.out.println(System.currentTimeMillis()-start);
    }

    /**
     * 使用CAS操作实现线程安全计数器
     */
    private void safeCount(){
        for(;;){
            int i =atomicI.get();
            boolean suc = atomicI.compareAndSet(i,i++);
            if (suc){
                break;
            }
        }
    }

    /**
     * 非安全计数器
     */
    private void count(){
        i++;
    }
}
