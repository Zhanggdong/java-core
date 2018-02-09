package com.gupao.edu.concurrent.chapter.three;

/**
 * @author 风骚的GRE
 * @Description 并发编程艺术：不安全的懒加载
 * @date 2018/2/9.
 */
public class UnsafeLazyInitialization {
    private static UnsafeLazyInitialization instance;
    private UnsafeLazyInitialization(){}
    // 下面的方法线程不安全：在UnsafeLazyInitialization类中，假设A线程执行代码1的同时，B线程执行代码2。此时，线
    // 程A可能会看到instance引用的对象还没有完成初始化
    public static UnsafeLazyInitialization getInstance() {
        if (instance == null) // 1：A线程执行
            instance = new UnsafeLazyInitialization(); // 2：B线程执行
        return instance;
    }
}

