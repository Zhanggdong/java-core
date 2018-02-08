package com.gupao.edu.concurrent.chapter.three;

/**
 * @author 风骚的GRE
 * @Description 并发编程艺术：(1) volatile写-读建立的happens-before关系
 *  （2）volatile写-读的内存语义：volatile写的内存语义如下：
 *                                当写一个volatile变量时，JMM会把该线程对应的本地内存中的共享变量值刷新到主内存。
 *                                volatile读的内存语义如下：
 *                                当读一个volatile变量时，JMM会把该线程对应的本地内存置为无效。线程接下来将从主内存中读取共享变量
 *
 * @date 2018/2/8.
 */
public class VolatileExample {
    int a = 0;
    volatile boolean flag = false;
    public void writer(){
        a = 1; // 1 普通写
        flag = true;// 2 volatile写
    }

    public void reader(){
        if (flag) {// 3 volatile读
            int i = a;// 4 普通写
        }
    }

    public static void main(String[] args) {
        // 假设线程A执行writer()方法后，线程B执行reader()方法
        // 根据happens-before规则,关系分为以下三类：
        // 1.根据程序次序规则 1 happens-before 2,3 happens-before 4
        // 2.根据Volatile规则 2 happens-before 3
        // 3.根据happens-before的传递性规则 1 happens-before 3
        final VolatileExample va = new VolatileExample();
        Thread A = new Thread(new Runnable() {
            @Override
            public void run() {
                va.writer();
                System.out.println(va.a);
                System.out.println(va.flag);
            }
        });
        A.start();
        Thread B = new Thread(new Runnable() {
            @Override
            public void run() {
                va.reader();
                System.out.println(va.a);
                System.out.println(va.flag);
            }
        });
        B.start();
    }
}
