package com.gupao.edu.concurrent.chapter.three;

/**
 * @author 风骚的GRE
 * @Description 并发编程艺术：为什么final引用不能从构造函数内“溢出”
 * @date 2018/2/9.
 */
public class FinalReferenceEscapeExample {
    final int i;
    static FinalReferenceEscapeExample obj;
    public FinalReferenceEscapeExample () {
        // 下面的1 和 2 可能会出现重排序，导致读取到的a还是默认值0
        i = 1; // 1写final域
        obj = this; // 2 this引用在此"逸出"
    }
    public static void writer() {
        new FinalReferenceEscapeExample ();
    }
    public static void reader() {
        if (obj != null) { // 3
            int temp = obj.i; // 4
        }
    }
}
