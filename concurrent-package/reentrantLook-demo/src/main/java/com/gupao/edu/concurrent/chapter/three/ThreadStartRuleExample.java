package com.gupao.edu.concurrent.chapter.three;

/**
 * @author 风骚的GRE
 * @Description 并发编程艺术：start()规则：如果线程A执行操作ThreadB.start()（启动线程B），那么A线程的
 *                              ThreadB.start()操作happens-before于线程B中的任意操作。
 * @date 2018/2/9.
 */
public class ThreadStartRuleExample {
    final int[] array;

    public ThreadStartRuleExample() {
        array = new int[1];
        array[0] = 1;
    }

    public static void main(String[] args) {
        final ThreadStartRuleExample obj = new ThreadStartRuleExample();
        final Thread B = new Thread(new Runnable() {
            @Override
            public void run() {
                obj.array[0] = 3;
                System.out.println(Thread.currentThread().getName()+"->启动");
            }
        });

        Thread A = new Thread(new Runnable() {
            @Override
            public void run() {
                B.start();
                System.out.println(Thread.currentThread().getName()+"->启动");
            }
        });
        A.start();
    }
}
