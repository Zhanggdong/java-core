package com.guopa.edu.pubsub.demo01;

/**
 * Created by ThinkPad on 2017/12/8.
 */
public class TestMain {
    public static void main(String[] args) {
        // TODO 自动生成的方法存根
        Observers observers1 = new Observers("王大锤");
        Observers observers2 = new Observers("黄晓明");
        Observers observers3 = new Observers("郑恺");
        Observers observers4 = new Observers("邓超");

        Angelababy angelababy = new Angelababy();

        // 注册添加观察者
        angelababy.addObservers(observers1);
        angelababy.addObservers(observers2);
        angelababy.addObservers(observers3);
        angelababy.addObservers(observers4);

        // 发出通知消息
        angelababy.notifyAllObservers("我不开心");

        //删除观察者
        angelababy.deleteObservers(observers1);
        System.out.println("---------------------");
        angelababy.notifyAllObservers("我不开心");
    }
}
