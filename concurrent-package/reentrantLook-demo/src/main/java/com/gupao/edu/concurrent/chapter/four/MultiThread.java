package com.gupao.edu.concurrent.chapter.four;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;

/**
 * @author 风骚的GRE
 * @Description 并发编程艺术：Java程序天生就是多线程程序
 * @date 2018/2/11.
 */
public class MultiThread {
    public static void main(String[] args) {
        // 获取Java线程管理MXBean
        ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
        // 不需要获取同步的monitor和synchronizer信息，仅获取线程和线程堆栈信息
        ThreadInfo[] threadInfos = threadMXBean.dumpAllThreads(false, false);
        // 遍历线程信息，仅打印线程ID和线程名称信息
        for (ThreadInfo threadInfo : threadInfos) {
            System.out.println("[" + threadInfo.getThreadId() + "] " + threadInfo.
                    getThreadName());
        }
    }
}

/**
 书上
  4] Signal Dispatcher　 // 分发处理发送给JVM信号的线程
 [3] Finalizer　　　　 // 调用对象finalize方法的线程
 [2] Reference Handler // 清除Reference的线程
 [1] main　 　　　　 // main线程，用户程序入口
 =============================================
 实际运行结果：
 [6] Monitor Ctrl-Break
 [5] Attach Listener
 [4] Signal Dispatcher
 [3] Finalizer
 [2] Reference Handler
 [1] main
 */