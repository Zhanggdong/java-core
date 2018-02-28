package com.gupao.edu.concurrent.chapter.six;

import java.util.HashMap;
import java.util.UUID;

/**
 * @author 风骚的GRE
 * @Description 演示线程不安全的HashMap
 * @date 2018/2/27.
 */
public class HashMapDemo {
    /**
     * HashMap在并发执行put操作时会引起死循环，是因为多线程会导致HashMap的Entry链表
     形成环形数据结构，一旦形成环形数据结构，Entry的next节点永远不为空，就会产生死循环获
     取Entry。
     * @param args
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {
        final HashMap<String, String> map = new HashMap<String, String>(2);
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10000; i++) {
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            map.put(UUID.randomUUID().toString(), "");
                        }
                    }, "ftf" + i).start();
                }
            }
        }, "ftf");
        t.start();
        t.join();
    }
}
