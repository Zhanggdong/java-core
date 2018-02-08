package com.gupao.edu.concurrent.chapter.three;

/**
 * @author 风骚的GRE
 * @Description 并发编程艺术：volatile内存屏障
 * @date 2018/2/8.
 */
public class VolatileBarrierExample {
    int a;
    volatile int v1 = 1;
    volatile int v2 = 2;
    void readAndWrite() {
        int i = v1; // 第一个volatile读
        int j = v2; // 第二个volatile读
        a = i + j; // 普通写
        v1 = i + 1; // 第一个volatile写
        v2 = j * 2; // 第二个 volatile写
    }

    public void othre(){
        // 其他方法
    }
}
