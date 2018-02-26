package com.gupao.edu.concurrent.chapter.four;

import java.util.concurrent.TimeUnit;

/**
 * @author 风骚的GRE
 * @Description TODO
 * @date 2018/2/11.
 */
public class SleepUtils {
    public static final void second(long seconds) {
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {
        }
    }
}
