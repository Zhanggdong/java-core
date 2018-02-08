package com.gupao.edu.concurrent.chapter.three;

/**
 * @author 风骚的GRE
 * @Description 并发编程艺术：重排序会改变多线程的执行结果
 * @date 2018/2/7.
 */
class ReOrderExample {
    int a = 0;
    boolean flag = false;
    public void writer() {
        a = 1; // 1
        flag = true; // 2
        System.out.println(a);
    }
    public void reader() {
        if (flag) { // 3
            int i = a * a; // 4
            System.out.println(a);
            System.out.println(i);
        }
    }

    public static void main(String[] args) {
        final ReOrderExample example = new ReOrderExample();
        Thread a = new Thread(new Runnable() {
            @Override
            public void run() {
                example.writer();
            }
        });
        a.start();

        Thread b = new Thread(new Runnable() {
            @Override
            public void run() {
                example.reader();
            }
        });
        b.start();

    }
}
