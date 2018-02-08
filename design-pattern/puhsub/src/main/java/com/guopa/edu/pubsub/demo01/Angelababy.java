package com.guopa.edu.pubsub.demo01;

import java.util.ArrayList;
import java.util.List;

/**
 * 具体主题
 * 这里选取众多屌丝追女神的例子
 * Created by ThinkPad on 2017/12/8.
 */
public class Angelababy implements IPrettyGirl{

    // 观察者集合
    private List<IObserver> mList = new ArrayList<IObserver>();// 注意这里集合的泛型用的是接口类型
    @Override
    public void addObservers(IObserver observers) {
        mList.add(observers);
    }

    @Override
    public void deleteObservers(IObserver observers) {
        mList.remove(observers);
    }

    @Override
    public void notifyAllObservers(String msg) {
        for (IObserver list : mList) {
            list.UpdateMsg(msg);
        }
    }
}
