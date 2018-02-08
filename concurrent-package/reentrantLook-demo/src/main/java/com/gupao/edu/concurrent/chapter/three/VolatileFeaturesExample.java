package com.gupao.edu.concurrent.chapter.three;

/**
 * @author 风骚的GRE
 * @Description 并发编程艺术：Volatile 特性
 * @date 2018/2/8.
 */
public class VolatileFeaturesExample {
    long v1 = 0L;// 使用volatile声明64位的long型变量
    public void set(long l){
        v1 = l;// 单个volatile变量的写
    }

    public void getAndIncrement () {
        v1++; // 复合（多个）volatile变量的读/写
    }
    public long get() {
        return v1; // 单个volatile变量的读
    }

    public static void main(String[] args) {
        final VolatileFeaturesExample v = new VolatileFeaturesExample();
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                v.set(2);
                System.out.println(v.get());
            }
        });

        t1.start();

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                v.getAndIncrement();
                System.out.println(v.get());
            }
        });

        t2.start();
    }

}
