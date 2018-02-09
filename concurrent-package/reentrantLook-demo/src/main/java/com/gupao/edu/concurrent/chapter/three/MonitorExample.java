package com.gupao.edu.concurrent.chapter.three;

/**
 * @author 风骚的GRE
 * @Description 并发编程艺术：锁的释放-获取建立的happens-before关系
 * @date 2018/2/9.
 */
public class MonitorExample {
    int a = 0;
    public synchronized void writer() { // 1
        a++; // 2
    } // 3
    public synchronized void reader() { // 4
        int i = a; // 5
    }// 6

    public static void main(String[] args) throws InterruptedException {
        final MonitorExample monitor = new MonitorExample();

        // 如果有线程A调用writer()方法，随后线程B调用reader()方法，其happens-before规则如下：
        //根据程序次序规则，1 happens-before 2,2 happens-before 3;4 happens-before 5,5 happensbefore 6。
        //根据监视器锁规则，3 happens-before 4。
        //根据happens-before的传递性，2 happens-before 5。

        Thread A = new Thread(new Runnable() {
            @Override
            public void run() {
                monitor.writer();
                System.out.println(Thread.currentThread().getName()+"->"+monitor.a);
            }
        });

        A.start();
        A.join();

        Thread B = new Thread(new Runnable() {
            @Override
            public void run() {
                monitor.reader();
                System.out.println(Thread.currentThread().getName()+"->"+monitor.a);
            }
        });

        B.start();
    }
}
