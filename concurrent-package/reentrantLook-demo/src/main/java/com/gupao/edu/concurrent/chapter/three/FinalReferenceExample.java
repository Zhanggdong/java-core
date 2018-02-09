package com.gupao.edu.concurrent.chapter.three;

/**
 * @author 风骚的GRE
 * @Description 并发编程艺术：final域的内存语义,final修饰对象引用
 * @date 2018/2/9.
 */
public class FinalReferenceExample {
    final int[] intArray;// final是引用类型
    static FinalReferenceExample obj;
    public FinalReferenceExample () { // 构造函数
        intArray = new int[1]; // 1
        intArray[0] = 1; // 2
    }

    public static void writeOne(){// 写线程A执行
        obj = new FinalReferenceExample();// 3
    }

    public static void writeTwo(){// 写线程B执行
        obj.intArray[0] = 2;// 4
    }

    public static void reader(){// 读线程C执行
        if (obj!=null){// 5
            int temp = obj.intArray[0];// 6
        }
    }

    public static void main(String[] args) {
        final FinalReferenceExample object = new FinalReferenceExample();
        Thread A = new Thread(new Runnable() {
            @Override
            public void run() {
                object.writeOne();
            }
        });
        A.start();

        Thread B = new Thread(new Runnable() {
            @Override
            public void run() {
                object.writeTwo();
            }
        });
        B.start();

        Thread C = new Thread(new Runnable() {
            @Override
            public void run() {
                object.reader();
            }
        });
        C.start();
    }

}
