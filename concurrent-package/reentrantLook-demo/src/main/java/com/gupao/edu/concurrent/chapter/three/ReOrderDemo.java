package com.gupao.edu.concurrent.chapter.three;

/**
 * @author 风骚的GRE
 * @Description 并发编程艺术：重排序类型
 * 下面三种类型的数据依赖，只要改变顺序，程序的执行结果就不同
 * 编译器和处理器不会改变存在数据依赖关系的执行顺序
 * @date 2018/2/7.
 */
public class ReOrderDemo {
    /**
     * 数据依赖性：写后读，写一个变量之后再读这个变量
     */
    public void writeAfterRead(){
        int a = 1;
        int b = a;
    }

    /**
     * 数据依赖性：写后写，写一个变量之后再写这个变量
     */
    public void writeAfterWrite(){
        int a = 1;
        a = 2;
    }

    /**
     * 数据依赖性：读后写，读一个变量之后再写这个变量
     */
    public void readAfterWrite(){
        int b = 1;

        //读一个变量之后再写这个变量
        int a = b;
        b = 2;
    }
}
