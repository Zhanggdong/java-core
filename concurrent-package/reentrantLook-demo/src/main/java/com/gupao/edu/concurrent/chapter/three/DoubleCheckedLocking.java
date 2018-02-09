package com.gupao.edu.concurrent.chapter.three;

/**
 * @author 风骚的GRE
 * @Description 并发编程艺术：双重检查锁定，如何解决这样的问题
 * @date 2018/2/9.
 */
public class DoubleCheckedLocking {//1
    private static DoubleCheckedLocking instance; // 2
    public static DoubleCheckedLocking getInstance() { // 3
        if (instance == null) { // 4:第一次检查
            synchronized (DoubleCheckedLocking.class) { // 5:加锁
                if (instance == null) // 6:第二次检查
                    instance = new DoubleCheckedLocking(); // 7:问题的根源出在这里
            } // 8
        } // 9
        return instance; // 10
    }
}

// instance = new DoubleCheckedLocking(); 可以分解为三个步骤：
/**
        memory = allocate();　　// 1：分配对象的内存空间
        ctorInstance(memory);　 // 2：初始化对象
        instance = memory;　　 // 3：设置instance指向刚分配的内存地址
 的2和3之间，可能会被重排序,导致真实的执行顺序如下：
        memory = allocate();　　// 1：分配对象的内存空间
        instance = memory;　　 // 3：设置instance指向刚分配的内存地址
        // 注意，此时对象还没有被初始化！
        ctorInstance(memory);　 // 2：初始化对象
*/