package com.greworld.java.core;

/**
 * @author 风骚的GRE
 * @Description TODO
 * @date 2018/3/1.
 */
public class Cat extends Animal{

    public Cat(){
        System.out.printf("I am a cat");
    }
    public static void main(String[] args) {
        Cat cat=new Cat();
    }

    @Override
    void say() {

    }
}
abstract class Animal{
    abstract void say();
}
