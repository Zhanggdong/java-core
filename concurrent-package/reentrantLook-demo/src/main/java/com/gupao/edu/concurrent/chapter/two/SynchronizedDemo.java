package com.gupao.edu.concurrent.chapter.two;

/**
 * @author 风骚的GRE
 * @Description 并发编程艺术：synchronized原理
 * （1）对于普通同步方法，锁是当前实例对象
 * （2）对于静态同步方法，锁是当前类的Class对象。
 * （3）对于同步方法块，锁是Synchonized括号里配置的对象。
 * @date 2018/2/6.
 */
public class SynchronizedDemo {
    int i = 0;

    /**
     * 这里锁的是普通的实例
     */
    public synchronized void add(){
        i++;
    }

    /**
     * 对于静态同步方法，锁是当前类的Class对象
     */
    public static synchronized void test(){

    }

    //对于同步方法块，锁是Synchonized括号里配置的对象。
    static {

    }

}
