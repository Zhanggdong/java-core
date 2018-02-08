package com.guopa.edu.pubsub.demo02;

/**
 * Created by ThinkPad on 2017/12/11.
 */
public class TestString {
    public static void main(String[] args) {
        String str = "abdc;ddd;fff;";
        //str=str.replace(str.charAt(str.length()-1)+"","");
        str = str.substring(0,str.length()-1);
        System.out.println(str);
    }
}
