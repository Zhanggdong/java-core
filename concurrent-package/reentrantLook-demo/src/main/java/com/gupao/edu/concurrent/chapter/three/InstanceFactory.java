package com.gupao.edu.concurrent.chapter.three;

/**
 * @author 风骚的GRE
 * @Description 并发编程艺术：双重检查锁定{@link DoubleCheckedLocking}问题解决方案2 基于类初始化的解决方案
 * @date 2018/2/9.
 */
public class InstanceFactory {
    private static class InstanceHolder {
        public static InstanceFactory instance = new InstanceFactory();
    }
    public static InstanceFactory getInstance() {
        return InstanceHolder.instance ;// 这里将导致InstanceHolder类被初始化
    }
}
