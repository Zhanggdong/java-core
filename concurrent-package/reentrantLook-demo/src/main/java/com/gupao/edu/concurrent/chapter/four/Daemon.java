package com.gupao.edu.concurrent.chapter.four;

/**
 * @author 风骚的GRE
 * @Description 并发编程艺术：Daemon线程
 *  PS:在构建Daemon线程时，不能依靠finally块中的内容来确保执行关闭或清理资源的逻辑。
 * @date 2018/2/11.
 */
public class Daemon {

    public static void main(String[] args) {
        Thread thread = new Thread(new DaemonRunner(), "DaemonRunner");
        thread.setDaemon(true);
        thread.start();
        // 不能在线程启动之后设置Deamon线程
        //thread.setDaemon(true);
    }

    static class DaemonRunner implements Runnable{
        @Override
        public void run() {
            try {
                SleepUtils.second(10);
            } finally {
                System.out.println("DaemonThread finally run.");
            }
        }
    }
}
