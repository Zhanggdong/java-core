package com.guopa.edu.pubsub.demo02;

/**
 * Created by lsl on 2017/6/30.
 */
public interface Subject {
    //这两个方法都需要一个观察者作为变量，该观察者是用来注册或被删除的。
    public void registerObserver(Observer o);
    public void removeObserver(Observer o);
    public void notifyObservers();
}
