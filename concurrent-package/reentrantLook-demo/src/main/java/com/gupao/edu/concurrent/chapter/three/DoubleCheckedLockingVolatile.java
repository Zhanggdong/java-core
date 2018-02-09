package com.gupao.edu.concurrent.chapter.three;

/**
 * @author 风骚的GRE
 * @Description 并发编程艺术：双重检查锁定{@link DoubleCheckedLocking}问题解决方案1
 * @date 2018/2/9.
 */
public class DoubleCheckedLockingVolatile {//1
    private volatile static DoubleCheckedLockingVolatile instance; // 2
    public static DoubleCheckedLockingVolatile getInstance() { // 3
        if (instance == null) { // 4:第一次检查
            synchronized (DoubleCheckedLockingVolatile.class) { // 5:加锁
                if (instance == null) // 6:第二次检查
                    instance = new DoubleCheckedLockingVolatile(); // 7:问题的根源出在这里
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
        由于使用volatile来禁止2和3重排序，所以线程B得到的结果将是正确的
*/