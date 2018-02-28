package com.greworld.datatructure.linkedlist.singleLinked;

/**
 * @author 风骚的GRE
 * @Description 单向链表节点
 * @date 2018/2/28.
 */
public class Node<T> {
    public T data;
    public Node<T> next;

    public Node(){

    }

    public Node(T data){
        this.data=data;
    }

    public Node(T data,Node<T> next){
        this.data=data;
        this.next=next;
    }
}
