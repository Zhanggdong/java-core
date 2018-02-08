package com.gupao.edu.concurrent.executor;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

/**
 * 周期性任务调度实例
 * Created by ThinkPad on 2017/11/18.
 */
public class ScheduleExecutorServiceDemo {
    public static void main(String[] args) {
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(10);
        //如果前面的任务没有完成，则调度也不会启动
        
    }
}
