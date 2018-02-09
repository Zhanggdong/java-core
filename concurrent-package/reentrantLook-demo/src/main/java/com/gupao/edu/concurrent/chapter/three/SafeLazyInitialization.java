package com.gupao.edu.concurrent.chapter.three;

/**
 * @author 风骚的GRE
 * @Description 对于 {@link UnsafeLazyInitialization}类会出现线程不安全，我们做了同步
 * @date 2018/2/9.
 */
public class SafeLazyInitialization {
    private static SafeLazyInitialization instance;
    private SafeLazyInitialization(){}
    // 使用synchronized来同步：思考有什么问题
    public synchronized static SafeLazyInitialization getInstance() {
        if (instance == null) // 1：A线程执行
            instance = new SafeLazyInitialization(); // 2：B线程执行
        return instance;
    }
}
