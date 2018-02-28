package com.gupao.edu.concurrent.chapter.six;

import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author 风骚的GRE
 * @Description 学习ConcurrentHashMap
 * @date 2018/2/27.
 */
public class ConcurrentHashMapDemo {
    public static void main(String[] args) throws InterruptedException {
        final ConcurrentHashMap<String, String> map = new ConcurrentHashMap<String, String>(64);
        /*Thread t = new Thread(new Runnable() {
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
        t.join();*/
        map.put("index",UUID.randomUUID().toString()+"-->value");
        System.out.println(map.get("index"));
    }
}
