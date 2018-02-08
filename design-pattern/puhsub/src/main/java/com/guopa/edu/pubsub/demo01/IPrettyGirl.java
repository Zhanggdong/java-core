package com.guopa.edu.pubsub.demo01;

/**
 * 抽象主题角色
 * 类型是接口或者抽象类
 * Created by ThinkPad on 2017/12/8.
 */
public interface IPrettyGirl {
    /**
     * 添加观察者
     * @param observers
     */
    public void addObservers(IObserver observers);
    /**
     * 删除观察者
     * @param observers
     */
    public void deleteObservers(IObserver observers);
    /**
     * 通知所有的观察者
     * @param msg
     */
    public void notifyAllObservers(String msg);
}
