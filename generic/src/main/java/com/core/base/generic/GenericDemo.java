package com.core.base.generic;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 张贵东
 * @version 1.0
 * @Description: 泛型入门
 * @date 2017/10/28 10:29
 * @since JDK 1.8
 */
public class GenericDemo {
    public static void main(String[] args) {
        //不使用泛型存在的问题
        List list = new ArrayList();
        list.add("aaa");
        //Integer i = list.get(0);//// (type error)  compilation-time error
        /**
         * 如果使用泛型List<Integer>，那么在list中添加元素的时候就只能添加Integer类型的参数了
         * 如果添加了其他类型的参数，在编译阶段就会检查到添加的元素非法
         */
        List<Integer> ints = new ArrayList<Integer>();
        ints.add(2);
        Integer i = ints.get(0);
    }
}
