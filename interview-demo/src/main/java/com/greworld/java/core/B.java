package com.greworld.java.core;

/**
 * @author 风骚的GRE
 * @Description TODO
 * @date 2018/3/1.
 */
public class B
{
    public static B t1 = new B();
    public static B t2 = new B();
    {
        System.out.println("构造块");
    }
    static
    {
        System.out.println("静态块");
    }
    public static void main(String[] args)
    {
        B t = new B();
    }
}
