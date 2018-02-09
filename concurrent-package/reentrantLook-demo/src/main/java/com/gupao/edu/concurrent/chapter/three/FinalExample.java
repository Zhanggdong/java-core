package com.gupao.edu.concurrent.chapter.three;

/**
 * @author 风骚的GRE
 * @Description 并发编程艺术：final域的内存语义
 * （1）写final域的重排序规则：写final域的重排序规则禁止把final域的写重排序到构造函数之外，它包含两方面的含义：
 *                              1.1 JMM禁止编译器把final域的写重排序到构造函数之外。
 *                              1.2 编译器会在final域的写之后，构造函数return之前，插入一个StoreStore屏障。这个屏障
 *                                   禁止处理器把final域的写重排序到构造函数之外
 * （2）读final域的重排序规则：读final域的重排序规则是，在一个线程中，初次读对象引用与初次读该对象包含的final
 *                             域，JMM禁止处理器重排序这两个操作（注意，这个规则仅仅针对处理器）。编译器会在读final
 *                             域操作的前面插入一个LoadLoad屏障。
 * @date 2018/2/9.
 */
public class FinalExample {
    int i;// 普通变量
    final int j;//final 变量
    static FinalExample obj;

    public FinalExample() {// 构造函数
        i = 1; // 写普通域
        j = 2; // 写final域
    }

    public static void writer(){//写线程A执行
        obj = new FinalExample ();
    }

    public static void reader(){ // 读线程B执行
        FinalExample object = obj; // 读对象引用
        int a = object.i; // 读普通域
        int b = object.j; // 读final域
    }
}
