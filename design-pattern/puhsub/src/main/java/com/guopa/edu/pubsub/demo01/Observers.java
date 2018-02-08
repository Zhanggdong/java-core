package com.guopa.edu.pubsub.demo01;

/**
 * Created by ThinkPad on 2017/12/8.
 */
public class Observers implements IObserver{
    private String name;

    public  Observers(String name) {
        this.name = name;
    }

    @Override
    public void UpdateMsg(String msg) {
        // TODO 自动生成的方法存根
        if ("我不开心".equals(msg)) {
            System.out.println(name+"说： 多喝热水");
        }
    }
}
