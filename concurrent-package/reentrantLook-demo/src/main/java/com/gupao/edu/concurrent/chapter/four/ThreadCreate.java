package com.gupao.edu.concurrent.chapter.four;

/**
 * @author 风骚的GRE
 * @Description 并发编程艺术：创建线程
 * @date 2018/2/11.
 */
public class ThreadCreate extends Thread{
    public static void main(String[] args) {
        ThreadCreate thread = new ThreadCreate();
        // 启动线程
        thread.start();
    }

    @Override
    public void run() {
        System.out.println("线程执行run()方法");
    }
}
