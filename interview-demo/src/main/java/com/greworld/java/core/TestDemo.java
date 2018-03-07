package com.greworld.java.core;

/**
 * @author 风骚的GRE
 * @Description TODO
 * @date 2018/3/1.
 */
public class TestDemo{
    public static void main(String[] args) {
        StringBuffer a = new StringBuffer("A");
        StringBuffer b = new StringBuffer("B");
        operator(a, b);
        System.out.println(a + "," + b);
        System.out.println(func());
        int c = '2';
        System.out.println(c);
    }
    public static void operator(StringBuffer x, StringBuffer y) {
        x.append(y);
        y = x.append("c");
    }

    public static int func (){
        try{
            return 1;
        }catch (Exception e){
            return 2;
        }finally{
            return 3;
        }
    }
}
