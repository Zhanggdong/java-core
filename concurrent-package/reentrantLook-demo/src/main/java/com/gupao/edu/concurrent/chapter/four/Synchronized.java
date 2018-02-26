package com.gupao.edu.concurrent.chapter.four;

/**
 * @author 风骚的GRE
 * @Description TODO
 * @date 2018/2/12.
 */
public class Synchronized {
    public static void main(String[] args) {
        // 对Synchronized Class对象进行加锁
        synchronized (Synchronized.class) {
        }
        // 静态同步方法，对Synchronized Class对象进行加锁
        m();
    }
    public static synchronized void m() {
    }
}
