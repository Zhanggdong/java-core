package com.gupao.edu.concurrent.chapter.two;

/**
 * @author 风骚的GRE
 * @Description 并发编程艺术：从底层分析Volatile的原理
 * (1)将当前处理器缓存行的数据写回到系统内存。
 * (2)这个写回内存的操作会使在其他CPU里缓存了该内存地址的数据无效。
 * @date 2018/2/6.
 */
public class VolatileDemo {
    private static VolatileDemo instance = null;

    private VolatileDemo() {

    }

    public static VolatileDemo getInstance(){
        if (instance == null){
            instance = new VolatileDemo();
        }
        return instance;
    }
}
