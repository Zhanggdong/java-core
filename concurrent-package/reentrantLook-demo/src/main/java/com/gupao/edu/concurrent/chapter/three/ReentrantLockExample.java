package com.gupao.edu.concurrent.chapter.three;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author 风骚的GRE
 * @Description 并发编程艺术：借助ReentrantLock的源代码，来分析锁内存语义的具体实现机制
 * @date 2018/2/9.
 */
public class ReentrantLockExample {
    int a = 0;
    ReentrantLock lock = new ReentrantLock(true);
    public void writer(){
        lock.lock();// 获取锁
        try {
            a++;
        }finally {
            lock.unlock(); // 释放锁
        }
    }

    public void reader(){
        lock.lock();//获取锁
        try {
            int i = a;
        }finally {
            lock.unlock();//释放锁
        }
    }
}
